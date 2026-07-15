package com.pla.epicfight_smart_npc.mixins.ai;

import com.pla.epicfight_smart_npc.util.EpicFightAiAnimation;
import com.pla.smart_npc.entity.PlayerNpcEntity;
import com.pla.smart_npc.entity.ai.BreakingBlockAi;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(value = BreakingBlockAi.class, remap = false)
public abstract class BreakingBlockAiMixin {
    @Shadow
    @Final
    private PlayerNpcEntity playerNpc;

    @Inject(
            method = "tick(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Ljava/util/function/Predicate;ILjava/lang/String;Z)Lcom/pla/smart_npc/entity/ai/BreakingBlockAi$TickResult;",
            at = @At("RETURN")
    )
    private void epicfightSmartNpc$updateDiggingAnimation(
            ServerLevel serverLevel,
            BlockPos targetPos,
            Predicate<BlockState> targetPredicate,
            int requiredTicks,
            String detail,
            boolean allowBlockEntity,
            CallbackInfoReturnable<BreakingBlockAi.TickResult> callbackInfo
    ) {
        EpicFightAiAnimation.updateDigging(this.playerNpc, callbackInfo.getReturnValue() == BreakingBlockAi.TickResult.RUNNING);
    }

    @Inject(
            method = "tickMiningSwing",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/pla/smart_npc/entity/PlayerNpcEntity;triggerMainHandAttackAnimation()V",
                    shift = At.Shift.AFTER
            )
    )
    private void epicfightSmartNpc$playMiningSwingAnimation(CallbackInfo callbackInfo) {
        EpicFightAiAnimation.playMainHandAttack(this.playerNpc);
    }

    @Inject(method = "stop", at = @At("TAIL"))
    private void epicfightSmartNpc$stopDiggingAnimation(CallbackInfo callbackInfo) {
        EpicFightAiAnimation.updateDigging(this.playerNpc, false);
    }
}
