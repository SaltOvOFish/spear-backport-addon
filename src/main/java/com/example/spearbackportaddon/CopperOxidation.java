package com.example.spearbackportaddon;

import net.minecraft.world.item.Item;

public class CopperOxidation {

    // 返回下一个氧化阶段；不可氧化返回 null
    public static Item getNext(Item item) {
        if (SpearBackportAddon.CANDC_COPPER_SPEAR == null) return null;
        if (item == SpearBackportAddon.CANDC_COPPER_SPEAR.get()) return SpearBackportAddon.CANDC_EXPOSED_COPPER_SPEAR.get();
        if (item == SpearBackportAddon.CANDC_EXPOSED_COPPER_SPEAR.get()) return SpearBackportAddon.CANDC_WEATHERED_COPPER_SPEAR.get();
        if (item == SpearBackportAddon.CANDC_WEATHERED_COPPER_SPEAR.get()) return SpearBackportAddon.CANDC_OXIDIZED_COPPER_SPEAR.get();
        return null;
    }

    // 返回涂蜡版本；不可涂蜡返回 null
    public static Item getWaxed(Item item) {
        if (SpearBackportAddon.CANDC_COPPER_SPEAR == null) return null;
        if (item == SpearBackportAddon.CANDC_COPPER_SPEAR.get()) return SpearBackportAddon.CANDC_WAXED_COPPER_SPEAR.get();
        if (item == SpearBackportAddon.CANDC_EXPOSED_COPPER_SPEAR.get()) return SpearBackportAddon.CANDC_WAXED_EXPOSED_COPPER_SPEAR.get();
        if (item == SpearBackportAddon.CANDC_WEATHERED_COPPER_SPEAR.get()) return SpearBackportAddon.CANDC_WAXED_WEATHERED_COPPER_SPEAR.get();
        if (item == SpearBackportAddon.CANDC_OXIDIZED_COPPER_SPEAR.get()) return SpearBackportAddon.CANDC_WAXED_OXIDIZED_COPPER_SPEAR.get();
        return null;
    }
}
