package me.zuyte.admin.events;

import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.arena.team.ITeam;
import me.zuyte.admin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getCurrentItem() == null || !e.getCurrentItem().hasItemMeta()) {
            return;
        }
        if (e.getClickedInventory().getTitle() == "Admin - Team Selector") {
            Player p = (Player) e.getWhoClicked();
            IArena arena = Main.getInstance().bw.getArenaUtil().getArenaByPlayer(p);
            String teamName = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName());
            ITeam team = arena.getTeam(teamName);
            if (arena.getTeam(teamName) == null) {
                p.sendMessage(ChatColor.RED + "Error: Team not found");
                return;
            }
            if (team.getMembers().size() == arena.getMaxInTeam()) {
                p.sendMessage(ChatColor.RED + "Team is full!");
                return;
            }

            Main.getInstance().temp.put(p.getName(), arena.getArenaName());
            Main.getInstance().temp.put(p.getName() + "-team", team.getName());
            p.sendMessage(ChatColor.GREEN + "Successfully set " + p.getName() + "'s Team to " + team.getColor().chat() + team.getName());
            e.setCancelled(true);
            p.closeInventory();
            return;
        }
    }
}