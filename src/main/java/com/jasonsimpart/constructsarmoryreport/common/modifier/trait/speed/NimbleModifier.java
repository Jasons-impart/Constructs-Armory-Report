package com.jasonsimpart.constructsarmoryreport.common.modifier.trait.speed;

import javax.annotation.Nonnull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;
import com.jasonsimpart.constructsarmoryreport.common.stat.ConstructsArmoryStats;

public class NimbleModifier extends Modifier {


  @Override
  public void addToolStats(@Nonnull ToolRebuildContext context, int level,
                           @Nonnull ModifierStatsBuilder builder) {
    ConstructsArmoryStats.MOVEMENT_SPEED.add(builder, level * 0.02f);
  }


}
