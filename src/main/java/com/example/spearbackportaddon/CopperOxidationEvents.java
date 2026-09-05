package com.example.spearbackportaddon;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = SpearBackportAddon.MOD_ID)
public class CopperOxidationEvents {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!SpearConfig.COPPER_OXIDATION.get()) return;
        Player player = event.getEntity();
        if (player.level().isClientSide()) return;
        if (player.level().getGameTime() % 60 != 0) return;

        int randomTickSpeed = player.level().getGameRules().getInt(GameRules.RULE_RANDOMTICKING);
        Inventory inventory = player.getInventory();

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.isEmpty()) continue;

            Item next = CopperOxidation.getNext(stack.getItem());
            if (next == null) continue;

            RandomSource random = player.level().getRandom();
            float chance = 0.05688889F * 0.01F;
            if (stack.getItem() == SpearBackportAddon.CANDC_COPPER_SPEAR.get()) {
                chance *= 0.75F; // 正常阶段氧化更慢，同原版
            }

            for (int j = 0; j < randomTickSpeed; j++) {
                if (random.nextFloat() < chance) {
                    ItemStack newStack = new ItemStack(next, stack.getCount());
                    newStack.applyComponents(stack.getComponentsPatch()); // 保留附魔、耐久等
                    inventory.setItem(i, newStack);
                    break;
                }
            }
        }
    }
}
