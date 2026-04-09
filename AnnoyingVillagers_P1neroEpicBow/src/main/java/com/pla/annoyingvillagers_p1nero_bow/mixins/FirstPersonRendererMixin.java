package com.pla.annoyingvillagers_p1nero_bow.mixins;

import com.pla.annoyingvillagers_p1nero_bow.client.layer.ObsidianArrowLayer;
import com.pla.annoyingvillagers_p1nero_bow.client.layer.PatchedObsidianArrowLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.client.renderer.FirstPersonRenderer;

@Mixin(FirstPersonRenderer.class)
public abstract class FirstPersonRendererMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void av$addObsidianArrowFirstPersonLayer(EntityRendererProvider.Context context, EntityType<?> entityType, CallbackInfo ci) {
        ((FirstPersonRenderer) (Object) this).addPatchedLayer(
                ObsidianArrowLayer.class,
                new PatchedObsidianArrowLayer<>(context)
        );
    }
}