package com.pla.epicfight_smart_npc.combatbehaviour;

import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.swordHeavyAnimations;

public class PlayerNpcUchigatana {
    public static final Builder<MobPatch<?>> UCHIGATANA = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    Animations.UCHIGATANA_AUTO1,
                    Animations.UCHIGATANA_AUTO2,
                    Animations.UCHIGATANA_AUTO3
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? swordHeavyAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    Animations.UCHIGATANA_DASH,
                    Animations.UCHIGATANA_SHEATHING_AUTO,
                    Animations.BATTOJUTSU,
                    Animations.BATTOJUTSU_DASH,
                    Animations.UCHIGATANA_AIR_SLASH,
                    Animations.UCHIGATANA_SHEATHING_DASH
            )
    );
}
