package corgiaoc.byg.common.world.feature.overworld.trees.willow.dead;

import com.mojang.serialization.Codec;
import corgiaoc.byg.common.world.feature.config.BYGTreeConfig;
import corgiaoc.byg.common.world.feature.overworld.trees.util.BYGAbstractTreeFeature;
import corgiaoc.byg.core.BYGBlocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.IWorldGenerationBaseReader;

import java.util.Random;
import java.util.Set;

public class WillowDeadTree1 extends BYGAbstractTreeFeature<BYGTreeConfig> {

    public WillowDeadTree1(Codec<BYGTreeConfig> configIn) {
        super(configIn);
    }

    protected boolean generate(Set<BlockPos> changedBlocks, ISeedReader worldIn, Random rand, BlockPos pos, MutableBoundingBox boundsIn, boolean isSapling, BYGTreeConfig config) {

        int randTreeHeight = config.getMinHeight() + rand.nextInt(config.getMaxPossibleHeight());
        BlockPos.Mutable mainmutable = new BlockPos.Mutable().setPos(pos);

        if (pos.getY() + randTreeHeight + 1 < worldIn.getHeight()) {

            if (!isDesiredGroundwDirtTag(worldIn, pos.down(), config)) {
                return false;
            } else if (!this.isAnotherTreeNearby(worldIn, pos, randTreeHeight, 0, isSapling)) {
                return false;
            } else if (!this.doesSaplingHaveSpaceToGrow(worldIn, pos, randTreeHeight, 5, 5, 5, isSapling)) {
                return false;
            } else {
                for (int buildTrunk = 0; buildTrunk <= randTreeHeight; buildTrunk++) {
                    placeTrunk(config, rand, changedBlocks, worldIn, mainmutable, boundsIn);

                    mainmutable.move(Direction.UP);
                }
                mainmutable.setPos(pos);

                //Stump
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-1, 0, -2), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(0, 0, -2), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(1, 0, -2), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-1, 0, -1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(0, 0, -1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(3, 0, -1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-2, 0, 0), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-1, 0, 0), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(1, 0, 0), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-1, 0, 1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(0, 0, 1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(1, 0, 1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(0, 1, -2), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-1, 1, -1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(0, 1, -1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(1, 1, -1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(2, 1, -1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-1, 1, 0), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(1, 1, 0), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(0, 1, 1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-1, 2, -1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(0, 2, -1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(0, 3, -1), boundsIn);


                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(0, randTreeHeight, -1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-2, randTreeHeight, 0), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-1, randTreeHeight, 0), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(1, randTreeHeight, 0), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(2, randTreeHeight, 0), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-1, randTreeHeight, 1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-4, randTreeHeight + 1, -1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-3, randTreeHeight + 1, -1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(0, randTreeHeight + 1, -1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(2, randTreeHeight + 1, 0), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(3, randTreeHeight + 1, 0), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(4, randTreeHeight + 1, 0), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-1, randTreeHeight + 1, 1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(0, randTreeHeight + 1, 1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(0, randTreeHeight + 1, 2), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-2, randTreeHeight + 2, -4), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-3, randTreeHeight + 2, -3), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-3, randTreeHeight + 2, -2), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(0, randTreeHeight + 2, -2), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(3, randTreeHeight + 2, -2), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(3, randTreeHeight + 2, -1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-1, randTreeHeight + 2, 1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(2, randTreeHeight + 2, 1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(5, randTreeHeight + 2, 1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(1, randTreeHeight + 2, 3), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(1, randTreeHeight + 2, 4), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(4, randTreeHeight + 3, -3), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(0, randTreeHeight + 3, -2), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-2, randTreeHeight + 3, 1), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(3, randTreeHeight + 3, 2), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(3, randTreeHeight + 3, 3), boundsIn);
                placeBranch(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-2, randTreeHeight + 1, 1), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(1, randTreeHeight - 2, 0), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(1, randTreeHeight - 1, 0), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-2, randTreeHeight - 1, 1), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-3, randTreeHeight, -1), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(4, randTreeHeight, -1), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-2, randTreeHeight, 1), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(0, randTreeHeight, 1), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-3, randTreeHeight + 1, -3), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(2, randTreeHeight + 1, -2), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-1, randTreeHeight + 1, -1), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(4, randTreeHeight + 1, -1), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(0, randTreeHeight + 1, 0), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(1, randTreeHeight + 1, 3), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(3, randTreeHeight + 1, 3), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-3, randTreeHeight + 2, -4), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-2, randTreeHeight + 2, -3), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(2, randTreeHeight + 2, -2), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(3, randTreeHeight + 2, 1), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-1, randTreeHeight + 2, 2), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(3, randTreeHeight + 2, 3), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-3, randTreeHeight + 3, -4), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(3, randTreeHeight + 3, -1), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(-1, randTreeHeight + 3, 1), boundsIn);
                placeLeaves(config, rand, changedBlocks, worldIn, mainmutable.setPos(pos).move(4, randTreeHeight + 3, 2), boundsIn);
            }
        }
        return true;
    }


    private void treeLog(Set<BlockPos> setlogblock, ISeedReader reader, BlockPos pos, MutableBoundingBox boundingBox) {
        if (canLogPlaceHereWater(reader, pos)) {
            this.setFinalBlockState(setlogblock, reader, pos, BYGBlocks.WILLOW_LOG.getDefaultState(), boundingBox);
        }
    }


    private void treeBranch(Set<BlockPos> setlogblock, ISeedReader reader, BlockPos pos, MutableBoundingBox boundingBox) {
        if (canLogPlaceHereWater(reader, pos)) {
            this.setFinalBlockState(setlogblock, reader, pos, BYGBlocks.WILLOW_LOG.getDefaultState(), boundingBox);
        }
    }


    private void leafs(Set<BlockPos> blockPos, ISeedReader reader, BlockPos pos, MutableBoundingBox boundingBox) {
        BlockPos.Mutable blockpos = new BlockPos.Mutable().setPos(pos);
        if (isAirOrWater(reader, blockpos)) {
            this.setFinalBlockState(blockPos, reader, blockpos, BYGBlocks.WILLOW_LEAVES.getDefaultState(), boundingBox);
        }
    }


    private boolean doesTreeFit(IWorldGenerationBaseReader reader, BlockPos blockPos, int height) {
        int x = blockPos.getX();
        int y = blockPos.getY();
        int z = blockPos.getZ();
        BlockPos.Mutable pos = new BlockPos.Mutable();

        for (int yOffset = 0; yOffset <= height + 1; ++yOffset) {
            //Distance/Density of trees. Positive Values ONLY
            int distance = 2;

            for (int xOffset = -distance; xOffset <= distance; ++xOffset) {
                for (int zOffset = -distance; zOffset <= distance; ++zOffset) {
                    if (!canLogPlaceHereWater(reader, pos.setPos(x + xOffset, y + yOffset, z + zOffset))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}