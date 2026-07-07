package com.pla.epicfight_smart_npc.compat.refm;

import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcCombatBehaviorBuilder;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import net.yonchi.refm.gameasset.RapierAnimations;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

import static com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagers.rapierSpecialAnimations;

public class PlayerNpcRapier {
    public static final Builder<MobPatch<?>> RAPIER = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    RapierAnimations.RAPIER_AUTO1,
                    RapierAnimations.RAPIER_AUTO2,
                    RapierAnimations.RAPIER_AUTO3
            ),
            CombatCommon.animations(
                    RapierAnimations.RAPIER_DASH,
                    RapierAnimations.RAPIER_AIR_SLASH
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? rapierSpecialAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    RapierAnimations.DEADLYBACKFLIP_FIRST,
                    RapierAnimations.DEADLYBACKFLIP_SECOND,
                    RapierAnimations.DEADLYBACKFLIP_FAIL
            )
    );

    public static final Builder<MobPatch<?>> ENDER_RAPIER = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    RapierAnimations.RAPIER_AUTO1,
                    RapierAnimations.RAPIER_AUTO2_ENDER,
                    RapierAnimations.RAPIER_AUTO3_ENDER
            ),
            CombatCommon.animations(
                    RapierAnimations.RAPIER_DASH_ENDER,
                    RapierAnimations.RAPIER_AIR_SLASH_ENDER
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? rapierSpecialAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    RapierAnimations.DEADLYBACKFLIP_FIRST,
                    RapierAnimations.DEADLYBACKFLIP_SECOND_ENDER,
                    RapierAnimations.DEADLYBACKFLIP_FAIL
            )
    );

    public static final Builder<MobPatch<?>> OCEAN_RAPIER = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    RapierAnimations.RAPIER_AUTO1,
                    RapierAnimations.RAPIER_AUTO2_OCEAN,
                    RapierAnimations.RAPIER_AUTO3_OCEAN
            ),
            CombatCommon.animations(
                    RapierAnimations.RAPIER_DASH_OCEAN,
                    RapierAnimations.RAPIER_AIR_SLASH_OCEAN
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? rapierSpecialAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    RapierAnimations.DEADLYBACKFLIP_FIRST,
                    RapierAnimations.DEADLYBACKFLIP_SECOND_OCEAN,
                    RapierAnimations.DEADLYBACKFLIP_FAIL
            )
    );

    public static final Builder<MobPatch<?>> WITHER_RAPIER = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    RapierAnimations.RAPIER_AUTO1,
                    RapierAnimations.RAPIER_AUTO2_WITHER,
                    RapierAnimations.RAPIER_AUTO3_WITHER
            ),
            CombatCommon.animations(
                    RapierAnimations.RAPIER_DASH_WITHER,
                    RapierAnimations.RAPIER_AIR_SLASH_WITHER
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? rapierSpecialAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    RapierAnimations.DEADLYBACKFLIP_FIRST,
                    RapierAnimations.DEADLYBACKFLIP_SECOND_WITHER,
                    RapierAnimations.DEADLYBACKFLIP_FAIL
            )
    );

    public static final Builder<MobPatch<?>> AMETHYST_RAPIER = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    RapierAnimations.RAPIER_AUTO1,
                    RapierAnimations.RAPIER_AUTO2_AMETHYST,
                    RapierAnimations.RAPIER_AUTO3_AMETHYST
            ),
            CombatCommon.animations(
                    RapierAnimations.RAPIER_DASH_AMETHYST,
                    RapierAnimations.RAPIER_AIR_SLASH_AMETHYST
            ),
            (ModList.get().isLoaded("annoyingvillagers") ? rapierSpecialAnimations() : CombatCommon.animations()),
            CombatCommon.animations(
                    RapierAnimations.DEADLYBACKFLIP_FIRST,
                    RapierAnimations.DEADLYBACKFLIP_SECOND_AMETHYST,
                    RapierAnimations.DEADLYBACKFLIP_FAIL
            )
    );
}
