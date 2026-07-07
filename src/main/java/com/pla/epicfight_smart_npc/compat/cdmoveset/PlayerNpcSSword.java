package com.pla.epicfight_smart_npc.compat.cdmoveset;

import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcCombatBehaviorBuilder;
import net.corruptdog.cdm.gameasset.CorruptAnimations;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.*;

public class PlayerNpcSSword {
    public static final Builder<MobPatch<?>> S_SWORD = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    CorruptAnimations.SWORD_ONEHAND_AUTO1,
                    CorruptAnimations.SWORD_ONEHAND_AUTO2,
                    CorruptAnimations.SWORD_ONEHAND_AUTO3,
                    CorruptAnimations.SWORD_ONEHAND_AUTO4
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? swordHeavyAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    CorruptAnimations.SWORD_ONEHAND_DASH,
                    Animations.SWORD_AIR_SLASH,
                    CorruptAnimations.SWORD_SLASH
            )
    );

    public static final Builder<MobPatch<?>> S_DUALSWORD = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    Animations.SWORD_DUAL_AUTO1,
                    Animations.SWORD_DUAL_AUTO2,
                    Animations.DAGGER_DUAL_AUTO3,
                    Animations.SWORD_DUAL_AUTO3,
                    Animations.DAGGER_DUAL_AUTO4
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? dualSwordSpecialAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    Animations.SWORD_DUAL_DASH,
                    Animations.SWORD_DUAL_AIR_SLASH,
                    CorruptAnimations.DUAL_SLASH
            )
    );
}
