package io.github.redstoneparadox.wayupthere.world

import io.github.redstoneparadox.wayupthere.mixin.BuiltinBiomesAccessor
import net.minecraft.sound.BiomeMoodSound
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeEffects
import net.minecraft.world.biome.GenerationSettings
import net.minecraft.world.biome.SpawnSettings
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilders

object WayUpThereBiomes {
    val SKY_KEY = makeKey("wayupthere:sky")
    var SKY: Biome = createSky()

    fun init() {
        BuiltinBiomesAccessor.invokeRegister(244, SKY_KEY, SKY)
    }

    private fun createSky(): Biome {
        val builder = GenerationSettings.Builder().surfaceBuilder(ConfiguredSurfaceBuilders.NOPE)
        return Biome.Builder().precipitation(Biome.Precipitation.NONE).category(Biome.Category.NONE).depth(0.1f)
            .scale(0.2f).temperature(0.5f).downfall(0.5f).effects(
                BiomeEffects.Builder().waterColor(4159204).waterFogColor(329011).fogColor(12638463)
                    .skyColor(getSkyColor(0.5f)).moodSound(BiomeMoodSound.CAVE).build()
            ).spawnSettings(SpawnSettings.INSTANCE).generationSettings(builder.build()).build()
    }

    private fun getSkyColor(temperature: Float): Int {
        var f = temperature / 3.0f
        f = MathHelper.clamp(f, -1.0f, 1.0f)
        return MathHelper.hsvToRgb(0.62222224f - f * 0.05f, 0.5f + f * 0.1f, 1.0f)
    }

    private fun makeKey(name: String): RegistryKey<Biome> {
        return RegistryKey.of(Registry.BIOME_KEY, Identifier(name))
    }
}