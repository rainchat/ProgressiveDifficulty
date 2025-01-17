package me.athlaeos.progressivelydifficultmobs.resourse.abilities;

import me.athlaeos.progressivelydifficultmobs.ProgressivelyMain;
import me.athlaeos.progressivelydifficultmobs.utils.Utils;
import me.athlaeos.progressivelydifficultmobs.utils.general.Ability;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class GiveOffhandAt900KarmaAbility extends Ability {
    private final NamespacedKey key = new NamespacedKey(ProgressivelyMain.getInstance(), "pdm-karma");
    private final NamespacedKey cursedKey = new NamespacedKey(ProgressivelyMain.getInstance(), "pdm-curse");

    public GiveOffhandAt900KarmaAbility() {
        this.name = Utils.chat("&a&lGive off-hand item to 900+ karma");
        this.description = Arrays.asList(
                Utils.chat("&7Monsters with this ability"),
                Utils.chat("&7will give the item they hold in"),
                Utils.chat("&7their off-hand to players with"),
                Utils.chat("&7900 or more karma when"),
                Utils.chat("&7right-clicked with an empty hand,"),
                Utils.chat("&7but only if the entity isn't cursed."));
        this.icon = Material.CAKE;
        this.mobWhiteList = null;
    }

    @Override
    public void execute(Entity entity, Player player, Event event) {
        if (entity instanceof LivingEntity) {
            LivingEntity clickedEntity = (LivingEntity) entity;
            if (player.getPersistentDataContainer().has(key, PersistentDataType.DOUBLE)) {
                if (player.getPersistentDataContainer().get(key, PersistentDataType.DOUBLE) >= 900) {
                    if (!clickedEntity.getPersistentDataContainer().has(cursedKey, PersistentDataType.STRING)) {
                        if (clickedEntity.getEquipment() != null) {
                            player.spawnParticle(Particle.HEART, clickedEntity.getLocation().add(0, 1, 0), 0, 0, 0.5, 0);
                            player.getInventory().setItemInMainHand(clickedEntity.getEquipment().getItemInOffHand());
                            clickedEntity.getEquipment().setItemInOffHand(new ItemStack(Material.AIR));
                        }
                    }
                }
            }
        }
    }
}
