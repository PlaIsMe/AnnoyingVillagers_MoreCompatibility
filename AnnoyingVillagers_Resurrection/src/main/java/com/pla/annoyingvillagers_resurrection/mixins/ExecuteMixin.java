package com.pla.annoyingvillagers_resurrection.mixins;

import net.corruptdog.cdm.skill.identity.Execute;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = {Execute.class}, remap = false)
public class ExecuteMixin {
    @Inject(method = {"onRightClickEntity"}, at = {@At("HEAD")}, cancellable = true)
    private static void unregisterEvent(PlayerInteractEvent.EntityInteract event, CallbackInfo ci) {
        ci.cancel();
    }
}