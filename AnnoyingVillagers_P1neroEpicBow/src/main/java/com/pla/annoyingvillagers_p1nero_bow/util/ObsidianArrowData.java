package com.pla.annoyingvillagers_p1nero_bow.util;

import com.pla.annoyingvillagers_p1nero_bow.network.AnnoyingVillagers_P1neroEpicBowModNetwork;
import com.pla.annoyingvillagers_p1nero_bow.network.SyncObsidianArrowCountS2CPacket;
import net.minecraft.world.entity.LivingEntity;

public final class ObsidianArrowData {
    public static final String COUNT_KEY = "annoyingvillagers:obsidian_arrow_count";
    public static final String REMOVE_TIME_KEY = "annoyingvillagers:obsidian_arrow_remove_time";

    private ObsidianArrowData() {}

    public static int getCount(LivingEntity entity) {
        return entity.getPersistentData().getInt(COUNT_KEY);
    }

    public static void setCount(LivingEntity entity, int count) {
        if (count <= 0) {
            entity.getPersistentData().remove(COUNT_KEY);
        } else {
            entity.getPersistentData().putInt(COUNT_KEY, count);
        }
    }

    public static int getRemoveTime(LivingEntity entity) {
        return entity.getPersistentData().getInt(REMOVE_TIME_KEY);
    }

    public static void setRemoveTime(LivingEntity entity, int time) {
        if (time <= 0) {
            entity.getPersistentData().remove(REMOVE_TIME_KEY);
        } else {
            entity.getPersistentData().putInt(REMOVE_TIME_KEY, time);
        }
    }

    public static void addCount(LivingEntity entity, int amount) {
        setCount(entity, getCount(entity) + amount);
    }

    public static void clear(LivingEntity entity) {
        setCount(entity, 0);
        setRemoveTime(entity, 0);
    }

    public static void applyClient(LivingEntity entity, int count) {
        setCount(entity, count);
    }

    public static void sync(LivingEntity entity) {
        if (entity.level().isClientSide) {
            return;
        }

        int count = getCount(entity);
        AnnoyingVillagers_P1neroEpicBowModNetwork.CHANNEL.send(
                net.minecraftforge.network.PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity),
                new SyncObsidianArrowCountS2CPacket(entity.getId(), count)
        );
    }
}