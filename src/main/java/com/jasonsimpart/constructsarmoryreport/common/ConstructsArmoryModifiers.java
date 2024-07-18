package com.jasonsimpart.constructsarmoryreport.common;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;
import slimeknights.tconstruct.tools.modifiers.traits.general.DenseModifier;
import slimeknights.tconstruct.tools.modifiers.traits.general.OvercastModifier;
import slimeknights.tconstruct.tools.modifiers.traits.general.OvergrowthModifier;
import slimeknights.tconstruct.tools.modifiers.traits.general.OverlordModifier;
import slimeknights.tconstruct.tools.modifiers.traits.general.OverworkedModifier;
import slimeknights.tconstruct.tools.modifiers.traits.general.SolarPoweredModifier;
import com.jasonsimpart.constructsarmoryreport.ConstructsArmoryReport;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.battle.HallowedModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.battle.IgneousModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.battle.PetrousModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.battle.StableModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.battle.VengefulModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.battle.WeightyModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.battle.counter.BlightedModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.battle.counter.BloodlettingModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.battle.counter.CounterattackModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.battle.counter.EnkindlingModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.battle.counter.MalignantModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.battle.counter.PricklyModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.battle.counter.SplinteredModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.general.DuctileModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.general.EndershieldModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.general.ExperiencedModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.general.SavoryModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.general.ShieldingModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.general.StoneguardModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.speed.AccelerationModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.speed.AerialModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.speed.DelvingModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.speed.FerventModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.speed.ImmaculateModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.speed.ImmaculateModifier2;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.speed.NimbleModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.speed.RadiantModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.speed.SalvagedModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.trait.speed.WovenModifier;
import slimeknights.tconstruct.tools.modifiers.upgrades.general.ReinforcedModifier;

public class ConstructsArmoryReportifiers {

  private static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(ConstructsArmoryReport.MOD_ID);

  // Tier 1
  public static final StaticModifier<WovenModifier> WOVEN =
      MODIFIERS.register("woven", WovenModifier::new);
  public static final StaticModifier<PetrousModifier> PETROUS =
      MODIFIERS.register("petrous", PetrousModifier::new);
  public static final StaticModifier<CounterattackModifier> SPLINTERED =
      MODIFIERS.register("splintered", SplinteredModifier::new);
  public static final StaticModifier<SolarPoweredModifier> SOLAR_POWERED =
      MODIFIERS.register("solar_powered", SolarPoweredModifier::new);

  // Tier 2
  public static final StaticModifier<ReinforcedModifier> REINFORCED =
      MODIFIERS.register("reinforced", ReinforcedModifier::new);
  public static final StaticModifier<DelvingModifier> DELVING =
      MODIFIERS.register("delving", DelvingModifier::new);
  public static final StaticModifier<OvergrowthModifier> OVERGROWTH =
      MODIFIERS.register("overgrowth", OvergrowthModifier::new);
  public static final StaticModifier<IgneousModifier> IGNEOUS =
      MODIFIERS.register("igneous", IgneousModifier::new);
  public static final StaticModifier<AerialModifier> AERIAL =
      MODIFIERS.register("aerial", AerialModifier::new);
  public static final StaticModifier<BloodlettingModifier> BLOODLETTING =
      MODIFIERS.register("bloodletting", BloodlettingModifier::new);
  public static final StaticModifier<MalignantModifier> MALIGNANT =
      MODIFIERS.register("malignant", MalignantModifier::new);

  // Tier 2 Addons
  public static final StaticModifier<DenseModifier> DENSE =
      MODIFIERS.register("dense", DenseModifier::new);
  public static final StaticModifier<WeightyModifier> WEIGHTY =
      MODIFIERS.register("weighty", WeightyModifier::new);
  public static final StaticModifier<RadiantModifier> RADIANT =
      MODIFIERS.register("radiant", RadiantModifier::new);
  public static final StaticModifier<HallowedModifier> HALLOWED =
      MODIFIERS.register("hallowed", HallowedModifier::new);
  public static final StaticModifier<ShieldingModifier> SHIELDING =
      MODIFIERS.register("shielding", ShieldingModifier::new);
  public static final StaticModifier<StoneguardModifier> STONEGUARD =
      MODIFIERS.register("stoneguard", StoneguardModifier::new);

  // Tier 3
  public static final StaticModifier<OvercastModifier> OVERCAST =
      MODIFIERS.register("overcast", OvercastModifier::new);
  public static final StaticModifier<ImmaculateModifier> IMMACULATE =
      MODIFIERS.register("immaculate", ImmaculateModifier::new);
  public static final StaticModifier<NimbleModifier> NIMBLE =
      MODIFIERS.register("nimble", NimbleModifier::new);
  public static final StaticModifier<PricklyModifier> PRICKLY =
      MODIFIERS.register("prickly", PricklyModifier::new);
  public static final StaticModifier<SavoryModifier> SAVORY =
      MODIFIERS.register("savory", SavoryModifier::new);

  // Tier 3 Addons
  public static final StaticModifier<DuctileModifier> DUCTILE =
      MODIFIERS.register("ductile", DuctileModifier::new);
  public static final StaticModifier<ImmaculateModifier2> IMMACULATE2 =
      MODIFIERS.register("immaculate_2", ImmaculateModifier2::new);
  public static final StaticModifier<StableModifier> STABLE =
      MODIFIERS.register("stable", StableModifier::new);
  public static final StaticModifier<FerventModifier> FERVENT =
      MODIFIERS.register("fervent", FerventModifier::new);
  public static final StaticModifier<ExperiencedModifier> EXPERIENCED =
      MODIFIERS.register("experienced", ExperiencedModifier::new);
  public static final StaticModifier<CounterattackModifier> BLIGHTED =
      MODIFIERS.register("blighted", BlightedModifier::new);
  public static final StaticModifier<OverworkedModifier> OVERWORKED =
      MODIFIERS.register("overworked", OverworkedModifier::new);

  // Tier 4
  public static final StaticModifier<OverlordModifier> OVERLORD =
      MODIFIERS.register("overlord", OverlordModifier::new);
  public static final StaticModifier<SalvagedModifier> SALVAGED =
      MODIFIERS.register("salvaged", SalvagedModifier::new);
  public static final StaticModifier<AccelerationModifier> ACCELERATION =
      MODIFIERS.register("acceleration", AccelerationModifier::new);
  public static final StaticModifier<VengefulModifier> VENGEFUL =
      MODIFIERS.register("vengeful", VengefulModifier::new);
  public static final StaticModifier<CounterattackModifier> ENKINDLING =
      MODIFIERS.register("enkindling", EnkindlingModifier::new);

  // Tier 5
  public static final StaticModifier<EndershieldModifier> ENDERSHIELD =
      MODIFIERS.register("endershield", EndershieldModifier::new);

  public static void init() {
    IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    MODIFIERS.register(bus);
  }
}
