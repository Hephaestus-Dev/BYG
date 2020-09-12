package voronoiaoc.byg.common.world.feature.features.overworld.trees.taiga.spruce;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import voronoiaoc.byg.common.world.feature.features.overworld.trees.util.BYGAbstractTreeFeature;
import voronoiaoc.byg.core.byglists.BYGBlockList;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

//THIS FEATURE MUST BE REGISTERED & ADDED TO A BIOME!
public class OrangeSpruceTree extends BYGAbstractTreeFeature<NoFeatureConfig> {
    //Blocks used for the tree.
    private static final BlockState LOG = Blocks.SPRUCE_LOG.getDefaultState();
    private static final BlockState LEAVES = BYGBlockList.ORANGE_SPRUCE_LEAVES.getDefaultState();
    private static final BlockState LEAVES3 = BYGBlockList.ORANGE_SPRUCE_LEAVES.getDefaultState();
    private static final BlockState BEENEST = Blocks.BEE_NEST.getDefaultState();


    public OrangeSpruceTree(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn) {
        super(configIn);
        //setSapling((net.minecraftforge.common.IPlantable) BYGBlockList.ORANGE_SPRUCE_SAPLING);
    }

    protected static boolean canTreeReplace(IWorldGenerationBaseReader genBaseReader, BlockPos blockPos) {
        return canLogPlaceHere(
                genBaseReader, blockPos
        );
    }

    public boolean place(Set<BlockPos> treeBlockSet, IWorldGenerationReader worldIn, Random rand, BlockPos pos, MutableBoundingBox boundsIn, boolean isSapling) {
//This sets heights for trees. Rand.nextint allows for tree height randomization. The final int value sets the minimum for tree Height.
        int randTreeHeight = rand.nextInt(3) + rand.nextInt(3) + 8;
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

                Direction direction = Direction.Plane.HORIZONTAL.random(rand);
                int randTreeHeight2 = randTreeHeight - rand.nextInt(1);//Crashes on 0.
                int posY1 = 2 - rand.nextInt(1);//Crashes on 0.
                int posX1 = posX;
                int posZ1 = posZ;
                int topTrunkHeight = posY + randTreeHeight - 1;

                for (int groundUpLogRemover = 0; groundUpLogRemover < randTreeHeight; ++groundUpLogRemover) {
                    if (groundUpLogRemover >= randTreeHeight2 && posY1 < 0) { //Unknown
                        posX1 += direction.getXOffset();
                        posZ1 += direction.getZOffset();
                        ++posY1;
                    }
                    //This Int is responsible for the Y coordinate of the trunk BlockPos'.
                    int logplacer = posY + groundUpLogRemover;
                    BlockPos blockpos1 = new BlockPos(posX1, logplacer, posZ1);

                        this.treelog(treeBlockSet, worldIn, blockpos1, boundsIn);
                }
                int leaveColor = rand.nextInt(2) + 1;
                int leavessquarespos = 1;
                int leavessquarespos2 = 2;
                int leavesAdder = rand.nextInt(2) + 1;
                {
                    if (leaveColor == 1) {
                        for (int posXLeafWidth = -leavessquarespos; posXLeafWidth <= leavessquarespos; ++posXLeafWidth) {//has to do with leaves
                            for (int posZLeafWidthL0 = -leavessquarespos; posZLeafWidthL0 <= leavessquarespos; ++posZLeafWidthL0) {
                                for (int posXLeafWidth2 = -leavessquarespos2; posXLeafWidth2 <= leavessquarespos2; ++posXLeafWidth2) {//has to do with leaves
                                    for (int posZLeafWidthL2 = -leavessquarespos2; posZLeafWidthL2 <= leavessquarespos2; ++posZLeafWidthL2) {

                                        this.leafs(worldIn, posX1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight + 1, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight + 1, posZ1 - 1, boundsIn, treeBlockSet);

                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 1, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 1, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight - 1, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight - 1, posZ1 - 1, boundsIn, treeBlockSet);

                                        this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 2, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 2, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 2, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight - 2, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight - 2, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 2, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 2, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 2, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 2, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 2, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 2, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 2, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 2, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 2, posZ1 - 1, boundsIn, treeBlockSet);

                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 3, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 3, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight - 3, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight - 3, posZ1 - 1, boundsIn, treeBlockSet);

                                        this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 4, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 4, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 4, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight - 4, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 4, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 4, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 4, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 4, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 4, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 4, posZ1 - 1, boundsIn, treeBlockSet);
                                        if (leavesAdder == 1) {
                                            this.leafs(worldIn, posX1 - 1, topTrunkHeight - 5, posZ1, boundsIn, treeBlockSet);
                                            this.leafs(worldIn, posX1 + 1, topTrunkHeight - 5, posZ1, boundsIn, treeBlockSet);
                                            this.leafs(worldIn, posX1, topTrunkHeight - 5, posZ1 + 1, boundsIn, treeBlockSet);
                                            this.leafs(worldIn, posX1, topTrunkHeight - 5, posZ1 - 1, boundsIn, treeBlockSet);

                                        } else if (leavesAdder == 2) {
                                            this.leafs(worldIn, posX1 + posXLeafWidth2, topTrunkHeight - 5, posZ1 + posZLeafWidthL2, boundsIn, treeBlockSet);
                                            this.leafs(worldIn, posX1 - 1, topTrunkHeight - 6, posZ1, boundsIn, treeBlockSet);
                                            this.leafs(worldIn, posX1 + 1, topTrunkHeight - 6, posZ1, boundsIn, treeBlockSet);
                                            this.leafs(worldIn, posX1, topTrunkHeight - 6, posZ1 + 1, boundsIn, treeBlockSet);
                                            this.leafs(worldIn, posX1, topTrunkHeight - 6, posZ1 - 1, boundsIn, treeBlockSet);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (leaveColor == 2) {
                        for (int posXLeafWidth = -leavessquarespos; posXLeafWidth <= leavessquarespos; ++posXLeafWidth) {//has to do with leaves
                            for (int posZLeafWidthL0 = -leavessquarespos; posZLeafWidthL0 <= leavessquarespos; ++posZLeafWidthL0) {
                                for (int posXLeafWidth2 = -leavessquarespos2; posXLeafWidth2 <= leavessquarespos2; ++posXLeafWidth2) {//has to do with leaves
                                    for (int posZLeafWidthL2 = -leavessquarespos2; posZLeafWidthL2 <= leavessquarespos2; ++posZLeafWidthL2) {

                                        this.leafs3(worldIn, posX1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 - 1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 + 1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1, topTrunkHeight + 1, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1, topTrunkHeight + 1, posZ1 - 1, boundsIn, treeBlockSet);

                                        this.leafs3(worldIn, posX1 - 1, topTrunkHeight - 1, posZ1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 + 1, topTrunkHeight - 1, posZ1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1, topTrunkHeight - 1, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1, topTrunkHeight - 1, posZ1 - 1, boundsIn, treeBlockSet);

                                        this.leafs3(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 2, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 + 2, topTrunkHeight - 2, posZ1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 - 2, topTrunkHeight - 2, posZ1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1, topTrunkHeight - 2, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1, topTrunkHeight - 2, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 - 2, topTrunkHeight - 2, posZ1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 + 1, topTrunkHeight - 2, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 - 1, topTrunkHeight - 2, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 + 1, topTrunkHeight - 2, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 - 1, topTrunkHeight - 2, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 + 2, topTrunkHeight - 2, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 + 2, topTrunkHeight - 2, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 - 2, topTrunkHeight - 2, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 - 2, topTrunkHeight - 2, posZ1 - 1, boundsIn, treeBlockSet);

                                        this.leafs3(worldIn, posX1 - 1, topTrunkHeight - 3, posZ1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 + 1, topTrunkHeight - 3, posZ1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1, topTrunkHeight - 3, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1, topTrunkHeight - 3, posZ1 - 1, boundsIn, treeBlockSet);

                                        this.leafs3(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 4, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 + 2, topTrunkHeight - 4, posZ1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 - 2, topTrunkHeight - 4, posZ1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1, topTrunkHeight - 4, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 - 2, topTrunkHeight - 4, posZ1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 + 1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 + 1, topTrunkHeight - 4, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 + 2, topTrunkHeight - 4, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 + 2, topTrunkHeight - 4, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 - 2, topTrunkHeight - 4, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs3(worldIn, posX1 - 2, topTrunkHeight - 4, posZ1 - 1, boundsIn, treeBlockSet);
                                        if (leavesAdder == 1) {
                                            this.leafs3(worldIn, posX1 - 1, topTrunkHeight - 5, posZ1, boundsIn, treeBlockSet);
                                            this.leafs3(worldIn, posX1 + 1, topTrunkHeight - 5, posZ1, boundsIn, treeBlockSet);
                                            this.leafs3(worldIn, posX1, topTrunkHeight - 5, posZ1 + 1, boundsIn, treeBlockSet);
                                            this.leafs3(worldIn, posX1, topTrunkHeight - 5, posZ1 - 1, boundsIn, treeBlockSet);
                                        } else if (leavesAdder == 2) {
                                            this.leafs3(worldIn, posX1 + posXLeafWidth2, topTrunkHeight - 5, posZ1 + posZLeafWidthL2, boundsIn, treeBlockSet);
                                            this.leafs3(worldIn, posX1 - 1, topTrunkHeight - 6, posZ1, boundsIn, treeBlockSet);
                                            this.leafs3(worldIn, posX1 + 1, topTrunkHeight - 6, posZ1, boundsIn, treeBlockSet);
                                            this.leafs3(worldIn, posX1, topTrunkHeight - 6, posZ1 + 1, boundsIn, treeBlockSet);
                                            this.leafs3(worldIn, posX1, topTrunkHeight - 6, posZ1 - 1, boundsIn, treeBlockSet);
                                        }
                                    }
                                }
                            }
                        }
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
            int distance = 2;
            if (yOffset == -5) {
                distance = 0;
            }

            if (yOffset >= height - 1) {
                distance = 1;
            }

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

    private void leafs3(IWorldGenerationReader reader, int x, int y, int z, MutableBoundingBox boundingBox, Set<BlockPos> blockPos) {
        BlockPos blockpos = new BlockPos(x, y, z);
        if (isAir(reader, blockpos)) {
            this.setFinalBlockState(blockPos, reader, blockpos, LEAVES3, boundingBox);
        }

    }
}