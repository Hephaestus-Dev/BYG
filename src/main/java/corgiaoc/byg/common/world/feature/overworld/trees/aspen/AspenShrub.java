package corgiaoc.byg.common.world.feature.overworld.trees.aspen;

import com.mojang.serialization.Codec;
import corgiaoc.byg.common.world.feature.config.BYGTreeConfig;
import corgiaoc.byg.common.world.feature.overworld.trees.util.BYGAbstractTreeFeature;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;

import java.util.Random;
import java.util.Set;

public class AspenShrub extends BYGAbstractTreeFeature<BYGTreeConfig> {

    public AspenShrub(Codec<BYGTreeConfig> configIn) {
        super(configIn);
    }

    public boolean generate(Set<BlockPos> changedBlocks, ISeedReader worldIn, Random rand, BlockPos pos, MutableBoundingBox boundsIn, boolean isSapling, BYGTreeConfig config) {

        int randTreeHeight = rand.nextInt(config.getMaxPossibleHeight()) + config.getMinHeight();
        //Positions
        int posX = pos.getX();
        int posY = pos.getY();
        int posZ = pos.getZ();
        if (posY >= 1 && posY + randTreeHeight + 1 < worldIn.getHeight()) {

            if (!isDesiredGroundwDirtTag(worldIn, pos.down(), config)) {
                return false;
            } else {
                Direction direction = Direction.Plane.HORIZONTAL.random(rand);
                int randTreeHeight2 = randTreeHeight - rand.nextInt(1);
                int posY1 = 2 - rand.nextInt(1);
                int posX1 = posX;
                int posZ1 = posZ;
                int topTrunkHeight = posY + randTreeHeight - 1;

                for (int buildTrunk = 0; buildTrunk < randTreeHeight; ++buildTrunk) {
                    if (buildTrunk >= randTreeHeight2 && posY1 < 0) {
                        posX1 += direction.getXOffset();
                        posZ1 += direction.getZOffset();
                        ++posY1;
                    }
                    int logplacer = posY + buildTrunk;
                    BlockPos blockpos1 = new BlockPos(posX1, logplacer, posZ1);
                    placeTrunk(config, rand, changedBlocks, worldIn, blockpos1, boundsIn);

                }

                int leavessquarespos = 2;
                for (int posXLeafWidth = -leavessquarespos; posXLeafWidth <= leavessquarespos; ++posXLeafWidth) {//has to do with leaves
                    for (int posZLeafWidthL0 = -leavessquarespos; posZLeafWidthL0 <= leavessquarespos; ++posZLeafWidthL0) {
                        placeLeaves(config, rand, worldIn, posX1, topTrunkHeight + 1, posZ1, boundsIn, changedBlocks);
                        placeLeaves(config, rand, worldIn, posX1 - 1, topTrunkHeight, posZ1, boundsIn, changedBlocks);
                        placeLeaves(config, rand, worldIn, posX1 + 1, topTrunkHeight, posZ1, boundsIn, changedBlocks);
                        placeLeaves(config, rand, worldIn, posX1, topTrunkHeight, posZ1 + 1, boundsIn, changedBlocks);
                        placeLeaves(config, rand, worldIn, posX1, topTrunkHeight, posZ1 - 1, boundsIn, changedBlocks);
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }
}
