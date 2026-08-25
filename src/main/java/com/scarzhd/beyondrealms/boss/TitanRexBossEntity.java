package com.scarzhd.beyondrealms.boss;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.BossEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class TitanRexBossEntity extends RealmBossEntity {
    public TitanRexBossEntity(EntityType<? extends TitanRexBossEntity> type, Level level) {
        super(type, level, "Titan Rex", BossEvent.BossBarColor.RED, 90);
        this.xpReward = 160;
    }

    @Override
    protected void performSpecialAttack(ServerLevel level, LivingEntity target) {
        for (Player player : nearbyPlayers(level, 10.0)) {
            player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 20 * 5, 2), this);
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20 * 5, 0), this);
        }
    }

    @Override
    protected void onEnrage(ServerLevel level) {
        this.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 20 * 60 * 30, 0, false, true));
    }
}
