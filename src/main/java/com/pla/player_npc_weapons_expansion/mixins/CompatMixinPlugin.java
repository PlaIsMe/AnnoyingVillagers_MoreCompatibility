package com.pla.player_npc_weapons_expansion.mixins;

import java.util.List;
import java.util.Set;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.LoadingModList;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public final class CompatMixinPlugin implements IMixinConfigPlugin {
    private static final String CD_MOVESET_COMPAT_PREFIX = "com.pla.player_npc_weapons_expansion.mixins.cdmoveset.";
    private static final String REFM_COMPAT_PREFIX = "com.pla.player_npc_weapons_expansion.mixins.refm.";

    private static boolean isModLoadedEarly(String modId) {
        LoadingModList list = FMLLoader.getLoadingModList();
        return list != null && list.getModFileById(modId) != null;
    }

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.startsWith(CD_MOVESET_COMPAT_PREFIX)) {
            return isModLoadedEarly("cdmoveset");
        }
        if (mixinClassName.startsWith(REFM_COMPAT_PREFIX)) {
            return isModLoadedEarly("refm");
        }
        return true;
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
}
