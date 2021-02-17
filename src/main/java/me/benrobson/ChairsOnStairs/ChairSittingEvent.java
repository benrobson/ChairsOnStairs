package me.benrobson.ChairsOnStairs;

import org.bukkit.Material;
import org.bukkit.block.Block;
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
        Block block = event.getClickedBlock();
        String BlockMaterial = block.getType().name();

        if (block != null) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK && !player.isInsideVehicle() && player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                Iterator ChairBlocks = plugin.getConfig().getStringList("chairs").iterator();

                while (ChairBlocks.hasNext()) {
                    String ChairBlock = (String)ChairBlocks.next();

                    player.sendMessage(BlockMaterial);

                    if (BlockMaterial.equalsIgnoreCase(ChairBlock)) {
                        player.sendMessage("This is a valid chair.");
                    }
                }

            }
        }

    }

}
