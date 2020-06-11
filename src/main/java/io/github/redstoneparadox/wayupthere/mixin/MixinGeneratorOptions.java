package io.github.redstoneparadox.wayupthere.mixin;

import io.github.redstoneparadox.wayupthere.world.SkyblockChunkGenerator;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.source.FixedBiomeSource;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.GeneratorOptions;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(GeneratorOptions.class)
public abstract class MixinGeneratorOptions {
    @Shadow
    public static SimpleRegistry<DimensionOptions> method_28608(SimpleRegistry<DimensionOptions> simpleRegistry, ChunkGenerator chunkGenerator) {
        return null;
    }

    @Inject(method = "getDefaultOptions", at = @At("HEAD"), cancellable = true)
    private static void getDefaultOptions(CallbackInfoReturnable<GeneratorOptions> cir) {
        long l = new Random().nextLong();
        SkyblockChunkGenerator skyblockGenerator = new SkyblockChunkGenerator(new FixedBiomeSource(Biomes.THE_VOID));
        cir.setReturnValue(new GeneratorOptions(l, true, false, method_28608(DimensionType.method_28517(l), skyblockGenerator)));
    }
}
