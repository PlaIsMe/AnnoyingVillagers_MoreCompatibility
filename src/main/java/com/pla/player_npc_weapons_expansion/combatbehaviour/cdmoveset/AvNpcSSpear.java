package com.pla.player_npc_weapons_expansion.combatbehaviour.cdmoveset;

import com.pla.annoyingvillagers.combatbehaviour.AvNpcCombatBehaviorBuilder;
import com.pla.annoyingvillagers.combatbehaviour.CombatCommon;
import com.pla.annoyingvillagers.gameasset.AnimsPugilistSteve;
import net.corruptdog.cdm.gameasset.CorruptAnimations;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

public class AvNpcSSpear {
    public static final Builder<MobPatch<?>> S_SPEAR_SHIELD = AvNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    CorruptAnimations.SSPEAR_ONEHAND_AUTO
            ),
            CombatCommon.animations(
                    CorruptAnimations.SSPEAR_DASH,
                    Animations.SPEAR_ONEHAND_AIR_SLASH
            ),
            CombatCommon.animations(
                    AnimsPugilistSteve.SPEAR_THRUST,
                    CorruptAnimations.SPEAR_SLASH
            )
    );

    public static final Builder<MobPatch<?>> S_SPEAR = AvNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    CorruptAnimations.SSPEAR_TWOHAND_AUTO1,
                    CorruptAnimations.SSPEAR_TWOHAND_AUTO2
            ),
            CombatCommon.animations(
                    CorruptAnimations.SSPEAR_DASH,
                    Animations.SPEAR_TWOHAND_AIR_SLASH
            ),
            CombatCommon.animations(
                    AnimsPugilistSteve.SPEAR_THRUST,
                    CorruptAnimations.SPEAR_SLASH
            )
    );
}
