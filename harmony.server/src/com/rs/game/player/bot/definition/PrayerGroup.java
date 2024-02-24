package com.rs.game.player.bot.definition;

import com.rs.game.player.Player;

/**
 * Created by Valkyr on 31/05/2016.
 */
public enum PrayerGroup {
    OVERHEAD(PrayersDefinition.CURSES_DEFLECT_MAGIC, PrayersDefinition.CURSES_DEFLECT_MELEE, PrayersDefinition.CURSES_DEFLECT_MISSILES, PrayersDefinition.REGULAR_PROTECT_FROM_MAGIC, PrayersDefinition.REGULAR_PROTECT_FROM_MELEE, PrayersDefinition.REGULAR_PROTECT_FROM_MISSILES, PrayersDefinition.REGULAR_SMITE, PrayersDefinition.CURSES_SOUL_SPLIT, PrayersDefinition.CURSES_WRATH),
    BONUS(PrayersDefinition.CURSES_TURMOIL, PrayersDefinition.REGULAR_PIETY, PrayersDefinition.REGULAR_AUGURY, PrayersDefinition.REGULAR_RIGOUR),
    ;

    private final PrayersDefinition[] definitions;

    PrayerGroup(PrayersDefinition... definitions) {
        this.definitions = definitions;
    }

    public static PrayersDefinition[] getUseable(Player player) {
        return null;
    }
}
