package de.aelpecyem.elementaristics.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.aelpecyem.elementaristics.Elementaristics;
import de.aelpecyem.elementaristics.common.block.base.SlabBaseBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.BlockStateProperty;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;
import net.minecraft.world.storage.loot.functions.ExplosionDecay;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ElemLootTableProvider implements IDataProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private final DataGenerator generator;

    public ElemLootTableProvider(DataGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void act(DirectoryCache cache) throws IOException {
        Map<ResourceLocation, LootTable.Builder> tables = new HashMap<>();
        for (Block b : ForgeRegistries.BLOCKS) {
            if (!Elementaristics.MODID.equals(b.getRegistryName().getNamespace()))
                continue;
            if (b instanceof SlabBaseBlock) {
                tables.put(b.getRegistryName(), genSlab(b));
            } else {
                tables.put(b.getRegistryName(), genRegular(b));
            }
        }
        for (Map.Entry<ResourceLocation, LootTable.Builder> e : tables.entrySet()) {
            Path path = getPath(generator.getOutputFolder(), e.getKey());
            IDataProvider.save(GSON, cache, LootTableManager.toJson(e.getValue().setParameterSet(LootParameterSets.BLOCK).build()), path);
        }
    }

    public static Path getPath(Path root, ResourceLocation id) {
        return root.resolve("data/" + id.getNamespace() + "/loot_tables/blocks/" + id.getPath() + ".json");
    }

    public static LootTable.Builder empty() {
        return LootTable.builder();
    }

    public static LootTable.Builder genSlab(Block b) {
        LootEntry.Builder<?> entry = ItemLootEntry.builder(b)
                .acceptFunction(SetCount.func_215932_a(ConstantRange.of(2))
                        .acceptCondition(BlockStateProperty.builder(b).with(SlabBlock.TYPE, SlabType.DOUBLE)))
                .acceptFunction(ExplosionDecay.func_215863_b());
        return LootTable.builder().addLootPool(LootPool.builder().name("main").rolls(ConstantRange.of(1)).addEntry(entry));
    }

    public static LootTable.Builder genRegular(Block b) {
        LootEntry.Builder<?> entry = ItemLootEntry.builder(b);
        LootPool.Builder pool = LootPool.builder().name("main").rolls(ConstantRange.of(1)).addEntry(entry)
                .acceptCondition(SurvivesExplosion.builder());
        return LootTable.builder().addLootPool(pool);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Elementaristics loot tables";
    }
}
