package com.maxwell.uhpe.Entity.Item_ALL.ClasterBomb;

import com.maxwell.uhpe.Entity.Item_ALL.MiniClasterBomb.MiniClasterBomb;
import com.maxwell.uhpe.Register.ModEntity;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class ClasterBomb extends Monster {
    private Monster target;
    private int explosionCounter = 0;

    public ClasterBomb(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        return false; // ✅ **ダメージ無効**
    }
    @Override
    public void tick() {
        super.tick();
        if (target == null && this.tickCount == 20) {
            this.discard();
        }
        if (target == null) {
            target = level().getNearestEntity(Monster.class, TargetingConditions.forNonCombat(), this, this.getX(), this.getY(), this.getZ(), this.getBoundingBox().inflate(1));
        }
        if (target != null) {
            this.setPos(target.getX(), target.getY(), target.getZ()); // ✅ **常に敵にくっつく**

            if (explosionCounter < 5 && this.tickCount % 20 == 0) { // ✅ **1秒ごとに爆発 (最大5回)**
                explode();
                spawnSmallExplodingEntities();
                explosionCounter++;
            }
            if (explosionCounter >= 5) {
                this.discard();
            }
        }
    }
    private void spawnSmallExplodingEntities() {
        for (int i = 0; i < 6; i++) { // ✅ **3体の小爆発エンティティを生成**
            MiniClasterBomb smallBomb = new MiniClasterBomb(ModEntity.NCLASTER_MINI.get(), level());
            smallBomb.setPos(this.getX(), this.getY(), this.getZ());
            level().addFreshEntity(smallBomb);
        }
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
            entity.hurt(damageSources().explosion(this,this), 10f); // ✅ **ダメージを適用 (10ダメージ)**
        }
    }


}
