package me.zuyte.admin.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.util.Vector;

public class launchBlocks {
    public static void put(Block b) {
        Object fb;
        if (b == null) return;
            fb = b.getWorld().spawnFallingBlock(b.getLocation().add(0.0D, 1.0D, 0.0D), b.getType(), b.getData());
            ((FallingBlock)fb).setDropItem(false);
        b.setType(Material.AIR);
        float x = -1.0F + (float)(Math.random() * 3.0D);
        float y = 0.5F;
        float z = -0.3F + (float)(Math.random() * 1.6D);
        ((Entity)fb).setVelocity(new Vector(x, y, z));
    }
}
