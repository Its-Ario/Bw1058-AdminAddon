package me.zuyte.admin.events;

import me.zuyte.admin.Main;
import me.zuyte.admin.utils.getBlocks;
import me.zuyte.admin.utils.launchBlocks;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;

public class ClickEvent implements Listener {

    @EventHandler
    public void ClickEvent(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            try {
            if (e.hasItem() == true) {
            if (e.getItem().getType() == Material.STICK) {
            if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Magic Toy Stick") && e.getPlayer().getItemInHand().containsEnchantment(Enchantment.LURE)) {
                Block clickedBlock = e.getClickedBlock();
                List<Block> blocks = getBlocks.put(clickedBlock.getLocation(), 4, false);
                for (Block block : blocks) {
                    if (!block.getType().equals(Material.AIR) || !block.getType().equals(Material.BED_BLOCK)) {
                        launchBlocks.put(block);
                    }
                    e.getPlayer().setItemInHand(new ItemStack(Material.AIR));
                    Location location = e.getPlayer().getLocation();
                    try {
                        e.getPlayer().setVelocity(new Vector(0, 10, 0));
                    } catch (Exception ex) {
                        Main.getInstance().getLogger().severe("An exception occurred while setting player velocity.");
                    }

                    e.getPlayer().getWorld().createExplosion(location, -1, false);
                }
                        }
                    }
                }
            } catch (NullPointerException ex) {
                // null
            }
        }
    }
}
