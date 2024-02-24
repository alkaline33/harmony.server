package com.rs.game.player.actions.mining;

import java.util.HashMap;
import java.util.Map;

import com.rs.Settings;
import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.actions.Action;

@SuppressWarnings("unused")
public class RedSandStone extends Action {

	public final String MESSAGE = "You melt the Red Sandstone into Molten Glass.";

	public enum Sandstone {
		Sand(new Item(23194, 1));

		private static Map<Short, Sandstone> sand = new HashMap<Short, Sandstone>();

		public static Sandstone forId(short itemId) {
			return sand.get(itemId);
		}

		static {
			for (Sandstone sands : Sandstone.values()) {
				sand.put((short) sands.getSand().getId(), sands);
			}
		}

		private Item item;

		private Sandstone(Item item) {
			this.item = item;
		}

		public Item getSand() {
			return item;
		}
	}

	private Sandstone sand;
	private int amount;
	private Item item;
	private WorldObject object;
	private Animation USING = new Animation(896);

	public RedSandStone(WorldObject object, Item item, int amount) {
		this.amount = amount;
		this.item = item;
		this.object = object;
	}

	public static Sandstone isGood(Item item) {
		return Sandstone.forId((short) item.getId());
	}

	@Override
	public boolean start(Player player) {
		if ((sand = Sandstone.forId((short) item.getId())) == null) {
			return false;
		}
		player.faceObject(object);
		return true;
	}

	@Override
	public boolean process(Player player) {
		if (!World.getRegion(object.getRegionId()).containsObject(
				object.getId(), object)) {
			return false;
		}
		if (!player.getInventory().containsItem(item.getId(), 1)) {
			return false;
		}
		if (!player.getInventory().containsItem(sand.getSand().getId(), 1)) {
			return false;
		}
		return true;
	}

	@Override
	public int processWithDelay(Player player) {
		player.setNextAnimation(USING);
		player.getInventory().deleteItem(item.getId(), 1);
		if (Settings.eventdoubleskillingresources > 0) {
			player.getInventory().addItem(1775, 1);
		}
		player.getInventory().addItem(1775, 1);
		player.getPackets().sendGameMessage(MESSAGE);
		player.getInventory().refresh();
		return 1;
	}

	@Override
	public void stop(final Player player) {
		setActionDelay(player, 1);
	}

}