package com.pla.epicfight_smart_npc.compat;

import reascer.wom.gameasset.WOMAnimations;
import reascer.wom.gameasset.animations.weapons.*;

import java.util.HashSet;
import java.util.Set;

public class Wom {
    private static final Set<String> DANGEROUS_ANIMATIONS = new HashSet<>();

    static {
        DANGEROUS_ANIMATIONS.addAll(Set.of(
                AnimsAgony.AGONY_SKY_DIVE_X.get().getRegistryName().toString(),
                AnimsAgony.AGONY_SKY_DIVE.get().getRegistryName().toString(),
                WOMAnimations.TORMENT_CHARGED_ATTACK_2.get().getRegistryName().toString(),
                WOMAnimations.TORMENT_CHARGED_ATTACK_3.get().getRegistryName().toString(),
                AnimsRuine.RUINE_PLUNDER.get().getRegistryName().toString(),
                WOMAnimations.ANTITHEUS_LAPSE.get().getRegistryName().toString(),
                WOMAnimations.ANTITHEUS_ASCENSION.get().getRegistryName().toString(),
                WOMAnimations.ANTITHEUS_ASCENDED_BLACKHOLE.get().getRegistryName().toString(),
                WOMAnimations.TORMENT_BERSERK_CONVERT.get().getRegistryName().toString(),
                AnimsSatsujin.SATSUJIN_GESSHOKU.get().getRegistryName().toString(),
                AnimsHerrscher.GESETZ_AUTO_3.get().getRegistryName().toString(),
                AnimsHerrscher.GESETZ_SPRENGKOPF.get().getRegistryName().toString(),
                AnimsHerrscher.GESETZ_WIDERSTAND.get().getRegistryName().toString(),
                AnimsMoonless.MOONLESS_LUNAR_ECHO.get().getRegistryName().toString(),
                AnimsMoonless.MOONLESS_LUNAR_ECLIPSE.get().getRegistryName().toString(),
                AnimsMoonless.MOONLESS_LUNAR_FULLMOON.get().getRegistryName().toString(),
                AnimsSolar.SOLAR_BRASERO.get().getRegistryName().toString(),
                AnimsSolar.SOLAR_BRASERO_OBSCURIDAD.get().getRegistryName().toString(),
                AnimsSolar.SOLAR_BRASERO_CREMATORIO.get().getRegistryName().toString(),
                AnimsSolar.SOLAR_BRASERO_INFIERNO.get().getRegistryName().toString(),
                AnimsNapoleon.NAPOLEON_AUSTERLITZ_SHOOT.get().getRegistryName().toString(),
                AnimsNapoleon.NAPOLEON_WATERLOW_SHOOT.get().getRegistryName().toString(),
                AnimsOrbit.ORBIT_LIGHT_BEAM.get().getRegistryName().toString()
        ));
    }

    public static Set<String> getDangerousAnimations() {
        return DANGEROUS_ANIMATIONS;
    }
}
