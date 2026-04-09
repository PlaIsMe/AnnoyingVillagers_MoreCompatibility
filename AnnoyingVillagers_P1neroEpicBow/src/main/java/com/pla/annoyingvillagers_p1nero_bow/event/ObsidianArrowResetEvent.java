package com.pla.annoyingvillagers_p1nero_bow.event;

import com.pla.annoyingvillagers_p1nero_bow.AnnoyingVillagers_P1neroEpicBow;
import com.pla.annoyingvillagers_p1nero_bow.util.ObsidianArrowData;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AnnoyingVillagers_P1neroEpicBow.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ObsidianArrowResetEvent {
    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof LivingEntity living)) {
            return;
        }

        if (event.getLevel().isClientSide) {
            ObsidianArrowData.clear(living);
            return;
        }

        if (event.loadedFromDisk()) {
            ObsidianArrowData.clear(living);
            ObsidianArrowData.sync(living);
        }
    }

    @SubscribeEvent
    public static void onEntityLeaveLevel(EntityLeaveLevelEvent event) {
        if (!(event.getEntity() instanceof LivingEntity living)) {
            return;
        }
        ObsidianArrowData.clear(living);
    }
}