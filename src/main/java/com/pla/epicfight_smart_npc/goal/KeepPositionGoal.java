package com.pla.epicfight_smart_npc.goal;

import com.pla.annoyingvillagers.combatbehaviour.CombatCommon;
import com.pla.smart_npc.entity.PlayerNpcEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import java.util.EnumSet;

public class KeepPositionGoal extends Goal {
    private final Mob mob;
    private double anchorX;
    private double anchorY;
    private double anchorZ;

    public KeepPositionGoal(Mob mob) {
        this.mob = mob;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (mob instanceof PlayerNpcEntity playerNpcEntity) {
            return CombatCommon.canEscape((MobPatch<?>) EpicFightCapabilities.getEntityPatch(playerNpcEntity, LivingEntityPatch.class));
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        if (mob instanceof PlayerNpcEntity playerNpcEntity) {
            return CombatCommon.canEscape((MobPatch<?>) EpicFightCapabilities.getEntityPatch(playerNpcEntity, LivingEntityPatch.class));
        }
        return false;
    }

    @Override
    public void start() {
        this.anchorX = mob.getX();
        this.anchorY = mob.getY();
        this.anchorZ = mob.getZ();
        mob.getNavigation().stop();
    }

    @Override
    public void stop() {
        mob.getNavigation().stop();
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        mob.getNavigation().stop();

        LivingEntity target = mob.getTarget();
        if (target != null) {
            mob.getLookControl().setLookAt(target, 30.0F, 30.0F);
        }

        if (mob.distanceToSqr(anchorX, anchorY, anchorZ) > 0.25D) {
            mob.getMoveControl().setWantedPosition(anchorX, anchorY, anchorZ, 1.0D);
        }
    }
}