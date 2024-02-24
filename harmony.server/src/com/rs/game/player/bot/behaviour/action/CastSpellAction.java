package com.rs.game.player.bot.behaviour.action;

import com.rs.game.player.Equipment;
import com.rs.game.player.bot.Bot;
import com.rs.game.player.bot.definition.MagicDefinition;
import com.rs.game.player.bot.definition.MetadataDefinition;
import com.rs.game.player.bot.definition.SpecialWeaponDefinition;
import com.rs.game.player.content.Combat;
import com.rs.game.player.content.Magic;

/**
 * Created by Valkyr on 21/05/2016.
 */
public class CastSpellAction extends Action {

    public CastSpellAction() {
        super(0, 0);
    }

    @Override
    public boolean process(Bot bot) {
        MagicDefinition spell = bot.getMetaData(MetadataDefinition.MAGIC_SPELL);
        if (spell != null) {
            if (spell.equals(MagicDefinition.VENGEANCE)) {
                Magic.processLunarSpell(bot, spell.getSpellId(), -1);
            } else {
                int weaponType = bot.getCombatDefinitions().getType(Equipment.SLOT_WEAPON);
                if (bot.getCombatDefinitions().getAutoCastSpell() != 0 && weaponType != Combat.MAGIC_TYPE) {
                    setAutocast(bot, spell, false);
                    return true;
                } else if(bot.getCombatDefinitions().getAutoCastSpell() == 0 && weaponType == Combat.MAGIC_TYPE) {
                    setAutocast(bot, spell, true);
                    return true;
                }
            }
        }
        return false;
    }

    private void setAutocast(Bot bot, MagicDefinition spellDefinition, boolean enable) {
        int spell = spellDefinition.getSpellId();
        int book = spellDefinition.getSpellBook();
        if (!enable) spell = 0;
        if (bot.getCombatDefinitions().getSpellBook() != book)
            bot.getCombatDefinitions().setSpellBook(book);
        bot.getCombatDefinitions().setAutoCastSpell(spell);
    }
}
