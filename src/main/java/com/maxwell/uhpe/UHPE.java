package com.maxwell.uhpe;

import com.maxwell.uhpe.Register.ModBlock;
import com.maxwell.uhpe.Register.ModEntity;
import com.maxwell.uhpe.Register.ModItem;
import com.tacz.guns.api.resource.ResourceManager;
import me.xjqsh.Fallout;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(UHPE.MODID)
public class UHPE
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "uhpe";
    public static final String DEFAULT_PACK_NAME = "nukalauncher";
    public UHPE(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        ModBlock.BLOCKS.register(modEventBus);
        ModItem.ITEMS.register(modEventBus);
        ModItem.TABS.register(modEventBus);
        ModEntity.DEF_REG.register(modEventBus);
        registerDefaultExtraGunPack();
    }
    private static void registerDefaultExtraGunPack() {
        String jarDefaultPackPath = String.format("/assets/%s/custom/%s", "uhpe", "nukalauncher");
        ResourceManager.registerExportResource(UHPE.class, jarDefaultPackPath);
    }
}
