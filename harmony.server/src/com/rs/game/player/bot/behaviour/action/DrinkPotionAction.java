package com.rs.game.player.bot.behaviour.action;

import com.rs.game.item.Item;
import com.rs.game.player.bot.Bot;
import com.rs.game.player.content.Pots;

/**
 * Created by Valkyr on 21/05/2016.
 */
public class DrinkPotionAction extends Action {

    public DrinkPotionAction() {
        super(0, 0);
    }

    @Override
   public boolean process(Bot bot) {
        for (Item item : bot.getInventory().getItems().getItems()) {
            if (item != null) {
                Pots.Pot pot = Pots.getPot(item.getId());
                if (pot != null && !item.getName().contains("estore") && pot.getEffect().canDrink(bot)) {
                    for (int skillId : pot.getEffect().getAffectedSkills()) {
                        if (bot.getSkills().getLevel(skillId) <= bot.getSkills().getLevelForXp(skillId)) {
                            if (Pots.pot(bot, item, bot.getInventory().getItems().getThisItemSlot(item)))
                                return true;
                        }
                    }
                }

            }
        }
        return false;
    }
}
