package com.rs.game.player.bot.definition;

/**
 * Created by Valkyr on 19/05/2016.
 */
public enum PrayersDefinition {

    REGULAR_PROTECT_ITEM(0, 10),
    REGULAR_PROTECT_FROM_MAGIC(0, 17),
    REGULAR_PROTECT_FROM_MISSILES(0, 18),
    REGULAR_PROTECT_FROM_MELEE(0, 19),
    REGULAR_SMITE(0, 24),
    REGULAR_PIETY(0, 27),
    REGULAR_RIGOUR(0, 28),
    REGULAR_AUGURY(0, 29),
    CURSES_PROTECT_ITEM(1, 0),
    CURSES_BERSERKER(1, 5),
    CURSES_DEFLECT_MAGIC(1, 7),
    CURSES_DEFLECT_MISSILES(1, 8),
    CURSES_DEFLECT_MELEE(1, 9),
    CURSES_WRATH(1, 17),
    CURSES_SOUL_SPLIT(1, 18),
    CURSES_TURMOIL(1, 19),
    //25, 70, 52
    ;

    private final int prayerId;
    private final int bookId;

    PrayersDefinition(int bookId, int prayerId) {
        this.bookId = bookId;
        this.prayerId = prayerId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getPrayerId() {
        return prayerId;
    }
}
