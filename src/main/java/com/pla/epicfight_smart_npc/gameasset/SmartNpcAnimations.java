package com.pla.epicfight_smart_npc.gameasset;

import com.pla.epicfight_smart_npc.EpicFightSmartNpc;
import com.pla.epicfight_smart_npc.compat.p1nero_bow.AnimsP1neroEpicBow;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.model.armature.HumanoidArmature;

@Mod.EventBusSubscriber(modid = EpicFightSmartNpc.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SmartNpcAnimations {
    public static AnimationManager.AnimationAccessor<StaticAnimation> IDLE_BREAK;
    public static AnimationManager.AnimationAccessor<StaticAnimation> SWING_HAND_LEFT;

    @SubscribeEvent
    public static void registerAnimations(AnimationManager.AnimationRegistryEvent event) {
        event.newBuilder(EpicFightSmartNpc.MODID, SmartNpcAnimations::build);
    }

    private static void build(AnimationManager.AnimationBuilder builder) {
        if (ModList.get().isLoaded("p1nero_bow")) {
            AnimsP1neroEpicBow.build(builder);
        }
        Armatures.ArmatureAccessor<HumanoidArmature> humanoidArmature = Armatures.BIPED;
        IDLE_BREAK = builder.nextAccessor("biped/living/idle_break",
                accessor -> new StaticAnimation(false, accessor, humanoidArmature));
        SWING_HAND_LEFT = builder.nextAccessor("biped/living/casting_one_hand_top",
                accessor -> new StaticAnimation(false, accessor, humanoidArmature)
                        .addState(EntityState.CAN_BASIC_ATTACK, false));
    }
}
