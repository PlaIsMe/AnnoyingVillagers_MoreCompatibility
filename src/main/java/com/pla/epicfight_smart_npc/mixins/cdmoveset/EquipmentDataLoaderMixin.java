package com.pla.epicfight_smart_npc.mixins.cdmoveset;

import com.pla.smart_npc.clazz.Difficulty;
import com.pla.smart_npc.util.EquipmentDataLoader;
import net.corruptdog.cdm.world.CorruptWeaponCategories;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

import java.util.List;
import java.util.Optional;

@Mixin(value = EquipmentDataLoader.class, remap = false)
public abstract class EquipmentDataLoaderMixin {
    private static final List<String> EPICFIGHT_SMART_NPC$S_DAGGER_OFFHAND_POOL = List.of(
            "cdmoveset:s_wooden_dagger",
            "cdmoveset:s_stone_dagger",
            "cdmoveset:s_iron_dagger",
            "cdmoveset:s_golden_dagger",
            "cdmoveset:s_diamond_dagger",
            "cdmoveset:s_netherite_dagger",
            "epicfight:wooden_dagger",
            "epicfight:stone_dagger",
            "epicfight:iron_dagger",
            "epicfight:golden_dagger",
            "epicfight:diamond_dagger",
            "epicfight:netherite_dagger"
    );

    private static final List<String> EPICFIGHT_SMART_NPC$S_GREATSWORD_OFFHAND_POOL = List.of(
            "cdmoveset:s_wooden_greatsword",
            "cdmoveset:s_stone_greatsword",
            "cdmoveset:s_iron_greatsword",
            "cdmoveset:s_golden_greatsword",
            "cdmoveset:s_diamond_greatsword",
            "cdmoveset:s_netherite_greatsword",
            "epicfight:wooden_greatsword",
            "epicfight:stone_greatsword",
            "epicfight:iron_greatsword",
            "epicfight:golden_greatsword",
            "epicfight:diamond_greatsword",
            "epicfight:netherite_greatsword",
            "wom:wooden_greataxe",
            "wom:stone_greataxe",
            "wom:iron_greataxe",
            "wom:golden_greataxe",
            "wom:diamond_greataxe",
            "wom:netherite_greataxe"
    );

    private static final List<String> EPICFIGHT_SMART_NPC$S_SWORD_OFFHAND_POOL = List.of(
            "minecraft:wooden_sword",
            "minecraft:stone_sword",
            "minecraft:iron_sword",
            "minecraft:golden_sword",
            "minecraft:diamond_sword",
            "minecraft:netherite_sword",
            "cdmoveset:s_wooden_sword",
            "cdmoveset:s_stone_sword",
            "cdmoveset:s_iron_sword",
            "cdmoveset:s_golden_sword",
            "cdmoveset:s_diamond_sword",
            "cdmoveset:s_netherite_sword"
    );

    @Inject(method = "canUseCompatDualWeapon", at = @At("HEAD"), cancellable = true)
    private static void epicfightSmartNpc$canUseCdDualWeapon(ItemStack stack, CallbackInfoReturnable<Boolean> callbackInfo) {
        CapabilityItem capabilityItem = EpicFightCapabilities.getItemStackCapability(stack);

        if (capabilityItem instanceof WeaponCapability weaponCapability
                && (weaponCapability.getWeaponCategory() == CorruptWeaponCategories.S_DAGGER
                || weaponCapability.getWeaponCategory() == CorruptWeaponCategories.S_GREATSWORD
                || weaponCapability.getWeaponCategory() == CorruptWeaponCategories.S_SWORD)) {
            callbackInfo.setReturnValue(true);
        }
    }

    @Inject(method = "canUseCompatShield", at = @At("HEAD"), cancellable = true)
    private static void epicfightSmartNpc$canUseCdShield(ItemStack stack, CallbackInfoReturnable<Boolean> callbackInfo) {
        CapabilityItem capabilityItem = EpicFightCapabilities.getItemStackCapability(stack);

        if (capabilityItem instanceof WeaponCapability weaponCapability
                && (weaponCapability.getWeaponCategory() == CorruptWeaponCategories.S_SWORD
                || weaponCapability.getWeaponCategory() == CorruptWeaponCategories.S_LONGSWORD
                || weaponCapability.getWeaponCategory() == CorruptWeaponCategories.S_SPEAR)) {
            callbackInfo.setReturnValue(true);
        }
    }

    @Inject(method = "getCompatDualWeaponOffhandItem", at = @At("HEAD"), cancellable = true)
    private static void epicfightSmartNpc$getCdDualWeaponOffhandItem(String mainHandItemId, ItemStack mainHandStack, Difficulty difficulty, CallbackInfoReturnable<Optional<String>> callbackInfo) {
        CapabilityItem capabilityItem = EpicFightCapabilities.getItemStackCapability(mainHandStack);

        if (capabilityItem instanceof WeaponCapability weaponCapability) {
            if (weaponCapability.getWeaponCategory() == CorruptWeaponCategories.S_DAGGER) {
                callbackInfo.setReturnValue(EquipmentDataLoader.getRandomExistingItem(EPICFIGHT_SMART_NPC$S_DAGGER_OFFHAND_POOL));
            } else if (weaponCapability.getWeaponCategory() == CorruptWeaponCategories.S_GREATSWORD) {
                callbackInfo.setReturnValue(EquipmentDataLoader.getRandomExistingItem(EPICFIGHT_SMART_NPC$S_GREATSWORD_OFFHAND_POOL));
            } else if (weaponCapability.getWeaponCategory() == CorruptWeaponCategories.S_SWORD) {
                callbackInfo.setReturnValue(EquipmentDataLoader.getRandomExistingItem(EPICFIGHT_SMART_NPC$S_SWORD_OFFHAND_POOL));
            }
        }
    }
}
