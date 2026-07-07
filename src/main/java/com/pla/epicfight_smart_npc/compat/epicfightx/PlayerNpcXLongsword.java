package com.pla.epicfight_smart_npc.compat.epicfightx;

import com.asanginxst.epicfightx.gameassets.animations.AnimationsX;
import com.asanginxst.epicfightx.gameassets.animations.ExtraAnimations;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcCombatBehaviorBuilder;
import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.swordHeavyAnimations;

public class PlayerNpcXLongsword {
        public static final Builder<MobPatch<?>> X_LONGSWORD_SHIELD = PlayerNpcCombatBehaviorBuilder.weapon(
                CombatCommon.animations(
                        AnimationsX.LONGSWORD_AUTO1,
                        AnimationsX.LONGSWORD_AUTO2,
                        AnimationsX.LONGSWORD_AUTO3,
                        ExtraAnimations.LONGSWORD_AUTO4,
                        ExtraAnimations.LONGSWORD_AUTO5
                ),
                (ModList.get().isLoaded("annoyingvillagers") ? swordHeavyAnimations() : CombatCommon.animations()),
                CombatCommon.animations(
                        AnimationsX.LONGSWORD_DASH,
                        AnimationsX.LONGSWORD_AIR_SLASH,
                        AnimationsX.BATTOJUTSU,
                        AnimationsX.SHARP_STAB
                )
        );

        public static final Builder<MobPatch<?>> X_LONGSWORD = PlayerNpcCombatBehaviorBuilder.weapon(
                CombatCommon.animations(
                        AnimationsX.LONGSWORD_AUTO1,
                        AnimationsX.LONGSWORD_AUTO2,
                        AnimationsX.LONGSWORD_AUTO3,
                        ExtraAnimations.LONGSWORD_AUTO4,
                        ExtraAnimations.LONGSWORD_AUTO5
                ),
                (ModList.get().isLoaded("annoyingvillagers") ? swordHeavyAnimations() : CombatCommon.animations()),
                CombatCommon.animations(
                        ExtraAnimations.LONGSWORD_TWOHAND_DASH,
                        ExtraAnimations.LONGSWORD_TWOHAND_AIR_SLASH,
                        AnimationsX.LONGSWORD_LIECHTENAUER_AUTO1,
                        AnimationsX.LONGSWORD_LIECHTENAUER_AUTO2,
                        AnimationsX.LONGSWORD_LIECHTENAUER_AUTO3,
                        ExtraAnimations.LONGSWORD_LIECHTENAUER_AUTO4,
                        ExtraAnimations.LONGSWORD_LIECHTENAUER_AUTO5
                )
        );
}
