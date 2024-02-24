package com.rs.game.player.quests.impl;

import com.rs.game.player.Player;


/**
 * Easter Event
 * 
 **/

public class EasterEvent {
	
	public static void handleProgressQuest(final Player player) {
		player.startedEasterEvent = true;
		player.inProgressEasterEvent = true;
		//player.getPackets().sendConfig(160, 1);
		player.getInterfaceManager().sendInterfaces();
		player.getPackets().sendUnlockIComponentOptionSlots(190, 15, 0, 201, 0, 1, 2, 3);
	}
		
	public static void handleQuestCompleteInterface(final Player player) {
		player.getInterfaceManager().sendInterface(277);
		player.getPackets().sendIComponentText(277, 4, "You have completed the Easter Event.");
		player.getPackets().sendIComponentText(277, 7, "");
		player.getPackets().sendIComponentText(277, 9, "You are awarded:");
		player.getPackets().sendIComponentText(277, 10, "Easter Bunny Mask");
		player.getPackets().sendIComponentText(277, 11, "");
		player.getPackets().sendIComponentText(277, 12, "Collect 100 bunny ears for the easter bunny for a new item!");
		player.getPackets().sendIComponentText(277, 13, "");
		player.getPackets().sendIComponentText(277, 14, "");
		player.getPackets().sendIComponentText(277, 15, "");
		player.getPackets().sendIComponentText(277, 16, "");
		player.getPackets().sendIComponentText(277, 17, "");
		player.getPackets().sendItemOnIComponent(277, 5, 1037, 1);
		player.getPackets().sendGameMessage("The Queen of Snow hands you a hand crafted Easter egg hat.");
		player.getPackets().sendGameMessage("Congratulations! You have completed the Easter Event!");
		}

	public static void handleQuestComplete(final Player player) {
		player.inProgressEasterEvent = false;
		player.completedEasterEvent = true;
		player.getInventory().addItemDrop(29828, 1);
		player.getPackets().sendConfig(160, 2);
		player.getInterfaceManager().sendInterfaces();
		player.getPackets().sendUnlockIComponentOptionSlots(190, 15, 0, 201, 0, 1, 2, 3);
		
	}
	}