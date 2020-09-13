package io.github.redstoneparadox.wayupthere.world

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import io.github.redstoneparadox.wayupthere.id
import net.minecraft.block.Blocks
import net.minecraft.structure.StructurePlacementData
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView
import net.minecraft.world.ChunkRegion
import net.minecraft.world.Heightmap
import net.minecraft.world.WorldAccess
import net.minecraft.world.biome.source.BiomeSource
import net.minecraft.world.chunk.Chunk
import net.minecraft.world.gen.StructureAccessor
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.StructuresConfig
import net.minecraft.world.gen.chunk.VerticalBlockSample
import java.util.function.Function

class SkyblockChunkGenerator(biomeSource: BiomeSource): ChunkGenerator(biomeSource, StructuresConfig(false)) {
    override fun getColumnSample(x: Int, z: Int): BlockView {
        return VerticalBlockSample(arrayOf())
    }

    override fun getHeight(x: Int, z: Int, heightmapType: Heightmap.Type?): Int {
        return 0
    }

    override fun buildSurface(region: ChunkRegion, chunk: Chunk) {
        val pos = chunk.pos.startPos
        val rand = region.random

        for (x in 0..15) {
            for (z in 0..15) {
                val trueX = x + pos.x
                val trueZ = z + pos.z

                if (trueX == 0 && trueZ == 0) {
                    val world = region.toServerWorld()
                    val manager = world.structureManager
                    val startingIsland = manager.getStructureOrBlank("wayupthere:starting_island".id())

                    world.server.execute {
                        startingIsland.place(world, BlockPos(trueX, 60, trueZ), StructurePlacementData(), rand)
                        world.server.execute {
                            val spawn = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, BlockPos(0, 60, 0))
                            world.setSpawnPos(spawn, 0.0f)
                        }
                    }
                }

                biomeSource.biomes[0].buildSurface(
                    rand,
                    chunk,
                    trueX,
                    trueZ,
                    255,
                    1.0,
                    Blocks.AIR.defaultState,
                    Blocks.AIR.defaultState,
                    0,
                    0
                )
            }
        }
    }

    override fun getCodec(): Codec<out ChunkGenerator> {
        return CODEC
    }

    override fun populateNoise(world: WorldAccess?, accessor: StructureAccessor?, chunk: Chunk?) {
    }

    override fun withSeed(seed: Long): ChunkGenerator {
        return this
    }

    companion object {
        val CODEC: Codec<out SkyblockChunkGenerator> = RecordCodecBuilder.create { instance ->
            instance.group(
                BiomeSource.CODEC.fieldOf("biome_source")
                    .forGetter { generator: SkyblockChunkGenerator -> generator.biomeSource }
            )
                .apply(instance, instance.stable(Function {source ->
                    SkyblockChunkGenerator(
                        source
                    )
                }))
        }
    }
}