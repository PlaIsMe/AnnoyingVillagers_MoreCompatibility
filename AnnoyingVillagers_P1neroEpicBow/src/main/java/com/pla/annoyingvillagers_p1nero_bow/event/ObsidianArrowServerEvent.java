package com.pla.annoyingvillagers_p1nero_bow.event;

import com.pla.annoyingvillagers_p1nero_bow.AnnoyingVillagers_P1neroEpicBow;
import com.pla.annoyingvillagers_p1nero_bow.network.AnnoyingVillagers_P1neroEpicBowModNetwork;
import com.pla.annoyingvillagers_p1nero_bow.network.SyncObsidianArrowCountS2CPacket;
import com.pla.annoyingvillagers_p1nero_bow.util.ObsidianArrowData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = AnnoyingVillagers_P1neroEpicBow.MODID)
public final class ObsidianArrowServerEvent {
    private ObsidianArrowServerEvent() {}

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity living = event.getEntity();

        if (living.level().isClientSide) {
            return;
        }

        int count = ObsidianArrowData.getCount(living);
        if (count <= 0) {
            if (ObsidianArrowData.getRemoveTime(living) != 0) {
                ObsidianArrowData.setRemoveTime(living, 0);
            }
            return;
        }

        int removeTime = ObsidianArrowData.getRemoveTime(living);

        if (removeTime <= 0) {
            removeTime = Math.max(1, 20 * (30 - count));
        }

        removeTime--;

        if (removeTime <= 0) {
            ObsidianArrowData.setCount(living, count - 1);
            ObsidianArrowData.setRemoveTime(living, 0);
            ObsidianArrowData.sync(living);
        } else {
            ObsidianArrowData.setRemoveTime(living, removeTime);
        }
    }

    @SubscribeEvent
    public static void onStartTracking(PlayerEvent.StartTracking event) {
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) {
            return;
        }

        if (event.getTarget() instanceof LivingEntity living) {
            AnnoyingVillagers_P1neroEpicBowModNetwork.CHANNEL.send(
                    PacketDistributor.PLAYER.with(() -> serverPlayer),
                    new SyncObsidianArrowCountS2CPacket(
                            living.getId(),
                            ObsidianArrowData.getCount(living)
                    )
            );
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer serverPlayer)) {
            return;
        }

        AnnoyingVillagers_P1neroEpicBowModNetwork.CHANNEL.send(
                PacketDistributor.PLAYER.with(() -> serverPlayer),
                new SyncObsidianArrowCountS2CPacket(
                        serverPlayer.getId(),
                        ObsidianArrowData.getCount(serverPlayer)
                )
        );
    }
}