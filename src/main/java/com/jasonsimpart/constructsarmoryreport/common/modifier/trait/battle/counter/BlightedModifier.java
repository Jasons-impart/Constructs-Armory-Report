package com.jasonsimpart.constructsarmoryreport.common.modifier.trait.battle.counter;

import javax.annotation.Nonnull;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class BlightedModifier extends CounterattackModifier {

  private static MobEffectInstance makeDecayEffect(int level) {
    return new MobEffectInstance(MobEffects.WITHER, 20 * (5 + (RANDOM.nextInt(level * 3))), level - 1);
  }

  @Override
  protected int counter(@Nonnull IToolStackView tool, int level, LivingEntity attacker,
                        @Nonnull EquipmentContext context, @Nonnull EquipmentSlot slotType,
                        DamageSource source, float amount) {

    if (RANDOM.nextFloat() < 0.15f * level) {

      if (RANDOM.nextInt(3) == 0) {
        context.getEntity().addEffect(makeDecayEffect(level));
      }
      attacker.addEffect(makeDecayEffect(level));
      return 1;
    }
    return 0;
  }
}
