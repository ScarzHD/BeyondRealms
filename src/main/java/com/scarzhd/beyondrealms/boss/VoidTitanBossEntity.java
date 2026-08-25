package com.scarzhd.beyondrealms.boss;

import com.scarzhd.beyondrealms.registry.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.BossEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class VoidTitanBossEntity extends RealmBossEntity {
    public VoidTitanBossEntity(EntityType<? extends VoidTitanBossEntity> type, Level level) {
        super(type, level, "Void Titan", BossEvent.BossBarColor.BLUE, 120);
        this.xpReward = 240;
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity target) {
        if (!super.doHurtTarget(level, target)) return false;
        if (target instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(MobEffects.WITHER, 20 * 7, 1), this);
            living.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 20 * 5, 0), this);
        }
        return true;
    }

    @Override
    protected void performSpecialAttack(ServerLevel level, LivingEntity target) {
        for (Player player : nearbyPlayers(level, 12.0)) {
            player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 20 * 8, 0), this);
            player.addEffect(new MobEffectInstance(MobEffects.WITHER, 20 * 5, 0), this);
            player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 20 * 4, 1), this);
        }
        spawnMinions(level, ModEntities.VOID_STALKER, 2, 6);
    }

    @Override
    protected void onEnrage(ServerLevel level) {
        this.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 20 * 60 * 30, 1, false, true));
        spawnMinions(level, ModEntities.VOID_STALKER, 4, 7);
    }
}
