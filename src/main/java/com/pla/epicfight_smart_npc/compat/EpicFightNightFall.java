package com.pla.epicfight_smart_npc.compat;

import com.hm.efn.animations.types.stun.EFNStunAnimation;
import com.hm.efn.client.sound.EFNSounds;
import com.hm.efn.gameasset.EFNAnimations;
import com.hm.efn.gameasset.animations.*;
import com.hm.efn.item.custom.*;
import com.hm.efn.item.geo.ExcaliburItem;
import com.hm.efn.particle.EFNParticles;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.shelmarow.combat_evolution.ai.CEHumanoidPatch;
import org.joml.Vector3d;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.AnimationPlayer;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.particle.HitParticleType;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

import java.util.HashSet;
import java.util.Set;

public class EpicFightNightFall {
    private static final Set<String> DANGEROUS_ANIMATIONS = new HashSet<>();
    public static Set<String> getDangerousAnimations() {
        return DANGEROUS_ANIMATIONS;
    }

    public static boolean isEFNStun(AssetAccessor<? extends StaticAnimation> assetAccessor) {
        return assetAccessor.get() instanceof EFNStunAnimation;
    }
}
