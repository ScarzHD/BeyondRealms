package com.scarzhd.beyondrealms.client;

import com.scarzhd.beyondrealms.BeyondRealmsMod;
import com.scarzhd.beyondrealms.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class BeyondRealmsClient implements ClientModInitializer {
    public static final ModelLayerLocation PRIMEVAL_RAPTOR_LAYER = new ModelLayerLocation(BeyondRealmsMod.id("primeval_raptor"), "main");
    public static final ModelLayerLocation GRAVE_WRAITH_LAYER = new ModelLayerLocation(BeyondRealmsMod.id("grave_wraith"), "main");
    public static final ModelLayerLocation BROODLING_LAYER = new ModelLayerLocation(BeyondRealmsMod.id("broodling"), "main");
    public static final ModelLayerLocation VOID_STALKER_LAYER = new ModelLayerLocation(BeyondRealmsMod.id("void_stalker"), "main");

    @Override
    public void onInitializeClient() {
        ModelLayerRegistry.registerModelLayer(PRIMEVAL_RAPTOR_LAYER, RealmMobModels.Raptor::layer);
        ModelLayerRegistry.registerModelLayer(GRAVE_WRAITH_LAYER, RealmMobModels.Wraith::layer);
        ModelLayerRegistry.registerModelLayer(BROODLING_LAYER, RealmMobModels.Broodling::layer);
        ModelLayerRegistry.registerModelLayer(VOID_STALKER_LAYER, RealmMobModels.Stalker::layer);
        EntityRenderers.register(ModEntities.PRIMEVAL_RAPTOR, RealmMobRenderers.Raptor::new);
        EntityRenderers.register(ModEntities.GRAVE_WRAITH, RealmMobRenderers.Wraith::new);
        EntityRenderers.register(ModEntities.BROODLING, RealmMobRenderers.Broodling::new);
        EntityRenderers.register(ModEntities.VOID_STALKER, RealmMobRenderers.Stalker::new);
    }
}
