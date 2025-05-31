package com.maxwell.uhpe.Register;

import com.maxwell.uhpe.Block.UHCPE;
import com.maxwell.uhpe.Block.UHPE;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlock {
    public static final DeferredRegister<Block> BLOCKS;
    public static final RegistryObject<Block> UHPE;
    public static final RegistryObject<Block> UHCPE;

    static {
        BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "uhpe");
        UHPE = BLOCKS.register("uhpe_block", () -> new UHPE(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(2.4F, 6.0F).sound(SoundType.NETHERITE_BLOCK)));
        UHCPE = BLOCKS.register("uhcpe_block", () -> new UHCPE(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).strength(2.4F, 6.0F).sound(SoundType.NETHERITE_BLOCK)));
    }
}
