package com.scarzhd.beyondrealms.registry;

import java.util.function.Function;

import com.scarzhd.beyondrealms.BeyondRealmsMod;
import com.scarzhd.beyondrealms.equipment.EterniumArmorMaterials;
import com.scarzhd.beyondrealms.item.AbilityRelicItem;
import com.scarzhd.beyondrealms.item.BossSigilItem;
import com.scarzhd.beyondrealms.item.GodGearCatalystItem;
import com.scarzhd.beyondrealms.item.RiftKeyItem;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.block.Block;

public final class ModItems {
    public static final TagKey<Block> INCORRECT_FOR_ETERNIUM_TOOL = TagKey.create(
            Registries.BLOCK, BeyondRealmsMod.id("incorrect_for_eternium_tool")
    );

    public static final ToolMaterial ETERNIUM_TOOL_MATERIAL = new ToolMaterial(
            INCORRECT_FOR_ETERNIUM_TOOL,
            4096,
            12.0F,
            6.0F,
            35,
            EterniumArmorMaterials.REPAIRS_ETERNIIUM_ARMOR
    );

    public static final ToolMaterial GODFORGED_TOOL_MATERIAL = new ToolMaterial(
            INCORRECT_FOR_ETERNIUM_TOOL,
            10000,
            24.0F,
            14.0F,
            60,
            EterniumArmorMaterials.REPAIRS_GODFORGED_ARMOR
    );

    public static final Item RAW_ETERNIUM = register("raw_eternium", Item::new, new Item.Properties());
    public static final Item ETERNIUM_INGOT = register("eternium_ingot", Item::new, new Item.Properties().rarity(Rarity.RARE));
    public static final Item TITAN_CORE = register("titan_core", Item::new, new Item.Properties().stacksTo(16).rarity(Rarity.EPIC));

    public static final Item SPEED_RELIC = register(
            "speed_relic",
            props -> new AbilityRelicItem(
                    props,
                    "message.beyondrealms.speed_relic",
                    new AbilityRelicItem.EffectSpec(MobEffects.SPEED, 20 * 45, 2),
                    new AbilityRelicItem.EffectSpec(MobEffects.JUMP_BOOST, 20 * 45, 1)
            ),
            new Item.Properties().stacksTo(1).rarity(Rarity.RARE)
    );

    public static final Item STRENGTH_RELIC = register(
            "strength_relic",
            props -> new AbilityRelicItem(
                    props,
                    "message.beyondrealms.strength_relic",
                    new AbilityRelicItem.EffectSpec(MobEffects.STRENGTH, 20 * 45, 2),
                    new AbilityRelicItem.EffectSpec(MobEffects.HASTE, 20 * 45, 1)
            ),
            new Item.Properties().stacksTo(1).rarity(Rarity.RARE)
    );

    public static final Item SHADOW_RELIC = register(
            "shadow_relic",
            props -> new AbilityRelicItem(
                    props,
                    "message.beyondrealms.shadow_relic",
                    new AbilityRelicItem.EffectSpec(MobEffects.INVISIBILITY, 20 * 45, 0),
                    new AbilityRelicItem.EffectSpec(MobEffects.NIGHT_VISION, 20 * 60, 0),
                    new AbilityRelicItem.EffectSpec(MobEffects.SPEED, 20 * 45, 1)
            ),
            new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)
    );

    public static final Item TITAN_RELIC = register(
            "titan_relic",
            props -> new AbilityRelicItem(
                    props,
                    "message.beyondrealms.titan_relic",
                    new AbilityRelicItem.EffectSpec(MobEffects.RESISTANCE, 20 * 45, 2),
                    new AbilityRelicItem.EffectSpec(MobEffects.ABSORPTION, 20 * 45, 4),
                    new AbilityRelicItem.EffectSpec(MobEffects.REGENERATION, 20 * 20, 1)
            ),
            new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)
    );

    public static final Item DINOSAUR_RIFT_KEY = register(
            "dinosaur_rift_key",
            props -> new RiftKeyItem(props, "beyondrealms:dinosaur_wilds", 120, "message.beyondrealms.dinosaur_key"),
            new Item.Properties().stacksTo(1).rarity(Rarity.RARE)
    );
    public static final Item PARANORMAL_RIFT_KEY = register(
            "paranormal_rift_key",
            props -> new RiftKeyItem(props, "beyondrealms:paranormal_realm", 80, "message.beyondrealms.paranormal_key"),
            new Item.Properties().stacksTo(1).rarity(Rarity.RARE)
    );
    public static final Item SPIDER_RIFT_KEY = register(
            "spider_rift_key",
            props -> new RiftKeyItem(props, "beyondrealms:spider_caves", 80, "message.beyondrealms.spider_key"),
            new Item.Properties().stacksTo(1).rarity(Rarity.RARE)
    );
    public static final Item TITAN_RIFT_KEY = register(
            "titan_rift_key",
            props -> new RiftKeyItem(props, "beyondrealms:titan_expanse", 90, "message.beyondrealms.titan_key"),
            new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)
    );
    public static final Item HOMEWARD_KEY = register(
            "homeward_key",
            props -> new RiftKeyItem(props, "minecraft:overworld", 110, "message.beyondrealms.homeward_key"),
            new Item.Properties().stacksTo(1).rarity(Rarity.RARE)
    );

    public static final Item TITAN_REX_SIGIL = register(
            "titan_rex_sigil",
            props -> new BossSigilItem(props, "minecraft:ravager", "br_titan_rex", "Titan Rex", "dark_red", 300.0, 26.0, 1.65, 2, "minecraft:hoglin"),
            new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)
    );
    public static final Item WRAITH_LORD_SIGIL = register(
            "wraith_lord_sigil",
            props -> new BossSigilItem(props, "minecraft:wither", "br_wraith_lord", "Wraith Lord", "dark_purple", 450.0, 20.0, 1.15, 4, "minecraft:vex"),
            new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)
    );
    public static final Item BROOD_MOTHER_SIGIL = register(
            "brood_mother_sigil",
            props -> new BossSigilItem(props, "minecraft:spider", "br_brood_mother", "Brood Mother", "dark_green", 260.0, 22.0, 2.75, 6, "minecraft:cave_spider"),
            new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)
    );
    public static final Item VOID_TITAN_SIGIL = register(
            "void_titan_sigil",
            props -> new BossSigilItem(props, "minecraft:warden", "br_void_titan", "Void Titan", "dark_aqua", 650.0, 45.0, 1.35, 4, "minecraft:enderman"),
            new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)
    );

    public static final Item TITAN_REX_TROPHY = trophy("titan_rex_trophy");
    public static final Item WRAITH_LORD_TROPHY = trophy("wraith_lord_trophy");
    public static final Item BROOD_MOTHER_TROPHY = trophy("brood_mother_trophy");
    public static final Item VOID_TITAN_TROPHY = trophy("void_titan_trophy");

    public static final Item ETERNIUM_SWORD = register("eternium_sword", Item::new,
            new Item.Properties().sword(ETERNIUM_TOOL_MATERIAL, 5.0F, -2.35F).rarity(Rarity.RARE));
    public static final Item ETERNIUM_PICKAXE = register("eternium_pickaxe", Item::new,
            new Item.Properties().pickaxe(ETERNIUM_TOOL_MATERIAL, 1.5F, -2.8F).rarity(Rarity.RARE));
    public static final Item ETERNIUM_AXE = register("eternium_axe", Item::new,
            new Item.Properties().axe(ETERNIUM_TOOL_MATERIAL, 7.0F, -3.0F).rarity(Rarity.RARE));
    public static final Item ETERNIUM_SHOVEL = register("eternium_shovel", Item::new,
            new Item.Properties().shovel(ETERNIUM_TOOL_MATERIAL, 2.0F, -3.0F).rarity(Rarity.RARE));
    public static final Item ETERNIUM_HOE = register("eternium_hoe", Item::new,
            new Item.Properties().hoe(ETERNIUM_TOOL_MATERIAL, -4.0F, 0.0F).rarity(Rarity.RARE));

    public static final Item ETERNIUM_HELMET = register("eternium_helmet", Item::new,
            new Item.Properties().humanoidArmor(EterniumArmorMaterials.ETERNIUM, ArmorType.HELMET)
                    .durability(ArmorType.HELMET.getDurability(EterniumArmorMaterials.ETERNIUM_BASE_DURABILITY)).rarity(Rarity.RARE));
    public static final Item ETERNIUM_CHESTPLATE = register("eternium_chestplate", Item::new,
            new Item.Properties().humanoidArmor(EterniumArmorMaterials.ETERNIUM, ArmorType.CHESTPLATE)
                    .durability(ArmorType.CHESTPLATE.getDurability(EterniumArmorMaterials.ETERNIUM_BASE_DURABILITY)).rarity(Rarity.RARE));
    public static final Item ETERNIUM_LEGGINGS = register("eternium_leggings", Item::new,
            new Item.Properties().humanoidArmor(EterniumArmorMaterials.ETERNIUM, ArmorType.LEGGINGS)
                    .durability(ArmorType.LEGGINGS.getDurability(EterniumArmorMaterials.ETERNIUM_BASE_DURABILITY)).rarity(Rarity.RARE));
    public static final Item ETERNIUM_BOOTS = register("eternium_boots", Item::new,
            new Item.Properties().humanoidArmor(EterniumArmorMaterials.ETERNIUM, ArmorType.BOOTS)
                    .durability(ArmorType.BOOTS.getDurability(EterniumArmorMaterials.ETERNIUM_BASE_DURABILITY)).rarity(Rarity.RARE));
    public static final Item ETERNIUM_SHIELD = register("eternium_shield", ShieldItem::new,
            new Item.Properties().durability(2500).rarity(Rarity.RARE));

    public static final Item GODFORGED_SWORD = register("godforged_sword", Item::new,
            new Item.Properties().sword(GODFORGED_TOOL_MATERIAL, 10.0F, -2.0F).rarity(Rarity.EPIC));
    public static final Item GODFORGED_PICKAXE = register("godforged_pickaxe", Item::new,
            new Item.Properties().pickaxe(GODFORGED_TOOL_MATERIAL, 4.0F, -2.4F).rarity(Rarity.EPIC));
    public static final Item GODFORGED_HELMET = register("godforged_helmet", Item::new,
            new Item.Properties().humanoidArmor(EterniumArmorMaterials.GODFORGED, ArmorType.HELMET)
                    .durability(ArmorType.HELMET.getDurability(EterniumArmorMaterials.GODFORGED_BASE_DURABILITY)).rarity(Rarity.EPIC));
    public static final Item GODFORGED_CHESTPLATE = register("godforged_chestplate", Item::new,
            new Item.Properties().humanoidArmor(EterniumArmorMaterials.GODFORGED, ArmorType.CHESTPLATE)
                    .durability(ArmorType.CHESTPLATE.getDurability(EterniumArmorMaterials.GODFORGED_BASE_DURABILITY)).rarity(Rarity.EPIC));
    public static final Item GODFORGED_LEGGINGS = register("godforged_leggings", Item::new,
            new Item.Properties().humanoidArmor(EterniumArmorMaterials.GODFORGED, ArmorType.LEGGINGS)
                    .durability(ArmorType.LEGGINGS.getDurability(EterniumArmorMaterials.GODFORGED_BASE_DURABILITY)).rarity(Rarity.EPIC));
    public static final Item GODFORGED_BOOTS = register("godforged_boots", Item::new,
            new Item.Properties().humanoidArmor(EterniumArmorMaterials.GODFORGED, ArmorType.BOOTS)
                    .durability(ArmorType.BOOTS.getDurability(EterniumArmorMaterials.GODFORGED_BASE_DURABILITY)).rarity(Rarity.EPIC));
    public static final Item GODFORGED_SHIELD = register("godforged_shield", ShieldItem::new,
            new Item.Properties().durability(10000).rarity(Rarity.EPIC));

    public static final Item GOD_GEAR_CATALYST = register("god_gear_catalyst", GodGearCatalystItem::new,
            new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));

    public static final ResourceKey<CreativeModeTab> BEYOND_REALMS_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), BeyondRealmsMod.id("beyond_realms")
    );

    public static final CreativeModeTab BEYOND_REALMS_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(TITAN_CORE))
            .title(Component.translatable("creativeTab.beyondrealms"))
            .displayItems((parameters, output) -> {
                output.accept(ModBlocks.ETERNIUM_ORE);
                output.accept(ModBlocks.DEEPSLATE_ETERNIIUM_ORE);
                output.accept(ModBlocks.ETERNIUM_BLOCK);
                output.accept(RAW_ETERNIUM);
                output.accept(ETERNIUM_INGOT);
                output.accept(TITAN_CORE);
                output.accept(ETERNIUM_SWORD);
                output.accept(ETERNIUM_PICKAXE);
                output.accept(ETERNIUM_AXE);
                output.accept(ETERNIUM_SHOVEL);
                output.accept(ETERNIUM_HOE);
                output.accept(ETERNIUM_HELMET);
                output.accept(ETERNIUM_CHESTPLATE);
                output.accept(ETERNIUM_LEGGINGS);
                output.accept(ETERNIUM_BOOTS);
                output.accept(ETERNIUM_SHIELD);
                output.accept(SPEED_RELIC);
                output.accept(STRENGTH_RELIC);
                output.accept(SHADOW_RELIC);
                output.accept(TITAN_RELIC);
                output.accept(DINOSAUR_RIFT_KEY);
                output.accept(PARANORMAL_RIFT_KEY);
                output.accept(SPIDER_RIFT_KEY);
                output.accept(TITAN_RIFT_KEY);
                output.accept(HOMEWARD_KEY);
                output.accept(TITAN_REX_SIGIL);
                output.accept(WRAITH_LORD_SIGIL);
                output.accept(BROOD_MOTHER_SIGIL);
                output.accept(VOID_TITAN_SIGIL);
                output.accept(TITAN_REX_TROPHY);
                output.accept(WRAITH_LORD_TROPHY);
                output.accept(BROOD_MOTHER_TROPHY);
                output.accept(VOID_TITAN_TROPHY);
                output.accept(GODFORGED_SWORD);
                output.accept(GODFORGED_PICKAXE);
                output.accept(GODFORGED_HELMET);
                output.accept(GODFORGED_CHESTPLATE);
                output.accept(GODFORGED_LEGGINGS);
                output.accept(GODFORGED_BOOTS);
                output.accept(GODFORGED_SHIELD);
                output.accept(GOD_GEAR_CATALYST);
            })
            .build();

    private ModItems() {
    }

    private static Item trophy(String name) {
        return register(name, Item::new, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    private static Item register(String name, Function<Item.Properties, Item> itemFactory, Item.Properties properties) {
        ResourceKey<Item> itemKey = ModItemIds.create(name);
        Item item = itemFactory.apply(properties.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }

    public static void initialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, BEYOND_REALMS_TAB_KEY, BEYOND_REALMS_TAB);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(output -> {
            output.accept(ModBlocks.ETERNIUM_ORE);
            output.accept(ModBlocks.DEEPSLATE_ETERNIIUM_ORE);
            output.accept(ModBlocks.ETERNIUM_BLOCK);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.accept(RAW_ETERNIUM);
            output.accept(ETERNIUM_INGOT);
            output.accept(TITAN_CORE);
            output.accept(SPEED_RELIC);
            output.accept(STRENGTH_RELIC);
            output.accept(SHADOW_RELIC);
            output.accept(TITAN_RELIC);
            output.accept(DINOSAUR_RIFT_KEY);
            output.accept(PARANORMAL_RIFT_KEY);
            output.accept(SPIDER_RIFT_KEY);
            output.accept(TITAN_RIFT_KEY);
            output.accept(HOMEWARD_KEY);
            output.accept(TITAN_REX_SIGIL);
            output.accept(WRAITH_LORD_SIGIL);
            output.accept(BROOD_MOTHER_SIGIL);
            output.accept(VOID_TITAN_SIGIL);
            output.accept(TITAN_REX_TROPHY);
            output.accept(WRAITH_LORD_TROPHY);
            output.accept(BROOD_MOTHER_TROPHY);
            output.accept(VOID_TITAN_TROPHY);
            output.accept(GOD_GEAR_CATALYST);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(output -> {
            output.accept(ETERNIUM_PICKAXE);
            output.accept(ETERNIUM_AXE);
            output.accept(ETERNIUM_SHOVEL);
            output.accept(ETERNIUM_HOE);
            output.accept(GODFORGED_PICKAXE);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> {
            output.accept(ETERNIUM_SWORD);
            output.accept(ETERNIUM_HELMET);
            output.accept(ETERNIUM_CHESTPLATE);
            output.accept(ETERNIUM_LEGGINGS);
            output.accept(ETERNIUM_BOOTS);
            output.accept(ETERNIUM_SHIELD);
            output.accept(GODFORGED_SWORD);
            output.accept(GODFORGED_HELMET);
            output.accept(GODFORGED_CHESTPLATE);
            output.accept(GODFORGED_LEGGINGS);
            output.accept(GODFORGED_BOOTS);
            output.accept(GODFORGED_SHIELD);
        });
    }
}
