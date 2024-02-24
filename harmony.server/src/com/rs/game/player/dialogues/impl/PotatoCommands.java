package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.Equipment;
import com.rs.game.player.Player;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Utils;

public class PotatoCommands extends Dialogue {
	
	/*
	 * Author = Username.. / joopz
	 */

	private int npcId = 11583;

	@Override
	public void start() {
		if (player.getRights() < 2) {
			player.getPackets().sendGameMessage("Potato is in no mood to talk to you.");
			end();
			return;
		}
		//npcId = (Integer) parameters[0];
		sendEntityDialogue(SEND_2_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(1).name,
						"Good day to you " + Utils.formatPlayerNameForDisplay(player.getUsername()) + "! Serving you at my best.",
						" What command will it be sir?" }, IS_NPC, npcId, 9845);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (player.rights <= 1) {
			World.sendWorldMessage("<img=7><col=ff0000>News: "+player.getDisplayName()+" Has just tried to use an Administrator item!", false);
			player.getInventory().deleteItem(5733, 2147000000);
			return;
		}
		if (stage == -1) {
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "Show me them my good friend!" },
					IS_PLAYER, player.getIndex(), 9845);
			stage = 1;
	} else if (stage == 1) {
			sendOptionsDialogue("Administrator Commands!", "Spawn items", "Account stuff", "Server stuff", "Nothing");
			stage = 2;
			
	} else if (stage == 2) {
		if (componentId == OPTION_1) {
			sendOptionsDialogue("Spawn Commands!", "Admin Clothing", "Void pkin", "Max cash", "Runes");
			stage = 3;
		} else if (componentId == OPTION_2) {
			sendOptionsDialogue("Account Commands!", "Wipe bank", "Master (14M xp)", "1k GWD KC", "Turn into Npc");
			stage = 4;
		}else if (componentId == OPTION_3) {
				sendOptionsDialogue("Server Commands!", "Free 2 spins to World", "Event is live", "10% d/r boost (30 mins)", "World Emote");
			stage = 5;
		}else if (componentId == OPTION_4) {
			player.getInterfaceManager().closeChatBoxInterface();
			player.getInterfaceManager().closeOverlay(true);
		}
		
	} else if (stage == 3) {
		if (componentId == OPTION_1) {
			player.getEquipment().getItems().set(Equipment.SLOT_HAT, new Item(29789, 1));
			player.getEquipment().getItems().set(Equipment.SLOT_AMULET, new Item(29724, 1));
			player.getEquipment().getItems().set(Equipment.SLOT_HANDS, new Item(29710, 1));
			player.getEquipment().getItems().set(Equipment.SLOT_RING, new Item(773, 1));
			player.getEquipment().getItems().set(Equipment.SLOT_CAPE, new Item(20771, 1));
			player.getEquipment().getItems().set(Equipment.SLOT_FEET, new Item(9005, 1));
			player.getEquipment().getItems().set(Equipment.SLOT_SHIELD, new Item(13742, 1));
			player.getEquipment().getItems().set(Equipment.SLOT_WEAPON, new Item(25202, 1));
			player.getEquipment().refresh(1);
			player.getEquipment().refresh(2);
			player.getEquipment().refresh(3);
			player.getEquipment().refresh(4);
			player.getEquipment().refresh(5);
			player.getEquipment().refresh(6);
			player.getEquipment().refresh(7);
			player.getEquipment().refresh(8);
			player.getEquipment().refresh(9);
			player.getEquipment().refresh(10);
			player.getEquipment().refresh(11);
			player.getEquipment().refresh(12);
			player.getInterfaceManager().closeChatBoxInterface();
			player.getInterfaceManager().closeOverlay(true);
		} else if (componentId == OPTION_2) {
			player.getInterfaceManager().closeChatBoxInterface();
			player.getInterfaceManager().closeOverlay(true);
		}else if (componentId == OPTION_3) {
			player.getInventory().addItem(995, 2147000000);
			player.getInterfaceManager().closeChatBoxInterface();
			player.getInterfaceManager().closeOverlay(true);
		}else if (componentId == OPTION_4) {
			player.getInventory().addItem(554, 2147000000);
			player.getInventory().addItem(555, 2147000000);
			player.getInventory().addItem(556, 2147000000);
			player.getInventory().addItem(557, 2147000000);
			player.getInventory().addItem(558, 2147000000);
			player.getInventory().addItem(559, 2147000000);
			player.getInventory().addItem(560, 2147000000);
			player.getInventory().addItem(561, 2147000000);
			player.getInventory().addItem(562, 2147000000);
			player.getInventory().addItem(563, 2147000000);
			player.getInventory().addItem(564, 2147000000);
			player.getInventory().addItem(565, 2147000000);
			player.getInventory().addItem(566, 2147000000);
			player.getInventory().addItem(9075, 2147000000);
			player.getInterfaceManager().closeChatBoxInterface();
			player.getInterfaceManager().closeOverlay(true);
		}
	} else if (stage == 4) {
		if (componentId == OPTION_1) {
			player.getBank().destroyTab(0);
            player.getBank().collapseEnd(1);
            player.getBank().collapseEnd(2);
            player.getBank().collapseEnd(3);
            player.getBank().collapseEnd(4);
            player.getBank().collapseEnd(5);
            player.getBank().collapseEnd(6);
            player.getBank().collapseEnd(7);
            player.getBank().collapseEnd(8);
            player.getBank().collapseEnd(9);
            player.forceLogout();
			player.getInterfaceManager().closeChatBoxInterface();
			player.getInterfaceManager().closeOverlay(true);
		} else if (componentId == OPTION_2) {
			for (int skill = 0; skill < 25; skill++)
			player.getSkills().setXp(skill, 14000000);
			player.getInterfaceManager().closeChatBoxInterface();
			player.getInterfaceManager().closeOverlay(true);
		}else if (componentId == OPTION_3) {
			player.gwdkc += 1000;
			player.getInterfaceManager().closeChatBoxInterface();
			player.getInterfaceManager().closeOverlay(true);
		}else if (componentId == OPTION_4) {
			player.getPackets().sendRunScript(108, "Npc ID: ");
			player.getTemporaryAttributtes().put("turn_into_npc", true);
			end();
			player.getInterfaceManager().closeChatBoxInterface();
			player.getInterfaceManager().closeOverlay(true);
		}
	} else if (stage == 5) {
		if (componentId == OPTION_1) {
			for (Player p :World.getPlayers()) {
				p.spins += 2;
				p.sendMessage("<col=00ff00>"+player.getDisplayName()+" has given you two free spins!");
			}
			player.getInterfaceManager().closeChatBoxInterface();
			player.getInterfaceManager().closeOverlay(true);
		} else if (componentId == OPTION_2) {
			World.sendWorldMessage("<col=ff0000> A server event is now LIVE! Join "+player.getDisplayName()+"'s FC to participate.", false);
			player.getInterfaceManager().closeChatBoxInterface();
			player.getInterfaceManager().closeOverlay(true);
		}else if (componentId == OPTION_3) {
				World.sendWorldMessage("<col=ff0000> [News] 30 minutes of 10% drop rate buff will activate shortly!", false);
				Settings.amountdonated = 25;
			player.getInterfaceManager().closeChatBoxInterface();
			player.getInterfaceManager().closeOverlay(true);
		}else if (componentId == OPTION_4) {
			player.getPackets().sendRunScript(108, "Emote ID: ");
			player.getTemporaryAttributtes().put("world_does_emote", true);
			end();
			player.getInterfaceManager().closeChatBoxInterface();
			player.getInterfaceManager().closeOverlay(true);
		}
	}
	}
	
	@Override
	public void finish() {

	}
}
