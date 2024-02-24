package com.rs.game.npc.familiar;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Summoning.Pouches;

public class Packyak extends Familiar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1397015887332756680L;

	public Packyak(Player owner, Pouches pouch, WorldTile tile,
			int mapAreaNameHash, boolean canBeAttackFromOutOfArea) {
		super(owner, pouch, tile, mapAreaNameHash, false);
	}

	@Override
	public int getSpecialAmount() {
		return 12;
	}

	@Override
	public String getSpecialName() {
		return "Winter Storage";
	}

	@Override
	public String getSpecialDescription() {
		return "Use special move on an item in your inventory to send it to your bank.";
	}

	@Override
	public SpecialAttack getSpecialAttack() {
		return SpecialAttack.ITEM;
	}

	@Override
	public int getBOBSize() {
		return 30;
	}

	@Override
	public boolean isAgressive() {
		return false;
	}

	@Override
	public boolean submitSpecial(Object object) {
		int slotId = (Integer) object;
		if (!getOwner().getInventory().containsOneItem(pouch.getScrollId())) {
			getOwner()
					.sendMessage(
							"You do not have any Winter storage scrolls in your inventory.");
			return false;
		}
		if (getOwner().hasEnteredPin == false && getOwner().hasBankPin == true) {
			getOwner()
			.sendMessage(
					"Please enter your bank pin before doing this.");
			return false;
		}
		if (specialEnergy < getSpecialAmount()) {
			getOwner()
					.getPackets()
					.sendGameMessage(
							"You familiar doesn't have enough special energy to do that.");
			return false;
		}
		if (getOwner().getSkills().getLevel(Skills.SUMMONING) < 0) {
			getOwner()
					.sendMessage(
							"You do not have enough summoning points to use this ability.");
			return false;
		}
		if (getOwner().getBank().hasBankSpace()) {
			getOwner().stopAll(true);
			getOwner().getBank().depositItem(slotId, 1, true);
			getOwner().getInventory().deleteItem(pouch.getScrollId(), 1);
			getOwner().getPackets().sendGameMessage(
					"The pack yak has sent an item into your bank.");
			getOwner().getFamiliar().drainSpecial(getSpecialAmount());
			getOwner().setNextGraphics(new Graphics(1316));
			getOwner().setNextAnimation(new Animation(7660));
		} else {
			getOwner()
					.sendMessage(
							"You do not have enough bank space to send that to your bank.");
		}
		return true;
	}
}
