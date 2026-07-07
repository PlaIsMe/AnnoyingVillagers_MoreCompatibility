package com.pla.epicfight_smart_npc.mixins.refm;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import com.pla.epicfight_smart_npc.mobpatch.PlayerNpcPatch;
import com.pla.epicfight_smart_npc.compat.refm.PlayerNpcRapier;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors;
import net.shelmarow.combat_evolution.ai.CEHumanoidPatch;
import net.yonchi.refm.gameasset.RapierAnimations;
import net.yonchi.refm.world.capabilities.item.RapierWeaponCategories;
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
    private void addRefmWeaponMotions(Factions factions, CallbackInfo ci) {
        CEHumanoidPatch self = (CEHumanoidPatch) (Object) this;

        if (self instanceof PlayerNpcPatch) {
            putRapierMotions(RapierWeaponCategories.RAPIER, PlayerNpcRapier.RAPIER, RapierAnimations.BIPED_RUN_RAPIER);
            putRapierMotions(RapierWeaponCategories.ENDER_RAPIER, PlayerNpcRapier.ENDER_RAPIER, RapierAnimations.BIPED_RUN_RAPIER);
            putRapierMotions(RapierWeaponCategories.OCEAN_RAPIER, PlayerNpcRapier.OCEAN_RAPIER, RapierAnimations.BIPED_RUN_RAPIER);
            putRapierMotions(RapierWeaponCategories.WITHER_RAPIER, PlayerNpcRapier.WITHER_RAPIER, RapierAnimations.BIPED_RUN_RAPIER_WITHER);
            putRapierMotions(RapierWeaponCategories.AMETHYST_RAPIER, PlayerNpcRapier.AMETHYST_RAPIER, RapierAnimations.BIPED_RUN_RAPIER);
        }
    }

    private void putRapierMotions(
            WeaponCategory category,
            CECombatBehaviors.Builder<MobPatch<?>> attackMotions,
            AnimationManager.AnimationAccessor<? extends StaticAnimation> runMotion
    ) {
        weaponLivingMotions.put(
                category,
                ImmutableMap.of(CapabilityItem.Styles.OCHS, rapierLivingMotions(runMotion))
        );
        weaponAttackMotions.put(
                category,
                ImmutableMap.of(CapabilityItem.Styles.OCHS, attackMotions)
        );
        guardHitMotions.put(
                category,
                ImmutableMap.of(CapabilityItem.Styles.OCHS, List.of(
                        RapierAnimations.RAPIER_GUARD_HIT,
                        RapierAnimations.RAPIER_GUARD_DEFLECT1,
                        RapierAnimations.RAPIER_GUARD_DEFLECT2
                ))
        );
    }

    private static Set<Pair<LivingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation>>> rapierLivingMotions(
            AnimationManager.AnimationAccessor<? extends StaticAnimation> runMotion
    ) {
        return Set.of(
                living(LivingMotions.BLOCK, RapierAnimations.RAPIER_GUARD),
                living(LivingMotions.IDLE, RapierAnimations.BIPED_HOLD_RAPIER),
                living(LivingMotions.WALK, RapierAnimations.BIPED_WALK_RAPIER),
                living(LivingMotions.RUN, runMotion),
                living(LivingMotions.CHASE, runMotion),
                living(LivingMotions.JUMP, RapierAnimations.BIPED_HOLD_RAPIER),
                living(LivingMotions.KNEEL, RapierAnimations.BIPED_SNEAK_RAPIER),
                living(LivingMotions.SNEAK, RapierAnimations.BIPED_SNEAK_RAPIER),
                living(LivingMotions.SWIM, RapierAnimations.BIPED_HOLD_RAPIER),
                living(LivingMotions.DEATH, Animations.BIPED_DEATH)
        );
    }

    private static Pair<LivingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation>> living(
            LivingMotion motion,
            AnimationManager.AnimationAccessor<? extends StaticAnimation> animation
    ) {
        return Pair.of(motion, animation);
    }
}
