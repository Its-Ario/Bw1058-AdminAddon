package me.zuyte.admin.events;

import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent;
import me.zuyte.admin.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Arenaleave implements Listener {

    @EventHandler
    public void onArenaLeave(PlayerLeaveArenaEvent e) {
        if (Main.getInstance().temp.containsKey(e.getPlayer().getName())) {
            if (Main.getInstance().temp.get(e.getPlayer().getName()) == e.getArena().getArenaName()) {
                Main.getInstance().temp.remove(e.getPlayer().getName());
            }
        }
        if (Main.getInstance().mlg.containsKey(e.getPlayer().getName())) {
            Main.getInstance().mlg.remove(e.getPlayer().getName());
        }
        if (Main.getInstance().temp.containsKey(e.getPlayer().getName() + "-team")) {
            Main.getInstance().temp.remove(e.getPlayer().getName() + "-team");
        }
        if (Main.getInstance().kaboom.containsKey(e.getPlayer().getName())) {
            Main.getInstance().kaboom.remove(e.getPlayer().getName());
        }
    }
}
