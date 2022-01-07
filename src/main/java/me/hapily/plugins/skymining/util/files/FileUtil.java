package me.hapily.plugins.skymining.util.files;

import me.hapily.plugins.skymining.SkyMining;
import me.hapily.plugins.skymining.util.Util;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public static File getFile(String name){
        try {
            File file = new File(Bukkit.getPluginManager().getPlugin(SkyMining.getInstance().getName()).getDataFolder() + File.separator + name);;
            if(file.createNewFile()) {
                Util.log("File '" + name + "' not found! Creating it...");
            }
            return file;
        } catch (IOException e) {
            Util.log("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    public static void setConfig(String key, Object value) {
        SkyMining instance = SkyMining.getInstance();
        instance.getConfig().set(key, value);
        instance.saveConfig();
    }

}
