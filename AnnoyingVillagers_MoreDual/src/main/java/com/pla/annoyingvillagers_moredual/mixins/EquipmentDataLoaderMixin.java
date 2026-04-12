package com.pla.annoyingvillagers_moredual.mixins;

import com.pla.annoyingvillagers.util.EquipmentDataLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

@Mixin(value = {EquipmentDataLoader.class}, remap = false)
public abstract class EquipmentDataLoaderMixin {
    @Inject(method = "addMoreDualCap", at = @At("HEAD"), cancellable = true)
    private static void addCdWeaponDualCap(WeaponCapability weaponCapability, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (weaponCapability.getWeaponCategory() == CapabilityItem.WeaponCategories.GREATSWORD
                || weaponCapability.getWeaponCategory() == CapabilityItem.WeaponCategories.AXE) callbackInfoReturnable.setReturnValue(true);
    }
}
