package com.pla.epicfight_smart_npc.compat.cdmoveset;

import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcCombatBehaviorBuilder;
import net.corruptdog.cdm.gameasset.CorruptAnimations;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.swordHeavyAnimations;

public class PlayerNpcSLongsword {
    public static final Builder<MobPatch<?>> S_LONGSWORD = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    CorruptAnimations.TACHI_TWOHAND_AUTO_1,
                    CorruptAnimations.TACHI_TWOHAND_AUTO_2,
                    CorruptAnimations.TACHI_TWOHAND_AUTO_3,
                    CorruptAnimations.TACHI_TWOHAND_AUTO_4
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? swordHeavyAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    CorruptAnimations.LONGSWORD_OLD_DASH,
                    CorruptAnimations.LONGSWORD_OLD_AIRSLASH,
                    Animations.LONGSWORD_LIECHTENAUER_AUTO1,
                    Animations.LONGSWORD_LIECHTENAUER_AUTO2,
                    Animations.LONGSWORD_LIECHTENAUER_AUTO3
            )
    );
}
