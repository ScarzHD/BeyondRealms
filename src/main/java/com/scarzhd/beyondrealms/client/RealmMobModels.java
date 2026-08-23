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

    public static class Raptor extends EntityModel<LivingEntityRenderState> {
        private final ModelPart head, leftLeg, rightLeg, tail;
        public Raptor(ModelPart root) { super(root); head=root.getChild("head"); leftLeg=root.getChild("left_leg"); rightLeg=root.getChild("right_leg"); tail=root.getChild("tail"); }
        public static LayerDefinition layer() {
            MeshDefinition mesh=new MeshDefinition(); PartDefinition root=mesh.getRoot();
            root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0,0).addBox(-4,-5,-6,8,9,14), PartPose.offset(0,13,1));
            PartDefinition h=root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0,24).addBox(-3,-4,-5,6,7,7), PartPose.offset(0,9,-6));
            h.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(26,24).addBox(-2.5F,-1.5F,-5,5,3,5), PartPose.offset(0,0,-4));
            root.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(32,0).addBox(-2,-2,0,4,4,15), PartPose.offsetAndRotation(0,12,8,-0.15F,0,0));
            root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0,40).addBox(-2,0,-2,4,11,4), PartPose.offset(3,14,3));
            root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(16,40).addBox(-2,0,-2,4,11,4), PartPose.offset(-3,14,3));
            root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32,38).addBox(-1,0,-1,2,7,2), PartPose.offset(4,10,-3));
            root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40,38).addBox(-1,0,-1,2,7,2), PartPose.offset(-4,10,-3));
            return LayerDefinition.create(mesh,64,64);
        }
        @Override public void setupAnim(LivingEntityRenderState s){ super.setupAnim(s); head.yRot=s.yRot*((float)Math.PI/180F); head.xRot=s.xRot*((float)Math.PI/180F); float p=s.walkAnimationPos*.75F, a=s.walkAnimationSpeed; leftLeg.xRot=Mth.cos(p)*1.2F*a; rightLeg.xRot=Mth.cos(p+(float)Math.PI)*1.2F*a; tail.yRot=Mth.sin(s.ageInTicks*.08F)*.18F+Mth.sin(p*.5F)*.12F*a; }
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
