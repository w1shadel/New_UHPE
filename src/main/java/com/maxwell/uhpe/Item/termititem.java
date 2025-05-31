package com.maxwell.uhpe.Item;

import com.maxwell.uhpe.Entity.Item_ALL.MineBomb.minebomb;
import com.maxwell.uhpe.Entity.Item_ALL.TermitGrenade.termit;
import com.maxwell.uhpe.Register.ModEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class termititem extends Item {
    public termititem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!level.isClientSide()) {
            termit c4 = new termit(ModEntity.TERMIT.get(),level);
            c4.setPos(player.getX(), player.getEyeY(), player.getZ());
            Vec3 initialVelocity = player.getLookAngle().scale(0.7).add(0, 0.2, 0); // 前方 & 上方向へ
            c4.setDeltaMovement(initialVelocity);
            level.addFreshEntity(c4);
            player.getCooldowns().addCooldown(this, 10);
            itemstack.shrink(1);
        }
        return InteractionResultHolder.success(itemstack);
    }
}
