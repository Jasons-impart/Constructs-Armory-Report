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
import net.minecraftforge.common.Tags;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import com.jasonsimpart.constructsarmoryreport.common.modifier.EquipmentUtil;

import java.util.List;

public class PetrousModifier extends Modifier {

  private static final float BONUS_PER_BLOCK = 0.1f;

  public PetrousModifier() {

  }

  private static float getBonus(LivingEntity living, int level) {
    BlockPos pos = living.blockPosition();
    BlockPos center = pos.below();
    BlockPos[] candidates =
        new BlockPos[] {center, center.north(), center.south(), center.east(), center.west(),
            center.north().east(), center.north().west(), center.south().east(),
            center.south().west()};
    float bonus = 0;

    for (BlockPos candidate : candidates) {

      if (living.level.getBlockState(candidate).is(Tags.Blocks.STONE)) {
        bonus += BONUS_PER_BLOCK;
      }
    }
    return bonus * level;
  }

  @Override
  public float getProtectionModifier(@Nonnull IToolStackView tool, int level,
                                     @Nonnull EquipmentContext context,
                                     @Nonnull EquipmentSlot slotType, DamageSource source,
                                     float modifierValue) {

    if (!source.isBypassMagic() && !source.isBypassInvul()) {
      modifierValue += getBonus(context.getEntity(), level);
    }
    return modifierValue;
  }

  @Override
  public void addInformation(@Nonnull IToolStackView tool, int level,
                             @Nullable Player player, @Nonnull List<Component> tooltip,
                             @Nonnull TooltipKey key, @Nonnull TooltipFlag flag) {
    float bonus;

    if (player != null && key == TooltipKey.SHIFT) {
      bonus = getBonus(player, level);
    } else {
      bonus = level * BONUS_PER_BLOCK;
    }
    EquipmentUtil.addResistanceTooltip(this, tool, bonus, tooltip);
  }
}
