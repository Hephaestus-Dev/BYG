package voronoiaoc.byg.common.world.feature.features.overworld.trees.deciduous;

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

//Copied and Pasted Dark Oak to try and undertstand the logic and math for tree creation.
public class BrownDeciduousTree extends BYGAbstractTreeFeature<NoFeatureConfig> {
    private static final BlockState LOG = Blocks.DARK_OAK_LOG.getDefaultState();
    private static final BlockState LEAVES = BYGBlockList.BROWN_OAK_LEAVES.getDefaultState();
    private static final BlockState BEENEST = Blocks.BEE_NEST.getDefaultState();

    public BrownDeciduousTree(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn) {
        super(configIn);
    }

    protected static boolean canTreeReplace(IWorldGenerationBaseReader p_214587_0_, BlockPos p_214587_1_) {
        return canLogPlaceHere(
                p_214587_0_, p_214587_1_
        );
    }

    public boolean place(Set<BlockPos> treeBlockSet, IWorldGenerationReader worldIn, Random rand, BlockPos pos, MutableBoundingBox boundsIn, boolean isSapling) {
        int randTreeHeight = rand.nextInt(3) + rand.nextInt(3) + 10; //First value changes height of the trunk.
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

//this.setGroundBlockAt(worldIn, blockpos.east(), pos, Blocks.DIRT.getDefaultState());

                //this.setGroundBlockAt(worldIn, blockpos.south(), pos, Blocks.DIRT.getDefaultState());

                //this.setGroundBlockAt(worldIn, blockpos.south().east(), pos, Blocks.DIRT.getDefaultState());

                Direction direction = Direction.Plane.HORIZONTAL.random(rand);
                int random1 = randTreeHeight - rand.nextInt(1);//Crashes on 0. Unknown use.
                int posY1 = 2 - rand.nextInt(1);//Crashes on 0. Unknown Use
                int posX1 = posX;
                int posZ1 = posZ;
                int topTrunkHeight = posY + randTreeHeight - 1;

                /*int z1 = posX;
                int z2 = posY;
                int z3 = posZ;*/


                for (int posX2LogRemover = 0; posX2LogRemover < randTreeHeight; ++posX2LogRemover) {//raising this value will remove log blocks from the ground up.
                    if (posX2LogRemover >= random1 && posY1 < 0) { //Unknown
                        posX1 += direction.getXOffset();
                        posZ1 += direction.getZOffset();
                        ++posY1;
                    }
                    int logplacer = posY + posX2LogRemover;
                    int logplacer2 = posY + posX2LogRemover;
                    BlockPos blockpos1 = new BlockPos(posX1, logplacer, posZ1);
                    //BlockPos blockpos2 = new BlockPos(posX1 , logplacer2, posZ1 );
                    BlockPos blockposwest1 = new BlockPos(posX1 - 1, logplacer2, posZ1);
                    BlockPos blockposeast1 = new BlockPos(posX1 + 1, logplacer2, posZ1);
                    BlockPos blockposnorth1 = new BlockPos(posX1, logplacer2, posZ1 - 1);
                    BlockPos blockpossouth1 = new BlockPos(posX1, logplacer2, posZ1 + 1);
                    //BlockPos blockpos7 = new BlockPos(posX1 - 1, logplacer2, posZ1 );

                        this.treelog(treeBlockSet, worldIn, blockpos1, boundsIn);
                        //this.treelog(treeBlockSet, worldIn, blockpos1.east(), boundsIn);
                        // this.treelog(treeBlockSet, worldIn, blockpos1.south(), boundsIn);
                        // this.treelog(treeBlockSet, worldIn, blockpos1.west(), boundsIn);
                        // this.treelog(treeBlockSet, worldIn, blockpos1.north(), boundsIn);
                        //this.treelog(treeBlockSet, worldIn, blockpos1.north(2), boundsIn);
                        //this.treelog(treeBlockSet, worldIn, blockposnorth1.north(), boundsIn);
                        // this.treelog(treeBlockSet, worldIn, blockpossouth1.south(), boundsIn);
                        //  this.treelog(treeBlockSet, worldIn, blockposeast1.east(), boundsIn);
                        // this.treelog(treeBlockSet, worldIn, blockposwest1.west(), boundsIn)
                }
                int leavesetting = rand.nextInt(3) + 1;
                {
                    if (leavesetting == 1) {
                        int leavessquarespos = rand.nextInt(1) + 1;
                        for (int posXLeafWidth = (leavessquarespos * -1); posXLeafWidth <= leavessquarespos; ++posXLeafWidth) {//has to do with leaves
                            for (int posZLeafWidthL0 = (leavessquarespos * -1); posZLeafWidthL0 <= leavessquarespos; ++posZLeafWidthL0) {
                                //int leaveheight = 0;//0 lines it up with top log
                                this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 6, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 4, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 2, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                                //this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 1, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);

                                //Y + 0
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight, posZ1 + 1, boundsIn, treeBlockSet);

                                //Y=-2
                                this.leafs(worldIn, posX1 + 2, topTrunkHeight - 2, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 2, topTrunkHeight - 2, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 2, posZ1 - 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 2, posZ1 + 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight - 2, posZ1 + 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight - 2, posZ1 + 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight - 2, posZ1 - 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 2, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 2, posZ1 + 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 2, posZ1 - 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 2, posZ1 - 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 2, topTrunkHeight - 2, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 2, topTrunkHeight - 2, posZ1 + 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 2, topTrunkHeight - 2, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 2, topTrunkHeight - 2, posZ1 + 1, boundsIn, treeBlockSet);

                                //Y=-3
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 3, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight - 3, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 3, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 3, posZ1 + 1, boundsIn, treeBlockSet);

                                //Y=-4
                                int leafsize = rand.nextInt(10) + 1;
                                if (leafsize == 2) {
                                    this.leafs(worldIn, posX1 + 2, topTrunkHeight - 4, posZ1, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 - 2, topTrunkHeight - 4, posZ1, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1, topTrunkHeight - 4, posZ1 + 2, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 + 1, topTrunkHeight - 4, posZ1 + 1, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 + 1, topTrunkHeight - 4, posZ1 + 2, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 + 1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1 - 1, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1 + 2, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 + 2, topTrunkHeight - 4, posZ1 - 1, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 + 2, topTrunkHeight - 4, posZ1 + 1, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 - 2, topTrunkHeight - 4, posZ1 - 1, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 - 2, topTrunkHeight - 4, posZ1 + 1, boundsIn, treeBlockSet);
                                }
                                //Y=-5
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 5, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight - 5, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 5, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 5, posZ1 + 1, boundsIn, treeBlockSet);

                                //Leave Layer On Top of Top Trunk Block Y + 1
                                this.leafs(worldIn, posX1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight + 1, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight + 1, posZ1 + 1, boundsIn, treeBlockSet);

                                //Leave Layer Trunk Y+2
                                this.leafs(worldIn, posX1, topTrunkHeight + 2, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight + 2, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight + 2, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight + 2, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight + 2, posZ1 + 1, boundsIn, treeBlockSet);

                                //Leave Layer Trunk Y-7
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 7, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight - 7, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 7, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 7, posZ1 + 1, boundsIn, treeBlockSet);
                            }
                        /*for (int posXLeafWidth = (leavessquarespos * -1); posXLeafWidth <= leavessquarespos; ++posXLeafWidth) {//has to do with leaves
                            for (int posZLeafWidthL0 = (leavessquarespos * -1); posZLeafWidthL0 <= leavessquarespos; ++posZLeafWidthL0) {
                                int leaveheight = 0;//0 lines it up with top log
                                this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight + leaveheight - 3, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight + leaveheight - 2, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight + leaveheight - 1, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight + leaveheight - 1, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);


                                this.leafs(worldIn, posX1 + 1, topTrunkHeight, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight, posZ1 + 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight, posZ1 + 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight, posZ1 + 1, boundsIn, treeBlockSet);

                                //Leave Layer On Top Trunk Block
                                this.leafs(worldIn, posX1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight + 1, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight + 1, posZ1 + 1, boundsIn, treeBlockSet);

                                //Leave Layer Trunk Y+2
                                this.leafs(worldIn, posX1, topTrunkHeight + 2, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight + 2, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight + 2, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight + 2, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight + 2, posZ1 + 1, boundsIn, treeBlockSet);

                                //Leave Layer Trunk Y-4
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight - 4, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 4, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 4, posZ1 + 1, boundsIn, treeBlockSet);

                            }*/
                        }
                    } else if (leavesetting == 2) {
                        int leavessquarespos = rand.nextInt(1) + 1;
                        for (int posXLeafWidth = (leavessquarespos * -1); posXLeafWidth <= leavessquarespos; ++posXLeafWidth) {//has to do with leaves
                            for (int posZLeafWidthL0 = (leavessquarespos * -1); posZLeafWidthL0 <= leavessquarespos; ++posZLeafWidthL0) {
                                //int leaveheight = 0;//0 lines it up with top log
                                this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 6, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 4, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 2, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 1, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);

                                //Y + 0
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight, posZ1 + 1, boundsIn, treeBlockSet);

                                //Y=-2
                                this.leafs(worldIn, posX1 + 2, topTrunkHeight - 2, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 2, topTrunkHeight - 2, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 2, posZ1 - 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 2, posZ1 + 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight - 2, posZ1 + 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight - 2, posZ1 + 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight - 2, posZ1 - 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 2, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 2, posZ1 + 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 2, posZ1 - 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 2, posZ1 - 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 2, topTrunkHeight - 2, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 2, topTrunkHeight - 2, posZ1 + 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 2, topTrunkHeight - 2, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 2, topTrunkHeight - 2, posZ1 + 1, boundsIn, treeBlockSet);

                                //Y=-3
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 3, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight - 3, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 3, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 3, posZ1 + 1, boundsIn, treeBlockSet);

                                //Y=-4
                                if (rand.nextBoolean()) {
                                    this.leafs(worldIn, posX1 + 2, topTrunkHeight - 4, posZ1, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 - 2, topTrunkHeight - 4, posZ1, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1, topTrunkHeight - 4, posZ1 + 2, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 + 1, topTrunkHeight - 4, posZ1 + 1, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 + 1, topTrunkHeight - 4, posZ1 + 2, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 + 1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1 - 1, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1 + 2, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 + 2, topTrunkHeight - 4, posZ1 - 1, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 + 2, topTrunkHeight - 4, posZ1 + 1, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 - 2, topTrunkHeight - 4, posZ1 - 1, boundsIn, treeBlockSet);
                                    this.leafs(worldIn, posX1 - 2, topTrunkHeight - 4, posZ1 + 1, boundsIn, treeBlockSet);
                                }

                                //Y=-5
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 5, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight - 5, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 5, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 5, posZ1 + 1, boundsIn, treeBlockSet);

                                //Leave Layer On Top of Top Trunk Block Y + 1
                                this.leafs(worldIn, posX1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight + 1, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight + 1, posZ1 + 1, boundsIn, treeBlockSet);

                                //Leave Layer Trunk Y+2
                                this.leafs(worldIn, posX1, topTrunkHeight + 2, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight + 2, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight + 2, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight + 2, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight + 2, posZ1 + 1, boundsIn, treeBlockSet);

                                //Leave Layer Trunk Y-7
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 7, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight - 7, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 7, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 7, posZ1 + 1, boundsIn, treeBlockSet);
                            }
                        }
                    } else if (leavesetting == 3) {
                        int leavessquarespos = rand.nextInt(1) + 1;
                        for (int posXLeafWidth = (leavessquarespos * -1); posXLeafWidth <= leavessquarespos; ++posXLeafWidth) {//has to do with leaves
                            for (int posZLeafWidthL0 = (leavessquarespos * -1); posZLeafWidthL0 <= leavessquarespos; ++posZLeafWidthL0) {
                                //int leaveheight = 0;//0 lines it up with top log
                                this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 6, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 5, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 4, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);

                                this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 3, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 2, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight - 1, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);

                                //Y + 0
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 2, topTrunkHeight, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 2, topTrunkHeight, posZ1, boundsIn, treeBlockSet);

                                this.leafs(worldIn, posX1, topTrunkHeight, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight, posZ1 - 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight, posZ1 + 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight, posZ1 + 2, boundsIn, treeBlockSet);


                                this.leafs(worldIn, posX1 + 1, topTrunkHeight, posZ1 + 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight, posZ1 + 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight, posZ1 - 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight, posZ1 + 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 2, topTrunkHeight, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 2, topTrunkHeight, posZ1 + 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 2, topTrunkHeight, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 2, topTrunkHeight, posZ1 + 1, boundsIn, treeBlockSet);

                                //Y=-1
                                this.leafs(worldIn, posX1 + 2, topTrunkHeight - 1, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 2, topTrunkHeight - 1, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 1, posZ1 - 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 1, posZ1 + 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight - 1, posZ1 + 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight - 1, posZ1 + 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight - 1, posZ1 - 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 1, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 1, posZ1 + 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 1, posZ1 - 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 1, posZ1 - 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 2, topTrunkHeight - 1, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 2, topTrunkHeight - 1, posZ1 + 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 2, topTrunkHeight - 1, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 2, topTrunkHeight - 1, posZ1 + 1, boundsIn, treeBlockSet);

                                //Y=-2
                                this.leafs(worldIn, posX1 + 2, topTrunkHeight - 2, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 2, topTrunkHeight - 2, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 2, posZ1 - 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 2, posZ1 + 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight - 2, posZ1 + 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight - 2, posZ1 + 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight - 2, posZ1 - 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 2, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 2, posZ1 + 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 2, posZ1 - 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 2, posZ1 - 2, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 2, topTrunkHeight - 2, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 2, topTrunkHeight - 2, posZ1 + 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 2, topTrunkHeight - 2, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 2, topTrunkHeight - 2, posZ1 + 1, boundsIn, treeBlockSet);

                                int leaveyroundness = rand.nextInt(3) + 1;
                                {
                                    if (leaveyroundness == 1) {
                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 3, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 3, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight - 3, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight - 3, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 3, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 3, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 3, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 3, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 3, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 3, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 3, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 3, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 3, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 3, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 3, posZ1 + 1, boundsIn, treeBlockSet);
                                    } else if (leaveyroundness == 2) {
                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 3, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 3, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight - 3, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight - 3, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 3, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 3, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 3, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 3, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 3, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 3, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 3, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 3, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 3, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 3, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 3, posZ1 + 1, boundsIn, treeBlockSet);

                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 4, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 4, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight - 4, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 4, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 4, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 4, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 4, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 4, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 4, posZ1 + 1, boundsIn, treeBlockSet);
                                    } else if (leaveyroundness == 3) {
                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 3, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 3, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight - 3, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight - 3, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 3, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 3, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 3, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 3, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 3, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 3, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 3, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 3, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 3, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 3, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 3, posZ1 + 1, boundsIn, treeBlockSet);

                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 4, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 4, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight - 4, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 4, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 4, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 4, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 4, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 4, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 4, posZ1 + 1, boundsIn, treeBlockSet);

                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 5, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 5, posZ1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight - 5, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1, topTrunkHeight - 5, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 5, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 5, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 1, topTrunkHeight - 5, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 5, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 5, posZ1 + 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 5, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 1, topTrunkHeight - 5, posZ1 - 2, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 5, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 + 2, topTrunkHeight - 5, posZ1 + 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 5, posZ1 - 1, boundsIn, treeBlockSet);
                                        this.leafs(worldIn, posX1 - 2, topTrunkHeight - 5, posZ1 + 1, boundsIn, treeBlockSet);
                                    }
                                }


                                //Leave Layer On Top of Top Trunk Block Y + 1
                                this.leafs(worldIn, posX1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight + 1, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight + 1, posZ1 + 1, boundsIn, treeBlockSet);

                                //Leave Layer Trunk Y+2
                                this.leafs(worldIn, posX1, topTrunkHeight + 2, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight + 2, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight + 2, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight + 2, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight + 2, posZ1 + 1, boundsIn, treeBlockSet);

                                //Leave Layer Trunk Y-4
                                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 7, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1 + 1, topTrunkHeight - 7, posZ1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 7, posZ1 - 1, boundsIn, treeBlockSet);
                                this.leafs(worldIn, posX1, topTrunkHeight - 7, posZ1 + 1, boundsIn, treeBlockSet);
                            }
                        }
                        //this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight + leaveheight, 1 + posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        //this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight + leaveheight + 1, 1 + posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        //this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight + leaveheight + 2, 1 + posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        //this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight + leaveheight + 2, 1 + posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        //this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight + leaveheight + 3, 1 + posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        //this.leafs(worldIn, 1 + posX1 - posXLeafWidth, topTrunkHeight + leaveheight - 1, 1 + posZ1 - posZLeafWidthL0 , boundsIn, treeBlockSet);
                        //this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight + leaveheight, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        //this.leafs(worldIn, 1 + posX1 - posXLeafWidth, topTrunkHeight + leaveheight, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                        //this.leafs(worldIn, 1 + posX1 - posXLeafWidth, topTrunkHeight + leaveheight, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);


                        /*if ((posXLeafWidth > -2 || posZLeafWidthL0 > -1) && (posXLeafWidth != -1 || posZLeafWidthL0 != -2)) {
                            leaveheight = 1;//sets leave height?
                            this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight + leaveheight, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                            this.leafs(worldIn, 1 + posX1 - posXLeafWidth, topTrunkHeight + leaveheight, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                            this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight + leaveheight, 1 + posZ1 - posZLeafWidthL0, boundsIn, treeBlockSet);
                            this.leafs(worldIn, 1 + posX1 - posXLeafWidth, topTrunkHeight + leaveheight, 1 + posZ1 - posZLeafWidthL0, boundsIn, treeBlockSet);
                            this.leafs(worldIn, posX1 + posXLeafWidth, topTrunkHeight + leaveheight, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);
                            this.leafs(worldIn, 1 + posX1 - posXLeafWidth, topTrunkHeight + leaveheight, posZ1 + posZLeafWidthL0, boundsIn, treeBlockSet);




                        }*/
                    }
                }
                //Layer1
                //int leaves = rand.nextInt(2);
                //if (leaves == 2) {
                //Leave Layer level Top Trunk Block
                this.leafs(worldIn, posX1 + 1, topTrunkHeight, posZ1, boundsIn, treeBlockSet);
                this.leafs(worldIn, posX1 - 1, topTrunkHeight, posZ1, boundsIn, treeBlockSet);
                this.leafs(worldIn, posX1, topTrunkHeight, posZ1 - 1, boundsIn, treeBlockSet);
                this.leafs(worldIn, posX1, topTrunkHeight, posZ1 + 1, boundsIn, treeBlockSet);
                this.leafs(worldIn, posX1 + 1, topTrunkHeight, posZ1 + 1, boundsIn, treeBlockSet);
                this.leafs(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 1, boundsIn, treeBlockSet);
                this.leafs(worldIn, posX1 - 1, topTrunkHeight, posZ1 - 1, boundsIn, treeBlockSet);
                this.leafs(worldIn, posX1 + 1, topTrunkHeight, posZ1 - 1, boundsIn, treeBlockSet);
                this.leafs(worldIn, posX1 - 1, topTrunkHeight, posZ1 + 1, boundsIn, treeBlockSet);

                //Leave Layer On Top Trunk Block
                this.leafs(worldIn, posX1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                this.leafs(worldIn, posX1 - 1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                this.leafs(worldIn, posX1 + 1, topTrunkHeight + 1, posZ1, boundsIn, treeBlockSet);
                this.leafs(worldIn, posX1, topTrunkHeight + 1, posZ1 - 1, boundsIn, treeBlockSet);
                this.leafs(worldIn, posX1, topTrunkHeight + 1, posZ1 + 1, boundsIn, treeBlockSet);

                //Leave Layer Trunk Y+2
                this.leafs(worldIn, posX1, topTrunkHeight + 2, posZ1, boundsIn, treeBlockSet);
                this.leafs(worldIn, posX1 - 1, topTrunkHeight + 2, posZ1, boundsIn, treeBlockSet);
                this.leafs(worldIn, posX1 + 1, topTrunkHeight + 2, posZ1, boundsIn, treeBlockSet);
                this.leafs(worldIn, posX1, topTrunkHeight + 2, posZ1 - 1, boundsIn, treeBlockSet);
                this.leafs(worldIn, posX1, topTrunkHeight + 2, posZ1 + 1, boundsIn, treeBlockSet);

                //Leave Layer Trunk Y-4
                this.leafs(worldIn, posX1 - 1, topTrunkHeight - 4, posZ1, boundsIn, treeBlockSet);
                this.leafs(worldIn, posX1 + 1, topTrunkHeight - 4, posZ1, boundsIn, treeBlockSet);
                this.leafs(worldIn, posX1, topTrunkHeight - 4, posZ1 - 1, boundsIn, treeBlockSet);
                this.leafs(worldIn, posX1, topTrunkHeight - 4, posZ1 + 1, boundsIn, treeBlockSet);
                //}

                //Layer2
                /*for(int k3 = -1; k3 <= 4; ++k3) { //Changes X
                    for(int j4 = -3; j4 <= 4; ++j4) { //Changes Z
                        if ((k3 != -3 || j4 != -3) && (k3 != -3 || j4 != 4) && (k3 != 4 || j4 != -3) && (k3 != 4 || j4 != 4) && (Math.abs(k3) < 3 || Math.abs(j4) < 3)) {
                            this.leafs(worldIn, posX1 + k3, topTrunkHeight, posZ1 + j4, boundsIn, treeBlockSet);
                        }
                    }
                }*/
                //Layer
                /*for(int l3 = -1; l3 <= 2; ++l3) {
                    for(int k4 = -1; k4 <= 2; ++k4) {
                        if ((l3 < 0 || l3 > 1 || k4 < 0 || k4 > 1) && rand.nextInt(3) <= 0) {
                            int i5 = rand.nextInt(3) + 2; //makes the tree increase both upwards and downwards

                            for(int l2 = 0; l2 < i5; ++l2) { //does nothing
                                this.treelog(treeBlockSet, worldIn, new BlockPos(posX , topTrunkHeight, posZ + 1), boundsIn); //creates Branches, number value of 2 or lower does nothing 5 seems to be a good value
                            }*/


                //LAYER 3
                            /*for(int j5 = -1; j5 <= 1; ++j5) { //changes the x width of the leaves on row 3
                                for(int i3 = -1; i3 <= 1; ++i3) { //changes the z width of the leaves on row 3
                                    this.leafs(worldIn, posX1 + l3 + j5, topTrunkHeight, posZ1 + k4 + i3, boundsIn, treeBlockSet);
                                }
                            }*/
//LAYER 4
                            /*for(int k5 = -2; k5 <= 2; ++k5) { //changes the X width of the leaves on row 4
                                for(int l5 = -2; l5 <= 2; ++l5) { //changes the Z width of the leaves on row 4
                                    if (Math.abs(k5) != 2 || Math.abs(l5) != 2) { //does nothing
                                        this.leafs(worldIn, posX1 + l3 + k5, topTrunkHeight - 1, posZ1 + k4 + l5, boundsIn, treeBlockSet); //topTrunkHeight -1 apears to have no change on the tree
                                    }
                                }
                           // }*/
                // }
                //}
            }

            return true;
            //}
        } else {
            return false;
        }
    }

    //BYGTree Height Maybe?
    private boolean doesTreeFit(IWorldGenerationBaseReader reader, BlockPos blockPos, int height) {
        int x = blockPos.getX();
        int y = blockPos.getY();
        int z = blockPos.getZ();
        BlockPos.Mutable pos = new BlockPos.Mutable();

        for (int yOffset = 0; yOffset <= height + 1; ++yOffset) {
            int distance = 1; //higher the value, lower the density of trees?

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
//