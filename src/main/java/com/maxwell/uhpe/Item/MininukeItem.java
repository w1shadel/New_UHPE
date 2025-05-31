package com.maxwell.uhpe.Item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class MininukeItem extends Item {
    public MininukeItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(net.minecraft.network.chat.Component.translatable("item.uhpe.mininuke.desc").withStyle(ChatFormatting.AQUA));
        tooltip.add(net.minecraft.network.chat.Component.translatable("item.uhpe.mininuke.desc2").withStyle(ChatFormatting.RED));
    }
}
