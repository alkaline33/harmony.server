package com.rs.game.player;

import java.util.concurrent.ConcurrentHashMap;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.npc.Drop;
import com.rs.game.player.interfaces.RSInterface;
import com.rs.utils.Colors;
import com.rs.utils.NPCDrops;
import com.rs.utils.NoteTab;
import com.rs.utils.Utils;

public class InterfaceManager {

	public static final int FIXED_WINDOW_ID = 548;
	public static final int RESIZABLE_WINDOW_ID = 746;
	public static final int CHAT_BOX_TAB = 13;
	public static final int FIXED_SCREEN_TAB_ID = 27;
	public static final int RESIZABLE_SCREEN_TAB_ID = 28;
	public static final int FIXED_INV_TAB_ID = 166;
	public static final int RESIZABLE_INV_TAB_ID = 108;
	private Player player;
	private RSInterface rsInterface;

	private final ConcurrentHashMap<Integer, int[]> openedinterfaces = new ConcurrentHashMap<Integer, int[]>();

	private boolean resizableScreen;
	private int windowsPane;

	public InterfaceManager(Player player) {
		this.player = player;
	}

	public void sendTab(int tabId, int interfaceId) {
		player.getPackets().sendInterface(true,
				resizableScreen ? RESIZABLE_WINDOW_ID : FIXED_WINDOW_ID, tabId,
				interfaceId);
	}

	public void sendChatBoxInterface(int interfaceId) {
		player.getPackets().sendInterface(true, 752, CHAT_BOX_TAB, interfaceId);
	}

	public void closeChatBoxInterface() {
		player.getPackets().closeInterface(CHAT_BOX_TAB);
	}

	public void sendOverlay(int interfaceId, boolean fullScreen) {
		sendTab(resizableScreen ? fullScreen ? 1 : 11 : 0, interfaceId);
	}
	
	public void closeOverlay(boolean fullScreen) {
		player.getPackets().closeInterface(resizableScreen ? fullScreen ? 1 : 11 : 0);
	}
	
	public void sendInterface(int interfaceId) {
		player.getPackets()
				.sendInterface(
						false,
						resizableScreen ? RESIZABLE_WINDOW_ID : FIXED_WINDOW_ID,
						resizableScreen ? RESIZABLE_SCREEN_TAB_ID
								: FIXED_SCREEN_TAB_ID, interfaceId);
	}

	public void sendInventoryInterface(int childId) {
		player.getPackets().sendInterface(false,
				resizableScreen ? RESIZABLE_WINDOW_ID : FIXED_WINDOW_ID,
				resizableScreen ? RESIZABLE_INV_TAB_ID : FIXED_INV_TAB_ID,
				childId);
	}
	
	public void sendDropRates(int npcId) {
		NPCDefinitions defs = NPCDefinitions.getNPCDefinitions(npcId);
		Drop[] drops = NPCDrops.getDrops(npcId);
		if (drops == null) {
			player.sendMessage("This NPC has no Drops");
			return;
		}
		sendInterface(275);
		player.getPackets().sendIComponentText(275, 1, defs.name +" Drop Table");

		player.getPackets().sendIComponentText(275, 10, "Drop            Amount            Rate</shad></col>");
		for (int i = 0;i<drops.length;i++) {
			player.getPackets().sendIComponentText(275, 11 + i, !drops[i].oneAmount() ? "<col=000000>" +
																ItemDefinitions.getItemDefinitions(drops[i].getItemId()).getName() + "           " +
																drops[i].getFormattedMinAmount() + "-" + drops[i].getFormattedMaxAmount() + "        " + drops[i].getFormattedRate() + "</col>" :
																"<col=000000>" +ItemDefinitions.getItemDefinitions(drops[i].getItemId()).getName() + "           " +
																drops[i].getFormattedMaxAmount() + "        " + drops[i].getFormattedRate() + "</col>");
		}
																								
		for (int i = 11 + drops.length; i < 300; i++) {
			player.getPackets().sendIComponentText(275, i, "");
		}
	}
	
	public void sendItemDrops(ItemDefinitions defs) {
		int i = 0;
		String dropEntry = "";
		player.getInterfaceManager().sendInterface(275);
		player.getPackets().sendIComponentText(275, 1, "Drops: <col=9900FF><shad=000000>" + defs.name + "</col></shad>");
		for (i = 10; i < 310; i++) {
			player.getPackets().sendIComponentText(275, i, "");
		}
		i = 0;
		for (int n = 0; n < Utils.getNPCDefinitionsSize(); n++) {
//			if (n == 0)
//			continue;
		//	System.out.println(n);
			NPCDefinitions def = NPCDefinitions.getNPCDefinitions(n);
			Drop[] drops = NPCDrops.getDrops(def.getId());
			if (drops != null) {
			for (Drop drop : drops) {
				if (drop.getItemId() == 0) {
					continue;
				}
				ItemDefinitions itemDefs = ItemDefinitions.getItemDefinitions(drop.getItemId());
				if (itemDefs.getId() != defs.getId()
						|| !itemDefs.name.contains(defs.name)
						|| !itemDefs.name.equalsIgnoreCase(defs.name)) {
					continue;
				}
				StringBuilder sb = new StringBuilder("").append(def.name)
						.append(": ").append(itemDefs.name)
						.append(drop.getMaxAmount() == 1 ?
								"" : drop.getMinAmount() == drop.getMaxAmount() ? " (" + drop.getMaxAmount() + ")" :
									" (" + drop.getMinAmount() + "-" + drop.getMaxAmount() + ")");
				dropEntry = sb.toString();
				if (i < 300) {
					player.getPackets().sendIComponentText(275, 10 + i, dropEntry);
				}
				dropEntry = "";
				i++;
				}
			}
		}
	}
	
	public void sendNPCDrops(NPCDefinitions defs) {
		int i = 0;
		String dropEntry = "";
		player.getInterfaceManager().sendInterface(275);
		player.getPackets().sendIComponentText(275, 1, "Drops: <col=9900FF><shad=000000>" + defs.name + "</col></shad>");
		for (i = 10; i < 310; i++) {
			player.getPackets().sendIComponentText(275, i, "");
		}
		i = 0;
		Drop[] drops = NPCDrops.getDrops(defs.getId());
		if (drops == null) {
			return;
		}
		if (drops != null) {
		for (Drop drop : drops) {
			if (drop.getItemId() == 0) {
				continue;
			}
			ItemDefinitions itemDefs = ItemDefinitions.getItemDefinitions(drop.getItemId());
			StringBuilder sb = new StringBuilder("").append(itemDefs.name)
					.append(drop.getMaxAmount() == 1 ?
							"" : drop.getMinAmount() == drop.getMaxAmount() ? " (" + drop.getMaxAmount() + ")" :
								" (" + drop.getMinAmount() + "-" + drop.getMaxAmount() + ")")
					.append("");
			dropEntry = sb.toString();
			if (i < 300) {
				player.getPackets().sendIComponentText(275, 10 + i, dropEntry);
			}
			dropEntry = "";
			i++;
			}
		}
	}
	
	public void sendTimerInterface(Player player) {
		player.getInterfaceManager().sendTab(player.getInterfaceManager().hasRezizableScreen() ? 10 : 8, 3000);
		player.getPackets().sendIComponentText(3000, 5, "");
	       player.getPackets().sendIComponentText(3000, 6, "");
		player.getPackets().sendIComponentText(3000, 7, "");
		player.getPackets().sendIComponentText(3000, 8, "");
		player.getPackets().sendIComponentText(3000, 9, "");
		}

	public final void sendInterfaces() {
		if (player.getDisplayMode() == 2 || player.getDisplayMode() == 3) {
			resizableScreen = true;
			sendFullScreenInterfaces();
		} else {
			resizableScreen = false;
			sendFixedInterfaces();
		}
		sendTimerInterface(player);
		player.getSkills().sendInterfaces();
		player.getCombatDefinitions().sendUnlockAttackStylesButtons();
		player.getMusicsManager().unlockMusicPlayer();
		player.getEmotesManager().unlockEmotesBook();
		player.getInventory().unlockInventoryOptions();
		player.getPrayer().unlockPrayerBookButtons();
		if (player.getFamiliar() != null && player.isRunning()) {
			player.getFamiliar().unlock();
		}
		player.getControlerManager().sendInterfaces();
	}
	


	
	public void doIngenuityInterface() {
		player.getInterfaceManager().sendOverlay(194, false);
		player.getPackets().sendIComponentText(194, 1, "Ingenuity");
		player.getPackets().sendIComponentText(194, 2, "Total Energy:");
		player.getPackets().sendIComponentText(194, 3, ""+Settings.ingenuityenergyadd()+"");
		player.getPackets().sendIComponentText(194, 4, "Total mining energy:");
		player.getPackets().sendIComponentText(194, 9, ""+Settings.Ingenuitymining+"/250");
		player.getPackets().sendIComponentText(194, 5, "Total woodcutting energy:");
		player.getPackets().sendIComponentText(194, 10, ""+Settings.Ingenuitychopping+"/250");
		player.getPackets().sendIComponentText(194, 6, "Total fishing energy:");
		player.getPackets().sendIComponentText(194, 11, ""+Settings.Ingenuityfishing+"/250");
		player.getPackets().sendIComponentText(194, 7, "Total firemaking energy:");
		player.getPackets().sendIComponentText(194, 12, ""+Settings.Ingenuityfirepiting+"/250");
		player.getPackets().sendIComponentText(194, 8, "Total Energy Consumed");
		player.getPackets().sendIComponentText(194, 13, ""+Settings.timesclickedtospawnsboss+"/250");
	}
	


	public void replaceRealChatBoxInterface(int interfaceId) {
		player.getPackets().sendInterface(true, 752, 11, interfaceId);
	}

	public void closeReplacedRealChatBoxInterface() {
		player.getPackets().closeInterface(752, 11);
	}

	public void sendWindowPane() {
		player.getPackets().sendWindowsPane(resizableScreen ? 746 : 548, 0);
	}
	
	public void sendFullScreenInterfaces() {
		player.getPackets().sendWindowsPane(746, 0);
		sendTab(21, 752);
		sendTab(22, 751);
		sendTab(15, 745);
		sendTab(25, 754);
		sendTab(195, 748);
		sendTab(196, 749);
		sendTab(197, 750);
		sendTab(198, 747);
		player.getPackets().sendInterface(true, 752, 9, 137);
		sendCombatStyles();
		sendTaskSystem();
		sendSkills();
		sendMsPortal(); //sendTab(114, 506);
		//sendLootboxOverlay();
		sendNotesTab();
		//sendDstore3();
		sendShopTab();
		sendDonationTab();
		sendNewStarterTab();
		sendDonatorsAnim();
		sendInventory();
		sendEquipment();
		//sendSquealOfFortune();
		sendPrayerBook();
		sendMagicBook();
		sendTab(120, 550); // friend list
		sendTab(121, 1109); // 551 ignore now friendchat
		sendTab(122, 1110); // 589 old clan chat now new clan chat
		sendSettings();
		sendEmotes();
		sendTab(125, 187); // music
		//sendTab(126, 930); // notes
		sendTab(129, 182); // logout*/
	}
	
	public void sendFixedInterfaces() {
		player.getPackets().sendWindowsPane(548, 0);
		sendTab(161, 752);
		sendTab(37, 751);
		sendTab(23, 745);
		sendTab(25, 754);
		sendTab(155, 747);
		sendTab(151, 748);
		sendTab(152, 749);
		sendTab(153, 750);
		player.getPackets().sendInterface(true, 752, 9, 137);
		sendMagicBook();
		sendPrayerBook();
		sendEquipment();
		//sendSquealOfFortune();
		sendInventory();
		sendMsPortal();
		//sendLootboxOverlay();
		sendNotesTab();
		sendTab(181, 1109);// 551 ignore now friendchat
		sendTab(182, 1110);// 589 old clan chat now new clan chat
		sendTab(180, 550);// friend list
		sendTab(185, 187);// music
		//sendTab(186, 930); // notes
		sendTab(189, 182);
		player.getPackets().sendGlobalConfig(823, 1);
		sendSkills();
		sendEmotes();
		sendSettings();
		sendTaskSystem();
		sendCombatStyles();
		sendDonationTab();
		sendNewStarterTab();
	}

	public void sendXPPopup() {
		sendTab(resizableScreen ? 38 : 10, 1213); //xp
	}
	
	public void sendXPDisplay() {
		sendXPDisplay(1215);  //xp counter
	}
	
	public void sendXPDisplay(int interfaceId) {
		sendTab(resizableScreen ? 27 : 29, interfaceId);  //xp counter
	}
	
	public void closeXPPopup() {
		player.getPackets().closeInterface(resizableScreen ? 38 : 10);
	}
	
	public void sendSquealOfFortune() {
		sendTab(resizableScreen ? 119 : 179, 1139);
         	player.getPackets().sendGlobalConfig(823, 1);
        }
	
	public void closeXPDisplay() {
		player.getPackets().closeInterface(resizableScreen ? 27 : 29);
	}
	
	public void sendEquipment() {
		sendTab(resizableScreen ? 116 : 176, 387);
	}
	
	public void sendSlayerTab() {
		player.getPackets().sendIComponentText(164, 22, "Buy");//bars
        player.getPackets().sendIComponentText(164, 20, ""+player.getSlayerPoints());//SLAY POINTS
        player.getPackets().sendIComponentText(161, 19, ""+player.getSlayerPoints()); //SLAY POINTS
        player.getPackets().sendIComponentText(163, 18, ""+player.getSlayerPoints()); //SLAY POINTS
        player.getPackets().sendIComponentText(163, 26, "Used to create level 90 jewelry.");
        player.getPackets().sendIComponentText(163, 23, "Hydrix Gem"); //buy ring or something
        player.getPackets().sendIComponentText(163, 25, "Does nothing at the moment.");
        player.getPackets().sendIComponentText(163, 29, "Not added.");
        player.getPackets().sendIComponentText(163, 30, "2000 points");
        player.getPackets().sendIComponentText(163, 31, "350 points.");
        player.getPackets().sendIComponentText(163, 22, "Not added"); //learn broad bolts
        player.getPackets().sendIComponentText(163, 27, "If equiped, receive a damage increase during a task.");
        player.getPackets().sendIComponentText(163, 24, "Buy Slayer Helmet"); //buy slay helm
        player.getPackets().sendIComponentText(164, 24, "Slayer Experience"); //next to slayer icon
        player.getPackets().sendIComponentText(164, 26, "Ring Of Wealth"); //next to ring icon
        player.getPackets().sendIComponentText(164, 28, "All God Capes"); //500x all runes
        player.getPackets().sendIComponentModel(164, 29, 2603);
        player.getPackets().sendIComponentModel(164, 30, 2603);
        player.getPackets().sendIComponentText(164, 37, "Enchanted Gem"); //500x all bolts
        player.getPackets().sendIComponentModel(164, 38, 2528);
        player.getPackets().sendIComponentText(164, 39, "Mystery Box"); //500x all arrows
        player.getPackets().sendIComponentModel(164, 40, 2426);
        player.getPackets().sendIComponentText(164, 32, "500 points"); // BUY XP POINTS
        player.getPackets().sendIComponentText(164, 33, "60 points"); // BUY RING PTS
        player.getPackets().sendIComponentText(164, 34, "1 point"); // BUY DART PTS
        player.getPackets().sendIComponentText(164, 35, "400 points"); // BUY BOLT PTS
        player.getPackets().sendIComponentText(164, 36, "250 points"); //BUY ARROW PTS
    		}
	public void sendMagicChest() {
		player.getPackets().sendIComponentModel(28, 43, -1);
		player.getPackets().sendIComponentModel(28, 40, -1);
		player.getPackets().sendIComponentModel(28, 36, -1);
		player.getPackets().sendIComponentModel(28, 33, -1);
		player.getPackets().sendIComponentModel(28, 30, -1);
		player.getPackets().sendIComponentModel(28, 27, -1);
		player.getPackets().sendIComponentModel(28, 24, -1);
		player.getPackets().sendIComponentModel(28, 21, -1);
		player.getPackets().sendIComponentText(28, 42, "");
		player.getPackets().sendIComponentText(28, 39, "");
		player.getPackets().sendIComponentText(28, 37, "");
		player.getPackets().sendIComponentText(28, 34, "");
		player.getPackets().sendIComponentText(28, 31, "");
		player.getPackets().sendIComponentText(28, 28, "");
		player.getPackets().sendIComponentText(28, 25, "");
		player.getPackets().sendIComponentText(28, 22, "");
		player.getPackets().sendIComponentText(28, 44, "");
		player.getPackets().sendIComponentText(28, 41, "");
		player.getPackets().sendIComponentText(28, 38, "");
		player.getPackets().sendIComponentText(28, 35, "");
		player.getPackets().sendIComponentText(28, 32, "");
		player.getPackets().sendIComponentText(28, 29, "");
		player.getPackets().sendIComponentText(28, 26, "");
		player.getPackets().sendIComponentText(28, 23, "");
	}
	public void sendDonationTab() {
		player.getPackets().sendIComponentText(1226, 2, "You're missing out!");
		player.getPackets().sendIComponentText(1226, 3, "Looks like you aren't a donator! Ever wanted to own your own armour set, have a chilled skilling zone or even bank while skilling? Now is your chance!");
		player.getPackets().sendIComponentText(1226, 4, "Support Harmony now!");
    		}
	public void sendNewStarterTab() {
		player.getPackets().sendIComponentText(685, 14, "Welcome to Harmony!");
		player.getPackets().sendIComponentText(685, 15, "Possible issues;");
		player.getPackets().sendIComponentText(685, 17, "You should make sure you're using OpenGL in the client settings as this can increase performance.");
		player.getPackets().sendIComponentText(685, 16, "<col=ff0000>WARNING: If your screen is black, don't panic! The cache is loading and can take some time depending on your internet speed.");
		player.getPackets().sendIComponentText(685, 18, "Advise;");
		player.getPackets().sendIComponentText(685, 19, "Open your console using the ` button which is under your esc key.");
		player.getPackets().sendIComponentText(685, 20, "Type Displayfps and it will show you the % of cache downloaded, once thats full, reload the client.");
		player.getPackets().sendIComponentText(685, 21, "The guide will offer you to choose your xp rates and account type. This can't be changed once chosen!");
		player.getPackets().sendIComponentText(685, 22, "<col33E3FF>If you have any issues at all, just fire a message in the friends chat.");
	}
	public void sendDstore3() {
				player.getPackets().sendIComponentText(1015, 31, "Donators Store 3");
				player.getPackets().sendIComponentText(1015, 107, "Temporary animations");
				player.getPackets().sendIComponentText(1015, 135, "These animations are temporary effects only, they will reset when you log out.");
				player.getPackets().sendIComponentText(1015, 136, "All purchases are done by ingame coins and are not refundable.");
				player.getPackets().sendIComponentText(1015, 11, "Coins in inventory");
				player.getPackets().sendIComponentText(1015, 146, "This is");
    		}
	public void sendDonatorsAnim() {
		player.getPackets().sendIComponentText(903, 19, "Donators Animations");
		player.getPackets().sendIComponentText(903, 74, "Woodcutting");
		player.getPackets().sendIComponentText(903, 75, "5M Coins");
		player.getPackets().sendIComponentText(903, 66, "Mining");
		player.getPackets().sendIComponentText(903, 67, "5M Coins");
		player.getPackets().sendIComponentText(903, 56, "Gnome Teleport");
		player.getPackets().sendIComponentText(903, 57, "10M Coins");
		player.getPackets().sendIComponentText(903, 48, "Reset Woodcut");
		player.getPackets().sendIComponentText(903, 49, "Free");
		player.getPackets().sendIComponentText(903, 40, "Reset Mining");
		player.getPackets().sendIComponentText(903, 41, "Free");
		player.getPackets().sendIComponentText(903, 32, "Reset Tele");
		player.getPackets().sendIComponentText(903, 33, "Free");
    		}

	public void sendShopTab() {
		player.getPackets().sendIComponentText(1312, 27, "Shops");
		player.getPackets().sendIComponentText(1312, 38, "Weaponry");
		player.getPackets().sendIComponentText(1312, 46, "Misc");
		player.getPackets().sendIComponentText(1312, 54, "Pure Store");
		player.getPackets().sendIComponentText(1312, 62, "Armoury");
		player.getPackets().sendIComponentText(1312, 70, "Runecrafting supplies");
		player.getPackets().sendIComponentText(1312, 78, "Crafting store");
		player.getPackets().sendIComponentText(1312, 86, "Ranged Armour");
		player.getPackets().sendIComponentText(1312, 94, "Ranged Weaponry");
		player.getPackets().sendIComponentText(1312, 102, "Magic Store");
	}
    	

	
	public void sendMsPortal() {
		sendTab(resizableScreen ? 114 : 174, 506);
		player.getPackets().sendIComponentText(506, 0,  "Harmony</col>");
		player.getPackets().sendIComponentText(506, 4, "Drop Viewer");
		player.getPackets().sendIComponentText(506, 6, "Quests");
		player.getPackets().sendIComponentText(506, 10, "World Events");
		player.getPackets().sendIComponentText(506, 8, "Perks");
		player.getPackets().sendIComponentText(506, 12, "Cosmetic Overrides");
		player.getPackets().sendIComponentText(506, 16, "Settings");
		player.getPackets().sendIComponentText(506, 2, Colors.green+"Teleports");
		player.getPackets().sendIComponentText(506, 18, "Titles");
		player.getPackets().sendIComponentText(506, 14, "Pets");
	}
	
	public void sendLootboxOverlay() {
		player.getInterfaceManager().sendOverlay(3023, false);
		player.getPackets().sendIComponentText(3023, 2, "Loot box<br> in: "+player.dailylootboxtimer+"<br> mins");
	}
	
	public void sendNotesTab() {
		sendTab(resizableScreen ? 126 : 186, 930);
		NoteTab.sendTab(player);
		}
	
	public void closeInterface(int one, int two) {
		player.getPackets().closeInterface(resizableScreen ? two : one);
	}

	public void closeEquipment() {
		player.getPackets().closeInterface(resizableScreen ? 116 : 176);
	}

	public void sendInventory() {
		sendTab(resizableScreen ? 115 : 175, Inventory.INVENTORY_INTERFACE);
	}

	public void closeInventory() {
		player.getPackets().closeInterface(resizableScreen ? 115 : 175);
	}
	
	public void closeSkills() {
		player.getPackets().closeInterface(resizableScreen ? 113 : 206);
	}
	
	public void closeCombatStyles() {
		player.getPackets().closeInterface(resizableScreen ? 111 : 204);
	}
	
	public void closeTaskSystem() {
		player.getPackets().closeInterface(resizableScreen ? 112 : 205);
	}
	
	public void sendCombatStyles() {
		sendTab(resizableScreen ? 111 : 171, 884);
	}
	
	public void sendTaskSystem() {
        sendTab(resizableScreen ? 112 : 172, 1019);
        player.getPackets().sendIComponentText(1019, 3,  "Player Tab");
        player.getPackets().sendIComponentText(1019, 16,  "Comp. Reqs");
        player.getPackets().sendIComponentText(1019, 18,  "Achievement Diary");
		player.getPackets().sendIComponentText(1019, 11, "Collection Log");
        player.getPackets().sendIComponentText(1019, 0,  "See the Completionist reqs!");
        player.getPackets().sendIComponentText(1019, 8,  "Check what achievements you need to do, for rewards! ");
    }

	public void sendSkills() {
		sendTab(resizableScreen ? 113 : 173, 320);
	}

	public void sendSettings() {
		sendSettings(261);
		
	}

	public void sendSettings(int interfaceId) {
		sendTab(resizableScreen ? 123 : 183, interfaceId);
		player.getPackets().sendIComponentText(261, 5, "Server Information");
	}

	public void sendPrayerBook() {
		sendTab(resizableScreen ? 117 : 177, 271);
	}
	
	public void closePrayerBook() {
		player.getPackets().closeInterface(resizableScreen ? 117 : 210);
	}


	public void sendMagicBook() {
		sendTab(resizableScreen ? 118 : 178, player.getCombatDefinitions()
				.getSpellBook());
	}
	
	public void closeMagicBook() {
		player.getPackets().closeInterface(resizableScreen ? 118 : 211);
	}
	
	public void sendEmotes() {
		sendTab(resizableScreen ? 124 : 184, 590);
	}
	
	public void closeEmotes() {
		player.getPackets().closeInterface(resizableScreen ? 124 : 217);
	}

	public boolean addInterface(int windowId, int tabId, int childId) {
		if (openedinterfaces.containsKey(tabId)) {
			player.getPackets().closeInterface(tabId);
		}
		openedinterfaces.put(tabId, new int[] { childId, windowId });
		return openedinterfaces.get(tabId)[0] == childId;
	}

	public boolean containsInterface(int tabId, int childId) {
		if (childId == windowsPane) {
			return true;
		}
		if (!openedinterfaces.containsKey(tabId)) {
			return false;
		}
		return openedinterfaces.get(tabId)[0] == childId;
	}

	public int getTabWindow(int tabId) {
		if (!openedinterfaces.containsKey(tabId)) {
			return FIXED_WINDOW_ID;
		}
		return openedinterfaces.get(tabId)[1];
	}

	public boolean containsInterface(int childId) {
		if (childId == windowsPane) {
			return true;
		}
		for (int[] value : openedinterfaces.values()) {
			if (value[0] == childId) {
				return true;
			}
		}
		return false;
	}

	public boolean containsTab(int tabId) {
		return openedinterfaces.containsKey(tabId);
	}

	public void removeAll() {
		openedinterfaces.clear();
	}

	public boolean containsScreenInter() {
		return containsTab(resizableScreen ? RESIZABLE_SCREEN_TAB_ID
				: FIXED_SCREEN_TAB_ID);
	}

	public void closeScreenInterface() {
		player.getPackets()
				.closeInterface(
						resizableScreen ? RESIZABLE_SCREEN_TAB_ID
								: FIXED_SCREEN_TAB_ID);
	}

	public boolean containsInventoryInter() {
		return containsTab(resizableScreen ? RESIZABLE_INV_TAB_ID
				: FIXED_INV_TAB_ID);
	}

	public void closeInventoryInterface() {
		player.getPackets().closeInterface(
				resizableScreen ? RESIZABLE_INV_TAB_ID : FIXED_INV_TAB_ID);
	}

	public boolean containsChatBoxInter() {
		return containsTab(CHAT_BOX_TAB);
	}

	public boolean removeTab(int tabId) {
		return openedinterfaces.remove(tabId) != null;
	}

	public boolean removeInterface(int tabId, int childId) {
		if (!openedinterfaces.containsKey(tabId)) {
			return false;
		}
		if (openedinterfaces.get(tabId)[0] != childId) {
			return false;
		}
		return openedinterfaces.remove(tabId) != null;
	}

	public void sendFadingInterface(int backgroundInterface) {
		if (hasRezizableScreen()) {
			player.getPackets().sendInterface(true, RESIZABLE_WINDOW_ID, 12,backgroundInterface);
		} else {
			player.getPackets().sendInterface(true, FIXED_WINDOW_ID, 11,backgroundInterface);
		}
	}
	
	public void closeFadingInterface() {
		if (hasRezizableScreen()) {
			player.getPackets().closeInterface(12);
		} else {
			player.getPackets().closeInterface(11);
		}
	}
	
	public void sendScreenInterface(int backgroundInterface, int interfaceId) {
		player.getInterfaceManager().closeScreenInterface();

		if (hasRezizableScreen()) {
			player.getPackets().sendInterface(false, RESIZABLE_WINDOW_ID, 40,
					backgroundInterface);
			player.getPackets().sendInterface(false, RESIZABLE_WINDOW_ID, 41,
					interfaceId);
		} else {
			player.getPackets().sendInterface(false, FIXED_WINDOW_ID, 200,
					backgroundInterface);
			player.getPackets().sendInterface(false, FIXED_WINDOW_ID, 201,
					interfaceId);
			
		}

		player.setCloseInterfacesEvent(new Runnable() {
			@Override
			public void run() {
				if (hasRezizableScreen()) {
					player.getPackets().closeInterface(40);
					player.getPackets().closeInterface(41);
				} else {
					player.getPackets().closeInterface(200);
					player.getPackets().closeInterface(201);
				}
			}
		});
	}

	public boolean hasRezizableScreen() {
		return resizableScreen;
	}

	public void setWindowsPane(int windowsPane) {
		this.windowsPane = windowsPane;
	}

	public int getWindowsPane() {
		return windowsPane;
	}
	
	public void gazeOrbOfOculus() {
		player.getPackets().sendWindowsPane(475, 0);
		player.getPackets().sendInterface(true, 475, 57, 751);
		player.getPackets().sendInterface(true, 475, 55, 752);
		player.setCloseInterfacesEvent(new Runnable() {

			@Override
			public void run() {
				player.getPackets().sendWindowsPane(player.getInterfaceManager().hasRezizableScreen() ? 746 : 548, 0);
				player.getPackets().sendResetCamera();
			}
			
		});
	}

	/*
	 * returns lastGameTab
	 */
	public int openGameTab(int tabId) {
		player.getPackets().sendGlobalConfig(168, tabId);
		int lastTab = 4; // tabId
		// tab = tabId;
		return lastTab;
	}

		public void closeInterfaceCustom() {
		player.getPackets().sendWindowsPane(
        		player.getInterfaceManager().hasRezizableScreen() ? 746
        		: 548, -1);
        		player.closeInterfaces();
	}

	public void sendInterface(RSInterface rsInterface) {
		if (rsInterface == null) {
			System.out.println("Null interface has attempted to open > ");
			return;
		}
		this.rsInterface = rsInterface;
		sendInterface(rsInterface.getId());
		rsInterface.open();
	}

	public void clearRSInterface() {
		rsInterface = null;
	}

	public RSInterface getRSInterface() {
		return rsInterface;
	}
	

}
