package me.athlaeos.progressivelydifficultmobs.resourse.abilities;

import me.athlaeos.progressivelydifficultmobs.utils.Utils;
import me.athlaeos.progressivelydifficultmobs.utils.general.Ability;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Raider;
import org.bukkit.event.Event;

import java.util.Arrays;

public class RaidCaptainAbility extends Ability {
    public RaidCaptainAbility() {
        this.name = Utils.chat("&7&lRaid Captain");
        this.description = Arrays.asList(
                Utils.chat("&7Illagers with this ability"),
                Utils.chat("&7will spawn as raid captains."));
        this.icon = Material.GRAY_BANNER;
        this.mobWhiteList = Arrays.asList(EntityType.PILLAGER, EntityType.VINDICATOR, EntityType.EVOKER, EntityType.ILLUSIONER);
    }

    @Override
    public void execute(Entity entity, Player player, Event event) {
        if (entity instanceof Raider) {
            Raider raider = (Raider) entity;
            raider.setPatrolLeader(true);
        }
    }
}
