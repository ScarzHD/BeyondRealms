package com.scarzhd.beyondrealms.registry;

import java.util.function.Function;
import com.scarzhd.beyondrealms.BeyondRealmsMod;
import com.scarzhd.beyondrealms.item.AbilityRelicItem;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class ModRealmContent {
    public static final Block PRIMEVAL_AMBER_ORE = registerBlock("primeval_amber_ore", BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE).strength(4.0F, 6.0F).requiresCorrectToolForDrops());
    public static final Block WRAITHSTONE_ORE = registerBlock("wraithstone_ore", BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_GOLD_ORE).strength(4.5F, 7.0F).lightLevel(state -> 4).requiresCorrectToolForDrops());
    public static final Block BROOD_CRYSTAL_ORE = registerBlock("brood_crystal_ore", BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE).strength(6.0F, 8.0F).lightLevel(state -> 3).requiresCorrectToolForDrops());
    public static final Block VOIDIUM_ORE = registerBlock("voidium_ore", BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE).strength(7.0F, 12.0F).lightLevel(state -> 5).requiresCorrectToolForDrops());

    public static final Item PRIMEVAL_AMBER = registerItem("primeval_amber", Item::new, new Item.Properties().rarity(Rarity.RARE));
    public static final Item WRAITH_SHARD = registerItem("wraith_shard", Item::new, new Item.Properties().rarity(Rarity.RARE));
    public static final Item BROOD_CRYSTAL = registerItem("brood_crystal", Item::new, new Item.Properties().rarity(Rarity.RARE));
    public static final Item RAW_VOIDIUM = registerItem("raw_voidium", Item::new, new Item.Properties().rarity(Rarity.EPIC));
    public static final Item VOIDIUM_INGOT = registerItem("voidium_ingot", Item::new, new Item.Properties().rarity(Rarity.EPIC));
    public static final Item RAPTOR_FANG = registerItem("raptor_fang", Item::new, new Item.Properties().rarity(Rarity.RARE));
    public static final Item ECTOPLASM = registerItem("ectoplasm", Item::new, new Item.Properties().rarity(Rarity.RARE));
    public static final Item BROOD_VENOM_GLAND = registerItem("brood_venom_gland", Item::new, new Item.Properties().rarity(Rarity.RARE));
    public static final Item VOID_HEART = registerItem("void_heart", Item::new, new Item.Properties().rarity(Rarity.EPIC));
    public static final Item NEXUS_CORE = registerItem("nexus_core", props -> new AbilityRelicItem(props, "message.beyondrealms.nexus_core",
            new AbilityRelicItem.EffectSpec(MobEffects.RESISTANCE, 20 * 90, 2),
            new AbilityRelicItem.EffectSpec(MobEffects.STRENGTH, 20 * 90, 2),
            new AbilityRelicItem.EffectSpec(MobEffects.SPEED, 20 * 90, 1),
            new AbilityRelicItem.EffectSpec(MobEffects.REGENERATION, 20 * 30, 1)),
            new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));

    private ModRealmContent() {}

    private static Block registerBlock(String name, BlockBehaviour.Properties properties) {
        Identifier id = BeyondRealmsMod.id(name);
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, id);
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);
        Block block = new Block(properties.setId(blockKey));
        Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
        Registry.register(BuiltInRegistries.ITEM, itemKey, new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(itemKey)));
        return block;
    }

    private static Item registerItem(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, BeyondRealmsMod.id(name));
        Item item = factory.apply(properties.setId(key));
        Registry.register(BuiltInRegistries.ITEM, key, item);
        return item;
    }

    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(ModItems.BEYOND_REALMS_TAB_KEY).register(output -> {
            output.accept(PRIMEVAL_AMBER_ORE); output.accept(WRAITHSTONE_ORE); output.accept(BROOD_CRYSTAL_ORE); output.accept(VOIDIUM_ORE);
            output.accept(PRIMEVAL_AMBER); output.accept(WRAITH_SHARD); output.accept(BROOD_CRYSTAL); output.accept(RAW_VOIDIUM); output.accept(VOIDIUM_INGOT);
            output.accept(RAPTOR_FANG); output.accept(ECTOPLASM); output.accept(BROOD_VENOM_GLAND); output.accept(VOID_HEART); output.accept(NEXUS_CORE);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.BUILDING_BLOCKS).register(output -> {
            output.accept(PRIMEVAL_AMBER_ORE); output.accept(WRAITHSTONE_ORE); output.accept(BROOD_CRYSTAL_ORE); output.accept(VOIDIUM_ORE);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.accept(PRIMEVAL_AMBER); output.accept(WRAITH_SHARD); output.accept(BROOD_CRYSTAL); output.accept(RAW_VOIDIUM); output.accept(VOIDIUM_INGOT);
            output.accept(RAPTOR_FANG); output.accept(ECTOPLASM); output.accept(BROOD_VENOM_GLAND); output.accept(VOID_HEART); output.accept(NEXUS_CORE);
        });
    }
}
