package com.jasonsimpart.constructsarmoryreport.common.modifier.trait.battle.counter;

import javax.annotation.Nonnull;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class SplinteredModifier extends CounterattackModifier {

  @Override
  protected int counter(@Nonnull IToolStackView tool, int level, LivingEntity attacker,
                        @Nonnull EquipmentContext context, @Nonnull EquipmentSlot slotType,
                        DamageSource source, float amount) {

    if (RANDOM.nextFloat() < 0.15f * level) {
      float percent = (float) tool.getDamage() / tool.getStats().get(ToolStats.DURABILITY);
      float maxDamage = level * 3f;
      LivingEntity user = context.getEntity();
      attacker.hurt(DamageSource.thorns(user), maxDamage * percent);
      return 1;
    }
    return 0;
  }
}
