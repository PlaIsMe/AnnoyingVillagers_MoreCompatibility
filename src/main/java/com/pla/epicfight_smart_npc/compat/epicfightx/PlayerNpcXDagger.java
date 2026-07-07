package com.pla.epicfight_smart_npc.compat.epicfightx;

import com.asanginxst.epicfightx.gameassets.animations.AnimationsX;
import com.asanginxst.epicfightx.gameassets.animations.ExtraAnimations;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcCombatBehaviorBuilder;
import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.dualSwordSpecialAnimations;
import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.swordHeavyAnimations;

public class PlayerNpcXDagger {
    public static final Builder<MobPatch<?>> X_DAGGER = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    AnimationsX.DAGGER_AUTO1,
                    AnimationsX.DAGGER_AUTO2,
                    AnimationsX.DAGGER_AUTO3,
                    ExtraAnimations.DAGGER_AUTO4,
                    ExtraAnimations.DAGGER_AUTO5
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? swordHeavyAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    AnimationsX.DAGGER_DASH,
                    AnimationsX.DAGGER_AIR_SLASH,
                    AnimationsX.EVISCERATE_FIRST,
                    AnimationsX.EVISCERATE_SECOND
            )
    );

    public static final Builder<MobPatch<?>> X_DUAL_DAGGER = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    AnimationsX.DAGGER_DUAL_AUTO1,
                    AnimationsX.DAGGER_DUAL_AUTO2,
                    AnimationsX.DAGGER_DUAL_AUTO3,
                    AnimationsX.DAGGER_DUAL_AUTO4,
                    ExtraAnimations.DAGGER_DUAL_AUTO5
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? dualSwordSpecialAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    AnimationsX.DAGGER_DUAL_DASH,
                    AnimationsX.DAGGER_DUAL_AIR_SLASH,
                    AnimationsX.BLADE_RUSH_COMBO1,
                    AnimationsX.BLADE_RUSH_COMBO2,
                    AnimationsX.BLADE_RUSH_COMBO3
            )
    );
}
