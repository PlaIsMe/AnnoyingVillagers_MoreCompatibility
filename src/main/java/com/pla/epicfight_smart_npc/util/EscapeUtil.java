package com.pla.epicfight_smart_npc.util;

import com.pla.epicfight_smart_npc.compat.EpicFightNightFall;
import com.pla.epicfight_smart_npc.compat.EpicFightResurrection;
import com.pla.epicfight_smart_npc.compat.EpicFightSwordSoaring;
import com.pla.epicfight_smart_npc.compat.Wom;
import com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.gameassets.animation.ExecutionAttackAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EscapeUtil {
    private static final Set<String> DANGEROUS_ANIMATIONS = new HashSet<>();

    static {
        if (ModList.get().isLoaded("annoyingvillagers")) {
            try {
                DANGEROUS_ANIMATIONS.addAll(AnnoyingVillagers.getDangerousAnimations());
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }

        if (ModList.get().isLoaded("wom")) {
            try {
                DANGEROUS_ANIMATIONS.addAll(Wom.getDangerousAnimations());
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }

        if (ModList.get().isLoaded("efn")) {
            try {
                DANGEROUS_ANIMATIONS.addAll(EpicFightNightFall.getDangerousAnimations());
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }

        if (ModList.get().isLoaded("sword_soaring")) {
            try {
                DANGEROUS_ANIMATIONS.addAll(EpicFightSwordSoaring.getDangerousAnimations());
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }

        if (ModList.get().isLoaded("cdmoveset")) {
            try {
                DANGEROUS_ANIMATIONS.addAll(EpicFightResurrection.getDangerousAnimations());
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
    }

    public static boolean isAnimationDangerous(AssetAccessor<? extends StaticAnimation> targetDynamicAnimation) {
        if (targetDynamicAnimation != null && targetDynamicAnimation.get().getRegistryName() != null) {
            String animation = targetDynamicAnimation.get().getRegistryName().toString();
            return DANGEROUS_ANIMATIONS.contains(animation);
        }
        return false;
    }

    public static boolean checkEscape(Mob mob) {
        LivingEntity target = mob.getTarget();
        LivingEntityPatch<?> targetLivingEntityPatch = EpicFightCapabilities.getEntityPatch(target, LivingEntityPatch.class);
        if (target == null || targetLivingEntityPatch == null) return false;
        AssetAccessor<? extends StaticAnimation> targetDynamicAnimation = Objects.requireNonNull(targetLivingEntityPatch.getAnimator().getPlayerFor(null)).getRealAnimation();
        return isAnimationDangerous(targetDynamicAnimation) || targetDynamicAnimation.get() instanceof ExecutionAttackAnimation;
    }
}
