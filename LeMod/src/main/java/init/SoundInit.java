package init;

import com.modding.lemod.LeModMain;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundInit 
{
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, LeModMain.MODID);
	
	public static final RegistryObject<SoundEvent> PORTAL_RADIO = SOUND_EVENTS.register("portal_radio", 
			() -> new SoundEvent(new ResourceLocation(LeModMain.MODID, "portal_radio")));
	
	public static final RegistryObject<SoundEvent> YIPPEE = SOUND_EVENTS.register("yippee", 
			() -> new SoundEvent(new ResourceLocation(LeModMain.MODID, "yippee")));
	
	public static void register(IEventBus eventBus) 
	{
		SOUND_EVENTS.register(eventBus);
	}
}
