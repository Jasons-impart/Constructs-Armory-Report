package com.jasonsimpart.constructsarmoryreport.data;

import javax.annotation.Nonnull;

import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.library.client.data.material.AbstractPartSpriteProvider;
import com.jasonsimpart.constructsarmoryreport.ConstructsArmoryReport;
import com.jasonsimpart.constructsarmoryreport.api.ArmorMaterialStatsIdentifiers;

public class ArmorPartSpriteProvider extends AbstractPartSpriteProvider {

  public ArmorPartSpriteProvider() {
    super(ConstructsArmoryReport.MOD_ID);
  }

  @Nonnull
  @Override
  public String getName() {
    return "Construct's Armory Part Textures";
  }

  @Override
  protected void addAllSpites() {
    addPlate("body_plate");
    addPlate("head_plate");
    addPlate("legs_plate");
    addPlate("feet_plate");
    addPart("mail", ArmorMaterialStatsIdentifiers.MAIL);

    buildArmor("helmet").addBreakablePart("head_plate", ArmorMaterialStatsIdentifiers.PLATE)
        .addPart("mail", ArmorMaterialStatsIdentifiers.MAIL);
    buildArmor("chestplate").addBreakablePart("body_plate", ArmorMaterialStatsIdentifiers.PLATE)
        .addPart("mail", ArmorMaterialStatsIdentifiers.MAIL);
    buildArmor("leggings").addBreakablePart("legs_plate", ArmorMaterialStatsIdentifiers.PLATE)
        .addPart("mail", ArmorMaterialStatsIdentifiers.MAIL);
    buildArmor("boots").addBreakablePart("feet_plate", ArmorMaterialStatsIdentifiers.PLATE)
        .addPart("mail", ArmorMaterialStatsIdentifiers.MAIL);

    addTexture("models/armor/material_armor_plate_layer_1", ArmorMaterialStatsIdentifiers.PLATE);
    addTexture("models/armor/material_armor_plate_layer_2", ArmorMaterialStatsIdentifiers.PLATE);
    addTexture("models/armor/material_armor_mail_layer_1", ArmorMaterialStatsIdentifiers.MAIL);
    addTexture("models/armor/material_armor_mail_layer_2", ArmorMaterialStatsIdentifiers.MAIL);
  }

  protected void addPlate(String name) {
    addPart(name, ArmorMaterialStatsIdentifiers.PLATE);
  }

  protected ToolSpriteBuilder buildArmor(String name) {
    return buildTool(new ResourceLocation(ConstructsArmoryReport.MOD_ID, name));
  }
}
