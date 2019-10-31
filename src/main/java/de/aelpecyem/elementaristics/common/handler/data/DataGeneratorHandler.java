package de.aelpecyem.elementaristics.common.handler.data;

import de.aelpecyem.elementaristics.reg.ModRecipes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import java.util.function.Consumer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGeneratorHandler {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(new RecipeProvider(generator) {
            @Override
            protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
                ModRecipes.initCraftingRecipes(consumer);
            }
        });
        //todo, generate proper loot tables automatically
    }
}
