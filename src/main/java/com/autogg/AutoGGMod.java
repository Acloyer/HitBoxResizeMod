package com.autogg;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.List;

@Mod(modid = "autogg", version = "2.3", acceptedMinecraftVersions = "[1.8.9]")
public class AutoGGMod {
    protected Minecraft mc = Minecraft.getMinecraft();
    private Entity pointedEntity;
    private MovingObjectPosition moving;
    public static float hitBoxMultiplier = 1.0F;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand((ICommand) new AutoGGCommands());
    }

    @SubscribeEvent
    public void onMouse(MouseEvent e) {
        try {
            if (this.moving != null && e.button == 0 && e.buttonstate)
                this.mc.objectMouseOver = this.moving;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent e) {
        AutoGGCommands getHitBoxValue = new AutoGGCommands();
        if (this.mc.theWorld != null)
            hitBoxMultiplier = AutoGGCommands.expandMultiplier;
        getMouseOver(1.0F);
    }

    private void getMouseOver(float partialTicks) {
        if (Minecraft.getMinecraft().getRenderViewEntity() != null && Minecraft.getMinecraft().theWorld != null) {
            Minecraft.getMinecraft().pointedEntity = null;
            double reach = 3.0D;
            this.moving = Minecraft.getMinecraft().getRenderViewEntity().rayTrace(reach, partialTicks);
            double d1 = reach;
            Vec3 eyePosition = Minecraft.getMinecraft().getRenderViewEntity().getPositionEyes(partialTicks);
            if (this.moving != null)
                d1 = this.moving.hitVec.distanceTo(eyePosition);
            Vec3 look = Minecraft.getMinecraft().getRenderViewEntity().getLook(partialTicks);
            Vec3 target = eyePosition.addVector(look.xCoord * reach, look.yCoord * reach, look.zCoord * reach);

            this.pointedEntity = null;
            Vec3 hitVec = null;
            float margin = 1.0F;
            List<Entity> list = Minecraft.getMinecraft().theWorld.getEntitiesWithinAABBExcludingEntity(
                    Minecraft.getMinecraft().getRenderViewEntity(),
                    Minecraft.getMinecraft().getRenderViewEntity().getEntityBoundingBox()
                            .addCoord(look.xCoord * reach, look.yCoord * reach, look.zCoord * reach)
                            .expand(margin, margin, margin));

            double closestDistance = d1;
            for (Entity entity : list) {
                if (entity.canBeCollidedWith()) {
                    float expand = 0.13F * hitBoxMultiplier;
                    AxisAlignedBB aabb = entity.getEntityBoundingBox().expand(expand, expand, expand);
                    MovingObjectPosition mop = aabb.calculateIntercept(eyePosition, target);

                    if (aabb.isVecInside(eyePosition)) {
                        if (0.0D < closestDistance || closestDistance == 0.0D) {
                            this.pointedEntity = entity;
                            hitVec = mop == null ? eyePosition : mop.hitVec;
                            closestDistance = 0.0D;
                        }
                    } else if (mop != null) {
                        double dist = eyePosition.distanceTo(mop.hitVec);
                        if (dist < closestDistance || closestDistance == 0.0D) {
                            if (entity == Minecraft.getMinecraft().getRenderViewEntity().ridingEntity && !entity.canRiderInteract()) {
                                if (closestDistance == 0.0D) {
                                    this.pointedEntity = entity;
                                    hitVec = mop.hitVec;
                                }
                            } else {
                                this.pointedEntity = entity;
                                hitVec = mop.hitVec;
                                closestDistance = dist;
                            }
                        }
                    }
                }
            }

            if (this.pointedEntity != null && (closestDistance < d1 || this.moving == null)) {
                this.moving = new MovingObjectPosition(this.pointedEntity, hitVec);
                if (this.pointedEntity instanceof net.minecraft.entity.EntityLivingBase
                        || this.pointedEntity instanceof net.minecraft.entity.item.EntityItemFrame) {
                    Minecraft.getMinecraft().pointedEntity = this.pointedEntity;
                }
            }
        }
    }
}
