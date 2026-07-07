package com.pla.epicfight_smart_npc.compat.annoyingvillagers;

import com.pla.annoyingvillagers.gameasset.AnimsEpicFightBattleArts;
import com.pla.annoyingvillagers.gameasset.AnimsPugilistSteve;
import com.pla.annoyingvillagers.gameasset.AnimsYonchiChikito;
import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcCombatBehaviorBuilder;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import reascer.wom.gameasset.WOMAnimations;
import reascer.wom.gameasset.animations.weapons.AnimsSolar;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

public class PlayerNpcAvGreatsword {
    public static final Builder<MobPatch<?>> AV_GREATSWORD = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    Animations.GREATSWORD_AUTO1,
                    Animations.GREATSWORD_AUTO2,
                    WOMAnimations.TORMENT_AUTO_2,
                    WOMAnimations.TORMENT_AUTO_3,
                    AnimsSolar.SOLAR_HORNO
            ),
            CombatCommon.animations(
                    AnimsPugilistSteve.GIANT_WHIRLWIND,
                    AnimsPugilistSteve.GREATSWORD_SKILL
            ),
            CombatCommon.animations(
                    Animations.GREATSWORD_DASH,
                    Animations.GREATSWORD_AIR_SLASH
            )
    );

    public static final Builder<MobPatch<?>> GREATAXE = avGreatswordAxeMoveset(
            AnimsYonchiChikito.SLAM_THIRD
    );

    public static final Builder<MobPatch<?>> GIANT_AXE = avGreatswordAxeMoveset(
            AnimsYonchiChikito.SLAM_SECOND
    );

    public static final Builder<MobPatch<?>> BATTLE_AXE = avGreatswordAxeMoveset(
            AnimsYonchiChikito.SLAM_FIRST
    );

    private static Builder<MobPatch<?>> avGreatswordAxeMoveset(
            AnimationManager.AnimationAccessor<? extends StaticAnimation> slamAnimation
    ) {
        return PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    AnimsYonchiChikito.GREATAXE_SLASH,
                    WOMAnimations.TORMENT_AUTO_3,
                    AnimsEpicFightBattleArts.GREATSWORD_DASH_ATTACK,
                    WOMAnimations.TORMENT_BERSERK_AUTO_1,
                    WOMAnimations.TORMENT_BERSERK_AUTO_2
            ),
            CombatCommon.animations(
                    AnimsEpicFightBattleArts.GREATSWORD_POWER_GEYSER,
                    AnimsEpicFightBattleArts.GREATSWORD_AIRSLAM
            ),
            CombatCommon.animations(
                    AnimsPugilistSteve.GIANT_WHIRLWIND,
                    slamAnimation
            )
        );
    }

    public static final Builder<MobPatch<?>> CLEAVER = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    AnimsEpicFightBattleArts.SQUIRE_SWORD_AUTO_1,
                    AnimsEpicFightBattleArts.SQUIRE_SWORD_AUTO_2,
                    Animations.LONGSWORD_LIECHTENAUER_AUTO1,
                    Animations.LONGSWORD_LIECHTENAUER_AUTO2,
                    AnimsEpicFightBattleArts.SQUIRE_SWORD_AUTO_3
            ),
            CombatCommon.animations(
                    AnimsEpicFightBattleArts.SQUIRE_SWORD_DASH_ATTACK,
                    AnimsEpicFightBattleArts.SQUIRE_SWORD_HOP_ATTACK
            ),
            CombatCommon.animations(
                    AnimsPugilistSteve.GIANT_WHIRLWIND,
                    AnimsEpicFightBattleArts.SQUIRE_SWORD_HEAVY_BLOW
            )
    );
}
