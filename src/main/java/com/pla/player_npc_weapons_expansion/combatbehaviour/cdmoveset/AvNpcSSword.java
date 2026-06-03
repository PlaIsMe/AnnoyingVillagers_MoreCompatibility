package com.pla.player_npc_weapons_expansion.combatbehaviour.cdmoveset;

import com.pla.annoyingvillagers.combatbehaviour.AvNpcCombatBehaviorBuilder;
import com.pla.annoyingvillagers.combatbehaviour.CombatCommon;
import com.pla.annoyingvillagers.gameasset.AnimsPugilistSteve;
import net.corruptdog.cdm.gameasset.CorruptAnimations;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

public class AvNpcSSword {
    public static final Builder<MobPatch<?>> S_SWORD = AvNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    CorruptAnimations.SWORD_ONEHAND_AUTO1,
                    CorruptAnimations.SWORD_ONEHAND_AUTO2,
                    CorruptAnimations.SWORD_ONEHAND_AUTO3,
                    CorruptAnimations.SWORD_ONEHAND_AUTO4
            ),
            CombatCommon.animations(
                    AnimsPugilistSteve.SWORD_HEAVY_AUTO_1,
                    AnimsPugilistSteve.SWORD_HEAVY_AUTO_2,
                    AnimsPugilistSteve.SWORD_HEAVY_AUTO_3
            ),
            CombatCommon.animations(
                    CorruptAnimations.SWORD_ONEHAND_DASH,
                    Animations.SWORD_AIR_SLASH,
                    CorruptAnimations.SWORD_SLASH
            )
    );

    public static final Builder<MobPatch<?>> S_DUALSWORD = AvNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    Animations.SWORD_DUAL_AUTO1,
                    Animations.SWORD_DUAL_AUTO2,
                    Animations.DAGGER_DUAL_AUTO3,
                    Animations.SWORD_DUAL_AUTO3,
                    Animations.DAGGER_DUAL_AUTO4
            ),
            CombatCommon.animations(
                    Animations.DAGGER_DUAL_DASH,
                    Animations.LONGSWORD_AUTO2,
                    AnimsPugilistSteve.DUAL_DANCING_EDGE,
                    AnimsPugilistSteve.DUAL_SWORD_DANCING_EDGE
            ),
            CombatCommon.animations(
                    Animations.SWORD_DUAL_DASH,
                    Animations.SWORD_DUAL_AIR_SLASH,
                    CorruptAnimations.DUAL_SLASH
            )
    );
}
