package me.zuyte.admin;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.configuration.ConfigManager;
import me.zuyte.admin.commands.*;
import me.zuyte.admin.events.*;
import me.zuyte.admin.proxy.AdminCommandProxy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Main extends JavaPlugin {

    private static Main instance;
    public static String version;
//    private static ConfigManager cfg;
    public static BedWars bw;
    public Map<String, String> temp = new HashMap<String, String>();
    public Map<String, ItemStack> mlg = new HashMap<String, ItemStack>();
    public Map<String, String> kaboom = new HashMap<String, String>();
    public Map<String, BlockFace> beds = new HashMap<String, BlockFace>();
    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("BedWars1058") != null) {
//            cfg = new ConfigManager(this, "config", "plugins/BedWars1058/Addons/AdminAddon");
//            setupConfig();
            getLogger().info(ChatColor.translateAlternateColorCodes('&',"&fFound Bedwars1058 & hooked into."));
            setup();
        } else if (Bukkit.getPluginManager().getPlugin("BedWarsProxy") != null) {
            getLogger().info(ChatColor.translateAlternateColorCodes('&', "&fFound Bedwars1058 Proxy & hooked into."));
            setupProxy();
        } else {
            getLogger().severe("BedWars1058 plugin was not found. Disabling...");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    private void setup() {
        instance = this;
        version = getDescription().getVersion();
        bw = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
        new admin(bw.getBedWarsCommand(), "admin");
        getCommand("bwa").setExecutor(new AdminCommand());
        getCommand("bwa").setTabCompleter(new AdminTabCompleter());
        if (Bukkit.getPluginManager().getPlugin("BedWars1058-TeamSelector") == null) {
            getLogger().warning("Bedwars1058-TeamSelector Plugin was not found.");
        } else {
            getServer().getPluginManager().registerEvents(new Teamselect(), this);
            getLogger().info(ChatColor.translateAlternateColorCodes('&', "&fFound Bedwars1058-TeamSelector & hooked into."));
        }
        getServer().getPluginManager().registerEvents(new Assignevent(), this);
        getServer().getPluginManager().registerEvents(new Arenastart(), this);
        getServer().getPluginManager().registerEvents(new Arenaleave(), this);
        getServer().getPluginManager().registerEvents(new WBDeath(), this);
        getServer().getPluginManager().registerEvents(new WBPlace(), this);
        getServer().getPluginManager().registerEvents(new Damage(), this);
        getServer().getPluginManager().registerEvents(new EntityDamage(), this);
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
        getServer().getPluginManager().registerEvents(new GUIListener() , this);
        getLogger().info(ChatColor.translateAlternateColorCodes('&',"&aRunning Bedwars1058-AdminAddon &fv" + getDescription().getVersion() + " &7- &eBy Zuyte"));
    }

    private void setupProxy() {
        instance = this;
        version = getDescription().getVersion();
        getCommand("bwa").setExecutor(new AdminCommandProxy());
        getCommand("bwa").setTabCompleter(new AdminTabCompleter());
        getLogger().info(ChatColor.translateAlternateColorCodes('&',"&aRunning Bedwars1058-AdminAddon &fv" + getDescription().getVersion() + " &7- &eBy Zuyte"));
    }

//    private static void setupConfig() {
//        YamlConfiguration yml = getCfg().getYml();
//        yml.options().header("Bedwars1058 AdminAddon adds admin & troll features!" +
//                "\nDocumentation: https://zuyte.netlify.app/\nFound any bugs? report them at https://discord.gg/Gmb8JAVR6H\nSet your kickall message below! {sec} replaces with the seconds.");
//        // if anyone is wondering what debug does currently, there's no use.
//        yml.addDefault(ConfigPath.DEBUG, false);
//        yml.addDefault(ConfigPath.KICKALL_MSG, "&cThe Arena will be Restarting in {sec} seconds.\n&cEveryone will be kicked from the arena.");
//        yml.options().copyDefaults(true);
//        getCfg().save();
//    }

//    public static ConfigManager getCfg() {
//        return cfg;
//    }

    public static Main getInstance(){
        return instance;
    }
}