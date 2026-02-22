package com.pla.annoyingvillagers_p1nero_bow.mixins;

import com.p1nero.epicfightbow.item.EFBowItems;
import com.pla.annoyingvillagers.clazz.AVNpc;
import com.pla.annoyingvillagers.entity.AlexEntity;
import com.pla.annoyingvillagers.entity.RedVillagerGeneralEntity;
import com.pla.annoyingvillagers.entity.VillagerScoutCaptainEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = {AVNpc.class}, remap = false)
public abstract class AVNpcMixin {
    @Inject(method = "getBowItem", at = @At("HEAD"), cancellable = true)
    private void addMortisBow(CallbackInfoReturnable<ItemStack> callbackInfoReturnable) {
        AVNpc self = (AVNpc) (Object) this;
        if (self instanceof AlexEntity || self instanceof VillagerScoutCaptainEntity || self instanceof RedVillagerGeneralEntity) {
            callbackInfoReturnable.setReturnValue(new ItemStack(EFBowItems.MORTIS.get()));
        }
    }
}
