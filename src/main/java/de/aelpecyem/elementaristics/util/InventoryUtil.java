package de.aelpecyem.elementaristics.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.function.Predicate;

public class InventoryUtil {
    public static boolean check(IItemHandler inventory, Predicate<Integer> predicate) {
        return slotForCheck(inventory, predicate) > -1;
    }

    public static int slotForCheck(IItemHandler inventory, Predicate<Integer> predicate) {
        for (int i = 0; i < inventory.getSlots(); i++) {
            if (predicate.test(i))
                return i;
        }
        return -1;
    }

    public static boolean interactWithTileInventory(TileEntity tile, IItemHandler itemHandler, PlayerEntity player, Hand handIn, int extractAmount) {
        ItemStack heldItem = player.getHeldItem(handIn);
        int targetSlot = getNextSlotWithSpace(heldItem, itemHandler);
        ItemStack insertStackLeft = targetSlot < 0 || !itemHandler.isItemValid(targetSlot, heldItem) ? heldItem : itemHandler.insertItem(targetSlot, heldItem, false);
        if (heldItem.isEmpty() || targetSlot < 0) {
            targetSlot = getNextFilledSlot(itemHandler);
            if (targetSlot > -1)
                ItemHandlerHelper.giveItemToPlayer(player, itemHandler.extractItem(targetSlot, extractAmount, false));
        } else {
            player.setHeldItem(handIn, insertStackLeft);
        }

        player.world.updateComparatorOutputLevel(tile.getPos(), null);
        tile.markDirty();
        return true;
    }

    public static void dropAllItemsFromInventory(TileEntity tile, IItemHandler itemHandler, World world) {
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            if (!itemHandler.getStackInSlot(i).isEmpty())
                InventoryHelper.spawnItemStack(world, tile.getPos().getX() + 0.5, tile.getPos().getY() + 0.5, tile.getPos().getZ() + 0.5, itemHandler.getStackInSlot(i));
        }
    }

    public static boolean areStacksEqualIgnoreCount(ItemStack stack, ItemStack other) {
        return stack.getItem() == other.getItem() && ItemStack.areItemStackTagsEqual(stack, other);
    }

    public static int getNextEmptySlot(IItemHandler itemHandler) {
        return slotForCheck(itemHandler, slot -> itemHandler.getStackInSlot(slot).isEmpty());
    }

    public static int getNextSlotWithSpace(ItemStack stackAdded, IItemHandler itemHandler) {
        return slotForCheck(itemHandler, slot -> (itemHandler.getStackInSlot(slot).isEmpty() || (areStacksEqualIgnoreCount(itemHandler.getStackInSlot(slot), stackAdded) && itemHandler.getStackInSlot(slot).isStackable())));
    }

    public static int getNextFilledSlot(IItemHandler itemHandler) {
        return slotForCheck(itemHandler, slot -> !itemHandler.getStackInSlot(slot).isEmpty());
    }

    public static boolean isEmpty(IItemHandler inventory) {
        return !check(inventory, slot -> !inventory.getStackInSlot(slot).isEmpty());
    }
}
