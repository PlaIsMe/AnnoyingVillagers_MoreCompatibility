package com.pla.annoyingvillagers_p1nero_bow.init;

import com.pla.annoyingvillagers.init.AnnoyingVillagersModCreativeTabs;
import com.pla.annoyingvillagers_p1nero_bow.AnnoyingVillagers_P1neroEpicBow;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = AnnoyingVillagers_P1neroEpicBow.MODID,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class AnnoyingVillagers_P1neroEpicBowModCreativeTabCompat {
    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(AnnoyingVillagersModCreativeTabs.AV_TAB.getKey())) {
            event.accept(AnnoyingVillagers_P1neroEpicBowModItems.OBSIDIAN_BOW);
        }
    }
}