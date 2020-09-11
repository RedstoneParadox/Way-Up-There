package io.github.redstoneparadox.wayupthere.world

import com.mojang.serialization.Codec
import net.minecraft.structure.pool.StructurePool
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig
import java.util.function.Supplier

class StartingIslandFeatureConfig(supplier: Supplier<StructurePool>, size: Int) : StructurePoolFeatureConfig(supplier, size) {
    companion object {
        val INSTANCE = StartingIslandFeatureConfig({ StartingIslandFeature.Start.POOL }, 8)
        val CODEC = Codec.unit { INSTANCE }
    }
}