package com.lucasprojects.chathandlerpro;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadcastCommand implements CommandExecutor {

    private Main main;

    public BroadcastCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission((String) main.getSection("server.broadcast-permission"))) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < args.length; i++) sb.append(args[i] + " ");
            Bukkit.broadcastMessage(main.prefix + main.translate(sb.toString()));
        } else {
            sender.sendMessage(main.prefix + ChatColor.ITALIC + "No permission!");
        }

        return false;
    }
}
