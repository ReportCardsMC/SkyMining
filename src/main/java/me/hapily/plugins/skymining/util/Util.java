package me.hapily.plugins.skymining.util;


import me.hapily.plugins.skymining.SkyMining;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Util {

    public static void log(String text){
        Bukkit.getLogger().info("[SkyMining] " + text);
    }

    public static boolean hasPlayed(Player player){
        return SkyMining.getInstance().getPlayedConfig().contains("players." + player.getUniqueId());
    }

    // expected format: "coal_ore:1,dirt:5"
    public static HashMap<Material, Number> itemsConvert(String decrypt){
       try{
           decrypt = decrypt.toUpperCase();
           String[] split = decrypt.split(",");
           HashMap<Material, Number> map = new HashMap<>();
           Material mat;
           for(String string : split){
               String[] spl = string.split(":");
               mat = Material.getMaterial(spl[0]);
               map.put(mat,Double.valueOf(spl[1]));
           }
           return map;
       } catch (NumberFormatException e) {
           e.printStackTrace();
       }
       return null;
    }

}
