package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class Transportation extends Dialogue {

	// Ring of duelling
	// Combat bracelet

	public static int EMOTE = 9603, GFX = 1684;

	@Override
	public void start() {
		sendOptionsDialogue("Where would you like to teleport to",
				(String) parameters[0], (String) parameters[2],
				(String) parameters[4], (String) parameters[6], "Nowhere");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		boolean teleported = false;
		player.getTemporaryAttributtes().put("GloryTeleport", Boolean.TRUE);
		if (componentId == OPTION_1)
			teleported = Magic.sendItemTeleportSpell(player, true, EMOTE, GFX,
					4, (WorldTile) parameters[1]);
		else if (componentId == OPTION_2)
			teleported = Magic.sendItemTeleportSpell(player, true, EMOTE, GFX,
					4, (WorldTile) parameters[3]);
		else if (componentId == OPTION_3)
			teleported = Magic.sendItemTeleportSpell(player, true, EMOTE, GFX,
					4, (WorldTile) parameters[5]);
		else if (componentId == OPTION_4)
			teleported = Magic.sendItemTeleportSpell(player, true, EMOTE, GFX,
					4, (WorldTile) parameters[7]);
		if (!teleported) {
			end();
			return;
		}
		Item item = player.getInventory().getItems()
				.lookup((Integer) parameters[8]);
		if (item.getId() >= 3853 && item.getId() <= 3865
				|| item.getId() >= 10354 && item.getId() <= 10361
				|| item.getId() >= 2552 && item.getId() <= 2564) {
			item.setId(item.getId() + 2);
		} else if (item.getId() == 3867 || item.getId() == 2566) {
			item.setId(-1);
		} else {
			item.setId(item.getId() - 2);
		}
		player.getTemporaryAttributtes().remove("GloryTeleport");
		player.getInventory().refresh(
				player.getInventory().getItems().getThisItemSlot(item));
		end();
	}

	@Override
	public void finish() {
	}

}
