package me.hapily.plugins.skymining.util;


import me.hapily.plugins.skymining.SkyMining;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Util {

    public static void log(String text){
        Bukkit.getLogger().info("[SkyMining] " + text);
    }

    public static boolean hasPlayed(Player player){
        return SkyMining.getInstance().getPlayedConfig().contains("players." + player.getUniqueId());
    }

}
