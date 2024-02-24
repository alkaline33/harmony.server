package com.rs.game.player.dialogues.impl;

import java.util.ArrayList;
import java.util.List;

import com.rs.game.player.GearPresets.Gear;
import com.rs.game.player.dialogues.Dialogue;

public class GearPresetsD extends Dialogue {

	private int[][] pages;
	private int currentPage;
	private int maxPagesNeeded;
	private int choosenGear;
	private List<Gear> availableGears;

	@Override
	public void start() {
		availableGears = player.getGearPresets().getGears();
		currentPage = 0;
		sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Save current gear/inventory", "Load/Manage gears", "Cancel");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			switch (componentId) {
			case OPTION_1:
				end();
				player.getTemporaryAttributtes().put("ADD_GEAR_NAME", Boolean.TRUE);
				player.getPackets().sendInputNameScript("Enter the Name of the gear: ");
				break;
			case OPTION_2:
				if (availableGears.isEmpty()) {
					player.getDialogueManager().startDialogue("SimpleMessage", "You don't have any gears to manage.");
					return;
				}
				stage = 0;
				currentPage = 0;
				sendOptionsDialogue("CHOOSE THE GEAR YOU WANT TO EDIT", getDialogueOptions());
				break;
			case OPTION_3:
				end();
				break;
			}
			break;
		case 0:
			int itemsCount = getGearsCount();
			switch (componentId) {
			case OPTION_1:
				if (currentPage == 0) {
					choosenGear = pages[currentPage][0];
					sendOptions();
				} else {// back
					currentPage--;
					sendOptionsDialogue("CHOOSE THE RESTRICTED ITEM YOU WANT TO EDIT", getDialogueOptions());
				}
				break;
			case OPTION_2:
				if (currentPage == 0) {
					if (itemsCount > 1)
						choosenGear = pages[currentPage][1];
				} else {
					if (itemsCount > 0)
						choosenGear = pages[currentPage][0];
				}
				if (choosenGear != -1)
					sendOptions();
				else
					end();
				break;
			case OPTION_3:
				if (currentPage == 0) {
					if (itemsCount > 2)
						choosenGear = pages[currentPage][2];
				} else {
					if (itemsCount > 1)
						choosenGear = pages[currentPage][1];
				}
				if (choosenGear != -1)
					sendOptions();
				else
					end();
				break;
			case OPTION_4:
				if (currentPage == 0) {
					if (itemsCount > 3)
						choosenGear = pages[currentPage][3];
				} else {
					if (itemsCount > 2)
						choosenGear = pages[currentPage][2];
				}
				if (choosenGear != -1)
					sendOptions();
				else
					end();
				break;
			case OPTION_5:
				if (currentPage < (maxPagesNeeded - 1) && getGearsCount((maxPagesNeeded - 1)) > 0) {
					currentPage++;
					sendOptionsDialogue("CHOOSE THE GEAR YOU WANT TO EDIT", getDialogueOptions());
				} else
					end();
				break;
			}
			break;
		case 1:
			switch (componentId) {
			case OPTION_1:
				end();
				player.getGearPresets().loadGear(choosenGear);
				player.getPackets().sendGameMessage("Finished loading gear " + getGearName(choosenGear) + ".");
				break;
			case OPTION_2:
				end();
				player.getPackets()
						.sendGameMessage(getGearName(choosenGear) + " have been removed from your saved gears.");

				player.getGearPresets().removeGear(choosenGear);
				break;
			case OPTION_3:
				stage = 0;
				sendOptionsDialogue("CHOOSE THE GEAR YOU WANT TO EDIT", getDialogueOptions());
				break;
			}
			break;
		}
	}

	@Override
	public void finish() {

	}

	public void sendOptions() {
		stage = 1;
		sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Load gear", "Remove gear", "Back to previous page.");
	}

	public String getGearName(int index) {
		return availableGears.get(index).getName();
	}

	public int getGearsCount() {
		int gearsCount = currentPage == 0 ? 1 : 0;
		for (int i = 0; i < (currentPage == 0 ? pages[currentPage].length : (pages[currentPage].length - 1)); i++) {
			if (pages[currentPage][i] != 0)
				gearsCount++;
		}
		return gearsCount;
	}

	public int getGearsCount(int page) {
		int gearsCount = page == 0 ? 1 : 0;
		for (int i = 0; i < (page == 0 ? pages[page].length : (pages[page].length - 1)); i++) {
			if (pages[page][i] != 0)
				gearsCount++;
		}
		return gearsCount;
	}

	private String[] getDialogueOptions() {
		maxPagesNeeded = ((int) Math.ceil((double) (availableGears.size() / (currentPage == 0 ? 4.00 : 3.00))));
		maxPagesNeeded = maxPagesNeeded == 0 ? 1 : maxPagesNeeded;
		choosenGear = -1;
		int index = 0;
		pages = new int[maxPagesNeeded][4];
		for (int i = 0; i < pages.length; i++) {
			for (int j = 0; j < (i > 0 ? (pages[i].length - 1) : pages[i].length); j++) {
				if (index > (availableGears.size() - 1))
					continue;
				pages[i][j] = index;
				index++;
			}
		}
		ArrayList<String> dialogueOptions = new ArrayList<String>(5);
		dialogueOptions.add(currentPage == 0 ? (getGearName(pages[currentPage][0])) : "Back");
		int gearsCount = getGearsCount();
		if (currentPage == 0)
			gearsCount--;
		for (int i = 0; i < gearsCount; i++) {
			dialogueOptions.add(getGearName(pages[currentPage][currentPage == 0 ? (i + 1) : i]));
		}

		if (currentPage < (maxPagesNeeded - 1) && getGearsCount((maxPagesNeeded - 1)) > 0)
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

}
