package com.maxwell.uhpe.Entity.Item_ALL.C4;

import com.maxwell.uhpe.Register.ModItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class C4Entity extends Monster {
    private boolean attached = false;
    private LivingEntity attachedEntity = null; // ✅ **エンティティを格納する変数を追加！**
    private int ticksSinceSpawn = 0; // ✅ **スポーンしてからの時間を管理！**


    public C4Entity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getDirectEntity() instanceof Player player) {
            ItemStack heldItem = player.getMainHandItem();
            if (heldItem.getItem() == ModItem.C4_CONTROLLER.get()) {
                return super.hurt(source, amount); // ✅ **C4コントローラならダメージを受ける**
            }
        }
        return false; // ✅ **それ以外の攻撃は無効**
    }
    @Override
    protected void dropCustomDeathLoot(DamageSource source, int lootingLevel, boolean recentlyHit) {
        super.dropCustomDeathLoot(source, lootingLevel, recentlyHit);
        this.spawnAtLocation(ModItem.C4_ITEM.get()); // ✅ **C4アイテムをドロップ**
    }
    @Override
    public void push(double x, double y, double z) {
    }
    @Override
    public void tick() {
        super.tick();
        ticksSinceSpawn++; // ✅ **毎tickでカウントを増加！**
        if (!attached) {
            BlockPos pos = this.blockPosition();
            BlockState state = level().getBlockState(pos);
            if (state.isSolidRender(level(), pos)) {
                this.setDeltaMovement(Vec3.ZERO);
            }
        }
        if (attachedEntity != null) {
            Vec3 offset = new Vec3(0, 2.5, 0);
            attached = true;
            this.setPos(attachedEntity.position().add(offset));
        }
    }
    public void attachTo(LivingEntity entity) {
        this.attached = true;
        this.attachedEntity = entity;
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 1)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.KNOCKBACK_RESISTANCE,999)
                .add(Attributes.ATTACK_DAMAGE, 5.0);
    }
}

