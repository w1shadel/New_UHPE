package com.maxwell.uhpe.Entity.Item_ALL.dynamite;


import com.maxwell.uhpe.UHPE;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class dynamiteRenderer extends MobRenderer<dynamite, dynamiteModel<dynamite>>{
    private static final ResourceLocation DYNA_LOCATION = new ResourceLocation(UHPE.MODID, "textures/entity/dynamite.png");
    public dynamiteRenderer(EntityRendererProvider.Context context) {
        super(context, new dynamiteModel<>(context.bakeLayer(dynamiteModel.LAYER_LOCATION)), 0.5F);
    }
    @Override
    public ResourceLocation getTextureLocation(dynamite pEntity) {
        return DYNA_LOCATION;
    }
}