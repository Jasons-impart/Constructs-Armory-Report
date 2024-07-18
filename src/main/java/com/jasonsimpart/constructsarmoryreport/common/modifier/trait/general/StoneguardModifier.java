package com.jasonsimpart.constructsarmoryreport.common.modifier.trait.general;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.impl.DurabilityShieldModifier;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.definition.ModifiableArmorMaterial;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.modifiers.traits.general.StoneshieldModifier;
import com.jasonsimpart.constructsarmoryreport.common.ConstructsArmoryReportifiers;

/**
 * Modified copy of {@link StoneshieldModifier} from Tinkers' Construct
 * MIT License (c) SlimeKnights
 */
public class StoneguardModifier extends DurabilityShieldModifier {

  public StoneguardModifier() {
    MinecraftForge.EVENT_BUS.addListener(StoneguardModifier::onItemPickup);
  }

  @Override
  protected int getShieldCapacity(IToolStackView tool, int level) {
    return (int) (level * 100 * tool.getMultiplier(ToolStats.DURABILITY));
  }

  @Override
  public int getPriority() {
    return 175;
  }

  private static void onItemPickup(final EntityItemPickupEvent evt) {
    Player player = evt.getPlayer();

    if (player.isSpectator()) {
      return;
    }
    EquipmentContext context = new EquipmentContext(player);

    if (!context.hasModifiableArmor()) {
      return;
    }
    ItemStack stack = evt.getItem().getItem();

    if (!stack.is(TinkerTags.Items.STONESHIELDS)) {
      return;
    }

    if (!player.level.isClientSide() && player.isAlive()) {

      for (EquipmentSlot slotType : ModifiableArmorMaterial.ARMOR_SLOTS) {
        IToolStackView armor = context.getToolInSlot(slotType);

        if (armor != null && !armor.isBroken()) {

          for (ModifierEntry entry : armor.getModifierList()) {

            if (entry.getModifier() == ConstructsArmoryReportifiers.STONEGUARD.get()) {
              int addedShield = 0;
              int level = entry.getLevel();
              float chance = level * 0.20f;

              if (chance >= 1.0f) {
                addedShield += stack.getCount();
                stack.shrink(stack.getCount());
              } else {
                int reduced = 0;

                for (int i = 0; i < stack.getCount(); i++) {

                  if (RANDOM.nextFloat() < chance) {
                    reduced++;
                  }
                }
                stack.shrink(reduced);
                addedShield += reduced;
              }

              if (addedShield > 0) {
                ((StoneguardModifier) entry.getModifier()).addShield(armor, level, addedShield * 3);
              }

              if (stack.getCount() == 0) {
                return;
              }
            }
          }
        }
      }
    }
  }

  @Nullable
  @Override
  public Boolean showDurabilityBar(@Nonnull IToolStackView tool, int level) {
    return getShield(tool) > 0 ? true : null;
  }

  @Override
  public int getDurabilityRGB(@Nonnull IToolStackView tool, int level) {

    if (getShield(tool) > 0) {
      return 0x7f7f7f;
    }
    return -1;
  }
}
