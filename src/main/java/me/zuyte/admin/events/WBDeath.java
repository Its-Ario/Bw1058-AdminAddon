package me.zuyte.admin.events;

import me.zuyte.admin.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class WBDeath implements Listener {

    @EventHandler
    public void onWBDeath(PlayerDeathEvent e) {
        if (Main.getInstance().mlg.containsKey(e.getEntity().getName())) {
            Main.getInstance().mlg.remove(e.getEntity().getName());
            e.getEntity().sendMessage(ChatColor.RED + "Failed!");
        }
    }
}
