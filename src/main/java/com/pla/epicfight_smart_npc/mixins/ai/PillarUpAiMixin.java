package com.pla.epicfight_smart_npc.mixins.ai;

import com.pla.epicfight_smart_npc.util.EpicFightAiAnimation;
import com.pla.smart_npc.entity.PlayerNpcEntity;
import com.pla.smart_npc.entity.ai.PillarUpAi;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PillarUpAi.class, remap = false)
public abstract class PillarUpAiMixin {
    @Shadow
    @Final
    private PlayerNpcEntity playerNpc;

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/pla/smart_npc/entity/PlayerNpcEntity;shortPillarJump()V",
                    shift = At.Shift.AFTER
            )
    )
    private void epicfightSmartNpc$playPillarJumpAnimation(ServerLevel serverLevel, CallbackInfoReturnable<PillarUpAi.TickResult> callbackInfo) {
        EpicFightAiAnimation.playPillarJump(this.playerNpc);
    }
}
