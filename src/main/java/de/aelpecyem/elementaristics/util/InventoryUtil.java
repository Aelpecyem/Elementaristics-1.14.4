package de.aelpecyem.elementaristics.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.function.Predicate;

public class InventoryUtil {
    public static boolean check(IItemHandler inventory, Predicate<ItemStack> predicate) {
        return slotForCheck(inventory, predicate) > -1;
    }

    public static int slotForCheck(IItemHandler inventory, Predicate<ItemStack> predicate) {
        for (int i = 0; i < inventory.getSlots(); i++) {
            if (predicate.test(inventory.getStackInSlot(i)))
                return i;
        }
        return -1;
    }


    public static boolean interactWithTileInventory(TileEntity tile, IItemHandler itemHandler, PlayerEntity player, Hand handIn, int extractAmount) {
        ItemStack heldItem = player.getHeldItem(handIn);
        int targetSlot = getNextSlotWithSpace(heldItem, itemHandler);
        ItemStack insertStackLeft = targetSlot < 0 ? heldItem : itemHandler.insertItem(targetSlot, heldItem, false);
        if (targetSlot < 0) {
            targetSlot = getNextFilledSlot(itemHandler);
            ItemHandlerHelper.giveItemToPlayer(player, itemHandler.extractItem(targetSlot, extractAmount, false));
        } else {
            player.setHeldItem(handIn, insertStackLeft);
        }

        player.world.updateComparatorOutputLevel(tile.getPos(), null);
        tile.markDirty();
        return true;
    }

    public static boolean areStacksEqualIgnoreCount(ItemStack stack, ItemStack other) {
        return stack.getItem() == other.getItem() && ItemStack.areItemStackTagsEqual(stack, other);
    }

    public static int getNextEmptySlot(IItemHandler itemHandler) {
        return slotForCheck(itemHandler, stack -> stack.isEmpty());
    }

    public static int getNextSlotWithSpace(ItemStack stackAdded, IItemHandler itemHandler) {
        return slotForCheck(itemHandler, stack -> stack.isEmpty() || (areStacksEqualIgnoreCount(stack, stackAdded) && stack.isStackable()));
    }

    public static int getNextFilledSlot(IItemHandler itemHandler) {
        return slotForCheck(itemHandler, stack -> !stack.isEmpty());
    }
}
