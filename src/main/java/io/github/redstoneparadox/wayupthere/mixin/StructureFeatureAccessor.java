package io.github.redstoneparadox.wayupthere.mixin;

import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(StructureFeature.class)
public interface StructureFeatureAccessor {
    @Accessor("STRUCTURE_TO_GENERATION_STEP")
    public static Map<StructureFeature<?>, GenerationStep.Feature> getStructureToGenerationStep() {
        throw new AssertionError();
    }
}
