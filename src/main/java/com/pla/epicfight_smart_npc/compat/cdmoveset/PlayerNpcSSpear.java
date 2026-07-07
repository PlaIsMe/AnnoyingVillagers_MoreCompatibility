package com.pla.epicfight_smart_npc.compat.cdmoveset;

import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcCombatBehaviorBuilder;
import net.corruptdog.cdm.gameasset.CorruptAnimations;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.spearSpecialAnimations;

public class PlayerNpcSSpear {
    public static final Builder<MobPatch<?>> S_SPEAR_SHIELD = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    CorruptAnimations.SSPEAR_ONEHAND_AUTO
            ),
            CombatCommon.animations(
                    CorruptAnimations.SSPEAR_DASH,
                    Animations.SPEAR_ONEHAND_AIR_SLASH
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? spearSpecialAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    CorruptAnimations.SPEAR_SLASH
            )
    );

    public static final Builder<MobPatch<?>> S_SPEAR = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    CorruptAnimations.SSPEAR_TWOHAND_AUTO1,
                    CorruptAnimations.SSPEAR_TWOHAND_AUTO2
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? spearSpecialAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    CorruptAnimations.SSPEAR_DASH,
                    Animations.SPEAR_TWOHAND_AIR_SLASH
            ),
            CombatCommon.animations(
                    CorruptAnimations.SPEAR_SLASH
            )
    );
}
