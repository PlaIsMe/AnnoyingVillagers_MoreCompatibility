package com.pla.epicfight_smart_npc.mixins.goal;

import com.pla.epicfight_smart_npc.util.EpicFightAiAnimation;
import com.pla.smart_npc.entity.PlayerNpcEntity;
import com.pla.smart_npc.entity.goal.EscapeHoleWithBlockGoal;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EscapeHoleWithBlockGoal.class, remap = false)
public abstract class EscapeHoleWithBlockGoalMixin {
    @Shadow
    @Final
    private PlayerNpcEntity playerNpc;

    @Shadow
    private BlockPos minePos;

    @Shadow
    private BlockPos pillarClearPos;

    @Shadow
    private BlockPos exitClearPos;

    @Shadow
    private int mineTicks;

    @Inject(method = {"tick", "m_8037_"}, at = @At("TAIL"), require = 0)
    private void epicfightSmartNpc$updateEscapeDiggingAnimation(CallbackInfo callbackInfo) {
        EpicFightAiAnimation.updateDigging(this.playerNpc, this.epicfightSmartNpc$isDirectMining());
    }

    @Inject(method = {"stop", "m_8041_"}, at = @At("TAIL"), require = 0)
    private void epicfightSmartNpc$stopEscapeDiggingAnimation(CallbackInfo callbackInfo) {
        EpicFightAiAnimation.updateDigging(this.playerNpc, false);
    }

    @Inject(method = "clearPillarClearance", at = @At("TAIL"), require = 0)
    private void epicfightSmartNpc$stopPillarClearDiggingAnimation(CallbackInfo callbackInfo) {
        EpicFightAiAnimation.updateDigging(this.playerNpc, false);
    }

    @Unique
    private boolean epicfightSmartNpc$isDirectMining() {
        return this.mineTicks > 0
                && (this.minePos != null
                || this.pillarClearPos != null
                || this.exitClearPos != null);
    }
}
