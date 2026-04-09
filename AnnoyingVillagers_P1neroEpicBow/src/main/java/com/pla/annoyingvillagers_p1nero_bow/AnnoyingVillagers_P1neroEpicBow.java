package com.pla.annoyingvillagers_p1nero_bow;
import com.mojang.logging.LogUtils;
import com.pla.annoyingvillagers_p1nero_bow.init.AnnoyingVillagers_P1neroEpicBowModEntities;
import com.pla.annoyingvillagers_p1nero_bow.init.AnnoyingVillagers_P1neroEpicBowModItems;
import com.pla.annoyingvillagers_p1nero_bow.network.AnnoyingVillagers_P1neroEpicBowModNetwork;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(AnnoyingVillagers_P1neroEpicBow.MODID)
public class AnnoyingVillagers_P1neroEpicBow
{
    public static final String MODID = "annoyingvillagers_p1nero_bow";
    private static final Logger LOGGER = LogUtils.getLogger();

    public AnnoyingVillagers_P1neroEpicBow(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        AnnoyingVillagers_P1neroEpicBowModEntities.REGISTRY.register(modEventBus);
        AnnoyingVillagers_P1neroEpicBowModItems.REGISTRY.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class initer {
        @SubscribeEvent
        public static void init(FMLCommonSetupEvent fmlCommonSetupEvent) {
            AnnoyingVillagers_P1neroEpicBowModNetwork.register();
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
            event.enqueueWork(() -> {
                ItemProperties.register(
                        AnnoyingVillagers_P1neroEpicBowModItems.OBSIDIAN_BOW.get(),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "pulling"),
                        (stack, level, entity, seed) -> {
                            if (stack.hasTag() && stack.getTag() != null && stack.getTag().contains("Pulling")) {
                                return 1.0F;
                            }
                            if (entity == null) {
                                return 0.0F;
                            }
                            return entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
                        }
                );
                ItemProperties.register(
                        AnnoyingVillagers_P1neroEpicBowModItems.OBSIDIAN_BOW.get(),
                        ResourceLocation.fromNamespaceAndPath("minecraft", "pull"),
                        (stack, level, entity, seed) -> {
                            if (stack.hasTag() && stack.getTag() != null && stack.getTag().contains("Pulling")) {
                                return stack.getTag().getFloat("Pulling");
                            }
                            if (entity == null) {
                                return 0.0F;
                            }
                            if (entity.getUseItem() != stack) {
                                return 0.0F;
                            }
                            float used = (float)(stack.getUseDuration() - entity.getUseItemRemainingTicks());
                            return used / 20.0F;
                        }
                );
            });
        }
    }
}