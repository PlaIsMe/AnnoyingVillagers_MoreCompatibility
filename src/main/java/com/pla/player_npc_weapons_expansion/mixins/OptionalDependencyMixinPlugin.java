package com.pla.player_npc_weapons_expansion.mixins;

import net.minecraftforge.fml.ModList;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class OptionalDependencyMixinPlugin implements IMixinConfigPlugin {
    private String mixinPackage;

    @Override
    public void onLoad(String mixinPackage) {
        this.mixinPackage = mixinPackage;
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        String requiredModId = getRequiredModId(mixinClassName);
        return requiredModId.isEmpty() || ModList.get().isLoaded(requiredModId);
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    private String getRequiredModId(String mixinClassName) {
        if (mixinPackage == null || !mixinClassName.startsWith(mixinPackage + ".")) {
            return "";
        }

        String relativeMixinName = mixinClassName.substring(mixinPackage.length() + 1);
        int separator = relativeMixinName.indexOf('.');
        return separator > 0 ? relativeMixinName.substring(0, separator) : "";
    }
}
