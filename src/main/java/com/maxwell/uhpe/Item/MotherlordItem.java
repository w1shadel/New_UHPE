package com.maxwell.uhpe.Item;

import com.maxwell.uhpe.Entity.Item_ALL.MotherLord.Motherlord;
import com.maxwell.uhpe.Register.ModEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class MotherlordItem extends Item {
    public MotherlordItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!level.isClientSide()) {
            Vec3 direction = player.getLookAngle(); // ✅ **プレイヤーの視線方向**
            Vec3 targetPos = player.position().add(direction.scale(3)).add(0, 5, 0); // ✅ **3マス前 & 上空**

            Motherlord fireBomb = new Motherlord(ModEntity.MFIRE.get(), level);
            fireBomb.setPos(targetPos.x, targetPos.y, targetPos.z);
            level.addFreshEntity(fireBomb);
        }
        return InteractionResultHolder.success(itemstack);
    }

}
