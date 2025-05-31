package com.maxwell.uhpe.Entity.Item_ALL.Mininuke;

import com.maxwell.uhpe.Entity.Item_ALL.MiniClasterBomb.MiniClasterBomb;
import com.maxwell.uhpe.Entity.Item_ALL.MiniClasterBomb.MiniClasterbombModel;
import com.maxwell.uhpe.UHPE;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MininukeRenderer extends MobRenderer<Mininuke, MininukeModel<Mininuke>> {
    private static final ResourceLocation DYNA_LOCATION = new ResourceLocation(UHPE.MODID, "textures/entity/mininuke.png");
    public MininukeRenderer(EntityRendererProvider.Context context) {
        super(context, new MininukeModel<>(context.bakeLayer(MininukeModel.LAYER_LOCATION)), 0.5F);
    }
    @Override
    public ResourceLocation getTextureLocation(Mininuke pEntity) {
        return DYNA_LOCATION;
    }
}