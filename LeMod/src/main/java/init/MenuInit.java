package init;

import com.modding.lemod.LeModMain;

import menus.SteelFurnaceMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class MenuInit 
{
	public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, LeModMain.MODID);
	
	public static final RegistryObject<MenuType<SteelFurnaceMenu>> STEEL_FURNACE = MENUS.register("steel_furnace", () -> new MenuType<>(SteelFurnaceMenu::getClientMenu));
	
}
