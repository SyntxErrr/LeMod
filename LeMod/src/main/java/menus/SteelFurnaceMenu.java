package menus;

import blockentities.SteelFurnaceBlockEntity;
import init.BlockInit;
import init.MenuInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SteelFurnaceMenu extends AbstractContainerMenu
{
	private final ContainerLevelAccess levelAccess;
	private final ContainerData data;
	
	protected SteelFurnaceMenu(int id, Inventory playerInv, IItemHandler slots, BlockPos pos, ContainerData data) 
	{
		super(MenuInit.STEEL_FURNACE.get(), id);
		this.levelAccess = ContainerLevelAccess.create(playerInv.player.getLevel(), pos);
		this.data = data;
		
		addSlot(new SlotItemHandler(slots, 0, 34, 35));
		addSlot(new SlotItemHandler(slots, 1, 56, 24));
		addSlot(new SlotItemHandler(slots, 2, 56, 45));
		addSlot(new SlotWithRestriction(slots, 3, 116, 35));
		
		final int slotSizePlus2 = 18;
		final int startX = 8;
		final int startY = 84;
		final int hotbarY = 142;
		for(int row = 0; row < 3; ++row) 
		{
			for(int column = 0; column < 9; ++column) 
			{
				// adds the inventory slots
				addSlot(new Slot(playerInv, column + row * 9 + 9, startX + column * slotSizePlus2, startY + row * slotSizePlus2));
			}
		}
		
		for(int column = 0; column < 9; ++column) 
		{
			// adds the hotbar slots
			addSlot(new Slot(playerInv, column, startX + column * slotSizePlus2, hotbarY));
		}
		
		this.addDataSlots(this.data);
	}
	
	public boolean isCrafting() {
        return data.get(0) > 0;
    }
	
	public int getScaledProgress() 
	{
		int progress = this.data.get(0);
		int maxProgress = this.data.get(1);
		int progressArrowSize = 23;
		
		return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
	}

	public static SteelFurnaceMenu getClientMenu(int id, Inventory playerInv) 
	{
		return new SteelFurnaceMenu(id, playerInv, new ItemStackHandler(4), BlockPos.ZERO, new SimpleContainerData(4));
	}
	
	public static MenuConstructor getServerMenu(SteelFurnaceBlockEntity blockEntity, BlockPos pos) 
	{
		return (id, playerInv, player) -> new SteelFurnaceMenu(id, playerInv, blockEntity.getInventory(), pos, blockEntity.getContainerData());
	}
	
	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack itemStack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot.hasItem()) 
		{
			ItemStack current = slot.getItem();
			itemStack = current.copy();
			if (index < 4) 
			{
				if (!this.moveItemStackTo(current, 4, this.slots.size(), true)) 
				{
					return ItemStack.EMPTY;
				} else if (!this.moveItemStackTo(current, 0, 4, false)) 
				{
					return ItemStack.EMPTY;
				}
			}
			
			if (current.isEmpty()) 
			{
				slot.set(ItemStack.EMPTY);
			} else 
			{
				slot.setChanged();
			}
		}
		
		return itemStack;
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(this.levelAccess, player, BlockInit.STEEL_FURNACE.get());
	}
	
	public ContainerData getData() 
	{
		return data;
	}

}
