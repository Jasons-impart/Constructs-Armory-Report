package com.jasonsimpart.constructsarmoryreport.common.stat.impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.jasonsimpart.constructsarmoryreport.ConstructsArmoryReport;
import com.jasonsimpart.constructsarmoryreport.api.ArmorStatsCalculator;
import com.jasonsimpart.constructsarmoryreport.common.stat.ConstructsArmoryStats;
import lombok.*;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import slimeknights.tconstruct.library.materials.stats.BaseMaterialStats;
import slimeknights.tconstruct.library.materials.stats.IRepairableMaterialStats;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.tools.stat.FloatToolStat;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.library.utils.Util;

import javax.annotation.Nonnull;
import java.text.DecimalFormat;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class PlateMaterialStats extends BaseMaterialStats implements IRepairableMaterialStats {

  public static final MaterialStatsId ID =
      new MaterialStatsId(ConstructsArmoryReport.getResource("plate"));
  public static final PlateMaterialStats DEFAULT = new PlateMaterialStats();

  public static final DecimalFormat PERCENT_FORMAT = new DecimalFormat("#.##%");

  private static final List<Component> DESCRIPTION =
      ImmutableList.of(new TranslatableComponent(
              "tool_stat." + ConstructsArmoryReport.MOD_ID + ".durability.description"),
          new TranslatableComponent(
              "tool_stat." + ConstructsArmoryReport.MOD_ID + ".armor.description"),
          ToolStats.ARMOR_TOUGHNESS.getDescription(),
          ToolStats.KNOCKBACK_RESISTANCE.getDescription(),
          ConstructsArmoryStats.MOVEMENT_SPEED.getDescription());

  private int durability;
  private float armor;
  private float toughness;
  private float knockbackResistance;
  private float movementSpeed;

  public PlateMaterialStats(FriendlyByteBuf buffer) {
    this.durability = buffer.readInt();
    this.armor = buffer.readFloat();
    this.toughness = buffer.readFloat();
    this.knockbackResistance = buffer.readFloat();
    this.movementSpeed = buffer.readFloat();
  }

  @Override
  public void encode(FriendlyByteBuf buffer) {
    buffer.writeInt(this.durability);
    buffer.writeFloat(this.armor);
    buffer.writeFloat(this.toughness);
    buffer.writeFloat(this.knockbackResistance);
    buffer.writeFloat(this.movementSpeed);
  }

  @Nonnull
  @Override
  public MaterialStatsId getIdentifier() {
    return ID;
  }

  @Override
  @Nonnull
  public List<Component> getLocalizedInfo() {
    List<Component> info = Lists.newArrayList();
    int[] durabilities = ArmorStatsCalculator.getDurabilityStats(this.durability);
    info.add(formatArray(ToolStats.DURABILITY, durabilities[1], durabilities[3], durabilities[2],
        durabilities[0]));
    float[] armors = ArmorStatsCalculator.getArmorStats(this.armor);
    info.add(formatArray(ToolStats.ARMOR, armors[1], armors[3], armors[2], armors[0]));
    info.add(ToolStats.ARMOR_TOUGHNESS.formatValue(this.toughness));
    info.add(ToolStats.KNOCKBACK_RESISTANCE.formatValue(this.knockbackResistance * 10f));
    info.add(new TranslatableComponent(
        "tool_stat." + ConstructsArmoryReport.MOD_ID + ".movement_speed").append(
        new TextComponent(PERCENT_FORMAT.format(this.movementSpeed)).withStyle(
            style -> style.withColor(ConstructsArmoryStats.MOVEMENT_SPEED.getColor()))));
    return info;
  }

  public Component formatArray(FloatToolStat toolStat, float num1, float num2, float num3,
                                    float num4) {
    String name = toolStat.getName().getPath();
    TextColor color = toolStat.getColor();
    String loc = "tool_stat." + ConstructsArmoryReport.MOD_ID + "." + name;
    return new TranslatableComponent(loc).append(
        new TextComponent(Util.COMMA_FORMAT.format(num1) + "/").withStyle(
            style -> style.withColor(color))).append(
        new TextComponent(Util.COMMA_FORMAT.format(num2) + "/").withStyle(
            style -> style.withColor(color))).append(
        new TextComponent(Util.COMMA_FORMAT.format(num3) + "/").withStyle(
            style -> style.withColor(color))).append(
        new TextComponent(Util.COMMA_FORMAT.format(num4)).withStyle(
            style -> style.withColor(color)));
  }

  @Override
  @Nonnull
  public List<Component> getLocalizedDescriptions() {
    return DESCRIPTION;
  }
}
