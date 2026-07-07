package com.pla.epicfight_smart_npc.combatbehaviour;

import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.spearSpecialAnimations;

public class PlayerNpcSpear {
    public static final Builder<MobPatch<?>> SPEAR_SHIELD = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    Animations.SPEAR_ONEHAND_AUTO
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? spearSpecialAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    Animations.SPEAR_DASH,
                    Animations.SPEAR_ONEHAND_AIR_SLASH,
                    Animations.HEARTPIERCER
            )
    );

    public static final Builder<MobPatch<?>> SPEAR = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    Animations.SPEAR_TWOHAND_AUTO1,
                    Animations.SPEAR_TWOHAND_AUTO2
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? spearSpecialAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    Animations.SPEAR_DASH,
                    Animations.SPEAR_TWOHAND_AIR_SLASH,
                    Animations.GRASPING_SPIRAL_FIRST,
                    Animations.GRASPING_SPIRAL_SECOND
            )
    );
}
