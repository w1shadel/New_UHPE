package com.maxwell.uhpe.Entity.Item_ALL.MotherLord;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class Motherlord extends Monster {
    private int explosionTimer = 20; // ✅ **1秒後に爆発**

    public Motherlord(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        return false; // ✅ **ダメージ無効**
    }
    @Override
    public void tick() {
        super.tick();
        if (--explosionTimer <= 0) {
            explode();

            // ✅ **炎のアリーナ範囲を定義**
            AABB fireArena = new AABB(
                    this.getX() - 7, this.getY(), this.getZ() - 7,
                    this.getX() + 7, this.getY() + 2, this.getZ() + 7
            );

            List<LivingEntity> entitiesInFire = level().getEntitiesOfClass(LivingEntity.class, fireArena);

            for (LivingEntity entity : entitiesInFire) {
                entity.hurt(damageSources().onFire(), 6.0F); // ✅ **甚大なダメージ**
                entity.setSecondsOnFire(4); // ✅ **火を付ける (4秒間)**
            }
            this.discard();
        }
    }
    private void explode() {
        Explosion explosion = new Explosion(
                level(), this, null, null, // ✅ **ノックバック処理をオフ**
                this.getX(), this.getY(), this.getZ(),
                5.0F, false, Explosion.BlockInteraction.KEEP
        );
        explosion.finalizeExplosion(true);
        level().addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        BlockPos center = this.blockPosition();
        for (int angle = 0; angle < 360; angle += 10) {
            double rad = Math.toRadians(angle);
            int x = center.getX() + (int) (Math.cos(rad) * 7); // ✅ **半径7ブロック**
            int z = center.getZ() + (int) (Math.sin(rad) * 7);
            BlockPos firePos = new BlockPos(x, center.getY(), z);

            level().setBlockAndUpdate(firePos, Blocks.FIRE.defaultBlockState()); // ✅ **炎を配置**
        }
    }

}