package com.maxwell.uhpe.Entity.Item_ALL.rollingthunder;

import com.maxwell.uhpe.Entity.Item_ALL.Missile.Misslie;
import com.maxwell.uhpe.Register.ModEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FlareEntity extends Monster {
    private int explosionStep = 0;
    private boolean bombingStarted = false;

    public FlareEntity(EntityType<? extends FlareEntity> entityType, Level level) {
        super(entityType, level);
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        return false; // ✅ **ダメージ無効**
    }
    @Override
    public void tick() {
        super.tick();

        // ✅ **投擲後2秒（40tick）経過後、爆撃開始**
        if (!bombingStarted && this.tickCount >= 40) {
            bombingStarted = true;
        }

        // ✅ **爆撃中なら、20tickごとにTNTを降らせる**
        if (bombingStarted && this.tickCount % 20 == 0 && explosionStep < 20) {
            triggerBombing();
            explosionStep++;
        }
        if (explosionStep == 20) {
         this.discard();
        }
    }

    private void triggerBombing() {
        Player closestPlayer = level().getNearestPlayer(this, 20);
        if (closestPlayer == null) return;
        Vec3 direction = closestPlayer.getViewVector(1.0F);
        BlockPos startPos = this.blockPosition().offset((int) (direction.x * 5), 0, (int) (direction.z * 5)); // ✅ **初期位置を前方 5 ブロック移動！**
        int i = explosionStep * 4;
        BlockPos dropPos = new BlockPos(
                (int) (startPos.getX() + Math.floor(direction.x * i)),
                startPos.getY() + 15,
                (int) (startPos.getZ() + Math.floor(direction.z * i))
        );

        for (int j = -10; j <= 10; j += 5) { // ✅ **横幅20ブロックの範囲で降下**
            BlockPos tntPos = dropPos.offset(j, 0, j);
            spawnTNT(tntPos);
        }
    }


    private void spawnTNT(BlockPos pos) {
        Misslie tnt = new Misslie(ModEntity.MISSILE.get(),level());
        // ✅ **座標を適用し、適切な位置にスポーン**
        tnt.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        this.level().addFreshEntity(tnt);
    }
}
