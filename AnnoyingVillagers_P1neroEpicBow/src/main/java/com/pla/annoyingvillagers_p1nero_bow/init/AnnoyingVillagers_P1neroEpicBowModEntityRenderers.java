package com.pla.annoyingvillagers_p1nero_bow.init;

import com.pla.annoyingvillagers_p1nero_bow.AnnoyingVillagers_P1neroEpicBow;
import com.pla.annoyingvillagers_p1nero_bow.client.model.ObsidianArrowModel;
import com.pla.annoyingvillagers_p1nero_bow.client.renderer.ObsidianArrowRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = AnnoyingVillagers_P1neroEpicBow.MODID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public class AnnoyingVillagers_P1neroEpicBowModEntityRenderers {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
                AnnoyingVillagers_P1neroEpicBowModEntities.OBSIDIAN_ARROW.get(),
                ObsidianArrowRenderer::new
        );
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(
                ObsidianArrowModel.LAYER_LOCATION,
                ObsidianArrowModel::createBodyLayer
        );
    }
}