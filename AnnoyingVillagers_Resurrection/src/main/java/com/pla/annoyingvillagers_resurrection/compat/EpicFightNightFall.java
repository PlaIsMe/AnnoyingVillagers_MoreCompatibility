package com.pla.annoyingvillagers_resurrection.compat;

import com.pla.annoyingvillagers_resurrection.AnnoyingVillagers_Resurrection;
import net.corruptdog.cdm.gameasset.CorruptAnimations;
import net.corruptdog.cdm.world.CorruptWeaponCategories;
import net.corruptdog.cdm.world.item.CDAddonItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import yesman.epicfight.api.client.forgeevent.WeaponCategoryIconRegisterEvent;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.api.forgeevent.SkillBuildEvent.ModRegistryWorker.SkillCreateEvent;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.skill.guard.ParryingSkill;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

import java.util.List;

@Mod.EventBusSubscriber(modid = AnnoyingVillagers_Resurrection.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EpicFightNightFall implements ICompatModule {
    public static void forceGuard(SkillBuildEvent bus) {
    }

    @SubscribeEvent
    public static void onEnhancedParrySkillCreate(SkillCreateEvent<ParryingSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("efn","efn_parry"))) {
            GuardSkill.Builder builder = event.getSkillBuilder();
            builder
                    .addGuardMotion(CorruptWeaponCategories.KATANA, (item, player) -> Animations.UCHIGATANA_GUARD_HIT)
                    .addGuardMotion(CorruptWeaponCategories.S_GREATSWORD, (item, player) -> item.getStyle(player) == CapabilityItem.Styles.TWO_HAND ? Animations.GREATSWORD_GUARD_HIT : Animations.SWORD_DUAL_GUARD_HIT)
                    .addGuardMotion(CorruptWeaponCategories.YAMATO, (item, player) -> CorruptAnimations.YAMATO_GUARD_HIT)
                    .addGuardMotion(CorruptWeaponCategories.S_SWORD, (item, player) -> item.getStyle(player) == CapabilityItem.Styles.ONE_HAND ? Animations.SWORD_GUARD_HIT : Animations.SWORD_DUAL_GUARD_HIT)
                    .addGuardMotion(CorruptWeaponCategories.S_LONGSWORD, (item, player) -> Animations.LONGSWORD_GUARD_HIT)
                    .addGuardMotion(CorruptWeaponCategories.S_TACHI, (item, player) -> CorruptAnimations.TACHI_GUARD_HIT)
                    .addGuardMotion(CorruptWeaponCategories.S_SPEAR, (item, player) -> Animations.SPEAR_GUARD_HIT)
                    .addGuardMotion(CorruptWeaponCategories.GREAT_TACHI, (item, player) -> item.getStyle(player) == CapabilityItem.Styles.TWO_HAND ? CorruptAnimations.TACHI_GUARD_HIT : Animations.SWORD_DUAL_GUARD_HIT)
                    .addGuardBreakMotion(CorruptWeaponCategories.KATANA, (item, player) -> CorruptAnimations.GUARD_BREAK1)
                    .addGuardBreakMotion(CorruptWeaponCategories.S_GREATSWORD, (item, player) -> CorruptAnimations.GUARD_BREAK2)
                    .addGuardBreakMotion(CorruptWeaponCategories.YAMATO, (item, player) -> CorruptAnimations.GUARD_BREAK1)
                    .addGuardBreakMotion(CorruptWeaponCategories.S_SWORD, (item, player) -> CorruptAnimations.GUARD_BREAK1)
                    .addGuardBreakMotion(CorruptWeaponCategories.S_LONGSWORD, (item, player) -> CorruptAnimations.GUARD_BREAK2)
                    .addGuardBreakMotion(CorruptWeaponCategories.S_TACHI, (item, player) -> CorruptAnimations.GUARD_BREAK2)
                    .addGuardBreakMotion(CorruptWeaponCategories.S_SPEAR, (item, player) -> CorruptAnimations.GUARD_BREAK2)
                    .addGuardBreakMotion(CorruptWeaponCategories.GREAT_TACHI, (item, player) -> CorruptAnimations.GUARD_BREAK2)
                    .addAdvancedGuardMotion(CorruptWeaponCategories.KATANA, (item, player) -> List.of(Animations.SWORD_GUARD_ACTIVE_HIT1, Animations.SWORD_GUARD_ACTIVE_HIT2))
                    .addAdvancedGuardMotion(CorruptWeaponCategories.S_GREATSWORD, (item, player) -> item.getStyle(player) == CapabilityItem.Styles.TWO_HAND ? List.of(Animations.LONGSWORD_GUARD_ACTIVE_HIT1, Animations.LONGSWORD_GUARD_ACTIVE_HIT2) : List.of(Animations.SWORD_GUARD_ACTIVE_HIT2, Animations.SWORD_GUARD_ACTIVE_HIT3))
                    .addAdvancedGuardMotion(CorruptWeaponCategories.YAMATO, (item, player) -> List.of(CorruptAnimations.YAMATO_ACTIVE_GUARD_HIT, CorruptAnimations.YAMATO_ACTIVE_GUARD_HIT2))
                    .addAdvancedGuardMotion(CorruptWeaponCategories.S_SWORD, (item, player) -> item.getStyle(player) == CapabilityItem.Styles.ONE_HAND ? List.of(Animations.SWORD_GUARD_ACTIVE_HIT1, Animations.SWORD_GUARD_ACTIVE_HIT2) : List.of(Animations.SWORD_GUARD_ACTIVE_HIT2, Animations.SWORD_GUARD_ACTIVE_HIT3))
                    .addAdvancedGuardMotion(CorruptWeaponCategories.S_LONGSWORD, (item, player) -> List.of(Animations.LONGSWORD_GUARD_ACTIVE_HIT1, Animations.LONGSWORD_GUARD_ACTIVE_HIT2))
                    .addAdvancedGuardMotion(CorruptWeaponCategories.S_TACHI, (item, player) -> List.of(Animations.LONGSWORD_GUARD_ACTIVE_HIT1, Animations.LONGSWORD_GUARD_ACTIVE_HIT2))
                    .addAdvancedGuardMotion(CorruptWeaponCategories.S_SPEAR, (item, player) -> List.of(Animations.LONGSWORD_GUARD_ACTIVE_HIT1, Animations.LONGSWORD_GUARD_ACTIVE_HIT2))
                    .addAdvancedGuardMotion(CorruptWeaponCategories.GREAT_TACHI, (item, player) -> item.getStyle(player) == CapabilityItem.Styles.TWO_HAND ? List.of(Animations.LONGSWORD_GUARD_ACTIVE_HIT1, Animations.LONGSWORD_GUARD_ACTIVE_HIT2) : List.of(Animations.SWORD_GUARD_ACTIVE_HIT2, Animations.SWORD_GUARD_ACTIVE_HIT3));
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onIconCreate(WeaponCategoryIconRegisterEvent icon){
        icon.registerCategory(CorruptWeaponCategories.YAMATO, new ItemStack(CDAddonItems.YAMATO.get()));
        icon.registerCategory(CorruptWeaponCategories.KATANA, new ItemStack(CDAddonItems.KATANA.get()));
        icon.registerCategory(CorruptWeaponCategories.S_SWORD, new ItemStack(CDAddonItems.S_IRON_SWORD.get()));
        icon.registerCategory(CorruptWeaponCategories.S_GREATSWORD, new ItemStack(CDAddonItems.S_IRON_GREATSWORD.get()));
        icon.registerCategory(CorruptWeaponCategories.S_LONGSWORD, new ItemStack(CDAddonItems.S_IRON_LONGSWORD.get()));
        icon.registerCategory(CorruptWeaponCategories.S_TACHI, new ItemStack(CDAddonItems.S_IRON_TACHI.get()));
        icon.registerCategory(CorruptWeaponCategories.S_SPEAR, new ItemStack(CDAddonItems.S_IRON_SPEAR.get()));
        icon.registerCategory(CorruptWeaponCategories.GREAT_TACHI, new ItemStack(CDAddonItems.GREAT_TACHI.get()));
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
