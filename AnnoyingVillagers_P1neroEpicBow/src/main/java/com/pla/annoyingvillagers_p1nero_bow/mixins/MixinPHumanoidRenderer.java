package com.pla.annoyingvillagers_p1nero_bow.mixins;

import com.pla.annoyingvillagers_p1nero_bow.client.layer.ObsidianArrowLayer;
import com.pla.annoyingvillagers_p1nero_bow.client.layer.PatchedObsidianArrowLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.client.renderer.patched.entity.PHumanoidRenderer;

@Mixin(value = PHumanoidRenderer.class, remap = false)
public abstract class MixinPHumanoidRenderer {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addHerobrineEye(AssetAccessor<?> mesh,
                                 EntityRendererProvider.Context context,
                                 EntityType<?> type,
                                 CallbackInfo ci) {
        ((PHumanoidRenderer) (Object) this).addPatchedLayer(
                ObsidianArrowLayer.class,
                new PatchedObsidianArrowLayer<>(context)
        );
    }
}