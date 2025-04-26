package com.autogg;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;

public class CommandAU extends CommandBase {
    @Override
    public String getCommandName() {
        return AutoGGMod.customCommand;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + AutoGGMod.customCommand + " <scale|show|hide|command <name>>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.addChatMessage(new ChatComponentText("Usage: /" + AutoGGMod.customCommand + " <scale|show|hide|command <name>>"));
            return;
        }

        if (args[0].equalsIgnoreCase("show")) {
            AutoGGMod.renderHitboxes = true;
            sender.addChatMessage(new ChatComponentText("§aHitbox rendering enabled."));
            return;
        }

        if (args[0].equalsIgnoreCase("hide")) {
            AutoGGMod.renderHitboxes = false;
            sender.addChatMessage(new ChatComponentText("§cHitbox rendering disabled."));
            return;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("command")) {
            AutoGGMod.customCommand = args[1];
            ClientCommandHandler.instance.registerCommand(new CommandAU());
            sender.addChatMessage(new ChatComponentText("§bCommand changed to /" + args[1]));
            return;
        }

        try {
            float scale = Float.parseFloat(args[0]);
            if (scale < 0.1f) {
                sender.addChatMessage(new ChatComponentText("§cScale must be at least 0.1"));
                return;
            }
            AutoGGMod.hitboxScale = scale;
            sender.addChatMessage(new ChatComponentText("§aHitbox scale set to " + scale));
        } catch (NumberFormatException e) {
            sender.addChatMessage(new ChatComponentText("§cInvalid number."));
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}