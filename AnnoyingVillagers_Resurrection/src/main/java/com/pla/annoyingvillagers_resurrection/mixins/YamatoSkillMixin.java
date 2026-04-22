package com.pla.annoyingvillagers_resurrection.mixins;

import net.corruptdog.cdm.gameasset.CDSkills;
import net.corruptdog.cdm.gameasset.CorruptAnimations;
import net.corruptdog.cdm.gameasset.CorruptSound;
import net.corruptdog.cdm.skill.CDSkillDataKeys;
import net.corruptdog.cdm.skill.weaponinnate.YamatoSkill;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.AnimationPlayer;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.utils.AttackResult;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.skill.*;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.entity.eventlistener.ComboCounterHandleEvent;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Mixin(value = YamatoSkill.class, remap = false)
public abstract class YamatoSkillMixin extends WeaponInnateSkill {
    @Shadow @Final
    private static UUID EVENT_UUID;
    @Shadow @Final
    private ScheduledExecutorService scheduler;

    protected YamatoSkillMixin(SkillBuilder<? extends WeaponInnateSkill> builder) {
        super(builder);
    }

    @Inject(method = {"onInitiate"}, at = {@At("HEAD")}, cancellable = true)
    private void overrideOnInitiate(SkillContainer container, CallbackInfo ci) {
        super.onInitiate(container);
        YamatoSkill self = (YamatoSkill) (Object) this;
        container.getExecutor().getOriginal().getMainHandItem().getOrCreateTag().putBoolean("unsheathed", false);
        PlayerEventListener listener = container.getExecutor().getEventListener();
        listener.addEventListener(PlayerEventListener.EventType.ACTION_EVENT_SERVER, EVENT_UUID, (event) -> {
            int animation = event.getAnimation().get().getId();
            if (container.getExecutor().getSkill(SkillSlots.DODGE).isActivated()) {
                PlayerPatch<?> playerPatch = container.getExecutor();
                if (playerPatch instanceof ServerPlayerPatch patch) {
                    BasicAttack.setComboCounterWithEvent(ComboCounterHandleEvent.Causal.ANOTHER_ACTION_ANIMATION, patch, patch.getSkill(SkillSlots.BASIC_ATTACK), event.getAnimation(), 0);
                    container.getExecutor().getOriginal().getMainHandItem().getOrCreateTag().putBoolean("unsheathed", false);
                }
            }

            if (animation == CorruptAnimations.YAMATO_COUNTER1.get().getId()) {
                container.getDataManager().setData(CDSkillDataKeys.COUNTER_SUCCESS.get(), true);
            }

            if (animation == CorruptAnimations.YAMATO_COUNTER2.get().getId()) {
                container.getDataManager().setData(CDSkillDataKeys.COUNTER_SUCCESS.get(), false);
            }

            if (animation == CorruptAnimations.YAMATO_RISING_STAR.get().getId()) {
                this.scheduler.schedule(() -> container.getExecutor().getSkill(SkillSlots.WEAPON_INNATE).getDataManager().setData(CDSkillDataKeys.YAMATO_ATTACK_ACTIVE.get(), true), 1000L, TimeUnit.MILLISECONDS);
            }

            if (animation == CorruptAnimations.YAMATO_STRIKE1.get().getId() || animation == CorruptAnimations.YAMATO_STRIKE2.get().getId()) {
                container.getDataManager().setData(CDSkillDataKeys.COUNTER_SUCCESS.get(), false);
            }

            if (animation == CorruptAnimations.YAMATO_POWER3.get().getId()) {
                container.getDataManager().setData(CDSkillDataKeys.YAMATO_POWER3_ACTIVE.get(), true);
            }

            if (animation != CorruptAnimations.YAMATO_POWER3.get().getId() && animation != CorruptAnimations.YAMATO_POWER3_REPEAT.get().getId() && (Boolean)container.getDataManager().getDataValue((SkillDataKey)CDSkillDataKeys.YAMATO_POWER3_ACTIVE.get())) {
                container.getDataManager().setData(CDSkillDataKeys.YAMATO_POWER3_ACTIVE.get(), false);
            }

            if (animation != CorruptAnimations.YAMATO_AIR1.get().getId() && animation != CorruptAnimations.YAMATO_AIR2.get().getId() && animation != CorruptAnimations.YAMATO_AIR3.get().getId() && animation != CorruptAnimations.YAMATO_RISING_STAR_SKILL_DAWN.get().getId()) {
                int attack = (Integer)container.getDataManager().getDataValue((SkillDataKey)CDSkillDataKeys.YAMATO_ATTACK.get());
                if (attack > 0) {
                    container.getDataManager().setData(CDSkillDataKeys.YAMATO_ATTACK.get(), 0);
                }
            }

            if (animation == CorruptAnimations.YAMATO_STRIKE1.get().getId()) {
                Skill skill = container.getExecutor().getSkill(SkillSlots.WEAPON_INNATE).getSkill();
                int strike1_cost = 1;
                int skillstack = event.getPlayerPatch().getSkill(CDSkills.YAMATOSKILL).getStack();
                if (skillstack >= 1 && skill != null) {
                    stackCost(container, strike1_cost, self);
                }
            }

            if (animation != CorruptAnimations.YAMATO_POWER3.get().getId() && animation != CorruptAnimations.YAMATO_POWER3_REPEAT.get().getId() && animation != CorruptAnimations.YAMATO_POWER3_FINISH.get().getId() && animation != CorruptAnimations.YAMATO_POWER_DASH.get().getId() && animation != CorruptAnimations.YAMATO_POWER0_1.get().getId() && animation != CorruptAnimations.YAMATO_JUDGEMENT_CUT_END.get().getId() && (Integer)container.getDataManager().getDataValue((SkillDataKey)CDSkillDataKeys.DAMAGES.get()) > 1) {
                container.getDataManager().setData(CDSkillDataKeys.DAMAGES.get(), 0);
            }

        });
        listener.addEventListener(PlayerEventListener.EventType.ATTACK_ANIMATION_END_EVENT, EVENT_UUID, (event) -> {
            int id = event.getAnimation().get().getId();
            if (id != CorruptAnimations.YAMATO_POWER3.get().getId() && id != CorruptAnimations.YAMATO_POWER3_REPEAT.get().getId()) {
                if (id == CorruptAnimations.YAMATO_COUNTER1.get().getId()) {
                    event.getPlayerPatch().reserveAnimation(CorruptAnimations.YAMATO_COUNTER2);
                } else if (id != CorruptAnimations.YAMATO_POWER3_FINISH.get().getId() && id != CorruptAnimations.YAMATO_POWER_DASH.get().getId()) {
                    if (id == CorruptAnimations.YAMATO_DAWN.get().getId()) {
                        event.getPlayerPatch().reserveAnimation(CorruptAnimations.YAMATO_DAWN_END);
                        int end_recover = 1;
                        ServerPlayerPatch playerPatch = (ServerPlayerPatch)container.getExecutor();
                        if (playerPatch != null) {
                            stackCost(container, -end_recover, self);
                        }
                    }
                } else {
                    container.getDataManager().setData(CDSkillDataKeys.DAMAGES.get(), 0);
                }
            } else {
                event.getPlayerPatch().reserveAnimation(CorruptAnimations.YAMATO_POWER3_FINISH);
            }

        });
        listener.addEventListener(PlayerEventListener.EventType.DEAL_DAMAGE_EVENT_ATTACK, EVENT_UUID, (event) -> {
            int id = event.getDamageSource().getAnimation().get().getId();
            float maxstamina = event.getPlayerPatch().getMaxStamina();
            float stamina = event.getPlayerPatch().getStamina();
            if (id == CorruptAnimations.YAMATO_POWER1.get().getId()) {
                float r = 0.25F;
                if (stamina < maxstamina) {
                    container.getExecutor().setStamina(stamina + r * maxstamina);
                }
            } else if (id == CorruptAnimations.YAMATO_COUNTER2.get().getId()) {
                float c = 0.1F;
                if (stamina < maxstamina) {
                    container.getExecutor().setStamina(stamina + c * maxstamina);
                }
            }

            if (id == CorruptAnimations.YAMATO_POWER3_REPEAT.get().getId() || id == CorruptAnimations.YAMATO_POWER3.get().getId() || id == CorruptAnimations.YAMATO_JUDGEMENT_CUT_END.get().getId()) {
                Integer k = (Integer)container.getDataManager().getDataValue((SkillDataKey)CDSkillDataKeys.DAMAGES.get());
                container.getDataManager().setData(CDSkillDataKeys.DAMAGES.get(), k + 1);
            }

            if (id == CorruptAnimations.YAMATO_AIR1.get().getId() || id == CorruptAnimations.YAMATO_AIR2.get().getId() || id == CorruptAnimations.YAMATO_AIR3.get().getId() || id == CorruptAnimations.YAMATO_RISING_STAR_SKILL_DAWN.get().getId() || id == CorruptAnimations.YAMATO_RISING_STAR_END.get().getId() || id == CorruptAnimations.YAMATO_RISING_STAR.get().getId()) {
                event.getTarget().addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 30, 0));
            }

        });
        listener.addEventListener(PlayerEventListener.EventType.MODIFY_DAMAGE_EVENT, EVENT_UUID, (event) -> {
            ResourceLocation rl = Objects.requireNonNull(container.getExecutor().getAnimator().getPlayerFor(null)).getAnimation().get().getRegistryName();
            float attackDamage = event.getBaseDamage();
            float bonus = 0.23F;
            int max = 35;
            if (rl == CorruptAnimations.YAMATO_POWER3_FINISH.get().getRegistryName() || rl == CorruptAnimations.YAMATO_POWER_DASH.get().getRegistryName() || rl == CorruptAnimations.YAMATO_JUDGEMENT_CUT_END.get().getRegistryName()) {
                Integer k = (Integer)container.getDataManager().getDataValue((SkillDataKey)CDSkillDataKeys.DAMAGES.get());
                k = Math.min(k, max);
                event.attachValueModifier(ValueModifier.adder(attackDamage * (1.0F + bonus * (float)k)));
                System.out.println(attackDamage * (1.0F + bonus * (float)k));
            }

        });
        listener.addEventListener(PlayerEventListener.EventType.BASIC_ATTACK_EVENT, EVENT_UUID, (event) -> {
            ServerPlayerPatch executor = event.getPlayerPatch();
            BlockPos ground = executor.getOriginal().getOnPos();
            boolean isInAir = executor.getOriginal().level().getBlockState(ground).is(Blocks.AIR);
            if ((Boolean)container.getDataManager().getDataValue((SkillDataKey)CDSkillDataKeys.YAMATO_ATTACK_ACTIVE.get()) && isInAir) {
                float stamina = event.getPlayerPatch().getStamina();
                float cost = 2.0F;
                if (stamina < cost) {
                    container.getDataManager().setData(CDSkillDataKeys.YAMATO_ATTACK.get(), 0);
                    container.getDataManager().setData(CDSkillDataKeys.YAMATO_ATTACK_ACTIVE.get(), false);
                    return;
                }

                executor.setStamina(stamina - cost);
                event.setCanceled(true);
                int attack = (Integer)container.getDataManager().getDataValue((SkillDataKey)CDSkillDataKeys.YAMATO_ATTACK.get());
                StaticAnimation[] animations = new StaticAnimation[]{CorruptAnimations.YAMATO_AIR1.get(), CorruptAnimations.YAMATO_AIR2.get(), CorruptAnimations.YAMATO_AIR3.get(), CorruptAnimations.YAMATO_RISING_STAR_SKILL_DAWN.get()};
                executor.playAnimationSynchronized(animations[attack].getRealAnimation(), 0.0F);
                if (attack < 3) {
                    SkillDataManager var10000 = container.getDataManager();
                    SkillDataKey<Integer> var10001 = CDSkillDataKeys.YAMATO_ATTACK.get();
                    ++attack;
                    var10000.setData(var10001, attack);
                } else {
                    container.getDataManager().setData(CDSkillDataKeys.YAMATO_ATTACK.get(), 0);
                    container.getDataManager().setData(CDSkillDataKeys.YAMATO_ATTACK_ACTIVE.get(), false);
                }
            }

            Boolean counterSuccess = (Boolean)container.getDataManager().getDataValue((SkillDataKey)CDSkillDataKeys.COUNTER_SUCCESS.get());
            if (counterSuccess) {
                event.setCanceled(true);
                executor.playAnimationSynchronized(CorruptAnimations.YAMATO_STRIKE1, 0.0F);
                container.getDataManager().setData(CDSkillDataKeys.COUNTER_SUCCESS.get(), false);
            }

            float stamina = event.getPlayerPatch().getStamina();
            float cost = 4.0F;
            Boolean power3 = (Boolean)container.getDataManager().getDataValue((SkillDataKey)CDSkillDataKeys.YAMATO_POWER3_ACTIVE.get());
            if (stamina < cost) {
                container.getDataManager().setData(CDSkillDataKeys.YAMATO_POWER3_ACTIVE.get(), false);
            }

            ResourceLocation rl = Objects.requireNonNull(container.getExecutor().getAnimator().getPlayerFor(null)).getAnimation().get().getRegistryName();
            if (power3 && (rl == CorruptAnimations.YAMATO_POWER3.get().getRegistryName() || rl == CorruptAnimations.YAMATO_POWER3_REPEAT.get().getRegistryName())) {
                if (stamina < cost) {
                    event.setCanceled(true);
                    executor.playAnimationSynchronized(CorruptAnimations.YAMATO_POWER3_FINISH, 0.0F);
                    container.getExecutor().setStamina(stamina - cost);
                } else {
                    container.getExecutor().setStamina(stamina - cost);
                    event.setCanceled(true);
                    executor.playAnimationSynchronized(CorruptAnimations.YAMATO_POWER3_REPEAT, 0.0F);
                }
            }

            int skillstack = event.getPlayerPatch().getSkill(SkillSlots.WEAPON_INNATE).getStack();
            int power_cost = 5;
            if ((skillstack == 5 || container.getExecutor().getOriginal().isCreative()) && container.getExecutor().getOriginal().isCrouching() && container.getExecutor().getOriginal().onGround() && container.getExecutor().getSkill(SkillSlots.WEAPON_INNATE).hasSkill(CDSkills.YAMATOSKILL)) {
                container.getExecutor().playSound(CorruptSound.POWER.get(), 0.8F, 1.2F);
                event.setCanceled(true);
                container.getExecutor().getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 3));
                this.scheduler.schedule(() -> container.getExecutor().getOriginal().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 30, 50)), 500L, TimeUnit.MILLISECONDS);
                container.getExecutor().getOriginal().addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20, 10));
                container.getExecutor().playAnimationSynchronized(CorruptAnimations.YAMATO_JUDGEMENT_CUT_END, 0.0F);
                stackCost(container, power_cost, self);
            }

        });
        listener.addEventListener(PlayerEventListener.EventType.TAKE_DAMAGE_EVENT_ATTACK, EVENT_UUID, (event) -> {
            int power2_recover = 2;
            Skill skill = container.getExecutor().getSkill(SkillSlots.WEAPON_INNATE).getSkill();
            ServerPlayerPatch executer = event.getPlayerPatch();
            AnimationPlayer animationPlayer = executer.getAnimator().getPlayerFor(null);
            float elapsedTime = 0.0F;
            if (animationPlayer != null) {
                elapsedTime = animationPlayer.getElapsedTime();
            }

            int animationId = Objects.requireNonNull(executer.getAnimator().getPlayerFor(null)).getAnimation().get().getId();
            if (elapsedTime <= 0.4F && animationId == CorruptAnimations.YAMATO_POWER0_1.get().getId()) {
                DamageSource damagesource = event.getDamageSource();
                Vec3 sourceLocation = damagesource.getSourcePosition();
                if (sourceLocation != null) {
                    Vec3 viewVector = event.getPlayerPatch().getOriginal().getViewVector(1.0F);
                    Vec3 toSourceLocation = sourceLocation.subtract(event.getPlayerPatch().getOriginal().position()).normalize();
                    if (toSourceLocation.dot(viewVector) > (double)0.0F && !damagesource.is(DamageTypeTags.IS_EXPLOSION) && !damagesource.is(DamageTypes.MAGIC) && !damagesource.is(DamageTypeTags.BYPASSES_ARMOR) && !damagesource.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                        container.getDataManager().setData(CDSkillDataKeys.COUNTER_SUCCESS.get(), true);
                        this.scheduler.schedule(() -> executer.getSkill(SkillSlots.WEAPON_INNATE).getDataManager().setData(CDSkillDataKeys.COUNTER_SUCCESS.get(), false), 1500L, TimeUnit.MILLISECONDS);
                        event.setCanceled(true);
                        event.setResult(AttackResult.ResultType.BLOCKED);
                        POWER0_2(executer);
                        if (skill != null) {
                            stackCost(container, -power2_recover, self);
                        } else {
                            event.getPlayerPatch().playAnimationSynchronized(CorruptAnimations.YAMATO_POWER0_2, 0.15F);
                        }
                    }
                }
            }

        });
        ci.cancel();
    }

    private void stackCost(SkillContainer container, int cost, YamatoSkill yamatoSkill) {
        if (container.getExecutor().getSkill(SkillSlots.WEAPON_INNATE).hasSkill(CDSkills.YAMATOSKILL)) {
            yamatoSkill.setStackSynchronize(container, container.getExecutor().getSkill(CDSkills.YAMATOSKILL).getStack() - cost);
        }
    }

    private void POWER0_2(ServerPlayerPatch executer) {
        executer.playAnimationSynchronized(CorruptAnimations.YAMATO_POWER0_2, 0.05F);
    }
}