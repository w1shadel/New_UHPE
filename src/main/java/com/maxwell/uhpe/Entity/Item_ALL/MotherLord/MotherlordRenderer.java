package com.maxwell.uhpe.Entity.Item_ALL.MotherLord;

import com.maxwell.uhpe.Entity.Item_ALL.Missile.MissileModel;
import com.maxwell.uhpe.Entity.Item_ALL.Missile.Misslie;
import com.maxwell.uhpe.UHPE;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MotherlordRenderer extends MobRenderer<Motherlord, MotherLordModel<Motherlord>> {
    private static final ResourceLocation MINE_LOCATION = new ResourceLocation(UHPE.MODID, "textures/entity/mflear.png");
    public MotherlordRenderer(EntityRendererProvider.Context context) {
        super(context, new MotherLordModel<>(context.bakeLayer(MotherLordModel.LAYER_LOCATION)), 0.1F);
    }
    @Override
    public ResourceLocation getTextureLocation(Motherlord pEntity) {
        return MINE_LOCATION;
    }
}
