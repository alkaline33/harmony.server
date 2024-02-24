package com.rs.game.player.bot.behaviour.action;

import com.rs.game.Entity;
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
public class PerformSpecialAttackAction extends Action {
    private final Map<Bot, Integer[]> botWeapons = new HashMap<>();

    public PerformSpecialAttackAction() {
        super(0, 0, new InCombatCondition());
    }

    @Override
    public boolean process(Bot bot) {
        int specWeaponId = getSpecWeapon(bot);
        Player target = bot.getMetaData(MetadataDefinition.INTERACT_TARGET);
        if (specWeaponId > -1) {
            boolean switched = false;
            if (target != null) {
                if (!target.isDead() && target.getHitpoints() < target.getMaxHitpoints() / 2
                        && bot.getEquipment().getItem(Equipment.SLOT_WEAPON).getId() != specWeaponId
                        && bot.getCombatDefinitions().getSpecialAttackPercentage() >= PlayerCombat.getSpecialAmmount(specWeaponId)) {
                    if (!bot.getCombatDefinitions().isUsingSpecialAttack()) {
                        updateWeapons(bot);
                        bot.getEquipment().getItems().set(Equipment.SLOT_WEAPON, new Item(specWeaponId));
                        if (bot.getEquipment().hasTwoHandedWeapon())
                            bot.getEquipment().getItems().set(Equipment.SLOT_SHIELD, null);
                        bot.getCombatDefinitions().switchUsingSpecialAttack();
                        switched = true;
                    }
                } else if (bot.getEquipment().getItem(Equipment.SLOT_WEAPON).getId() == specWeaponId) {
                    if (target.getHitpoints() > target.getMaxHitpoints() / 2) {
                        if (bot.getCombatDefinitions().isUsingSpecialAttack())
                            bot.getCombatDefinitions().switchUsingSpecialAttack();
                        bot.getEquipment().getItems().set(Equipment.SLOT_WEAPON, new Item(getWeapon(bot)));
                        bot.getEquipment().getItems().set(Equipment.SLOT_SHIELD, new Item(getShield(bot)));
                        switched = true;
                    }
                }
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

    private Integer[] getBotWeapons(Bot bot) {
        if (!botWeapons.containsKey(bot)) {
            updateWeapons(bot);
        }

        return botWeapons.get(bot);
    }

    protected void updateWeapons(Bot bot) {
        int weapon = bot.getEquipment().getWeaponId();
        int shield = bot.getEquipment().getShieldId();
        int specWeapon = -1;
        SpecialWeaponDefinition spec = bot.getMetaData(MetadataDefinition.SPEC_WEAPON);
        if (spec != null)
            specWeapon = spec.getId();
        Integer[] weapons = {weapon, shield, specWeapon};
        botWeapons.put(bot, weapons);
    }

    protected int getWeapon(Bot bot) {
        return getBotWeapons(bot)[0];
    }

    protected int getShield(Bot bot) {
        return getBotWeapons(bot)[1];
    }

    protected int getSpecWeapon(Bot bot) {
        return getBotWeapons(bot)[2];
    }
}
