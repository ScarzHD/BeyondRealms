package com.scarzhd.beyondrealms.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.scarzhd.beyondrealms.BeyondRealmsMod;
import com.scarzhd.beyondrealms.boss.BroodMotherBossEntity;
import com.scarzhd.beyondrealms.boss.TitanRexBossEntity;
import com.scarzhd.beyondrealms.boss.VoidTitanBossEntity;
import com.scarzhd.beyondrealms.boss.WraithLordBossEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public final class BossRenderers {
    private BossRenderers() {}

    private abstract static class Base<E extends net.minecraft.world.entity.Mob, M extends EntityModel<LivingEntityRenderState>>
            extends MobRenderer<E, LivingEntityRenderState, M> {
        private final Identifier texture;
        private final float scale;

        protected Base(EntityRendererProvider.Context ctx, M model, String textureName, float shadow, float scale) {
            super(ctx, model, shadow);
            this.texture = BeyondRealmsMod.id("textures/entity/boss/" + textureName + ".png");
            this.scale = scale;
        }

        @Override
        public Identifier getTextureLocation(LivingEntityRenderState state) {
            return texture;
        }

        @Override
        public LivingEntityRenderState createRenderState() {
            return new LivingEntityRenderState();
        }

        @Override
        protected void scale(LivingEntityRenderState state, PoseStack poseStack) {
            poseStack.scale(scale, scale, scale);
        }
    }

    public static final class TitanRex extends Base<TitanRexBossEntity, BossModels.TitanRex> {
        public TitanRex(EntityRendererProvider.Context ctx) {
            super(ctx, new BossModels.TitanRex(ctx.bakeLayer(BeyondRealmsClient.TITAN_REX_BOSS_LAYER)), "titan_rex", 2.2F, 1.05F);
        }
    }

    public static final class WraithLord extends Base<WraithLordBossEntity, BossModels.WraithLord> {
        public WraithLord(EntityRendererProvider.Context ctx) {
            super(ctx, new BossModels.WraithLord(ctx.bakeLayer(BeyondRealmsClient.WRAITH_LORD_BOSS_LAYER)), "wraith_lord", 1.4F, 1.15F);
        }
    }

    public static final class BroodMother extends Base<BroodMotherBossEntity, BossModels.BroodMother> {
        public BroodMother(EntityRendererProvider.Context ctx) {
            super(ctx, new BossModels.BroodMother(ctx.bakeLayer(BeyondRealmsClient.BROOD_MOTHER_BOSS_LAYER)), "brood_mother", 2.0F, 1.05F);
        }
    }

    public static final class VoidTitan extends Base<VoidTitanBossEntity, BossModels.VoidTitan> {
        public VoidTitan(EntityRendererProvider.Context ctx) {
            super(ctx, new BossModels.VoidTitan(ctx.bakeLayer(BeyondRealmsClient.VOID_TITAN_BOSS_LAYER)), "void_titan", 2.2F, 1.10F);
        }
    }
}
