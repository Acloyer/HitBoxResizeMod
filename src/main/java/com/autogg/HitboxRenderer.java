package com.autogg;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class HitboxRenderer {
    @SubscribeEvent
    public void onRenderLiving(RenderLivingEvent.Specials.Pre<EntityLivingBase> event) {
        if (!AutoGGMod.renderHitboxes) return;
        if (event.entity != Minecraft.getMinecraft().thePlayer) {
            double x = event.x;
            double y = event.y;
            double z = event.z;

            float scale = AutoGGMod.hitboxScale;

            GlStateManager.pushMatrix();
            GlStateManager.disableTexture2D();
            GlStateManager.disableDepth();
            GlStateManager.color(1.0F, 0.0F, 0.0F, 0.5F);
            GlStateManager.translate(x, y, z);

            GL11.glLineWidth(2);
            drawHitbox(event.entity.width * scale, event.entity.height * scale);

            GlStateManager.enableTexture2D();
            GlStateManager.enableDepth();
            GlStateManager.popMatrix();
        }
    }

    private void drawHitbox(float width, float height) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer buffer = tessellator.getWorldRenderer();

        float hw = width / 2;

        buffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);

        buffer.pos(-hw, 0, -hw).endVertex(); buffer.pos(hw, 0, -hw).endVertex();
        buffer.pos(hw, 0, -hw).endVertex(); buffer.pos(hw, 0, hw).endVertex();
        buffer.pos(hw, 0, hw).endVertex(); buffer.pos(-hw, 0, hw).endVertex();
        buffer.pos(-hw, 0, hw).endVertex(); buffer.pos(-hw, 0, -hw).endVertex();

        buffer.pos(-hw, height, -hw).endVertex(); buffer.pos(hw, height, -hw).endVertex();
        buffer.pos(hw, height, -hw).endVertex(); buffer.pos(hw, height, hw).endVertex();
        buffer.pos(hw, height, hw).endVertex(); buffer.pos(-hw, height, hw).endVertex();
        buffer.pos(-hw, height, hw).endVertex(); buffer.pos(-hw, height, -hw).endVertex();

        buffer.pos(-hw, 0, -hw).endVertex(); buffer.pos(-hw, height, -hw).endVertex();
        buffer.pos(hw, 0, -hw).endVertex(); buffer.pos(hw, height, -hw).endVertex();
        buffer.pos(hw, 0, hw).endVertex(); buffer.pos(hw, height, hw).endVertex();
        buffer.pos(-hw, 0, hw).endVertex(); buffer.pos(-hw, height, hw).endVertex();

        tessellator.draw();
    }
}
