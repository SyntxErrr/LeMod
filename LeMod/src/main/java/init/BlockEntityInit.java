package init;

import com.modding.lemod.LeModMain;

import blockentities.SteelFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit 
{
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, LeModMain.MODID);
	
	
	public static final RegistryObject<BlockEntityType<SteelFurnaceBlockEntity>> STEEL_FURNACE = BLOCK_ENTITIES.register("steel_furnace", () -> BlockEntityType.Builder.of(SteelFurnaceBlockEntity::new, BlockInit.STEEL_FURNACE.get()).build(null)); 
}
