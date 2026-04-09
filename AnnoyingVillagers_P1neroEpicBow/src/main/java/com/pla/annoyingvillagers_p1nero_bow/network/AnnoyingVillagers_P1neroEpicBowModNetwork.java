package com.pla.annoyingvillagers_p1nero_bow.network;

import com.pla.annoyingvillagers_p1nero_bow.AnnoyingVillagers_P1neroEpicBow;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public final class AnnoyingVillagers_P1neroEpicBowModNetwork {
    private static final String PROTOCOL = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            ResourceLocation.fromNamespaceAndPath(AnnoyingVillagers_P1neroEpicBow.MODID, "main"),
            () -> PROTOCOL,
            PROTOCOL::equals,
            PROTOCOL::equals
    );

    private AnnoyingVillagers_P1neroEpicBowModNetwork() {}

    public static void register() {
        int id = 0;

        CHANNEL.registerMessage(
                id++,
                SyncObsidianArrowCountS2CPacket.class,
                SyncObsidianArrowCountS2CPacket::encode,
                SyncObsidianArrowCountS2CPacket::decode,
                SyncObsidianArrowCountS2CPacket::handle
        );
    }
}