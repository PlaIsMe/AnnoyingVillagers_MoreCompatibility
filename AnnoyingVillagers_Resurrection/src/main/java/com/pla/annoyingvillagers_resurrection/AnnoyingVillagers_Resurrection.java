package com.pla.annoyingvillagers_resurrection;
import com.mojang.logging.LogUtils;
import com.pla.annoyingvillagers_resurrection.compat.EpicFightNightFall;
import com.pla.annoyingvillagers_resurrection.compat.WeaponsOfMiracles;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import yesman.epicfight.compat.ICompatModule;

@Mod(AnnoyingVillagers_Resurrection.MODID)
public class AnnoyingVillagers_Resurrection
{
    public static final String MODID = "annoyingvillagers_resurrection";
    private static final Logger LOGGER = LogUtils.getLogger();

    public AnnoyingVillagers_Resurrection(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);

        if (ModList.get().isLoaded("wom")) {
            ICompatModule.loadCompatModule(context, WeaponsOfMiracles.class);
            modEventBus.addListener(WeaponsOfMiracles::buildSkillEvent);
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modEventBus.addListener(WeaponsOfMiracles::regIcon));
        }
        if (ModList.get().isLoaded("efn")) {
            ICompatModule.loadCompatModule(context, EpicFightNightFall.class);
            modEventBus.addListener(EpicFightNightFall::forceGuard);
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modEventBus.addListener(EpicFightNightFall::onIconCreate));
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}