package com.scarzhd.beyondrealms.item;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class RiftKeyItem extends Item {
    private final String dimensionId;
    private final int arrivalY;
    private final String messageKey;

    public RiftKeyItem(Properties properties, String dimensionId, int arrivalY, String messageKey) {
        super(properties);
        this.dimensionId = dimensionId;
        this.arrivalY = arrivalY;
        this.messageKey = messageKey;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            var source = serverPlayer.createCommandSourceStack().withPermission(2).withSuppressedOutput();
            var commands = serverPlayer.getServer().getCommands();
            int floorY = arrivalY - 1;

            commands.performPrefixedCommand(source, "effect give @s minecraft:resistance 12 4 true");
            commands.performPrefixedCommand(source, "effect give @s minecraft:slow_falling 20 0 true");
            commands.performPrefixedCommand(source, "execute in " + dimensionId + " run fill -2 " + floorY + " -2 2 " + floorY + " 2 minecraft:obsidian");
            commands.performPrefixedCommand(source, "execute in " + dimensionId + " run fill -2 " + arrivalY + " -2 2 " + (arrivalY + 3) + " 2 minecraft:air");
            commands.performPrefixedCommand(source, "execute in " + dimensionId + " run tp @s 0 " + arrivalY + " 0");
            serverPlayer.displayClientMessage(Component.translatable(messageKey), true);
        }
        return InteractionResult.SUCCESS;
    }
}
