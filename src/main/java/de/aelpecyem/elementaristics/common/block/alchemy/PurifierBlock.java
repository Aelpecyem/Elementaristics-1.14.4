package de.aelpecyem.elementaristics.common.block.alchemy;

import de.aelpecyem.elementaristics.common.block.base.TileEntityFacingBaseBlock;
import de.aelpecyem.elementaristics.common.block.tile.PurifierTileEntity;
import de.aelpecyem.elementaristics.common.network.PacketHandler;
import de.aelpecyem.elementaristics.util.InventoryUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IEnviromentBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;
import java.util.Random;

public class PurifierBlock extends TileEntityFacingBaseBlock {
    public PurifierBlock() {
        super("purifier", Block.Properties.create(Material.ROCK, MaterialColor.GRAY).hardnessAndResistance(5).harvestLevel(1).harvestTool(ToolType.PICKAXE));
    }

    @Override
    public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.LIT);
        super.fillStateContainer(builder);
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

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return super.getStateForPlacement(context).with(BlockStateProperties.LIT, false);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.get(BlockStateProperties.WATERLOGGED) && stateIn.get(BlockStateProperties.LIT)) {
            worldIn.setBlockState(currentPos, stateIn.with(BlockStateProperties.LIT, false), 3);
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
                    worldIn.setBlockState(pos, state.with(BlockStateProperties.LIT, true));
                    worldIn.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
                    player.getHeldItem(handIn).damageItem(1, player, p -> p.sendBreakAnimation(handIn));
                    System.out.println("awoo!");
                } else if (player.isSneaking() && !InventoryUtil.check(itemHandler, slot -> !itemHandler.getStackInSlot(slot).isEmpty())) {
                    worldIn.setBlockState(pos, state.with(BlockStateProperties.LIT, false));
                    System.out.println("awoo");
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


    @Override
    public int getLightValue(BlockState state, IEnviromentBlockReader world, BlockPos pos) {
        return state.get(BlockStateProperties.LIT) ? 10 : 0;
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
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PurifierTileEntity();
    }
}
