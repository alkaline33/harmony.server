package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Colors;

/**
 * 
 * @author Mr_Joopz
 */
public class BrothercorruptionD extends Dialogue {

    @Override
    public void start() {
    	int itemId = 0;
    	itemId = (Integer) parameters[0];
    	if (itemId == 28991) {
    		stage = 1;
    		sendOptionsDialogue("Select a Corrupted Verac's item to craft", "Helm (2000)", "Brassard (5000)", "Plateskirt (3000)");
    	} else if (itemId == 28990) {
    		stage = 11;
    		sendOptionsDialogue("Select a Corrupted Ahrim's item to craft", "Hood (2000)", "Robe Top (5000)", "Robe Skirt (3000)");
    	} else if (itemId == 28989) {
    		stage = 21;
    		sendOptionsDialogue("Select a Corrupted Karil's item to craft", "Hood (2000)", "Top (5000)", "Skirt (3000)");
    	} else {
    		end();
    	}
    }

    @Override
    public void run(int interfaceId, int componentId) {
    	switch (stage) {
    	case 1:
    		/**
    		 * Verac's
    		 */
    		if (componentId == OPTION_1) {
    			if (player.getInventory().containsItem(28991, 2000)){
    				player.getInventory().deleteItem(28991, 2000);
    				player.getInventory().addItemDrop(28997, 1);
    				end();
    			} else {
    				player.sendMessage(Colors.red+"You do not have enough corruption to do this!");
    				end();
    			}
    		} else if (componentId == OPTION_2) {
    			if (player.getInventory().containsItem(28991, 5000)){
    				player.getInventory().deleteItem(28991, 5000);
    				player.getInventory().addItemDrop(28996, 1);
    				end();
    			} else {
    				player.sendMessage(Colors.red+"You do not have enough corruption to do this!");
    				end();
    			}
    		} else if (componentId == OPTION_3) {
    			if (player.getInventory().containsItem(28991, 3000)){
    				player.getInventory().deleteItem(28991, 3000);
    				player.getInventory().addItemDrop(28995, 1);
    				end();
    			} else {
    				player.sendMessage(Colors.red+"You do not have enough corruption to do this!");
    				end();
    			}
    		}
    		break;
    		
    	case 11:
    		/**
    		 * Ahrim's
    		 */
    		if (componentId == OPTION_1) {
    			if (player.getInventory().containsItem(28990, 2000)){
    				player.getInventory().deleteItem(28990, 2000);
    				player.getInventory().addItemDrop(29000, 1);
    				end();
    			} else {
    				player.sendMessage(Colors.red+"You do not have enough corruption to do this!");
    				end();
    			}
    		} else if (componentId == OPTION_2) {
    			if (player.getInventory().containsItem(28990, 5000)){
    				player.getInventory().deleteItem(28990, 5000);
    				player.getInventory().addItemDrop(28999, 1);
    				end();
    			} else {
    				player.sendMessage(Colors.red+"You do not have enough corruption to do this!");
    				end();
    			}
    		} else if (componentId == OPTION_3) {
    			if (player.getInventory().containsItem(28990, 3000)){
    				player.getInventory().deleteItem(28990, 3000);
    				player.getInventory().addItemDrop(28998, 1);
    				end();
    			} else {
    				player.sendMessage(Colors.red+"You do not have enough corruption to do this!");
    				end();
    			}
    		}
    		break;
    		
    	case 21:
    		/**
    		 * Karil's
    		 */
    		if (componentId == OPTION_1) {
    			if (player.getInventory().containsItem(28989, 2000)){
    				player.getInventory().deleteItem(28989, 2000);
    				player.getInventory().addItemDrop(28994, 1);
    				end();
    			} else {
    				player.sendMessage(Colors.red+"You do not have enough corruption to do this!");
    				end();
    			}
    		} else if (componentId == OPTION_2) {
    			if (player.getInventory().containsItem(28989, 5000)){
    				player.getInventory().deleteItem(28989, 5000);
    				player.getInventory().addItemDrop(28993, 1);
    				end();
    			} else {
    				player.sendMessage(Colors.red+"You do not have enough corruption to do this!");
    				end();
    			}
    		} else if (componentId == OPTION_3) {
    			if (player.getInventory().containsItem(28989, 3000)){
    				player.getInventory().deleteItem(28989, 3000);
    				player.getInventory().addItemDrop(28992, 1);
    				end();
    			} else {
    				player.sendMessage(Colors.red+"You do not have enough corruption to do this!");
    				end();
    			}
    		}
    		break;
    		
    		
    	case 10:
    		finish();
    		break;
    	}
    }

    @Override
    public void finish() { player.getInterfaceManager().closeChatBoxInterface(); }

}