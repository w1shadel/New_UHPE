package com.maxwell.uhpe.Entity.BlockEntity;

import com.maxwell.uhpe.Register.ModBlock;
import com.maxwell.uhpe.Register.ModEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

import java.util.ArrayList;
import java.util.List;

public class PrimedUHPE extends Entity {

    private static final EntityDataAccessor<Integer> TIME = SynchedEntityData.defineId(PrimedUHPE.class, EntityDataSerializers.INT);
    public static final int MAX_TIME = 300;
    private boolean spawnedExplosion = false;

    public PrimedUHPE(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    public PrimedUHPE(PlayMessages.SpawnEntity spawnEntity, Level level) {
        this(ModEntity.NUCLEAR_BOMB.get(), level);
        this.setBoundingBox(this.makeBoundingBox());
    }

    public PrimedUHPE(Level level, double x, double y, double z) {
        this(ModEntity.NUCLEAR_BOMB.get(), level);
        this.setPos(x, y, z);
        double d0 = level.random.nextDouble() * (double)((float)Math.PI * 2F);
        this.setDeltaMovement(-Math.sin(d0) * 0.02D, (double)0.2F, -Math.cos(d0) * 0.02D);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return (Packet<ClientGamePacketListener>) NetworkHooks.getEntitySpawningPacket(this);
    }

    public void tick() {
        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
        }
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.93D));
        if (this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.7D, -0.7, 0.7D));
        }
        int i = this.getTime() + 1;
        if (i > MAX_TIME) {
            this.discard();
            if (!this.level().isClientSide && !spawnedExplosion) {
                this.apocalypticNuclearExplode();
                spawnedExplosion = true;
            }
        } else {
            this.setTime(i);
            this.updateInWaterStateAndDoFluidPushing();
            if (this.level().isClientSide && MAX_TIME - i > 10 && random.nextFloat() < 0.3F && this.onGround()) {
                Vec3 center = this.getEyePosition();
            }
        }
    }
    private void apocalypticNuclearExplode() {
        Level level = this.level();
        int explosionRadius = 100; // ✅ **超広範囲爆発**
        BlockPos centerPos = this.blockPosition();

        // ✅ **中心爆発 (チャンクをまとめて破壊)**
        level.explode(this, centerPos.getX(), centerPos.getY(), centerPos.getZ(), explosionRadius, Level.ExplosionInteraction.TNT);

        // ✅ **横方向拡散爆破 (X/Z方向 100ブロック分に拡散)**
        for (int x = -100; x <= 100; x += 20) {
            for (int z = -100; z <= 100; z += 20) {
                BlockPos spreadPos = centerPos.offset(x, 0, z);
                level.explode(null, spreadPos.getX(), spreadPos.getY(), spreadPos.getZ(), 18.0F, Level.ExplosionInteraction.TNT);

                // ✅ **すべての爆発を岩盤まで貫通**
                for (int depth = 0; depth <= 60; depth += 6) {
                    BlockPos deepPos = spreadPos.below(depth);
                    level.explode(null, deepPos.getX(), deepPos.getY(), deepPos.getZ(), 25.0F, Level.ExplosionInteraction.TNT);
                }
            }
        }

        // ✅ **拡散爆破 (超密度)**
        for (int layer = 1; layer <= 15; layer++) { // ✅ **15層に分割**
            int layerRadius = explosionRadius - (layer * 10);
            for (int i = 0; i < 50; i++) { // ✅ **50方向へ爆破**
                double angle = Math.toRadians(i * (360 / 50));
                Vec3 explosionOffset = new Vec3(Math.cos(angle) * layerRadius, -layer * 4, Math.sin(angle) * layerRadius);

                BlockPos explosionPos = centerPos.offset((int) explosionOffset.x, (int) explosionOffset.y, (int) explosionOffset.z);
                if (level.getBlockState(explosionPos.below()).isSolidRender(level, explosionPos.below())) {
                    level.explode(null, explosionPos.getX(), explosionPos.getY(), explosionPos.getZ(), 20.0F, Level.ExplosionInteraction.TNT);

                    // ✅ **追加で岩盤まで貫通爆破**
                    for (int depth = 0; depth <= 60; depth += 6) {
                        BlockPos deepPos = explosionPos.below(depth);
                        level.explode(null, deepPos.getX(), deepPos.getY(), deepPos.getZ(), 20.0F, Level.ExplosionInteraction.TNT);
                    }
                }
            }
        }
    }
    @Override
    public void resetFallDistance() {
        if (this.fallDistance > 20.0F) {
            this.discard();
            if (!this.level().isClientSide) {
                this.apocalypticNuclearExplode();
            }
        }
    }
    @Override
    protected void defineSynchedData() {
        this.entityData.define(TIME, 0);
    }

    public int getTime() {
        return this.entityData.get(TIME);
    }

    public void setTime(int time) {
        this.entityData.set(TIME, time);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {

    }

    public boolean isPickable() {
        return !this.isRemoved();
    }

    public boolean shouldBeSaved() {
        return !this.isRemoved();
    }

    public boolean isAttackable() {
        return false;
    }

    @Override
    public ItemStack getPickResult() {
        return new ItemStack(ModBlock.UHPE.get());
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Tags.Items.SHEARS)) {
            player.swing(hand);
            this.remove(RemovalReason.KILLED);
            this.spawnAtLocation(new ItemStack(ModBlock.UHPE.get()));
            if (!player.getAbilities().instabuild) {
                itemStack.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(hand));
            }
            return InteractionResult.SUCCESS;
        } else if (player.isSecondaryUseActive()) {
            return InteractionResult.PASS;
        } else if (!this.level().isClientSide) {
            return player.startRiding(this) ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else {
            return InteractionResult.SUCCESS;
        }
    }

    public void positionRider(Entity passenger, MoveFunction moveFunction) {
        if (this.isPassengerOfSameVehicle(passenger) && passenger instanceof LivingEntity living && !this.touchingUnloadedChunk()) {
            float progress = this.getTime() / (float) MAX_TIME;
            float expandScale = 1F + (float) Math.sin(progress * progress * Math.PI) * 0.5F;
            float f1 = -(this.getXRot() / 40F);
            float j = expandScale - progress * 0.3F;
            double d0 = this.getY() + j + passenger.getMyRidingOffset() - 0.2F;
            moveFunction.accept(passenger, this.getX(), d0, this.getZ());
            passenger.fallDistance = 0.0F;
        } else {
            super.positionRider(passenger, moveFunction);
        }
    }

    public boolean causeFallDamage(float f, float f1, DamageSource damageSource) {
        return false;
    }
}