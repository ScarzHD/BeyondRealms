package com.scarzhd.beyondrealms.item;

import java.util.function.Supplier;
import com.scarzhd.beyondrealms.entity.RealmMobEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DinosaurEggItem extends Item {
    private final Supplier<? extends EntityType<? extends RealmMobEntity>> entityType;
    private final String creatureName;

    public DinosaurEggItem(Properties properties, Supplier<? extends EntityType<? extends RealmMobEntity>> entityType, String creatureName) {
        super(properties);
        this.entityType = entityType;
        this.creatureName = creatureName;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!(level instanceof ServerLevel serverLevel)) return InteractionResult.SUCCESS;
        ItemStack stack = player.getItemInHand(hand);
        BlockPos spawnPos = player.blockPosition().above();
        RealmMobEntity spawned = entityType.get().spawn(serverLevel, stack, player, spawnPos, EntitySpawnReason.SPAWN_ITEM_USE, true, false);
        if (spawned != null) {
            stack.consume(1, player);
            player.sendSystemMessage(Component.literal(creatureName + " has hatched."));
            return InteractionResult.SUCCESS;
        }
        player.sendSystemMessage(Component.literal("The incubated egg needs more room to hatch."));
        return InteractionResult.FAIL;
    }
}
