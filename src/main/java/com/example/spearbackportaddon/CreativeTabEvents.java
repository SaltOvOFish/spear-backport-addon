package com.example.spearbackportaddon;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;

@EventBusSubscriber(modid = SpearBackportAddon.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CreativeTabEvents {

    private static Item spear(String name) {
        return BuiltInRegistries.ITEM.get(ResourceLocation.parse("minecraft:" + name));
    }

    private static void insert(BuildCreativeModeTabContentsEvent event, Item before, Item item) {
        if (item == null) return;
        event.insertAfter(new ItemStack(before), new ItemStack(item), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    private static Item insertVariant(BuildCreativeModeTabContentsEvent event, Item prev, DeferredItem<Item> item) {
        if (item == null) return prev;
        insert(event, prev, item.get());
        return item.get();
    }

    @SubscribeEvent
    public static void onCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            if (SpearBackportAddon.CANDC_COPPER_SPEAR != null) {
                insert(event, spear("stone_spear"), SpearBackportAddon.CANDC_COPPER_SPEAR.get());
                if (SpearConfig.COPPER_VARIANTS_IN_TAB.get()) {
                    Item prev = SpearBackportAddon.CANDC_COPPER_SPEAR.get();
                    prev = insertVariant(event, prev, SpearBackportAddon.CANDC_EXPOSED_COPPER_SPEAR);
                    prev = insertVariant(event, prev, SpearBackportAddon.CANDC_WEATHERED_COPPER_SPEAR);
                    prev = insertVariant(event, prev, SpearBackportAddon.CANDC_OXIDIZED_COPPER_SPEAR);
                    prev = insertVariant(event, prev, SpearBackportAddon.CANDC_WAXED_COPPER_SPEAR);
                    prev = insertVariant(event, prev, SpearBackportAddon.CANDC_WAXED_EXPOSED_COPPER_SPEAR);
                    prev = insertVariant(event, prev, SpearBackportAddon.CANDC_WAXED_WEATHERED_COPPER_SPEAR);
                    prev = insertVariant(event, prev, SpearBackportAddon.CANDC_WAXED_OXIDIZED_COPPER_SPEAR);
                }
            }
            if (SpearBackportAddon.OREGANIZED_SILVER_SPEAR != null)
                insert(event, spear("iron_spear"), SpearBackportAddon.OREGANIZED_SILVER_SPEAR.get());
            if (SpearBackportAddon.CANDC_SILVER_SPEAR != null) {
                Item before = (SpearBackportAddon.OREGANIZED_SILVER_SPEAR != null)
                        ? SpearBackportAddon.OREGANIZED_SILVER_SPEAR.get()
                        : spear("iron_spear");
                insert(event, before, SpearBackportAddon.CANDC_SILVER_SPEAR.get());
            }
            if (SpearBackportAddon.CANDC_NECROMIUM_SPEAR != null)
                insert(event, spear("netherite_spear"), SpearBackportAddon.CANDC_NECROMIUM_SPEAR.get());
        }
    }
}
