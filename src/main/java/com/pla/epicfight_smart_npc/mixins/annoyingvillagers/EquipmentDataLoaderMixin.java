package com.pla.epicfight_smart_npc.mixins.annoyingvillagers;

import com.pla.epicfight_smart_npc.compat.annoyingvillagers.AnnoyingVillagersEquipment;
import com.pla.smart_npc.clazz.Difficulty;
import com.pla.smart_npc.util.EquipmentDataLoader;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(value = EquipmentDataLoader.class, remap = false)
public abstract class EquipmentDataLoaderMixin {
    @Inject(method = "canUseShield", at = @At("HEAD"), cancellable = true)
    private static void epicfightSmartNpc$preventAvSpearShield(ItemStack stack, CallbackInfoReturnable<Boolean> callbackInfo) {
        if (AnnoyingVillagersEquipment.preventsShield(stack)) {
            callbackInfo.setReturnValue(false);
        }
    }

    @Inject(method = "canUseCompatShield", at = @At("HEAD"), cancellable = true)
    private static void epicfightSmartNpc$canUseAvShield(ItemStack stack, CallbackInfoReturnable<Boolean> callbackInfo) {
        if (AnnoyingVillagersEquipment.canUseShield(stack)) {
            callbackInfo.setReturnValue(true);
        }
    }

    @Inject(method = "canTwoHand", at = @At("HEAD"), cancellable = true)
    private static void epicfightSmartNpc$preventAvRandomDualWeapon(ItemStack stack, CallbackInfoReturnable<Boolean> callbackInfo) {
        if (AnnoyingVillagersEquipment.preventsDualWeapon(stack)) {
            callbackInfo.setReturnValue(false);
        }
    }

    @Inject(method = "canUseCompatDualWeapon", at = @At("HEAD"), cancellable = true)
    private static void epicfightSmartNpc$canUseAvDualWeapon(ItemStack stack, CallbackInfoReturnable<Boolean> callbackInfo) {
        if (AnnoyingVillagersEquipment.canUseDualWeapon(stack)) {
            callbackInfo.setReturnValue(true);
        }
    }

    @Inject(method = "getCompatGeneratedOffhandItem", at = @At("HEAD"), cancellable = true)
    private static void epicfightSmartNpc$getAvGeneratedOffhandItem(String mainHandItemId, ItemStack mainHandStack, Difficulty difficulty, boolean allowShield, CallbackInfoReturnable<Optional<String>> callbackInfo) {
        Optional<String> offhandItem = AnnoyingVillagersEquipment.getGeneratedOffhandItem(mainHandItemId, difficulty);
        if (offhandItem.isPresent()) {
            callbackInfo.setReturnValue(offhandItem);
        }
    }

    @Inject(method = "getCompatDualWeaponOffhandItem", at = @At("HEAD"), cancellable = true)
    private static void epicfightSmartNpc$getAvDualWeaponOffhandItem(String mainHandItemId, ItemStack mainHandStack, Difficulty difficulty, CallbackInfoReturnable<Optional<String>> callbackInfo) {
        Optional<String> offhandItem = AnnoyingVillagersEquipment.getDualWeaponOffhandItem(mainHandStack, difficulty);
        if (offhandItem.isPresent()) {
            callbackInfo.setReturnValue(offhandItem);
        }
    }
}
