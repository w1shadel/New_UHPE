package com.maxwell.uhpe.Register;

import com.maxwell.uhpe.Entity.Item_ALL.C4.C4Renderer;
import com.maxwell.uhpe.Entity.Item_ALL.C4.c4model;
import com.maxwell.uhpe.Entity.Item_ALL.ClasterBomb.ClasterBombRenderer;
import com.maxwell.uhpe.Entity.Item_ALL.ClasterBomb.ClasterbombModel;
import com.maxwell.uhpe.Entity.Item_ALL.GFlear.GFlareEntityModel;
import com.maxwell.uhpe.Entity.Item_ALL.GFlear.GFlearRenderer;
import com.maxwell.uhpe.Entity.Item_ALL.Gasgranade.gasgranadeModel;
import com.maxwell.uhpe.Entity.Item_ALL.MineBomb.minebombModel;
import com.maxwell.uhpe.Entity.Item_ALL.MiniClasterBomb.MiniClasterBombRenderer;
import com.maxwell.uhpe.Entity.Item_ALL.MiniClasterBomb.MiniClasterbombModel;
import com.maxwell.uhpe.Entity.Item_ALL.Mininuke.MininukeModel;
import com.maxwell.uhpe.Entity.Item_ALL.Mininuke.MininukeRenderer;
import com.maxwell.uhpe.Entity.Item_ALL.Missile.MissileModel;
import com.maxwell.uhpe.Entity.Item_ALL.Missile.missileRenderer;
import com.maxwell.uhpe.Entity.Item_ALL.MotherLord.MotherLordModel;
import com.maxwell.uhpe.Entity.Item_ALL.MotherLord.MotherlordRenderer;
import com.maxwell.uhpe.Entity.Item_ALL.TermitGrenade.termitModel;
import com.maxwell.uhpe.Entity.Item_ALL.dynamite.dynamiteModel;
import com.maxwell.uhpe.Entity.Item_ALL.MineBomb.mineBombRenderer;
import com.maxwell.uhpe.Entity.Item_ALL.Gasgranade.gasgranadeRenderer;
import com.maxwell.uhpe.Entity.Item_ALL.TermitGrenade.termitRenderer;
import com.maxwell.uhpe.Entity.Item_ALL.rollingthunder.FlareEntityModel;
import com.maxwell.uhpe.Entity.Item_ALL.rollingthunder.FlareEntityRenderer;
import com.maxwell.uhpe.Entity.Renderer.PrimedUHCPERenderer;
import com.maxwell.uhpe.Entity.Renderer.PrimedUHPERenderer;
import com.maxwell.uhpe.UHPE;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.maxwell.uhpe.Entity.Item_ALL.dynamite.dynamiteRenderer;

@Mod.EventBusSubscriber(modid = UHPE.MODID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT)
public class MaxwellsEventBusClientEvent {
    @SubscribeEvent
    public static void onRegisterRenderers_NCB(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntity.NUCLEAR_BOMB.get(), PrimedUHPERenderer::new);
    }
    @SubscribeEvent
    public static void onRegisterRenderers_CHP(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntity.COMPRESSED_NUCLEAR_BOMB.get(), PrimedUHCPERenderer::new);
    }
    @SubscribeEvent
    public static void onRegisterRenderers_C4(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntity.C4.get(), C4Renderer::new);
    }
    @SubscribeEvent
    public static void registerLayerDefinitions_C4(
            EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(c4model.LAYER_LOCATION,
                c4model::createBodyLayer);
    }
    @SubscribeEvent
    public static void onRegisterRenderers_DYNA(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntity.DYNAMITE.get(), dynamiteRenderer::new);
    }
    @SubscribeEvent
    public static void registerLayerDefinitions_DYNA(
            EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(dynamiteModel.LAYER_LOCATION,
                dynamiteModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void onRegisterRenderers_mine(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntity.MINEBOMB.get(), mineBombRenderer::new);
    }
    @SubscribeEvent
    public static void registerLayerDefinitions_mine(
            EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(minebombModel.LAYER_LOCATION,
                minebombModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void onRegisterRenderers_termit(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntity.TERMIT.get(), termitRenderer::new);
    }
    @SubscribeEvent
    public static void registerLayerDefinitions_termit(
            EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(termitModel.LAYER_LOCATION,
                termitModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void onRegisterRenderers_gas(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntity.GAS.get(), gasgranadeRenderer::new);
    }
    @SubscribeEvent
    public static void registerLayerDefinitions_gas(
            EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(gasgranadeModel.LAYER_LOCATION,
                gasgranadeModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void onRegisterRenderers_rolling(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntity.FLARE.get(), FlareEntityRenderer::new);
    }
    @SubscribeEvent
    public static void registerLayerDefinitions_rolling(
            EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(FlareEntityModel.LAYER_LOCATION,
                FlareEntityModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void onRegisterRenderers_Grolling(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntity.GFLARE.get(), GFlearRenderer::new);
    }
    @SubscribeEvent
    public static void registerLayerDefinitions_Grolling(
            EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GFlareEntityModel.LAYER_LOCATION,
                GFlareEntityModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void onRegisterRenderers_Missile(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntity.MISSILE.get(), missileRenderer::new);
    }
    @SubscribeEvent
    public static void registerLayerDefinitions_Missile(
            EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MissileModel.LAYER_LOCATION,
                MissileModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void onRegisterRenderers_Mflear(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntity.MFIRE.get(), MotherlordRenderer::new);
    }
    @SubscribeEvent
    public static void registerLayerDefinitions_Mflear(
            EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MotherLordModel.LAYER_LOCATION,
                MotherLordModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void onRegisterRenderers_nclaster(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntity.NCLASTER.get(), ClasterBombRenderer::new);
    }
    @SubscribeEvent
    public static void registerLayerDefinitions_nclaster(
            EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ClasterbombModel.LAYER_LOCATION,
                ClasterbombModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void onRegisterRenderers_Mnclaster(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntity.NCLASTER_MINI.get(), MiniClasterBombRenderer::new);
    }
    @SubscribeEvent
    public static void registerLayerDefinitions_Mnclaster(
            EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MiniClasterbombModel.LAYER_LOCATION,
                MiniClasterbombModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void onRegisterRenderers_Mininuke(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntity.MININUKE.get(), MininukeRenderer::new);
    }
    @SubscribeEvent
    public static void registerLayerDefinitions_Mininuke(
            EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MininukeModel.LAYER_LOCATION,
                MininukeModel::createBodyLayer);
    }
}
