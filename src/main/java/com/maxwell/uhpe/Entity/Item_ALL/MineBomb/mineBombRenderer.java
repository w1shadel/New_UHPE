package com.maxwell.uhpe.Entity.Item_ALL.MineBomb;

import com.maxwell.uhpe.Entity.Item_ALL.dynamite.dynamite;
import com.maxwell.uhpe.Entity.Item_ALL.dynamite.dynamiteModel;
import com.maxwell.uhpe.UHPE;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class mineBombRenderer extends MobRenderer<minebomb, minebombModel<minebomb>> {
    private static final ResourceLocation MINE_LOCATION = new ResourceLocation(UHPE.MODID, "textures/entity/minebomb.png");
    public mineBombRenderer(EntityRendererProvider.Context context) {
        super(context, new minebombModel<>(context.bakeLayer(minebombModel.LAYER_LOCATION)), 0.1F);
    }
    @Override
    public ResourceLocation getTextureLocation(minebomb pEntity) {
        return MINE_LOCATION;
    }
}
