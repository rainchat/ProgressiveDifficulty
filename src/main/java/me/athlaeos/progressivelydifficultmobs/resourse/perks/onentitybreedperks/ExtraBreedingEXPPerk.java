package me.athlaeos.progressivelydifficultmobs.resourse.perks.onentitybreedperks;

import me.athlaeos.progressivelydifficultmobs.managers.WorldguardManager;
import me.athlaeos.progressivelydifficultmobs.resourse.perks.Perk;
import me.athlaeos.progressivelydifficultmobs.resourse.perks.PerkTriggerPriority;
import me.athlaeos.progressivelydifficultmobs.utils.Utils;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityBreedEvent;

public class ExtraBreedingEXPPerk extends Perk {
    private final int expAmount;
    private final double bonusEXPChance;

    public ExtraBreedingEXPPerk() {
        this.id = 1003;
        this.perkPriority = PerkTriggerPriority.SOONER;
        this.icon = Material.EXPERIENCE_BOTTLE;
        this.description = Utils.seperateStringIntoLines(Utils.chat("&7Players gain extra EXP when" +
                " breeding animals together"), 36, "&7");
        this.name = "breeding_exp";
        this.displayName = Utils.chat(config.getString("perks." + name + ".display_name"));
        this.bonusEXPChance = config.getDouble("perks." + name + ".bonus_chance");
        this.expAmount = config.getInt("perks." + name + ".exp_amount");
    }


    @Override
    public void execute(Event e) {
        if (e instanceof EntityBreedEvent) {
            EntityBreedEvent event = (EntityBreedEvent) e;
            if (!event.isCancelled()) {
                if (WorldguardManager.getWorldguardManager().isLocationInRegionWithFlag(event.getEntity().getLocation(), "pdm-breeding-exp-deny")
                        || WorldguardManager.getWorldguardManager().isLocationInRegionWithFlag(event.getEntity().getLocation(), "pdm-perks-deny-all")) {
                    return;
                }
                if (Utils.getRandom().nextDouble() < bonusEXPChance) {
                    event.setExperience(event.getExperience() + expAmount);
                }
            }
        }
    }
}
