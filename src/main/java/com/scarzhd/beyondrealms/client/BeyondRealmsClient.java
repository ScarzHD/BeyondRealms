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
    public static final ModelLayerLocation CARNIVORE_DINOSAUR_LAYER = new ModelLayerLocation(BeyondRealmsMod.id("carnivore_dinosaur"), "main");
    public static final ModelLayerLocation HERBIVORE_DINOSAUR_LAYER = new ModelLayerLocation(BeyondRealmsMod.id("herbivore_dinosaur"), "main");
    public static final ModelLayerLocation SPINOSAUR_LAYER = new ModelLayerLocation(BeyondRealmsMod.id("spinosaur"), "main");

    @Override
    public void onInitializeClient() {
        ModelLayerRegistry.registerModelLayer(PRIMEVAL_RAPTOR_LAYER, RealmMobModels.Raptor::layer);
        ModelLayerRegistry.registerModelLayer(GRAVE_WRAITH_LAYER, RealmMobModels.Wraith::layer);
        ModelLayerRegistry.registerModelLayer(BROODLING_LAYER, RealmMobModels.Broodling::layer);
        ModelLayerRegistry.registerModelLayer(VOID_STALKER_LAYER, RealmMobModels.Stalker::layer);
        ModelLayerRegistry.registerModelLayer(CARNIVORE_DINOSAUR_LAYER, DinosaurModels.Carnivore::layer);
        ModelLayerRegistry.registerModelLayer(HERBIVORE_DINOSAUR_LAYER, DinosaurModels.Herbivore::layer);
        ModelLayerRegistry.registerModelLayer(SPINOSAUR_LAYER, DinosaurModels.Spinosaur::layer);

        EntityRenderers.register(ModEntities.PRIMEVAL_RAPTOR, RealmMobRenderers.Raptor::new);
        EntityRenderers.register(ModEntities.GRAVE_WRAITH, RealmMobRenderers.Wraith::new);
        EntityRenderers.register(ModEntities.BROODLING, RealmMobRenderers.Broodling::new);
        EntityRenderers.register(ModEntities.VOID_STALKER, RealmMobRenderers.Stalker::new);

        EntityRenderers.register(ModEntities.TYRANNOSAUR, ctx -> new DinosaurRenderers.Carnivore(ctx,"tyrannosaur",1.45F,1.35F));
        EntityRenderers.register(ModEntities.TRICERATOPS, ctx -> new DinosaurRenderers.Herbivore(ctx,"triceratops",1.25F,1.25F));
        EntityRenderers.register(ModEntities.DILOPHOSAURUS, ctx -> new DinosaurRenderers.RaptorVariant(ctx,"dilophosaurus",.92F,.75F));
        EntityRenderers.register(ModEntities.SPINOSAURUS, ctx -> new DinosaurRenderers.Spinosaur(ctx,"spinosaurus",1.50F,1.40F));
        EntityRenderers.register(ModEntities.ANKYLOSAURUS, ctx -> new DinosaurRenderers.Herbivore(ctx,"ankylosaurus",1.05F,1.20F));
        EntityRenderers.register(ModEntities.CARNOTAURUS, ctx -> new DinosaurRenderers.Carnivore(ctx,"carnotaurus",1.18F,1.10F));

        EntityRenderers.register(ModEntities.TYRANT_RAPTOR, ctx -> new DinosaurRenderers.RaptorVariant(ctx,"tyrant_raptor",1.18F,.9F));
        EntityRenderers.register(ModEntities.VENOMJAW_REX, ctx -> new DinosaurRenderers.Carnivore(ctx,"venomjaw_rex",1.55F,1.45F));
        EntityRenderers.register(ModEntities.SPECTRAL_RAPTOR, ctx -> new DinosaurRenderers.RaptorVariant(ctx,"spectral_raptor",1.05F,.8F));
        EntityRenderers.register(ModEntities.WEBFANG, ctx -> new DinosaurRenderers.RaptorVariant(ctx,"webfang",1.10F,.85F));
        EntityRenderers.register(ModEntities.VOID_REX, ctx -> new DinosaurRenderers.Carnivore(ctx,"void_rex",1.68F,1.55F));
        EntityRenderers.register(ModEntities.APEX_CHIMERA, ctx -> new DinosaurRenderers.Carnivore(ctx,"apex_chimera",1.85F,1.70F));
    }
}
