package me.athlaeos.progressivelydifficultmobs.managers;

import me.athlaeos.progressivelydifficultmobs.ProgressivelyMain;
import me.athlaeos.progressivelydifficultmobs.resourse.items.*;
import me.athlaeos.progressivelydifficultmobs.utils.general.ActiveItem;
import org.bukkit.NamespacedKey;

import java.util.HashMap;
import java.util.Map;

public class ActiveItemManager {

    private static ActiveItemManager manager = null;
    private final Map<NamespacedKey, ActiveItem> standaloneActiveItems;
    private final Map<NamespacedKey, ActiveItem> applyOnEntityActiveItems;

    private final Map<NamespacedKey, ActiveItem> allActiveItems;

    public ActiveItemManager() {
        allActiveItems = new HashMap<>();
        standaloneActiveItems = new HashMap<>();
        applyOnEntityActiveItems = new HashMap<>();

        registerStandaloneActiveItem(new Cleanse1CurseItem());
        registerStandaloneActiveItem(new CleanseAllCurseItem());
        registerStandaloneActiveItem(new CursedEnemyDropsEnableItem());
        registerStandaloneActiveItem(new LargeAOEDamageItem());
        registerStandaloneActiveItem(new MobSpawnPreventionItem());
        registerStandaloneActiveItem(new CleanseDebuffsActiveItem());

        registerApplyOnEntityActiveItem(new RevealEvilItem());

        allActiveItems.putAll(standaloneActiveItems);
        allActiveItems.putAll(applyOnEntityActiveItems);
    }

    public static ActiveItemManager getInstance() {
        if (manager == null) {
            manager = new ActiveItemManager();
        }
        return manager;
    }

    /**
     * Registers a new standalone ActiveItem
     *
     * @param item
     */
    public void registerStandaloneActiveItem(ActiveItem item) {
        standaloneActiveItems.put(item.getKey(), item);
        allActiveItems.put(item.getKey(), item);
    }

    /**
     * Registers a new apply-on-entity ActiveItem
     *
     * @param item
     */
    public void registerApplyOnEntityActiveItem(ActiveItem item) {
        applyOnEntityActiveItems.put(item.getKey(), item);
        allActiveItems.put(item.getKey(), item);
    }

    public void reload() {
        for (NamespacedKey key : allActiveItems.keySet()) {
            ProgressivelyMain.getInstance().getServer().removeRecipe(key);
        }
        manager = new ActiveItemManager();
    }

    /**
     * Gets all the standalone usable items. With this it's implied that these items can be used
     * on their own, without any other circumstances.
     *
     * @return a map of a NamespacedKey with an ActiveItem
     */
    public Map<NamespacedKey, ActiveItem> getStandaloneActiveItems() {
        return standaloneActiveItems;
    }

    /**
     * Gets all the apply-on-entity items. With this it's implied that these items can only be used
     * on another entity.
     *
     * @return a map of a NamespacedKey with an ActiveItem
     */
    public Map<NamespacedKey, ActiveItem> getApplyOnEntityActiveItems() {
        return applyOnEntityActiveItems;
    }

    /**
     * Gets all active usable items.
     *
     * @return a map of a NamespacedKey with an ActiveItem
     */
    public Map<NamespacedKey, ActiveItem> getAllActiveItems() {
        return allActiveItems;
    }
}
