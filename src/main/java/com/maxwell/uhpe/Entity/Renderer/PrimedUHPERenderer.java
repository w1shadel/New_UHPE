package com.maxwell.uhpe.Entity.Renderer;

import com.maxwell.uhpe.Entity.BlockEntity.PrimedUHPE;
import com.maxwell.uhpe.Register.ModBlock;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;

import javax.annotation.Nullable;
import java.util.List;

public class PrimedUHPERenderer extends EntityRenderer<PrimedUHPE> {

    public PrimedUHPERenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.0F;
    }

    public void render(PrimedUHPE entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource source, int lightIn) {
        super.render(entity, entityYaw, partialTicks, poseStack, source, lightIn);
        float progress = (entity.getTime() + partialTicks) / PrimedUHPE.MAX_TIME;
        float expandScale = 1F + (float) Math.sin(progress * progress * Math.PI) * 0.5F;
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees((float) (Math.cos((double) entity.tickCount * 3.25D) * 1.2F * progress * Math.PI)));
        poseStack.scale(1F + progress * 0.03F, 1, 1F + progress * 0.03F);
        poseStack.pushPose();
        poseStack.scale(expandScale, expandScale - progress * 0.3F, expandScale);
        poseStack.translate(-0.5D, 0.0D, -0.5D);
        BlockState state = ModBlock.UHPE.get().defaultBlockState();
        BakedModel bakedmodel = Minecraft.getInstance().getBlockRenderer().getBlockModel(state);
        float f = 1.0F - progress * 0.5F;
        float f1 = 1.0F + progress;
        float f2 = 1.0F - progress;
        for (net.minecraft.client.renderer.RenderType rt : bakedmodel.getRenderTypes(state, RandomSource.create(42), ModelData.EMPTY)) {
            renderModel(poseStack.last(), source.getBuffer(net.minecraftforge.client.RenderTypeHelper.getEntityRenderType(rt, false)), state, bakedmodel, f, f1, f2, 240, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, rt);
        }
        poseStack.popPose();
        poseStack.popPose();
    }

    public static void renderModel(PoseStack.Pose p_111068_, VertexConsumer p_111069_, @Nullable BlockState p_111070_, BakedModel p_111071_, float p_111072_, float p_111073_, float p_111074_, int p_111075_, int p_111076_, net.minecraftforge.client.model.data.ModelData modelData, net.minecraft.client.renderer.RenderType renderType) {
        RandomSource randomsource = RandomSource.create();
        long i = 42L;

        for (Direction direction : Direction.values()) {
            randomsource.setSeed(42L);
            renderQuadList(p_111068_, p_111069_, p_111072_, p_111073_, p_111074_, p_111071_.getQuads(p_111070_, direction, randomsource, modelData, renderType), p_111075_, p_111076_);
        }

        randomsource.setSeed(42L);
        renderQuadList(p_111068_, p_111069_, p_111072_, p_111073_, p_111074_, p_111071_.getQuads(p_111070_, (Direction) null, randomsource, modelData, renderType), p_111075_, p_111076_);
    }

    private static void renderQuadList(PoseStack.Pose p_111059_, VertexConsumer p_111060_, float p_111061_, float p_111062_, float p_111063_, List<BakedQuad> p_111064_, int p_111065_, int p_111066_) {
        for (BakedQuad bakedquad : p_111064_) {
            float f;
            float f1;
            float f2;
            f = Mth.clamp(p_111061_, 0.0F, 1.0F);
            f1 = Mth.clamp(p_111062_, 0.0F, 1.0F);
            f2 = Mth.clamp(p_111063_, 0.0F, 1.0F);
            p_111060_.putBulkData(p_111059_, bakedquad, f, f1, f2, p_111065_, p_111066_);
        }

    }

    public ResourceLocation getTextureLocation(PrimedUHPE entity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}