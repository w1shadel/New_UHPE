package com.maxwell.uhpe.Entity.Item_ALL.TermitGrenade;

import com.maxwell.uhpe.Entity.Item_ALL.MineBomb.minebomb;
import com.maxwell.uhpe.Entity.Item_ALL.MineBomb.minebombModel;
import com.maxwell.uhpe.UHPE;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class termitRenderer extends MobRenderer<termit, termitModel<termit>> {
    private static final ResourceLocation TERMIT_LOCATION = new ResourceLocation(UHPE.MODID, "textures/entity/termit.png");
    public termitRenderer(EntityRendererProvider.Context context) {
        super(context, new termitModel<>(context.bakeLayer(termitModel.LAYER_LOCATION)), 0.1F);
    }
    @Override
    public ResourceLocation getTextureLocation(termit pEntity) {
        return TERMIT_LOCATION;
    }
}
