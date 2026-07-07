package com.pla.epicfight_smart_npc.compat.epicfightx;

import com.asanginxst.epicfightx.gameassets.animations.AnimationsX;
import com.asanginxst.epicfightx.gameassets.animations.ExtraAnimations;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcCombatBehaviorBuilder;
import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.greatswordSpecialAnimations;

public class PlayerNpcXGreatsword {
    public static final Builder<MobPatch<?>> X_GREATSWORD = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    AnimationsX.GREATSWORD_AUTO1,
                    AnimationsX.GREATSWORD_AUTO2,
                    ExtraAnimations.GREATSWORD_AUTO3,
                    ExtraAnimations.GREATSWORD_AUTO4,
                    ExtraAnimations.GREATSWORD_AUTO5
            ),
            CombatCommon.animations(
                    AnimationsX.GREATSWORD_DASH,
                    AnimationsX.GREATSWORD_AIR_SLASH
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? greatswordSpecialAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    AnimationsX.STEEL_WHIRLWIND_CHARGING,
                    AnimationsX.STEEL_WHIRLWIND
            )
    );
}
