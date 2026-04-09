package com.pla.annoyingvillagers_p1nero_bow.init;

import com.pla.annoyingvillagers_p1nero_bow.AnnoyingVillagers_P1neroEpicBow;
import com.pla.annoyingvillagers_p1nero_bow.entity.ObsidianArrowEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AnnoyingVillagers_P1neroEpicBowModEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AnnoyingVillagers_P1neroEpicBow.MODID);

    public static final RegistryObject<EntityType<ObsidianArrowEntity>> OBSIDIAN_ARROW =
            register(
                    EntityType.Builder.<ObsidianArrowEntity>of(ObsidianArrowEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .clientTrackingRange(4)
                            .updateInterval(20)
            );

    private static <T extends Entity> RegistryObject<EntityType<T>> register(EntityType.Builder<T> builder) {
        return REGISTRY.register("obsidian_arrow",
                () -> builder.build(ResourceLocation.fromNamespaceAndPath(AnnoyingVillagers_P1neroEpicBow.MODID, "obsidian_arrow").toString()));
    }
}