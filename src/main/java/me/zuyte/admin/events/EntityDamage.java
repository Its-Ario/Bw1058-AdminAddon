package me.zuyte.admin.events;

import me.zuyte.admin.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
        if (e.getDamager() instanceof LivingEntity) {
            if (e.getDamager().getCustomName() == ChatColor.RED + e.getEntity().getName() + "'s Enemy") {
                e.setCancelled(false);
                }
            }
        if (e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            if (e.getDamager().getType() == EntityType.FIREBALL) {
                if (Main.getInstance().bw.getArenaUtil().getArenaByPlayer(((Player) e.getEntity()).getPlayer()).isReSpawning(((Player) e.getEntity()).getPlayer()) || Main.getInstance().bw.getArenaUtil().getArenaByPlayer(((Player) e.getEntity()).getPlayer()).isSpectator(((Player) e.getEntity()).getPlayer())) return;
                e.setCancelled(false);
                }
            }
        }
    }
}
