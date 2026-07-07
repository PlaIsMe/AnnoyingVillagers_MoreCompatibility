package com.pla.epicfight_smart_npc.init;

import com.pla.epicfight_smart_npc.EpicFightSmartNpc;
import com.pla.epicfight_smart_npc.mobpatch.PlayerNpcPatch;
import com.pla.smart_npc.init.SmartNpcModEntities;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.forgeevent.EntityPatchRegistryEvent;

@Mod.EventBusSubscriber(modid = EpicFightSmartNpc.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class EpicFightSmartNpcPatches {
    private EpicFightSmartNpcPatches() {
    }

    @SubscribeEvent
    public static void setPatch(EntityPatchRegistryEvent event) {
        event.getTypeEntry().put(SmartNpcModEntities.PLAYER_NPC.get(), entity -> PlayerNpcPatch::new);
    }
}
