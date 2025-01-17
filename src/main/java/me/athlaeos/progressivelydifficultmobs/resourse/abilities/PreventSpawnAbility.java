package me.athlaeos.progressivelydifficultmobs.resourse.abilities;

import me.athlaeos.progressivelydifficultmobs.utils.Utils;
import me.athlaeos.progressivelydifficultmobs.utils.general.Ability;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Arrays;

public class PreventSpawnAbility extends Ability {
    public PreventSpawnAbility() {
        this.name = Utils.chat("&7&lPrevent Spawn");
        this.description = Arrays.asList(
                Utils.chat("&7Monsters with this ability"),
                Utils.chat("&7will not be spawned entirely."));
        this.icon = Material.BARRIER;
        this.mobWhiteList = null;
    }

    @Override
    public void execute(Entity entity, Player player, Event event) {
        if (event instanceof EntitySpawnEvent) {
            EntitySpawnEvent e = (EntitySpawnEvent) event;
            e.setCancelled(true);
        }
    }
}
