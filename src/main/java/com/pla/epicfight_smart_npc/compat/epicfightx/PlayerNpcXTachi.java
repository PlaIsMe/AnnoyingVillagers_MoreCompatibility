package com.pla.epicfight_smart_npc.compat.epicfightx;

import com.asanginxst.epicfightx.gameassets.animations.AnimationsX;
import com.asanginxst.epicfightx.gameassets.animations.ExtraAnimations;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcCombatBehaviorBuilder;
import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.swordHeavyAnimations;

public class PlayerNpcXTachi {
        public static final Builder<MobPatch<?>> X_TACHI = PlayerNpcCombatBehaviorBuilder.weapon(
                CombatCommon.animations(
                        AnimationsX.TACHI_AUTO1,
                        AnimationsX.TACHI_AUTO2,
                        AnimationsX.TACHI_AUTO3,
                        ExtraAnimations.TACHI_AUTO4,
                        ExtraAnimations.TACHI_AUTO5
                ),
                (ModList.get().isLoaded("annoyingvillagers") ? swordHeavyAnimations() : CombatCommon.animations()),
                CombatCommon.animations(
                        AnimationsX.TACHI_DASH,
                        ExtraAnimations.TACHI_AIR_SLASH,
                        AnimationsX.RUSHING_TEMPO1,
                        AnimationsX.RUSHING_TEMPO2,
                        AnimationsX.RUSHING_TEMPO3,
                        ExtraAnimations.RUSHING_TEMPO4,
                        ExtraAnimations.RUSHING_TEMPO5
                )
        );
}
