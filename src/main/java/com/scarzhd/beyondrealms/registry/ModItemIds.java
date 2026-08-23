package com.scarzhd.beyondrealms.registry;

import com.scarzhd.beyondrealms.BeyondRealmsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public final class ModItemIds {
    private ModItemIds() {
    }

    public static ResourceKey<Item> create(String name) {
        return ResourceKey.create(
                Registries.ITEM,
                Identifier.fromNamespaceAndPath(BeyondRealmsMod.MOD_ID, name)
        );
    }
}
