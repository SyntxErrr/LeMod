package com.modding.lemod.Items.armor;

import java.util.UUID;

import init.ItemInit;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ChassisItem extends ArmorItem
{

	public ChassisItem(ArmorMaterial material, EquipmentSlot slot, Properties properties) 
	{
		super(material, slot, properties);
	}
	
	@Override
    public void onArmorTick(ItemStack stack, Level world, Player player) 
	{
        if (!world.isClientSide()){
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 20, 3));
            boolean fullSet = player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ItemInit.CHASSIS_HELMET.get() && player.getItemBySlot(EquipmentSlot.CHEST).getItem() == ItemInit.CHASSIS_CHESTPLATE.get() && player.getItemBySlot(EquipmentSlot.LEGS).getItem() == ItemInit.CHASSIS_LEGGING.get() && player.getItemBySlot(EquipmentSlot.FEET).getItem() == ItemInit.CHASSIS_BOOTS.get();
            if (fullSet){
                // bruh
                
            }
        }
    }
	
}
