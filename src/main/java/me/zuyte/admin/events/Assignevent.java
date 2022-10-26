package me.zuyte.admin.events;

import com.andrei1058.bedwars.api.arena.team.ITeam;
import com.andrei1058.bedwars.api.events.gameplay.TeamAssignEvent;
import me.zuyte.admin.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Assignevent implements Listener {

    @EventHandler
    public void onAssignevent(TeamAssignEvent e) {
        if (Main.getInstance().temp.containsKey(e.getPlayer().getName())) {
            if (Main.getInstance().temp.get(e.getPlayer().getName()).equals(e.getArena().getArenaName())) {
                e.setCancelled(true);
                ITeam playerSelTeam = e.getArena().getTeam(Main.getInstance().temp.get(e.getPlayer().getName() + "-team"));
                playerSelTeam.addPlayers(e.getPlayer());
            }
        }
    }
}

