package com.pla.epicfight_smart_npc.compat.epicfightx;

import com.asanginxst.epicfightx.gameassets.animations.AnimationsX;
import com.asanginxst.epicfightx.gameassets.animations.ExtraAnimations;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcCombatBehaviorBuilder;
import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.*;

public class PlayerNpcXSword {
    public static final Builder<MobPatch<?>> X_SWORD = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    AnimationsX.SWORD_AUTO1,
                    AnimationsX.SWORD_AUTO2,
                    AnimationsX.SWORD_AUTO3,
                    ExtraAnimations.SWORD_AUTO4,
                    ExtraAnimations.SWORD_AUTO5
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? swordHeavyAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    AnimationsX.SWORD_DASH,
                    AnimationsX.SWORD_AIR_SLASH,
                    AnimationsX.SWEEPING_EDGE
            )
    );

    public static final Builder<MobPatch<?>> X_DUAL_SWORD = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    AnimationsX.SWORD_DUAL_AUTO1,
                    AnimationsX.SWORD_DUAL_AUTO2,
                    AnimationsX.SWORD_DUAL_AUTO3,
                    ExtraAnimations.SWORD_DUAL_AUTO4,
                    ExtraAnimations.SWORD_DUAL_AUTO5
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? dualSwordSpecialAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    AnimationsX.SWORD_DUAL_DASH,
                    AnimationsX.SWORD_DUAL_AIR_SLASH,
                    AnimationsX.DANCING_EDGE
            )
    );
}
