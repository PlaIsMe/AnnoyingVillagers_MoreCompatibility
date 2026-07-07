package com.pla.epicfight_smart_npc.combatbehaviour;

import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.*;

public class PlayerNpcAxe {
    public static final Builder<MobPatch<?>> AXE = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    Animations.AXE_AUTO1,
                    Animations.AXE_AUTO2
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? axeHeavyAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    Animations.AXE_DASH,
                    Animations.AXE_AIRSLASH,
                    Animations.THE_GUILLOTINE
            )
    );
}
