package com.pla.annoyingvillagers_p1nero_bow.event;
import com.pla.annoyingvillagers.init.AnnoyingVillagersModEntities;
import com.pla.annoyingvillagers_p1nero_bow.AnnoyingVillagers_P1neroEpicBow;
import com.pla.annoyingvillagers_p1nero_bow.client.layer.ObsidianArrowLayer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import se.gory_moon.player_mobs.client.render.PlayerMobRenderer;

@Mod.EventBusSubscriber(modid = AnnoyingVillagers_P1neroEpicBow.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ObsidianArrowLayerEvent {
    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        PlayerRenderer defaultRenderer = event.getSkin("default");
        if (defaultRenderer != null) {
            defaultRenderer.addLayer(new ObsidianArrowLayer<>(event.getContext(), defaultRenderer));
        }

        PlayerRenderer slimRenderer = event.getSkin("slim");
        if (slimRenderer != null) {
            slimRenderer.addLayer(new ObsidianArrowLayer<>(event.getContext(), slimRenderer));
        }

        addLayerToHumanoidMobRenderer(AnnoyingVillagersModEntities.PLAYER_NPC.get(), event);
        addLayerToHumanoidMobRenderer(AnnoyingVillagersModEntities.ANGRY_STEVE.get(), event);
        addLayerToHumanoidMobRenderer(AnnoyingVillagersModEntities.ALEX.get(), event);
        addLayerToHumanoidMobRenderer(AnnoyingVillagersModEntities.CHRIS.get(), event);
        addLayerToHumanoidMobRenderer(AnnoyingVillagersModEntities.VILLAGER_SCOUT.get(), event);
        addLayerToHumanoidMobRenderer(AnnoyingVillagersModEntities.VILLAGER_SCOUT_CAPTAIN.get(), event);
        addLayerToHumanoidMobRenderer(AnnoyingVillagersModEntities.RED_VILLAGER_GENERAL.get(), event);
        addLayerToHumanoidMobRenderer(AnnoyingVillagersModEntities.BLUE_VILLAGER_GENERAL.get(), event);
        addLayerToHumanoidMobRenderer(AnnoyingVillagersModEntities.GREEN_VILLAGER_GENERAL.get(), event);
        addLayerToHumanoidMobRenderer(AnnoyingVillagersModEntities.PURPLE_VILLAGER_GENERAL.get(), event);
    }

    private static void addLayerToHumanoidMobRenderer(EntityType entityType, EntityRenderersEvent.AddLayers event) {
        EntityRenderer<?> entityRenderer = event.getRenderer(entityType);
        if (entityRenderer instanceof HumanoidMobRenderer humanoidMobRenderer) {
            humanoidMobRenderer.addLayer(new ObsidianArrowLayer(event.getContext(), humanoidMobRenderer));
        }
    }
}