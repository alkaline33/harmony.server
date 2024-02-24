package com.rs.game.player.content.interfaces;

import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.player.Equipment;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class ViewProfile {

    /**
     * @author Jessica
     * @modified by Sagacity
     */

    private static final int INTER = 3090;
    private static final int SLOTS[] = {30, 34, 38, 42, 50, 54, 58, 62, 66, 70, 74, 78};
    private static final int ITEM_SLOTS[] = {302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313};

    private static final int SKILLS_CID[] = {90, 97, 103, 109, 115, 121, 127, 133, 139, 145, 151, 157, 163, 169, 175, 181, 187,
            193, 199, 205, 211, 217, 223,
            229, 235, 241};

    private static final int AURA = 0, CAPE = 1, AMULET = 2, RING = 3, HELM = 4, BODY = 5, GLOVES = 6, LEGS = 7,
            FOOT = 8, ARROWS = 9, WEAPON = 10, SHIELD = 11;

    private static ItemsContainer<Item> inventory = new ItemsContainer<Item>(28, false);



    public static void sendInterface(Player player1, Player player2) {
        if (player2.publicProfile || player1.getRights() >= 1) {
            player1.getInterfaceManager().sendInterface(INTER);
            player1.getPackets().sendIComponentText(INTER, 16, player2.getDisplayName() + "'s Profile");
            sendEquipmentItems(player1, player2);
            sendInventory(player1, player2);
            sendOtherTabs(player1, player2);
            WorldTasksManager.schedule(new WorldTask() {
                @Override
                public void run() {
                    sendLevels(player1, player2);
                }
            }, 1);
        } else {
            player1.sendMessage("This player has their profile set to private.");
        }
    }


    private static void sendLevels(Player player1, Player player2) {
        for(int i = 0; i < SKILLS_CID.length; i++) {
            if(SKILLS_CID[i] == 235) { //205
                player1.getPackets().sendIComponentText(INTER, SKILLS_CID[i], "120");
            } else {
                player1.getPackets().sendIComponentText(INTER, SKILLS_CID[i], "99");
            }
        }
        for(int i = 0; i < SKILLS_CID.length; i++) {
            int level = player2.getSkills().getLevelForXp(getSkillByOrder(i));
            player1.getPackets().sendIComponentText(INTER, SKILLS_CID[i] - 1, ""+level);
        }
        player1.getPackets().sendIComponentText(INTER, 246, ""+player2.getSkills().getTotalLevel());
    }

    private static void sendOtherTabs(Player player1, Player player2) {
        player1.getPackets().sendIComponentText(INTER, 255, "Time played");
        player1.getPackets().sendIComponentText(INTER, 256, player2.playdays + "D, " + player2.playhours + "H, " + player2.getPlayPoints() + "M");

        player1.getPackets().sendIComponentText(INTER, 264, "Total boss kills");
        player1.getPackets().sendIComponentText(INTER, 265, player2.BossKills() + "");

        player1.getPackets().sendIComponentText(INTER, 273, "Donor rank");
//    	String status = null;
//    	if(player2.isDonator()) {
//			status = "Donator";
//		} else {
//			status = "Player";
//		}

        player1.getPackets().sendIComponentText(INTER, 282, "XP Rate");
        player1.getPackets().sendIComponentText(INTER, 283, player2.getXpMode());

        player1.getPackets().sendIComponentText(INTER, 273, "Game mode");//273
        player1.getPackets().sendIComponentText(INTER, 274, player2.getGameMode());//274

        player1.getPackets().sendIComponentText(INTER, 291, "Prestiges");
        player1.getPackets().sendIComponentText(INTER, 292, player2.prestigeTokens + "");
        player1.getPackets().sendHideIComponent(INTER, 237, true);
    }

    private static int getSkillByOrder(int skill) {
        switch(skill) {
            case 0:
                return Skills.ATTACK;
            case 1:
                return Skills.HITPOINTS;
            case 2:
                return Skills.MINING;
            case 3:
                return Skills.STRENGTH;
            case 4:
                return Skills.AGILITY;
            case 5:
                return Skills.SMITHING;
            case 6:
                return Skills.DEFENCE;
            case 7:
                return Skills.HERBLORE;
            case 8:
                return Skills.FISHING;
            case 9:
                return Skills.RANGE;
            case 10:
                return Skills.THIEVING;
            case 11:
                return Skills.COOKING;
            case 12:
                return Skills.PRAYER;
            case 13:
                return Skills.CRAFTING;
            case 14:
                return Skills.FIREMAKING;
            case 15:
                return Skills.MAGIC;
            case 16:
                return Skills.FLETCHING;
            case 17:
                return Skills.WOODCUTTING;
            case 18:
                return Skills.RUNECRAFTING;
            case 19:
                return Skills.SLAYER;
            case 20:
                return Skills.FARMING;
            case 21:
                return Skills.CONSTRUCTION;
            case 22:
                return Skills.HUNTER;
            case 23:
                return Skills.SUMMONING;
            case 24:
                return Skills.DUNGEONEERING;
            case 25:
                return Skills.DUNGEONEERING;
        }
        return -1;
    }

    private static void sendItem(Player player, int slot, int item) {
        player.getPackets().sendInterSetItemsOptionsScript(INTER, ITEM_SLOTS[slot], slot, 3, 3, "Examine");
        player.getPackets().sendUnlockIComponentOptionSlots(INTER, ITEM_SLOTS[slot], 0, 160, 0);
        player.getPackets().sendItems(slot, new Item[]{new Item((Integer) item, 1)});
        player.getPackets().sendHideIComponent(INTER, SLOTS[slot]+3, true);
    }

    private static void sendEquipmentItems(Player player1, Player player2) {
        for(Item item : player2.getEquipment().getItems().getItems()) {
            if(item == null)
                continue;
            int itemId = item.getId();
            int slotId = getFixedSlot(Equipment.getItemSlot(itemId));
            sendItem(player1, slotId, itemId);
        }
    }


    private static int getFixedSlot(int slotId) {
        switch(slotId) {
            case 0:
                return HELM;
            case 1:
                return CAPE;
            case 2:
                return AMULET;
            case 3:
                return WEAPON;
            case 4:
                return BODY;
            case 5:
                return SHIELD;
            case 7:
                return LEGS;
            case 9:
                return GLOVES;
            case 10:
                return FOOT;
            case 12:
                return RING;
            case 13:
                return ARROWS;
            case 14:
                return AURA;
        }
        return -1;
    }



    public static void handleButtons(Player player, int interfaceId, int componentId) {
        switch(interfaceId) {
            case INTER:
                switch(componentId) {
                    case 9://close
                        player.closeInterfaces();
                        break;
                    case 297://equipment button
                        player.getPackets().sendHideIComponent(INTER, 26, true);
                        player.getPackets().sendHideIComponent(INTER, 92, false);
                        break;
                    case 294://skills button
                        player.getPackets().sendHideIComponent(INTER, 26, false);
                        player.getPackets().sendHideIComponent(INTER, 92, true);
                        break;
                }
                break;
        }
    }

    private static void sendInventory(Player player1, Player player2) {
        inventory.clear();
        player1.getPackets().sendInterSetItemsOptionsScript(INTER, 301, 90, 4, 7, "Examine");
        player1.getPackets().sendUnlockIComponentOptionSlots(INTER, 301, 0, 160, 0);
        for(Item item : player2.getInventory().getItems().getItems()) {
            if(item == null) {
                continue;
            }
            inventory.add(new Item(item.getId(), item.getAmount()));
        }
        player1.getPackets().sendItems(90, false, inventory);
    }
}