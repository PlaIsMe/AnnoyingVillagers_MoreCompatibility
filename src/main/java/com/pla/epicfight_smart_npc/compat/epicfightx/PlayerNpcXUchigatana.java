package com.pla.epicfight_smart_npc.compat.epicfightx;

import com.asanginxst.epicfightx.gameassets.animations.AnimationsX;
import com.asanginxst.epicfightx.gameassets.animations.ExtraAnimations;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcCombatBehaviorBuilder;
import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.swordHeavyAnimations;

public class PlayerNpcXUchigatana {
    public static final CECombatBehaviors.Builder<MobPatch<?>> X_UCHIGATANA = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    AnimationsX.UCHIGATANA_AUTO1,
                    AnimationsX.UCHIGATANA_AUTO2,
                    AnimationsX.UCHIGATANA_AUTO3,
                    ExtraAnimations.UCHIGATANA_AUTO4,
                    ExtraAnimations.UCHIGATANA_AUTO5
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? swordHeavyAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    AnimationsX.UCHIGATANA_DASH,
                    AnimationsX.UCHIGATANA_SHEATHING_AUTO,
                    AnimationsX.BATTOJUTSU,
                    AnimationsX.BATTOJUTSU_DASH,
                    AnimationsX.UCHIGATANA_AIR_SLASH,
                    AnimationsX.UCHIGATANA_SHEATHING_DASH
            )
    );
}
