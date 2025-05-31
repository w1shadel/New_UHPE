package com.maxwell.uhpe.Entity.Item_ALL.C4;

import com.maxwell.uhpe.UHPE;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class C4Renderer extends MobRenderer<C4Entity, c4model<C4Entity>> {
    private static final ResourceLocation C4_LOCATION = new ResourceLocation(UHPE.MODID, "textures/entity/c4model.png");
    public C4Renderer(EntityRendererProvider.Context context) {
        super(context, new c4model<>(context.bakeLayer(c4model.LAYER_LOCATION)), 0.5F);
    }
    @Override
    public ResourceLocation getTextureLocation(C4Entity pEntity) {
        return C4_LOCATION;
    }
}
