package de.aelpecyem.elementaristics.common.block;


import de.aelpecyem.elementaristics.Elementaristics;
import javafx.scene.paint.Material;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.client.model.ModelLoader;

public class BlockBase extends Block{
    protected String name;
    public BlockBase(String name, Properties properties) {
        super(properties);
        this.name = name;
        setRegistryName(new ResourceLocation(Elementaristics.MODID, name));
    }
}
