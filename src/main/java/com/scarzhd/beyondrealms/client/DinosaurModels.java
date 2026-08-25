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

public final class DinosaurModels {
    private DinosaurModels() {}

    /** T. rex / Carnotaurus / rex-hybrid body plan: long horizontal torso, deep skull, jaw, counterbalancing tail and digitigrade legs. */
    public static class Carnivore extends EntityModel<LivingEntityRenderState> {
        private final ModelPart head, jaw, leftLeg, rightLeg, tail1, tail2, leftArm, rightArm;

        public Carnivore(ModelPart root) {
            super(root);
            head = root.getChild("head");
            jaw = head.getChild("jaw");
            leftLeg = root.getChild("left_leg");
            rightLeg = root.getChild("right_leg");
            tail1 = root.getChild("tail1");
            tail2 = tail1.getChild("tail2");
            leftArm = root.getChild("left_arm");
            rightArm = root.getChild("right_arm");
        }

        public static LayerDefinition layer() {
            MeshDefinition mesh = new MeshDefinition();
            PartDefinition root = mesh.getRoot();

            root.addOrReplaceChild("hips", CubeListBuilder.create().texOffs(0,0).addBox(-5,-5,-4,10,10,10), PartPose.offset(0,12,7));
            root.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0,22).addBox(-6,-6,-10,12,12,16), PartPose.offsetAndRotation(0,10,-2,-0.10F,0,0));
            root.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(58,0).addBox(-4,-4,-7,8,8,9), PartPose.offsetAndRotation(0,8,-11,0.16F,0,0));

            PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(48,22).addBox(-5,-5,-8,10,10,10), PartPose.offset(0,5,-16));
            head.addOrReplaceChild("brow", CubeListBuilder.create().texOffs(0,54).addBox(-5,-2,-4,10,3,6), PartPose.offset(0,-4,-2));
            head.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(32,54).addBox(-4,-2.5F,-9,8,5,9), PartPose.offset(0,1,-7));
            head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(68,48).addBox(-4,-1,-8,8,3,9), PartPose.offset(0,5,-7));

            PartDefinition tail1 = root.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0,72).addBox(-3.5F,-3.5F,0,7,7,18), PartPose.offsetAndRotation(0,10,12,-0.10F,0,0));
            tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(48,72).addBox(-2.5F,-2.5F,0,5,5,20), PartPose.offsetAndRotation(0,0,17,-0.03F,0,0));

            PartDefinition leftLeg = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0,102).addBox(-3,-1,-3,6,11,6), PartPose.offset(4.5F,12,6));
            leftLeg.addOrReplaceChild("left_shin", CubeListBuilder.create().texOffs(24,102).addBox(-2,-1,-2,4,10,4), PartPose.offsetAndRotation(0,9,1,0.15F,0,0));
            leftLeg.addOrReplaceChild("left_foot", CubeListBuilder.create().texOffs(40,102).addBox(-2,-2,-7,4,4,9), PartPose.offset(0,18,-1));
            PartDefinition rightLeg = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0,102).mirror().addBox(-3,-1,-3,6,11,6), PartPose.offset(-4.5F,12,6));
            rightLeg.addOrReplaceChild("right_shin", CubeListBuilder.create().texOffs(24,102).mirror().addBox(-2,-1,-2,4,10,4), PartPose.offsetAndRotation(0,9,1,0.15F,0,0));
            rightLeg.addOrReplaceChild("right_foot", CubeListBuilder.create().texOffs(40,102).mirror().addBox(-2,-2,-7,4,4,9), PartPose.offset(0,18,-1));

            PartDefinition leftArm = root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(76,102).addBox(-1.5F,0,-1.5F,3,7,3), PartPose.offset(6,8,-7));
            leftArm.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(88,102).addBox(-1,-1,-4,2,2,5), PartPose.offset(0,6,-1));
            PartDefinition rightArm = root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(76,102).mirror().addBox(-1.5F,0,-1.5F,3,7,3), PartPose.offset(-6,8,-7));
            rightArm.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(88,102).mirror().addBox(-1,-1,-4,2,2,5), PartPose.offset(0,6,-1));

            return LayerDefinition.create(mesh,128,128);
        }

        @Override
        public void setupAnim(LivingEntityRenderState s) {
            super.setupAnim(s);
            head.yRot = s.yRot * ((float)Math.PI / 180F);
            head.xRot = s.xRot * ((float)Math.PI / 180F) * 0.60F;
            jaw.xRot = 0.06F + Mth.abs(Mth.sin(s.ageInTicks * 0.055F)) * 0.07F;
            float p = s.walkAnimationPos * 0.58F, a = s.walkAnimationSpeed;
            leftLeg.xRot = Mth.cos(p) * 0.78F * a;
            rightLeg.xRot = Mth.cos(p + (float)Math.PI) * 0.78F * a;
            leftArm.xRot = Mth.cos(p + (float)Math.PI) * 0.22F * a - 0.15F;
            rightArm.xRot = Mth.cos(p) * 0.22F * a - 0.15F;
            tail1.yRot = Mth.sin(s.ageInTicks * 0.055F) * 0.10F + Mth.sin(p * 0.5F) * 0.10F * a;
            tail2.yRot = tail1.yRot * 1.45F;
        }
    }

    public static class Herbivore extends EntityModel<LivingEntityRenderState> {
        private final ModelPart head, tail, leftFront, rightFront, leftBack, rightBack;
        public Herbivore(ModelPart root) {
            super(root); head=root.getChild("head"); tail=root.getChild("tail"); leftFront=root.getChild("left_front"); rightFront=root.getChild("right_front"); leftBack=root.getChild("left_back"); rightBack=root.getChild("right_back");
        }
        public static LayerDefinition layer() {
            MeshDefinition mesh=new MeshDefinition(); PartDefinition root=mesh.getRoot();
            root.addOrReplaceChild("body",CubeListBuilder.create().texOffs(0,0).addBox(-8,-7,-11,16,14,23),PartPose.offset(0,10,2));
            PartDefinition h=root.addOrReplaceChild("head",CubeListBuilder.create().texOffs(0,38).addBox(-5,-5,-7,10,10,9),PartPose.offset(0,8,-12));
            h.addOrReplaceChild("frill",CubeListBuilder.create().texOffs(40,40).addBox(-8,-7,-1,16,14,2),PartPose.offset(0,-1,2));
            h.addOrReplaceChild("horn_l",CubeListBuilder.create().texOffs(0,58).addBox(-1,-1,-8,2,2,9),PartPose.offset(3,-2,-6));
            h.addOrReplaceChild("horn_r",CubeListBuilder.create().texOffs(0,58).addBox(-1,-1,-8,2,2,9),PartPose.offset(-3,-2,-6));
            h.addOrReplaceChild("nose_horn",CubeListBuilder.create().texOffs(12,58).addBox(-1,-1,-5,2,2,6),PartPose.offset(0,2,-7));
            root.addOrReplaceChild("tail",CubeListBuilder.create().texOffs(56,0).addBox(-3,-3,0,6,6,18),PartPose.offsetAndRotation(0,9,13,-.08F,0,0));
            root.addOrReplaceChild("left_front",CubeListBuilder.create().texOffs(24,60).addBox(-2.5F,0,-2.5F,5,13,5),PartPose.offset(6,11,-6));
            root.addOrReplaceChild("right_front",CubeListBuilder.create().texOffs(44,60).addBox(-2.5F,0,-2.5F,5,13,5),PartPose.offset(-6,11,-6));
            root.addOrReplaceChild("left_back",CubeListBuilder.create().texOffs(64,60).addBox(-3,0,-3,6,13,6),PartPose.offset(6,11,8));
            root.addOrReplaceChild("right_back",CubeListBuilder.create().texOffs(88,60).addBox(-3,0,-3,6,13,6),PartPose.offset(-6,11,8));
            return LayerDefinition.create(mesh,128,128);
        }
        @Override public void setupAnim(LivingEntityRenderState s) {
            super.setupAnim(s); head.yRot=s.yRot*((float)Math.PI/180F); head.xRot=s.xRot*((float)Math.PI/180F)*.6F;
            float p=s.walkAnimationPos*.55F,a=s.walkAnimationSpeed;
            leftFront.xRot=Mth.cos(p)*.7F*a; rightBack.xRot=Mth.cos(p)*.7F*a;
            rightFront.xRot=Mth.cos(p+(float)Math.PI)*.7F*a; leftBack.xRot=Mth.cos(p+(float)Math.PI)*.7F*a;
            tail.yRot=Mth.sin(s.ageInTicks*.06F)*.12F;
        }
    }

    public static class Spinosaur extends EntityModel<LivingEntityRenderState> {
        private final ModelPart head, leftLeg, rightLeg, tail;
        public Spinosaur(ModelPart root) { super(root); head=root.getChild("head"); leftLeg=root.getChild("left_leg"); rightLeg=root.getChild("right_leg"); tail=root.getChild("tail"); }
        public static LayerDefinition layer() {
            MeshDefinition mesh=new MeshDefinition(); PartDefinition root=mesh.getRoot();
            root.addOrReplaceChild("body",CubeListBuilder.create().texOffs(0,0).addBox(-6,-7,-10,12,13,22),PartPose.offset(0,11,2));
            root.addOrReplaceChild("sail",CubeListBuilder.create().texOffs(50,0).addBox(-1,-16,-9,2,17,19),PartPose.offset(0,5,3));
            PartDefinition h=root.addOrReplaceChild("head",CubeListBuilder.create().texOffs(0,38).addBox(-4,-4,-8,8,8,10),PartPose.offset(0,6,-11));
            h.addOrReplaceChild("snout",CubeListBuilder.create().texOffs(36,38).addBox(-3,-2,-10,6,4,11),PartPose.offset(0,1,-6));
            root.addOrReplaceChild("tail",CubeListBuilder.create().texOffs(74,0).addBox(-3,-3,0,6,6,28),PartPose.offsetAndRotation(0,10,13,-.1F,0,0));
            root.addOrReplaceChild("left_leg",CubeListBuilder.create().texOffs(0,60).addBox(-3,0,-3,6,15,6),PartPose.offset(5,10,6));
            root.addOrReplaceChild("right_leg",CubeListBuilder.create().texOffs(24,60).addBox(-3,0,-3,6,15,6),PartPose.offset(-5,10,6));
            root.addOrReplaceChild("left_arm",CubeListBuilder.create().texOffs(48,60).addBox(-1.5F,0,-1.5F,3,9,3),PartPose.offset(6,7,-5));
            root.addOrReplaceChild("right_arm",CubeListBuilder.create().texOffs(60,60).addBox(-1.5F,0,-1.5F,3,9,3),PartPose.offset(-6,7,-5));
            return LayerDefinition.create(mesh,128,128);
        }
        @Override public void setupAnim(LivingEntityRenderState s) {
            super.setupAnim(s); head.yRot=s.yRot*((float)Math.PI/180F); head.xRot=s.xRot*((float)Math.PI/180F);
            float p=s.walkAnimationPos*.58F,a=s.walkAnimationSpeed; leftLeg.xRot=Mth.cos(p)*.9F*a; rightLeg.xRot=Mth.cos(p+(float)Math.PI)*.9F*a; tail.yRot=Mth.sin(s.ageInTicks*.06F)*.2F;
        }
    }
}
