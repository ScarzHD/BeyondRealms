package com.scarzhd.beyondrealms.equipment;

import java.util.Map;

import com.scarzhd.beyondrealms.BeyondRealmsMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

public final class EterniumArmorMaterials {
    public static final int ETERNIUM_BASE_DURABILITY = 55;
    public static final int GODFORGED_BASE_DURABILITY = 120;

    public static final TagKey<Item> REPAIRS_ETERNIIUM_ARMOR = TagKey.create(
            BuiltInRegistries.ITEM.key(), BeyondRealmsMod.id("repairs_eternium_armor")
    );
    public static final TagKey<Item> REPAIRS_GODFORGED_ARMOR = TagKey.create(
            BuiltInRegistries.ITEM.key(), BeyondRealmsMod.id("repairs_godforged_armor")
    );

    public static final ResourceKey<EquipmentAsset> ETERNIUM_ASSET = ResourceKey.create(
            EquipmentAssets.ROOT_ID, BeyondRealmsMod.id("eternium")
    );
    public static final ResourceKey<EquipmentAsset> GODFORGED_ASSET = ResourceKey.create(
            EquipmentAssets.ROOT_ID, BeyondRealmsMod.id("godforged")
    );

    public static final ArmorMaterial ETERNIUM = new ArmorMaterial(
            ETERNIUM_BASE_DURABILITY,
            Map.of(
                    ArmorType.HELMET, 4,
                    ArmorType.CHESTPLATE, 9,
                    ArmorType.LEGGINGS, 7,
                    ArmorType.BOOTS, 4
            ),
            35,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            4.0F,
            0.12F,
            REPAIRS_ETERNIIUM_ARMOR,
            ETERNIUM_ASSET
    );

    public static final ArmorMaterial GODFORGED = new ArmorMaterial(
            GODFORGED_BASE_DURABILITY,
            Map.of(
                    ArmorType.HELMET, 8,
                    ArmorType.CHESTPLATE, 16,
                    ArmorType.LEGGINGS, 12,
                    ArmorType.BOOTS, 8
            ),
            60,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            8.0F,
            0.30F,
            REPAIRS_GODFORGED_ARMOR,
            GODFORGED_ASSET
    );

    private EterniumArmorMaterials() {
    }
}
