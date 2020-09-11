package io.github.redstoneparadox.wayupthere

import io.github.redstoneparadox.wayupthere.mixin.GeneratorTypeAccessor
import io.github.redstoneparadox.wayupthere.world.SkyblockChunkGenerator
import io.github.redstoneparadox.wayupthere.world.WayUpThereBiomes
import net.fabricmc.api.ModInitializer
import net.minecraft.client.world.GeneratorType
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.source.FixedBiomeSource
import net.minecraft.world.gen.chunk.ChunkGenerator
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings

object WayUpThere: ModInitializer {
    val SKYBLOCK_TYPE = object: GeneratorType("skyblock") {
        override fun getChunkGenerator(biomeRegistry: Registry<Biome>, chunkGeneratorSettingsRegistry: Registry<ChunkGeneratorSettings>, seed: Long): ChunkGenerator {
            return SkyblockChunkGenerator(FixedBiomeSource(biomeRegistry.get(WayUpThereBiomes.SKY_KEY)))
        }
    }

    override fun onInitialize() {
        Registry.register(Registry.CHUNK_GENERATOR, Identifier("wayupthere", "skyblock"), SkyblockChunkGenerator.CODEC)
        WayUpThereBiomes.init()
        GeneratorTypeAccessor.getValues().add(SKYBLOCK_TYPE)
    }
}