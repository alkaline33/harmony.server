package com.rs.game.player.dialogues.impl;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.content.bossinstance.BossInstance;
import com.rs.game.player.content.bossinstance.BossInstanceManager;
import com.rs.game.player.content.bossinstance.impl.RaptorInstance;
import com.rs.game.player.content.bossinstance.impl.ZulrahInstance;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Colors;

public class PrestigeBossTeleporter extends Dialogue {

	private int npcId = 8641;

	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9827,
				"Hey "
						+ player.getUsername()
						+ ", Which boss would you like to fight?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Select a boss to teleport to.",
					"Huge Troll",
					"Celestia, Defender of Sliske",
					"the Raptor");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				player.setNextWorldTile(new WorldTile(4451, 4422, 0));
				player.sendMessage(Colors.cyan + "Your Prestige Boss additional drop rate boost for your xp mode & prestige level is: " + player.PrestigeDropBuff() + "%!");
			} else if (componentId == OPTION_2) {
				int prestigereq = player.isHeroic() ? 1 : player.isHard() ? 3 : player.isAverage() ? 5 : 8;
				if (player.prestigeTokens < prestigereq) {
					player.getPackets().sendGameMessage("You need a prestige level of "+prestigereq+" to fight Celestia!");
					end();
					return;
				}
				player.setNextWorldTile(new WorldTile(3555, 9763, 0));
				end();
				return;
			} else if (componentId == OPTION_3) {	
				if (!World.isHomeArea(player)) {
					player.sendMessage("You must be in the home area to use this teleport.");
					end();
					return;
				} else {
				BossInstance instance = null;
				instance = new RaptorInstance(player);
				BossInstanceManager.SINGLETON.add(instance);
				end();
				}
			}
		
		} else {
			end();
	}
}

	@Override
	public void finish() {

	}

}