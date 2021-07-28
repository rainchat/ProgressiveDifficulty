package me.athlaeos.progressivelydifficultmobs.abilities;

import me.athlaeos.progressivelydifficultmobs.pojo.Ability;
import me.athlaeos.progressivelydifficultmobs.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Arrays;

public class DamageBonus50Ability extends Ability {
    public DamageBonus50Ability(){
        this.name = Utils.chat("&c&lDamage: +50%");
        this.description = Arrays.asList(
                Utils.chat("&7Monsters with this ability"),
                Utils.chat("&7will deal 50% (1.5x) more damage"),
                Utils.chat("&7against the player hit."),
                Utils.chat("&7Stacks with similar abilities."));
        this.icon = Material.RED_DYE;
        this.mobWhiteList = null;
    }

    @Override
    public void execute(Entity entity, Player player, Event event) {
        EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
        e.setDamage(e.getDamage() * 1.5);
    }
}
