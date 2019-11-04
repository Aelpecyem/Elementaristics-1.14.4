package de.aelpecyem.elementaristics.common.item.crafting;

import de.aelpecyem.elementaristics.common.item.BaseItem;
import de.aelpecyem.elementaristics.common.misc.processing.AspectProcess;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class AlchemicalMatterItem extends BaseItem {
    public static final String PROCESS_TAG = "process";
    public static final String PROCESS_STAGE_TAG = "process_stage";
    public static final String COLOR_TAG = "color";

    public AlchemicalMatterItem() {
        super("alchemical_matter");
    }


    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (stack.hasTag()) {
            tooltip.add(stack.getTag().toFormattedComponent(" ", 2));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    public static boolean setUp(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains(PROCESS_TAG) && stack.getTag().contains(PROCESS_STAGE_TAG) && stack.getTag().contains(COLOR_TAG)) {
            return true;
        }
        if (!stack.hasTag()) {
            stack.setTag(new CompoundNBT());
            stack.getTag().put(PROCESS_TAG, new CompoundNBT());
            stack.getTag().putInt(PROCESS_STAGE_TAG, 0);
            stack.getTag().putInt(COLOR_TAG, 16777215);
        }
        return false;
    }
    //have them in nbt

    public static ItemStack addSuccessfulProcess(ItemStack stack, AspectProcess part) {
        CompoundNBT processTag = getProcessTag(stack);
        processTag.put("process" + getProcessStage(stack), part.write(new CompoundNBT()));
        setProcessStage(stack, getProcessStage(stack) + 1);
        setProcessTag(stack, processTag);
        return stack;
    }

    public static CompoundNBT getProcessTag(ItemStack stack) {
        setUp(stack);
        return stack.getTag().getCompound(PROCESS_TAG);
    }


    public static ItemStack setProcessTag(ItemStack stack, CompoundNBT nbt) {
        setUp(stack);
        stack.getTag().put(PROCESS_TAG, nbt);
        return stack;
    }

    public static List<AspectProcess> getProcessesDone(ItemStack stack) {
        List<AspectProcess> processes = new ArrayList<>();
        int count = getProcessStage(stack);
        CompoundNBT from = getProcessTag(stack);
        for (int i = 0; i < count; i++) {
            processes.add(AspectProcess.read(from.getCompound("process" + i)));
        }
        return processes;
    }

    public static int getProcessStage(ItemStack stack) {
        setUp(stack);
        return stack.getTag().getInt(PROCESS_STAGE_TAG);
    }

    public static void setProcessStage(ItemStack stack, int stage) {
        setUp(stack);
        stack.getTag().putInt(PROCESS_STAGE_TAG, stage);
    }

    public static void setProcessString(ItemStack stack, String newProcess) {
        stack.getTag().putString(PROCESS_TAG, newProcess);
    }

    public static int getColor(ItemStack stack) {
        setUp(stack);
        return stack.getTag().getInt(COLOR_TAG);
    }

    public static void setColor(ItemStack stack, int color) {
        setUp(stack);
        stack.getTag().putInt(COLOR_TAG, Math.abs(color));
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.EAT;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
