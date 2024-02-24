package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class Banning extends Dialogue {

    @Override
    public void start() {
	sendDialogue("Harmony's banning system.", "Follow the instructions carefully!");

    }

    @Override
    public void run(int interfaceId, int componentId) {
	if (stage == -1) {
	    player.getTemporaryAttributtes().put("reason", Boolean.TRUE);
	    player.getPackets().sendInputNameScript("Please tell us who you banned and why.");
	    end();
	    //stage = 2;
	}
   // if (stage == 2) {
    //	 player.getTemporaryAttributtes().put("reason", Boolean.TRUE);
 	 //   player.getPackets().sendInputNameScript("Please enter the reason you banned this player.");

    //}
    }

    @Override
    public void finish() {
	// TODO Auto-generated method stub

    }

}
