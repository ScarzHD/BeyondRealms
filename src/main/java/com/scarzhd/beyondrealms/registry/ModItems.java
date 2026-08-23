package com.scarzhd.beyondrealms.registry;

import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public final class ModItems {
    public static final ResourceKey<Item> ETERNIUM_INGOT_KEY = ModItemIds.create("eternium_ingot");
    public static final ResourceKey<Item> TITAN_CORE_KEY = ModItemIds.create("titan_core");

    public static final Item ETERNIUM_INGOT = register(
            ETERNIUM_INGOT_KEY,
            Item::new,
            new Item.Properties()
    );

    public static final Item TITAN_CORE = register(
            TITAN_CORE_KEY,
            Item::new,
            new Item.Properties().stacksTo(16)
    );

    private ModItems() {
    }

    private static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
        Item item = itemFactory.apply(properties.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }

    public static void initialize() {
        // Triggers static registration.
    }
}
