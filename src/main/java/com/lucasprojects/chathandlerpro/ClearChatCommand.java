package com.lucasprojects.chathandlerpro;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand implements CommandExecutor {

    private Main main;

    public ClearChatCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String message = main.translate(main.prefix + ((String) main.getSection("clearchat.clearchat-message")).replaceAll((String) main.getSection("server.player-variable"), sender.getName()));

        if (sender.hasPermission((String) main.getSection("clearchat.clearchat-command-permission"))) {
            if ((boolean) main.getSection("clearchat.clearchat-ignore-staff") == true) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (!p.hasPermission((String) main.getSection("clearchat.clearchat-command-permission"))) {
                        for (int i = 0; i < 300; i++) { p.sendMessage(" "); }
                    }
                }
                Bukkit.broadcastMessage(message);
            } else {
                for (int i = 0; i < 300; i++) { Bukkit.broadcastMessage(" "); }
                Bukkit.broadcastMessage(message);
            }
        } else {
            sender.sendMessage(main.prefix + ChatColor.ITALIC + "No permission!");
        }

        return false;
    }
}
