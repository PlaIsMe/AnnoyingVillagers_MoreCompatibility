package com.pla.epicfight_smart_npc.mobpatch;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;

import java.util.List;
import java.util.Set;

import com.pla.epicfight_smart_npc.combatbehaviour.*;
import com.pla.epicfight_smart_npc.compat.EpicFightBow;
import com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers;
import com.pla.epicfight_smart_npc.compat.dualaxes.PlayerNpcDualAxe;
import com.pla.epicfight_smart_npc.compat.dualgreatsword.PlayerNpcDualGreatsword;
import com.pla.epicfight_smart_npc.compat.epicfightx.*;
import com.pla.epicfight_smart_npc.gameasset.SmartNpcAnimations;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors;
import net.shelmarow.combat_evolution.ai.CEHumanoidPatch;
import net.shelmarow.combat_evolution.ai.iml.CustomExecuteEntity;
import net.shelmarow.combat_evolution.execution.ExecutionTypeManager;
import yesman.epicfight.api.animation.AnimationManager.AnimationAccessor;
import yesman.epicfight.api.animation.Animator;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.utils.AttackResult;
import yesman.epicfight.api.utils.AttackResult.ResultType;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.particle.HitParticleType;
import yesman.epicfight.world.capabilities.entitypatch.Factions;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;
import yesman.epicfight.world.capabilities.item.CapabilityItem.WeaponCategories;
import yesman.epicfight.world.damagesource.EpicFightDamageSource;
import yesman.epicfight.world.damagesource.StunType;

public class PlayerNpcPatch extends CEHumanoidPatch<PathfinderMob> implements CustomExecuteEntity {
    public PlayerNpcPatch() {
        super(Factions.NEUTRAL);
    }

    public void initAnimator(Animator animator) {
        super.initAnimator(animator);
        animator.addLivingAnimation(LivingMotions.BLOCK, Animations.BIPED_BLOCK);
        animator.addLivingAnimation(LivingMotions.IDLE, Animations.BIPED_IDLE);
        animator.addLivingAnimation(LivingMotions.WALK, Animations.BIPED_WALK);
        animator.addLivingAnimation(LivingMotions.RUN, Animations.BIPED_RUN);
        animator.addLivingAnimation(LivingMotions.CHASE, Animations.BIPED_RUN);
        AnimationAccessor<? extends StaticAnimation> sneakAnimation = sneakAnimation();
        if (sneakAnimation != null) {
            animator.addLivingAnimation(LivingMotions.SNEAK, sneakAnimation);
        }
        if (SmartNpcAnimations.MINING_SWING != null) {
            animator.addLivingAnimation(LivingMotions.DIGGING, SmartNpcAnimations.MINING_SWING);
        } else if (Animations.BIPED_DIG != null) {
            animator.addLivingAnimation(LivingMotions.DIGGING, Animations.BIPED_DIG);
        }
        animator.addLivingAnimation(LivingMotions.DEATH, Animations.BIPED_DEATH);
    }

    @Override
    public void updateMotion(boolean considerInaction) {
        super.updateMotion(considerInaction);

        PathfinderMob original = this.getOriginal();
        if ((original.isShiftKeyDown() || original.isCrouching())
                && (this.currentLivingMotion == LivingMotions.IDLE
                || this.currentLivingMotion == LivingMotions.WALK
                || this.currentLivingMotion == LivingMotions.RUN
                || this.currentLivingMotion == LivingMotions.CHASE)) {
            this.currentLivingMotion = LivingMotions.SNEAK;
            this.currentCompositeMotion = LivingMotions.SNEAK;
        }
    }

    private static AnimationAccessor<? extends StaticAnimation> sneakAnimation() {
        return SmartNpcAnimations.SNEAK != null ? SmartNpcAnimations.SNEAK : Animations.BIPED_SNEAK;
    }

    protected void setWeaponMotions() {
        this.weaponLivingMotions
                .put(WeaponCategories.NOT_WEAPON,
                        ImmutableMap.of(Styles.COMMON,
                                Set.of(
                                        Pair.of(LivingMotions.IDLE, Animations.BIPED_IDLE),
                                        Pair.of(LivingMotions.SNEAK, sneakAnimation()),
                                        Pair.of(LivingMotions.WALK, Animations.BIPED_WALK),
                                        Pair.of(LivingMotions.RUN, Animations.BIPED_RUN),
                                        Pair.of(LivingMotions.CHASE, Animations.BIPED_RUN),
                                        Pair.of(LivingMotions.DEATH, Animations.BIPED_DEATH)
                                )));
        this.weaponAttackMotions
                .put(WeaponCategories.NOT_WEAPON,
                        ImmutableMap.of(Styles.COMMON, PlayerNpcFist.FIST));

        this.weaponLivingMotions
                .put(WeaponCategories.FIST,
                        ImmutableMap.of(Styles.COMMON,
                                Set.of(
                                        Pair.of(LivingMotions.IDLE, Animations.BIPED_IDLE),
                                        Pair.of(LivingMotions.SNEAK, sneakAnimation()),
                                        Pair.of(LivingMotions.WALK, Animations.BIPED_WALK),
                                        Pair.of(LivingMotions.RUN, Animations.BIPED_RUN),
                                        Pair.of(LivingMotions.CHASE, Animations.BIPED_RUN),
                                        Pair.of(LivingMotions.DEATH, Animations.BIPED_DEATH)
                                )));
        if (!ModList.get().isLoaded("epicfightx")) {
            this.weaponAttackMotions
                    .put(WeaponCategories.FIST,
                            ImmutableMap.of(Styles.COMMON, PlayerNpcFist.FIST));
            this.weaponAttackMotions
                    .put(WeaponCategories.SWORD,
                            ImmutableMap.of(
                                    Styles.ONE_HAND, PlayerNpcSword.SWORD,
                                    Styles.TWO_HAND, PlayerNpcSword.DUAL_SWORD
                            ));

            this.weaponAttackMotions
                    .put(WeaponCategories.DAGGER,
                            ImmutableMap.of(
                                    Styles.ONE_HAND, PlayerNpcDagger.DAGGER,
                                    Styles.TWO_HAND, PlayerNpcDagger.DUAL_DAGGER
                            ));

            this.weaponAttackMotions
                    .put(WeaponCategories.UCHIGATANA,
                            ImmutableMap.of(
                                    Styles.TWO_HAND, PlayerNpcUchigatana.UCHIGATANA
                            ));
            this.weaponAttackMotions
                    .put(WeaponCategories.SPEAR,
                            ImmutableMap.of(
                                    Styles.ONE_HAND, PlayerNpcSpear.SPEAR_SHIELD,
                                    Styles.TWO_HAND, PlayerNpcSpear.SPEAR
                            ));

            this.weaponAttackMotions
                    .put(WeaponCategories.LONGSWORD,
                            ImmutableMap.of(
                                    Styles.ONE_HAND, PlayerNpcLongsword.LONGSWORD_SHIELD,
                                    Styles.TWO_HAND, PlayerNpcLongsword.LONGSWORD
                            ));

            this.weaponAttackMotions
                    .put(WeaponCategories.TACHI,
                            ImmutableMap.of(
                                    Styles.TWO_HAND, PlayerNpcTachi.TACHI
                            ));
        } else {
            this.weaponAttackMotions
                    .put(CapabilityItem.WeaponCategories.FIST,
                            ImmutableMap.of(CapabilityItem.Styles.COMMON, PlayerNpcXFist.X_FIST));
            this.weaponAttackMotions
                    .put(CapabilityItem.WeaponCategories.SWORD,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.ONE_HAND, PlayerNpcXSword.X_SWORD,
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcXSword.X_DUAL_SWORD
                            ));

            this.weaponAttackMotions
                    .put(CapabilityItem.WeaponCategories.DAGGER,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.ONE_HAND, PlayerNpcXDagger.X_DAGGER,
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcXDagger.X_DUAL_DAGGER
                            ));

            this.weaponAttackMotions
                    .put(CapabilityItem.WeaponCategories.UCHIGATANA,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcXUchigatana.X_UCHIGATANA
                            ));
            this.weaponAttackMotions
                    .put(CapabilityItem.WeaponCategories.SPEAR,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.ONE_HAND, PlayerNpcXSpear.X_SPEAR_SHIELD,
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcXSpear.X_SPEAR
                            ));

            this.weaponAttackMotions
                    .put(CapabilityItem.WeaponCategories.LONGSWORD,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.ONE_HAND, PlayerNpcXLongsword.X_LONGSWORD_SHIELD,
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcXLongsword.X_LONGSWORD
                            ));

            this.weaponAttackMotions
                    .put(CapabilityItem.WeaponCategories.TACHI,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcXTachi.X_TACHI
                            ));
        }

        if (ModList.get().isLoaded("epicfightx")) {
            weaponAttackMotions
                    .put(CapabilityItem.WeaponCategories.AXE,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.ONE_HAND, PlayerNpcXAxe.X_AXE)
                    );
        } else if (ModList.get().isLoaded("dualaxes")) {
            weaponAttackMotions
                    .put(CapabilityItem.WeaponCategories.AXE,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.ONE_HAND, PlayerNpcDualAxe.AXE,
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcDualAxe.DUAL_AXE)
                    );
        } else {
            this.weaponAttackMotions
                    .put(WeaponCategories.AXE,
                            ImmutableMap.of(Styles.ONE_HAND, PlayerNpcAxe.AXE));
        }

        if (ModList.get().isLoaded("epicfightx")) {
            weaponAttackMotions
                    .put(CapabilityItem.WeaponCategories.GREATSWORD,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcXGreatsword.X_GREATSWORD));
        } else if (ModList.get().isLoaded("dualgreatswords")) {
            weaponAttackMotions
                    .put(CapabilityItem.WeaponCategories.GREATSWORD,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcGreatsword.GREATSWORD,
                                    CapabilityItem.Styles.OCHS, PlayerNpcDualGreatsword.DUAL_GREATSWORD));
        } else {
            this.weaponAttackMotions
                    .put(WeaponCategories.GREATSWORD,
                            ImmutableMap.of(Styles.TWO_HAND, PlayerNpcGreatsword.GREATSWORD));
        }
        this.guardHitMotions.put(WeaponCategories.SWORD,
                ImmutableMap.of(
                        Styles.ONE_HAND, List.of(
                                Animations.SWORD_GUARD_ACTIVE_HIT1,
                                Animations.SWORD_GUARD_ACTIVE_HIT2,
                                Animations.SWORD_GUARD_ACTIVE_HIT3
                        ),
                        Styles.TWO_HAND, List.of(
                                Animations.SWORD_DUAL_GUARD_HIT
                        )
                )
        );
        this.guardHitMotions.put(WeaponCategories.LONGSWORD,
                ImmutableMap.of(
                        Styles.ONE_HAND, List.of(
                                Animations.LONGSWORD_GUARD_ACTIVE_HIT1,
                                Animations.LONGSWORD_GUARD_ACTIVE_HIT2
                        ),
                        Styles.TWO_HAND, List.of(
                                Animations.LONGSWORD_GUARD_HIT
                        )
                )
        );
        if (ModList.get().isLoaded("dualaxes")) {
            this.guardHitMotions.put(CapabilityItem.WeaponCategories.AXE,
                    ImmutableMap.of(
                            CapabilityItem.Styles.ONE_HAND, List.of(
                                    Animations.SWORD_GUARD_ACTIVE_HIT1,
                                    Animations.SWORD_GUARD_ACTIVE_HIT2,
                                    Animations.SWORD_GUARD_ACTIVE_HIT3
                            ),
                            CapabilityItem.Styles.TWO_HAND, List.of(
                                    Animations.SWORD_DUAL_GUARD_HIT
                            )
                    )
            );
        } else {
            this.guardHitMotions.put(WeaponCategories.AXE,
                    ImmutableMap.of(
                            Styles.ONE_HAND, List.of(
                                    Animations.SWORD_GUARD_ACTIVE_HIT1,
                                    Animations.SWORD_GUARD_ACTIVE_HIT2,
                                    Animations.SWORD_GUARD_ACTIVE_HIT3
                            )
                    )
            );
        }
        if (ModList.get().isLoaded("dualgreatswords")) {
            guardHitMotions.put(CapabilityItem.WeaponCategories.GREATSWORD,
                    ImmutableMap.of(
                            CapabilityItem.Styles.TWO_HAND, List.of(
                                    Animations.GREATSWORD_GUARD_HIT
                            ),
                            CapabilityItem.Styles.OCHS, List.of(
                                    Animations.SWORD_DUAL_GUARD_HIT
                            )
                    )
            );
        } else {
            this.guardHitMotions.put(WeaponCategories.GREATSWORD,
                    ImmutableMap.of(
                            Styles.TWO_HAND, List.of(
                                    Animations.GREATSWORD_GUARD_HIT
                            )
                    )
            );
        }
        this.guardHitMotions.put(WeaponCategories.SPEAR,
                ImmutableMap.of(
                        Styles.ONE_HAND, List.of(
                                Animations.SPEAR_GUARD_HIT
                        ),
                        Styles.TWO_HAND, List.of(
                                Animations.SPEAR_GUARD_HIT
                        )
                )
        );
        this.guardHitMotions.put(WeaponCategories.TRIDENT,
                ImmutableMap.of(
                        Styles.ONE_HAND, List.of(
                                Animations.SPEAR_GUARD_HIT
                        ),
                        Styles.TWO_HAND, List.of(
                                Animations.SPEAR_GUARD_HIT
                        )
                )
        );
        this.guardHitMotions.put(WeaponCategories.UCHIGATANA,
                ImmutableMap.of(
                        Styles.OCHS, List.of(
                                Animations.SWORD_GUARD_ACTIVE_HIT1,
                                Animations.SWORD_GUARD_ACTIVE_HIT2,
                                Animations.SWORD_GUARD_ACTIVE_HIT3
                        ),
                        Styles.TWO_HAND, List.of(
                                Animations.SWORD_DUAL_GUARD_HIT
                        )
                )
        );
        this.guardHitMotions.put(WeaponCategories.DAGGER,
                ImmutableMap.of(
                        Styles.ONE_HAND, List.of(
                                Animations.SWORD_GUARD_ACTIVE_HIT1,
                                Animations.SWORD_GUARD_ACTIVE_HIT2,
                                Animations.SWORD_GUARD_ACTIVE_HIT3
                        ),
                        Styles.TWO_HAND, List.of(
                                Animations.SWORD_DUAL_GUARD_HIT
                        )
                )
        );
        this.guardHitMotions.put(WeaponCategories.TACHI,
                ImmutableMap.of(
                        Styles.ONE_HAND, List.of(
                                Animations.SWORD_GUARD_ACTIVE_HIT1,
                                Animations.SWORD_GUARD_ACTIVE_HIT2,
                                Animations.SWORD_GUARD_ACTIVE_HIT3
                        ),
                        Styles.TWO_HAND, List.of(
                                Animations.SWORD_DUAL_GUARD_HIT
                        )
                )
        );
    }

    @Override
    protected CECombatBehaviors.Builder<MobPatch<?>> getCustomWeaponMotionBuilder() {
        CapabilityItem mainHandCap = this.getHoldingItemCapability(InteractionHand.MAIN_HAND);
        CapabilityItem offHandCap = this.getHoldingItemCapability(InteractionHand.OFF_HAND);
        CECombatBehaviors.Builder<MobPatch<?>> customOverride = null;
        if (ModList.get().isLoaded("annoyingvillagers")) {
            customOverride = AnnoyingVillagers.overideCustomWeaponMotionBuilderForAvNpc(mainHandCap, offHandCap, mainHandCap.getStyle(this));
            if (customOverride == null) customOverride = AnnoyingVillagers.overideBowMotionBuilderForPlayerNpc(mainHandCap, mainHandCap.getStyle(this));
        }

        if (customOverride == null) customOverride = EpicFightBow.overideBowMotionBuilderForPlayerNpc(mainHandCap, mainHandCap.getStyle(this));
        return customOverride != null ? customOverride : super.getCustomWeaponMotionBuilder();
    }

    public void playGuardBreakSound() {
        this.playSound(EpicFightSounds.NEUTRALIZE_MOBS.get(), 0.0F, 0.0F);
    }

    public AttackResult attack(EpicFightDamageSource epicFightDamageSource, Entity entity, InteractionHand interactionhand) {
        AttackResult attackresult = super.attack(epicFightDamageSource, entity, interactionhand);

        if (attackresult.resultType == ResultType.SUCCESS && entity.isAlive()) {
            // More logic when mob attack success
        }

        return attackresult;
    }

    public void tick(LivingTickEvent livingTickEvent) {
        super.tick(livingTickEvent);
    }

    public void onDeath(LivingDeathEvent livingDeathEvent) {
        super.onDeath(livingDeathEvent);
    }

    @Override
    public void onGuardHit(DamageSource damageSource) {
        super.onGuardHit(damageSource);
        if (this.getOriginal().level() instanceof ServerLevel serverLevel) {
            EpicFightParticles.HIT_BLUNT.get().spawnParticleWithArgument(serverLevel, HitParticleType.FRONT_OF_EYES, HitParticleType.ZERO, this.getOriginal(), damageSource.getEntity());
        }
    }

    public AnimationAccessor<? extends StaticAnimation> getHitAnimation(StunType stuntype) {
        return switch (stuntype) {
            case LONG -> Animations.BIPED_HIT_LONG;
            case SHORT, HOLD -> Animations.BIPED_HIT_SHORT;
            case KNOCKDOWN -> Animations.BIPED_KNOCKDOWN;
            case NEUTRALIZE -> (
                    this.getHoldingItemCapability(InteractionHand.MAIN_HAND).getWeaponCategory() == WeaponCategories.GREATSWORD ?
                            Animations.GREATSWORD_GUARD_BREAK : Animations.BIPED_COMMON_NEUTRALIZED);
            case FALL -> Animations.BIPED_LANDING;
            default -> null;
        };
    }

    @Override
    public boolean canBeExecuted(LivingEntityPatch<?> livingEntityPatch) {
        return true;
    }

    @Override
    public boolean canUseCustomType(LivingEntityPatch<?> livingEntityPatch, ExecutionTypeManager.Type type) {
        return true;
    }

    @Override
    public ExecutionTypeManager.Type getExecutionType(LivingEntityPatch<?> livingEntityPatch, ExecutionTypeManager.Type type) {
        return type;
    }
}
