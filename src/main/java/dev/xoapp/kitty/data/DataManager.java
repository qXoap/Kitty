package dev.xoapp.kitty.data;

import cn.nukkit.Server;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import dev.xoapp.kitty.Loader;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class DataManager {

    private final Config config;

    public DataManager() {

        File file = new File(Loader.getInstance().getDataFolder() + "modules.json");

        try {
            if (file.createNewFile()) {
                Server.getInstance().getLogger().info(TextFormat.colorize("&aWarps Module Json created"));
            }
        } catch (IOException exception) {
            Server.getInstance().getLogger().critical(TextFormat.colorize("&cWarps Module cant be created"));
        }

        this.config = new Config(file, Config.JSON);
    }

    public void setData(String key, Object data) {
        this.config.set(key, data);
        this.config.save();
    }

    public Object getData(String key) {
        return this.config.get(key);
    }

    public void unsetData(String key) {
        this.config.remove(key);
        this.config.save();
    }

    public Map<String, Object> getSavedData() {
        return this.config.getAll();
    }
}
