package com.pla.epicfight_smart_npc.compat.cdmoveset;

import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcCombatBehaviorBuilder;
import net.corruptdog.cdm.gameasset.CorruptAnimations;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.swordHeavyAnimations;

public class PlayerNpcKatana {
    public static final Builder<MobPatch<?>> KATANA = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    CorruptAnimations.KATANA_AUTO1,
                    CorruptAnimations.KATANA_AUTO2,
                    CorruptAnimations.KATANA_AUTO3
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? swordHeavyAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
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
