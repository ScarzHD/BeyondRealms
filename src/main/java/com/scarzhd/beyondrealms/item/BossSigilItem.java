package com.scarzhd.beyondrealms.item;

import com.scarzhd.beyondrealms.boss.RealmBossEntity;
import com.scarzhd.beyondrealms.registry.ModEntities;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BossSigilItem extends Item {
    private final String bossTag;
    private final String bossName;

    public BossSigilItem(Properties properties, String entityType, String bossTag, String bossName, String color,
                         double maxHealth, double attackDamage, double scale, int minionCount, String minionType) {
        super(properties);
        this.bossTag = bossTag;
        this.bossName = bossName;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return InteractionResult.SUCCESS;
        }

        RealmBossEntity boss = createBoss(serverLevel);
        if (boss == null) {
            player.sendSystemMessage(Component.literal("The sigil fails to answer."));
            return InteractionResult.FAIL;
        }

        var pos = player.blockPosition().offset(0, 1, 5);
        boss.snapTo(pos, player.getYRot(), 0.0F);
        boss.setCustomName(Component.literal(this.bossName));
        boss.setCustomNameVisible(false);
        serverLevel.addFreshEntity(boss);

        ItemStack stack = player.getItemInHand(hand);
        stack.consume(1, player);
        player.sendSystemMessage(Component.literal(this.bossName + " has entered the realm!"));
        return InteractionResult.SUCCESS;
    }

    private RealmBossEntity createBoss(ServerLevel level) {
        return switch (this.bossTag) {
            case "br_titan_rex" -> ModEntities.TITAN_REX.create(level, EntitySpawnReason.SPAWN_ITEM_USE);
            case "br_wraith_lord" -> ModEntities.WRAITH_LORD.create(level, EntitySpawnReason.SPAWN_ITEM_USE);
            case "br_brood_mother" -> ModEntities.BROOD_MOTHER.create(level, EntitySpawnReason.SPAWN_ITEM_USE);
            case "br_void_titan" -> ModEntities.VOID_TITAN.create(level, EntitySpawnReason.SPAWN_ITEM_USE);
            default -> null;
        };
    }
}
