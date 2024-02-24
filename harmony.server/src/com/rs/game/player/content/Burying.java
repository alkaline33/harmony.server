package com.rs.game.player.content;

import java.util.HashMap;
import java.util.Map;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class Burying {

	public enum Bone {
		BONES(526, 4),

		BURNT(528, 4),

		WOLF(2859, 4),

		MONKEY(3183, 4),

		BAT(530, 4),

		BIG_BONES(532, 15),

		JOGRE(3125, 15),

		ZOGRE(4812, 15),

		SHAIKAHAN(3123, 15),

		BABYDRAGON_BONES(534, 30),

		WYVERN_BONES(6812, 50),

		DRAGON_BONES(536, 72),

		FAYRG(4830, 72),

		RAURG(4832, 72),

		DAGANNOTH_BONES(6729, 125),

		OURG_BONES(4834, 125),

		FROST_DRAGON_BONES(18830, 180),
		
		DFROST_DRAGON_BONES(29576, 170),
		
		RUNE_DRAGON_BONES(29650, 190),
		
		SUPERIOR_DRAGON_BONES(29194, 150),

		ADDY_DRAGON_BONES(29652, 144);

		private int id;
		private double experience;

		private static Map<Integer, Bone> bones = new HashMap<Integer, Bone>();

		static {
			for (Bone bone : Bone.values()) {
				bones.put(bone.getId(), bone);
			}
		}

		public static Bone forId(int id) {
			return bones.get(id);
		}

		private Bone(int id, double experience) {
			this.id = id;
			this.experience = experience;
		}

		public int getId() {
			return id;
		}

		public double getExperience() {
			return experience;
		}
		
		public static final Animation BURY_ANIMATION = new Animation(827);

		public static void bury(final Player player, int inventorySlot) {
			final Item item = player.getInventory().getItem(inventorySlot);
			if (item == null || Bone.forId(item.getId()) == null)
				return;
			if (player.getBoneDelay() > Utils.currentTimeMillis())
				return;
			final Bone bone = Bone.forId(item.getId());
			final ItemDefinitions itemDef = new ItemDefinitions(item.getId());
			player.addBoneDelay(3000);
			player.lock();
			player.getPackets().sendSound(2738, 0, 1);
			player.setNextAnimation(BURY_ANIMATION);
				player.bury50bbones ++;	
			player.getPackets().sendGameMessage(
					"You dig a hole in the ground...");
			if (item.getId() == 532 && player.challengeid == 1 && player.challengeamount > 0) {
				DailyChallenges.UpdateChallenge(player);
			}
			if (item.getId() == 536 && player.challengeid == 2 && player.challengeamount > 0) {
				DailyChallenges.UpdateChallenge(player);
			}
			if (item.getId() == 29194 && player.challengeid == 3 && player.challengeamount > 0) {
				DailyChallenges.UpdateChallenge(player);
			}
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					player.getPackets().sendGameMessage(
							"You bury the " + itemDef.getName().toLowerCase());
					player.getInventory().deleteItem(item.getId(), 1);
					player.unlock();
					double xp = bone.getExperience() * player.getAuraManager().getPrayerMultiplier();
					player.getSkills().addXp(Skills.PRAYER, xp);
					Double lastPrayer = (Double) player.getTemporaryAttributtes().get("current_prayer_xp");
					if (lastPrayer == null) {
						lastPrayer = 0.0;
					}
					double total = xp + lastPrayer;
					int amount = (int) (total / 500);
					if (amount != 0) {
						double restore = player.getAuraManager().getPrayerRestoration() * (player.getSkills().getLevelForXp(Skills.PRAYER) * 10);
						player.getPrayer().restorePrayer((int) (amount * restore));
						total -= amount * 500;
					}
					player.getTemporaryAttributtes().put("current_prayer_xp", total);
					stop();
				}

			}, 2);
		}
	}
}
