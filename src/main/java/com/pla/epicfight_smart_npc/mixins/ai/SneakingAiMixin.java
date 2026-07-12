package com.pla.epicfight_smart_npc.mixins.ai;

import com.pla.epicfight_smart_npc.util.EpicFightAiAnimation;
import com.pla.smart_npc.entity.PlayerNpcEntity;
import com.pla.smart_npc.entity.ai.SneakingAi;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SneakingAi.class, remap = false)
public abstract class SneakingAiMixin {
    @Shadow
    @Final
    private PlayerNpcEntity playerNpc;

    @Inject(method = "setSneaking", at = @At("TAIL"))
    private void epicfightSmartNpc$updateSneakAnimation(boolean sneaking, CallbackInfo callbackInfo) {
        EpicFightAiAnimation.updateSneaking(this.playerNpc, sneaking);
    }
}
