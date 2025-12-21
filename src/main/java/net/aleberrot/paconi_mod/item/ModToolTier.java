package net.aleberrot.paconi_mod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTier {
    // Registramos una tier para el cobre, un poco mejor que la piedra
    public static  final Tier COPPER_TIER = new SimpleTier(
            BlockTags.INCORRECT_FOR_STONE_TOOL,
            200,
            5.0F,
            1.5F,
            10,
            // Indica que se puede reparar con lingotes de cobre en el yunque
            () -> Ingredient.of(Items.COPPER_INGOT)
    );
}
