package me.zuyte.admin.events;

import me.zuyte.admin.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;

public class WBPlace implements Listener {

    @EventHandler
    public void onWBPlace(PlayerBucketEmptyEvent e) {
        if (Main.getInstance().mlg.containsKey(e.getPlayer().getName())) {
            if (e.getBucket() == Material.WATER_BUCKET) {
                e.getPlayer().setFallDistance(0);
                e.setCancelled(true);
                    e.getPlayer().sendMessage(ChatColor.GREEN + "Success!");
                    e.getPlayer().setItemInHand(Main.getInstance().mlg.get(e.getPlayer().getName()));
                    Main.getInstance().mlg.remove(e.getPlayer().getName());
            }
        }
    }
}
