package me.hapily.plugins.skymining.events;

import me.hapily.plugins.skymining.SkyMining;
import me.hapily.plugins.skymining.SkyPlayer;
import me.hapily.plugins.skymining.util.Util;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        if(!Util.hasPlayed(p)){
            SkyPlayer skyPlayer = new SkyPlayer(p);
            SkyMining.getInstance().getPlayedConfig().set("players." + p.getUniqueId(), (byte) 0);
            try {
                SkyMining.getInstance().getPlayedConfig().save(SkyMining.getInstance().getPlayedFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            p.sendMessage(Component.text("You're new!"));
        }
    }

}
