package com.pla.epicfight_smart_npc.combatbehaviour;

import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.swordHeavyAnimations;

public class PlayerNpcTachi {
    public static final Builder<MobPatch<?>> TACHI = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    Animations.TACHI_AUTO1,
                    Animations.TACHI_AUTO2,
                    Animations.TACHI_AUTO3
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? swordHeavyAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    Animations.TACHI_DASH,
                    Animations.RUSHING_TEMPO1,
                    Animations.RUSHING_TEMPO2,
                    Animations.LONGSWORD_AIR_SLASH,
                    Animations.RUSHING_TEMPO3
            )
    );
}
