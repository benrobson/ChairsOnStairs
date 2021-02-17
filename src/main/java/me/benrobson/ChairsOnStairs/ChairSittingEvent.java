package me.benrobson.ChairsOnStairs;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Iterator;

public class ChairSittingEvent implements Listener {
    ChairsOnStairsMain plugin;
    public ChairSittingEvent(ChairsOnStairsMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerStairInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location PlayerLocation = event.getPlayer().getLocation();
        Block block = event.getClickedBlock();
        String BlockMaterial = block.getType().name();
        Location BlockLocation = block.getLocation();
        int ChairSittingRange = 0;

        if (block != null) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK && !player.isInsideVehicle() && player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                Iterator ChairBlocks = plugin.getConfig().getStringList("chairs").iterator();

                while (ChairBlocks.hasNext()) {
                    String ChairBlock = (String)ChairBlocks.next();

                    if (BlockMaterial.equalsIgnoreCase(ChairBlock)) {
                        if (ChairSittingRange > 0 && PlayerLocation.distance(BlockLocation) - 1.0D > (double)ChairSittingRange) return; // Check if the player is next to the block.

                        World world = player.getWorld();

                        Entity ChairEntity = world.spawnEntity(player.getLocation(), EntityType.ARROW);
                        ChairEntity.teleport(BlockLocation.add(0.5D, 0.2D, 0.5D));
                        ChairEntity.setPassenger(player);
                        event.setCancelled(true);
                        return;
                    }

                }

            }
        }

    }

}
