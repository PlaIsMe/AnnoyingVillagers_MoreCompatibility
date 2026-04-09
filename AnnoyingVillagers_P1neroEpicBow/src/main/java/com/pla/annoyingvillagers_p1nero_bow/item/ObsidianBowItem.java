package com.pla.annoyingvillagers_p1nero_bow.item;

import com.pla.annoyingvillagers.init.AnnoyingVillagersModSounds;
import com.pla.annoyingvillagers_p1nero_bow.entity.ObsidianArrowEntity;
import com.pla.annoyingvillagers_p1nero_bow.init.AnnoyingVillagers_P1neroEpicBowModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class ObsidianBowItem extends BowItem {
    public ObsidianBowItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return stack -> stack.is(AnnoyingVillagers_P1neroEpicBowModItems.OBSIDIAN_ARROW.get());
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack bowStack = player.getItemInHand(hand);

        InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(
                bowStack, level, player, hand, true
        );
        if (ret != null) {
            return ret;
        }

        player.startUsingItem(hand);
        return InteractionResultHolder.consume(bowStack);
    }

    @Override
    public void releaseUsing(@NotNull ItemStack bowStack, @NotNull Level level, @NotNull LivingEntity livingEntity, int timeLeft) {
        if (!(livingEntity instanceof Player player)) {
            return;
        }

        int charge = this.getUseDuration(bowStack) - timeLeft;
        charge = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(bowStack, level, player, charge, true);
        if (charge < 0) {
            return;
        }

        float power = BowItem.getPowerForTime(charge);
        if ((double) power < 0.1D) {
            return;
        }

        if (!level.isClientSide) {
            ItemStack fakeAmmo = new ItemStack(AnnoyingVillagers_P1neroEpicBowModItems.OBSIDIAN_ARROW.get());
            ArrowItem arrowItem = (ArrowItem) fakeAmmo.getItem();

            AbstractArrow abstractArrow = arrowItem.createArrow(level, fakeAmmo, player);
            abstractArrow = this.customArrow(abstractArrow);
            abstractArrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, power * 3.0F, 1.0F);

            if (power == 1.0F) {
                abstractArrow.setCritArrow(true);
            }

            int powerLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, bowStack);
            if (powerLevel > 0) {
                abstractArrow.setBaseDamage(abstractArrow.getBaseDamage() + (double) powerLevel * 0.5D + 0.5D);
            }

            int punchLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, bowStack);
            if (punchLevel > 0) {
                abstractArrow.setKnockback(punchLevel);
            }

            if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, bowStack) > 0) {
                abstractArrow.setSecondsOnFire(100);
            }

            bowStack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(player.getUsedItemHand()));
            abstractArrow.pickup = AbstractArrow.Pickup.DISALLOWED;

            level.addFreshEntity(abstractArrow);
        }

        level.playSound(
                null,
                player.getX(), player.getY(), player.getZ(),
                AnnoyingVillagersModSounds.METAL_HIT.get(),
                SoundSource.PLAYERS,
                1.0F,
                1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + power * 0.5F
        );

        player.awardStat(Stats.ITEM_USED.get(this));
    }

    @Override
    public @NotNull AbstractArrow customArrow(@NotNull AbstractArrow arrow) {
        if (arrow instanceof ObsidianArrowEntity obsidianArrow) {
            return obsidianArrow;
        }
        return arrow;
    }
}