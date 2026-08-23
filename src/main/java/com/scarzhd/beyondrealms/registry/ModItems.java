package com.scarzhd.beyondrealms.registry;

import java.util.function.Function;

import com.scarzhd.beyondrealms.BeyondRealmsMod;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

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

    public static final ResourceKey<CreativeModeTab> BEYOND_REALMS_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(),
            Identifier.fromNamespaceAndPath(BeyondRealmsMod.MOD_ID, "beyond_realms")
    );

    public static final CreativeModeTab BEYOND_REALMS_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(TITAN_CORE))
            .title(Component.translatable("creativeTab.beyondrealms"))
            .displayItems((parameters, output) -> {
                output.accept(ETERNIUM_INGOT);
                output.accept(TITAN_CORE);
            })
            .build();

    private ModItems() {
    }

    private static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
        Item item = itemFactory.apply(properties.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }

    public static void initialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, BEYOND_REALMS_TAB_KEY, BEYOND_REALMS_TAB);
    }
}
