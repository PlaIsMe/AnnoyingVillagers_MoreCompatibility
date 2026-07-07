package com.pla.epicfight_smart_npc.combatbehaviour;

import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.dualSwordSpecialAnimations;
import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.swordHeavyAnimations;

public class PlayerNpcSword {
    public static final Builder<MobPatch<?>> SWORD = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    Animations.SWORD_AUTO1,
                    Animations.SWORD_AUTO2,
                    Animations.SWORD_AUTO3
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? swordHeavyAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    Animations.SWORD_DASH,
                    Animations.SWORD_AIR_SLASH,
                    Animations.SWEEPING_EDGE
            )
    );

    public static final Builder<MobPatch<?>> DUAL_SWORD = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    Animations.SWORD_DUAL_AUTO1,
                    Animations.SWORD_DUAL_AUTO2,
                    Animations.SWORD_DUAL_AUTO3
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? dualSwordSpecialAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    Animations.SWORD_DUAL_DASH,
                    Animations.SWORD_DUAL_AIR_SLASH,
                    Animations.DANCING_EDGE
            )
    );
}
