package com.pla.annoyingvillagers_p1nero_bow.network;

import com.pla.annoyingvillagers_p1nero_bow.util.ObsidianArrowData;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SyncObsidianArrowCountS2CPacket {
    private final int entityId;
    private final int count;

    public SyncObsidianArrowCountS2CPacket(int entityId, int count) {
        this.entityId = entityId;
        this.count = count;
    }

    public static void encode(SyncObsidianArrowCountS2CPacket msg, FriendlyByteBuf buf) {
        buf.writeVarInt(msg.entityId);
        buf.writeVarInt(msg.count);
    }

    public static SyncObsidianArrowCountS2CPacket decode(FriendlyByteBuf buf) {
        return new SyncObsidianArrowCountS2CPacket(buf.readVarInt(), buf.readVarInt());
    }

    public static void handle(SyncObsidianArrowCountS2CPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            if (mc.level == null) {
                return;
            }

            Entity entity = mc.level.getEntity(msg.entityId);
            if (entity instanceof LivingEntity livingEntity) {
                ObsidianArrowData.applyClient(livingEntity, msg.count);
            }
        });

        ctx.get().setPacketHandled(true);
    }
}