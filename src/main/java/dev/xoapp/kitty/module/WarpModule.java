package dev.xoapp.kitty.module;

import cn.nukkit.level.Location;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class WarpModule {

    private final String name;
    private Location location;

    public WarpModule(String name, @Nullable Location location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(@Nullable Location location) {
        this.location = location;
    }

    public HashMap<String, Object> serialize() {

        HashMap<String, Object> data = new HashMap<>();

        if (this.location == null) {
            data.put("X", 0);
            data.put("Y", 0);
            data.put("Z", 0);
            data.put("level", "Unknown");
        } else {
            data.put("X", this.location.getX());
            data.put("Y", this.location.getY());
            data.put("Z", this.location.getZ());
            data.put("level", this.location.getLevel().getFolderName());
        }

        return data;
    }
}
