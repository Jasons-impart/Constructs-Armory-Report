package com.jasonsimpart.constructsarmoryreport.common.modifier.trait.speed;

import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

/**
 * Modified copy of {@link MaintainedModifier2} from Tinkers' Construct
 * MIT License (c) SlimeKnights
 */
public class ImmaculateModifier2 extends ImmaculateModifier {

  @Override
  protected float getTotalBoost(IToolStackView armor, int level) {
    int durability = armor.getCurrentDurability();
    int fullMax = armor.getStats().getInt(ToolStats.DURABILITY);
    return boost(durability, 0.025f, fullMax / 4, fullMax) * level;
  }
}
