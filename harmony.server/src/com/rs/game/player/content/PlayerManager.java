package com.rs.game.player.content;

import com.rs.game.player.Player;

/**
 * @author Dikkekont
 */
public class PlayerManager {

	public static void beginIntroduction(Player player) {
		player.getInterfaceManager().closeChatBoxInterface();
		player.getInventory().refresh();
		player.lock();
		player.getDialogueManager().startDialogue("NewUser");
	}
	public static void SelectStarter(Player player) {
		player.getInterfaceManager().closeChatBoxInterface();
		player.getInventory().refresh();
		player.getDialogueManager().startDialogue("Starter");
	}
	public static void complete(Player player) {
		player.unlock();
		player.sendMessage("Welcome to Harmony! Your adventure starts now!");
		player.getInventory().refresh();
	}
	
}
