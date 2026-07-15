package com.pla.epicfight_smart_npc.mixins;

import com.pla.epicfight_smart_npc.IdleAnimation;
import com.pla.epicfight_smart_npc.access.PlayerNpcDiggingAnimationAccess;
import com.pla.epicfight_smart_npc.access.PlayerNpcIdleAnimationAccess;
import com.pla.epicfight_smart_npc.goal.KeepPositionGoal;
import com.pla.epicfight_smart_npc.util.CombatBehaviour;
import com.pla.epicfight_smart_npc.util.EpicFightAiAnimation;
import com.pla.epicfight_smart_npc.util.EpicfightUtil;
import com.pla.smart_npc.clazz.FakePlayer;
import com.pla.smart_npc.entity.PlayerNpcEntity;
import com.pla.smart_npc.task.DelayedTask;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
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
public abstract class PlayerNpcEntityMixin extends FakePlayer implements PlayerNpcIdleAnimationAccess, PlayerNpcDiggingAnimationAccess {
    @Unique
    private static final EntityDataAccessor<Boolean> EPICFIGHT_SMART_NPC_DIGGING_ANIMATION_ACTIVE =
            SynchedEntityData.defineId(PlayerNpcEntity.class, EntityDataSerializers.BOOLEAN);

    @Shadow
    private int stunEscapeCooldown;

    @Shadow
    private int playingIdleCooldown;

    @Unique
    private IdleAnimation epicfightSmartNpc$idleAnimationChoice;

    @Unique
    private AssetAccessor<? extends StaticAnimation> epicfightSmartNpc$idleAnimation;

    @Unique
    private boolean epicfightSmartNpc$playingIdle;

    protected PlayerNpcEntityMixin(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public IdleAnimation epicfightSmartNpc$getIdleAnimationChoice() {
        return this.epicfightSmartNpc$idleAnimationChoice;
    }

    @Override
    public void epicfightSmartNpc$setIdleAnimationChoice(IdleAnimation idleAnimation) {
        this.epicfightSmartNpc$idleAnimationChoice = idleAnimation;
    }

    @Override
    public AssetAccessor<? extends StaticAnimation> epicfightSmartNpc$getIdleAnimation() {
        return this.epicfightSmartNpc$idleAnimation;
    }

    @Override
    public void epicfightSmartNpc$setIdleAnimation(AssetAccessor<? extends StaticAnimation> idleAnimation) {
        this.epicfightSmartNpc$idleAnimation = idleAnimation;
    }

    @Override
    public boolean epicfightSmartNpc$isPlayingIdle() {
        return this.epicfightSmartNpc$playingIdle;
    }

    @Override
    public void epicfightSmartNpc$setPlayingIdle(boolean playingIdle) {
        this.epicfightSmartNpc$playingIdle = playingIdle;
    }

    @Override
    public void epicfightSmartNpc$clearIdleAnimationState() {
        this.epicfightSmartNpc$idleAnimationChoice = null;
        this.epicfightSmartNpc$idleAnimation = null;
        this.epicfightSmartNpc$playingIdle = false;
    }

    @Override
    public boolean epicfightSmartNpc$isDiggingAnimationActive() {
        return this.entityData.get(EPICFIGHT_SMART_NPC_DIGGING_ANIMATION_ACTIVE);
    }

    @Override
    public void epicfightSmartNpc$setDiggingAnimationActive(boolean active) {
        this.entityData.set(EPICFIGHT_SMART_NPC_DIGGING_ANIMATION_ACTIVE, active);
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void epicfightSmartNpc$defineDiggingAnimationData(CallbackInfo ci) {
        this.entityData.define(EPICFIGHT_SMART_NPC_DIGGING_ANIMATION_ACTIVE, false);
    }

    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void epicfightSmartNpc$registerKeepPositionGoal(CallbackInfo ci) {
        this.goalSelector.addGoal(1, new KeepPositionGoal((PlayerNpcEntity) (Object) this));
    }

    @Inject(method = "triggerMainHandUseAnimation", at = @At("TAIL"))
    private void epicfightSmartNpc$playMainHandUseAnimation(CallbackInfo ci) {
        EpicFightAiAnimation.playMainHandUse((PlayerNpcEntity) (Object) this);
    }

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
