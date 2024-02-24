package com.rs.game.player.controlers.events.eviltree;

import  com.rs.game.Animation;
import  com.rs.game.World;
import  com.rs.game.WorldObject;
import com.rs.game.player.Player;
import  com.rs.game.player.actions.Action;
import  com.rs.utils.Utils;

public final class EvilTreeEvent extends Action {

	public static enum EvilTreeDefinitions {

		NORMAL_EVIL_TREE("Evil tree", 11435, 1, 1, 1, 15.1, 20.0, 200.0, 2300),
		EVIL_OAK_TREE("Oak Evil Tree", 11437, 15, 7, 15, 32.4, 45.0, 300.0, 2500),
		EVIL_WILLOW_TREE("Willow Evil Tree", 11441, 30, 15, 30, 45.7, 66.0, 450.0, 2700),
		EVIL_MAPLE_TREE("Maple Evil Tree", 11444, 45, 22, 45, 55.8, 121.5, 675.0, 2900),
		EVIL_YEW_TREE("Yew Evil Tree", 11916, 60, 30, 60, 64.4, 172.5, 1012.5, 3100),
		EVIL_MAGIC_TREE("Magic Evil Tree", 11919, 75, 37, 75, 70.9, 311.0, 1517.5, 3300),
		EVIL_ELDER_TREE("Elder Evil Tree", 11922, 90, 42, 90, 77.8, 687.5, 2000.5, 3500);
		
		private String treeName;
		private int id;
		private int woodcuttingLevel;
		private int farmingLevel;
		private int firemakingLevel;
		private double woodcuttingXp;
		private double farmingXp;
		private double firemakingXp;
		private int treeHealth;
		
		private EvilTreeDefinitions(String treeName, int id, int woodcuttingLevel, int farmingLevel, int firemakingLevel, double woodcuttingXp,
				double farmingXp, double firemakingXp, int treeHealth) {
			this.treeName = treeName;
			this.id = id;
			this.woodcuttingLevel = woodcuttingLevel;
			this.farmingLevel = farmingLevel;
			this.firemakingLevel = firemakingLevel;
			this.woodcuttingXp = woodcuttingXp;
			this.farmingXp = farmingXp;
			this.firemakingXp = firemakingXp;
			this.treeHealth = treeHealth;
		}

		public String getTreeName(){
			return treeName;
		}
		public int getId() {
			return id;
		}
		
		public int getWoodcuttingLevel() {
			return woodcuttingLevel;
		}
		
		public int getFarmingLevel() {
			return farmingLevel;
		}
		
		public int getFiremakingLevel() {
			return firemakingLevel;
		}
		
		public double getWoodcuttingXp() {
			return woodcuttingXp;
		}
		
		public double getFarmingXp() {
			return farmingXp;
		}
		
		public double getFiremakingXp() {
			return firemakingXp;
		}
		
		public int getTreeHealth() {
			return treeHealth;
		}
		
		public void setTreeHealth (int damage) {
			treeHealth = damage;
		}
	}
	
	private WorldObject tree;
	private EvilTreeDefinitions definitions;
	
	private boolean spawned;
	
	private int emoteId;
	private int axeTime;
	
	public EvilTreeEvent(WorldObject tree, EvilTreeDefinitions definitions) {
		this.tree = tree;
		this.definitions = definitions;
	}

	@Override
	public boolean start(Player player) {
		if (!checkAll(player)) {
			return false;
		}
		player.getPackets().sendGameMessage("You swing your hatchet at the " +definitions.getTreeName()+ ".");
		setActionDelay(player, 10);
		return true;
	}

	private boolean checkAll(Player player) {
		if (!hasAxe(player)) {
			player.getPackets().sendGameMessage(
					"You need a hatchet to chop down this tree.");
			return false;
		}
		if (!setAxe(player)) {
			player.getPackets().sendGameMessage(
					"You dont have the required level to use that axe.");
			return false;
		}
		if (!hasWoodcuttingLevel(player)) {
			return false;
		}
		return true;
	}
	
	private boolean hasWoodcuttingLevel(Player player) {
		if (definitions.getWoodcuttingLevel() > player.getSkills().getLevel(8)) {
			player.getPackets().sendGameMessage(
					"You need a woodcutting level of " + definitions.getWoodcuttingLevel()
							+ " to chop down this tree.");
			return false;
		}
		return true;
	}

	private boolean setAxe(Player player) {
		int level = player.getSkills().getLevel(8);
		int weaponId = player.getEquipment().getWeaponId();
		if (weaponId != -1) {
			switch (weaponId) {
			case 6739: // dragon axe
				if (level >= 61) {
					emoteId = 2846;
					axeTime = 13;
					return true;
				}
				break;
			case 1359: // rune axe
				if (level >= 41) {
					emoteId = 867;
					axeTime = 10;
					return true;
				}
				break;
			case 1357: // adam axe
				if (level >= 31) {
					emoteId = 869;
					axeTime = 7;
					return true;
				}
				break;
			case 1355: // mit axe
				if (level >= 21) {
					emoteId = 871;
					axeTime = 5;
					return true;
				}
				break;
			case 1361: // black axe
				if (level >= 11) {
					emoteId = 873;
					axeTime = 4;
					return true;
				}
				break;
			case 1353: // steel axe
				if (level >= 6) {
					emoteId = 875;
					axeTime = 3;
					return true;
				}
				break;
			case 1349: // iron axe
				emoteId = 877;
				axeTime = 2;
				return true;
			case 1351: // bronze axe
				emoteId = 879;
				axeTime = 1;
				return true;
			case 13661: // Inferno adze
				if (level >= 61) {
					emoteId = 10251;
					axeTime = 13;
					return true;
				}
				break;
			}
		}
		if (player.getInventory().containsOneItem(6739)) {
			if (level >= 61) {
				emoteId = 2846;
				axeTime = 13;
				return true;
			}
		}
		if (player.getInventory().containsOneItem(1359)) {
			if (level >= 41) {
				emoteId = 867;
				axeTime = 10;
				return true;
			}
		}
		if (player.getInventory().containsOneItem(1357)) {
			if (level >= 31) {
				emoteId = 869;
				axeTime = 7;
				return true;
			}
		}
		if (player.getInventory().containsOneItem(1355)) {
			if (level >= 21) {
				emoteId = 871;
				axeTime = 5;
				return true;
			}
		}
		if (player.getInventory().containsOneItem(1361)) {
			if (level >= 11) {
				emoteId = 873;
				axeTime = 4;
				return true;
			}
		}
		if (player.getInventory().containsOneItem(1353)) {
			if (level >= 6) {
				emoteId = 875;
				axeTime = 3;
				return true;
			}
		}
		if (player.getInventory().containsOneItem(1349)) {
			emoteId = 877;
			axeTime = 2;
			return true;
		}
		if (player.getInventory().containsOneItem(1351)) {
			emoteId = 879;
			axeTime = 1;
			return true;
		}
		if (player.getInventory().containsOneItem(13661)) {
			if (level >= 61) {
				emoteId = 10251;
				axeTime = 13;
				return true;
			}
		}
		return false;

	}

	private boolean hasAxe(Player player) {
		if (player.getInventory().containsOneItem(1351, 1349, 1353, 1355, 1357,
				1361, 1359, 6739, 13661)) {
			return true;
		}
		int weaponId = player.getEquipment().getWeaponId();
		if (weaponId == -1) {
			return false;
		}
		switch (weaponId) {
		case 1351:// Bronze Axe
		case 1349:// Iron Axe
		case 1353:// Steel Axe
		case 1361:// Black Axe
		case 1355:// Mithril Axe
		case 1357:// Adamant Axe
		case 1359:// Rune Axe
		case 6739:// Dragon Axe
		case 13661: // Inferno adze
			return true;
		default:
			return false;
		}

	}

	@Override
	public boolean process(Player player) {
		player.setNextAnimation(new Animation(emoteId));
		return true;
	}

	@Override
	public int processWithDelay(Player player) {
		if (Utils.getRandom(6) == 1) {
			player.getSkills().addXp(8, definitions.getWoodcuttingXp());
		}
		if (Utils.getRandom(8) == 1) {
			int health = Utils.random(5, 50);
			definitions.treeHealth -= health;
			player.EvilTreeDamage += health;
			player.getInventory().addItem(20731,1);
			player.getInventory().addItem(20730,1);
			player.getInventory().addItem(20732,1);
			player.getPackets().sendGameMessage("You have received a set of keys. I should head back to the Queen of Snow.");
		}
		if (Utils.getRandom(9) == 1) {
			World.spawnObject(
					new WorldObject(11426, 10, 0,
							player.getX(), player.getY(), player
							.getPlane()), true);
		}
	 if (definitions.getTreeHealth() == 0) {
			World.removeObject(tree, true);
	}
		return 10;
}

	@Override
	public void stop(Player player) {
		setActionDelay(player, 3);
	}
}