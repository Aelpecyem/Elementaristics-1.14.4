package de.aelpecyem.elementaristics.common.block.pantheon;

import de.aelpecyem.elementaristics.common.block.base.TileEntityFacingBaseBlock;
import de.aelpecyem.elementaristics.common.block.tile.ShrineTileEntity;
import de.aelpecyem.elementaristics.common.misc.pantheon.Deity;
import de.aelpecyem.elementaristics.reg.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class ShrineBlock extends TileEntityFacingBaseBlock {
    public Deity deityBound;

    public ShrineBlock(String name, Deity deityBound) {
        super(name, Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE).lightValue(5));
        this.deityBound = deityBound;
        this.setDefaultState(this.getDefaultState().with(BlockStateProperties.WATERLOGGED, false));
        ModBlocks.SHRINES.add(this);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Block.makeCuboidShape(1, 0, 1, 15, 15, 15);
    }

    @Override
    public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return Block.makeCuboidShape(0, 0, 0, 0, 0, 0);
    }


    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        ShrineTileEntity shrine = new ShrineTileEntity();
        shrine.deityBound = deityBound;
        return shrine;
    }
}
