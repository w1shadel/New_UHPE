package com.maxwell.uhpe.Item;

import com.maxwell.uhpe.Entity.Item_ALL.C4.C4Entity;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.List;

public class C4controller extends Item {

    public C4controller(Properties pProperties) {
        super(pProperties);
    }

    private boolean terrainDestructionEnabled = true;

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        player.startUsingItem(hand);

        // ✅ **シフトキーで地形破壊モードを切り替え (クライアント側)**
        if (level.isClientSide()) {
            if (player.isShiftKeyDown()) {
                terrainDestructionEnabled = !terrainDestructionEnabled;

                if (terrainDestructionEnabled) {
                    itemstack.setHoverName(Component.translatable("item.uhpe.c4_controller_true"));
                } else {
                    itemstack.setHoverName(Component.translatable("item.uhpe.c4_controller_false"));
                }
            }
            return InteractionResultHolder.success(itemstack);
        }
        player.getCooldowns().addCooldown(this, 10);
        AABB explosionArea = new AABB(
                player.getX() - 30, player.getY() - 30, player.getZ() - 30,
                player.getX() + 30, player.getY() + 30, player.getZ() + 30
        );

        List<C4Entity> c4Entities = level.getEntitiesOfClass(C4Entity.class, explosionArea);

        for (C4Entity c4 : c4Entities) {
            level.explode(c4, c4.getX(), c4.getY(), c4.getZ(), 4.0F,
                    terrainDestructionEnabled ? Level.ExplosionInteraction.TNT : Level.ExplosionInteraction.NONE); // ✅ **地形破壊の切り替え**
            c4.discard();
        }

        return InteractionResultHolder.success(itemstack);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<net.minecraft.network.chat.Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(net.minecraft.network.chat.Component.translatable("item.uhpe.c4_controller.desc").withStyle(ChatFormatting.WHITE));
        tooltip.add(net.minecraft.network.chat.Component.translatable("item.uhpe.c4_controller.desc2").withStyle(ChatFormatting.RED));
        tooltip.add(net.minecraft.network.chat.Component.translatable("item.uhpe.c4_controller.desc3").withStyle(ChatFormatting.WHITE));
    }

}
