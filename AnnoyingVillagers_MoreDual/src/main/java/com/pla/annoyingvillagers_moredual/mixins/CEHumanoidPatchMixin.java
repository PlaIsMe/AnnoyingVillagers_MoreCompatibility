package com.pla.annoyingvillagers_moredual.mixins;
import M6FGR.dualaxes.gameassets.DualAxesAnimations;
import M6FGR.dualgreatswords.gameassets.DualGreatSwordsAnimations;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import com.pla.annoyingvillagers.combatbehaviour.PlayerNpcGreatsword;
import com.pla.annoyingvillagers.mobpatch.LowHerobrineClonePatch;
import com.pla.annoyingvillagers.mobpatch.PlayerNpcPatch;
import com.pla.annoyingvillagers_moredual.combatbehaviour.PlayerNpcDualAxe;
import com.pla.annoyingvillagers_moredual.combatbehaviour.PlayerNpcDualGreatsword;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors;
import net.shelmarow.combat_evolution.ai.CEHumanoidPatch;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.Factions;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mixin(value = {CEHumanoidPatch.class}, remap = false)
public abstract class CEHumanoidPatchMixin {
    @Shadow protected Map<WeaponCategory, Map<Style, Set<Pair<LivingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation>>>>>
            weaponLivingMotions;

    @Shadow protected Map<WeaponCategory, Map<Style, List<AnimationManager.AnimationAccessor<? extends StaticAnimation>>>>
            guardHitMotions;

    @Shadow protected Map<WeaponCategory, Map<Style, CECombatBehaviors.Builder<MobPatch<?>>>>
            weaponAttackMotions;


    @Inject(method = "<init>", at = @At("RETURN"))
    private void addMoreWeaponMotions(Factions factions, CallbackInfo ci) {
        CEHumanoidPatch self = (CEHumanoidPatch) (Object) this;

        if (self instanceof PlayerNpcPatch || self instanceof LowHerobrineClonePatch) {
            weaponLivingMotions
                    .put(CapabilityItem.WeaponCategories.GREATSWORD,
                            ImmutableMap.of(CapabilityItem.Styles.OCHS,
                                    Set.of(
                                            Pair.of(LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD),
                                            Pair.of(LivingMotions.IDLE, DualGreatSwordsAnimations.GREATSWORD_DUAL_IDLE),
                                            Pair.of(LivingMotions.WALK, DualGreatSwordsAnimations.GREATSWORD_DUAL_WALK),
                                            Pair.of(LivingMotions.RUN, DualGreatSwordsAnimations.GREATSWORD_DUAL_RUN),
                                            Pair.of(LivingMotions.CHASE, DualGreatSwordsAnimations.GREATSWORD_DUAL_RUN),
                                            Pair.of(LivingMotions.DEATH, Animations.BIPED_DEATH)
                                    )));
            weaponAttackMotions
                    .put(CapabilityItem.WeaponCategories.GREATSWORD,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcGreatsword.GREATSWORD,
                                    CapabilityItem.Styles.OCHS, PlayerNpcDualGreatsword.DUAL_GREATSWORD));
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

            weaponLivingMotions
                    .put(CapabilityItem.WeaponCategories.AXE,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.TWO_HAND,
                                    Set.of(
                                            Pair.of(LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD),
                                            Pair.of(LivingMotions.IDLE, DualAxesAnimations.AXE_DUAL_IDLE),
                                            Pair.of(LivingMotions.WALK, Animations.BIPED_WALK),
                                            Pair.of(LivingMotions.RUN, Animations.BIPED_RUN_DUAL),
                                            Pair.of(LivingMotions.CHASE, Animations.BIPED_RUN_DUAL),
                                            Pair.of(LivingMotions.DEATH, Animations.BIPED_DEATH)
                                    )
                                    ));
            weaponAttackMotions
                    .put(CapabilityItem.WeaponCategories.AXE,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcDualAxe.DUAL_AXE,
                                    CapabilityItem.Styles.ONE_HAND, PlayerNpcDualAxe.AXE)
                    );
            guardHitMotions.put(CapabilityItem.WeaponCategories.AXE,
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
        }
    }
}
