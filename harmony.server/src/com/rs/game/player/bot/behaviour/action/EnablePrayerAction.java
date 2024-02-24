package com.rs.game.player.bot.behaviour.action;


import com.rs.game.player.bot.Bot;
import com.rs.game.player.bot.definition.MetadataDefinition;
import com.rs.game.player.bot.definition.PrayersDefinition;

/**
 * Created by Valkyr on 21/05/2016.
 */
public class EnablePrayerAction extends Action {

    public EnablePrayerAction() {
        super(0, 0);
    }

    @Override
    public boolean process(Bot bot) {
        final PrayersDefinition[] prayers = bot.getMetaData(MetadataDefinition.PRAYERS);
        if (prayers != null) {
            boolean enabled = false;
            for (PrayersDefinition prayer : prayers) {
                int bookId = prayer.getBookId();
                boolean curses = bookId == 1;
                int prayerId = prayer.getPrayerId();
                if (bot.getPrayer().isAncientCurses() != curses)
                    bot.getPrayer().setPrayerBook(curses);
                if (!bot.getPrayer().usingPrayer(bookId, prayerId)) {
                    bot.getPrayer().switchPrayer(prayerId);
                    enabled = true;
                }
            }
            return enabled;
        }
        return false;
    }
}
