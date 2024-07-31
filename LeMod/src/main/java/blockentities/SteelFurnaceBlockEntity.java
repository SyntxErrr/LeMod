package blockentities;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.modding.lemod.LeModMain;

import init.BlockEntityInit;
import init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

public class SteelFurnaceBlockEntity extends BlockEntity
{
	private static final int MAX_PROGRESS = 80;
	
	private int craftingProgress;
	
	private final ItemStackHandler inventory = new ItemStackHandler(4);
	private final LazyOptional<IItemHandlerModifiable> optional = LazyOptional.of(() -> this.inventory);
	
	private final ContainerData data = new ContainerData() {
		@Override
		public int get(int index) 
		{
			return switch (index) {
				case 0 -> SteelFurnaceBlockEntity.this.craftingProgress;
				case 1 -> SteelFurnaceBlockEntity.this.MAX_PROGRESS;
				default -> 0;
			};
		}

		@Override
		public void set(int index, int value) 
		{
			switch(index) {
				case 0 -> SteelFurnaceBlockEntity.this.craftingProgress = value;
				default -> {}
			};
		}
		
		@Override
		public int getCount() 
		{
			return 4;
		}
	};
	
	public static final Component TITLE = Component.translatable("container." + LeModMain.MODID + ".steel_furnace");
	
	public SteelFurnaceBlockEntity(BlockPos pos, BlockState state) 
	{
		super(BlockEntityInit.STEEL_FURNACE.get(), pos, state);
	}
	
	public void tick(Level level, BlockPos pos, BlockState state) 
	{
		if(hasRecipe()) 
		{
			increaseCraftingProgress();
			setChanged(level, pos, state);
			
			if(hasProgressFinished()) 
			{
				craftItem();
				resetProgress();
			}
		}
	}
	
	private void resetProgress() 
	{
		craftingProgress = 0;
	}

	private void craftItem() 
	{
		ItemStack result = new ItemStack(ItemInit.STEEL_INGOT.get(), 1);
		this.inventory.extractItem(0, 2, false);
		this.inventory.extractItem(1, 6, false);
		this.inventory.extractItem(2, 1, false);
		
		this.inventory.setStackInSlot(3, new ItemStack(result.getItem(), this.inventory.getStackInSlot(3).getCount() + 1));
	}

	private boolean hasRecipe() 
	{
		boolean hasCraftingItem1 = this.inventory.getStackInSlot(0).getItem() == Items.IRON_INGOT && this.inventory.getStackInSlot(0).getCount() >= 2;
		boolean hasCraftingItem2 = this.inventory.getStackInSlot(1).getItem() == Items.COAL && this.inventory.getStackInSlot(1).getCount() >= 6;
		boolean hasCraftingItem3 = this.inventory.getStackInSlot(2).getItem() == Items.QUARTZ && this.inventory.getStackInSlot(2).getCount() >= 1;
		boolean hasCraftingItems = hasCraftingItem1 && hasCraftingItem2 && hasCraftingItem3;
		ItemStack result = new ItemStack(ItemInit.STEEL_INGOT.get());
		
		return hasCraftingItems && canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
	}
	
	private boolean canInsertItemIntoOutputSlot(Item item) 
	{
		return this.inventory.getStackInSlot(3).isEmpty() || this.inventory.getStackInSlot(3).is(item);
	}

	private boolean canInsertAmountIntoOutputSlot(int count) 
	{
		return this.inventory.getStackInSlot(3).getCount() + count <= this.inventory.getStackInSlot(3).getMaxStackSize();
	}
	
	private boolean hasProgressFinished() 
	{
		return craftingProgress >= MAX_PROGRESS;
	}

	private void increaseCraftingProgress() 
	{
		craftingProgress++;
	}

	public void drops() 
	{
		SimpleContainer inventory2 = new SimpleContainer(inventory.getSlots());
		for(int i = 0; i < inventory.getSlots(); i++) 
		{
			inventory2.setItem(i, inventory.getStackInSlot(i));
		}
		Containers.dropContents(this.level, this.worldPosition, inventory2);
	}
	
	@Override
	public void load(CompoundTag nbt) 
	{
		super.load(nbt);
		this.craftingProgress = nbt.getInt("Progress");
		this.inventory.deserializeNBT(nbt.getCompound("Inventory"));
	}
	
	@Override
	protected void saveAdditional(CompoundTag nbt) 
	{
		super.saveAdditional(nbt);
		nbt.putInt("Progress", this.craftingProgress);
		nbt.put("Inventory", this.inventory.serializeNBT());
	}
	
	@Override
	public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) 
	{
		return cap == ForgeCapabilities.ITEM_HANDLER ? this.optional.cast() : super.getCapability(cap, side);
	}
	
	@Override
	public void invalidateCaps() 
	{
		this.optional.invalidate();
	}
	
	public ItemStackHandler getInventory() 
	{
		return inventory;
	}
	
	public ContainerData getContainerData() 
	{
		return this.data;
	}
	
	
}
