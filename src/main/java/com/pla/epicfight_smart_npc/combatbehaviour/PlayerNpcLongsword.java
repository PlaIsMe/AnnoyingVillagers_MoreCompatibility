package com.pla.epicfight_smart_npc.combatbehaviour;

import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.swordHeavyAnimations;

public class PlayerNpcLongsword {
    public static final Builder<MobPatch<?>> LONGSWORD_SHIELD = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    Animations.LONGSWORD_AUTO1,
                    Animations.LONGSWORD_AUTO2,
                    Animations.LONGSWORD_AUTO3
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? swordHeavyAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    Animations.LONGSWORD_DASH,
                    Animations.LONGSWORD_AIR_SLASH,
                    Animations.BATTOJUTSU,
                    Animations.SHARP_STAB
            )
    );

    public static final Builder<MobPatch<?>> LONGSWORD = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    Animations.LONGSWORD_AUTO1,
                    Animations.LONGSWORD_AUTO2,
                    Animations.LONGSWORD_AUTO3
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? swordHeavyAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    Animations.LONGSWORD_DASH,
                    Animations.LONGSWORD_AIR_SLASH,
                    Animations.LONGSWORD_LIECHTENAUER_AUTO1,
                    Animations.LONGSWORD_LIECHTENAUER_AUTO2,
                    Animations.LONGSWORD_LIECHTENAUER_AUTO3
            )
    );
}
