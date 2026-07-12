package com.pla.epicfight_smart_npc.access;

import com.pla.epicfight_smart_npc.IdleAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.asset.AssetAccessor;

public interface PlayerNpcIdleAnimationAccess {
    IdleAnimation epicfightSmartNpc$getIdleAnimationChoice();

    void epicfightSmartNpc$setIdleAnimationChoice(IdleAnimation idleAnimation);

    AssetAccessor<? extends StaticAnimation> epicfightSmartNpc$getIdleAnimation();

    void epicfightSmartNpc$setIdleAnimation(AssetAccessor<? extends StaticAnimation> idleAnimation);

    boolean epicfightSmartNpc$isPlayingIdle();

    void epicfightSmartNpc$setPlayingIdle(boolean playingIdle);

    void epicfightSmartNpc$clearIdleAnimationState();
}
