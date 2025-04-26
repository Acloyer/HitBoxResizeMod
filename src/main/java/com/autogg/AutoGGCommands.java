package com.autogg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class AutoGGCommands extends CommandBase implements ICommand {
    public static float expandMultiplier = 1.0F;

    @Override
    public String getCommandName() {
        return "autogg";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/autogg [Multiplier]";
    }

    @Override
    public List<String> getCommandAliases() {
        return Collections.singletonList("autogg");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            sender.addChatMessage(new ChatComponentText("Invalid Arguments. Correct Usage: " + getCommandUsage(sender)));
            return;
        }

        if (args.length == 1) {
            try {
                expandMultiplier = Float.parseFloat(args[0]);
                sender.addChatMessage(new ChatComponentText("AutoGG Multiplier set to: " + expandMultiplier));
            } catch (NumberFormatException e) {
                sender.addChatMessage(new ChatComponentText("Invalid number format."));
            }
        } else {
            sender.addChatMessage(new ChatComponentText("Invalid Arguments. Correct Usage: " + getCommandUsage(sender)));
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }
}
