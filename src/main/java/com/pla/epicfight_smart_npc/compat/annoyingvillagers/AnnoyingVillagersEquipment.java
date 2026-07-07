package com.pla.epicfight_smart_npc.compat.annoyingvillagers;

import com.pla.smart_npc.clazz.Difficulty;
import com.pla.smart_npc.util.EquipmentDataLoader;
import net.minecraft.world.item.ItemStack;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public final class AnnoyingVillagersEquipment {
    private static final String ANNOYING_VILLAGERS = "annoyingvillagers";
    private static final String EPIC_FIGHT = "epicfight";
    private static final String MINECRAFT = "minecraft";
    private static final String WOM = "wom";

    private static final List<String> LONGSWORD_OFFHAND_POOL = List.of(
            avItem("diamond_longsword"),
            avItem("golden_longsword"),
            avItem("iron_longsword"),
            epicFightItem("stone_longsword"),
            epicFightItem("iron_longsword"),
            epicFightItem("golden_longsword"),
            epicFightItem("diamond_longsword"),
            epicFightItem("netherite_longsword")
    );

    private static final List<String> FALCHION_OFFHAND_POOL = List.of(
            avItem("diamond_falchion"),
            avItem("diamond_great_falchion"),
            avItem("netherite_falchion"),
            epicFightItem("wooden_tachi"),
            epicFightItem("stone_tachi"),
            epicFightItem("iron_tachi"),
            epicFightItem("golden_tachi"),
            epicFightItem("diamond_tachi"),
            epicFightItem("netherite_tachi"),
            epicFightItem("wooden_falchion"),
            epicFightItem("stone_falchion"),
            epicFightItem("iron_falchion"),
            epicFightItem("golden_falchion"),
            epicFightItem("diamond_falchion"),
            epicFightItem("netherite_falchion")
    );

    private static final List<String> SWORD_OFFHAND_POOL = List.of(
            avItem("jade_sword"),
            avItem("red_diamond_sword"),
            minecraftItem("golden_sword"),
            minecraftItem("stone_sword"),
            minecraftItem("diamond_sword"),
            minecraftItem("netherite_sword"),
            minecraftItem("iron_sword")
    );

    private static final List<String> DAGGER_OFFHAND_POOL = List.of(
            avItem("knife"),
            avItem("netherite_knife"),
            avItem("diamond_knife"),
            epicFightItem("iron_dagger"),
            epicFightItem("stone_dagger"),
            epicFightItem("golden_dagger"),
            epicFightItem("diamond_dagger"),
            epicFightItem("netherite_dagger")
    );

    private static final Map<String, List<String>> BOUND_OFFHAND_WEAPONS = Map.ofEntries(
            Map.entry(avItem("exterminator_battleaxe"), List.of(
                    avItem("exterminator_battleaxe"),
                    avItem("exterminator_battleaxe_green")
            )),
            Map.entry(avItem("exterminator_battleaxe_green"), List.of(
                    avItem("exterminator_battleaxe"),
                    avItem("exterminator_battleaxe_green")
            )),
            Map.entry(avItem("diamond_mace"), List.of(
                    avItem("diamond_mace"),
                    avItem("golden_mace")
            )),
            Map.entry(avItem("golden_mace"), List.of(
                    avItem("diamond_mace"),
                    avItem("golden_mace")
            )),
            Map.entry(avItem("diamond_armblade"), List.of(
                    avItem("diamond_armblade")
            )),
            Map.entry(avItem("diamond_moon_blade"), List.of(
                    avItem("diamond_moon_blade"),
                    avItem("golden_moon_blade")
            )),
            Map.entry(avItem("golden_moon_blade"), List.of(
                    avItem("diamond_moon_blade"),
                    avItem("golden_moon_blade")
            )),
            Map.entry(avItem("twin_diamond_spear"), List.of(
                    avItem("twin_diamond_spear")
            )),
            Map.entry(avItem("diamond_longsword"), LONGSWORD_OFFHAND_POOL),
            Map.entry(avItem("golden_longsword"), LONGSWORD_OFFHAND_POOL),
            Map.entry(avItem("iron_longsword"), LONGSWORD_OFFHAND_POOL),
            Map.entry(avItem("diamond_falchion"), FALCHION_OFFHAND_POOL),
            Map.entry(avItem("diamond_great_falchion"), FALCHION_OFFHAND_POOL),
            Map.entry(avItem("netherite_falchion"), FALCHION_OFFHAND_POOL)
    );

    private static final Set<String> RANDOM_OFFHAND_WEAPON_BLACKLIST = Set.of(
            avItem("diamond_moon_blade"),
            avItem("golden_moon_blade"),
            avItem("diamond_armblade"),
            avItem("diamond_claw"),
            avItem("iron_cleaver"),
            avItem("diamond_sabre"),
            avItem("netherite_sabre"),
            avItem("blackscratcher"),
            avItem("diamond_warblade"),
            avItem("diamond_laevateinn")
    );

    private AnnoyingVillagersEquipment() {
    }

    public static boolean preventsShield(ItemStack stack) {
        CapabilityItem capabilityItem = EpicFightCapabilities.getItemStackCapability(stack);
        return capabilityItem instanceof WeaponCapability weaponCapability
                && isAnnoyingVillagersSpear(stack, weaponCapability);
    }

    public static boolean canUseShield(ItemStack stack) {
        if (!isAnnoyingVillagersItem(stack)) {
            return false;
        }

        CapabilityItem capabilityItem = EpicFightCapabilities.getItemStackCapability(stack);
        if (capabilityItem instanceof WeaponCapability weaponCapability) {
            if (isAnnoyingVillagersSpear(stack, weaponCapability)) {
                return false;
            }

            return weaponCapability.getWeaponCategory() == CapabilityItem.WeaponCategories.SWORD
                    || weaponCapability.getWeaponCategory() == CapabilityItem.WeaponCategories.LONGSWORD
                    || weaponCapability.getWeaponCategory() == CapabilityItem.WeaponCategories.SPEAR;
        }

        return false;
    }

    public static boolean preventsDualWeapon(ItemStack stack) {
        return isAnnoyingVillagersItem(stack) && isRandomOffhandWeaponBlacklisted(stack);
    }

    public static boolean canUseDualWeapon(ItemStack stack) {
        if (!isAnnoyingVillagersItem(stack) || isRandomOffhandWeaponBlacklisted(stack)) {
            return false;
        }

        CapabilityItem capabilityItem = EpicFightCapabilities.getItemStackCapability(stack);
        if (capabilityItem instanceof WeaponCapability weaponCapability) {
            return weaponCapability.getWeaponCategory() == CapabilityItem.WeaponCategories.SWORD
                    || weaponCapability.getWeaponCategory() == CapabilityItem.WeaponCategories.FIST
                    || weaponCapability.getWeaponCategory() == CapabilityItem.WeaponCategories.DAGGER;
        }

        return false;
    }

    public static Optional<String> getGeneratedOffhandItem(String mainHandItemId, Difficulty difficulty) {
        List<String> offhandItems = BOUND_OFFHAND_WEAPONS.get(mainHandItemId);
        if (offhandItems == null || offhandItems.isEmpty()) {
            return Optional.empty();
        }

        return getRandomExistingItem(filterGeneratedPool(offhandItems, difficulty));
    }

    public static Optional<String> getDualWeaponOffhandItem(ItemStack mainHandStack, Difficulty difficulty) {
        if (!isAnnoyingVillagersItem(mainHandStack) || isRandomOffhandWeaponBlacklisted(mainHandStack)) {
            return Optional.empty();
        }

        CapabilityItem capabilityItem = EpicFightCapabilities.getItemStackCapability(mainHandStack);
        if (capabilityItem instanceof WeaponCapability weaponCapability) {
            if (weaponCapability.getWeaponCategory() == CapabilityItem.WeaponCategories.SWORD) {
                return getRandomExistingItem(filterGeneratedPool(SWORD_OFFHAND_POOL, difficulty));
            }

            if (weaponCapability.getWeaponCategory() == CapabilityItem.WeaponCategories.DAGGER) {
                return getRandomExistingItem(filterGeneratedPool(DAGGER_OFFHAND_POOL, difficulty));
            }
        }

        return Optional.empty();
    }

    private static Optional<String> getRandomExistingItem(List<String> itemIds) {
        return EquipmentDataLoader.getRandomExistingItem(itemIds);
    }

    private static List<String> filterGeneratedPool(List<String> itemIds, Difficulty difficulty) {
        Difficulty currentDifficulty = difficulty == null ? Difficulty.EASY : difficulty;
        return itemIds.stream()
                .filter(itemId -> inferMinimumDifficulty(itemId).ordinal() <= currentDifficulty.ordinal())
                .toList();
    }

    private static Difficulty inferMinimumDifficulty(String itemId) {
        String path = itemId.contains(":") ? itemId.substring(itemId.indexOf(':') + 1) : itemId;
        if (path.contains("netherite")
                || path.contains("diamond")
                || path.contains("unlight")
                || path.contains("ruby")
                || path.contains("exterminator")
                || path.contains("blackscratcher")
                || path.contains("laevateinn")
                || path.contains("moon_blade")
                || path.contains("armblade")) {
            return Difficulty.HARD;
        }

        if (path.contains("iron")
                || path.contains("gold")
                || path.contains("chainmail")
                || path.contains("turtle")
                || path.contains("jade")
                || path.contains("red_axe")) {
            return Difficulty.MEDIUM;
        }

        return Difficulty.EASY;
    }

    private static boolean isRandomOffhandWeaponBlacklisted(ItemStack stack) {
        String itemId = EquipmentDataLoader.getItemId(stack);
        return RANDOM_OFFHAND_WEAPON_BLACKLIST.contains(itemId)
                || BOUND_OFFHAND_WEAPONS.containsKey(itemId);
    }

    private static boolean isAnnoyingVillagersSpear(ItemStack stack, WeaponCapability weaponCapability) {
        String itemId = EquipmentDataLoader.getItemId(stack);
        return isAnnoyingVillagersItem(itemId)
                && (getItemPath(itemId).contains("spear")
                || weaponCapability.getWeaponCategory() == CapabilityItem.WeaponCategories.SPEAR);
    }

    private static boolean isAnnoyingVillagersItem(ItemStack stack) {
        return isAnnoyingVillagersItem(EquipmentDataLoader.getItemId(stack));
    }

    private static boolean isAnnoyingVillagersItem(String itemId) {
        return itemId.startsWith(ANNOYING_VILLAGERS + ":");
    }

    private static String getItemPath(String itemId) {
        int separator = itemId.indexOf(':');
        return separator >= 0 ? itemId.substring(separator + 1) : itemId;
    }

    private static String avItem(String path) {
        return ANNOYING_VILLAGERS + ":" + path;
    }

    private static String epicFightItem(String path) {
        return EPIC_FIGHT + ":" + path;
    }

    private static String minecraftItem(String path) {
        return MINECRAFT + ":" + path;
    }

    private static String womItem(String path) {
        return WOM + ":" + path;
    }
}
