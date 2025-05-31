package com.maxwell.uhpe.Entity.Item_ALL.TermitGrenade;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class termit extends Monster {
    public termit(EntityType<? extends Monster> entityType, Level level) {
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
    public boolean hurt(DamageSource source, float amount) {
        return false; // ✅ **ダメージ無効**
    }
    private int radius = 0; // ✅ **拡張する火の範囲**
    private BlockPos center; // ✅ **炎の中心地点**
    private int tickCounter = 0; // ✅ **時間管理**
    @Override
    public void tick() {
        super.tick();

        // ✅ **着地判定**
        if (this.onGround() && center == null) {
            center = this.blockPosition(); // ✅ **着地点を中心に設定**
        }

        if (center != null) {
            tickCounter++;

            // ✅ **0.5秒（10tick）ごとに炎の範囲を拡張**
            if (tickCounter % 10 == 0 && radius < 8) {
                radius++;
                spreadFire(radius);
            }
            if (tickCounter >= 200)
            {
                this.discard();
            }
        }
    }
    private void spreadFire(int currentRadius) {
        for (int x = -currentRadius; x <= currentRadius; x++) {
            for (int z = -currentRadius; z <= currentRadius; z++) {
                if (Math.sqrt(x * x + z * z) <= currentRadius) { // ✅ **円形の判定**
                    BlockPos firePos = center.offset(x, 0, z);
                    BlockState belowState = level().getBlockState(firePos.below());

                    // ✅ **草・雪なら強制的に火を配置！**
                    if (belowState.is(BlockTags.TALL_FLOWERS) || belowState.is(BlockTags.SNOW)) {
                        level().setBlock(firePos, Blocks.FIRE.defaultBlockState(), 3);
                    } else {
                        // ✅ **地形に沿って炎を配置**
                        while (level().getBlockState(firePos.below()).isAir() && firePos.getY() > center.getY() - 5) {
                            firePos = firePos.below();
                        }

                        if (level().getBlockState(firePos).isAir() && belowState.isSolid()) {
                            level().setBlock(firePos, Blocks.FIRE.defaultBlockState(), 3);
                        }
                    }

                    // ✅ **炎のパーティクルを発生**
                    level().addParticle(ParticleTypes.FLAME, firePos.getX() + 0.5, firePos.getY() + 0.5, firePos.getZ() + 0.5, 0, 0.1, 0);
                }
            }
        }
    }


}
