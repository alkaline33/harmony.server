package com.rs.game.player.interfaces;

import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;
import com.rs.utils.Colors;

public class SkillTeleportInterface {

	/**
	 * 
	 * @author Connor
	 */

	public static void sendInterface(Player player) {
		int INTER = 583;
		player.getInterfaceManager().sendInterface(INTER);
		player.getPackets().sendIComponentText(INTER, 14, "Skilling Teleports");
		player.getPackets().sendIComponentText(INTER, 50, "Skiller Island");
		player.getPackets().sendIComponentText(INTER, 51, "Woodcutting");
		player.getPackets().sendIComponentText(INTER, 52, "Thieving");
		player.getPackets().sendIComponentText(INTER, 53, "Summoning");
		player.getPackets().sendIComponentText(INTER, 54, "Hunter");
		player.getPackets().sendIComponentText(INTER, 55, "Gnome Agility Course");
		player.getPackets().sendIComponentText(INTER, 56, "Barbarian Outpost Course");
		player.getPackets().sendIComponentText(INTER, 57, "Mining");
		player.getPackets().sendIComponentText(INTER, 58, "Fishing");
		player.getPackets().sendIComponentText(INTER, 59, "Runecrafting Abyss");
		player.getPackets().sendIComponentText(INTER, 71, "ZMI Altar");
		player.getPackets().sendIComponentText(INTER, 60, "Farming");
		player.getPackets().sendIComponentText(INTER, 61, "Smithing");
		player.getPackets().sendIComponentText(INTER, 62, "Shilo Village");
		player.getPackets().sendIComponentText(INTER, 64, "Essence Mining");
		player.getPackets().sendIComponentText(INTER, 63, "Dungeoneering");
		player.getPackets().sendIComponentText(INTER, 65, "Charging Orb Obelisk's");
		player.getPackets().sendIComponentText(INTER, 70, "Ingenuity");
		player.getPackets().sendIComponentText(INTER, 66, "Black Chinchompas <col=ffff00>PVP</col>");
		player.getPackets().sendIComponentText(INTER, 67, "Puro Puro");
		player.getPackets().sendIComponentText(INTER, 68, "Wilderness Skilling Area<br> (<col=00ff00>2x Skill points</col>) <col=ffff00>PVP</col>");
		player.getPackets().sendIComponentText(INTER, 69, "Living Rock Cavern");
		player.getPackets().sendIComponentText(INTER, 72, "Construction");
		player.getPackets().sendIComponentText(INTER, 73, "");
		player.getPackets().sendIComponentText(INTER, 77, "");
		player.getPackets().sendIComponentText(INTER, 78, "");

		player.getPackets().sendIComponentText(INTER, 79, "");
		player.getPackets().sendIComponentText(INTER, 81, "");
		player.getPackets().sendIComponentText(INTER, 80, "");
		player.getPackets().sendIComponentText(INTER, 75, "");
		player.getPackets().sendIComponentText(INTER, 76, "");
		player.getPackets().sendIComponentText(INTER, 74, "");
		player.skilltelingcomponent = true;

	}

	public static boolean handleButtons(Player player, int componentId) {
		int npcId = 0;
		// System.out.println(componentId);
		switch (componentId) {
		case 50: // Teleport location button handler.
			player.skilltelingcomponent = false;
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3808, 3569, 0));
			return true;
		case 51: // Teleport location button handler.
			player.skilltelingcomponent = false;
			player.getInterfaceManager().closeInterfaceCustom();
			player.getDialogueManager().startDialogue("WoodcuttingTeleportD");
			//Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2725, 3491, 0));//woodcutting
			return true;
		case 52:
			player.skilltelingcomponent = false;
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2662, 3303, 0));
			return true;
		case 53:
			player.skilltelingcomponent = false;
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2333, 10015, 0));
			return true;
		case 54:
			player.skilltelingcomponent = false;
			player.getInterfaceManager().closeInterfaceCustom();
			player.getDialogueManager().startDialogue("HunterTeleportD");
			return true;
		case 55:
			player.skilltelingcomponent = false;
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2468, 3437, 0));
			return true;
		case 56:
			player.skilltelingcomponent = false;
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2552, 3563, 0));
			return true;
		case 57:
			player.skilltelingcomponent = false;
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3298, 3283, 0));
			return true;
		case 58:
			player.skilltelingcomponent = false;
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2599, 3421, 0));
			return true;
		case 59:
			player.skilltelingcomponent = false;
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3042, 4844, 0));
			return true;
		case 71:
			player.skilltelingcomponent = false;
			if (!player.completedruesaltar) {
				player.getInterfaceManager().closeInterfaceCustom();
				player.sendMessage(Colors.red+"You must've completed Rue's Altar quest before accessing this altar.");
				return false;
			}
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3305, 4809, 0));
			return true;
		case 60:
			player.skilltelingcomponent = false;
			player.getInterfaceManager().closeInterfaceCustom();
			player.getDialogueManager().startDialogue("FarmingTeleports");
			return true;
		case 61:
			player.skilltelingcomponent = false;
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1751, 5289, 1));
			return true;
		case 62:
			player.skilltelingcomponent = false;
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2825, 2999, 0));//
			return true;
		case 64:
			player.skilltelingcomponent = false;
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2924, 4819, 0));
			return true;
		case 63:
			player.skilltelingcomponent = false;
			player.getInterfaceManager().closeInterfaceCustom();
			player.getDialogueManager().startDialogue("Dunghandler");
			return true;
		case 65:
			player.skilltelingcomponent = false;
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2840, 3420, 0));
			return true;
		case 70:
			player.skilltelingcomponent = false;
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2591, 3909, 0));
			player.sendMessage("NOTE: Only players with a total level of 1500 can loot the chest, however others can still gain experience.");
			return true;
		case 66:
			player.skilltelingcomponent = false;
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3137, 3778, 0));
			player.getControlerManager().startControler("Wilderness");
			return true;
		case 67:
			player.skilltelingcomponent = false;
			player.getInterfaceManager().closeInterfaceCustom();
			player.getControlerManager().startControler("PuroPuro");
			return true;
		case 68:
			player.skilltelingcomponent = false;
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3186, 3933, 0));
			player.sendMessage(Colors.red + "Right-click and picklock the gate to enter.");
			player.getControlerManager().startControler("Wilderness");
			return true;
		case 69:
			player.skilltelingcomponent = false;
			player.getInterfaceManager().closeInterfaceCustom();
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3656, 5113, 0));
			return true;
		case 72:
			player.skilltelingcomponent = false;
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2542, 3095, 0));
			return true;

		}
		return false;
	}

}
