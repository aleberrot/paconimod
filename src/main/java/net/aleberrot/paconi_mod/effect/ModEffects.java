package net.aleberrot.paconi_mod.effect;

import net.aleberrot.paconi_mod.PaconiMod;
import net.aleberrot.paconi_mod.effect.effects.CacaitoEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static  final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, PaconiMod.MOD_ID);

    public  static  final DeferredHolder<MobEffect, MobEffect> CACAITO_EFFECT = MOB_EFFECTS.register("cacaito",
            () -> new CacaitoEffect(
                    MobEffectCategory.BENEFICIAL,
                    0x964B00
            ).addAttributeModifier(Attributes.MOVEMENT_SPEED,
                    ResourceLocation.fromNamespaceAndPath(PaconiMod.MOD_ID, "cacaito"), 0.7F,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE,
                            ResourceLocation.fromNamespaceAndPath(PaconiMod.MOD_ID, "cacaito"
                            ), -0.55F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static  void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }

}
