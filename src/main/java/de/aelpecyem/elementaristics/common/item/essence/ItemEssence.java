package de.aelpecyem.elementaristics.common.item.essence;

import de.aelpecyem.elementaristics.common.misc.aspect.Aspect;
import de.aelpecyem.elementaristics.common.item.ItemBase;
import de.aelpecyem.elementaristics.reg.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ItemEssence extends ItemBase {
    public static final String ASPECT_TAG = "aspect";

    public ItemEssence() {
        super("essence");
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        return new TranslationTextComponent(this.getTranslationKey(stack) + (getAspect(stack) != null ? "." + getAspect(stack).getName() : ""), new Object[0]); //super.getDisplayName(stack);
    }

    public static ItemStack withAspect(int count, Aspect aspect){
        return new ItemStack(ModItems.essence, count, getAspectTag(aspect));
    }

    public static ItemStack withAspect(Aspect aspect){
        return withAspect(1, aspect);
    }

    public static CompoundNBT getAspectTag(Aspect aspect){
        CompoundNBT tag = new CompoundNBT();
        tag.putString(ASPECT_TAG, aspect.getName());
        return tag;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        System.out.println("Aspect String: " + playerIn.getHeldItem(handIn).getTag().getString(ASPECT_TAG));
        System.out.println("Aspect: " + ItemEssence.getAspect(playerIn.getHeldItem(handIn)));
        System.out.println("Aspect Tag: " + getAspectTag(Aspect.LIGHT));
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    public static boolean setUp(ItemStack stack){
        if (stack.hasTag() && stack.getTag().contains(ASPECT_TAG)){
            return true;
        }
        if (!stack.hasTag()){
            stack.setTag(new CompoundNBT());
            stack.getTag().putString(ASPECT_TAG, "");
        }
        if (!stack.getTag().contains(ASPECT_TAG)){
            stack.getTag().putString(ASPECT_TAG, "");
        }
        return false;
    }

    public static ItemStack setAspect(Aspect aspect, ItemStack stack){
        setUp(stack);
        stack.getTag().putString(ASPECT_TAG, aspect.getName());
        return stack;
    }

    public static Aspect getAspect(ItemStack stack) {
        return setUp(stack) ? Aspect.ASPECTS.get(stack.getTag().getString(ASPECT_TAG)) : null;
    }
}
