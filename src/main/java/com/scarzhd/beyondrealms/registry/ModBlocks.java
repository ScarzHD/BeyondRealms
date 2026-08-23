package com.scarzhd.beyondrealms.registry;

import com.scarzhd.beyondrealms.BeyondRealmsMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class ModBlocks {
    public static final Block ETERNIUM_ORE = register(
            "eternium_ore",
            BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
                    .strength(5.5F, 7.0F)
                    .requiresCorrectToolForDrops()
    );

    public static final Block DEEPSLATE_ETERNIIUM_ORE = register(
            "deepslate_eternium_ore",
            BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE)
                    .strength(6.5F, 8.0F)
                    .requiresCorrectToolForDrops()
    );

    public static final Block ETERNIUM_BLOCK = register(
            "eternium_block",
            BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)
                    .strength(8.0F, 12.0F)
                    .requiresCorrectToolForDrops()
    );

    private ModBlocks() {
    }

    private static Block register(String name, BlockBehaviour.Properties properties) {
        Identifier id = BeyondRealmsMod.id(name);
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, id);
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);

        Block block = new Block(properties.setId(blockKey));
        Registry.register(BuiltInRegistries.BLOCK, blockKey, block);

        BlockItem blockItem = new BlockItem(
                block,
                new Item.Properties().useBlockDescriptionPrefix().setId(itemKey)
        );
        Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        return block;
    }

    public static void initialize() {
        // Static registration is enough.
    }
}
