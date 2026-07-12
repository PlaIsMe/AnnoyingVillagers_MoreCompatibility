package com.pla.epicfight_smart_npc.mixins;

import com.pla.epicfight_smart_npc.goal.PlayIdleAnimationGoal;
import com.pla.smart_npc.entity.PlayerNpcEntity;
import com.pla.smart_npc.entity.goal.BeingAtHomeGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BeingAtHomeGoal.class, remap = false)
public abstract class BeingAtHomeGoalMixin {
    @Unique
    private static final int epicfightSmartNpc$HOME_IDLE_MIN_DURATION_TICKS = 80;

    @Unique
    private PlayIdleAnimationGoal epicfightSmartNpc$homeIdleAnimationGoal;

    @Unique
    private boolean epicfightSmartNpc$homeIdleAnimationRunning;

    @Inject(method = "tickCustomHomeIdleAnimation", at = @At("HEAD"), cancellable = true)
    private void epicfightSmartNpc$tickCustomHomeIdleAnimation(PlayerNpcEntity playerNpc, CallbackInfoReturnable<Boolean> callbackInfo) {
        PlayIdleAnimationGoal idleAnimationGoal = this.epicfightSmartNpc$getHomeIdleAnimationGoal(playerNpc);

        if (this.epicfightSmartNpc$homeIdleAnimationRunning) {
            if (idleAnimationGoal.canContinueToUse()) {
                idleAnimationGoal.tick();
                callbackInfo.setReturnValue(true);
            } else {
                idleAnimationGoal.stop();
                this.epicfightSmartNpc$homeIdleAnimationRunning = false;
                callbackInfo.setReturnValue(false);
            }
            return;
        }

        if (idleAnimationGoal.canUse()) {
            idleAnimationGoal.start();
            this.epicfightSmartNpc$homeIdleAnimationRunning = true;
            callbackInfo.setReturnValue(true);
        }
    }

    @Inject(method = "stopCustomHomeIdleAnimation", at = @At("HEAD"))
    private void epicfightSmartNpc$stopCustomHomeIdleAnimation(PlayerNpcEntity playerNpc, CallbackInfo callbackInfo) {
        if (!this.epicfightSmartNpc$homeIdleAnimationRunning || this.epicfightSmartNpc$homeIdleAnimationGoal == null) {
            return;
        }

        this.epicfightSmartNpc$homeIdleAnimationGoal.stop();
        this.epicfightSmartNpc$homeIdleAnimationRunning = false;
    }

    @Unique
    private PlayIdleAnimationGoal epicfightSmartNpc$getHomeIdleAnimationGoal(PlayerNpcEntity playerNpc) {
        if (this.epicfightSmartNpc$homeIdleAnimationGoal == null) {
            this.epicfightSmartNpc$homeIdleAnimationGoal =
                    new PlayIdleAnimationGoal(playerNpc, epicfightSmartNpc$HOME_IDLE_MIN_DURATION_TICKS);
        }
        return this.epicfightSmartNpc$homeIdleAnimationGoal;
    }
}
