package com.maxwell.uhpe.Item;

import com.maxwell.uhpe.Entity.Item_ALL.C4.C4Entity;
import com.maxwell.uhpe.Entity.Item_ALL.ClasterBomb.ClasterBomb;
import com.maxwell.uhpe.Entity.Item_ALL.MineBomb.minebomb;
import com.maxwell.uhpe.Entity.Item_ALL.Mininuke.Mininuke;
import com.maxwell.uhpe.Register.ModEntity;
import com.maxwell.uhpe.Register.ModItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class Clasterlauncher extends Item {
    public Clasterlauncher(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        ItemStack offhandItem = player.getOffhandItem(); // ✅ **オフハンドのアイテムを取得**

        if (!level.isClientSide()) {
            boolean hasSpecialItem = offhandItem.is(ModItem.MININUKE.get()); // ✅ **オフハンドの判定**

            // ✅ **オフハンドに特定アイテムがある場合は `SPECIAL_ENTITY` を発射**
            Entity projectile = hasSpecialItem
                    ? new Mininuke(ModEntity.MININUKE.get(), level) // ✅ **特別な弾丸**
                    : new ClasterBomb(ModEntity.NCLASTER.get(), level); // ✅ **通常のクラスター**

            projectile.setPos(player.getX(), player.getEyeY(), player.getZ());
            Vec3 initialVelocity = player.getLookAngle().scale(3).add(0, -0.2, 0); // ✅ **前方へ飛行**
            projectile.setDeltaMovement(initialVelocity);
            level.addFreshEntity(projectile);

            player.getCooldowns().addCooldown(this, 100);
        }

        return InteractionResultHolder.success(itemstack);
    }
}

