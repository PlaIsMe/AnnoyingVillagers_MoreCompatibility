package com.pla.epicfight_smart_npc.compat.dualgreatsword;

import M6FGR.dualgreatswords.gameassets.DualGreatSwordsAnimations;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcCombatBehaviorBuilder;
import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.greatswordSpecialAnimations;

public class PlayerNpcDualGreatsword {
    public static final CECombatBehaviors.Builder<MobPatch<?>> DUAL_GREATSWORD = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    DualGreatSwordsAnimations.GREATSWORD_DUAL_AUTO_1,
                    DualGreatSwordsAnimations.GREATSWORD_DUAL_AUTO_2,
                    DualGreatSwordsAnimations.GREATSWORD_DUAL_AUTO_3,
                    DualGreatSwordsAnimations.GREATSWORD_DUAL_AUTO_4
            ),
            CombatCommon.animations(
                    DualGreatSwordsAnimations.GREATSWORD_DUAL_DASH,
                    DualGreatSwordsAnimations.GREATSWORD_DUAL_AIRSLASH
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? greatswordSpecialAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    DualGreatSwordsAnimations.GREATSWORD_EARTH_QUAKE
            )
    );
}
