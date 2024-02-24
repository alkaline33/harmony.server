package com.rs.game.player.dialogues.impl;

import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.actions.Action;
import com.rs.game.player.content.Pots;
import com.rs.game.player.content.Pots.Pot;
import com.rs.game.player.content.SkillsDialogue;
import com.rs.game.player.dialogues.Dialogue;

public class FlaskDecantingD extends Dialogue {

	private Pot usedPot, pot;

	//TODO flasks decanting is wrong. this skill dialogue never existed....
	
	@Override
	public void start() {
		//World.sendWorldMessage("2", false);
		usedPot = (Pot) this.parameters[0];
	//	World.sendWorldMessage("3", false);
		//run(stage, stage);
		//start();
		for (Pot pot : Pot.values()) {
			if (pot.ordinal() == usedPot.ordinal() || !pot.name().replace("_FLASK", "").equals(usedPot.name().replace("_POTION", "")))
				continue;
			this.pot = pot;
			SkillsDialogue.sendSkillsDialogue(player, SkillsDialogue.MAKE, "Choose how many you wish to make,<br>then click on the item to begin.", 40, new int[]
			{ pot.getIdForDoses(pot.getMaxDoses()) }, null);
			return;
		}
		end();
	}

	@Override
	public void run(int interfaceId, int componentId) {
		end();
		player.getActionManager().setAction(new Action() {

			private int potionSlot, flaskSlot, ticks;

			@Override
			public boolean start(Player player) {
				//World.sendWorldMessage("4", false);
				ticks = 1;//SkillsDialogue.getQuantity(player);
				potionSlot = calculatePotionSlot(player);
				calculateFlaskSlot(player);
				if (!checkAll(player)) {
					return false;
				}
				return true;
			}

			private boolean checkAll(Player player) {
				int maxDoses = usedPot.getMaxDoses();
				if (maxDoses > 4) {
					System.out.println("Error in pot: " + usedPot.getIdForDoses(maxDoses));
					return false;
				} else if (flaskSlot == -1) {
					return false;
				} else if (potionSlot == -1) {
					return false;
				} else if (player.getInventory().getItem(flaskSlot).getId() == pot.getIdForDoses(pot.getMaxDoses())) {
					calculateFlaskSlot(player);
					if (flaskSlot == -1)
						return false;
				} else if (player.getInventory().getItem(potionSlot).getId() == Pots.VIAL) {
					potionSlot = calculatePotionSlot(player);
					if (potionSlot == -1)
						return false;
				}
				return true;
			}

			@Override
			public boolean process(Player player) {
				return checkAll(player) && ticks > 0;
			}

			@Override
			public int processWithDelay(Player player) {
				ticks--;
				Item jar = player.getInventory().getItem(flaskSlot);
				Item potion = player.getInventory().getItem(potionSlot);
				if (jar == null || potion == null)
					return -1;
				int requiredDoses = pot.getMaxDoses() - Pot.getDoses(pot, jar);
				int potionDoses = Pot.getDoses(usedPot, potion);
				int reducedDoses = ((requiredDoses - potionDoses) * -1);
				player.getInventory().getItems().set(potionSlot, new Item(requiredDoses >= potionDoses ? Pots.VIAL : usedPot.getIdForDoses(reducedDoses), 1));
				if (potionDoses < 4) {
					requiredDoses = potionDoses;
				}
				int totalDoses = requiredDoses + Pot.getDoses(pot, jar);
				player.getInventory().getItems().set(flaskSlot, new Item(pot.getIdForDoses(totalDoses > 6 ? 6 : totalDoses)));
				player.getInventory().refresh(potionSlot, flaskSlot);
				return 1;
			}

			@Override
			public void stop(Player player) {
				setActionDelay(player, 3);
			}

			private int calculatePotionSlot(Player player) {
				for (int ids : usedPot.getId1()) {
					int slot = player.getInventory().getItems().getThisItemSlot1(ids);
					if (slot != -1)
						return slot;
				}
				return -1;
			}

			private void calculateFlaskSlot(Player player) {
				flaskSlot = player.getInventory().getItems().getThisItemSlot1(23191);
			}
		});
	}

	@Override
	public void finish() {

	}
}
