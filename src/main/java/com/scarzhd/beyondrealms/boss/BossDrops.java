package com.scarzhd.beyondrealms.boss;

import com.scarzhd.beyondrealms.registry.ModItems;
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

            if (entity.getTags().contains("br_titan_rex")) {
                drop(serverLevel, entity, ModItems.TITAN_REX_TROPHY, 1);
                drop(serverLevel, entity, ModItems.TITAN_CORE, 2);
                drop(serverLevel, entity, ModItems.ETERNIUM_INGOT, 8);
            } else if (entity.getTags().contains("br_wraith_lord")) {
                drop(serverLevel, entity, ModItems.WRAITH_LORD_TROPHY, 1);
                drop(serverLevel, entity, ModItems.TITAN_CORE, 2);
            } else if (entity.getTags().contains("br_brood_mother")) {
                drop(serverLevel, entity, ModItems.BROOD_MOTHER_TROPHY, 1);
                drop(serverLevel, entity, ModItems.TITAN_CORE, 2);
            } else if (entity.getTags().contains("br_void_titan")) {
                drop(serverLevel, entity, ModItems.VOID_TITAN_TROPHY, 1);
                drop(serverLevel, entity, ModItems.TITAN_CORE, 4);
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
