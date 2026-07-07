package com.pla.epicfight_smart_npc.combatbehaviour;

import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.dualSwordSpecialAnimations;
import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.swordHeavyAnimations;

public class PlayerNpcDagger {
    public static final Builder<MobPatch<?>> DAGGER = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    Animations.DAGGER_AUTO1,
                    Animations.DAGGER_AUTO2,
                    Animations.DAGGER_AUTO3
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? swordHeavyAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    Animations.DAGGER_DASH,
                    Animations.DAGGER_AIR_SLASH,
                    Animations.EVISCERATE_FIRST,
                    Animations.EVISCERATE_SECOND
            )
    );

    public static final Builder<MobPatch<?>> DUAL_DAGGER = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    Animations.DAGGER_DUAL_AUTO1,
                    Animations.DAGGER_DUAL_AUTO2,
                    Animations.DAGGER_DUAL_AUTO3
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? dualSwordSpecialAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    Animations.DAGGER_DUAL_DASH,
                    Animations.DAGGER_AIR_SLASH,
                    Animations.LONGSWORD_AUTO2,
                    Animations.BLADE_RUSH_COMBO1,
                    Animations.BLADE_RUSH_COMBO2,
                    Animations.BLADE_RUSH_COMBO3,
                    Animations.DAGGER_DUAL_AUTO4
            )
    );
}
