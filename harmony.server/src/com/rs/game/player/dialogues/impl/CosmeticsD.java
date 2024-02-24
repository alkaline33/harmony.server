package com.rs.game.player.dialogues.impl;

import java.util.ArrayList;
import java.util.List;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.player.Equipment;
import com.rs.game.player.content.CosmeticsHandler;
import com.rs.game.player.dialogues.Dialogue;

public class CosmeticsD extends Dialogue {

	private int slotId;
	private List<Integer> availableCostumes;
	int maxPagesNeeded;
	private int[][] pages;
	private int currentPage;

	@Override
	public void start() {
		slotId = (int) this.parameters[0];
		availableCostumes = new ArrayList<Integer>();
		int[] costumes = slotId == Equipment.SLOT_HAT ? CosmeticsHandler.HATS
				: slotId == Equipment.SLOT_CAPE ? CosmeticsHandler.CAPES
						: slotId == Equipment.SLOT_AMULET ? CosmeticsHandler.AMULETS
								: slotId == Equipment.SLOT_CHEST ? CosmeticsHandler.CHESTS
										: slotId == Equipment.SLOT_LEGS ? CosmeticsHandler.LEGS
												: slotId == Equipment.SLOT_HANDS ? CosmeticsHandler.GLOVES
														: slotId == Equipment.SLOT_WEAPON ? CosmeticsHandler.WEAPONS
																: slotId == Equipment.SLOT_SHIELD
																		? CosmeticsHandler.SHIELDS
																		: CosmeticsHandler.BOOTS;
		for (int costumeId : costumes) {
			if (costumeId == -1 || CosmeticsHandler.isRestrictedItem(player, costumeId))
				continue;
			availableCostumes.add(costumeId);

		}
		maxPagesNeeded = ((int) Math.ceil((double) (availableCostumes.size() / 3.00)));
		maxPagesNeeded = maxPagesNeeded == 0 ? 1 : maxPagesNeeded;
		int index = 0;
		pages = new int[maxPagesNeeded][3];
		for (int i = 0; i < pages.length; i++) {
			for (int j = 0; j < pages[i].length; j++) {
				if (index > (availableCostumes.size() - 1))
					continue;
				pages[i][j] = availableCostumes.get(index);
				index++;
			}
		}
		currentPage = 0;
		if (player.getEquipment().getCosmeticItems().get(slotId) != null) {
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE,
					(player.getEquipment().getHiddenSlots()[slotId] ? "Unhide" : "Hide"), "Remove current costume",
					"Nevermind");
		} else
			sendOptionsDialogue("CHOOSE THE COSTUME YOU WANT", getDialogueOptions());
	}

	private String[] getDialogueOptions() {
		ArrayList<String> dialogueOptions = new ArrayList<String>(5);

		dialogueOptions
				.add(currentPage == 0 ? (player.getEquipment().getHiddenSlots()[slotId] ? "Unhide" : "Hide") : "Back");
		int itemsCount = 0;
		for (int i = 0; i < pages[currentPage].length; i++) {
			Item item = new Item(pages[currentPage][i]);
			if (item.getId() != 0)
				itemsCount++;
		}
		for (int i = 0; i < itemsCount; i++) {
			Item item = new Item(pages[currentPage][i]);
			if (item.getId() != 0)
				
				  if (CosmeticsHandler.isRestrictedItem(player, item.getId()))
				  continue;
				 
				dialogueOptions.add(CosmeticsHandler.isRestrictedItem(player, item.getId())
						? "<col=ff0000>" + CosmeticsHandler.getNameOnDialogue(item.getId())
						: "" + item.getId() + "<col=00ff00>" + CosmeticsHandler.getNameOnDialogue(item.getId()));
		}
		if (currentPage < (maxPagesNeeded - 1))
			dialogueOptions.add("More");
		else
			dialogueOptions.add("Cancel");

		String[] options = new String[dialogueOptions.size()];
		for (int i = 0; i < options.length; i++) {
			String option = dialogueOptions.get(i);
			if (option == null)
				continue;
			options[i] = option;
		}
		return options;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (player.getEquipment().getCosmeticItems().get(slotId) != null) {
			if (componentId == OPTION_1)
				player.getEquipment().getHiddenSlots()[slotId] = !player.getEquipment().getHiddenSlots()[slotId];
			else if (componentId == OPTION_2)
				player.getEquipment().getCosmeticItems().set(slotId, null);
			player.getAppearence().generateAppearenceData();
			end();
			return;
		}
		int itemsCount = 0;
		for (int i = 0; i < pages[currentPage].length; i++) {
			Item item = new Item(pages[currentPage][i]);
			if (item.getId() != 0)
				itemsCount++;
		}
		switch (componentId) {
		case OPTION_1:
			if (currentPage == 0) { //hide
				player.getEquipment().getHiddenSlots()[slotId] = !player.getEquipment().getHiddenSlots()[slotId];
				player.getAppearence().generateAppearenceData();
				end();
			} else {// back
				currentPage--;
				sendOptionsDialogue("CHOOSE THE COSTUME YOU WANT", getDialogueOptions());
			}
			break;
		case OPTION_2:
			if (itemsCount > 0) {
				Item item = new Item(pages[currentPage][0]);
				setCostume(item);
			}
			end();
			break;
		case OPTION_3:
			if (itemsCount > 1) {
				Item item = new Item(pages[currentPage][1]);
				setCostume(item);
			}
			end();
			break;
		case OPTION_4:
			if (itemsCount > 2) {
				Item item = new Item(pages[currentPage][2]);
				setCostume(item);
			}
			end();
			break;
		case OPTION_5:
			if (itemsCount > 2) {
				if (currentPage < (maxPagesNeeded - 1)) {
					currentPage++;
					sendOptionsDialogue("CHOOSE THE COSTUME YOU WANT", getDialogueOptions());
				} else
					end();
			} else
				end();
			break;
		}
	}

	public int getKeepsake() {
		return player.keepsakecape;
	}

	public void setCostume(Item item) {
	/*	if (CosmeticsHandler.isRestrictedItem(player, item.getId())) {this wont be needed thats all test
			player.getPackets().sendGameMessage("You have not unlocked this cosmetic!");
			return;
		}*/
		/**
		 * KeepSake - Author - Mr_Joopz
		 */
		if (item.getId() == 29544 && slotId == Equipment.SLOT_CAPE) {
			if (player.keepsakecape <= 0) {
				player.getPackets().sendGameMessage("Your keepsake is currently empty!");
				return;
			}
			if (ItemDefinitions.getItemDefinitions(player.keepsakecape).getEquipSlot() != slotId) {
				player.getPackets().sendGameMessage("You must load your keepsake from the correct slot1.");

				return;
			}
			item.setId(player.keepsakecape);
		}
		if (item.getId() == 29544 && slotId == Equipment.SLOT_HAT) {
			if (player.keepsakehat <= 0) {
				player.getPackets().sendGameMessage("Your keepsake is currently empty!");
				return;
			}
			if (ItemDefinitions.getItemDefinitions(player.keepsakehat).getEquipSlot() != slotId) {
				player.getPackets().sendGameMessage("You must load your keepsake from the correct slot2.");

				return;
			}
			item.setId(player.keepsakehat);
		}
		if (item.getId() == 29544 && slotId == Equipment.SLOT_AMULET) {
			if (player.keepsakeamulet <= 0) {
				player.getPackets().sendGameMessage("Your keepsake is currently empty!");
				return;
			}
			if (ItemDefinitions.getItemDefinitions(player.keepsakeamulet).getEquipSlot() != slotId) {
				player.getPackets().sendGameMessage("You must load your keepsake from the correct slot.");
				return;
			}
			item.setId(player.keepsakeamulet);
		}
		if (item.getId() == 29544 && slotId == Equipment.SLOT_CHEST) {
			if (player.keepsakebody <= 0) {
				player.getPackets().sendGameMessage("Your keepsake is currently empty!");
				return;
			}
			if (ItemDefinitions.getItemDefinitions(player.keepsakebody).getEquipSlot() != slotId) {
				player.getPackets().sendGameMessage("You must load your keepsake from the correct slot.");
				return;
			}
			item.setId(player.keepsakebody);
		}
		if (item.getId() == 29544 && slotId == Equipment.SLOT_FEET) {
			if (player.keepsakeboots <= 0) {
				player.getPackets().sendGameMessage("Your keepsake is currently empty!");
				return;
			}
			if (ItemDefinitions.getItemDefinitions(player.keepsakeboots).getEquipSlot() != slotId) {
				player.getPackets().sendGameMessage("You must load your keepsake from the correct slot.");
				return;
			}
			item.setId(player.keepsakeboots);
		}
		if (item.getId() == 29544 && slotId == Equipment.SLOT_HANDS) {
			if (player.keepsakegloves <= 0) {
				player.getPackets().sendGameMessage("Your keepsake is currently empty!");
				return;
			}
			if (ItemDefinitions.getItemDefinitions(player.keepsakegloves).getEquipSlot() != slotId) {
				player.getPackets().sendGameMessage("You must load your keepsake from the correct slot.");
				return;
			}
			item.setId(player.keepsakegloves);
		}
		if (item.getId() == 29544 && slotId == Equipment.SLOT_LEGS) {
			if (player.keepsakelegs <= 0) {
				player.getPackets().sendGameMessage("Your keepsake is currently empty!");
				return;
			}
			if (ItemDefinitions.getItemDefinitions(player.keepsakelegs).getEquipSlot() != slotId) {
				player.getPackets().sendGameMessage("You must load your keepsake from the correct slot.");
				return;
			}
			item.setId(player.keepsakelegs);
		}
		if (item.getId() == 29544 && slotId == Equipment.SLOT_SHIELD) {
			if (player.keepsakeshield <= 0) {
				player.getPackets().sendGameMessage("Your keepsake is currently empty!");
				return;
			}
			if (ItemDefinitions.getItemDefinitions(player.keepsakeshield).getEquipSlot() != slotId) {
				player.getPackets().sendGameMessage("You must load your keepsake from the correct slot.");
				return;
			}
			item.setId(player.keepsakeshield);
		}
		if (item.getId() == 29544 && slotId == Equipment.SLOT_WEAPON) {
			if (player.keepsakeweapon <= 0) {
				player.getPackets().sendGameMessage("Your keepsake is currently empty!");
				return;
			}
			if (ItemDefinitions.getItemDefinitions(player.keepsakeweapon).getEquipSlot() != slotId) {
				player.getPackets().sendGameMessage("You must load your keepsake from the correct slot.");
				return;
			}
			item.setId(player.keepsakeweapon);
		}
		if (slotId == Equipment.SLOT_WEAPON) {
			boolean twoHanded = Equipment.isTwoHandedWeapon(item);
			boolean hasShield = player.getEquipment().getCosmeticItems().get(Equipment.SLOT_SHIELD) != null
					|| player.getEquipment().getShieldId() != -1;
			if (twoHanded && hasShield) {
				player.getPackets().sendGameMessage("You cannot put on a two handed weapon whilst having a shield.");
				return;
			}
		}
		player.getEquipment().getCosmeticItems().set(slotId, item);
		player.getAppearence().generateAppearenceData();
	}

	@Override
	public void finish() {
	}

}