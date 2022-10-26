package me.zuyte.admin.events;

import com.andrei1058.bedwars.teamselector.api.events.TeamSelectorChooseEvent;
import me.zuyte.admin.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Teamselect implements Listener {

    @EventHandler
public void onTeamSelect(TeamSelectorChooseEvent e) {
    if (Main.getInstance().temp.containsKey(e.getPlayer().getName())) {
        if (Main.getInstance().temp.get(e.getPlayer().getName()) == e.getArena().getArenaName()) {
            e.setCancelled(true);
        }
    }
 }
}
