package com.pla.epicfight_smart_npc.compat;

import com.p1nero.epicfightbow.item.EFBowItems;
import com.pla.epicfight_smart_npc.compat.p1nero_bow.PlayerNpcP1neroBow;
import com.pla.epicfight_smart_npc.compat.p1nero_bow.PlayerNpcP1neroMortisBow;
import net.minecraft.world.item.Items;
import net.shelmarow.combat_evolution.ai.CECombatBehaviors;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;

public class EpicFightBow {
    public static boolean isMortisBow(CapabilityItem mainHandCap) {
        return mainHandCap == EpicFightCapabilities.getItemStackCapability(EFBowItems.MORTIS.get().getDefaultInstance());
    }

    public static CECombatBehaviors.Builder<MobPatch<?>> overideBowMotionBuilderForPlayerNpc(CapabilityItem mainHandCap, Style style) {
        if (EpicFightBow.isMortisBow(mainHandCap)) {
            return PlayerNpcP1neroMortisBow.MORTIS_BOW;
        }
        if (mainHandCap == EpicFightCapabilities.getItemStackCapability(Items.BOW.getDefaultInstance())) {
            return PlayerNpcP1neroBow.BOW;
        }
        return null;
    }
}
