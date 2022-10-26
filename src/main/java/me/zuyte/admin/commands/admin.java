package me.zuyte.admin.commands;

import com.andrei1058.bedwars.api.command.ParentCommand;
import com.andrei1058.bedwars.api.command.SubCommand;
import me.zuyte.admin.Main;
import net.md_5.bungee.api.chat.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class admin extends SubCommand {
    public admin(ParentCommand parent, String name) {
        super(parent, name);
        showInList(true);
        setDisplayInfo(textComponentBuilder("§6 ▪ §7/bw " + getSubCommandName() + " §8- §eAdmin commands list"));
        setPriority(20);
        setArenaSetupCommand(false);
        setPermission("bw.admin");
    }

    @Override
    public boolean execute(String[] args, CommandSender commandSender) {
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if (!p.hasPermission("bw.admin"))return false;
            if (args.length == 0) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r\n&8&lþ &6BedWars1058 Admin v" + Main.version + " &7- &cCommands\n \n&6• &7/bwa forcejoin <player> <arena> &8- &eForcejoin player into a arena\n&6• &7/bwa setteam <player> &8- &eSet the player's team\n&6• &7/bwa revive <player> <final ┃ bed> &8- &eRevives the player\n&6• &7/bwa setbed <player> <true ┃ false> &8 &eSet a team's bed status\n&6• &7/bwa nextevent <arena> <event> &8- &eSet an arena's next event\n&6• &7/bwa skipevent <arena> &8- &eSkip upcoming event\n \n&6• &7/bwa troll mlg <player> &8- &eMakes the player complete an mlg\n&6• &7/bwa troll cage <player> &8- &eTraps the player in a breakable glass cage\n&6• &7/bwa troll blind <player> <seconds> &8- &eBlinds the player\n&6• &7/bwa troll slowhands <player> <seconds> &8- &eSlows mining speed of the player\n&6• &7/bwa troll kaboom <player> &8- &eLaunched player in the air\n&6• &7/bwa troll toystick <player> &8- &eGives the player an explosive toystick\n&6• &7/bwa troll ghast <player> &8- &eSpawns ghast at the player\n&6• &7/bwa troll mobattack <player> &8- &eSpawns Zombies & Skeletons at the player"));
            }
        }
        if (commandSender instanceof ConsoleCommandSender){
            ConsoleCommandSender c = (ConsoleCommandSender) commandSender;
            if (args.length == 0) {
                c.sendMessage(ChatColor.translateAlternateColorCodes('&', "&r \n&r \n&8&lþ &6BedWars1058 Admin v" + Main.version + " &7- &cCommands\n \n&6• &7/bwa forcejoin <player> <arena> &8- &eForcejoin player into a arena\n&6• &7/bwa setteam <player> <team> &8- &eSet the player's team\n&6• &7/bwa revive <player> <final ┃ bed> &8- &eRevives the player\n&6• &7/bwa setbed <player> <true ┃ false> &8 &eSet a team's bed status\n&6• &7/bwa nextevent <arena> <event> &8- &eSet an arena's next event\n&6• &7/bwa skipevent <arena> &8- &eSkip upcoming event\n \n&6• &7/bwa troll mlg <player> &8- &eMakes the player complete an mlg\n&6• &7/bwa troll cage <player> &8- &eTraps the player in a breakable glass cage\n&6• &7/bwa troll blind <player> <seconds> &8- &eBlinds the player\n&6• &7/bwa troll slowhands <player> <seconds> &8- &eSlows mining speed of the player\n&6• &7/bwa troll kaboom <player> &8- &eLaunched player in the air\n&6• &7/bwa troll toystick <player> &8- &eGives the player an explosive toystick\n&6• &7/bwa troll ghast <player> &8- &eSpawns ghast at the player\n&6• &7/bwa troll mobattack <player> &8- &eSpawns Zombies & Skeletons at the player"));
            }
        }
        return true;
    }

    @Override
    public List<String> getTabComplete() {
        return null;
    }

    public TextComponent textComponentBuilder(String s){
        TextComponent textComponent = new TextComponent(s);
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/bw " + getSubCommandName()));
        textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Sends admin commands list").create()));
        return textComponent;
    }
}