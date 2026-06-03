package com.pla.player_npc_weapons_expansion.combatbehaviour.cdmoveset;

import com.pla.annoyingvillagers.combatbehaviour.AvNpcCombatBehaviorBuilder;
import com.pla.annoyingvillagers.combatbehaviour.CombatCommon;
import com.pla.annoyingvillagers.gameasset.AnimsPugilistSteve;
import net.corruptdog.cdm.gameasset.CorruptAnimations;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

public class AvNpcKatana {
    public static final Builder<MobPatch<?>> KATANA = AvNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    CorruptAnimations.KATANA_AUTO1,
                    CorruptAnimations.KATANA_AUTO2,
                    CorruptAnimations.KATANA_AUTO3
            ),
            CombatCommon.animations(
                    AnimsPugilistSteve.SWORD_HEAVY_AUTO_1,
                    AnimsPugilistSteve.SWORD_HEAVY_AUTO_2,
                    AnimsPugilistSteve.SWORD_HEAVY_AUTO_3,
                    CorruptAnimations.YAMATO_DASH,
                    CorruptAnimations.KATANA_SHEATHING_DASH,
                    CorruptAnimations.KATANA_SHEATH_AIR_SLASH
            ),
            CombatCommon.animations(
                    CorruptAnimations.BLADE_RUSH_FINISHER,
                    CorruptAnimations.FATAL_DRAW_DASH
            )
    );
}
