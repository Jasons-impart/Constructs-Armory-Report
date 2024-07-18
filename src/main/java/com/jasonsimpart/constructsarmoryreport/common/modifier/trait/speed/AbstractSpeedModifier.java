package com.jasonsimpart.constructsarmoryreport.common.modifier.trait.speed;

import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.EquipmentChangeContext;
import com.jasonsimpart.constructsarmoryreport.common.modifier.EquipmentUtil;
import com.jasonsimpart.constructsarmoryreport.common.modifier.IArmorUpdateModifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public abstract class AbstractSpeedModifier extends Modifier implements IArmorUpdateModifier {

  @Override
  public void onUnequip(IToolStackView tool, int level,
                        EquipmentChangeContext context) {
    LivingEntity livingEntity = context.getEntity();
    IToolStackView newTool = context.getReplacementTool();

    if (newTool == null || newTool.isBroken() || newTool.getModifierLevel(this) != level) {
      AttributeInstance attribute = livingEntity.getAttribute(Attributes.MOVEMENT_SPEED);

      if (attribute != null) {
        attribute.removeModifier(EquipmentUtil.getUuid(getId(), context.getChangedSlot()));
      }
    }
  }

  @Nullable
  @Override
  public <T> T getModule(@Nonnull Class<T> type) {
    return tryModuleMatch(type, IArmorUpdateModifier.class, this);
  }

  @Override
  public void onUpdate(IToolStackView armor, EquipmentSlot slotType, int level,
                       LivingEntity living) {

    if (living.level.isClientSide()) {
      return;
    }
    AttributeInstance attribute = living.getAttribute(Attributes.MOVEMENT_SPEED);

    if (attribute == null) {
      return;
    }
    UUID uuid = EquipmentUtil.getUuid(getId(), slotType);
    attribute.removeModifier(uuid);

    if (!armor.isBroken()) {
      applyBoost(armor, slotType, attribute, uuid, level, living);
    }
  }

  protected abstract void applyBoost(IToolStackView armor, EquipmentSlot slotType,
                                     AttributeInstance attribute, UUID uuid, int level,
                                     LivingEntity living);
}
