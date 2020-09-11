package io.github.redstoneparadox.wayupthere.world

import com.google.common.collect.ImmutableList
import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import io.github.redstoneparadox.wayupthere.id
import net.minecraft.structure.PoolStructurePiece
import net.minecraft.structure.StructureManager
import net.minecraft.structure.StructureStart
import net.minecraft.structure.pool.StructurePool
import net.minecraft.structure.pool.StructurePoolBasedGenerator
import net.minecraft.structure.pool.StructurePoolElement
import net.minecraft.structure.pool.StructurePools
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockBox
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkPos
import net.minecraft.util.registry.DynamicRegistryManager
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.source.BiomeSource
import net.minecraft.world.gen.ChunkRandom
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.feature.StructureFeature


class StartingIslandFeature(codec: Codec<StartingIslandFeatureConfig>): StructureFeature<StartingIslandFeatureConfig>(
    codec
) {
    override fun getStructureStartFactory(): StructureStartFactory<StartingIslandFeatureConfig> {
        return ::Start as StructureStartFactory<StartingIslandFeatureConfig>
    }

    override fun shouldStartAt(
        chunkGenerator: ChunkGenerator?,
        biomeSource: BiomeSource?,
        worldSeed: Long,
        chunkRandom: ChunkRandom?,
        chunkX: Int,
        chunkZ: Int,
        biome: Biome?,
        chunkPos: ChunkPos?,
        featureConfig: StartingIslandFeatureConfig?
    ): Boolean {
        return chunkX == 0 && chunkZ == 0
    }

    class Start(
        feature: StructureFeature<StartingIslandFeatureConfig>?,
        chunkX: Int,
        chunkZ: Int,
        box: BlockBox,
        references: Int,
        seed: Long
    ) : StructureStart<StartingIslandFeatureConfig>(feature, chunkX, chunkZ, box, references, seed) {
        override fun init(
            registryManager: DynamicRegistryManager,
            chunkGenerator: ChunkGenerator,
            manager: StructureManager,
            chunkX: Int,
            chunkZ: Int,
            biome: Biome,
            config: StartingIslandFeatureConfig
        ) {
            StructurePoolBasedGenerator.method_30419(
                registryManager,
                config,
                ::PoolStructurePiece,
                chunkGenerator,
                manager,
                BlockPos(chunkX shl 4, 60, chunkZ shl 4),
                children,
                random,
                true,
                true
            )
            setBoundingBoxFromChildren()
        }

        companion object {
            val STARTING_ISLAND = "wayupthere:startingisland"

            val POOL = StructurePools.register(
                StructurePool(
                    "wayupthere:startingisland".id(),
                    Identifier("empty"),
                    ImmutableList.of(
                        Pair(StructurePoolElement.method_30425(STARTING_ISLAND), 1)
                    ),
                    StructurePool.Projection.RIGID
                )
            )
        }
    }
}