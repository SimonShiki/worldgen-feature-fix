package com.simonshiki.worldgenfeaturefix.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.SnowAndFreezeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SnowAndFreezeFeature.class)
public class SnowAndFreezeFeatureMixin {
    @Redirect(method = "place", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/WorldGenLevel;getBiome(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/core/Holder;"))
    private net.minecraft.core.Holder<Biome> redirectGetBiome(WorldGenLevel worldGenLevel, BlockPos pos) {
        if (worldGenLevel.hasChunk(pos.getX() >> 4, pos.getZ() >> 4)) {
            return worldGenLevel.getBiome(pos);
        }
        return worldGenLevel.getUncachedNoiseBiome(pos.getX() >> 2, pos.getY() >> 2, pos.getZ() >> 2);
    }
}
