package me.hapily.plugins.skymining;

import me.hapily.plugins.skymining.util.Util;
import me.hapily.plugins.skymining.util.files.FileUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Field;

public final class SkyMining extends JavaPlugin {

    public static SkyMining instance;
    public static SkyMining getInstance(){
        return instance;
    }
    private File playedFile = FileUtil.getFile("playersplayed.yml");
    private FileConfiguration playedConfig = YamlConfiguration.loadConfiguration(playedFile);

    @Override
    public void onEnable() {
        instance = this;
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            Util.log("Commands registered");
        } catch(Exception e) {
            e.printStackTrace();
        }
        File f = new File(Bukkit.getPluginManager().getPlugin(this.getName()).getDataFolder() + "/");
        if(!f.exists())
            Util.log("Plugin folder not found, creating one!");
            f.mkdir();

        Util.log("Successfully enabled!");
    }

    @Override
    public void onDisable() {
        Util.log("Successfully disabled.");
    }

    public FileConfiguration getPlayedConfig(){
        return playedConfig;
    }

    public File getPlayedFile(){
        return playedFile;
    }
}
