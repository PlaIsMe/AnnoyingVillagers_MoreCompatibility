package com.pla.annoyingvillagers_epicfightx.mixins;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import com.pla.annoyingvillagers.combatbehaviour.*;
import com.pla.annoyingvillagers.mobpatch.LowHerobrineClonePatch;
import com.pla.annoyingvillagers.mobpatch.PlayerNpcPatch;
import com.pla.annoyingvillagers_epicfightx.combatbehaviour.*;
import net.minecraftforge.fml.ModList;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors;
import net.shelmarow.combat_evolution.ai.CEHumanoidPatch;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.LivingMotion;
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

        if (self instanceof PlayerNpcPatch || self instanceof LowHerobrineClonePatch) {
            if (!ModList.get().isLoaded("annoyingvillagers_moredual")) {
                weaponAttackMotions
                        .put(CapabilityItem.WeaponCategories.AXE,
                                ImmutableMap.of(
                                        CapabilityItem.Styles.ONE_HAND, PlayerNpcXAxe.X_AXE));
                weaponAttackMotions
                        .put(CapabilityItem.WeaponCategories.GREATSWORD,
                                ImmutableMap.of(
                                        CapabilityItem.Styles.ONE_HAND, PlayerNpcXGreatsword.X_GREATSWORD));
            }
            this.weaponAttackMotions
                    .put(CapabilityItem.WeaponCategories.FIST,
                            ImmutableMap.of(CapabilityItem.Styles.COMMON, PlayerNpcXFist.X_FIST));
            this.weaponAttackMotions
                    .put(CapabilityItem.WeaponCategories.SWORD,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.ONE_HAND, PlayerNpcXSword.X_SWORD,
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcXSword.X_DUAL_SWORD
                            ));

            this.weaponAttackMotions
                    .put(CapabilityItem.WeaponCategories.DAGGER,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.ONE_HAND, PlayerNpcXDagger.X_DAGGER,
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcXDagger.X_DUAL_DAGGER
                            ));

            this.weaponAttackMotions
                    .put(CapabilityItem.WeaponCategories.UCHIGATANA,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcXUchigatana.X_UCHIGATANA
                            ));
            this.weaponAttackMotions
                    .put(CapabilityItem.WeaponCategories.SPEAR,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.ONE_HAND, PlayerNpcXSpear.X_SPEAR_SHIELD,
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcXSpear.X_SPEAR
                            ));

            this.weaponAttackMotions
                    .put(CapabilityItem.WeaponCategories.LONGSWORD,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.ONE_HAND, PlayerNpcXLongsword.X_LONGSWORD,
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcXLongsword.X_LONGSWORD
                            ));

            this.weaponAttackMotions
                    .put(CapabilityItem.WeaponCategories.TACHI,
                            ImmutableMap.of(
                                    CapabilityItem.Styles.TWO_HAND, PlayerNpcXTachi.X_TACHI
                            ));
        }
    }
}
