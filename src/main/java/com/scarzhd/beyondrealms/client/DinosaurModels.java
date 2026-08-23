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

    public static class Carnivore extends EntityModel<LivingEntityRenderState> {
        private final ModelPart head, leftLeg, rightLeg, tail, leftArm, rightArm;
        public Carnivore(ModelPart root) {
            super(root);
            head = root.getChild("head"); leftLeg = root.getChild("left_leg"); rightLeg = root.getChild("right_leg");
            tail = root.getChild("tail"); leftArm = root.getChild("left_arm"); rightArm = root.getChild("right_arm");
        }
        public static LayerDefinition layer() {
            MeshDefinition mesh = new MeshDefinition(); PartDefinition root = mesh.getRoot();
            root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0,0).addBox(-6,-7,-9,12,13,20), PartPose.offset(0,11,2));
            PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0,34).addBox(-5,-5,-7,10,10,10), PartPose.offset(0,5,-10));
            head.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(40,34).addBox(-4,-2.5F,-8,8,5,8), PartPose.offset(0,1,-5));
            root.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(48,0).addBox(-3,-3,0,6,6,25), PartPose.offsetAndRotation(0,10,12,-.12F,0,0));
            root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0,58).addBox(-3,0,-3,6,15,6), PartPose.offset(5,10,5));
            root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(24,58).addBox(-3,0,-3,6,15,6), PartPose.offset(-5,10,5));
            root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(48,58).addBox(-1,0,-1,2,8,2), PartPose.offset(6,7,-5));
            root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(56,58).addBox(-1,0,-1,2,8,2), PartPose.offset(-6,7,-5));
            return LayerDefinition.create(mesh,128,128);
        }
        @Override public void setupAnim(LivingEntityRenderState s) {
            super.setupAnim(s); head.yRot=s.yRot*((float)Math.PI/180F); head.xRot=s.xRot*((float)Math.PI/180F);
            float p=s.walkAnimationPos*.62F,a=s.walkAnimationSpeed;
            leftLeg.xRot=Mth.cos(p)*1.0F*a; rightLeg.xRot=Mth.cos(p+(float)Math.PI)*1.0F*a;
            leftArm.xRot=Mth.cos(p+(float)Math.PI)*.45F*a; rightArm.xRot=Mth.cos(p)*.45F*a;
            tail.yRot=Mth.sin(s.ageInTicks*.07F)*.16F+Mth.sin(p*.5F)*.1F*a;
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
