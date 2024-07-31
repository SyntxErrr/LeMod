package com.modding.lemod.Blocks;

import blockentities.SteelFurnaceBlockEntity;
import init.BlockEntityInit;
import menus.SteelFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class SteelFurnaceBlock extends Block implements EntityBlock
{

	public SteelFurnaceBlock(Properties properties) 
	{
		super(properties);
	}

	
	@Override
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) 
	{
		if (state.getBlock() != newState.getBlock()) 
		{
			BlockEntity blockEntity = level.getBlockEntity(pos);
			if (blockEntity instanceof SteelFurnaceBlockEntity) 
			{
				((SteelFurnaceBlockEntity) blockEntity).drops();
			}
		}
		
		super.onRemove(state, level, pos, newState, isMoving);
	}
	
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return BlockEntityInit.STEEL_FURNACE.get().create(pos, state);
	}
	
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) 
	{
		return level.isClientSide() ? null : ($0, $1, $2, blockEntity) -> {
			if(blockEntity instanceof SteelFurnaceBlockEntity steel_furnace) 
			{
				steel_furnace.tick(level, $1, state);
			}
		};
	}
	
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) 
	{
		if(!level.isClientSide()) 
		{
			if(level.getBlockEntity(pos) instanceof SteelFurnaceBlockEntity steel_furnace) 
			{
				MenuConstructor menuConstructor = SteelFurnaceMenu.getServerMenu(steel_furnace, pos);
				SimpleMenuProvider provider = new SimpleMenuProvider(menuConstructor, SteelFurnaceBlockEntity.TITLE);
				NetworkHooks.openScreen((ServerPlayer) player, provider, pos);
			}
		}
		
		return InteractionResult.sidedSuccess(!level.isClientSide());
	}
	
}
