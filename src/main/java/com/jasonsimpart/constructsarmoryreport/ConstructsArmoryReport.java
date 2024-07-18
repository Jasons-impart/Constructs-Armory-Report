package com.jasonsimpart.constructsarmoryreport;

import com.jasonsimpart.constructsarmoryreport.client.ConstructsArmoryClient;
import com.jasonsimpart.constructsarmoryreport.common.ConstructsArmoryEffects;
import com.jasonsimpart.constructsarmoryreport.common.ConstructsArmoryEvents;
import com.jasonsimpart.constructsarmoryreport.common.ConstructsArmoryItems;
import com.jasonsimpart.constructsarmoryreport.common.ConstructsArmoryReportifiers;
import com.jasonsimpart.constructsarmoryreport.common.stat.ConstructsArmoryMaterialStats;
import com.jasonsimpart.constructsarmoryreport.common.stat.ConstructsArmoryStats;
import com.jasonsimpart.constructsarmoryreport.data.ArmorDefinitionDataProvider;
import com.jasonsimpart.constructsarmoryreport.data.ArmorMaterialDataProvider;
import com.jasonsimpart.constructsarmoryreport.data.ArmorMaterialSpriteProvider;
import com.jasonsimpart.constructsarmoryreport.data.ArmorMaterialStatsDataProvider;
import com.jasonsimpart.constructsarmoryreport.data.ArmorMaterialTraitsDataProvider;
import com.jasonsimpart.constructsarmoryreport.data.ArmorPartSpriteProvider;
import com.jasonsimpart.constructsarmoryreport.data.ArmorRecipeProvider;
import com.jasonsimpart.constructsarmoryreport.data.ArmorSlotLayoutProvider;
import com.jasonsimpart.constructsarmoryreport.data.ArmorTagProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimeknights.tconstruct.common.data.tags.BlockTagProvider;
import slimeknights.tconstruct.library.client.data.material.GeneratorPartTextureJsonGenerator;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;

@Mod(ConstructsArmoryReport.MOD_ID)
public class ConstructsArmoryReport {
  public static final String MOD_ID = "constructsarmoryreportreport";
  public static final Logger LOGGER = LogManager.getLogger();

  public ConstructsArmoryReport() {
    IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
    eventBus.addListener(this::setup);
    eventBus.addListener(this::gatherData);
    ConstructsArmoryItems.init();
    ConstructsArmoryReportifiers.init();
    ConstructsArmoryStats.init();
    ConstructsArmoryEffects.init();
    DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ConstructsArmoryClient::init);
  }

  private void setup(final FMLCommonSetupEvent evt) {
    ConstructsArmoryEvents.setup();
    evt.enqueueWork(ConstructsArmoryMaterialStats::setup);
  }

  private void gatherData(final GatherDataEvent evt) {
    ExistingFileHelper existingFileHelper = evt.getExistingFileHelper();
    DataGenerator generator = evt.getGenerator();

    if (evt.includeServer()) {
      BlockTagsProvider blockTags = new BlockTagProvider(generator, existingFileHelper);
      AbstractMaterialDataProvider materials = new ArmorMaterialDataProvider(generator);
      generator.addProvider(true, materials);
      generator.addProvider(true, new ArmorMaterialStatsDataProvider(generator, materials));
      generator.addProvider(true, new ArmorMaterialTraitsDataProvider(generator, materials));
      generator.addProvider(true, new ArmorRecipeProvider(generator));
      generator.addProvider(true, new ArmorDefinitionDataProvider(generator));
      generator.addProvider(true, new ArmorSlotLayoutProvider(generator));
      generator.addProvider(true, new ArmorTagProvider(generator, blockTags, existingFileHelper));
    }

    if (evt.includeClient()) {
      ArmorPartSpriteProvider armorPartSpriteProvider = new ArmorPartSpriteProvider();
      generator.addProvider(true,
              new GeneratorPartTextureJsonGenerator(generator, ConstructsArmoryReport.MOD_ID,
              armorPartSpriteProvider));
      generator.addProvider(true,
              new MaterialPartTextureGenerator(generator, existingFileHelper, armorPartSpriteProvider,
              new ArmorMaterialSpriteProvider()));
    }
  }

  public static ResourceLocation getResource(String id) {
    return new ResourceLocation(MOD_ID, id);
  }

  public static <T> TinkerDataCapability.TinkerDataKey<T> createKey(String name) {
    return TinkerDataCapability.TinkerDataKey.of(getResource(name));
  }
}
