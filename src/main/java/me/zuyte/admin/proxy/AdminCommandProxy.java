package me.zuyte.admin.proxy;

import me.zuyte.admin.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class AdminCommandProxy implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r\n&8&lþ &6BedWars1058 Admin v" + Main.version + " &7- &cCommands\n \n&6• &7/bwa forcejoin <player> <arena> &8- &eForcejoin player into an arena"));
                return true;
            } else if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r\n&8&lþ &6BedWars1058 Admin v" + Main.version + " &7- &cCommands\n \n&6• &7/bwa forcejoin <player> <arena> &8- &eForcejoin player into an arena"));
                    return true;
                } else if (args[0].equalsIgnoreCase("forcejoin")) {
                    new forcejoinproxy().player(p, args);
                    return true;
                }
                p.sendMessage(ChatColor.RED + "Command not found.");
                return true;
            }
        }
        if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender c = (ConsoleCommandSender) sender;
            if (args.length == 0) {
                c.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r\n&8&lþ &6BedWars1058 Admin v" + Main.version + " &7- &cCommands\n \n&6• &7/bwa forcejoin <player> <arena> &8- &eForcejoin player into an arena"));
                return true;
            }
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    c.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r\n&8&lþ &6BedWars1058 Admin v" + Main.version + " &7- &cCommands\n \n&6• &7/bwa forcejoin <player> <arena> &8- &eForcejoin player into an arena"));
                    return true;
                } else if (args[0].equalsIgnoreCase("forcejoin")) {
                    new forcejoinproxy().console(c, args);
                    return true;
                }
                c.sendMessage(ChatColor.RED + "Command not found.");
                return true;
            }
        }
        return false;
    }
}
