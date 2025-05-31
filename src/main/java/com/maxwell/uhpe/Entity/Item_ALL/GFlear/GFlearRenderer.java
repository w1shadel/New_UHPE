package com.maxwell.uhpe.Entity.Item_ALL.GFlear;

import com.maxwell.uhpe.Entity.Item_ALL.Gasgranade.gasgranade;
import com.maxwell.uhpe.Entity.Item_ALL.Gasgranade.gasgranadeModel;
import com.maxwell.uhpe.UHPE;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GFlearRenderer extends MobRenderer<GFlear, GFlareEntityModel<GFlear>> {
    private static final ResourceLocation GAS_LOCATION = new ResourceLocation(UHPE.MODID, "textures/entity/gflearmodel.png");
    public GFlearRenderer(EntityRendererProvider.Context context) {
        super(context, new GFlareEntityModel<>(context.bakeLayer(GFlareEntityModel.LAYER_LOCATION)), 0.5F);
    }
    @Override
    public ResourceLocation getTextureLocation(GFlear pEntity) {
        return GAS_LOCATION;
    }
}