package me.hapily.plugins.skymining.events;

import me.hapily.plugins.skymining.util.Selection;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;


public class ClickEvents implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        Player p = event.getPlayer();
        if(p.getInventory().getItemInMainHand().displayName().equals(Component.text("§5§lMines Selection Tool"))){
            System.out.println("Passed tool check");
            event.setCancelled(true);

            if(event.getAction().isLeftClick()){
                System.out.println("Passed click type");
                if(Selection.loc1.containsKey(p)){
                    System.out.println("Passed other");
                    Selection.loc1.replace(p, Objects.requireNonNull(event.getClickedBlock()).getLocation());
                }
                else{
                    System.out.println("Passed other");
                    Selection.loc1.put(p, Objects.requireNonNull(event.getClickedBlock()).getLocation());
                }
                Location loc = event.getClickedBlock().getLocation();
                p.sendMessage("§5Mines: §dFirst position set to (" + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + ").");
            }
            if(event.getAction().isRightClick()){
                if(Selection.loc2.containsKey(p)){
                    Selection.loc2.replace(p, Objects.requireNonNull(event.getClickedBlock()).getLocation());
                }
                else{
                    Selection.loc2.put(p, Objects.requireNonNull(event.getClickedBlock()).getLocation());
                }
                Location loc = event.getClickedBlock().getLocation();
                p.sendMessage("§5Mines: §dFirst position set to (" + loc.getBlockX() + ", " + loc.getBlockY() + ", " + loc.getBlockZ() + ").");
            }
        }

    }

}
