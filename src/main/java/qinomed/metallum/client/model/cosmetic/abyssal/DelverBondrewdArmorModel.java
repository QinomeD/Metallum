package qinomed.metallum.client.model.cosmetic.abyssal;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import team.lodestar.lodestone.helpers.render.RenderHelper;
import team.lodestar.lodestone.systems.model.LodestoneArmorModel;

public class DelverBondrewdArmorModel extends LodestoneArmorModel {
    public static ModelLayerLocation LAYER = new ModelLayerLocation(new ResourceLocation("metallum", "bondrewd"), "main");
    public ModelPart helmetFullBright;

    public DelverBondrewdArmorModel(ModelPart root) {
        super(root);
        this.helmetFullBright = root.getChild("helmet_full_bright");
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.renderToBuffer(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        if (!this.young && slot == EquipmentSlot.HEAD) {
            helmetFullBright.render(matrixStack, buffer, RenderHelper.FULL_BRIGHT, packedOverlay, red, green, blue, alpha);
        }
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        if (this.slot == EquipmentSlot.CHEST) {
            return ImmutableList.of(this.body, this.leftArm, this.rightArm);
        } else if (this.slot == EquipmentSlot.LEGS) {
            return ImmutableList.of(this.leftLegging, this.rightLegging, this.leggings);
        } else {
            return this.slot == EquipmentSlot.FEET ? ImmutableList.of(this.leftFoot, this.rightFoot) : ImmutableList.of();
        }
    }

    @Override
    public void copyFromDefault(HumanoidModel model) {
        super.copyFromDefault(model);
        helmetFullBright.copyFrom(model.head);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = HumanoidModel.createMesh(new CubeDeformation(0), 0);
        PartDefinition root = createHumanoidAlias(mesh);
        PartDefinition helmetFullBright = root.addOrReplaceChild("helmet_full_bright", new CubeListBuilder(), PartPose.ZERO);

        PartDefinition head = root.getChild("head");
        PartDefinition helmet = head.addOrReplaceChild("helmet", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.525F))
                .texOffs(32, 0).addBox(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.95F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = root.getChild("body");
        PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.525F))
                .texOffs(16, 32).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.7F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition right_legging = root.getChild("right_legging");
        PartDefinition right_leg = right_legging.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-3.9F, -12.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.525F))
                .texOffs(0, 32).addBox(-3.9F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.7F)), PartPose.offset(2.0F, 12.0F, 0.0F));

        PartDefinition right_foot = root.getChild("right_foot");
        PartDefinition right_boot = right_foot.addOrReplaceChild("right_boot", CubeListBuilder.create().texOffs(32, 52).addBox(-3.1F, 7.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.25F))
                .texOffs(0, 52).addBox(-3.1F, 7.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.1F, 0.0F, 0.0F));

        PartDefinition right_arm = root.getChild("right_arm");
        PartDefinition right_shoulder = right_arm.addOrReplaceChild("right_shoulder", CubeListBuilder.create().texOffs(40, 16).addBox(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.525F))
                .texOffs(40, 32).addBox(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.7F)), PartPose.offset(5.0F, 22.0F, 0.0F));

        PartDefinition left_legging = root.getChild("left_legging");
        PartDefinition left_leg = left_legging.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-0.1F, -12.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.525F)).mirror(false)
                .texOffs(0, 32).mirror().addBox(-0.1F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.7F)).mirror(false), PartPose.offset(-2.0F, 12.0F, 0.0F));

        PartDefinition left_foot = root.getChild("left_foot");
        PartDefinition left_boot = left_foot.addOrReplaceChild("left_boot", CubeListBuilder.create().texOffs(32, 52).mirror().addBox(-2.9F, 7.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.25F)).mirror(false)
                .texOffs(0, 52).mirror().addBox(-2.9F, 7.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-0.1F, 0.0F, 0.0F));

        PartDefinition left_arm = root.getChild("left_arm");
        PartDefinition left_shoulder = left_arm.addOrReplaceChild("left_shoulder", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(4.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.525F)).mirror(false)
                .texOffs(40, 32).mirror().addBox(4.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.7F)).mirror(false), PartPose.offset(-5.0F, 22.0F, 0.0F));

        PartDefinition helmet_full_bright = helmetFullBright.addOrReplaceChild("helmet_full_bright", CubeListBuilder.create().texOffs(11, 8).addBox(-0.5F, -31.99F, -4.1F, 1.0F, 8.0F, 0.0F, new CubeDeformation(0.525F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }
}
