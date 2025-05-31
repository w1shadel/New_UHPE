package com.maxwell.uhpe.Register;

import com.maxwell.uhpe.Entity.Item_ALL.C4.C4Entity;
import com.maxwell.uhpe.Entity.Item_ALL.Gasgranade.gasgranade;
import com.maxwell.uhpe.Entity.Item_ALL.MineBomb.minebomb;
import com.maxwell.uhpe.Entity.Item_ALL.TermitGrenade.termit;
import com.maxwell.uhpe.Entity.Item_ALL.dynamite.dynamite;
import com.maxwell.uhpe.UHPE;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UHPE.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MaxwellEventBusEvent {
    @SubscribeEvent
    public static void registerAttributes_c4(
            EntityAttributeCreationEvent event) {
        event.put(ModEntity.C4.get(),
                C4Entity.createAttributes().build());
    }
    @SubscribeEvent
    public static void registerAttributes_dyna(
            EntityAttributeCreationEvent event) {
        event.put(ModEntity.DYNAMITE.get(),
                dynamite.createAttributes().build());
    }
    @SubscribeEvent
    public static void registerAttributes_minr(
            EntityAttributeCreationEvent event) {
        event.put(ModEntity.MINEBOMB.get(),
                minebomb.createAttributes().build());
    }
    @SubscribeEvent
    public static void registerAttributes_termit(
            EntityAttributeCreationEvent event) {
        event.put(ModEntity.TERMIT.get(),
                termit.createAttributes().build());
    }
    @SubscribeEvent
    public static void registerAttributes_gas(
            EntityAttributeCreationEvent event) {
        event.put(ModEntity.GAS.get(),
                gasgranade.createAttributes().build());
    }
    @SubscribeEvent
    public static void registerAttributes_rolling(
            EntityAttributeCreationEvent event) {
        event.put(ModEntity.FLARE.get(),
                gasgranade.createAttributes().build());
    }
    @SubscribeEvent
    public static void registerAttributes_Grolling(
            EntityAttributeCreationEvent event) {
        event.put(ModEntity.GFLARE.get(),
                gasgranade.createAttributes().build());
    }
    @SubscribeEvent
    public static void registerAttributes_missile(
            EntityAttributeCreationEvent event) {
        event.put(ModEntity.MISSILE.get(),
                gasgranade.createAttributes().build());
    }
    @SubscribeEvent
    public static void registerAttributes_miflear(
            EntityAttributeCreationEvent event) {
        event.put(ModEntity.MFIRE.get(),
                gasgranade.createAttributes().build());
    }
    @SubscribeEvent
    public static void registerAttributes_nclaster(
            EntityAttributeCreationEvent event) {
        event.put(ModEntity.NCLASTER.get(),
                gasgranade.createAttributes().build());
    }
    @SubscribeEvent
    public static void registerAttributes_Mnclaster(
            EntityAttributeCreationEvent event) {
        event.put(ModEntity.NCLASTER_MINI.get(),
                gasgranade.createAttributes().build());
    }
    @SubscribeEvent
    public static void registerAttributes_Mininuke(
            EntityAttributeCreationEvent event) {
        event.put(ModEntity.MININUKE.get(),
                gasgranade.createAttributes().build());
    }
}
