package com.pla.annoyingvillagers_resurrection.event;

import com.pla.annoyingvillagers.init.AnnoyingVillagersModItems;
import com.pla.annoyingvillagers_resurrection.AnnoyingVillagers_Resurrection;
import com.pla.annoyingvillagers_resurrection.gameasset.AnnoyingVillagersResurrectionExecutionType;
import net.corruptdog.cdm.world.CorruptWeaponCategories;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.shelmarow.combat_evolution.api.event.RegisterCustomExecutionEvent;
import net.shelmarow.combat_evolution.execution.ExecutionTypeManager;
import reascer.wom.world.capabilities.item.WOMWeaponCategories;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = AnnoyingVillagers_Resurrection.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExecuteEvent {
    private static final List<ResourceLocation> listAvSwords = new ArrayList<>(Arrays.asList(
            AnnoyingVillagersModItems.BLACK_FIRE_SWORD.getId(),
            AnnoyingVillagersModItems.BLUE_FLAME_SWORD.getId(),
            AnnoyingVillagersModItems.CLOW_SWORD.getId(),
            AnnoyingVillagersModItems.DIAMOND_ATTRACTOR_SWORD.getId(),
            AnnoyingVillagersModItems.DIAMOND_BLASTER_SWORD.getId(),
            AnnoyingVillagersModItems.DIAMOND_SABRE.getId(),
            AnnoyingVillagersModItems.DNAX_HOOKED_SWORD.getId(),
            AnnoyingVillagersModItems.FLANKER_HOOKED_SWORD.getId(),
            AnnoyingVillagersModItems.JADE_SWORD.getId(),
            AnnoyingVillagersModItems.NETHERITE_GREATBLADE.getId(),
            AnnoyingVillagersModItems.PALADIN_SWORD.getId(),
            AnnoyingVillagersModItems.RED_DIAMOND_SWORD.getId(),
            AnnoyingVillagersModItems.RUBY_SWORD.getId(),
            AnnoyingVillagersModItems.RUBY_KNIGHT_SWORD.getId(),
            AnnoyingVillagersModItems.THUNDER_DIAMOND_BLADE.getId()
    ));

    @SubscribeEvent
    public static void registerExecution(RegisterCustomExecutionEvent event){
        event.RegisterExecutionByItem(AnnoyingVillagersModItems.OBSIDIAN_WEAPON.getId(), AnnoyingVillagersResurrectionExecutionType.WRESTLING);
        event.RegisterExecutionByItem(AnnoyingVillagersModItems.SHADOW_OBSIDIAN_WEAPON.getId(), AnnoyingVillagersResurrectionExecutionType.WRESTLING);
        event.registerExecutionByItem(AnnoyingVillagersModItems.SHADOW_OBSIDIAN_PILLAR.getId(), ExecutionTypeManager.TACHI_TYPE);
        event.registerExecutionByItem(AnnoyingVillagersModItems.SHADOW_OBSIDIAN_SWORD.getId(), ExecutionTypeManager.TACHI_TYPE);
        event.RegisterExecutionByItem(AnnoyingVillagersModItems.BEDROCK_WEAPON.getId(), AnnoyingVillagersResurrectionExecutionType.WRESTLING_BACK);
        event.RegisterExecutionByItem(AnnoyingVillagersModItems.NULL_WEAPON.getId(), AnnoyingVillagersResurrectionExecutionType.STRANGLE);
        event.RegisterExecutionByItem(AnnoyingVillagersModItems.ENDER_AEGIS.getId(), AnnoyingVillagersResurrectionExecutionType.FIST);
        event.RegisterExecutionByItem(AnnoyingVillagersModItems.CRAFTING_TABLE.getId(), AnnoyingVillagersResurrectionExecutionType.DUAL_GREATSWORD);
        event.RegisterExecutionByItem(AnnoyingVillagersModItems.BLUE_DEMON_TRIDENT.getId(), AnnoyingVillagersResurrectionExecutionType.TRIDENT);
        event.registerExecutionByItem(AnnoyingVillagersModItems.GREAT_SWORD.getId(), CapabilityItem.Styles.COMMON, (item, livingEntityPatch) -> {
            WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
            if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                return AnnoyingVillagersResurrectionExecutionType.SHIELD;
            }
            return AnnoyingVillagersResurrectionExecutionType.STAB;
        });
        event.registerExecutionByItem(AnnoyingVillagersModItems.WOOPIE_THE_SWORD.getId(), CapabilityItem.Styles.COMMON, (item, livingEntityPatch) -> {
            WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
            if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                return AnnoyingVillagersResurrectionExecutionType.SHIELD;
            }
            return AnnoyingVillagersResurrectionExecutionType.STAB;
        });
        listAvSwords.forEach(avSword -> {
            event.registerExecutionByItem(avSword, CapabilityItem.Styles.ONE_HAND, (item, livingEntityPatch) -> {
                WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
                if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                    return AnnoyingVillagersResurrectionExecutionType.SHIELD;
                }
                return AnnoyingVillagersResurrectionExecutionType.STAB;
            });
            event.registerExecutionByItem(avSword, CapabilityItem.Styles.TWO_HAND, AnnoyingVillagersResurrectionExecutionType.DUAL_STAB);
        });
        event.registerExecutionByCategory(CapabilityItem.WeaponCategories.SWORD, CapabilityItem.Styles.COMMON, ((item, livingEntityPatch) -> {
            WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
            if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                return AnnoyingVillagersResurrectionExecutionType.SHIELD;
            }
            return AnnoyingVillagersResurrectionExecutionType.SWORD;
        }));
        event.RegisterExecutionByCategory(CapabilityItem.WeaponCategories.FIST, AnnoyingVillagersResurrectionExecutionType.FIST);
        event.registerExecutionByCategory(CapabilityItem.WeaponCategories.SPEAR, CapabilityItem.Styles.COMMON, ((item, livingEntityPatch) -> {
            WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
            if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                return AnnoyingVillagersResurrectionExecutionType.SHIELD;
            }
            return AnnoyingVillagersResurrectionExecutionType.SPEAR;
        }));
        event.RegisterExecutionByCategory(CapabilityItem.WeaponCategories.GREATSWORD, AnnoyingVillagersResurrectionExecutionType.GREATSWORD);
        event.registerExecutionByCategory(CapabilityItem.WeaponCategories.TRIDENT, CapabilityItem.Styles.COMMON, ((item, livingEntityPatch) -> {
            WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
            if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                return AnnoyingVillagersResurrectionExecutionType.SHIELD;
            }
            return AnnoyingVillagersResurrectionExecutionType.TRIDENT;
        }));
        event.RegisterExecutionByCategory(CapabilityItem.WeaponCategories.TACHI, AnnoyingVillagersResurrectionExecutionType.TACHI);
        event.registerExecutionByCategory(CapabilityItem.WeaponCategories.DAGGER, CapabilityItem.Styles.ONE_HAND, AnnoyingVillagersResurrectionExecutionType.DAGGER);
        event.registerExecutionByCategory(CapabilityItem.WeaponCategories.DAGGER, CapabilityItem.Styles.TWO_HAND, AnnoyingVillagersResurrectionExecutionType.DUAL_DAGGER);
        event.registerExecutionByCategory(CapabilityItem.WeaponCategories.LONGSWORD, CapabilityItem.Styles.COMMON, ((item, livingEntityPatch) -> {
            WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
            if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                return AnnoyingVillagersResurrectionExecutionType.SHIELD;
            }
            return AnnoyingVillagersResurrectionExecutionType.LONGSWORD;
        }));
        event.RegisterExecutionByCategory(CapabilityItem.WeaponCategories.UCHIGATANA, AnnoyingVillagersResurrectionExecutionType.KATANA);
        event.RegisterExecutionByCategory(CorruptWeaponCategories.GREAT_TACHI, AnnoyingVillagersResurrectionExecutionType.TACHI);
        event.RegisterExecutionByCategory(CorruptWeaponCategories.KATANA, AnnoyingVillagersResurrectionExecutionType.KATANA);
        event.RegisterExecutionByCategory(CorruptWeaponCategories.PHANTOM_KATANA, AnnoyingVillagersResurrectionExecutionType.KATANA);
        event.registerExecutionByCategory(CorruptWeaponCategories.S_DAGGER, CapabilityItem.Styles.ONE_HAND, AnnoyingVillagersResurrectionExecutionType.DAGGER);
        event.registerExecutionByCategory(CorruptWeaponCategories.S_DAGGER, CapabilityItem.Styles.TWO_HAND, AnnoyingVillagersResurrectionExecutionType.DUAL_DAGGER);
        event.registerExecutionByCategory(CorruptWeaponCategories.S_GREATSWORD, CapabilityItem.Styles.TWO_HAND, AnnoyingVillagersResurrectionExecutionType.GREATSWORD);
        event.registerExecutionByCategory(CorruptWeaponCategories.S_GREATSWORD, CapabilityItem.Styles.ONE_HAND, AnnoyingVillagersResurrectionExecutionType.DUAL_GREATSWORD);
        event.registerExecutionByCategory(CorruptWeaponCategories.S_LONGSWORD, CapabilityItem.Styles.COMMON, ((item, livingEntityPatch) -> {
            WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
            if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                return AnnoyingVillagersResurrectionExecutionType.SHIELD;
            }
            return AnnoyingVillagersResurrectionExecutionType.LONGSWORD;
        }));
        event.registerExecutionByCategory(CorruptWeaponCategories.S_SPEAR, CapabilityItem.Styles.COMMON, ((item, livingEntityPatch) -> {
            WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
            if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                return AnnoyingVillagersResurrectionExecutionType.SHIELD;
            }
            return AnnoyingVillagersResurrectionExecutionType.SPEAR;
        }));
        event.registerExecutionByCategory(CorruptWeaponCategories.S_SWORD, CapabilityItem.Styles.COMMON, ((item, livingEntityPatch) -> {
            WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
            if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                return AnnoyingVillagersResurrectionExecutionType.SHIELD;
            }
            return AnnoyingVillagersResurrectionExecutionType.SWORD;
        }));
        event.RegisterExecutionByCategory(CorruptWeaponCategories.S_TACHI, AnnoyingVillagersResurrectionExecutionType.TACHI);
        event.RegisterExecutionByCategory(CorruptWeaponCategories.YAMATO, AnnoyingVillagersResurrectionExecutionType.YAMATO);
        event.RegisterExecutionByCategory(WOMWeaponCategories.TORMENT, AnnoyingVillagersResurrectionExecutionType.GREATSWORD);
        event.RegisterExecutionByCategory(WOMWeaponCategories.ENDERBLASTER, AnnoyingVillagersResurrectionExecutionType.FIST);
        event.RegisterExecutionByCategory(WOMWeaponCategories.NAPOLEON, AnnoyingVillagersResurrectionExecutionType.SPEAR);
    }
}
