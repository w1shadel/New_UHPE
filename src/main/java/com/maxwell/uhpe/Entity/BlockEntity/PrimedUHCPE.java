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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

public class PrimedUHCPE extends Entity {

    private static final EntityDataAccessor<Integer> TIME = SynchedEntityData.defineId(PrimedUHCPE.class, EntityDataSerializers.INT);
    public static final int MAX_TIME = 300;
    private boolean spawnedExplosion = false;

    public PrimedUHCPE(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    public PrimedUHCPE(PlayMessages.SpawnEntity spawnEntity, Level level) {
        this(ModEntity.COMPRESSED_NUCLEAR_BOMB.get(), level);
        this.setBoundingBox(this.makeBoundingBox());
    }

    public PrimedUHCPE(Level level, double x, double y, double z) {
        this(ModEntity.COMPRESSED_NUCLEAR_BOMB.get(), level);
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
                this.explode();
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
    public void explode() {
        int radius = 40; // ✅ **半径40ブロック**
        int centerX = (int) this.getX();
        int centerY = (int) this.getY();
        int centerZ = (int) this.getZ();

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    if (x * x + y * y + z * z <= radius * radius) { // ✅ **球体判定**
                        int targetX = centerX + x;
                        int targetY = centerY + y;
                        int targetZ = centerZ + z;

                        if (!this.level().getBlockState(new BlockPos(targetX, targetY, targetZ)).isAir()) {
                            this.level().destroyBlock(new BlockPos(targetX, targetY, targetZ), false); // ✅ **ブロック削除**
                        }
                    }
                }
            }
        }

        // ✅ **高く立ち上がる煙**
        for (int height = 0; height < 100; height += 2) {
            this.level().addParticle(ParticleTypes.SMOKE, centerX, centerY + height, centerZ, 50, 1.0, 1.0);
            this.level().addParticle(ParticleTypes.FLAME, centerX, centerY + height, centerZ, 10, 0.5, 0.5); // ✅ **火の粉を追加**
        }
    }


    @Override
    public void resetFallDistance() {
        if (this.fallDistance > 20.0F) {
            this.discard();
            if (!this.level().isClientSide) {
                this.explode();
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