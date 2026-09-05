package com.example.spearbackportaddon;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddPackFindersEvent;

@EventBusSubscriber(modid = SpearBackportAddon.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ResourcePackEvents {

    @SubscribeEvent
    public static void onPackFinders(AddPackFindersEvent event) {
        if (!SpearConfig.ELECTRUM_TEXTURE_OVERRIDE.get()) return;
        event.addPackFinders(
                ResourceLocation.fromNamespaceAndPath("spear_backport_addon", "resourcepacks/electrum_override"),
                PackType.CLIENT_RESOURCES,
                Component.literal("Electrum Spear Texture Override"),
                PackSource.BUILT_IN,
                true,
                Pack.Position.TOP
        );
    }
}
