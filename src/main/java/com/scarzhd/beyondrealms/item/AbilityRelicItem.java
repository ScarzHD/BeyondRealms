package com.scarzhd.beyondrealms.item;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class AbilityRelicItem extends Item {
    public record EffectSpec(Holder<MobEffect> effect, int durationTicks, int amplifier) {
    }

    private final String messageKey;
    private final EffectSpec[] effects;

    public AbilityRelicItem(Properties properties, String messageKey, EffectSpec... effects) {
        super(properties);
        this.messageKey = messageKey;
        this.effects = effects;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            for (EffectSpec effect : effects) {
                player.addEffect(new MobEffectInstance(effect.effect(), effect.durationTicks(), effect.amplifier()));
            }
            player.sendSystemMessage(Component.translatable(messageKey));
        }
        return InteractionResult.SUCCESS;
    }
}
