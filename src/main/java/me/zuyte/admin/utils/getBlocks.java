package me.zuyte.admin.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class getBlocks {
    public static List<Block> put(Location location, int radius, boolean hollow) {
        List<Block> blocks = new ArrayList<>();
        int bX = location.getBlockX();
        int bY = location.getBlockY();
        int bZ = location.getBlockZ();
        for (int x = bX - radius; x <= bX + radius; x++) {
            for (int y = bY - radius; y <= bY + radius; y++) {
                for (int z = bZ - radius; z <= bZ + radius; z++) {
                    double distance = ((bX - x) * (bX - x) + (bY - y) * (bY - y) + (bZ - z) * (bZ - z));
                    if (distance < (radius * radius) && (!hollow || distance >= ((radius - 1) * (radius - 1)))) {
                        Location Blocklocation = new Location(location.getWorld(), x, y, z);
                        if (Blocklocation.getBlock().getType() != Material.BARRIER)
                            blocks.add(Blocklocation.getBlock());
                    }
                }
            }
        }
        return blocks;
    }
}
