package com.rs.game.player.dialogues.impl;

import com.rs.game.item.Item;
import com.rs.game.player.FarmingManager.FarmingSpot;
import com.rs.game.player.FarmingManager.Gardeners;
import com.rs.game.player.FarmingManager.SpotInfo;
import com.rs.game.player.dialogues.Dialogue;

public class GardenersD extends Dialogue {

	private int npcId;
	private int STAGE;
	private Gardeners gardeners;
	private FarmingSpot spot;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		STAGE = (Integer) parameters[1];
		gardeners = Gardeners.getGardeners(npcId);
		SpotInfo info = SpotInfo.getInfo(STAGE == 2 ? gardeners.getSouthObject() : gardeners.getObjectId());
		spot = player.getFarmingManager().getSpot(info);
		if (STAGE != -1) {
			if (spot == null || spot.productInfo == null)
				player.getDialogueManager().startDialogue("SimpleNPCMessage", npcId,
						"You don't have anything planted in that patch. Plant something and i might agree to look after it for you.");
			else if (spot.reachedMaxStage())
				player.getDialogueManager().startDialogue("SimpleNPCMessage", npcId,
						"I don't know what you're talking about - Your crops are already fully grown.");
			else if (spot.isProtected()) {
				player.getDialogueManager().startDialogue("SimpleNPCMessage", npcId,
						"I don't know what you're talking about - I'm already looking after that patch for you.");
			} else {
				Item protectionItem = spot.productInfo.getProtectionItem();
				String pItemName = protectionItem.getName();
				int pItemAmount = protectionItem.getAmount();
				stage = 2;
				sendNPCDialogue(npcId, 9827, "If you like, but i want " + pItemAmount + " " + pItemName + " for that");
			}
		} else
			sendOptionsDialogue("CHOOSE AN OPTION", "Would you look after crops for me?",
					"Can you " + (player.getFarmingManager().isAlwaysWeeded ? "stop weeding my patches for me please?"
							: "keep my farming patches weeded please?"),
					"Can you sell me something?", "Thats all, thanks.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (componentId == OPTION_1) {
				if (gardeners.getType() == 0) {// allotment
					stage = 1;
					sendOptionsDialogue("SELECT AN OPTION", "The north-western allotment",
							"The south-eastern allotment");
				} else {
					if (spot == null || spot.productInfo == null)
						player.getDialogueManager().startDialogue("SimpleNPCMessage", npcId,
								"You don't have anything planted in that patch. Plant something and i might agree to look after it for you.");
					else if (spot.reachedMaxStage())
						player.getDialogueManager().startDialogue("SimpleNPCMessage", npcId,
								"I don't know what you're talking about - Your crops are already fully grown.");
					else if (spot.isProtected()) {
						player.getDialogueManager().startDialogue("SimpleNPCMessage", npcId,
								"I don't know what you're talking about - I'm already looking after that patch for you.");
					} else if (gardeners.getObjectId() == 18816) {
						spot.setIsProtected(true);
						player.getDialogueManager().startDialogue("SimpleNPCMessage", npcId,
								"That'll do nicely, Sir. Leave it with me - i'll make sure it grows for you.");
					} else {
						Item protectionItem = spot.productInfo.getProtectionItem();
						String pItemName = protectionItem.getName();
						int pItemAmount = protectionItem.getAmount();
						stage = 2;
						this.sendNPCDialogue(npcId, 9827,
								"If you like, but i want " + pItemAmount + " " + pItemName + " for that");
					}
				}
			} else if (componentId == OPTION_2) {
				player.getFarmingManager().isAlwaysWeeded = !player.getFarmingManager().isAlwaysWeeded;
				player.getDialogueManager().startDialogue("SimpleNPCMessage", npcId,
						(player.getFarmingManager().isAlwaysWeeded ? "Sure, i will keep crops weeded for you."
								: "Sure, i will stop weeding crops for you."));
			} else if (componentId == OPTION_3) {
				end();
			} else
				end();
		} else if (stage == 1) {
			SpotInfo info = SpotInfo
					.getInfo((componentId == OPTION_1) ? gardeners.getObjectId() : gardeners.getSouthObject());
			spot = player.getFarmingManager().getSpot(info);
			if (spot == null || spot.productInfo == null)
				player.getDialogueManager().startDialogue("SimpleNPCMessage", npcId,
						"You don't have anything planted in that patch. Plant something and i might agree to look after it for you.");
			else if (spot.reachedMaxStage())
				player.getDialogueManager().startDialogue("SimpleNPCMessage", npcId,
						"I don't know what you're talking about - Your crops are already fully grown.");
			else if (spot.isProtected()) {
				player.getDialogueManager().startDialogue("SimpleNPCMessage", npcId,
						"I don't know what you're talking about - I'm already looking after that patch for you.");
			} else {
				Item protectionItem = spot.productInfo.getProtectionItem();
				String pItemName = protectionItem.getName();
				int pItemAmount = protectionItem.getAmount();
				stage = 2;
				this.sendNPCDialogue(npcId, 9827,
						"If you like, but i want " + pItemAmount + " " + pItemName + " for that");
			}
		} else if (stage == 2) {
			stage = 3;
			sendOptionsDialogue("CHOOSE AN OPTION", "Yes, please look after them.", "No, thats too much.");
		} else if (stage == 3) {
			if (componentId == OPTION_1) {
				stage = 4;
				sendPlayerDialogue(9827, "Yes, i would like you to look after them please.");
			} else {
				player.getDialogueManager().startDialogue("SimplePlayerMessage",
						"No thanks, that will be too much for me.");
			}
		} else if (stage == 4) {
			Item protectionItem = spot.productInfo.getProtectionItem();
			int pItemAmount = protectionItem.getAmount();
			int pItemAmountInv = player.getInventory().getNumberOf(protectionItem.getId())
					+ player.getInventory().getNumberOf(protectionItem.getId() + 1);// noted
																					// +
																					// unnoted
																					// amounts
			if (pItemAmountInv >= pItemAmount) {// amount >= required
				int itemsRemoved = 0;
				while (player.getInventory().containsItem(protectionItem.getId(), 1) && itemsRemoved < pItemAmount)// while
																													// inventory
																													// contains
																													// unnoted
																													// items
																													// and
																													// we
																													// still
																													// got
																													// items
																													// to
																													// be
																													// removed
				{
					player.getInventory().deleteItem(protectionItem.getId(), 1);
					itemsRemoved++;
				}
				if (itemsRemoved < pItemAmount)// we still got items to be
												// removed
					player.getInventory().deleteItem(protectionItem.getId() + 1, pItemAmount - itemsRemoved);
				spot.setIsProtected(true);
				player.getDialogueManager().startDialogue("SimpleNPCMessage", npcId,
						"That'll do nicely, Sir. Leave it with me - i'll make sure it grows for you.");
			} else {
				player.getDialogueManager().startDialogue("SimpleNPCMessage", npcId,
						"You don't have the required items.");
			}
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}