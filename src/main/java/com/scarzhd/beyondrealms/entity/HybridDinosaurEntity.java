package com.scarzhd.beyondrealms.entity;

import com.scarzhd.beyondrealms.registry.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class HybridDinosaurEntity extends RealmMobEntity {
    public HybridDinosaurEntity(EntityType<? extends HybridDinosaurEntity> type, Level level) {
        super(type, level);
        this.xpReward = 30;
    }

    @Override
    public boolean doHurtTarget(ServerLevel level, Entity target) {
        if (!super.doHurtTarget(level, target)) return false;
        if (target instanceof LivingEntity living) {
            if (this.getType() == ModEntities.VENOMJAW_REX || this.getType() == ModEntities.WEBFANG) {
                living.addEffect(new MobEffectInstance(MobEffects.POISON, 20 * 10, 1), this);
            } else if (this.getType() == ModEntities.SPECTRAL_RAPTOR) {
                living.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 20 * 6, 0), this);
                living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20 * 10, 1), this);
            } else if (this.getType() == ModEntities.VOID_REX || this.getType() == ModEntities.APEX_CHIMERA) {
                living.addEffect(new MobEffectInstance(MobEffects.WITHER, 20 * 6, 1), this);
            }
        }
        return true;
    }
}
