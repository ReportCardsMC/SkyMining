package me.hapily.plugins.skymining.events;

import me.hapily.plugins.skymining.util.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        if(Util.hasPlayed(p)){
            
        }
    }

}
