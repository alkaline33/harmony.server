package com.rs.game.player;

import com.discord.Discord;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.content.WildernessTasks;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.game.player.controlers.Wilderness;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

public class ClueScrolls {
	//chest id 2717
	public static int[] ScrollIds = {2677,2678,2679,2680,2681,2682,2683,2684,2685,2686,2687,2688,2689,2690,2691,2692,2693,2694,2695,2696,2697,2698};
	
	public static int[] RareItems = {29805, 1};
	
	public static enum Scrolls {
		Scroll1(new int[] {ScrollIds[0],1}, Maps.Map1),
		Scroll2(new int[] {ScrollIds[1],1}, Maps.Map2),
		Scroll3(new int[] {ScrollIds[2],1}, Maps.Map3),
		Scroll4(new int[] {ScrollIds[3],1}, Maps.Map4),
		Scroll5(new int[] {ScrollIds[4],1}, Maps.Map5),
		Scroll6(new int[] {ScrollIds[5],1}, Maps.Map6),
		Scroll7(new int[] {ScrollIds[6],1}, Maps.Map7),
		Scroll8(new int[] {ScrollIds[7],1}, Maps.Map8),
		Scroll9(new int[] {ScrollIds[8],1}, Maps.Map9),
		Scroll10(new int[] {ScrollIds[9],1}, Maps.Map10),
		Scroll11(new int[] {ScrollIds[10],1}, Maps.Map11),
		Scroll12(new int[] {ScrollIds[11],1}, Maps.Map12),
		Scroll13(new int[] {ScrollIds[12],1}, Maps.Map13),
		Scroll14(new int[] {ScrollIds[13],1}, Maps.Map14),
		Scroll15(new int[] {ScrollIds[14],1}, Maps.Map15),
		Scroll16(new int[] {ScrollIds[15],1}, ObjectMaps.Map1),
		Scroll17(new int[] {ScrollIds[16],1}, ObjectMaps.Map2),
		Scroll18(new int[] {ScrollIds[17],1}, Riddles.Riddle1),
		Scroll19(new int[] {ScrollIds[18],1}, Riddles.Riddle2),
		Scroll20(new int[] {ScrollIds[19],1}, Riddles.Riddle3),
		Scroll21(new int[] {ScrollIds[20],1}, Riddles.Riddle4),
		Scroll22(new int[] {ScrollIds[21],1}, Riddles.Riddle5);
		
		public static Maps getMap(int itemid){
			for (Scrolls scroll: Scrolls.values()){
				if (scroll.id == itemid){
					if (scroll.getHiding() == null) {
						continue;
					} else {
						return scroll.getHiding();
					}
					}
				}
			return null;
		}

		public static Riddles getRiddles(int itemid){
			for (Scrolls scroll: Scrolls.values()){
				if (scroll.id == itemid){
					if (scroll.getRiddle() == null) {
						continue;
					} else {
						return scroll.getRiddle();
					}
					}
				}
			return null;
		}

		public Riddles getRiddle(){
			return riddle;
		}
		
		public static ObjectMaps getObjMap(int itemid){
			for (Scrolls scroll: Scrolls.values()){
				if (scroll.id == itemid){
					if (scroll.getLocationMap() == null) {
						continue;
					} else {
						return scroll.getLocationMap();
					}
				}
			}
			return null;
		}

		public ObjectMaps getLocationMap(){
			return locationmap;
		}
		
		public Maps getHiding(){
			return hiding;
		}
		int[] infos;
		Maps hiding;
		int id, level;
		ObjectMaps locationmap;
		Riddles riddle;
		private Scrolls(int[] info, Riddles riddle){
			infos = info;
			this.riddle = riddle;
			id = info[0];
			level = info[1];
		}
		private Scrolls(int[] info, ObjectMaps map){
			infos = info;
			locationmap = map;
			id = info[0];
			level = info[1];
		}
		private Scrolls(int[] info, Maps hiden){
		infos = info;
		id = info[0];
		level = info[1];
		hiding = hiden;
		}
	}
	private enum Riddles {
		Riddle1(22, new int[] {2967,4386,2970,4380}, new String[] {"There once was a villan","of grey and white", "he also had a bit of bage","do a clap outside his cave","to scare him off","","",""}),//Corp
		Riddle2(15, new int[] {3190,9828,3193,9825}, new String[] {"I am a token of the greatest love", "I have no beginning or end","Go to the place where money is lent","Jig by the gate to be my friend!","","","",""}),//Varrock Bank Basement
		Riddle3(28, new int[] {3162,3255,3171,3244}, new String[] {"For the reward you seek", "a city of lumber and bridge", "is west of a place that you", "must go to get some ham", "once outside do a lean"," to meat Mr. Mean!","",""}),//Ham Entrance
		Riddle4(14, new int[] {2987,3123,3001,3109}, new String[] {"Near a ring known to teleport","On a point full of mud", "A simple emote is needed", "An emote known as skipping or dance!","","","",""}),
		Riddle5(30, new int[] {2884,3449,2898,3438}, new String[] {"This reward will require a bit","For the first thing you will","Need to be at a den","and you have to be a rouge","You must have an idea outside","Of its entrance to get a reward!","",""});//Mudsckipper Point
		int[] locations;
		String[] riddles;
		int emoteid;
		private Riddles(int id, int[] location, String[] riddles){
			locations = location;
			this.riddles = riddles;
			emoteid = id;
		}
		//Riddle interface 345
	}
	private enum ObjectMaps {
		Map1(358, new int[] {18506, 2457, 3182}, "Near an observatory meant for getting a compas on RS!"),
		Map2(361, new int[] {46331, 2565, 3248}, "Just south of a city known for thieving and outside a tower of clock!");
		
		
		
		
		
		
		int objectid, objectx, objecty;
		int[] objectinfo;
		String hint;
		int interid;
		private ObjectMaps(int interid, int[] object, String text){
			hint = text;
			this.interid = interid;
			objectinfo = object;
			objectid = object[0];
			objectx = object[1];
			objecty = object[2];
		}
	}
	private enum Maps {
		Map1(337,2971,3414,"If you Fala by A Door you might need help on this one!"),
		Map2(338,3021,3912,"Inbeetween a lava blaze and a near Deathly Agility Course!"),
		Map3(339,2722,3339,"South of where legneds may be placed, and east of great thieving!"),
		Map4(341,3435,3265,"South of a muchky mucky mucky mucky swamp lands, and barely north of Haunted Mines!"),
		Map5(344,2665,3561,"West of a murderous Mansion, and south of a city of vikings!"),
		Map6(346,3166,3359,"Slightly South of a city of great knights and lots of Shops!"),
		Map7(347,3290,3372,"A mining place located near a city of great knights and lots of Shops"),
		Map8(348,3092,3225,"Slightly south of a village known for thieving masters of farming!"),
		Map9(351,3043,3398,"NorthEast Corner of a city based around a castle with a mort around it!"),
		Map10(352,2906,3295,"Rite next to a guild known for people with skilled hands! [CRAFTING]"),
		Map11(353,2616,3077,"In a city that Rhymes with tan i will, if you say it really fast!"),
		Map12(354,2612,3482,"West of some woods that sound like Mc Jagger!"),
		Map13(356,3110,3152,"South of a tower full of magical people!"),
		Map14(360,2652,3232,"North of a tower known to give life and south of a city that contains thieving!"),
		Map15(362,2923,3210,"West of the place best known for starting a house!");
		
		String chat;
		int interfaceId,xcoord,ycoord;
		private Maps(int interid, int x, int y,String hint){
			interfaceId = interid;
			xcoord = x;
			ycoord = y;
			chat = hint;
		}
	}

	public static Scrolls hasClue(Player p){
		for (Scrolls scroll: Scrolls.values()){
						if (p.getInventory().containsOneItem(scroll.id)){
							return scroll;
						}
			}
		return null;
	}
	public static ObjectMaps hasObjectMapClue(Player p, int scrollid){
		for (Scrolls scroll: Scrolls.values()){
				if (scroll.getLocationMap() == null) {
					continue;
				} else {
						if (scroll.id == scrollid){
						if (p.getInventory().containsOneItem(scroll.id)){
							return scroll.getLocationMap();
						}
						}
					}
			}
		return null;
	}
	public static Maps hasMapClue(Player p, int scrollid){
		for (Scrolls scroll: Scrolls.values()){
				if (scroll.getHiding() == null) {
					continue;
				} else {
						if (scroll.id == scrollid){
						if (p.getInventory().containsOneItem(scroll.id)){
							return scroll.getHiding();
						}
						}
					}
			}
		return null;
	}
	public static Riddles hasRiddleClue(Player p, int scrollid){
		for (Scrolls scroll: Scrolls.values()){
				if (scroll.getRiddle() == null) {
					continue;
				} else {
						if (scroll.id == scrollid){
						if (p.getInventory().containsOneItem(scroll.id)){
							return scroll.getRiddle();
						}
						}
					}
			}
		return null;
	}
	public static boolean completedRiddle(Player p, int emoteid){
		Scrolls scroll = hasClue(p);
		if (scroll != null){
		if (hasRiddleClue(p, scroll.id) != null){
			Riddles riddleclue = hasRiddleClue(p, scroll.id);
			WorldTile lastloc = p.getLastWorldTile();
			if (lastloc.getX() >= riddleclue.locations[0] && lastloc.getY() <= riddleclue.locations[1] && lastloc.getX() <= riddleclue.locations[2] && lastloc.getY() >= riddleclue.locations[3]){
				if (emoteid == riddleclue.emoteid){
					p.getPackets().sendGameMessage("You have successfully completed the riddle and have been awarded a chest!");
					p.getInventory().deleteItem(scroll.id, 1);
					p.getInventory().addItem(2717, 1);
				}
			}
		}
		}
		return false;
	}

	public static boolean objectSpot(Player p, WorldObject obj){
		Scrolls scroll = hasClue(p);
		if (scroll != null){
		if (hasObjectMapClue(p, scroll.id) != null){
			ObjectMaps mapclue = hasObjectMapClue(p, scroll.id);
			WorldTile lastloc = p.getLastWorldTile();
			if (obj.getX() == mapclue.objectx && obj.getY() == mapclue.objecty && obj.getId() == mapclue.objectid){
					p.getPackets().sendGameMessage("You have successfully completed the riddle and have been awarded a chest!");
					p.getInventory().deleteItem(scroll.id, 1);
					p.getInventory().addItem(2717, 1);
				}
			}
		}
		return false;
	}
	
	public static boolean digSpot(Player p){
		Scrolls scroll = hasClue(p);
		if (scroll != null){
		if (hasMapClue(p, scroll.id) != null){
			Maps mapclue = hasMapClue(p, scroll.id);
			WorldTile lastloc = p.getLastWorldTile();
			if (lastloc.getX() == mapclue.xcoord && lastloc.getY() == mapclue.ycoord){
					p.sendMessage("You have successfully completed the riddle and have been awarded a chest!");
					p.getInventory().deleteItem(scroll.id, 1);
					p.getInventory().addItem(2717, 1);
				}
			}
		}
		return false;
		
	}
	
	public static void showObjectMap(Player p, ObjectMaps objmap){
		p.getPackets().sendInterface(false, p.getInterfaceManager().hasRezizableScreen() ? 746:548, p.getInterfaceManager().hasRezizableScreen() ? 28:27, objmap.interid);
		p.sendMessage(objmap.hint);
		
	}
	
	public static void showRiddle(Player p, Riddles riddle){
		p.getPackets().sendInterface(false, p.getInterfaceManager().hasRezizableScreen() ? 746:548, p.getInterfaceManager().hasRezizableScreen() ? 28:27, 345);
		p.getPackets().sendIComponentText(345, 1, riddle.riddles[0]);
		p.getPackets().sendIComponentText(345, 2, riddle.riddles[1]);
		p.getPackets().sendIComponentText(345, 3, riddle.riddles[2]);
		p.getPackets().sendIComponentText(345, 4, riddle.riddles[3]);
		p.getPackets().sendIComponentText(345, 5, riddle.riddles[4]);
		p.getPackets().sendIComponentText(345, 6, riddle.riddles[5]);
		p.getPackets().sendIComponentText(345, 7, riddle.riddles[6]);
		p.getPackets().sendIComponentText(345, 8, riddle.riddles[7]);
	}
	
	public static void showMap(Player p, Maps map){
		p.getPackets().sendInterface(false, p.getInterfaceManager().hasRezizableScreen() ? 746:548, p.getInterfaceManager().hasRezizableScreen() ? 28:27, map.interfaceId);
		p.sendMessage(map.chat);
	}
	/*static Item[] EasyRewards = {new Item(88),new Item(74),new Item(995, 100000),new Item(995, 100000),new Item(7319),new Item(7321),new Item(7323),new Item(7325),new Item(7327),
		new Item(7332),new Item(7334),new Item(7336),new Item(7338),new Item(7340),new Item(7342),new Item(74),new Item(995, 100000),new Item(995, 100000),new Item(88),new Item(74),new Item(995, 100000),new Item(995, 100000),new Item(7344),
		new Item(7346),new Item(7348),new Item(7350),new Item(7352),new Item(7354),new Item(7356),new Item(74),new Item(995, 100000),new Item(995, 100000),new Item(74),new Item(995, 100000),new Item(995, 100000),new Item(7358),
		new Item(7360),new Item(7362),new Item(7364),new Item(7366),new Item(7368),new Item(7370),new Item(74),new Item(995, 100000),new Item(995, 100000),new Item(74),new Item(995, 100000),new Item(995, 100000),new Item(7372),
		new Item(7374),new Item(7376),new Item(7378),new Item(7380),new Item(7382),new Item(7384),new Item(74),new Item(995, 100000),new Item(995, 100000),new Item(74),new Item(995, 100000),new Item(995, 100000),new Item(7386),
		new Item(7388),new Item(7390),new Item(7392),new Item(7394),new Item(7396),new Item(7398),new Item(74),new Item(995, 100000),new Item(995, 100000),new Item(74),new Item(995, 100000),new Item(995, 100000),new Item(7399),
		new Item(7400),new Item(10286),new Item(10288),new Item(10290),new Item(10292),new Item(10294),new Item(74),new Item(995, 100000),new Item(995, 100000),new Item(74),new Item(995, 100000),new Item(995, 100000),new Item(10296),
		new Item(10298),new Item(10300),new Item(10302),new Item(10304),new Item(10306),new Item(10308),new Item(74),new Item(995, 100000),new Item(995, 100000),new Item(74),new Item(995, 100000),new Item(995, 100000),new Item(10310),
		new Item(10312),new Item(10314),new Item(10368),new Item(10370),new Item(10372),new Item(10374),new Item(74),new Item(995, 100000),new Item(995, 100000),new Item(74),new Item(995, 100000),new Item(995, 100000),new Item(10376),
		new Item(10378),new Item(10380),new Item(10382),new Item(10384),new Item(10386),new Item(10388),new Item(74),new Item(995, 100000),new Item(995, 100000),new Item(74),new Item(995, 100000),new Item(995, 100000),new Item(10390),
		new Item(10392),new Item(10394),new Item(10396),new Item(10398),new Item(10400)};
	static Item[] ThirdAge = {new Item(10330),new Item(10332),new Item(10334),new Item(10336),new Item(10338),new Item(10340),new Item(10342),new Item(10344),new Item(10346),new Item(10348),new Item(10350),new Item(10352)};
	*/
	static int[] EasyRewards = {995, 2583, 2585, 2587, 2589,
		2591, 2593, 2595, 2597, 2633, 2635, 2637,
		2631, 2583, 7390, 7392, 7386, 7388, 7394,
		7396, 10366, 10458, 10460, 10462, 10464, 10466,
		10468, 13095, 10748, 10750, 10752, 10756, 10402,
		10406, 10410, 10414, 10418, 10420, 10424, 10428, 10432,
		10436, 10422, 10426, 10430, 29017, 10434, 10438, 10392, 10396, 10394, 10354, 10364, 10366,
		626, 628, 630, 632, 634, 1837,29616, 1833, 1835, };

	static int[] MediumRewards = {995, 2599, 2601, 2603, 2605,
		2607, 2609, 2611, 2613, 2579, 7319,
		7321, 7323, 7325, 7327, 10364, 10452, 10454,
		10456, 19380, 19382, 19390, 19384, 19386,29040,29038, 19388, 193890,
		13107, 13109, 13111, 13113, 29019,29020,29021,29022,29023, 13115, 13097, 12210,
		12213, 12216, 12219, 12222, 2643, 2639, 2641, 11280, 9005, 10354,
		2894, 2904, 2914, 2924,29616, 2934,};

	static int[] HardRewards = {995, 2615, 2617, 2619, 2621,
		2623, 2625, 2627, 2629, 2661, 2663, 2665,
		2667, 3479, 2669, 2671, 2673, 2675, 3480,
		2653, 2655, 2657, 2659, 3478, 3481, 3483,
		3485, 3486, 3488, 8950, 7398, 7399,
		7400,10470, 10472, 10474,29616, 19392, 19394, 19396,
		10440,
		10442, 10444, 19362, 19364, 19366, 19374, 19376,
		19378, 19368, 19370, 10368, 10370, 10372, 10374,
		10384, 10386, 10388, 10390, 10376, 10378, 10380,
		10382, 19281, 19284, 19387, 29015,29024, 29016, 13099, 13101, 9470,
		9471, 22215, 22216, 22217, 29018, 22218, 18744, 18745,
		18746, 19346, 19348, 19350, 19352, 19354, 19356,
		19358, 19360, 19333, 29805,};

	static int[] EliteRewards = {995, 19413,29033,29034,29032,29036, 19416, 19419, 19422, 19425, 19428,
		19431, 19434, 19437, 19398, 19401, 19404, 19407,
		19410, 19443, 19445, 19447, 19449, 19451, 19453,
		19455, 19457, 19459, 19461, 19463, 19465, 19362,
		19392, 19365, 19394, 19396, 19366, 19290, 19293,
		19296, 19299, 19302, 19305, 19143, 19146, 19149,
		10330, 10332, 10334, 10336, 10338, 10340,
		10342, 10344, 10346, 10348, 10350, 10352, 10362,
			2577, 2581, 3057, 3058, 3059, 29616, 3060, 3061, 29805 };
	
	static int[] MasterRewards = { 29033,29034,29032,29036,
			10330, 10332, 10334, 10336, 10338, 10340,
			10342, 10344, 10346, 10348, 10350, 10352,
				2577, 2581,29805 };
	
	public static void giveMasterReward(Player p){
		int random = 876;
		if (p.cluenoreward == 1){
			random += 876;
			p.cluenoreward += 1;
		} else if (p.cluenoreward == 2){
			random += 876;
			p.cluenoreward += 1;
		} else if (p.cluenoreward == 3){
			random += 876;
		}
		if (random > 875){
			int itemCount = 1;
			if (p.getInventory().getFreeSlots() < itemCount) {
				p.sendMessage("You don't have enough inventory space to do that.");
				return;
			}
			Item[] rewards = new Item[itemCount];
			for(int i=0; i < itemCount; i++) {
				int eliteReward = MasterRewards[Utils.getRandom(MasterRewards.length-1)];
				random = Utils.getRandom(99)+1;
				//if (random <= 2) {
					rewards[i] = new Item(eliteReward);
					if(eliteReward == 995) {
						rewards[i] = new Item(eliteReward, Utils.getRandom(1000000)+100000);
					}
					if(eliteReward == 29616) {
						rewards[i] = new Item(eliteReward, Utils.getRandom(100)+50);
					}
					if (eliteReward == 29805) {
						World.sendWorldMessage("<col=336666> "+p.getDisplayName()+" has obtained an Acid dye from a clue scroll.", false);
						Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has obtained a " + ItemDefinitions.getItemDefinitions(eliteReward).getName() + " from a clue scroll!");
					}
					if (ItemDefinitions.getItemDefinitions(eliteReward).getName().toLowerCase().contains("third-age")) {
						World.sendWorldMessage("<col=336666> "+p.getDisplayName()+" has obtained a "+ItemDefinitions.getItemDefinitions(eliteReward).getName()+" from a clue scroll.", false);
						Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has obtained a " + ItemDefinitions.getItemDefinitions(eliteReward).getName() + " from a clue scroll!");
					}
					if (ItemDefinitions.getItemDefinitions(eliteReward).getName().toLowerCase().contains("ornament kit")) {
						World.sendWorldMessage("<col=336666> "+p.getDisplayName()+" has obtained a "+ItemDefinitions.getItemDefinitions(eliteReward).getName()+" from a clue scroll.", false);
						Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has obtained a " + ItemDefinitions.getItemDefinitions(eliteReward).getName() + " from a clue scroll!");	
				}
			}
			p.getInventory().deleteItem(28921,1);
			for (Item item: rewards){
					p.getInventory().addItem(item);
					

			}			
			p.getInterfaceManager().sendInterface(364);
			p.getPackets().sendItems(141, rewards);
			SeasonEvent.HandleActivity(p, "Clue Scrolls", 0);
			p.cluenoreward = 0;
		} else {
			p.cluenoreward = 0;
			
		}
	}
	
	
	
	public static void giveReward(Player p){
		if (p.challengeid == 19 && p.challengeamount > 0 && Wilderness.isAtWild(p)) {
			WildernessTasks.UpdateChallenge(p);
		}
		int random = 876;
		if (p.cluenoreward == 1){
			random += 876;
			p.cluenoreward += 1;
		} else if (p.cluenoreward == 2){
			random += 876;
			p.cluenoreward += 1;
		} else if (p.cluenoreward == 3){
			random += 876;
		}
		if (random > 875){
			int extra = p.getInventory().contains(28932) ? 4 : 3;
			int itemCount = Utils.getRandom(3)+extra;
			if (p.getInventory().contains(28932))
				p.sendMessage(Colors.cyan+"Your clue scroll enhancer grants you an extra reward!");
			if (p.getInventory().getFreeSlots() < itemCount) {
				p.sendMessage("You don't have enough inventory space to do that.");
				return;
			}
			Item[] rewards = new Item[itemCount];
			for(int i=0; i < itemCount; i++) {
				int easyReward = EasyRewards[Utils.getRandom(EasyRewards.length-1)];
				int mediumReward = MediumRewards[Utils.getRandom(MediumRewards.length-1)];
				int hardReward = HardRewards[Utils.getRandom(HardRewards.length-1)];
				int eliteReward = EliteRewards[Utils.getRandom(EliteRewards.length-1)];
				random = Utils.getRandom(99)+1;
				if (random <= 2) {
					rewards[i] = new Item(eliteReward);
					if(eliteReward == 995) {
						rewards[i] = new Item(eliteReward, Utils.getRandom(1000000)+100000);
					}
					if(eliteReward == 29616) {
						rewards[i] = new Item(eliteReward, Utils.getRandom(100)+50);
					}
					if (eliteReward == 29805) {
						World.sendWorldMessage("<col=336666> "+p.getDisplayName()+" has obtained an Acid dye from a clue scroll.", false);
						Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has obtained a " + ItemDefinitions.getItemDefinitions(eliteReward).getName() + " from a clue scroll!");
					}
					if (ItemDefinitions.getItemDefinitions(eliteReward).getName().toLowerCase().contains("third-age")) {
						World.sendWorldMessage("<col=336666> "+p.getDisplayName()+" has obtained a "+ItemDefinitions.getItemDefinitions(eliteReward).getName()+" from a clue scroll.", false);
						Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has obtained a " + ItemDefinitions.getItemDefinitions(eliteReward).getName() + " from a clue scroll!");
					}
					if (ItemDefinitions.getItemDefinitions(eliteReward).getName().toLowerCase().contains("ornament kit")) {
						World.sendWorldMessage("<col=336666> "+p.getDisplayName()+" has obtained a "+ItemDefinitions.getItemDefinitions(eliteReward).getName()+" from a clue scroll.", false);
						Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has obtained a " + ItemDefinitions.getItemDefinitions(eliteReward).getName() + " from a clue scroll!");
					}
				} else if (random <= 10) {
					rewards[i] = new Item(hardReward);
					if(hardReward == 995) {
						rewards[i] = new Item(hardReward, Utils.getRandom(750000)+100000);
					}
					if(hardReward == 29616) {
						rewards[i] = new Item(hardReward, Utils.getRandom(50)+50);
					}
				} else if (random <= 65) {
					rewards[i] = new Item(mediumReward);
					if(mediumReward == 995) {
						rewards[i] = new Item(mediumReward, Utils.getRandom(500000)+100000);
					}
					if(mediumReward == 29616) {
						rewards[i] = new Item(mediumReward, Utils.getRandom(50)+50);
					}
				} else if (random <= 100) {
					rewards[i] = new Item(easyReward);
					if(easyReward == 995) {
						rewards[i] = new Item(easyReward, Utils.getRandom(250000)+100000);
					}
					if(easyReward == 29616) {
						rewards[i] = new Item(easyReward, Utils.getRandom(50)+50);
					}
					
				}
			}
			p.getInventory().deleteItem(2717,1);
			for (Item item: rewards){
					p.getInventory().addItem(item);
					

			}
			
			p.getInterfaceManager().sendInterface(364);
			p.getPackets().sendItems(141, rewards);
			//p.dailycluescrolls++;
			SeasonEvent.HandleActivity(p, "Clue Scrolls", 0);
			/*if (p.dailycluescrolls == 5) {
				p.sendMessage(Colors.lightGray + "<img=6>You have completed the Daily Money Maker: Complete 5 clue scrolls! Your rewards have been placed in your bank.");
				p.getBank().addItem(5022, 20, true);
				p.getBank().addItem(29426, 1, true);
			}*/
			p.cluescompleted ++;
			p.sendMessage("<col=ff0000>You have now completed "+p.cluescompleted+" clue scrolls!");
			if (p.cluescompleted == 500) {
				World.sendWorldMessage(Colors.red+""+p.getDisplayName()+" has just unlocked the Bloodhound pet!", false);
			}
			p.cluenoreward = 0;
		} else {
			//p.sendMessage("You found another clue scroll inside the casket!");
			//p.getInventory().deleteItem(2717,1);
			//p.getInventory().addItem(ScrollIds[Utils.getRandom(ScrollIds.length-1)], 1);
			//if (p.cluenoreward == 0){
			//	p.cluenoreward += 1;
			//}
			p.cluenoreward = 0;
			
		}
	}

}