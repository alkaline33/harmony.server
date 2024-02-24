package com.rs.game.player.bot.behaviour.action;

import com.rs.game.player.bot.Bot;
import com.rs.game.player.bot.behaviour.action.condition.IsNotWalkingCondition;
import com.rs.game.player.bot.behaviour.action.condition.OutOfCombatCondition;

/**
 * Created by Valkyr on 21/05/2016.
 */
public class RandomWalkAction extends Action {
    public RandomWalkAction() {
        super(10000, 30000, new OutOfCombatCondition(), new IsNotWalkingCondition());
    }

    @Override
   public boolean process(Bot bot) {
    	
        int x = bot.getX() + (10 - ((int) (Math.random() * 20)));
        int y = bot.getY() + (10 - ((int) (Math.random() * 20)));
        if(bot.canWalkNPC(x,y, true))
        	 bot.addWalkSteps(x,y);
        bot.addWalkSteps(x, y);
        return true;
    }
}
