package com.jasonsimpart.constructsarmoryreport.common.modifier.trait.general;

import javax.annotation.Nonnull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import com.jasonsimpart.constructsarmoryreport.common.stat.ConstructsArmoryStats;

public class DuctileModifier extends Modifier {

  @Override
  public void addToolStats(@Nonnull ToolRebuildContext context, int level,
                           @Nonnull ModifierStatsBuilder builder) {
    ToolStats.DURABILITY.multiply(builder, 1 + (level * 0.04f));
    ToolStats.ARMOR.multiply(builder, 1 + (level * 0.04f));
    ToolStats.ARMOR_TOUGHNESS.multiply(builder, 1 + (level * 0.04f));
    ToolStats.KNOCKBACK_RESISTANCE.multiply(builder, 1 + (level * 0.04f));
    ConstructsArmoryStats.MOVEMENT_SPEED.multiply(builder, 1 + (level * 0.04f));
  }
}
