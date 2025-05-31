package com.maxwell.uhpe.Register;

import com.maxwell.uhpe.Entity.BlockEntity.*;
import com.maxwell.uhpe.Entity.Item_ALL.C4.C4Entity;
import com.maxwell.uhpe.Entity.Item_ALL.ClasterBomb.ClasterBomb;
import com.maxwell.uhpe.Entity.Item_ALL.GFlear.GFlear;
import com.maxwell.uhpe.Entity.Item_ALL.Gasgranade.gasgranade;
import com.maxwell.uhpe.Entity.Item_ALL.MineBomb.minebomb;
import com.maxwell.uhpe.Entity.Item_ALL.MiniClasterBomb.MiniClasterBomb;
import com.maxwell.uhpe.Entity.Item_ALL.Mininuke.Mininuke;
import com.maxwell.uhpe.Entity.Item_ALL.Missile.Misslie;
import com.maxwell.uhpe.Entity.Item_ALL.MotherLord.Motherlord;
import com.maxwell.uhpe.Entity.Item_ALL.TermitGrenade.termit;
import com.maxwell.uhpe.Entity.Item_ALL.dynamite.dynamite;
import com.maxwell.uhpe.Entity.Item_ALL.rollingthunder.FlareEntity;
import com.maxwell.uhpe.Entity.Item_ALL.rollingthunder.FlareEntityModel;
import com.maxwell.uhpe.UHPE;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
@Mod.EventBusSubscriber(modid = UHPE.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntity {
    public static final DeferredRegister<EntityType<?>> DEF_REG = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, UHPE.MODID);
    public static final RegistryObject<EntityType> NUCLEAR_BOMB = DEF_REG.register("nuclear_bomb", () -> (EntityType) EntityType.Builder.of(PrimedUHPE::new, MobCategory.MISC).sized(0.98F, 0.98F).setCustomClientFactory(PrimedUHPE::new).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true).updateInterval(10).clientTrackingRange(20).build("nuclear_bomb"));
    public static final RegistryObject<EntityType> COMPRESSED_NUCLEAR_BOMB = DEF_REG.register("compressed_nuclear_bomb", () -> (EntityType) EntityType.Builder.of(PrimedUHCPE::new, MobCategory.MISC).sized(0.98F, 0.98F).setCustomClientFactory(PrimedUHCPE::new).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true).updateInterval(10).clientTrackingRange(20).build("compressed_nuclear_bomb"));
    public static final RegistryObject<EntityType<C4Entity>> C4 = DEF_REG.register("c4model", () -> EntityType.Builder.of(C4Entity::new, MobCategory.MISC).sized(0.3f, 0.3f).clientTrackingRange(8).build(new ResourceLocation(UHPE.MODID, "c4model").toString()));
    public static final RegistryObject<EntityType<dynamite>> DYNAMITE = DEF_REG.register("dynamite", () -> EntityType.Builder.of(dynamite::new, MobCategory.MISC).sized(0.8f, 0.5f).clientTrackingRange(8).build(new ResourceLocation(UHPE.MODID, "dynamite").toString()));
    public static final RegistryObject<EntityType<termit>> TERMIT = DEF_REG.register("termit", () -> EntityType.Builder.of(termit::new, MobCategory.MISC).sized(0.3f, 0.3f).clientTrackingRange(8).build(new ResourceLocation(UHPE.MODID, "termit").toString()));
    public static final RegistryObject<EntityType<minebomb>> MINEBOMB = DEF_REG.register("minebomb", () -> EntityType.Builder.of(minebomb::new, MobCategory.MISC).sized(0.5f, 0.1f).clientTrackingRange(8).build(new ResourceLocation(UHPE.MODID, "minebomb").toString()));
    public static final RegistryObject<EntityType<gasgranade>> GAS = DEF_REG.register("gasgranade", () -> EntityType.Builder.of(gasgranade::new, MobCategory.MISC).sized(0.5f, 0.1f).clientTrackingRange(8).build(new ResourceLocation(UHPE.MODID, "gasgranade").toString()));
    public static final RegistryObject<EntityType<FlareEntity>> FLARE = DEF_REG.register("flare", () -> EntityType.Builder.of(FlareEntity::new, MobCategory.MISC).sized(0.5f, 0.1f).clientTrackingRange(8).build(new ResourceLocation(UHPE.MODID, "flear").toString()));
    public static final RegistryObject<EntityType<GFlear>> GFLARE = DEF_REG.register("gflare", () -> EntityType.Builder.of(GFlear::new, MobCategory.MISC).sized(0.5f, 0.1f).clientTrackingRange(8).build(new ResourceLocation(UHPE.MODID, "gflear").toString()));
    public static final RegistryObject<EntityType<Motherlord>> MFIRE = DEF_REG.register("mflear", () -> EntityType.Builder.of(Motherlord::new, MobCategory.MISC).sized(0.5f, 0.1f).clientTrackingRange(8).build(new ResourceLocation(UHPE.MODID, "mflear").toString()));
    public static final RegistryObject<EntityType<ClasterBomb>> NCLASTER = DEF_REG.register("claster", () -> EntityType.Builder.of(ClasterBomb::new, MobCategory.MISC).sized(0.1f, 0.1f).clientTrackingRange(8).build(new ResourceLocation(UHPE.MODID, "claster").toString()));
    public static final RegistryObject<EntityType<Mininuke>> MININUKE = DEF_REG.register("mininuke", () -> EntityType.Builder.of(Mininuke::new, MobCategory.MISC).sized(0.1f, 0.1f).clientTrackingRange(8).build(new ResourceLocation(UHPE.MODID, "mininuke").toString()));
    public static final RegistryObject<EntityType<MiniClasterBomb>> NCLASTER_MINI = DEF_REG.register("miniclaster", () -> EntityType.Builder.of(MiniClasterBomb::new, MobCategory.MISC).sized(0.1f, 0.1f).clientTrackingRange(8).build(new ResourceLocation(UHPE.MODID, "miniclaster").toString()));
    public static final RegistryObject<EntityType<Misslie>> MISSILE = DEF_REG.register("missile", () -> EntityType.Builder.of(Misslie::new, MobCategory.MISC).sized(0.5f, 0.1f).clientTrackingRange(8).build(new ResourceLocation(UHPE.MODID, "missile").toString()));
    public static void register(IEventBus eventBus) {
        DEF_REG.register(eventBus);
    }
}