package me.athlaeos.progressivelydifficultmobs.managers;

import me.athlaeos.progressivelydifficultmobs.ProgressivelyMain;
import me.athlaeos.progressivelydifficultmobs.utils.general.Config;

import java.util.HashMap;

//All credit to spigotmc.org user Bimmr for this manager
public class ConfigManager {

    private static ConfigManager manager = null;
    private final ProgressivelyMain plugin;
    private final HashMap<String, Config> configs = new HashMap<String, Config>();

    public ConfigManager() {
        plugin = ProgressivelyMain.getInstance();
        getConfig("config.yml").save();
        getConfig("perks.yml").save();
        getConfig("custom-abilities.yml").save();
        getConfig("karma.yml").save();
        getConfig("items.yml").save();
        getConfig("translations.yml").save();
    }

    public static ConfigManager getInstance() {
        if (manager == null) {
            manager = new ConfigManager();
        }
        return manager;
    }

    public HashMap<String, Config> getConfigs() {
        return configs;
    }

    public Config getConfig(String name) {
        if (!configs.containsKey(name))
            configs.put(name, new Config(name));

        return configs.get(name);
    }

    public Config saveConfig(String name) {
        return getConfig(name).save();
    }

    public Config reloadConfig(String name) {
        return getConfig(name).reload();
    }

}
