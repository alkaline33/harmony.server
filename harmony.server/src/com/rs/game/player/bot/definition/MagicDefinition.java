package com.rs.game.player.bot.definition;

import com.rs.game.item.Item;
import com.rs.game.player.bot.Bot;

/**
 * Created by Valkyr on 19/05/2016.
 */
public enum MagicDefinition {

    ICE_BARRAGE(1, 23, ItemsDefinition.DEATH_RUNE, ItemsDefinition.BLOOD_RUNE, ItemsDefinition.WATER_RUNE),
    FIRE_SURGE(1, 23, ItemsDefinition.DEATH_RUNE, ItemsDefinition.BLOOD_RUNE, ItemsDefinition.WATER_RUNE),
    VENGEANCE(2, 37, ItemsDefinition.ASTRAL_RUNE, ItemsDefinition.EARTH_RUNE, ItemsDefinition.DEATH_RUNE),;

    private final int spellBook;

    private final ItemsDefinition[] runes;
    private int spellId;

    MagicDefinition(int spellBook, int spellId, ItemsDefinition... runes) {
        this.spellBook = spellBook;
        this.spellId = spellId;
        this.runes = runes;
    }

    public int getSpellId() {
        return spellId;
    }

    public ItemsDefinition[] getRunes() {
        return runes;
    }

    public int getSpellBook() {
        return spellBook;
    }

    public void giveRunes(Bot bot) {
        for (ItemsDefinition item : runes)
            bot.getInventory().getItems().add(new Item(item.getId(), item.getAmount()));
    }
}
