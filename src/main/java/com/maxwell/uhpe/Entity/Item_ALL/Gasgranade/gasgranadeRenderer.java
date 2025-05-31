package com.maxwell.uhpe.Entity.Item_ALL.Gasgranade;

import com.maxwell.uhpe.Entity.Item_ALL.dynamite.dynamite;
import com.maxwell.uhpe.Entity.Item_ALL.dynamite.dynamiteModel;
import com.maxwell.uhpe.UHPE;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class gasgranadeRenderer extends MobRenderer<gasgranade, gasgranadeModel<gasgranade>> {
    private static final ResourceLocation GAS_LOCATION = new ResourceLocation(UHPE.MODID, "textures/entity/gasgranade.png");
    public gasgranadeRenderer(EntityRendererProvider.Context context) {
        super(context, new gasgranadeModel<>(context.bakeLayer(gasgranadeModel.LAYER_LOCATION)), 0.5F);
    }
    @Override
    public ResourceLocation getTextureLocation(gasgranade pEntity) {
        return GAS_LOCATION;
    }
}