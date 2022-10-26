package me.zuyte.admin.commands;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.arena.team.ITeam;
import me.zuyte.admin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class setteam {

    BedWars.ArenaUtil arenaUtil = Main.getInstance().bw.getArenaUtil();

    public void player(Player p, String[] args) {
            if (!p.hasPermission("bw.admin.setteam")) {
                p.sendMessage(ChatColor.RED + "You dont have permission to use this command.");
                return;
            }
            if (args.length > 1) {
                Player player = Bukkit.getPlayerExact(args[1]);
                if (player == null) {
                    p.sendMessage(ChatColor.RED + "Player not found");
                    return;
                }
                if (arenaUtil.getArenaByPlayer(player) == null) {
                    p.sendMessage(ChatColor.RED + "Player has not joined an arena");
                    return;
                }
                IArena playerArena = arenaUtil.getArenaByPlayer(player);
                if (args.length >= 3) {
                    String team = args[2].substring(0,1).toUpperCase() + args[2].substring(1).toLowerCase();
                    if (playerArena.getTeam(team) == null) {
                        p.sendMessage(ChatColor.RED + "Team not found");
                        return;
                    }
                    ITeam playerArenaTeam = playerArena.getTeam(team);
                    if (playerArena.getPlayerTeam(args[1]) != null) {
                        p.sendMessage(ChatColor.RED + "Player has already selected a team!");
                        return;
                    }

                    if (playerArenaTeam.getMembers().size() == playerArena.getMaxInTeam()) {
                        p.sendMessage(ChatColor.RED + "Team is full!");
                        return;
                    }
                    p.sendMessage(ChatColor.GREEN + "Successfully set " + args[1] + "'s Team to " + playerArenaTeam.getColor().chat() + playerArenaTeam.getName());
                    Main.getInstance().temp.put(player.getName(), playerArena.getArenaName());
                    Main.getInstance().temp.put(player.getName() + "-team", playerArenaTeam.getName());
                    return;
                } else {
                    if (playerArena.getPlayerTeam(args[1]) != null) {
                        p.sendMessage(ChatColor.RED + "Player has already selected a team!");
                        return;
                    }
                    setTeamGui(player, playerArena);
                    return;
                }
            }
            p.sendMessage(ChatColor.RED + "Usage: /bw setteam <player> <team>");
        }
    public void console(ConsoleCommandSender c, String[] args) {
                if (args.length > 1) {
                    Player player = Bukkit.getPlayerExact(args[1]);
                    if (player == null) {
                        c.sendMessage(ChatColor.RED + "Player not found");
                        return;
                    }
                    if (arenaUtil.getArenaByPlayer(player) == null) {
                        c.sendMessage(ChatColor.RED + "Player has not joined an arena");
                        return;
                    }
                    IArena playerArena = arenaUtil.getArenaByPlayer(player);
                    if (args.length >= 3) {
                        String team = args[2].substring(0,1).toUpperCase() + args[2].substring(1).toLowerCase();
                        if (playerArena.getTeam(team) == null) {
                            c.sendMessage(ChatColor.RED + "Team not found");
                            return;
                        }
                        ITeam playerArenaTeam = playerArena.getTeam(team);
                        if (playerArena.getPlayerTeam(args[1]) != null) {
                            c.sendMessage(ChatColor.RED + "Player has already selected a team!");
                            return;
                        }

                        if (playerArenaTeam.getMembers().size() == playerArena.getMaxInTeam()) {
                            c.sendMessage(ChatColor.RED + "Team is full!");
                            return;
                        }
                        c.sendMessage(ChatColor.GREEN + "Successfully set " + args[1] + "'s Team to " + playerArenaTeam.getColor().chat() + playerArenaTeam.getName());
                        Main.getInstance().temp.put(player.getName(), playerArena.getArenaName());
                        Main.getInstance().temp.put(player.getName() + "-team", playerArenaTeam.getName());
                        return;
                    }
                c.sendMessage(ChatColor.RED + "Usage: /bw setteam <player> <team>");
            }
        return;
    }

    private void setTeamGui(Player player, IArena arena) {
        Inventory setTeamInventory = Bukkit.createInventory(player, 9, "Admin - Team Selector");
        for (int i = 0; i < arena.getTeams().size(); i++) {
            ITeam team = arena.getTeams().get(i);
            ItemStack itemStacki = new ItemStack(Material.WOOL, 1, team.getColor().dye().getWoolData());
            ItemMeta itemMetai = itemStacki.getItemMeta();
            itemMetai.setDisplayName(team.getColor().chat() + "" + ChatColor.BOLD + team.getName());
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "•" + ChatColor.GREEN + " Click to select team!");
            itemMetai.setLore(lore);
            itemStacki.setItemMeta(itemMetai);
            setTeamInventory.setItem(i, itemStacki);
        }
        player.openInventory(setTeamInventory);
    }
}