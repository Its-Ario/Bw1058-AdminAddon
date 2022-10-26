package me.zuyte.admin.proxy;

import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.proxy.arenamanager.ArenaManager;
import me.zuyte.admin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminTabCompleterProxy implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("bwa")) {
            if (args.length == 1) {
                return Arrays.asList("help", "forcejoin");
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("forcejoin")) {
                    List<String> arenaNames = new ArrayList<>();
                    IArena[] arenas = new IArena[ArenaManager.getArenas().size()];
                    ArenaManager.getArenas().toArray(arenas);
                    for (int i = 0; i < arenas.length; i++) {
                        arenaNames.add(arenas[i].getArenaName());
                    }
                    return arenaNames;
                }
        }
            if (args.length == 3) {
                if (args[0].equalsIgnoreCase("forcejoin")) {
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
