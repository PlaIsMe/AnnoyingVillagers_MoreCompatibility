package com.pla.epicfight_smart_npc.util;

import com.pla.epicfight_smart_npc.compat.EpicFightNightFall;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.execution.ExecutionHandler;
import net.shelmarow.combat_evolution.gameassets.animation.ExecutionHitAnimation;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

public class EpicfightUtil {
    public static boolean isLongHitAnimationNotExecutedAnimation(AssetAccessor<? extends StaticAnimation> dynamicAnimation, LivingEntityPatch<?> livingEntityPatch) {
        return !(dynamicAnimation.get() instanceof ExecutionHitAnimation)
                && (dynamicAnimation.get() instanceof KnockdownAnimation
                || (ModList.get().isLoaded("efn") && EpicFightNightFall.isEFNStun(dynamicAnimation))
                || ExecutionHandler.isTargetGuardBreak(dynamicAnimation, livingEntityPatch));
    }

    public static boolean isLongHitAnimation(AssetAccessor<? extends StaticAnimation> dynamicAnimation, LivingEntityPatch<?> livingEntityPatch) {
        return dynamicAnimation.get() instanceof ExecutionHitAnimation
                || dynamicAnimation.get() instanceof KnockdownAnimation
                || (ModList.get().isLoaded("efn") && EpicFightNightFall.isEFNStun(dynamicAnimation))
                || ExecutionHandler.isTargetGuardBreak(dynamicAnimation, livingEntityPatch);
    }
}
