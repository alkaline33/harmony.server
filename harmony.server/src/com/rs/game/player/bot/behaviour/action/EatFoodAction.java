package com.rs.game.player.bot.behaviour.action;

import com.rs.Settings;
import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.bot.Bot;
import com.rs.game.player.content.Foods;
import com.rs.game.player.content.Magic;
import com.rs.game.player.controlers.QueenBlackDragonController;
import com.rs.game.player.controlers.Wilderness;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Valkyr on 21/05/2016.
 */
public class EatFoodAction extends Action {

    private final Map<Bot, Integer> nextEatHps = new HashMap<>();

    private Player killer;
    
    public EatFoodAction() {
        super(0, 0);
    }

    @Override
    public boolean process(Bot bot) {
    	if (bot.getHitpoints() <= 0) {
    		//World.sendWorldMessage("dead", false);
    	bot.sendDeath(killer);
    	bot.setNextAnimation(new Animation(836));
    	//bot.lock();
    	return true;
    	}
        if (bot.getHitpoints() <= getNextEatHp(bot)) {
            for (Item item : bot.getInventory().getItems().getItems()) {
                if (item != null) {
                    Foods.Food food = Foods.Food.forId(item.getId());
                    if (food != null) {
                        if (Foods.eat(bot, item, bot.getInventory().getItems().getThisItemSlot(item), item.getId())) {
                            generateNextEatHp(bot);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
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
        double hpMax = bot.getMaxHitpoints() / 2;
        double hpMin = bot.getMaxHitpoints() / 10;
        double nextHp = (Math.random() * hpMax) + hpMin;
        return (int) nextHp;
    }
}
