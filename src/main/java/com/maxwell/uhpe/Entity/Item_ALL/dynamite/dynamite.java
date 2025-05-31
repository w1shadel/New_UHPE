package com.maxwell.uhpe.Entity.Item_ALL.dynamite;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class dynamite extends Monster {
    private int explosionCountdown = 100; // ✅ **5秒 (100tick) のカウントダウン**

    public dynamite(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 6)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.KNOCKBACK_RESISTANCE, 999)
                .add(Attributes.ATTACK_DAMAGE, 5.0);
    }

    @Override
    public void tick() {
        super.tick();
        this.fallDistance = 0; // ✅ **落下ダメージを無効化**
        if (explosionCountdown > 0) {
            explosionCountdown--;
            if (explosionCountdown % 20 == 0) {
                this.level().playSound(null, this.blockPosition(), SoundEvents.COMPARATOR_CLICK, SoundSource.HOSTILE, 1.0F, 1.0F);
            }
            for (int i = 0; i < 5; i++) {
                double offsetX = (random.nextDouble() - 0.5) * 0.2;
                double offsetZ = (random.nextDouble() - 0.5) * 0.2;
                double upwardVelocity = random.nextDouble() * 0.03 + 0.05; // ✅ **上昇する炎**

                this.level().addParticle(ParticleTypes.FLAME, this.getX() + offsetX, this.getY() + (i * 0.1), this.getZ() + offsetZ, 0, upwardVelocity, 0);
                this.level().addParticle(ParticleTypes.SMOKE, this.getX() + offsetX, this.getY() + (i * 0.1), this.getZ() + offsetZ, 0, upwardVelocity - 0.02, 0);
            }
        }
        if (explosionCountdown == 0) {
            explode();
        }
    }
    private void explode() {
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), 6.3F, Level.ExplosionInteraction.TNT); // ✅ **爆発**
        this.discard(); // ✅ **エンティティを削除**
    }
    @Override
    public void die(DamageSource source) {
        super.die(source);
        explode();
    }
    @Override
    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source) {
        return false; // ✅ **ダメージを完全キャンセル**
    }
}
