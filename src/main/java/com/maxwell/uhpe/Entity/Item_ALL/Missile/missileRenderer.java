package com.maxwell.uhpe.Entity.Item_ALL.Missile;

import com.maxwell.uhpe.Entity.Item_ALL.MineBomb.minebomb;
import com.maxwell.uhpe.Entity.Item_ALL.MineBomb.minebombModel;
import com.maxwell.uhpe.UHPE;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class missileRenderer extends MobRenderer<Misslie, MissileModel<Misslie>> {
    private static final ResourceLocation MINE_LOCATION = new ResourceLocation(UHPE.MODID, "textures/entity/missile.png");
    public missileRenderer(EntityRendererProvider.Context context) {
        super(context, new MissileModel<>(context.bakeLayer(MissileModel.LAYER_LOCATION)), 0.1F);
    }
    @Override
    public ResourceLocation getTextureLocation(Misslie pEntity) {
        return MINE_LOCATION;
    }
}
