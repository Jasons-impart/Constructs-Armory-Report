package com.jasonsimpart.constructsarmoryreport.common;

import java.util.function.IntFunction;
import java.util.function.Supplier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.common.TinkerEffect;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;
import com.jasonsimpart.constructsarmoryreport.ConstructsArmoryReport;

public class ConstructsArmoryEffects {

  protected static final DeferredRegister<MobEffect> POTIONS =
      DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, ConstructsArmoryReport.MOD_ID);

  private static final IntFunction<Supplier<TinkerEffect>> MARKER_EFFECT =
      color -> () -> new NoMilkEffect(MobEffectCategory.BENEFICIAL, color, true);

  public static final RegistryObject<TinkerEffect> VENGEFUL =
      POTIONS.register("vengeful", MARKER_EFFECT.apply(0x9261cc));
  public static final RegistryObject<TinkerEffect> ACCELERATION =
      POTIONS.register("acceleration", MARKER_EFFECT.apply(0x60496b));
  public static final RegistryObject<TinkerEffect> MALIGNANT =
      POTIONS.register("malignant", MARKER_EFFECT.apply(0x4d4d4d));
  public static final RegistryObject<TinkerEffect> BLOODLETTING =
      POTIONS.register("bloodletting", MARKER_EFFECT.apply(0xb30000));

  public static void init() {
    IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    POTIONS.register(bus);
  }
}
