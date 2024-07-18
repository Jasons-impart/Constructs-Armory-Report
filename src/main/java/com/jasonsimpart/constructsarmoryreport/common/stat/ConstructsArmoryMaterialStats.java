package com.jasonsimpart.constructsarmoryreport.common.stat;

import slimeknights.tconstruct.library.materials.IMaterialRegistry;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import com.jasonsimpart.constructsarmoryreport.common.stat.impl.PlateMaterialStats;
import com.jasonsimpart.constructsarmoryreport.common.stat.impl.MailMaterialStats;

public class ConstructsArmoryMaterialStats {

  public static void setup() {
    IMaterialRegistry registry = MaterialRegistry.getInstance();
    registry.registerStatType(PlateMaterialStats.DEFAULT, PlateMaterialStats.class, PlateMaterialStats::new);
    registry.registerStatType(MailMaterialStats.DEFAULT, MailMaterialStats.class, MailMaterialStats::new);
  }
}
