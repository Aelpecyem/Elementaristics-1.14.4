package de.aelpecyem.elementaristics.common.block.alchemy;

import de.aelpecyem.elementaristics.common.block.base.TileEntityFacingBaseBlock;
import de.aelpecyem.elementaristics.common.block.tile.BoilingBasingTileEntity;
import de.aelpecyem.elementaristics.common.network.PacketHandler;
import de.aelpecyem.elementaristics.util.InventoryUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.Random;

public class BoilingBasinBlock extends TileEntityFacingBaseBlock {
    public BoilingBasinBlock() {
        super("boiling_basin", Block.Properties.create(Material.ROCK, MaterialColor.GRAY).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE).lightValue(8));
        this.setDefaultState(this.stateContainer.getBaseState().with(BlockStateProperties.LIT, false));
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.LIT);
        super.fillStateContainer(builder);
    }

    @Override
    public int getLightValue(BlockState state) {
        return state.get(BlockStateProperties.LIT) ? super.getLightValue(state) : 0;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            InventoryUtil.dropAllItemsFromInventory(tileentity, tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).orElse(null), worldIn);
            worldIn.updateComparatorOutputLevel(pos, this);
            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, IFluidState fluidStateIn) {
        if (!state.get(BlockStateProperties.WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER) {
            if (!worldIn.isRemote()) {
                worldIn.setBlockState(pos, state.with(BlockStateProperties.WATERLOGGED, true).with(BlockStateProperties.LIT, false), 2); //change this bit later
                worldIn.getPendingFluidTicks().scheduleTick(pos, fluidStateIn.getFluid(), fluidStateIn.getFluid().getTickRate(worldIn));
            }
            return true;
        } else {
            return false;
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return super.getStateForPlacement(context).with(BlockStateProperties.LIT, false);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(BlockStateProperties.WATERLOGGED) && stateIn.get(BlockStateProperties.LIT)) {
            changeLitState(worldIn, currentPos, stateIn, false);
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {

            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile != null) {
                IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, hit.getFace()).orElse(null);
                if (!state.get(BlockStateProperties.WATERLOGGED) && !state.get(BlockStateProperties.LIT) && player.getHeldItem(handIn).getItem() instanceof FlintAndSteelItem) {
                    changeLitState(worldIn, pos, state, true);
                    worldIn.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
                    player.getHeldItem(handIn).damageItem(1, player, p -> p.sendBreakAnimation(handIn));
                } else if (player.isSneaking() && !InventoryUtil.check(itemHandler, slot -> !itemHandler.getStackInSlot(slot).isEmpty())) {
                    changeLitState(worldIn, pos, state, false);
                } else {
                    if (itemHandler != null) {
                        return InventoryUtil.interactWithTileInventory(tile, itemHandler, player, handIn, player.isSneaking() ? 64 : 1);
                    }
                    PacketHandler.Util.updateTE(tile);
                }
            }
        }
        return true;
    }

    public void changeLitState(IWorld world, BlockPos pos, BlockState state, boolean lit) {
        world.setBlockState(pos, state.with(BlockStateProperties.LIT, lit), 2);
    }

    @Override
    public int getLightValue(BlockState state, IEnviromentBlockReader world, BlockPos pos) {
        return state.get(BlockStateProperties.LIT) ? super.getLightValue(state, world, pos) : 0;
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (stateIn.get(BlockStateProperties.LIT)) {
            double posX = (double) pos.getX() + 0.5D;
            double posY = (double) pos.getY() + 0.03F;
            double posZ = (double) pos.getZ() + 0.5D;
            if (rand.nextDouble() < 0.1D) {
                worldIn.playSound(posX, posY, posZ, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 0.5F, 0.7F, false);
            }
            double xOffset = rand.nextGaussian() / 10F;
            double yOffset = rand.nextFloat() / 10F;
            double zOffset = rand.nextGaussian() / 10F;
            worldIn.addParticle(ParticleTypes.FLAME, posX + xOffset, posY + yOffset, posZ + zOffset, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Block.makeCuboidShape(3, 0, 3, 13, 11, 13);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    public boolean doesSideBlockRendering(BlockState state, IEnviromentBlockReader world, BlockPos pos, Direction face) {
        return false;
    }

    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }

    public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return false;
    }

    public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return false;
    }

    public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
        return false;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new BoilingBasingTileEntity();
    }
}
