package me.zuyte.admin.commands;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.arena.team.ITeam;
import me.zuyte.admin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminTabCompleter implements TabCompleter {
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("bwa")) {
        if (args.length == 1) {
            // "revive",
            return Arrays.asList("help", "forcejoin", "setteam", "setbed", "skipevent", "nextevent", "troll");
        }
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("forcejoin") || args[0].equalsIgnoreCase("setteam") || args[0].equalsIgnoreCase("setbed")) {
                List<String> playerNames = new ArrayList<>();
                Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                Bukkit.getServer().getOnlinePlayers().toArray(players);
                for (int i = 0; i < players.length; i++) {
                    playerNames.add(players[i].getName());
                }
                return playerNames;
            } else if (args[0].equalsIgnoreCase("nextevent") || args[0].equalsIgnoreCase("skipevent")) {
                List<String> arenaNames = new ArrayList<>();
                IArena[] arenas = new IArena[Main.getInstance().bw.getArenaUtil().getArenas().size()];
                Main.getInstance().bw.getArenaUtil().getArenas().toArray(arenas);
                for (int i = 0; i < arenas.length; i++) {
                    arenaNames.add(arenas[i].getArenaName());
                }
                return arenaNames;
            } else if (args[0].equalsIgnoreCase("troll")) {
                return Arrays.asList("mlg", "cage", "blind", "slowhands", "kaboom", "toystick", "ghast", "mobattack");
            }
        }
        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("forcejoin")) {
                List<String> arenaNames = new ArrayList<>();
                List arenaGroupsList = Main.bw.getConfigs().getMainConfig().getYml().getStringList(".arenaGroups");
                String[] arenaGroups = new String[arenaGroupsList.size()];
                arenaGroupsList.toArray(arenaGroups);
                for (int h = 0; h < arenaGroupsList.size(); h++) {
                    arenaNames.add(arenaGroups[h]);
                }
                    IArena[] arenas = new IArena[Main.getInstance().bw.getArenaUtil().getArenas().size()];
                    Main.getInstance().bw.getArenaUtil().getArenas().toArray(arenas);

                    for (int i = 0; i < arenas.length; i++) {
                        arenaNames.add(arenas[i].getArenaName());
                    }
                    return arenaNames;
            }
//            if (args[0].equalsIgnoreCase("revive")) {
//                return Arrays.asList("final", "bed");
//            }
            if (args[0].equalsIgnoreCase("setbed")) {
                return Arrays.asList("true", "false");
            }
            if (args[0].equalsIgnoreCase("nextevent")) {
                return Arrays.asList("diamond-2", "diamond-3", "emerald-2", "emerald-3", "bed-destroy", "dragon", "end");
            }
            if (args[0].equalsIgnoreCase("setteam")) {
                BedWars.ArenaUtil arenaUtil = Main.getInstance().bw.getArenaUtil();
                if (Bukkit.getPlayerExact(args[1]) != null) {
                    if (arenaUtil.getArenaByPlayer(Bukkit.getPlayerExact(args[1])) != null) {
                        Player player = Bukkit.getPlayerExact(args[1]);
                        List<String> teamNames = new ArrayList<>();
                        ITeam[] teams = new ITeam[arenaUtil.getArenaByPlayer(player).getTeams().size()];
                        arenaUtil.getArenaByPlayer(player).getTeams().toArray(teams);
                        for (int i = 0; i < teams.length; i++) {
                            teamNames.add(teams[i].getName());
                        }
                        return teamNames;
                    }
                }
            }
            if (args[0].equalsIgnoreCase("troll")) {
                List<String> playerNames = new ArrayList<>();
                Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                Bukkit.getServer().getOnlinePlayers().toArray(players);
                for (int i = 0; i < players.length; i++) {
                    playerNames.add(players[i].getName());
                }
                return playerNames;
            }
        }
    }
        return null;
    }
}
