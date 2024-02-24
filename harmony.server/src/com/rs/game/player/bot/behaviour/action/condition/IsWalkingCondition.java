package com.rs.game.player.bot.behaviour.action.condition;

import com.rs.game.player.bot.Bot;

/**
 * Created by Valkyr on 21/05/2016.
 */
public class IsWalkingCondition extends ActionCondition {
    @Override
    public boolean validate(Bot bot) {
    	
        return bot.hasWalkSteps();
    }
}
