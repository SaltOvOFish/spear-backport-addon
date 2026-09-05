package com.example.spearbackportaddon;

import net.neoforged.neoforge.common.ModConfigSpec;

public class SpearConfig {

    public static final ModConfigSpec SPEC;

    // 配置1：新标签页
    public static final ModConfigSpec.BooleanValue NEW_SPEAR_TAB;

    // 配置2：各种矛注册开关
    public static final ModConfigSpec.BooleanValue ELECTRUM_TEXTURE_OVERRIDE;
    public static final ModConfigSpec.BooleanValue ENABLE_OREGANIZED_SILVER_SPEAR;
    public static final ModConfigSpec.BooleanValue ENABLE_THALLASIUM_SPEAR;
    public static final ModConfigSpec.BooleanValue ENABLE_TERMINITE_SPEAR;
    public static final ModConfigSpec.BooleanValue ENABLE_AETERNIUM_SPEAR;
    public static final ModConfigSpec.BooleanValue ENABLE_CANDC_SILVER_SPEAR;
    public static final ModConfigSpec.BooleanValue ENABLE_CANDC_NECROMIUM_SPEAR;
    public static final ModConfigSpec.BooleanValue ENABLE_CANDC_COPPER_SPEAR;

    // 配置3： Caverns & Chasms 铜矛氧化/涂蜡
    public static final ModConfigSpec.BooleanValue COPPER_OXIDATION;

    // 配置4： Caverns & Chasms 铜矛变体显示
    public static final ModConfigSpec.BooleanValue COPPER_VARIANTS_IN_TAB;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.push("creative_tab");
        NEW_SPEAR_TAB = builder
                .comment("是否启用新的创造模式标签页「长矛附加」，并将所有矛同时显示在其中（原标签页保留）", "默认：关闭")
                .define("new_spear_tab", false);
        builder.pop();

        builder.push("spear_registration");
        ELECTRUM_TEXTURE_OVERRIDE = builder
                .comment("是否用本模组修改后的贴图覆盖 Backported Spears 的 Oreganized 琥珀金矛贴图", "默认：开启")
                .define("electrum_texture_override", true);
        ENABLE_OREGANIZED_SILVER_SPEAR = builder
                .comment("是否注册 Oreganized 银矛", "默认：开启")
                .define("oreganized_silver_spear", true);
        ENABLE_THALLASIUM_SPEAR = builder
                .comment("是否注册 BetterEnd 塔拉斯金矛与矛头", "默认：开启")
                .define("thallasium_spear", true);
        ENABLE_TERMINITE_SPEAR = builder
                .comment("是否注册 BetterEnd 终末合金矛与矛头", "默认：开启")
                .define("terminite_spear", true);
        ENABLE_AETERNIUM_SPEAR = builder
                .comment("是否注册 BetterEnd 太古合金矛与矛头", "默认：开启")
                .define("aeternium_spear", true);
        ENABLE_CANDC_SILVER_SPEAR = builder
                .comment("是否注册 Caverns & Chasms 银矛", "默认：开启")
                .define("candc_silver_spear", true);
        ENABLE_CANDC_NECROMIUM_SPEAR = builder
                .comment("是否注册 Caverns & Chasms 死灵合金矛", "默认：开启")
                .define("candc_necromium_spear", true);
        ENABLE_CANDC_COPPER_SPEAR = builder
                .comment("是否注册 Caverns & Chasms 铜矛（所有变体共享此开关）", "默认：开启")
                .define("candc_copper_spear", true);
        COPPER_OXIDATION = builder
                .comment("是否启用 Caverns & Chasms 铜矛氧化与涂蜡机制", "关闭时仅注册普通铜矛，不注册变体", "默认：开启")
                .define("copper_oxidation", true);
        COPPER_VARIANTS_IN_TAB = builder
                .comment("是否在创造模式物品栏中显示 Caverns & Chasms 铜矛的七种变体", "开启时按序排列在普通铜矛之后", "默认：关闭")
                .define("copper_variants_in_tab", false);
        builder.pop();

        SPEC = builder.build();
    }
}
