package com.maxwell.uhpe.Entity.Item_ALL.ClasterBomb;

import com.maxwell.uhpe.UHPE;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ClasterBombRenderer extends MobRenderer<ClasterBomb, ClasterbombModel<ClasterBomb>> {
    private static final ResourceLocation DYNA_LOCATION = new ResourceLocation(UHPE.MODID, "textures/entity/clasterbomb.png");
    public ClasterBombRenderer(EntityRendererProvider.Context context) {
        super(context, new ClasterbombModel<>(context.bakeLayer(ClasterbombModel.LAYER_LOCATION)), 0.5F);
    }
    @Override
    public ResourceLocation getTextureLocation(ClasterBomb pEntity) {
        return DYNA_LOCATION;
    }
}