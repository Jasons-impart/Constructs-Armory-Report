package com.jasonsimpart.constructsarmoryreport.common.modifier.trait.general;

import javax.annotation.Nonnull;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.shared.TinkerCommons;

public class SavoryModifier extends Modifier {

  @Override
  public void onAttacked(@Nonnull IToolStackView tool, int level,
                         @Nonnull EquipmentContext context, @Nonnull EquipmentSlot slotType,
                         @Nonnull DamageSource source, float amount, boolean isDirectDamage) {

    if (amount > 0) {
      LivingEntity livingEntity = context.getEntity();

      if (livingEntity.invulnerableTime <= 10 &&
          RANDOM.nextInt(24 / level) <= (Math.log(amount + 1.0f) * 2.0f)) {
        context.getEntity().spawnAtLocation(new ItemStack(TinkerCommons.bacon));
      }
    }
  }
}
