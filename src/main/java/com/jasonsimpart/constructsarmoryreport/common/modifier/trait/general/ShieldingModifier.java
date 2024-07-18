package com.jasonsimpart.constructsarmoryreport.common.modifier.trait.general;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.PotionEvent;
import org.apache.commons.lang3.reflect.FieldUtils;
import slimeknights.tconstruct.library.modifiers.impl.TotalArmorLevelModifier;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import com.jasonsimpart.constructsarmoryreport.ConstructsArmoryReport;

import java.lang.reflect.Field;

public class ShieldingModifier extends TotalArmorLevelModifier {

  private static final TinkerDataCapability.TinkerDataKey<Integer> SHIELDING =
      ConstructsArmoryReport.createKey("shielding");

  public ShieldingModifier() {
    super(SHIELDING);
    MinecraftForge.EVENT_BUS.addListener(ShieldingModifier::onPotionStart);
  }

  private static void onPotionStart(final PotionEvent.PotionAddedEvent evt) {
    MobEffectInstance newEffect = evt.getPotionEffect();

    if (!newEffect.getCurativeItems().isEmpty()) {
      LivingEntity living = evt.getEntityLiving();
      living.getCapability(TinkerDataCapability.CAPABILITY).ifPresent(data -> {
        int levels = data.get(SHIELDING, 0);

        if (levels > 0) {
          float change = levels * 0.05f;

          if (!newEffect.getEffect().isBeneficial()) {
            change *= -1;
          }
          try {
            Field f_19503_ = FieldUtils.getDeclaredField(MobEffectInstance.class, "f_19503_", true);
            f_19503_.setAccessible(true);
            f_19503_.set(newEffect, Math.max(0, (int) (newEffect.getDuration() * (1 + change))));
          } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
          }
        }
      });
    }
  }
}
