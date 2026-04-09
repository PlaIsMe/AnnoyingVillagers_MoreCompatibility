package com.pla.annoyingvillagers_p1nero_bow.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.pla.annoyingvillagers_p1nero_bow.entity.ObsidianArrowEntity;
import com.pla.annoyingvillagers_p1nero_bow.util.ObsidianArrowData;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.StuckInBodyLayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class ObsidianArrowLayer<T extends LivingEntity, M extends PlayerModel<T>> extends StuckInBodyLayer<T, M> {
    private final EntityRenderDispatcher dispatcher;
    private static final double PUSH_OUT = 0.18D;

    public ObsidianArrowLayer(EntityRendererProvider.Context context, LivingEntityRenderer<T, M> renderer) {
        super(renderer);
        this.dispatcher = context.getEntityRenderDispatcher();
    }

    @Override
    protected int numStuck(@NotNull T entity) {
        return ObsidianArrowData.getCount(entity);
    }

    @Override
    protected void renderStuckItem(@NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, Entity entity, float x, float y, float z, float partialTick) {
        float horizontal = Mth.sqrt(x * x + z * z);
        float length = Mth.sqrt(x * x + y * y + z * z);

        if (length > 1.0E-4F) {
            float nx = x / length;
            float ny = y / length;
            float nz = z / length;

            // Push the arrow outward a bit so it is not buried inside the model
            poseStack.translate(nx * PUSH_OUT, ny * PUSH_OUT, nz * PUSH_OUT);
        }

        ObsidianArrowEntity arrow = new ObsidianArrowEntity(entity.level(), entity.getX(), entity.getY(), entity.getZ());
        arrow.setYRot((float)(Math.atan2(x, z) * (180F / (float)Math.PI)));
        arrow.setXRot((float)(Math.atan2(y, horizontal) * (180F / (float)Math.PI)));
        arrow.yRotO = arrow.getYRot();
        arrow.xRotO = arrow.getXRot();

        this.dispatcher.render(arrow, 0.0D, 0.0D, 0.0D, 0.0F, partialTick, poseStack, buffer, packedLight);
    }
}