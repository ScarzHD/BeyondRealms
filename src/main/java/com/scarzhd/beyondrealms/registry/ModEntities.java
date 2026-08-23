package com.scarzhd.beyondrealms.registry;

import com.scarzhd.beyondrealms.BeyondRealmsMod;
import com.scarzhd.beyondrealms.entity.RealmMobEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public final class ModEntities {
    public static final EntityType<RealmMobEntity> PRIMEVAL_RAPTOR = register("primeval_raptor", 1.15F, 1.55F);
    public static final EntityType<RealmMobEntity> GRAVE_WRAITH = register("grave_wraith", 0.85F, 2.10F);
    public static final EntityType<RealmMobEntity> BROODLING = register("broodling", 1.35F, 0.75F);
    public static final EntityType<RealmMobEntity> VOID_STALKER = register("void_stalker", 0.90F, 2.90F);

    private ModEntities() {}

    private static EntityType<RealmMobEntity> register(String name, float width, float height) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, BeyondRealmsMod.id(name));
        EntityType<RealmMobEntity> type = EntityType.Builder.of(RealmMobEntity::new, MobCategory.MONSTER).sized(width, height).build(key);
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type);
    }

    public static void initialize() {
        FabricDefaultAttributeRegistry.register(PRIMEVAL_RAPTOR, RealmMobEntity.attributes(36.0, 0.38, 7.0, 30.0, 0.15));
        FabricDefaultAttributeRegistry.register(GRAVE_WRAITH, RealmMobEntity.attributes(48.0, 0.31, 9.0, 38.0, 0.35));
        FabricDefaultAttributeRegistry.register(BROODLING, RealmMobEntity.attributes(30.0, 0.35, 6.0, 26.0, 0.20));
        FabricDefaultAttributeRegistry.register(VOID_STALKER, RealmMobEntity.attributes(64.0, 0.32, 12.0, 42.0, 0.50));
    }
}
