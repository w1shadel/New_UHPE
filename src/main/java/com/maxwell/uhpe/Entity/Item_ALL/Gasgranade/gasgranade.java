package com.maxwell.uhpe.Entity.Item_ALL.Gasgranade;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class gasgranade extends Monster {
    private int timer = 0; // ✅ **経過時間管理**
    private boolean gasActive = false; // ✅ **ガスが展開されたかを管理**

    public gasgranade(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        return false; // ✅ **ダメージ無効**
    }
    @Override
    public void tick() {
        super.tick();
        timer++;
        if (timer >= 40 && !gasActive) {
            gasActive = true;
            this.level().playSound(null, this.blockPosition(), SoundEvents.ANVIL_LAND, SoundSource.HOSTILE, 2.0F, 1.0F);
        }
        if (gasActive) {
            spreadGasEffect();
        }
        if (timer >= 240) {
            this.discard();
        }
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 6)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.KNOCKBACK_RESISTANCE, 999)
                .add(Attributes.ATTACK_DAMAGE, 5.0);
    }
    private void spreadGasEffect() {
        AABB gasArea = new AABB(
                this.getX() - 10, this.getY() - 10, this.getZ() - 10,
                this.getX() + 10, this.getY() + 10, this.getZ() + 10
        );

        List<LivingEntity> affectedEntities = this.level().getEntitiesOfClass(LivingEntity.class, gasArea);

        for (LivingEntity entity : affectedEntities) {
            if (entity != this) { // ✅ **自身は影響を受けない**
                entity.hurt(damageSources().magic(), 4.0F); // ✅ **継続ダメージ**
                entity.addEffect(new MobEffectInstance(MobEffects.POISON, 40, 4)); // ✅ **毒**
                entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 40, 1)); // ✅ **吐き気**
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 0)); // ✅ **鈍足**
                entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40, 3)); // ✅ **弱体化**
            }
        }

        // ✅ **緑色のガスを発生**
        for (int i = 0; i < timer; i++) {
            double offsetX = (random.nextDouble() - 0.5) * 5.0;
            double offsetZ = (random.nextDouble() - 0.5) * 5.0;
            this.level().addParticle(ParticleTypes.SPORE_BLOSSOM_AIR, this.getX() + offsetX, this.getY() + 0.5, this.getZ() + offsetZ, 0, 0.05, 0);
        }
    }

}
