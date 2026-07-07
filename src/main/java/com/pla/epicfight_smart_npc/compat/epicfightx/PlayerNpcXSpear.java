package com.pla.epicfight_smart_npc.compat.epicfightx;

import com.asanginxst.epicfightx.gameassets.animations.AnimationsX;
import com.asanginxst.epicfightx.gameassets.animations.ExtraAnimations;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcCombatBehaviorBuilder;
import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.spearSpecialAnimations;

public class PlayerNpcXSpear {
        public static final Builder<MobPatch<?>> X_SPEAR_SHIELD = PlayerNpcCombatBehaviorBuilder.weapon(
                CombatCommon.animations(
                        AnimationsX.SPEAR_ONEHAND_AUTO,
                        ExtraAnimations.SPEAR_ONEHAND_AUTO1,
                        ExtraAnimations.SPEAR_ONEHAND_AUTO2
                ),
                (ModList.get().isLoaded("annoyingvillagers") ? spearSpecialAnimations() : CombatCommon.animations()),
                CombatCommon.animations(
                        AnimationsX.SPEAR_DASH,
                        AnimationsX.SPEAR_ONEHAND_AIR_SLASH,
                        AnimationsX.HEARTPIERCER
                )
        );

        public static final Builder<MobPatch<?>> X_SPEAR = PlayerNpcCombatBehaviorBuilder.weapon(
                CombatCommon.animations(
                        AnimationsX.SPEAR_TWOHAND_AUTO1,
                        AnimationsX.SPEAR_TWOHAND_AUTO2,
                        ExtraAnimations.SPEAR_TWOHAND_AUTO3,
                        ExtraAnimations.SPEAR_TWOHAND_AUTO4,
                        ExtraAnimations.SPEAR_TWOHAND_AUTO5
                ),
                (ModList.get().isLoaded("annoyingvillagers") ? spearSpecialAnimations() : CombatCommon.animations()),
                CombatCommon.animations(
                        ExtraAnimations.SPEAR_TWOHAND_DASH,
                        AnimationsX.SPEAR_TWOHAND_AIR_SLASH,
                        AnimationsX.GRASPING_SPIRAL_FIRST,
                        AnimationsX.GRASPING_SPIRAL_SECOND
                )
        );
}
