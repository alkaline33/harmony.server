package com.rs.game.player.bot.behaviour;

import com.rs.game.ForceTalk;
import com.rs.game.player.PublicChatMessage;
import com.rs.game.player.bot.Bot;
import com.rs.game.player.bot.behaviour.action.*;
import com.rs.game.player.content.Magic;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * Created by Valkyr on 21/05/2016.
 */
public class HybridBehaviour extends Behaviour {

    public HybridBehaviour() {
        super(new RandomWalkAction(), new OutOfFoodTeleportAction(), new EatFoodAction(), new CastSpellAction(), new PerformSpecialAttackAction(), new EnablePrayerAction(), new DrinkPotionAction(), new DrinkRestoreAction(), new AttackPlayerAction(), new HybridSwitchEquipmentAction());
    }

    @Override
    public void onDeath(Bot bot) {
    	switch (Utils.getRandom(10)) {
		case 0:
			  bot.sendPublicChatMessage(new PublicChatMessage("Oh shit", 0));
			break;
		case 1:
			  bot.sendPublicChatMessage(new PublicChatMessage("K den", 0));
			break;
		case 2:
			  bot.sendPublicChatMessage(new PublicChatMessage("Gf", 0));
			break;
		case 3:
			  bot.sendPublicChatMessage(new PublicChatMessage("Im out", 0));
			break;
		case 4:
			  bot.sendPublicChatMessage(new PublicChatMessage("Safer", 0));
			break;
		case 5:
			  bot.sendPublicChatMessage(new PublicChatMessage("Hacker", 0));
			break;
		case 6:
			  bot.sendPublicChatMessage(new PublicChatMessage("Brb", 0));
			break;
		case 7:
			  bot.sendPublicChatMessage(new PublicChatMessage("Rm?", 0));
			break;
		case 8:
			  bot.sendPublicChatMessage(new PublicChatMessage("Cya", 0));
			break;
		case 9:
			  bot.sendPublicChatMessage(new PublicChatMessage("Whatever", 0));
			break;
		case 10:
			  bot.sendPublicChatMessage(new PublicChatMessage("k", 0));
			break;
		}
      //  bot.sendPublicChatMessage(new PublicChatMessage("Oh shit", 0));
        WorldTasksManager.schedule(new WorldTask() {
            boolean hasTeleported = true;
            double teleBackTime = 0;

            @Override
            public void run() {
                if (!hasTeleported && Utils.currentTimeMillis() > teleBackTime) {
                    bot.loadEquipment();
                    bot.loadInventory();

                    Magic.useTeleTab(bot, bot.getSpawnWorldTile());

                    stop();
                } else if (hasTeleported) {
                	  teleBackTime = Utils.currentTimeMillis() + 300000 + (Math.random() * 600000);
                    hasTeleported = false;
                }
            }
        }, 0, 1);
    }
}
