package com.scarzhd.beyondrealms.item;

import net.minecraft.network.chat.Component;
import net.minecraft.server.permissions.PermissionSet;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class GodGearCatalystItem extends Item {
    public GodGearCatalystItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            var source = serverPlayer.createCommandSourceStack()
                    .withPermission(PermissionSet.ALL_PERMISSIONS)
                    .withSuppressedOutput();
            var server = serverPlayer.level().getServer();
            if (server == null) {
                return InteractionResult.FAIL;
            }
            var commands = server.getCommands();

            commands.performPrefixedCommand(source, "give @s beyondrealms:godforged_sword[enchantments={sharpness:10,smite:10,bane_of_arthropods:10,fire_aspect:5,looting:5,knockback:3,unbreaking:10,mending:1,sweeping_edge:5},unbreakable={}] 1");
            commands.performPrefixedCommand(source, "give @s beyondrealms:godforged_pickaxe[enchantments={efficiency:10,fortune:5,silk_touch:1,unbreaking:10,mending:1},unbreakable={}] 1");
            commands.performPrefixedCommand(source, "give @s beyondrealms:godforged_helmet[enchantments={protection:10,fire_protection:10,blast_protection:10,projectile_protection:10,respiration:5,aqua_affinity:1,thorns:5,unbreaking:10,mending:1},unbreakable={}] 1");
            commands.performPrefixedCommand(source, "give @s beyondrealms:godforged_chestplate[enchantments={protection:10,fire_protection:10,blast_protection:10,projectile_protection:10,thorns:5,unbreaking:10,mending:1},unbreakable={}] 1");
            commands.performPrefixedCommand(source, "give @s beyondrealms:godforged_leggings[enchantments={protection:10,fire_protection:10,blast_protection:10,projectile_protection:10,swift_sneak:5,thorns:5,unbreaking:10,mending:1},unbreakable={}] 1");
            commands.performPrefixedCommand(source, "give @s beyondrealms:godforged_boots[enchantments={protection:10,fire_protection:10,blast_protection:10,projectile_protection:10,feather_falling:10,depth_strider:5,frost_walker:3,soul_speed:5,thorns:5,unbreaking:10,mending:1},unbreakable={}] 1");
            commands.performPrefixedCommand(source, "give @s beyondrealms:godforged_shield[enchantments={unbreaking:10,mending:1},unbreakable={}] 1");
            serverPlayer.sendSystemMessage(Component.translatable("message.beyondrealms.godgear"));
        }
        return InteractionResult.SUCCESS;
    }
}
