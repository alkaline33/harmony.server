package com.rs.game.player.dialogues.impl;

import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.interfaces.MasterSlayerTaskInterface;

/**
 * 
 * @author Mr_Joopz
 */
public class TrimmedMasterCapePerks extends Dialogue {

    @Override
    public void start() {
		sendOptionsDialogue("Select a Master Cape", "Construction", "Firemaking", "Hunter", "Magic", "Slayer");
    }

    @Override
    public void run(int interfaceId, int componentId) {
    	switch (stage) {
    	case -1:
    		switch (componentId) {
    		case OPTION_1:
    			if (player.isTWorthy() && player.getSkills().getXp(Skills.CONSTRUCTION) >= 104273167) {
    				player.getDialogueManager().startDialogue("EnterHouseD");
    				end();
    			} else {
    				player.getDialogueManager().startDialogue("SimpleMessage", "You don't have the requirements to do this!");
    			}
	    		break;
    		case OPTION_2:
    			if (player.isTWorthy() && player.getSkills().getXp(Skills.FIREMAKING) >= 104273167) {
    				player.setLastBonfire(60 * 100);
    				int percentage = (int) (1.1 * 100 - 100);
    				player.getPackets().sendGameMessage("<col=00ff00>The bonfire's warmth increases your maximum health by " + percentage + "%. This will last " + 60 + " minutes.");
    				player.getEquipment().refreshConfigs(false);
    				player.getDialogueManager().startDialogue("SimpleMessage", "<col=00ff00>The bonfire's warmth increases your maximum health by " + percentage + "%. This will last " + 60 + " minutes.");
    			} else {
    				player.getDialogueManager().startDialogue("SimpleMessage", "You don't have the requirements to do this!");
    			}
	    		break;
    		case OPTION_3:
    			if (player.isTWorthy() && player.getSkills().getXp(Skills.HUNTER) >= 104273167) {
    				player.DailymasterChins();
    				player.getPackets().sendGameMessage("You attempt to claim your daily chins....");
    			} else {
    				player.getDialogueManager().startDialogue("SimpleMessage", "You don't have the requirements to do this!");
    			}
    			break;
    		case OPTION_4:
    			if (player.isTWorthy() && player.getSkills().getXp(Skills.MAGIC) >= 104273167) {
    				player.getDialogueManager().startDialogue("AltarSpells");
    			} else {
    				player.getDialogueManager().startDialogue("SimpleMessage", "You don't have the requirements to do this!");
    			}
    			break;
    		case OPTION_5:
    			if (player.isTWorthy() && player.getSkills().getXp(Skills.SLAYER) >= 104273167) {
    				player.getInterfaceManager().closeInterfaceCustom();
    				MasterSlayerTaskInterface.OpenInterface(player);
    			} else {
    				player.getDialogueManager().startDialogue("SimpleMessage", "You don't have the requirements to do this!");
    			}
    			break;
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