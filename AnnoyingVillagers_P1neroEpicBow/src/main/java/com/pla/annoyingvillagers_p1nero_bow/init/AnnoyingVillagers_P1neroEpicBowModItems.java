package com.pla.annoyingvillagers_p1nero_bow.init;

import com.pla.annoyingvillagers_p1nero_bow.AnnoyingVillagers_P1neroEpicBow;
import com.pla.annoyingvillagers_p1nero_bow.item.ObsidianArrowItem;
import com.pla.annoyingvillagers_p1nero_bow.item.ObsidianBowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AnnoyingVillagers_P1neroEpicBowModItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, AnnoyingVillagers_P1neroEpicBow.MODID);
    public static final RegistryObject<Item> OBSIDIAN_BOW = AnnoyingVillagers_P1neroEpicBowModItems.REGISTRY.register("obsidian_bow", () -> new ObsidianBowItem(new Item.Properties().rarity(Rarity.EPIC).durability(2025).fireResistant()));
    public static final RegistryObject<Item> OBSIDIAN_ARROW =
            REGISTRY.register("obsidian_arrow", () -> new ObsidianArrowItem(new Item.Properties()));
}
