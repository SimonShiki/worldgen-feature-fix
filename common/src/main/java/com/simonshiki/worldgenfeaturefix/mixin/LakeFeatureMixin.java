package com.simonshiki.worldgenfeaturefix.mixin;

import com.simonshiki.worldgenfeaturefix.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LakeFeature.class)
public class LakeFeatureMixin {
    @Redirect(method = "place", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/WorldGenLevel;getBiome(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/core/Holder;"))
    private net.minecraft.core.Holder<Biome> redirectGetBiome(WorldGenLevel worldGenLevel, BlockPos pos) {
        try {
            return worldGenLevel.getBiome(pos);
        } catch (Exception e) {
            // If getBiome fails (chunk unavailable during world generation),
            // use getUncachedNoiseBiome as fallback
            Constants.LOG.warn("LakeFeature: getBiome failed at {} due to chunk unavailability, falling back to getUncachedNoiseBiome. Reason: {}", pos, e.getMessage());
            return worldGenLevel.getUncachedNoiseBiome(pos.getX(), pos.getY(), pos.getZ());
        }
    }
}
