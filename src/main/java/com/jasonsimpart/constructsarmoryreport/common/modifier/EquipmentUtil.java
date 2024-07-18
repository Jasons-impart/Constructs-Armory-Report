package com.jasonsimpart.constructsarmoryreport.common.modifier;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.Util;

import java.util.*;

public class EquipmentUtil {

  private static final Map<ModifierId, Map<EquipmentSlot, UUID>> UUIDS = new HashMap<>();

  public static UUID getUuid(ModifierId id, EquipmentSlot slotType) {
    return UUIDS.computeIfAbsent(id, (k) -> new EnumMap<>(EquipmentSlot.class))
        .computeIfAbsent(slotType, (k) -> {
          String key = id + slotType.toString();
          return UUID.nameUUIDFromBytes(key.getBytes());
        });
  }

  public static void addResistanceTooltip(Modifier modifier, IToolStackView armor,
                                          float multiplier, List<Component> tooltip) {

    if (armor.hasTag(TinkerTags.Items.ARMOR)) {
      tooltip.add(modifier.applyStyle(new TextComponent(
              Util.PERCENT_BOOST_FORMAT.format(multiplier / 25f))
              .append(" ")
              .append(
                  new TranslatableComponent(modifier.getTranslationKey() + ".resistance"))));
    }
  }

  public static void addSpeedTooltip(Modifier modifier, IToolStackView armor, float multiplier,
                                     List<Component> tooltip) {

    if (armor.hasTag(TinkerTags.Items.ARMOR)) {
      tooltip.add(modifier.applyStyle(new TextComponent(
          Util.PERCENT_BOOST_FORMAT.format(multiplier))
          .append(" ")
          .append(
              new TranslatableComponent(modifier.getTranslationKey() + ".speed"))));
    }
  }
}
