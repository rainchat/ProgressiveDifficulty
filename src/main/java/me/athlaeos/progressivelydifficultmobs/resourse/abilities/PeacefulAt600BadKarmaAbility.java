package me.athlaeos.progressivelydifficultmobs.resourse.abilities;

import me.athlaeos.progressivelydifficultmobs.ProgressivelyMain;
import me.athlaeos.progressivelydifficultmobs.utils.Utils;
import me.athlaeos.progressivelydifficultmobs.utils.general.Ability;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class PeacefulAt600BadKarmaAbility extends Ability {
    private final NamespacedKey key = new NamespacedKey(ProgressivelyMain.getInstance(), "pdm-karma");
    private final NamespacedKey cursedKey = new NamespacedKey(ProgressivelyMain.getInstance(), "pdm-curse");

    public PeacefulAt600BadKarmaAbility() {
        this.name = Utils.chat("&c&lPeaceful to -600 or lower karma");
        this.description = Arrays.asList(
                Utils.chat("&7Non-cursed monsters with this ability"),
                Utils.chat("&7will not go aggressive to players"),
                Utils.chat("&7with &c-600&7 or less karma (bad karma)"));
        this.icon = Material.WITHER_ROSE;
        this.mobWhiteList = null;
    }

    @Override
    public void execute(Entity entity, Player player, Event event) {
        if (event instanceof EntityTargetLivingEntityEvent) {
            EntityTargetLivingEntityEvent e = (EntityTargetLivingEntityEvent) event;
            if (!e.isCancelled()) {
                if (e.getTarget() instanceof Player) {
                    Player p = (Player) e.getTarget();
                    if (p.getPersistentDataContainer().has(key, PersistentDataType.DOUBLE)) {
                        if (p.getPersistentDataContainer().get(key, PersistentDataType.DOUBLE) <= -600) {
                            if (!e.getEntity().getPersistentDataContainer().has(cursedKey, PersistentDataType.STRING)) {
                                e.setCancelled(true);
                            }
                        }
                    }
                }
            }
        }
    }
}
