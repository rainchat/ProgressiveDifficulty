package me.athlaeos.progressivelydifficultmobs.listeners;

import me.athlaeos.progressivelydifficultmobs.ProgressivelyMain;
import me.athlaeos.progressivelydifficultmobs.managers.AbilityManager;
import me.athlaeos.progressivelydifficultmobs.managers.ActiveItemManager;
import me.athlaeos.progressivelydifficultmobs.managers.LeveledMonsterManager;
import me.athlaeos.progressivelydifficultmobs.managers.PlayerPerksManager;
import me.athlaeos.progressivelydifficultmobs.resourse.perks.Perk;
import me.athlaeos.progressivelydifficultmobs.utils.general.Ability;
import me.athlaeos.progressivelydifficultmobs.utils.general.LeveledMonster;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class ItemInteractOnEntityListener implements Listener {
    private final NamespacedKey monsterKey = new NamespacedKey(ProgressivelyMain.getInstance(), "pdm-monsterID");

    @EventHandler
    public void onEntityClick(PlayerInteractEntityEvent e) {
        if (!e.isCancelled()) {
            if (e.getHand() == EquipmentSlot.OFF_HAND) {
                return;
            }
            if (e.getHand() == EquipmentSlot.HAND) {
                PlayerPerksManager manager = PlayerPerksManager.getInstance();
                for (Perk perk : manager.sortPerksByPriority(manager.getPlayersTotalPerks(e.getPlayer()))) {
                    perk.execute(e);
                }
                ItemStack clickedItem = e.getPlayer().getInventory().getItemInMainHand();
                if (clickedItem.getType() != Material.AIR) {
                    for (NamespacedKey key : ActiveItemManager.getInstance().getApplyOnEntityActiveItems().keySet()) {
                        if (clickedItem.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.INTEGER)) {
                            ActiveItemManager.getInstance().getApplyOnEntityActiveItems().get(key).execute(e);
                        }
                    }
                } else {
                    if (e.getRightClicked().getPersistentDataContainer().has(monsterKey, PersistentDataType.STRING)) {
                        LeveledMonster monster = LeveledMonsterManager.getInstance().getMonster(e.getRightClicked().getPersistentDataContainer().get(monsterKey, PersistentDataType.STRING));
                        if (monster != null) {
                            for (String a : monster.getAbilities()) {
                                Ability ability = AbilityManager.getInstance().getOnMobClickedAbilities().get(a);
                                if (ability != null) {
                                    ability.execute(e.getRightClicked(), e.getPlayer(), e);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
