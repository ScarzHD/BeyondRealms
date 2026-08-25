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

public class BroodMotherBossEntity extends RealmBossEntity {
    public BroodMotherBossEntity(EntityType<? extends BroodMotherBossEntity> type, Level level) {
        super(type, level, "Brood Mother", BossEvent.BossBarColor.GREEN, 100);
        this.xpReward = 170;
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity target) {
        if (!super.doHurtTarget(level, target)) return false;
        if (target instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(MobEffects.POISON, 20 * 10, 1), this);
        }
        return true;
    }

    @Override
    protected void performSpecialAttack(ServerLevel level, LivingEntity target) {
        for (Player player : nearbyPlayers(level, 9.0)) {
            player.addEffect(new MobEffectInstance(MobEffects.POISON, 20 * 8, 1), this);
            player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 20 * 6, 1), this);
        }
        spawnMinions(level, ModEntities.BROODLING, 4, 5);
    }

    @Override
    protected void onEnrage(ServerLevel level) {
        spawnMinions(level, ModEntities.BROODLING, 6, 6);
    }
}
