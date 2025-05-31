package com.maxwell.uhpe.Entity.Item_ALL.rollingthunder;

import com.maxwell.uhpe.Entity.Item_ALL.MineBomb.minebomb;
import com.maxwell.uhpe.Entity.Item_ALL.MineBomb.minebombModel;
import com.maxwell.uhpe.UHPE;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlareEntityRenderer extends MobRenderer<FlareEntity, FlareEntityModel<FlareEntity>> {
    private static final ResourceLocation MINE_LOCATION = new ResourceLocation(UHPE.MODID, "textures/entity/flare.png");
    public FlareEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new FlareEntityModel<>(context.bakeLayer(FlareEntityModel.LAYER_LOCATION)), 0.1F);
    }
    @Override
    public ResourceLocation getTextureLocation(FlareEntity pEntity) {
        return MINE_LOCATION;
    }
}
