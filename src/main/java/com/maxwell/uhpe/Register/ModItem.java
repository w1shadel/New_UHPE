package com.maxwell.uhpe.Register;

import com.maxwell.uhpe.Item.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItem {
    public static final DeferredRegister<Item> ITEMS;
    public static final DeferredRegister<CreativeModeTab> TABS;
    public static final RegistryObject<Item> UHPE;
    public static final RegistryObject<Item> UHCPE;
    public static final RegistryObject<Item> URANIUM;
    public static final RegistryObject<Item> HIGH_COMPRESSED_URANIUM;
    public static final RegistryObject<Item> YELLOWCAKE;
    public static final RegistryObject<Item> RAW_HIGH_COMPRESSED_IRON;
    public static final RegistryObject<Item> HIGH_COMPRESSED_IRON;
    public static final RegistryObject<Item> URANIUM_BAR;
    public static final RegistryObject<Item> PURTONIIUM;
    public static final RegistryObject<Item> C4_CONTROLLER;
    public static final RegistryObject<Item> C4_ITEM;
    public static final RegistryObject<Item> DYNAMITE;
    public static final RegistryObject<Item> MINEBOMB;
    public static final RegistryObject<Item> TERMIT;
    public static final RegistryObject<Item> CLASTERLAUNCHER;
    public static final RegistryObject<Item> FLREA;
    public static final RegistryObject<Item> MFLEAR;
    public static final RegistryObject<Item> Gas;
    public static final RegistryObject<Item> ENDERURANIUMTANK;
    public static final RegistryObject<Item> URANIUM_ORE;
    public static final RegistryObject<Item> CLASTERBOMB;
    public static final RegistryObject<Item> GFLEAR;
    public static final RegistryObject<Item> INFERNOFIRE;
    public static final RegistryObject<Item> MININUKE;
    public static final RegistryObject<CreativeModeTab> UHPE_TAB;
    static
    {
        TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "uhpe");
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "uhpe");
        UHPE = registerWithTab("uhpe_item", () -> new BlockItem((Block) ModBlock.UHPE.get(), new Item.Properties().rarity(Rarity.RARE).stacksTo(16)));
        UHCPE = registerWithTab("uhcpe_item", () -> new BlockItem((Block) ModBlock.UHCPE.get(), new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)));
        HIGH_COMPRESSED_IRON = registerWithTab("hc_iron", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
        C4_CONTROLLER = registerWithTab("c4_controller", () -> new C4controller(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1)));
        URANIUM = registerWithTab("uranium", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
        RAW_HIGH_COMPRESSED_IRON = registerWithTab("raw_hc_iron", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
        URANIUM_ORE = registerWithTab("uranium_ore", () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
        INFERNOFIRE = registerWithTab("eternalfire", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
        MININUKE = registerWithTab("mininuke", () -> new MininukeItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1).fireResistant()));
        ENDERURANIUMTANK = registerWithTab("enderuraniumtank", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
        DYNAMITE = registerWithTab("dynamite", () -> new dynamiteitem(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(32)));
        C4_ITEM = registerWithTab("c4_item", () -> new C4_item(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(32)));
        Gas = registerWithTab("gasgranade_item", () -> new Gasthrower(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(8)));
        GFLEAR = registerWithTab("gflear", () -> new Gflareitem(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1)));
        TERMIT = registerWithTab("termititem", () -> new termititem(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(16)));
        FLREA = registerWithTab("flear", () -> new flareitem(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1)));
        MFLEAR = registerWithTab("motherlord", () -> new MotherlordItem(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1)));
        MINEBOMB = registerWithTab("minebombitem", () -> new minebombItem(new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(32)));
        YELLOWCAKE = registerWithTab("yellow_cake", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
        CLASTERBOMB = registerWithTab("clasterbomb", () -> new Item(new Item.Properties().rarity(Rarity.COMMON)));
        PURTONIIUM = registerWithTab("purtonium", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
        CLASTERLAUNCHER = registerWithTab("clasterlauncher", () -> new Clasterlauncher(new Item.Properties().rarity(Rarity.RARE)));
        URANIUM_BAR = registerWithTab("uranium_bar", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
        HIGH_COMPRESSED_URANIUM = registerWithTab("hc_uranium", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
        UHPE_TAB = TABS.register("uhpe_tab", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.uhpe_tab.uhpe_item")).icon(() -> new ItemStack((ItemLike)UHPE.get())).displayItems((enabledFeatures, entries) -> {
            entries.accept((ItemLike)UHPE.get());
            entries.accept((ItemLike)C4_ITEM.get());
            entries.accept((ItemLike)MFLEAR.get());
            entries.accept((ItemLike)CLASTERLAUNCHER.get());
            entries.accept((ItemLike)CLASTERBOMB.get());
            entries.accept((ItemLike)DYNAMITE.get());
            entries.accept((ItemLike)FLREA.get());
            entries.accept((ItemLike)GFLEAR.get());
            entries.accept((ItemLike)TERMIT.get());
            entries.accept((ItemLike)Gas.get());
            entries.accept((ItemLike)MINEBOMB.get());
            entries.accept((ItemLike)C4_CONTROLLER.get());
            entries.accept((ItemLike)HIGH_COMPRESSED_IRON.get());
            entries.accept((ItemLike)RAW_HIGH_COMPRESSED_IRON.get());
            entries.accept((ItemLike)HIGH_COMPRESSED_URANIUM.get());
            entries.accept((ItemLike)ENDERURANIUMTANK.get());
            entries.accept((ItemLike)INFERNOFIRE.get());
            entries.accept((ItemLike)URANIUM.get());
            entries.accept((ItemLike)YELLOWCAKE.get());
            entries.accept((ItemLike)PURTONIIUM.get());
            entries.accept((ItemLike)URANIUM_ORE.get());
            entries.accept((ItemLike)URANIUM_BAR.get());
        }).build());
    }
    public static RegistryObject<Item> registerWithTab(String name, Supplier<Item> supplier) {
        RegistryObject<Item> block = ITEMS.register(name, supplier);
        return block;
    }
}
