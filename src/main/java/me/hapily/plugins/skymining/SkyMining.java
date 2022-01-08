package me.hapily.plugins.skymining;

import me.hapily.plugins.skymining.events.ClickEvents;
import me.hapily.plugins.skymining.events.JoinEvent;
import me.hapily.plugins.skymining.mines.CMDMine;
import me.hapily.plugins.skymining.util.Util;
import me.hapily.plugins.skymining.util.files.FileUtil;
import me.hapily.plugins.skymining.util.files.ObjConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public final class SkyMining extends JavaPlugin {

    public static SkyMining instance;

    public static SkyMining getInstance(){
        return instance;
    }

    private File playedFile;
    private FileConfiguration playedConfig;

    @Override
    public void onEnable() {
        instance = this;
        File f = new File(Bukkit.getPluginManager().getPlugin(this.getName()).getDataFolder() + "/");
        if(!f.exists())
            Util.log("Plugin folder not found, creating one!");
        f.mkdir();
        this.saveDefaultConfig();
        playedFile = FileUtil.getFile("playersplayed.yml");
        playedConfig = YamlConfiguration.loadConfiguration(playedFile);
        try {
            playedConfig.save(playedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjConfig.initialize();
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            commandMap.register("SkyMining", new CMDMine("mine"));

            Util.log("Commands registered");
        } catch(Exception e) {
            e.printStackTrace();
        }
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ClickEvents(), this);
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
