package com.pla.annoyingvillagers_p1nero_bow.mixins;

import com.p1nero.epicfightbow.item.EFBowItems;
import com.pla.annoyingvillagers.combatbehaviour.NpcBow;
import com.pla.annoyingvillagers.util.MobPatchCommon;
import com.pla.annoyingvillagers_p1nero_bow.combatbehaviour.NpcP1neroBow;
import com.pla.annoyingvillagers_p1nero_bow.combatbehaviour.NpcP1neroMortisBow;
import com.pla.annoyingvillagers_p1nero_bow.combatbehaviour.PlayerNpcP1neroBow;
import com.pla.annoyingvillagers_p1nero_bow.combatbehaviour.PlayerNpcP1neroMortisBow;
import net.minecraft.world.item.Items;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

@Mixin(value = {MobPatchCommon.class}, remap = false)
public abstract class MobPatchCommonMixin {
    @Inject(method = "overideBowMotionBuilderForNpc", at = @At("HEAD"), cancellable = true)
    private static void addBowMovesetForNpc(CapabilityItem mainHandCap, Style style, CallbackInfoReturnable<CECombatBehaviors.Builder<MobPatch<?>>> callbackInfoReturnable) {
        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(EFBowItems.MORTIS.get().getDefaultInstance())) {
            callbackInfoReturnable.setReturnValue(NpcP1neroMortisBow.MORTIS_BOW);
            return;
        }
        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(Items.BOW.getDefaultInstance())) {
            callbackInfoReturnable.setReturnValue(NpcP1neroBow.BOW);
            return;
        }
    }

    @Inject(method = "overideBowMotionBuilderForPlayerNpc", at = @At("HEAD"), cancellable = true)
    private static void addBowMovesetForPlayerNpc(CapabilityItem mainHandCap, Style style, CallbackInfoReturnable<CECombatBehaviors.Builder<MobPatch<?>>> callbackInfoReturnable) {
        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(EFBowItems.MORTIS.get().getDefaultInstance())) {
            callbackInfoReturnable.setReturnValue(PlayerNpcP1neroMortisBow.MORTIS_BOW);
            return;
        }
        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(Items.BOW.getDefaultInstance())) {
            callbackInfoReturnable.setReturnValue(PlayerNpcP1neroBow.BOW);
            return;
        }
    }
}
