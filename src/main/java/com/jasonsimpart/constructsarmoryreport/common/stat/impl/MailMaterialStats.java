package com.jasonsimpart.constructsarmoryreport.common.stat.impl;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.materials.stats.BaseMaterialStats;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.tools.stat.IToolStat;
import com.jasonsimpart.constructsarmoryreport.ConstructsArmoryReport;
import com.jasonsimpart.constructsarmoryreport.api.ArmorMaterialStatsIdentifiers;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@With
public class MailMaterialStats extends BaseMaterialStats {

  public static final MaterialStatsId ID =
      new MaterialStatsId(ConstructsArmoryReport.getResource("mail"));
  public static final MailMaterialStats DEFAULT = new MailMaterialStats();

  private static final String DURABILITY_PREFIX =
      makeTooltipKey(TConstruct.getResource("durability"));
  private static final String ARMOR_PREFIX =
      makeTooltipKey(TConstruct.getResource("armor"));
  private static final String MOVEMENT_SPEED_PREFIX =
      makeTooltipKey(ConstructsArmoryReport.getResource("movement_speed"));

  private static final Component DURABILITY_DESCRIPTION =
      makeTooltip(ConstructsArmoryReport.getResource("mail.durability.description"));
  private static final Component ARMOR_DESCRIPTION =
      makeTooltip(ConstructsArmoryReport.getResource("mail.armor.description"));
  private static final Component MOVEMENT_SPEED_DESCRIPTION =
      makeTooltip(ConstructsArmoryReport.getResource("mail.movement_speed.description"));
  private static final List<Component> DESCRIPTION =
      ImmutableList.of(DURABILITY_DESCRIPTION, ARMOR_DESCRIPTION, MOVEMENT_SPEED_DESCRIPTION);

  private float durability = 1.0f;
  private float armor = 1.0f;
  private float movementSpeed = 1.0f;

  public MailMaterialStats(FriendlyByteBuf buffer) {
    this.durability = buffer.readFloat();
    this.armor = buffer.readFloat();
    this.movementSpeed = buffer.readFloat();
  }

  @Override
  public void encode(FriendlyByteBuf buffer) {
    buffer.writeFloat(this.durability);
    buffer.writeFloat(this.armor);
    buffer.writeFloat(this.movementSpeed);
  }

  @Override
  @Nonnull
  public MaterialStatsId getIdentifier() {
    return ArmorMaterialStatsIdentifiers.MAIL;
  }

  @Override
  @Nonnull
  public List<Component> getLocalizedInfo() {
    List<Component> list = new ArrayList<>();
    list.add(formatDurability(this.durability));
    list.add(formatArmor(this.armor));
    list.add(formatMovementSpeed(this.movementSpeed));
    return list;
  }

  @Override
  @Nonnull
  public List<Component> getLocalizedDescriptions() {
    return DESCRIPTION;
  }

  public static Component formatDurability(float durability) {
    return IToolStat.formatColoredMultiplier(DURABILITY_PREFIX, durability);
  }

  public static Component formatArmor(float armor) {
    return IToolStat.formatColoredMultiplier(ARMOR_PREFIX, armor);
  }

  public static Component formatMovementSpeed(float movementSpeed) {
    return IToolStat.formatColoredMultiplier(MOVEMENT_SPEED_PREFIX, movementSpeed);
  }
}
