package com.rs.game.player.bot.definition;

import com.rs.game.player.bot.behaviour.*;

/**
 * Created by Valkyr on 21/05/2016.
 */
public enum BehaviourDefinition {
    PKER(new PKerBehaviour()),
    DHAROKER(new DharokerBehaviour()),
    HYBRID(new HybridBehaviour());

    private final Behaviour behaviour;

    BehaviourDefinition(Behaviour behaviour) {
        this.behaviour = behaviour;
    }

    public Behaviour getBehaviour() {
        return behaviour;
    }
}
