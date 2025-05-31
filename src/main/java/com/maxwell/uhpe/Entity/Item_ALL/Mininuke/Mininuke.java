package com.maxwell.uhpe.Entity.Item_ALL.Mininuke;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class Mininuke extends Monster {
    public Mininuke(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public boolean hurt(DamageSource source, float amount) {
        return false; // ✅ **ダメージ無効**
    }
    @Override
    public void tick() {
        super.tick();

        // ✅ **地面に接触したかチェック**
        if (this.onGround()) {
            explode();
            this.discard(); // ✅ **爆発後、エンティティを削除**
        }
    }
    private void explode() {
        if (!level().isClientSide()) { // ✅ **サーバー側のみ処理**
            level().explode(this, this.getX(), this.getY(), this.getZ(), 5, Level.ExplosionInteraction.NONE);
            AABB damageArea = new AABB(
                    this.getX() - 5, this.getY() - 0, this.getZ() - 5,
                    this.getX() + 5, this.getY() + 30, this.getZ() + 5
            );

            List<LivingEntity> entities = level().getEntitiesOfClass(LivingEntity.class, damageArea);

            for (LivingEntity entity : entities) {
                entity.hurt(damageSources().magic(), 1000f); // ✅ **超高ダメージ (50ダメージ)**
                entity.setSecondsOnFire(100); // ✅ **炎上効果 (10秒間)**
            }
        }
    }

}
