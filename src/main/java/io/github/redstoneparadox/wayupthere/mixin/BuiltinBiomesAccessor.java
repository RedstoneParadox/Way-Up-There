package io.github.redstoneparadox.wayupthere.mixin;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BuiltinBiomes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BuiltinBiomes.class)
public interface BuiltinBiomesAccessor {
    @Invoker("register")
    public static Biome invokeRegister(int rawId, RegistryKey<Biome> registryKey, Biome biome)  {
        throw new AssertionError();
    }
}
