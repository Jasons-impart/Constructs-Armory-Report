package com.jasonsimpart.constructsarmoryreport.data;

import javax.annotation.Nonnull;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.item.ArmorSlotType;
import com.jasonsimpart.constructsarmoryreport.ConstructsArmoryReport;
import com.jasonsimpart.constructsarmoryreport.common.ConstructsArmoryDefinitions;
import com.jasonsimpart.constructsarmoryreport.common.ConstructsArmoryItems;
import com.jasonsimpart.constructsarmoryreport.common.stat.ConstructsArmoryStats;

public class ArmorDefinitionDataProvider extends AbstractToolDefinitionDataProvider {

  public ArmorDefinitionDataProvider(DataGenerator generator) {
    super(generator, ConstructsArmoryReport.MOD_ID);
  }

  @Override
  protected void addToolDefinitions() {
    defineArmor(ConstructsArmoryDefinitions.MATERIAL_ARMOR)
        .part(ArmorSlotType.HELMET, ConstructsArmoryItems.HEAD_PLATE.get(), 1)
        .part(ArmorSlotType.HELMET, ConstructsArmoryItems.MAIL.get(), 1)
        .part(ArmorSlotType.CHESTPLATE, ConstructsArmoryItems.BODY_PLATE.get(), 1)
        .part(ArmorSlotType.CHESTPLATE, ConstructsArmoryItems.MAIL.get(), 1)
        .part(ArmorSlotType.LEGGINGS, ConstructsArmoryItems.LEGS_PLATE.get(), 1)
        .part(ArmorSlotType.LEGGINGS, ConstructsArmoryItems.MAIL.get(), 1)
        .part(ArmorSlotType.BOOTS, ConstructsArmoryItems.FEET_PLATE.get(), 1)
        .part(ArmorSlotType.BOOTS, ConstructsArmoryItems.MAIL.get(), 1)
        .statAll(ToolStats.ARMOR, 0)
        .statAll(ToolStats.ARMOR_TOUGHNESS, 0)
        .statAll(ToolStats.KNOCKBACK_RESISTANCE, 0)
        .statAll(ConstructsArmoryStats.MOVEMENT_SPEED, 0)
        .startingSlots(SlotType.UPGRADE, 1)
        .startingSlots(SlotType.DEFENSE, 2)
        .startingSlots(SlotType.ABILITY, 1);
  }

  @Nonnull
  @Override
  public String getName() {
    return "Construct's Armory Armor Definition Data Generator";
  }
}
