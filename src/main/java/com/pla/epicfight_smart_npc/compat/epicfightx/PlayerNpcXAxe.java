package com.pla.epicfight_smart_npc.compat.epicfightx;

import com.asanginxst.epicfightx.gameassets.animations.AnimationsX;
import com.asanginxst.epicfightx.gameassets.animations.ExtraAnimations;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcCombatBehaviorBuilder;
import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.axeHeavyAnimations;

public class PlayerNpcXAxe {
    public static final Builder<MobPatch<?>> X_AXE = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    AnimationsX.AXE_AUTO1,
                    AnimationsX.AXE_AUTO2,
                    ExtraAnimations.AXE_AUTO3,
                    ExtraAnimations.AXE_AUTO4,
                    ExtraAnimations.AXE_AUTO5
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? axeHeavyAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    AnimationsX.AXE_DASH,
                    AnimationsX.AXE_AIRSLASH,
                    AnimationsX.THE_GUILLOTINE
            )
    );
}
