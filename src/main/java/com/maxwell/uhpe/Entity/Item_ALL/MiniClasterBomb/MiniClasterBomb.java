package com.maxwell.uhpe.Entity.Item_ALL.MiniClasterBomb;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class MiniClasterBomb extends Monster {
    private int explosionTimer = 30; // ✅ **2秒後に爆発**

    public MiniClasterBomb(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }
    public boolean hurt(DamageSource source, float amount) {
        return false; // ✅ **ダメージ無効**
    }
    @Override
    public void tick() {
        super.tick();

        if (this.explosionTimer-- <= 0) {
            explode();
            this.discard(); // ✅ **爆発後に削除**
        } else {
            randomFly(); // ✅ **ランダム移動**
        }
    }

    private void randomFly() {
        double xOffset = (random.nextDouble() - 0.5) * 4.0; // ✅ **ランダムなX方向移動**
        double yOffset = (random.nextDouble() - 0.5); // ✅ **少し上下に変化**
        double zOffset = (random.nextDouble() - 0.5) * 4.0; // ✅ **ランダムなZ方向移動**
        this.setDeltaMovement(xOffset, yOffset, zOffset);
    }

    private void explode() {
        // ✅ **爆発の処理 (吹っ飛びなし)**
        Explosion explosion = new Explosion(
                level(), this, null, null,
                this.getX(), this.getY(), this.getZ(),
                5.0F, false, Explosion.BlockInteraction.KEEP
        );

        explosion.finalizeExplosion(true); // ✅ **爆風の影響を完全無効化**
        level().addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getX(), this.getY(), this.getZ(), 0, 0, 0);

        // ✅ **ダメージを与える範囲**
        AABB damageArea = new AABB(
                this.getX() - 5, this.getY() - 3, this.getZ() - 5,
                this.getX() + 5, this.getY() + 3, this.getZ() + 5
        );

        List<LivingEntity> entities = level().getEntitiesOfClass(LivingEntity.class, damageArea);

        for (LivingEntity entity : entities) {
            entity.hurt(damageSources().explosion(this,this), 6f); // ✅ **ダメージを適用 (10ダメージ)**
        }
    }

}