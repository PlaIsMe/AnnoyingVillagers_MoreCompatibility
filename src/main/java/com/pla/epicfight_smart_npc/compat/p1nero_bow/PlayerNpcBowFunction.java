package com.pla.epicfight_smart_npc.compat.p1nero_bow;

import com.pla.smart_npc.entity.PlayerNpcEntity;
import net.minecraft.world.entity.LivingEntity;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

public final class PlayerNpcBowFunction {
    private PlayerNpcBowFunction() {
    }

    public static void bowShoot(LivingEntityPatch<?> livingEntityPatch) {
        if (!(livingEntityPatch.getOriginal() instanceof PlayerNpcEntity playerNpc)) {
            return;
        }

        LivingEntity target = livingEntityPatch.getTarget();
        if (target == null || !target.isAlive()) {
            return;
        }

        playerNpc.performRangedAttack(target, 1.0F);
    }
}
