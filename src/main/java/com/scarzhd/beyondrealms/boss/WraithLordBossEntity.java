package com.scarzhd.beyondrealms.boss;

import com.scarzhd.beyondrealms.registry.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.BossEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class WraithLordBossEntity extends RealmBossEntity {
    public WraithLordBossEntity(EntityType<? extends WraithLordBossEntity> type, Level level) {
        super(type, level, "Wraith Lord", BossEvent.BossBarColor.PURPLE, 110);
        this.xpReward = 180;
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity target) {
        if (!super.doHurtTarget(level, target)) return false;
        if (target instanceof LivingEntity living) {
            living.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 20 * 7, 0), this);
            living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20 * 8, 1), this);
        }
        return true;
    }

    @Override
    protected void performSpecialAttack(ServerLevel level, LivingEntity target) {
        target.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 20 * 10, 0), this);
        target.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 20 * 6, 1), this);
        spawnMinions(level, ModEntities.GRAVE_WRAITH, 2, 4);
    }
}
