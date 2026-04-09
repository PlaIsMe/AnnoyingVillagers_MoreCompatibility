package com.pla.annoyingvillagers_p1nero_bow.item;

import com.pla.annoyingvillagers_p1nero_bow.entity.ObsidianArrowEntity;
import com.pla.annoyingvillagers_p1nero_bow.init.AnnoyingVillagers_P1neroEpicBowModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ObsidianArrowItem extends ArrowItem {
    public ObsidianArrowItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull AbstractArrow createArrow(@NotNull Level level, @NotNull ItemStack ammoStack, @NotNull LivingEntity shooter) {
        return new ObsidianArrowEntity(level, shooter);
    }

    @Override
    public boolean isInfinite(@NotNull ItemStack ammoStack, ItemStack bowStack, @NotNull Player player) {
        return bowStack.is(AnnoyingVillagers_P1neroEpicBowModItems.OBSIDIAN_BOW.get());
    }
}