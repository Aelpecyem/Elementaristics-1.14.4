package de.aelpecyem.elementaristics.common.item.essence;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.misc.aspect.Aspect;
import de.aelpecyem.elementaristics.reg.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ItemEssence extends Item {
    public static final String ASPECT_TAG = "aspect";

    public ItemEssence() {
        super(new Item.Properties().maxStackSize(64));
        setRegistryName(new ResourceLocation(Elementaristics.MODID, "essence"));
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        return new TranslationTextComponent(this.getTranslationKey(stack) + (getAspect(stack) != null ? "." + getAspect(stack).getName() : ""), new Object[0]); //super.getDisplayName(stack);
    }

    public static ItemStack withAspect(int count, Aspect aspect){
        ItemStack stack = new ItemStack(ModItems.essence, count);
        ItemEssence.setAspect(aspect, stack);
        return stack;
    }

    public static ItemStack withAspect(Aspect aspect){
        return withAspect(1, aspect);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        System.out.println("Aspect String: " + playerIn.getHeldItem(handIn).getTag().getString(ASPECT_TAG));
        System.out.println("Aspect: " + ItemEssence.getAspect(playerIn.getHeldItem(handIn)));
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
