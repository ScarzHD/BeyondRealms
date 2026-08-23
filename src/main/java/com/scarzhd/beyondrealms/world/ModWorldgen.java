package com.scarzhd.beyondrealms.world;

import com.scarzhd.beyondrealms.BeyondRealmsMod;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public final class ModWorldgen {
    public static final ResourceKey<PlacedFeature> ETERNIUM_ORE_PLACED = ResourceKey.create(
            Registries.PLACED_FEATURE,
            BeyondRealmsMod.id("eternium_ore")
    );

    private ModWorldgen() {
    }

    public static void initialize() {
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ETERNIUM_ORE_PLACED
        );
    }
}
