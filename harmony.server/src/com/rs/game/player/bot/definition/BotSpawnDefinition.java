package com.rs.game.player.bot.definition;

import com.rs.game.WorldTile;

/**
 * Created by Valkyr on 30/05/2016.
 */
public enum BotSpawnDefinition {
    EASTS(new WorldTile(3360, 3658, 0), 50, BotDefinition.LOW_HYBRID, BotDefinition.MED_HYBRID, BotDefinition.MAXED_HYBRID, BotDefinition.TANK_RANGER, BotDefinition.PURE_RANGER),
    WESTS(new WorldTile(3000, 3623, 0), 50, BotDefinition.MED_HYBRID, BotDefinition.MAXED_HYBRID, BotDefinition.TANK_RANGER, BotDefinition.DHAROKER),
    EDGE(new WorldTile(3000, 3623, 0), 50, BotDefinition.MAXED_ZERK, BotDefinition.PURE_ZERK, BotDefinition.PURE_RANGER, BotDefinition.MAX_MELEE, BotDefinition.DHAROKER),
    //MADE_BANK(new WorldTile(3000, 3623, 0), 50, BotDefinition.MAXED_ZERK, BotDefinition.PURE_ZERK, BotDefinition.PURE_RANGER, BotDefinition.MAX_MELEE, BotDefinition.DHAROKER),
    ;

    private final WorldTile tile;
    private final int count;
    private final BotDefinition[] definitions;

    BotSpawnDefinition(WorldTile tile, int count, BotDefinition... definitions) {
        this.tile = tile;
        this.count = count;
        this.definitions = definitions;

    }
}
