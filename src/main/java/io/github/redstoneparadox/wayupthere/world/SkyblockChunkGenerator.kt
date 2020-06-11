package io.github.redstoneparadox.wayupthere.world

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkPos
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

    override fun method_28506(): Codec<out ChunkGenerator> {
        return CODEC
    }

    override fun getHeight(x: Int, z: Int, heightmapType: Heightmap.Type?): Int {
        return 0
    }

    override fun buildSurface(region: ChunkRegion, chunk: Chunk) {
        if (chunk.pos == ChunkPos(0, 0)) {
            for (x in 8..11) {
                for (z in 8..11) {
                    for (y in 64 downTo 61) {
                        val pos = BlockPos(x, y, z)

                        if (y == 64) chunk.setBlockState(pos, Blocks.GRASS_BLOCK.defaultState, false)
                        else chunk.setBlockState(pos, Blocks.DIRT.defaultState, false)
                    }
                }
            }
        }
    }

    override fun populateNoise(world: WorldAccess?, accessor: StructureAccessor?, chunk: Chunk?) {
    }

    override fun withSeed(seed: Long): ChunkGenerator {
        return this
    }

    companion object {
        val CODEC: Codec<out SkyblockChunkGenerator> = RecordCodecBuilder.create { instance ->
            instance.group(
                BiomeSource.field_24713.fieldOf("biome_source")
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