package com.pla.epicfight_smart_npc.compat.annoyingvillagers;

import com.pla.annoyingvillagers.clazz.HerobrineObsidianBlock;
import com.pla.annoyingvillagers.combatbehaviour.PlayerNpcBow;
import com.pla.annoyingvillagers.compat.p1nero_bow.PlayerNpcP1neroBow;
import com.pla.annoyingvillagers.compat.p1nero_bow.PlayerNpcP1neroMortisBow;
import com.pla.annoyingvillagers.entity.HerobrineDragonEntity;
import com.pla.annoyingvillagers.gameasset.*;
import com.pla.annoyingvillagers.init.AnnoyingVillagersModItems;
import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import com.pla.epicfight_smart_npc.compat.EpicFightBow;
import com.pla.epicfight_smart_npc.compat.dualgreatsword.PlayerNpcDualGreatsword;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.RegistryObject;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors;
import reascer.wom.gameasset.animations.weapons.*;
import reascer.wom.world.item.WOMItems;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.HashSet;
import java.util.Set;

public class AnnoyingVillagers {
    private static final Set<String> DANGEROUS_ANIMATIONS = new HashSet<>();

    static {
        DANGEROUS_ANIMATIONS.addAll(Set.of(
                AnimsWom.ENDER_AEGIS_BULL_CHARGE.get().getRegistryName().toString(),
                AnimsWom.YELLOW_TORMENT_CHARGED_ATTACK_3.get().getRegistryName().toString(),
                AnimsWom.ENDER_GLAIVE_NAPOLEON_SHOOT_3.get().getRegistryName().toString(),
                AnimsWom.ENDER_GLAIVE_AGONY_AUTO_1.get().getRegistryName().toString(),
                AnimsEpicFight.AEGIS_SHIELD_SHOOT.get().getRegistryName().toString(),
                AnimsWom.CLONE_NAPOLEON_WATERLOW_SHOOT.get().getRegistryName().toString(),
                AVAnimations.TRIDENT_ATTACK.get().getRegistryName().toString(),
                AnimsPugilistSteve.BLUE_DEMON_STATE_TRANSFORM.get().getRegistryName().toString(),
                AnimsWom.ELECTRIC_FIELD.get().getRegistryName().toString(),
                AVAnimations.SNAKE_BLADE_GUARD.get().getRegistryName().toString()
        ));
    }

    public static Set<String> getDangerousAnimations() {
        return DANGEROUS_ANIMATIONS;
    }

    public static AnimationManager.AnimationAccessor<? extends StaticAnimation>[] axeHeavyAnimations() {
        return CombatCommon.animations(
                AnimsPugilistSteve.AXE_HEAVY_AUTO_1,
                AnimsPugilistSteve.AXE_HEAVY_AUTO_2,
                AnimsPugilistSteve.AXE_FUN_SKILL
        );
    }
    public static AnimationManager.AnimationAccessor<? extends StaticAnimation>[] swordHeavyAnimations() {
        return CombatCommon.animations(
                AnimsPugilistSteve.SWORD_HEAVY_AUTO_1,
                AnimsPugilistSteve.SWORD_HEAVY_AUTO_2,
                AnimsPugilistSteve.SWORD_HEAVY_AUTO_3
        );
    }
    public static AnimationManager.AnimationAccessor<? extends StaticAnimation>[] fistSpecialAnimations() {
        return CombatCommon.animations(
                AnimsPugilistSteve.FIST_LEFT,
                Animations.FIST_DASH,
                AnimsPugilistSteve.WHIRLWIND_KICK_LEFT,
                AnimsPugilistSteve.FIST_UP,
                AnimsPugilistSteve.FIST_DASH,
                AnimsPugilistSteve.WHIRLWIND_KICK
        );
    }

    public static AnimationManager.AnimationAccessor<? extends StaticAnimation>[] greatswordSpecialAnimations() {
        return CombatCommon.animations(
                AnimsPugilistSteve.GIANT_WHIRLWIND
        );
    }

    public static AnimationManager.AnimationAccessor<? extends StaticAnimation>[] dualSwordSpecialAnimations() {
        return CombatCommon.animations(
                AnimsPugilistSteve.DUAL_DANCING_EDGE,
                AnimsPugilistSteve.DUAL_SWORD_DANCING_EDGE,
                AnimsPugilistSteve.DAGGER_DUAL_AUTO4
        );
    }

    public static AnimationManager.AnimationAccessor<? extends StaticAnimation>[] spearSpecialAnimations() {
        return CombatCommon.animations(
                AnimsPugilistSteve.SPEAR_THRUST
        );
    }

    public static AnimationManager.AnimationAccessor<? extends StaticAnimation>[] rapierSpecialAnimations() {
        return CombatCommon.animations(
                AnimsEpicFightBattleArts.SABRE_QUAD_STING
        );
    }

    public static CECombatBehaviors.Builder<MobPatch<?>> overideCustomWeaponMotionBuilderForAvNpc(CapabilityItem mainHandCap, CapabilityItem offHandCap, Style style) {
        if (ModList.get().isLoaded("dualgreatswords")
                && mainHandCap.getWeaponCategory() == CapabilityItem.WeaponCategories.GREATSWORD
                && offHandCap != null
                && offHandCap.getWeaponCategory() == CapabilityItem.WeaponCategories.GREATSWORD) {
            return PlayerNpcDualGreatsword.DUAL_GREATSWORD;
        }

        CECombatBehaviors.Builder<MobPatch<?>> avNpcWeaponOverride = overideRequestedAvNpcWeaponMotionBuilder(mainHandCap, style);
        if (avNpcWeaponOverride != null) {
            return avNpcWeaponOverride;
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(WOMItems.DIAMOND_STAFF.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSpear.STAFF;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(WOMItems.GOLDEN_STAFF.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSpear.STAFF;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(WOMItems.IRON_STAFF.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSpear.STAFF;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(WOMItems.STONE_STAFF.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSpear.STAFF;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(WOMItems.WOODEN_STAFF.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSpear.STAFF;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.BLACK_FIRE_SWORD.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvSword.BLACK_FIRE_SWORD;
            } else if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSword.DUAL_BLACK_FIRE_SWORD;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.BLUE_FLAME_SWORD.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvSword.BLUE_FLAME_SWORD;
            } else if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSword.DUAL_BLUE_FLAME_SWORD;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.CENTRANOS_SWORD.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.IRON_CLEAVER.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvGreatsword.CLEAVER;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.CLOW_SWORD.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvSword.CLOW_SWORD;
            } else if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSword.DUAL_CLOW_SWORD;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_ATTRACTOR_SWORD.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvSword.DIAMOND_ATTRACTOR_SWORD;
            } else if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSword.DUAL_DIAMOND_ATTRACTOR_SWORD;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_BLASTER_SWORD.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvSword.DIAMOND_BLASTER_SWORD;
            } else if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSword.DUAL_DIAMOND_BLASTER_SWORD;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.HACKER_SWORD.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvSword.HACKER_SWORD;
            } else if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSword.DUAL_HACKER_SWORD;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_WARBLADE.get().getDefaultInstance())) {
            return PlayerNpcAvTachi.DIAMOND_WARBLADE;
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_LAEVATEINN.get().getDefaultInstance())) {
            return PlayerNpcAvTachi.DIAMOND_LAEVATEINN;
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_FALCHION.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_GREAT_FALCHION.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.NETHERITE_FALCHION.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvTachi.FALCHION;
            } else if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvTachi.DUAL_FALCHION;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_SABRE.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.NETHERITE_SABRE.get().getDefaultInstance())) {
            return PlayerNpcAvLongsword.DIAMOND_SABRE;
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.HOOKED_IRON_SWORD.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.HOOKED_GOLDEN_SWORD.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvSword.HOOK_SWORD;
            } else if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSword.DUAL_HOOK_SWORD;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.FLANKER_HOOKED_SWORD.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvSword.FLANKER_HOOK_SWORD;
            } else if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSword.DUAL_HOOK_SWORD;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DNAX_HOOKED_SWORD.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvSword.DNAX_HOOK_SWORD;
            } else if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSword.DUAL_DNAX_HOOK_SWORD;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_LONGSWORD.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.GOLDEN_LONGSWORD.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.IRON_LONGSWORD.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.RUBY_LONGSWORD.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvLongsword.AV_LONGSWORD;
            } else if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvLongsword.DUAL_AV_LONGSWORD;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_CHIPPED_LONGSWORD.get().getDefaultInstance())) {
            return PlayerNpcAvLongsword.CHIPPED_LONGSWORD;
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_GREATSWORD.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.RUBY_GREATSWORD.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvGreatsword.AV_GREATSWORD;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.RUBY_SWORD.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.THUNDER_DIAMOND_BLADE.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.JADE_SWORD.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.RED_DIAMOND_SWORD.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.WOOPIE_THE_SWORD.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_KNIGHT_SWORD.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.RUBY_KNIGHT_SWORD.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.PALADIN_SWORD.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.GREAT_SWORD.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvSword.AV_SWORD;
            } else if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSword.AV_DUAL_SWORD;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.EARTH_AXE.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvAxe.EARTH_AXE;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.RED_AXE.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvAxe.RED_AXE;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_BATTLEAXE.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvGreatsword.BATTLE_AXE;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.GIANT_NETHERITE_AXE.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvGreatsword.GIANT_AXE;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.EXTERMINATOR_BATTLEAXE.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.EXTERMINATOR_BATTLEAXE_GREEN.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.GOLDEN_MACE.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_MACE.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvAxe.EXTERMINATOR_BATTLE_AXE;
            } else if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvAxe.DUAL_EXTERMINATOR_BATTLE_AXE;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.IRON_GREATAXE.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_GREATAXE.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.NETHERITE_GREATAXE.get().getDefaultInstance())) {
            return PlayerNpcAvGreatsword.GREATAXE;
        }


        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_HALBERD.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.IRON_DOUBLE_BLADED_HALBERD.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_HALBERD.get().getDefaultInstance())) {
            return PlayerNpcAvAxe.HALBERD;
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.SAMANTHA_THE_KILLER_AXE.get().getDefaultInstance())) {
            return PlayerNpcAvAxe.KILLER_AXE;
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.KNIFE.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_KNIFE.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.NETHERITE_KNIFE.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvDagger.KNIFE;
            } else if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvDagger.DUAL_KNIFE;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_ARMBLADE.get().getDefaultInstance())) {
            return PlayerNpcAvDagger.ARM_BLADE;
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.GOLDEN_MOON_BLADE.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_MOON_BLADE.get().getDefaultInstance())) {
            return PlayerNpcAvDagger.MOON_BLADE;
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_CLAW.get().getDefaultInstance())) {
            return PlayerNpcAvDagger.CLAW;
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.SPEAR_AXE.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_BOLT.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_SPEAR.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.NETHERITE_SPEAR.get().getDefaultInstance())) {
            return PlayerNpcAvSpear.GUANDAO;
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.TWIN_DIAMOND_SPEAR.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvSpear.GUANDAO;
            } else if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSpear.SPEAR_STAFF;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.IRON_TWIN_BLADE_KATANA.get().getDefaultInstance())
                || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DOUBLE_DIAMOND_GLAIVE.get().getDefaultInstance())) {
            return PlayerNpcAvSpear.SPEAR_STAFF;
        }


        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.BLACKSCRATCHER.get().getDefaultInstance())) {
            return PlayerNpcAvSpear.BLACK_SCRATCHER;
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.DIAMOND_SICKLE.get().getDefaultInstance()) || mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.IRON_SICKLE.get().getDefaultInstance())) {
            return PlayerNpcAvSpear.SICKLE;
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.WOODEN_DOOR.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvBlockWeapon.WOODEN_DOOR;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.CRAFTING_TABLE.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvBlockWeapon.CRAFTING_TABLE;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.LADDER.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvBlockWeapon.LADDER;
            }
        }

        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(AnnoyingVillagersModItems.TRAPDOOR.get().getDefaultInstance())) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvBlockWeapon.TRAPDOOR;
            }
        }

        return null;
    }
    private static CECombatBehaviors.Builder<MobPatch<?>> overideRequestedAvNpcWeaponMotionBuilder(CapabilityItem mainHandCap, Style style) {
        if (matches(mainHandCap,
                AnnoyingVillagersModItems.HOOKED_DIAMOND_SWORD,
                AnnoyingVillagersModItems.HOOKED_IRON_SWORD,
                AnnoyingVillagersModItems.HOOKED_GOLDEN_SWORD)) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvSword.HOOK_SWORD;
            } else if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSword.DUAL_HOOK_SWORD;
            }
        }

        if (matches(mainHandCap, AnnoyingVillagersModItems.FLANKER_HOOKED_SWORD)) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvSword.FLANKER_HOOK_SWORD;
            } else if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSword.DUAL_HOOK_SWORD;
            }
        }

        if (matches(mainHandCap, AnnoyingVillagersModItems.DNAX_HOOKED_SWORD)) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvSword.DNAX_HOOK_SWORD;
            } else if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSword.DUAL_DNAX_HOOK_SWORD;
            }
        }

        if (matches(mainHandCap,
                AnnoyingVillagersModItems.DIAMOND_SABRE,
                AnnoyingVillagersModItems.NETHERITE_SABRE)) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvLongsword.DIAMOND_SABRE;
            }
        }

        if (matches(mainHandCap,
                AnnoyingVillagersModItems.DIAMOND_HALBERD,
                AnnoyingVillagersModItems.IRON_HALBERD)) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvAxe.HALBERD;
            }
        }

        if (matches(mainHandCap, AnnoyingVillagersModItems.IRON_DOUBLE_BLADED_HALBERD)) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvAxe.DOUBLE_HALBERD;
            }
        }

        if (matches(mainHandCap, AnnoyingVillagersModItems.SAMANTHA_THE_KILLER_AXE)) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvAxe.KILLER_AXE;
            }
        }

        if (matches(mainHandCap, AnnoyingVillagersModItems.EARTH_AXE)) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvAxe.EARTH_AXE;
            }
        }

        if (matches(mainHandCap, AnnoyingVillagersModItems.RED_AXE)) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvAxe.RED_AXE;
            }
        }

        if (matches(mainHandCap,
                AnnoyingVillagersModItems.EXTERMINATOR_BATTLEAXE,
                AnnoyingVillagersModItems.EXTERMINATOR_BATTLEAXE_GREEN,
                AnnoyingVillagersModItems.GOLDEN_MACE,
                AnnoyingVillagersModItems.DIAMOND_MACE)) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvAxe.EXTERMINATOR_BATTLE_AXE;
            } else if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvAxe.DUAL_EXTERMINATOR_BATTLE_AXE;
            }
        }

        if (matches(mainHandCap,
                AnnoyingVillagersModItems.DIAMOND_SPEAR,
                AnnoyingVillagersModItems.NETHERITE_SPEAR,
                AnnoyingVillagersModItems.SPEAR_AXE)) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSpear.GUANDAO;
            }
        }

        if (matches(mainHandCap,
                AnnoyingVillagersModItems.DOUBLE_DIAMOND_GLAIVE,
                AnnoyingVillagersModItems.IRON_TWIN_BLADE_KATANA)) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSpear.SPEAR_STAFF;
            }
        }

        if (matches(mainHandCap, AnnoyingVillagersModItems.TWIN_DIAMOND_SPEAR)) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvSpear.GUANDAO;
            } else if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSpear.SPEAR_STAFF;
            }
        }

        if (matches(mainHandCap,
                AnnoyingVillagersModItems.DIAMOND_SICKLE,
                AnnoyingVillagersModItems.IRON_SICKLE)) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSpear.SICKLE;
            }
        }

        if (matches(mainHandCap, AnnoyingVillagersModItems.DIAMOND_BOLT)) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSpear.BOLT;
            }
        }

        if (matches(mainHandCap, AnnoyingVillagersModItems.BLACKSCRATCHER)) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvSpear.BLACK_SCRATCHER;
            }
        }

        if (matches(mainHandCap, AnnoyingVillagersModItems.DIAMOND_WARBLADE)) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvTachi.DIAMOND_WARBLADE;
            }
        }

        if (matches(mainHandCap, AnnoyingVillagersModItems.DIAMOND_LAEVATEINN)) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvTachi.DIAMOND_LAEVATEINN;
            }
        }

        if (matches(mainHandCap,
                AnnoyingVillagersModItems.DIAMOND_FALCHION,
                AnnoyingVillagersModItems.DIAMOND_GREAT_FALCHION,
                AnnoyingVillagersModItems.NETHERITE_FALCHION)) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvTachi.FALCHION;
            } else if (style == CapabilityItem.Styles.OCHS) {
                return PlayerNpcAvTachi.DUAL_FALCHION;
            }
        }

        if (matches(mainHandCap,
                AnnoyingVillagersModItems.DIAMOND_LONGSWORD,
                AnnoyingVillagersModItems.GOLDEN_LONGSWORD,
                AnnoyingVillagersModItems.IRON_LONGSWORD,
                AnnoyingVillagersModItems.RUBY_LONGSWORD)) {
            if (style == CapabilityItem.Styles.ONE_HAND) {
                return PlayerNpcAvLongsword.AV_LONGSWORD;
            } else if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvLongsword.DUAL_AV_LONGSWORD;
            }
        }

        if (matches(mainHandCap, AnnoyingVillagersModItems.DIAMOND_CHIPPED_LONGSWORD)) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvLongsword.CHIPPED_LONGSWORD;
            }
        }

        if (matches(mainHandCap,
                AnnoyingVillagersModItems.DIAMOND_GREATSWORD,
                AnnoyingVillagersModItems.RUBY_GREATSWORD)) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvGreatsword.AV_GREATSWORD;
            }
        }

        if (matches(mainHandCap,
                AnnoyingVillagersModItems.DIAMOND_GREATAXE,
                AnnoyingVillagersModItems.IRON_GREATAXE,
                AnnoyingVillagersModItems.NETHERITE_GREATAXE)) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvGreatsword.GREATAXE;
            }
        }

        if (matches(mainHandCap, AnnoyingVillagersModItems.GIANT_NETHERITE_AXE)) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvGreatsword.GIANT_AXE;
            }
        }

        if (matches(mainHandCap, AnnoyingVillagersModItems.DIAMOND_BATTLEAXE)) {
            if (style == CapabilityItem.Styles.TWO_HAND) {
                return PlayerNpcAvGreatsword.BATTLE_AXE;
            }
        }

        return null;
    }

    @SafeVarargs
    private static boolean matches(CapabilityItem mainHandCap, RegistryObject<Item>... items) {
        for (RegistryObject<Item> item : items) {
            if (mainHandCap == EpicFightCapabilities.getItemStackCapability(item.get().getDefaultInstance())) {
                return true;
            }
        }
        return false;
    }

    public static CECombatBehaviors.Builder<MobPatch<?>> overideBowMotionBuilderForPlayerNpc(CapabilityItem mainHandCap, Style style) {
        if (ModList.get().isLoaded("p1nero_bow")) {
            if (EpicFightBow.isMortisBow(mainHandCap)) {
                return PlayerNpcP1neroMortisBow.MORTIS_BOW;
            }
            if (mainHandCap == EpicFightCapabilities.getItemStackCapability(Items.BOW.getDefaultInstance())) {
                return PlayerNpcP1neroBow.BOW;
            }
        } else {
            if (mainHandCap == EpicFightCapabilities.getItemStackCapability(Items.BOW.getDefaultInstance())) {
                return PlayerNpcBow.BOW;
            }
        }
        return null;
    }

    public static boolean isHerobrineDragon(LivingEntity livingEntity) {
        return livingEntity instanceof HerobrineDragonEntity;
    }

    public static boolean isHerobrineReplacedLiquidObsidianBlock(BlockState blockState) {
        return blockState.getBlock() instanceof HerobrineObsidianBlock
                && blockState.hasProperty(HerobrineObsidianBlock.REPLACE_BY_LIQUID);
    }

    public static void setBlockWithHerobrineReplacedLiquidObsidianBlock(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {
        serverLevel.setBlock(
                blockPos,
                blockState.setValue(HerobrineObsidianBlock.REPLACE_BY_LIQUID, 0),
                3
        );
    }
}
