package de.aelpecyem.elementaristics.common.item.crafting;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.misc.processing.AspectProcess;
import de.aelpecyem.elementaristics.common.misc.processing.AspectProcessPart;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class AlchemicalMatterItem extends Item {
    public static final String PROCESS_TAG = "process";
    public static final String PROCESS_STAGE_TAG = "process_stage";

    public AlchemicalMatterItem() {
        super(new Properties().maxStackSize(64));
        setRegistryName(new ResourceLocation(Elementaristics.MODID, "alchemical_matter"));
    }


    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (stack.hasTag()) {
            //display the steps here
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }


    public static boolean setUp(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains(PROCESS_TAG) && stack.getTag().contains(PROCESS_STAGE_TAG)) {
            return true;
        }
        if (!stack.hasTag()) {
            stack.setTag(new CompoundNBT());
            stack.getTag().putString(PROCESS_TAG, "");
            stack.getTag().putInt(PROCESS_STAGE_TAG, 0);
        }
        return false;
    }

    public static ItemStack addSuccessfulProcess(ItemStack stack, AspectProcessPart part) {
        setProcessStage(stack, getProcessStage(stack) + 1);
        String oldProcess = getProcessString(stack);
        String newProcess = oldProcess + part.serialize();
        setProcessString(stack, newProcess);
        return stack;
    }

    public static String getProcessString(ItemStack stack) {
        setUp(stack);
        return stack.getTag().getString(PROCESS_TAG);
    }

    public static List<AspectProcessPart> getProcessesDone(ItemStack stack) {
        return AspectProcess.Util.getCompletedProcessesFor(stack);
    }

    public static int getProcessStage(ItemStack stack) {
        setUp(stack);
        return stack.getTag().getInt(PROCESS_STAGE_TAG);
    }

    public static void setProcessStage(ItemStack stack, int stage) {
        stack.getTag().putInt(PROCESS_STAGE_TAG, stage);
    }

    public static void setProcessString(ItemStack stack, String newProcess) {
        stack.getTag().putString(PROCESS_TAG, newProcess);
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
