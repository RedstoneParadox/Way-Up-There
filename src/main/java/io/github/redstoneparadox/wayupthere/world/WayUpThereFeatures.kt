package io.github.redstoneparadox.wayupthere.world

import io.github.redstoneparadox.wayupthere.mixin.StructureFeatureAccessor
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.util.registry.Registry
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.ConfiguredStructureFeature
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.feature.StructureFeature
import java.util.*

object WayUpThereFeatures {
    val STARTING_ISLAND = registerStructureFeature(
        "Starting_Island",
        StartingIslandFeature(
            StartingIslandFeatureConfig.CODEC
        ),
        GenerationStep.Feature.TOP_LAYER_MODIFICATION
    )

    val CONFIGURED_STARTING_ISLAND = register("wayupthere:starting_island", STARTING_ISLAND.configure(StartingIslandFeatureConfig.INSTANCE))

    private fun <F : StructureFeature<*>?> registerStructureFeature(
        name: String,
        structureFeature: F,
        step: GenerationStep.Feature
    ): F {
        StructureFeature.STRUCTURES[name.toLowerCase(Locale.ROOT)] = structureFeature
        StructureFeatureAccessor.getStructureToGenerationStep()[structureFeature] = step
        return Registry.register(Registry.STRUCTURE_FEATURE, name.toLowerCase(Locale.ROOT), structureFeature) as F
    }

    private fun <FC : FeatureConfig?, F : StructureFeature<FC>?> register(id: String, configuredStructureFeature: ConfiguredStructureFeature<FC, F>): ConfiguredStructureFeature<FC, F>? {
        return BuiltinRegistries.add(
            BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE,
            id,
            configuredStructureFeature
        ) as ConfiguredStructureFeature<FC, F>
    }
}