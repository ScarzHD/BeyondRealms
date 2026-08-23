package com.scarzhd.beyondrealms.item;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class BossSigilItem extends Item {
    private final String entityType;
    private final String bossTag;
    private final String bossName;
    private final String color;
    private final double maxHealth;
    private final double attackDamage;
    private final double scale;
    private final int minionCount;
    private final String minionType;

    public BossSigilItem(Properties properties, String entityType, String bossTag, String bossName, String color,
                         double maxHealth, double attackDamage, double scale, int minionCount, String minionType) {
        super(properties);
        this.entityType = entityType;
        this.bossTag = bossTag;
        this.bossName = bossName;
        this.color = color;
        this.maxHealth = maxHealth;
        this.attackDamage = attackDamage;
        this.scale = scale;
        this.minionCount = minionCount;
        this.minionType = minionType;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            var source = serverPlayer.createCommandSourceStack().withPermission(2).withSuppressedOutput();
            var commands = serverPlayer.getServer().getCommands();
            String selector = "@e[tag=" + bossTag + ",sort=nearest,limit=1,distance=..20]";
            String nameJson = "{\"text\":\"" + bossName + "\",\"color\":\"" + color + "\",\"bold\":true}";

            commands.performPrefixedCommand(source,
                    "execute at @s run summon " + entityType + " ~ ~1 ~ {Tags:[\"" + bossTag + "\"],CustomName:'" + nameJson + "',CustomNameVisible:1b,PersistenceRequired:1b}");
            commands.performPrefixedCommand(source, "attribute " + selector + " minecraft:max_health base set " + maxHealth);
            commands.performPrefixedCommand(source, "data modify entity " + selector + " Health set value " + maxHealth + "f");
            commands.performPrefixedCommand(source, "attribute " + selector + " minecraft:attack_damage base set " + attackDamage);
            commands.performPrefixedCommand(source, "attribute " + selector + " minecraft:scale base set " + scale);
            commands.performPrefixedCommand(source, "effect give " + selector + " minecraft:resistance infinite 1 true");
            commands.performPrefixedCommand(source, "effect give " + selector + " minecraft:strength infinite 1 true");
            commands.performPrefixedCommand(source, "effect give " + selector + " minecraft:speed infinite 0 true");

            if (minionType != null && minionCount > 0) {
                for (int i = 0; i < minionCount; i++) {
                    int dx = (i % 2 == 0 ? 2 : -2) * (1 + i / 2);
                    int dz = (i % 3 == 0 ? 2 : -2);
                    commands.performPrefixedCommand(source,
                            "execute at @s run summon " + minionType + " ~" + dx + " ~1 ~" + dz);
                }
            }

            serverPlayer.displayClientMessage(Component.literal(bossName + " has entered the realm!"), false);
        }
        return InteractionResult.SUCCESS;
    }
}
