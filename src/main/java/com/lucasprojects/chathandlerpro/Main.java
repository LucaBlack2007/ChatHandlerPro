package com.lucasprojects.chathandlerpro;

import org.bukkit.ChatColor;
import org.bukkit.command.defaults.ReloadCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    // server-prefix
    // player-variable

    // mutechat-message
    // unmutechat-message
    // mutechat-command-permission
    // mutechat-override-permission

    // clearchat-message
    // clearchat-command-permission

    // coloredchat-character
    // coloredchat-permission

    public Object getSection(String input) { return getConfig().get(input);  }
    public String translate(String str) { return ChatColor.translateAlternateColorCodes('&', str); }
    public String translate(char code, String str) { return ChatColor.translateAlternateColorCodes(code, str); }

    public String prefix = translate(getConfig().getString("server.server-prefix")  );
    public boolean chatMuted = false;

    public void reloadConfiguration() {
        reloadConfig();
        prefix = translate(getConfig().getString("server.server-prefix"));
    }

    @Override
    public void onEnable() {
        if (!this.getDataFolder().exists())
            this.getDataFolder().mkdir();

        saveDefaultConfig();

        getCommand("mutechat").setExecutor(new MuteChatCommand(this));
        getCommand("clearchat").setExecutor(new ClearChatCommand(this));
        getCommand("broadcast").setExecutor(new BroadcastCommand(this));
        getCommand("chathandlerpro").setExecutor(new ConfigurationReloadCommnad(this));

        getServer().getPluginManager().registerEvents(new Events(this), this);
    }

}
