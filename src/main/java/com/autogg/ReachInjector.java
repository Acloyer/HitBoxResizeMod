package com.autogg;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;

public class ReachInjector {
    @SubscribeEvent
    public void onRenderUpdate(EntityViewRenderEvent.CameraSetup event) {
        try {
            Field reach = ReflectionHelper.findField(Minecraft.class, "field_71442_b", "playerController");
            Object controller = reach.get(Minecraft.getMinecraft());
            Field blockReach = ReflectionHelper.findField(controller.getClass(), "field_78770_f", "blockReachDistance");
            blockReach.setAccessible(true);
            blockReach.setFloat(controller, 3.0F * AutoGGMod.hitboxScale);
        } catch (Exception ignored) {}
    }
}