package me.athlaeos.progressivelydifficultmobs.perks.onattackperks;

import me.athlaeos.progressivelydifficultmobs.items.CursedEnemyDropsEnableItem;
import me.athlaeos.progressivelydifficultmobs.managers.ActiveItemManager;
import me.athlaeos.progressivelydifficultmobs.managers.CooldownManager;
import me.athlaeos.progressivelydifficultmobs.managers.PlayerPerksManager;
import me.athlaeos.progressivelydifficultmobs.managers.WorldguardManager;
import me.athlaeos.progressivelydifficultmobs.perks.Perk;
import me.athlaeos.progressivelydifficultmobs.perks.PerkTriggerPriority;
import me.athlaeos.progressivelydifficultmobs.pojo.Ability;
import me.athlaeos.progressivelydifficultmobs.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageBuffPerk extends Perk {
    private double damageMultiplier;
    private boolean includePlayers;
    public DamageBuffPerk(double damageMultiplier, int id){
        this.id = id;
        this.icon = Material.DIAMOND_SWORD;
        this.perkPriority = PerkTriggerPriority.SOONER;
        this.description = Utils.seperateStringIntoLines(Utils.chat("&7Players with this perk" +
                " have their damage dealt increased by " + ((damageMultiplier) * 100) + "%"), 36, "&7");
        this.name = "damage_buff";
        this.damageMultiplier = damageMultiplier;
        this.displayName = Utils.chat(config.getString("perks." + name + ".display_name").replace("{increase}", "" + ((damageMultiplier) * 100)));
        this.includePlayers = config.getBoolean("perks." + name + ".include_players");
    }

    @Override
    public void execute(Event e) {
        if (e instanceof EntityDamageByEntityEvent){
            EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
            if (!event.isCancelled()){
                Entity damager = event.getDamager();
                if (WorldguardManager.getWorldguardManager().isLocationInRegionWithFlag(damager.getLocation(), "pdm-damage-buff-deny")
                        || WorldguardManager.getWorldguardManager().isLocationInRegionWithFlag(damager.getLocation(), "pdm-perks-deny-all")) {
                    return;
                }
                Entity defender = event.getEntity();
                if (damager instanceof Projectile){
                    Projectile projectile = (Projectile) damager;
                    if (projectile.getShooter() instanceof Entity){
                        damager = (Entity) projectile.getShooter();
                    }
                }
                if (damager instanceof Player){
                    if (!includePlayers && defender instanceof Player){
                        return;
                    }
                    event.setDamage(event.getDamage() * (1D + damageMultiplier));
                }
            }
        }
    }
}