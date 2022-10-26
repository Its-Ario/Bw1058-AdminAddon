package me.zuyte.admin.events;

import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.arena.team.ITeam;
import com.andrei1058.bedwars.api.events.gameplay.GameStateChangeEvent;
import me.zuyte.admin.Main;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Arenastart implements Listener {

    @EventHandler
    public void onArenaStart(GameStateChangeEvent e) {
        if (e.getNewState() == GameState.playing) {
            for (int i = 0; i < e.getArena().getTeams().size(); i++) {
                ITeam team = e.getArena().getTeams().get(i);
                if (team.isBedDestroyed()) return;
                BlockFace targetFace = ((org.bukkit.material.Bed) team.getBed().getBlock().getState().getData()).getFacing();
                Main.getInstance().beds.put(e.getArena().getArenaName() + team.getName() + "Main", targetFace);
            }
            if (Main.getInstance().temp.containsValue(e.getArena().getArenaName())) {
                for (int i = 0; i < e.getArena().getPlayers().toArray().length; i++) {
                    Player player = e.getArena().getPlayers().get(i);
                    String playerName = player.getName();
                    if (Main.getInstance().temp.containsKey(playerName)) {
                        String value = Main.getInstance().temp.get(playerName);
                        if (value == e.getArena().getArenaName()) {
                            Main.getInstance().temp.remove(playerName);
                        }
                    }
                    if (Main.getInstance().temp.containsKey(playerName + "-team")) {
                        Main.getInstance().temp.remove(playerName + "-team");
                    }
                }
            }
        }
    }
}
