package com.rs.game.player.bot.behaviour.action.condition;

import com.rs.game.player.bot.Bot;
import com.rs.utils.Utils;

/**
 * Created by Valkyr on 21/05/2016.
 */
public class InCombatCondition extends ActionCondition {
    @Override
    public boolean validate(Bot bot) {
        long millis = Utils.currentTimeMillis();
        return millis <= bot.getAttackedByDelay() && millis <= bot.getAttackedByDelay();
    }
}
