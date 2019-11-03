package de.aelpecyem.elementaristics.common.misc.processing;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AspectProcess {
    public AspectProcessType type;
    public List<ItemStack> stacksUsed;

    public AspectProcess(AspectProcessType type, ItemStack... stacksUsed) {
        this.type = type;
        this.stacksUsed = Arrays.asList(stacksUsed);
    }

    public AspectProcess(AspectProcessType type, List<ItemStack> stacksUsed) {
        this.type = type;
        this.stacksUsed = stacksUsed;
    }

    public AspectProcessType getType() {
        return type;
    }

    public void setType(AspectProcessType type) {
        this.type = type;
    }

    public List<ItemStack> getStacksUsed() {
        return stacksUsed;
    }

    public CompoundNBT write(CompoundNBT to) {
        to.putString("type", type.getName().toString());
        int count = stacksUsed.size();
        to.putInt("stackCount", count);
        for (int i = 0; i < count; i++) {
            to.put("stack" + i, stacksUsed.get(i).write(new CompoundNBT()));
        }
        return to;
    }

    public static AspectProcess read(CompoundNBT from) {
        AspectProcessType type = AspectProcessType.PROCESS_TYPES.get(ResourceLocation.tryCreate(from.getString("type")));
        List<ItemStack> stacksUsed = new ArrayList<>();
        int count = from.getInt("stackCount");
        for (int i = 0; i < count; i++) {
            stacksUsed.add(ItemStack.read(from.getCompound("stack" + i)));
        }
        return new AspectProcess(type, stacksUsed);
    }
}
