package com.pla.epicfight_smart_npc.compat.annoyingvillagers;

import com.pla.annoyingvillagers.gameasset.*;
import com.pla.epicfight_smart_npc.combatbehaviour.CombatCommon;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcCombatBehaviorBuilder;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors.Builder;
import reascer.wom.gameasset.WOMAnimations;
import reascer.wom.gameasset.animations.weapons.AnimsAgony;
import reascer.wom.gameasset.animations.weapons.AnimsOrbit;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;

public class PlayerNpcAvSpear {
    public static final Builder<MobPatch<?>> GUANDAO = avSpearMoveset(
            CombatCommon.animations(
                    AnimsEpicFightGuandao.FALCHION_AUTO1,
                    AnimsEpicFightGuandao.FALCHION_AUTO2,
                    AnimsEpicFightGuandao.FALCHION_AUTO3,
                    AnimsOrbit.ORBIT_ATTACK_4,
                    AnimsOrbit.ORBIT_ATTACK_3
            ),
            CombatCommon.animations(
                    AnimsAgony.AGONY_CLAWSTRIKE,
                    AnimsOrbit.ORBIT_MAD_REACH,
                    AnimsPugilistSteve.SPEAR_THRUST
            )
    );

    public static final Builder<MobPatch<?>> SPEAR_STAFF = avSpearMoveset(
            CombatCommon.animations(
                    AnimsEpicFightGuandao.FALCHION_AUTO1,
                    AnimsEpicFightGuandao.FALCHION_AUTO2,
                    WOMAnimations.STAFF_AUTO_2,
                    WOMAnimations.STAFF_AUTO_3,
                    WOMAnimations.STAFF_CHARYBDIS
            ),
            CombatCommon.animations(
                    AnimsYonchiChikito.SAKURA_STAFF_DASH,
                    AnimsAgony.AGONY_RIPPING_FANGS,
                    AnimsPugilistSteve.SPEAR_THRUST
            )
    );

    public static final Builder<MobPatch<?>> SICKLE = avSpearMoveset(
            CombatCommon.animations(
                    AnimsEpicFightGuandao.FALCHION_AUTO2,
                    AnimsEpicFightGuandao.FALCHION_AUTO1,
                    AnimsWom.CLONE_ANTITHEUS_AUTO_2,
                    AnimsWom.CLONE_ANTITHEUS_AUTO_1,
                    AnimsWom.CLONE_ANTITHEUS_AUTO_4
            ),
            CombatCommon.animations(
                    AnimsOrbit.ORBIT_SATELITE,
                    AnimsWom.CLONE_ANTITHEUS_GUILLOTINE,
                    AnimsPugilistSteve.SPEAR_THRUST
            )
    );

    public static final Builder<MobPatch<?>> BOLT = avSpearMoveset(
            CombatCommon.animations(
                    AnimsEpicFightGuandao.FALCHION_AUTO1,
                    AnimsEpicFightGuandao.FALCHION_AUTO2,
                    AnimsEpicFightGuandao.FALCHION_AUTO3,
                    AnimsPugilistSteve.SPEAR_THRUST
            ),
            CombatCommon.animations(
                    AnimsAgony.AGONY_CLAWSTRIKE,
                    AnimsOrbit.ORBIT_MAD_REACH,
                    AnimsPugilistSteve.SPEAR_THRUST
            )
    );

    public static final Builder<MobPatch<?>> BLACK_SCRATCHER = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    AVAnimations.BLACKSCRATCHER_ATTACK,
                    AVAnimations.BLACKSCRATCHER_ATTACK,
                    AVAnimations.BLACKSCRATCHER_ATTACK
            ),
            CombatCommon.animations(
                    AVAnimations.BLACKSCRATCHER_ATTACK
            ),
            CombatCommon.animations(
                    AVAnimations.BLACKSCRATCHER_ATTACK
            )
    );

    public static final Builder<MobPatch<?>> STAFF = PlayerNpcCombatBehaviorBuilder.weapon(
            CombatCommon.animations(
                    WOMAnimations.STAFF_AUTO_1,
                    WOMAnimations.STAFF_AUTO_2,
                    WOMAnimations.STAFF_AUTO_3
            ),
            CombatCommon.animations(
                    WOMAnimations.STAFF_SQUALL,
                    WOMAnimations.STAFF_KINKONG
            ),
            CombatCommon.animations(
                    WOMAnimations.STAFF_CHARYBDIS,
                    AnimsPugilistSteve.SPEAR_THRUST
            )
    );

    private static Builder<MobPatch<?>> avSpearMoveset(
            AnimationManager.AnimationAccessor<? extends StaticAnimation>[] opener,
            AnimationManager.AnimationAccessor<? extends StaticAnimation>[] utility
    ) {
        return PlayerNpcCombatBehaviorBuilder.weapon(opener,
                utility,
                CombatCommon.animations(
                        AnimsEpicFightGuandao.FALCHION_FORWARD,
                        AnimsEpicFightGuandao.FALCHION_BACKWARD,
                        AnimsEpicFightGuandao.FALCHION_SIDE
                )
        );
    }

    private static Builder<MobPatch<?>> avSpearStaffMoveset(
            AnimationManager.AnimationAccessor<? extends StaticAnimation>[] opener,
            AnimationManager.AnimationAccessor<? extends StaticAnimation>[] utility
    ) {
        return PlayerNpcCombatBehaviorBuilder.weapon(opener,
                utility,
                CombatCommon.animations(
                        AnimsEpicFightGuandao.FALCHION_FORWARD,
                        AnimsEpicFightGuandao.FALCHION_BACKWARD,
                        AnimsEpicFightGuandao.FALCHION_SIDE
                )
        );
    }
}
