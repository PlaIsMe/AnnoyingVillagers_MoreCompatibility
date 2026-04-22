package com.pla.annoyingvillagers_resurrection.mixins;

import com.pla.annoyingvillagers.util.EquipmentDataLoader;
import net.corruptdog.cdm.world.CorruptWeaponCategories;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

@Mixin(value = {EquipmentDataLoader.class}, remap = false)
public abstract class EquipmentDataLoaderMixin {
    @Inject(method = "addMoreDualCap", at = @At("HEAD"), cancellable = true)
    private static void addCdWeaponDualCap(WeaponCapability weaponCapability, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (weaponCapability.getWeaponCategory() == CorruptWeaponCategories.S_DAGGER
                || weaponCapability.getWeaponCategory() == CorruptWeaponCategories.S_GREATSWORD
                || weaponCapability.getWeaponCategory() == CorruptWeaponCategories.S_SWORD) callbackInfoReturnable.setReturnValue(true);
    }

    @Inject(method = "addMoreShieldCap", at = @At("HEAD"), cancellable = true)
    private static void addCdWeaponShield(WeaponCapability weaponCapability, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (weaponCapability.getWeaponCategory() == CorruptWeaponCategories.S_SWORD
                || weaponCapability.getWeaponCategory() == CorruptWeaponCategories.S_LONGSWORD
                || weaponCapability.getWeaponCategory() == CorruptWeaponCategories.S_SPEAR) callbackInfoReturnable.setReturnValue(true);
    }
}
