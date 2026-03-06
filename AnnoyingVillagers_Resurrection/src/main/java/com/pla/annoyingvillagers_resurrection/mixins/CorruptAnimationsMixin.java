package com.pla.annoyingvillagers_resurrection.mixins;

import net.corruptdog.cdm.gameasset.CorruptAnimations;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.types.MainFrameAnimation;
import yesman.epicfight.skill.BasicAttack;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.entity.eventlistener.ComboCounterHandleEvent;

@Mixin(value = {CorruptAnimations.class}, remap = false)
public class CorruptAnimationsMixin {
    @Shadow @Final @Mutable
    private static AnimationEvent.E0 COMBO_BREAK;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void replaceComboBreak(CallbackInfo ci) {
        COMBO_BREAK = (entitypatch, animation, params) -> {
            if (!(entitypatch instanceof ServerPlayerPatch patch)) return;

            AnimationManager.AnimationAccessor<? extends MainFrameAnimation> causal = null;
            if (animation instanceof AnimationManager.AnimationAccessor<?> animationAccessor
                    && animationAccessor.get() instanceof MainFrameAnimation) {
                @SuppressWarnings("unchecked")
                AnimationManager.AnimationAccessor<? extends MainFrameAnimation> casted =
                        (AnimationManager.AnimationAccessor<? extends MainFrameAnimation>) animationAccessor;
                causal = casted;
            }
            if (causal != null) {
                BasicAttack.setComboCounterWithEvent(
                        ComboCounterHandleEvent.Causal.ANOTHER_ACTION_ANIMATION,
                        patch,
                        patch.getSkill(SkillSlots.BASIC_ATTACK),
                        causal,
                        0
                );
            }
        };
    }
}