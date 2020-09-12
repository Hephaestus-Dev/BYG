package voronoiaoc.byg.common.world.feature.features.overworld.trees.coniferous;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
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

public class ConiferTree7 extends BYGAbstractTreeFeature<NoFeatureConfig> {
    //Blocks used for the tree.
    private static final BlockState LOG = BYGBlockList.FIR_LOG.getDefaultState();
    private static final BlockState LEAVES = BYGBlockList.FIR_LEAVES.getDefaultState();
    private static final BlockState BEENEST = Blocks.BEE_NEST.getDefaultState();

    public ConiferTree7(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn) {
        super(configIn);
        //setSapling((net.minecraftforge.common.IPlantable) BYGBlockList.FIR_SAPLING);

    }

    protected static boolean canTreePlace(IWorldGenerationBaseReader genBaseReader, BlockPos blockPos) {
        return canLogPlaceHere(
                genBaseReader, blockPos
        );
    }

    public boolean place(Set<BlockPos> treeBlockSet, IWorldGenerationReader worldIn, Random rand, BlockPos pos, MutableBoundingBox boundsIn, boolean isSapling) {
//This sets heights for trees. Rand.nextint allows for tree height randomization. The final int value sets the minimum for tree Height.
        int randTreeHeight = rand.nextInt(5) + rand.nextInt(5) + 18;
        //Positions
        int posX = pos.getX();
        int posY = pos.getY();
        int posZ = pos.getZ();
        if (posY >= 1 && posY + randTreeHeight + 1 < worldIn.getMaxHeight()) {
            BlockPos checkGround = pos.down();
            if (!isDesiredGroundwDirtTag(worldIn, checkGround, Blocks.GRASS_BLOCK)) {
                return false;
            } else if (!this.doesTreeFit(worldIn, pos, randTreeHeight)) {
                return false;
            } else {

                Direction direction = Direction.Plane.HORIZONTAL.random(rand);
                int randTreeHeight2 = randTreeHeight - rand.nextInt(1);//Crashes on 0.
                int posY2 = 2 - rand.nextInt(1);//Crashes on 0.
                int posX2 = posX;
                int posZ2 = posZ;
                int topTrunkHeight = posY + randTreeHeight - 1;

                for (int trunkBottom = 0; trunkBottom < randTreeHeight; ++trunkBottom) {
                    if (trunkBottom >= randTreeHeight2 && posY2 < 0) { //Unknown
                        posX2 += direction.getXOffset();
                        posZ2 += direction.getZOffset();
                        ++posY2;
                    }
                    int logplacer = posY + trunkBottom;


                    BlockPos trunkPos1 = new BlockPos(posX2, logplacer, posZ2);
                    this.treelog(treeBlockSet, worldIn, trunkPos1, boundsIn);

                }
                for (int posXLeafSize = -1; posXLeafSize <= 1; ++posXLeafSize) {
                    for (int posZLeafSize = -1; posZLeafSize <= 1; ++posZLeafSize) {
                        this.leafs(worldIn, posX2, topTrunkHeight + 2, posZ2, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX2, topTrunkHeight + 1, posZ2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + 1, topTrunkHeight + 1, posZ2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 - 1, topTrunkHeight + 1, posZ2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2, topTrunkHeight + 1, posZ2 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2, topTrunkHeight + 1, posZ2 + 1, boundsIn, treeBlockSet);


                        this.leafs(worldIn, posX2 + posXLeafSize, topTrunkHeight - 1, posZ2 + posZLeafSize + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + posXLeafSize, topTrunkHeight - 1, posZ2 + posZLeafSize - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + posXLeafSize - 1, topTrunkHeight - 1, posZ2 + posZLeafSize, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + posXLeafSize + 1, topTrunkHeight - 1, posZ2 + posZLeafSize, boundsIn, treeBlockSet);


                        this.leafs(worldIn, posX2 + 1, topTrunkHeight - 2, posZ2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 - 1, topTrunkHeight - 2, posZ2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2, topTrunkHeight - 2, posZ2 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2, topTrunkHeight - 2, posZ2 + 1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX2 + posXLeafSize, topTrunkHeight - 3, posZ2 + posZLeafSize + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + posXLeafSize, topTrunkHeight - 3, posZ2 + posZLeafSize - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + posXLeafSize - 1, topTrunkHeight - 3, posZ2 + posZLeafSize, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + posXLeafSize + 1, topTrunkHeight - 3, posZ2 + posZLeafSize, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX2 + 1, topTrunkHeight - 4, posZ2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 - 1, topTrunkHeight - 4, posZ2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2, topTrunkHeight - 4, posZ2 - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2, topTrunkHeight - 4, posZ2 + 1, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX2 + posXLeafSize + 1, topTrunkHeight - 5, posZ2 + posZLeafSize + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + posXLeafSize - 1, topTrunkHeight - 5, posZ2 + posZLeafSize - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + posXLeafSize + 1, topTrunkHeight - 5, posZ2 + posZLeafSize - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + posXLeafSize - 1, topTrunkHeight - 5, posZ2 + posZLeafSize + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + 3, topTrunkHeight - 5, posZ2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 - 3, topTrunkHeight - 5, posZ2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2, topTrunkHeight - 5, posZ2 - 3, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2, topTrunkHeight - 5, posZ2 + 3, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX2 + posXLeafSize, topTrunkHeight - 6, posZ2 + posZLeafSize, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + 2, topTrunkHeight - 6, posZ2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 - 2, topTrunkHeight - 6, posZ2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2, topTrunkHeight - 6, posZ2 - 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2, topTrunkHeight - 6, posZ2 + 2, boundsIn, treeBlockSet);

                        this.leafs(worldIn, posX2 + posXLeafSize, topTrunkHeight - 7, posZ2 + posZLeafSize + 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + posXLeafSize, topTrunkHeight - 7, posZ2 + posZLeafSize - 2, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + posXLeafSize - 2, topTrunkHeight - 7, posZ2 + posZLeafSize, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + posXLeafSize + 2, topTrunkHeight - 7, posZ2 + posZLeafSize, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + posXLeafSize + 1, topTrunkHeight - 7, posZ2 + posZLeafSize + 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + posXLeafSize - 1, topTrunkHeight - 7, posZ2 + posZLeafSize - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + posXLeafSize + 1, topTrunkHeight - 7, posZ2 + posZLeafSize - 1, boundsIn, treeBlockSet);
                        this.leafs(worldIn, posX2 + posXLeafSize - 1, topTrunkHeight - 7, posZ2 + posZLeafSize + 1, boundsIn, treeBlockSet);


                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    //This Method essentially checks whether or not our tree can generate
    private boolean doesTreeFit(IWorldGenerationBaseReader reader, BlockPos blockPos, int height) {
        int x = blockPos.getX();
        int y = blockPos.getY();
        int z = blockPos.getZ();
        BlockPos.Mutable pos = new BlockPos.Mutable();

        for (int yOffset = 0; yOffset <= height + 1; ++yOffset) {
            //Distance/Density of trees. Positive Values ONLY
            int distance = 1;

            for (int xDistance = -distance; xDistance <= distance; ++xDistance) {
                for (int zDistance = -distance; zDistance <= distance; ++zDistance) {
                    if (!canTreePlace(reader, pos.setPos(x + xDistance, y + yOffset, z + zDistance))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //Log Placement
    private void treelog(Set<BlockPos> setlogblock, IWorldGenerationReader reader, BlockPos pos, MutableBoundingBox boundingBox) {
        if (canTreePlace(reader, pos)) {
            this.setFinalBlockState(setlogblock, reader, pos, LOG, boundingBox);
        }

    }

    //Leaves Placement
    private void leafs(IWorldGenerationReader reader, int x, int y, int z, MutableBoundingBox boundingBox, Set<BlockPos> blockPos) {
        BlockPos leafpos = new BlockPos(x, y, z);
        if (isAir(reader, leafpos)) {
            this.setFinalBlockState(blockPos, reader, leafpos, LEAVES, boundingBox);
        }

    }

}