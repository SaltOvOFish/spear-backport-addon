package com.example.spearbackportaddon;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class CopperWaxingRecipe extends CustomRecipe {

    public CopperWaxingRecipe(CraftingBookCategory category) {
        super(category);
    }

    // 判断是否是涂蜡材料（蜜脾或锡板）
    private static boolean isWaxMaterial(ItemStack stack) {
        Item item = stack.getItem();
        if (item == Items.HONEYCOMB) return true;
        Item tinplate = BuiltInRegistries.ITEM.get(ResourceLocation.parse("caverns_and_chasms:tinplate"));
        return item == tinplate;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if (!SpearConfig.COPPER_OXIDATION.get()) return false;
        int spearCount = 0;
        int waxCount = 0;
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.isEmpty()) continue;
            if (CopperOxidation.getWaxed(stack.getItem()) != null) {
                spearCount++;
            } else if (isWaxMaterial(stack)) {
                waxCount++;
            } else {
                return false;
            }
            if (spearCount > 1 || waxCount > 1) return false;
        }
        return spearCount == 1 && waxCount == 1;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack spearStack = ItemStack.EMPTY;
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (!stack.isEmpty() && CopperOxidation.getWaxed(stack.getItem()) != null) {
                spearStack = stack;
                break;
            }
        }
        Item waxed = CopperOxidation.getWaxed(spearStack.getItem());
        ItemStack result = new ItemStack(waxed, spearStack.getCount());
        result.applyComponents(spearStack.getComponentsPatch()); // 保留附魔、耐久等
        return result;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SpearBackportAddon.COPPER_WAXING_SERIALIZER.get();
    }
}
