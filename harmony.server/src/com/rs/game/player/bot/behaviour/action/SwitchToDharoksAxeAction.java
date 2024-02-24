package com.rs.game.player.bot.behaviour.action;

import com.rs.game.item.Item;
import com.rs.game.player.Equipment;
import com.rs.game.player.Player;
import com.rs.game.player.actions.PlayerCombat;
import com.rs.game.player.bot.Bot;
import com.rs.game.player.bot.behaviour.action.condition.InCombatCondition;
import com.rs.game.player.bot.definition.MetadataDefinition;
import com.rs.game.player.bot.definition.SpecialWeaponDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Valkyr on 21/05/2016.
 */
public class SwitchToDharoksAxeAction extends PerformSpecialAttackAction {
    private final Map<Bot, Integer[]> botWeapons = new HashMap<>();

    public SwitchToDharoksAxeAction() {
        super();
    }

    @Override
    public boolean process(Bot bot) {
        int specWeaponId = getSpecWeapon(bot);
        if (specWeaponId > -1) {
            boolean switched = false;
                if (!bot.isDead() && bot.getHitpoints() < bot.getMaxHitpoints() / 2 && bot.getEquipment().getItem(Equipment.SLOT_WEAPON).getId() != specWeaponId) {
                    updateWeapons(bot);
                    bot.getEquipment().getItems().set(Equipment.SLOT_WEAPON, new Item(specWeaponId));
                    if (bot.getEquipment().hasTwoHandedWeapon())
                        bot.getEquipment().getItems().set(Equipment.SLOT_SHIELD, null);
                    switched = true;
                } else if (bot.getHitpoints() > bot.getMaxHitpoints() / 2 && bot.getEquipment().getItem(Equipment.SLOT_WEAPON).getId() == specWeaponId) {
                    bot.getEquipment().getItems().set(Equipment.SLOT_WEAPON, new Item(getWeapon(bot)));
                    bot.getEquipment().getItems().set(Equipment.SLOT_SHIELD, new Item(getShield(bot)));
                    switched = true;
                }
            if (switched) {
                bot.getEquipment().refresh(Equipment.SLOT_WEAPON);
                bot.getEquipment().refresh(Equipment.SLOT_SHIELD);
                bot.getAppearence().generateAppearenceData();
            }
            return switched;
        }
        return false;
    }
}
