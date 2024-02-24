package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Colors;

public class CatacombAltarD extends Dialogue {

	@Override
	public void start() {
		sendDialogue("What would you like to do?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			stage = 0;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Teleport to Skotizo", "Pay dwarf cannon daily 5M fee", "Nothing");
		} else if (stage == 0) {
			if (componentId == OPTION_1) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1696, 9886, 0));
				player.sendMessage("Once you have killed Skotizo, it will consume your totem, should you not have a totem, you will not obtain a drop.");
					end();
					return;
			}
			if (componentId == OPTION_2) {
				if (player.dailydwarfcatacombfee == true) {
					player.sendMessage("You have already paid your daily fee!");
					end();
					return;
				}
				if (player.getInventory().containsItem(995, 5000000)) {
					player.getInventory().deleteItem(995, 5000000);
					Settings.GpSyncAmount += 5000000;
					player.dailydwarfcatacombfee = true;
					player.sendMessage("Thank you. You may now use a dwarf cannon inside the Catacombs.");
					end();
					return;
				} else if (player.getMoneyPouch().getCoinAmount() >= 5000000) {
					player.coinamount -= 5000000;
					Settings.GpSyncAmount += 5000000;
					player.refreshMoneyPouch();
					player.getPackets().sendGameMessage(player.getMoneyPouch().getFormattedNumber(5000000) + " coins have been removed from your money pouch.");
					player.dailydwarfcatacombfee = true;
					player.sendMessage("Thank you. You may now use a dwarf cannon inside the Catacombs.");
					end();
					return;
				} else {
					player.sendMessage(Colors.red + "Seems like you don't have enough money to do this.");
					end();
				}
			} else {
				end();
			}
		}
	}

	@Override
	public void finish() {

	}

}
