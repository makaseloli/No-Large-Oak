package io.github.makaseloli.nolargeoak.mixin;

import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(TreeFeature.class)
public abstract class TreeFeatureMixin {

  @ModifyVariable(
    method = { "place", "m_142674_" },
    at = @At("STORE"),
    ordinal = 0
  )
  private TreeConfiguration nolargeoak$replaceFancyOakConfiguration(
    TreeConfiguration config
  ) {
    if (!(config.trunkPlacer instanceof FancyTrunkPlacer)) {
      return config;
    }

    return new TreeConfiguration.TreeConfigurationBuilder(
      BlockStateProvider.simple(Blocks.OAK_LOG),
      new StraightTrunkPlacer(4, 2, 0),
      BlockStateProvider.simple(Blocks.OAK_LEAVES),
      new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
      new TwoLayersFeatureSize(1, 0, 1)
    )
      .ignoreVines()
      .decorators(config.decorators)
      .build();
  }
}
