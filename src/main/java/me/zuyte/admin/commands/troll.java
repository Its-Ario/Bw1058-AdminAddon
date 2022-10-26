package me.zuyte.admin.commands;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.arena.IArena;
import me.zuyte.admin.Main;
import org.bukkit.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class troll{

    BedWars.ArenaUtil arenaUtil = Main.getInstance().bw.getArenaUtil();

    public void player(Player p, String[] args) {
            if (!p.hasPermission("bw.admin.troll")) {
                p.sendMessage(ChatColor.RED + "You dont have permission to use this command.");
                return;
            }
            if (args.length > 1) {
                if (args[1].equalsIgnoreCase("mlg")) {
                    if (args.length >= 3) {
                        Player player = Bukkit.getPlayerExact(args[2]);
                        if (player == null) {
                            p.sendMessage(ChatColor.RED + "Player not found");
                            return;
                        }
                        if (!arenaUtil.isPlaying(player)) {
                            p.sendMessage(ChatColor.RED + "Player is not playing");
                            return;
                        }
                        Location playerLoc = player.getLocation();
                        playerLoc.setY(playerLoc.getY()+50);
                        playerLoc.setPitch(90);
                        playerLoc.setYaw(-90);
                        player.teleport(playerLoc);
                        Main.getInstance().mlg.put(player.getName(), player.getItemInHand());
                        player.setItemInHand(new ItemStack(Material.WATER_BUCKET));
                        player.sendMessage(ChatColor.AQUA + "MLG TIME!!");
                        return;
                    }
                }
                if (args[1].equalsIgnoreCase("cage")) {
                    if (args.length >= 3) {
                        Player player = Bukkit.getPlayerExact(args[2]);
                        if (player == null) {
                            p.sendMessage(ChatColor.RED + "Player not found");
                            return;
                        }
                        if (!arenaUtil.isPlaying(player)) {
                            p.sendMessage(ChatColor.RED + "Player is not playing");
                            return;
                        }
                        IArena playerArena = arenaUtil.getArenaByPlayer(player);
                        int x = player.getLocation().getBlockX();
                        int y = player.getLocation().getBlockY();
                        int z = player.getLocation().getBlockZ();
                        if (player.getWorld().getBlockAt(x,y+2,z).getType() == Material.AIR) {
                            player.getWorld().getBlockAt(x, y+2, z).setType(Material.GLASS);
                            playerArena.addPlacedBlock(player.getWorld().getBlockAt(x, y+2, z));
                        }
                        if (player.getWorld().getBlockAt(x,y-1,z).getType() == Material.AIR) {
                            player.getWorld().getBlockAt(x, y-1, z).setType(Material.GLASS);
                            playerArena.addPlacedBlock(player.getWorld().getBlockAt(x, y-1, z));
                        }
                        if (player.getWorld().getBlockAt(x,y,z+1).getType() == Material.AIR) {
                            player.getWorld().getBlockAt(x, y, z+1).setType(Material.GLASS);
                            playerArena.addPlacedBlock(player.getWorld().getBlockAt(x, y, z+1));
                        }
                        if (player.getWorld().getBlockAt(x,y,z-1).getType() == Material.AIR) {
                            player.getWorld().getBlockAt(x, y, z-1).setType(Material.GLASS);
                            playerArena.addPlacedBlock(player.getWorld().getBlockAt(x,y,z-1));
                        }
                        if (player.getWorld().getBlockAt(x+1,y,z).getType() == Material.AIR) {
                            player.getWorld().getBlockAt(x+1, y, z).setType(Material.GLASS);
                            playerArena.addPlacedBlock(player.getWorld().getBlockAt(x+1, y, z));
                        }
                        if (player.getWorld().getBlockAt(x-1,y,z).getType() == Material.AIR) {
                            player.getWorld().getBlockAt(x-1, y, z).setType(Material.GLASS);
                            playerArena.addPlacedBlock(player.getWorld().getBlockAt(x-1, y, z));
                        }
                        // above layer
                        if (player.getWorld().getBlockAt(x, y+1, z+1).getType() == Material.AIR) {
                            player.getWorld().getBlockAt(x, y+1, z+1).setType(Material.GLASS);
                            playerArena.addPlacedBlock(player.getWorld().getBlockAt(x, y+1, z+1));
                        }
                        if (player.getWorld().getBlockAt(x, y+1, z-1).getType() == Material.AIR) {
                            player.getWorld().getBlockAt(x, y+1, z-1).setType(Material.GLASS);
                            playerArena.addPlacedBlock(player.getWorld().getBlockAt(x, y+1, z-1));
                        }
                        if (player.getWorld().getBlockAt(x+1, y+1, z).getType() == Material.AIR) {
                            player.getWorld().getBlockAt(x+1, y+1, z).setType(Material.GLASS);
                            playerArena.addPlacedBlock(player.getWorld().getBlockAt(x+1, y+1, z));
                        }
                        if (player.getWorld().getBlockAt(x-1, y+1, z).getType() == Material.AIR) {
                            player.getWorld().getBlockAt(x-1, y+1, z).setType(Material.GLASS);
                            playerArena.addPlacedBlock(player.getWorld().getBlockAt(x-1, y+1, z));
                        }
                        p.sendMessage(ChatColor.LIGHT_PURPLE + "Trapped " + args[2]);
                        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                if (player.getWorld().getBlockAt(x,y+2,z).getType() == Material.GLASS) {
                                    player.getWorld().getBlockAt(x, y+2, z).setType(Material.AIR);
                                }
                                if (player.getWorld().getBlockAt(x,y-1,z).getType() == Material.GLASS) {
                                    player.getWorld().getBlockAt(x, y-1, z).setType(Material.AIR);
                                }
                                if (player.getWorld().getBlockAt(x,y,z+1).getType() == Material.GLASS) {
                                    player.getWorld().getBlockAt(x, y, z+1).setType(Material.AIR);
                                }
                                if (player.getWorld().getBlockAt(x,y,z-1).getType() == Material.GLASS) {
                                    player.getWorld().getBlockAt(x, y, z-1).setType(Material.AIR);
                                }
                                if (player.getWorld().getBlockAt(x+1,y,z).getType() == Material.GLASS) {
                                    player.getWorld().getBlockAt(x+1, y, z).setType(Material.AIR);
                                }
                                if (player.getWorld().getBlockAt(x-1,y,z).getType() == Material.GLASS) {
                                    player.getWorld().getBlockAt(x-1, y, z).setType(Material.AIR);
                                }
                                // above layer
                                if (player.getWorld().getBlockAt(x, y+1, z+1).getType() == Material.GLASS) {
                                    player.getWorld().getBlockAt(x, y+1, z+1).setType(Material.AIR);
                                }
                                if (player.getWorld().getBlockAt(x, y+1, z-1).getType() == Material.GLASS) {
                                    player.getWorld().getBlockAt(x, y+1, z-1).setType(Material.AIR);
                                }
                                if (player.getWorld().getBlockAt(x+1, y+1, z).getType() == Material.GLASS) {
                                    player.getWorld().getBlockAt(x+1, y+1, z).setType(Material.AIR);
                                }
                                if (player.getWorld().getBlockAt(x-1, y+1, z).getType() == Material.GLASS) {
                                    player.getWorld().getBlockAt(x-1, y+1, z).setType(Material.AIR);
                                }
                            }
                        }, 1200L);
                        return;
                    }
                }
                if (args[1].equalsIgnoreCase("blind")) {
                    if (args.length >= 3) {
                        Player player = Bukkit.getPlayerExact(args[2]);
                        if (player == null) {
                            p.sendMessage(ChatColor.RED + "Player not found");
                            return;
                        }
                        if (!arenaUtil.isPlaying(player)) {
                            p.sendMessage(ChatColor.RED + "Player is not playing");
                            return;
                        }
                        if (args.length >= 4) {
                            try {
                                Integer.parseInt(args[3]);
                            } catch (NumberFormatException nfe) {
                                p.sendMessage(ChatColor.RED + "Please enter a number.");
                                return;
                            }
                            int duration = Integer.parseInt(args[3]);
                            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, duration*20, 1));
                            p.sendMessage(ChatColor.RED + args[2] + " is blinded for " + args[3] + " seconds");
                            return;
                        }
                    }
                }
                if (args[1].equalsIgnoreCase("slowhands")) {
                    if (args.length >= 3) {
                        Player player = Bukkit.getPlayerExact(args[2]);
                        if (player == null) {
                            p.sendMessage(ChatColor.RED + "Player not found");
                            return;
                        }
                        if (!arenaUtil.isPlaying(player)) {
                            p.sendMessage(ChatColor.RED + "Player is not playing");
                            return;
                        }
                        if (args.length >= 4) {
                            try {
                                Integer.parseInt(args[3]);
                            } catch (NumberFormatException nfe) {
                                p.sendMessage(ChatColor.RED + "Please enter a number.");
                                return;
                            }
                            int duration = Integer.parseInt(args[3]);
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, duration*20, 1));
                            p.sendMessage(ChatColor.RED + args[2] + " has slowhands for " + args[3] + " seconds");
                            return;
                        }
                    }
                }
                if (args[1].equalsIgnoreCase("kaboom")) {
                    if (args.length >= 3) {
                        Player player = Bukkit.getPlayerExact(args[2]);
                        if (player == null) {
                            p.sendMessage(ChatColor.RED + "Player not found");
                            return;
                        }
                        if (!arenaUtil.isPlaying(player)) {
                            p.sendMessage(ChatColor.RED + "Player is not playing");
                            return;
                        }
                        Main.getInstance().kaboom.put(player.getDisplayName(), "true");
                        Location playerLocation = player.getLocation();
                        player.setVelocity(new Vector(0, 35, 0));
                        player.getWorld().strikeLightningEffect(playerLocation);
                        player.getWorld().strikeLightningEffect(playerLocation);
                        player.getWorld().strikeLightningEffect(playerLocation);
                        // 1st
                        player.getWorld().strikeLightningEffect(playerLocation);
                        player.getWorld().strikeLightningEffect(playerLocation);
                        player.getWorld().strikeLightningEffect(playerLocation);
                        // 2nd
                        player.getWorld().strikeLightningEffect(playerLocation);
                        player.getWorld().strikeLightningEffect(playerLocation);
                        player.getWorld().strikeLightningEffect(playerLocation);
                        return;
                    }
                }
                if (args[1].equalsIgnoreCase("ghast")) {
                    if (args.length >= 3) {
                        Player player = Bukkit.getPlayerExact(args[2]);
                        if (player == null) {
                            p.sendMessage(ChatColor.RED + "Player not found");
                            return;
                        }
                        if (!arenaUtil.isPlaying(player)) {
                            p.sendMessage(ChatColor.RED + "Player is not playing");
                            return;
                        }
                        player.getWorld().setGameRuleValue("doFireTick", "false");
                        player.getWorld().setGameRuleValue("doMobLoot", "false");
                        player.getWorld().spawnEntity(player.getLocation(), EntityType.GHAST);
                        p.sendMessage(ChatColor.RED + "Summoned Ghast at " + args[2]);
                        return;
                    }
                }
                if (args[1].equalsIgnoreCase("mobattack")) {
                    if (args.length >= 3) {
                        Player player = Bukkit.getPlayerExact(args[2]);
                        if (player == null) {
                            p.sendMessage(ChatColor.RED + "Player not found");
                            return;
                        }
                        if (!arenaUtil.isPlaying(player)) {
                            p.sendMessage(ChatColor.RED + "Player is not playing");
                            return;
                        }
                        // 1st zombie
                        player.getWorld().setGameRuleValue("doMobLoot", "false");
                        Entity zombie = player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
                        LivingEntity livingZombie = (LivingEntity) zombie;
                        livingZombie.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
                        livingZombie.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                        livingZombie.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                        livingZombie.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                        livingZombie.getEquipment().setHelmetDropChance(0);
                        livingZombie.getEquipment().setChestplateDropChance(0);
                        livingZombie.getEquipment().setLeggingsDropChance(0);
                        livingZombie.getEquipment().setBootsDropChance(0);
                        livingZombie.setCustomName(ChatColor.RED + player.getName() + "'s Enemy!");
                        // 2nd zombie
                        Entity zombie1 = player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
                        LivingEntity livingZombie1 = (LivingEntity) zombie1;
                        livingZombie1.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
                        livingZombie1.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                        livingZombie1.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                        livingZombie1.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                        livingZombie1.getEquipment().setHelmetDropChance(0);
                        livingZombie1.getEquipment().setChestplateDropChance(0);
                        livingZombie1.getEquipment().setLeggingsDropChance(0);
                        livingZombie1.getEquipment().setBootsDropChance(0);
                        livingZombie1.setCustomName(ChatColor.RED + player.getName() + "'s Enemy!");
                        // 1st skeleton
                        Entity skeleton = player.getWorld().spawnEntity(player.getLocation(), EntityType.SKELETON);
                        LivingEntity livingSkeleton = (LivingEntity) skeleton;
                        livingSkeleton.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
                        livingSkeleton.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                        livingSkeleton.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                        livingSkeleton.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                        livingSkeleton.getEquipment().setHelmetDropChance(0);
                        livingSkeleton.getEquipment().setChestplateDropChance(0);
                        livingSkeleton.getEquipment().setLeggingsDropChance(0);
                        livingSkeleton.getEquipment().setBootsDropChance(0);
                        livingSkeleton.setCustomName(ChatColor.RED + player.getName() + "'s Enemy!");
                        // 2nd skeleton
                        Entity skeleton1 = player.getWorld().spawnEntity(player.getLocation(), EntityType.SKELETON);
                        LivingEntity livingSkeleton1 = (LivingEntity) skeleton1;
                        livingSkeleton1.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
                        livingSkeleton1.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                        livingSkeleton1.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                        livingSkeleton1.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                        livingSkeleton1.getEquipment().setHelmetDropChance(0);
                        livingSkeleton1.getEquipment().setChestplateDropChance(0);
                        livingSkeleton1.getEquipment().setLeggingsDropChance(0);
                        livingSkeleton1.getEquipment().setBootsDropChance(0);
                        livingSkeleton1.setCustomName(ChatColor.RED + player.getName() + "'s Enemy!");
                        // done
                        p.sendMessage(ChatColor.RED + "Summoned Zombies & Skeletons at " + args[2]);
                        return;
                    }
                }
                if (args[1].equalsIgnoreCase("toystick")) {
                    if (args.length >= 3) {
                        Player player = Bukkit.getPlayerExact(args[2]);
                        if (player == null) {
                            p.sendMessage(ChatColor.RED + "Player not found");
                            return;
                        }
                        if (!arenaUtil.isPlaying(player)) {
                            p.sendMessage(ChatColor.RED + "Player is not playing");
                            return;
                        }
                        ItemStack toyStick = new ItemStack(Material.STICK, 1);
                        toyStick.addUnsafeEnchantment(Enchantment.LURE, 1);
                        ItemMeta meta = toyStick.getItemMeta();
                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bMagic Toy Stick"));
                        List<String> loreList = new ArrayList<String>();
                        loreList.add(ChatColor.translateAlternateColorCodes('&', "&eClick me for an explosion!"));
                        meta.setLore(loreList);
                        toyStick.setItemMeta(meta);
                        player.getInventory().addItem(toyStick);
                        p.sendMessage(ChatColor.GREEN + "Gave " + args[2] + " a toystick!");
                        return;
                    }
                }
                if (args[1].equalsIgnoreCase("mlg") || args[1].equalsIgnoreCase("cage") || args[1].equalsIgnoreCase("kaboom") || args[1].equalsIgnoreCase("toystick") || args[1].equalsIgnoreCase("ghast") || args[1].equalsIgnoreCase("mobattack")) {
                 p.sendMessage(ChatColor.RED + "Usage: /bwa troll " + args[1].toLowerCase() + " <player>");
                 return;
                } else if (args[1].equalsIgnoreCase("blind") || args[1].equalsIgnoreCase("slowhands")) {
                    p.sendMessage(ChatColor.RED + "Usage: /bwa troll " + args[1].toLowerCase() + " <player> <seconds>");
                return;
                }
                p.sendMessage(ChatColor.RED + "Unknown troll command.");
            }
        }

    public void console(ConsoleCommandSender p, String[] args) {
        if (args.length > 1) {
            if (args[1].equalsIgnoreCase("mlg")) {
                if (args.length >= 3) {
                    Player player = Bukkit.getPlayerExact(args[2]);
                    if (player == null) {
                        p.sendMessage(ChatColor.RED + "Player not found");
                        return;
                    }
                    if (!arenaUtil.isPlaying(player)) {
                        p.sendMessage(ChatColor.RED + "Player is not playing");
                        return;
                    }
                    Location playerLoc = player.getLocation();
                    playerLoc.setY(playerLoc.getY()+50);
                    playerLoc.setPitch(90);
                    playerLoc.setYaw(-90);
                    player.teleport(playerLoc);
                    Main.getInstance().mlg.put(player.getName(), player.getItemInHand());
                    player.setItemInHand(new ItemStack(Material.WATER_BUCKET));
                    player.sendMessage(ChatColor.AQUA + "MLG TIME!!");
                    return;
                }
            }
            if (args[1].equalsIgnoreCase("cage")) {
                if (args.length >= 3) {
                    Player player = Bukkit.getPlayerExact(args[2]);
                    if (player == null) {
                        p.sendMessage(ChatColor.RED + "Player not found");
                        return;
                    }
                    if (!arenaUtil.isPlaying(player)) {
                        p.sendMessage(ChatColor.RED + "Player is not playing");
                        return;
                    }
                    IArena playerArena = arenaUtil.getArenaByPlayer(player);
                    int x = player.getLocation().getBlockX();
                    int y = player.getLocation().getBlockY();
                    int z = player.getLocation().getBlockZ();
                    if (player.getWorld().getBlockAt(x,y+2,z).getType() == Material.AIR) {
                        player.getWorld().getBlockAt(x, y+2, z).setType(Material.GLASS);
                        playerArena.addPlacedBlock(player.getWorld().getBlockAt(x, y+2, z));
                    }
                    if (player.getWorld().getBlockAt(x,y-1,z).getType() == Material.AIR) {
                        player.getWorld().getBlockAt(x, y-1, z).setType(Material.GLASS);
                        playerArena.addPlacedBlock(player.getWorld().getBlockAt(x, y-1, z));
                    }
                    if (player.getWorld().getBlockAt(x,y,z+1).getType() == Material.AIR) {
                        player.getWorld().getBlockAt(x, y, z+1).setType(Material.GLASS);
                        playerArena.addPlacedBlock(player.getWorld().getBlockAt(x, y, z+1));
                    }
                    if (player.getWorld().getBlockAt(x,y,z-1).getType() == Material.AIR) {
                        player.getWorld().getBlockAt(x, y, z-1).setType(Material.GLASS);
                        playerArena.addPlacedBlock(player.getWorld().getBlockAt(x,y,z-1));
                    }
                    if (player.getWorld().getBlockAt(x+1,y,z).getType() == Material.AIR) {
                        player.getWorld().getBlockAt(x+1, y, z).setType(Material.GLASS);
                        playerArena.addPlacedBlock(player.getWorld().getBlockAt(x+1, y, z));
                    }
                    if (player.getWorld().getBlockAt(x-1,y,z).getType() == Material.AIR) {
                        player.getWorld().getBlockAt(x-1, y, z).setType(Material.GLASS);
                        playerArena.addPlacedBlock(player.getWorld().getBlockAt(x-1, y, z));
                    }
                    // above layer
                    if (player.getWorld().getBlockAt(x, y+1, z+1).getType() == Material.AIR) {
                        player.getWorld().getBlockAt(x, y+1, z+1).setType(Material.GLASS);
                        playerArena.addPlacedBlock(player.getWorld().getBlockAt(x, y+1, z+1));
                    }
                    if (player.getWorld().getBlockAt(x, y+1, z-1).getType() == Material.AIR) {
                        player.getWorld().getBlockAt(x, y+1, z-1).setType(Material.GLASS);
                        playerArena.addPlacedBlock(player.getWorld().getBlockAt(x, y+1, z-1));
                    }
                    if (player.getWorld().getBlockAt(x+1, y+1, z).getType() == Material.AIR) {
                        player.getWorld().getBlockAt(x+1, y+1, z).setType(Material.GLASS);
                        playerArena.addPlacedBlock(player.getWorld().getBlockAt(x+1, y+1, z));
                    }
                    if (player.getWorld().getBlockAt(x-1, y+1, z).getType() == Material.AIR) {
                        player.getWorld().getBlockAt(x-1, y+1, z).setType(Material.GLASS);
                        playerArena.addPlacedBlock(player.getWorld().getBlockAt(x-1, y+1, z));
                    }
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "Trapped " + args[2]);
                    Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            if (player.getWorld().getBlockAt(x,y+2,z).getType() == Material.GLASS) {
                                player.getWorld().getBlockAt(x, y+2, z).setType(Material.AIR);
                            }
                            if (player.getWorld().getBlockAt(x,y-1,z).getType() == Material.GLASS) {
                                player.getWorld().getBlockAt(x, y-1, z).setType(Material.AIR);
                            }
                            if (player.getWorld().getBlockAt(x,y,z+1).getType() == Material.GLASS) {
                                player.getWorld().getBlockAt(x, y, z+1).setType(Material.AIR);
                            }
                            if (player.getWorld().getBlockAt(x,y,z-1).getType() == Material.GLASS) {
                                player.getWorld().getBlockAt(x, y, z-1).setType(Material.AIR);
                            }
                            if (player.getWorld().getBlockAt(x+1,y,z).getType() == Material.GLASS) {
                                player.getWorld().getBlockAt(x+1, y, z).setType(Material.AIR);
                            }
                            if (player.getWorld().getBlockAt(x-1,y,z).getType() == Material.GLASS) {
                                player.getWorld().getBlockAt(x-1, y, z).setType(Material.AIR);
                            }
                            // above layer
                            if (player.getWorld().getBlockAt(x, y+1, z+1).getType() == Material.GLASS) {
                                player.getWorld().getBlockAt(x, y+1, z+1).setType(Material.AIR);
                            }
                            if (player.getWorld().getBlockAt(x, y+1, z-1).getType() == Material.GLASS) {
                                player.getWorld().getBlockAt(x, y+1, z-1).setType(Material.AIR);
                            }
                            if (player.getWorld().getBlockAt(x+1, y+1, z).getType() == Material.GLASS) {
                                player.getWorld().getBlockAt(x+1, y+1, z).setType(Material.AIR);
                            }
                            if (player.getWorld().getBlockAt(x-1, y+1, z).getType() == Material.GLASS) {
                                player.getWorld().getBlockAt(x-1, y+1, z).setType(Material.AIR);
                            }
                        }
                    }, 1200L);
                    return;
                }
            }
            if (args[1].equalsIgnoreCase("blind")) {
                if (args.length >= 3) {
                    Player player = Bukkit.getPlayerExact(args[2]);
                    if (player == null) {
                        p.sendMessage(ChatColor.RED + "Player not found");
                        return;
                    }
                    if (!arenaUtil.isPlaying(player)) {
                        p.sendMessage(ChatColor.RED + "Player is not playing");
                        return;
                    }
                    if (args.length >= 4) {
                        try {
                            Integer.parseInt(args[3]);
                        } catch (NumberFormatException nfe) {
                            p.sendMessage(ChatColor.RED + "Please enter a number.");
                            return;
                        }
                        int duration = Integer.parseInt(args[3]);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, duration*20, 1));
                        p.sendMessage(ChatColor.RED + args[2] + " is blinded for " + args[3] + " seconds");
                        return;
                    }
                }
            }
            if (args[1].equalsIgnoreCase("slowhands")) {
                if (args.length >= 3) {
                    Player player = Bukkit.getPlayerExact(args[2]);
                    if (player == null) {
                        p.sendMessage(ChatColor.RED + "Player not found");
                        return;
                    }
                    if (!arenaUtil.isPlaying(player)) {
                        p.sendMessage(ChatColor.RED + "Player is not playing");
                        return;
                    }
                    if (args.length >= 4) {
                        try {
                            Integer.parseInt(args[3]);
                        } catch (NumberFormatException nfe) {
                            p.sendMessage(ChatColor.RED + "Please enter a number.");
                            return;
                        }
                        int duration = Integer.parseInt(args[3]);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, duration*20, 1));
                        p.sendMessage(ChatColor.RED + args[2] + " has slowhands for " + args[3] + " seconds");
                        return;
                    }
                }
            }
            if (args[1].equalsIgnoreCase("kaboom")) {
                if (args.length >= 3) {
                    Player player = Bukkit.getPlayerExact(args[2]);
                    if (player == null) {
                        p.sendMessage(ChatColor.RED + "Player not found");
                        return;
                    }
                    if (!arenaUtil.isPlaying(player)) {
                        p.sendMessage(ChatColor.RED + "Player is not playing");
                        return;
                    }
                    Main.getInstance().kaboom.put(player.getDisplayName(), "true");
                    Location playerLocation = player.getLocation();
                    player.setVelocity(new Vector(0, 35, 0));
                    player.getWorld().strikeLightningEffect(playerLocation);
                    player.getWorld().strikeLightningEffect(playerLocation);
                    player.getWorld().strikeLightningEffect(playerLocation);
                    // 1st
                    player.getWorld().strikeLightningEffect(playerLocation);
                    player.getWorld().strikeLightningEffect(playerLocation);
                    player.getWorld().strikeLightningEffect(playerLocation);
                    // 2nd
                    player.getWorld().strikeLightningEffect(playerLocation);
                    player.getWorld().strikeLightningEffect(playerLocation);
                    player.getWorld().strikeLightningEffect(playerLocation);
                    return;
                }
            }
            if (args[1].equalsIgnoreCase("ghast")) {
                if (args.length >= 3) {
                    Player player = Bukkit.getPlayerExact(args[2]);
                    if (player == null) {
                        p.sendMessage(ChatColor.RED + "Player not found");
                        return;
                    }
                    if (!arenaUtil.isPlaying(player)) {
                        p.sendMessage(ChatColor.RED + "Player is not playing");
                        return;
                    }
                    player.getWorld().setGameRuleValue("doFireTick", "false");
                    player.getWorld().setGameRuleValue("doMobLoot", "false");
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.GHAST);
                    p.sendMessage(ChatColor.RED + "Summoned Ghast at " + args[2]);
                    return;
                }
            }
            if (args[1].equalsIgnoreCase("mobattack")) {
                if (args.length >= 3) {
                    Player player = Bukkit.getPlayerExact(args[2]);
                    if (player == null) {
                        p.sendMessage(ChatColor.RED + "Player not found");
                        return;
                    }
                    if (!arenaUtil.isPlaying(player)) {
                        p.sendMessage(ChatColor.RED + "Player is not playing");
                        return;
                    }
                    // 1st zombie
                    player.getWorld().setGameRuleValue("doMobLoot", "false");
                    Entity zombie = player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
                    LivingEntity livingZombie = (LivingEntity) zombie;
                    livingZombie.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
                    livingZombie.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                    livingZombie.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                    livingZombie.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                    livingZombie.getEquipment().setHelmetDropChance(0);
                    livingZombie.getEquipment().setChestplateDropChance(0);
                    livingZombie.getEquipment().setLeggingsDropChance(0);
                    livingZombie.getEquipment().setBootsDropChance(0);
                    livingZombie.setCustomName(ChatColor.RED + player.getName() + "'s Enemy!");
                    // 2nd zombie
                    Entity zombie1 = player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
                    LivingEntity livingZombie1 = (LivingEntity) zombie1;
                    livingZombie1.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
                    livingZombie1.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                    livingZombie1.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                    livingZombie1.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                    livingZombie1.getEquipment().setHelmetDropChance(0);
                    livingZombie1.getEquipment().setChestplateDropChance(0);
                    livingZombie1.getEquipment().setLeggingsDropChance(0);
                    livingZombie1.getEquipment().setBootsDropChance(0);
                    livingZombie1.setCustomName(ChatColor.RED + player.getName() + "'s Enemy!");
                    // 1st skeleton
                    Entity skeleton = player.getWorld().spawnEntity(player.getLocation(), EntityType.SKELETON);
                    LivingEntity livingSkeleton = (LivingEntity) skeleton;
                    livingSkeleton.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
                    livingSkeleton.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                    livingSkeleton.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                    livingSkeleton.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                    livingSkeleton.getEquipment().setHelmetDropChance(0);
                    livingSkeleton.getEquipment().setChestplateDropChance(0);
                    livingSkeleton.getEquipment().setLeggingsDropChance(0);
                    livingSkeleton.getEquipment().setBootsDropChance(0);
                    livingSkeleton.setCustomName(ChatColor.RED + player.getName() + "'s Enemy!");
                    // 2nd skeleton
                    Entity skeleton1 = player.getWorld().spawnEntity(player.getLocation(), EntityType.SKELETON);
                    LivingEntity livingSkeleton1 = (LivingEntity) skeleton1;
                    livingSkeleton1.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
                    livingSkeleton1.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                    livingSkeleton1.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
                    livingSkeleton1.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
                    livingSkeleton1.getEquipment().setHelmetDropChance(0);
                    livingSkeleton1.getEquipment().setChestplateDropChance(0);
                    livingSkeleton1.getEquipment().setLeggingsDropChance(0);
                    livingSkeleton1.getEquipment().setBootsDropChance(0);
                    livingSkeleton1.setCustomName(ChatColor.RED + player.getName() + "'s Enemy!");
                    // done
                    p.sendMessage(ChatColor.RED + "Summoned Zombies & Skeletons at " + args[2]);
                    return;
                }
            }
            if (args[1].equalsIgnoreCase("toystick")) {
                if (args.length >= 3) {
                    Player player = Bukkit.getPlayerExact(args[2]);
                    if (player == null) {
                        p.sendMessage(ChatColor.RED + "Player not found");
                        return;
                    }
                    if (!arenaUtil.isPlaying(player)) {
                        p.sendMessage(ChatColor.RED + "Player is not playing");
                        return;
                    }
                    ItemStack toyStick = new ItemStack(Material.STICK, 1);
                    toyStick.addUnsafeEnchantment(Enchantment.LURE, 1);
                    ItemMeta meta = toyStick.getItemMeta();
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bMagic Toy Stick"));
                    List<String> loreList = new ArrayList<String>();
                    loreList.add(ChatColor.translateAlternateColorCodes('&', "&eClick me for an explosion!"));
                    meta.setLore(loreList);
                    toyStick.setItemMeta(meta);
                    player.getInventory().addItem(toyStick);
                    p.sendMessage(ChatColor.GREEN + "Gave " + args[2] + " a toystick!");
                    return;
                }
            }
            if (args[1].equalsIgnoreCase("mlg") || args[1].equalsIgnoreCase("cage") || args[1].equalsIgnoreCase("kaboom") || args[1].equalsIgnoreCase("toystick") || args[1].equalsIgnoreCase("ghast") || args[1].equalsIgnoreCase("mobattack")) {
                p.sendMessage(ChatColor.RED + "Usage: /bwa troll " + args[1].toLowerCase() + " <player>");
                return;
            } else if (args[1].equalsIgnoreCase("blind") || args[1].equalsIgnoreCase("slowhands")) {
                p.sendMessage(ChatColor.RED + "Usage: /bwa troll " + args[1].toLowerCase() + " <player> <seconds>");
                return;
            }
            p.sendMessage(ChatColor.RED + "Unknown troll command.");
        }
    }
}
