package com.rs.game.player.bot.behaviour.action.condition;

import com.rs.game.player.bot.Bot;

import java.io.Serializable;

/**
 * Created by Valkyr on 21/05/2016.
 */
public abstract class ActionCondition implements Serializable {
    public abstract boolean validate(Bot bot);
}
