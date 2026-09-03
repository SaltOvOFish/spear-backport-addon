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

@EventBusSubscriber(modid = SpearBackportAddon.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CreativeTabEvents {

    private static Item spear(String name) {
        return BuiltInRegistries.ITEM.get(ResourceLocation.parse("minecraft:" + name));
    }

    private static void insert(BuildCreativeModeTabContentsEvent event, Item before, Item item) {
        if (item == null) return;
        event.insertAfter(new ItemStack(before), new ItemStack(item), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    @SubscribeEvent
    public static void onCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            if (SpearBackportAddon.CANDC_COPPER_SPEAR != null)
                insert(event, spear("stone_spear"), SpearBackportAddon.CANDC_COPPER_SPEAR.get());
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
