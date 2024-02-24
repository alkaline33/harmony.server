package com.rs.game.player.actions;

import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.FloorItem;
import com.rs.game.item.Item;
import com.rs.game.minigames.duel.DuelArena;
import com.rs.game.minigames.duel.DuelControler;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Firemaking.Fire;
import com.rs.game.player.content.DailyChallenges;
//import com.rs.game.player.content.Tasksmanager;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.net.decoders.handlers.InventoryOptionsHandler;
import com.rs.utils.Utils;

public class Firemaking extends Action {

	public static enum Fire {
		NORMAL(1511, 1, 300, 70755, 40, 20)
		, ACHEY(2862, 1, 300, 70756, 40, 1)
		, OAK(1521, 15, 450, 70757, 60, 1)
		, WILLOW(1519, 30, 450, 70758, 90, 1)
		, TEAK(6333, 35, 450, 70759, 105, 1)
		, ARCTIC_PINE(10810, 42, 500, 70760, 125, 1)
		, MAPLE(1517, 45, 500, 70761, 135.5, 1)
		, ACADIA(40285, 47, 500, 70761, 140, 1)
		, MAHOGANY(6332, 50,700, 70762, 157.5, 1)
		, EUCALYPTUS(12581, 58, 700, 70763, 193.5, 1)
		, YEW(1515, 60, 800, 70764, 202.5, 1)
		, MAGIC(1513, 75, 900, 70765, 308.8,1)
		, DMAGIC(29578, 75, 900, 70765, 298.8,1)
		, CURSED_MAGIC(13567, 82, 1000, 70766, 308.8, 1)
		, REDWOOD_LOG(29138, 90, 1100, 70764, 350, 1)
		, DREAM_LOG(9067, 90, 1100, 70767, 450, 1);

		private int logId;
		private int level;
		private int life;
		private int fireId;
		private int time;
		private double xp;
		long logsBurnt = 0;

		Fire(int logId, int level, int life, int fireId, double xp, int time) {
			this.logId = logId;
			this.level = level;
			this.life = life;
			this.fireId = fireId;
			this.xp = xp;
			this.time = time;
		}

		public int getLogId() {
			return logId;
		}

		public int getLevel() {
			return level;
		}

		public int getLife() {
			return life * 600;
		}

		public int getFireId() {
			return fireId;
		}

		public double getExperience() {
			return xp;
		}

		public int getTime() {
			return time;
		}
	}

	private Fire fire;

	public Firemaking(Fire fire) {
		this.fire = fire;
	}

	@Override
	public boolean start(Player player) {
		if (!checkAll(player)) {
			return false;
		}
		player.getPackets().sendGameMessage("You attempt to light the logs.",
				true);
		player.getInventory().deleteItem(fire.getLogId(), 1);
		World.addGroundItem(new Item(fire.getLogId(), 1),
				new WorldTile(player), player, false, 180, true);
		Long time = (Long) player.getTemporaryAttributtes().remove("Fire");
		boolean quickFire = time != null && time > Utils.currentTimeMillis();
		setActionDelay(player, quickFire ? 1 : 2);
		if (!quickFire) {
			player.setNextAnimation(new Animation(16700));
		}
		return true;
	}

	public static boolean isFiremaking(Player player, Item item1, Item item2) {
		Item log = InventoryOptionsHandler.contains(590, item1, item2);
		if (log == null) {
			return false;
		}
		return isFiremaking(player, log.getId());
	}

	public static boolean isFiremaking(Player player, int logId) {
		for (Fire fire : Fire.values()) {
			if (fire.getLogId() == logId) {
				player.getActionManager().setAction(new Firemaking(fire));
				return true;
			}
		}
		return false;

	}

	public static void startFamiliarFire(Player player, Familiar familiar,
			Fire fire) {
		if (player.getFamiliar().getId() == 7378
				|| player.getFamiliar().getId() == 7377) {
		}
	}

	public boolean checkAll(Player player) {
		if (!player.getInventory().containsItemToolBelt(590, 1)) {
			player.getPackets().sendGameMessage(
					"You do not have the required items to light this.");
			return false;
		}
		if (player.getSkills().getLevel(Skills.FIREMAKING) < fire.getLevel()) {
			player.getPackets().sendGameMessage(
					"You do not have the required level to light this.");
			return false;
		}
		if (!World.canMoveNPC(player.getPlane(), player.getX(), player.getY(),
				1) // cliped
				|| World.getRegion(player.getRegionId()).getSpawnedObject(
						player) != null
						|| player.getControlerManager().getControler() instanceof DuelArena || player.getControlerManager().getControler() instanceof DuelControler) { // contains
			// object
			player.getPackets().sendGameMessage("You can't light a fire here.");
			return false;
		}
		return true;
	}

	@Override
	public boolean process(Player player) {
		return checkAll(player);
	}

	public static double increasedExperience(Player player, double totalXp) {
		if (player.getEquipment().getGlovesId() == 13660) {
			totalXp *= 1.025;
		}
		if (player.getEquipment().getRingId() == 13659) {
			totalXp *= 1.025;
		}
		return totalXp;
	}

	@Override
	public int processWithDelay(final Player player) {
		player.coldhands = 0;
		final WorldTile tile = new WorldTile(player);
		if (!player.addWalkSteps(player.getX() - 1, player.getY(), 1)) {
			if (!player.addWalkSteps(player.getX() + 1, player.getY(), 1)) {
				if (!player.addWalkSteps(player.getX(), player.getY() + 1, 1)) {
					player.addWalkSteps(player.getX(), player.getY() - 1, 1);
				}
			}
		}
		player.getPackets().sendGameMessage(
				"The fire catches and the logs begin to burn.", true);
		player.burn100logs++;
		if (fire == Fire.MAGIC) {
		player.logsburnt++;
		}
		if (player.challengeid == 46 && player.challengeamount > 0 && fire == Fire.OAK) {
			DailyChallenges.UpdateChallenge(player);
		}
		if (player.challengeid == 47 && player.challengeamount > 0 && fire == Fire.MAPLE) {
			DailyChallenges.UpdateChallenge(player);
		}
		if (player.challengeid == 48 && player.challengeamount > 0 && fire == Fire.MAGIC) {
			DailyChallenges.UpdateChallenge(player);
		}
		if(Utils.random(99999) == 0) {
			if (player.getPet() == null && player.firemakingpet != true) {
				player.getPetManager().spawnPet(29521, false);
				player.firemakingpet = true;
				player.sendMessage("You have a funny feeling something is following you.");
				World.sendWorldMessage("<img=7><col=339966>News: "+player.getDisplayName()+" has just found a Firemaking pet! ("+ Utils.getFormattedNumber((int) player.getSkills().getXp(Skills.FIREMAKING))+"XP)", false);
			} else if (player.getPet() != null && player.firemakingpet != true) {
				player.firemakingpet = true;
				player.sendMessage("Speak to the pet tamer at home to claim your pet.");
				World.sendWorldMessage("<img=7><col=339966>News: "+player.getDisplayName()+" has just found a Firemaking pet! ("+ Utils.getFormattedNumber((int) player.getSkills().getXp(Skills.FIREMAKING))+"XP)", false);
			} else {
				player.sendMessage("You feel like something was following you.");
			}
		}

		//Tasksmanager.Addcount(1, player, "Firemaking");
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				final FloorItem item = World.getRegion(tile.getRegionId())
						.getGroundItem(fire.getLogId(), tile, player);
				if (item == null) {
					return;
				}
				if (!World.removeGroundItem(player, item, false)) {
					return;
				}
				//player.getPackets().sendSound(2594, 0, 1); //TODO find fire sound
				World.spawnTempGroundObject(new WorldObject(fire.getFireId(),
						10, 0, tile.getX(), tile.getY(), tile.getPlane()), 592,
						fire.getLife());
				player.getSkills().addXp(Skills.FIREMAKING, increasedExperience(player, fire.getExperience()));
				player.setNextFaceWorldTile(tile);
			}
		}, 1);
		player.getTemporaryAttributtes().put("Fire",
				Utils.currentTimeMillis() + 1800);
		return -1;
	}

	@Override
	public void stop(final Player player) {
		setActionDelay(player, 3);
	}

}
