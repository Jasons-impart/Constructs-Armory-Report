package com.jasonsimpart.constructsarmoryreport.common.modifier.trait.battle.counter;

import javax.annotation.Nonnull;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.tools.TinkerModifiers;

public class PricklyModifier extends CounterattackModifier {

  @Override
  protected int counter(@Nonnull IToolStackView tool, int level, LivingEntity attacker,
                        @Nonnull EquipmentContext context, @Nonnull EquipmentSlot slotType,
                        DamageSource source, float amount) {

    if (RANDOM.nextFloat() < 0.15f * level) {
      attacker.setLastHurtByMob(context.getEntity());
      TinkerModifiers.bleeding.get()
          .apply(attacker, 1 + 20 * (2 + (RANDOM.nextInt(level + 3))), level - 1, true);
      return 1;
    }
    return 0;
  }
}
