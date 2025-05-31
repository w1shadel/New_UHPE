package com.maxwell.uhpe.Entity.Item_ALL.MiniClasterBomb;

import com.maxwell.uhpe.Entity.Item_ALL.ClasterBomb.ClasterBomb;
import com.maxwell.uhpe.Entity.Item_ALL.ClasterBomb.ClasterbombModel;
import com.maxwell.uhpe.UHPE;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MiniClasterBombRenderer extends MobRenderer<MiniClasterBomb,MiniClasterbombModel<MiniClasterBomb>> {
    private static final ResourceLocation DYNA_LOCATION = new ResourceLocation(UHPE.MODID, "textures/entity/clasterbomb.png");
    public MiniClasterBombRenderer(EntityRendererProvider.Context context) {
        super(context, new MiniClasterbombModel<>(context.bakeLayer(MiniClasterbombModel.LAYER_LOCATION)), 0.5F);
    }
    @Override
    public ResourceLocation getTextureLocation(MiniClasterBomb pEntity) {
        return DYNA_LOCATION;
    }
}