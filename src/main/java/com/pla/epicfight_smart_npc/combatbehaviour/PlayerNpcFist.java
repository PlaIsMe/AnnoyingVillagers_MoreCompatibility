package com.pla.epicfight_smart_npc.combatbehaviour;

import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.fistSpecialAnimations;

public class PlayerNpcFist {
    public static final Builder<MobPatch<?>> FIST = PlayerNpcCombatBehaviorBuilder.fist(
            CombatCommon.animations(
                    Animations.FIST_AUTO1,
                    Animations.FIST_AUTO2,
                    Animations.FIST_AUTO3
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? fistSpecialAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    Animations.RELENTLESS_COMBO,
                    Animations.FIST_AIR_SLASH
            )
    );
}
