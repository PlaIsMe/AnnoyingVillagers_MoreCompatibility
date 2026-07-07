package com.pla.epicfight_smart_npc.compat.epicfightx;

import com.asanginxst.epicfightx.gameassets.animations.AnimationsX;
import com.asanginxst.epicfightx.gameassets.animations.ExtraAnimations;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcCombatBehaviorBuilder;
import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.fistSpecialAnimations;

public class PlayerNpcXFist {
    public static final Builder<MobPatch<?>> X_FIST = PlayerNpcCombatBehaviorBuilder.fist(
            CombatCommon.animations(
                    AnimationsX.FIST_AUTO1,
                    AnimationsX.FIST_AUTO2,
                    AnimationsX.FIST_AUTO3,
                    ExtraAnimations.FIST_AUTO4,
                    ExtraAnimations.FIST_AUTO5
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? fistSpecialAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    AnimationsX.FIST_DASH,
                    AnimationsX.FIST_AIR_SLASH,
                    AnimationsX.RELENTLESS_COMBO
            )
    );
}
