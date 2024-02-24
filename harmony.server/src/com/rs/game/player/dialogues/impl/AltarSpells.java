package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

/**
 * 
 * @author Mr_Joopz
 */
public class AltarSpells extends Dialogue {

    @Override
    public void start() {
		sendOptionsDialogue("Select an Option", "Switch between ancients & normal", "Switch to lunar spells");
    }

    @Override
    public void run(int interfaceId, int componentId) {
    	switch (stage) {
    	case -1:
    		switch (componentId) {
    		case OPTION_1:
				player.getDialogueManager().startDialogue("AncientAltar");
	    		break;
    		case OPTION_2:
    			player.getDialogueManager().startDialogue("LunarAltar");
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