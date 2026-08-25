package com.scarzhd.beyondrealms.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.scarzhd.beyondrealms.BeyondRealmsMod;
import com.scarzhd.beyondrealms.entity.RealmMobEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public final class DinosaurRenderers {
    private DinosaurRenderers() {}

    private abstract static class Base<M extends EntityModel<LivingEntityRenderState>> extends MobRenderer<RealmMobEntity, LivingEntityRenderState, M> {
        private final Identifier texture;
        private final float scale;
        protected Base(EntityRendererProvider.Context ctx, M model, float shadow, String textureName, float scale) {
            super(ctx, model, shadow); this.texture=BeyondRealmsMod.id("textures/entity/"+textureName+".png"); this.scale=scale;
        }
        @Override public Identifier getTextureLocation(LivingEntityRenderState state) { return texture; }
        @Override public LivingEntityRenderState createRenderState() { return new LivingEntityRenderState(); }
        @Override protected void scale(LivingEntityRenderState state, PoseStack poseStack) { poseStack.scale(scale, scale, scale); }
    }

    public static final class Carnivore extends Base<DinosaurModels.Carnivore> {
        public Carnivore(EntityRendererProvider.Context ctx, String texture, float scale, float shadow) { super(ctx,new DinosaurModels.Carnivore(ctx.bakeLayer(BeyondRealmsClient.CARNIVORE_DINOSAUR_LAYER)),shadow,texture,scale); }
    }
    public static final class Herbivore extends Base<DinosaurModels.Herbivore> {
        public Herbivore(EntityRendererProvider.Context ctx, String texture, float scale, float shadow) { super(ctx,new DinosaurModels.Herbivore(ctx.bakeLayer(BeyondRealmsClient.HERBIVORE_DINOSAUR_LAYER)),shadow,texture,scale); }
    }
    public static final class Spinosaur extends Base<DinosaurModels.Spinosaur> {
        public Spinosaur(EntityRendererProvider.Context ctx, String texture, float scale, float shadow) { super(ctx,new DinosaurModels.Spinosaur(ctx.bakeLayer(BeyondRealmsClient.SPINOSAUR_LAYER)),shadow,texture,scale); }
    }
    public static final class RaptorVariant extends Base<RealmMobModels.Raptor> {
        public RaptorVariant(EntityRendererProvider.Context ctx, String texture, float scale, float shadow) { super(ctx,new RealmMobModels.Raptor(ctx.bakeLayer(BeyondRealmsClient.PRIMEVAL_RAPTOR_LAYER)),shadow,texture,scale); }
    }
}
