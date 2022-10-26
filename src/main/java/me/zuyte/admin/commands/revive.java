package me.zuyte.admin.commands;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.arena.team.ITeam;
import me.zuyte.admin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class revive {

    BedWars.ArenaUtil arenaUtil = Main.getInstance().bw.getArenaUtil();

    public void player(Player p, String[] args) {
            if (!p.hasPermission("bw.admin.revive")) {
                p.sendMessage(ChatColor.RED + "You dont have permission to use this command.");
                return;
            }
            if (args.length > 2) {
                Player player = Bukkit.getPlayerExact(args[1]);
                if (player == null) {
                    p.sendMessage(ChatColor.RED + "Player not found");
                    return;
                }
                if (arenaUtil.getArenaByPlayer(player) == null) {
                    p.sendMessage(ChatColor.RED + "Player has not joined an arena");
                    return;
                }
                    IArena arena = arenaUtil.getArenaByPlayer(player);
                if (args.length >= 3) {
                    if (args[2].equalsIgnoreCase("bed")) {
                        arena.startReSpawnSession(player, 0);
                        player.resetTitle();
                        player.sendTitle(ChatColor.AQUA + "Revived!", ChatColor.RED + "By " + p.getName());
                        ITeam mplayerteam = arena.getPlayerTeam(player.getName());
                        mplayerteam.setBedDestroyed(false);
                        BlockFace targetFace = Main.getInstance().beds.get(arena.getArenaName()+ mplayerteam.getName()+"Main");
                        placeBed(mplayerteam.getBed(), targetFace.getOppositeFace());
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully revived " + args[1]));
                        return;
                    } else if (args[2].equalsIgnoreCase("final")) {
                        arena.startReSpawnSession(player, 0);
                        player.resetTitle();
                        player.sendTitle(ChatColor.AQUA + "Revived!", ChatColor.RED + "By " + p.getName());
                        ITeam mplayerteam = arena.getPlayerTeam(player.getName());
                        mplayerteam.setBedDestroyed(true);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully revived " + args[1]));
                        return;
                    }
                }
            }
            p.sendMessage(ChatColor.RED + "Usage: /bw revive <player> <final ┃ bed>");
        }

        public void console(ConsoleCommandSender c, String[] args) {
            if (args.length > 2) {
                Player player = Bukkit.getPlayerExact(args[1]);
                if (player == null) {
                    c.sendMessage(ChatColor.RED + "Player not found");
                    return;
                }
                if (arenaUtil.getArenaByPlayer(player) == null) {
                    c.sendMessage(ChatColor.RED + "Player has not joined an arena");
                    return;
                }
                IArena arena = arenaUtil.getArenaByPlayer(player);
                if (args.length >= 3) {
                    if (args[2].equalsIgnoreCase("bed")) {
                        arena.startReSpawnSession(player, 0);
                        player.resetTitle();
                        player.sendTitle(ChatColor.AQUA + "Revived!", ChatColor.RED + "By " + c.getName());
                        ITeam mplayerteam = arena.getPlayerTeam(player.getName());
                        mplayerteam.setBedDestroyed(false);
                        BlockFace targetFace = Main.getInstance().beds.get(arena.getArenaName()+ mplayerteam.getName()+"Main");
                        placeBed(mplayerteam.getBed(), targetFace.getOppositeFace());
                        c.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully revived " + args[1]));
                        return;
                    } else if (args[2].equalsIgnoreCase("final")) {
                        arena.startReSpawnSession(player, 0);
                        player.resetTitle();
                        player.sendTitle(ChatColor.AQUA + "Revived!", ChatColor.RED + "By " + c.getName());
                        ITeam mplayerteam = arena.getPlayerTeam(player.getName());
                        mplayerteam.setBedDestroyed(true);
                        c.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully revived " + args[1]));
                        return;
                    }
                }
            c.sendMessage(ChatColor.RED + "Usage: /bw revive <player> <final ┃ bed>");
        }
    }

    private void placeBed(Location loc, BlockFace face) {

        BlockState bedFoot = loc.getBlock().getState();
        BlockState bedHead = bedFoot.getBlock().getRelative(face.getOppositeFace()).getState();

        bedFoot.setType(Material.BED_BLOCK);
        bedHead.setType(Material.BED_BLOCK);

        bedFoot.setRawData((byte) face.ordinal());
        bedHead.setRawData((byte) (face.ordinal()+8));

        bedFoot.update(true, false);
        bedHead.update(true, true);

    }

}