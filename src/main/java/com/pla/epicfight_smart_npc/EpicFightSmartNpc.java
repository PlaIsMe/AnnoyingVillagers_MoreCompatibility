package com.pla.epicfight_smart_npc;

import com.pla.smart_npc.init.SmartNpcModEntities;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import yesman.epicfight.gameasset.Armatures;

@Mod(EpicFightSmartNpc.MODID)
public class EpicFightSmartNpc {
    public static final String MODID = "epicfight_smart_npc";

    public EpicFightSmartNpc(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(EpicFightSmartNpc::registerArmatures);
    }

    public static void registerArmatures() {
        Armatures.registerEntityTypeArmature(SmartNpcModEntities.PLAYER_NPC.get(), Armatures.BIPED);
    }
}
