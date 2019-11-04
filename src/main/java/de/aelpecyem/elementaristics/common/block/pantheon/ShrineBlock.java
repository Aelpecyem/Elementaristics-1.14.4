package de.aelpecyem.elementaristics.common.block.pantheon;

import de.aelpecyem.elementaristics.common.block.base.TileEntityFacingBaseBlock;
import de.aelpecyem.elementaristics.common.block.tile.ShrineTileEntity;
import de.aelpecyem.elementaristics.common.misc.pantheon.Deity;
import de.aelpecyem.elementaristics.reg.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;

public class ShrineBlock extends TileEntityFacingBaseBlock implements IWaterLoggable {
    public Deity deityBound;

    public ShrineBlock(String name, Deity deityBound) {
        super(name, Block.Properties.create(Material.ROCK, MaterialColor.BLACK).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE).lightValue(5));
        this.deityBound = deityBound;
        this.setDefaultState(this.getDefaultState().with(BlockStateProperties.WATERLOGGED, false));
        ModBlocks.SHRINES.add(this);
    }

    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.WATERLOGGED);
        super.fillStateContainer(builder);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(BlockStateProperties.WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER || ifluidstate.getFluid() == Fluids.WATER);
    }

    public IFluidState getFluidState(BlockState state) {
        return state.get(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
        return true;
    }

    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(BlockStateProperties.WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        switch (type) {
            case LAND:
                return false;
            case WATER:
                return worldIn.getFluidState(pos).isTagged(FluidTags.WATER);
            case AIR:
                return false;
            default:
                return false;
        }
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
