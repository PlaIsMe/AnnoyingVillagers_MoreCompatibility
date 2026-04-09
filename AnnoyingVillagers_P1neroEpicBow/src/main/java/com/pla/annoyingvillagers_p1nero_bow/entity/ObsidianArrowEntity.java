package com.pla.annoyingvillagers_p1nero_bow.entity;

import com.pla.annoyingvillagers.init.AnnoyingVillagersModParticleTypes;
import com.pla.annoyingvillagers.init.AnnoyingVillagersModSounds;
import com.pla.annoyingvillagers_p1nero_bow.init.AnnoyingVillagers_P1neroEpicBowModEntities;
import com.pla.annoyingvillagers_p1nero_bow.init.AnnoyingVillagers_P1neroEpicBowModItems;
import com.pla.annoyingvillagers_p1nero_bow.util.ObsidianArrowData;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class ObsidianArrowEntity extends Arrow {
    public ObsidianArrowEntity(EntityType<? extends ObsidianArrowEntity> type, Level level) {
        super(type, level);
    }

    public ObsidianArrowEntity(Level level, LivingEntity shooter) {
        this(AnnoyingVillagers_P1neroEpicBowModEntities.OBSIDIAN_ARROW.get(), level);
        this.setOwner(shooter);
        this.setPos(shooter.getX(), shooter.getEyeY() - 0.1D, shooter.getZ());
    }

    public ObsidianArrowEntity(Level level, double x, double y, double z) {
        this(AnnoyingVillagers_P1neroEpicBowModEntities.OBSIDIAN_ARROW.get(), level);
        this.setPos(x, y, z);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide && !this.inGround) {
            Vec3 motion = this.getDeltaMovement();

            for (int i = 0; i < 2; i++) {
                double back = i * 0.25D;

                this.level().addParticle(
                        AnnoyingVillagersModParticleTypes.PE.get(),
                        this.getX() - motion.x * back,
                        this.getY() + 0.05D - motion.y * back,
                        this.getZ() - motion.z * back,
                        0.0D, 0.0D, 0.0D
                );
            }
        }
    }

    @Override
    protected @NotNull SoundEvent getDefaultHitGroundSoundEvent() {
        return AnnoyingVillagersModSounds.METAL_HIT.get();
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult hitResult) {
        super.onHitBlock(hitResult);

        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                    ParticleTypes.REVERSE_PORTAL,
                    this.getX(), this.getY() + 0.05D, this.getZ(),
                    8,
                    0.08D, 0.08D, 0.08D,
                    0.02D
            );
        }
    }

    @Override
    protected void doPostHurtEffects(@NotNull LivingEntity target) {
        super.doPostHurtEffects(target);

        if (!this.level().isClientSide && this.getPierceLevel() <= 0) {
            ObsidianArrowData.addCount(target, 1);
            ObsidianArrowData.sync(target);
        }
    }

    @Override
    @NotNull
    public ItemStack getPickupItem() {
        return new ItemStack(AnnoyingVillagers_P1neroEpicBowModItems.OBSIDIAN_ARROW.get());
    }
}