package com.rs.game.player.dialogues.impl;

import com.rs.game.ForceTalk;
import com.rs.game.npc.NPC;
import com.rs.game.player.content.Lottery;
import com.rs.game.player.dialogues.Dialogue;

/**
 * 
 * @author Savions Sw
 *
 */
public class LotteryDialogue extends Dialogue {

	/**
	 * The id of the npc.
	 */
	private NPC npc;
	
	@Override
	public void finish() {
		
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case 0:
			stage = 1;
			sendOptionsDialogue("Enter lottery", "Yes, I would love to win that jackpot!", "No thank you.");
			break;
		case 1:
			if (componentId == OPTION_1)
				Lottery.INSTANCE.addPlayer(player, npc);
			end();
			break;
		}
	}

	@Override
	public void start() {
		npc = (NPC) parameters[0];
		if (player.getPrize() != null) {
			if (player.getInventory().getFreeSlots() < 28) {
				player.getPackets().sendGameMessage("Please empty your inventory before claiming.");
				end();
				return;
			}
			player.getInventory().addItem(player.getPrize());
			player.getPackets().sendGameMessage("Enjoy your claim.");
			player.setPrize(null);
			end();
			return;
		}
		stage = 0;
		if (npc != null)
			npc.setNextForceTalk(new ForceTalk("Enter the lottery now! The current jackpot amount is at " + Lottery.INSTANCE.getFormattedNumber(Lottery.INSTANCE.getPrize().getAmount()) + "!"));
		sendNPCDialogue(npc.getId(), 9827, "Would you like to buy a lottery ticket? A Ticket cost " + Lottery.TICKET_PRICE.getAmount() + "x " + Lottery.TICKET_PRICE.getDefinitions().name + ".");
	}
}