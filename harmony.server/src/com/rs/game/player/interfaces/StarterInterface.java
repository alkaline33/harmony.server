package com.rs.game.player.interfaces;

import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Colors;

public class StarterInterface {

	/**
	 * Author @Mr_Joopz
	 */

	private static int INTER = 3026;

	public static void SendInterface(Player player) {
		
		player.lock();
		/**
		 * Initial interface being opened.
		 */
		player.starterGameMode = 0;
		player.starterXpMode = 0;
		player.getPackets().sendIComponentSprite(INTER, 23, 941);
		player.getPackets().sendIComponentSprite(INTER, 24, 941);
		player.getPackets().sendIComponentSprite(INTER, 25, 941);
		player.getPackets().sendIComponentSprite(INTER, 22, 941);
		player.getPackets().sendIComponentSprite(INTER, 77, 941);
		player.getPackets().sendIComponentSprite(INTER, 32, 941);
		player.getPackets().sendIComponentSprite(INTER, 33, 941);
		player.getPackets().sendIComponentSprite(INTER, 34, 941);
		player.getPackets().sendIComponentSprite(INTER, 31, 941);
		
		player.getInterfaceManager().sendInterface(INTER);

		player.getPackets().sendIComponentText(INTER, 36, "Hello " + player.getDisplayName() + ",<br> please choose an experience mode &<br> game mode from the 2 panels on the left.");
		player.getPackets().sendIComponentText(INTER, 75, "");
		player.getPackets().sendIComponentText(INTER, 39, "Begin");
		player.getPackets().sendIComponentText(INTER, 16, "Welcome to Harmony");
		player.getPackets().sendIComponentText(INTER, 76, "Realism");
	}

	public static void HandleButtons(Player player, int componentId) {
	//System.out.println(componentId);
		/**
		 * Handles clicking on the interface.
		 */
		switch (componentId) {
		/**
		 * Confirmation button
		 */
		case 39:
			/**
			 * Xp & Game mode confirmation methods
			 */
			if (player.starterGameMode == 0 || player.starterXpMode == 0) {
				player.sendMessage(Colors.red+"Please choose an xp & game mode!");
				return;
			}
			if (player.starterXpMode == 1) {
				player.iseasy = true;
				player.newuserdone = true;
				player.getStarter();
				player.getInterfaceManager().closeInterfaceCustom();
				player.getDialogueManager().startDialogue("Welcome");
			} else if (player.starterXpMode == 2) {
				player.isaverage = true;
				player.newuserdone = true;
				player.getStarter();
				player.getInterfaceManager().closeInterfaceCustom();
				player.getDialogueManager().startDialogue("Welcome");
			} else if (player.starterXpMode == 3) {
				player.ishard = true;
				player.newuserdone = true;
				player.getStarter();
				player.getInterfaceManager().closeInterfaceCustom();
				player.getDialogueManager().startDialogue("Welcome");
			} else if (player.starterXpMode == 4) {
				player.isheroic = true;
				player.newuserdone = true;
				player.getStarter();
				player.getInterfaceManager().closeInterfaceCustom();
				player.getDialogueManager().startDialogue("Welcome");
			} else if (player.starterXpMode == 5) {
				player.isrealism = true;
				player.newuserdone = true;
				player.getStarter();
				player.getInterfaceManager().closeInterfaceCustom();
				player.getDialogueManager().startDialogue("Welcome");
			} else {
				player.sendMessage(Colors.red+"Please choose an xp mode!");
			}
			if (player.starterGameMode == 1) {
				
			} else if (player.starterGameMode == 2) {
				player.ironman = true;
				player.getAppearence().setTitle(9861);
			} else if (player.starterGameMode == 3) {
				player.ironman = true;
				player.hcironman = true;
				player.getAppearence().setTitle(9861);
			} else if (player.starterGameMode == 4) {
				player.ironman = true;
				player.ironmanduo = true;
				player.getAppearence().setTitle(9862);
				player.sendMessage(Colors.red+"Right click a player to request partnership. ::acceptpartner to complete & ::mypartner to see who your partner is.");
					
			} else {
				player.sendMessage(Colors.red+"Please choose an xp mode!");
			}
			break;
		/**
		 * Xp Modes
		 */
		case 22:
			player.getPackets().sendIComponentText(INTER, 36, "You will have a 125x XP rate. <br><col=ff0000>Using this experience mode means you <br><col=ff0000>cannot obtain the completionist cape!</col>");
			/**
			 * Turns off over button sprites
			 */
			player.getPackets().sendIComponentSprite(INTER, 23, 941);
			player.getPackets().sendIComponentSprite(INTER, 24, 941);
			player.getPackets().sendIComponentSprite(INTER, 25, 941);
			player.getPackets().sendIComponentSprite(INTER, 77, 941);
			/**
			 * Variable
			 */
			player.starterXpMode = 1;
			break;
		case 23:
			player.getPackets().sendIComponentText(INTER, 36, "You will have a 70x XP rate.");
			/**
			 * Turns off over button sprites
			 */
			player.getPackets().sendIComponentSprite(INTER, 22, 941);
			player.getPackets().sendIComponentSprite(INTER, 24, 941);
			player.getPackets().sendIComponentSprite(INTER, 25, 941);
			player.getPackets().sendIComponentSprite(INTER, 77, 941);
			/**
			 * Variable
			 */
			player.starterXpMode = 2;
			break;
		case 24:
			player.getPackets().sendIComponentText(INTER, 36, "You will have 25x XP rate. <br><col=00ff00>You will also have a +3% drop rate bonus!</col>");
			/**
			 * Turns off over button sprites
			 */
			player.getPackets().sendIComponentSprite(INTER, 23, 941);
			player.getPackets().sendIComponentSprite(INTER, 22, 941);
			player.getPackets().sendIComponentSprite(INTER, 25, 941);
			player.getPackets().sendIComponentSprite(INTER, 77, 941);
			/**
			 * Variable
			 */
			player.starterXpMode = 3;
			break;
		case 25:
			player.getPackets().sendIComponentText(INTER, 36, "You will have 3x XP rate. <br><col=00ff00>You will also have a +6% drop rate bonus!</col>");
			/**
			 * Turns off over button sprites
			 */
			player.getPackets().sendIComponentSprite(INTER, 23, 941);
			player.getPackets().sendIComponentSprite(INTER, 24, 941);
			player.getPackets().sendIComponentSprite(INTER, 22, 941);
			player.getPackets().sendIComponentSprite(INTER, 77, 941);
			/**
			 * Variable
			 */
			player.starterXpMode = 4;
		break;
		case 77:
			player.getPackets().sendIComponentText(INTER, 36, "You will have 1x XP rate. <br><col=00ff00>You will also have a +10% drop rate bonus!</col>");
			/**
			 * Turns off over button sprites
			 */
			player.getPackets().sendIComponentSprite(INTER, 23, 941);
			player.getPackets().sendIComponentSprite(INTER, 24, 941);
			player.getPackets().sendIComponentSprite(INTER, 22, 941);
			player.getPackets().sendIComponentSprite(INTER, 25, 941);
			/**
			 * Variable
			 */
			player.starterXpMode = 5;
		break;
		/**
		 * Game modes
		 */
		case 31:
			/**
			 * Turns off over button sprites
			 */
			player.getPackets().sendIComponentSprite(INTER, 32, 941);
			player.getPackets().sendIComponentSprite(INTER, 33, 941);
			player.getPackets().sendIComponentSprite(INTER, 34, 941);
			/**
			 * Container
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 1000000));
			player.getDmm().getContainer().add(new Item(1725, 1));
			player.getDmm().getContainer().add(new Item(1856, 1));
			player.getDmm().getContainer().add(new Item(1540, 1));
			player.getDmm().getContainer().add(new Item(380, 150));
			player.getDmm().getContainer().add(new Item(3105, 1));
			player.getDmm().getContainer().add(new Item(1321, 1));
			player.getDmm().getContainer().add(new Item(1333, 1));
			player.getDmm().getContainer().add(new Item(841, 1));
			player.getDmm().getContainer().add(new Item(857, 1));
			player.getDmm().getContainer().add(new Item(884, 250));
			player.getDmm().getContainer().add(new Item(892, 50));
			player.getDmm().getContainer().add(new Item(1359, 1));
			player.getDmm().getContainer().add(new Item(1275, 1));
			player.getDmm().getContainer().add(new Item(318, 20));
			player.getDmm().getContainer().add(new Item(1512, 20));
			player.getDmm().getContainer().add(new Item(6568, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 37, 100, 6, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 37, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(100, player.getDmm().getContainer());
			player.getPackets().sendIComponentText(INTER, 75, "Regular mode allows you to interact<br> & trade with other users.");
			/**
			 * Variable
			 */
			player.starterGameMode = 1;
			break;
		case 32:
			/**
			 * Turns off over button sprites
			 */
			player.getPackets().sendIComponentSprite(INTER, 31, 941);
			player.getPackets().sendIComponentSprite(INTER, 33, 941);
			player.getPackets().sendIComponentSprite(INTER, 34, 941);
			/**
			 * Container
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 50000));
			player.getDmm().getContainer().add(new Item(1725, 1));
			player.getDmm().getContainer().add(new Item(1856, 1));
			player.getDmm().getContainer().add(new Item(1321, 1));
			player.getDmm().getContainer().add(new Item(1333, 1));
			player.getDmm().getContainer().add(new Item(841, 1));
			player.getDmm().getContainer().add(new Item(857, 1));
			player.getDmm().getContainer().add(new Item(884, 250));
			player.getDmm().getContainer().add(new Item(892, 50));
			player.getDmm().getContainer().add(new Item(1359, 1));
			player.getDmm().getContainer().add(new Item(1275, 1));
			player.getDmm().getContainer().add(new Item(318, 20));
			player.getDmm().getContainer().add(new Item(1512, 20));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 37, 100, 6, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 37, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(100, player.getDmm().getContainer());
			player.getPackets().sendIComponentText(INTER, 75, "Ironman mode means you <br>cannot interact with other users.");
			/**
			 * Variable
			 */
			player.starterGameMode = 2;
			break;
		case 33:
			/**
			 * Turns off over button sprites
			 */
			player.getPackets().sendIComponentSprite(INTER, 32, 941);
			player.getPackets().sendIComponentSprite(INTER, 31, 941);
			player.getPackets().sendIComponentSprite(INTER, 34, 941);
			/**
			 * Container
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 50000));
			player.getDmm().getContainer().add(new Item(1725, 1));
			player.getDmm().getContainer().add(new Item(1856, 1));
			player.getDmm().getContainer().add(new Item(1321, 1));
			player.getDmm().getContainer().add(new Item(1333, 1));
			player.getDmm().getContainer().add(new Item(841, 1));
			player.getDmm().getContainer().add(new Item(857, 1));
			player.getDmm().getContainer().add(new Item(884, 250));
			player.getDmm().getContainer().add(new Item(892, 50));
			player.getDmm().getContainer().add(new Item(1359, 1));
			player.getDmm().getContainer().add(new Item(1275, 1));
			player.getDmm().getContainer().add(new Item(303, 1));
			player.getDmm().getContainer().add(new Item(318, 20));
			player.getDmm().getContainer().add(new Item(1512, 20));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 37, 100, 6, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 37, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(100, player.getDmm().getContainer());
			player.getPackets().sendIComponentText(INTER, 75, "Hardcore Ironman mode means you <br>cannot interact with other users.<br><col=ff0000>You will only have 1 life.</col>");
			/**
			 * Variable
			 */
			player.starterGameMode = 3;
			break;
		case 34:
			/**
			 * Turns off over button sprites
			 */
			player.getPackets().sendIComponentSprite(INTER, 32, 941);
			player.getPackets().sendIComponentSprite(INTER, 33, 941);
			player.getPackets().sendIComponentSprite(INTER, 31, 941);
			/**
			 * Container
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 50000));
			player.getDmm().getContainer().add(new Item(1725, 1));
			player.getDmm().getContainer().add(new Item(1856, 1));
			player.getDmm().getContainer().add(new Item(1321, 1));
			player.getDmm().getContainer().add(new Item(1333, 1));
			player.getDmm().getContainer().add(new Item(841, 1));
			player.getDmm().getContainer().add(new Item(857, 1));
			player.getDmm().getContainer().add(new Item(884, 250));
			player.getDmm().getContainer().add(new Item(892, 50));
			player.getDmm().getContainer().add(new Item(1359, 1));
			player.getDmm().getContainer().add(new Item(1275, 1));
			player.getDmm().getContainer().add(new Item(303, 1));
			player.getDmm().getContainer().add(new Item(318, 20));
			player.getDmm().getContainer().add(new Item(1512, 20));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 37, 100, 6, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 37, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(100, player.getDmm().getContainer());
			player.getPackets().sendIComponentText(INTER, 75, "Ironman mode means you can <br>partner with another Duo Ironman.<br>You can only trade with your partner.");
			/**
			 * Variable
			 */
			player.starterGameMode = 4;
			break;
			
		default:

			break;
		}
	}

}
