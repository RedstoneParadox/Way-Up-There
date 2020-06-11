package io.github.redstoneparadox.wayupthere

import io.github.redstoneparadox.wayupthere.world.SkyblockChunkGenerator
import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object WayUpThere: ModInitializer {
    override fun onInitialize() {
        Registry.register(Registry.CHUNK_GENERATOR, Identifier("wayupthere", "skyblock"), SkyblockChunkGenerator.CODEC)
    }
}