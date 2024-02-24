package com.rs.game.player.bot.behaviour.action;

import com.rs.game.player.bot.Bot;
import com.rs.game.player.bot.behaviour.action.condition.ActionCondition;
import com.rs.utils.Utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Valkyr on 21/05/2016.
 */
public abstract class Action implements Serializable {

    private final Map<Bot, Long> nextActionTimes = new HashMap<>();
    private final int min;
    private final int max;
    private final ActionCondition[] conditions;

    public Action(int min, int max, ActionCondition... conditions) {
        this.min = min;
        this.max = max - min;
        this.conditions = conditions;
    }

    public boolean execute(Bot bot) {
        long nextActionTime = getNextActionTime(bot);
        if (Utils.currentTimeMillis() > nextActionTime) {
            if (conditions != null)
                for (ActionCondition condition : conditions)
                    if (!condition.validate(bot)) {
                        return false;
                    }
            boolean processed = process(bot);
            if (processed)
                setNextActionTime(bot);
            return processed;
        }
        return false;
    }

    private long getNextActionTime(Bot bot) {
        if (!nextActionTimes.containsKey(bot)) {
            setNextActionTime(bot);
        }
        return nextActionTimes.get(bot);
    }

    public abstract boolean process(Bot bot);

    private void setNextActionTime(Bot bot) {
        long nextActionTime = Utils.currentTimeMillis() +  (int) (min + (Math.random() * max));
        nextActionTimes.put(bot, nextActionTime);
    }
}
