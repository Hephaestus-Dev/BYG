package corgiaoc.byg.common.world.feature.overworld.pumpkins;

import com.mojang.serialization.Codec;
import corgiaoc.byg.common.world.feature.FeatureUtil;
import corgiaoc.byg.common.world.feature.config.PumpkinConfig;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class LargePumpkin1 extends Feature<PumpkinConfig> {

    public LargePumpkin1(Codec<PumpkinConfig> configIn) {
        super(configIn);
    }

    @Override
    public boolean func_241855_a(ISeedReader world, ChunkGenerator generator, Random random, BlockPos pos, PumpkinConfig config) {
        BlockPos.Mutable mutable = new BlockPos.Mutable().setPos(pos);
        BlockState pumpkinState = config.getPumpkinProvider().getBlockState(random, pos);
        BlockState logState = config.getStemProvider().getBlockState(random, pos);

        int posX = pos.getX();
        int posY = pos.getY();
        int posZ = pos.getZ();

        if (posY - 1 < world.getHeight()) {
            setPumpkinState(mutable.setPos(posX, posY + 1, posZ), pumpkinState, world);
            setPumpkinState(mutable.setPos(posX, posY + 1, posZ), pumpkinState, world);
            setPumpkinState(mutable.setPos(posX - 1, posY, posZ), pumpkinState, world);
            setPumpkinState(mutable.setPos(posX + 1, posY, posZ), pumpkinState, world);
            setPumpkinState(mutable.setPos(posX + 1, posY, posZ + 1), pumpkinState, world);
            setPumpkinState(mutable.setPos(posX, posY, posZ + 1), pumpkinState, world);
            setPumpkinState(mutable.setPos(posX, posY, posZ - 1), pumpkinState, world);
        }
        return true;
    }

    private void setPumpkinState(BlockPos pos, BlockState pumpkinState, ISeedReader world) {
        if (world.isAirBlock(pos) || FeatureUtil.isPlant(world, pos)) {
            world.setBlockState(pos, pumpkinState, 2);
        }
    }
}