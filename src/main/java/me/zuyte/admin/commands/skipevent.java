package me.zuyte.admin.commands;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.arena.NextEvent;
import me.zuyte.admin.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class skipevent{

    BedWars.ArenaUtil arenaUtil = Main.getInstance().bw.getArenaUtil();

    public void player(Player p, String[] args) {
            if (!p.hasPermission("bw.admin.skipevent")) {
                p.sendMessage(ChatColor.RED + "You dont have permission to use this command.");
                return;
            }
            if (args.length > 1) {
                if (arenaUtil.getArenaByName(args[1]) == null) {
                    p.sendMessage(ChatColor.RED + "Arena not found");
                    return;
                }
                IArena arena = arenaUtil.getArenaByName(args[1]);
                    if (arena.getNextEvent().equals(NextEvent.DIAMOND_GENERATOR_TIER_II)) {
                        arena.setNextEvent(NextEvent.DIAMOND_GENERATOR_TIER_III);
                    } else if (arena.getNextEvent().equals(NextEvent.DIAMOND_GENERATOR_TIER_III)) {
                        arena.setNextEvent(NextEvent.EMERALD_GENERATOR_TIER_II);
                    } else if (arena.getNextEvent().equals(NextEvent.EMERALD_GENERATOR_TIER_II)) {
                        arena.setNextEvent(NextEvent.EMERALD_GENERATOR_TIER_III);
                    } else if (arena.getNextEvent().equals(NextEvent.EMERALD_GENERATOR_TIER_III)) {
                        arena.setNextEvent(NextEvent.BEDS_DESTROY);
                    } else if (arena.getNextEvent().equals(NextEvent.BEDS_DESTROY)) {
                        for (int i = 0; i < arena.getTeams().size(); i++) {
                            if (!arena.getTeams().get(i).isBedDestroyed())
                                arena.getTeams().get(i).setBedDestroyed(true);
                        }
                        arena.setNextEvent(NextEvent.ENDER_DRAGON);
                    } else if (arena.getNextEvent().equals(NextEvent.ENDER_DRAGON)) {
                        arena.setNextEvent(NextEvent.GAME_END);
                    } else if (arena.getNextEvent().equals(NextEvent.GAME_END)) {
                        p.sendMessage((ChatColor.RED + "You are already at the last event"));
                        return;
                    }
                    p.sendMessage(ChatColor.GREEN + "Current event has been skipped!");
                    return;
                }
            p.sendMessage(ChatColor.RED + "Usage: /bw skipevent <arena>");
        }

        public void console(ConsoleCommandSender c, String[] args) {
                if (args.length > 1) {
                    if (arenaUtil.getArenaByName(args[1]) == null) {
                        c.sendMessage(ChatColor.RED + "Arena not found");
                        return;
                    }
                    IArena arena = arenaUtil.getArenaByName(args[1]);
                    if (arena.getNextEvent().equals(NextEvent.DIAMOND_GENERATOR_TIER_II)) {
                        arena.setNextEvent(NextEvent.DIAMOND_GENERATOR_TIER_III);
                    } else if (arena.getNextEvent().equals(NextEvent.DIAMOND_GENERATOR_TIER_III)) {
                        arena.setNextEvent(NextEvent.EMERALD_GENERATOR_TIER_II);
                    } else if (arena.getNextEvent().equals(NextEvent.EMERALD_GENERATOR_TIER_II)) {
                        arena.setNextEvent(NextEvent.EMERALD_GENERATOR_TIER_III);
                    } else if (arena.getNextEvent().equals(NextEvent.EMERALD_GENERATOR_TIER_III)) {
                        arena.setNextEvent(NextEvent.BEDS_DESTROY);
                    } else if (arena.getNextEvent().equals(NextEvent.BEDS_DESTROY)) {
                        arena.setNextEvent(NextEvent.ENDER_DRAGON);
                    } else if (arena.getNextEvent().equals(NextEvent.ENDER_DRAGON)) {
                        arena.setNextEvent(NextEvent.GAME_END);
                    } else if (arena.getNextEvent().equals(NextEvent.GAME_END)) {
                        c.sendMessage((ChatColor.RED + "You are already at the last event"));
                        return;
                    }
                    c.sendMessage(ChatColor.GREEN + "Current event has been skipped!");
                    return;
                }
                c.sendMessage(ChatColor.RED + "Usage: /bw skipevent <arena>");
    }
}
