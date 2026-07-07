package com.pla.epicfight_smart_npc.combatbehaviour;

import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.greatswordSpecialAnimations;

public class PlayerNpcGreatsword {
    public static final Builder<MobPatch<?>> GREATSWORD = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    Animations.GREATSWORD_AUTO1,
                    Animations.GREATSWORD_AUTO2
            ),
            CombatCommon.animations(
                    Animations.GREATSWORD_DASH,
                    Animations.GREATSWORD_AIR_SLASH
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? greatswordSpecialAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    Animations.STEEL_WHIRLWIND_CHARGING,
                    Animations.STEEL_WHIRLWIND
            )
    );
}
