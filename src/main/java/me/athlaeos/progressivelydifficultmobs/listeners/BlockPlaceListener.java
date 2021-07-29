package me.athlaeos.progressivelydifficultmobs.listeners;

import me.athlaeos.progressivelydifficultmobs.managers.ConfigManager;
import me.athlaeos.progressivelydifficultmobs.managers.PlayerKarmaManager;
import me.athlaeos.progressivelydifficultmobs.managers.PlayerPerksManager;
import me.athlaeos.progressivelydifficultmobs.resourse.perks.Perk;
import me.athlaeos.progressivelydifficultmobs.utils.enums.KarmaGainReason;
import me.athlaeos.progressivelydifficultmobs.utils.general.Config;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashMap;
import java.util.Map;

public class BlockPlaceListener implements Listener {

    private static BlockPlaceListener listener = null;
    private Map<Material, Double> karmaGrantedOnBlockPlace;

    public BlockPlaceListener() {
        listener = this;
        loadConfig();
    }

    public static BlockPlaceListener getInstance() {
        return listener;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (!e.isCancelled()) {
            PlayerPerksManager manager = PlayerPerksManager.getInstance();
            for (Perk perk : manager.sortPerksByPriority(manager.getPlayersTotalPerks(e.getPlayer()))) {
                perk.execute(e);
            }
            if (karmaGrantedOnBlockPlace.containsKey(e.getBlock().getType())) {
                PlayerKarmaManager.getInstance().addKarma(e.getPlayer(),
                        karmaGrantedOnBlockPlace.get(e.getBlock().getType()),
                        KarmaGainReason.BLOCK_PLACE,
                        true, false);
            }
        }
    }

    public void loadConfig() {
        karmaGrantedOnBlockPlace = new HashMap<>();
        Config config = ConfigManager.getInstance().getConfig("karma.yml");
        YamlConfiguration yaml = config.get();
        for (String p : yaml.getConfigurationSection("karma_block_place").getKeys(false)) {
            Material m;
            try {
                m = Material.valueOf(p);
            } catch (IllegalArgumentException e) {
                System.out.println("Could not register type " + p + ". Are you sure you spelled it correctly?");
                continue;
            }
            karmaGrantedOnBlockPlace.put(m, yaml.getDouble("karma_block_place." + p));
        }

        config.copyDefaults(true).save();
    }
}
