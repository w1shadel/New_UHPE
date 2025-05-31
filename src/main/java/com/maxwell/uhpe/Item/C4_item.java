package com.maxwell.uhpe.Item;

import com.maxwell.uhpe.Entity.Item_ALL.C4.C4Entity;
import com.maxwell.uhpe.Register.ModEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class C4_item extends Item {
    public C4_item(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!level.isClientSide()) {
                C4Entity c4 = new C4Entity(ModEntity.C4.get(),level);
                c4.setPos(player.getX(), player.getEyeY(), player.getZ());
                Vec3 initialVelocity = player.getLookAngle().scale(1.0).add(0, 0.8, 0); // 前方 & 上方向へ
                c4.setDeltaMovement(initialVelocity);
                level.addFreshEntity(c4);
            player.getCooldowns().addCooldown(this, 10);
            itemstack.shrink(1);
        }
        return InteractionResultHolder.success(itemstack);
    }
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.level().isClientSide()) {
                C4Entity c4 = new C4Entity(ModEntity.C4.get(), attacker.level());
                c4.setPos(target.getX(), target.getY(), target.getZ()); // ✅ **エンティティに密着**
                c4.attachTo(target); // ✅ **対象エンティティに張り付く**
                attacker.level().addFreshEntity(c4);
            stack.shrink(1); // ✅ **アイテムを1つ減らす**
        }
        return super.hurtEnemy(stack, target, attacker);
    }
}
