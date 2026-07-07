package com.pla.epicfight_smart_npc.compat.cdmoveset;

import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcCombatBehaviorBuilder;
import net.corruptdog.cdm.gameasset.CorruptAnimations;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.greatswordSpecialAnimations;

public class PlayerNpcSGreatsword {
    public static final Builder<MobPatch<?>> S_GREATSWORD = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    CorruptAnimations.GREATSWORD_OLD_AUTO1,
                    CorruptAnimations.GREATSWORD_OLD_AUTO2,
                    CorruptAnimations.GREATSWORD_OLD_AUTO3
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? greatswordSpecialAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    CorruptAnimations.GREATSWORD_OLD_DASH,
                    CorruptAnimations.GREATSWORD_OLD_AIRSLASH
            )
    );

    public static final Builder<MobPatch<?>> DUAL_S_GREATSWORD = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    CorruptAnimations.DUAL_GREATSWORD_AUTO1,
                    CorruptAnimations.DUAL_GREATSWORD_AUTO2,
                    CorruptAnimations.DUAL_GREATSWORD_AUTO3,
                    CorruptAnimations.DUAL_GREATSWORD_AUTO4
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? greatswordSpecialAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    CorruptAnimations.GREATSWORD_OLD_DASH,
                    CorruptAnimations.GREATSWORD_OLD_AIRSLASH
            )
    );
}
