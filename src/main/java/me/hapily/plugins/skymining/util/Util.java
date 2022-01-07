package me.hapily.plugins.skymining.util;


import me.hapily.plugins.skymining.SkyMining;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Util {

    public static void log(String text){
        Bukkit.getLogger().info("[SkyMining] " + text);
    }

    public static Boolean hasPlayed(Player player){
        UUID uuid = (UUID) SkyMining.getInstance().getPlayedConfig().get("players." + player.getUniqueId());
        return uuid != null;
    }

}
