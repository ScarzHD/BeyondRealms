package com.scarzhd.beyondrealms.boss;

import com.scarzhd.beyondrealms.registry.ModEntities;
import com.scarzhd.beyondrealms.registry.ModItems;
import com.scarzhd.beyondrealms.registry.ModRealmContent;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public final class BossDrops {
    private BossDrops() {
    }

    public static void initialize() {
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, source) -> {
            if (!(entity.level() instanceof ServerLevel serverLevel)) {
                return;
            }

            if (entity.getType() == ModEntities.TITAN_REX) {
                drop(serverLevel, entity, ModItems.TITAN_REX_TROPHY, 1);
                drop(serverLevel, entity, ModItems.TITAN_CORE, 3);
                drop(serverLevel, entity, ModItems.ETERNIUM_INGOT, 12);
            } else if (entity.getType() == ModEntities.WRAITH_LORD) {
                drop(serverLevel, entity, ModItems.WRAITH_LORD_TROPHY, 1);
                drop(serverLevel, entity, ModItems.TITAN_CORE, 3);
                drop(serverLevel, entity, ModRealmContent.WRAITH_SHARD, 8);
            } else if (entity.getType() == ModEntities.BROOD_MOTHER) {
                drop(serverLevel, entity, ModItems.BROOD_MOTHER_TROPHY, 1);
                drop(serverLevel, entity, ModItems.TITAN_CORE, 3);
                drop(serverLevel, entity, ModRealmContent.BROOD_CRYSTAL, 8);
            } else if (entity.getType() == ModEntities.VOID_TITAN) {
                drop(serverLevel, entity, ModItems.VOID_TITAN_TROPHY, 1);
                drop(serverLevel, entity, ModItems.TITAN_CORE, 5);
                drop(serverLevel, entity, ModRealmContent.VOIDIUM_INGOT, 6);
            }
        });
    }

    private static void drop(ServerLevel level, LivingEntity entity, Item item, int count) {
        ItemEntity drop = new ItemEntity(
                level,
                entity.getX(),
                entity.getY() + 0.5D,
                entity.getZ(),
                new ItemStack(item, count)
        );
        level.addFreshEntity(drop);
    }
}
