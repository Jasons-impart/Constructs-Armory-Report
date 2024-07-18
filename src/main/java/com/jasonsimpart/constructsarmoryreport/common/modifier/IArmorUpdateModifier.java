package com.jasonsimpart.constructsarmoryreport.common.modifier;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public interface IArmorUpdateModifier {

  void onUpdate(IToolStackView armor, EquipmentSlot slotType, int level,
                LivingEntity living);
}
