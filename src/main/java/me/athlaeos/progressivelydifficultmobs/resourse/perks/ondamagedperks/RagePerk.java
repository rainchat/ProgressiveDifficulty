package me.athlaeos.progressivelydifficultmobs.resourse.perks.ondamagedperks;

import me.athlaeos.progressivelydifficultmobs.ProgressivelyMain;
import me.athlaeos.progressivelydifficultmobs.managers.CooldownManager;
import me.athlaeos.progressivelydifficultmobs.managers.WorldguardManager;
import me.athlaeos.progressivelydifficultmobs.resourse.perks.Perk;
import me.athlaeos.progressivelydifficultmobs.resourse.perks.PerkTriggerPriority;
import me.athlaeos.progressivelydifficultmobs.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RagePerk extends Perk {
    private final int cooldown;
    private final int duration;
    private final int amplifier;

    public RagePerk() {
        this.id = 2046;
        this.icon = Material.BLAZE_ROD;
        this.perkPriority = PerkTriggerPriority.SOONER;
        this.description = Utils.seperateStringIntoLines(Utils.chat("&7Players with this perk" +
                " receive a strength buff upon being damaged by an entity"), 36, "&7");
        this.name = "rage";
        this.displayName = Utils.chat(config.getString("perks." + name + ".display_name"));
        this.cooldown = config.getInt("perks." + name + ".cooldown");
        this.duration = config.getInt("perks." + name + ".duration");
        this.amplifier = config.getInt("perks." + name + ".amplifier");
    }

    @Override
    public void execute(Event e) {
        if (e instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) e;
            if (!event.isCancelled()) {
                Entity defender = event.getEntity();
                if (WorldguardManager.getWorldguardManager().isLocationInRegionWithFlag(defender.getLocation(), "pdm-rage-deny")
                        || WorldguardManager.getWorldguardManager().isLocationInRegionWithFlag(defender.getLocation(), "pdm-perks-deny-all")) {
                    return;
                }
                if (defender instanceof Player) {
                    Player p = (Player) defender;
                    if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
                        if (p.getPotionEffect(PotionEffectType.INCREASE_DAMAGE).getAmplifier() > amplifier) {
                            return;
                        }
                    }
                    if (CooldownManager.getInstance().cooldownLowerThanZero(p, "perks_rage_cooldown")) {
                        PotionEffect effect = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, duration, amplifier);
                        EntityPotionEffectEvent potionEvent = new EntityPotionEffectEvent(p, null, effect, EntityPotionEffectEvent.Cause.PLUGIN, EntityPotionEffectEvent.Action.ADDED, true);
                        ProgressivelyMain.getInstance().getServer().getPluginManager().callEvent(potionEvent);
                        if (!potionEvent.isCancelled()) {
                            p.addPotionEffect(effect);
                            CooldownManager.getInstance().setCooldown(p, cooldown * 1000, "perks_rage_cooldown");
                        }
                    }
                }
            }
        }
    }
}
