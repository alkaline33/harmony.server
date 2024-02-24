/*package com.rs.game.player.content;

import java.util.Arrays;


import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.AncientEffigyD;


*//**
 *
 * @author _Hassan <https://www.rune-server.ee/members/_hassan/> Created: 18 Apr
 *         2017 ~ server
 *
 *//*


public class AncientEffigies {


    private static AncientEffigy instance;


    public static final AncientEffigy getInstance() {
        if (instance == null)
            instance = new AncientEffigy();
        return instance;
    }


    private Item item;


    private final Object[][] EMOTES = { { new Animation(14177), new Graphics(2692) },
            { new Animation(4068), new Animation(4067) } };


    private final Item[] EFFIGIES = { new Item(18778), new Item(18779), new Item(18780), new Item(18781) };


    private final int[] SKILL_1 = { Skills.AGILITY, Skills.CONSTRUCTION, Skills.COOKING, Skills.FISHING,
            Skills.FLETCHING, Skills.HERBLORE, Skills.MINING, Skills.SUMMONING };


    private final int[] SKILL_2 = { Skills.CRAFTING, Skills.THIEVING, Skills.FIREMAKING, Skills.FARMING,
            Skills.WOODCUTTING, Skills.HUNTER, Skills.SMITHING, Skills.RUNECRAFTING };


    private final String[] SKILL_MESSAGES = { "deftness and precision", "buildings and security",
            "fire and preparation", "life and cultivation", "lumber and woodworking", "flora and fuana",
            "metalwork and minerals", "binding essence and spirits" };


    public boolean isEffigy(Player player, Item item) {
        this.item = item;
        if (item.getId() == effigy().getId()) {
            player.getDialogueManager().startDialogue(AncientEffigyD.class, item);
            return true;
        }
        return false;
    }


    public void advanceEffigy(Player player, Item item) {
        player.lock(5);
        player.getInventory().deleteItem(effigy().getId(), 1);
        player.getInventory().addItem(effigy().getId() + 1, 1);
        player.setNextAnimation((Animation) EMOTES[1][0]);
        if (effigy().getId() + 1 == 18782)
            player.setAniAndGfx((Animation) EMOTES[0][0], (Graphics) EMOTES[0][1]);
    }


    private final Item effigy() {
        return Arrays.stream(EFFIGIES).filter(effigy -> item.getId() == effigy.getId()).findAny().orElse(null);
    }


    public int[] getSkills(int type) {
        return type == 0 ? SKILL_1 : SKILL_2;
    }


    public String getMessage(int skill) {
        return SKILL_MESSAGES[skill];
    }


    public int getRequiredLvl(Item item) {
        switch (item.getId()) {
        case 18778:
            return 91;
        case 18779:
            return 93;
        case 18780:
            return 95;
        case 18781:
            return 97;
        }
        return -1;
    }


    public int getXP(Item item) {
        switch (item.getId()) {
        case 18778:
            return 15000;
        case 18779:
            return 20000;
        case 18780:
            return 25000;
        case 18781:
            return 30000;
        }
        return -1;
    }


}*/