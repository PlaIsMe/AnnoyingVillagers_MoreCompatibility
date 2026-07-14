package com.pla.epicfight_smart_npc.mixins.client;

import com.pla.smart_npc.client.gui.SmartNpcInspectorOverlay;
import com.pla.smart_npc.entity.PlayerNpcEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.client.model.SkinnedMesh;
import yesman.epicfight.client.mesh.HumanoidMesh;
import yesman.epicfight.client.renderer.patched.entity.PatchedLivingEntityRenderer;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

@Mixin(value = PatchedLivingEntityRenderer.class, remap = false)
public abstract class PatchedLivingEntityRendererMixin {
    @Inject(method = "prepareModel", at = @At("TAIL"))
    private void epicfightSmartNpc$hideInspectatorHead(
            SkinnedMesh mesh,
            LivingEntity entity,
            LivingEntityPatch<?> entityPatch,
            LivingEntityRenderer<?, ? extends EntityModel<?>> renderer,
            CallbackInfo ci) {
        if (entity instanceof PlayerNpcEntity
                && mesh instanceof HumanoidMesh humanoidMesh
                && SmartNpcInspectorOverlay.shouldRenderInspectatorCameraTargetBody(entity)) {
            if (humanoidMesh.head != null) {
                humanoidMesh.head.setHidden(true);
            }
            if (humanoidMesh.hat != null) {
                humanoidMesh.hat.setHidden(true);
            }
            if (humanoidMesh.torso != null) {
                humanoidMesh.torso.setHidden(true);
            }
            if (humanoidMesh.jacket != null) {
                humanoidMesh.jacket.setHidden(true);
            }
        }
    }
}
