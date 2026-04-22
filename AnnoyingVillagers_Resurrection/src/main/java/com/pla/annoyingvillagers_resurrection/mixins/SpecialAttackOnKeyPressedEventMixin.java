package com.pla.annoyingvillagers_resurrection.mixins;
import com.pla.annoyingvillagers.event.SpecialAttackOnKeyPressedEvent;
import com.pla.annoyingvillagers.gameasset.AVAnimations;
import net.corruptdog.cdm.world.CorruptWeaponCategories;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

@Mixin(value = {SpecialAttackOnKeyPressedEvent.class}, remap = false)
public abstract class SpecialAttackOnKeyPressedEventMixin {
    @Inject(method = "registerMoreSpecialAttackCategories", at = @At("HEAD"), cancellable = true)
    private static void addCdMovesetSpecialAttack(PlayerPatch<?> playerpatch, Entity entity, LivingEntityPatch<?> livingEntityPatch, CallbackInfo ci) {
        if (playerpatch.getHoldingItemCapability(InteractionHand.MAIN_HAND).getWeaponCategory() == CorruptWeaponCategories.S_SPEAR) {
            if (entity.level() instanceof ServerLevel) {
                livingEntityPatch.playAnimationSynchronized(AVAnimations.SPEAR_THRUST, 0.0F);
                ci.cancel();
            }
        }

        if (playerpatch.getHoldingItemCapability(InteractionHand.MAIN_HAND).getWeaponCategory() == CorruptWeaponCategories.S_SWORD
                || playerpatch.getHoldingItemCapability(InteractionHand.MAIN_HAND).getWeaponCategory() == CorruptWeaponCategories.S_DAGGER
                || playerpatch.getHoldingItemCapability(InteractionHand.MAIN_HAND).getWeaponCategory() == CorruptWeaponCategories.GREAT_TACHI
                && (playerpatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CorruptWeaponCategories.S_SWORD
                || playerpatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CorruptWeaponCategories.GREAT_TACHI
                || playerpatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == CorruptWeaponCategories.S_DAGGER)) {
            if (entity.level() instanceof ServerLevel) {
                if (!entity.getPersistentData().contains("DualSwordCombo")) {
                    livingEntityPatch.playAnimationSynchronized(Animations.DAGGER_DUAL_DASH, 0.0F);
                    entity.getPersistentData().putDouble("DualSwordCombo", 1.0);
                } else if (entity.getPersistentData().getDouble("DualSwordCombo") == 1.0) {
                    livingEntityPatch.playAnimationSynchronized(Animations.LONGSWORD_AUTO2, 0.0F);
                    entity.getPersistentData().putDouble("DualSwordCombo", 2.0);
                } else if (entity.getPersistentData().getDouble("DualSwordCombo") == 2.0) {
                    livingEntityPatch.playAnimationSynchronized(AVAnimations.DUAL_DANCING_EDGE, 0.0F);
                    entity.getPersistentData().putDouble("DualSwordCombo", 3.0);
                } else if (entity.getPersistentData().getDouble("DualSwordCombo") == 3.0) {
                    livingEntityPatch.playAnimationSynchronized(AVAnimations.DUAL_SWORD_DANCING_EDGE, 0.0F);
                    entity.getPersistentData().remove("DualSwordCombo");
                }
                ci.cancel();
            }
        }

        if ((playerpatch.getHoldingItemCapability(InteractionHand.MAIN_HAND).getWeaponCategory() == CorruptWeaponCategories.S_SWORD
                || playerpatch.getHoldingItemCapability(InteractionHand.MAIN_HAND).getWeaponCategory() == CorruptWeaponCategories.S_LONGSWORD
                || playerpatch.getHoldingItemCapability(InteractionHand.MAIN_HAND).getWeaponCategory() == CorruptWeaponCategories.S_TACHI
                || playerpatch.getHoldingItemCapability(InteractionHand.MAIN_HAND).getWeaponCategory() == CorruptWeaponCategories.S_DAGGER
                || playerpatch.getHoldingItemCapability(InteractionHand.MAIN_HAND).getWeaponCategory() == CorruptWeaponCategories.GREAT_TACHI
                || playerpatch.getHoldingItemCapability(InteractionHand.MAIN_HAND).getWeaponCategory() == CorruptWeaponCategories.KATANA)
                && (playerpatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() != CorruptWeaponCategories.S_SWORD
                && playerpatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() != CorruptWeaponCategories.GREAT_TACHI
                && playerpatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() != CorruptWeaponCategories.S_DAGGER)) {
            if (entity.level() instanceof ServerLevel) {
                if (!entity.getPersistentData().contains("SwordCombo")) {
                    livingEntityPatch.playAnimationSynchronized(AVAnimations.SWORD_HEAVY_AUTO_1, 0.0F);
                    entity.getPersistentData().putDouble("SwordCombo", 1.0);
                } else if (entity.getPersistentData().getDouble("SwordCombo") == 1.0) {
                    livingEntityPatch.playAnimationSynchronized(AVAnimations.SWORD_HEAVY_AUTO_2, 0.0F);
                    entity.getPersistentData().putDouble("SwordCombo", 2.0);
                } else if (entity.getPersistentData().getDouble("SwordCombo") == 2.0) {
                    livingEntityPatch.playAnimationSynchronized(AVAnimations.SWORD_HEAVY_AUTO_3, 0.0F);
                    entity.getPersistentData().remove("SwordCombo");
                }
                ci.cancel();
            }
        }

        if (playerpatch.getHoldingItemCapability(InteractionHand.MAIN_HAND).getWeaponCategory() == CorruptWeaponCategories.S_GREATSWORD) {
            if (entity.level() instanceof ServerLevel) {
                livingEntityPatch.playAnimationSynchronized(AVAnimations.GIANT_WHIRLWIND, 0.0F);
                ci.cancel();
            }
        }
    }
}
