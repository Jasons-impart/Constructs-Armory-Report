package com.jasonsimpart.constructsarmoryreport.common;

import net.minecraft.world.damagesource.CombatRules;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.definition.ModifiableArmorMaterial;
import slimeknights.tconstruct.library.tools.helper.ArmorUtil;
import com.jasonsimpart.constructsarmoryreport.common.modifier.IArmorUpdateModifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class ConstructsArmoryEvents {

  public static void setup() {
    MinecraftForge.EVENT_BUS.addListener(ConstructsArmoryEvents::livingUpdate);
    MinecraftForge.EVENT_BUS.addListener(ConstructsArmoryEvents::livingHurt);
  }

  private static void livingHurt(final LivingHurtEvent evt) {
    LivingEntity living = evt.getEntityLiving();
    EquipmentContext context = new EquipmentContext(living);

    if (!context.hasModifiableArmor()) {
      return;
    }

    if (!evt.getSource().isBypassArmor()) {
      AttributeInstance armorAtt = living.getAttribute(Attributes.ARMOR);
      float armor = 0;

      if (armorAtt != null) {
        armor = (float) armorAtt.getValue();
      }

      if (armor % 1 == 0) {
        return;
      }
      AttributeInstance toughnessAtt = living.getAttribute(Attributes.ARMOR_TOUGHNESS);
      float toughness = 0;

      if (toughnessAtt != null) {
        toughness = (float) toughnessAtt.getValue();
      }
      float damage = CombatRules.getDamageAfterAbsorb(evt.getAmount(), armor, toughness);
      evt.setAmount(
          ArmorUtil.getDamageBeforeArmorAbsorb(damage, living.getArmorValue(), toughness));
    }
  }

  private static void livingUpdate(final LivingEvent.LivingUpdateEvent evt) {
    LivingEntity living = evt.getEntityLiving();

    if (living.isSpectator()) {
      return;
    }
    EquipmentContext context = new EquipmentContext(living);

    if (!context.hasModifiableArmor()) {
      return;
    }

    if (!living.level.isClientSide() && living.isAlive() && living.tickCount % 20 == 0) {

      for (EquipmentSlot slotType : ModifiableArmorMaterial.ARMOR_SLOTS) {
        IToolStackView armor = context.getToolInSlot(slotType);

        if (armor != null) {

          for (ModifierEntry entry : armor.getModifierList()) {
            IArmorUpdateModifier hook = entry.getModifier().getModule(IArmorUpdateModifier.class);

            if (hook != null) {
              hook.onUpdate(armor, slotType, entry.getLevel(), living);
            }
          }
        }
      }
    }
  }
}
