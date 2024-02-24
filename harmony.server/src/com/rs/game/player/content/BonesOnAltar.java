package com.rs.game.player.content;

import java.util.HashMap;
import java.util.Map;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;
import com.rs.utils.Utils;

@SuppressWarnings("unused")
public class BonesOnAltar extends Action {
	
	public final String MESSAGE = "The gods are very pleased with your offerings.";
	public final double MULTIPLIER = 2.5;
	
	public enum Bones {
		BONES(new Item(526, 1), 15),
		BIG_BONES(new Item(532, 1), 52 ),
		BAT_BONES(new Item(530, 1), 52),
		BABYDRAGON_BONES(new Item(534, 1), 105),
		WYVERN_BONES(new Item(6812, 1), 252),
		INFERNAL_ASHES(new Item(20268), 218),
		DRAGON_BONES(new Item(536, 1), 252),
		OURG_BONES(new Item(4834, 1), 490),
		FROST_DRAGON_BONES(new Item(18830, 1), 630),
		DFROST_DRAGON_BONES(new Item(29576, 1), 600),
		RUNE_DRAGON_BONES(new Item(29650, 1), 665),
		SUPERIOR_DRAGON_BONES(new Item(29194, 1), 600),
		ADDY_DRAGON_BONES(new Item(29652, 1), 504),
		DAGANNOTH_BONES(new Item(6729, 1), 438);
	
		private static Map<Short, Bones> bones = new HashMap<Short, Bones>();
	
		public static Bones forId(short itemId) {
			return bones.get(itemId);
		}
	
		static {
			for (Bones bone: Bones.values()) {
				bones.put((short) bone.getBone().getId(), bone);
			}
		}
	
		private Item item;
		private int xp;
	
		private Bones(Item item, int xp) {
			this.item = item;
			this.xp = xp;
		}
		
		public Item getBone() {
			return item;
		}
		
		public int getXP() {
			return xp;
		}
	}
		
	private Bones bone;
	private int amount;
	private Item item;
	private WorldObject object;
	private Animation USING = new Animation(896);
	
	public BonesOnAltar(WorldObject object, Item item, int amount) {
		this.amount = amount;
		this.item = item;
		this.object = object;
	}
	
	public static Bones isGood(Item item) {
		return Bones.forId((short) item.getId());
	}
	
	@Override
	public boolean start(Player player) {
		if((bone = Bones.forId((short) item.getId())) == null) {
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
		if (!player.getInventory().containsItem(bone.getBone().getId(), 1)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int processWithDelay(Player player) {
			player.setNextAnimation(USING);
			player.getPackets().sendGraphics(new Graphics(624), object);
		if (Utils.random(1, 10) == 1 && player.getEquipment().getCapeId() == 29982) {
			player.sendMessage("Your master prayer cape perk saved a bone.");
		} else if (Utils.random(1, 10) == 1 && player.getEquipment().getCapeId() == 20771 && player.getSkills().getXp(Skills.PRAYER) >= 104273167) {
				player.sendMessage("Your master prayer cape perk saved a bone.");
		} else {
			player.getInventory().deleteItem(item.getId(), 1);
		}
			player.getSkills().addXp(Skills.PRAYER, bone.getXP()*MULTIPLIER);
			player.getPackets().sendGameMessage(MESSAGE);
			player.getInventory().refresh();
			return 3;
	}

	@Override
	public void stop(final Player player) {
		setActionDelay(player, 3);
	}
	
}