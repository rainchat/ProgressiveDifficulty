package me.athlaeos.progressivelydifficultmobs.utils.general;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.List;

public abstract class Ability {

    protected Material icon;
    protected String name;
    protected List<String> description;
    protected List<EntityType> mobWhiteList;

    public abstract void execute(Entity entity, Player player, Event event);

    public Material getIcon() {
        return icon;
    }

    public void setIcon(Material icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public List<EntityType> getMobWhiteList() {
        return mobWhiteList;
    }

    public void setMobWhiteList(List<EntityType> mobWhiteList) {
        this.mobWhiteList = mobWhiteList;
    }
}
