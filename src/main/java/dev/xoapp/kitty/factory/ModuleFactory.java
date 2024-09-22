package dev.xoapp.kitty.factory;

import cn.nukkit.Server;
import cn.nukkit.level.Level;
import cn.nukkit.level.Location;
import cn.nukkit.utils.TextFormat;
import com.google.gson.Gson;
import dev.xoapp.kitty.Loader;
import dev.xoapp.kitty.module.WarpModule;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ModuleFactory {

    private static final HashMap<String, WarpModule> modules = new HashMap<>();

    public static void initialize() {

        Map<String, Object> saved_data = Loader.getDataManager().getSavedData();

        saved_data.forEach((key, value) -> {
            HashMap<String, Object> data = new Gson().fromJson(value.toString(), HashMap.class);

            if (data == null) {
                return;
            }

            Level level = Server.getInstance().getLevelByName(data.get("level").toString());

            if (!Server.getInstance().isLevelLoaded(data.get("level").toString())) {
                Server.getInstance().loadLevel(data.get("level").toString());
            }

            Location location = new Location(0, 0, 0, level);
            location.setX(Double.parseDouble(data.get("X").toString()));
            location.setY(Double.parseDouble(data.get("Y").toString()));
            location.setZ(Double.parseDouble(data.get("Z").toString()));

            add(new WarpModule(key, location));
        });

        Server.getInstance().getLogger().info(
                TextFormat.colorize("&aLoaded &e" + modules.size() + "&a Modules")
        );
    }

    public static void add(@NotNull WarpModule module) {
        modules.put(module.getName(), module);
    }

    public static WarpModule get(String name) {
        return modules.get(name);
    }

    public static void delete(String name) {
        modules.remove(name);
        Loader.getDataManager().unsetData(name);
    }

    public static HashMap<String, WarpModule> getModules() {
        return modules;
    }

    public static void save() {
        modules.forEach((key, module) -> {
            Loader.getDataManager().setData(key, module.serialize());
        });
    }
}
