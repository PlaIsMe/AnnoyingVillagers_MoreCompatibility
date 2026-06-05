package com.pla.player_npc_weapons_expansion.mixins.refm;

import com.pla.annoyingvillagers.event.SpecialAttackOnKeyPressedEvent;
import com.pla.annoyingvillagers.gameasset.AnimsEpicFightBattleArts;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.yonchi.refm.world.capabilities.item.RapierWeaponCategories;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

@Mixin(value = {SpecialAttackOnKeyPressedEvent.class}, remap = false)
public abstract class SpecialAttackOnKeyPressedEventMixin {
    @Inject(method = "registerMoreSpecialAttackCategories", at = @At("HEAD"), cancellable = true)
    private static void addRefmSpecialAttack(PlayerPatch<?> playerpatch, Entity entity, LivingEntityPatch<?> livingEntityPatch, CallbackInfo ci) {
        WeaponCategory mainHandCategory = playerpatch.getHoldingItemCapability(InteractionHand.MAIN_HAND).getWeaponCategory();

        if (isRapier(mainHandCategory) && entity.level() instanceof ServerLevel) {
            livingEntityPatch.playAnimationSynchronized(AnimsEpicFightBattleArts.SABRE_QUAD_STING, 0.0F);
            ci.cancel();
        }
    }

    private static boolean isRapier(WeaponCategory category) {
        return category == RapierWeaponCategories.RAPIER
                || category == RapierWeaponCategories.ENDER_RAPIER
                || category == RapierWeaponCategories.OCEAN_RAPIER
                || category == RapierWeaponCategories.WITHER_RAPIER
                || category == RapierWeaponCategories.AMETHYST_RAPIER;
    }
}
