package com.scarzhd.beyondrealms.boss;

import java.util.List;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import com.scarzhd.beyondrealms.entity.RealmMobEntity;

public abstract class RealmBossEntity extends RealmMobEntity {
    private final ServerBossEvent bossEvent;
    private final int specialInterval;
    private int nextSpecialTick;
    private boolean enraged;

    protected RealmBossEntity(
            EntityType<? extends RealmBossEntity> type,
            Level level,
            String name,
            BossEvent.BossBarColor color,
            int specialInterval
    ) {
        super(type, level);
        this.xpReward = 100;
        this.specialInterval = specialInterval;
        this.nextSpecialTick = specialInterval;
        this.setCustomName(Component.literal(name));
        this.setCustomNameVisible(false);
        this.setPersistenceRequired();
        this.bossEvent = new ServerBossEvent(
                Mth.createInsecureUUID(this.random),
                Component.literal(name),
                color,
                BossEvent.BossBarOverlay.PROGRESS
        );
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.18, true));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 24.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    protected void customServerAiStep(ServerLevel level) {
        super.customServerAiStep(level);
        this.bossEvent.setProgress(Math.max(0.0F, this.getHealth() / this.getMaxHealth()));

        if (!this.enraged && this.getHealth() <= this.getMaxHealth() * 0.5F) {
            this.enraged = true;
            this.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 20 * 60 * 30, 1, false, true));
            this.addEffect(new MobEffectInstance(MobEffects.SPEED, 20 * 60 * 30, 0, false, true));
            onEnrage(level);
        }

        LivingEntity target = this.getTarget();
        if (target != null && target.isAlive() && this.tickCount >= this.nextSpecialTick) {
            performSpecialAttack(level, target);
            this.nextSpecialTick = this.tickCount + this.specialInterval;
        }
    }

    protected void onEnrage(ServerLevel level) {
    }

    protected abstract void performSpecialAttack(ServerLevel level, LivingEntity target);

    protected List<Player> nearbyPlayers(ServerLevel level, double radius) {
        return level.getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(radius));
    }

    protected void spawnMinions(ServerLevel level, EntityType<? extends RealmMobEntity> type, int count, int radius) {
        for (int i = 0; i < count; i++) {
            var pos = this.blockPosition().offset(
                    this.random.nextInt(radius * 2 + 1) - radius,
                    1,
                    this.random.nextInt(radius * 2 + 1) - radius
            );
            RealmMobEntity mob = type.create(level, EntitySpawnReason.MOB_SUMMONED);
            if (mob != null) {
                mob.snapTo(pos, this.getYRot(), 0.0F);
                mob.finalizeSpawn(level, level.getCurrentDifficultyAt(pos), EntitySpawnReason.MOB_SUMMONED, null);
                level.addFreshEntity(mob);
            }
        }
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossEvent.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossEvent.removePlayer(player);
    }
}
