package com.autogg;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "autogg", name = "AutoGG", version = "1.0")
public class AutoGGMod {
    public static float hitboxScale = 1.0f;
    public static String customCommand = "au";
    public static boolean renderHitboxes = true;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new HitboxRenderer());
        MinecraftForge.EVENT_BUS.register(new ReachInjector());
        ClientCommandHandler.instance.registerCommand(new CommandAU());
    }
}