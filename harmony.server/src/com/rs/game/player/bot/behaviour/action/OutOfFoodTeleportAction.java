package com.rs.game.player.bot.behaviour.action;

import com.rs.game.Animation;
import com.rs.game.item.Item;
import com.rs.game.player.bot.Bot;
import com.rs.game.player.bot.behaviour.action.Action;
import com.rs.game.player.content.Foods;
import com.rs.game.player.content.Magic;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Valkyr on 21/05/2016.
 */
public class OutOfFoodTeleportAction extends Action {

    private final Map<Bot, Integer> nextEatHps = new HashMap<>();

    public OutOfFoodTeleportAction() {
        super(0, 0);
    }

    @Override
    public boolean process(Bot bot) {

        for (Item item : bot.getInventory().getItems().getItems()) {
            if (item != null) {
                Foods.Food food = Foods.Food.forId(item.getId());
                if (food != null) {
                    return false;
                }
            }
        }
        // If out of food
        WorldTasksManager.schedule(new WorldTask() {
            boolean hasTeleported = false;
            double teleBackTime = 0;
            @Override
            public void run() {
                if (hasTeleported && Utils.currentTimeMillis() > teleBackTime) {
                    bot.loadEquipment();
                    bot.loadInventory();
                    Magic.useTeleTab(bot, bot.getSpawnWorldTile());
                   // bot.setNextAnimation(new Animation(836));
                    stop(); //change equip items
                } else if (!hasTeleported) {
                    hasTeleported = true;
                    teleBackTime = Utils.currentTimeMillis() + 300000 + (Math.random() * 600000);
                    
                    for (Item item : bot.getInventory().getItems().getItems()) {
                        if (item != null && item.getName().contains("teleport")) {
                            Magic.useTabTeleport(bot, item.getId());
                            return;
                        }
                    }
                }
            }
        }, 0, 1);
        return true;
    }

    private int getNextEatHp(Bot bot) {
        if (!nextEatHps.containsKey(bot)) {
            updateNextEatHp(bot);
        }
        return nextEatHps.get(bot);
    }

    private void updateNextEatHp(Bot bot) {
        nextEatHps.put(bot, generateNextEatHp(bot));
    }

    private int generateNextEatHp(Bot bot) {
        double hpMax = bot.getMaxHitpoints() / 1.5;
        double hpMin = bot.getMaxHitpoints() / 10;
        double nextHp = (Math.random() * hpMax) + hpMin;
        return (int) nextHp;
    }
}
