package de.aelpecyem.elementaristics.common.item.essence;

import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.misc.aspect.Aspect;
import de.aelpecyem.elementaristics.reg.ModItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class EssenceItem extends Item {
    public static final String ASPECT_TAG = "aspect";

    public EssenceItem() {
        super(new Item.Properties().maxStackSize(64));
        setRegistryName(new ResourceLocation(Elementaristics.MODID, "essence"));
    }


    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (getAspect(stack) != null)
            tooltip.add(new TranslationTextComponent(getAspect(stack).getUnlocalizedName()).setStyle(new Style().setItalic(true).setColor(TextFormatting.DARK_GRAY)));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    public static ItemStack withAspect(int count, Aspect aspect){
        ItemStack stack = new ItemStack(ModItems.essence, count);
        EssenceItem.setAspect(aspect, stack);
        return stack;
    }

    public static ItemStack withAspect(Aspect aspect){
        return withAspect(1, aspect);
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

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        Elementaristics.proxy.giveVision("test");
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    public static ItemStack setAspect(Aspect aspect, ItemStack stack){
        setUp(stack);
        stack.getTag().putString(ASPECT_TAG, aspect.getName().toString());
        return stack;
    }

    public static Aspect getAspect(ItemStack stack) {
        return setUp(stack) ? Aspect.ASPECTS.get(new ResourceLocation(stack.getTag().getString(ASPECT_TAG))) : null;
    }
}
