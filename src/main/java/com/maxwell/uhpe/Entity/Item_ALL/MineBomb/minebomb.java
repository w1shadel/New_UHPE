package com.maxwell.uhpe.Entity.Item_ALL.MineBomb;

import com.maxwell.uhpe.Register.ModItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class minebomb extends Monster {
    private int detonationCountdown = 0; // ✅ **爆発カウント**

    public minebomb(EntityType<? extends Monster> entityType, Level level) {
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
    public void push(double x, double y, double z) {
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        Entity attacker = source.getEntity();

        if (attacker != null && attacker.getClass().equals(this.getClass())) {
            return false; // ✅ **同じエンティティならダメージ無効化**
        }

        return super.hurt(source, amount); // ✅ **通常のダメージ処理**
    }
    @Override
    protected void dropCustomDeathLoot(DamageSource source, int lootingLevel, boolean recentlyHit) {
        super.dropCustomDeathLoot(source, lootingLevel, recentlyHit);
        this.spawnAtLocation(ModItem.MINEBOMB.get());
    }
    @Override
    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source) {
        return false; // ✅ **ダメージを完全キャンセル**
    }
    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide()) {
            // ✅ **2×2×2 の範囲を定義**
            AABB detectionArea = new AABB(
                    this.getX() - 1, this.getY() - 1, this.getZ() - 1,
                    this.getX() + 1, this.getY() + 1, this.getZ() + 1
            );

            // ✅ **範囲内の敵モブを取得**
            List<Monster> enemies = this.level().getEntitiesOfClass(Monster.class, detectionArea)
                    .stream()
                    .filter(mob -> !(mob instanceof minebomb))
                    .toList();

            // ✅ **範囲内に敵がいれば爆発カウント開始**
            if (!enemies.isEmpty()) {
                detonationCountdown++;

                // ✅ **爆発までのカウントダウン音**
                if (detonationCountdown % 20 == 0) {
                    this.level().playSound(null, this.blockPosition(), SoundEvents.COMPARATOR_CLICK, SoundSource.HOSTILE, 1.0F, 1.0F);
                }

                // ✅ **60tick (3秒) で爆発**
                if (detonationCountdown >= 20) {
                    explode();
                }
            } else {
                detonationCountdown = 0; // ✅ **敵がいなければカウントリセット**
            }
        }
    }


    private void explode() {
        // ✅ **縦方向へ強い爆発**
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), 1.0F, Level.ExplosionInteraction.NONE);
        this.discard();
    }
}
