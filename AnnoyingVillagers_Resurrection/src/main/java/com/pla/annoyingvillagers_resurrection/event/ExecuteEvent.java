package com.pla.annoyingvillagers_resurrection.event;

import com.pla.annoyingvillagers.init.AnnoyingVillagersModItems;
import com.pla.annoyingvillagers_resurrection.AnnoyingVillagers_Resurrection;
import com.pla.annoyingvillagers_resurrection.gameasset.AnnoyingVillagersResurrectionExecutionType;
import net.corruptdog.cdm.world.CorruptWeaponCategories;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.shelmarow.combat_evolution.api.event.RegisterCustomExecutionEvent;
import net.shelmarow.combat_evolution.execution.ExecutionTypeManager;
import reascer.wom.world.capabilities.item.WOMWeaponCategories;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

@Mod.EventBusSubscriber(modid = AnnoyingVillagers_Resurrection.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExecuteEvent {

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
        event.registerExecutionByItem(AnnoyingVillagersModItems.HARD_GREATSWORD.getId(), CapabilityItem.Styles.COMMON, (item, livingEntityPatch) -> {
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
        event.registerExecutionByItem(AnnoyingVillagersModItems.DIAMOND_SWORD.getId(), CapabilityItem.Styles.ONE_HAND, (item, livingEntityPatch) -> {
            WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
            if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                return AnnoyingVillagersResurrectionExecutionType.SHIELD;
            }
            return AnnoyingVillagersResurrectionExecutionType.STAB;
        });
        event.registerExecutionByItem(AnnoyingVillagersModItems.DIAMOND_SWORD.getId(), CapabilityItem.Styles.TWO_HAND, AnnoyingVillagersResurrectionExecutionType.DUAL_STAB);
        event.registerExecutionByItem(AnnoyingVillagersModItems.DIAMOND_BLADE.getId(), CapabilityItem.Styles.ONE_HAND, (item, livingEntityPatch) -> {
            WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
            if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                return AnnoyingVillagersResurrectionExecutionType.SHIELD;
            }
            return AnnoyingVillagersResurrectionExecutionType.STAB;
        });
        event.registerExecutionByItem(AnnoyingVillagersModItems.DIAMOND_BLADE.getId(), CapabilityItem.Styles.TWO_HAND, AnnoyingVillagersResurrectionExecutionType.STAB);
        event.registerExecutionByItem(AnnoyingVillagersModItems.HOOKED_DIAMOND_SWORD.getId(), CapabilityItem.Styles.ONE_HAND, (item, livingEntityPatch) -> {
            WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
            if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                return AnnoyingVillagersResurrectionExecutionType.SHIELD;
            }
            return AnnoyingVillagersResurrectionExecutionType.STAB;
        });
        event.registerExecutionByItem(AnnoyingVillagersModItems.HOOKED_DIAMOND_SWORD.getId(), CapabilityItem.Styles.TWO_HAND, AnnoyingVillagersResurrectionExecutionType.STAB);
        event.registerExecutionByItem(AnnoyingVillagersModItems.PALADIN_SWORD.getId(), CapabilityItem.Styles.ONE_HAND, (item, livingEntityPatch) -> {
            WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
            if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                return AnnoyingVillagersResurrectionExecutionType.SHIELD;
            }
            return AnnoyingVillagersResurrectionExecutionType.STAB;
        });
        event.registerExecutionByItem(AnnoyingVillagersModItems.PALADIN_SWORD.getId(), CapabilityItem.Styles.TWO_HAND, AnnoyingVillagersResurrectionExecutionType.STAB);
        event.registerExecutionByItem(AnnoyingVillagersModItems.BLUE_FLAME_SWORD.getId(), CapabilityItem.Styles.ONE_HAND, (item, livingEntityPatch) -> {
            WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
            if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                return AnnoyingVillagersResurrectionExecutionType.SHIELD;
            }
            return AnnoyingVillagersResurrectionExecutionType.STAB;
        });
        event.registerExecutionByItem(AnnoyingVillagersModItems.BLUE_FLAME_SWORD.getId(), CapabilityItem.Styles.TWO_HAND, AnnoyingVillagersResurrectionExecutionType.STAB);
        event.registerExecutionByItem(AnnoyingVillagersModItems.DIAMOND_MAGNET_SWORD.getId(), CapabilityItem.Styles.ONE_HAND, (item, livingEntityPatch) -> {
            WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
            if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                return AnnoyingVillagersResurrectionExecutionType.SHIELD;
            }
            return AnnoyingVillagersResurrectionExecutionType.STAB;
        });
        event.registerExecutionByItem(AnnoyingVillagersModItems.DIAMOND_MAGNET_SWORD.getId(), CapabilityItem.Styles.TWO_HAND, AnnoyingVillagersResurrectionExecutionType.STAB);
        event.registerExecutionByItem(AnnoyingVillagersModItems.DIAMOND_SABER.getId(), CapabilityItem.Styles.ONE_HAND, (item, livingEntityPatch) -> {
            WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
            if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                return AnnoyingVillagersResurrectionExecutionType.SHIELD;
            }
            return AnnoyingVillagersResurrectionExecutionType.STAB;
        });
        event.registerExecutionByItem(AnnoyingVillagersModItems.DIAMOND_SABER.getId(), CapabilityItem.Styles.TWO_HAND, AnnoyingVillagersResurrectionExecutionType.STAB);
        event.registerExecutionByItem(AnnoyingVillagersModItems.HOOKED_GOLDEN_SWORD.getId(), CapabilityItem.Styles.ONE_HAND, (item, livingEntityPatch) -> {
            WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
            if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                return AnnoyingVillagersResurrectionExecutionType.SHIELD;
            }
            return AnnoyingVillagersResurrectionExecutionType.STAB;
        });
        event.registerExecutionByItem(AnnoyingVillagersModItems.HOOKED_GOLDEN_SWORD.getId(), CapabilityItem.Styles.TWO_HAND, AnnoyingVillagersResurrectionExecutionType.STAB);
        event.registerExecutionByItem(AnnoyingVillagersModItems.HOOKED_IRON_SWORD.getId(), CapabilityItem.Styles.ONE_HAND, (item, livingEntityPatch) -> {
            WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
            if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                return AnnoyingVillagersResurrectionExecutionType.SHIELD;
            }
            return AnnoyingVillagersResurrectionExecutionType.STAB;
        });
        event.registerExecutionByItem(AnnoyingVillagersModItems.HOOKED_IRON_SWORD.getId(), CapabilityItem.Styles.TWO_HAND, AnnoyingVillagersResurrectionExecutionType.STAB);
        event.registerExecutionByItem(AnnoyingVillagersModItems.EMERALD_SWORD.getId(), CapabilityItem.Styles.ONE_HAND, (item, livingEntityPatch) -> {
            WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
            if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                return AnnoyingVillagersResurrectionExecutionType.SHIELD;
            }
            return AnnoyingVillagersResurrectionExecutionType.STAB;
        });
        event.registerExecutionByItem(AnnoyingVillagersModItems.EMERALD_SWORD.getId(), CapabilityItem.Styles.TWO_HAND, AnnoyingVillagersResurrectionExecutionType.STAB);
        event.registerExecutionByItem(AnnoyingVillagersModItems.RED_DIAMOND_SWORD.getId(), CapabilityItem.Styles.ONE_HAND, (item, livingEntityPatch) -> {
            WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
            if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                return AnnoyingVillagersResurrectionExecutionType.SHIELD;
            }
            return AnnoyingVillagersResurrectionExecutionType.STAB;
        });
        event.registerExecutionByItem(AnnoyingVillagersModItems.RED_DIAMOND_SWORD.getId(), CapabilityItem.Styles.TWO_HAND, AnnoyingVillagersResurrectionExecutionType.STAB);
        event.registerExecutionByItem(AnnoyingVillagersModItems.RUBY_SWORD.getId(), CapabilityItem.Styles.ONE_HAND, (item, livingEntityPatch) -> {
            WeaponCategory weaponCategory = livingEntityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory();
            if (livingEntityPatch.isOffhandItemValid() && weaponCategory == CapabilityItem.WeaponCategories.SHIELD) {
                return AnnoyingVillagersResurrectionExecutionType.SHIELD;
            }
            return AnnoyingVillagersResurrectionExecutionType.STAB;
        });
        event.registerExecutionByItem(AnnoyingVillagersModItems.RUBY_SWORD.getId(), CapabilityItem.Styles.TWO_HAND, AnnoyingVillagersResurrectionExecutionType.STAB);
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
