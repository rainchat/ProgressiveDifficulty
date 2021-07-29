package me.athlaeos.progressivelydifficultmobs.resourse.abilities;

import me.athlaeos.progressivelydifficultmobs.utils.Utils;
import me.athlaeos.progressivelydifficultmobs.utils.general.Ability;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.Arrays;

public class FireAspectIIIAbility extends Ability {
    public FireAspectIIIAbility() {
        this.name = Utils.chat("&6&lFire Aspect III");
        this.description = Arrays.asList(
                Utils.chat("&7Monsters with this ability"),
                Utils.chat("&7will light the player hit"),
                Utils.chat("&7on fire for 12 seconds."));
        this.icon = Material.FLINT_AND_STEEL;
        this.mobWhiteList = null;
    }

    @Override
    public void execute(Entity entity, Player player, Event event) {
        player.setFireTicks(240);
    }
}