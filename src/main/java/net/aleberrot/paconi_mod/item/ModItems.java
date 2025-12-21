package net.aleberrot.paconi_mod.item;

import net.aleberrot.paconi_mod.PaconiMod;
import net.aleberrot.paconi_mod.item.custom.HammerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static  final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PaconiMod.MOD_ID);

    // add hammers
    public  static  final DeferredItem<HammerItem> STONE_HAMMER = ITEMS.register(
            "stone_hammer",
            () -> new HammerItem(Tiers.STONE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(Tiers.STONE, 4F, -3.6F)))
    );

    public static final DeferredItem<HammerItem> COPPER_HAMMER = ITEMS.register(
            "copper_hammer",
            () -> new HammerItem(ModToolTier.COPPER_TIER, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(Tiers.STONE, 4.8F, -3.5F)))
    );

    public static  final DeferredItem<HammerItem> IRON_HAMMER = ITEMS.register(
            "iron_hammer",
            () -> new HammerItem(Tiers.IRON, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(Tiers.IRON, 5.8F, -3.2F)))
    );

    public static final DeferredItem<HammerItem> GOLD_HAMMER = ITEMS.register(
            "gold_hammer",
            () -> new HammerItem(Tiers.GOLD, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(Tiers.GOLD, 5F, -2.7F)))
    );

    public static final DeferredItem<HammerItem> DIAMOND_HAMMER = ITEMS.register(
            "diamond_hammer",
            () -> new HammerItem(Tiers.DIAMOND, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(Tiers.DIAMOND, 7.5F, -2.9F)))
    );

    public static  final DeferredItem<HammerItem> NETHERITE_HAMMER = ITEMS.register(
            "netherite_hammer",
            () -> new HammerItem(Tiers.NETHERITE, new Item.Properties()
                    // Resistencia al fuego
                    .fireResistant()
                    .attributes(PickaxeItem.createAttributes(Tiers.NETHERITE, 8.8F, -2.5F)))
    );

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}

