package com.pla.annoyingvillagers_resurrection.compat;

import com.pla.annoyingvillagers_resurrection.AnnoyingVillagers_Resurrection;
import net.corruptdog.cdm.gameasset.CorruptAnimations;
import net.corruptdog.cdm.world.CorruptWeaponCategories;
import net.corruptdog.cdm.world.item.CDAddonItems;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;
import reascer.wom.gameasset.WOMSkills;
import yesman.epicfight.api.client.forgeevent.WeaponCategoryIconRegisterEvent;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

@Mod.EventBusSubscriber(modid = AnnoyingVillagers_Resurrection.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaponsOfMiracles implements ICompatModule {
    public static void registerGuard(Event event) {
    }

    public static void regIcon(WeaponCategoryIconRegisterEvent event) {
        event.registerCategory(CorruptWeaponCategories.YAMATO, new ItemStack(CDAddonItems.YAMATO.get()));
        event.registerCategory(CorruptWeaponCategories.KATANA, new ItemStack(CDAddonItems.KATANA.get()));
        event.registerCategory(CorruptWeaponCategories.S_SWORD, new ItemStack(CDAddonItems.S_IRON_SWORD.get()));
        event.registerCategory(CorruptWeaponCategories.S_GREATSWORD, new ItemStack(CDAddonItems.S_IRON_GREATSWORD.get()));
        event.registerCategory(CorruptWeaponCategories.S_LONGSWORD, new ItemStack(CDAddonItems.S_IRON_LONGSWORD.get()));
        event.registerCategory(CorruptWeaponCategories.S_TACHI, new ItemStack(CDAddonItems.S_IRON_TACHI.get()));
        event.registerCategory(CorruptWeaponCategories.S_SPEAR, new ItemStack(CDAddonItems.S_IRON_SPEAR.get()));
        event.registerCategory(CorruptWeaponCategories.GREAT_TACHI, new ItemStack(CDAddonItems.GREAT_TACHI.get()));
    }

    public static boolean regGuarded = false;

    public static void buildSkillEvent(RegisterEvent event) {
        if (EpicFightSkills.GUARD == null) {
            return;
        }
        if (regGuarded) {
            return;
        }
        try {
            regGuard();
        } catch (Exception e) {
            e.printStackTrace();
        }
        regGuarded = true;
    }

    public static void regGuard() throws NoSuchFieldException, IllegalAccessException {
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> guardMotions = new HashMap<>();
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> guardBreakMotions = new HashMap<>();
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> advancedGuardMotions = new HashMap<>();

        guardMotions.put(CorruptWeaponCategories.KATANA, (item, player) ->
                Animations.UCHIGATANA_GUARD_HIT);
        guardBreakMotions.put(CorruptWeaponCategories.KATANA, (item, player) ->
                CorruptAnimations.GUARD_BREAK1);
        advancedGuardMotions.put(CorruptWeaponCategories.KATANA, (itemCap, playerpatch) ->
                List.of(Animations.SWORD_GUARD_ACTIVE_HIT1, Animations.SWORD_GUARD_ACTIVE_HIT2));

        guardMotions.put(CorruptWeaponCategories.YAMATO, (item, player) ->
                CorruptAnimations.YAMATO_GUARD_HIT);
        guardBreakMotions.put(CorruptWeaponCategories.YAMATO, (item, player) ->
                CorruptAnimations.GUARD_BREAK1);
        advancedGuardMotions.put(CorruptWeaponCategories.YAMATO, (itemCap, playerpatch) ->
                List.of(CorruptAnimations.YAMATO_ACTIVE_GUARD_HIT, CorruptAnimations.YAMATO_ACTIVE_GUARD_HIT2));

        guardMotions.put(CorruptWeaponCategories.S_GREATSWORD, (item, player) ->
                item.getStyle(player) == CapabilityItem.Styles.TWO_HAND ? Animations.GREATSWORD_GUARD_HIT : Animations.SWORD_DUAL_GUARD_HIT);
        guardBreakMotions.put(CorruptWeaponCategories.S_GREATSWORD, (item, player) ->
                CorruptAnimations.GUARD_BREAK2);
        advancedGuardMotions.put(CorruptWeaponCategories.S_GREATSWORD, (itemCap, playerpatch) ->
                itemCap.getStyle(playerpatch) == CapabilityItem.Styles.TWO_HAND ? List.of(Animations.LONGSWORD_GUARD_ACTIVE_HIT1, Animations.LONGSWORD_GUARD_ACTIVE_HIT2) : List.of(Animations.SWORD_GUARD_ACTIVE_HIT2, Animations.SWORD_GUARD_ACTIVE_HIT3));

        guardMotions.put(CorruptWeaponCategories.S_SWORD, (item, player) ->
                item.getStyle(player) == CapabilityItem.Styles.ONE_HAND ? Animations.SWORD_GUARD_HIT : Animations.SWORD_DUAL_GUARD_HIT);
        guardBreakMotions.put(CorruptWeaponCategories.S_SWORD, (item, player) ->
                CorruptAnimations.GUARD_BREAK1);
        advancedGuardMotions.put(CorruptWeaponCategories.S_SWORD, (itemCap, playerpatch) ->
                itemCap.getStyle(playerpatch) == CapabilityItem.Styles.ONE_HAND ? List.of(Animations.SWORD_GUARD_ACTIVE_HIT1, Animations.SWORD_GUARD_ACTIVE_HIT2) : List.of(Animations.SWORD_GUARD_ACTIVE_HIT2, Animations.SWORD_GUARD_ACTIVE_HIT3));

        guardMotions.put(CorruptWeaponCategories.S_LONGSWORD, (item, player) ->
                List.of(Animations.LONGSWORD_GUARD_ACTIVE_HIT1, Animations.LONGSWORD_GUARD_ACTIVE_HIT2));
        guardBreakMotions.put(CorruptWeaponCategories.S_LONGSWORD, (item, player) ->
                CorruptAnimations.GUARD_BREAK2);
        advancedGuardMotions.put(CorruptWeaponCategories.S_LONGSWORD, (itemCap, playerpatch) ->
                Animations.LONGSWORD_GUARD_HIT);

        guardMotions.put(CorruptWeaponCategories.S_TACHI, (item, player) ->
                CorruptAnimations.TACHI_GUARD_HIT);
        guardBreakMotions.put(CorruptWeaponCategories.S_TACHI, (item, player) ->
                CorruptAnimations.GUARD_BREAK2);
        advancedGuardMotions.put(CorruptWeaponCategories.S_TACHI, (itemCap, playerpatch) ->
                List.of(Animations.LONGSWORD_GUARD_ACTIVE_HIT1, Animations.LONGSWORD_GUARD_ACTIVE_HIT2));

        guardMotions.put(CorruptWeaponCategories.S_SPEAR, (item, player) ->
                Animations.SPEAR_GUARD_HIT);
        guardBreakMotions.put(CorruptWeaponCategories.S_SPEAR, (item, player) ->
                CorruptAnimations.GUARD_BREAK2);
        advancedGuardMotions.put(CorruptWeaponCategories.S_SPEAR, (itemCap, playerpatch) ->
                List.of(Animations.LONGSWORD_GUARD_ACTIVE_HIT1, Animations.LONGSWORD_GUARD_ACTIVE_HIT2));

        guardMotions.put(CorruptWeaponCategories.GREAT_TACHI, (item, player) ->
                item.getStyle(player) == CapabilityItem.Styles.TWO_HAND ? CorruptAnimations.TACHI_GUARD_HIT : Animations.SWORD_DUAL_GUARD_HIT);
        guardBreakMotions.put(CorruptWeaponCategories.GREAT_TACHI, (item, player) ->
                CorruptAnimations.GUARD_BREAK2);
        advancedGuardMotions.put(CorruptWeaponCategories.GREAT_TACHI, (itemCap, playerpatch) ->
                itemCap.getStyle(playerpatch) == CapabilityItem.Styles.TWO_HAND ? List.of(Animations.LONGSWORD_GUARD_ACTIVE_HIT1, Animations.LONGSWORD_GUARD_ACTIVE_HIT2) : List.of(Animations.SWORD_GUARD_ACTIVE_HIT2, Animations.SWORD_GUARD_ACTIVE_HIT3));

        Field temp;
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> target;
        temp = GuardSkill.class.getDeclaredField("guardMotions");
        temp.setAccessible(true);
        target = (Map) temp.get(WOMSkills.COUNTER_ATTACK);
        for (WeaponCategory weaponCapability : guardMotions.keySet()) {
            target.put(weaponCapability, guardMotions.get(weaponCapability));
        }
        target = (Map) temp.get(WOMSkills.VENGEFUL_PARRY);
        for (WeaponCategory weaponCapability : guardMotions.keySet()) {
            target.put(weaponCapability, guardMotions.get(weaponCapability));
        }

        temp = GuardSkill.class.getDeclaredField("guardBreakMotions");
        temp.setAccessible(true);
        target = (Map) temp.get(WOMSkills.COUNTER_ATTACK);
        for (WeaponCategory weaponCapability : guardBreakMotions.keySet()) {
            target.put(weaponCapability, guardBreakMotions.get(weaponCapability));
        }
        target = (Map) temp.get(WOMSkills.VENGEFUL_PARRY);
        for (WeaponCategory weaponCapability : guardBreakMotions.keySet()) {
            target.put(weaponCapability, guardBreakMotions.get(weaponCapability));
        }

        temp = GuardSkill.class.getDeclaredField("advancedGuardMotions");
        temp.setAccessible(true);
        target = (Map) temp.get(WOMSkills.COUNTER_ATTACK);
        for (WeaponCategory weaponCapability : advancedGuardMotions.keySet()) {
            target.put(weaponCapability, advancedGuardMotions.get(weaponCapability));
        }

    }

    @Override
    public void onModEventBus(IEventBus iEventBus) {
    }
    @Override
    public void onForgeEventBus(IEventBus iEventBus) {
    }
    @Override
    public void onModEventBusClient(IEventBus iEventBus) {
    }
    @Override
    public void onForgeEventBusClient(IEventBus iEventBus) {
    }
}
