package voronoiaoc.byg.common.world.feature.features.overworld.trees.aspen;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import voronoiaoc.byg.common.world.feature.features.overworld.trees.util.BYGAbstractTreeFeature;
import voronoiaoc.byg.core.byglists.BYGBiomeList;
import voronoiaoc.byg.core.byglists.BYGBlockList;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class AspenTree3 extends BYGAbstractTreeFeature<NoFeatureConfig> {
    //Blocks used for the tree.
    private static final BlockState LOG = BYGBlockList.ASPEN_LOG.getDefaultState();
    private static final BlockState LEAVES = BYGBlockList.ASPEN_LEAVES.getDefaultState();
    private static final BlockState BEENEST = Blocks.BEE_NEST.getDefaultState();
    Random random = new Random();

    public AspenTree3(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn) {
        super(configIn);
        //setSapling((net.minecraftforge.common.IPlantable) BYGBlockList.ASPEN_SAPLING);
    }

    protected static boolean canTreeReplace(IWorldGenerationBaseReader genBaseReader, BlockPos blockPos) {
        return canLogPlaceHere(
                genBaseReader, blockPos
        );
    }

    public boolean place(Set<BlockPos> treeBlockSet, IWorldGenerationReader worldIn, Random rand, BlockPos pos, MutableBoundingBox boundsIn, boolean isSapling) {
        int minHeight = 9;
        if (worldIn instanceof IWorld) {
            Biome biome = ((IWorld) worldIn).getBiome(pos);
            if (biome == BYGBiomeList.ASPENFORESTHILLS || biome == BYGBiomeList.SEASONALTAIGA || biome == BYGBiomeList.SEASONALTAIGAHILLS || biome == BYGBiomeList.SEASONALGIANTTAIGA || biome == BYGBiomeList.THE_BLACK_FOREST || biome == BYGBiomeList.BLACK_FOREST_HILLS || biome == BYGBiomeList.BLACK_FOREST_CLEARING || biome == BYGBiomeList.FOREST_FAULT)
                minHeight = 13;
        }
        int randTreeHeight = rand.nextInt(3) + minHeight;
        //Positions
        int posX = pos.getX();
        int posY = pos.getY();
        int posZ = pos.getZ();
        if (posY >= 1 && posY + randTreeHeight + 1 < worldIn.getMaxHeight()) {
            if (!isDesiredGroundwDirtTag(worldIn, pos.offset(Direction.DOWN), Blocks.GRASS_BLOCK)) {
                return false;
            } else if (!this.isAnotherTreeNearby(worldIn, pos, randTreeHeight, 0, isSapling)) {
                return false;
            } else if (!this.doesSaplingHaveSpaceToGrow(worldIn, pos, randTreeHeight, 5, 5, 5, isSapling)) {
                return false;
            } else if (!this.doesTreeFit(worldIn, pos, randTreeHeight)) {
                return false;
            } else {
                //Places dirt under logs where/when necessary.

                Direction direction = Direction.Plane.HORIZONTAL.random(rand);
                int randTreeHeight2 = randTreeHeight - rand.nextInt(1);//Crashes on 0.
                int posY1 = 2 - rand.nextInt(1);//Crashes on 0.
                int posX1 = posX;
                int posZ1 = posZ;
                int topTrunkHeight = posY + randTreeHeight - 1;
                int topTrunkHeight2 = posY + randTreeHeight + randTreeHeight - 1;

                //Raising the 'groundUpLogRemover'  will remove all log blocks from the ground up no matter how thick the trunk is based on the value given. 5 would destroy all trunks from 5 up off the ground.
                for (int groundUpLogRemover = 0; groundUpLogRemover < randTreeHeight; ++groundUpLogRemover) {
                    if (groundUpLogRemover >= randTreeHeight2 && posY1 < 0) { //Unknown
                        posX1 += direction.getXOffset();
                        posZ1 += direction.getZOffset();
                        ++posY1;
                    }
                    //This Int is responsible for the Y coordinate of the trunk BlockPos'.
                    int logplacer = posY + groundUpLogRemover;
                    int logplacer2 = posY + randTreeHeight;
                    BlockPos blockpos1 = new BlockPos(posX1, logplacer, posZ1);
                    BlockPos blockpos2 = new BlockPos(posX1, logplacer2, posZ1);

                    //Sets Logs
                    this.treelog(treeBlockSet, worldIn, blockpos1, boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.north().west(), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.down().south().east(), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.south().east(), boundsIn);
                    this.treelog(treeBlockSet, worldIn, blockpos2.up().south().east(), boundsIn);

                }
                //This allows a random rotation between 3 differently leave Presets in the same class. Optimizes Performance instead of the loading of several classes.

                int leavessquarespos = 2;
                for (int posXLeafWidth = -leavessquarespos; posXLeafWidth <= leavessquarespos; ++posXLeafWidth) {//has to do with leaves
                    for (int posZLeafWidthL0 = -leavessquarespos; posZLeafWidthL0 <= leavessquarespos; ++posZLeafWidthL0) {

                        int posX2 = posX1 + 1;
                        int posZ2 = posZ1 + 1;

                        int posX3 = posX1 - 1;
                        int posZ3 = posZ1 - 1;

                        //Bottom Leaves
//                        this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight + 1, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
//                        this.leafs(worldIn, posX1 + posXLeafWidth - 2, topTrunkHeight + 1, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
//                        this.leafs(worldIn, posX1 + posXLeafWidth - 1, topTrunkHeight + 1, posZ1 + posZLeafWidthL0 + 1, boundsIn, treeBlockSet);
//                        this.leafs(worldIn, posX1 + posXLeafWidth - 1, topTrunkHeight + 1, posZ1 + posZLeafWidthL0 - 1, boundsIn, treeBlockSet);

                        //3x3
                        if (posXLeafWidth <= 1 && posZLeafWidthL0 <= 1 && posZLeafWidthL0 >= -1 && posXLeafWidth >= -1) {

                            this.leafs(worldIn, posX2 + posXLeafWidth, topTrunkHeight + 3, posZ2 + posZLeafWidthL0, boundsIn, treeBlockSet);
                            this.leafs(worldIn, posX3 + posXLeafWidth, topTrunkHeight + 2, posZ3 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        }

                        //2x3
                        if (posXLeafWidth <= 0 && posZLeafWidthL0 <= 1 && posZLeafWidthL0 >= -1 && posXLeafWidth >= -1) {
                        }

                        //Left Leaves
                        this.leafs(worldIn, posX2 + 1, topTrunkHeight + 2, posZ2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + 2, topTrunkHeight + 2, posZ2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + 1, topTrunkHeight + 1, posZ2, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX2, topTrunkHeight + 2, posZ2 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2, topTrunkHeight + 2, posZ2 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2, topTrunkHeight + 3, posZ2 + 2, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX2 - 1, topTrunkHeight + 2, posZ2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 - 2, topTrunkHeight + 2, posZ2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 - 2, topTrunkHeight + 3, posZ2, boundsIn, treeBlockSet);


                        this.leafs(worldIn, posX2, topTrunkHeight + 1, posZ2 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2, topTrunkHeight + 3, posZ2 - 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2, topTrunkHeight + 2, posZ2 - 2, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX2 + 1, topTrunkHeight + 3, posZ2 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + 1, topTrunkHeight + 2, posZ2 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + 2, topTrunkHeight + 3, posZ2 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + 1, topTrunkHeight + 2, posZ2 - 2, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX2 - 1, topTrunkHeight + 3, posZ2 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 - 1, topTrunkHeight + 3, posZ2 - 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 - 2, topTrunkHeight + 2, posZ2 - 1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX2 - 1, topTrunkHeight + 3, posZ2 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 - 2, topTrunkHeight + 3, posZ2 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 - 1, topTrunkHeight + 3, posZ2 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 - 1, topTrunkHeight + 2, posZ2 + 1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX2 + 1, topTrunkHeight + 3, posZ2 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + 1, topTrunkHeight + 3, posZ2 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + 1, topTrunkHeight + 2, posZ2 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + 2, topTrunkHeight + 2, posZ2 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + 2, topTrunkHeight + 3, posZ2 + 1, boundsIn, treeBlockSet);


                        this.leafs(worldIn, posX2, topTrunkHeight + 4, posZ2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2, topTrunkHeight + 5, posZ2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2, topTrunkHeight + 6, posZ2, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX2, topTrunkHeight + 4, posZ2 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 - 1, topTrunkHeight + 4, posZ2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + 1, topTrunkHeight + 4, posZ2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2, topTrunkHeight + 4, posZ2 - 1, boundsIn, treeBlockSet);

                        //Right Leaves
                        this.leafs(worldIn, posX3 + 1, topTrunkHeight + 1, posZ3, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 + 2, topTrunkHeight + 1, posZ3, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 + 1, topTrunkHeight, posZ3, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX3, topTrunkHeight, posZ3 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3, topTrunkHeight, posZ3 + 3, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3, topTrunkHeight, posZ3 + 3, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX3 - 1, topTrunkHeight + 1, posZ3, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 - 2, topTrunkHeight + 1, posZ3, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 - 2, topTrunkHeight + 2, posZ3, boundsIn, treeBlockSet);


                        this.leafs(worldIn, posX3, topTrunkHeight, posZ3 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3, topTrunkHeight + 1, posZ3 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3, topTrunkHeight + 1, posZ3 - 2, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX3 + 1, topTrunkHeight + 2, posZ3 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 + 1, topTrunkHeight + 1, posZ3 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 + 2, topTrunkHeight + 2, posZ3 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 + 1, topTrunkHeight + 1, posZ3 - 2, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX3 - 1, topTrunkHeight + 2, posZ3 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 - 1, topTrunkHeight + 2, posZ3 - 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 - 2, topTrunkHeight + 1, posZ3 - 1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX3 - 1, topTrunkHeight + 2, posZ3 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 - 2, topTrunkHeight + 2, posZ3 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 - 1, topTrunkHeight + 2, posZ3 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 - 1, topTrunkHeight + 1, posZ3 + 1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX3 + 1, topTrunkHeight + 2, posZ3 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 + 1, topTrunkHeight + 2, posZ3 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 + 1, topTrunkHeight + 1, posZ3 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 + 2, topTrunkHeight + 1, posZ3 + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 + 2, topTrunkHeight + 2, posZ3 + 1, boundsIn, treeBlockSet);


                        this.leafs(worldIn, posX3, topTrunkHeight + 3, posZ3, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3, topTrunkHeight + 4, posZ3, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3, topTrunkHeight + 5, posZ3, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX3, topTrunkHeight + 3, posZ3 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 - 1, topTrunkHeight + 3, posZ3, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3 + 1, topTrunkHeight + 3, posZ3, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX3, topTrunkHeight + 3, posZ3 - 1, boundsIn, treeBlockSet);

                        //Foilage
                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 2, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 2, posZ1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 3, posZ1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX1, topTrunkHeight - 3, posZ1 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1, topTrunkHeight - 3, posZ1 + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 3, posZ1 - 1, boundsIn, treeBlockSet);
                    }
                }
            }

            return true;
            //}
        } else {
            return false;
        }
    }

    private boolean doesTreeFit(IWorldGenerationBaseReader reader, BlockPos blockPos, int height) {
        int x = blockPos.getX();
        int y = blockPos.getY();
        int z = blockPos.getZ();
        BlockPos.Mutable pos = new BlockPos.Mutable();

        for (int yOffset = 0; yOffset <= height + 1; ++yOffset) {
            //Distance/Density of trees. Positive Values ONLY
            int distance = 0;

            for (int xOffset = -distance; xOffset <= distance; ++xOffset) {
                for (int zOffset = -distance; zOffset <= distance; ++zOffset) {
                    if (!canTreeReplace(reader, pos.setPos(x + xOffset, y + yOffset, z + zOffset))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //Log Placement
    private void treelog(Set<BlockPos> setlogblock, IWorldGenerationReader reader, BlockPos pos, MutableBoundingBox boundingBox) {
        if (canTreeReplace(reader, pos)) {
            this.setFinalBlockState(setlogblock, reader, pos, LOG, boundingBox);
        }

    }

    //Leaves Placement
    private void leafs(IWorldGenerationReader reader, int x, int y, int z, MutableBoundingBox boundingBox, Set<BlockPos> blockPos) {
        BlockPos blockpos = new BlockPos(x, y, z);
        if (isAir(reader, blockpos)) {
            this.setFinalBlockState(blockPos, reader, blockpos, LEAVES, boundingBox);
        }

    }

}