package com.maxwell.uhpe.Entity.Item_ALL.GFlear;

import com.maxwell.uhpe.Entity.Item_ALL.MineBomb.minebomb;
import com.maxwell.uhpe.Entity.Item_ALL.Missile.Misslie;
import com.maxwell.uhpe.Register.ModEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class GFlear extends Monster {
    private int BombCount = 0; // ✅ **爆発カウント**
    private boolean bombingStarted = false;
    public GFlear(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        return false; // ✅ **ダメージ無効**
    }
    @Override
    public void tick() {
        super.tick();
        if (!bombingStarted && this.tickCount >= 40) {
            bombingStarted = true;
        }
        if (bombingStarted && this.tickCount % 20 == 0 && BombCount < 20) { // ✅ **10tickごとに実行**
            BombCount++;
            dropRandomTNT();
        }
        if (BombCount == 20)
        {
            this.discard();
        }
    }
    private void dropRandomTNT() {
        BlockPos center = this.blockPosition(); // ✅ **信号エンティティの位置**

        for (int i = 0; i < 5; i++) { // ✅ **一度に5発のTNTを落とす**
            double angle = Math.toRadians(random.nextInt(360)); // ✅ **ランダムな角度**
            double distance = random.nextDouble() * 20; // ✅ **半径20ブロックの範囲**

            int x = center.getX() + (int) Math.round(Math.cos(angle) * distance);
            int z = center.getZ() + (int) Math.round(Math.sin(angle) * distance);
            int y = center.getY() + 15; // ✅ **上空15ブロックから落下**

            BlockPos tntPos = new BlockPos(x, y, z);
            spawnTNT(tntPos);
        }

    }
    private void spawnTNT(BlockPos pos) {
        Misslie tnt = new Misslie(ModEntity.MISSILE.get(),level());
        tnt.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        this.level().addFreshEntity(tnt);
    }
}
