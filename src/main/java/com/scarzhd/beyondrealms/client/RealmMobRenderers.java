package com.scarzhd.beyondrealms.client;

import com.scarzhd.beyondrealms.BeyondRealmsMod;
import com.scarzhd.beyondrealms.entity.RealmMobEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public final class RealmMobRenderers {
    private RealmMobRenderers() {}

    private abstract static class Base<M extends EntityModel<LivingEntityRenderState>> extends MobRenderer<RealmMobEntity, LivingEntityRenderState, M> {
        private final Identifier texture;
        protected Base(EntityRendererProvider.Context ctx, M model, float shadow, String textureName) {
            super(ctx, model, shadow);
            this.texture = BeyondRealmsMod.id("textures/entity/" + textureName + ".png");
        }
        @Override public Identifier getTextureLocation(LivingEntityRenderState state) { return texture; }
        @Override public LivingEntityRenderState createRenderState() { return new LivingEntityRenderState(); }
    }

    public static final class Raptor extends Base<RealmMobModels.Raptor> {
        public Raptor(EntityRendererProvider.Context ctx) { super(ctx, new RealmMobModels.Raptor(ctx.bakeLayer(BeyondRealmsClient.PRIMEVAL_RAPTOR_LAYER)), .8F, "primeval_raptor"); }
    }
    public static final class Wraith extends Base<RealmMobModels.Wraith> {
        public Wraith(EntityRendererProvider.Context ctx) { super(ctx, new RealmMobModels.Wraith(ctx.bakeLayer(BeyondRealmsClient.GRAVE_WRAITH_LAYER)), .65F, "grave_wraith"); }
    }
    public static final class Broodling extends Base<RealmMobModels.Broodling> {
        public Broodling(EntityRendererProvider.Context ctx) { super(ctx, new RealmMobModels.Broodling(ctx.bakeLayer(BeyondRealmsClient.BROODLING_LAYER)), .7F, "broodling"); }
    }
    public static final class Stalker extends Base<RealmMobModels.Stalker> {
        public Stalker(EntityRendererProvider.Context ctx) { super(ctx, new RealmMobModels.Stalker(ctx.bakeLayer(BeyondRealmsClient.VOID_STALKER_LAYER)), .75F, "void_stalker"); }
    }
}
