package me.hapily.plugins.skymining.util.files;

import me.hapily.plugins.skymining.SkyMining;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ObjConfig {

    public static File objectsFile;
    public static FileConfiguration objectsConfig;

    public static void initialize(){
        objectsFile = FileUtil.getFile("objects.yml");
        objectsConfig = YamlConfiguration.loadConfiguration(objectsFile);
        save();
    }

    public static void save(){
        try {
            objectsConfig.save(objectsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setValue(String key, Object value){
        objectsConfig.set("objects." + key, value);
        save();
    }

    public static Object getValue(String key){
        return SkyMining.getInstance().getConfig().get("objects." + key);
    }

}
