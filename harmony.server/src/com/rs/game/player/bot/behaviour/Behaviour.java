package com.rs.game.player.bot.behaviour;

import com.rs.game.player.bot.Bot;
import com.rs.game.player.bot.behaviour.action.Action;

import java.io.Serializable;

/**
 * Created by Valkyr on 21/05/2016.
 */
public abstract class Behaviour implements Serializable {

    private final Action[] actions;

    public Behaviour(Action... actions) {
        this.actions = actions;
    }

    public void process(Bot bot) {
        for (Action action : actions) {
            if (action.execute(bot)) {
              // System.out.println("Executed " + action.getClass().getSimpleName());
                break;
            }
        }
    }

    public abstract void onDeath(Bot bot);
    
    
}
