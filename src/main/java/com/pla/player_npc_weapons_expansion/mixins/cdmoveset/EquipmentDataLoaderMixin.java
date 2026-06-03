package com.pla.player_npc_weapons_expansion.mixins.cdmoveset;

import com.pla.annoyingvillagers.util.EquipmentDataLoader;
import net.corruptdog.cdm.world.CorruptWeaponCategories;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Mixin(value = {EquipmentDataLoader.class}, remap = false)
public abstract class EquipmentDataLoaderMixin {
    private static final Random PLAYER_NPC_WEAPONS_EXPANSION$RANDOM = new Random();

    private static final List<String> PLAYER_NPC_WEAPONS_EXPANSION$S_DAGGER_OFFHAND_POOL = List.of(
            "cdmoveset:s_wooden_dagger",
            "cdmoveset:s_stone_dagger",
            "cdmoveset:s_iron_dagger",
            "cdmoveset:s_golden_dagger",
            "cdmoveset:s_diamond_dagger",
            "epicfight:wooden_dagger",
            "epicfight:stone_dagger",
            "epicfight:iron_dagger",
            "epicfight:golden_dagger",
            "epicfight:diamond_dagger"
    );

    private static final List<String> PLAYER_NPC_WEAPONS_EXPANSION$S_GREATSWORD_OFFHAND_POOL = List.of(
            "cdmoveset:s_wooden_greatsword",
            "cdmoveset:s_stone_greatsword",
            "cdmoveset:s_iron_greatsword",
            "cdmoveset:s_golden_greatsword",
            "cdmoveset:s_diamond_greatsword",
            "epicfight:wooden_greatsword",
            "epicfight:stone_greatsword",
            "epicfight:iron_greatsword",
            "epicfight:golden_greatsword",
            "epicfight:diamond_greatsword",
            "wom:wooden_greataxe",
            "wom:stone_greataxe",
            "wom:iron_greataxe",
            "wom:golden_greataxe",
            "wom:diamond_greataxe"
    );

    private static final List<String> PLAYER_NPC_WEAPONS_EXPANSION$S_SWORD_OFFHAND_POOL = List.of(
            "minecraft:wooden_sword",
            "minecraft:stone_sword",
            "minecraft:iron_sword",
            "minecraft:golden_sword",
            "minecraft:diamond_sword",
            "cdmoveset:s_wooden_sword",
            "cdmoveset:s_stone_sword",
            "cdmoveset:s_iron_sword",
            "cdmoveset:s_golden_sword",
            "cdmoveset:s_diamond_sword"
    );

    @Inject(method = "addMoreDualCap", at = @At("HEAD"), cancellable = true)
    private static void addCdWeaponDualCap(ItemStack stack, WeaponCapability weaponCapability, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
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

    @Inject(method = "getLegacyRandomOffhandWeapon", at = @At("HEAD"), cancellable = true)
    private static void getCdRandomOffhandWeapon(ItemStack mainHandStack, CallbackInfoReturnable<Optional<String>> callbackInfoReturnable) {
        CapabilityItem capabilityItem = EpicFightCapabilities.getItemStackCapability(mainHandStack);

        if (capabilityItem instanceof WeaponCapability weaponCapability) {
            if (weaponCapability.getWeaponCategory() == CorruptWeaponCategories.S_DAGGER) {
                callbackInfoReturnable.setReturnValue(getRandomExistingItem(PLAYER_NPC_WEAPONS_EXPANSION$S_DAGGER_OFFHAND_POOL));
            } else if (weaponCapability.getWeaponCategory() == CorruptWeaponCategories.S_GREATSWORD) {
                callbackInfoReturnable.setReturnValue(getRandomExistingItem(PLAYER_NPC_WEAPONS_EXPANSION$S_GREATSWORD_OFFHAND_POOL));
            } else if (weaponCapability.getWeaponCategory() == CorruptWeaponCategories.S_SWORD) {
                callbackInfoReturnable.setReturnValue(getRandomExistingItem(PLAYER_NPC_WEAPONS_EXPANSION$S_SWORD_OFFHAND_POOL));
            }
        }
    }

    private static Optional<String> getRandomExistingItem(List<String> itemIds) {
        List<String> existingItemIds = itemIds.stream()
                .filter(EquipmentDataLoaderMixin::itemExists)
                .toList();

        if (existingItemIds.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(existingItemIds.get(PLAYER_NPC_WEAPONS_EXPANSION$RANDOM.nextInt(existingItemIds.size())));
    }

    private static boolean itemExists(String itemId) {
        String[] parts = itemId.split(":", 2);
        if (parts.length != 2) {
            return false;
        }

        return ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath(parts[0], parts[1])) != null;
    }
}
