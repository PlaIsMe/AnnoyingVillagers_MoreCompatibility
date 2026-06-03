package com.pla.player_npc_weapons_expansion.combatbehaviour.cdmoveset;

import com.pla.annoyingvillagers.combatbehaviour.AvNpcCombatBehaviorBuilder;
import com.pla.annoyingvillagers.combatbehaviour.CombatCommon;
import com.pla.annoyingvillagers.gameasset.AnimsPugilistSteve;
import net.corruptdog.cdm.gameasset.CorruptAnimations;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

public class AvNpcSGreatsword {
    public static final Builder<MobPatch<?>> S_GREATSWORD = AvNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    CorruptAnimations.GREATSWORD_OLD_AUTO1,
                    CorruptAnimations.GREATSWORD_OLD_AUTO2,
                    CorruptAnimations.GREATSWORD_OLD_AUTO3
            ),
            CombatCommon.animations(
                    CorruptAnimations.GREATSWORD_OLD_DASH,
                    CorruptAnimations.GREATSWORD_OLD_AIRSLASH
            ),
            CombatCommon.animations(
                    AnimsPugilistSteve.GIANT_WHIRLWIND,
                    CorruptAnimations.WIND_SLASH
            )
    );

    public static final Builder<MobPatch<?>> DUAL_S_GREATSWORD = AvNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    CorruptAnimations.DUAL_GREATSWORD_AUTO1,
                    CorruptAnimations.DUAL_GREATSWORD_AUTO2,
                    CorruptAnimations.DUAL_GREATSWORD_AUTO3,
                    CorruptAnimations.DUAL_GREATSWORD_AUTO4
            ),
            CombatCommon.animations(
                    CorruptAnimations.GREATSWORD_OLD_DASH,
                    CorruptAnimations.GREATSWORD_OLD_AIRSLASH
            ),
            CombatCommon.animations(
                    AnimsPugilistSteve.GIANT_WHIRLWIND,
                    CorruptAnimations.WIND_SLASH
            )
    );
}
