package init;

import com.modding.lemod.CustomCreativeModeTab;
import com.modding.lemod.LeModMain;
import com.modding.lemod.Items.FusionCoreItem;
import com.modding.lemod.Items.armor.ChassisItem;
import com.modding.lemod.Items.armor.PowerChassisMaterial;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ItemInit 
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LeModMain.MODID);
	
	
	
	public static final RegistryObject<Item> SMILE = ITEMS.register("smile", () -> new Item(new Item.Properties().tab(CustomCreativeModeTab.instance)));
	
	public static final RegistryObject<Item> FRUIT = ITEMS.register("fruit", () -> new Item(new Item.Properties().tab(CustomCreativeModeTab.instance)
			.food(new FoodProperties.Builder().nutrition(4).saturationMod(2).build())));
	
	public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", 
			() -> new Item(new Item.Properties().tab(CustomCreativeModeTab.instance)));
	
	public static final RegistryObject<Item> FUSION_CORE = ITEMS.register("fusion_core", 
			() -> new FusionCoreItem(new Item.Properties().durability(1000).tab(CustomCreativeModeTab.instance)));
	
	public static final RegistryObject<Item> PORTAL_RADIO_MUSIC_DISC = ITEMS.register("portal_radio_music_disc", 
			() -> new RecordItem(6, SoundInit.PORTAL_RADIO, new Item.Properties().stacksTo(1).tab(CustomCreativeModeTab.instance), 900));
	
	public static final RegistryObject<Item> YIPPEE_MUSIC_DISC = ITEMS.register("yippee_music_disc", 
			() -> new RecordItem(6, SoundInit.YIPPEE, new Item.Properties().stacksTo(1).tab(CustomCreativeModeTab.instance), 20));
	
	public static final RegistryObject<Item> CHASSIS_HELMET = ITEMS.register("chassis_helmet", 
			() -> new ChassisItem(PowerChassisMaterial.CHASSIS, EquipmentSlot.HEAD, new Item.Properties().tab(CustomCreativeModeTab.instance)));
	
	public static final RegistryObject<Item> CHASSIS_CHESTPLATE = ITEMS.register("chassis_chestplate", 
			() -> new ChassisItem(PowerChassisMaterial.CHASSIS, EquipmentSlot.CHEST, new Item.Properties().tab(CustomCreativeModeTab.instance)));
	
	public static final RegistryObject<Item> CHASSIS_LEGGING = ITEMS.register("chassis_leggings", 
			() -> new ChassisItem(PowerChassisMaterial.CHASSIS, EquipmentSlot.LEGS, new Item.Properties().tab(CustomCreativeModeTab.instance)));
	
	public static final RegistryObject<Item> CHASSIS_BOOTS = ITEMS.register("chassis_boots", 
			() -> new ChassisItem(PowerChassisMaterial.CHASSIS, EquipmentSlot.FEET, new Item.Properties().tab(CustomCreativeModeTab.instance)));
	
	
}
