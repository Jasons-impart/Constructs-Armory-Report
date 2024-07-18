package com.jasonsimpart.constructsarmoryreport.common.modifier.trait.battle;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.tools.TinkerModifiers;
import com.jasonsimpart.constructsarmoryreport.common.modifier.EquipmentUtil;

import java.util.List;

public class IgneousModifier extends Modifier {

  private static final float BASELINE_TEMPERATURE = 0.75f;

  @Override
  public void addVolatileData(@Nonnull ToolRebuildContext context, int level,
                              @Nonnull ModDataNBT volatileData) {
    TinkerModifiers.tank.get().addCapacity(volatileData, FluidValues.INGOT * 2);
  }

  private static float temperatureBoost(LivingEntity living, int level) {
    BlockPos attackerPos = living.blockPosition();
    return (living.level.getBiome(attackerPos).value().getBaseTemperature() - BASELINE_TEMPERATURE) *
        (level * 1.6f);
  }

  @Override
  public float getProtectionModifier(@Nonnull IToolStackView tool, int level,
                                     @Nonnull EquipmentContext context,
                                     @Nonnull EquipmentSlot slotType, DamageSource source,
                                     float modifierValue) {

    if (!source.isBypassMagic() && !source.isBypassInvul()) {
      modifierValue += temperatureBoost(context.getEntity(), level);
    }
    return modifierValue;
  }

  @Override
  public void addInformation(@Nonnull IToolStackView tool, int level,
                             @Nullable Player player,
                             @Nonnull List<Component> tooltip, @Nonnull TooltipKey key,
                             @Nonnull TooltipFlag flag) {
    float bonus;

    if (player != null && key == TooltipKey.SHIFT) {
      bonus = temperatureBoost(player, level);
    } else {
      bonus = 2f;
    }
    EquipmentUtil.addResistanceTooltip(this, tool, bonus, tooltip);
  }
}
