package com.lucasprojects.chathandlerpro;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteChatCommand implements CommandExecutor {

    private Main main;

    public MuteChatCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String MCMessage = main.translate(((String) main.getSection("mutechat.mutechat-message")).replaceAll((String) main.getSection("server.player-variable"), sender.getName()));
        String UMCMessage = main.translate(((String) main.getSection("mutechat.unmutechat-message")).replaceAll((String) main.getSection("server.player-variable"), sender.getName()));

        if (sender.hasPermission((String) main.getSection("mutechat.mutechat-command-permission"))) {
            if (main.chatMuted) {
                main.chatMuted = false;
                Bukkit.broadcastMessage(main.prefix + UMCMessage);
            } else {
                main.chatMuted = true;
                Bukkit.broadcastMessage(main.prefix + MCMessage);
            }
        } else {
            sender.sendMessage(main.prefix + ChatColor.ITALIC + "No permission!");
        }

        return false;
    }
}
