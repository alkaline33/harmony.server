package com.rs;

import java.math.BigInteger;

import com.rs.game.WorldTile;

public final class Settings {

	// private transient Player player;
	/**
	 * General client and server settings.
	 */
	public static final String SERVER_NAME = "Harmony";
	public static final int PORT_ID = 43595;
	public static final String LASTEST_UPDATE = "<col=7E2217>Check forums";
	public static String BOSS_SPOTLIGHT = "Vorago";
	public static String EVENT_SPOTLIGHT = "Double minigame rewards";
	public static final String CACHE_PATH = "data/cache/";
	public static final int RECEIVE_DATA_LIMIT = 7500;
	public static final int PACKET_SIZE_LIMIT = 7500;
	public static final int CLIENT_BUILD = 718;
	public static final int CUSTOM_CLIENT_BUILD = 934834238;


	/**
	 * Launching settings
	 */
	public static boolean DEBUG = true;
	public static boolean HOSTED;
	public static boolean ECONOMY;
	
	public static int DONATED_TO_WELL;
	public static boolean WELLDOUBLE = false;
	public static int WELLTIMER;
	
	public static boolean DOUBLEDROPS = false;
	
	public static boolean HWEEN = false;

	public static boolean TENPDROPS = false;
	public static int TENPDROPSTIME;
	public static int TENPDROPSTARGET = 25;////

	/**
	 * Season Event
	 */

	public static String SEASON_EVENT = "Donating & Voting";

	/**
	 * Raids osrs
	 */

	public static int ICEBOSSOPEN = 0;
	public static int VAMPYREBOSSOPEN = 0;
	public static int PITBOSSOPEN = 0;

	public static boolean ITEMFINDERNEWS;

	public static long totaldonatedtowell;

	public static boolean CALANOENTER;
	
	public static boolean GULEGAENTER;
	
	public static boolean TRIOCANENTER;
	public static boolean TRIORAIDTASKACTIVE;
	public static boolean TRIOBUSH;
	public static boolean TRIOVINE;
	public static boolean TRIOLEVER;
	public static boolean TRIONPCLOAD;
	
	public static boolean canteletopdemon = false;
	public static boolean voragospawn = false;

	public static int newplayers = 0;

	public static final String[] BOTS = { "Connor", "Asher", "Xio", "Skillaholic", "Froglist", "Gecko_V2", "Dr Woopy" };

	public static final String[] BETA_ACCESS = { "Froglist", "Connor", "Gecko_V2", "Cman_8_23_93", "Mtkm", "Joopz",
			"Mod_Connor" };

	public static final String DUNG_Q_A[][] = {{"What is the highest level Dungeoneering can go.","120"},};
	
	/**
	 * Statistics
	 */

	public static long GpSyncAmount;//

	/**
	 * dry vs suns
	 */

	public static int SunsKilled = 0;

	/**
	 * Ingenuity
	 */

	public static boolean canclickspawnboss = false;
	public static int timesclickedtospawnsboss = 0;

	public static boolean canskillmining = true;
	public static boolean canskillfishing = false;
	public static boolean canskillchopping = false;
	public static boolean canskillfirepit = false;

	public static int Ingenuitymining; // object - 4028
	public static int Ingenuityfishing; // npc - 2722
	public static int Ingenuitychopping; // object - 2023
	public static int Ingenuityfirepiting; // object - 29095 unlit - lit 29094

	/**
	 * spaaghetti shit
	 */

	public static int WaterAdded = 1;
	public static int skeletonhellhound = 0;

	/**
	 * ingame server info
	 */

	/**
	 * hunger games
	 */

	public static boolean hungergamesactive = false;

	public static int days, hours, minutes, seconds;
	public static long serverxp, serverxp10, serverxp100, serverxp1k, serverxp10k, serverxp100k, serverxp1m,
			serverxp10m, serverxp100m, serverxp1b;
	public static long goldenter;
	public static int levelups;
	public static int monsterskilled;
	public static int amountdonated;
	public static int eventdoublehits;
	public static int eventdropeventtokens;
	public static int eventdoublecaskets;
	public static int eventspotlightdouble;
	public static int eventdoubleskillingresources;
	public static int eventbosspetchance;
	public static int playerinwild;
	/**
	 * If the use of the managment server is enabled.
	 */
	public static boolean MANAGMENT_SERVER_ENABLED = true;

	/**
	 * Graphical User Interface settings
	 */
	public static final String GUI_SIGN = "Harmony";
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	/**
	 * Player settings
	 */
	public static final int START_PLAYER_HITPOINTS = 100;
	public static final WorldTile START_PLAYER_LOCATION = new WorldTile(2847, 3337, 0);
	public static final String START_CONTROLER = "null";
	public static final WorldTile RESPAWN_PLAYER_LOCATION = new WorldTile(2851, 3343, 0);
	public static final long MAX_PACKETS_DECODER_PING_DELAY = 30000; // 30seconds
	public static final int COMBAT_XP_RATE = 1;
	public static final int XP_RATE = 85;
	public static final int XP_RATE1 = 85;
	public static final int XP_RATE2 = 85;
	public static final int XP_RATE3 = 85; // 85 normal setting
	public static int DROP_RATE = 1;
	
	public static final boolean SQUEAL_OF_FORTUNE_ENABLED = true;
	public static final double[] SOF_CHANCES = new double[] { 1.0D, 0.35D, 0.0089D, 0.0058D };
	public static final int[] SOF_COMMON_CASH_AMOUNTS = new int[] { 100, 250, 500, 1000, 5000 };
	public static final int[] SOF_UNCOMMON_CASH_AMOUNTS = new int[] { 10000, 25000, 50000, 100000, 500000 };
	public static final int[] SOF_RARE_CASH_AMOUNTS = new int[] { 1000000, 2500000, 5000000, 10000000, 25000000 };
	public static final int[] SOF_JACKPOT_CASH_AMOUNTS = new int[] { 50 * 1000000, 50 * 1000000, 75 * 1000000, 100 * 1000000 };
	public static final int[] SOF_COMMON_LAMPS = new int[] { 23713, 23717, 23721, 23725, 23729, 23737, 23733, 23741, 23745, 23749, 23753, 23757, 23761, 23765, 23769, 23778, 23774, 23786, 23782, 23794, 23790, 23802, 23798, 23810, 23806, 23814 };
	public static final int[] SOF_UNCOMMON_LAMPS = new int[] { /*34030, 34031, 34032, 34033, 34034, 34035, 34036,*/ 23714, 23718, 23722, 23726, 23730, 23738, 23734, 23742, 23746, 23750, 23754, 23758, 23762, 23766, 23770, 23779, 23775, 23787, 23783, 23795, 23791, 23803, 23799, 23811, 23807, 23815 };
	public static final int[] SOF_RARE_LAMPS = new int[] { 23715, 23719, 23723, 23727, 23731, 23739, 23735, 23743, 23747, 23751, 23755, 23759, 23763, 23767, 23771, 23780, 23776, 23788, 23784, 23796, 23792, 23804, 23800, 23812, 23808, 23816 };
	public static final int[] SOF_JACKPOT_LAMPS = new int[] { 23716, 23720, 23724, 23728, 23732, 23740, 23736, 23744, 23748, 23752, 23756, 23760, 23764, 23768, 23773, 23781, 23777, 23789, 23785, 23797, 23793, 23805, 23801, 23813, 23809, 23817 };
	public static final int[] SOF_COMMON_OTHERS = new int[] { 2, 563, 886, 1511, 1205, 438, 4097, 556, 18830, /* 25758, 25755, */ 1127, 7394, 4587 };// todo
	public static final int[] SOF_UNCOMMON_OTHERS = new int[] { 10176, 24154, 24154, 24155, 24155, 1119, 1125, 1121, 1123, 1127, 1131, 1133, 6322, 1135, 12971, 4091, 1295, 1297, 1299, 1303, 1301, 1327, 1325, 1331, 1329, 1311, 1333, 1315, 1313, 1319, 1317, 1367, 1365, 1371, 1369, 1273, 1373, 1361, 1271, 1275, 843, 849, 1355, 1357, 9174, 9177, 853, 857, 9183, 9181, 9179 };
	public static final int[] SOF_RARE_OTHERS = new int[] { 29922,995, 995, 995, 995, 23665, 23666, 23667, 4151, 10491, 23668, 23690, 23692, 23695, 23698, 23688,23669, 23670, 23671, 23672, 23673, 23674, 23675, 23676, 23677, 23678, 23679, 23680, 23681, 23682, 23691, 23692, 23693, 23694, 23695, 23696, 23687, 23688, 23689, 23684, 23686, 23685, 23697, 23690, 23699, 23700, 23683, 23698 };
	public static final int[] SOF_JACKPOT_OTHERS = new int[] { 995, 995, 995, 995, 20929, 24437, 23671, 11286, 23672, 25202, 11732/*, 26568, 26569*/, 6859, 6861, 10836, 5608, 5608, 12681, 20929, 23674, 24433, /*26568, 26569 */};



	public static int getDropQuantityRate() {
		return 2;
	}
	/**
	 * Dryaxions minigames
	 */
	//public static int DRYAXIONS_STAGES;

	/**
	 * World settings
	 */
	public static final int WORLD_CYCLE_TIME = 600; // the speed of world in ms

	/**
	 * Music & Emote settings
	 */
	public static final int AIR_GUITAR_MUSICS_COUNT = 50;

	/**
	 * Quest settings
	 */
	public static final int QUESTS = 183;

	/**
	 * Memory settings
	 */
	public static final int PLAYERS_LIMIT = 2000;
	public static final int LOCAL_PLAYERS_LIMIT = 250;
	public static final int NPCS_LIMIT = Short.MAX_VALUE;
	public static final int LOCAL_NPCS_LIMIT = 250;
	public static final int MIN_FREE_MEM_ALLOWED = 30000000; // 30mb

	/**
	 * Game constants
	 */
	public static final int[] MAP_SIZES = { 104, 120, 136, 168, 72 };

	public static final String GRAB_SERVER_TOKEN = "hAJWGrsaETglRjuwxMwnlA/d5W6EgYWx";
	public static final int[] GRAB_SERVER_KEYS = {1441,78700,44880,39771,363186,44375,0,16140,7316
		,271148,810710,216189,379672,454149,933950,21006,25367,17247,1244,1,14856,1494,119,882901,1818764,3963,3618};
	
	
	//an exeption(grab server has his own keyset unlike rest of client)
	public static final BigInteger GRAB_SERVER_PRIVATE_EXPONENT = new BigInteger("95776340111155337321344029627634178888626101791582245228586750697996713454019354716577077577558156976177994479837760989691356438974879647293064177555518187567327659793331431421153203931914933858526857396428052266926507860603166705084302845740310178306001400777670591958466653637275131498866778592148380588481");
	public static final BigInteger GRAB_SERVER_MODULUS =  new BigInteger("119555331260995530494627322191654816613155476612603817103079689925995402263457895890829148093414135342420807287820032417458428763496565605970163936696811485500553506743979521465489801746973392901885588777462023165252483988431877411021816445058706597607453280166045122971960003629860919338852061972113876035333");
	
	public static final BigInteger PRIVATE_EXPONENT = new BigInteger("90587072701551327129007891668787349509347630408215045082807628285770049664232156776755654198505412956586289981306433146503308411067358680117206732091608088418458220580479081111360656446804397560752455367862620370537461050334224448167071367743407184852057833323917170323302797356352672118595769338616589092625");
	public static final BigInteger MODULUS = new BigInteger("102876637271116124732338500663639643113504464789339249490399312659674772039314875904176809267475033772367707882873773291786014475222178654932442254125731622781524413208523465520758537060408541610254619166907142593731337618490879831401461945679478046811438574041131738117063340726565226753787565780501845348613");
	public static Integer[] MBOX_ITEMS = { 11726, 11724, 21787, 11694, 11698, 11696, 11700, 1038, 1040, 1042, 1044, 1046, 1048, 1050, 1053, 1055, 1057, 29017, 20054, 23679, 23695, 23687, 23688, 11726, 11724, 21787, 11694, 11698, 11696, 11726, 11726, 11724, 21787, 11694, 11698, 11696, 11700, 1038, 1040, 1042, 1044, 1046, 1048, 1050, 1053, 1055, 1057, 29017, 20054, 23679, 23695, 23687, 23688, 11726, 11724, 21787, 11694, 11698, 11696, 11726 };

	public static int[] PETS_WITH_PERKS = { 36536, 29970, 29971, 29972, 30174, 30175, 30176, 30173, 29973, 12503, 30172, 31495, 29974, 30170, 30164,30166,30168,29992,29990,29989, 30157, 37640, 30156, 30155, 7412, 29119, 29918, 29977, 6702, 30091, 30090, 30089, 30094, 30093, 37296, 30154, 39025, 30153, 30152, 30150, 30148, 29211, 6908, 6909, 30146, 30147, 29978, 36537, 29979, 36547, 36558, 38671, 30217, 8491, 6908, 6909, 7844, 6900, 6901, 6902, 6903, 6904, 6905, 6906, 6907, 6942, 6943, 29999, 13637, 30007, 29980, 29995, 29994, 30136, 30017, 29991, 30020, 30032, 30033, 30003, 30005, 30006, 30029, 30004, 30001, 29998, 29997, 30086, 29996, 29987, 30018, 30019, 30023, 30077, 30084, 37639, 36883, 38519, 31964, 30139, 30140 };


	/**
	 * Donator settings
	 */
	public static String[] DONATOR_ITEMS = { "primal", "thok's",
			};

	public static String[] EXTREME_DONATOR_ITEMS = {};
	public static String[] LOST_CITY_ITEMS = { "Fire rune", "Water rune", "Air rune" };
	/**
	 * Item settings
	 */
	public static String[] EARNED_ITEMS = { "tokkul", "(class", "sacred clay",  "magic carpet" };

	/*
	 * public static String[] UNTRADABLES = { "(deg", "(class", "sacred clay",
	 * "null", "flaming skull"," overload (4) ", "extreme", "korasi", "chaotic",
	 * "dreadnip", "max hood", "max cape", "dungeoneering", "abyssal vine whip",
	 * "tokhaar-kal", "culinaromancer", "spin ticket", "tokkul", "fighter",
	 * "fire cape", "lamp", "completion", "arcane stream", "arcane pulse",
	 * "arcane blast", "magical blastbox", "cape (t)", "penance", "defender",
	 * "charm", "keenblade", "quickbow", "mindspike", "baby troll"};
	 */

	public static String[] RARE_DROPS = { "succubus", "icey", "frosty", "garg", "bandi", "corpie", "nexy", "kingy",
			"zammi", "sara", "arma", "feast", "sunny", "dryax", "drygore", "ancient upgrade", "troll potion",
			"dragonbone", "santa hat", "seismic", "signet", "ring imbuling", "vorago", "ascension", "zamorak", "hilt",
			"bandos", "armadyl", "saradomin", "torva", "pernix", "virtus", "zaryte", "arcane", "divine", "spectral",
			"elysian", "holy e", "visage", "steadfast", "ragefire", "glaiven", "dark", "dragon claw" };

	public static int[] DESTROY_BUT_TRADEABLE_ITEMS = { 33915,18768, 32283, 25345, 18689, 29677,10176 };

	public static int[] LEGENDARY_PETS = { 17788,17782};


	public static String[] REMOVING_ITEMS = { "your nan's nikes" };

	public static int[] NO_BANK_ITEMS = { 10024, 14463, 14469, 14470, 14471, 13305, 20730, 4286, 20731, 17401, 1485,
			13758, 14496, 20732 };


	public static String[] ALLOWED_COMMANDS = { "lostcity", "dz", "donatorzone", "resettrollname",
			"settrollname", "recanswer", "recquestion", "empty", "ticket", "lostcity", "score", "kdr", "players",
			"titles", "hidetips", "commands", "nomads", "chill", "lrc", "brim", "tavdung", "summoning", "jadinkos",
			"jadinko", "gano", "ganos", "simonztitle", "ashertitle", "lakstitle", "dungtitle", "godstitle",
			"bandostitle", "saradomintitle", "armadyltitle", "zamoraktitle", "defeatertitle", "maxtitle", "comptitle",
			"divinetitle", "jadtitle", "settylevelshaha", "cb", "cc", "cgg", "cg", "home", "vorago", "gwdkc",
			"runespan", "help", "checklogs", "votepoints", "checkmusic", "checkfire", "bgwdkc", "checkore",
			"checkbronze", "checklaps", "checktask", "dungtokens", "drypoints", "bank", "blueskin", "greenskin", "yell",
			"forum", "forums", "vote", "check", "claim", "reward", "donate", "lockxp", "hideyell", "changepass",
			"checkdonation", "answer", "switchitemslook" };

	public static boolean inApacheEmperorZone(WorldTile tile) {
		return tile.getX() >= 2830 && tile.getX() <= 2862 && tile.getY() >= 10042 && tile.getY() <= 10062;
	}

	public static int ingenuityenergyadd() {
		return Settings.Ingenuitychopping + Settings.Ingenuityfirepiting + Settings.Ingenuityfishing
				+ Settings.Ingenuitymining;
	}


	private Settings() {

	}
}
