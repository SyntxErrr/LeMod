package com.modding.lemod;

import com.mojang.logging.LogUtils;

import init.BlockEntityInit;
import init.BlockInit;
import init.ItemInit;
import init.MenuInit;
import init.SoundInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ContainerScreenEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import screens.SteelFurnaceScreen;

import org.slf4j.Logger;

@Mod(LeModMain.MODID)
public class LeModMain
{
    public static final String MODID = "lemod";
    
    private static final Logger LOGGER = LogUtils.getLogger();


    public LeModMain()
    {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);
        ItemInit.ITEMS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        BlockEntityInit.BLOCK_ENTITIES.register(modEventBus);
        MenuInit.MENUS.register(modEventBus);
        SoundInit.SOUND_EVENTS.register(modEventBus);
        
        // ModList.get().isLoaded('MOD_NAME')
        // This lets you check if a mod is loaded and then run associated code
        if(ModList.get().isLoaded("tconstruct")) 
        {
        	LOGGER.info("--------------Tinkers' was detected!!!</!\\>--------------------");
        }
        if(ModList.get().isLoaded("create")) 
        {
        	LOGGER.info("--------------Create was detected!!!</!\\>--------------------");
        }
        
        
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void setup(final FMLCommonSetupEvent event)
    {
    	
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents 
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) 
        {
            MenuScreens.register(MenuInit.STEEL_FURNACE.get(), SteelFurnaceScreen::new);
        }
    }
    
    
}
