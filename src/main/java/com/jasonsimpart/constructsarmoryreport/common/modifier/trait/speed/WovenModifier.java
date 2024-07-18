package com.jasonsimpart.constructsarmoryreport.common.modifier.trait.speed;

import java.util.UUID;
import java.util.function.BiConsumer;
import javax.annotation.Nonnull;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.util.Lazy;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.modifiers.traits.DamageSpeedTradeModifier;
import com.jasonsimpart.constructsarmoryreport.common.modifier.EquipmentUtil;

/**
 * Modified copy of {@link DamageSpeedTradeModifier} from Tinkers' Construct
 * MIT License (c) SlimeKnights
 */
public class WovenModifier extends Modifier {

  private final static float MULTIPLIER = 0.005f;
  private final Lazy<String> speedName = Lazy.of(() -> {
    ResourceLocation id = getId();
    return id.getPath() + "." + id.getNamespace() + ".speed";
  });
  private final Lazy<String> armorName = Lazy.of(() -> {
    ResourceLocation id = getId();
    return id.getPath() + "." + id.getNamespace() + ".armor";
  });

  private float getMultiplier(IToolStackView armor, int level) {
    return (float) (Math.sqrt(armor.getDamage() * level / armor.getMultiplier(ToolStats.DURABILITY)) *
        MULTIPLIER);
  }

  @Override
  public void addAttributes(@Nonnull IToolStackView armor, int level,
                            @Nonnull EquipmentSlot slot,
                            @Nonnull BiConsumer<Attribute, AttributeModifier> consumer) {

    if (slot.getType() == EquipmentSlot.Type.ARMOR) {
      float boost = getMultiplier(armor, level);

      if (boost != 0) {
        UUID uuid = EquipmentUtil.getUuid(getId(), slot);
        consumer.accept(Attributes.ARMOR, new AttributeModifier(uuid, armorName.get(), -boost * 2,
            AttributeModifier.Operation.MULTIPLY_TOTAL));
        consumer.accept(Attributes.MOVEMENT_SPEED,
            new AttributeModifier(uuid, speedName.get(), boost / 2,
                AttributeModifier.Operation.MULTIPLY_TOTAL));
      }
    }
  }
}
