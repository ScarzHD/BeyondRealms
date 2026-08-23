package com.scarzhd.beyondrealms.item;

import com.scarzhd.beyondrealms.registry.ModGenetics;
import com.scarzhd.beyondrealms.registry.ModRealmContent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class GeneticsLabItem extends Item {
    public enum Mode { AMBER_EXTRACTOR, GENOME_SEQUENCER, GENE_SPLICER, EGG_INCUBATOR }

    private final Mode mode;

    public GeneticsLabItem(Properties properties, Mode mode) {
        super(properties);
        this.mode = mode;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            switch (mode) {
                case AMBER_EXTRACTOR -> extractAmber(level, player);
                case GENOME_SEQUENCER -> sequenceGenome(player);
                case GENE_SPLICER -> spliceGenome(player);
                case EGG_INCUBATOR -> incubateEgg(player);
            }
        }
        return InteractionResult.SUCCESS;
    }

    private static void extractAmber(Level level, Player player) {
        if (!has(player, ModRealmContent.PRIMEVAL_AMBER, 1)) {
            player.sendSystemMessage(Component.literal("Amber Extractor: you need Primeval Amber."));
            return;
        }
        consume(player, ModRealmContent.PRIMEVAL_AMBER, 1);
        Item[] fragments = {
                ModGenetics.RAPTOR_DNA_FRAGMENT,
                ModGenetics.TYRANNOSAUR_DNA_FRAGMENT,
                ModGenetics.TRICERATOPS_DNA_FRAGMENT,
                ModGenetics.DILOPHOSAURUS_DNA_FRAGMENT,
                ModGenetics.SPINOSAURUS_DNA_FRAGMENT,
                ModGenetics.ANKYLOSAURUS_DNA_FRAGMENT,
                ModGenetics.CARNOTAURUS_DNA_FRAGMENT
        };
        Item fragment = fragments[level.getRandom().nextInt(fragments.length)];
        give(player, new ItemStack(fragment));
        player.sendSystemMessage(Component.literal("Amber Extractor recovered: ").append(new ItemStack(fragment).getHoverName()));
    }

    private static void sequenceGenome(Player player) {
        Item[][] pairs = {
                {ModGenetics.RAPTOR_DNA_FRAGMENT, ModGenetics.RAPTOR_GENOME},
                {ModGenetics.TYRANNOSAUR_DNA_FRAGMENT, ModGenetics.TYRANNOSAUR_GENOME},
                {ModGenetics.TRICERATOPS_DNA_FRAGMENT, ModGenetics.TRICERATOPS_GENOME},
                {ModGenetics.DILOPHOSAURUS_DNA_FRAGMENT, ModGenetics.DILOPHOSAURUS_GENOME},
                {ModGenetics.SPINOSAURUS_DNA_FRAGMENT, ModGenetics.SPINOSAURUS_GENOME},
                {ModGenetics.ANKYLOSAURUS_DNA_FRAGMENT, ModGenetics.ANKYLOSAURUS_GENOME},
                {ModGenetics.CARNOTAURUS_DNA_FRAGMENT, ModGenetics.CARNOTAURUS_GENOME}
        };
        for (Item[] pair : pairs) {
            if (has(player, pair[0], 3)) {
                consume(player, pair[0], 3);
                give(player, new ItemStack(pair[1]));
                player.sendSystemMessage(Component.literal("Genome Sequencer completed: ").append(new ItemStack(pair[1]).getHoverName()));
                return;
            }
        }
        player.sendSystemMessage(Component.literal("Genome Sequencer: collect 3 matching DNA fragments."));
    }

    private static void spliceGenome(Player player) {
        if (splice(player, ModGenetics.TYRANT_RAPTOR_GENOME, ModGenetics.VOID_REX_GENOME, ModRealmContent.NEXUS_CORE, ModGenetics.APEX_CHIMERA_GENOME)) return;
        if (splice(player, ModGenetics.TYRANNOSAUR_GENOME, ModGenetics.RAPTOR_GENOME, null, ModGenetics.TYRANT_RAPTOR_GENOME)) return;
        if (splice(player, ModGenetics.TYRANNOSAUR_GENOME, ModGenetics.DILOPHOSAURUS_GENOME, null, ModGenetics.VENOMJAW_REX_GENOME)) return;
        if (splice(player, ModGenetics.RAPTOR_GENOME, ModRealmContent.ECTOPLASM, null, ModGenetics.SPECTRAL_RAPTOR_GENOME)) return;
        if (splice(player, ModGenetics.RAPTOR_GENOME, ModRealmContent.BROOD_VENOM_GLAND, null, ModGenetics.WEBFANG_GENOME)) return;
        if (splice(player, ModGenetics.TYRANNOSAUR_GENOME, ModRealmContent.VOID_HEART, null, ModGenetics.VOID_REX_GENOME)) return;
        player.sendSystemMessage(Component.literal("Gene Splicer: no compatible genome combination found."));
    }

    private static boolean splice(Player player, Item first, Item second, Item catalyst, Item output) {
        if (!has(player, first, 1) || !has(player, second, 1) || (catalyst != null && !has(player, catalyst, 1))) return false;
        consume(player, first, 1);
        consume(player, second, 1);
        if (catalyst != null) consume(player, catalyst, 1);
        give(player, new ItemStack(output));
        player.sendSystemMessage(Component.literal("Gene Splicer created: ").append(new ItemStack(output).getHoverName()));
        return true;
    }

    private static void incubateEgg(Player player) {
        if (!has(player, Items.EGG, 1)) {
            player.sendSystemMessage(Component.literal("Egg Incubator: you need a Minecraft Egg as the embryo shell."));
            return;
        }
        Item[][] pairs = {
                {ModGenetics.APEX_CHIMERA_GENOME, ModGenetics.APEX_CHIMERA_EGG},
                {ModGenetics.VOID_REX_GENOME, ModGenetics.VOID_REX_EGG},
                {ModGenetics.WEBFANG_GENOME, ModGenetics.WEBFANG_EGG},
                {ModGenetics.SPECTRAL_RAPTOR_GENOME, ModGenetics.SPECTRAL_RAPTOR_EGG},
                {ModGenetics.VENOMJAW_REX_GENOME, ModGenetics.VENOMJAW_REX_EGG},
                {ModGenetics.TYRANT_RAPTOR_GENOME, ModGenetics.TYRANT_RAPTOR_EGG},
                {ModGenetics.TYRANNOSAUR_GENOME, ModGenetics.TYRANNOSAUR_EGG},
                {ModGenetics.TRICERATOPS_GENOME, ModGenetics.TRICERATOPS_EGG},
                {ModGenetics.DILOPHOSAURUS_GENOME, ModGenetics.DILOPHOSAURUS_EGG},
                {ModGenetics.SPINOSAURUS_GENOME, ModGenetics.SPINOSAURUS_EGG},
                {ModGenetics.ANKYLOSAURUS_GENOME, ModGenetics.ANKYLOSAURUS_EGG},
                {ModGenetics.CARNOTAURUS_GENOME, ModGenetics.CARNOTAURUS_EGG},
                {ModGenetics.RAPTOR_GENOME, ModGenetics.RAPTOR_EGG}
        };
        for (Item[] pair : pairs) {
            if (has(player, pair[0], 1)) {
                consume(player, pair[0], 1);
                consume(player, Items.EGG, 1);
                give(player, new ItemStack(pair[1]));
                player.sendSystemMessage(Component.literal("Egg Incubator produced: ").append(new ItemStack(pair[1]).getHoverName()));
                return;
            }
        }
        player.sendSystemMessage(Component.literal("Egg Incubator: no completed dinosaur or hybrid genome found."));
    }

    private static boolean has(Player player, Item item, int count) {
        int found = 0;
        for (ItemStack stack : player.getInventory().getNonEquipmentItems()) {
            if (stack.is(item)) {
                found += stack.getCount();
                if (found >= count) return true;
            }
        }
        return false;
    }

    private static void consume(Player player, Item item, int count) {
        int remaining = count;
        Inventory inventory = player.getInventory();
        for (ItemStack stack : inventory.getNonEquipmentItems()) {
            if (stack.is(item)) {
                int take = Math.min(remaining, stack.getCount());
                stack.shrink(take);
                remaining -= take;
                if (remaining <= 0) return;
            }
        }
    }

    private static void give(Player player, ItemStack stack) {
        if (!player.getInventory().add(stack) && !stack.isEmpty()) {
            player.drop(stack, false);
        }
    }
}
