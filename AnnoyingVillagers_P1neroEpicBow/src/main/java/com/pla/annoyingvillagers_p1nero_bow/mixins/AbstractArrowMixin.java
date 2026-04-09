package com.pla.annoyingvillagers_p1nero_bow.mixins;

import com.pla.annoyingvillagers_p1nero_bow.entity.ObsidianArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin {
    @Redirect(
            method = "onHitEntity(Lnet/minecraft/world/phys/EntityHitResult;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;setArrowCount(I)V"
            )
    )
    private void skipVanillaArrowCountForObsidian(LivingEntity target, int newCount) {
        AbstractArrow self = (AbstractArrow) (Object) this;

        if (self instanceof ObsidianArrowEntity) {
            return;
        }

        target.setArrowCount(newCount);
    }
}