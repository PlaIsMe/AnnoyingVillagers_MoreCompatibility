package com.pla.annoyingvillagers_resurrection.compat;

import com.pla.annoyingvillagers_resurrection.AnnoyingVillagers_Resurrection;
import net.corruptdog.cdm.world.CorruptWeaponCategories;
import net.corruptdog.cdm.world.item.CDAddonItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.client.forgeevent.WeaponCategoryIconRegisterEvent;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.api.forgeevent.SkillBuildEvent.ModRegistryWorker.SkillCreateEvent;
import yesman.epicfight.skill.passive.EmergencyEscapeSkill;
import yesman.epicfight.skill.passive.SwordmasterSkill;

@Mod.EventBusSubscriber(modid = AnnoyingVillagers_Resurrection.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EpicFight {
    public static void forceGuard(SkillBuildEvent bus) {
    }

    @SubscribeEvent
    public static void onScapeSkillCreate(SkillCreateEvent<EmergencyEscapeSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","emergency_escape"))) {
            EmergencyEscapeSkill.Builder builder = event.getSkillBuilder();
            builder.addAvailableWeaponCategory(CorruptWeaponCategories.YAMATO)
                    .addAvailableWeaponCategory(CorruptWeaponCategories.KATANA)
                    .addAvailableWeaponCategory(CorruptWeaponCategories.S_SWORD)
                    .addAvailableWeaponCategory(CorruptWeaponCategories.S_GREATSWORD)
                    .addAvailableWeaponCategory(CorruptWeaponCategories.S_LONGSWORD)
                    .addAvailableWeaponCategory(CorruptWeaponCategories.S_TACHI)
                    .addAvailableWeaponCategory(CorruptWeaponCategories.S_SPEAR)
                    .addAvailableWeaponCategory(CorruptWeaponCategories.GREAT_TACHI);
        }
    }

    @SubscribeEvent
    public static void onSwordSkillCreate(SkillCreateEvent<SwordmasterSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","swordmaster"))) {
            SwordmasterSkill.Builder builder = event.getSkillBuilder();
            builder.addAvailableWeaponCategory(CorruptWeaponCategories.YAMATO)
                    .addAvailableWeaponCategory(CorruptWeaponCategories.KATANA)
                    .addAvailableWeaponCategory(CorruptWeaponCategories.S_SWORD)
                    .addAvailableWeaponCategory(CorruptWeaponCategories.S_GREATSWORD)
                    .addAvailableWeaponCategory(CorruptWeaponCategories.S_LONGSWORD)
                    .addAvailableWeaponCategory(CorruptWeaponCategories.S_TACHI)
                    .addAvailableWeaponCategory(CorruptWeaponCategories.S_SPEAR)
                    .addAvailableWeaponCategory(CorruptWeaponCategories.GREAT_TACHI);
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
}
