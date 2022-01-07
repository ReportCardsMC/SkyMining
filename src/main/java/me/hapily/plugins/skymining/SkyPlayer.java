package me.hapily.plugins.skymining;

import me.hapily.plugins.skymining.util.files.FileUtil;
import org.bukkit.entity.Player;

public class SkyPlayer {

    public Player player;

    public SkyPlayer(Player player){
        this.player = player;
        setValue("name", player.getName());
    }

    public void setValue(String key, Object value){
        FileUtil.setConfig("players." + player.getUniqueId() + "." + key, value);
    }

    public Object getValue(String key){
        return SkyMining.getInstance().getConfig().get("players." + player.getUniqueId() + "." + key);
    }

    public String getName(){
        return (String) getValue("name");
    }

}
