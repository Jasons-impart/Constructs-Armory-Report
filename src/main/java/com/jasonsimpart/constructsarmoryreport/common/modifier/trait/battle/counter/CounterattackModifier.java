package com.jasonsimpart.constructsarmoryreport.common.modifier.trait.battle.counter;

import javax.annotation.Nonnull;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public abstract class CounterattackModifier extends Modifier {

  @Override
  public void onAttacked(@Nonnull IToolStackView tool, int level,
                         @Nonnull EquipmentContext context, @Nonnull EquipmentSlot slotType,
                         DamageSource source, float amount, boolean isDirectDamage) {
    Entity attacker = source.getEntity();

    if (attacker instanceof LivingEntity && attacker.isAlive() && isDirectDamage) {
      int durabilityDamage =
          counter(tool, level, (LivingEntity) attacker, context, slotType, source, amount);

      if (durabilityDamage > 0) {
        ToolDamageUtil.damageAnimated(tool, durabilityDamage, context.getEntity(), slotType);
      }
    }
  }

  protected abstract int counter(@Nonnull IToolStackView tool, int level,
                                 LivingEntity attacker, @Nonnull EquipmentContext context,
                                 @Nonnull EquipmentSlot slotType, DamageSource source,
                                 float amount);
}
