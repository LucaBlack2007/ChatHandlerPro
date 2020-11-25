package com.lucasprojects.chathandlerpro;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ConfigurationReloadCommnad implements CommandExecutor {

    private Main main;

    public ConfigurationReloadCommnad(Main main) { this.main = main; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("*")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("restart")) {
                    main.reloadConfiguration();
                    sender.sendMessage(main.prefix + ChatColor.ITALIC + "reloading...");
                    Bukkit.getScheduler().runTaskLater(main, new Runnable() {
                        @Override
                        public void run() {
                            sender.sendMessage(main.prefix + ChatColor.ITALIC + "Reloaded configuration successfully!");
                        }
                    }, 30l);
                } else {
                    sender.sendMessage(main.prefix + ChatColor.ITALIC + "Incorrect Usage! Usage: /chathandlerpro reload|restart");
                }
            }
        } else {
            sender.sendMessage(main.prefix + ChatColor.ITALIC + "No permission!");
        }

        return false;
    }
}
