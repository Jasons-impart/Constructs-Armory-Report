package com.jasonsimpart.constructsarmoryreport.data;

import com.jasonsimpart.constructsarmoryreport.common.ConstructsArmoryReportifiers;
import com.jasonsimpart.constructsarmoryreport.common.stat.impl.MailMaterialStats;
import com.jasonsimpart.constructsarmoryreport.common.stat.impl.PlateMaterialStats;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;
import slimeknights.tconstruct.tools.data.material.MaterialIds;

import javax.annotation.Nonnull;

public class ArmorMaterialTraitsDataProvider extends AbstractMaterialTraitDataProvider {

  public ArmorMaterialTraitsDataProvider(DataGenerator gen,
                                         AbstractMaterialDataProvider materials) {
    super(gen, materials);
  }

  @Override
  protected void addMaterialTraits() {
    // Tier 1
    addArmorTraits(MaterialIds.rock, ConstructsArmoryReportifiers.PETROUS);
    addArmorTraits(MaterialIds.leather, ConstructsArmoryReportifiers.WOVEN);
    addArmorTraits(MaterialIds.bone, ConstructsArmoryReportifiers.SPLINTERED);
    addTraits(MaterialIds.vine, MailMaterialStats.ID,
        ConstructsArmoryReportifiers.SOLAR_POWERED);

    // Tier 2
    addArmorTraits(MaterialIds.iron, ConstructsArmoryReportifiers.REINFORCED);
    addArmorTraits(MaterialIds.slimewood, ConstructsArmoryReportifiers.OVERGROWTH);
    addArmorTraits(MaterialIds.copper, ConstructsArmoryReportifiers.DELVING);
    addArmorTraits(MaterialIds.searedStone, ConstructsArmoryReportifiers.IGNEOUS);
    addArmorTraits(MaterialIds.necroticBone, ConstructsArmoryReportifiers.MALIGNANT);
    addArmorTraits(MaterialIds.bloodbone, ConstructsArmoryReportifiers.BLOODLETTING);
    addTraits(MaterialIds.skyslimeVine, MailMaterialStats.ID,
        ConstructsArmoryReportifiers.AERIAL);

    // Tier 2 Addons
    addArmorTraits(MaterialIds.osmium, ConstructsArmoryReportifiers.DENSE);
    addArmorTraits(MaterialIds.tungsten, ConstructsArmoryReportifiers.WEIGHTY);
    addArmorTraits(MaterialIds.platinum, ConstructsArmoryReportifiers.RADIANT);
    addArmorTraits(MaterialIds.silver, ConstructsArmoryReportifiers.HALLOWED);
    addArmorTraits(MaterialIds.lead, ConstructsArmoryReportifiers.SHIELDING);
    addArmorTraits(MaterialIds.whitestone, ConstructsArmoryReportifiers.STONEGUARD);

    // Tier 3
    addArmorTraits(MaterialIds.slimesteel, ConstructsArmoryReportifiers.OVERCAST);
    addArmorTraits(MaterialIds.bronze, ConstructsArmoryReportifiers.IMMACULATE);
    addArmorTraits(MaterialIds.cobalt, ConstructsArmoryReportifiers.NIMBLE);
    addArmorTraits(MaterialIds.pigIron, ConstructsArmoryReportifiers.SAVORY);
    addArmorTraits(MaterialIds.nahuatl, ConstructsArmoryReportifiers.PRICKLY);

    // Tier 3 Addons
    addArmorTraits(MaterialIds.steel, ConstructsArmoryReportifiers.DUCTILE);
    addArmorTraits(MaterialIds.bronze, ConstructsArmoryReportifiers.IMMACULATE2);
    addArmorTraits(MaterialIds.constantan, ConstructsArmoryReportifiers.FERVENT);
    addArmorTraits(MaterialIds.invar, ConstructsArmoryReportifiers.STABLE);
    addArmorTraits(MaterialIds.necronium, ConstructsArmoryReportifiers.BLIGHTED);
    addArmorTraits(MaterialIds.electrum, ConstructsArmoryReportifiers.EXPERIENCED);
    addArmorTraits(MaterialIds.platedSlimewood, ConstructsArmoryReportifiers.OVERWORKED);

    // Tier 4
    addArmorTraits(MaterialIds.queensSlime, ConstructsArmoryReportifiers.OVERLORD);
    addArmorTraits(MaterialIds.ancientHide, ConstructsArmoryReportifiers.SALVAGED);
    addArmorTraits(MaterialIds.hepatizon, ConstructsArmoryReportifiers.ACCELERATION);
    addArmorTraits(MaterialIds.manyullyn, ConstructsArmoryReportifiers.VENGEFUL);
    addArmorTraits(MaterialIds.blazingBone, ConstructsArmoryReportifiers.ENKINDLING);

    // Tier 5
    addTraits(MaterialIds.enderslimeVine, MailMaterialStats.ID,
        ConstructsArmoryReportifiers.ENDERSHIELD);
  }

  protected void addArmorTraits(MaterialId materialId, StaticModifier<? extends Modifier> trait) {
    addTraits(materialId, PlateMaterialStats.ID, trait);
    addTraits(materialId, MailMaterialStats.ID, trait);
  }

  @Override
  @Nonnull
  public String getName() {
    return "Construct's Armory Material Traits";
  }
}
