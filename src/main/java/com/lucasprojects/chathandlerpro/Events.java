package com.lucasprojects.chathandlerpro;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Events implements Listener {

    private Main main;

    public Events(Main main) { this.main = main; }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (!main.chatMuted) {
            if (player.hasPermission(String.valueOf(main.getSection("coloredchat.coloredchat-permission")))) {
                String msg = main.translate((char) main.getSection("coloredchat.coloredchat-character"), e.getMessage());
                e.setFormat(e.getFormat().replaceAll(e.getMessage(), msg));
            } else {
                e.setFormat(e.getFormat());
            }
        } else {
            if (player.hasPermission(String.valueOf(main.getSection("mutechat.mutechat-override-permission")))) {
                if (player.hasPermission(String.valueOf(main.getSection("coloredchat.coloredchat-permission")))) {
                    String msg = main.translate((char) main.getSection("coloredchat.coloredchat-character"), e.getMessage());
                    e.setFormat(e.getFormat().replaceAll(e.getMessage(), msg));
                } else {
                    e.setFormat(e.getFormat());
                }
            } else {
                player.sendMessage(main.prefix + "&7Chat is muted!");
            }
        }
    }

}
