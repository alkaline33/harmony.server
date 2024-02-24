package com.rs.game.player;

import java.io.Serializable;

import com.rs.utils.Utils;

/**
 * @author Wolfey
 * @author Mystic Flow
 * @author Bandoswhips
 * @author Nexon/Fuzen Seth
 */
public class SlayerTask implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3885979679549716755L;

	public static void sendShop(Player player) {
		player.getPackets().sendIComponentText(1308, 342, "" + player.getSlayerPoints() + "");
		player.getInterfaceManager().sendInterface(1308);
	}

	public static void handleButtons(Player player, int componentId) {

		if (componentId == 65) {
		}
	}

	public enum Master {
		KURADAL(9085, // high ONLY ADD TO THE BOTTOM, ELSE IT SCREWS UP SLAYER MASTER CAPE
				new Object[][] { { "Abyssal demon", 85, 25, 130, 150 }, { "Kraken", 87, 15, 40, 255 }, { "Nechryael", 80, 45, 85, 105.0 },
						{ "Dust devil", 60, 40, 75, 105.0 }, { "Ice strykewyrm", 93, 25, 80, 225.0 },
						{ "Jungle strykewyrm", 73, 25, 80, 190.0 }, { "Desert strykewyrm", 77, 25, 80, 192.0 },
						{ "Iron dragon", 30, 40, 75, 173.2 }, { "Steel dragon", 30, 40, 75, 220.5 },
						{ "Black dragon", 70, 40, 75, 199.5 }, { "Green dragon", 40, 40, 75, 75.0 },
						{ "Mithril dragon", 1, 30, 40, 273.0 }, { "Glacor", 1, 30, 40, 329.0 }, { "Runite dragon", 1, 30, 40, 247.0 }, { "Adamantite dragon", 1, 30, 40, 235.0 },
						{ "Jelly", 1, 25, 60, 75.0 }, { "Infernal mage", 1, 40, 75, 60.0 },
						{ "Fire Giant", 1, 40, 75, 111.0 }, { "Aberrant spectre", 1, 40, 75, 90.0 },
						{ "Blue dragon", 50, 40, 75, 107.5 }, { "Black demon", 20, 40, 75, 160.0 },
						{ "Mutated jadinko baby", 50, 40, 80, 190.5 },
						{ "Corporeal beast", 85, 20, 35, 1100.0 }, { "King black dragon", 75, 20, 45, 258.0 },
						{ "Gargoyle", 75, 150, 200, 105.0 }, { "Frost dragon", 80, 25, 60, 227.0 },
						{ "Hellhound", 1, 25, 60, 116.0 }, { "Dark beast", 90, 40, 75, 225.4 },
						{ "Edimmu", 90, 20, 55, 305.0 }, { "Nex", 95, 10, 25, 1500.0 }, { "Ganodermic beast", 95, 30, 100, 250.0 }, { "General Graardor", 90, 20, 30, 500.0 },
						{ "Commander Zilyana", 90, 20, 30, 500.0 }, { "K'ril Tsutsaroth", 90, 20, 30, 500.0 },
						{ "Bloodveld", 50, 30, 100, 120.0 }, { "Smoke devil", 93, 40, 75, 140.0 },
						{ "Zulrah", 93, 20, 35, 500.0 }, { "Lizardman shaman", 1, 30, 60, 157.5 }, { "Abyssal Sire", 85, 20, 25, 450.0 }
						, { "Turoth", 55, 20, 60, 76.0 }, { "Kurask", 70, 20, 60, 97.0 }, { "Aquanite", 78, 20, 60, 100.0 }, { "Cave horror", 58, 20, 55, 55.0 },
						{ "Banshee", 15, 40, 75, 22.0 }, { "Ankou", 1, 40, 75, 60.0 }, { "Dagannoth", 1, 40, 75, 70.0 }, { "Moss giant", 1, 40, 75, 60.0 }, { "Greater demon", 1, 40, 75, 87.0 },
						{ "Lesser demon", 1, 40, 75, 79.0 }, { "Bronze dragon", 1, 40, 75, 125.0 }, { "Red dragon", 1, 40, 75, 143.0 }, { "Cerberus", 91, 20, 40, 690.0 }, { "Callisto", 1, 20, 40, 312.0 },
						{ "Kree'arra", 90, 20, 30, 500.0 }, { "Venenatis", 90, 20, 30, 388.8 },
						{ "Chaos fanatic", 90, 20, 30, 253 }, { "Crazy archaeologist", 90, 20, 30, 275 }, { "Scorpia", 90, 20, 30, 260 }, { "Vet'ion", 90, 20, 30, 312 }, { "Long-tailed Wyvern", 66, 20, 60, 205.0 },
						{ "Taloned Wyvern", 66, 20, 60, 205.0 }, { "Spitting Wyvern", 66, 20, 60, 205.0 }, { "Ancient Wyvern", 82, 20, 60, 315.0 },
						{ "Drake", 84, 50, 150, 268.7 }, { "Wyrm", 62, 50, 150, 133.2 }, { "Hydra", 95, 50, 150, 322.5 }, { "NULL", 1000, 40, 75, 1.0 } }),

		KURADALNUB(9085, // low
				new Object[][] { { "Chaos dwarf", 1, 30, 50, 61.0 }, { "Magic axe", 1, 30, 50, 45.0 }, { "Giant bat", 1, 30, 50, 32.0 }, { "Skeleton", 1, 30, 50, 29.0 }, { "Crawling Hand", 1, 25, 60, 16.0 }, { "Farmer", 1, 30, 50, 16.0 }, { "Cow", 1, 25, 60, 8.0 },
						{ "Jelly", 1, 25, 60, 75.0 }, { "Rock crab", 1, 25, 60, 50.0 }, { "Bat", 1, 25, 60, 8.0 },
						{ "Hill giant", 1, 25, 60, 35.0 }, { "Cave crawler", 1, 25, 60, 22.0 }, { "Rockslug", 1, 25, 60, 27.0 }, { "Cockatrice", 1, 25, 60, 37.0 }
						, { "Pyrefiend", 40, 20, 60, 45.0 }, { "Basilisk", 40, 20, 60, 75.0 }, { "Banshee", 15, 40, 75, 22.0 }, { "Ankou", 1, 40, 75, 60.0 }, { "Dagannoth", 1, 40, 75, 70.0 }, { "Sand crab", 1, 25, 60, 40.0 }, { "Ghost", 1, 25, 60, 25.0 }, { "Lesser demon", 1, 40, 75, 79.0 }, { "Sulphur lizard", 44, 50, 150, 50.0 }, { "NULL", 1000, 40, 75, 1.0 } }),

		KURADAL1(9085, // med
				new Object[][] { { "Dust devil", 60, 40, 75, 105.0 }, { "Infernal mage", 1, 40, 75, 60.0 },
						{ "Fire Giant", 1, 40, 75, 111.0 }, { "Aberrant spectre", 1, 40, 75, 90.0 },
						{ "Iron dragon", 30, 40, 75, 173.2 }, { "Jelly", 1, 25, 60, 75.0 },
						{ "Steel dragon", 30, 40, 75, 220.5 }, { "Adamantite dragon", 1, 30, 40, 235.0 },
						{ "Green dragon", 40, 40, 75, 75.0 }, { "Blue dragon", 50, 40, 75, 107.5 },
						{ "Cave horror", 58, 20, 55, 55.0 },
						{ "Black dragon", 70, 40, 75, 199.5 },{ "Hellhound", 1, 25, 60, 116.0 },
						{ "Black demon", 20, 40, 75, 160.0 }, { "King black dragon", 75, 20, 45, 258.0 },
						{ "Gargoyle", 75, 150, 200, 105.0 }, { "Frost dragon", 80, 25, 60, 227.0 },
						{ "Bloodveld", 50, 30, 100, 120.0 }, { "Waterfiend", 1, 25, 60, 128.0 }
						, { "Lizardman shaman", 1, 30, 60, 157.5 }, { "Cave crawler", 1, 25, 60, 22.0 }, { "Rockslug", 1, 25, 60, 27.0 }, { "Cockatrice", 1, 25, 60, 37.0 }, { "Pyrefiend", 40, 20, 60, 45.0 }, { "Basilisk", 40, 20, 60, 75.0 }
						, { "Hill giant", 1, 25, 60, 35.0 }, { "Turoth", 55, 20, 60, 76.0 }, { "Kurask", 70, 20, 60, 97.0 }, { "Aquanite", 78, 20, 60, 100.0 }, { "Banshee", 15, 40, 75, 22.0 }, { "Ankou", 1, 40, 75, 60.0 }, { "Dagannoth", 1, 40, 75, 70.0 }, { "Moss giant", 1, 40, 75, 60.0 }, { "Greater demon", 1, 40, 75, 87.0 }, { "Lesser demon", 1, 40, 75, 79.0 }, { "Drake", 84, 50, 150, 268.7 }, { "Wyrm", 62, 50, 150, 133.2 }, { "Sulphur lizard", 44, 50, 150, 50.0 }, { "NULL", 1000, 40, 75, 1.0 } }),

		SUMONA(7780,
				new Object[][] { { "Abyssal demon", 85, 25, 130, 300 }, { "Nechryael", 80, 45, 85, 157.5 },
			{ "Dust devil", 60, 40, 75, 157.5 }, { "Ice strykewyrm", 93, 25, 80, 337.5 },
			 { "Jungle strykewyrm", 73, 25, 80, 285.0 },  { "Desert strykewyrm", 77, 25, 80, 288.0 },
			{ "Iron dragon", 30, 40, 75, 259.2 }, { "Steel dragon", 30, 40, 75, 330.5 },
			{ "Black dragon", 70, 40, 75, 298.5 }, { "Green dragon", 40, 40, 75, 112.5 },
						{ "Mithril dragon", 1, 30, 40, 409.5 }, { "Glacor", 1, 20, 40, 493.0 }, { "Runite dragon", 1, 30, 40, 370.0 }, { "Adamantite dragon", 1, 30, 40, 352.0 },
			{ "Jelly", 1, 25, 60, 112.0 }, { "Infernal mage", 1, 40, 75, 90.0 },
						{ "Cave horror", 58, 20, 55, 77.0 },
						// { "Cave horror", 58, 20, 55, 77.0 },
			{ "Fire Giant", 1, 40, 75, 166.0 }, { "Aberrant spectre", 1, 40, 75, 135.0 },
			{ "Blue dragon", 50, 40, 75, 160.5 }, { "Black demon", 20, 40, 75, 240.0 },
						{ "Corporeal beast", 85, 20, 35, 1650.0 }, { "King black dragon", 75, 20, 45, 387.0 }, { "Turoth", 55, 20, 60, 114.0 }, { "Kurask", 70, 20, 60, 145.5 }, { "Aquanite", 78, 20, 60, 150.0 },
			{ "Gargoyle", 75, 150, 200, 157.0 }, { "Frost dragon", 80, 25, 60, 340.0 },
			{ "Hellhound", 1, 25, 60, 174.0 }, { "Dark beast", 90, 40, 75, 337.4 },
						{ "Edimmu", 90, 20, 55, 457.0 }, { "Nex", 95, 10, 25, 2250.0 }, { "Ganodermic beast", 95, 30, 100, 375.0 }, { "General Graardor", 90, 20, 30, 750.0 }, { "Commander Zilyana", 90, 20, 30, 750.0 }, { "K'ril Tsutsaroth", 90, 20, 30, 750.0 }, { "Bloodveld", 50, 30, 100, 180.0 }, { "Kraken", 87, 25, 40, 382 }, { "Smoke devil", 93, 40, 75, 210.0 }, { "Zulrah", 93, 25, 35, 750.0 }, { "Abyssal Sire", 85, 29, 35, 675.0 }, { "Lizardman shaman", 1, 30, 60, 235 }, { "Dagannoth", 1, 40, 75, 105.0 }, { "Moss giant", 1, 40, 75, 90.0 }, { "Greater demon", 1, 40, 75, 130.5 }, { "Lesser demon", 1, 40, 75, 118.5 }, { "Venenatis", 90, 20, 30, 583.2 }, { "Chaos fanatic", 90, 20, 30, 379.5 }, { "Crazy archaeologist", 90, 20, 30, 412.5 }, { "Scorpia", 90, 20, 30, 390 },
						{ "Vet'ion", 90, 20, 30, 468 }, { "Long-tailed Wyvern", 66, 20, 60, 307.5 }, { "Taloned Wyvern", 66, 20, 60, 307.5 }, { "Spitting Wyvern", 66, 20, 60, 307.5 }, { "Ancient Wyvern", 82, 20, 60, 472.5 },
						{ "Drake", 84, 50, 150, 403.5 }, { "Wyrm", 62, 50, 150, 199.8 }, { "Hydra", 95, 50, 150, 483.75 }, { "NULL", 1000, 40, 75, 1.0 } }),

		DEATH(9085,
				new Object[][] { { "Corporeal beast", 99, 20, 35, 2000.0 }, { "King black dragon", 99, 20, 40, 535.0 }, { "Cerberus", 99, 20, 40, 800.0 }, { "Nex", 99, 10, 25, 3100.0 },
			{ "Kree'arra", 99, 20, 40, 700.0 }, { "General Graardor", 99, 20, 40, 700.0 }, { "Commander Zilyana", 99, 20, 40, 700.0 }, { "K'ril Tsutsaroth", 99, 20, 40, 700.0 },
			{ "Avatar of Destruction", 99, 20, 25, 1000.0 }, { "Night-gazer khighorahk", 99, 20, 10, 3500.0 }, { "WildyWyrm", 99, 20, 30, 1150.0 }, { "Dryax", 99, 20, 30, 750.0 },
			{ "Kalphite King", 99, 20, 38, 2650.0 }, { "Vorago", 99, 20, 35, 6500.0 }, { "Sirenic the Spider", 99, 20, 39, 825.0 }, { "Hope Devourer", 99, 20, 35, 1400.0 },
			{ "Bork", 99, 20, 35, 250.0 }, { "Anivia", 99, 20, 35, 1300.0 }, { "Aquatic Wyrm", 99, 20, 35, 1150.0 },
						{ "Zulrah", 93, 20, 35, 800.0 },
						{ "Abyssal Sire", 85, 20, 35, 700.0 }, { "Sliske", 85, 10, 20, 1700.0 }, { "Sunfreet", 85, 10, 20, 1200.0 },
						{ "Callisto", 1, 20, 40, 390.0 }, { "Venenatis", 90, 20, 30, 450.0 }, { "Chaos fanatic", 90, 20, 30, 300 }, { "Crazy archaeologist", 90, 20, 30, 350 },
						{ "Scorpia", 90, 20, 30, 320 }, { "Vet'ion", 90, 20, 30, 400 },
						{ "Vorkath", 1, 10, 35, 1125.0 }, { "Alchemical Hydra", 1, 10, 25, 1320.0 }, { "NULL", 1000, 40, 75, 1.0 } });

		private int id;
		private Object[][] data;

		private Master(int id, Object[][] data) {
			this.id = id;
			this.data = data;
		}

		public static Master forId(int id) {
			for (Master master : Master.values()) {
				if (master.id == id) {
					return master;
				}
			}
			return null;
		}

		public int getId() {
			return id;
		}

	}

	private Master master;
	private int taskId;
	private int taskAmount;
	private int amountKilled;
	public long slaytask;

	public SlayerTask(Master master, int taskId, int taskAmount) {
		this.master = master;
		this.taskId = taskId;
		this.taskAmount = taskAmount;
	}

	public String getName() {
		return (String) master.data[taskId][0];
	}

	public static void sendBuy(Player player) {
		player.getPackets().sendIComponentText(164, 20, +player.getSlayerPoints() + "");
		player.getInterfaceManager().sendInterface(164);
	}

	public static void sendLearn(Player player) {
		player.getPackets().sendIComponentText(163, 18, +player.getSlayerPoints() + "");
		player.getPackets().sendIComponentText(163, 24, "Purchase a Full Slayer Helm");
		player.getInterfaceManager().sendInterface(163);
	}

	public static SlayerTask random(Player player, Master master) {
		SlayerTask task = null;
		while (true) {
			int random = Utils.random(master.data.length - 1);
			int requiredLevel = (Integer) master.data[random][1];
			if (player.getSkills().getLevel(Skills.SLAYER) < requiredLevel) {
				continue;
			}
			int minimum = (Integer) master.data[random][2];
			int maximum = (Integer) master.data[random][3];
			if (task == null) {
				task = new SlayerTask(master, random, Utils.random(minimum, maximum));
				player.setTask(task);
			}
			break;
		}
		return task;
	}

	public static SlayerTask notrandom(Player player, Master master, int taskid) {
		SlayerTask task = null;
		while (true) {
			int random = Utils.random(master.data.length - 1);
			int requiredLevel = (Integer) master.data[random][1];
			if (player.getSkills().getLevel(Skills.SLAYER) < requiredLevel) {
				continue;
			}
			int minimum = (Integer) master.data[random][2];
			int maximum = (Integer) master.data[random][3];
			if (task == null) {
				task = new SlayerTask(master, taskid, Utils.random(minimum, maximum));
				player.setTask(task);
			}
			break;
		}
		return task;
	}

	public int getTaskId() {
		return taskId;
	}
	
	public int setTaskId(int id) {
		return taskId = id;
	}

	public int getTaskAmount() {
		return taskAmount;
	}
	
	public int setTaskAmount() {
		return taskAmount = Utils.random(100, 150);
	}

	public void decreaseAmount() {
		taskAmount--;
	}

	public int getXPAmount() {
		Object obj = master.data[taskId][4];
		if (obj instanceof Double) {
			return (int) Math.round((Double) obj);
		}
		if (obj instanceof Integer) {
			return (Integer) obj;
		}
		return 0;
	}

	public Master getMaster() {
		return master;
	}

	/**
	 * @return the amountKilled
	 */
	public int getAmountKilled() {
		return amountKilled;
	}

	/**
	 * @param amountKilled
	 *            the amountKilled to set
	 */
	public void setAmountKilled(int amountKilled) {
		this.amountKilled = amountKilled;
	}


}
