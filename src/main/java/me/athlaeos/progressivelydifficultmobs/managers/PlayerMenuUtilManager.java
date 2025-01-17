package me.athlaeos.progressivelydifficultmobs.managers;

import me.athlaeos.progressivelydifficultmobs.utils.general.PlayerMenuUtility;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerMenuUtilManager {
    private static PlayerMenuUtilManager manager = null;
    private final HashMap<Player, PlayerMenuUtility> playerMenuMap = new HashMap<>();

    public PlayerMenuUtilManager() {

    }

    public static PlayerMenuUtilManager getInstance() {
        if (manager == null) {
            manager = new PlayerMenuUtilManager();
        }
        return manager;
    }

    /**
     * Returns a PlayerMenuUtility object belonging to the given player, or a new blank one if none exist.
     * This PlayerMenuUtility contains some required details in controlling the GUI menu's during usage.
     *
     * @return A PlayerMenuUtility object belonging to a player, or a new blank one if none were found.
     */
    public PlayerMenuUtility getPlayerMenuUtility(Player p) {
        if (!playerMenuMap.containsKey(p)) {
            PlayerMenuUtility utility = new PlayerMenuUtility(p);
            playerMenuMap.put(p, utility);
        }
        return playerMenuMap.get(p);
    }
}
