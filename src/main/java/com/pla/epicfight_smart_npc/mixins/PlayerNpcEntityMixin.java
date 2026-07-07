package com.pla.epicfight_smart_npc.mixins;

import com.pla.epicfight_smart_npc.util.CombatBehaviour;
import com.pla.epicfight_smart_npc.util.EpicfightUtil;
import com.pla.smart_npc.entity.PlayerNpcEntity;
import com.pla.smart_npc.task.DelayedTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

import java.util.Objects;
import java.util.Random;

@Mixin(value = PlayerNpcEntity.class, remap = false)
public abstract class PlayerNpcEntityMixin {
    @Shadow
    private int stunEscapeCooldown;

    @Shadow
    private int playingIdleCooldown;

    @Inject(method = "tick", at = @At("TAIL"))
    private void epicfightSmartNpc$tickGuardBreakWakeUp(CallbackInfo ci) {
        PlayerNpcEntity entity = (PlayerNpcEntity) (Object) this;

        if (ModList.get().isLoaded("efkick")
                && this.stunEscapeCooldown == 0
                && entity.level() instanceof ServerLevel serverLevel) {
            LivingEntityPatch<?> livingEntityPatch = EpicFightCapabilities.getEntityPatch(entity, LivingEntityPatch.class);
            if (livingEntityPatch != null) {
                AssetAccessor<? extends StaticAnimation> dynamicAnimation =
                        Objects.requireNonNull(livingEntityPatch.getAnimator().getPlayerFor(null)).getRealAnimation();
                if (EpicfightUtil.isLongHitAnimationNotExecutedAnimation(dynamicAnimation, livingEntityPatch)
                        && entity.isAlive()
                        && entity.getRandom().nextFloat() < CombatBehaviour.calculateGuardBreakWakeUpChance(entity)) {
                    this.stunEscapeCooldown = 100;
                    this.playingIdleCooldown = this.playingIdleCooldown + 100;

                    new DelayedTask(new Random().nextInt(5, 10)) {
                        @Override
                        public void run() {
                            LivingEntityPatch<?> currentPatch = EpicFightCapabilities.getEntityPatch(entity, LivingEntityPatch.class);
                            if (currentPatch != null
                                    && EpicfightUtil.isLongHitAnimationNotExecutedAnimation(dynamicAnimation, currentPatch)
                                    && entity.isAlive()) {
                                CombatBehaviour.postGuardBreakWakeUp(entity, currentPatch, serverLevel);
                            } else {
                                PlayerNpcEntityMixin.this.stunEscapeCooldown = 1;
                            }
                        }
                    };
                }
            }
        }
    }
}
