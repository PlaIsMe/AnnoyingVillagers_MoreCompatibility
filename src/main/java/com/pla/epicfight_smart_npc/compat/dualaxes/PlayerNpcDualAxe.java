package com.pla.epicfight_smart_npc.compat.dualaxes;

import M6FGR.dualaxes.gameassets.DualAxesAnimations;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcCombatBehaviorBuilder;
import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.axeHeavyAnimations;

public class PlayerNpcDualAxe {
    public static final CECombatBehaviors.Builder<MobPatch<?>> AXE = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    DualAxesAnimations.AXE_AUTO_1,
                    DualAxesAnimations.AXE_AUTO_2,
                    DualAxesAnimations.AXE_AUTO_3
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? axeHeavyAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    Animations.BIPED_MOB_TACHI,
                    Animations.AXE_AIRSLASH,
                    Animations.THE_GUILLOTINE
            )
    );

    public static final CECombatBehaviors.Builder<MobPatch<?>> DUAL_AXE = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    DualAxesAnimations.AXE_DUAL_AUTO_1,
                    DualAxesAnimations.AXE_DUAL_AUTO_2,
                    DualAxesAnimations.AXE_DUAL_AUTO_3
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? axeHeavyAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    DualAxesAnimations.AXE_DUAL_DASH,
                    DualAxesAnimations.AXE_DUAL_AIRSLASH,
                    DualAxesAnimations.AXE_SPINNING_DEATH
            )
    );
}
