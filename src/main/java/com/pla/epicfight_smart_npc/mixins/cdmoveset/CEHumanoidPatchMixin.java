package com.pla.epicfight_smart_npc.mixins.cdmoveset;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcDagger;
import com.pla.epicfight_smart_npc.combatbehaviour.PlayerNpcLongsword;
import com.pla.epicfight_smart_npc.compat.cdmoveset.*;
import com.pla.epicfight_smart_npc.mobpatch.PlayerNpcPatch;
import net.corruptdog.cdm.gameasset.CorruptAnimations;
import net.corruptdog.cdm.world.CorruptWeaponCategories;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors;
import net.shelmarow.combat_evolution.ai.CEHumanoidPatch;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.Factions;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Mixin(value = {CEHumanoidPatch.class}, remap = false)
public abstract class CEHumanoidPatchMixin {
    @Shadow protected Map<WeaponCategory, Map<Style, Set<Pair<LivingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation>>>>>
            weaponLivingMotions;

    @Shadow protected Map<WeaponCategory, Map<Style, List<AnimationManager.AnimationAccessor<? extends StaticAnimation>>>>
            guardHitMotions;

    @Shadow protected Map<WeaponCategory, Map<Style, CECombatBehaviors.Builder<MobPatch<?>>>>
            weaponAttackMotions;


    @Inject(method = "<init>", at = @At("RETURN"))
    private void addMoreWeaponMotions(Factions factions, CallbackInfo ci) {
        CEHumanoidPatch self = (CEHumanoidPatch) (Object) this;

        if (self instanceof PlayerNpcPatch) {
            weaponLivingMotions
                    .put(CorruptWeaponCategories.KATANA,
                            ImmutableMap.of(CapabilityItem.Styles.TWO_HAND,
                                    Set.of(
                                            Pair.of(LivingMotions.BLOCK, Animations.UCHIGATANA_GUARD),
                                            Pair.of(LivingMotions.IDLE, CorruptAnimations.BIPED_HOLD_KATANA),
                                            Pair.of(LivingMotions.WALK, CorruptAnimations.WALK_KATANA),
                                            Pair.of(LivingMotions.RUN, CorruptAnimations.RUN_KATANA),
                                            Pair.of(LivingMotions.CHASE, CorruptAnimations.RUN_KATANA),
                                            Pair.of(LivingMotions.DEATH, Animations.BIPED_DEATH)
                                    )));
            weaponAttackMotions
                    .put(CorruptWeaponCategories.KATANA,
                            ImmutableMap.of(CapabilityItem.Styles.TWO_HAND, PlayerNpcKatana.KATANA));
            guardHitMotions.put(CorruptWeaponCategories.KATANA,
                    ImmutableMap.of(
                            CapabilityItem.Styles.TWO_HAND, List.of(
                                    Animations.SWORD_GUARD_ACTIVE_HIT1,
                                    Animations.SWORD_GUARD_ACTIVE_HIT2,
                                    Animations.SWORD_GUARD_ACTIVE_HIT3
                            )
                    )
            );

            weaponLivingMotions
                    .put(CorruptWeaponCategories.S_GREATSWORD,
                            ImmutableMap.of(CapabilityItem.Styles.TWO_HAND,
                                    Set.of(
                                            Pair.of(LivingMotions.BLOCK, Animations.GREATSWORD_GUARD),
                                            Pair.of(LivingMotions.IDLE, CorruptAnimations.GREATSWORD_OLD_IDLE),
                                            Pair.of(LivingMotions.WALK, CorruptAnimations.GREATSWORD_OLD_WALK),
                                            Pair.of(LivingMotions.RUN, CorruptAnimations.GREATSWORD_OLD_RUN),
                                            Pair.of(LivingMotions.CHASE, CorruptAnimations.GREATSWORD_OLD_RUN),
                                            Pair.of(LivingMotions.DEATH, Animations.BIPED_DEATH)
                                    ), CapabilityItem.Styles.ONE_HAND,
                                    Set.of(
                                            Pair.of(LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD),
                                            Pair.of(LivingMotions.IDLE, CorruptAnimations.DUAL_GREATSWORD_IDLE),
                                            Pair.of(LivingMotions.WALK, CorruptAnimations.DUAL_GREATSWORD_WALK),
                                            Pair.of(LivingMotions.RUN, CorruptAnimations.DUAL_GREATSWORD_RUN),
                                            Pair.of(LivingMotions.CHASE, CorruptAnimations.DUAL_GREATSWORD_RUN),
                                            Pair.of(LivingMotions.DEATH, Animations.BIPED_DEATH)
                                    )));
            weaponAttackMotions
                    .put(CorruptWeaponCategories.S_GREATSWORD,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcSGreatsword.S_GREATSWORD,
                                    CapabilityItem.Styles.ONE_HAND, PlayerNpcSGreatsword.DUAL_S_GREATSWORD
                            ));
            guardHitMotions.put(CorruptWeaponCategories.S_GREATSWORD,
                    ImmutableMap.of(
                            CapabilityItem.Styles.TWO_HAND, List.of(
                                    Animations.GREATSWORD_GUARD_HIT
                            ),
                            CapabilityItem.Styles.ONE_HAND, List.of(
                                    Animations.SWORD_DUAL_GUARD_HIT
                            )
                    )
            );

            weaponLivingMotions
                    .put(CorruptWeaponCategories.S_TACHI,
                            ImmutableMap.of(CapabilityItem.Styles.TWO_HAND,
                                    Set.of(
                                            Pair.of(LivingMotions.BLOCK, CorruptAnimations.TACHI_GUARD),
                                            Pair.of(LivingMotions.IDLE, Animations.BIPED_HOLD_TACHI),
                                            Pair.of(LivingMotions.WALK, Animations.BIPED_HOLD_TACHI),
                                            Pair.of(LivingMotions.RUN, Animations.BIPED_HOLD_TACHI),
                                            Pair.of(LivingMotions.CHASE, Animations.BIPED_HOLD_TACHI),
                                            Pair.of(LivingMotions.DEATH, Animations.BIPED_DEATH)
                                    )));
            weaponAttackMotions
                    .put(CorruptWeaponCategories.S_TACHI,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcSTachi.S_TACHI
                            ));
            guardHitMotions.put(CorruptWeaponCategories.S_TACHI,
                    ImmutableMap.of(
                            CapabilityItem.Styles.TWO_HAND, List.of(
                                    CorruptAnimations.TACHI_GUARD_HIT
                            )
                    )
            );

            weaponLivingMotions
                    .put(CorruptWeaponCategories.GREAT_TACHI,
                            ImmutableMap.of(CapabilityItem.Styles.TWO_HAND,
                                    Set.of(
                                            Pair.of(LivingMotions.BLOCK, CorruptAnimations.TACHI_GUARD),
                                            Pair.of(LivingMotions.IDLE, CorruptAnimations.BIPED_HOLD_KATANA),
                                            Pair.of(LivingMotions.WALK, CorruptAnimations.WALK_KATANA),
                                            Pair.of(LivingMotions.RUN, CorruptAnimations.RUN_KATANA),
                                            Pair.of(LivingMotions.CHASE, CorruptAnimations.RUN_KATANA),
                                            Pair.of(LivingMotions.DEATH, Animations.BIPED_DEATH)
                                    )));
            weaponAttackMotions
                    .put(CorruptWeaponCategories.GREAT_TACHI,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcSTachi.GREAT_TACHI
                            ));
            guardHitMotions.put(CorruptWeaponCategories.GREAT_TACHI,
                    ImmutableMap.of(
                            CapabilityItem.Styles.TWO_HAND, List.of(
                                    CorruptAnimations.TACHI_GUARD_HIT
                            )
                    )
            );

            weaponLivingMotions
                    .put(CorruptWeaponCategories.S_DAGGER,
                            ImmutableMap.of(CapabilityItem.Styles.TWO_HAND,
                                    Set.of(
                                            Pair.of(LivingMotions.IDLE, Animations.BIPED_HOLD_DUAL_WEAPON),
                                            Pair.of(LivingMotions.WALK, Animations.BIPED_HOLD_DUAL_WEAPON),
                                            Pair.of(LivingMotions.RUN, Animations.BIPED_HOLD_DUAL_WEAPON),
                                            Pair.of(LivingMotions.CHASE, Animations.BIPED_HOLD_DUAL_WEAPON),
                                            Pair.of(LivingMotions.DEATH, Animations.BIPED_DEATH)
                                    ), CapabilityItem.Styles.ONE_HAND,
                                    Set.of(
                                            Pair.of(LivingMotions.IDLE, Animations.BIPED_IDLE),
                                            Pair.of(LivingMotions.WALK, Animations.BIPED_WALK),
                                            Pair.of(LivingMotions.RUN, Animations.BIPED_RUN),
                                            Pair.of(LivingMotions.CHASE, Animations.BIPED_RUN),
                                            Pair.of(LivingMotions.DEATH, Animations.BIPED_DEATH)
                                    )));
            weaponAttackMotions
                    .put(CorruptWeaponCategories.S_DAGGER,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcDagger.DUAL_DAGGER,
                                    CapabilityItem.Styles.ONE_HAND, PlayerNpcDagger.DAGGER
                            ));

            weaponLivingMotions
                    .put(CorruptWeaponCategories.S_LONGSWORD,
                            ImmutableMap.of(CapabilityItem.Styles.TWO_HAND,
                                    Set.of(
                                            Pair.of(LivingMotions.BLOCK, Animations.LONGSWORD_GUARD),
                                            Pair.of(LivingMotions.IDLE, Animations.BIPED_HOLD_LONGSWORD),
                                            Pair.of(LivingMotions.WALK, Animations.BIPED_WALK_LONGSWORD),
                                            Pair.of(LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD),
                                            Pair.of(LivingMotions.CHASE, Animations.BIPED_RUN_LONGSWORD),
                                            Pair.of(LivingMotions.DEATH, Animations.BIPED_DEATH)
                                    ), CapabilityItem.Styles.ONE_HAND,
                                    Set.of(
                                            Pair.of(LivingMotions.BLOCK, Animations.LONGSWORD_GUARD),
                                            Pair.of(LivingMotions.IDLE, Animations.BIPED_HOLD_LONGSWORD),
                                            Pair.of(LivingMotions.WALK, Animations.BIPED_WALK_LONGSWORD),
                                            Pair.of(LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD),
                                            Pair.of(LivingMotions.CHASE, Animations.BIPED_RUN_LONGSWORD),
                                            Pair.of(LivingMotions.DEATH, Animations.BIPED_DEATH)
                                    )));
            weaponAttackMotions
                    .put(CorruptWeaponCategories.S_LONGSWORD,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcSLongsword.S_LONGSWORD,
                                    CapabilityItem.Styles.ONE_HAND, PlayerNpcLongsword.LONGSWORD_SHIELD
                            ));
            guardHitMotions.put(CorruptWeaponCategories.S_LONGSWORD,
                    ImmutableMap.of(
                            CapabilityItem.Styles.TWO_HAND, List.of(
                                    Animations.LONGSWORD_GUARD_ACTIVE_HIT1,
                                    Animations.LONGSWORD_GUARD_ACTIVE_HIT2
                            ),
                            CapabilityItem.Styles.ONE_HAND, List.of(
                                    Animations.LONGSWORD_GUARD_HIT
                            )
                    )
            );

            weaponLivingMotions
                    .put(CorruptWeaponCategories.S_SPEAR,
                            ImmutableMap.of(CapabilityItem.Styles.TWO_HAND,
                                    Set.of(
                                            Pair.of(LivingMotions.BLOCK, Animations.SPEAR_GUARD),
                                            Pair.of(LivingMotions.IDLE, Animations.BIPED_HOLD_SPEAR),
                                            Pair.of(LivingMotions.WALK, Animations.BIPED_WALK_SPEAR),
                                            Pair.of(LivingMotions.RUN, Animations.BIPED_RUN_SPEAR),
                                            Pair.of(LivingMotions.CHASE, Animations.BIPED_RUN_SPEAR),
                                            Pair.of(LivingMotions.DEATH, Animations.BIPED_DEATH)
                                    ), CapabilityItem.Styles.ONE_HAND,
                                    Set.of(
                                            Pair.of(LivingMotions.BLOCK, Animations.SPEAR_GUARD),
                                            Pair.of(LivingMotions.IDLE, Animations.BIPED_WALK),
                                            Pair.of(LivingMotions.WALK, Animations.BIPED_WALK),
                                            Pair.of(LivingMotions.RUN, Animations.BIPED_RUN_SPEAR),
                                            Pair.of(LivingMotions.CHASE, Animations.BIPED_RUN_SPEAR),
                                            Pair.of(LivingMotions.DEATH, Animations.BIPED_DEATH)
                                    )));
            weaponAttackMotions
                    .put(CorruptWeaponCategories.S_SPEAR,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcSSpear.S_SPEAR,
                                    CapabilityItem.Styles.ONE_HAND, PlayerNpcSSpear.S_SPEAR_SHIELD
                            ));
            guardHitMotions.put(CorruptWeaponCategories.S_SPEAR,
                    ImmutableMap.of(
                            CapabilityItem.Styles.TWO_HAND, List.of(
                                    Animations.SPEAR_GUARD_HIT
                            ),
                            CapabilityItem.Styles.ONE_HAND, List.of(
                                    Animations.SPEAR_GUARD_HIT
                            )
                    )
            );

            weaponLivingMotions
                    .put(CorruptWeaponCategories.S_SWORD,
                            ImmutableMap.of(CapabilityItem.Styles.TWO_HAND,
                                    Set.of(
                                            Pair.of(LivingMotions.BLOCK, Animations.SWORD_DUAL_GUARD),
                                            Pair.of(LivingMotions.IDLE, Animations.BIPED_HOLD_DUAL_WEAPON),
                                            Pair.of(LivingMotions.WALK, Animations.BIPED_HOLD_DUAL_WEAPON),
                                            Pair.of(LivingMotions.RUN, Animations.BIPED_RUN_DUAL),
                                            Pair.of(LivingMotions.CHASE, Animations.BIPED_RUN_DUAL),
                                            Pair.of(LivingMotions.DEATH, Animations.BIPED_DEATH)
                                    ), CapabilityItem.Styles.ONE_HAND,
                                    Set.of(
                                            Pair.of(LivingMotions.BLOCK, Animations.SWORD_GUARD),
                                            Pair.of(LivingMotions.IDLE, Animations.BIPED_HOLD_LONGSWORD),
                                            Pair.of(LivingMotions.WALK, Animations.BIPED_WALK_LONGSWORD),
                                            Pair.of(LivingMotions.RUN, Animations.BIPED_RUN_LONGSWORD),
                                            Pair.of(LivingMotions.CHASE, Animations.BIPED_RUN_LONGSWORD),
                                            Pair.of(LivingMotions.DEATH, Animations.BIPED_DEATH)
                                    )));
            weaponAttackMotions
                    .put(CorruptWeaponCategories.S_SWORD,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcSSword.S_DUALSWORD,
                                    CapabilityItem.Styles.ONE_HAND, PlayerNpcSSword.S_SWORD
                            ));
            guardHitMotions.put(CorruptWeaponCategories.S_SWORD,
                    ImmutableMap.of(
                            CapabilityItem.Styles.TWO_HAND, List.of(
                                    Animations.SWORD_DUAL_GUARD_HIT
                            ),
                            CapabilityItem.Styles.ONE_HAND, List.of(
                                    Animations.SWORD_GUARD_HIT
                            )
                    )
            );
        }
    }
}
