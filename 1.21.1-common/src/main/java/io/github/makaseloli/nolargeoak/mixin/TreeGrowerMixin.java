package io.github.makaseloli.nolargeoak.mixin;

import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TreeGrower.class)
public abstract class TreeGrowerMixin {
    @Inject(method = "getConfiguredFeature", at = @At("HEAD"), cancellable = true)
    private void nolargeoak$disableFancyOak(
            RandomSource random,
            boolean hasFlowers,
            CallbackInfoReturnable<ResourceKey<ConfiguredFeature<?, ?>>> cir
    ) {
        if ((Object) this == TreeGrower.OAK) {
            cir.setReturnValue(hasFlowers ? TreeFeatures.OAK_BEES_005 : TreeFeatures.OAK);
        }
    }
}
