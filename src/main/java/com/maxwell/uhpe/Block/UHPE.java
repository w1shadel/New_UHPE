package com.maxwell.uhpe.Block;

import com.maxwell.uhpe.Entity.BlockEntity.PrimedUHPE;
import com.maxwell.uhpe.Register.ModEntity;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.Block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class UHPE extends Block {
    public static final BooleanProperty UNSTABLE;

    public UHPE(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState((BlockState)this.defaultBlockState().setValue(UNSTABLE, false));
    }

    public void onCaughtFire(BlockState state, Level world, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {
        explode(world, pos);
    }

    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (!pOldState.is(pState.getBlock()) && pLevel.hasNeighborSignal(pPos)) {
            this.onCaughtFire(pState, pLevel, pPos, (Direction)null, (LivingEntity)null);
            pLevel.removeBlock(pPos, false);
        }

    }

    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (pLevel.hasNeighborSignal(pPos)) {
            this.onCaughtFire(pState, pLevel, pPos, (Direction)null, (LivingEntity)null);
            pLevel.removeBlock(pPos, false);
        }

    }

    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        if (!pLevel.isClientSide() && !pPlayer.isCreative() && (Boolean)pState.getValue(UNSTABLE)) {
            this.onCaughtFire(pState, pLevel, pPos, (Direction)null, (LivingEntity)null);
        }

        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }
    public static void explode(Level level, BlockPos position) {
        if (!level.isClientSide) { // ✅ **クライアント側で生成しない**
            PrimedUHPE entity = new PrimedUHPE(ModEntity.NUCLEAR_BOMB.get(), level);
            entity.moveTo(position.getX() + 0.5, position.getY(), position.getZ() + 0.5, 0.0F, 0.0F); // ✅ **BlockPosを適用**
            level.addFreshEntity(entity); // ✅ **ワールドに追加**
        }
    }




    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (!itemstack.is(Items.FLINT_AND_STEEL) && !itemstack.is(Items.FIRE_CHARGE)) {
            return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
        } else {
            this.onCaughtFire(pState, pLevel, pPos, pHit.getDirection(), pPlayer);
            pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 11);
            Item item = itemstack.getItem();
            if (!pPlayer.isCreative()) {
                if (itemstack.is(Items.FLINT_AND_STEEL)) {
                    itemstack.hurtAndBreak(1, pPlayer, (p_57425_) -> p_57425_.broadcastBreakEvent(pHand));
                } else {
                    itemstack.shrink(1);
                }
            }

            pPlayer.awardStat(Stats.ITEM_USED.get(item));
            return InteractionResult.sidedSuccess(pLevel.isClientSide);
        }
    }
    public boolean dropFromExplosion(Explosion pExplosion) {
        return false;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(new Property[]{UNSTABLE});
    }

    static {
        UNSTABLE = BlockStateProperties.UNSTABLE;
    }
}

