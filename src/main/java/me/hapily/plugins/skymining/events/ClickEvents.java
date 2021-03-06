package me.hapily.plugins.skymining.events;

import me.hapily.plugins.skymining.util.Selection;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Objects;


public class ClickEvents implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if (event.getHand() == EquipmentSlot.HAND){
            if(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("§5§lMines Selection Tool")){
                event.setCancelled(true);
                if(event.getAction().isLeftClick()){
                    if(Selection.loc1.containsKey(p)){
                        Selection.loc1.replace(p, Objects.requireNonNull(event.getClickedBlock()).getLocation());
                    }
                    else{
                        Selection.loc1.put(p, Objects.requireNonNull(event.getClickedBlock()).getLocation());
                    }
                    Location loc = event.getClickedBlock().getLocation();
                    p.sendMessage("§5Mines: §dFirst position set to (" + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + ").");
                }
                else if(event.getAction().isRightClick()){
                    if(Selection.loc2.containsKey(p)){
                        Selection.loc2.replace(p, Objects.requireNonNull(event.getClickedBlock()).getLocation());
                    }
                    else{
                        Selection.loc2.put(p, Objects.requireNonNull(event.getClickedBlock()).getLocation());
                    }
                    Location loc = event.getClickedBlock().getLocation();
                    p.sendMessage("§5Mines: §dSecond position set to (" + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + ").");
                }
            }
        }


    }

}
