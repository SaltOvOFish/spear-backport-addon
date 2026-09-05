package com.example.spearbackportaddon;

import com.notunanancyowen.spears.Spears;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Unit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(SpearBackportAddon.MOD_ID)
public class SpearBackportAddon {

    public static final String MOD_ID = "spear_backport_addon";

    private static final DeferredRegister.Items OREGANIZED_ITEMS = DeferredRegister.createItems("oreganized");
    private static final DeferredRegister.Items BETTEREND_ITEMS = DeferredRegister.createItems("betterend");
    private static final DeferredRegister.Items CAVERNS_AND_CHASMS_ITEMS = DeferredRegister.createItems("caverns_and_chasms");
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, MOD_ID);
    private static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<CopperWaxingRecipe>> COPPER_WAXING_SERIALIZER =
            RECIPE_SERIALIZERS.register("crafting_special_copper_waxing", () -> new SimpleCraftingRecipeSerializer<>(CopperWaxingRecipe::new));

    public static DeferredItem<Item> OREGANIZED_SILVER_SPEAR;
    public static DeferredItem<Item> TERMINITE_SPEAR;
    public static DeferredItem<Item> AETERNIUM_SPEAR;
    public static DeferredItem<Item> THALLASIUM_SPEAR;
    public static DeferredItem<Item> THALLASIUM_SPEAR_HEAD;
    public static DeferredItem<Item> TERMINITE_SPEAR_HEAD;
    public static DeferredItem<Item> AETERNIUM_SPEAR_HEAD;
    public static DeferredItem<Item> CANDC_SILVER_SPEAR;
    public static DeferredItem<Item> CANDC_NECROMIUM_SPEAR;

    public static DeferredItem<Item> CANDC_COPPER_SPEAR;
    public static DeferredItem<Item> CANDC_EXPOSED_COPPER_SPEAR;
    public static DeferredItem<Item> CANDC_WEATHERED_COPPER_SPEAR;
    public static DeferredItem<Item> CANDC_OXIDIZED_COPPER_SPEAR;
    public static DeferredItem<Item> CANDC_WAXED_COPPER_SPEAR;
    public static DeferredItem<Item> CANDC_WAXED_EXPOSED_COPPER_SPEAR;
    public static DeferredItem<Item> CANDC_WAXED_WEATHERED_COPPER_SPEAR;
    public static DeferredItem<Item> CANDC_WAXED_OXIDIZED_COPPER_SPEAR;

    public static DeferredHolder<CreativeModeTab, CreativeModeTab> SPEAR_COMPAT_TAB;

    public SpearBackportAddon(ModContainer container, IEventBus modEventBus) {
        container.registerConfig(ModConfig.Type.STARTUP, SpearConfig.SPEC);

        if (ModList.get().isLoaded("oreganized") && SpearConfig.ENABLE_OREGANIZED_SILVER_SPEAR.get()) {
            OREGANIZED_SILVER_SPEAR = OREGANIZED_ITEMS.register("silver_spear",
                    () -> makeSpear(oreganizedSilverTier(), 1.05F,
                            0.95F, 0.6F, 2.5F, 8.0F, 4.5F, 5.1F, 11.25F, 4.6F));
        }
        if (ModList.get().isLoaded("betterend")) {
            if (SpearConfig.ENABLE_THALLASIUM_SPEAR.get()) {
                THALLASIUM_SPEAR = BETTEREND_ITEMS.register("thallasium_spear",
                        () -> makeSpear(thallasiumTier(), 1.15F,
                                0.95F, 0.6F, 2.5F, 8.0F, 4.5F, 5.1F, 11.25F, 4.6F));
                THALLASIUM_SPEAR_HEAD = BETTEREND_ITEMS.register("thallasium_spear_head",
                        () -> new Item(new Item.Properties()));
            }
            if (SpearConfig.ENABLE_TERMINITE_SPEAR.get()) {
                TERMINITE_SPEAR = BETTEREND_ITEMS.register("terminite_spear",
                        () -> makeSpear(terminiteTier(), 1.0F,
                                1.2F, 0.4F, 2.5F, 7.0F, 3.5F, 5.1F, 8.75F, 4.6F));
                TERMINITE_SPEAR_HEAD = BETTEREND_ITEMS.register("terminite_spear_head",
                        () -> new Item(new Item.Properties()));
            }
            if (SpearConfig.ENABLE_AETERNIUM_SPEAR.get()) {
                AETERNIUM_SPEAR = BETTEREND_ITEMS.register("aeternium_spear",
                        () -> makeSpear(aeterniumTier(), 1.2F,
                                1.2F, 0.4F, 2.5F, 7.0F, 3.5F, 5.1F, 8.75F, 4.6F));
                AETERNIUM_SPEAR_HEAD = BETTEREND_ITEMS.register("aeternium_spear_head",
                        () -> new Item(new Item.Properties()));
            }
        }
        if (ModList.get().isLoaded("caverns_and_chasms")) {
            if (SpearConfig.ENABLE_CANDC_SILVER_SPEAR.get()) {
                CANDC_SILVER_SPEAR = CAVERNS_AND_CHASMS_ITEMS.register("silver_spear",
                        () -> makeSpear(candcSilverTier(), 1.05F,
                                0.95F, 0.6F, 2.5F, 8.0F, 4.5F, 5.1F, 11.25F, 4.6F));
            }
            if (SpearConfig.ENABLE_CANDC_NECROMIUM_SPEAR.get()) {
                CANDC_NECROMIUM_SPEAR = CAVERNS_AND_CHASMS_ITEMS.register("necromium_spear",
                        () -> makeSpear(necromiumTier(), 0.87F,
                                1.2F, 0.4F, 2.5F, 7.0F, 3.5F, 5.1F, 8.75F, 4.6F));
            }
            if (SpearConfig.ENABLE_CANDC_COPPER_SPEAR.get()) {
                CANDC_COPPER_SPEAR = CAVERNS_AND_CHASMS_ITEMS.register("copper_spear", SpearBackportAddon::makeCopperSpear);
                if (SpearConfig.COPPER_OXIDATION.get()) {
                    CANDC_EXPOSED_COPPER_SPEAR = CAVERNS_AND_CHASMS_ITEMS.register("exposed_copper_spear", SpearBackportAddon::makeCopperSpear);
                    CANDC_WEATHERED_COPPER_SPEAR = CAVERNS_AND_CHASMS_ITEMS.register("weathered_copper_spear", SpearBackportAddon::makeCopperSpear);
                    CANDC_OXIDIZED_COPPER_SPEAR = CAVERNS_AND_CHASMS_ITEMS.register("oxidized_copper_spear", SpearBackportAddon::makeCopperSpear);
                    CANDC_WAXED_COPPER_SPEAR = CAVERNS_AND_CHASMS_ITEMS.register("waxed_copper_spear", SpearBackportAddon::makeCopperSpear);
                    CANDC_WAXED_EXPOSED_COPPER_SPEAR = CAVERNS_AND_CHASMS_ITEMS.register("waxed_exposed_copper_spear", SpearBackportAddon::makeCopperSpear);
                    CANDC_WAXED_WEATHERED_COPPER_SPEAR = CAVERNS_AND_CHASMS_ITEMS.register("waxed_weathered_copper_spear", SpearBackportAddon::makeCopperSpear);
                    CANDC_WAXED_OXIDIZED_COPPER_SPEAR = CAVERNS_AND_CHASMS_ITEMS.register("waxed_oxidized_copper_spear", SpearBackportAddon::makeCopperSpear);
                }
            }
        }

        if (SpearConfig.NEW_SPEAR_TAB.get()) {
            SPEAR_COMPAT_TAB = TABS.register("spear_compat", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.spear_backport_addon.spear_compat"))
                    .icon(() -> {
                        if (OREGANIZED_SILVER_SPEAR != null) return new ItemStack(OREGANIZED_SILVER_SPEAR.get());
                        if (CANDC_COPPER_SPEAR != null) return new ItemStack(CANDC_COPPER_SPEAR.get());
                        return new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.parse("minecraft:iron_spear")));
                    })
                    .displayItems((params, output) -> addAllSpears(output))
                    .build());
        }

        OREGANIZED_ITEMS.register(modEventBus);
        BETTEREND_ITEMS.register(modEventBus);
        CAVERNS_AND_CHASMS_ITEMS.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);
        TABS.register(modEventBus);

        modEventBus.addListener(this::onModifyComponents);
    }

    private static void addAllSpears(CreativeModeTab.Output output) {
        if (OREGANIZED_SILVER_SPEAR != null) output.accept(OREGANIZED_SILVER_SPEAR.get());
        if (THALLASIUM_SPEAR_HEAD != null) output.accept(THALLASIUM_SPEAR_HEAD.get());
        if (THALLASIUM_SPEAR != null) output.accept(THALLASIUM_SPEAR.get());
        if (TERMINITE_SPEAR_HEAD != null) output.accept(TERMINITE_SPEAR_HEAD.get());
        if (TERMINITE_SPEAR != null) output.accept(TERMINITE_SPEAR.get());
        if (AETERNIUM_SPEAR_HEAD != null) output.accept(AETERNIUM_SPEAR_HEAD.get());
        if (AETERNIUM_SPEAR != null) output.accept(AETERNIUM_SPEAR.get());
        if (CANDC_SILVER_SPEAR != null) output.accept(CANDC_SILVER_SPEAR.get());
        if (CANDC_NECROMIUM_SPEAR != null) output.accept(CANDC_NECROMIUM_SPEAR.get());
        if (CANDC_COPPER_SPEAR != null) {
            output.accept(CANDC_COPPER_SPEAR.get());
            if (SpearConfig.COPPER_VARIANTS_IN_TAB.get()) {
                addVariant(output, CANDC_EXPOSED_COPPER_SPEAR);
                addVariant(output, CANDC_WEATHERED_COPPER_SPEAR);
                addVariant(output, CANDC_OXIDIZED_COPPER_SPEAR);
                addVariant(output, CANDC_WAXED_COPPER_SPEAR);
                addVariant(output, CANDC_WAXED_EXPOSED_COPPER_SPEAR);
                addVariant(output, CANDC_WAXED_WEATHERED_COPPER_SPEAR);
                addVariant(output, CANDC_WAXED_OXIDIZED_COPPER_SPEAR);
            }
        }
    }

    private static void addVariant(CreativeModeTab.Output output, DeferredItem<Item> item) {
        if (item != null) output.accept(item.get());
    }

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BETTEREND_SPEARS_TAB = TABS.register("betterend_spears",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.spear_backport_addon.betterend_spears"))
                    .icon(() -> {
                        if (AETERNIUM_SPEAR != null) return new ItemStack(AETERNIUM_SPEAR.get());
                        if (AETERNIUM_SPEAR_HEAD != null) return new ItemStack(AETERNIUM_SPEAR_HEAD.get());
                        return new ItemStack(BuiltInRegistries.ITEM.get(ResourceLocation.parse("minecraft:iron_spear")));
                    })
                    .displayItems((params, output) -> {
                        if (THALLASIUM_SPEAR_HEAD != null) output.accept(THALLASIUM_SPEAR_HEAD.get());
                        if (THALLASIUM_SPEAR != null) output.accept(THALLASIUM_SPEAR.get());
                        if (TERMINITE_SPEAR_HEAD != null) output.accept(TERMINITE_SPEAR_HEAD.get());
                        if (TERMINITE_SPEAR != null) output.accept(TERMINITE_SPEAR.get());
                        if (AETERNIUM_SPEAR_HEAD != null) output.accept(AETERNIUM_SPEAR_HEAD.get());
                        if (AETERNIUM_SPEAR != null) output.accept(AETERNIUM_SPEAR.get());
                    })
                    .build());

    private static Item makeCopperSpear() {
        return makeSpear(copperTier(), 0.87F,
                0.95F, 0.6F, 2.5F, 8.0F, 4.5F, 5.1F, 11.25F, 4.6F);
    }

    private static Item makeSpear(Tier material, float attackSpeed,
                                  float chargeDamageMultiplier, float chargeDelay,
                                  float maxDismount, float minDismount,
                                  float maxKnockback, float minKnockback,
                                  float maxChargeDamage, float minChargeDamage) {
        return Spears.registerSpearRaw(material, 1.0F / attackSpeed,
                chargeDamageMultiplier, chargeDelay,
                maxDismount, minDismount,
                maxKnockback, minKnockback,
                maxChargeDamage, minChargeDamage);
    }

    private static Tier oreganizedSilverTier() {
        return new SimpleTier(
                BlockTags.INCORRECT_FOR_IRON_TOOL,
                191, 6.0F, 2.0F, 14,
                () -> Ingredient.of(BuiltInRegistries.ITEM.get(ResourceLocation.parse("oreganized:silver_ingot")))
        );
    }

    private static Tier terminiteTier() {
        return new SimpleTier(
                BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
                1230, 9.0F, 3.0F, 15,
                () -> Ingredient.of(BuiltInRegistries.ITEM.get(ResourceLocation.parse("betterend:terminite_ingot")))
        );
    }

    private static Tier aeterniumTier() {
        return new SimpleTier(
                BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
                2196, 9.0F, 5.0F, 15,
                () -> Ingredient.of(BuiltInRegistries.ITEM.get(ResourceLocation.parse("betterend:aeternium_ingot")))
        );
    }

    private static Tier thallasiumTier() {
        return new SimpleTier(
                BlockTags.INCORRECT_FOR_IRON_TOOL,
                320, 6.0F, 1.5F, 14,
                () -> Ingredient.of(BuiltInRegistries.ITEM.get(ResourceLocation.parse("betterend:thallasium_ingot")))
        );
    }

    private static Tier candcSilverTier() {
        return new SimpleTier(
                BlockTags.INCORRECT_FOR_IRON_TOOL,
                157, 6.0F, 1.0F, 25,
                () -> Ingredient.of(BuiltInRegistries.ITEM.get(ResourceLocation.parse("caverns_and_chasms:silver_ingot")))
        );
    }

    private static Tier necromiumTier() {
        return new SimpleTier(
                BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
                2031, 9.0F, 3.0F, 15,
                () -> Ingredient.of(BuiltInRegistries.ITEM.get(ResourceLocation.parse("caverns_and_chasms:necromium_ingot")))
        );
    }

    private static Tier copperTier() {
        return new SimpleTier(
                BlockTags.INCORRECT_FOR_STONE_TOOL,
                3191, 5.0F, 3.0F, 14,
                () -> Ingredient.of(TagKey.create(Registries.ITEM, ResourceLocation.parse("c:storage_blocks/all_copper")))
        );
    }

    private void onModifyComponents(ModifyDefaultComponentsEvent event) {
        if (AETERNIUM_SPEAR != null) {
            event.modify(AETERNIUM_SPEAR.get(),
                    builder -> builder.set(DataComponents.FIRE_RESISTANT, Unit.INSTANCE));
        }
        if (AETERNIUM_SPEAR_HEAD != null) {
            event.modify(AETERNIUM_SPEAR_HEAD.get(),
                    builder -> builder.set(DataComponents.FIRE_RESISTANT, Unit.INSTANCE));
        }
    }
}
