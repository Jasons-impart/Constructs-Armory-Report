package com.jasonsimpart.constructsarmoryreport.common.modifier.trait.battle;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.modifiers.impl.TotalArmorLevelModifier;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import com.jasonsimpart.constructsarmoryreport.ConstructsArmoryReport;
import com.jasonsimpart.constructsarmoryreport.common.ConstructsArmoryEffects;
import com.jasonsimpart.constructsarmoryreport.common.modifier.EquipmentUtil;

import java.util.List;

public class VengefulModifier extends TotalArmorLevelModifier {

  private static final TinkerDataCapability.TinkerDataKey<Integer> VENGEFUL =
      ConstructsArmoryReport.createKey("vengeful");

  public VengefulModifier() {
    super(VENGEFUL);
    MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, VengefulModifier::onHurt);
  }

  private static void onHurt(final LivingHurtEvent evt) {
    LivingEntity living = evt.getEntityLiving();
    living.getCapability(TinkerDataCapability.CAPABILITY).ifPresent(holder -> {
      int levels = holder.get(VENGEFUL, 0);

      if (levels > 0) {
        int effectLevel = Math.min(7, ConstructsArmoryEffects.VENGEFUL.get().getLevel(living) + 1);
        ConstructsArmoryEffects.VENGEFUL.get().apply(living, 5 * 20, effectLevel, true);
      }
    });
  }

  private static float getBonus(LivingEntity attacker, int level) {
    int effectLevel = ConstructsArmoryEffects.VENGEFUL.get().getLevel(attacker) + 1;
    return level * effectLevel / 8f;
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
      bonus = 2f;
    }
    EquipmentUtil.addResistanceTooltip(this, tool, bonus, tooltip);
  }
}
