package com.pla.annoyingvillagers_resurrection.gameasset;

import com.pla.annoyingvillagers_resurrection.AnnoyingVillagers_Resurrection;
import net.corruptdog.cdm.gameasset.CorruptSound;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.shelmarow.combat_evolution.gameassets.animation.ExecutionAttackAnimation;
import net.shelmarow.combat_evolution.gameassets.animation.ExecutionHitAnimation;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.collider.MultiCollider;
import yesman.epicfight.api.collider.MultiOBBCollider;
import yesman.epicfight.api.collider.OBBCollider;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.api.animation.property.AnimationProperty.StaticAnimationProperty;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;
import yesman.epicfight.world.damagesource.StunType;

import java.util.Set;

import static net.corruptdog.cdm.gameasset.CorruptAnimations.*;

@Mod.EventBusSubscriber(modid = AnnoyingVillagers_Resurrection.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AVEpicFightResurrectionAnimations {
    private static final ExtraDamageInstance.ExtraDamage TARGET_MAX_HEALTH = new ExtraDamageInstance.ExtraDamage((attacker, itemstack, target, baseDamage, params) -> params[0] + target.getMaxHealth() * params[1], (itemstack, tooltips, baseDamage, params) -> {
    });
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> STRANGLE_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> STRANGLE_EXECUTE_HIT;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> WRESTLING_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> WRESTLING_EXECUTE_HIT;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> WRESTLING_BACK_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> WRESTLING_BACK_EXECUTE_HIT;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> STAB_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> DUAL_GREATSWORD_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> DUAL_STAB_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> STAB_EXECUTE_HIT;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> SHIELD_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> SHIELD_EXECUTE_HIT;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> SWORD_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> SWORD_EXECUTE_HIT;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> FIST_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> FIST_EXECUTE_HIT;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> SPEAR_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> SPEAR_EXECUTE_HIT;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> GREATSWORD_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> GREATSWORD_EXECUTE_HIT;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> YAMATO_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> YAMATO_EXECUTE_HIT;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> KATANA_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> KATANA_EXECUTE_HIT;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> TRIDENT_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> TRIDENT_EXECUTE_HIT;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> TACHI_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> TACHI_EXECUTE_HIT;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> DAGGER_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> DAGGER_EXECUTE_HIT;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> LONGSWORD_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> LONGSWORD_EXECUTE_HIT;
    public static AnimationManager.AnimationAccessor<ExecutionAttackAnimation> DUAL_DAGGER_EXECUTE;
    public static AnimationManager.AnimationAccessor<ExecutionHitAnimation> DUAL_DAGGER_EXECUTE_HIT;

    @SubscribeEvent
    public static void registerAnimations(AnimationManager.AnimationRegistryEvent event) {
        event.newBuilder(AnnoyingVillagers_Resurrection.MODID, AVEpicFightResurrectionAnimations::build);
    }

    public static void build(AnimationManager.AnimationBuilder builder) {
        MultiCollider<OBBCollider> executionCollider = new MultiOBBCollider(3, 1.25F, 1.5F, 1.5F, 0.0F, 1.5F, -1.5F);
        MultiCollider<OBBCollider> executionColliderBack = new MultiOBBCollider(3, 1.25F, 1.5F, 1.5F, 0.0F, 1.5F, 1.5F);

        AVEpicFightResurrectionAnimations.STRANGLE_EXECUTE = builder.nextAccessor("biped/cdmoveset_clone/strangle_execute",
                (accessor) -> (new ExecutionAttackAnimation(0.01F, accessor, Armatures.BIPED,
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.1F, 0.29F, 1.0F, 1.2F, 1.2F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get()),
                        (new ExecutionAttackAnimation.ExecutionPhase(true, 1.2F, 0.0F, 3.36F, 1.9F, 1.9F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.OLD_FALL.get())))
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, (self, livingEntityPatch, speed, prevElapsedTime, elapsedTime) -> 1.0F)
                        .addEvents(new AnimationEvent[]{
                                AnimationEvent.InTimeEvent.create(0.6F, (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 9, false, false)),
                                        AnimationEvent.Side.BOTH)}
                        ));
        AVEpicFightResurrectionAnimations.STRANGLE_EXECUTE_HIT = builder.nextAccessor("biped/cdmoveset_clone/strangle_execute_hit", (accessor) -> (new ExecutionHitAnimation(0.01F, accessor, Armatures.BIPED)).addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, (self, livingEntityPatch, speed, prevElapsedTime, elapsedTime) -> 0.8333333F));

        AVEpicFightResurrectionAnimations.WRESTLING_EXECUTE = builder.nextAccessor("biped/cdmoveset_clone/wrestling_execute",
                (accessor) -> (new ExecutionAttackAnimation(0.05F, accessor, Armatures.BIPED,
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.05F, 1.85F, 2.0F, 2.0F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.OLD_FALL.get()),
                        (new ExecutionAttackAnimation.ExecutionPhase(true, 2.0F, 0.0F, 3.36F, 2.5F, 2.5F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())))
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, (self, livingEntityPatch, speed, prevElapsedTime, elapsedTime) -> 1.0F)
                        .addEvents(new AnimationEvent[]{
                                AnimationEvent.InTimeEvent.create(0.6F, (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 9, false, false)),
                                        AnimationEvent.Side.BOTH)}
                        ));
        AVEpicFightResurrectionAnimations.WRESTLING_EXECUTE_HIT = builder.nextAccessor("biped/cdmoveset_clone/wrestling_execute_hit", (accessor) -> (new ExecutionHitAnimation(0.01F, accessor, Armatures.BIPED)).addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, (self, livingEntityPatch, speed, prevElapsedTime, elapsedTime) -> 0.8333333F));

        AVEpicFightResurrectionAnimations.WRESTLING_BACK_EXECUTE = builder.nextAccessor("biped/cdmoveset_clone/wrestling_back_execute",
                (accessor) -> (new ExecutionAttackAnimation(0.05F, accessor, Armatures.BIPED,
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.05F, 1.85F, 2.0F, 2.0F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.OLD_FALL.get()),
                        (new ExecutionAttackAnimation.ExecutionPhase(true, 2.0F, 0.0F, 3.36F, 2.5F, 2.5F, Armatures.BIPED.get().rootJoint, executionColliderBack))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())))
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, (self, livingEntityPatch, speed, prevElapsedTime, elapsedTime) -> 1.0F)
                        .addEvents(new AnimationEvent[]{
                                AnimationEvent.InTimeEvent.create(0.6F, (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 9, false, false)),
                                        AnimationEvent.Side.BOTH)}
                        ));
        AVEpicFightResurrectionAnimations.WRESTLING_BACK_EXECUTE_HIT = builder.nextAccessor("biped/cdmoveset_clone/wrestling_back_execute_hit", (accessor) -> (new ExecutionHitAnimation(0.01F, accessor, Armatures.BIPED)).addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, (self, livingEntityPatch, speed, prevElapsedTime, elapsedTime) -> 0.8333333F));

        AVEpicFightResurrectionAnimations.STAB_EXECUTE = builder.nextAccessor("biped/cdmoveset_clone/stab_execute",
                (accessor) -> (new ExecutionAttackAnimation(0.05F, accessor, Armatures.BIPED,
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.05F, 0.05F, 1.85F, 2.0F, 0.4F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.OLD_FALL.get()),
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.4F,  0.4F, 0.6F, 0.6F, 1.1F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.01F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(4.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(true, 1.0F, 1.2F, 1.4F, Float.MAX_VALUE, Float.MAX_VALUE, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(4.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)))
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addEvents(new AnimationEvent[]{
                                AnimationEvent.InTimeEvent.create(0.6F, (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 9, false, false)),
                                        AnimationEvent.Side.BOTH)}
                        ));
        AVEpicFightResurrectionAnimations.DUAL_GREATSWORD_EXECUTE = builder.nextAccessor("biped/cdmoveset_clone/dual_greatsword_execute",
                (accessor) -> (new ExecutionAttackAnimation(0.05F, accessor, Armatures.BIPED,
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.05F, 0.05F, 1.85F, 2.0F, 0.4F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.OLD_FALL.get()),
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.4F,  0.4F, 0.6F, 0.6F, 1.1F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.01F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(4.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(true, 1.0F, 1.2F, 1.4F, Float.MAX_VALUE, Float.MAX_VALUE, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(4.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)))
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addEvents(new AnimationEvent[]{
                                AnimationEvent.InTimeEvent.create(0.6F, (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 9, false, false)),
                                        AnimationEvent.Side.BOTH)}
                        ));
        AVEpicFightResurrectionAnimations.DUAL_STAB_EXECUTE = builder.nextAccessor("biped/cdmoveset_clone/dual_stab_execute",
                (accessor) -> (new ExecutionAttackAnimation(0.05F, accessor, Armatures.BIPED,
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.05F, 0.05F, 1.85F, 2.0F, 0.4F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.OLD_FALL.get()),
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.4F,  0.4F, 0.6F, 0.6F, 1.1F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.01F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(4.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(true, 1.0F, 1.2F, 1.4F, Float.MAX_VALUE, Float.MAX_VALUE, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(4.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)))
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addEvents(new AnimationEvent[]{
                                AnimationEvent.InTimeEvent.create(0.6F, (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 9, false, false)),
                                        AnimationEvent.Side.BOTH)}
                        ));
        AVEpicFightResurrectionAnimations.STAB_EXECUTE_HIT = builder.nextAccessor("biped/cdmoveset_clone/stab_execute_hit", (accessor) -> (new ExecutionHitAnimation(0.1F, accessor, Armatures.BIPED)).addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE));

        AVEpicFightResurrectionAnimations.SHIELD_EXECUTE = builder.nextAccessor("biped/cdmoveset_clone/shield_execute",
                (accessor) -> (new ExecutionAttackAnimation(0.05F, accessor, Armatures.BIPED,
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.1F, 0.65F, 0.8F, 1.2F, 1.2F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.01F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(8.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 1.2F, 1.45F, 1.6F, 1.6F, 1.6F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.01F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(8.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(true, 1.6F, 2.05F, 2.3F, 2.3F, 2.3F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(4.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)))
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addEvents(new AnimationEvent[]{
                                AnimationEvent.InTimeEvent.create(0.6F, (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 9, false, false)),
                                        AnimationEvent.Side.BOTH)}
                        ));
        AVEpicFightResurrectionAnimations.SHIELD_EXECUTE_HIT = builder.nextAccessor("biped/cdmoveset_clone/shield_execute_hit", (accessor) -> (new ExecutionHitAnimation(0.1F, accessor, Armatures.BIPED)).addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE));

        AVEpicFightResurrectionAnimations.SWORD_EXECUTE = builder.nextAccessor("biped/cdmoveset_clone/sword_execute",
                (accessor) -> (new ExecutionAttackAnimation(0.05F, accessor, Armatures.BIPED,
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.55F, 0.75F, 1.1F, 1.3F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 1.3F, 1.45F, 1.65F, 1.8F, 1.8F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 1.8F, 2.35F, 2.55F, 2.6F, 2.6F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE),
                        (new ExecutionAttackAnimation.ExecutionPhase(true, 2.6F, 3.2F, 3.4F, Float.MAX_VALUE, Float.MAX_VALUE, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, CorruptSound.DAMAGE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)))
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
                        .addEvents(new AnimationEvent[]{
                                AnimationEvent.InTimeEvent.create(0.6F, (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 9, false, false)),
                                        AnimationEvent.Side.BOTH)}
                        ));
        AVEpicFightResurrectionAnimations.SWORD_EXECUTE_HIT = builder.nextAccessor("biped/cdmoveset_clone/sword_execute_hit", (accessor) -> (new ExecutionHitAnimation(0.1F, accessor, Armatures.BIPED)).addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, (self, livingEntityPatch, speed, prevElapsedTime, elapsedTime) -> elapsedTime >= 3.55F && elapsedTime < Float.MAX_VALUE ? 1.15F : 0.85F));

        AVEpicFightResurrectionAnimations.FIST_EXECUTE = builder.nextAccessor("biped/cdmoveset_clone/fist_execute",
                (accessor) -> (new ExecutionAttackAnimation(0.05F, accessor, Armatures.BIPED,
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.2F, 0.3F, 0.3F, 0.35F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.35F, 0.45F, 0.55F, 0.95F, 1.0F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(true, 1.0F, 1.15F, 1.4F, Float.MAX_VALUE, Float.MAX_VALUE, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)))
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, SLOW_SPEED)
                        .addEvents(new AnimationEvent[]{
                                AnimationEvent.InTimeEvent.create(0.6F, (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 9, false, false)),
                                        AnimationEvent.Side.BOTH)}
                        ));
        AVEpicFightResurrectionAnimations.FIST_EXECUTE_HIT = builder.nextAccessor("biped/cdmoveset_clone/fist_execute_hit", (accessor) -> (new ExecutionHitAnimation(0.1F, accessor, Armatures.BIPED)).addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, SLOW_SPEED));

        AVEpicFightResurrectionAnimations.SPEAR_EXECUTE = builder.nextAccessor("biped/cdmoveset_clone/spear_execute",
                (accessor) -> (new ExecutionAttackAnimation(0.05F, accessor, Armatures.BIPED,
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.35F, 0.31F, 0.95F, 1.1F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(true, 1.0F, 1.15F, 1.35F, Float.MAX_VALUE, Float.MAX_VALUE, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, CorruptSound.DAMAGE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)))
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, SLOW_SPEED)
                        .addEvents(new AnimationEvent[]{
                                AnimationEvent.InTimeEvent.create(0.6F, (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 9, false, false)),
                                        AnimationEvent.Side.BOTH)}
                        ));
        AVEpicFightResurrectionAnimations.SPEAR_EXECUTE_HIT = builder.nextAccessor("biped/cdmoveset_clone/spear_execute_hit", (accessor) -> (new ExecutionHitAnimation(0.1F, accessor, Armatures.BIPED)).addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, EXECUT));

        AVEpicFightResurrectionAnimations.GREATSWORD_EXECUTE = builder.nextAccessor("biped/cdmoveset_clone/greatsword_execute",
                (accessor) -> (new ExecutionAttackAnimation(0.05F, accessor, Armatures.BIPED,
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.75F, 0.95F, 1.85F, 1.85F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(true, 1.0F, 1.85F, 2.15F, 2.55F, Float.MAX_VALUE, Float.MAX_VALUE, Armatures.BIPED.get().rootJoint, executionColliderBack))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, CorruptSound.DAMAGE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)))
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, EXECUT)
                        .addEvents(new AnimationEvent[]{
                                AnimationEvent.InTimeEvent.create(0.6F, (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 9, false, false)),
                                        AnimationEvent.Side.BOTH)}
                        ));
        AVEpicFightResurrectionAnimations.GREATSWORD_EXECUTE_HIT = builder.nextAccessor("biped/cdmoveset_clone/greatsword_execute_hit", (accessor) -> (new ExecutionHitAnimation(0.6F, accessor, Armatures.BIPED)).addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, SLOW_SPEED));

        AVEpicFightResurrectionAnimations.YAMATO_EXECUTE = builder.nextAccessor("biped/cdmoveset_clone/yamato_execute",
                (accessor) -> (new ExecutionAttackAnimation(0.05F, accessor, Armatures.BIPED,
                        (new ExecutionAttackAnimation.ExecutionPhase(false, .0F, 0.35F, 0.55F, 0.7F, 0.7F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, CorruptSound.DAMAGE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(true, 0.7F, 0.75F, 1.05F, Float.MAX_VALUE, Float.MAX_VALUE, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)))
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, SLOW_SPEED)
                        .addEvents(new AnimationEvent[]{
                                AnimationEvent.InTimeEvent.create(0.6F, (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 9, false, false)),
                                        AnimationEvent.Side.BOTH)}
                        ));
        AVEpicFightResurrectionAnimations.YAMATO_EXECUTE_HIT = builder.nextAccessor("biped/cdmoveset_clone/yamato_execute_hit", (accessor) -> (new ExecutionHitAnimation(0.05F, accessor, Armatures.BIPED)).addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, EXECUT));

        AVEpicFightResurrectionAnimations.KATANA_EXECUTE = builder.nextAccessor("biped/cdmoveset_clone/katana_execute",
                (accessor) -> (new ExecutionAttackAnimation(0.05F, accessor, Armatures.BIPED,
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.0F, 0.35F, 0.55F, 0.7F, 0.7F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, CorruptSound.DAMAGE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(true, 0.7F, 0.75F, 1.05F, Float.MAX_VALUE, Float.MAX_VALUE, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)))
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, SLOW_SPEED)
                        .addEvents(new AnimationEvent[]{
                                AnimationEvent.InTimeEvent.create(0.6F, (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 9, false, false)),
                                        AnimationEvent.Side.BOTH)}
                        ));
        AVEpicFightResurrectionAnimations.KATANA_EXECUTE_HIT = builder.nextAccessor("biped/cdmoveset_clone/katana_execute_hit", (accessor) -> (new ExecutionHitAnimation(0.05F, accessor, Armatures.BIPED)).addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, EXECUT));

        AVEpicFightResurrectionAnimations.TRIDENT_EXECUTE = builder.nextAccessor("biped/cdmoveset_clone/trident_execute",
                (accessor) -> (new ExecutionAttackAnimation(0.01F, accessor, Armatures.BIPED,
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.25F, 0.5F, 0.8F, 0.8F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(true, 0.8F, 0.85F, 1.1F, 2.3F, Float.MAX_VALUE, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, CorruptSound.DAMAGE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)))
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, SLOW_SPEED)
                        .addEvents(new AnimationEvent[]{
                                AnimationEvent.InTimeEvent.create(0.6F, (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 9, false, false)),
                                        AnimationEvent.Side.BOTH)}
                        ));
        AVEpicFightResurrectionAnimations.TRIDENT_EXECUTE_HIT = builder.nextAccessor("biped/cdmoveset_clone/trident_execute_hit", (accessor) -> (new ExecutionHitAnimation(0.05F, accessor, Armatures.BIPED)).addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, EXECUT));

        AVEpicFightResurrectionAnimations.TACHI_EXECUTE = builder.nextAccessor("biped/cdmoveset_clone/tachi_execute",
                (accessor) -> (new ExecutionAttackAnimation(0.05F, accessor, Armatures.BIPED,
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.35F, 0.41F, 0.45F, 0.5F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.5F, 0.55F, 0.75F, 0.95F, 1.0F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(true, 1.0F, 1.35F, 1.55F, Float.MAX_VALUE, Float.MAX_VALUE, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, CorruptSound.DAMAGE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)))
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, SLOW_SPEED)
                        .addEvents(new AnimationEvent[]{
                                AnimationEvent.InTimeEvent.create(0.6F, (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 9, false, false)),
                                        AnimationEvent.Side.BOTH)}
                        ));
        AVEpicFightResurrectionAnimations.TACHI_EXECUTE_HIT = builder.nextAccessor("biped/cdmoveset_clone/tachi_execute_hit", (accessor) -> (new ExecutionHitAnimation(0.05F, accessor, Armatures.BIPED)).addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, TACHI_EX));

        AVEpicFightResurrectionAnimations.DAGGER_EXECUTE = builder.nextAccessor("biped/cdmoveset_clone/dagger_execute",
                (accessor) -> (new ExecutionAttackAnimation(0.05F, accessor, Armatures.BIPED,
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.55F, 0.8F, 1.1F, 1.3F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 1.3F, 1.45F, 1.55F, 1.6F, 1.6F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, CorruptSound.DAMAGE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(true, 1.6F, 1.9F, 2.1F, Float.MAX_VALUE, Float.MAX_VALUE, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)))
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, EXECUT)
                        .addEvents(new AnimationEvent[]{
                                AnimationEvent.InTimeEvent.create(0.6F, (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 9, false, false)),
                                        AnimationEvent.Side.BOTH)}
                        ));
        AVEpicFightResurrectionAnimations.DAGGER_EXECUTE_HIT = builder.nextAccessor("biped/cdmoveset_clone/dagger_execute_hit", (accessor) -> (new ExecutionHitAnimation(0.05F, accessor, Armatures.BIPED)).addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, EXECUT));

        AVEpicFightResurrectionAnimations.LONGSWORD_EXECUTE = builder.nextAccessor("biped/cdmoveset_clone/longsword_execute",
                (accessor) -> (new ExecutionAttackAnimation(0.05F, accessor, Armatures.BIPED,
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.7F, 0.85F, 1.1F, 1.3F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 1.3F, 1.35F, 1.55F, 1.6F, 1.6F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, CorruptSound.DAMAGE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(true, 1.6F, 2.35F, 2.55F, Float.MAX_VALUE, Float.MAX_VALUE, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)))
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, SLOW_SPEED)
                        .addEvents(new AnimationEvent[]{
                                AnimationEvent.InTimeEvent.create(0.6F, (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 9, false, false)),
                                        AnimationEvent.Side.BOTH)}
                        ));
        AVEpicFightResurrectionAnimations.LONGSWORD_EXECUTE_HIT = builder.nextAccessor("biped/cdmoveset_clone/longsword_execute_hit", (accessor) -> (new ExecutionHitAnimation(0.05F, accessor, Armatures.BIPED)).addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, (self, livingEntityPatch, speed, prevElapsedTime, elapsedTime) -> elapsedTime >= 3.0F && elapsedTime < Float.MAX_VALUE ? 1.25F : 0.7F));

        AVEpicFightResurrectionAnimations.DUAL_DAGGER_EXECUTE = builder.nextAccessor("biped/cdmoveset_clone/dual_dagger_execute",
                (accessor) -> (new ExecutionAttackAnimation(0.1F, accessor, Armatures.BIPED,
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.0F, 0.2F, 0.3F, 0.3F, 0.35F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLADE_RUSH_FINISHER.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 0.35F, 0.45F, 0.55F, 0.95F, 1.0F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, CorruptSound.DAMAGE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(false, 1.0F, 1.15F, 1.3F, 1.3F, 1.35F, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, CorruptSound.DAMAGE.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.0F)),
                        (new ExecutionAttackAnimation.ExecutionPhase(true, 1.35F, 1.65F, 1.75F, Float.MAX_VALUE, Float.MAX_VALUE, Armatures.BIPED.get().rootJoint, executionCollider))
                                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.5F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1.0F))
                                .addProperty(AnimationProperty.AttackPhaseProperty.EXTRA_DAMAGE, Set.of(TARGET_MAX_HEALTH.create(15.0F, 0.08F)))
                                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)))
                        .addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, SLOW_SPEED)
                        .addEvents(new AnimationEvent[]{
                                AnimationEvent.InTimeEvent.create(0.6F, (livingEntityPatch, assetAccessor, animationParameters) -> livingEntityPatch.getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 9, false, false)),
                                        AnimationEvent.Side.BOTH)}
                        ));
        AVEpicFightResurrectionAnimations.DUAL_DAGGER_EXECUTE_HIT = builder.nextAccessor("biped/cdmoveset_clone/dual_dagger_execute_hit", (accessor) -> (new ExecutionHitAnimation(0.05F, accessor, Armatures.BIPED)).addProperty(StaticAnimationProperty.PLAY_SPEED_MODIFIER, SLOW_SPEED));
    }
}