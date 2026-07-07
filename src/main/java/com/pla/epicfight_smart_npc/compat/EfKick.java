package com.pla.epicfight_smart_npc.compat;

import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import com.pla.epicfight_smart_npc.util.EpicfightUtil;
import com.pla.efkick.animations.KickAttackAnimation;
import com.pla.efkick.config.EFKickConfig;
import com.pla.efkick.gameasset.EFKickAnimations;
import net.minecraft.world.damagesource.DamageSource;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

public class EfKick {
    public static AnimationManager.AnimationAccessor<? extends StaticAnimation>[] kickAnimations() {
        return CombatCommon.animations(
                EFKickAnimations.KICK_1,
                EFKickAnimations.KICK_2,
                EFKickAnimations.KICK_3,
                EFKickAnimations.KICK_4,
                EFKickAnimations.KICK_C,
                EFKickAnimations.KICK_RUSH,
                EFKickAnimations.KICK_H
        );
    }

    public static AnimationManager.AnimationAccessor<? extends StaticAnimation>[] fistKickAnimations() {
        return CombatCommon.animations(
                EFKickAnimations.KICK_1,
                EFKickAnimations.KICK_2,
                EFKickAnimations.KICK_3,
                EFKickAnimations.KICK_4,
                EFKickAnimations.KICK_C,
                EFKickAnimations.KICK_RUSH,
                EFKickAnimations.KICK_COMBO
        );
    }

    public static AnimationManager.AnimationAccessor<? extends StaticAnimation>[] basicKickAnimations() {
        return CombatCommon.animations(
                EFKickAnimations.KICK_1,
                EFKickAnimations.KICK_2,
                EFKickAnimations.KICK_3,
                EFKickAnimations.KICK_4
        );
    }
}
