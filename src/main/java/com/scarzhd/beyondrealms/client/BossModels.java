package com.scarzhd.beyondrealms.client;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

public final class BossModels {
    private BossModels() {}

    public static final class TitanRex extends EntityModel<LivingEntityRenderState> {
        private final ModelPart head, jaw, tail1, tail2, leftLeg, rightLeg, leftArm, rightArm;

        public TitanRex(ModelPart root) {
            super(root);
            head = root.getChild("head");
            jaw = head.getChild("jaw");
            tail1 = root.getChild("tail1");
            tail2 = tail1.getChild("tail2");
            leftLeg = root.getChild("left_leg");
            rightLeg = root.getChild("right_leg");
            leftArm = root.getChild("left_arm");
            rightArm = root.getChild("right_arm");
        }

        public static LayerDefinition layer() {
            MeshDefinition mesh = new MeshDefinition();
            PartDefinition root = mesh.getRoot();
            root.addOrReplaceChild("hips", CubeListBuilder.create().texOffs(0,0).addBox(-8,-7,-5,16,14,13), PartPose.offset(0,12,7));
            root.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0,28).addBox(-9,-8,-11,18,16,18), PartPose.offsetAndRotation(0,10,-3,-0.12F,0,0));
            root.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(72,0).addBox(-6,-6,-9,12,12,11), PartPose.offsetAndRotation(0,8,-13,0.18F,0,0));

            PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(64,26).addBox(-7,-6,-10,14,12,13), PartPose.offset(0,5,-19));
            head.addOrReplaceChild("brow_l", CubeListBuilder.create().texOffs(0,66).addBox(0,-2,-4,5,3,6), PartPose.offset(2,-5,-3));
            head.addOrReplaceChild("brow_r", CubeListBuilder.create().texOffs(0,66).mirror().addBox(-5,-2,-4,5,3,6), PartPose.offset(-2,-5,-3));
            head.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(24,66).addBox(-6,-3,-11,12,6,12), PartPose.offset(0,1,-8));
            head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(72,54).addBox(-5,-1,-10,10,4,11), PartPose.offset(0,5,-8));

            PartDefinition tail1 = root.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0,86).addBox(-5,-5,0,10,10,19), PartPose.offsetAndRotation(0,10,14,-0.10F,0,0));
            tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(58,86).addBox(-3.5F,-3.5F,0,7,7,22), PartPose.offsetAndRotation(0,0,18,-0.04F,0,0));

            root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0,118).addBox(-4,0,-4,8,18,8), PartPose.offset(7,11,8));
            root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32,118).addBox(-4,0,-4,8,18,8), PartPose.offset(-7,11,8));
            root.addOrReplaceChild("left_foot", CubeListBuilder.create().texOffs(64,118).addBox(-4,-2,-9,8,4,12), PartPose.offset(7,28,5));
            root.addOrReplaceChild("right_foot", CubeListBuilder.create().texOffs(64,118).mirror().addBox(-4,-2,-9,8,4,12), PartPose.offset(-7,28,5));
            root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(104,74).addBox(-2,0,-2,4,10,4), PartPose.offset(9,9,-8));
            root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(104,74).mirror().addBox(-2,0,-2,4,10,4), PartPose.offset(-9,9,-8));
            return LayerDefinition.create(mesh, 160, 160);
        }

        @Override
        public void setupAnim(LivingEntityRenderState s) {
            super.setupAnim(s);
            head.yRot = s.yRot * ((float)Math.PI / 180F);
            head.xRot = s.xRot * ((float)Math.PI / 180F) * 0.55F;
            jaw.xRot = 0.08F + Mth.abs(Mth.sin(s.ageInTicks * 0.06F)) * 0.10F;
            float p = s.walkAnimationPos * 0.52F, a = s.walkAnimationSpeed;
            leftLeg.xRot = Mth.cos(p) * 0.70F * a;
            rightLeg.xRot = Mth.cos(p + (float)Math.PI) * 0.70F * a;
            leftArm.xRot = Mth.cos(p + (float)Math.PI) * 0.25F * a;
            rightArm.xRot = Mth.cos(p) * 0.25F * a;
            tail1.yRot = Mth.sin(s.ageInTicks * 0.045F) * 0.12F + Mth.sin(p * 0.5F) * 0.08F * a;
            tail2.yRot = tail1.yRot * 1.4F;
        }
    }

    public static final class WraithLord extends EntityModel<LivingEntityRenderState> {
        private final ModelPart head, leftArm, rightArm, leftTendril, rightTendril;

        public WraithLord(ModelPart root) {
            super(root);
            head = root.getChild("head");
            leftArm = root.getChild("left_arm");
            rightArm = root.getChild("right_arm");
            leftTendril = root.getChild("left_tendril");
            rightTendril = root.getChild("right_tendril");
        }

        public static LayerDefinition layer() {
            MeshDefinition mesh = new MeshDefinition();
            PartDefinition root = mesh.getRoot();
            root.addOrReplaceChild("cloak", CubeListBuilder.create().texOffs(0,24).addBox(-8,-9,-4,16,22,8), PartPose.offset(0,12,0));
            root.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(52,20).addBox(-7,-6,-4,14,12,8), PartPose.offset(0,8,0));
            PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0,0).addBox(-5,-5,-5,10,10,10), PartPose.offset(0,1,0));
            head.addOrReplaceChild("crown_l", CubeListBuilder.create().texOffs(42,0).addBox(-1,-8,-1,2,9,2), PartPose.offset(3,-4,0));
            head.addOrReplaceChild("crown_r", CubeListBuilder.create().texOffs(50,0).addBox(-1,-8,-1,2,9,2), PartPose.offset(-3,-4,0));
            head.addOrReplaceChild("crown_mid", CubeListBuilder.create().texOffs(58,0).addBox(-1,-10,-1,2,11,2), PartPose.offset(0,-4,1));
            root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(92,20).addBox(-2,-2,-2,4,22,4), PartPose.offset(9,5,0));
            root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(108,20).addBox(-2,-2,-2,4,22,4), PartPose.offset(-9,5,0));
            root.addOrReplaceChild("left_tendril", CubeListBuilder.create().texOffs(0,58).addBox(-2,0,-2,4,19,4), PartPose.offsetAndRotation(4,21,1,0,0,0.18F));
            root.addOrReplaceChild("right_tendril", CubeListBuilder.create().texOffs(16,58).addBox(-2,0,-2,4,19,4), PartPose.offsetAndRotation(-4,21,1,0,0,-0.18F));
            root.addOrReplaceChild("back_spines", CubeListBuilder.create().texOffs(36,58).addBox(-1,-8,-1,2,20,2), PartPose.offsetAndRotation(0,10,5,0.35F,0,0));
            return LayerDefinition.create(mesh, 128, 128);
        }

        @Override
        public void setupAnim(LivingEntityRenderState s) {
            super.setupAnim(s);
            head.yRot = s.yRot * ((float)Math.PI / 180F);
            head.xRot = s.xRot * ((float)Math.PI / 180F);
            float wave = Mth.sin(s.ageInTicks * 0.065F);
            leftArm.zRot = -0.25F + wave * 0.12F;
            rightArm.zRot = 0.25F - wave * 0.12F;
            leftTendril.zRot = 0.18F + wave * 0.08F;
            rightTendril.zRot = -0.18F - wave * 0.08F;
        }
    }

    public static final class BroodMother extends EntityModel<LivingEntityRenderState> {
        private final ModelPart head;
        private final ModelPart[] legs = new ModelPart[8];

        public BroodMother(ModelPart root) {
            super(root);
            head = root.getChild("head");
            for (int i = 0; i < 8; i++) legs[i] = root.getChild("leg" + i);
        }

        public static LayerDefinition layer() {
            MeshDefinition mesh = new MeshDefinition();
            PartDefinition root = mesh.getRoot();
            root.addOrReplaceChild("abdomen", CubeListBuilder.create().texOffs(0,0).addBox(-10,-7,-9,20,14,20), PartPose.offset(0,13,9));
            root.addOrReplaceChild("thorax", CubeListBuilder.create().texOffs(0,38).addBox(-8,-6,-8,16,12,16), PartPose.offset(0,14,-5));
            PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(68,0).addBox(-7,-5,-7,14,10,10), PartPose.offset(0,14,-15));
            head.addOrReplaceChild("fang_l", CubeListBuilder.create().texOffs(68,24).addBox(-1,0,-7,2,5,8), PartPose.offset(3,3,-5));
            head.addOrReplaceChild("fang_r", CubeListBuilder.create().texOffs(76,24).addBox(-1,0,-7,2,5,8), PartPose.offset(-3,3,-5));
            for (int i = 0; i < 4; i++) {
                float z = -7 + i * 5.0F;
                root.addOrReplaceChild("leg" + i, CubeListBuilder.create().texOffs(0,72).addBox(-16,-2,-2,17,4,4), PartPose.offsetAndRotation(-7,15,z,0,0.28F - i * 0.17F,-0.42F));
                root.addOrReplaceChild("leg" + (i + 4), CubeListBuilder.create().texOffs(0,72).mirror().addBox(-1,-2,-2,17,4,4), PartPose.offsetAndRotation(7,15,z,0,-0.28F + i * 0.17F,0.42F));
            }
            root.addOrReplaceChild("spine", CubeListBuilder.create().texOffs(0,88).addBox(-1,-8,-7,2,10,16), PartPose.offset(0,7,8));
            return LayerDefinition.create(mesh, 128, 128);
        }

        @Override
        public void setupAnim(LivingEntityRenderState s) {
            super.setupAnim(s);
            head.yRot = s.yRot * ((float)Math.PI / 180F) * 0.65F;
            head.xRot = s.xRot * ((float)Math.PI / 180F) * 0.45F;
            float p = s.walkAnimationPos * 1.0F, a = s.walkAnimationSpeed;
            for (int i = 0; i < 4; i++) {
                float swing = Mth.sin(p + i * 0.8F) * 0.22F * a;
                legs[i].zRot = -0.42F + swing;
                legs[i + 4].zRot = 0.42F - swing;
            }
        }
    }

    public static final class VoidTitan extends EntityModel<LivingEntityRenderState> {
        private final ModelPart head, leftArm, rightArm, leftLeg, rightLeg;

        public VoidTitan(ModelPart root) {
            super(root);
            head = root.getChild("head");
            leftArm = root.getChild("left_arm");
            rightArm = root.getChild("right_arm");
            leftLeg = root.getChild("left_leg");
            rightLeg = root.getChild("right_leg");
        }

        public static LayerDefinition layer() {
            MeshDefinition mesh = new MeshDefinition();
            PartDefinition root = mesh.getRoot();
            root.addOrReplaceChild("pelvis", CubeListBuilder.create().texOffs(0,0).addBox(-7,-5,-5,14,10,10), PartPose.offset(0,14,0));
            root.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(0,24).addBox(-11,-10,-5,22,20,10), PartPose.offset(0,1,0));
            root.addOrReplaceChild("core", CubeListBuilder.create().texOffs(72,28).addBox(-4,-4,-2,8,8,4), PartPose.offset(0,0,-6));
            PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(64,0).addBox(-6,-6,-6,12,12,12), PartPose.offset(0,-14,0));
            head.addOrReplaceChild("horn_l", CubeListBuilder.create().texOffs(112,0).addBox(-2,-12,-2,4,13,4), PartPose.offsetAndRotation(4,-5,0,0,0,-0.25F));
            head.addOrReplaceChild("horn_r", CubeListBuilder.create().texOffs(112,0).mirror().addBox(-2,-12,-2,4,13,4), PartPose.offsetAndRotation(-4,-5,0,0,0,0.25F));
            root.addOrReplaceChild("left_shoulder", CubeListBuilder.create().texOffs(0,58).addBox(-5,-4,-5,10,8,10), PartPose.offset(14,-3,0));
            root.addOrReplaceChild("right_shoulder", CubeListBuilder.create().texOffs(40,58).addBox(-5,-4,-5,10,8,10), PartPose.offset(-14,-3,0));
            root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(80,58).addBox(-4,0,-4,8,25,8), PartPose.offset(15,0,0));
            root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(112,58).addBox(-4,0,-4,8,25,8), PartPose.offset(-15,0,0));
            root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0,84).addBox(-5,0,-5,10,23,10), PartPose.offset(6,17,0));
            root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(40,84).addBox(-5,0,-5,10,23,10), PartPose.offset(-6,17,0));
            root.addOrReplaceChild("back_spine", CubeListBuilder.create().texOffs(84,96).addBox(-2,-20,-2,4,28,4), PartPose.offsetAndRotation(0,3,6,0.18F,0,0));
            return LayerDefinition.create(mesh, 160, 160);
        }

        @Override
        public void setupAnim(LivingEntityRenderState s) {
            super.setupAnim(s);
            head.yRot = s.yRot * ((float)Math.PI / 180F) * 0.75F;
            head.xRot = s.xRot * ((float)Math.PI / 180F) * 0.65F;
            float p = s.walkAnimationPos * 0.42F, a = s.walkAnimationSpeed;
            leftLeg.xRot = Mth.cos(p) * 0.55F * a;
            rightLeg.xRot = Mth.cos(p + (float)Math.PI) * 0.55F * a;
            leftArm.xRot = Mth.cos(p + (float)Math.PI) * 0.28F * a;
            rightArm.xRot = Mth.cos(p) * 0.28F * a;
        }
    }
}
