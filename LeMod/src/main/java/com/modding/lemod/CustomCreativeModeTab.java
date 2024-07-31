package com.modding.lemod;

import init.ItemInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CustomCreativeModeTab extends CreativeModeTab 
{
	public static final CustomCreativeModeTab instance = new CustomCreativeModeTab(CreativeModeTab.TABS.length, "lemod");
	
	private CustomCreativeModeTab(int index, String label) 
	{
		super(index, label);
	}
	
	@Override
	public ItemStack makeIcon() 
	{
		return new ItemStack(ItemInit.SMILE.get());
	}
}
