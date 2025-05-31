package com.maxwell.uhpe.Entity.Item_ALL.Missile;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class Misslie extends Monster {

    public Misslie(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
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
        level().explode(this, this.getX(), this.getY(), this.getZ(), 5.0F, Level.ExplosionInteraction.NONE);
    }
}
