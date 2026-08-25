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

public final class RealmMobModels {
    private RealmMobModels() {}

    /** Lean, horizontal raptor body plan shared by Primeval Raptor and raptor hybrids. */
    public static class Raptor extends EntityModel<LivingEntityRenderState> {
        private final ModelPart head, jaw, leftLeg, rightLeg, tail1, tail2, leftArm, rightArm;

        public Raptor(ModelPart root) {
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

            root.addOrReplaceChild("hips", CubeListBuilder.create().texOffs(0,0).addBox(-3,-3,-3,6,6,7), PartPose.offset(0,14,5));
            root.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0,15).addBox(-3.5F,-3.5F,-7,7,7,11), PartPose.offsetAndRotation(0,12,-1,-0.12F,0,0));
            root.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(40,0).addBox(-2.5F,-2.5F,-5,5,5,7), PartPose.offsetAndRotation(0,10,-8,0.18F,0,0));

            PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(36,15).addBox(-3,-3,-5,6,6,6), PartPose.offset(0,8,-12));
            head.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(0,35).addBox(-2.5F,-1.5F,-6,5,3,6), PartPose.offset(0,1,-4));
            head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(24,35).addBox(-2.5F,-1,-5,5,2,6), PartPose.offset(0,3,-4));
            head.addOrReplaceChild("crest_l", CubeListBuilder.create().texOffs(48,35).addBox(-1,-4,-1,2,5,2), PartPose.offset(2,-2,-1));
            head.addOrReplaceChild("crest_r", CubeListBuilder.create().texOffs(48,35).mirror().addBox(-1,-4,-1,2,5,2), PartPose.offset(-2,-2,-1));

            PartDefinition tail1 = root.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0,48).addBox(-2,-2,0,4,4,14), PartPose.offsetAndRotation(0,13,8,-0.08F,0,0));
            tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(40,48).addBox(-1.5F,-1.5F,0,3,3,16), PartPose.offset(0,0,13));

            PartDefinition leftLeg = root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0,69).addBox(-2,-1,-2,4,8,4), PartPose.offset(2.8F,14,4));
            leftLeg.addOrReplaceChild("left_shin", CubeListBuilder.create().texOffs(16,69).addBox(-1.5F,-1,-1.5F,3,7,3), PartPose.offsetAndRotation(0,6,1,0.18F,0,0));
            leftLeg.addOrReplaceChild("left_foot", CubeListBuilder.create().texOffs(28,69).addBox(-1.5F,-1,-5,3,2,6), PartPose.offset(0,12,-1));
            leftLeg.addOrReplaceChild("left_claw", CubeListBuilder.create().texOffs(46,69).addBox(-0.5F,-2,-3,1,3,4), PartPose.offset(0,11,-3));

            PartDefinition rightLeg = root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0,69).mirror().addBox(-2,-1,-2,4,8,4), PartPose.offset(-2.8F,14,4));
            rightLeg.addOrReplaceChild("right_shin", CubeListBuilder.create().texOffs(16,69).mirror().addBox(-1.5F,-1,-1.5F,3,7,3), PartPose.offsetAndRotation(0,6,1,0.18F,0,0));
            rightLeg.addOrReplaceChild("right_foot", CubeListBuilder.create().texOffs(28,69).mirror().addBox(-1.5F,-1,-5,3,2,6), PartPose.offset(0,12,-1));
            rightLeg.addOrReplaceChild("right_claw", CubeListBuilder.create().texOffs(46,69).mirror().addBox(-0.5F,-2,-3,1,3,4), PartPose.offset(0,11,-3));

            PartDefinition leftArm = root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0,84).addBox(-1,0,-1,2,6,2), PartPose.offset(4,10,-5));
            leftArm.addOrReplaceChild("left_hand", CubeListBuilder.create().texOffs(8,84).addBox(-0.5F,-1,-3,1,2,4), PartPose.offset(0,5,-1));
            PartDefinition rightArm = root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0,84).mirror().addBox(-1,0,-1,2,6,2), PartPose.offset(-4,10,-5));
            rightArm.addOrReplaceChild("right_hand", CubeListBuilder.create().texOffs(8,84).mirror().addBox(-0.5F,-1,-3,1,2,4), PartPose.offset(0,5,-1));

            return LayerDefinition.create(mesh,96,96);
        }

        @Override
        public void setupAnim(LivingEntityRenderState s) {
            super.setupAnim(s);
            head.yRot = s.yRot * ((float)Math.PI / 180F);
            head.xRot = s.xRot * ((float)Math.PI / 180F) * 0.70F;
            jaw.xRot = 0.05F + Mth.abs(Mth.sin(s.ageInTicks * 0.075F)) * 0.06F;
            float p = s.walkAnimationPos * 0.78F, a = s.walkAnimationSpeed;
            leftLeg.xRot = Mth.cos(p) * 0.95F * a;
            rightLeg.xRot = Mth.cos(p + (float)Math.PI) * 0.95F * a;
            leftArm.xRot = Mth.cos(p + (float)Math.PI) * 0.30F * a - 0.18F;
            rightArm.xRot = Mth.cos(p) * 0.30F * a - 0.18F;
            tail1.yRot = Mth.sin(s.ageInTicks * 0.075F) * 0.13F + Mth.sin(p * 0.5F) * 0.10F * a;
            tail2.yRot = tail1.yRot * 1.55F;
        }
    }

    public static class Wraith extends EntityModel<LivingEntityRenderState> {
        private final ModelPart head,leftArm,rightArm;
        public Wraith(ModelPart root){ super(root); head=root.getChild("head"); leftArm=root.getChild("left_arm"); rightArm=root.getChild("right_arm"); }
        public static LayerDefinition layer(){ MeshDefinition mesh=new MeshDefinition(); PartDefinition root=mesh.getRoot(); root.addOrReplaceChild("shroud",CubeListBuilder.create().texOffs(0,16).addBox(-5,-9,-3,10,18,6),PartPose.offset(0,13,0)); root.addOrReplaceChild("head",CubeListBuilder.create().texOffs(0,0).addBox(-4,-4,-4,8,8,8),PartPose.offset(0,4,0)); root.addOrReplaceChild("left_arm",CubeListBuilder.create().texOffs(32,16).addBox(-1.5F,-1,-1.5F,3,18,3),PartPose.offset(6,7,0)); root.addOrReplaceChild("right_arm",CubeListBuilder.create().texOffs(44,16).addBox(-1.5F,-1,-1.5F,3,18,3),PartPose.offset(-6,7,0)); return LayerDefinition.create(mesh,64,64); }
        @Override public void setupAnim(LivingEntityRenderState s){ super.setupAnim(s); head.yRot=s.yRot*((float)Math.PI/180F); head.xRot=s.xRot*((float)Math.PI/180F); float d=Mth.sin(s.ageInTicks*.08F)*.18F; leftArm.zRot=-.2F+d; rightArm.zRot=.2F-d; }
    }

    public static class Broodling extends EntityModel<LivingEntityRenderState> {
        private final ModelPart head; private final ModelPart[] legs=new ModelPart[8];
        public Broodling(ModelPart root){ super(root); head=root.getChild("head"); for(int i=0;i<8;i++) legs[i]=root.getChild("leg"+i); }
        public static LayerDefinition layer(){ MeshDefinition mesh=new MeshDefinition(); PartDefinition root=mesh.getRoot(); root.addOrReplaceChild("head",CubeListBuilder.create().texOffs(0,0).addBox(-4,-3,-5,8,6,6),PartPose.offset(0,16,-3)); root.addOrReplaceChild("body",CubeListBuilder.create().texOffs(0,12).addBox(-4,-3,-4,8,6,8),PartPose.offset(0,16,2)); root.addOrReplaceChild("abdomen",CubeListBuilder.create().texOffs(28,0).addBox(-5,-4,-5,10,8,10),PartPose.offset(0,15,8)); CubeListBuilder r=CubeListBuilder.create().texOffs(24,20).addBox(-9,-1,-1,10,2,2), l=CubeListBuilder.create().texOffs(24,20).mirror().addBox(-1,-1,-1,10,2,2); for(int i=0;i<4;i++){ float z=-1+i*2F; root.addOrReplaceChild("leg"+i,r,PartPose.offsetAndRotation(-4,16,z,0,.55F-i*.35F,-.6F)); root.addOrReplaceChild("leg"+(i+4),l,PartPose.offsetAndRotation(4,16,z,0,-.55F+i*.35F,.6F)); } return LayerDefinition.create(mesh,64,64); }
        @Override public void setupAnim(LivingEntityRenderState s){ super.setupAnim(s); head.yRot=s.yRot*((float)Math.PI/180F); head.xRot=s.xRot*((float)Math.PI/180F); float p=s.walkAnimationPos*1.25F,a=s.walkAnimationSpeed; for(int i=0;i<4;i++){ float swing=Mth.sin(p+i*.8F)*.35F*a; legs[i].zRot=-.6F+swing; legs[i+4].zRot=.6F-swing; } }
    }

    public static class Stalker extends EntityModel<LivingEntityRenderState> {
        private final ModelPart head,leftArm,rightArm,leftLeg,rightLeg;
        public Stalker(ModelPart root){ super(root); head=root.getChild("head"); leftArm=root.getChild("left_arm"); rightArm=root.getChild("right_arm"); leftLeg=root.getChild("left_leg"); rightLeg=root.getChild("right_leg"); }
        public static LayerDefinition layer(){ MeshDefinition mesh=new MeshDefinition(); PartDefinition root=mesh.getRoot(); PartDefinition h=root.addOrReplaceChild("head",CubeListBuilder.create().texOffs(0,0).addBox(-4,-4,-4,8,8,8),PartPose.offset(0,2,0)); h.addOrReplaceChild("horn_l",CubeListBuilder.create().texOffs(32,0).addBox(0,-6,-1,2,7,2),PartPose.offset(3,-3,0)); h.addOrReplaceChild("horn_r",CubeListBuilder.create().texOffs(40,0).addBox(-2,-6,-1,2,7,2),PartPose.offset(-3,-3,0)); root.addOrReplaceChild("body",CubeListBuilder.create().texOffs(0,16).addBox(-4,-6,-2,8,14,4),PartPose.offset(0,10,0)); root.addOrReplaceChild("left_arm",CubeListBuilder.create().texOffs(24,16).addBox(-1.5F,0,-1.5F,3,20,3),PartPose.offset(5.5F,5,0)); root.addOrReplaceChild("right_arm",CubeListBuilder.create().texOffs(36,16).addBox(-1.5F,0,-1.5F,3,20,3),PartPose.offset(-5.5F,5,0)); root.addOrReplaceChild("left_leg",CubeListBuilder.create().texOffs(0,36).addBox(-1.5F,0,-1.5F,3,18,3),PartPose.offset(2,16,0)); root.addOrReplaceChild("right_leg",CubeListBuilder.create().texOffs(12,36).addBox(-1.5F,0,-1.5F,3,18,3),PartPose.offset(-2,16,0)); return LayerDefinition.create(mesh,64,64); }
        @Override public void setupAnim(LivingEntityRenderState s){ super.setupAnim(s); head.yRot=s.yRot*((float)Math.PI/180F); head.xRot=s.xRot*((float)Math.PI/180F); float p=s.walkAnimationPos*.45F,a=s.walkAnimationSpeed; leftLeg.xRot=Mth.cos(p)*.7F*a; rightLeg.xRot=Mth.cos(p+(float)Math.PI)*.7F*a; leftArm.xRot=Mth.cos(p+(float)Math.PI)*.45F*a; rightArm.xRot=Mth.cos(p)*.45F*a; }
    }
}
