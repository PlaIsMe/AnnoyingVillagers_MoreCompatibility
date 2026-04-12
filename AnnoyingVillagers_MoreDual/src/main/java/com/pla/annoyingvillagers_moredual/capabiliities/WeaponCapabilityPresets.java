package com.pla.annoyingvillagers_moredual.capabiliities;

import M6FGR.dualaxes.gameassets.DualAxesAnimations;
import M6FGR.dualaxes.gameassets.DualAxesSkills;
import M6FGR.dualgreatswords.gameassets.DualGreatSwordsAnimations;
import M6FGR.dualgreatswords.gameassets.DualGreatSwordsSkills;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import reascer.wom.gameasset.WOMAnimations;
import reascer.wom.gameasset.animations.weapons.AnimsSolar;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

import java.util.function.Function;

public class WeaponCapabilityPresets {
    public static final Function<Item, CapabilityItem.Builder> AXE =
            (item) -> (CapabilityItem.Builder) WeaponCapability.builder()
            .category(CapabilityItem.WeaponCategories.AXE).styleProvider((entityPatch) -> {
                if (entityPatch instanceof PlayerPatch<?> playerPatch) {
                    return playerPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CapabilityItem.WeaponCategories.AXE
                           ? CapabilityItem.Styles.TWO_HAND : CapabilityItem.Styles.ONE_HAND;
                } else if (entityPatch instanceof LivingEntityPatch<?>) {
                    return entityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CapabilityItem.WeaponCategories.AXE ? CapabilityItem.Styles.TWO_HAND : CapabilityItem.Styles.ONE_HAND;
                }
                return CapabilityItem.Styles.ONE_HAND;
            })
            .collider(ColliderPreset.TOOLS)
            .hitSound(EpicFightSounds.BLADE_HIT.get())
            .newStyleCombo(CapabilityItem.Styles.ONE_HAND, DualAxesAnimations.AXE_AUTO_1, DualAxesAnimations.AXE_AUTO_2, DualAxesAnimations.AXE_AUTO_3, Animations.BIPED_MOB_TACHI, Animations.AXE_AIRSLASH)
            .innateSkill(CapabilityItem.Styles.ONE_HAND, (itemstack) -> EpicFightSkills.GUILLOTINE_AXE).livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, Animations.BIPED_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, Animations.BIPED_WALK)
            .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.RUN, Animations.BIPED_RUN)
            .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.JUMP, Animations.BIPED_JUMP)
            .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.KNEEL, Animations.BIPED_KNEEL)
            .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.SNEAK, Animations.BIPED_SNEAK)
            .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.SWIM, Animations.BIPED_SWIM)
            .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)
            .newStyleCombo(CapabilityItem.Styles.TWO_HAND, DualAxesAnimations.AXE_DUAL_AUTO_1, DualAxesAnimations.AXE_DUAL_AUTO_2, DualAxesAnimations.AXE_DUAL_AUTO_3, DualAxesAnimations.AXE_DUAL_DASH, DualAxesAnimations.AXE_DUAL_AIRSLASH)
            .innateSkill(CapabilityItem.Styles.TWO_HAND, (itemstack) -> DualAxesSkills.SPINNING_DEATH).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DualAxesAnimations.AXE_DUAL_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_DUAL)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.JUMP, Animations.BIPED_JUMP)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.KNEEL, Animations.BIPED_KNEEL)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SNEAK, Animations.BIPED_SNEAK)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_SWIM)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)
            .weaponCombinationPredicator((livingEntityPatch) -> true);

    public static final Function<Item, CapabilityItem.Builder> GREATSWORD = (item) -> (CapabilityItem.Builder) WeaponCapability.builder()
            .category(CapabilityItem.WeaponCategories.GREATSWORD)
            .styleProvider((playerpatch) -> playerpatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CapabilityItem.WeaponCategories.GREATSWORD ? CapabilityItem.Styles.OCHS : CapabilityItem.Styles.TWO_HAND).collider(ColliderPreset.GREATSWORD)
            .hitSound(EpicFightSounds.BLADE_HIT.get())
            .swingSound(EpicFightSounds.WHOOSH_BIG.get())
            .newStyleCombo(CapabilityItem.Styles.TWO_HAND, Animations.GREATSWORD_AUTO1, Animations.GREATSWORD_AUTO2, Animations.GREATSWORD_DASH, Animations.GREATSWORD_AIR_SLASH)
            .innateSkill(CapabilityItem.Styles.TWO_HAND, (itemstack) -> EpicFightSkills.STEEL_WHIRLWIND).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, Animations.BIPED_HOLD_GREATSWORD)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK_GREATSWORD)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.CHASE, Animations.BIPED_WALK_GREATSWORD)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_GREATSWORD)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.JUMP, Animations.BIPED_WALK_GREATSWORD)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.KNEEL, Animations.BIPED_HOLD_GREATSWORD)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SNEAK, Animations.BIPED_SNEAK)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_HOLD_GREATSWORD)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.GREATSWORD_GUARD)
            .newStyleCombo(CapabilityItem.Styles.OCHS, DualGreatSwordsAnimations.GREATSWORD_DUAL_AUTO_1, DualGreatSwordsAnimations.GREATSWORD_DUAL_AUTO_2, DualGreatSwordsAnimations.GREATSWORD_DUAL_AUTO_3, DualGreatSwordsAnimations.GREATSWORD_DUAL_AUTO_4, DualGreatSwordsAnimations.GREATSWORD_DUAL_DASH, DualGreatSwordsAnimations.GREATSWORD_DUAL_AIRSLASH)
            .innateSkill(CapabilityItem.Styles.OCHS, (itemstack) -> DualGreatSwordsSkills.EARTHQUAKE).livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.IDLE, DualGreatSwordsAnimations.GREATSWORD_DUAL_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.WALK, DualGreatSwordsAnimations.GREATSWORD_DUAL_WALK)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.CHASE, DualGreatSwordsAnimations.GREATSWORD_DUAL_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.RUN, DualGreatSwordsAnimations.GREATSWORD_DUAL_RUN)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.JUMP, DualGreatSwordsAnimations.GREATSWORD_DUAL_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.KNEEL, DualGreatSwordsAnimations.GREATSWORD_DUAL_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.SNEAK, DualGreatSwordsAnimations.GREATSWORD_DUAL_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.SWIM, DualGreatSwordsAnimations.GREATSWORD_DUAL_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)
            .weaponCombinationPredicator((entitypatch) -> entitypatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CapabilityItem.WeaponCategories.GREATSWORD);

    public static final Function<Item, CapabilityItem.Builder> AV_AXE =
            (item) -> (CapabilityItem.Builder) WeaponCapability.builder()
                    .category(CapabilityItem.WeaponCategories.AXE).styleProvider((entityPatch) -> {
                        if (entityPatch instanceof PlayerPatch<?> playerPatch) {
                            return playerPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CapabilityItem.WeaponCategories.AXE
                                    ? CapabilityItem.Styles.TWO_HAND : CapabilityItem.Styles.ONE_HAND;
                        } else if (entityPatch instanceof LivingEntityPatch<?>) {
                            return entityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CapabilityItem.WeaponCategories.AXE ? CapabilityItem.Styles.TWO_HAND : CapabilityItem.Styles.ONE_HAND;
                        }
                        return CapabilityItem.Styles.ONE_HAND;
                    })
                    .collider(ColliderPreset.TOOLS)
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .newStyleCombo(CapabilityItem.Styles.ONE_HAND,
                            Animations.AXE_AUTO1,
                            Animations.AXE_AUTO2,
                            Animations.SWORD_AUTO1,
                            Animations.SWORD_AUTO2,
                            Animations.SWORD_AUTO3,
                            Animations.AXE_DASH,
                            Animations.AXE_AIRSLASH)
                    .innateSkill(CapabilityItem.Styles.ONE_HAND, (itemstack) -> EpicFightSkills.GUILLOTINE_AXE).livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, Animations.BIPED_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, Animations.BIPED_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.RUN, Animations.BIPED_RUN)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.JUMP, Animations.BIPED_JUMP)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.KNEEL, Animations.BIPED_KNEEL)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.SNEAK, Animations.BIPED_SNEAK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.SWIM, Animations.BIPED_SWIM)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND, DualAxesAnimations.AXE_DUAL_AUTO_1, DualAxesAnimations.AXE_DUAL_AUTO_2, DualAxesAnimations.AXE_DUAL_AUTO_3, DualAxesAnimations.AXE_DUAL_DASH, DualAxesAnimations.AXE_DUAL_AIRSLASH)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, (itemstack) -> DualAxesSkills.SPINNING_DEATH).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, DualAxesAnimations.AXE_DUAL_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_DUAL)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.JUMP, Animations.BIPED_JUMP)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.KNEEL, Animations.BIPED_KNEEL)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SNEAK, Animations.BIPED_SNEAK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_SWIM)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)
                    .weaponCombinationPredicator((livingEntityPatch) -> true);

    public static final Function<Item, CapabilityItem.Builder> AV_GREATSWORD = (item) -> (CapabilityItem.Builder) WeaponCapability.builder()
            .category(CapabilityItem.WeaponCategories.GREATSWORD)
            .styleProvider((playerpatch) -> playerpatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CapabilityItem.WeaponCategories.GREATSWORD ? CapabilityItem.Styles.OCHS : CapabilityItem.Styles.TWO_HAND).collider(ColliderPreset.GREATSWORD)
            .hitSound(EpicFightSounds.BLADE_HIT.get())
            .swingSound(EpicFightSounds.WHOOSH_BIG.get())
            .newStyleCombo(CapabilityItem.Styles.TWO_HAND,
                    Animations.GREATSWORD_AUTO1,
                    Animations.GREATSWORD_AUTO2,
                    WOMAnimations.TORMENT_AUTO_2,
                    WOMAnimations.TORMENT_AUTO_3,
                    AnimsSolar.SOLAR_HORNO,
                    Animations.GREATSWORD_DASH,
                    Animations.GREATSWORD_AIR_SLASH)
            .innateSkill(CapabilityItem.Styles.TWO_HAND, (itemstack) -> EpicFightSkills.STEEL_WHIRLWIND).livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, Animations.BIPED_HOLD_GREATSWORD)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.BIPED_WALK_GREATSWORD)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.CHASE, Animations.BIPED_WALK_GREATSWORD)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.BIPED_RUN_GREATSWORD)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.JUMP, Animations.BIPED_WALK_GREATSWORD)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.KNEEL, Animations.BIPED_HOLD_GREATSWORD)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SNEAK, Animations.BIPED_SNEAK)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.SWIM, Animations.BIPED_HOLD_GREATSWORD)
            .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.GREATSWORD_GUARD)
            .newStyleCombo(CapabilityItem.Styles.OCHS, DualGreatSwordsAnimations.GREATSWORD_DUAL_AUTO_1, DualGreatSwordsAnimations.GREATSWORD_DUAL_AUTO_2, DualGreatSwordsAnimations.GREATSWORD_DUAL_AUTO_3, DualGreatSwordsAnimations.GREATSWORD_DUAL_AUTO_4, DualGreatSwordsAnimations.GREATSWORD_DUAL_DASH, DualGreatSwordsAnimations.GREATSWORD_DUAL_AIRSLASH)
            .innateSkill(CapabilityItem.Styles.OCHS, (itemstack) -> DualGreatSwordsSkills.EARTHQUAKE).livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.IDLE, DualGreatSwordsAnimations.GREATSWORD_DUAL_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.WALK, DualGreatSwordsAnimations.GREATSWORD_DUAL_WALK)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.CHASE, DualGreatSwordsAnimations.GREATSWORD_DUAL_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.RUN, DualGreatSwordsAnimations.GREATSWORD_DUAL_RUN)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.JUMP, DualGreatSwordsAnimations.GREATSWORD_DUAL_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.KNEEL, DualGreatSwordsAnimations.GREATSWORD_DUAL_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.SNEAK, DualGreatSwordsAnimations.GREATSWORD_DUAL_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.SWIM, DualGreatSwordsAnimations.GREATSWORD_DUAL_IDLE)
            .livingMotionModifier(CapabilityItem.Styles.OCHS, LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD)
            .weaponCombinationPredicator((entitypatch) -> entitypatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CapabilityItem.WeaponCategories.GREATSWORD);

    public WeaponCapabilityPresets() {
    }

    @SubscribeEvent
    public static void register(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath("epicfight", "axe"), AXE);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath("epicfight", "greatsword"), GREATSWORD);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath("annoyingvillagers", "av_axe"), AV_AXE);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath("annoyingvillagers", "av_greatsword"), AV_GREATSWORD);
    }
}