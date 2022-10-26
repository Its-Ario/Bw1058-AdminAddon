package me.zuyte.admin.commands;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.arena.NextEvent;
import me.zuyte.admin.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class nextevent{
    BedWars.ArenaUtil arenaUtil = Main.getInstance().bw.getArenaUtil();

public void player(Player p, String[] args) {
            if (!p.hasPermission("bw.admin.nextevent")) {
                p.sendMessage(ChatColor.RED + "You dont have permission to use this command.");
                return;
            }
            if (args.length > 1) {
                if (arenaUtil.getArenaByName(args[1]) == null) {
                    p.sendMessage(ChatColor.RED + "Arena not found");
                    return;
                }
                IArena arena = arenaUtil.getArenaByName(args[1]);
                if (args.length >= 3) {
                    if (args[2].equalsIgnoreCase("diamond-2")) {
                        arena.setNextEvent(NextEvent.DIAMOND_GENERATOR_TIER_II);
                    } else if (args[2].equalsIgnoreCase("diamond-3")) {
                        arena.setNextEvent(NextEvent.DIAMOND_GENERATOR_TIER_III);
                    } else if (args[2].equalsIgnoreCase("emerald-2")) {
                        arena.setNextEvent(NextEvent.EMERALD_GENERATOR_TIER_II);
                    } else if (args[2].equalsIgnoreCase("emerald-3")) {
                        arena.setNextEvent(NextEvent.EMERALD_GENERATOR_TIER_III);
                    } else if (args[2].equalsIgnoreCase("bed-destroy")) {
                        arena.setNextEvent(NextEvent.BEDS_DESTROY);
                    } else if (args[2].equalsIgnoreCase("dragon")) {
                        arena.setNextEvent(NextEvent.ENDER_DRAGON);
                    } else if (args[2].equalsIgnoreCase("end")) {
                        arena.setNextEvent(NextEvent.GAME_END);
                    }
                    p.sendMessage(ChatColor.GREEN + "Success!");
                    return;
                }
            }
            p.sendMessage(ChatColor.RED + "Usage: /bw nextevent <arena> <event>");
        }

        public void console(ConsoleCommandSender c, String[] args) {
            if (args.length > 1) {
                if (arenaUtil.getArenaByName(args[1]) == null) {
                    c.sendMessage(ChatColor.RED + "Arena not found");
                    return;
                }
                IArena arena = arenaUtil.getArenaByName(args[1]);
                if (args.length >= 3) {
                    if (args[2].equalsIgnoreCase("diamond-2")) {
                        arena.setNextEvent(NextEvent.DIAMOND_GENERATOR_TIER_II);
                    } else if (args[2].equalsIgnoreCase("diamond-3")) {
                        arena.setNextEvent(NextEvent.DIAMOND_GENERATOR_TIER_III);
                    } else if (args[2].equalsIgnoreCase("emerald-2")) {
                        arena.setNextEvent(NextEvent.EMERALD_GENERATOR_TIER_II);
                    } else if (args[2].equalsIgnoreCase("emerald-3")) {
                        arena.setNextEvent(NextEvent.EMERALD_GENERATOR_TIER_III);
                    } else if (args[2].equalsIgnoreCase("bed-destroy")) {
                        arena.setNextEvent(NextEvent.BEDS_DESTROY);
                    } else if (args[2].equalsIgnoreCase("dragon")) {
                        arena.setNextEvent(NextEvent.ENDER_DRAGON);
                    } else if (args[2].equalsIgnoreCase("end")) {
                        arena.setNextEvent(NextEvent.GAME_END);
                    }
                    c.sendMessage(ChatColor.GREEN + "Success!");
                    return;
                }
            }
            c.sendMessage(ChatColor.RED + "Usage: /bw nextevent <arena> <event>");
    }

}
