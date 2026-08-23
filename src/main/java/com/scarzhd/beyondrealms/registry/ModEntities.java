package com.scarzhd.beyondrealms.registry;

import com.scarzhd.beyondrealms.BeyondRealmsMod;
import com.scarzhd.beyondrealms.entity.HerbivoreDinosaurEntity;
import com.scarzhd.beyondrealms.entity.HybridDinosaurEntity;
import com.scarzhd.beyondrealms.entity.RealmMobEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public final class ModEntities {
    public static final EntityType<RealmMobEntity> PRIMEVAL_RAPTOR = hostile("primeval_raptor", 1.15F, 1.55F);
    public static final EntityType<RealmMobEntity> GRAVE_WRAITH = hostile("grave_wraith", 0.85F, 2.10F);
    public static final EntityType<RealmMobEntity> BROODLING = hostile("broodling", 1.35F, 0.75F);
    public static final EntityType<RealmMobEntity> VOID_STALKER = hostile("void_stalker", 0.90F, 2.90F);

    public static final EntityType<RealmMobEntity> TYRANNOSAUR = hostile("tyrannosaur", 2.60F, 3.20F);
    public static final EntityType<HerbivoreDinosaurEntity> TRICERATOPS = herbivore("triceratops", 2.40F, 2.00F);
    public static final EntityType<RealmMobEntity> DILOPHOSAURUS = hostile("dilophosaurus", 1.40F, 1.70F);
    public static final EntityType<RealmMobEntity> SPINOSAURUS = hostile("spinosaurus", 2.80F, 3.20F);
    public static final EntityType<HerbivoreDinosaurEntity> ANKYLOSAURUS = herbivore("ankylosaurus", 2.50F, 1.50F);
    public static final EntityType<RealmMobEntity> CARNOTAURUS = hostile("carnotaurus", 2.10F, 2.70F);

    public static final EntityType<HybridDinosaurEntity> TYRANT_RAPTOR = hybrid("tyrant_raptor", 1.50F, 2.00F);
    public static final EntityType<HybridDinosaurEntity> VENOMJAW_REX = hybrid("venomjaw_rex", 2.80F, 3.30F);
    public static final EntityType<HybridDinosaurEntity> SPECTRAL_RAPTOR = hybrid("spectral_raptor", 1.30F, 1.80F);
    public static final EntityType<HybridDinosaurEntity> WEBFANG = hybrid("webfang", 1.50F, 1.70F);
    public static final EntityType<HybridDinosaurEntity> VOID_REX = hybrid("void_rex", 3.00F, 3.50F);
    public static final EntityType<HybridDinosaurEntity> APEX_CHIMERA = hybrid("apex_chimera", 3.20F, 3.80F);

    private ModEntities() {}

    private static EntityType<RealmMobEntity> hostile(String name, float width, float height) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, BeyondRealmsMod.id(name));
        EntityType<RealmMobEntity> type = EntityType.Builder.of(RealmMobEntity::new, MobCategory.MONSTER).sized(width, height).build(key);
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type);
    }

    private static EntityType<HerbivoreDinosaurEntity> herbivore(String name, float width, float height) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, BeyondRealmsMod.id(name));
        EntityType<HerbivoreDinosaurEntity> type = EntityType.Builder.of(HerbivoreDinosaurEntity::new, MobCategory.CREATURE).sized(width, height).build(key);
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type);
    }

    private static EntityType<HybridDinosaurEntity> hybrid(String name, float width, float height) {
        ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, BeyondRealmsMod.id(name));
        EntityType<HybridDinosaurEntity> type = EntityType.Builder.of(HybridDinosaurEntity::new, MobCategory.MONSTER).sized(width, height).build(key);
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type);
    }

    public static void initialize() {
        FabricDefaultAttributeRegistry.register(PRIMEVAL_RAPTOR, RealmMobEntity.attributes(36.0, 0.38, 7.0, 30.0, 0.15));
        FabricDefaultAttributeRegistry.register(GRAVE_WRAITH, RealmMobEntity.attributes(48.0, 0.31, 9.0, 38.0, 0.35));
        FabricDefaultAttributeRegistry.register(BROODLING, RealmMobEntity.attributes(30.0, 0.35, 6.0, 26.0, 0.20));
        FabricDefaultAttributeRegistry.register(VOID_STALKER, RealmMobEntity.attributes(64.0, 0.32, 12.0, 42.0, 0.50));

        FabricDefaultAttributeRegistry.register(TYRANNOSAUR, RealmMobEntity.attributes(120.0, 0.28, 18.0, 42.0, 0.55));
        FabricDefaultAttributeRegistry.register(TRICERATOPS, RealmMobEntity.attributes(100.0, 0.24, 14.0, 28.0, 0.70));
        FabricDefaultAttributeRegistry.register(DILOPHOSAURUS, RealmMobEntity.attributes(50.0, 0.34, 9.0, 32.0, 0.20));
        FabricDefaultAttributeRegistry.register(SPINOSAURUS, RealmMobEntity.attributes(140.0, 0.27, 20.0, 44.0, 0.60));
        FabricDefaultAttributeRegistry.register(ANKYLOSAURUS, RealmMobEntity.attributes(110.0, 0.20, 16.0, 24.0, 0.80));
        FabricDefaultAttributeRegistry.register(CARNOTAURUS, RealmMobEntity.attributes(90.0, 0.35, 15.0, 38.0, 0.45));

        FabricDefaultAttributeRegistry.register(TYRANT_RAPTOR, RealmMobEntity.attributes(85.0, 0.42, 15.0, 40.0, 0.35));
        FabricDefaultAttributeRegistry.register(VENOMJAW_REX, RealmMobEntity.attributes(160.0, 0.30, 23.0, 46.0, 0.65));
        FabricDefaultAttributeRegistry.register(SPECTRAL_RAPTOR, RealmMobEntity.attributes(70.0, 0.44, 13.0, 44.0, 0.20));
        FabricDefaultAttributeRegistry.register(WEBFANG, RealmMobEntity.attributes(80.0, 0.40, 12.0, 38.0, 0.30));
        FabricDefaultAttributeRegistry.register(VOID_REX, RealmMobEntity.attributes(190.0, 0.31, 26.0, 50.0, 0.75));
        FabricDefaultAttributeRegistry.register(APEX_CHIMERA, RealmMobEntity.attributes(260.0, 0.34, 32.0, 56.0, 0.85));
    }
}
