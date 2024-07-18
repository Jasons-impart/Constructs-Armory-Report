package com.jasonsimpart.constructsarmoryreport.common.modifier.trait.general;

import javax.annotation.Nonnull;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import slimeknights.tconstruct.library.events.teleport.EnderdodgingTeleportEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.TeleportHelper;
import slimeknights.tconstruct.tools.TinkerModifiers;

public class EndershieldModifier extends Modifier {

  private static final TeleportHelper.ITeleportEventFactory FACTORY =
      EnderdodgingTeleportEvent::new;

  @Override
  public void onAttacked(@Nonnull IToolStackView tool, int level, EquipmentContext context,
                         @Nonnull EquipmentSlot slotType, @Nonnull DamageSource source,
                         float amount, boolean isDirectDamage) {
    LivingEntity self = context.getEntity();

    if (!self.hasEffect(TinkerModifiers.teleportCooldownEffect.get()) &&
        RANDOM.nextInt(10 - level * 2) == 0) {

      if (TeleportHelper.randomNearbyTeleport(context.getEntity(), FACTORY)) {
        TinkerModifiers.teleportCooldownEffect.get().apply(self, 15 * 20, 1, true);
      }
    }
  }
}
