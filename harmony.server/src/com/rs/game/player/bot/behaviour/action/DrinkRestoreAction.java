package com.rs.game.player.bot.behaviour.action;

import com.rs.game.item.Item;
import com.rs.game.player.Skills;
import com.rs.game.player.bot.Bot;
import com.rs.game.player.content.Pots;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Valkyr on 21/05/2016.
 */
public class DrinkRestoreAction extends Action {

    private final Map<Bot, Integer> nextRestorePrayerAmounts = new HashMap<>();

    public DrinkRestoreAction() {
        super(0, 0);
    }

    @Override
    public boolean process(Bot bot) {
        if (bot.getPrayer().getPrayerpoints() <= getNextRestorePrayerAmount(bot)) {
            for (Item item : bot.getInventory().getItems().getItems()) {
                if (item != null && item.getName().contains("estore")) {
                    if (Pots.pot(bot, item, bot.getInventory().getItems().getThisItemSlot(item))) {
                        updateNextRestorePrayerAmount(bot);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int getNextRestorePrayerAmount(Bot bot) {
        if (!nextRestorePrayerAmounts.containsKey(bot)) {
            updateNextRestorePrayerAmount(bot);
        }
        return nextRestorePrayerAmounts.get(bot);
    }

    private void updateNextRestorePrayerAmount(Bot bot) {
        nextRestorePrayerAmounts.put(bot, generateNextRestorePrayerAmount(bot));
    }

    private int generateNextRestorePrayerAmount(Bot bot) {
        int prayerMax = bot.getSkills().getLevelForXp(Skills.PRAYER) * 10;
        int prayerMid = prayerMax / 2;
        int prayerMin = prayerMax / 4;
        int rand = (int) (Math.random() * prayerMid) + prayerMin;
        int nextPrayer = prayerMid + rand;
        return nextPrayer;
    }
}
