package com.modding.lemod.Items;


import net.minecraft.client.model.ArmorStandModel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class FusionCoreItem extends Item 
{
	public FusionCoreItem(Properties properties) 
	{
		super(properties);
	}

	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            message(player);
            // this gives an effect to the player when using the item, in this case the effect is jump boost
            //player.addEffect(new MobEffectInstance(MobEffects.JUMP, 200, 3));
            testing(player);
            player.getCooldowns().addCooldown(this, 20);
        }

        return super.use(level, player, hand);
    }
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) 
	{
		this.setDamage(getDefaultInstance(), 50);
		return true;
	}
	
	private void message(Player player) {
        player.sendSystemMessage(Component.literal("Your Durability is " + this.getDamage(getDefaultInstance())));
    }
	
	private void testing(Player player) 
	{
		player.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.CHAINMAIL_CHESTPLATE));
		player.setAbsorptionAmount(100);
		// continue looking at the functions in the Player class
	}
	
}
