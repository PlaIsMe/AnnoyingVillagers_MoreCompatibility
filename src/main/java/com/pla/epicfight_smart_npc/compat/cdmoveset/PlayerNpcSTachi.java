package com.pla.epicfight_smart_npc.compat.cdmoveset;

import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcCombatBehaviorBuilder;
import net.corruptdog.cdm.gameasset.CorruptAnimations;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.swordHeavyAnimations;

public class PlayerNpcSTachi {
    public static final Builder<MobPatch<?>> S_TACHI = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    CorruptAnimations.LONGSWORD_OLD_AUTO1,
                    CorruptAnimations.LONGSWORD_OLD_AUTO2,
                    CorruptAnimations.LONGSWORD_OLD_AUTO3,
                    CorruptAnimations.LONGSWORD_OLD_AUTO4
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? swordHeavyAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    CorruptAnimations.LONGSWORD_OLD_DASH,
                    CorruptAnimations.LONGSWORD_OLD_AIRSLASH,
                    CorruptAnimations.LETHAL_SLICING_START,
                    CorruptAnimations.LETHAL_SLICING_ONCE,
                    CorruptAnimations.LETHAL_SLICING_TWICE,
                    CorruptAnimations.TACHI_SLASH
            )
    );

    public static final Builder<MobPatch<?>> GREAT_TACHI = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    CorruptAnimations.GREAT_TACHI_AUTO1,
                    CorruptAnimations.GREAT_TACHI_AUTO2,
                    CorruptAnimations.GREAT_TACHI_AUTO3,
                    CorruptAnimations.GREAT_TACHI_AUTO4
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? swordHeavyAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    CorruptAnimations.UCHIGATANA_DASH,
                    CorruptAnimations.LONGSWORD_OLD_AIRSLASH,
                    CorruptAnimations.UCHIGATANA_HEAVY1,
                    CorruptAnimations.UCHIGATANA_HEAVY2
            )
    );
}
