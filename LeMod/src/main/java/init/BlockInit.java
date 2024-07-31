package init;

import java.util.function.Supplier;

import com.modding.lemod.CustomCreativeModeTab;
import com.modding.lemod.LeModMain;
import com.modding.lemod.Blocks.SteelFurnaceBlock;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class BlockInit 
{
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LeModMain.MODID);
	
	public static final RegistryObject<Block> STEEL_BLOCK = BLOCKS.register("steel_block", () -> new Block(Block.Properties.of(Material.HEAVY_METAL).strength(4f, 1200f).requiresCorrectToolForDrops()));
	
	public static final RegistryObject<SteelFurnaceBlock> STEEL_FURNACE = BLOCKS.register("steel_furnace", () -> new SteelFurnaceBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(5f, 20f)));

	@SubscribeEvent
	public static void onRegisterItems(final RegisterEvent event) {
	    if (event.getRegistryKey().equals(ForgeRegistries.Keys.ITEMS)){
	        BLOCKS.getEntries().forEach( (blockRegistryObject) -> {
	            Block block = blockRegistryObject.get();
	            Item.Properties properties = new Item.Properties().tab(CustomCreativeModeTab.instance);
	            Supplier<Item> blockItemFactory = () -> new BlockItem(block, properties);
	            event.register(ForgeRegistries.Keys.ITEMS, blockRegistryObject.getId(), blockItemFactory);
	        });
	    }
	}
}
