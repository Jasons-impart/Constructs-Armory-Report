package com.jasonsimpart.constructsarmoryreport.common.modifier.trait.battle;

import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import com.jasonsimpart.constructsarmoryreport.common.modifier.EquipmentUtil;

public class WeightyModifier extends Modifier {

  private static final float BASELINE_MOVEMENT = 0.1f;
  private static final float MAX_MOVEMENT = 0.15f;

  private static float getBonus(float movementSpeed, float min, float max) {

    if (movementSpeed > BASELINE_MOVEMENT) {

      if (movementSpeed > MAX_MOVEMENT) {
        return min;
      }
      return (MAX_MOVEMENT - movementSpeed) / (MAX_MOVEMENT - BASELINE_MOVEMENT) * (max - min);
    }
    return max;
  }

  @Override
  public float getProtectionModifier(@Nonnull IToolStackView tool, int level,
                                     @Nonnull EquipmentContext context,
                                     @Nonnull EquipmentSlot slotType, DamageSource source,
                                     float modifierValue) {

    if (!source.isBypassMagic() && !source.isBypassInvul()) {
      AttributeInstance attributeInstance =
          context.getEntity().getAttribute(Attributes.MOVEMENT_SPEED);

      if (attributeInstance != null) {
        modifierValue += getBonus((float) attributeInstance.getValue(), 0.0f, 1.5f) * level;
      }
    }
    return modifierValue;
  }

  @Override
  public void addInformation(@Nonnull IToolStackView tool, int level,
                             @Nullable Player player, @Nonnull List<Component> tooltip,
                             @Nonnull TooltipKey key, @Nonnull TooltipFlag flag) {
    float bonus;

    if (player != null) {
      AttributeInstance attributeInstance =
          player.getAttribute(Attributes.MOVEMENT_SPEED);

      if (attributeInstance != null) {
        bonus = getBonus((float) attributeInstance.getValue(), 0.0f, 1.5f) * level;
      } else {
        bonus = 0f;
      }
    } else {
      bonus = level * 1.5f;
    }
    EquipmentUtil.addResistanceTooltip(this, tool, bonus, tooltip);
  }
}
