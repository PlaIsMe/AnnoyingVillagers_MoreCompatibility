package com.pla.epicfight_smart_npc.util;

import com.pla.epicfight_smart_npc.access.PlayerNpcDiggingAnimationAccess;
import com.pla.epicfight_smart_npc.gameasset.SmartNpcAnimations;
import com.pla.smart_npc.entity.PlayerNpcEntity;
import com.pla.smart_npc.task.DelayedTask;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.animation.Animator;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

public final class EpicFightAiAnimation {
    private static final String DIGGING_ANIMATION_ACTIVE_KEY = "epicfight_smart_npc_digging_animation_active";
    private static final String LAST_DIGGING_ANIMATION_SYNC_TICK_KEY = "epicfight_smart_npc_last_digging_animation_sync_tick";
    private static final String LAST_MAIN_HAND_USE_ANIMATION_TICK_KEY = "epicfight_smart_npc_last_main_hand_use_animation_tick";
    private static final int DIGGING_ANIMATION_RESYNC_TICKS = 10;
    private static final int MAIN_HAND_USE_ANIMATION_STOP_TICKS = 6;

    private EpicFightAiAnimation() {
    }

    public static void playMiningSwing(PlayerNpcEntity playerNpc) {
        playSynchronized(playerNpc, EpicFightAiAnimation::miningSwingAnimation);
    }

    public static void playMainHandUse(PlayerNpcEntity playerNpc) {
        playMainHandAnimation(playerNpc, true);
    }

    public static void playMainHandAttack(PlayerNpcEntity playerNpc) {
        playMainHandAnimation(playerNpc, false);
    }

    public static boolean isDiggingAnimationActive(PlayerNpcEntity playerNpc) {
        return playerNpc instanceof PlayerNpcDiggingAnimationAccess access
                && access.epicfightSmartNpc$isDiggingAnimationActive();
    }

    private static void playMainHandAnimation(PlayerNpcEntity playerNpc, boolean swingHand) {
        if (!canAnimate(playerNpc)) {
            return;
        }

        LivingEntityPatch<?> patch = getPatch(playerNpc);
        if (patch == null) {
            return;
        }

        AssetAccessor<? extends StaticAnimation> animation = miningSwingAnimation(patch);
        if (playIfPresent(patch, animation)) {
            long useTick = playerNpc.level().getGameTime();
            playerNpc.getPersistentData().putLong(LAST_MAIN_HAND_USE_ANIMATION_TICK_KEY, useTick);
            if (swingHand) {
                swingMainHand(playerNpc);
            }
            stopMainHandUseAnimationLater(playerNpc, animation, useTick);
        }
    }

    public static void updateDigging(PlayerNpcEntity playerNpc, boolean digging) {
        if (playerNpc == null) {
            return;
        }

        if (!digging) {
            setDiggingAnimationActive(playerNpc, false);
        }

        if (!canAnimate(playerNpc)) {
            return;
        }

        LivingEntityPatch<?> patch = getPatch(playerNpc);
        if (patch == null) {
            return;
        }

        CompoundTag persistentData = playerNpc.getPersistentData();
        if (!digging) {
            if (persistentData.getBoolean(DIGGING_ANIMATION_ACTIVE_KEY)) {
                stopIfPresent(patch, SmartNpcAnimations.MINING_SWING);
                stopIfPresent(patch, Animations.BIPED_DIG_MAINHAND);
                stopIfPresent(patch, Animations.BIPED_DIG);
                persistentData.remove(DIGGING_ANIMATION_ACTIVE_KEY);
                persistentData.remove(LAST_DIGGING_ANIMATION_SYNC_TICK_KEY);
            }
            return;
        }

        patch.currentLivingMotion = LivingMotions.DIGGING;
        patch.currentCompositeMotion = LivingMotions.DIGGING;

        long gameTime = playerNpc.level().getGameTime();
        boolean active = persistentData.getBoolean(DIGGING_ANIMATION_ACTIVE_KEY);
        if (active) {
            setDiggingAnimationActive(playerNpc, true);
        }
        long lastSyncTick = persistentData.getLong(LAST_DIGGING_ANIMATION_SYNC_TICK_KEY);
        if (!active || gameTime - lastSyncTick >= DIGGING_ANIMATION_RESYNC_TICKS) {
            boolean playedMainHandAnimationThisTick = persistentData.contains(LAST_MAIN_HAND_USE_ANIMATION_TICK_KEY)
                    && persistentData.getLong(LAST_MAIN_HAND_USE_ANIMATION_TICK_KEY) == gameTime;
            if (playedMainHandAnimationThisTick || playDiggingAnimation(playerNpc, patch)) {
                setDiggingAnimationActive(playerNpc, true);
                persistentData.putBoolean(DIGGING_ANIMATION_ACTIVE_KEY, true);
                persistentData.putLong(LAST_DIGGING_ANIMATION_SYNC_TICK_KEY, gameTime);
            }
        }
    }

    private static void setDiggingAnimationActive(PlayerNpcEntity playerNpc, boolean active) {
        if (playerNpc instanceof PlayerNpcDiggingAnimationAccess access) {
            access.epicfightSmartNpc$setDiggingAnimationActive(active);
        }
    }

    private static void playSynchronized(PlayerNpcEntity playerNpc, AnimationResolver animationResolver) {
        if (!canAnimate(playerNpc)) {
            return;
        }

        LivingEntityPatch<?> patch = getPatch(playerNpc);
        if (patch != null) {
            playIfPresent(patch, animationResolver.resolve(patch));
        }
    }

    private static AssetAccessor<? extends StaticAnimation> miningSwingAnimation(LivingEntityPatch<?> patch) {
        AssetAccessor<? extends StaticAnimation> animation = firstPresent(
                SmartNpcAnimations.MINING_SWING,
                Animations.BIPED_DIG_MAINHAND,
                Animations.BIPED_DIG
        );

        if (animation != null || patch == null || SmartNpcAnimations.MINING_SWING == null) {
            return animation;
        }

        Animator animator = patch.getAnimator();
        return animator == null ? null : animator.getLivingAnimation(LivingMotions.DIGGING, SmartNpcAnimations.MINING_SWING);
    }

    private static void stopMainHandUseAnimationLater(PlayerNpcEntity playerNpc, AssetAccessor<? extends StaticAnimation> animation, long useTick) {
        new DelayedTask(MAIN_HAND_USE_ANIMATION_STOP_TICKS) {
            @Override
            public void run() {
                if (!canAnimate(playerNpc)) {
                    return;
                }

                CompoundTag persistentData = playerNpc.getPersistentData();
                if (persistentData.getLong(LAST_MAIN_HAND_USE_ANIMATION_TICK_KEY) != useTick) {
                    return;
                }

                persistentData.remove(LAST_MAIN_HAND_USE_ANIMATION_TICK_KEY);
                if (persistentData.getBoolean(DIGGING_ANIMATION_ACTIVE_KEY)) {
                    return;
                }

                stopIfPresent(getPatch(playerNpc), animation);
            }
        };
    }

    private static void swingMainHand(PlayerNpcEntity playerNpc) {
        playerNpc.swing(InteractionHand.MAIN_HAND, true);
    }

    private static boolean playDiggingAnimation(PlayerNpcEntity playerNpc, LivingEntityPatch<?> patch) {
        swingMainHand(playerNpc);
        return playIfPresent(patch, miningSwingAnimation(patch));
    }

    @SafeVarargs
    private static AssetAccessor<? extends StaticAnimation> firstPresent(AssetAccessor<? extends StaticAnimation>... animations) {
        for (AssetAccessor<? extends StaticAnimation> animation : animations) {
            if (animation != null) {
                return animation;
            }
        }
        return null;
    }

    private static boolean playIfPresent(LivingEntityPatch<?> patch, AssetAccessor<? extends StaticAnimation> animation) {
        if (patch == null || animation == null) {
            return false;
        }

        patch.playAnimationSynchronized(animation, 0.0F);
        return true;
    }

    private static void stopIfPresent(LivingEntityPatch<?> patch, AssetAccessor<? extends StaticAnimation> animation) {
        if (patch != null && animation != null) {
            patch.stopPlaying(animation);
        }
    }

    private static LivingEntityPatch<?> getPatch(PlayerNpcEntity playerNpc) {
        return EpicFightCapabilities.getEntityPatch(playerNpc, LivingEntityPatch.class);
    }

    private static boolean canAnimate(PlayerNpcEntity playerNpc) {
        return playerNpc != null
                && !playerNpc.level().isClientSide
                && playerNpc.isAlive()
                && !playerNpc.isRemoved()
                && !playerNpc.isDeadOrDying();
    }

    private interface AnimationResolver {
        AssetAccessor<? extends StaticAnimation> resolve(LivingEntityPatch<?> patch);
    }
}
