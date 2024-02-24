package com.rs.game.player.content;

import java.util.concurrent.CopyOnWriteArrayList;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Colors;
import com.rs.utils.ItemExamines;
import com.rs.utils.ItemSetsKeyGenerator;

public class Shop {

	private static final int MAIN_STOCK_ITEMS_KEY = ItemSetsKeyGenerator.generateKey();

	private static final int MAX_SHOP_ITEMS = 400;
	public static final int COINS = 995;

	private String name;
	private Item[] mainStock;
	private int[] defaultQuantity;
	private Item[] generalStock;
	private int money;
	private int amount;

	private CopyOnWriteArrayList<Player> viewingPlayers;

	public Shop(String name, int money, Item[] mainStock, boolean isGeneralStore) {
		viewingPlayers = new CopyOnWriteArrayList<Player>();
		this.name = name;
		this.money = money;
		this.mainStock = mainStock;
		defaultQuantity = new int[mainStock.length];
		for (int i = 0; i < defaultQuantity.length; i++) {
			defaultQuantity[i] = mainStock[i].getAmount();
		}
		if (isGeneralStore && mainStock.length < MAX_SHOP_ITEMS) {
			generalStock = new Item[MAX_SHOP_ITEMS - mainStock.length];
		}
	}

	public boolean isGeneralStore() {
		return generalStock != null;
	}
	
	public static int[][] dryPrices = { { 1046, 5000 }, { 22268, 9000 }};
	
	public static int[][] diaryPrices = { { 7409, 30 }, { 28910, 100 }, { 28918, 60 }, { 28917, 30 }, { 28916, 30 }, { 28915, 15 }, { 28913, 20 }, { 28912, 20 }, { 28911, 18 }, { 28909, 40 }};

	public static int[][] pvmPrices = { { 29389, 20000 }, { 29354, 2500 }, { 29392, 20000 }, { 29395, 20000 }, { 29398, 20000 }, { 18015, 10000 }, { 19335, 15000 }, { 19670, 50000 }, { 29439, 500 }, { 9472, 5000000 }, { 15017, 250000 }, { 18747, 50000 }
			, { 8839, 8000 }, { 8840, 8000 }, { 8842, 8000 }, { 11663, 8000 }, { 11664, 8000 }, { 11665, 8000 }
			, { 7462, 6000 }, { 15126, 11500 }, { 6914, 14000 }, { 6889, 6000 }, { 10551, 9000 }, { 10548, 9000 }, { 29480, 20000 }
			, { 19712, 10000 }, { 29965, 25000 }};
	
	
	public static int[][] VotePrices = { { 29361, 2 }, { 29007, 10 }, { 29006, 25 }, { 29347, 35 }, { 29345, 35 }, { 29220, 35 }, { 29343, 35 }, { 3114, 1 }, { 29532, 5 }, { 24437, 21 }, { 29544, 26 }
	, { 6199, 12 }, { 25202, 12 }, { 23671, 35 }, { 29770, 1 }
	, { 23673, 100 }, { 15449, 15 }, { 15454, 15 }, { 15459, 15 }, { 15464, 15 }
	, { 23715, 15 }, { 1053, 300 }, { 1055, 300 }, { 1057, 300 }, { 29654, 100 }, { 18744, 100 }, { 18745, 100 }, { 18746, 100 }};
	
	public static int[][] dungPrices = { { 28937, 2000000 }, { 18339, 25000 }, { 29055, 250000 }, { 29543, 200000 }, { 18349, 200000 }, { 29915, 400000 }, { 18351, 200000 }
	, { 18355, 200000 }, { 18357, 200000 }, { 18353, 200000 }, { 18335, 50000 }
	, { 19669, 75000 }, { 18363, 200000 }, { 18359, 200000 }, { 18361, 200000 }
	, { 18347, 100000 }, { 18337, 100000 }, { 19894, 1000000 }, { 27068, 200000 }
	, { 29793, 300000 }, { 25991, 100000 }, { 25993, 100000 }, { 25995, 100000 },
	{ 27996, 100000 },{ 31453, 300000 },{ 29903, 300000 },{ 29517, 100000 },
			{ 29516, 100000 }, { 29515, 100000 }, { 19675, 100000 }, { 18346, 200000 }, { 29286, 200000 }, { 18340, 300000 } };
	
	public static int[][] playerwarsPrices = { { 29504, 1500 }, { 29503,200 }, { 29501, 10000 }, { 29500, 1000 }, { 29499, 1000 }
	, { 29498, 1000 }, { 29497, 1000 }, { 29496, 1000 }, { 29495, 1000 }, { 29494, 1000 }, { 29493, 1000 }, { 29492, 1000 }
	, { 29491, 1000 }, { 29490, 1000 }, { 29489, 1000 }, { 29488, 1000 }, { 29487, 1000 }, { 29486, 1000 }, { 29485, 1000 }
	, { 29484, 1000 }, { 29483, 1000 }, { 29482, 1000 }};

	
	public static int[][] SkillPrices = { { 29435, 2100 }, { 6739, 2100 }, { 222, 5 }, { 29352, 1200 }, { 6694, 20 }, { 4621, 50 }, { 238, 5 }, { 23328, 100 }, { 226, 15 }, { 775, 1500 }, { 13661, 7500 }, { 20786, 10000 }, { 14808, 3000 }, { 23779, 1500 }, { 2677, 1200 }, { 10034, 5 }, { 24300, 4000 }, { 9658, 1500 }, { 5316, 100 }, { 5314, 25 }, { 5304, 80 } };
	
	public static int[][] HeistPrices = { { 24154, 5 }, { 24549, 100 }, { 24805, 50 }, { 24804, 40 }, { 24803, 40 }, { 24802, 20 }, { 24801, 20 }, { 10506, 380 }, { 29572, 120 } };
	
	public static int[][] TriviaPrices = { { 23713, 5 },{ 29225, 40 }, { 29059, 25 }, { 29058, 30 }, { 29057, 50 }, { 29879, 30 }, { 15353, 25 }};
	
	public static int[][] challengePrices = { {28933, 70}, {28932, 100}, {2717, 20}, {25309, 20}, {25307, 20}, {25305, 20}, {25303, 20}, {25301, 20}, {25299, 20} };
	
	public static int[][] loyaltyPrices = { { 20958, 8500 }, { 22897, 5000 }, { 22268, 9000 },
			{ 20962, 5000 }, { 22270, 10000 }, { 20967, 5000 },
			{ 22272, 8000 }, { 22280, 5000 }, { 22282, 9000 }, { 22284, 5000 },
			{ 22286, 8000 }, { 20966, 5000 }, { 22274, 10000 },
			{ 20965, 5000 }, { 22276, 8000 }, { 22288, 5000 }, { 22290, 8000 },
			{ 22292, 5000 }, { 22294, 10000 }, { 22300, 7000 },
			{ 22296, 5000 }, { 22298, 10000 }, { 22302, 9000 }, { 23880, 5000 },
			{ 23882, 5000 }, { 23884, 5000 }, { 23886, 5000 }, { 23888, 5000 },
			{ 23890, 5000 }, { 23892, 5000 }, { 23894, 5000 }, { 23896, 5000 },
			{ 23898, 5000 }, { 23900, 5000 }, { 23902, 5000 }, { 23904, 5000 },
			{ 23906, 5000 }, { 23908, 5000 }, { 23910, 5000 }, { 23848, 10000 }, { 23850, 20000 },
			{ 23864, 30000 }, { 23866, 30000 },
			{ 23852, 40000 }, { 23854, 80000 }, { 23876, 20000 }, { 23874, 30000 }, { 22895, 200000 }, { 29284, 30000 }, { 29283, 30000 }, { 29282, 30000 } };
	
	public static int[][] pvpPrices = { { 13887, 10 }, { 13893, 10 },{ 13899, 10 },{ 13905, 10 },{ 13870, 10 },{ 13873, 10 },{ 13876, 10 },{ 13879, 2 },{ 13858, 10 },{ 13861, 10 },
		{ 13864, 10 },{ 13867, 10 },{ 7462, 3 },{ 20072, 5 },{ 8850, 3 },{ 10551, 10 },{ 10548, 10 },{ 29881, 50 },{ 8839, 10 },{ 8840, 10 },{ 8842, 10 },{ 11663, 10 },{ 11664, 10 },
		{ 11665, 10 },{ 19785, 20 },{ 19786, 20 },{ 13884, 20 },{ 13890, 20 },{ 13896, 30 },{ 13902, 30 },{ 6570, 6 },{ 29854, 16 },{ 14484, 80 },{ 11694, 50 },{ 15486, 25 },{ 11235, 25 },{ 29869, 100},
		{ 21787, 30} ,{ 21790, 30},{ 21793, 30}};
	
	public static int[][] pvpPrices2 = { { 29869, 100},
			{ 21787, 30} ,{ 21790, 30},{ 21793, 30},{ 15018, 20},{ 15019, 20},{ 15220, 20},{ 19784, 30},{ 19669, 15}
			,{ 13738, 80},{ 13740, 100},{ 13742, 90},{ 11724, 40},{ 11726, 40},{ 11718, 10},{ 11720, 30},{ 11722, 30}
	};
	
	public static int[][] slayersurvivalPrices = { { 29367, 50 }, { 29540, 5 }, { 989, 1 }, { 29183, 150 }, { 29182, 50 } };

	public static int[][] calaPrices = { { 29778, 120 }, { 29777, 210 },{ 29776, 210 },{ 29775, 140 },{ 29774, 400 }
	,{ 29773, 500 } ,{ 29772, 600 },{ 29771, 40 },{ 29762, 150 },{ 29761, 150 },{ 29759, 150 },{ 29758, 350 }
	,{ 29749, 200 },{ 29752, 200 },{ 29755, 200 },{ 29750, 230 },{ 29751, 230 },{ 29753, 230 },{ 29754, 230 },{ 29756, 230 },{ 29757, 230 }};
	

	public static int[][] slayerPrices = { { 4155, 1 }, { 2890, 20 }, { 2572, 60 }, { 2412, 100 }, { 2413, 100 }, { 2414, 100 }, { 29425, 200 }, { 29728, 2000 }, { 13263, 350 }, { 15490, 100 }, { 15488, 100 }, { 29424, 60 }, { 29698, 600 }, { 29699, 600 }, { 29700, 600 }, { 29701, 600 }, { 29697, 600 }, { 29702, 600 }, { 1720, 250 } };
 
	
	//public static int[][] taskPrices = { { 9470, 100 }, { 1837, 150 }, };

	public void addPlayer(final Player player) {
		viewingPlayers.add(player);
		player.getTemporaryAttributtes().put("Shop", this);
		player.setCloseInterfacesEvent(new Runnable() {
			@Override
			public void run() {
				viewingPlayers.remove(player);
				player.getTemporaryAttributtes().remove("Shop");
				player.getTemporaryAttributtes().remove("shop_buying");
				player.getTemporaryAttributtes().remove("amount_shop");
			}
		});
		player.getPackets().sendConfig(118, MAIN_STOCK_ITEMS_KEY);
		player.getPackets().sendConfig(1496, -1);
		player.getPackets().sendConfig(532, money);
		player.getPackets().sendConfig(2565, 0);
		sendStore(player);
		player.getPackets().sendGlobalConfig(199, -1);
		player.getInterfaceManager().sendInterface(1265);
		for (int i = 0; i < MAX_SHOP_ITEMS; i++)
		 {
			player.getPackets().sendGlobalConfig(946 + i, i < defaultQuantity.length ? defaultQuantity[i]: generalStock != null ? 0 : -1);// prices
		}
		player.getPackets().sendGlobalConfig(1241, 16750848);
		player.getPackets().sendGlobalConfig(1242, 15439903);
		player.getPackets().sendGlobalConfig(741, -1);
		player.getPackets().sendGlobalConfig(743, -1);
		player.getPackets().sendGlobalConfig(744, 0);
		if (generalStock != null) {
			player.getPackets().sendHideIComponent(1265, 19, false);
		}
		player.getPackets().sendIComponentSettings(1265, 20, 0, getStoreSize() * 6, 1150);
		player.getPackets().sendIComponentSettings(1265, 26, 0, getStoreSize() * 6, 82903066);
		sendInventory(player);
		player.getPackets().sendIComponentText(1265, 85, name);
		player.getTemporaryAttributtes().put("shop_buying", true);
		player.getTemporaryAttributtes().put("amount_shop", 1);
	}

	public void sendInventory(Player player) {
		player.getInterfaceManager().sendInventoryInterface(1266);
		player.getPackets().sendItems(93, player.getInventory().getItems());
		player.getPackets().sendUnlockIComponentOptionSlots(1266, 0, 0, 27, 0,
				1, 2, 3, 4, 5);
		player.getPackets().sendInterSetItemsOptionsScript(1266, 0, 93, 4, 7,
				"Value", "Sell 1", "Sell 5", "Sell 10", "Sell 50", "Examine");
	}
	//cid 67
	
	public void buy(Player player, int slotId, int quantity) {
		if (slotId >= getStoreSize()) {
			return;
		}
		Item item = slotId >= mainStock.length ? generalStock[slotId - mainStock.length] : mainStock[slotId];
		if (item == null) {
			return;
		}
		if (slotId >= mainStock.length && player.isIronman()) {
			player.sendMessage("Ironman accounts can not buy player sold stock.");
			return;
		}
		if (item.getDefinitions().getName().toLowerCase().contains("lucky")) {
			player.sendMessage("You cannot buy lucky items from the store.");
			return;
		}
		if (!player.getInventory().hasFreeSlots()) {
			player.sendMessage("You need some inventory space to do this!");
			return;
		}
		/*if (quantity == 500 && player.getTemporaryAttributtes().get("last_shop_purchase") != null && (long) player.getTemporaryAttributtes().get("last_shop_purchase") > Utils.currentTimeMillis()) {
			player.sendMessage("You can only buy 500x of an item every 10 seconds. Time remaining: " + TimeUnit.MILLISECONDS.toSeconds((long) player.getTemporaryAttributtes().get("last_shop_purchase") - Utils.currentTimeMillis()));
			return;
		}*/
		if (item.getAmount() == 0) {
			player.getPackets().sendGameMessage("There is no stock of that item at the moment.");
			return;
		}
		int dq = slotId >= mainStock.length ? 0 : defaultQuantity[slotId];
		int price = getBuyPrice(item, dq);
		int amountCoins = player.getInventory().getItems().getNumberOf(money);
		int amountCoinsPouch = player.coinamount;
		int maxQuantity = amountCoins / price;
		int maxQuantityPouch = amountCoinsPouch / price;
		int buyQ = item.getAmount() > quantity ? quantity : item.getAmount();
		boolean enoughCoins = maxQuantity >= buyQ;
		boolean enoughCoinsPouch = maxQuantityPouch >= buyQ;
		if (money != 995) {
			for (int[] loyaltyPrice : loyaltyPrices) {
				loyaltyShop = 31;
				if (item.getId() == loyaltyPrice[0]) {
					if (player.getLoyaltyPoints() < loyaltyPrice[1]
							* quantity) {
						player.getPackets().sendGameMessage(
								"You need " + loyaltyPrice[1]
										+ " Loyalty Points to buy this item!");
						return;
					} else {
						loyaltyShop = 31;
					}
					player.getPackets().sendGameMessage(
							"You have bought a "
									+ item.getDefinitions().getName()
									+ " from the loyalty store.");
					player.getInventory().addItem(loyaltyPrice[0], 1);
					player.setLoyaltyPoints(player.getLoyaltyPoints()
							- loyaltyPrice[1]);
					return;
				}
			}
		}

		if (money != 995) {
			for (int[] challengePrice : challengePrices) {
				challengeShop = 115;
				if (item.getId() == challengePrice[0]) {
					if (player.challengepoints < challengePrice[1]
							* quantity) {
						player.getPackets().sendGameMessage(
								"You need " + challengePrice[1]
										+ " Daily Challenge Points to buy this item!");
						return;
					} else {
						challengeShop = 115;
					}
					player.getPackets().sendGameMessage(
							"You have bought a "
									+ item.getDefinitions().getName()
									+ " from the daily challenge store.");
					player.getInventory().addItem(challengePrice[0], 1);
					player.setDailyChallengePoints(player.challengepoints
							- challengePrice[1]);
					return;
				}
			}
		}
		if (money != 995) {
			for (int[] dungPrice : dungPrices) {
				DungShop = 107;
				if (item.getId() == dungPrice[0]) {
					if (player.dungpoints < dungPrice[1]
							* quantity) {
						player.getPackets().sendGameMessage(
								"You need " + dungPrice[1]
										+ " Dungeoneering tokens to buy this item!");
						player.getPackets().sendGameMessage(
								"<col=ff0000>You only have " + player.dungpoints
										+ " Dungeoneering tokens!");
						return;
					} else {
						DungShop = 107;
					}
					player.getPackets().sendGameMessage(
							"You have bought a "
									+ item.getDefinitions().getName()
									+ " from the Dungeoneering store.");
					player.setDungPoints(player.getdungpoints()
							- dungPrice[1]);
					player.getPackets().sendGameMessage(
							"<col=ff0000>You only have " + player.dungpoints
									+ " Dungeoneering Tokens left!");
					player.getInventory().addItem(dungPrice[0], 1);
					return;
				}
			}
		}
		if (money != 995) {
			for (int[] votePrice : VotePrices) {
				VoteShop = 108;
				if (item.getId() == votePrice[0]) {
					if (player.VotePoint < votePrice[1]
							* quantity) {
						player.getPackets().sendGameMessage(
								"You need " + votePrice[1]
										+ " Vote points to buy this item!");
						player.getPackets().sendGameMessage(
								"<col=ff0000>You only have " + player.VotePoint
										+ " Vote points!");
						return;
					} else {
						VoteShop = 108;
					}
					player.getPackets().sendGameMessage(
							"You have bought a "
									+ item.getDefinitions().getName()
									+ " from the Vote store.");
					player.setVotePoints(player.getvotepoints()
							- votePrice[1]);
					player.getPackets().sendGameMessage(
							"<col=ff0000>You only have " + player.VotePoint
									+ " Vote Points left!");
					player.getInventory().addItem(votePrice[0], 1);
					return;
				}
			}
		}
		if (money != 995) {
			for (int[] diaryPrice : diaryPrices) {
				DiaryShop = 134;
				if (item.getId() == diaryPrice[0]) {
					if (player.diarypoints < diaryPrice[1]
							* quantity) {
						player.getPackets().sendGameMessage(
								"You need " + diaryPrice[1]
										+ " Diary points to buy this item!");
						player.getPackets().sendGameMessage(
								"<col=ff0000>You only have " + player.diarypoints
										+ " Diary points!");
						return;
					} else {
						DiaryShop = 134;
					}
					player.getPackets().sendGameMessage(
							"You have bought a "
									+ item.getDefinitions().getName()
									+ " from the Achievement Diary point store.");
					player.setDiaryPoints(player.getDiaryPoints()
							- diaryPrice[1]);
					player.getPackets().sendGameMessage(
							"<col=ff0000>You only have " + player.diarypoints
									+ " Diary Points left!");
					player.getInventory().addItem(diaryPrice[0], 1);
					return;
				}
			}
		}
		if (money != 995) {
			for (int[] pvmPrice : pvmPrices) {
				PvmShop = 110;
				if (item.getId() == pvmPrice[0]) {
					if (player.Drypoints < pvmPrice[1]
							* quantity) {
						player.getPackets().sendGameMessage(
								"You need " + pvmPrice[1]
										+ " Harmony points to buy this item!");
						player.getPackets().sendGameMessage(
								"<col=ff0000>You only have " + player.Drypoints
										+ " Harmony points!");
						return;
					} else {
						PvmShop = 110;
					}
					player.getPackets().sendGameMessage(
							"You have bought a "
									+ item.getDefinitions().getName()
									+ " from the Harmony point store.");
					player.setDryPoints(player.getDryPoints()
							- pvmPrice[1]);
					player.getPackets().sendGameMessage(
							"<col=ff0000>You only have " + player.Drypoints
									+ " Harmony Points left!");
					player.getInventory().addItem(pvmPrice[0], 1);
					return;
				}
			}
		}
		if (money != 995) {
			for (int[] slayerPrice : slayerPrices) {
				SlayShop = 117;
				if (item.getId() == slayerPrice[0]) {
					if (player.slayerPoints < slayerPrice[1]
							* quantity) {
						player.getPackets().sendGameMessage(
								"You need " + slayerPrice[1]
										+ " Slayer points to buy this item!");
						player.getPackets().sendGameMessage(
								"<col=ff0000>You only have " + player.slayerPoints + " Slayer points!");
						return;
					} else {
						SlayShop = 117;
					}
					if (item.getId() == 29699) {
						player.gotfreezypet = true;
						player.sendMessage(Colors.green+"You have unlocked Freezy the pet!");
					} else if (item.getId() == 29700) {
						player.gotsdragpet = true;
						player.sendMessage(Colors.green+"You have unlocked Steels the pet!");
					} else if (item.getId() == 29701) {
						player.gotidragpet = true;
						player.sendMessage(Colors.green+"You have unlocked Irony the pet!");
					} else if (item.getId() == 29702) {
						player.gotedipet = true;
						player.sendMessage(Colors.green+"You have unlocked Edi the pet!");
					} else if (item.getId() == 29698) {
						player.gotpugpet = true;
						player.sendMessage(Colors.green+"You have unlocked Pup the pet!");
					} else if (item.getId() == 29697) {
						player.gotdustypet = true;
						player.sendMessage(Colors.green+"You have unlocked Dusty the pet!");
					}
					player.getPackets().sendGameMessage(
							"You have bought a "
									+ item.getDefinitions().getName()
									+ " from the Slayer store.");
					player.setSlayerPoints(player.getSlayerPoints()
							- slayerPrice[1]);
					player.getPackets().sendGameMessage(
							"<col=ff0000>You only have " + player.slayerPoints + " Slayer Points left!");
					player.getInventory().addItem(slayerPrice[0], 1);
					return;
				}
			}
		}
		if (money != 995) {
			for (int[] playerwarsPrice : playerwarsPrices) {
				PlayerwarShop = 109;
				if (item.getId() == playerwarsPrice[0]) {
					if (player.playerwarspoints < playerwarsPrice[1]
							* quantity) {
						player.getPackets().sendGameMessage(
								"You need " + playerwarsPrice[1]
										+ " Player wars points to buy this item!");
						player.getPackets().sendGameMessage(
								"<col=ff0000>You only have " + player.playerwarspoints
										+ " Player wars points!");
						return;
					} else {
						PlayerwarShop = 108;
					}
					player.getPackets().sendGameMessage(
							"You have bought a "
									+ item.getDefinitions().getName()
									+ " from the Player wars store.");
					player.setPlayerWarsPoints(player.playerwarspoints
							- playerwarsPrice[1]);
					player.getPackets().sendGameMessage(
							"<col=ff0000>You only have " + player.playerwarspoints
									+ " Player wars Points left!");
					player.getInventory().addItem(playerwarsPrice[0], 1);
					return;
				}
			}
		}
		if (money != 995) {
			for (int[] slayersurvivalPrices : slayersurvivalPrices) {
				survivalShop = 126;
				if (item.getId() == slayersurvivalPrices[0]) {
					if (player.slayersurvivalpoints < slayersurvivalPrices[1] * quantity) {
						player.getPackets().sendGameMessage("You need " + slayersurvivalPrices[1] + " Slayer Survival points to buy this item!");
						player.getPackets().sendGameMessage("<col=ff0000>You only have " + player.slayersurvivalpoints + " Slayer Survival points!");
						return;
					} else {
						survivalShop = 126;
					}
					player.getPackets().sendGameMessage("You have bought a " + item.getDefinitions().getName() + " from the Slayer Survival rewards store.");
					player.setSlayerSurvivalPoints(player.slayersurvivalpoints - slayersurvivalPrices[1]);
					player.getPackets().sendGameMessage("<col=ff0000>You only have " + player.slayersurvivalpoints + " Slayer Survival reward points left!");
					player.getInventory().addItem(slayersurvivalPrices[0], 1);
					return;
				}
			}
		}
		if (money != 995) {
			for (int[] calaPrice : calaPrices) {
				calaShop = 94;
				if (item.getId() == calaPrice[0]) {
					if (player.calamityrewardpoints < calaPrice[1]
							* quantity) {
						player.getPackets().sendGameMessage(
								"You need " + calaPrice[1]
										+ " Calamity reward points to buy this item!");
						player.getPackets().sendGameMessage(
								"<col=ff0000>You only have " + player.calamityrewardpoints
										+ " Calamity reward points!");
						return;
					} else {
						calaShop = 94;
					}
					player.getPackets().sendGameMessage(
							"You have bought a "
									+ item.getDefinitions().getName()
									+ " from the Calamity rewards store.");
					player.setCalamityPoints(player.calamityrewardpoints
							- calaPrice[1]);
					player.getPackets().sendGameMessage(
							"<col=ff0000>You only have " + player.calamityrewardpoints
									+ " Calamity reward points left!");
					player.getInventory().addItem(calaPrice[0], 1);
					return;
				}
			}
		}
		if (money != 995) {
			for (int[] heistPrice : HeistPrices) {
				HeistShop = 106;
				if (item.getId() == heistPrice[0]) {
					if (player.heiststorepoints < heistPrice[1]
							* quantity) {
						player.getPackets().sendGameMessage(
								"You need " + heistPrice[1]
										+ " Heist Points to buy this item!");
						player.getPackets().sendGameMessage(
								"<col=ff0000>You only have " + player.heiststorepoints
										+ " Heist Points!");
						return;
					} else {
						HeistShop = 106;
					}
					player.getPackets().sendGameMessage(
							"You have bought a "
									+ item.getDefinitions().getName()
									+ " from the Heist store.");
					player.setHeistPoints(player.getHeistPoints()
							- heistPrice[1]);
					player.getPackets().sendGameMessage(
							"<col=ff0000>You only have " + player.heiststorepoints
									+ " Heist Points left!");
					player.getInventory().addItem(heistPrice[0], 1);
					return;
				}
			}
		}
		if (money != 995) {
			for (int[] triviaPrice : TriviaPrices) {
				TriviaShop = 130;
				if (item.getId() == triviaPrice[0]) {
					if (player.getTriviaPoints() < triviaPrice[1]
							* quantity) {
						player.getPackets().sendGameMessage(
								"You need " + triviaPrice[1]
										+ " Trivia Points to buy this item!");
						player.getPackets().sendGameMessage(
								"<col=ff0000>You only have " + player.getTriviaPoints()
										+ " Trivia Points!");
						return;
					} else {
						TriviaShop = 130;
					}
					player.getPackets().sendGameMessage(
							"You have bought a "
									+ item.getDefinitions().getName()
									+ " from the Trivia store.");
					player.setTriviaPoints(player.getTriviaPoints()
							- triviaPrice[1]);
					player.getPackets().sendGameMessage(
							"<col=ff0000>You only have " + player.getTriviaPoints()
									+ " Trivia Points left!");
					player.getInventory().addItem(triviaPrice[0], 1);
					return;
				}
			}
		}
		if (money != 995) {
			for (int[] skillPrice : SkillPrices) {
				SkillShop = 103;
				if (item.getId() == skillPrice[0]) {
					if (player.skillpoints < skillPrice[1]
							* quantity) {
						player.getPackets().sendGameMessage(
								"You need " + skillPrice[1]
										+ " Skilling Points to buy this item!");
						player.getPackets().sendGameMessage(
								"<col=ff0000>You only have " + player.skillpoints
										+ " Skilling Points!");
						return;
					} else {
						SkillShop = 103;
					}
					player.getPackets().sendGameMessage(
							"You have bought a "
									+ item.getDefinitions().getName()
									+ " from the Skilling store.");
					player.setSkillPoints(player.getSkillPoints()
							- skillPrice[1]);
					player.getPackets().sendGameMessage(
							"<col=ff0000>You only have " + player.skillpoints
									+ " Skilling Points left!");
					player.getInventory().addItem(skillPrice[0], 1);
					return;
				}
			}
		}
		if (money != 995) {
			for (int[] element : pvpPrices2) {
				pvpShop = 105;
				if (item.getId() == element[0]) {
					if (player.getPvpPoints() < element[1]
							* quantity) {
						player.getPackets().sendGameMessage(
								"You need " + element[1]
										+ " PvP Points to buy this item!");
						player.getPackets().sendGameMessage(
								"<col=ff0000>You only have " + player.pvppoints
										+ " PvP Points!");
						return;
					} else {
						pvpShop = 105;
					}
					player.getPackets().sendGameMessage(
							"You have bought a "
									+ item.getDefinitions().getName()
									+ " from the PvP store.");
					player.setPvpPoints(player.getPvpPoints()
							- element[1]);
					player.getPackets().sendGameMessage(
							"<col=ff0000>You only have " + player.pvppoints
									+ " PvP Points left!");
					player.getInventory().addItem(element[0], 1);
					return;
				}
			}
		}
		if (money != 995) {
			for (int[] pvpPrice : pvpPrices) {
				pvpShop = 93;
				if (item.getId() == pvpPrice[0]) {
					if (player.getPvpPoints() < pvpPrice[1]
							* quantity) {
						player.getPackets().sendGameMessage(
								"You need " + pvpPrice[1]
										+ " PvP Points to buy this item!");
						player.getPackets().sendGameMessage(
								"<col=ff0000>You only have " + player.pvppoints
										+ " PvP Points!");
						return;
					} else {
						pvpShop = 93;
					}
					player.getPackets().sendGameMessage(
							"You have bought a "
									+ item.getDefinitions().getName()
									+ " from the PvP store.");
					player.setPvpPoints(player.getPvpPoints()
							- pvpPrice[1]);
					player.getPackets().sendGameMessage(
							"<col=ff0000>You only have " + player.pvppoints
									+ " PvP Points left!");
					player.getInventory().addItem(pvpPrice[0], 1);
					return;
				}
			}
		}
		if (!enoughCoins && !enoughCoinsPouch) {
			player.getPackets().sendGameMessage("You don't have enough coins.");
			buyQ = maxQuantity;
		} else if (quantity > buyQ) {
			player.getPackets().sendGameMessage(
					"The shop has run out of stock.");
		}
		if (item.getDefinitions().isStackable()) {
			if (player.getInventory().getFreeSlots() < 1) {
				player.getPackets().sendGameMessage(
						"Not enough space in your inventory.");
				return;
			}
		} else {
			int freeSlots = player.getInventory().getFreeSlots();
			if (buyQ > freeSlots) {
				buyQ = freeSlots;
				player.getPackets().sendGameMessage(
						"Not enough space in your inventory.");
			}
		}
		if (buyQ != 0) {
			int totalPrice = price * buyQ;
			if (amountCoins + price > 0) {
				if (money == 995) {
					if (player.coinamount >= totalPrice && player.coinamount <= Integer.MAX_VALUE) {
						player.coinamount -= totalPrice;
						Settings.GpSyncAmount += totalPrice;
						player.getPackets().sendRunScript(5561, 0, totalPrice);
						player.refreshMoneyPouch();
						player.out(totalPrice + " coins have been removed from your money pouch.");
						player.getInventory().addItem(item.getId(), buyQ);
					} else if (player.getInventory().containsItem(995, totalPrice)) {
						player.getInventory().deleteItem(995, totalPrice);
						Settings.GpSyncAmount += totalPrice;
						player.out(totalPrice + " coins have been removed from your inventory.");
						player.getInventory().addItem(item.getId(), buyQ);
					} else {
						player.getPackets().sendGameMessage("You can't afford to buy that many");
						return;
					}
				} else if (money != 995) {
					if (player.getInventory().containsItem(money, totalPrice)) {
						player.getInventory().deleteItem(money, totalPrice);
						player.getInventory().addItem(item.getId(), buyQ);
					} else {
						player.sendMessage("You don't have enough "
								+ ItemDefinitions.getItemDefinitions(money).getName().toLowerCase() + ".");
						return;
					}
				}
			}
			item.setAmount(item.getAmount() - buyQ);
			if (item.getAmount() <= 0 && slotId >= mainStock.length) {
				generalStock[slotId - mainStock.length] = null;
			}
			refreshShop();
			sendInventory(player);
		}
	}
	
	public void restoreItems() {
		boolean needRefresh = false;
		for (int i = 0; i < mainStock.length; i++) {
			if (mainStock[i].getAmount() < defaultQuantity[i]) {
				mainStock[i].setAmount(mainStock[i].getAmount() + 1);
				needRefresh = true;
			} else if (mainStock[i].getAmount() > defaultQuantity[i]) {
				mainStock[i].setAmount(mainStock[i].getAmount() + -1);
				needRefresh = true;
			}
		}
		if (generalStock != null) {
			for (int i = 0; i < generalStock.length; i++) {
				Item item = generalStock[i];
				if (item == null) {
					continue;
				}
				item.setAmount(item.getAmount() - 1);
				if (item.getAmount() <= 0) {
					generalStock[i] = null;
				}
				needRefresh = true;
			}
		}
		if (needRefresh) {
			refreshShop();
		}
	}

	private boolean addItem(int itemId, int quantity) {
		for (Item item : mainStock) {
			if (item.getId() == itemId) {
				item.setAmount(item.getAmount() + quantity);
				refreshShop();
				return true;
			}
		}
		if (generalStock != null) {
			for (Item item : generalStock) {
				if (item == null) {
					continue;
				}
				if (item.getId() == itemId) {
					item.setAmount(item.getAmount() + quantity);
					refreshShop();
					return true;
				}
			}
			for (int i = 0; i < generalStock.length; i++) {
				if (generalStock[i] == null) {
					generalStock[i] = new Item(itemId, quantity);
					refreshShop();
					return true;
				}
			}
		}
		return false;
	}

	public void sell(Player player, int slotId, int quantity) {
		if (player.getInventory().getItemsContainerSize() < slotId) {
			return;
		}
//		if (player.isIronman()) {
//			player.sendMessage("Ironmen accounts can only alch items, not sell them.");
//			return;
//		}
		Item item = player.getInventory().getItem(slotId);
		if (item == null) {
			return;
		}
		int originalId = item.getId();
		if (item.getDefinitions().isNoted()) {
			item = new Item(item.getDefinitions().getCertId(), item.getAmount());
		}
		if (item.getDefinitions().isDestroyItem()
				|| ItemConstants.getItemDefaultCharges(item.getId()) != -1
				|| !ItemConstants.isTradeable(item) || item.getId() == money) {
			player.getPackets().sendGameMessage("You can't sell this item.");
			return;
		}
		if (money != 995) {
			player.sendMessage("You can't sell items to this shop.");
			return;
		}
			if (item.getDefinitions().isDestroyItem()
					|| !ItemConstants.noShopSell(item)) {
				player.getPackets().sendGameMessage("This item cannot be sold to stores.");
				return;
		}
		int dq = getDefaultQuantity(item.getId());
		if (dq == -1 && generalStock == null) {
			player.getPackets().sendGameMessage(
					"You can't sell this item to this shop.");
			return;
		}
		int price = getSellPrice(item, dq);
		int numberOff = player.getInventory().getItems()
				.getNumberOf(originalId);
		if (quantity > numberOff) {
			quantity = numberOff;
		}
		if (!addItem(item.getId(), quantity)) {
			player.getPackets().sendGameMessage("Shop is currently full.");
			return;
		}
		if (item.getId() == 5020)  {
			price = 1000000000;
		}
		if (item.getId() == 5023)  {
			price = 2000000000;
		}
		if (item.getId() == 5022)  {
			price = 1000000;
		}
		if (item.getDefinitions().getName().toLowerCase().contains("lucky")) {
			price = 10000000;
		}
		player.getInventory().deleteItem(originalId, quantity);
		player.getInventory().addItem(money, price * quantity);
		
	}

	//public static int taskShop = 0;
	
	public static int dryShop = 0;
	
	public static int loyaltyShop = 0;
	
	public static int challengeShop = 0;
	
	public static int pvpShop = 0;
	
	public static int HeistShop = 0;
	
	public static int TriviaShop = 0;
	
	public static int SkillShop = 0;
	
	public static int DungShop = 0;
	
	public static int VoteShop = 0;
	
	public static int PvmShop = 0;
	
	public static int DiaryShop = 0;
	
	public static int SlayShop = 0;
	
	public static int PlayerwarShop = 0;
	
	public static int calaShop = 0;
	
	public static int survivalShop = 0;

	public static int bodShop = 0;


	public void sendValue(Player player, int slotId) {
		if (player.getInventory().getItemsContainerSize() < slotId) {
			return;
		}
		Item item = player.getInventory().getItem(slotId);
		if (item == null) {
			return;
		}
		if (item.getDefinitions().isNoted()) {
			item = new Item(item.getDefinitions().getCertId(), item.getAmount());
		}
		if (item.getDefinitions().isNoted() || !ItemConstants.isTradeable(item)
				|| item.getId() == money) {
			player.getPackets().sendGameMessage("You can't sell this item.");
			return;
		}
		if (item.getDefinitions().isDestroyItem() || !ItemConstants.noShopSell(item)) {
			player.getPackets().sendGameMessage("This item cannot be sold to stores.");
			return;
		}
		int dq = getDefaultQuantity(item.getId());
		if (dq == -1 && generalStock == null) {
			player.getPackets().sendGameMessage(
					"You can't sell this item to this shop.");
			return;
		}
		int price = getSellPrice(item, dq);
		if (money == 995) {
			if (item.getDefinitions().getName().toLowerCase().contains("lucky")) {
				price = 10000000;
			}
		}
		player.getPackets().sendGameMessage(
				item.getDefinitions().getName()
				+ ": shop will buy for: "
				+ price
				+ " "
				+ ItemDefinitions.getItemDefinitions(money).getName()
				.toLowerCase()
				+ ". Right-click the item to sell.");
		
	}
	
	public static String commas(String str) {
		if(str.length() < 4){
			return str;
		}
		return commas(str.substring(0, str.length() - 3)) + "," + str.substring(str.length() - 3, str.length());
	}

	public int getDefaultQuantity(int itemId) {
		for (int i = 0; i < mainStock.length; i++) {
			if (mainStock[i].getId() == itemId) {
				return defaultQuantity[i];
			}
		}
		return -1;
	}

	public void sendInfo(Player player, int slotId, boolean isBuying) {
		if (slotId >= getStoreSize()) {
			return;
		}
		Item[] stock = isBuying ? mainStock : player.getInventory().getItems().getItems();
		Item item = slotId >= stock.length ? generalStock[slotId - stock.length] : stock[slotId];
		if (item == null) {
			return;
		}
		int dq = slotId >= mainStock.length ? 0 : defaultQuantity[slotId];
		int price = getBuyPrice(item, dq);
		player.getPackets().sendIComponentText(1265, 40, item.getDefinitions().getShopExamine());
		for (int[] loyaltyPrice : loyaltyPrices) {
			if (item.getId() == loyaltyPrice[0]) {
				player.getPackets().sendGameMessage(
						"" + item.getDefinitions().getName() + " costs "
								+ loyaltyPrice[1] + " loyalty points.");
				return;
			}
			
			
		}
		for (int[] challengePrice : challengePrices) {
			if (item.getId() == challengePrice[0]) {
				player.getPackets().sendGameMessage(
						"" + item.getDefinitions().getName() + " costs "
								+ challengePrice[1] + " daily challenge points.");
				return;
			}
			
			
		}
		for (int[] calaPrice : calaPrices) {
			if (item.getId() == calaPrice[0]) {
				player.getPackets().sendGameMessage(
						"" + item.getDefinitions().getName() + " costs "
								+ calaPrice[1] + " Calamity reward points.");
				player.getPackets().sendConfig(2564, calaPrice[1]);
				return;
			}
			
			
		}
		for (int[] slayersurvivalPrices : slayersurvivalPrices) {
			if (item.getId() == slayersurvivalPrices[0]) {
				player.getPackets().sendGameMessage("" + item.getDefinitions().getName() + " costs " + slayersurvivalPrices[1] + " Slayer Survival reward points.");
				player.getPackets().sendConfig(2564, slayersurvivalPrices[1]);
				return;
			}

		}
		for (int[] heistPrice : HeistPrices) {
			if (item.getId() == heistPrice[0]) {
				player.getPackets().sendGameMessage(
						"" + item.getDefinitions().getName() + " costs "
								+ heistPrice[1] + " Heist points.");
				player.getPackets().sendConfig(2564, heistPrice[1]);
				return;
			}
			
			
		}
		for (int[] triviaPrice : TriviaPrices) {
			if (item.getId() == triviaPrice[0]) {
				player.getPackets().sendGameMessage(
						"" + item.getDefinitions().getName() + " costs "
								+ triviaPrice[1] + " Trivia points.");
				player.getPackets().sendConfig(2564, triviaPrice[1]);
				return;
			}
			
			
		}
		for (int[] skillPrice : SkillPrices) {
			if (item.getId() == skillPrice[0]) {
				player.getPackets().sendGameMessage(
						"" + item.getDefinitions().getName() + " costs "
								+ skillPrice[1] + " Skill points.");
				player.getPackets().sendConfig(2564, skillPrice[1]);
				return;
			}
			
			
		}
		for (int[] dungPrice : dungPrices) {
			if (item.getId() == dungPrice[0]) {
				player.getPackets().sendGameMessage(
						"" + item.getDefinitions().getName() + " costs "
								+ dungPrice[1] + " Dungeoneering Tokens.");
				player.getPackets().sendConfig(2564, dungPrice[1]);
				return;
			}
			
			
		}
		for (int[] votePrice : VotePrices) {
			if (item.getId() == votePrice[0]) {
				player.getPackets().sendGameMessage(
						"" + item.getDefinitions().getName() + " costs "
								+ votePrice[1] + " Vote points.");
				player.getPackets().sendConfig(2564, votePrice[1]);
				return;
			}
			
			
		}
		for (int[] slayerPrice : slayerPrices) {
			if (item.getId() == slayerPrice[0]) {
				player.getPackets().sendGameMessage("" + item.getDefinitions().getName() + " costs " + slayerPrice[1] + " Slayer points.");
				player.getPackets().sendConfig(2564, slayerPrice[1]);
				return;
			}

		}
		for (int[] diaryPrice : diaryPrices) {
			if (item.getId() == diaryPrice[0]) {
				player.getPackets().sendGameMessage(
						"" + item.getDefinitions().getName() + " costs "
								+ diaryPrice[1] + " Diary points.");
				player.getPackets().sendConfig(2564, diaryPrice[1]);
				return;
			}
			
			
		}
		for (int[] pvmPrice : pvmPrices) {
			if (item.getId() == pvmPrice[0]) {
				player.getPackets().sendGameMessage(
						"" + item.getDefinitions().getName() + " costs "
								+ pvmPrice[1] + " Harmony points.");
				player.getPackets().sendConfig(2564, pvmPrice[1]);
				return;
			}
			
			
		}
		for (int[] playerwarsPrice : playerwarsPrices) {
			if (item.getId() == playerwarsPrice[0]) {
				player.getPackets().sendGameMessage(
						"" + item.getDefinitions().getName() + " costs "
								+ playerwarsPrice[1] + " Player wars points.");
				player.getPackets().sendConfig(2564, playerwarsPrice[1]);
				return;
			}
			
			
		}
		for (int[] element : pvpPrices2) {
			if (item.getId() == element[0]) {
				player.getPackets().sendGameMessage(
						"" + item.getDefinitions().getName() + " costs "
								+ element[1] + " PvP points.");
				player.getPackets().sendConfig(2564, element[1]);
				return;
			}
			
			
		}
		for (int[] pvpPrice : pvpPrices) {
			if (item.getId() == pvpPrice[0]) {
				player.getPackets().sendGameMessage(
						"" + item.getDefinitions().getName() + " costs "
								+ pvpPrice[1] + " PvP points.");
				player.getPackets().sendConfig(2564, pvpPrice[1]);
				return;
			}
			
			
		}

		player.getPackets().sendConfig(2564, price);
		player.getPackets().sendGameMessage(item.getDefinitions().getName() + ": shop will " + (isBuying ? "sell" : "buy") +" for " + price + " " + ItemDefinitions.getItemDefinitions(money).getName().toLowerCase() + ".");

	}
	
	
	
	
	

 public int getBuyPrice(Item item, int dq) {

	int price = ItemDefinitions.getItemDefinitions(item.getId()).getValue();
	 switch (item.getId()) {
	 case 21773:
		 item.getDefinitions().setValue(3000);
			break;
	 case 25202:
		 item.getDefinitions().setValue(3000);
			break;
	 case 13316:
	 case 13317:
		 item.getDefinitions().setValue(1);
			break;
	 case 1785:
			item.getDefinitions().setValue(168);
			break;
		case 1265:
			item.getDefinitions().setValue(20);
			break;
		case 2347:
			item.getDefinitions().setValue(120);
			break;
		case 952:
			item.getDefinitions().setValue(10);
			break;
		case 5343:
			item.getDefinitions().setValue(260);
			break;
		case 5329:
			item.getDefinitions().setValue(126);
			break;
		case 1355:
			item.getDefinitions().setValue(160);
			break;
		case 1359:
			item.getDefinitions().setValue(7564);
			break;
		case 946:
			item.getDefinitions().setValue(521);
			break;
		case 21452:
			item.getDefinitions().setValue(100000000);
			break;
		case 21512:
			item.getDefinitions().setValue(2);
			break;
		case 303:
			item.getDefinitions().setValue(248);
			break;
		case 305:
			item.getDefinitions().setValue(108);
			break;
		case 301:
			item.getDefinitions().setValue(258);
			break;
		case 311:
			item.getDefinitions().setValue(300);
			break;
		case 307:
			item.getDefinitions().setValue(25);
			break;
		case 309:
			item.getDefinitions().setValue(5);
			break;
		case 314:
			item.getDefinitions().setValue(25);
			break;
		case 313:
			item.getDefinitions().setValue(236);
			break;
		case 1935:
			item.getDefinitions().setValue(25);
			break;
		case 11814:
			item.getDefinitions().setValue(636);
			break;
		case 11816:
			item.getDefinitions().setValue(984);
			break;
		case 11818:
			item.getDefinitions().setValue(1476);
			break;
		case 11820:
			item.getDefinitions().setValue(1808);
			break;
		case 11822:
			item.getDefinitions().setValue(3847);
			break;
		case 11824:
			item.getDefinitions().setValue(19700);
			break;
		case 11826:
			item.getDefinitions().setValue(16600);
			break;
		case 11828:
			item.getDefinitions().setValue(7767);
			break;
		case 11830:
			item.getDefinitions().setValue(8329);
			break;
		case 11832:
			item.getDefinitions().setValue(20900);
			break;
		case 11834:
			item.getDefinitions().setValue(22700);
			break;
		case 11836:
			item.getDefinitions().setValue(135400);
			break;
		case 11838:
			item.getDefinitions().setValue(205000);
			break;
		case 11840:
			item.getDefinitions().setValue(205000);
			break;
		case 221:
			item.getDefinitions().setValue(26);
			break;
		case 223:
			item.getDefinitions().setValue(942);
			break;
		case 225:
			item.getDefinitions().setValue(2758);
			break;
		case 4255:
			item.getDefinitions().setValue(5795);
			break;
		case 9594:
			item.getDefinitions().setValue(1004);
			break;
		case 4621:
			item.getDefinitions().setValue(2000);
			break;
		case 6693:
			item.getDefinitions().setValue(11506);
			break;
		case 241:
			item.getDefinitions().setValue(1297);
			break;
		case 12539:
			item.getDefinitions().setValue(3847);
			break;
		case 5972:
			item.getDefinitions().setValue(2483);
			break;
		case 231:
			item.getDefinitions().setValue(283);
			break;
		case 235:
			item.getDefinitions().setValue(2145);
			break;
		case 239:
			item.getDefinitions().setValue(2225);
			break;
		case 245:
			item.getDefinitions().setValue(2716);
			break;
		case 1975:
			item.getDefinitions().setValue(401);
			break;
		case 2970:
			item.getDefinitions().setValue(840);
			break;
		case 6070:
			item.getDefinitions().setValue(654);
			break;
		case 2437:
			item.getDefinitions().setValue(1025);
			break;
		case 2441:
			item.getDefinitions().setValue(2628);
			break;
		case 2443:
			item.getDefinitions().setValue(2732);
			break;
		case 2445:
			item.getDefinitions().setValue(10800);
			break;
		case 2435:
			item.getDefinitions().setValue(5801);
			break;
		case 2453:
			item.getDefinitions().setValue(5715);
			break;
		case 1725:
			item.getDefinitions().setValue(1205);
			break;
		case 6107:
			item.getDefinitions().setValue(5000);
			break;
		case 3138:
			item.getDefinitions().setValue(717);
			break;
		case 2481:
			item.getDefinitions().setValue(10000);
			break;
		case 19613:
			item.getDefinitions().setValue(5000);
			break;
		case 7633:
			item.getDefinitions().setValue(5000);
			break;
		case 1540:
			item.getDefinitions().setValue(128);
			break;
		case 21621:
			item.getDefinitions().setValue(6000);
			break;
		//thieving stalls
	 case 532:
			item.getDefinitions().setValue(10000);
			break;
     case 15333://Overload Prestige10
     	item.getDefinitions().setValue(100000);
			break;
	 case 10499:
			item.getDefinitions().setValue(1000);
			break;
			
        case 6528://tzhaar-ket-om
        	item.getDefinitions().setValue(68100);
			break;
        case 13445:
        case 4153:
        case 4154://g maul
        	item.getDefinitions().setValue(200000);
			break;
	
        case 13506:
        case 1187:
        case 1188://d sq shield
        	item.getDefinitions().setValue(159000);
			break;
        case 3105:
        case 3106://rock climbers
        	item.getDefinitions().setValue(75000);
			break;
			
		//mage equipment store
        case 2580:
        case 2579://wizard boots
        	item.getDefinitions().setValue(1000000);
			break;
        case 4675:
        case 4676://anc staff
        	item.getDefinitions().setValue(65000);
			break;
        case 15486:
        case 15487://sol
        	item.getDefinitions().setValue(2350000);
			break;
			
			//max
			
        case 20767:
        	item.getDefinitions().setValue(50000000);
        case 20768:
        	item.getDefinitions().setValue(5000000);
        	break;
        case 24365://dragon kiteshield
        	item.getDefinitions().setValue(75000000);
			break;
        case 13734://spirit shield
        	item.getDefinitions().setValue(5000000);
			break;
			
		//skillcapes/hoods
        case 9748:
        case 9749:
        case 9751:
        case 9752:
        case 9754:
        case 9755:
        case 9757:
        case 9758:
        case 9760:
        case 9761:
        case 9763:
        case 9764:
        case 9766:
        case 9767:
        case 9769:
        case 9770:
        case 9772:
        case 9773:
        case 9775:
        case 9776:
        case 9778:
        case 9779:
        case 9781:
        case 9782:
        case 9784:
        case 9785:
        case 9787:
        case 9788:
        case 9790:
        case 9791:
        case 9793:
        case 9794:
        case 9796:
        case 9799:
        case 9800:
        case 9802:
        case 9803:
        case 9805:
        case 9806:
        case 9808:
        case 9809:
        case 9811:
        case 9812:
        case 18509:
        case 18510:
        	item.getDefinitions().setValue(99000);
        	break;
        case 6571://Uncut onyx
			item.getDefinitions().setValue(100000);
			break;
		case 590:
		item.getDefinitions().setValue(150);
			break;
       
		}
		return price;
	}

 	public static int getSellPrice(Item item, int dq) {

    	
		int price = ItemDefinitions.getItemDefinitions(item.getId()).getValue() / 2;
		switch (item.getId()) {
		//SHOP BUYS FOR HALF OF THE VALUE
		//thieving stalls
		case 1963:
			item.getDefinitions().setValue(2000);
		break;
			case 590:
			item.getDefinitions().setValue(150);
			break;
				case 6571://Uncut onyx
					item.getDefinitions().setValue(100000);
					break;
				 case 25202:
					 item.getDefinitions().setValue(3000);
						break;
				case 17815: //Raw CaveMoray
					item.getDefinitions().setValue(10000);
					break;
				case 24431: //Fish Mask
					item.getDefinitions().setValue(6000000);
					break;
				case 18177: //Cooked CaveMoray
					item.getDefinitions().setValue(1000);
					break;
					
				//tools store
		        case 15259://d pickaxe
		        	item.getDefinitions().setValue(11000000);
					break;
		        case 6528://tzhaar-ket-om
		        	item.getDefinitions().setValue(60000);
					break;
		        case 13445:
		        case 4153:
		        case 4154://g maul
		        	item.getDefinitions().setValue(170000);
					break;
		        case 13506:
		        case 1187:
		        case 1188://d sq shield
		        	item.getDefinitions().setValue(100000);
					break;
		        case 11732:
		        case 3105:
		        case 3106://rock climbers
		        	item.getDefinitions().setValue(65000);
					break;
					
				//mage equipment store
		        case 2580:
		        case 2579://wizard boots
		        	item.getDefinitions().setValue(750000);
					break;
		        case 4675:
		        case 4676://anc staff
		        	item.getDefinitions().setValue(60000);
					break;
		        case 15486:
		        case 15487://sol
		        	item.getDefinitions().setValue(2000000);
					break;
		        case 24365://dragon kiteshield
		        	item.getDefinitions().setValue(15000000);
					break;
		        case 13734://spirit shield
		        	item.getDefinitions().setValue(2500000);
					break;
					
				//skillcapes/hoods
		        case 9748:
		        case 9749:
		        case 9751:
		        case 9752:
		        case 9754:
		        case 9755:
		        case 9757:
		        case 9758:
		        case 9760:
		        case 9761:
		        case 9763:
		        case 9764:
		        case 9766:
		        case 9767:
		        case 9769:
		        case 9770:
		        case 9772:
		        case 9773:
		        case 9775:
		        case 9776:
		        case 9778:
		        case 9779:
		        case 9781:
		        case 9782:
		        case 9784:
		        case 9785:
		        case 9787:
		        case 9788:
		        case 9790:
		        case 9791:
		        case 9793:
		        case 9794:
		        case 9796:
		        case 9799:
		        case 9800:
		        case 9802:
		        case 9803:
		        case 9805:
		        case 9806:
		        case 9808:
		        case 9809:
		        case 9811:
		        case 9812:
		        case 18509:
		        case 18510:
		        	item.getDefinitions().setValue(99000);
					break;
        }
		return price;
	}

	public void sendExamine(Player player, int slotId) {
		if (slotId >= getStoreSize()) {
			return;
		}
		Item item = slotId >= mainStock.length ? generalStock[slotId
		                                                      - mainStock.length] : mainStock[slotId];
		if (item == null) {
			return;
		}
		player.getPackets().sendGameMessage(ItemExamines.getExamine(item));
	}

	public void refreshShop() {
		for (Player player : viewingPlayers) {
			sendStore(player);
			player.getPackets().sendIComponentSettings(620, 25, 0,
					getStoreSize() * 6, 1150);
		}
	}

	public int getStoreSize() {
		return mainStock.length
				+ (generalStock != null ? generalStock.length : 0);
	}

	public void sendStore(Player player) {
		Item[] stock = new Item[mainStock.length
		                        + (generalStock != null ? generalStock.length : 0)];
		System.arraycopy(mainStock, 0, stock, 0, mainStock.length);
		if (generalStock != null) {
			System.arraycopy(generalStock, 0, stock, mainStock.length,
					generalStock.length);
		}
		player.getPackets().sendItems(MAIN_STOCK_ITEMS_KEY, stock);
	}

	public void sendSellStore(Player player, Item[] inventory) {
		Item[] stock = new Item[inventory.length + (generalStock != null ? generalStock.length : 0)];
		System.arraycopy(inventory, 0, stock, 0, inventory.length);
		if (generalStock != null) {
			System.arraycopy(generalStock, 0, stock, inventory.length, generalStock.length);
		}
		player.getPackets().sendItems(MAIN_STOCK_ITEMS_KEY, stock);
	}

	/**
	 * Checks if the player is buying an item or selling it.
	 * @param player The player
	 * @param slotId The slot id
	 * @param amount The amount
	 */
	public void handleShop(Player player, int slotId, int amount) {
		boolean isBuying = player.getTemporaryAttributtes().get("shop_buying") != null;
		if (isBuying) {
			buy(player, slotId, amount);
		} else {
			sell(player, slotId, amount);
		}
	}

	public Item[] getMainStock() {
		return mainStock;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(Player player, int amount) {
		this.amount = amount;
		player.getPackets().sendIComponentText(1265, 67, String.valueOf(amount)); //just update it here
	}
}