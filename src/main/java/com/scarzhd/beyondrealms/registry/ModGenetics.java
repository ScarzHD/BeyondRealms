package com.scarzhd.beyondrealms.registry;

import java.util.function.Function;
import com.scarzhd.beyondrealms.BeyondRealmsMod;
import com.scarzhd.beyondrealms.item.DinosaurEggItem;
import com.scarzhd.beyondrealms.item.GeneticsLabItem;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public final class ModGenetics {
    public static final Item AMBER_EXTRACTOR = lab("amber_extractor", GeneticsLabItem.Mode.AMBER_EXTRACTOR);
    public static final Item GENOME_SEQUENCER = lab("genome_sequencer", GeneticsLabItem.Mode.GENOME_SEQUENCER);
    public static final Item GENE_SPLICER = lab("gene_splicer", GeneticsLabItem.Mode.GENE_SPLICER);
    public static final Item EGG_INCUBATOR = lab("egg_incubator", GeneticsLabItem.Mode.EGG_INCUBATOR);

    public static final Item RAPTOR_DNA_FRAGMENT = material("raptor_dna_fragment");
    public static final Item TYRANNOSAUR_DNA_FRAGMENT = material("tyrannosaur_dna_fragment");
    public static final Item TRICERATOPS_DNA_FRAGMENT = material("triceratops_dna_fragment");
    public static final Item DILOPHOSAURUS_DNA_FRAGMENT = material("dilophosaurus_dna_fragment");
    public static final Item SPINOSAURUS_DNA_FRAGMENT = material("spinosaurus_dna_fragment");
    public static final Item ANKYLOSAURUS_DNA_FRAGMENT = material("ankylosaurus_dna_fragment");
    public static final Item CARNOTAURUS_DNA_FRAGMENT = material("carnotaurus_dna_fragment");

    public static final Item RAPTOR_GENOME = genome("raptor_genome");
    public static final Item TYRANNOSAUR_GENOME = genome("tyrannosaur_genome");
    public static final Item TRICERATOPS_GENOME = genome("triceratops_genome");
    public static final Item DILOPHOSAURUS_GENOME = genome("dilophosaurus_genome");
    public static final Item SPINOSAURUS_GENOME = genome("spinosaurus_genome");
    public static final Item ANKYLOSAURUS_GENOME = genome("ankylosaurus_genome");
    public static final Item CARNOTAURUS_GENOME = genome("carnotaurus_genome");

    public static final Item TYRANT_RAPTOR_GENOME = hybridGenome("tyrant_raptor_genome");
    public static final Item VENOMJAW_REX_GENOME = hybridGenome("venomjaw_rex_genome");
    public static final Item SPECTRAL_RAPTOR_GENOME = hybridGenome("spectral_raptor_genome");
    public static final Item WEBFANG_GENOME = hybridGenome("webfang_genome");
    public static final Item VOID_REX_GENOME = hybridGenome("void_rex_genome");
    public static final Item APEX_CHIMERA_GENOME = hybridGenome("apex_chimera_genome");

    public static final Item RAPTOR_EGG = egg("raptor_incubated_egg", () -> ModEntities.PRIMEVAL_RAPTOR, "Primeval Raptor");
    public static final Item TYRANNOSAUR_EGG = egg("tyrannosaur_incubated_egg", () -> ModEntities.TYRANNOSAUR, "Tyrannosaur");
    public static final Item TRICERATOPS_EGG = egg("triceratops_incubated_egg", () -> ModEntities.TRICERATOPS, "Triceratops");
    public static final Item DILOPHOSAURUS_EGG = egg("dilophosaurus_incubated_egg", () -> ModEntities.DILOPHOSAURUS, "Dilophosaurus");
    public static final Item SPINOSAURUS_EGG = egg("spinosaurus_incubated_egg", () -> ModEntities.SPINOSAURUS, "Spinosaurus");
    public static final Item ANKYLOSAURUS_EGG = egg("ankylosaurus_incubated_egg", () -> ModEntities.ANKYLOSAURUS, "Ankylosaurus");
    public static final Item CARNOTAURUS_EGG = egg("carnotaurus_incubated_egg", () -> ModEntities.CARNOTAURUS, "Carnotaurus");
    public static final Item TYRANT_RAPTOR_EGG = egg("tyrant_raptor_incubated_egg", () -> ModEntities.TYRANT_RAPTOR, "Tyrant Raptor");
    public static final Item VENOMJAW_REX_EGG = egg("venomjaw_rex_incubated_egg", () -> ModEntities.VENOMJAW_REX, "Venomjaw Rex");
    public static final Item SPECTRAL_RAPTOR_EGG = egg("spectral_raptor_incubated_egg", () -> ModEntities.SPECTRAL_RAPTOR, "Spectral Raptor");
    public static final Item WEBFANG_EGG = egg("webfang_incubated_egg", () -> ModEntities.WEBFANG, "Webfang");
    public static final Item VOID_REX_EGG = egg("void_rex_incubated_egg", () -> ModEntities.VOID_REX, "Void Rex");
    public static final Item APEX_CHIMERA_EGG = egg("apex_chimera_incubated_egg", () -> ModEntities.APEX_CHIMERA, "Apex Chimera");

    private ModGenetics() {}

    private static Item lab(String name, GeneticsLabItem.Mode mode) {
        return register(name, p -> new GeneticsLabItem(p, mode), new Item.Properties().stacksTo(1).durability(512).rarity(Rarity.RARE));
    }

    private static Item material(String name) {
        return register(name, Item::new, new Item.Properties().rarity(Rarity.UNCOMMON));
    }

    private static Item genome(String name) {
        return register(name, Item::new, new Item.Properties().stacksTo(16).rarity(Rarity.RARE));
    }

    private static Item hybridGenome(String name) {
        return register(name, Item::new, new Item.Properties().stacksTo(8).rarity(Rarity.EPIC));
    }

    private static Item egg(String name, java.util.function.Supplier<? extends net.minecraft.world.entity.EntityType<? extends com.scarzhd.beyondrealms.entity.RealmMobEntity>> type, String creatureName) {
        return register(name, p -> new DinosaurEggItem(p, type, creatureName), new Item.Properties().stacksTo(16).rarity(Rarity.EPIC));
    }

    private static Item register(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, BeyondRealmsMod.id(name));
        Item item = factory.apply(properties.setId(key));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    public static void initialize() {
        CreativeModeTabEvents.modifyOutputEvent(ModItems.BEYOND_REALMS_TAB_KEY).register(output -> {
            output.accept(AMBER_EXTRACTOR); output.accept(GENOME_SEQUENCER); output.accept(GENE_SPLICER); output.accept(EGG_INCUBATOR);
            output.accept(RAPTOR_DNA_FRAGMENT); output.accept(TYRANNOSAUR_DNA_FRAGMENT); output.accept(TRICERATOPS_DNA_FRAGMENT); output.accept(DILOPHOSAURUS_DNA_FRAGMENT); output.accept(SPINOSAURUS_DNA_FRAGMENT); output.accept(ANKYLOSAURUS_DNA_FRAGMENT); output.accept(CARNOTAURUS_DNA_FRAGMENT);
            output.accept(RAPTOR_GENOME); output.accept(TYRANNOSAUR_GENOME); output.accept(TRICERATOPS_GENOME); output.accept(DILOPHOSAURUS_GENOME); output.accept(SPINOSAURUS_GENOME); output.accept(ANKYLOSAURUS_GENOME); output.accept(CARNOTAURUS_GENOME);
            output.accept(TYRANT_RAPTOR_GENOME); output.accept(VENOMJAW_REX_GENOME); output.accept(SPECTRAL_RAPTOR_GENOME); output.accept(WEBFANG_GENOME); output.accept(VOID_REX_GENOME); output.accept(APEX_CHIMERA_GENOME);
            output.accept(RAPTOR_EGG); output.accept(TYRANNOSAUR_EGG); output.accept(TRICERATOPS_EGG); output.accept(DILOPHOSAURUS_EGG); output.accept(SPINOSAURUS_EGG); output.accept(ANKYLOSAURUS_EGG); output.accept(CARNOTAURUS_EGG);
            output.accept(TYRANT_RAPTOR_EGG); output.accept(VENOMJAW_REX_EGG); output.accept(SPECTRAL_RAPTOR_EGG); output.accept(WEBFANG_EGG); output.accept(VOID_REX_EGG); output.accept(APEX_CHIMERA_EGG);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(output -> {
            output.accept(AMBER_EXTRACTOR); output.accept(GENOME_SEQUENCER); output.accept(GENE_SPLICER); output.accept(EGG_INCUBATOR);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.accept(RAPTOR_DNA_FRAGMENT); output.accept(TYRANNOSAUR_DNA_FRAGMENT); output.accept(TRICERATOPS_DNA_FRAGMENT); output.accept(DILOPHOSAURUS_DNA_FRAGMENT); output.accept(SPINOSAURUS_DNA_FRAGMENT); output.accept(ANKYLOSAURUS_DNA_FRAGMENT); output.accept(CARNOTAURUS_DNA_FRAGMENT);
            output.accept(RAPTOR_GENOME); output.accept(TYRANNOSAUR_GENOME); output.accept(TRICERATOPS_GENOME); output.accept(DILOPHOSAURUS_GENOME); output.accept(SPINOSAURUS_GENOME); output.accept(ANKYLOSAURUS_GENOME); output.accept(CARNOTAURUS_GENOME);
            output.accept(TYRANT_RAPTOR_GENOME); output.accept(VENOMJAW_REX_GENOME); output.accept(SPECTRAL_RAPTOR_GENOME); output.accept(WEBFANG_GENOME); output.accept(VOID_REX_GENOME); output.accept(APEX_CHIMERA_GENOME);
        });
    }
}
