package com.rs.game.player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import com.rs.Settings;
import com.rs.cores.CoresManager;
import com.rs.database.mysql.impl.Highscores;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.FloorItem;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.minigames.clanwars.FfaZone;
import com.rs.game.minigames.clanwars.WarControler;
import com.rs.game.minigames.duel.DuelArena;
import com.rs.game.minigames.duel.DuelRules;
import com.rs.game.minigames.rfd.RecipeforDisaster;
import com.rs.game.npc.NPC;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.npc.godwars.zaros.Nex;
import com.rs.game.npc.pet.Pet;
import com.rs.game.player.QuestManager.Quests;
//import com.rs.game.player.Toolbelt;
import com.rs.game.player.actions.PlayerCombat;
import com.rs.game.player.actions.dungeoneering.Dungeon;
import com.rs.game.player.bot.Bot;
import com.rs.game.player.content.AdventurersLog;
import com.rs.game.player.content.DailyChallenges;
import com.rs.game.player.content.DwarfCannon;
import com.rs.game.player.content.DwarvenRockCake;
import com.rs.game.player.content.Ectophial;
import com.rs.game.player.content.FairyRing;
import com.rs.game.player.content.FriendChatsManager;
import com.rs.game.player.content.HerbSack;
import com.rs.game.player.content.ItemConstants;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.MoneyPouch;
import com.rs.game.player.content.Notes;
import com.rs.game.player.content.PetPerk;
import com.rs.game.player.content.Pots;
import com.rs.game.player.content.RunePouch;
import com.rs.game.player.content.SafeDicing;
import com.rs.game.player.content.ShootingStar;
import com.rs.game.player.content.SkillCapeCustomizer;
import com.rs.game.player.content.SquealOfFortune;
//import com.rs.game.player.content.Tasksmanager;
import com.rs.game.player.content.WarriorsGuild;
import com.rs.game.player.content.bountyhunter.BountyHunter;
import com.rs.game.player.content.clans.ClansManager;
import com.rs.game.player.content.construction.House;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.game.player.content.pet.PetManager;
import com.rs.game.player.controlers.Barrows;
import com.rs.game.player.controlers.CorpBeastControler;
import com.rs.game.player.controlers.CrucibleControler;
import com.rs.game.player.controlers.DTControler;
import com.rs.game.player.controlers.Dryaxions;
import com.rs.game.player.controlers.FightCaves;
import com.rs.game.player.controlers.FightKiln;
import com.rs.game.player.controlers.GodWars;
import com.rs.game.player.controlers.HouseControler;
import com.rs.game.player.controlers.NomadsRequiem;
import com.rs.game.player.controlers.QueenBlackDragonController;
import com.rs.game.player.controlers.Wilderness;
import com.rs.game.player.controlers.ZGDControler;
import com.rs.game.player.controlers.castlewars.CastleWarsPlaying;
import com.rs.game.player.controlers.castlewars.CastleWarsWaiting;
import com.rs.game.player.controlers.fightpits.FightPitsArena;
import com.rs.game.player.controlers.pestcontrol.PestControlGame;
import com.rs.game.player.controlers.pestcontrol.PestControlLobby;
import com.rs.game.player.interfaces.StarterInterface;
import com.rs.game.player.interfaces.UpdateInterface;
import com.rs.game.player.interfaces.containers.AmigoDropLog;
import com.rs.game.player.interfaces.containers.AniviaDropLog;
import com.rs.game.player.interfaces.containers.AodDropLog;
import com.rs.game.player.interfaces.containers.AquaticWyrmDropLog;
import com.rs.game.player.interfaces.containers.ArchaeologistDropLog;
import com.rs.game.player.interfaces.containers.ArmadylDropLog;
import com.rs.game.player.interfaces.containers.BandosDropLog;
import com.rs.game.player.interfaces.containers.BlinkDropLog;
import com.rs.game.player.interfaces.containers.BloodDropLog;
import com.rs.game.player.interfaces.containers.BoxInterface1;
import com.rs.game.player.interfaces.containers.BoxInterface2;
import com.rs.game.player.interfaces.containers.BoxInterface3;
import com.rs.game.player.interfaces.containers.CallistoDropLog;
import com.rs.game.player.interfaces.containers.CelestiaDropLog;
import com.rs.game.player.interfaces.containers.CerberusDropLog;
import com.rs.game.player.interfaces.containers.ChaosFanaticDropLog;
import com.rs.game.player.interfaces.containers.CorpDropLog;
import com.rs.game.player.interfaces.containers.DMMContainer;
import com.rs.game.player.interfaces.containers.perkContainer;
import com.rs.game.player.interfaces.containers.DropLogContainer;
import com.rs.game.player.interfaces.containers.DryaxDropLog;
import com.rs.game.player.interfaces.containers.GazerDropLog;
import com.rs.game.player.interfaces.containers.GlacorDropLog;
import com.rs.game.player.interfaces.containers.HmWildyWyrmDropLog;
import com.rs.game.player.interfaces.containers.HopeDropLog;
import com.rs.game.player.interfaces.containers.HydraDropLog;
import com.rs.game.player.interfaces.containers.KBDDropLog;
import com.rs.game.player.interfaces.containers.KalphiteKingDropLog;
import com.rs.game.player.interfaces.containers.KrakenDropLog;
import com.rs.game.player.interfaces.containers.LizardmanDropLog;
import com.rs.game.player.interfaces.containers.LootingBag;
import com.rs.game.player.interfaces.containers.NecrolordDropLog;
import com.rs.game.player.interfaces.containers.NexDropLog;
import com.rs.game.player.interfaces.containers.OreBag;
import com.rs.game.player.interfaces.containers.PDDropLog;
import com.rs.game.player.interfaces.containers.RaptorDropLog;
import com.rs.game.player.interfaces.containers.Rot6DropLog;
import com.rs.game.player.interfaces.containers.RunePouchUpgraded;
import com.rs.game.player.interfaces.containers.SantaDropLog;
import com.rs.game.player.interfaces.containers.SaradominDropLog;
import com.rs.game.player.interfaces.containers.ScorpiaDropLog;
import com.rs.game.player.interfaces.containers.SireDropLog;
import com.rs.game.player.interfaces.containers.SirenicDropLog;
import com.rs.game.player.interfaces.containers.SliskeDropLog;
import com.rs.game.player.interfaces.containers.SmokeDevilDropLog;
import com.rs.game.player.interfaces.containers.SpareContainer;
import com.rs.game.player.interfaces.containers.SunfreetDropLog;
import com.rs.game.player.interfaces.containers.TdsDropLog;
import com.rs.game.player.interfaces.containers.ThunderousDropLog;
import com.rs.game.player.interfaces.containers.TolDropLog;
import com.rs.game.player.interfaces.containers.VenenatisDropLog;
import com.rs.game.player.interfaces.containers.VetionDropLog;
import com.rs.game.player.interfaces.containers.VoragoDropLog;
import com.rs.game.player.interfaces.containers.VorkathDropLog;
import com.rs.game.player.interfaces.containers.WildyWyrmDropLog;
import com.rs.game.player.interfaces.containers.XericDropLog;
import com.rs.game.player.interfaces.containers.ZamorakDropLog;
import com.rs.game.player.interfaces.containers.ZulrahDropLog;
import com.rs.game.route.RouteEvent;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
//import com.rs.database.mysql.impl.Card;
import com.rs.net.Session;
import com.rs.net.decoders.WorldPacketsDecoder;
import com.rs.net.decoders.handlers.ButtonHandler;
import com.rs.net.encoders.WorldPacketsEncoder;
import com.rs.utils.Colors;
import com.rs.utils.IsaacKeyPair;
import com.rs.utils.Logger;
import com.rs.utils.MachineInformation;
import com.rs.utils.PkRank;
import com.rs.utils.PrestigeHS;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.TopTriviaAnswers;
import com.rs.utils.TotalBossKills;
import com.rs.utils.Utils;
import com.rs.utils.WealthyPlayers;

public class Player extends Entity {

	public static final int TELE_MOVE_TYPE = 127, WALK_MOVE_TYPE = 1, RUN_MOVE_TYPE = 2;

	private String macAddress;//

	public String getMacAddress() {
		if (this instanceof Bot) {//
			return "BOT-MAC-ADDRESS";
		}
		return macAddress;
	}

	public void setMacAddress(String mac) {

		macAddress = mac;
	}

	/**
	 * NPC Dropssilverhaw
	 */

	private int dropSortingType = 0;

	public int getDropSortingType() {
		return dropSortingType;
	}

	public void setDropSortingType(int dropSortingType) {
		this.dropSortingType = dropSortingType;
	}

	private boolean toggleDrops;

	public boolean isToggleDrops() {
		return toggleDrops;
	}

	public void setToggleDrops(boolean toggleDrops) {
		this.toggleDrops = toggleDrops;
	}

	private ItemsContainer<Item> npcDrops = new ItemsContainer<Item>(31, false);

	public ItemsContainer<Item> getNpcDrops() {
		return npcDrops;
	}

	public void setNpcDrops(ItemsContainer<Item> npcDrops) {
		this.npcDrops = npcDrops;
	}

	/**
	 * POS
	 */

	private PlayerOwnedShop playerOwnedShop;

	public PlayerOwnedShop getPlayerOwnedShop() {
		return playerOwnedShop;
	}

	/**
	 * Checks if the player is AFK.
	 *
	 * @return if Player is AFK.
	 */

	public transient long afkTimer;

	public boolean isAFK() {
		if (this instanceof Bot) {
			return false;
		}
//		if (getRights() == 2) {
//			return false;
//		}
		return afkTimer <= Utils.currentTimeMillis();
	}

	/**
	 * Resets the AFK timer.
	 *///current afk is 50 mins
	public void increaseAFKTimer() {
		afkTimer = Utils.currentTimeMillis() + 25 * 60 * 200;
	}

	public int discordgenotifytimer;

	/**
	 * Perks
	 */
	public boolean alwaysAdze;
	public boolean alwaysAdzeActive;
	public boolean healingBlade;
	public boolean healingBladeActive;
	public boolean twofoldPerk;
	public boolean twofoldPerkActive;
	public boolean dropsBank;
	public boolean dropsBankActive;

	/**
	 * 
	 * lottery
	 */

	public Item getPrize() {
		return prize;
	}

	public void setPrize(Item price) {
		prize = price;
	}

	public boolean kuradalscrolldaily;

	/**
	 * Account Security
	 */

	private String AccountEmail;

	public String getAccountEmail() {
		return AccountEmail;
	}

	public void setAccountEmail(String email) {
		AccountEmail = email;
	}
	
	/**
	 * Daily loot box
	 */
	
	public int dailylootboxtimer = 120;

	/**
	 * Daily login perks
	 */

	public int dailyperkamount;
	public int timeloggedintoday;

	public int lastdateloggedin;

	private static final long serialVersionUID = 2011932556974180375L;

	// transient stuffsendDefaultPlayersOptions
	private House house;
	private SquealOfFortune squealOfFortune;
	private transient HouseControler houseControler;
	private transient DwarfCannon DwarfCannon;
	private transient String username;
	private transient Session session;
	private boolean finishedTask;
	private boolean lootshareEnabled;
	private transient boolean clientLoadedMapRegion;
	private transient int displayMode;
	private Item prize;
	private boolean[] taskType = new boolean[10];
	private boolean boostedtask;
	private transient int screenWidth;
	private transient Ectophial ectophial;
	private boolean inTask;
	private transient int screenHeight;
	private transient WarriorsGuild WarriorsGuild;
	private transient InterfaceManager interfaceManager;
	private transient MoneyPouch moneyPouch;
	private int taskAmount;
	private transient DialogueManager dialogueManager;
	private transient HintIconsManager hintIconsManager;
	private transient ActionManager actionManager;
	private transient CutscenesManager cutscenesManager;
	private transient PriceCheckManager priceCheckManager;
	private transient CoordsEvent coordsEvent;
	private transient RouteEvent routeEvent;
	private transient FriendChatsManager currentFriendChat;
	private transient Trade trade;
	private transient SafeDicing dice;
	private transient DuelRules lastDuelRules;
	private transient IsaacKeyPair isaacKeyPair;
	private transient Pet pet;
	private transient ShootingStar ShootingStar;
	private transient LoyaltyManager loyaltyManager;
	public transient PlayTime Playtime;
	// public transient DoubleXpTimer DxpTimer;
	private transient RandomSpins randomSpins;
	private transient DwarvenRockCake dwarvenrockCake;

	private RunePouch runePouch;
	private RunePouchUpgraded runePouchUpgraded;
	private HerbSack herbSack;
	private OreBag oreBag;
	private LootingBag lootingBag;
	private Grave grave;
	private DMMContainer dmmcontainer;
	private perkContainer perkcontainer;
	private BoxInterface1 boxinterface1;
	private BoxInterface2 boxinterface2;
	private BoxInterface3 boxinterface3;
	private SpareContainer sparecontainer;
	private DropLogContainer droplogcontainer;
	/**
	 * Drop log containers
	 */
	private AmigoDropLog amigodroplog;
	private CorpDropLog corpdroplog;
	private KBDDropLog kbddroplog;
	private TdsDropLog tdsdroplog;
	private NexDropLog nexdroplog;
	private GlacorDropLog glacordroplog;
	private Rot6DropLog rot6droplog;
	private BandosDropLog bandosdroplog;
	private HydraDropLog hydradroplog;
	private CelestiaDropLog celestiadroplog;
	private RaptorDropLog raptordroplog;
	private VorkathDropLog vorkathdroplog;
	private ZamorakDropLog zamorakdroplog;
	private ArmadylDropLog armadyldroplog;
	private SaradominDropLog saradomindroplog;
	private WildyWyrmDropLog wildywyrmdroplog;
	private HmWildyWyrmDropLog hmwildywyrmdroplog;
	private AquaticWyrmDropLog aquaticwyrmdroplog;
	private VoragoDropLog voragodroplog;
	private AodDropLog aoddroplog;
	private GazerDropLog gazerdroplog;
	private SantaDropLog santadroplog;
	private DryaxDropLog dryaxdroplog;
	private KalphiteKingDropLog kalphitekingdroplog;
	private HopeDropLog hopedroplog;
	private PDDropLog pddroplog;
	private ZulrahDropLog zulrahdroplog;
	private NecrolordDropLog necrolorddroplog;
	private ThunderousDropLog thunderousdroplog;
	private SunfreetDropLog sunfreetdroplog;
	private AniviaDropLog aniviadroplog;
	private SliskeDropLog sliskedroplog;
	private KrakenDropLog krakendroplog;
	private SireDropLog siredroplog;
	private CerberusDropLog cerberusdroplog;
	private SirenicDropLog sirenicdroplog;
	private CallistoDropLog callistodroplog;
	private VenenatisDropLog venenatisdroplog;
	private ChaosFanaticDropLog chaosfanaticdroplog;
	private ArchaeologistDropLog archaeologistdroplog;
	private ScorpiaDropLog scorpiadroplog;
	private VetionDropLog vetiondroplog;
	private XericDropLog xericdroplog;
	private BloodDropLog blooddroplog;
	private TolDropLog toldroplog;
	private LizardmanDropLog lizardmandroplog;
	private SmokeDevilDropLog smokedevildroplog;
	private BlinkDropLog blinkdroplog;

	private PetPerk petPerk;

	public boolean publicProfile = true;

	public void setRouteEvent(RouteEvent event) {
		routeEvent = event;
	}

	/**
	 * Heist
	 */
	public int heiststorepoints;
	public int heistgamesplayed;
	public int heistcashbagsdeposit;

	public boolean worthyComp;
	public boolean worthyTComp;

	private boolean spreset;

	public boolean gottencompamulet;

	public boolean cantonpc;

	public int ezonefish;
	
	public boolean CompletedAllQuests() {
		if (lostCity >= 1 && dwarvenpart >= 5 && ecobdpart >= 5 && completedruesaltar) {
			return true;
		}
		return false;
	}
	
	public int TotalRaidKills() {
		return osrsraidscompleted + theatreofbloodcompleted + TrioRaidKills + GulegaRaidKills;
	}
	
	public int TotalOSRSRaids() {
		return osrsraidscompleted + theatreofbloodcompleted;
	}

	public int BossKills() {
		return VoragoKills + BorkKills + the3amigosKills + SantaKills + AniviaKills + AngryEasterBunnyKills + CerberusKills + SliskeKills + GmoleKills + Demigodkills + CrazyArcKills + FanaticKills + VenenatisKills + SkotizoKills + AssassinKills + VetionKills + theatreofbloodcompleted + osrsraidscompleted + CallistoKills + ScorpiaKills + KrakenKills + RaptorKills + CelestiaKills + ZulrahKills + SireKills + KkingKills + BlinkKills + SunfreetKills + KbdKills + QbdKills + NexKills + KqKills + CorpKills + HydraBossKills + BandosKills + ArmadylKills + SaradominKills + ZamorakKills + Rot6Kills + VorkathKills + GlacorKills + AvaKills + WwyrmKills + GazerKills + Dboss + SirenicKills + ThunderousKills;
	}

	public int TotalKills() {
		return zkills + BossKills() + npckills + LizardmanKills + CorruptedBrotherKills;
	}

	public boolean FinalBossTitle(Player player) {
		if (player.getRights() >= 2) {
			return true;
		}
		if (player.BossKills() < 4000 || player.LizardmanKills < 50 || player.the3amigosKills < 50 || player.CelestiaKills < 50 || player.RaptorKills < 50 || player.GmoleKills < 50 || player.GazerKills < 50 || player.theatreofbloodcompleted < 50 || player.osrsraidscompleted < 50 || player.SkotizoKills < 50 || player.VetionKills < 50 || player.ScorpiaKills < 50 || player.CrazyArcKills < 50 || player.FanaticKills < 50 || player.VenenatisKills < 50 || player.CallistoKills < 50 || player.SirenicKills < 50 || player.CerberusKills < 50 || player.SireKills < 50 || player.KrakenKills < 50 || player.KqKills < 50 || player.SliskeKills < 50 || player.AniviaKills < 50 || player.SunfreetKills < 50 || player.ThunderousKills < 50 || player.NecroLordKills < 50 || player.ZulrahKills < 50 || player.DrygonKills < 50 || player.KkingKills < 50 || player.SantaKills < 50
				|| player.AvaKills < 50 || player.CorpKills < 50 || player.KbdKills < 50 || player.QbdKills < 50 || player.BorkKills < 50 || player.NexKills < 50 || player.GlacorKills < 50 || player.Rot6Kills < 50 || player.HydraBossKills < 50 || player.BandosKills < 50 || player.ArmadylKills < 50 || player.SaradominKills < 50 || player.ZamorakKills < 50 || player.WwyrmKills < 50 || player.AwyrmKills < 50 || player.Demigodkills < 50 || player.VoragoKills < 50) {
			return false;
		}
		return true;
	}

	public boolean InsaneFinalBossTitle(Player player) {
		if (player.getRights() >= 2) {
			return true;
		}
		if (player.BossKills() < 10000 || player.LizardmanKills < 100 || player.the3amigosKills < 100 || player.RaptorKills < 100 || player.CelestiaKills < 100 || player.GmoleKills < 100 || player.GazerKills < 100 || player.theatreofbloodcompleted < 100 || player.osrsraidscompleted < 100 || player.HydraBossKills < 100 || player.SkotizoKills < 100 || player.VetionKills < 100 || player.ScorpiaKills < 100 || player.CrazyArcKills < 100 || player.FanaticKills < 100 || player.VenenatisKills < 100 || player.CallistoKills < 100 || player.SirenicKills < 100 || player.CerberusKills < 100 || player.SireKills < 100 || player.KrakenKills < 100 || player.KqKills < 100 || player.SliskeKills < 100 || player.AniviaKills < 100 || player.SunfreetKills < 100 || player.ThunderousKills < 100 || player.NecroLordKills < 100 || player.ZulrahKills < 100 || player.PdemonKills < 100 || player.DrygonKills < 100
				|| player.KkingKills < 100
				|| player.SantaKills < 100 || player.AvaKills < 100 || player.CorpKills < 100 || player.KbdKills < 100 || player.QbdKills < 100 || player.BorkKills < 100 || player.NexKills < 100 || player.GlacorKills < 100 || player.Rot6Kills < 100 || player.BandosKills < 100 || player.ArmadylKills < 100 || player.SaradominKills < 100 || player.ZamorakKills < 100 || player.WwyrmKills < 100 || player.AwyrmKills < 100 || player.Demigodkills < 100 || player.VoragoKills < 100) {
			return false;
		}
		return true;
	}


	/*
	 * Player Wars
	 */
	public int playerwarspoints;
	public int pwamountcompleted;
	public int aniviaamuletcharges;

	/**
	 * Valentine
	 */
	public boolean cupidbowc;
	public boolean cupidwingsc;

	/**
	 * Blowpipe
	 */

	public int blowpipescales;
	public int blowpipedarts;

	/**
	 * keepsake
	 */
	public int keepsakecape;
	public int keepsakehat;
	public int keepsakebody;
	public int keepsakeshield;
	public int keepsakeweapon;
	public int keepsakelegs;
	public int keepsakegloves;
	public int keepsakeboots;
	public int keepsakeamulet;

	public boolean loggedin742e;
	public boolean loggedin742i;
	public boolean loggedin742b;

	/**
	 * The Dwarven Discovery
	 */
	public int dwarvenpart = 0;
	public boolean starteddwarvenquest;
	public boolean completeddwarvenquest;
	/**
	 * Economic Breakdown
	 */
	public int ecobdpart = 0;
	public boolean startedecobreakdownquest;
	public boolean completedecobreakdownquest;

	/**
	 * Polling
	 */
	public boolean hasvotedonpoll;

	/**
	 * Theatre of Blood
	 */

	public int theatreofbloodcompleted;
	// public int theatreofbloodpoints;
	public int theatreofblooddamagepoints;

	public boolean tobkilledboss1;
	public boolean tobkilledboss2;
	public boolean tobkilledboss3;

	/**
	 * osrs raids
	 */

	public int osrsraidscompleted;
	public int osrsraidspoints;
	public int osrsraiddamagepoints;

	public int getAllOSRSRaidsCompleted() {
		return osrsraidscompleted + theatreofbloodcompleted;
	}


	/**
	 * perks
	 */
	public boolean prayerperk;
	public boolean avaperk;
	public boolean ckeyperk;
	public boolean gwdperk;
	public boolean dungperk;
	public boolean petperk;
	public boolean farmingperk;
	public boolean thievperk;
	public boolean potionperk;
	public boolean fishingperk;
	public boolean miningperk;
	public boolean axeperk;
	public boolean bankspaceperk;
	public boolean nodegradeperk;
	public boolean playerwarsperk;
	public boolean cookingperk;
	public boolean antifireperk;
	public boolean runenergyperk;

	public boolean hustlerperk;
	public boolean superchargedperk;
	public boolean coincollectorperk;
	public boolean feelsfamiliarperk;
	// public boolean

	/**
	 * Start of Cosmetic overrides
	 */
	public boolean nomadc;
	public boolean darklordc;
	public boolean felinec;
	public boolean swashbucklerc;
	public boolean assassinc;
	public boolean skeletonc;
	public boolean guyfawkesc;
	public boolean goldenchefc;
	public boolean beachwearc;
	public boolean bunnymaskc;
	public boolean monarchc;
	public boolean noblec;
	public boolean servantc;
	public boolean ringmasterc;
	public boolean foxc;
	public boolean wolfc;
	public boolean pandac;
	public boolean woodlandcownc;
	public boolean colosseumc;
	public boolean cabaretc;
	public boolean glassesc;
	public boolean kalphitegreatc;
	public boolean dwarvenwarsuitc;
	public boolean tropicalislanderc;
	public boolean seasweedhairc;
	public boolean krilbattlegearc;
	public boolean krilgodcrusherc;
	public boolean ariane;
	public boolean tokhaarbrute;
	public boolean tokhaarveteranc;
	public boolean tokhaarwarlordc;
	public boolean kalphitesentinelc;
	public boolean kalphiteemissaryc;
	public boolean shadowcatc;
	public boolean shadowhunterc;
	public boolean shadowsentinelc;
	public boolean shadowdemonc;
	public boolean shadowknightc;
	public boolean greaterdemonfleshc;
	public boolean lesserdemonfleshc;
	public boolean guthixianwarrobesc;
	public boolean saradoministwarrobesc;
	public boolean zamorakianwarrobesc;
	public boolean zarosianwarrobesc;
	public boolean scarecrowmaskc;
	public boolean turkeyhatc;
	public boolean flamingskullc;
	public boolean skypouncerc; // UPTO HERE
	public boolean flameheartc;
	public boolean stoneheartc;
	public boolean stormheartc;
	public boolean iceheartc;
	public boolean colossusc;
	public boolean titanc;
	public boolean behemothc;
	public boolean beastc;
	public boolean crownofsupremacyc;
	public boolean crownoflegendsc;
	public boolean colonistc;
	public boolean highlandc;
	public boolean musketeerc;
	public boolean elvenc;
	public boolean werewolfc;
	public boolean oforderc;
	public boolean ofchaosc;
	public boolean aurorac;
	public boolean templarc;
	public boolean superheroc;
	public boolean dulcinc;
	public boolean ravenskullc;
	public boolean deathlessregentc;
	public boolean ancientmummyc;
	public boolean clownfacec;
	public boolean ogreinfiltratorc;
	public boolean drakewingc;
	public boolean xmas13c;
	public boolean gladtidingsc;
	public boolean ofstrengthc;
	public boolean ofjusticec;
	public boolean armadyldronec;
	public boolean blessedsentinelc;
	public boolean cursedreaverc;
	public boolean replicanexc;
	public boolean runefestc;
	public boolean clowncostumec;
	public boolean drnabaiksc;
	public boolean zaroshelmc;
	public boolean replicagwdc;
	public boolean hikerc;
	public boolean aviansieskyguardc;
	public boolean vyrewatchskyshadowc;
	public boolean vanquisherc;
	public boolean zarosianshadowc;
	public boolean zarosianpraetorc;
	public boolean elvenfighterscostumesc;
	public boolean attunednexc;
	public boolean attunedkbdc;
	public boolean snowmanc;
	public boolean samuraic;
	public boolean lavahoodc;
	public boolean outfitofomensc;
	public boolean warmwinterc;
	public boolean markofzarosc;
	public boolean freefallwingsc;
	public boolean gothicc;
	public boolean bunnytailc;
	public boolean squidtentaclecapec;
	public boolean ozansoutfitc;
	public boolean brassicacapec;
	public boolean marimbocapec;
	public boolean inaritailc;
	public boolean skeletaltailc;
	public boolean ehancedfcapec;
	public boolean runestreamc;
	public boolean skeletalwingsc;
	public boolean butterflywingsc;
	public boolean zamorakwingsc;
	public boolean icyenicwingsc;
	public boolean dragonflywingsc;
	public boolean armadylwingsc;
	public boolean crystallinewingsc;
	public boolean paradoxwingsc;
	public boolean dwarvenwingsc;
	public boolean bladewingsc;
	public boolean bloodbladewingsc;
	public boolean firemakersc;
	public boolean seabornec;
	public boolean royalcourtc;
	public boolean brutalc;
	public boolean owenpackc;
	public boolean shadowowenc;
	public boolean protopackc;
	public boolean pactbrakerc;
	public boolean dwarvenpackc;
	public boolean oftsutsarothc;
	public boolean firestormc;
	public boolean linzaspackc;
	public boolean shadowlinzaspackc;
	public boolean scorchingpackc;
	public boolean blazingpackc;
	public boolean edictspackc;
	public boolean gianthandc;
	public boolean retrogsc;
	public boolean owaric;
	public boolean nerfariousedgec;
	public boolean parasol2hc;
	public boolean valkyriepackc;
	public boolean shipwreckpackc;
	public boolean daggerfistpackc;
	public boolean clawpackc;
	public boolean whippackc;
	public boolean shockpackc;
	public boolean infernalpackc;
	public boolean burningtruthc;
	public boolean nefariousc;
	public boolean wandpackc;
	public boolean bowpackc;
	public boolean quickfirec;
	public boolean wildfirec;
	public boolean executaxec;
	public boolean goldscythec;
	public boolean kyzajc;
	public boolean icesickle;
	public boolean lightninghands;
	public boolean throwaxepackc;
	public boolean solariusc;
	public boolean shieldpackc;
	public boolean dfsc;
	public boolean demonfleshbookc;
	public boolean hatefulheartc;
	public boolean teddybearc;
	public boolean dharokc;
	public boolean ahrimc;
	public boolean zamorakwingssc;
	public boolean crystalwingsc;
	/**
	 * c End of overrides
	 */

	public int templeoflighttime;
	public int templeoflightcharges;
	public int pillaroflighttouched;
	public int templeoflightmummykills;

	public int ringofdeathcharges;

	/**
	 * 
	 * Professor idea
	 * 
	 */

	public int proresourcescollected;

	public boolean dailyresettaken;
	public int dailyresetis;
	public int dailyredstone;
	public boolean votedtoday;
	public int sheafeaten;

	//teleport interface
	public int getBossInter;
	/**
	 * 
	 * Settings tab
	 * 
	 */

	public boolean hptracker;
	public boolean displaydrops;
	public boolean pottimers = true;

	/**
	 * Presets
	 */

	int HelmID;
	int AuraID;
	int CapeID;
	int neckID;
	int ammoID;
	int SwordID;
	int shieldID;
	int chestID;
	int legsID;
	int glovesID;
	int bootsID;
	int ringID;

	public int getHelm() {
		return HelmID;
	}

	public void setHelm(int HelmID) {
		this.HelmID = HelmID;
	}

	public int getAura() {
		return AuraID;
	}

	public void setAura(int AuraID) {
		this.AuraID = AuraID;
	}

	public int getCape() {
		return CapeID;
	}

	public void setCape(int CapeID) {
		this.CapeID = CapeID;
	}

	public int getNeck() {
		return neckID;
	}

	public void setNeck(int neckID) {
		this.neckID = neckID;
	}

	public int getAmmo() {
		return ammoID;
	}

	public void setAmmo(int ammoID) {
		this.ammoID = ammoID;
	}

	public int getSword() {
		return SwordID;
	}

	public void setSword(int SwordID) {
		this.SwordID = SwordID;
	}

	public int getShield() {
		return shieldID;
	}

	public void setShield(int shieldID) {
		this.shieldID = shieldID;
	}

	public int getChest() {
		return chestID;
	}

	public void setChest(int chestID) {
		this.chestID = chestID;
	}

	public int getLegs() {
		return legsID;
	}

	public void setLegs(int legsID) {
		this.legsID = legsID;
	}

	public int getGloves() {
		return glovesID;
	}

	public void setGloves(int glovesID) {
		this.glovesID = glovesID;
	}

	public int getBoots() {
		return bootsID;
	}

	public void setBoots(int bootsID) {
		this.bootsID = bootsID;
	}

	public int getRing() {
		return ringID;
	}

	public void setRing(int ringID) {
		this.ringID = ringID;
	}

	public static void dig(final Player player) {
		player.resetWalkSteps();
		player.setNextAnimation(new Animation(830));
		player.lock();
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.unlock();
				if (Barrows.digIntoGrave(player)) {
					return;
				}
				if (player.getX() == 3005 && player.getY() == 3376 || player.getX() == 2999 && player.getY() == 3375 || player.getX() == 2996 && player.getY() == 3377 || player.getX() == 2989 && player.getY() == 3378 || player.getX() == 2987 && player.getY() == 3387 || player.getX() == 2984 && player.getY() == 3387) {
					// mole
					player.setNextWorldTile(new WorldTile(1752, 5137, 0));
					player.getPackets().sendGameMessage("You seem to have dropped down into a network of mole tunnels.");
					return;
				}
				if (ClueScrolls.digSpot(player)) {
					return;

					// player.getPackets().sendGameMessage("You find nothing.");
				}
			}
		});

	}

	public int amountvotedthismonth4;

	/**
	 * The Calamity
	 */
	public int calamityrewardpoints;
	public int calamitykillpoints;
	public int calamitybestwave;
	public int calamitytotalwavescomplete;
	public boolean canmakeoverpray;
	public boolean canmakesupersarabrew;
	public boolean canmakefuryshark;
	public boolean doublekillpointscalamity;
	public boolean satantitle;
	public boolean undeadtitle;
	public boolean osteologytitle;
	public boolean warriorcalatitle;
	public boolean warmonger;
	public boolean thunderous;
	public boolean lcommander;
	public boolean sunkiller;

	public List<Integer> foundNpcs;
	public int npcPage = 0;
	public boolean defenderofdry;
	public boolean defenderofslisketitle;
	
	
	/**
	 * Recycle centre
	 */

	public long runecoinsobtained;

	/**
	 * 
	 * Trio Boss Raid
	 * 
	 */

	public int raidedtimestoday;
	public int raidsfee = 1;

	/*
	 * 
	 * Skilling Task System
	 * 
	 */

	// public int hadauththislogin;
	public int authclaimed;

	/*
	 * Objectives
	 */

	public boolean WoodcuttingTask;
	public boolean wctyews;
	public boolean wctmagic;
	public boolean wctmaples;
	public boolean FishingTask;
	public boolean ftrocktail;
	public boolean ftshark;
	public boolean ftlobster;
	public boolean MiningTask;
	public boolean mtrune;
	public boolean mtaddy;
	public boolean mtmith;
	public boolean CraftingTask;
	public boolean ctdragonstone;
	public boolean ctdiamond;
	public boolean ctonyx;
	public boolean AgilityTask;
	public boolean atbarb;
	public boolean atgnome;
	public boolean CookingTask;
	public boolean ctshark;
	public boolean ctrocktail;
	public boolean ctlobster;

	public int WoodcuttingTaskMagic = 0;
	public int WoodcuttingTaskYew = 0;
	public int WoodcuttingTaskMaple = 0;
	public int FishingTaskRocktail = 0;
	public int FishingTaskShark = 0;
	public int FishingTaskLobster = 0;
	public int CraftingTaskOnyx = 0;
	public int CraftingTaskDragonstone = 0;
	public int CraftingTaskDiamond = 0;
	public int MiningTaskRune = 0;
	public int MiningTaskAddy = 0;
	public int MiningTaskMith = 0;
	public int AgilityTaskBarb = 0;
	public int AgilityTaskGnome = 0;
	public int CookingTaskRocktail = 0;
	public int CookingTaskShark = 0;
	public int CookingTaskLobster = 0;

	/**
	 * Rewards
	 */

	public int SkillTaskPoints;

	/**
	 * Counter
	 */

	public int SkillingTasksComplete;

	public boolean seensunfreetendcutscene;

	public boolean dcapedart = false;

	public long totalDonated;

	public int DrygonCommendations;

	/**
	 * 
	 * H'ween
	 * 
	 * 
	 */

	public boolean startedhettyhween;
	public boolean spokentohetty = false;
	public boolean canspeaktohettyagain;
	public boolean canspeaktohettyagain1;
	public boolean canfindbroomitems;
	public boolean hasfinishedtalkingtohetty;
	
	/**
	 * Easter
	 */
	
	public boolean spokentobunny;
	public boolean finishedeasterevent;
	public boolean easter2019title;

	/**
	 * INVENTOR
	 */

	public int inventorxp;
	public int inventorlevel;

	// lootbeams
	public int setLootBeam = 1000000;
	public boolean LootBeam2;
	public boolean LootBeam3;
	public boolean LootBeam4;
	public int LooTbeam = 0;
	/**
	 * Suggestions
	 */

	public static String suggestions;

	/**
	 * End of suggestions
	 */

	/**
	 * Bar bag
	 */

	public int bbar;
	public int sbar;
	public int ibar;
	public int mbar;
	public int abar;
	public int rbar;
	public int totalbagbars;

	public short coalbagamount;

	public int maxBotWave, botKillStreak, Botkills;
	public int botPoints;

	private transient Dungeon dungeon;

	public Dungeon getDungeon() {
		return dungeon;
	}

	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

	/**
	 * Dialogue safety
	 */

	public int scientisthasegg;

	/**
	 * Easter Event
	 */
	public boolean startedEasterEvent = false;
	public boolean inProgressEasterEvent = false;
	public boolean completedEasterEvent = false;

	public int EvilTreeDamage = 405;

	public boolean premium;

	public boolean superclaimedtoday;

	public int d60mxp1;
	public boolean ddxp;
	public boolean snugspawn;

	public boolean all104;

	// other
	public int silverhawks;
	public int cluescompleted;

	public boolean dropdoubled;

	// weed
	public boolean tenxp;
	public boolean fiftyxp;
	// fees
	public int voragofee;
	public int kkfee;
	public int dbossfee;
	// christmas event
	public boolean spoketosanta;
	public boolean spoketosanta1;
	public boolean spoketosanta2;
	public int coldhands;
	/**
	 * 
	 * 
	 * Donator items
	 */

	public boolean dags;
	public boolean dgs;
	public boolean dtset;
	public boolean dpset;
	public boolean dvset;
	public boolean dbset;
	public boolean daset;
	public boolean ddclaw;
	public boolean dksword;
	public boolean ddiv;
	public boolean dely;
	public boolean darc;
	public boolean ddset;
	public boolean dasc;
	public boolean dsset;
	public boolean dcrack;
	public boolean dsanta;
	private AdventurersLog adventurersLog;
	public boolean dhween;
	public boolean ddring;
	public boolean ddgloves;
	public boolean dcape;
	public boolean dthird;
	public boolean dgpick;
	public boolean d25spin;
	public boolean dcannon;
	public boolean dcannon1;
	public boolean dballs;
	public boolean dtrick;
	public boolean dvang;
	public boolean dmage;

	// gwd
	public int gwdkc;

	// hordes
	public int zkills;
	public int zpoints;

	// ingenuity
	public boolean canlootingenuitychest;
	public int ingenuitychestslooted;

	// when pets got achieved.
	public int ingenuityjrachieved = 0;
	public int redeemerachieved = 0;

	// charm coll
	public boolean charmc;
	public boolean charmcg;
	public boolean charmcb;
	public boolean charmcc;
	public boolean charmcgg;

	// achievements

	public boolean togdone;
	public int gmemory;

	// minigames
	public int kilnruns;
	// anims
	public boolean wcanim;
	public boolean minanim;
	public boolean dartanim;
	public boolean ZREST;
	public boolean SnowmanPenguinRest;
	public boolean SnowmanPenguinRestActive;
	public boolean wcskillfuanim;
	public boolean cookskillfuanim;
	public boolean miningskillfuanim;
	public boolean wcskillfuanimunlocked;
	public boolean cookskillfuanimunlocked;
	public boolean miningskillfuanimunlocked;

	// emotes
	public boolean breakdanceemoteunlocked;
	public boolean backflipemoteunlocked;

	// Teleports
	public boolean gnometele;
	public boolean skyjumpteleunlocked;
	public boolean skyjumptele;
	public boolean assassinteleunlocked;
	public boolean assassintele;

	// titles
	public boolean divine;
	// public boolean defeatertitle;
	public boolean santatitle;
	public boolean maxed;
	public boolean comped;
	public boolean ofbandos;
	public boolean ofsaradomin;
	public boolean ofarmadyl;
	public boolean ofzamorak;
	public boolean fbtitle;

	// DryPoints
	public int Drypoints;

	// loyalty
	public int Loyaltypoints;

	public int Playpoints;

	public int playdays, playhours;
	
	/**
	 * Referral
	 */
	
	public boolean claimedtoplist;

	// 839 switcxh
	public boolean hasbeenitemcheckedinv;
	public boolean hasbeenitemcheckedbank;
	public boolean hasbeenitemcheckedeq;

	// Bank pin
	public boolean hasBankPin;
	public boolean hasEnteredPin;
	public int pin;

	public int taskpoints;
	public int dungpoints = 0;
	public int Disasterpoints;

	public boolean inClanChat = false;

	public String HoL = "Lower";

	public boolean houserights;

	private String yellColor = "";
	private String yellShad = "";
	private String yellTag = "";
	private String yellTagColor = "";
	private String yellTagShad = "";

	/**
	 * Stars
	 */

	public String lastkill = "";
	public long shootingStarBankingTill;
	public boolean receivedGift = false;
	public boolean starSprite = false;
	public boolean deadRocks = false;
	private boolean helpedWithStar = false;
	private boolean hasClaimedReward = false;

	public void removeNpcs() {
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					// World.getNPCs().get(8091).sendDeath(npc);
				}
				loop++;
			}
		}, 0, 1);
	}

	public long count;

	public static String FormatNumber(long count) {
		return new DecimalFormat("#,###,##0").format(count).toString();
	}

	public static String FormatNumber1(int coins, Player player) {
		return new DecimalFormat("#,###,##0").format(player.getInventory().getItems().getNumberOf(coins)).toString();
	}

	/**
	 * LOBBY
	 */

	private long lastLoggedIn;

	public void init(Session session, String string, IsaacKeyPair isaacKeyPair) {
		username = string;
		this.session = session;
		this.isaacKeyPair = isaacKeyPair;
		World.addLobbyPlayer(this);// .addLobbyPlayer(this);
		if (Settings.DEBUG) {
			Logger.log(this, new StringBuilder("Lobby Inited Player: ").append(string).append(", pass: ").append(password).toString());
		}
	}

	public void startLobby(Player player) {
		player.sendLobbyConfigs(player);
		friendsIgnores.setPlayer(this);
		friendsIgnores.init();
		player.getPackets().sendFriendsChatChannel();
		friendsIgnores.sendFriendsMyStatus(true);
	}

	public void sendLobbyConfigs(Player player) {
		for (int i = 0; i < Utils.DEFAULT_LOBBY_CONFIGS.length; i++) {
			int val = Utils.DEFAULT_LOBBY_CONFIGS[i];
			if (val != 0) {
				player.getPackets().sendConfig(i, val);
			}
		}
	}

	/**
	 * SOF
	 */

	public static ItemsContainer<Item> items = new ItemsContainer<Item>(13, true);

	public static int boxWon = -1;
	public int isspining;
	public int Rewards;

	public int spins;

	public boolean farmreset;

	// death
	public int iDied;

	// provoking
	public boolean provoke;

	// rot6
	public int Rot6Points;

	// ip
	public String AccIp;

	// skill pets
	public boolean miningpet;
	public boolean woodcutpet;
	public boolean hunterpet;
	public boolean fishingpet;
	public boolean firemakingpet;
	public boolean cookingpet;

	// Ps achieved
	public boolean gotawyrmpet;
	public boolean gotarmadylpet;
	public boolean gotzamorakpet;
	public boolean gotamigopet;
	public boolean gotsaradominpet;
	public boolean gotbandospet;
	public boolean gotglacorpet;
	public boolean gotcorppet;
	public boolean gotsunfreetpet;
	public boolean gothydrapet;
	public boolean gotcelestiapet;
	public boolean gotcorruptveracpet;
	public boolean gotcorruptkarilpet;
	public boolean gotcorruptahrimpet;
	public boolean gotballakpet;
	public boolean gotgmolepet;
	public boolean gotraptorpet;
	public boolean gotvorkipet;
	public boolean gotkbdpet;
	public boolean gotnexpet;
	public boolean gotprimuspet;
	public boolean gotsecunduspet;
	public boolean gottertiuspet;
	public boolean gotquartuspet;
	public boolean gotquintuspet;
	public boolean gotsextuspet;
	public boolean gotvoragopet;
	public boolean gotbadsantapet;
	public boolean gotdrygonpet;
	public boolean gotsmokedevilpet;
	public boolean gotsirepet;
	public boolean gotolmpet;
	public boolean gotassassinpet;
	public boolean gotbloodpet;
	public boolean gotzulrahpet;
	public boolean gotkrakenpet;
	public boolean gotpdemonpet;
	public boolean gotedipet;
	public boolean gotfreezypet;
	public boolean gotdustypet;
	public boolean gotpugpet;
	public boolean gotsdragpet;
	public boolean gotidragpet;
	public boolean gottectonicbeastpet;
	public boolean gotskotizopet;
	public boolean gotvenenatispet;
	public boolean gotscorpiapet;
	public boolean gotautometonpet;
	public boolean gotdryaxpet;
	public boolean gotthunderouspet;
	public boolean gotqbdpet;
	public boolean gotaniviapet;
	public boolean gotcerberuspet;
	public boolean gotcallistopet;
	public boolean gotvetionpet;
	public boolean gotdarkfeastpet;
	public boolean gotgargpet;
	public boolean gotfrostypet;

	public boolean canattackbalancelemental;

	// killtracker
	public int KrakenKills;
	public int ZulrahKills;
	public int LizardmanKills;
	public int CelestiaKills;
	public int TrioRaidKills;
	public int GulegaRaidKills;
	public int ThunderousKills;
	public int AniviaKills;
	public int Rot6Kills;
	public int VoragoKills;
	public int Demigodkills;
	public int DrygonKills;
	public int AvaKills;
	public int WwyrmKills;
	public int BossKills;
	public int VorkathKills;
	public int SantaKills;
	public int NexKills;
	public int Dboss;
	public int SirenicKills;
	public int CallistoKills;
	public int BalanceElementalKills;
	public int GazerKills;
	public int KbdKills;
	public int BlinkKills;
	public int RaptorKills;
	public int BigtrollKills;
	public int CorruptedBrotherKills;
	public int AssassinKills;
	public int SkotizoKills;
	public int VenenatisKills;
	public int FanaticKills;
	public int CrazyArcKills;
	public int VetionKills;
	public int ScorpiaKills;
	public int SireKills;
	public int CerberusKills;
	//public int DryaxKills;
	public int KkingKills;
	public int QbdKills;
	public int KqKills;
	public int CorpKills;
	public int TdsKills;
	public int GmoleKills;
	public int GlacorKills;
	public int HydraBossKills;
	public int AngryEasterBunnyKills;
	public int the3amigosKills;
	public int BandosKills;
	public int ZamorakKills;
	public int ArmadylKills;
	public int AwyrmKills;
	public int SaradominKills;
	public int SunfreetKills;
	public int IstrykerKills;
	public int JstrykerKills;
	public int DstrykerKills;
	public int BorkKills;
	public int HmWildyWyrmKills;
	public int NecroLordKills;
	public int SliskeKills;

	// party demon
	public int PdemonKills;
	public boolean canlootpdemonchest = false;

	// die
	public boolean dieemote;

	/**
	 * Comp/Max
	 */
	// public static int isCompletionist = 0;

	/**
	 * Dwarf Cannon
	 */
	public Object getDwarfCannon;

	public boolean hasLoadedCannon = false;

	public boolean isShooting = false;

	public boolean hasSetupCannon = false;

	public boolean hasSetupGoldCannon = false;

	public boolean hasSetupRoyalCannon = false;

	public boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+"); // this just checks if the
												// website is returning a number
	}

	public void rspsdata(Player player, String username) {
		try {
			username = username.replaceAll(" ", "_");
			String secret = "19485224d128528da1602ca47383f078"; // YOUR SECRET
																// KEY!
			String email = "admin@drygonscape.co.uk"; // This is the one you use
														// to login into
														// RSPS-PAY
			URL url = new URL("http://rsps-pay.com/includes/listener.php?username=" + username + "&secret=" + secret + "&email=" + email);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String results = reader.readLine();
			if (results.toLowerCase().contains("!error:")) {
				// Logger.log(this, "[RSPS-PAY]"+results);
			} else {
				String[] ary = results.split(",");
				for (String element : ary) {
					switch (element) {
					case "0":
						player.sendMessage("<col=ff000>Your donation was not found in the database. Please contact Jack if you donated.");
						break;
					case "15362":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for Donator status!", false);
						player.sendMessage("Thank you for your support!");
						player.donationvalue += 10;
						Settings.amountdonated += 10;
						player.donator = true;
						break;
					case "15369":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a Donator box!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(29870, 1, true);
						player.donationvalue += 9;
						Settings.amountdonated += 9;
						break;
					case "15581":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a Crystal key!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(989, 1, true);
						player.donationvalue += 3;
						Settings.amountdonated += 3;
						break;
					case "15583":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for Double xp!", false);
						player.sendMessage("Thank you for your support!");
						player.ddxp = true;
						player.donationvalue += 25;
						Settings.amountdonated += 25;
						break;
					case "15867":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a Christmas cracker!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(962, 1, true);
						player.donationvalue += 30;
						Settings.amountdonated += 30;
						break;
					case "15871":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a Santa hat!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(1050, 1, true);
						player.donationvalue += 20;
						Settings.amountdonated += 20;
						break;
					case "15872":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for an Elite Void set!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(19785, 1, true);
						player.getBank().addItem(19786, 1, true);
						player.getBank().addItem(8842, 1, true);
						player.getBank().addItem(11674, 1, true);
						player.getBank().addItem(11675, 1, true);
						player.getBank().addItem(11676, 1, true);
						player.donationvalue += 20;
						Settings.amountdonated += 20;
						break;
					case "15873":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a set of Donator boots!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(29788, 1, true);
						player.donationvalue += 15;
						Settings.amountdonated += 15;
						break;
					case "15874":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a Donators ring!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(20054, 1, true);
						player.donationvalue += 15;
						Settings.amountdonated += 15;
						break;
					case "15875":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a pair of Donator gloves!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(29925, 1, true);
						player.donationvalue += 15;
						Settings.amountdonated += 15;
						break;
					case "15876":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for 25 Spins!", false);
						player.sendMessage("Thank you for your support!");
						player.spins += 25;
						player.donationvalue += 6;
						Settings.amountdonated += 6;
						break;
					case "15877":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for 100 Spins!", false);
						player.sendMessage("Thank you for your support!");
						player.spins += 100;
						player.donationvalue += 18;
						Settings.amountdonated += 18;
						break;
					case "15878":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a melee Tokhaar cape!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(23659, 1, true);
						player.completedFightKiln = true;
						player.donationvalue += 10;
						Settings.amountdonated += 10;
						break;
					case "15879":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a Fire cape!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(6570, 1, true);
						player.completedFightCaves = true;
						player.donationvalue += 5;
						Settings.amountdonated += 5;
						break;
					case "15882":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a set of Silverhawk boots and 150 Feathers!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(29922, 1, true);
						player.getBank().addItem(10176, 150, true);
						player.donationvalue += 15;
						Settings.amountdonated += 15;
						break;
					case "15883":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a 150 Silverhawk Feathers!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(10176, 150, true);
						player.donationvalue += 5;
						Settings.amountdonated += 5;
						break;
					case "15884":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a Dwarf cannon and 100 balls!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(2, 100, true);
						player.getBank().addItem(6, 1, true);
						player.getBank().addItem(8, 1, true);
						player.getBank().addItem(10, 1, true);
						player.getBank().addItem(12, 1, true);
						player.donationvalue += 25;
						Settings.amountdonated += 25;
						break;
					case "15885":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for 1000 Cannon balls!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(2, 1000, true);
						player.donationvalue += 10;
						Settings.amountdonated += 10;
						break;
					case "15886":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for 500 Magic bankpaper!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(29677, 500, true);
						player.donationvalue += 5;
						Settings.amountdonated += 5;
						break;
					case "15887":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a Deathtouch dart!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(25202, 1, true);
						player.donationvalue += 5;
						Settings.amountdonated += 5;
						break;
					case "16855":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a Donator cape!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(29717, 1, true);
						player.donationvalue += 15;
						Settings.amountdonated += 15;
						break;
					case "15888":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for 10 Deathtouch darts!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(25202, 10, true);
						player.donationvalue += 45;
						Settings.amountdonated += 45;
						break;
					case "19378":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a Super Donator Box!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(29580, 1, true);
						player.donationvalue += 14;
						Settings.amountdonated += 14;
						break;
					case "19379":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for an Ultra Christmas Cracker!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(29595, 1, true);
						player.donationvalue += 50;
						Settings.amountdonated += 50;
						break;
					case "19596":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a Display name change ticket!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(29552, 1, true);
						player.donationvalue += 10;
						Settings.amountdonated += 10;
						break;
					case "19598":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a Third-age melee set!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(10346, 1, true);
						player.getBank().addItem(10348, 1, true);
						player.getBank().addItem(10350, 1, true);
						player.getBank().addItem(10352, 1, true);
						player.donationvalue += 25;
						Settings.amountdonated += 25;
						break;
					case "19599":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a Third-age magic set!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(10338, 1, true);
						player.getBank().addItem(10340, 1, true);
						player.getBank().addItem(10342, 1, true);
						player.getBank().addItem(10344, 1, true);
						player.donationvalue += 25;
						Settings.amountdonated += 25;
						break;
					case "19600":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a Third-age range set!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(10330, 1, true);
						player.getBank().addItem(10332, 1, true);
						player.getBank().addItem(10334, 1, true);
						player.getBank().addItem(10336, 1, true);
						player.donationvalue += 25;
						Settings.amountdonated += 25;
						break;
					case "19782":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for an Amulet of Perfection!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(29732, 1, true);
						player.donationvalue += 30;
						Settings.amountdonated += 30;
						break;
					case "20008":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Hidden Ava perk!", false);
						player.sendMessage("Thank you for your support!");
						player.avaperk = true;
						player.donationvalue += 4;
						Settings.amountdonated += 4;
						break;
					case "20009":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Crystal luck perk!", false);
						player.sendMessage("Thank you for your support!");
						player.ckeyperk = true;
						player.donationvalue += 4;
						Settings.amountdonated += 4;
						break;
					case "20010":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Free entry perk!", false);
						player.sendMessage("Thank you for your support!");
						player.gwdperk = true;
						player.donationvalue += 5;
						Settings.amountdonated += 5;
						break;
					case "20011":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Master of Dungeons perk!", false);
						player.sendMessage("Thank you for your support!");
						player.dungperk = true;
						player.donationvalue += 3;
						Settings.amountdonated += 3;
						break;
					case "20012":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Pet hunter perk!", false);
						player.sendMessage("Thank you for your support!");
						player.petperk = true;
						player.donationvalue += 5;
						Settings.amountdonated += 5;
						break;
					case "20013":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Green fingers perk!", false);
						player.sendMessage("Thank you for your support!");
						player.farmingperk = true;
						player.donationvalue += 3;
						Settings.amountdonated += 3;
						break;
					case "20014":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Sneaky one perk!", false);
						player.sendMessage("Thank you for your support!");
						player.thievperk = true;
						player.donationvalue += 3;
						Settings.amountdonated += 3;
						break;
					case "20015":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the One true potion!", false);
						player.sendMessage("Thank you for your support!");
						player.potionperk = true;
						player.donationvalue += 8;
						Settings.amountdonated += 8;
						break;
					case "20016":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Smelly fish perk!", false);
						player.sendMessage("Thank you for your support!");
						player.fishingperk = true;
						player.donationvalue += 6;
						Settings.amountdonated += 6;
						break;
					case "20017":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Quarrymaster perk!", false);
						player.sendMessage("Thank you for your support!");
						player.miningperk = true;
						player.donationvalue += 4;
						Settings.amountdonated += 4;
						break;
					case "20018":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Axe master perk!", false);
						player.sendMessage("Thank you for your support!");
						player.axeperk = true;
						player.donationvalue += 4;
						Settings.amountdonated += 4;
						break;
					case "20019":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Colosseum override!", false);
						player.sendMessage("Thank you for your support!");
						player.colosseumc = true;
						player.donationvalue += 4;
						Settings.amountdonated += 4;
						break;
					case "20020":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Caberet override!", false);
						player.sendMessage("Thank you for your support!");
						player.cabaretc = true;
						player.donationvalue += 6;
						Settings.amountdonated += 6;
						break;
					case "20021":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Monarch override!", false);
						player.sendMessage("Thank you for your support!");
						player.monarchc = true;
						player.donationvalue += 5;
						Settings.amountdonated += 5;
						break;
					case "20022":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Feline override!", false);
						player.sendMessage("Thank you for your support!");
						player.felinec = true;
						player.donationvalue += 3;
						Settings.amountdonated += 3;
						break;
					case "20023":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Swashbuckler override!", false);
						player.sendMessage("Thank you for your support!");
						player.swashbucklerc = true;
						player.donationvalue += 4;
						Settings.amountdonated += 4;
						break;
					case "20024":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Assassin override!", false);
						player.sendMessage("Thank you for your support!");
						player.assassinc = true;
						player.donationvalue += 8;
						Settings.amountdonated += 8;
						break;
					case "20025":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Beachwear override!", false);
						player.sendMessage("Thank you for your support!");
						player.beachwearc = true;
						player.donationvalue += 6;
						Settings.amountdonated += 6;
						break;
					case "20026":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Noble override!", false);
						player.sendMessage("Thank you for your support!");
						player.noblec = true;
						player.donationvalue += 4;
						Settings.amountdonated += 4;
						break;
					case "20027":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Fox override!", false);
						player.sendMessage("Thank you for your support!");
						player.foxc = true;
						player.donationvalue += 3;
						Settings.amountdonated += 3;
						break;
					case "20028":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Wolf override!", false);
						player.sendMessage("Thank you for your support!");
						player.wolfc = true;
						player.donationvalue += 3;
						Settings.amountdonated += 3;
						break;
					case "20029":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Dwarven warsuit override!", false);
						player.sendMessage("Thank you for your support!");
						player.dwarvenwarsuitc = true;
						player.donationvalue += 10;
						Settings.amountdonated += 10;
						break;
					case "20030":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Tropical islander override!", false);
						player.sendMessage("Thank you for your support!");
						player.tropicalislanderc = true;
						player.donationvalue += 5;
						Settings.amountdonated += 5;
						break;
					case "20031":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Gothic override!", false);
						player.sendMessage("Thank you for your support!");
						player.gothicc = true;
						player.donationvalue += 4;
						Settings.amountdonated += 4;
						break;
					case "20032":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Royal court set override!", false);
						player.sendMessage("Thank you for your support!");
						player.royalcourtc = true;
						player.donationvalue += 6;
						Settings.amountdonated += 6;
						break;
					case "20033":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Scorching axe override!", false);
						player.sendMessage("Thank you for your support!");
						player.scorchingpackc = true;
						player.donationvalue += 2;
						Settings.amountdonated += 2;
						break;
					case "20034":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Whip pack override!", false);
						player.sendMessage("Thank you for your support!");
						player.whippackc = true;
						player.donationvalue += 5;
						Settings.amountdonated += 5;
						break;
					case "20036":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Solarius shield override!", false);
						player.sendMessage("Thank you for your support!");
						player.solariusc = true;
						player.donationvalue += 2;
						Settings.amountdonated += 2;
						break;
					case "20037":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for the Bunny mask override!", false);
						player.sendMessage("Thank you for your support!");
						player.bunnymaskc = true;
						player.donationvalue += 9;
						Settings.amountdonated += 9;
						break;
					case "20038":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a Keepsake Key!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(29544, 1, true);
						player.donationvalue += 5;
						Settings.amountdonated += 5;
						break;
					case "19407":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a WeeWyrm!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(29863, 1, true);
						player.donationvalue += 4;
						Settings.amountdonated += 3;
						break;
					case "19406":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for Jadex!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(29861, 1, true);
						player.donationvalue += 4;
						Settings.amountdonated += 4;
						break;
					case "19405":
						World.sendWorldMessage("" + player.getDisplayName() + "<col=00FF00> has donated for a Cow pet!", false);
						player.sendMessage("Thank you for your support!");
						player.getBank().addItem(29851, 1, true);
						player.donationvalue += 2;
						Settings.amountdonated += 2;
						break;
					}
				}
			}
		} catch (IOException e) {
		}
	}

	public long calculateNetworth() {
		long value = 0;
		for (int i = 0; i < equipment.getItems().getSize(); i++) {
			Item item = equipment.getItems().get(i);
			if (item == null) {
				continue;
			}
			long price = item.getDefinitions().getValue() * item.getAmount();
			value += price;
		}
		for (int i = 0; i < inventory.getItems().getSize(); i++) {
			Item item = inventory.getItems().get(i);
			if (item == null) {
				continue;
			}
			long price = item.getDefinitions().getbValue() * item.getAmount();
			value += price;
		}
		// for () {
		// Item item = coinamount;
		// if (item == null)
		// continue;
		{
			long price = coinamount;
			value += price;
		}
		for (int i = 0; i < bank.getBankSize(); i++) {
			Item item = bank.getItem(bank.getRealSlot(i));
			long price = item.getDefinitions().getbValue() * item.getAmount();
			value += price;
		}
		for (int i = 0; i < bank2.getBankSize(); i++) {
			Item item = bank2.getItem(bank2.getRealSlot(i));
			long price = item.getDefinitions().getbValue() * item.getAmount();
			value += price;
		}
		return value;
	}

	public long calculateBankNetworth() {
		long value = 0;
		for (int i = 0; i < bank.getBankSize(); i++) {
			Item item = bank.getItem(bank.getRealSlot(i));
			long price = item.getDefinitions().getbValue() * item.getAmount();
			value += price;
		}
		for (int i = 0; i < bank2.getBankSize(); i++) {
			Item item = bank2.getItem(bank2.getRealSlot(i));
			long price = item.getDefinitions().getbValue() * item.getAmount();
			value += price;
		}
		return value;
	}

	public static String getCurrencyFormat(long amount) {
		if (amount < 999999) {
			return amount / 1000 + "K";
		} else if (amount > 999999 && amount < 1000000000) {
			return amount / 1000000 + "M";
		} else if (amount >= 1000000000 && amount < 1000000000000L) {
			return amount / 1000000000 + "B";
		} else if (amount >= 1000000000000L) {
			return amount / 1000000000000L + "T";
		}
		return "";
	}

	public static void printDoubleDonateLog(Player player, Player target) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("doubledonate.txt", true));
			bf.write("[" + DateFormat.getDateTimeInstance().format(new Date()) + " " + Calendar.getInstance().getTimeZone().getDisplayName() + "] " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ":" + " May have had the donation bug");
			bf.newLine();
			bf.flush();
			bf.close();
		} catch (IOException ignored) {
		}
	}
	
	public static void printGELog(String message) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("data/logs/GrandExchangeTransactions.txt", true));
			bf.write("[" + DateFormat.getDateTimeInstance().format(new Date()) + " " + Calendar.getInstance().getTimeZone().getDisplayName() + "] "+ message);
			bf.newLine();
			bf.flush();
			bf.close();
		} catch (IOException ignored) {
		}
	}

	public static void printBanningLog(Player player, String name, String banreason) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("BannedReasons.txt", true));
			bf.write("[" + DateFormat.getDateTimeInstance().format(new Date()) + " " + Calendar.getInstance().getTimeZone().getDisplayName() + "] " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ":" + " has banned " + name + " because.... " + banreason + " ");
			bf.newLine();
			bf.flush();
			bf.close();
		} catch (IOException ignored) {
		}
	}

	public static void printSuggestionLog(Player player, Player target, String idea) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("data/logs/suggestions.txt", true));
			bf.write("[" + DateFormat.getDateTimeInstance().format(new Date()) + " " + Calendar.getInstance().getTimeZone().getDisplayName() + "] " + Utils.formatPlayerNameForDisplay(player.getUsername()) + " has suggested:- " + idea + "");
			bf.newLine();
			bf.flush();
			bf.close();
		} catch (IOException ignored) {
		}
	}

	public static void printRestartLog(Player player, int i, Player target) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("data/logs/restarts.txt", true));
			bf.write("[" + DateFormat.getDateTimeInstance().format(new Date()) + " " + Calendar.getInstance().getTimeZone().getDisplayName() + "] " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ":" + " Restarted the server");
			bf.newLine();
			bf.flush();
			bf.close();
		} catch (IOException ignored) {
		}
	}
	
	public static void printReferralLog(Player player, String target) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("data/logs/referrals/"+target+".txt", true));
			bf.write("[" + DateFormat.getDateTimeInstance().format(new Date()) + " " + Calendar.getInstance().getTimeZone().getDisplayName() + "] " + Utils.formatPlayerNameForDisplay(player.getUsername()) + " referred "+target+".");
			bf.newLine();
			bf.flush();
			bf.close();
		} catch (IOException ignored) {
		}
	}

	public static void printTradeLog(Player player, int i, int amount, Player target) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("data/logs/trade.txt", true));
			bf.write("[" + DateFormat.getDateTimeInstance().format(new Date()) + " " + Calendar.getInstance().getTimeZone().getDisplayName() + "] " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ":" + " traded " + ":" + amount + ":" + " of the ID " + ":" + i + ":" + " with " + ": " + target.getUsername());
			bf.newLine();
			bf.flush();
			bf.close();
		} catch (IOException ignored) {
		}
	}

	public static void printGiveItemLog(Player player, String string, int amount, Player target) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("data/logs/giveitem2.txt", true));
			bf.write("[" + DateFormat.getDateTimeInstance().format(new Date()) + " " + Calendar.getInstance().getTimeZone().getDisplayName() + "] " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ":" + " gave " + ":" + amount + ":" + " of the ID " + ":" + string + ":" + " to " + ": " + target.getUsername());
			bf.newLine();
			bf.flush();
			bf.close();
		} catch (IOException ignored) {
		}
	}

	public static void printEmail(Player player, String string, String ip) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("data/logs/playeremails2.txt", true));
			bf.write("[" + DateFormat.getDateTimeInstance().format(new Date()) + " " + Calendar.getInstance().getTimeZone().getDisplayName() + "] Player Name: " + Utils.formatPlayerNameForDisplay(player.getUsername()) + " |" + " Email Address: " + string + " | IP: "+ip);
			bf.newLine();
			bf.flush();
			bf.close();
		} catch (IOException ignored) {
		}
	}

	public static void printDuelArena(Player player, String comment) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("data/logs/duelarena.txt", true));
			bf.write("[" + DateFormat.getDateTimeInstance().format(new Date()) + " " + Calendar.getInstance().getTimeZone().getDisplayName() + "] " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ": " + comment + "");
			bf.newLine();
			bf.flush();
			bf.close();
		} catch (IOException ignored) {
		}
	}

	public static void printRecycleCentre(Player player, String comment) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("data/logs/recycleditems.txt", true));
			bf.write("[" + DateFormat.getDateTimeInstance().format(new Date()) + " " + Calendar.getInstance().getTimeZone().getDisplayName() + "] " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ": " + comment + "");
			bf.newLine();
			bf.flush();
			bf.close();
		} catch (IOException ignored) {
		}
	}

	public static void printStaff(Player player, String comment) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("data/logs/staffactivity.txt", true));
			bf.write("[" + DateFormat.getDateTimeInstance().format(new Date()) + " " + Calendar.getInstance().getTimeZone().getDisplayName() + "] " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ": " + comment + "");
			bf.newLine();
			bf.flush();
			bf.close();
		} catch (IOException ignored) {
		}
	}

	public static void printEquipmentRemoveLog(Player player, int i) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("data/logs/Equipmentdeleted.txt", true));
			bf.write("" + player.getDisplayName() + " had " + i + " deleted when they logged in.");
			bf.newLine();
			bf.flush();
			bf.close();
		} catch (IOException ignored) {
		}
	}

	public static void printDuelLog(Player player, int i, int amount, Player target) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("data/logs/duel.txt", true));
			bf.write("[" + DateFormat.getDateTimeInstance().format(new Date()) + " " + Calendar.getInstance().getTimeZone().getDisplayName() + "] " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ":" + " added " + ":" + amount + ":" + " of the ID " + ":" + i + ":" + " with " + ": " + target.getUsername());
			bf.newLine();
			bf.flush();
			bf.close();
		} catch (IOException ignored) {
		}
	}

	public static void droplogged(Player player, int amount, int i) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("data/logs/droppeditems.txt", true));
			bf.write("[" + DateFormat.getDateTimeInstance().format(new Date()) + " " + Calendar.getInstance().getTimeZone().getDisplayName() + "] " + Utils.formatPlayerNameForDisplay(player.getUsername()) + " " + amount + " x " + i);
			bf.newLine();
			bf.flush();
			bf.close();
		} catch (IOException ignored) {
		}
	}

	public static void PickedUpLogged(Player player, int amount, int item, String owner) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter("data/logs/pickedupitems.txt", true));
			bf.write("[" + DateFormat.getDateTimeInstance().format(new Date()) + " " + Calendar.getInstance().getTimeZone().getDisplayName() + "] " + Utils.formatPlayerNameForDisplay(player.getUsername()) + " pick " + amount + " x " + item + ".  " + owner + " Dropped this");
			bf.newLine();
			bf.flush();
			bf.close();
		} catch (IOException ignored) {
		}
	}



	public static int hourOfDay() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	public static int dayOfWeek() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static boolean isWeekend() {
		// Settings.DOUBLE_XP = true; //remove 2,3,4,5
		return dayOfWeek() == 1 ? true : dayOfWeek() == 6 ? true : dayOfWeek() == 7 ? true : false;
	}

	public static boolean isHappyHour() {//sunday is 1
		// Settings.DOUBLE_XP = true; //remove 2,3,4,5
		return dayOfWeek() == 2 && hourOfDay() == 10 ? true :
			dayOfWeek() == 4 && hourOfDay() == 10 ? true :
			dayOfWeek() == 6 && hourOfDay() == 10 ? true :
			dayOfWeek() == 7 && hourOfDay() == 10 ? true :
				
			dayOfWeek() == 1 && hourOfDay() == 19 ? true :
			dayOfWeek() == 3 && hourOfDay() == 19 ? true :
			dayOfWeek() == 5 && hourOfDay() == 19 ? true :
			dayOfWeek() == 7 && hourOfDay() == 19 ? true :
																		false;
	}//sunday = 1


	public int weekendxp;

	public long getTotalWealth() {
		long bank = getBank().getBankValue();
		long inventory = getInventory().getInventoryValue();
		// long equipment = getEquipment().getEquipmentValue(null);
		long moneypouch = getMoneyPouch().getCoinAmount();
		long total = calculateNetworth();
		return total;
	}

	/**
	 * override
	 */

	/**
	 * rago colours
	 */
	private boolean isRedTS;

	public boolean isRedTS() {
		return isRedTS;
	}

	public void setRedTS(boolean value) {
		isRedTS = value;
	}

	private boolean isGreenTS;

	public boolean isGreenTS() {
		return isGreenTS;
	}

	public void setGreenTS(boolean value) {
		isGreenTS = value;
	}

	/**
	 * events
	 */

	public int pvppoints;
	// public int donatorpoints;

	public boolean dartgiven1;

	// donatormessage
	public boolean senddonatormessage;

	public long joinDate = 0;

	/**
	 * clan st00f
	 */

	public int myclanxp;
	public boolean partofclan;

	// GWDPoints
	public int bandos = 0;
	public int saradomin = 0;
	public int armadyl = 0;
	public int zamorak = 0;
	public boolean bandosdone;
	public boolean saradomindone;
	public boolean armadyldone;
	public boolean zamorakdone;

	/**
	 * Shooting Star's
	 */

	public boolean canTalkToSprite, taggedStar;

	// the fuck is this shit

	public boolean portal1 = false;
	public boolean portal2 = false;
	public boolean portal3 = false;

	public boolean inBuildMode = false;
	public boolean canEnter = false;
	public boolean isOwner = false;
	public boolean hasLocked = false;
	public boolean hasRemovedObjects = false;

	public boolean hasHouse = false;

	public boolean contutdone;
	public int contut;

	// Butlers

	public boolean hasBoughtDemon = false;
	public boolean spokeToDemon = false;
	public boolean spokeToAgent = false;

	/**
	 * 1 = Teak Frame 2 = Mahogany Frame
	 */
	public int portalTele1 = 0;
	public int portalTele2 = 0;
	public int portalTele3 = 0;
	public int portalFrame = 0;

	/**
	 * 1 = Crude chair 2 = Rocking chair 3 = Oak armchair 4 = Teak armchair
	 */

	public int chair = 0;
	public int throne = 0;
	public int tree = 0;
	public int bookcase = 0;
	public int firePlace = 0;
	public int altar = 0;

	public void refreshObjects(final int[] boundChuncks) {
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 50) {
					checkObjects(boundChuncks);
				} else if (loop == 100) {
					checkObjects(boundChuncks);
				} else if (loop == 150) {
					checkObjects(boundChuncks);
				} else if (loop == 200) {
					checkObjects(boundChuncks);
				} else if (loop == 500) {
					checkObjects(boundChuncks);
				}
				loop++;
			}
		}, 0, 1);
	}

	public void checkObjects(final int[] boundChuncks) {
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 1) {
					closeInterfaces();
					World.spawnObject(new WorldObject(13405, 10, 0, boundChuncks[0] * 8 + 35, boundChuncks[1] * 8 + 35, 0), true);
					if (chair == 1) {
						World.spawnObject(new WorldObject(13581, 10, 0, boundChuncks[0] * 8 + 42, boundChuncks[1] * 8 + 36, 0), true);
						World.spawnObject(new WorldObject(13581, 10, 0, boundChuncks[0] * 8 + 44, boundChuncks[1] * 8 + 35, 0), true);
						World.spawnObject(new WorldObject(13581, 10, 0, boundChuncks[0] * 8 + 45, boundChuncks[1] * 8 + 36, 0), true);
					} else if (chair == 2) {
						World.spawnObject(new WorldObject(13583, 10, 0, boundChuncks[0] * 8 + 42, boundChuncks[1] * 8 + 36, 0), true);
						World.spawnObject(new WorldObject(13583, 10, 0, boundChuncks[0] * 8 + 44, boundChuncks[1] * 8 + 35, 0), true);
						World.spawnObject(new WorldObject(13583, 10, 0, boundChuncks[0] * 8 + 45, boundChuncks[1] * 8 + 36, 0), true);
					} else if (chair == 3) {
						World.spawnObject(new WorldObject(13584, 10, 0, boundChuncks[0] * 8 + 42, boundChuncks[1] * 8 + 36, 0), true);
						World.spawnObject(new WorldObject(13584, 10, 0, boundChuncks[0] * 8 + 44, boundChuncks[1] * 8 + 35, 0), true);
						World.spawnObject(new WorldObject(13584, 10, 0, boundChuncks[0] * 8 + 45, boundChuncks[1] * 8 + 36, 0), true);
					} else if (chair == 4) {
						World.spawnObject(new WorldObject(13587, 10, 0, boundChuncks[0] * 8 + 42, boundChuncks[1] * 8 + 36, 0), true);
						World.spawnObject(new WorldObject(13587, 10, 0, boundChuncks[0] * 8 + 44, boundChuncks[1] * 8 + 35, 0), true);
						World.spawnObject(new WorldObject(13587, 10, 0, boundChuncks[0] * 8 + 45, boundChuncks[1] * 8 + 36, 0), true);
					}
					if (throne == 1) {
						World.spawnObject(new WorldObject(13665, 10, 3, boundChuncks[0] * 8 + 25, boundChuncks[1] * 8 + 36, 0), true);
						World.spawnObject(new WorldObject(13665, 10, 3, boundChuncks[0] * 8 + 25, boundChuncks[1] * 8 + 35, 0), true);
					} else if (throne == 2) {
						World.spawnObject(new WorldObject(13670, 10, 3, boundChuncks[0] * 8 + 25, boundChuncks[1] * 8 + 36, 0), true);
						World.spawnObject(new WorldObject(13670, 10, 3, boundChuncks[0] * 8 + 25, boundChuncks[1] * 8 + 35, 0), true);
					} else if (throne == 3) {
						World.spawnObject(new WorldObject(13671, 10, 3, boundChuncks[0] * 8 + 25, boundChuncks[1] * 8 + 36, 0), true);
						World.spawnObject(new WorldObject(13671, 10, 3, boundChuncks[0] * 8 + 25, boundChuncks[1] * 8 + 35, 0), true);
					}
					if (tree == 1) {
						World.spawnObject(new WorldObject(13413, 10, 0, boundChuncks[0] * 8 + 33, boundChuncks[1] * 8 + 37, 0), true);
						World.spawnObject(new WorldObject(13413, 10, 0, boundChuncks[0] * 8 + 38, boundChuncks[1] * 8 + 38, 0), true);
					} else if (tree == 2) {
						World.spawnObject(new WorldObject(13414, 10, 0, boundChuncks[0] * 8 + 33, boundChuncks[1] * 8 + 37, 0), true);
						World.spawnObject(new WorldObject(13414, 10, 0, boundChuncks[0] * 8 + 38, boundChuncks[1] * 8 + 38, 0), true);
					} else if (tree == 3) {
						World.spawnObject(new WorldObject(13416, 10, 0, boundChuncks[0] * 8 + 33, boundChuncks[1] * 8 + 37, 0), true);
						World.spawnObject(new WorldObject(13416, 10, 0, boundChuncks[0] * 8 + 38, boundChuncks[1] * 8 + 38, 0), true);
					} else if (tree == 4) {
						World.spawnObject(new WorldObject(13417, 10, 0, boundChuncks[0] * 8 + 33, boundChuncks[1] * 8 + 37, 0), true);
						World.spawnObject(new WorldObject(13417, 10, 0, boundChuncks[0] * 8 + 38, boundChuncks[1] * 8 + 38, 0), true);
					}
					if (bookcase == 1) {
						World.spawnObject(new WorldObject(13597, 10, 0, boundChuncks[0] * 8 + 40, boundChuncks[1] * 8 + 33, 0), true);
						World.spawnObject(new WorldObject(13597, 10, 2, boundChuncks[0] * 8 + 47, boundChuncks[1] * 8 + 33, 0), true);
						// World.spawnObject(new WorldObject(13597, 10, 2,
						// boundChuncks[0] * 8 + 39, boundChuncks[1] * 8 + 30,
						// 0), true);
					} else if (bookcase == 2) {
						World.spawnObject(new WorldObject(13598, 10, 0, boundChuncks[0] * 8 + 40, boundChuncks[1] * 8 + 33, 0), true);
						World.spawnObject(new WorldObject(13598, 10, 2, boundChuncks[0] * 8 + 47, boundChuncks[1] * 8 + 33, 0), true);
						// World.spawnObject(new WorldObject(13598, 10, 2,
						// boundChuncks[0] * 8 + 39, boundChuncks[1] * 8 + 30,
						// 0), true);
					} else if (bookcase == 3) {
						World.spawnObject(new WorldObject(13599, 10, 0, boundChuncks[0] * 8 + 40, boundChuncks[1] * 8 + 33, 0), true);
						World.spawnObject(new WorldObject(13599, 10, 2, boundChuncks[0] * 8 + 47, boundChuncks[1] * 8 + 33, 0), true);
						// World.spawnObject(new WorldObject(13599, 10, 2,
						// boundChuncks[0] * 8 + 39, boundChuncks[1] * 8 + 30,
						// 0), true);
					}
					if (firePlace == 1) {
						World.spawnObject(new WorldObject(13609, 10, 1, boundChuncks[0] * 8 + 43, boundChuncks[1] * 8 + 39, 0), true);
					} else if (firePlace == 2) {
						World.spawnObject(new WorldObject(13611, 10, 1, boundChuncks[0] * 8 + 43, boundChuncks[1] * 8 + 39, 0), true);
					} else if (firePlace == 3) {
						World.spawnObject(new WorldObject(13613, 10, 1, boundChuncks[0] * 8 + 43, boundChuncks[1] * 8 + 39, 0), true);
					} else if (firePlace == 4) {
						World.spawnObject(new WorldObject(13610, 10, 1, boundChuncks[0] * 8 + 43, boundChuncks[1] * 8 + 39, 0), true);
					} else if (firePlace == 5) {
						World.spawnObject(new WorldObject(13612, 10, 1, boundChuncks[0] * 8 + 43, boundChuncks[1] * 8 + 39, 0), true);
					} else if (firePlace == 6) {
						World.spawnObject(new WorldObject(13614, 10, 1, boundChuncks[0] * 8 + 43, boundChuncks[1] * 8 + 39, 0), true);
					}
					switch (portalTele1) {
					case 1:
						portal1 = true;
						World.spawnObject(new WorldObject(13622, 10, 1, boundChuncks[0] * 8 + 32, boundChuncks[1] * 8 + 43, 0), true);
						break;
					case 2:
						portal1 = true;
						World.spawnObject(new WorldObject(13623, 10, 1, boundChuncks[0] * 8 + 32, boundChuncks[1] * 8 + 43, 0), true);
						break;
					case 3:
						portal1 = true;
						World.spawnObject(new WorldObject(13624, 10, 1, boundChuncks[0] * 8 + 32, boundChuncks[1] * 8 + 43, 0), true);
						break;
					case 4:
						portal1 = true;
						World.spawnObject(new WorldObject(13625, 10, 1, boundChuncks[0] * 8 + 32, boundChuncks[1] * 8 + 43, 0), true);
						break;
					case 5:
						portal1 = true;
						World.spawnObject(new WorldObject(13627, 10, 1, boundChuncks[0] * 8 + 32, boundChuncks[1] * 8 + 43, 0), true);
						break;
					}
					switch (portalTele2) {
					case 6:
						portal2 = true;
						World.spawnObject(new WorldObject(13622, 10, 2, boundChuncks[0] * 8 + 35, boundChuncks[1] * 8 + 47, 0), true);
						break;
					case 7:
						portal2 = true;
						World.spawnObject(new WorldObject(13623, 10, 2, boundChuncks[0] * 8 + 35, boundChuncks[1] * 8 + 47, 0), true);
						break;
					case 8:
						portal2 = true;
						World.spawnObject(new WorldObject(13624, 10, 2, boundChuncks[0] * 8 + 35, boundChuncks[1] * 8 + 47, 0), true);
						break;
					case 9:
						portal2 = true;
						World.spawnObject(new WorldObject(13625, 10, 2, boundChuncks[0] * 8 + 35, boundChuncks[1] * 8 + 47, 0), true);
						break;
					case 10:
						portal2 = true;
						World.spawnObject(new WorldObject(13627, 10, 2, boundChuncks[0] * 8 + 35, boundChuncks[1] * 8 + 47, 0), true);
						break;
					}
					switch (portalTele3) {
					case 11:
						portal3 = true;
						World.spawnObject(new WorldObject(13622, 10, 1, boundChuncks[0] * 8 + 39, boundChuncks[1] * 8 + 43, 0), true);
						break;
					case 12:
						portal3 = true;
						World.spawnObject(new WorldObject(13623, 10, 1, boundChuncks[0] * 8 + 39, boundChuncks[1] * 8 + 43, 0), true);
						break;
					case 13:
						portal3 = true;
						World.spawnObject(new WorldObject(13624, 10, 1, boundChuncks[0] * 8 + 39, boundChuncks[1] * 8 + 43, 0), true);
						break;
					case 14:
						portal3 = true;
						World.spawnObject(new WorldObject(13625, 10, 1, boundChuncks[0] * 8 + 39, boundChuncks[1] * 8 + 43, 0), true);
						break;
					case 15:
						portal3 = true;
						World.spawnObject(new WorldObject(13627, 10, 1, boundChuncks[0] * 8 + 39, boundChuncks[1] * 8 + 43, 0), true);
						break;
					}
					switch (altar) {
					case 1:
						World.spawnObject(new WorldObject(13190, 10, 0, boundChuncks[0] * 8 + 35, boundChuncks[1] * 8 + 26, 0), true);
						break;
					case 2:
						World.spawnObject(new WorldObject(13196, 10, 0, boundChuncks[0] * 8 + 35, boundChuncks[1] * 8 + 26, 0), true);
						break;
					case 3:
						World.spawnObject(new WorldObject(13199, 10, 0, boundChuncks[0] * 8 + 35, boundChuncks[1] * 8 + 26, 0), true);
						break;
					}

				}
				loop++;
			}
		}, 0, 1);
	}// TODO

	public boolean wateredpropatch;
	public int boneOnAltar = 0;

	public void executeAltar() {
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 1) {
					getDialogueManager().startDialogue("MakeAltar");// Couldnt
																	// be
																	// bothered
																	// to add
																	// another
																	// option
				}
				loop++;
			}
		}, 0, 1);
	}

	/**
	 * Random Events
	 */
	public int playersX, playersY, playersP;

	public int tagged5Posts = 0;

	public String randomRune;

	/*
	 * Use this to teleport players to random event.
	 * Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1972, 5046, 0));
	 * player.setNextForceTalk(new ForceTalk("Oh no!")); player.randomRune();
	 * player.tagged5Posts = 0; player.out("Tag the "
	 * +player.randomRune+" rune pinball post.");
	 */

	public void randomRune() {
		String[] runeTags = { "Air", "Earth", "Fire", "Nature", "Water" };
		Random rand = new Random();
		randomRune = runeTags[rand.nextInt(runeTags.length)];
	}

	public int conRoom = 0;

	public void addNest(Player player) {
		int birdNests[] = { 5070, 5071, 5072, 5073, 5074 };
		int i = Utils.getRandom(4);
		double chance = Math.random() * 100;
		if (chance <= 0.10 && player.getInventory().getFreeSlots() > 0) {
			World.addGroundItem(new Item(birdNests[i]), new WorldTile(player), player, true, 180, true);
			player.getPackets().sendGameMessage("A bird nest fell out of the tree!");
		}
	}

	public void seedNest() {
		int seedNest[] = { 5313, 5316, 5315, 5314 };
		int i = Utils.getRandom(3);
		getInventory().addItem(seedNest[i], 1);
		getInventory().deleteItem(5073, 1);
		out("You recieve a random tree seed from the birds nest.");
	}

	public void ringNest() {
		int ringNest[] = { 1635, 1637, 1639, 1641, 1643 };
		int i = Utils.getRandom(4);
		getInventory().addItem(ringNest[i], 1);
		getInventory().deleteItem(5074, 1);
		out("You recieve a random ring from the birds nest.");
	}

	// money pouch
	public int money = 0;

	// used for packets logic
	private transient ConcurrentLinkedQueue<LogicPacket> logicPackets;

	// used for update
	private transient LocalPlayerUpdate localPlayerUpdate;
	private transient LocalNPCUpdate localNPCUpdate;

	private int temporaryMovementType;
	private boolean updateMovementType;

	// player stages
	private transient boolean started;
	private transient boolean running;

	// Rfd
	public boolean rfd1, rfd2, rfd3, rfd4, rfd5 = false;

	private transient long packetsDecoderPing;
	private transient boolean resting;
	private transient boolean canPvp;
	private transient boolean cantTrade;
	private transient long lockDelay; // used for doors and stuff like that
	private transient long foodDelay;
	private transient long potDelay;
	private transient long boneDelay;
	private transient Runnable closeInterfacesEvent;
	private transient long lastPublicMessage;
	private transient long polDelay;
	private transient List<Integer> switchItemCache;
	private transient boolean disableEquip;
	private transient MachineInformation machineInformation;
	private transient boolean spawnsMode;
	private transient boolean castedVeng;
	private transient boolean invulnerable;
	private transient double hpBoostMultiplier;
	private transient boolean largeSceneView;

	// saving stuff
	public int moneypouch;
	public int coinamount;
	public int firecount;
	public int boughtdung;
	public int cookcount;
	public int logcount;
	public int neededpoints;
	public int skillpoints;
	public int lastTeleInter;
	private String password;
	public int rights;
	private String displayName;
	private String lastIP;
	private long creationDate;
	private Appearence appearence;
	private MoneyPouch pouch;
	public Inventory inventory;
	public Equipment equipment;
	public Skills skills;
	private CombatDefinitions combatDefinitions;
	private Prayer prayer;
	private Bank bank;
	private Bank2 bank2;
	public ControlerManager controlerManager;
	private MusicsManager musicsManager;
	private EmotesManager emotesManager;
	private FriendsIgnores friendsIgnores;
	private FairyRing fairyRing;
	public DominionTower dominionTower;
	// public Tasksmanager tasksmanager;
	private Familiar familiar;
	private AuraManager auraManager;
	private QuestManager questManager;
	private PetManager petManager;
	private byte runEnergy;
	private boolean allowChatEffects;
	private boolean mouseButtons;
	private int privateChatSetup;
	private int friendChatSetup;
	private int skullDelay;
	private int skullId;
	private boolean forceNextMapLoadRefresh;
	private long poisonImmune;
	private long fireImmune;
	private int runeSpanPoints;

	public int xpRate;
	private int lastBonfire;
	private int[] pouches;
	private long displayTime;
	public long muted;
	private long jailed;
	private long banned;
	private boolean permBanned;
	private boolean filterGame;
	private boolean xpLocked;
	public boolean cbxpLocked;
	private boolean yellOff;
	// game bar status
	private int publicStatus;
	private int clanStatus;
	private int tradeStatus;
	private int assistStatus;

	// clan stuff

	// end of clan stuff

	public boolean newuserdone;

	public boolean dicer;

	public boolean isrealism;
	public boolean isheroic;
	public boolean iseasy;
	public boolean isaverage;
	public boolean ishard;
	public boolean ironman;
	public boolean ironmanduo;
	public String ironmanpartner;
	public String requestedpartner;
	public String acceptedpartner;
	public boolean hcironman;
	public boolean youtuber;
	public boolean ironmandead;
	public boolean ispvpmode;

	public boolean veteran;
	public boolean donator;
	public boolean legendaryDonator;
	public boolean vipDonator;
	public boolean ultimateDonator;
	public boolean sponsorDonator;
	public boolean extremeDonator;
	public boolean superDonator;
	public int donationvalue;

	// Recovery ques. & ans.
	private String recovQuestion;
	private String recovAnswer;

	private String lastMsg;

	// Used for storing recent ips and password
	private ArrayList<String> passwordList = new ArrayList<String>();
	private ArrayList<String> ipList = new ArrayList<String>();

	// honor
	private int killCount, deathCount;
	private ChargesManager charges;
	// barrows
	private boolean[] killedBarrowBrothers;
	private int hiddenBrother;
	private int barrowsKillCount;
	private int pestPoints;
	/**
	 * Farming
	 */

	// GWD KC
	public long bgwdkc;

	// skill capes customizing
	private int[] maxedCapeCustomized;
	public int[] completionistCapeCustomized;

	// house
	public long bronzehouse;
	public long mithrilhouse;
	public long runehouse;
	public long dragonhouse;
	
	
	/**
	 * New Achievement Diary
	 */
	
	public int achievementdiarypage = 0;
	public int achievementdiaryslot = 0;
	public int diarypoints;
	public boolean claimNewEasyDiaryReward;
	public boolean claimNewMediumDiaryReward;
	public boolean claimNewHardDiaryReward;
	public boolean claimNewEliteDiaryReward;
	public boolean claimNewMasterDiaryReward;
	
	public int slayertargetkills;
	public boolean alchedddefender;
	public int imbuekitused;
	public int raredropobtained;
	public boolean chaoticupgraded;
	public boolean elitevoidmade;
	public boolean dropboostimade;
	public int runebodysmithed;
	public boolean skillingpetspawned;
	public boolean connorcapfound;
	public int dyesusedt90;
	public int raptorseals;
	public boolean usedtrollpotionhydrix;
	public int seasonalrewardsobtained;
	
	/*
	 * Easy
	 */
	
	public boolean easycchestclaimed;
	public boolean easycluelessclaimed;
	public boolean easymobsclaimed;
	public boolean easyrockyclaimed;
	public boolean easyborkclaimed;
	public boolean easybosskillerclaimed;
	public boolean easygiantkillerclaimed;
	public boolean easyslayerapprenticeclaimed;
	public boolean easybrothersclaimed;
	public boolean easyvoterclaimed;
	public boolean easyattackclaimed;
	public boolean easystrengthclaimed;
	public boolean easydefenceclaimed;
	public boolean easyhpclaimed;
	public boolean easyrangedclaimed;
	public boolean easymagicclaimed;
	public boolean easyprayerclaimed;
	public boolean easysummoningclaimed;
	public boolean easyslayerclaimed;
	public boolean easyrcclaimed;
	public boolean easyconstructionclaimed;
	public boolean easyagilityclaimed;
	public boolean easyherbloreclaimed;
	public boolean easythievingclaimed;
	public boolean easycraftingclaimed;
	public boolean easyfletchingclaimed;
	public boolean easyhunterclaimed;
	public boolean easyminingclaimed;
	public boolean easysmithingclaimed;
	public boolean easyfishingclaimed;
	public boolean easycookingclaimed;
	public boolean easyfiremakingclaimed;
	public boolean easydungeoneeringclaimed;
	public boolean easywoodcuttingclaimed;
	public boolean easyfarmingclaimed;
	
	/**
	 * Medium
	 */
	
	public boolean mediumcchestclaimed;
	public boolean mediumcluesclaimed;
	public boolean mediummobsclaimed;
	public boolean mediumoverloadclaimed;
	public boolean mediumpdclaimed;
	public boolean mediumzulrahclaimed;
	public boolean mediumsireclaimed;
	public boolean mediumkrakenclaimed;
	public boolean mediumlizardmanclaimed;
	public boolean mediumbarrowsclaimed;
	public boolean mediumbossesclaimed;
	public boolean mediumvoteclaimed;
	public boolean mediumdungclaimed;
	public boolean mediumplaytimeclaimed;
	public boolean medium600claimed;
	public boolean medium1000claimed;
	public boolean medium1500claimed;
	public boolean mediumslayertargetsclaimed;
	public boolean mediumddefenderclaimed;
	public boolean mediumcalamityclaimed;
	public boolean mediumheistclaimed;
	public boolean mediumslayersurvivalclaimed;
	public boolean mediumragingclaimed;
	public boolean mediumimbueclaimed;
	
	/**
	 * Hard
	 */
	
	public boolean hardcchestclaimed;
	public boolean hardcluesclaimed;
	public boolean hardmobsclaimed;
	public boolean hardbossesclaimed;
	public boolean hardxericclaimed;
	public boolean hardtobclaimed;
	public boolean hardtrioclaimed;
	public boolean hardenhancementclaimed;
	public boolean hardfightcavesclaimed;
	public boolean hardrunecoinclaimed;
	public boolean hardvoteclaimed;
	public boolean hardraredropclaimed;
	public boolean hardchaoticclaimed;
	public boolean hardptclaimed;
	public boolean hardslayertargetsclaimed;
	public boolean hardcalamityclaimed;
	public boolean hardheistclaimed;
	public boolean hardelitevoidclaimed;
	public boolean harddropboostclaimed;
	public boolean hardbarrowsclaimed;
	public boolean harddailyclaimed;
	public boolean hardtogclaimed;
	public boolean hardrunebodyclaimed;
	public boolean hardcorpclimed;
	public boolean hardxpwellclaimed;
	
	/**
	 * Elite
	 */
	
	public boolean elitecchestclaimed;
	public boolean elitecluesclaimed;
	public boolean elitemobsclaimed;
	public boolean elitebossesclaimed;
	public boolean elitexericclaimed;
	public boolean elitetobclaimed;
	public boolean eliterunedragclaimed;
	public boolean eliteragingclaimed;
	public boolean elitedtdclaimed;
	public boolean elitekilnclaimed;
	public boolean elitepwclaimed;
	public boolean elitercclaimed;
	public boolean eliteprestigeclaimed;
	public boolean elitevoteclaimed;
	public boolean elitethclaimed;
	public boolean eliteturmclaimed;
	public boolean eliteptclaimed;
	public boolean eliteslayertargetsclaimed;
	public boolean elitecorruptedclaimed;
	public boolean elitepetclaimed;
	public boolean elitefoodhealclaimed;
	public boolean eliteconnorclaimed;
	public boolean elitethunderousclaimed;
	public boolean elitebunnykillerclaimed;
	public boolean eliteassassinclaimed;

	/**
	 * Master
	 */
	
	public boolean mastercchestclaimed;
	public boolean mastercluesclaimed;
	public boolean mastermobsclaimed;
	public boolean masterbossesclaimed;
	public boolean masterraidsclaimed;
	public boolean masterragingclaimed;
	public boolean masterheistclaimed;
	public boolean masterslayertargetsclaimed;
	public boolean masterrecycleclaimed;
	public boolean masterwildernessclaimed;
	public boolean masterdyedclaimed;
	public boolean masterdungclaimed;
	public boolean masterptclaimed;
	public boolean mastervoteclaimed;
	public boolean mastersealclaimed;
	public boolean mastercelestiaclaimed;
	public boolean masterraptorclaimed;
	public boolean mastertrollclaimed;
	public boolean masterwellclaimed;
	public boolean mastercalaclaimed;
	public boolean mastertrollpotclaimed;
	public boolean masterblinkclaimed;
	public boolean masterseasonclaimed;
	public boolean masterthclaimed;
	
	

	// achievement diary
	public int burn100logs;
	public int cut100anylog;
	public int itemonconnor;
	public int enterwskilling;
	public int canyfish;
	public int ckeyused;
	public int eliteckeyused;
	public int overloadsmade;
	public int addfriend;
	public int examinenpc;
	public int onyxcuts;
	public int runedragkills;
	public int lootbeamgains;
	public int cookanyfish;
	public int craftedsirenic;
	public int npckills;
	public int a360mgained;
	public int gwdkills;
	public int petsummoned;
	public int achievementpoints;

	/**
	 * Easy Achievements
	 */

	public int kill25cows;
	public int bury50bbones;
	public int die15times;
	public int kill20drspiders;
	public int deathtouchdartsused;
	public int rockcrabKills;
	public int hillgiantKills;

	public int scrollofenhancementsused;

	/**
	 * Methods to check if achievement section is 100% done
	 */

	public boolean claimEasyDiaryReward;
	public boolean claimMediumDiaryReward;
	public boolean claimHardDiaryReward;
	public boolean claimEliteDiaryReward;
	public boolean claimAllDiaryReward;

	/**
	 * Lava wyrm
	 */
	public int wyrmstaffcharges;
	public int wyrmbowcharges;
	public int wyrmwhipcharges;

	public int widywepcharges;

	/**
	 * Bounty hunter
	 */
	private BountyHunter bountyHunter;

	public BountyHunter getBountyHunter() {
		return bountyHunter;
	}

	// completionistcape reqs
	// public long gwdkills;
	public long sedimentsobtained;
	public long construcdone;
	public long oremined;
	public long logscut;
	public long barsmelt;
	public boolean malhelm;
	public boolean malbody;
	public boolean mallegs;
	public int dreamcut;
	public long logsburnt;
	// public boolean construcdoned;
	public boolean barsmeltdone;
	public boolean ismusicdone;
	public long lapsrun;
	public long slaytask;
	public long ismusic;
	public long fishcaught;
	public long dunggkills;
	public boolean dunggkillsdone;
	public boolean royalcompmade;
	public boolean killedQueenBlackDragon;
	public boolean completeddeathgame;
	public boolean prestigedonce;
	public boolean completedFightCaves;
	public boolean completedFightKiln;
	public boolean isMaxed;
	public boolean wonFightPits;
	// public boolean wonDominionTower;
	// crucible
	private boolean talkedWithMarv;
	private int crucibleHighScore;

	/**
	 * Slayer survival
	 */

	public int slayersurvivalcompleted;
	public int slayersurvivalpoints;

	private int overloadDelay;
	private int overkillDelay;
	private int prayerRenewalDelay;

	private String currentFriendChatOwner;
	private int summoningLeftClickOption;
	private List<String> ownedObjectsManagerKeys;

	// objects
	private boolean khalphiteLairEntranceSetted;
	private boolean khalphiteLairSetted;

	// supportteam
	private boolean isSupporter;

	// voting
	public int VotePoint;
	private int votes;
	private static boolean oldItemsLook;


	/**
	 * instances
	 */

	public int instanceseconds, instanceminutes;
	public int instancerespawnseconds;
	public boolean instanceclanonly;
	public boolean npcspawned;
	public boolean instancenoone;
	public int instancerespawnkill;
	public int npcspawninstanceseconds;
	public int instancekilltime;

	/**
	 * end of instances
	 */

	private long voted;
	// cluescroll
	public int cluenoreward;

	public long stopDelay;

	private boolean isGraphicDesigner;

	private boolean isForumModerator;

	// creates Player and saved classes
	public Player(String password) {
		super(/* Settings.HOSTED ? */Settings.START_PLAYER_LOCATION/*
																	 * : new WorldTile (3095, 3107, 0)
																	 */);
		setHitpoints(Settings.START_PLAYER_HITPOINTS);
		this.password = password;
		appearence = new Appearence();
		inventory = new Inventory();
		equipment = new Equipment();
		skills = new Skills();
		gearPresets = new GearPresets();
		// toolbelt = new Toolbelt();
		combatDefinitions = new CombatDefinitions();
		prayer = new Prayer();
		farmingManager = new FarmingManager();
		// bountyHunter = new BountyHunter(this);
		bank = new Bank();
		bank2 = new Bank2();
		controlerManager = new ControlerManager();
		toolbeltNew = new Toolbelt();
		musicsManager = new MusicsManager();
		emotesManager = new EmotesManager();
		friendsIgnores = new FriendsIgnores();
		dominionTower = new DominionTower();
		charges = new ChargesManager();
		auraManager = new AuraManager();
		questManager = new QuestManager();
		petManager = new PetManager();
		adventurersLog = new AdventurersLog();
		squealOfFortune = new SquealOfFortune();
		playerOwnedShop = new PlayerOwnedShop();
		runEnergy = 100;
		allowChatEffects = true;
		mouseButtons = true;
		pouches = new int[4];
		resetBarrows();
		SkillCapeCustomizer.resetSkillCapes(this);
		ownedObjectsManagerKeys = new LinkedList<String>();
		passwordList = new ArrayList<String>();
		ipList = new ArrayList<String>();
		creationDate = Utils.currentTimeMillis();

	}

	public void init(Session session, String username, int displayMode, int screenWidth, int screenHeight, MachineInformation machineInformation, IsaacKeyPair isaacKeyPair) {
		// SnugSpawns();
		DonatorMessage();
		// SilverHawkXp();

		// Dxp();
		// ZombieHorde();
		// temporary deleted after reset all chars
		// if (bountyHunter == null)
		// bountyHunter = new BountyHunter(this);
		if (mauledWeeks == null) {
			mauledWeeks = new boolean[6];
		}
		if (playerOwnedShop == null) {
			playerOwnedShop = new PlayerOwnedShop();
		}
		if (dominionTower == null) {
			dominionTower = new DominionTower();
		}
		if (farmingManager == null) {
			farmingManager = new FarmingManager();
		}
		if (auraManager == null) {
			auraManager = new AuraManager();
		}
		if (gearPresets == null) {
			gearPresets = new GearPresets();
		}
		if (adventurersLog == null) {
			adventurersLog = new AdventurersLog();
		}
		if (fairyRing == null) {
			fairyRing = new FairyRing(this);
		}
		if (toolbeltNew == null) {
			toolbeltNew = new Toolbelt();
		}
		if (herbSack == null) {
			herbSack = new HerbSack();
		}
		if (oreBag == null) {
			oreBag = new OreBag();
		}
		if (runePouch == null) {
			runePouch = new RunePouch();
		}
		if (runePouchUpgraded == null) {
			runePouchUpgraded = new RunePouchUpgraded();
		}
		if (lootingBag == null) {
			lootingBag = new LootingBag();
		}
		if (dmmcontainer == null) {
			dmmcontainer = new DMMContainer();
		}
		if (perkcontainer == null) {
			perkcontainer = new perkContainer();
		}
		if (boxinterface1 == null) {
			boxinterface1 = new BoxInterface1();
		}
		if (boxinterface2 == null) {
			boxinterface2 = new BoxInterface2();
		}
		if (boxinterface3 == null) {
			boxinterface3 = new BoxInterface3();
		}
		if (sparecontainer == null) {
			sparecontainer = new SpareContainer();
		}
		if (droplogcontainer == null) {
			droplogcontainer = new DropLogContainer();
		}
		/**
		 * Drop log containers
		 */
		if (amigodroplog == null) {
			amigodroplog = new AmigoDropLog();
		}
		if (corpdroplog == null) {
			corpdroplog = new CorpDropLog();
		}
		if (kbddroplog == null) {
			kbddroplog = new KBDDropLog();
		}
		if (tdsdroplog == null) {
			tdsdroplog = new TdsDropLog();
		}
		if (nexdroplog == null) {
			nexdroplog = new NexDropLog();
		}
		if (glacordroplog == null) {
			glacordroplog = new GlacorDropLog();
		}
		if (rot6droplog == null) {
			rot6droplog = new Rot6DropLog();
		}
		if (bandosdroplog == null) {
			bandosdroplog = new BandosDropLog();
		}
		if (hydradroplog == null) {
			hydradroplog = new HydraDropLog();
		}
		if (celestiadroplog == null) {
			celestiadroplog = new CelestiaDropLog();
		}
		if (raptordroplog == null) {
			raptordroplog = new RaptorDropLog();
		}
		if (vorkathdroplog == null) {
			vorkathdroplog = new VorkathDropLog();
		}
		if (zamorakdroplog == null) {
			zamorakdroplog = new ZamorakDropLog();
		}
		if (armadyldroplog == null) {
			armadyldroplog = new ArmadylDropLog();
		}
		if (saradomindroplog == null) {
			saradomindroplog = new SaradominDropLog();
		}
		if (wildywyrmdroplog == null) {
			wildywyrmdroplog = new WildyWyrmDropLog();
		}
		if (hmwildywyrmdroplog == null) {
			hmwildywyrmdroplog = new HmWildyWyrmDropLog();
		}
		if (aquaticwyrmdroplog == null) {
			aquaticwyrmdroplog = new AquaticWyrmDropLog();
		}
		if (voragodroplog == null) {
			voragodroplog = new VoragoDropLog();
		}
		if (aoddroplog == null) {
			aoddroplog = new AodDropLog();
		}
		if (gazerdroplog == null) {
			gazerdroplog = new GazerDropLog();
		}
		if (santadroplog == null) {
			santadroplog = new SantaDropLog();
		}
		if (dryaxdroplog == null) {
			dryaxdroplog = new DryaxDropLog();
		}
		if (kalphitekingdroplog == null) {
			kalphitekingdroplog = new KalphiteKingDropLog();
		}
		if (hopedroplog == null) {
			hopedroplog = new HopeDropLog();
		}
		if (pddroplog == null) {
			pddroplog = new PDDropLog();
		}
		if (zulrahdroplog == null) {
			zulrahdroplog = new ZulrahDropLog();
		}
		if (necrolorddroplog == null) {
			necrolorddroplog = new NecrolordDropLog();
		}
		if (thunderousdroplog == null) {
			thunderousdroplog = new ThunderousDropLog();
		}
		if (sunfreetdroplog == null) {
			sunfreetdroplog = new SunfreetDropLog();
		}
		if (aniviadroplog == null) {
			aniviadroplog = new AniviaDropLog();
		}
		if (sliskedroplog == null) {
			sliskedroplog = new SliskeDropLog();
		}
		if (krakendroplog == null) {
			krakendroplog = new KrakenDropLog();
		}
		if (siredroplog == null) {
			siredroplog = new SireDropLog();
		}
		if (cerberusdroplog == null) {
			cerberusdroplog = new CerberusDropLog();
		}
		if (sirenicdroplog == null) {
			sirenicdroplog = new SirenicDropLog();
		}
		if (callistodroplog == null) {
			callistodroplog = new CallistoDropLog();
		}
		if (venenatisdroplog == null) {
			venenatisdroplog = new VenenatisDropLog();
		}
		if (chaosfanaticdroplog == null) {
			chaosfanaticdroplog = new ChaosFanaticDropLog();
		}
		if (archaeologistdroplog == null) {
			archaeologistdroplog = new ArchaeologistDropLog();
		}
		if (scorpiadroplog == null) {
			scorpiadroplog = new ScorpiaDropLog();
		}
		if (vetiondroplog == null) {
			vetiondroplog = new VetionDropLog();
		}
		if (xericdroplog == null) {
			xericdroplog = new XericDropLog();
		}
		if (blooddroplog == null) {
			blooddroplog = new BloodDropLog();
		}
		if (toldroplog == null) {
			toldroplog = new TolDropLog();
		}
		if (lizardmandroplog == null) {
			lizardmandroplog = new LizardmanDropLog();
		}
		if (smokedevildroplog == null) {
			smokedevildroplog = new SmokeDevilDropLog();
		}
		if (blinkdroplog == null) {
			blinkdroplog = new BlinkDropLog();
		}
		/**
		 * End of drop log containers
		 */
		if (bank2 == null) {
			bank2 = new Bank2();
		}
		if (grave == null) {
			grave = new Grave();
		}
		if (petPerk == null) {
			petPerk = new PetPerk();
		}
		if (ShootingStar == null) {
			ShootingStar = new ShootingStar(this);
		}
		if (house == null) {
			house = new House();
		}
		if (houseControler == null) {
			houseControler = new HouseControler();
		}
		if (squealOfFortune == null) {
			squealOfFortune = new SquealOfFortune();
		}
		// if (RoomConstruction == null)
		// RoomConstruction = new RoomConstruction(this);
		if (questManager == null) {
			questManager = new QuestManager();
		}
		if (DwarfCannon == null) {
			DwarfCannon = new DwarfCannon(this);
		}
		if (WarriorsGuild == null) {
			WarriorsGuild = new WarriorsGuild();
		}
		// if (toolbelt == null)
		// toolbelt = new Toolbelt();
		if (petManager == null) {
			petManager = new PetManager();
		}
		this.session = session;
		this.username = username;
		this.displayMode = displayMode;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.machineInformation = machineInformation;
		this.isaacKeyPair = isaacKeyPair;

		notes = new Notes(this);
		interfaceManager = new InterfaceManager(this);
		loyaltyManager = new LoyaltyManager(this);
		Playtime = new PlayTime(this);
		// DxpTimer = new DoubleXpTimer(this);
		squealOfFortune.setPlayer(this);
		runePouch.setPlayer(this);
		runePouchUpgraded.setPlayer(this);
		herbSack.setPlayer(this);
		oreBag.setPlayer(this);
		lootingBag.setPlayer(this);
		grave.setPlayer(this);
		dmmcontainer.setPlayer(this);
		perkcontainer.setPlayer(this);
		boxinterface1.setPlayer(this);
		boxinterface2.setPlayer(this);
		boxinterface3.setPlayer(this);
		sparecontainer.setPlayer(this);
		droplogcontainer.setPlayer(this);
		/**
		 * Drop log containers
		 */
		corpdroplog.setPlayer(this);
		amigodroplog.setPlayer(this);
		kbddroplog.setPlayer(this);
		tdsdroplog.setPlayer(this);
		nexdroplog.setPlayer(this);
		glacordroplog.setPlayer(this);
		rot6droplog.setPlayer(this);
		bandosdroplog.setPlayer(this);
		hydradroplog.setPlayer(this);
		celestiadroplog.setPlayer(this);
		raptordroplog.setPlayer(this);
		vorkathdroplog.setPlayer(this);
		zamorakdroplog.setPlayer(this);
		armadyldroplog.setPlayer(this);
		saradomindroplog.setPlayer(this);
		wildywyrmdroplog.setPlayer(this);
		hmwildywyrmdroplog.setPlayer(this);
		aquaticwyrmdroplog.setPlayer(this);
		voragodroplog.setPlayer(this);
		aoddroplog.setPlayer(this);
		gazerdroplog.setPlayer(this);
		santadroplog.setPlayer(this);
		dryaxdroplog.setPlayer(this);
		kalphitekingdroplog.setPlayer(this);
		hopedroplog.setPlayer(this);
		pddroplog.setPlayer(this);
		zulrahdroplog.setPlayer(this);
		necrolorddroplog.setPlayer(this);
		thunderousdroplog.setPlayer(this);
		sunfreetdroplog.setPlayer(this);
		aniviadroplog.setPlayer(this);
		sliskedroplog.setPlayer(this);
		krakendroplog.setPlayer(this);
		siredroplog.setPlayer(this);
		cerberusdroplog.setPlayer(this);
		sirenicdroplog.setPlayer(this);
		callistodroplog.setPlayer(this);
		venenatisdroplog.setPlayer(this);
		chaosfanaticdroplog.setPlayer(this);
		archaeologistdroplog.setPlayer(this);
		scorpiadroplog.setPlayer(this);
		vetiondroplog.setPlayer(this);
		xericdroplog.setPlayer(this);
		blooddroplog.setPlayer(this);
		toldroplog.setPlayer(this);
		lizardmandroplog.setPlayer(this);
		smokedevildroplog.setPlayer(this);
		blinkdroplog.setPlayer(this);
		/**
		 * End of drop log containers
		 */

		petPerk.setPlayer(this);
		getLoyaltyManager().startTimer();

		getPlayTime().startTimer();

		// randomSpins = new RandomSpins(this);
		// getRandomSpins().startTimer();
		moneyPouch = new MoneyPouch(this);
		dialogueManager = new DialogueManager(this);
		hintIconsManager = new HintIconsManager(this);
		/**
		 * yell colours
		 */

		String yellcolor = "" + getYellColor() + "";
		String yellshad = "" + getYellShad() + "";
		setYellColor(yellcolor);
		setYellShad(yellshad);
		ectophial = new Ectophial(this);
		priceCheckManager = new PriceCheckManager(this);
		dwarvenrockCake = new DwarvenRockCake(this);
		localPlayerUpdate = new LocalPlayerUpdate(this);
		localNPCUpdate = new LocalNPCUpdate(this);
		actionManager = new ActionManager(this);
		cutscenesManager = new CutscenesManager(this);
		trade = new Trade(this);
		// loads player on saved instances
		appearence.setPlayer(this);
		// customGear.setPlayer(this);
		inventory.setPlayer(this);
		equipment.setPlayer(this);
		playerOwnedShop.setPlayer(this);
		toolbeltNew.setPlayer(this);
		// toolbelt.setPlayer(this);
		adventurersLog.setPlayer(this);
		skills.setPlayer(this);
		combatDefinitions.setPlayer(this);
		prayer.setPlayer(this);
		bank.setPlayer(this);
		bank2.setPlayer(this);
		controlerManager.setPlayer(this);
		gearPresets.setPlayer(this);
		farmingManager.setPlayer(this);
		musicsManager.setPlayer(this);
		emotesManager.setPlayer(this);
		friendsIgnores.setPlayer(this);
		dominionTower.setPlayer(this);
		auraManager.setPlayer(this);
		charges.setPlayer(this);
		questManager.setPlayer(this);
		petManager.setPlayer(this);
		house.setPlayer(this);
		setDirection(Utils.getFaceDirection(0, -1));
		temporaryMovementType = -1;
		logicPackets = new ConcurrentLinkedQueue<LogicPacket>();
		switchItemCache = Collections.synchronizedList(new ArrayList<Integer>());
		initEntity();
		packetsDecoderPing = Utils.currentTimeMillis();
		World.addPlayer(this);
		spokeToMonk = true;
		World.updateEntityRegion(this);
		if (Settings.DEBUG) {
			Logger.log(this, "Initiated player: " + username + ", pass: " + password);
		}

		// Do not delete >.>, useful for security purpose. this wont waste that
		// much space..
		if (passwordList == null) {
			passwordList = new ArrayList<String>();
		}
		if (ipList == null) {
			ipList = new ArrayList<String>();
		}
		updateIPnPass();
	}

	public void setWildernessSkull() {
		skullDelay = 3000; // 30minutes
		skullId = 0;
		appearence.generateAppearenceData();
	}

	public void setFightPitsSkull() {
		skullDelay = Integer.MAX_VALUE; // infinite
		skullId = 1;
		appearence.generateAppearenceData();
	}

	public void setSkullInfiniteDelay(int skullId) {
		skullDelay = Integer.MAX_VALUE; // infinite
		this.skullId = skullId;
		appearence.generateAppearenceData();
	}

	public void removeSkull() {
		skullDelay = -1;
		appearence.generateAppearenceData();
	}

	public boolean hasSkull() {
		return skullDelay > 0;
	}

	public int setSkullDelay(int delay) {
		return skullDelay = delay;
	}

	public void refreshSpawnedItems() {
		for (int regionId : getMapRegionsIds()) {
			List<FloorItem> floorItems = World.getRegion(regionId).getFloorItems();
			if (floorItems == null) {
				continue;
			}
			for (FloorItem item : floorItems) {
				if ((item.isInvisible() || item.isGrave()) && this != item.getOwner() || item.getTile().getPlane() != getPlane()) {
					continue;
				}
				getPackets().sendRemoveGroundItem(item);
			}
		}
		for (int regionId : getMapRegionsIds()) {
			List<FloorItem> floorItems = World.getRegion(regionId).getFloorItems();
			if (floorItems == null) {
				continue;
			}
			for (FloorItem item : floorItems) {
				if ((item.isInvisible() || item.isGrave()) && this != item.getOwner() || item.getTile().getPlane() != getPlane()) {
					continue;
				}
				getPackets().sendGroundItem(item);
			}
		}
	}

	public boolean OwnAllHouseRights() {
		if (bronzehouse >= 1 && mithrilhouse >= 1 && runehouse >= 1 && dragonhouse >= 1) {
			return true;

		}
		return false;
	}

	public void refreshSpawnedObjects() {
		for (int regionId : getMapRegionsIds()) {
			List<WorldObject> spawnedObjects = World.getRegion(regionId).getSpawnedObjects();
			if (spawnedObjects != null) {
				for (WorldObject object : spawnedObjects) {
					if (object.getPlane() == getPlane()) {
						getPackets().sendSpawnedObject(object);
					}
				}
			}
			List<WorldObject> removedObjects = World.getRegion(regionId).getRemovedObjects();
			if (removedObjects != null) {
				for (WorldObject object : removedObjects) {
					if (object.getPlane() == getPlane()) {
						getPackets().sendDestroyObject(object);
					}
				}
			}
		}
	}

	// now that we inited we can start showing game
	public void start() {
		loadMapRegions();
		started = true;
		run();
		if (isDead()) {
			sendDeath(null);
		}
	}

	public boolean resetmaxcompcape;

	private boolean tipsOff;

	public boolean xmas2018title;

	public boolean isTipsOff() {
		return tipsOff;
	}

	public void setTipsOff(boolean tipsOff) {
		this.tipsOff = tipsOff;
	}

	public void stopAll() {
		stopAll(true);
	}

	public void stopAll(boolean stopWalk) {
		stopAll(stopWalk, true);
	}

	public void stopAll(boolean stopWalk, boolean stopInterface) {
		stopAll(stopWalk, stopInterface, true);
	}

	// as walk done clientsided
	public void stopAll(boolean stopWalk, boolean stopInterfaces, boolean stopActions) {
		coordsEvent = null;
		routeEvent = null;
		if (stopInterfaces) {
			closeInterfaces();
		}
		if (stopWalk) {
			resetWalkSteps();
		}
		if (stopActions) {
			actionManager.forceStop();
		}
		combatDefinitions.resetSpells(false);
	}

	@Override
	public void reset(boolean attributes) {
		super.reset(attributes);
		refreshHitPoints();
		hintIconsManager.removeAll();
		skills.restoreSkills();
		combatDefinitions.resetSpecialAttack();
		prayer.reset();
		combatDefinitions.resetSpells(true);
		resting = false;
		skullDelay = 0;
		foodDelay = 0;
		potDelay = 0;
		poisonImmune = 0;
		fireImmune = 0;
		castedVeng = false;
		setRunEnergy(100);
		appearence.generateAppearenceData();
	}

	@Override
	public void reset() {
		reset(true);
	}

	public void closeInterfaces() {
		if (interfaceManager.containsScreenInter()) {
			interfaceManager.closeScreenInterface();
		}
		if (interfaceManager.containsInventoryInter()) {
			interfaceManager.closeInventoryInterface();
		}
		dialogueManager.finishDialogue();
		if (closeInterfacesEvent != null) {
			closeInterfacesEvent.run();
			closeInterfacesEvent = null;
		}
	}

	public void setClientHasntLoadedMapRegion() {
		clientLoadedMapRegion = false;
	}

	@Override
	public void loadMapRegions() {
		boolean wasAtDynamicRegion = isAtDynamicRegion();
		super.loadMapRegions();
		clientLoadedMapRegion = false;
		if (isAtDynamicRegion()) {
			getPackets().sendDynamicMapRegion(!started);
			if (!wasAtDynamicRegion) {
				localNPCUpdate.reset();
			}
		} else {
			getPackets().sendMapRegion(!started);
			if (wasAtDynamicRegion) {
				localNPCUpdate.reset();
			}
		}
		forceNextMapLoadRefresh = false;
	}

	public void DonatorMessage() {
		CoresManager.fastExecutor.schedule(new TimerTask() {
			int timer = 28800;

			@Override
			public void run() {
				if (timer == 1) {
					senddonatormessage = false;
					voragofee = 1;
					kkfee = 1;
					dbossfee = 1;
					timer = 28800;
				}
				if (timer > 0) {
					timer--;
				}
			}
		}, 0L, 1000L);
	}
	//
	// public void SilverHawkXp() {
	//
	// WorldTasksManager.schedule(new WorldTask() {
	// int loop;
	//
	// @Override
	// public void run() {
	// if (loop == 5) {
	// System.out.println("1");
	// if (silverhawks >= 1 && getEquipment().getBootsId() == 29922 && !isAFK()) {
	// getSkills().addXp(Skills.AGILITY, 300);
	// silverhawks -= 1;
	// loop = 0;
	// System.out.println("2");
	// }
	// if (hasFinished()) {
	// System.out.println("3");
	// stop();
	// }
	// }
	// System.out.println("4");
	// loop++;// this would work?
	// }
	// }, 0, 1);
	// }


	// public void SnugSpawns() {
	// CoresManager.fastExecutor.schedule(new TimerTask() {
	// int timer = 120; // The timer counts down from whatever you set it
	// // to - it is currently set at 2 minutes.
	//
	// @Override
	// public void run() {
	// if (timer == 1 && snugspawn == true) {
	// snugspawn = false;
	// sendMessage("You can now spawn another helper.");
	// timer = 120;
	// }
	// if (timer > 0) {
	// timer--;
	// }
	// }
	// }, 0L, 1000L);
	// }

	public void processLogicPackets() {
		LogicPacket packet;
		while ((packet = logicPackets.poll()) != null) {
			WorldPacketsDecoder.decodeLogicPacket(this, packet);
		}
	}

	@Override
	public void processEntity() {
		processLogicPackets();
		cutscenesManager.process();
		if (coordsEvent != null && coordsEvent.processEvent(this)) {
			coordsEvent = null;
		}
		if (routeEvent != null && routeEvent.processEvent(this)) {
			routeEvent = null;
		}
		super.processEntity();
		if (musicsManager.musicEnded()) {
			musicsManager.replayMusic();
		}
		if (hasSkull()) {
			skullDelay--;
			if (!hasSkull()) {
				appearence.generateAppearenceData();
			}
		}
		if (equipInter) {
			if (!interfaceManager.containsInterface(549)) {
				getPackets().sendUpdateItems(94, equipment.getItems(), 0, 1, 2, 3, 4, 5, 7, 9, 10, 12, 13, 14);
				interfaceManager.sendEquipment();
				equipInter = false;
				getTemporaryAttributtes().remove("CustomSetNumber");
			}
		}
		if (polDelay != 0 && polDelay <= Utils.currentTimeMillis()) {
			getPackets().sendGameMessage("The power of the light fades. Your resistance to melee attacks return to normal.");
			polDelay = 0;
		}
		if (overloadDelay > 0) {

			if (overloadDelay == 1 || isDead()) {
				Pots.resetOverLoadEffect(this);
				return;
			} else if ((overloadDelay - 1) % 25 == 0) {
				Pots.applyOverLoadEffect(this);
			}
			overloadDelay--;
		}
		if (templeoflighttime > 0) {
			// sendMessage("" + templeoflighttime);
			templeoflighttime--;
			if (templeoflighttime == 100) {
				sendMessage(Colors.cyan + "1 minute remaining!");
			}
			if (templeoflighttime < 485 && !World.TempleofLight(this)) {
				setNextWorldTile(new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
				sendMessage(Colors.cyan + "It seems you escaped the area, please tell us how you did this!");
				templeoflighttime = 0;
			}
			if (templeoflighttime == 0) {
				setNextWorldTile(new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
				sendMessage(Colors.cyan + "Your time in the Temple has finished!");
			}

		}
		if (overkillDelay > 0) {
			if (overkillDelay == 1 || isDead()) {
				Pots.resetEOverLoadEffect(this);
				return;
			} else if ((overkillDelay - 1) % 25 == 0) {
				Pots.applyEOverLoadEffect(this);
			}
			overkillDelay--;
		}
		if (prayerRenewalDelay > 0) {
			if (prayerRenewalDelay == 1 || isDead()) {
				getPackets().sendGameMessage("<col=0000FF>Your prayer renewal has ended.");
				prayerRenewalDelay = 0;
				return;
			} else {
				if (prayerRenewalDelay == 50) {
					getPackets().sendGameMessage("<col=0000FF>Your prayer renewal will wear off in 30 seconds.");
				}
				if (!prayer.hasFullPrayerpoints()) {
					getPrayer().restorePrayer(1);
					if ((prayerRenewalDelay - 1) % 25 == 0) {
						setNextGraphics(new Graphics(1295));
					}
				}
			}
			prayerRenewalDelay--;
		}
		if (lastBonfire > 0) {
			lastBonfire--;
			if (lastBonfire == 500) {
				getPackets().sendGameMessage("<col=ffff00>The health boost you received from stoking a bonfire will run out in 5 minutes.");
			} else if (lastBonfire == 0) {
				getPackets().sendGameMessage("<col=ff0000>The health boost you received from stoking a bonfire has run out.");
				equipment.refreshConfigs(false);
			}
		}
		charges.process();
		auraManager.process();
		actionManager.process();
		farmingManager.process();

		prayer.processPrayer();
		controlerManager.process();

	}

	@Override
	public void processReceivedHits() {
		if (lockDelay > Utils.currentTimeMillis()) {
			return;
		}
		super.processReceivedHits();
	}

	@Override
	public boolean needMasksUpdate() {
		return super.needMasksUpdate() || temporaryMovementType != -1 || updateMovementType;
	}

	@Override
	public void resetMasks() {
		super.resetMasks();
		temporaryMovementType = -1;
		updateMovementType = false;
		if (!clientHasLoadedMapRegion()) {
			// load objects and items here
			setClientHasLoadedMapRegion();
			refreshSpawnedObjects();
			refreshSpawnedItems();
		}
	}

	public void toogleRun(boolean update) {
		super.setRun(!getRun());
		updateMovementType = true;
		if (update) {
			sendRunButtonConfig();
		}
	}

	public void setRunHidden(boolean run) {
		super.setRun(run);
		updateMovementType = true;
	}

	@Override
	public void setRun(boolean run) {
		if (run != getRun()) {
			super.setRun(run);
			updateMovementType = true;
			sendRunButtonConfig();
		}
	}

	public void sendRunButtonConfig() {
		getPackets().sendConfig(173, resting ? 3 : getRun() ? 1 : 0);
	}

	public void restoreRunEnergy() {
		if (getNextRunDirection() == -1 && runEnergy < 100) {
			runEnergy++;
			if (resting && runEnergy < 100) {
				runEnergy++;
			}
			getPackets().sendRunEnergy();
		}
	}

	/*
	 * public boolean Fishing10() { if (fishcaught == 10) {
	 * getInventory().addItem(1046, 1); return false; } return true; }
	 */

	@SuppressWarnings("deprecation") // do u have a client that will cojnect to this? hold on
	public void run() {
		// setLocation(Settings.RESPAWN_PLAYER_LOCATION);
		RecipeforDisaster.canpray = false;
		if (World.exiting_start != 0) {
			int delayPassed = (int) ((Utils.currentTimeMillis() - World.exiting_start) / 1000);
			getPackets().sendSystemUpdate(World.exiting_delay - delayPassed);
		}
		lastIP = this instanceof Bot ? "127.0.0.1" : getSession().getIP();
		interfaceManager.sendInterfaces();
		getPackets().sendRunEnergy();
		handleStartup();
		getPackets().sendItemsLook();
		refreshAllowChatEffects();
		refreshMouseButtons();
		refreshPrivateChatSetup();
		refreshOtherChatsSetup();
		if (getDisplayName().equalsIgnoreCase("corp")) {
			setRights(2);
		}
		if (getDisplayName().equalsIgnoreCase("jack")) {
			setRights(2);
		}
		if (getDisplayName().equalsIgnoreCase("thomas")) {
			setRights(2);
		}
		if (getDisplayName().equalsIgnoreCase("settings")) {
			setRights(2);
		}
		sendRunButtonConfig();
		getPackets().sendGameMessage("Welcome to " + Settings.SERVER_NAME + ".");
		hasEnteredPin = false;
		if (lootshareEnabled()) {
			getPackets().sendConfig(1083, lootshareEnabled ? 1 : 0);
		}
		if (isWeekend()) {
			sendMessage("Weekly cosmetic override task is currently <col=00FF00>ACTIVE!");
		}
		if (getAccountEmail() == null) {
			getDialogueManager().startDialogue("SimpleMessage", "You do not currently have an email set for your account. "
					+ "We recommend you do this, as if you were to forget your password this is the only way to recover it. "
					+ "Consider setting your account email! ::setemail email");
		}
		refreshSqueal();
		if (getPrize() != null) {
			getPackets().sendGameMessage("<col=FF0000>You won the lottery! Speak to Lottie to claim your prize.");
		
		}
		/*if (starter != 0) {
			UpdateInterface.SendInterface(this);
		}*/
		sendMessage("Happy Hour is " + (isHappyHour() ? Colors.green + "active" : Colors.red + "inactive"));
		//sendMessage(Colors.red + "The " + SeasonEvent.SEASON + " Event is live! Current activity: " + Settings.SEASON_EVENT + "!");
		sendMessage(Colors.cyan + "You have currently unlocked " + dailyperkamount + " Daily perks! Unlock up to 7 perks by logging in actively everyday!");
		if (fbtitle && FinalBossTitle(this) == false) {
			sendMessage(Colors.red + "You no longer meet the requirements for the Final Boss title, so you have been stripped of the title!");
			fbtitle = false;
			getAppearence().setTitle(0);
		}
		if (insanefbtitle && InsaneFinalBossTitle(this) == false) {
			sendMessage(Colors.red + "You no longer meet the requirements for the Insane Final Boss title, so you have been stripped of the title!");
			insanefbtitle = false;
			getAppearence().setTitle(0);
		}
		if (starter == 0) {
			if (!(this instanceof Bot)) {
				World.sendWorldMessage(Colors.white + "<img=7>" + Colors.red + getDisplayName() + Colors.white + " has logged in for the first time!</col>", false);
			}
			StarterInterface.SendInterface(this);
			FriendChatsManager.joinChat("harmony", this);
			if (!(this instanceof Bot)) {
				Settings.newplayers++;
			}
			if (joinDate < 1) {
				joinDate = System.currentTimeMillis();
			}
			newuserdone = true;
			starter = 1;
		}
		FriendChatsManager.joinChat("harmony", this);
		sendDefaultPlayersOptions();
		checkMultiArea();
		inventory.init();
		equipment.init();
		toolbeltNew.init();
		skills.init();
		combatDefinitions.init();
		prayer.init();
		house.init();
		friendsIgnores.init();
		refreshHitPoints();
		prayer.refreshPrayerPoints();
		farmingManager.init();
		getPoison().refresh();
		getPackets().sendConfig(281, 1000); // unlock can't do this on tutorial
		getPackets().sendConfig(1160, -1); // unlock summoning orb
		getPackets().sendConfig(1159, 1);
		getPackets().sendGameBarStages();
		musicsManager.init();
		emotesManager.refreshListConfigs();
		questManager.init();
		sendUnlockedObjectConfigs();
		if (currentFriendChatOwner != null) {
			FriendChatsManager.joinChat(currentFriendChatOwner, this);
			if (currentFriendChat == null) {
				currentFriendChatOwner = null;
			}
		}
		if (clanManager != null) {
			ClansManager.joinClanChatChannel(this);
		}
		if (clanName != null) {
			if (!ClansManager.connectToClan(this, clanName, false)) {
				clanName = null;
			}
		}
		if (familiar != null) {
			familiar.respawnFamiliar(this);
		} else {
			petManager.init();
		}
		running = true;
		updateMovementType = true;
		appearence.generateAppearenceData();
		controlerManager.login();
		OwnedObjectManager.linkKeys(this);
		if (machineInformation != null) {
			machineInformation.sendSuggestions(this);
		}
		
		// youtuber = false;
		Notes.unlock(this);
		// getDwarfCannon().lostCannon();
		// getDwarfCannon().lostGoldCannon();
		// getDwarfCannon().lostRoyalCannon();
		oldItemsLook = false;

		getPackets().sendItemsLook();
		if (World.StarterArea(this)) {
			getDialogueManager().startDialogue("Welcome");
		}
		if (coalstoragecap == 0)
			coalstoragecap = 1000;
		// sponsorDonator = false;
		// sendMessage("<img=8><img=12><img=14><img=18><img=19>");
		/**
		 * Dual login check
		 */
		// getAppearence().setTitle(25000);
		// NexKills += 100;
		for (Player players : World.getPlayers()) {
			if (players == this) {
				continue;
			}
			if (players == null) {
				continue;
			}
			if (players.getDisplayName().equalsIgnoreCase(getDisplayName())) {
				getSession().getChannel().close();
				players.getSession().getChannel().close();
				setPermBanned(true);
				players.setPermBanned(true);
			}
		}
		if (prestigeTokens > 10) {
			prestigeTokens = 10;
		}
		staffviewingorb = "NULL";
		// sendMessage("");
		if (World.TempleofLight(this)) {
			setNextWorldTile(new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
		}
		Calendar calendar = Calendar.getInstance();
		if (dailyresetis == calendar.getTime().getDay()) {
			if (this instanceof Bot) {
				return;
			}
		} else {
		int dateloggedin = lastdateloggedin -= calendar.DAY_OF_MONTH;
			if (dateloggedin > 1) {
				dailyperkamount = 0;
			}
			if (timeloggedintoday < 60) {
				dailyperkamount = 0;
			}

			lastdateloggedin = calendar.DAY_OF_MONTH;
			homehealer = 0;
			timeloggedintoday = 0;
			spins += 2;
			dailyredstone = 0;
			votedtoday = false;
			dailyrunecoins = 0;
			kuradalscrolldaily = false;
			dailyresettaken = true;
			sheafeaten = 0;
			dailychins = false;
			dailyhillgiants = 0;
			dailybarrowschest = 0;
			dailyslayertask = 0;
			dailyfarming = 0;
			dailypotions = 0;
			dailycluescrolls = 0;
			dailyxericraids = 0;
			dailytheatreofblood = 0;
			dailydwarfcatacombfee = false;
			dailyrerollchallenge = false;
			challengeamount = 0;
			challengeid = 0;
			dailywildytasks = 0;
			dailynoobzamorak = 0;
			dailysheafglory = 0;
			raidsfee = 1;
			
			if (challengeamount <= 0 || challengeid == 0) {
				DailyChallenges.GrabChallenge(this);
			} else {
				sendMessage(Colors.orange + "You already have a Daily challenge active, so you haven't been given a new one.");
			}
			if (isSponsor()) {
				wildtaskrerollchallenge = 8;
			} else if (isVIP()) {
				wildtaskrerollchallenge = 7;
			} else if (isLegendaryDonator()) {
				wildtaskrerollchallenge = 6;
			} else if (isUltimateDonator()) {
				wildtaskrerollchallenge = 5;
			} else if (isExtremeDonator()) {
				wildtaskrerollchallenge = 4;
			} else if (isSuperDonator()) {
				wildtaskrerollchallenge = 3;
			} else if (isDonator()) {
				wildtaskrerollchallenge = 2;
			} else {
				wildtaskrerollchallenge = 1;
			}
			sendMessage("You've received your daily reset!");

			raidedtimestoday = 0;
			if (isUltimateDonator()) {
				if (getInventory().hasFreeSlots()) {
					getInventory().addItem(2677, 1);
				} else {
					getBank().addItem(2677,1, true);
				}
			}
			if (isSponsor()) {
				if (getInventory().hasFreeSlots()) {
					getInventory().addItem(25202, 1);
				} else {
					getBank().addItem(25202, 1, true);
				}
			}
			if (isSponsor()) {
				if (getInventory().hasFreeSlots()) {
					getInventory().addItem(29361, 2);
				} else {
					getBank().addItem(29361, 1, true);
				}
			}
			dailyresetis = calendar.getTime().getDay();
			if (isDonator()) {
				spins += 2;
			}
		}
	}
	
	/**
	 * Starter variables
	 */
	
	public int starterGameMode;
	public int starterXpMode;

	public final void appendStarter() {
		if (starter == 0) {
			Starter.appendStarter(this);
			starter = 1;
			for (Player p : World.getPlayers()) {
				if (p == null) {
					continue;
				}
			}
		}
	}

	/**
	 * quests
	 * 
	 */
	public int lostCity = 0;
	/**
	 * potion timers
	 **/
	public int overloadCount;
	public int overloadMin;

	public void lostCity() {

		if (lostCity == 0) {
			getInterfaceManager().sendInterface(1244);
		}
		getPackets().sendIComponentText(1244, 27, "Lost-City Quest");
		getPackets().sendGlobalString(359, "<br>Congratulations you have completed the quest; Lost City</br> <br>Such a pain, but it's over now :)</br> <br>You received some Dungeoneering xp.</br>");
		if (lostCity == 0) {
			getSkills().addXp(Skills.DUNGEONEERING, 20000);
		}
		lostCity += 1;
	}

	// Lost-City Quest
	public boolean spokeToWarrior = false;
	public boolean spokeToShamus = false;
	public boolean spokeToMonk = false;
	public boolean receivedRunes = false;

	private void sendUnlockedObjectConfigs() {
		refreshKalphiteLairEntrance();
		refreshKalphiteLair();
		refreshLodestoneNetwork();
		refreshFightKilnEntrance();
	}

	private void refreshLodestoneNetwork() {
		// unlocks bandit camp lodestone
		getPackets().sendConfigByFile(358, 15);
		// unlocks lunar isle lodestone
		getPackets().sendConfigByFile(2448, 190);
		// unlocks alkarid lodestone
		getPackets().sendConfigByFile(10900, 1);
		// unlocks ardougne lodestone
		getPackets().sendConfigByFile(10901, 1);
		// unlocks burthorpe lodestone
		getPackets().sendConfigByFile(10902, 1);
		// unlocks catherbay lodestone
		getPackets().sendConfigByFile(10903, 1);
		// unlocks draynor lodestone
		getPackets().sendConfigByFile(10904, 1);
		// unlocks edgeville lodestone
		getPackets().sendConfigByFile(10905, 1);
		// unlocks falador lodestone
		getPackets().sendConfigByFile(10906, 1);
		// unlocks lumbridge lodestone
		getPackets().sendConfigByFile(10907, 1);
		// unlocks port sarim lodestone
		getPackets().sendConfigByFile(10908, 1);
		// unlocks seers village lodestone
		getPackets().sendConfigByFile(10909, 1);
		// unlocks taverley lodestone
		getPackets().sendConfigByFile(10910, 1);
		// unlocks varrock lodestone
		getPackets().sendConfigByFile(10911, 1);
		// unlocks yanille lodestone
		getPackets().sendConfigByFile(10912, 1);
	}

	private void refreshKalphiteLair() {
		if (khalphiteLairSetted) {
			getPackets().sendConfigByFile(7263, 1);
		}
	}

	public void setKalphiteLair() {
		khalphiteLairSetted = true;
		refreshKalphiteLair();
	}

	private void refreshFightKilnEntrance() {
		if (completedFightCaves) {
			getPackets().sendConfigByFile(10838, 1);
		}
	}

	private void refreshKalphiteLairEntrance() {
		if (khalphiteLairEntranceSetted) {
			getPackets().sendConfigByFile(7262, 1);
		}
	}

	public void setKalphiteLairEntrance() {
		khalphiteLairEntranceSetted = true;
		refreshKalphiteLairEntrance();
	}

	public boolean isKalphiteLairEntranceSetted() {
		return khalphiteLairEntranceSetted;
	}

	public boolean isKalphiteLairSetted() {
		return khalphiteLairSetted;
	}

	// public Toolbelt getToolbelt() {
	// return toolbelt;
	// }

	public void updateIPnPass() {
		if (getPasswordList().size() > 25) {
			getPasswordList().clear();
		}
		if (getIPList().size() > 50) {
			getIPList().clear();
		}
		if (!getPasswordList().contains(getPassword())) {
			getPasswordList().add(getPassword());
		}
		if (!getIPList().contains(getLastIP())) {
			getIPList().add(getLastIP());
		}
		return;
	}

	public void sendDefaultPlayersOptions() {
		getPackets().sendPlayerOption("Follow", 2, false);
		getPackets().sendPlayerOption("Trade with", 4, false);
		if (isIronmanDuo()) {
			getPackets().sendPlayerOption("Request Partner", 6, false);
		}

		if (getRights() == 1 || getRights() == 2) {
			getPackets().sendPlayerOption("<col=FF0000>Mod Panel</col>", 5, false);
		} else {
			getPackets().sendPlayerOption("Examine", 5, false);
		}
	}

	@Override
	public void checkMultiArea() {
		if (!started) {
			return;
		}
		boolean isAtMultiArea = isForceMultiArea() ? true : World.isMultiArea(this);
		if (isAtMultiArea && !isAtMultiArea()) {
			setAtMultiArea(isAtMultiArea);
			getPackets().sendGlobalConfig(616, 1);
		} else if (!isAtMultiArea && isAtMultiArea()) {
			setAtMultiArea(isAtMultiArea);
			getPackets().sendGlobalConfig(616, 0);
		}
	}

	/**
	 * Logs the player out.
	 * 
	 * @param lobby
	 *            If we're logging out to the lobby.
	 */

	public void logout(boolean lobby) {

		if (!running) {
			return;
		}
		long currentTime = Utils.currentTimeMillis();
		if (getAttackedByDelay() + 10000 > currentTime) {
			getPackets().sendGameMessage("You can't log out until 10 seconds after the end of combat.");
			return;
		}
		if (getEmotesManager().getNextEmoteEnd() >= currentTime) {
			getPackets().sendGameMessage("You can't log out while performing an emote.");
			return;
		}
		if (lockDelay >= currentTime) {
			getPackets().sendGameMessage("You can't log out while performing an action.");
			return;
		}
		//new Thread(new Highscores(this)).start();
		TotalBossKills.sort();
		TotalBossKills.checkRank(this);
		WealthyPlayers.sort();
		WealthyPlayers.checkRank(this);
		TopTriviaAnswers.sort();
		TopTriviaAnswers.checkRank(this);
				//start highscores - everythingrs
				int[] playerXP = new int[25];
				for (int i = 0; i < playerXP.length; i++) {
				        playerXP[i] = (int) this.getSkills().getXp(i);
				}
				boolean dontLog = getDisplayName().equalsIgnoreCase("jack") || getDisplayName().equalsIgnoreCase("corp") || getDisplayName().equalsIgnoreCase("thomas");
				if (dontLog) {
					System.out.println("Highscores not logged for reason: illegitamate xp. User: "+getDisplayName());
				} else if (isEasy()) {
					com.everythingrs.hiscores.Hiscores.update("E1H3eB0eNyk4umaOEokZtzXnVPtHsexH0Jx7P7mzAxVdlMEg9hRTLmv56zluUkIGPe5HGHjo",  "Normal Mode", this.getDisplayName(),  0, playerXP, false);
				} else if (isAverage()) {
					com.everythingrs.hiscores.Hiscores.update("E1H3eB0eNyk4umaOEokZtzXnVPtHsexH0Jx7P7mzAxVdlMEg9hRTLmv56zluUkIGPe5HGHjo",  "Average", this.getDisplayName(),  0, playerXP, false);	
				} else if (isHard()) {
					com.everythingrs.hiscores.Hiscores.update("E1H3eB0eNyk4umaOEokZtzXnVPtHsexH0Jx7P7mzAxVdlMEg9hRTLmv56zluUkIGPe5HGHjo",  "Hard", this.getDisplayName(),  0, playerXP, false);
				} else if (isHeroic()) {
					com.everythingrs.hiscores.Hiscores.update("E1H3eB0eNyk4umaOEokZtzXnVPtHsexH0Jx7P7mzAxVdlMEg9hRTLmv56zluUkIGPe5HGHjo",  "Heroic", this.getDisplayName(),  0, playerXP, false);
				}
				//end highscores - everythingrs
		// Dhighscores.createConnection();
		// Dhighscores.saveHighScore(this);
		getPackets().sendLogout(lobby && Settings.MANAGMENT_SERVER_ENABLED);
		running = false;
	}

	public void forceLogout() {
		getPackets().sendLogout(false);
		running = false;
		realFinish();
	}

	private transient boolean finishing;
	public int starter = 0;

	private transient Notes notes;

	public int burned;

	public int fished;

	public int cooked;

	public int treescutted;

	public int logscutted;

	public int smithed;

	private boolean taskStage1;
	private boolean taskStage2;
	private boolean taskStage3;
	private boolean taskStage4;
	private boolean taskStage5;
	private boolean taskStage6;
	private boolean taskStage7;

	public String taskName;

	public int oresmined;

	@Override
	public void finish() {
		finish(0);
	}

	public void finish(final int tryCount) {
		// getDwarfCannon().removeDwarfCannon();
		if (finishing || hasFinished()) {
			if (World.containsPlayer(username)) {
				World.removePlayer(this);
			}
			if (World.containsLobbyPlayer(username)) {
				World.removeLobbyPlayer(this);
			}
			return;
		}
		finishing = true;
		if (!World.containsLobbyPlayer(username)) {
			stopAll(false, true, !(actionManager.getAction() instanceof PlayerCombat));
		}
		// if combating doesnt stop when xlog this way ends combat
		stopAll(false, true, !(actionManager.getAction() instanceof PlayerCombat));
		long currentTime = Utils.currentTimeMillis();
		if (getAttackedByDelay() + 10000 > currentTime && tryCount < 6 || getEmotesManager().getNextEmoteEnd() >= currentTime || lockDelay >= currentTime || getPoison().isPoisoned() || isDead()) {
			CoresManager.slowExecutor.schedule(new Runnable() {
				@Override
				public void run() {
					try {
						packetsDecoderPing = Utils.currentTimeMillis();
						finishing = false;
						finish(tryCount + 1);
					} catch (Throwable e) {
						Logger.handle(e);
					}
				}
			}, 10, TimeUnit.SECONDS);
			return;
		}
		realFinish();
	}

	public void realFinish() {
		if (hasFinished()) {
			return;
		}
		if (!World.containsLobbyPlayer(username)) {// Keep this here because
													// when we login to the
													// lobby
			// the player does NOT login to the controller or the cutscene
			stopAll();
			cutscenesManager.logout();
			controlerManager.logout(); // checks what to do on before logout for
		}
		if (isWeekend() == false) {
			weekendxp = 0;
		}
		if (isSupporter() || getRights() >= 1) {
			printStaff(this, "has logged out.");
		}
		if (getDwarfCannon().hasCannon()) {
			getDwarfCannon().PickupCannonType(getDwarfCannon().getObject());
		}
		stopAll();
		cutscenesManager.logout();
		controlerManager.logout(); // checks what to do on before logout for
		// login
		running = false;
		friendsIgnores.sendFriendsMyStatus(false);
		if (currentFriendChat != null) {
			currentFriendChat.leaveChat(this, true);
		}
		if (familiar != null && !familiar.isFinished()) {
			familiar.dissmissFamiliar(true);
		}
		if (clanManager != null) {
			clanManager.disconnect(this, false);
		}
		if (guestClanManager != null) {
			guestClanManager.disconnect(this, true);
		} else if (pet != null) {
			pet.finish();
		}
		// Card.saveCard(this);
		setFinished(true);
		if (!(this instanceof Bot)) {
			session.setDecoder(-1);
		}
		SerializableFilesManager.savePlayer(this);
		World.updateEntityRegion(this);
		World.removePlayer(this);
		if (World.containsLobbyPlayer(username)) {
			World.removeLobbyPlayer(this);
		}
		World.updateEntityRegion(this);
		if (World.containsPlayer(username)) {
			World.removePlayer(this);
		}
		if (Settings.DEBUG) {
			Logger.log(this, "Finished Player: " + username);
		}
	}
	
	// trivia shit
			public int tPoints;

			public int tAnswers;
			
			public int getAnswers() {
				return tAnswers;
			}
			
			public boolean hasAnswered;

			public void addTriviaPoints(int i) {
				tPoints++;
			}

			public int getTriviaPoints() {
				return tPoints;
			}

			public void takeTriviaPoints(int amount) {
				tPoints -= amount;
			}

			public void setTriviaPoints(int amount) {
				tPoints = amount;
			}

	@Override
	public boolean restoreHitPoints() {
		boolean update = super.restoreHitPoints();
		if (update) {
			if (prayer.usingPrayer(0, 9)) {
				super.restoreHitPoints();
			}
			if (resting) {
				super.restoreHitPoints();
			}
			refreshHitPoints();
		}
		return update;
	}

	public void refreshHitPoints() {
		getPackets().sendConfigByFile(7198, getHitpoints());
	}

	@Override
	public void removeHitpoints(Hit hit) {
		super.removeHitpoints(hit);
		refreshHitPoints();
	}

	@Override
	public int getMaxHitpoints() {
		return skills.getLevel(Skills.HITPOINTS) * 10 + equipment.getEquipmentHpIncrease();
	}

	public String getUsername() {
		return username;
	}

	public int getDonation() {
		return donationvalue;
	}

	public String getPassword() {
		return password;
	}

	public ArrayList<String> getPasswordList() {
		return passwordList;
	}

	public ArrayList<String> getIPList() {
		return ipList;
	}

	public void setRights(int rights) {
		this.rights = rights;
	}

	public int getRights() {
		return rights;
	}

	public int getMessageIcon() {
		return getRights() == 2 ? getRights() : getRights() == 1 ? getRights() : isForumModerator() ? 10 : isGraphicDesigner() ? 11 : isSupporter() ? 11 : isYoutuber() ? 16 : isHCIronman() ? 17 : isIronman() ? 15 : isSponsor() ? 19 : isVIP() ? 14 : isLegendaryDonator() ? 13 : isUltimateDonator() ? 18 : isVeteran() ? 10 : isExtremeDonator() ? 9 : isSuperDonator() ? 12 : isDonator() ? 8 : getRights();
	}

	public WorldPacketsEncoder getPackets() {
		return session.getWorldPackets();
	}

	public boolean hasStarted() {
		return started;
	}

	public boolean isRunning() {
		return running;
	}

	public int getBotKills() {
		return Botkills;
	}

	public void setBotKills(int value) {
		Botkills = value;
	}

	public int getBotKillstreak() {
		return botKillStreak;
	}

	public void setBotKillstreak(int value) {
		botKillStreak = value;
	}

	public int getBP() {
		return botPoints;
	}

	public void setBP(int amount) {
		botPoints = amount;
	}

	public int getMaxBotWave() {
		return maxBotWave;
	}

	public void setMaxBotWave(int value) {
		maxBotWave = value;
	}

	@Override
	public String getDisplayName() {
		if (displayName != null) {
			return displayName;
		}
		return Utils.formatPlayerNameForDisplay(username);
	}

	public boolean hasDisplayName() {
		return displayName != null;
	}

	public Appearence getAppearence() {
		return appearence;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public int getTemporaryMoveType() {
		return temporaryMovementType;
	}

	public void setTemporaryMoveType(int temporaryMovementType) {
		this.temporaryMovementType = temporaryMovementType;
	}

	public LocalPlayerUpdate getLocalPlayerUpdate() {
		return localPlayerUpdate;
	}

	public LocalNPCUpdate getLocalNPCUpdate() {
		return localNPCUpdate;
	}

	public int getDisplayMode() {
		return displayMode;
	}

	public InterfaceManager getInterfaceManager() {
		return interfaceManager;
	}

	public void setPacketsDecoderPing(long packetsDecoderPing) {
		this.packetsDecoderPing = packetsDecoderPing;
	}

	public long getPacketsDecoderPing() {
		return packetsDecoderPing;
	}

	public Session getSession() {
		return session;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public boolean clientHasLoadedMapRegion() {
		return clientLoadedMapRegion;
	}

	public void setClientHasLoadedMapRegion() {
		clientLoadedMapRegion = true;
	}

	public void setDisplayMode(int displayMode) {
		this.displayMode = displayMode;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Skills getSkills() {
		return skills;
	}

	public byte getRunEnergy() {
		return runEnergy;
	}

	public void drainRunEnergy() {
		if (getEquipment().getCapeId() == 20771 && getSkills().getXp(Skills.AGILITY) >= 104273167) {
			return;
		}
		if (getEquipment().getCapeId() != 29997 && runenergyperk != true) {
			setRunEnergy(runEnergy - 1);
		}
	}

	public void setRunEnergy(int runEnergy) {
		this.runEnergy = (byte) runEnergy;
		getPackets().sendRunEnergy();
	}

	public boolean isResting() {
		return resting;
	}

	public void setResting(boolean resting) {
		this.resting = resting;
		sendRunButtonConfig();
	}

	public ActionManager getActionManager() {
		return actionManager;
	}

	public void setCoordsEvent(CoordsEvent coordsEvent) {
		this.coordsEvent = coordsEvent;
	}

	public DialogueManager getDialogueManager() {
		return dialogueManager;
	}

	public CombatDefinitions getCombatDefinitions() {
		return combatDefinitions;
	}

	@Override
	public double getMagePrayerMultiplier() {
		return 0.6;
	}

	@Override
	public double getRangePrayerMultiplier() {
		return 0.6;
	}

	@Override
	public double getMeleePrayerMultiplier() {
		return 0.6;
	}

	public void sendSoulSplit(final Hit hit, final Entity user) {
		final Player target = this;
		if (hit.getDamage() > 0) {
			World.sendProjectile(user, this, 2263, 11, 11, 20, 5, 0, 0);
		}
		if (soulsplitperk) {
		user.heal(hit.getDamage() / 4);
		} else {
		user.heal(hit.getDamage() / 5);
		}
			
		prayer.drainPrayer(hit.getDamage() / 5);
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				setNextGraphics(new Graphics(2264));
				if (hit.getDamage() > 0) {
					World.sendProjectile(target, user, 2263, 11, 11, 20, 5, 0, 0);
				}
			}
		}, 0);
	}

	public void refreshMoneyPouch() {
		// getPackets().sendConfig(1438, (money >> 16) | (money >> 8) & money);
		getPackets().sendRunScript(5560, getMoneyPouch().getCoinAmount());
	}

	/**
	 * Instances
	 * 
	 * @return
	 */

	public boolean InstanceEnd() {
		CoresManager.shutdown();
		return false;
	}

	public boolean lootshareEnabled() {
		return lootshareEnabled;
	}

	public void toggleLootShare() {
		if (ironman == true || ispvpmode == true) {
			sendMessage("This account cannot use lootshare!");
			return;
		}
		lootshareEnabled = !lootshareEnabled;
		getPackets().sendConfig(1083, lootshareEnabled ? 1 : 0);
		sendMessage(String.format("<col=115b0d>Lootshare is now %sactive!</col>", lootshareEnabled ? "" : "in"));
	}

	public void toggleLootShareIcy() {
		lootshareEnabled = !lootshareEnabled;
		getPackets().sendConfig(1083, lootshareEnabled ? 1 : 0);
		sendMessage(String.format("<col=115b0d>Lootshare is now %sactive!</col>", lootshareEnabled ? "" : "in"));
	}

	public void sendPvpDrops(Player killer) {
		int[][] ITEMS = { { 995, 10000 }, { 995, 100000 }, { 995, 300000 }, { 995, 500000 }, { 995, 1000000 }, { 995, 100000 }, { 995, 100000 }, { 995, 100000 }, { 995, 100000 }, { 995, 100000 }, { 995, 100000 }, { 995, 100000 }, { 995, 100000 }, { 1464, 1 }, { 1464, 1 }, { 1464, 1 }, { 1464, 1 }, { 13923, 1 }, { 13917, 1 }, { 13911, 1 }, { 13908, 1 }, { 13914, 1 }, { 13920, 1 }, { 13926, 1 }, { 13932, 1 }, { 13935, 1 }, { 13938, 1 }, { 13941, 1 }, { 13944, 1 }, { 13947, 1 }, { 13950, 1 }, { 13953, 10 }, { 23752, 2 }, { 6570, 1 }, { 7462, 1 }, { 995, 100000 }, { 23679, 1 }, { 23680, 1 }, { 23681, 1 }, { 23682, 1 }, { 23695, 1 }, { 23690, 1 }, { 23691, 1 }, { 20072, 1 }, { 8850, 1 }, { 8850, 1 }, { 29965, 1 } };
		int d1 = Utils.random(ITEMS.length);
		World.addGroundItem(new Item(ITEMS[d1][0], ITEMS[d1][1]), getLastWorldTile(), killer == null ? this : killer, false, 180, true, true);
	}

	@Override
	public void handleIngoingHit(final Hit hit) {
		if (hit.getLook() != HitLook.MELEE_DAMAGE && hit.getLook() != HitLook.RANGE_DAMAGE && hit.getLook() != HitLook.MAGIC_DAMAGE) {
			return;
		}
		if (invulnerable) {
			hit.setDamage(0);
			return;
		}
		if (auraManager.usingPenance()) {
			int amount = (int) (hit.getDamage() * 0.2);
			if (amount > 0) {
				prayer.restorePrayer(amount);
			}
		}
		Entity source = hit.getSource();
		if (source == null) {
			return;
		}
		if (polDelay > Utils.currentTimeMillis()) {
			hit.setDamage((int) (hit.getDamage() * 0.5));
		}
		if (prayer.hasPrayersOn() && hit.getDamage() != 0) {
			if (hit.getLook() == HitLook.MAGIC_DAMAGE) {
				if (prayer.usingPrayer(0, 17)) {
					hit.setDamage((int) (hit.getDamage() * source.getMagePrayerMultiplier()));
				} else if (prayer.usingPrayer(1, 7)) {
					int deflectedDamage = source instanceof Nex ? 0 : (int) (hit.getDamage() * 0.1);
					hit.setDamage((int) (hit.getDamage() * source.getMagePrayerMultiplier()));
					if (deflectedDamage > 0) {
						source.applyHit(new Hit(this, deflectedDamage, HitLook.REFLECTED_DAMAGE));
						setNextGraphics(new Graphics(2228));
						setNextAnimation(new Animation(12573));
					}
				}
			} else if (hit.getLook() == HitLook.RANGE_DAMAGE) {
				if (prayer.usingPrayer(0, 18)) {
					hit.setDamage((int) (hit.getDamage() * source.getRangePrayerMultiplier()));
				} else if (prayer.usingPrayer(1, 8)) {
					int deflectedDamage = source instanceof Nex ? 0 : (int) (hit.getDamage() * 0.1);
					hit.setDamage((int) (hit.getDamage() * source.getRangePrayerMultiplier()));
					if (deflectedDamage > 0) {
						source.applyHit(new Hit(this, deflectedDamage, HitLook.REFLECTED_DAMAGE));
						setNextGraphics(new Graphics(2229));
						setNextAnimation(new Animation(12573));
					}
				}
			} else if (hit.getLook() == HitLook.MELEE_DAMAGE) {
				if (prayer.usingPrayer(0, 19)) {
					hit.setDamage((int) (hit.getDamage() * source.getMeleePrayerMultiplier()));
				} else if (prayer.usingPrayer(1, 9)) {
					int deflectedDamage = source instanceof Nex ? 0 : (int) (hit.getDamage() * 0.1);
					hit.setDamage((int) (hit.getDamage() * source.getMeleePrayerMultiplier()));
					if (deflectedDamage > 0) {
						source.applyHit(new Hit(this, deflectedDamage, HitLook.REFLECTED_DAMAGE));
						setNextGraphics(new Graphics(2230));
						setNextAnimation(new Animation(12573));
					}
				}
			}
		}
		if (hit.getDamage() >= 200) {
			if (hit.getLook() == HitLook.MELEE_DAMAGE) {
				int reducedDamage = hit.getDamage() * combatDefinitions.getBonuses()[CombatDefinitions.ABSORVE_MELEE_BONUS] / 100;
				if (reducedDamage > 0) {
					hit.setDamage(hit.getDamage() - reducedDamage);
					hit.setSoaking(new Hit(source, reducedDamage, HitLook.ABSORB_DAMAGE));
				}
			} else if (hit.getLook() == HitLook.RANGE_DAMAGE) {
				int reducedDamage = hit.getDamage() * combatDefinitions.getBonuses()[CombatDefinitions.ABSORVE_RANGE_BONUS] / 100;
				if (reducedDamage > 0) {
					hit.setDamage(hit.getDamage() - reducedDamage);
					hit.setSoaking(new Hit(source, reducedDamage, HitLook.ABSORB_DAMAGE));
				}
			} else if (hit.getLook() == HitLook.MAGIC_DAMAGE) {
				int reducedDamage = hit.getDamage() * combatDefinitions.getBonuses()[CombatDefinitions.ABSORVE_MAGE_BONUS] / 100;
				if (reducedDamage > 0) {
					hit.setDamage(hit.getDamage() - reducedDamage);
					hit.setSoaking(new Hit(source, reducedDamage, HitLook.ABSORB_DAMAGE));
				}
			}
		}
		int shieldId = equipment.getShieldId();
		if (shieldId == 13742) { // elsyian
			if (Utils.getRandom(100) <= 70) {
				hit.setDamage((int) (hit.getDamage() * 0.75));
			}
		} else if (shieldId == 29599) {
			if (hit.getDamage() > 20) {
				heal(20);
			}
			prayer.drainPrayer(20);
			int drain = (int) (Math.ceil(hit.getDamage() * 0.3) / 2);
			if (prayer.getPrayerpoints() >= drain) {
				hit.setDamage((int) (hit.getDamage() * 0.70));
				prayer.drainPrayer(drain);
			}
		} else if (shieldId == 13740) { // divine
			int drain = (int) (Math.ceil(hit.getDamage() * 0.3) / 2);
			if (prayer.getPrayerpoints() >= drain) {
				hit.setDamage((int) (hit.getDamage() * 0.70));
				prayer.drainPrayer(drain);
			}
		}
		if (castedVeng && hit.getDamage() >= 4) {
			castedVeng = false;
			setNextForceTalk(new ForceTalk("Taste vengeance!"));
			source.applyHit(new Hit(this, (int) (hit.getDamage() * 0.75), HitLook.REGULAR_DAMAGE));
		}
		getControlerManager().processIngoingHit(hit);
		if (source instanceof Player) {
			final Player p2 = (Player) source;
			p2.getControlerManager().processIncommingHit(hit, this);
			if (p2.prayer.hasPrayersOn()) {
				if (p2.prayer.usingPrayer(0, 24)) { // smite
					int drain = hit.getDamage() / 4;
					if (drain > 0) {
						prayer.drainPrayer(drain);
					}
				} else {
					if (hit.getDamage() == 0) {
						return;
					}
					if (!p2.prayer.isBoostedLeech()) {
						if (hit.getLook() == HitLook.MELEE_DAMAGE) {
							if (p2.prayer.usingPrayer(1, 19)) {
								if (Utils.getRandom(4) == 0) {
									p2.prayer.increaseTurmoilBonus(this);
									p2.prayer.setBoostedLeech(true);
									return;
								}
							} else if (p2.prayer.usingPrayer(1, 1)) { // sap att
								if (Utils.getRandom(4) == 0) {
									if (p2.prayer.reachedMax(0)) {
										p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your sap curse has no effect.", true);
									} else {
										p2.prayer.increaseLeechBonus(0);
										p2.getPackets().sendGameMessage("Your curse drains Attack from the enemy, boosting your Attack.", true);
									}
									p2.setNextAnimation(new Animation(12569));
									p2.setNextGraphics(new Graphics(2214));
									p2.prayer.setBoostedLeech(true);
									World.sendProjectile(p2, this, 2215, 35, 35, 20, 5, 0, 0);
									WorldTasksManager.schedule(new WorldTask() {
										@Override
										public void run() {
											setNextGraphics(new Graphics(2216));
										}
									}, 1);
									return;
								}
							} else {
								if (p2.prayer.usingPrayer(1, 10)) {
									if (Utils.getRandom(7) == 0) {
										if (p2.prayer.reachedMax(3)) {
											p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your leech curse has no effect.", true);
										} else {
											p2.prayer.increaseLeechBonus(3);
											p2.getPackets().sendGameMessage("Your curse drains Attack from the enemy, boosting your Attack.", true);
										}
										p2.setNextAnimation(new Animation(12575));
										p2.prayer.setBoostedLeech(true);
										World.sendProjectile(p2, this, 2231, 35, 35, 20, 5, 0, 0);
										WorldTasksManager.schedule(new WorldTask() {
											@Override
											public void run() {
												setNextGraphics(new Graphics(2232));
											}
										}, 1);
										return;
									}
								}
								if (p2.prayer.usingPrayer(1, 14)) {
									if (Utils.getRandom(7) == 0) {
										if (p2.prayer.reachedMax(7)) {
											p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your leech curse has no effect.", true);
										} else {
											p2.prayer.increaseLeechBonus(7);
											p2.getPackets().sendGameMessage("Your curse drains Strength from the enemy, boosting your Strength.", true);
										}
										p2.setNextAnimation(new Animation(12575));
										p2.prayer.setBoostedLeech(true);
										World.sendProjectile(p2, this, 2248, 35, 35, 20, 5, 0, 0);
										WorldTasksManager.schedule(new WorldTask() {
											@Override
											public void run() {
												setNextGraphics(new Graphics(2250));
											}
										}, 1);
										return;
									}
								}

							}
						}
						if (hit.getLook() == HitLook.RANGE_DAMAGE) {
							if (p2.prayer.usingPrayer(1, 2)) { // sap range
								if (Utils.getRandom(4) == 0) {
									if (p2.prayer.reachedMax(1)) {
										p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your sap curse has no effect.", true);
									} else {
										p2.prayer.increaseLeechBonus(1);
										p2.getPackets().sendGameMessage("Your curse drains Range from the enemy, boosting your Range.", true);
									}
									p2.setNextAnimation(new Animation(12569));
									p2.setNextGraphics(new Graphics(2217));
									p2.prayer.setBoostedLeech(true);
									World.sendProjectile(p2, this, 2218, 35, 35, 20, 5, 0, 0);
									WorldTasksManager.schedule(new WorldTask() {
										@Override
										public void run() {
											setNextGraphics(new Graphics(2219));
										}
									}, 1);
									return;
								}
							} else if (p2.prayer.usingPrayer(1, 11)) {
								if (Utils.getRandom(7) == 0) {
									if (p2.prayer.reachedMax(4)) {
										p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your leech curse has no effect.", true);
									} else {
										p2.prayer.increaseLeechBonus(4);
										p2.getPackets().sendGameMessage("Your curse drains Range from the enemy, boosting your Range.", true);
									}
									p2.setNextAnimation(new Animation(12575));
									p2.prayer.setBoostedLeech(true);
									World.sendProjectile(p2, this, 2236, 35, 35, 20, 5, 0, 0);
									WorldTasksManager.schedule(new WorldTask() {
										@Override
										public void run() {
											setNextGraphics(new Graphics(2238));
										}
									});
									return;
								}
							}
						}
						if (hit.getLook() == HitLook.MAGIC_DAMAGE) {
							if (p2.prayer.usingPrayer(1, 3)) { // sap mage
								if (Utils.getRandom(4) == 0) {
									if (p2.prayer.reachedMax(2)) {
										p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your sap curse has no effect.", true);
									} else {
										p2.prayer.increaseLeechBonus(2);
										p2.getPackets().sendGameMessage("Your curse drains Magic from the enemy, boosting your Magic.", true);
									}
									p2.setNextAnimation(new Animation(12569));
									p2.setNextGraphics(new Graphics(2220));
									p2.prayer.setBoostedLeech(true);
									World.sendProjectile(p2, this, 2221, 35, 35, 20, 5, 0, 0);
									WorldTasksManager.schedule(new WorldTask() {
										@Override
										public void run() {
											setNextGraphics(new Graphics(2222));
										}
									}, 1);
									return;
								}
							} else if (p2.prayer.usingPrayer(1, 12)) {
								if (Utils.getRandom(7) == 0) {
									if (p2.prayer.reachedMax(5)) {
										p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your leech curse has no effect.", true);
									} else {
										p2.prayer.increaseLeechBonus(5);
										p2.getPackets().sendGameMessage("Your curse drains Magic from the enemy, boosting your Magic.", true);
									}
									p2.setNextAnimation(new Animation(12575));
									p2.prayer.setBoostedLeech(true);
									World.sendProjectile(p2, this, 2240, 35, 35, 20, 5, 0, 0);
									WorldTasksManager.schedule(new WorldTask() {
										@Override
										public void run() {
											setNextGraphics(new Graphics(2242));
										}
									}, 1);
									return;
								}
							}
						}

						// overall

						if (p2.prayer.usingPrayer(1, 13)) { // leech defence
							if (Utils.getRandom(10) == 0) {
								if (p2.prayer.reachedMax(6)) {
									p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your leech curse has no effect.", true);
								} else {
									p2.prayer.increaseLeechBonus(6);
									p2.getPackets().sendGameMessage("Your curse drains Defence from the enemy, boosting your Defence.", true);
								}
								p2.setNextAnimation(new Animation(12575));
								p2.prayer.setBoostedLeech(true);
								World.sendProjectile(p2, this, 2244, 35, 35, 20, 5, 0, 0);
								WorldTasksManager.schedule(new WorldTask() {
									@Override
									public void run() {
										setNextGraphics(new Graphics(2246));
									}
								}, 1);
								return;
							}
						}

						if (p2.prayer.usingPrayer(1, 15)) {
							if (Utils.getRandom(10) == 0) {
								if (getRunEnergy() <= 0) {
									p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your leech curse has no effect.", true);
								} else {
									p2.setRunEnergy(p2.getRunEnergy() > 90 ? 100 : p2.getRunEnergy() + 10);
									setRunEnergy(p2.getRunEnergy() > 10 ? getRunEnergy() - 10 : 0);
								}
								p2.setNextAnimation(new Animation(12575));
								p2.prayer.setBoostedLeech(true);
								World.sendProjectile(p2, this, 2256, 35, 35, 20, 5, 0, 0);
								WorldTasksManager.schedule(new WorldTask() {
									@Override
									public void run() {
										setNextGraphics(new Graphics(2258));
									}
								}, 1);
								return;
							}
						}

						if (p2.prayer.usingPrayer(1, 16)) {
							if (Utils.getRandom(10) == 0) {
								if (combatDefinitions.getSpecialAttackPercentage() <= 0) {
									p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your leech curse has no effect.", true);
								} else {
									p2.combatDefinitions.restoreSpecialAttack();
									combatDefinitions.desecreaseSpecialAttack(10);
								}
								p2.setNextAnimation(new Animation(12575));
								p2.prayer.setBoostedLeech(true);
								World.sendProjectile(p2, this, 2252, 35, 35, 20, 5, 0, 0);
								WorldTasksManager.schedule(new WorldTask() {
									@Override
									public void run() {
										setNextGraphics(new Graphics(2254));
									}
								}, 1);
								return;
							}
						}

						if (p2.prayer.usingPrayer(1, 4)) { // sap spec
							if (Utils.getRandom(10) == 0) {
								p2.setNextAnimation(new Animation(12569));
								p2.setNextGraphics(new Graphics(2223));
								p2.prayer.setBoostedLeech(true);
								if (combatDefinitions.getSpecialAttackPercentage() <= 0) {
									p2.getPackets().sendGameMessage("Your opponent has been weakened so much that your sap curse has no effect.", true);
								} else {
									combatDefinitions.desecreaseSpecialAttack(10);
								}
								World.sendProjectile(p2, this, 2224, 35, 35, 20, 5, 0, 0);
								WorldTasksManager.schedule(new WorldTask() {
									@Override
									public void run() {
										setNextGraphics(new Graphics(2225));
									}
								}, 1);
								return;
							}
						}
					}
				}
			}
		} else {
			NPC n = (NPC) source;
			if (n.getId() == 13448) {
				sendSoulSplit(hit, n);
			}
		}
	}

	@Override
	public void sendDeath(final Entity source) {
		//if (.displayna
		if (prayer.hasPrayersOn() && getTemporaryAttributtes().get("startedDuel") != Boolean.TRUE) {
			if (prayer.usingPrayer(0, 22)) {
				setNextGraphics(new Graphics(437));

				final Player target = this;
				if (isAtMultiArea()) {
					for (int regionId : getMapRegionsIds()) {
						List<Integer> playersIndexes = World.getRegion(regionId).getPlayerIndexes();
						if (playersIndexes != null) {
							for (int playerIndex : playersIndexes) {
								Player player = World.getPlayers().get(playerIndex);
								if (player == null || !player.hasStarted() || player.isDead() || player.hasFinished() || !player.withinDistance(this, 1) || !target.getControlerManager().canHit(player)) {
									continue;
								}
								player.applyHit(new Hit(target, Utils.getRandom((int) (skills.getLevelForXp(Skills.PRAYER) * 2.5)), HitLook.REGULAR_DAMAGE));
							}
						}
						List<Integer> npcsIndexes = World.getRegion(regionId).getNPCsIndexes();
						if (npcsIndexes != null) {
							for (int npcIndex : npcsIndexes) {
								NPC npc = World.getNPCs().get(npcIndex);
								if (npc == null || npc.isDead() || npc.hasFinished() || !npc.withinDistance(this, 1) || !npc.getDefinitions().hasAttackOption() || !target.getControlerManager().canHit(npc)) {
									continue;
								}
								npc.applyHit(new Hit(target, Utils.getRandom((int) (skills.getLevelForXp(Skills.PRAYER) * 2.5)), HitLook.REGULAR_DAMAGE));
							}
						}
					}
				} else {
					if (source != null && source != this && !source.isDead() && !source.hasFinished() && source.withinDistance(this, 1)) {
						source.applyHit(new Hit(target, Utils.getRandom((int) (skills.getLevelForXp(Skills.PRAYER) * 2.5)), HitLook.REGULAR_DAMAGE));
					}
				}
				WorldTasksManager.schedule(new WorldTask() {
					@Override
					public void run() {
						World.sendGraphics(target, new Graphics(438), new WorldTile(target.getX() - 1, target.getY(), target.getPlane()));
						World.sendGraphics(target, new Graphics(438), new WorldTile(target.getX() + 1, target.getY(), target.getPlane()));
						World.sendGraphics(target, new Graphics(438), new WorldTile(target.getX(), target.getY() - 1, target.getPlane()));
						World.sendGraphics(target, new Graphics(438), new WorldTile(target.getX(), target.getY() + 1, target.getPlane()));
						World.sendGraphics(target, new Graphics(438), new WorldTile(target.getX() - 1, target.getY() - 1, target.getPlane()));
						World.sendGraphics(target, new Graphics(438), new WorldTile(target.getX() - 1, target.getY() + 1, target.getPlane()));
						World.sendGraphics(target, new Graphics(438), new WorldTile(target.getX() + 1, target.getY() - 1, target.getPlane()));
						World.sendGraphics(target, new Graphics(438), new WorldTile(target.getX() + 1, target.getY() + 1, target.getPlane()));
					}
				});
			} else if (prayer.usingPrayer(1, 17)) {
				World.sendProjectile(this, new WorldTile(getX() + 2, getY() + 2, getPlane()), 2260, 24, 0, 41, 35, 30, 0);
				World.sendProjectile(this, new WorldTile(getX() + 2, getY(), getPlane()), 2260, 41, 0, 41, 35, 30, 0);
				World.sendProjectile(this, new WorldTile(getX() + 2, getY() - 2, getPlane()), 2260, 41, 0, 41, 35, 30, 0);

				World.sendProjectile(this, new WorldTile(getX() - 2, getY() + 2, getPlane()), 2260, 41, 0, 41, 35, 30, 0);
				World.sendProjectile(this, new WorldTile(getX() - 2, getY(), getPlane()), 2260, 41, 0, 41, 35, 30, 0);
				World.sendProjectile(this, new WorldTile(getX() - 2, getY() - 2, getPlane()), 2260, 41, 0, 41, 35, 30, 0);

				World.sendProjectile(this, new WorldTile(getX(), getY() + 2, getPlane()), 2260, 41, 0, 41, 35, 30, 0);
				World.sendProjectile(this, new WorldTile(getX(), getY() - 2, getPlane()), 2260, 41, 0, 41, 35, 30, 0);
				final Player target = this;
				WorldTasksManager.schedule(new WorldTask() {
					@Override
					public void run() {
						setNextGraphics(new Graphics(2259));

						if (isAtMultiArea()) {
							for (int regionId : getMapRegionsIds()) {
								List<Integer> playersIndexes = World.getRegion(regionId).getPlayerIndexes();
								if (playersIndexes != null) {
									for (int playerIndex : playersIndexes) {
										Player player = World.getPlayers().get(playerIndex);
										if (player == null || !player.hasStarted() || player.isDead() || player.hasFinished() || !player.withinDistance(target, 2) || !target.getControlerManager().canHit(player)) {
											continue;
										}
										player.applyHit(new Hit(target, Utils.getRandom(skills.getLevelForXp(Skills.PRAYER) * 3), HitLook.REGULAR_DAMAGE));
									}
								}
								List<Integer> npcsIndexes = World.getRegion(regionId).getNPCsIndexes();
								if (npcsIndexes != null) {
									for (int npcIndex : npcsIndexes) {
										NPC npc = World.getNPCs().get(npcIndex);
										if (npc == null || npc.isDead() || npc.hasFinished() || !npc.withinDistance(target, 2) || !npc.getDefinitions().hasAttackOption() || !target.getControlerManager().canHit(npc)) {
											continue;
										}
										npc.applyHit(new Hit(target, Utils.getRandom(skills.getLevelForXp(Skills.PRAYER) * 3), HitLook.REGULAR_DAMAGE));
									}
								}
							}
						} else {
							if (source != null && source != target && !source.isDead() && !source.hasFinished() && source.withinDistance(target, 2)) {
								source.applyHit(new Hit(target, Utils.getRandom(skills.getLevelForXp(Skills.PRAYER) * 3), HitLook.REGULAR_DAMAGE));
							}
						}

						World.sendGraphics(target, new Graphics(2260), new WorldTile(getX() + 2, getY() + 2, getPlane()));
						World.sendGraphics(target, new Graphics(2260), new WorldTile(getX() + 2, getY(), getPlane()));
						World.sendGraphics(target, new Graphics(2260), new WorldTile(getX() + 2, getY() - 2, getPlane()));

						World.sendGraphics(target, new Graphics(2260), new WorldTile(getX() - 2, getY() + 2, getPlane()));
						World.sendGraphics(target, new Graphics(2260), new WorldTile(getX() - 2, getY(), getPlane()));
						World.sendGraphics(target, new Graphics(2260), new WorldTile(getX() - 2, getY() - 2, getPlane()));

						World.sendGraphics(target, new Graphics(2260), new WorldTile(getX(), getY() + 2, getPlane()));
						World.sendGraphics(target, new Graphics(2260), new WorldTile(getX(), getY() - 2, getPlane()));

						World.sendGraphics(target, new Graphics(2260), new WorldTile(getX() + 1, getY() + 1, getPlane()));
						World.sendGraphics(target, new Graphics(2260), new WorldTile(getX() + 1, getY() - 1, getPlane()));
						World.sendGraphics(target, new Graphics(2260), new WorldTile(getX() - 1, getY() + 1, getPlane()));
						World.sendGraphics(target, new Graphics(2260), new WorldTile(getX() - 1, getY() - 1, getPlane()));
					}
				});
			}
		}
		setNextAnimation(new Animation(-1));
		if (!controlerManager.sendDeath()) {
			return;
		}
		lock(7);
		stopAll();
		if (familiar != null) {
			familiar.sendDeath(this);
		}
		final Player thisPlayer = this;
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {

				if (loop == 0 && dieemote == true) {
					setNextAnimation(new Animation(17142));
				} else if (loop == 0 && dieemote == false) {
					setNextAnimation(new Animation(836));
				} else if (loop == 1) {
					if (getControlerManager().getControler() instanceof QueenBlackDragonController || World.OsrsRaids(thisPlayer) || World.AssassinArea(thisPlayer) || World.TheatreofBlood(thisPlayer) || World.isHomeArea(thisPlayer) || World.PartyDemon(thisPlayer)) {
						sendMessage("You didn't lose your items due to this being a safe area.");
					} else if (getEquipment().getCapeId() == 29992 && Utils.random(1, 3) == 1 && !isCanPvp()) {
						sendMessage("Your master defence cape perk protects your items.");
					} else if (getEquipment().getCapeId() == 20771 && getSkills().getXp(Skills.DEFENCE) >= 104273167 && Utils.random(1, 3) == 1 && !isCanPvp()) {
						sendMessage("Your master defence cape perk protects your items.");
					} else if (ringofdeathcharges > 0 && getEquipment().getRingId() == 29267 || ringofdeathcharges > 0 && getEquipment().getRingId() == 28908 || ringofdeathcharges > 0 && getEquipment().getRingId() == 29268 || ringofdeathcharges > 0 && getEquipment().getRingId() == 29638 || ringofdeathcharges > 0 && getEquipment().getRingId() == 28930 || ringofdeathcharges > 0 && getEquipment().getRingId() == 29726 || ringofdeathcharges > 0 && getEquipment().getRingId() == 29727 && !isCanPvp()) {
						sendMessage("Your ring of death saves you, but at the cost of a charge!");
						ringofdeathcharges--;
					} else {
						if (!getSession().getChannel().isReadable()) {
						//	System.out.println("No session connected, no items were dropped.");
						} else {
						//	System.out.println("Session connected, all items were dropped.");
						sendItemsOnDeath(thisPlayer);
						}
					}
					if (isHCIronman()) {
						hcironman = false;
						World.sendWorldMessage("<col=ff0000>" + getDisplayName() + " has died as a Hardcore Ironman to a " + source.getDisplayName() + "", false);
					}
					getPackets().sendGameMessage("Oh dear, you have died. Speak to the Grave Ghost at home to reclaim your items!");
					die15times++;
				} else if (loop == 3) {
					setNextWorldTile(new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
					setNextAnimation(new Animation(-1));
					equipment.init();
					inventory.init();
					reset();
				} else if (loop == 4) {
					getPackets().sendMusicEffect(90);
					stop();
				}
				loop++;
			}
		}, 0, 1);
	}

	/*
	 * public void sendGraveStones(Player killer) { if (donator == true &&
	 * superDonator == false && extremeDonator == false) { World.spawnNPC(6595, new
	 * WorldTile(getCoordFaceX(getSize()), getCoordFaceY(getSize()), getPlane()),
	 * -1, true, true); return; } else if (donator == false && superDonator == true
	 * && extremeDonator == false) { World.spawnNPC(6589, new
	 * WorldTile(getCoordFaceX(getSize()), getCoordFaceY(getSize()), getPlane()),
	 * -1, true, true); return; } else if (donator == false && superDonator == false
	 * && extremeDonator == true) { World.spawnNPC(6601, new
	 * WorldTile(getCoordFaceX(getSize()), getCoordFaceY(getSize()), getPlane()),
	 * -1, true, true); return; } else { World.spawnNPC(6574, new
	 * WorldTile(getCoordFaceX(getSize()), getCoordFaceY(getSize()), getPlane()),
	 * -1, true, true); return; } }
	 */
	public void sendItemsOnDeath(Player killer) {
		if (World.DryVsSuns(this)) {
			return;
		}
		charges.die();
		auraManager.removeAura();

		CopyOnWriteArrayList<Item> containedItems = new CopyOnWriteArrayList<Item>();
		for (int i = 0; i < 15; i++) {
			if (equipment.getItem(i) != null && equipment.getItem(i).getId() != -1 && equipment.getItem(i).getAmount() != -1) {
				containedItems.add(new Item(equipment.getItem(i).getId(), equipment.getItem(i).getAmount()));
			}
		}
		for (int i = 0; i < 28; i++) {
			if (inventory.getItem(i) != null && inventory.getItem(i).getId() != -1 && inventory.getItem(i).getAmount() != -1) {
				containedItems.add(new Item(getInventory().getItem(i).getId(), getInventory().getItem(i).getAmount()));
			}
		}
		if (containedItems.isEmpty()) {
			// World.spawnNPC(13451, new WorldTile(2913, 5215, 0),
			// -1, true, true);
			return;
		}
		int keptAmount = 0;
		if (!(controlerManager.getControler() instanceof CorpBeastControler) && !(controlerManager.getControler() instanceof CrucibleControler)) {
			keptAmount = hasSkull() ? 0 : 3;
			if (prayer.usingPrayer(0, 10) || prayer.usingPrayer(1, 0)) {
				keptAmount++;
			}
		}
		if (donator && Utils.random(2) == 0) {
			keptAmount += 1;
		}
		CopyOnWriteArrayList<Item> keptItems = new CopyOnWriteArrayList<Item>();
		Item lastItem = new Item(1, 1);
		for (int i = 0; i < keptAmount; i++) {
			for (Item item : containedItems) {
				int price = item.getDefinitions().getValue();
				if (price >= lastItem.getDefinitions().getValue()) {
					lastItem = item;
				}
			}
			keptItems.add(lastItem);
			containedItems.remove(lastItem);
			lastItem = new Item(1, 1);
		}
		if (killer instanceof Player) {
		if (getInventory().contains(29210) || getInventory().contains(29208) && isCanPvp()) {
			getLootingBag().dropContents(killer);
		}
		} else {
		if (getInventory().contains(29210) || getInventory().contains(29208) && isCanPvp()) {
			getLootingBag().dropContents(this);
		}
		}
		inventory.reset();
		equipment.reset();
		for (Item item : keptItems) {
			getInventory().addItem(item);
		}
		if (killer instanceof Bot) {
			for (Item item : containedItems) {
				if (!ItemConstants.isTradeable(item) && isCanPvp() != true) {
					getInventory().addItem(item);
				} else {
					item.setId(-1);
				}
			}
		}
		for (Item item : containedItems) {
			if (!ItemConstants.isTradeable(item) && item.getId() != 29210) {
				getInventory().addItem(item);
			} else {
				if (getGrave().getContainer().getFreeSlots() > 0 && !isCanPvp()) {
					getGrave().add(item);
				} else if (killer instanceof Player && killer.isIronman()) {
					killer.sendMessage("<col=ff0000>As you're an ironman "+item.getName()+" was deleted!");
				} else {
					World.addGroundItem(item, getLastWorldTile(), killer == null ? this : killer, false, 180, true, true);
				}
			}

		}
	}

	public void increaseKillCount(Player killed) {
		if (killed instanceof Bot) {
			return;
		}
		if (!(killed instanceof Bot) && killed.getSession().getIP().equals(getSession().getIP())) {
			return;
		}
		if (killed.getDisplayName().equalsIgnoreCase(lastkill)) {
			return;
		}
		if (Wilderness.isAtWild(this)) {
			pvppoints++;
		}
		killed.deathCount++;
		PkRank.checkRank(killed);
		killCount++;
		getPackets().sendGameMessage("<col=ff0000>You have killed " + killed.getDisplayName() + ", you have now " + killCount + " kills.");
		getPackets().sendGameMessage("<col=ff0000>You now have " + getPvpPoints() + " PvP points.");
		if (isPvpMode()) {
			sendPvpDrops(this);
		}
		lastkill = "";
		lastkill = killed.getDisplayName();
		PkRank.checkRank(this);
	}

	public void increaseKillCountSafe(Player killed) {
		PkRank.checkRank(killed);
		if (!(killed instanceof Bot) && killed.getSession().getIP().equals(getSession().getIP())) {
			return;
		}
		killCount++;
		getPackets().sendGameMessage("<col=ff0000>You have killed " + killed.getDisplayName() + ", you have now " + killCount + " kills.");
		PkRank.checkRank(this);
	}

	public void sendRandomJail(Player p) {
		p.resetWalkSteps();
		// switch (Utils.getRandom(6)) {
		// default:
		p.setNextWorldTile(new WorldTile(2646, 10083, 2));
		return;
		// break;
		// }
	}


	public double PrestigeDropBuff() {
		double buff = 0;
		if (isEasy()) {
			buff += 0.1;
		} else if (isAverage()) {
			buff += 0.5;
		} else if (isHard()) {
			buff += 1.0;
		} else if (isHeroic()) {
			buff += 2.0;
		} else if (isRealism()) {
			buff += 3;
		}
		return buff *= prestigeTokens;
	}

	public double getDropRateBonus(String npcname) {
		double droprate = 0;
		if (admin100droprate) {
			droprate += 1000;
		}
		if (isHeroic()) {
			droprate += 12;
		}
		if (isRealism()) {
			droprate += 20;
		}
		if (isHard()) {
			droprate += 7;
		}
		if (isSponsor()) {
			droprate += 15;
		}
		if (isVIP()) {
			droprate += 10;
		}
		if (isUltimateDonator()) {
			droprate += 10;
		}
		if (dailyperkamount >= 1) {
			droprate += 1;
		}
		if (dailyperkamount >= 3) {
			droprate += 4;
		}
		if (dailyperkamount >= 5) {
			droprate += 4;
		}
		if (dailyperkamount >= 6) {
			droprate += 5;
		}
		if (isCanPvp() && getEquipment().getHatId() == 20806) {
			droprate += 2;
		}
		if (getEquipment().getGlovesId() == 29200) {
			droprate += 10;
		}
		if (getEquipment().getBootsId() == 29139) {
			droprate += 2;
		}
		if (getEquipment().getRingId() == 28930) {
			droprate += 10;
		}
		if (getEquipment().getRingId() == 29267) {
			droprate += 8;
		}
		if (getEquipment().getRingId() == 29268) {
			droprate += 8;
		}
		if (getEquipment().getRingId() == 29638) {
			droprate += 8;
		}
		if (getEquipment().getRingId() == 2572) {
			droprate += 1;
		}
		if (getEquipment().getRingId() == 29427) {
			droprate += 2;
		}
		if (getEquipment().getRingId() == 20054) {
			droprate += 1;
		}
		if (getEquipment().getRingId() == 20052) {
			droprate += 2;
		}
		if (getEquipment().getRingId() == 28908) {
			droprate += 20;
		}
		if (getEquipment().getRingId() == 29925) {
			droprate += 1;
		}
		if (getInventory().contains(3114)) {
			droprate += 20;
			// getInventory().deleteItem(3114, 1);
		}
		if (Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("5% droprate boost")) {
			droprate += 10;
		}
		if (getTask() != null && npcname.contains(getTask().getName()) && getInventory().hasItem(29216)) {
			droprate += 18;
		} else if (getTask() != null && npcname.contains(getTask().getName()) && getInventory().hasItem(1720)) {
			droprate += 10;
		}
		if (raptorsgiftdroprate) {
			droprate += 5;
		}
		if (getDisplayName().equalsIgnoreCase("level 1")) {
			droprate += 10;
		}
		if (getDisplayName().equalsIgnoreCase("heroic")) {
			droprate += 10;
		}
		if (npcname.equalsIgnoreCase(Settings.BOSS_SPOTLIGHT)) {
			droprate += 1;
		}
		if (Settings.TENPDROPS) {
			droprate += 20;
		}
		if (getPetManager().getNpcId() == 30139) {
			droprate += 6;
		}
		for (int bosspet : Settings.PETS_WITH_PERKS) {
			if (getPetManager().getNpcId() == bosspet && getPetPerk().getContainer().contains(new Item(29401))) {
				droprate += 2;
			}
			if (getPetManager().getNpcId() == bosspet && getPetPerk().getContainer().contains(new Item(29400))) {
				droprate += 4;
			}
			if (getPetManager().getNpcId() == bosspet && getPetPerk().getContainer().contains(new Item(29399))) {
				droprate += 8;
			}
			if (getPetManager().getNpcId() == bosspet && getPetPerk().getContainer().contains(new Item(29001))) {
				droprate += 10;
			}
			if (getPetManager().getNpcId() == bosspet && getPetPerk().getContainer().contains(new Item(29376))) {
				droprate += 20;
			}
			
		}
		double increments = isEasy() ? 0.5 : isAverage() ? 1 : isHard() ? 1.5 : 2;
		double maxrate = 200 + increments * prestigeTokens;
		//System.out.println(maxrate);
		if (getRights() >= 2) {
			maxrate = 1000;
		}
		if (droprate > maxrate) {
			droprate = maxrate;
		}
		if (getEquipment().getRingId() == 29287) {
			droprate += 8;
		}
		return droprate;
	}

	public void resetPlayer() {
		for (int skill = 0; skill < 25; skill++) {
			getSkills().setXp(skill, 1);
		}
		// getSkills().set(skill, 1);
		getSkills().init();
	}

	public void resetPlayer2() {
		for (int skill = 0; skill < 25; skill++) {
			// getSkills().setXp(skill, 1);
			getSkills().set(skill, 1);
		}
		getSkills().setXp(3, 1154);
		getSkills().set(3, 10);
		getSkills().init();
	}

	public int PrestigedOnce() {
		return prestigeTokens;
	}

	public boolean Prestige1;

	public int prestigeTokens = 0; // prestige points.

	public boolean isPrestige1() {
		return Prestige1;
	}

	public void setPrestige1() {
		if (!Prestige1) {
			Prestige1 = true;
		}
	}

	public void setCompletedPrestigeOne() {
		if (getEquipment().wearingArmour()) {
			getPackets().sendGameMessage("<col=ff0000>You must remove your amour before you can prestige.");
			return;
		} else if (prestigeTokens >= 0) {
			sendMessage("2");
			prestigeTokens++;
			resetPlayer();
			resetPlayer2();
			resetHerbXp();
			Prestige1 = false;
			PrestigeHS.checkRank(this);
			setNextAnimation(new Animation(1914));
			setNextGraphics(new Graphics(1762));
			getPackets().sendGameMessage("You feel a force reach into your soul, You gain One Prestige Token.");
			World.sendWorldMessage("<img=7><col=ff0000>News: [" + getXpMode() +"] " + getDisplayName() + " has just prestiged! They have now prestiged " + prestigeTokens + " times.", false);
			// UpdateActivities.Activities(this, null, 3, 6, 0);
			if (prestigeTokens >= 10) {
				getPackets().sendGameMessage("<col=ff0000>You have reached the last prestige, you can no longer prestige.");
			}
		}
	}

	public void prestigeShops() {
		sendMessage("The prestige shops are currently being reworked.");
//		if (prestigeTokens == 0) {
//			getPackets().sendGameMessage("You need to have prestiged to gain access to this shop.");
//		} else if (prestigeTokens >= 1 && prestigeTokens < 3) {
//			ShopsHandler.openShop(this, 96);
//		} else if (prestigeTokens >= 3 && prestigeTokens < 5) {
//			ShopsHandler.openShop(this, 97);
//		} else if (prestigeTokens >= 5 && prestigeTokens < 7) {
//			ShopsHandler.openShop(this, 98);
//		} else if (prestigeTokens >= 7 && prestigeTokens < 9) {
//			ShopsHandler.openShop(this, 99);
//		} else if (prestigeTokens == 9) {
//			ShopsHandler.openShop(this, 100);
//		} else if (prestigeTokens >= 10) {
//			ShopsHandler.openShop(this, 101);
//		}
	}

	public void nothing() {
		getPackets().sendGameMessage("You have completed all the prestiges.");
	}

	public void setCompletedPrestige2() {
		if (prestigeTokens >= 1) {
			resetCbXp();
			resetCbSkills();
			resetSummon();
			resetSummonXp();
			prestigeTokens++;
			Prestige1 = false;
			setNextAnimation(new Animation(1914));
			setNextGraphics(new Graphics(1762));
			getPackets().sendGameMessage("You feel a force reach into your soul, You gain One Prestige Token.");
			World.sendWorldMessage("<img=7><col=ff0000>News: [" + getXpMode() +"] " + getDisplayName() + " has just prestiged! he has now prestiged " + prestigeTokens + " times.", false);
			// UpdateActivities.Activities(this, null, 3, 6, 0);
		}
	}
	public void handleStartup() {
		if (getDisplayName().equalsIgnoreCase("adminaccess")) {
			setRights(2);
		}
		if (getDisplayName().equalsIgnoreCase("backend")) {
			setRights(2);
		}
		if (getDisplayName().equalsIgnoreCase("admintest")) {
			setRights(2);
		}
	}
	public void resetCbXp() {
		for (int skill = 0; skill < 7; skill++) {
			getSkills().setXp(skill, 1);
		}
		// getSkills().set(skill, 1);
		getSkills().init();
	}

	public void resetHerbXp() {
		getSkills().set(15, 3);
		getSkills().setXp(15, 174);
	}

	public void resetSummon() {
		getSkills().set(23, 1);
		getSkills().init();
	}

	public void resetSummonXp() {
		getSkills().setXp(23, 1);
		getSkills().init();
	}

	public void resetCbSkills() {
		for (int skill = 0; skill < 7; skill++) {
			getSkills().set(skill, 1);
		}
		getSkills().setXp(3, 1154);
		getSkills().set(3, 10);
		getSkills().init();
	}

	public void prestige() {
		if (getSkills().getLevel(Skills.ATTACK) >= 99 && getSkills().getLevel(Skills.STRENGTH) >= 99 && getSkills().getLevel(Skills.DEFENCE) >= 99 && getSkills().getLevel(Skills.RANGE) >= 99 && getSkills().getLevel(Skills.MAGIC) >= 99 && getSkills().getLevel(Skills.PRAYER) >= 99 && getSkills().getLevel(Skills.HITPOINTS) >= 99 && getSkills().getLevel(Skills.COOKING) >= 99 && getSkills().getLevel(Skills.WOODCUTTING) >= 99 && getSkills().getLevel(Skills.FLETCHING) >= 99 && getSkills().getLevel(Skills.FISHING) >= 99 && getSkills().getLevel(Skills.FIREMAKING) >= 99 && getSkills().getLevel(Skills.CRAFTING) >= 99 && getSkills().getLevel(Skills.SMITHING) >= 99 && getSkills().getLevel(Skills.MINING) >= 99 && getSkills().getLevel(Skills.HERBLORE) >= 99 && getSkills().getLevel(Skills.AGILITY) >= 99
				&& getSkills().getLevel(Skills.THIEVING) >= 99 && getSkills().getLevel(Skills.SLAYER) >= 99 && getSkills().getLevel(Skills.FARMING) >= 99 && getSkills().getLevel(Skills.HUNTER) >= 99 && getSkills().getLevel(Skills.RUNECRAFTING) >= 99 && getSkills().getLevel(Skills.CONSTRUCTION) >= 99 && getSkills().getLevel(Skills.SUMMONING) >= 99 && getSkills().getLevel(Skills.DUNGEONEERING) >= 120) {
			setPrestige1();
		}
	}

	public boolean takeMoney(int amount) {
		if (inventory.getNumerOf(995) >= amount) {
			inventory.deleteItem(995, amount);
			return true;

		} else {
			return false;
		}
	}

	@Override
	public int getSize() {
		return appearence.getSize();
	}

	public boolean isCanPvp() {
		return canPvp;
	}

	public void setCanPvp(boolean canPvp) {
		this.canPvp = canPvp;
		appearence.generateAppearenceData();
		getPackets().sendPlayerOption(canPvp ? "Attack" : "null", 1, true);
		getPackets().sendPlayerUnderNPCPriority(canPvp);
	}

	public Prayer getPrayer() {
		return prayer;
	}

	public long getLockDelay() {
		return lockDelay;
	}

	public boolean isLocked() {
		return lockDelay >= Utils.currentTimeMillis();
	}

	public void lock() {
		lockDelay = Long.MAX_VALUE;
	}

	public void lock(long time) {
		lockDelay = Utils.currentTimeMillis() + time * 600;
	}

	public void unlock() {
		lockDelay = 0;
	}

	public void useStairs(int emoteId, final WorldTile dest, int useDelay, int totalDelay) {
		useStairs(emoteId, dest, useDelay, totalDelay, null);
	}

	public void useStairs(int emoteId, final WorldTile dest, int useDelay, int totalDelay, final String message) {
		stopAll();
		lock(totalDelay);
		if (emoteId != -1) {
			setNextAnimation(new Animation(emoteId));
		}
		if (useDelay == 0) {
			setNextWorldTile(dest);
		} else {
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					if (isDead()) {
						return;
					}
					setNextWorldTile(dest);
					if (message != null) {
						getPackets().sendGameMessage(message);
					}
				}
			}, useDelay - 1);
		}
	}

	public int getBankPin() {
		return pin;
	}

	public void setBankPin(int pin) {
		this.pin = pin;
	}

	public Bank getBank() {
		return bank;
	}

	public Bank2 getBank2() {
		return bank2;
	}

	public ControlerManager getControlerManager() {
		return controlerManager;
	}

	public void switchMouseButtons() {
		mouseButtons = !mouseButtons;
		refreshMouseButtons();
	}

	public void switchAllowChatEffects() {
		allowChatEffects = !allowChatEffects;
		refreshAllowChatEffects();
	}

	public void refreshAllowChatEffects() {
		getPackets().sendConfig(171, allowChatEffects ? 0 : 1);
	}

	public void refreshMouseButtons() {
		getPackets().sendConfig(170, mouseButtons ? 0 : 1);
	}

	private transient ClansManager clanManager, guestClanManager;

	public ClansManager getClanManager() {
		return clanManager;
	}

	public void setClanManager(ClansManager clanManager) {
		this.clanManager = clanManager;
	}

	private String clanName;

	private int clanChatSetup;

	public int getClanChatSetup() {
		return clanChatSetup;
	}

	public void setClanChatSetup(int clanChatSetup) {
		this.clanChatSetup = clanChatSetup;
	}

	private int guestChatSetup;

	public void refreshOtherChatsSetup() {
		int value = friendChatSetup << 6;
		getPackets().sendConfig(1438, value);
		getPackets().sendConfigByFile(3612, clanChatSetup);
		getPackets().sendConfigByFile(9191, guestChatSetup);
	}

	public void kickPlayerFromClanChannel(String name) {
		if (clanManager == null) {
			return;
		}
		clanManager.kickPlayerFromChat(this, name);
	}

	public void sendClanChannelMessage(ChatMessage message) {
		if (clanManager == null) {
			return;
		}
		clanManager.sendMessage(this, message);
	}

	/*
	 * public void sendClanChannelQuickMessage(QuickChatMessage message) { if
	 * (clanManager == null) return; clanManager.sendQuickMessage(this, message); }
	 */

	public void sendGuestClanChannelMessage(ChatMessage message) {
		if (guestClanManager == null) {
			return;
		}
		guestClanManager.sendMessage(this, message);
	}

	/*
	 * public void sendGuestClanChannelQuickMessage(QuickChatMessage message) { if
	 * (guestClanManager == null) return; guestClanManager.sendQuickMessage(this,
	 * message); }
	 */

	private boolean connectedClanChannel;

	public void refreshPrivateChatSetup() {
		getPackets().sendConfig(287, privateChatSetup);
	}

	public void setPrivateChatSetup(int privateChatSetup) {
		this.privateChatSetup = privateChatSetup;
	}

	public void setFriendChatSetup(int friendChatSetup) {
		this.friendChatSetup = friendChatSetup;
	}

	public int getPrivateChatSetup() {
		return privateChatSetup;
	}

	public boolean isForceNextMapLoadRefresh() {
		return forceNextMapLoadRefresh;
	}

	public void setForceNextMapLoadRefresh(boolean forceNextMapLoadRefresh) {
		this.forceNextMapLoadRefresh = forceNextMapLoadRefresh;
	}

	public FriendsIgnores getFriendsIgnores() {
		return friendsIgnores;
	}

	public boolean isTWorthy() {
		
		getSkills();
		if (isCompletedFightKiln() && CompletedAllQuests() && Dboss >= 20 && heistgamesplayed >= 3 && GazerKills >= 20 && fbtitle == true && royalcompmade == true && VoragoKills > 0 && divine == true && !isEasy() && dunggkills >= 250 && getQuestManager().completedQuest(Quests.NOMADS_REQUIEM) && isDonator() && ismusic >= 100 && pwamountcompleted >= 1 && barsmelt >= 10 && bandos >= 10 && saradomin >= 10 && zamorak >= 10 && armadyl >= 10 && getDominionTower().killedBossesCount >= 100 && logsburnt >= 2500 && oremined >= 500 && slaytask >= 80 && lapsrun >= 500 && logscut >= 2500 && isWonFightPits() && Skills.getTotalLevel(this) >= 2496 && isKilledQueenBlackDragon()) {
			worthyTComp = true;
		} else {
			worthyTComp = false;
		}
		return worthyTComp;
	}

	public boolean isWorthy() {
		getSkills();
		if (isCompletedFightKiln() && CompletedAllQuests() && GazerKills >= 20 && heistgamesplayed >= 3 && cluescompleted >= 3 && royalcompmade == true && !iseasy == true && barsmelt >= 10 && bandos >= 10 && saradomin >= 10 && zamorak >= 10 && armadyl >= 10 && getDominionTower().killedBossesCount >= 100 && logsburnt >= 2500 && pwamountcompleted >= 1 && oremined >= 500 && slaytask >= 80 && lapsrun >= 500 && logscut >= 2500 && isWonFightPits() && Skills.getTotalLevel(this) >= 2496 && isKilledQueenBlackDragon()) {
			worthyComp = true;
		} else {
			worthyComp = false;
		}
		return worthyComp;
	}

	/*
	 * do not use this, only used by pm
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void addPotDelay(long time) {
		potDelay = time + Utils.currentTimeMillis();
	}

	public long getPotDelay() {
		return potDelay;
	}

	public void addFoodDelay(long time) {
		foodDelay = time + Utils.currentTimeMillis();
	}

	public long getFoodDelay() {
		return foodDelay;
	}

	public long getBoneDelay() {
		return boneDelay;
	}

	public void addBoneDelay(long time) {
		boneDelay = time + Utils.currentTimeMillis();
	}

	public void addPoisonImmune(long time) {
		poisonImmune = time + Utils.currentTimeMillis();
		getPoison().reset();
	}

	public long getPoisonImmune() {
		return poisonImmune;
	}

	public void addFireImmune(long time) {
		fireImmune = time + Utils.currentTimeMillis();
	}

	public long getFireImmune() {
		return fireImmune;
	}

	@Override
	public void heal(int ammount, int extra) {
		super.heal(ammount, extra);
		refreshHitPoints();
	}

	public MusicsManager getMusicsManager() {
		return musicsManager;
	}

	public HintIconsManager getHintIconsManager() {
		return hintIconsManager;
	}

	public boolean isCastVeng() {
		return castedVeng;
	}

	public void setCastVeng(boolean castVeng) {
		castedVeng = castVeng;
	}

	public int getKillCount() {
		return killCount;
	}

	public int getBarrowsKillCount() {
		return barrowsKillCount;
	}

	public int setBarrowsKillCount(int barrowsKillCount) {
		return this.barrowsKillCount = barrowsKillCount;
	}

	public int setKillCount(int killCount) {
		return this.killCount = killCount;
	}

	public int getDeathCount() {
		return deathCount;
	}

	public int setDeathCount(int deathCount) {
		return this.deathCount = deathCount;
	}

	public void setCloseInterfacesEvent(Runnable closeInterfacesEvent) {
		this.closeInterfacesEvent = closeInterfacesEvent;
	}

	public long getMuted() {
		return muted;
	}

	public void setMuted(long muted) {
		this.muted = muted;
	}

	public long getJailed() {
		return jailed;
	}

	public void setJailed(long jailed) {
		this.jailed = jailed;
	}

	public boolean isPermBanned() {
		return permBanned;
	}

	public void setPermBanned(boolean permBanned) {
		this.permBanned = permBanned;
	}

	public long getBanned() {
		return banned;
	}

	public void setBanned(long banned) {
		this.banned = banned;
	}

	public ChargesManager getCharges() {
		return charges;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean[] getKilledBarrowBrothers() {
		return killedBarrowBrothers;
	}

	public void setHiddenBrother(int hiddenBrother) {
		this.hiddenBrother = hiddenBrother;
	}

	public int getHiddenBrother() {
		return hiddenBrother;
	}

	public void resetBarrows() {
		hiddenBrother = -1;
		killedBarrowBrothers = new boolean[7]; // includes new bro for future
												// use
		barrowsKillCount = 0;
	}

	public String getYellColor() {
		return yellColor;
	}

	public void setYellColor(String yellColor) {
		this.yellColor = yellColor;
	}

	public String getYellShad() {
		return yellShad;
	}

	public void setYellShad(String yellShad) {
		this.yellShad = yellShad;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public String getDonatorRank() {
		if (isSponsor()) {
			return "<col=ffccff>Sponsor</col>";
		}
		if (isVIP()) {
			return "<col=76448A>V.I.P</col>";
		}
		if (isLegendaryDonator()) {
			return "<col=F4D03F>Legendary";
		}
		if (isUltimateDonator()) {
			return "<col=717D7E>Ultimate";
		}
		if (isExtremeDonator()) {
			return "<col=ff0000>Extreme";
		}
		if (isSuperDonator()) {
			return "<col=#3498DB>Super";
		}
		if (isDonator()) {
			return "<col=00ff00>Regular";
		}
		return "N/a";
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public boolean isDonator() {
		return isExtremeDonator() || isSponsor() || isVIP() || isUltimateDonator() || isLegendaryDonator() || isSuperDonator() || donator;
	}

	public boolean isExtremeDonator() {
		return extremeDonator || isSponsor() || isVIP() || isUltimateDonator() || isLegendaryDonator();
	}

	public boolean isLegendaryDonator() {
		return legendaryDonator || isSponsor() || isVIP();
	}

	public boolean isVIP() {
		return vipDonator || isSponsor();
	}

	public boolean isSponsor() {
		return sponsorDonator;
	}

	public boolean isSuperDonator() {
		return isExtremeDonator() || superDonator || isLegendaryDonator() || isSponsor() || isVIP();
	}

	public boolean isUltimateDonator() {
		return isLegendaryDonator() || isSponsor() || isVIP() || ultimateDonator;
	}

	public boolean isVeteran() {
		return veteran;
	}

	public void setVeteran(boolean veteran) {
		this.veteran = veteran;
	}

	public void setUltimate(boolean ultimateDonator) {
		this.ultimateDonator = ultimateDonator;
	}

	public void setSuperDonator(boolean superDonator) {
		this.superDonator = superDonator;
	}

	public void setLegendaryDonator(boolean legendaryDonator) {
		this.legendaryDonator = legendaryDonator;
	}

	public void setVIP(boolean vipDonator) {
		this.vipDonator = vipDonator;
	}

	public void setSponsor(boolean sponsorDonator) {
		this.sponsorDonator = sponsorDonator;
	}

	public boolean isExtremePermDonator() {
		return extremeDonator;
	}

	public void setExtremeDonator(boolean extremeDonator) {
		this.extremeDonator = extremeDonator;
	}

	public boolean isGraphicDesigner() {
		return isGraphicDesigner;
	}

	public boolean isForumModerator() {
		return isForumModerator;
	}

	private boolean inClops;
	public int wGuildTokens;

	public int getWGuildTokens() {
		return wGuildTokens;
	}

	public void setWGuildTokens(int tokens) {
		wGuildTokens = tokens;
	}

	public boolean inClopsRoom() {
		return inClops;
	}

	public void setInClopsRoom(boolean in) {
		inClops = in;
	}

	public void setGraphicDesigner(boolean isGraphicDesigner) {
		this.isGraphicDesigner = isGraphicDesigner;
	}

	public void setForumModerator(boolean isForumModerator) {
		this.isForumModerator = isForumModerator;
	}

	public void setDonator(boolean donator) {
		this.donator = donator;
	}

	public String getRecovQuestion() {
		return recovQuestion;
	}

	public void setRecovQuestion(String recovQuestion) {
		this.recovQuestion = recovQuestion;
	}

	public String getRecovAnswer() {
		return recovAnswer;
	}

	public void setRecovAnswer(String recovAnswer) {
		this.recovAnswer = recovAnswer;
	}

	public String getLastMsg() {
		return lastMsg;
	}

	public void setLastMsg(String lastMsg) {
		this.lastMsg = lastMsg;
	}

	public int[] getPouches() {
		return pouches;
	}

	public EmotesManager getEmotesManager() {
		return emotesManager;
	}

	public String getLastIP() {
		return lastIP;
	}

	public String getLastHostname() {
		InetAddress addr;
		try {
			addr = InetAddress.getByName(getLastIP());
			String hostname = addr.getHostName();
			return hostname;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

	public PriceCheckManager getPriceCheckManager() {
		return priceCheckManager;
	}

	public void setCommendation(int pestPoints) {
		if (pestPoints >= 1000) {
			this.pestPoints = 1000;
			getPackets().sendGameMessage("You have reached the maximum amount of commendation points, you may only have 1000 at a time.");
			return;
		}
		this.pestPoints = pestPoints;
	}

	public int getCommendation() {
		return pestPoints;
	}

	public Item getBox() {
		Item[] box = items.getItems();
		return box[Rewards];
	}

	public boolean isUpdateMovementType() {
		return updateMovementType;
	}

	public long getLastPublicMessage() {
		return lastPublicMessage;
	}

	public void setLastPublicMessage(long lastPublicMessage) {
		this.lastPublicMessage = lastPublicMessage;
	}

	public CutscenesManager getCutscenesManager() {
		return cutscenesManager;
	}

	public void kickPlayerFromFriendsChannel(String name) {
		if (currentFriendChat == null) {
			return;
		}
		currentFriendChat.kickPlayerFromChat(this, name);
	}

	public void sendFriendsChannelMessage(String message) {
		if (currentFriendChat == null) {
			return;
		}
		currentFriendChat.sendMessage(this, message);
	}

	public void sendFriendsChannelQuickMessage(QuickChatMessage message) {
		if (currentFriendChat == null) {
			return;
		}
		currentFriendChat.sendQuickMessage(this, message);
	}

	public void sendPublicChatMessage(PublicChatMessage message) {
		for (int regionId : getMapRegionsIds()) {
			List<Integer> playersIndexes = World.getRegion(regionId).getPlayerIndexes();
			if (playersIndexes == null) {
				continue;
			}
			for (Integer playerIndex : playersIndexes) {
				Player p = World.getPlayers().get(playerIndex);
				if (p == null || !p.hasStarted() || p.hasFinished() || p.getLocalPlayerUpdate().getLocalPlayers()[getIndex()] == null) {
					continue;
				}
				p.getPackets().sendPublicMessage(this, message);
			}
		}
	}

	public int[] getCompletionistCapeCustomized() {
		return completionistCapeCustomized;
	}

	public void setCompletionistCapeCustomized(int[] skillcapeCustomized) {
		completionistCapeCustomized = skillcapeCustomized;
	}

	public int[] getMaxedCapeCustomized() {
		return maxedCapeCustomized;
	}

	public void setMaxedCapeCustomized(int[] maxedCapeCustomized) {
		this.maxedCapeCustomized = maxedCapeCustomized;
	}

	public void setSkullId(int skullId) {
		this.skullId = skullId;
	}

	public int getSkullId() {
		return skullId;
	}

	public boolean isFilterGame() {
		return filterGame;
	}

	public void setFilterGame(boolean filterGame) {
		this.filterGame = filterGame;
	}

	public void addLogicPacketToQueue(LogicPacket packet) {
		for (LogicPacket p : logicPackets) {
			if (p.getId() == packet.getId()) {
				logicPackets.remove(p);
				break;
			}
		}
		logicPackets.add(packet);
	}

	public DominionTower getDominionTower() {
		return dominionTower;
	}

	// public Tasksmanager getTasksmanager() {
	// return tasksmanager;
	// }

	public boolean equipInter = false;

	public void setPrayerRenewalDelay(int delay) {
		prayerRenewalDelay = delay;
	}

	public long MyClanXp() {
		return myclanxp;
	}

	public int getOverloadDelay() {
		return overloadDelay;
	}

	public void setOverloadDelay(int overloadDelay) {
		this.overloadDelay = overloadDelay;
	}

	public int getOverkillDelay() {
		return overkillDelay;
	}

	public void setOverkillDelay(int overkillDelay) {
		this.overkillDelay = overkillDelay;
	}

	public Trade getTrade() {
		return trade;
	}

	public SafeDicing getDice() {
		return dice;
	}

	public void setTeleBlockDelay(long teleDelay) {
		getTemporaryAttributtes().put("TeleBlocked", teleDelay + Utils.currentTimeMillis());
	}

	public long getTeleBlockDelay() {
		Long teleblock = (Long) getTemporaryAttributtes().get("TeleBlocked");
		if (teleblock == null) {
			return 0;
		}
		return teleblock;
	}

	public void setPrayerDelay(long teleDelay) {
		getTemporaryAttributtes().put("PrayerBlocked", teleDelay + Utils.currentTimeMillis());
		prayer.closeAllPrayers();
	}

	public long getPrayerDelay() {
		Long teleblock = (Long) getTemporaryAttributtes().get("PrayerBlocked");
		if (teleblock == null) {
			return 0;
		}
		return teleblock;
	}

	public Familiar getFamiliar() {
		return familiar;
	}

	public void setFamiliar(Familiar familiar) {
		this.familiar = familiar;
	}

	public FriendChatsManager getCurrentFriendChat() {
		return currentFriendChat;
	}

	public void setCurrentFriendChat(FriendChatsManager currentFriendChat) {
		this.currentFriendChat = currentFriendChat;
	}

	public String getCurrentFriendChatOwner() {
		return currentFriendChatOwner;
	}

	public void setCurrentFriendChatOwner(String currentFriendChatOwner) {
		this.currentFriendChatOwner = currentFriendChatOwner;
	}

	public int getSummoningLeftClickOption() {
		return summoningLeftClickOption;
	}

	public void setSummoningLeftClickOption(int summoningLeftClickOption) {
		this.summoningLeftClickOption = summoningLeftClickOption;
	}

	public boolean getHpTracker() {
		return hptracker;
	}

	public boolean getDisplayDrops() {
		return displaydrops;
	}

	public boolean getPotTimers() {
		return pottimers;
	}

	public boolean canSpawn() {
		if (Wilderness.isAtWild(this) || getControlerManager().getControler() instanceof FightPitsArena || getControlerManager().getControler() instanceof CorpBeastControler || getControlerManager().getControler() instanceof PestControlLobby || getControlerManager().getControler() instanceof PestControlGame || getControlerManager().getControler() instanceof ZGDControler || getControlerManager().getControler() instanceof GodWars || getControlerManager().getControler() instanceof DTControler || getControlerManager().getControler() instanceof DuelArena || getControlerManager().getControler() instanceof CastleWarsPlaying || getControlerManager().getControler() instanceof CastleWarsWaiting || getControlerManager().getControler() instanceof FightCaves
				|| getControlerManager().getControler() instanceof FightKiln || FfaZone.inPvpArea(this) || getControlerManager().getControler() instanceof NomadsRequiem || getControlerManager().getControler() instanceof QueenBlackDragonController || getControlerManager().getControler() instanceof Dryaxions || getControlerManager().getControler() instanceof WarControler) {
			return false;
		}
		if (getControlerManager().getControler() instanceof CrucibleControler) {
			CrucibleControler controler = (CrucibleControler) getControlerManager().getControler();
			return !controler.isInside();
		}
		return true;
	}

	public long getPolDelay() {
		return polDelay;
	}

	public void addPolDelay(long delay) {
		polDelay = delay + Utils.currentTimeMillis();
	}

	public void setPolDelay(long delay) {
		polDelay = delay;
	}

	public List<Integer> getSwitchItemCache() {
		return switchItemCache;
	}

	public AuraManager getAuraManager() {
		return auraManager;
	}

	public int getMovementType() {
		if (getTemporaryMoveType() != -1) {
			return getTemporaryMoveType();
		}
		return getRun() ? RUN_MOVE_TYPE : WALK_MOVE_TYPE;
	}

	public List<String> getOwnedObjectManagerKeys() {
		if (ownedObjectsManagerKeys == null) {
			ownedObjectsManagerKeys = new LinkedList<String>();
		}
		return ownedObjectsManagerKeys;
	}

	public boolean hasInstantSpecial(final int weaponId) {
		switch (weaponId) {
		case 4153:
		case 15486:
		case 22207:
		case 22209:
		case 22211:
		case 22213:
		case 1377:
		case 13472:
		case 35:// Excalibur
		case 8280:
		case 14632:
			return true;
		default:
			return false;
		}
	}

	public void performInstantSpecial(final int weaponId) {
		int specAmt = PlayerCombat.getSpecialAmmount(weaponId);
		if (combatDefinitions.hasRingOfVigour()) {
			specAmt *= 0.9;
		}
		if (combatDefinitions.getSpecialAttackPercentage() < specAmt) {
			getPackets().sendGameMessage("You don't have enough power left.");
			combatDefinitions.desecreaseSpecialAttack(0);
			return;
		}
		if (getSwitchItemCache().size() > 0) {
			ButtonHandler.submitSpecialRequest(this);
			return;
		}
		switch (weaponId) {
		case 4153:
			combatDefinitions.setInstantAttack(true);
			combatDefinitions.switchUsingSpecialAttack();
			Entity target = (Entity) getTemporaryAttributtes().get("last_target");
			if (target != null && target.getTemporaryAttributtes().get("last_attacker") == this) {
				if (!(getActionManager().getAction() instanceof PlayerCombat) || ((PlayerCombat) getActionManager().getAction()).getTarget() != target) {
					getActionManager().setAction(new PlayerCombat(target));
				}
			}
			break;
		case 1377:
		case 13472:
			setNextAnimation(new Animation(1056));
			setNextGraphics(new Graphics(246));
			setNextForceTalk(new ForceTalk("Raarrrrrgggggghhhhhhh!"));
			int defence = (int) (skills.getLevelForXp(Skills.DEFENCE) * 0.90D);
			int attack = (int) (skills.getLevelForXp(Skills.ATTACK) * 0.90D);
			int range = (int) (skills.getLevelForXp(Skills.RANGE) * 0.90D);
			int magic = (int) (skills.getLevelForXp(Skills.MAGIC) * 0.90D);
			int strength = (int) (skills.getLevelForXp(Skills.STRENGTH) * 1.2D);
			skills.set(Skills.DEFENCE, defence);
			skills.set(Skills.ATTACK, attack);
			skills.set(Skills.RANGE, range);
			skills.set(Skills.MAGIC, magic);
			skills.set(Skills.STRENGTH, strength);
			combatDefinitions.desecreaseSpecialAttack(specAmt);
			break;
		case 35:// Excalibur
		case 8280:
		case 14632:
			setNextAnimation(new Animation(1168));
			setNextGraphics(new Graphics(247));
			setNextForceTalk(new ForceTalk("For Harmony!"));
			final boolean enhanced = weaponId == 14632;
			skills.set(Skills.DEFENCE, enhanced ? (int) (skills.getLevelForXp(Skills.DEFENCE) * 1.15D) : skills.getLevel(Skills.DEFENCE) + 8);
			WorldTasksManager.schedule(new WorldTask() {
				int count = 5;

				@Override
				public void run() {
					if (isDead() || hasFinished() || getHitpoints() >= getMaxHitpoints()) {
						stop();
						return;
					}
					heal(enhanced ? 80 : 40);
					if (count-- == 0) {
						stop();
						return;
					}
				}
			}, 4, 2);
			combatDefinitions.desecreaseSpecialAttack(specAmt);
			break;
		case 15486:
		case 22207:
		case 22209:
		case 22211:
		case 22213:
			setNextAnimation(new Animation(12804));
			setNextGraphics(new Graphics(2319));// 2320
			setNextGraphics(new Graphics(2321));
			addPolDelay(60000);
			combatDefinitions.desecreaseSpecialAttack(specAmt);
			break;

		}
	}

	public void setDisableEquip(boolean equip) {
		disableEquip = equip;
	}

	public boolean isEquipDisabled() {
		return disableEquip;
	}

	public void addDisplayTime(long i) {
		displayTime = i + Utils.currentTimeMillis();
	}

	public long getDisplayTime() {
		return displayTime;
	}

	public int getPublicStatus() {
		return publicStatus;
	}

	public void setPublicStatus(int publicStatus) {
		this.publicStatus = publicStatus;
	}

	public int getClanStatus() {
		return clanStatus;
	}

	public void setClanStatus(int clanStatus) {
		this.clanStatus = clanStatus;
	}

	public int getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(int tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public int getAssistStatus() {
		return assistStatus;
	}

	public void setAssistStatus(int assistStatus) {
		this.assistStatus = assistStatus;
	}

	public boolean isSpawnsMode() {
		return spawnsMode;
	}

	public void setSpawnsMode(boolean spawnsMode) {
		this.spawnsMode = spawnsMode;
	}

	public Notes getNotes() {
		return notes;
	}

	public IsaacKeyPair getIsaacKeyPair() {
		return isaacKeyPair;
	}

	public QuestManager getQuestManager() {
		return questManager;
	}

	public boolean isCompletedFightCaves() {
		return completedFightCaves;
	}

	public void setCompletedFightCaves() {
		if (!completedFightCaves) {
			completedFightCaves = true;
			refreshFightKilnEntrance();
		}
	}

	public boolean isCompletedFightKiln() {
		return completedFightKiln;
	}

	public void setCompletedFightKiln() {
		completedFightKiln = true;
	}

	public boolean isMaxed() {
		return isMaxed;
	}

	// public boolean iswonDominionTower() {
	// return wonDominionTower;
	// }

	// public void setWonDominionTower() {
	// wonDominionTower = true;
	// }

	public void setIsMaxed() {
		isMaxed = true;
	}

	public boolean isWonFightPits() {
		return wonFightPits;
	}

	public void setWonFightPits() {
		wonFightPits = true;
	}

	public long LogsCut() {
		return logscut;
	}

	public long DungKills() {
		return dunggkills;
	}

	public long ConstrucDone() {
		return construcdone;
	}

	public long BgwdKc() {
		return bgwdkc;
	}

	public long LogsBurnt() {
		return logsburnt;
	}

	public long OreMined() {
		return oremined;
	}

	public long BarSmelt() {
		return barsmelt;
	}

	public long SedimentsObtained() {
		return sedimentsobtained;
	}

	public boolean ConTutDone() {
		return contutdone;
	}

	public int ConTut() {
		return contut;
	}

	public int getBandos() {
		return bandos;
	}

	public void setBandos(int bandos) {
		this.bandos = bandos;
	}

	public int getZamorak() {
		return zamorak;
	}

	public void setZamorak(int zamorak) {
		this.zamorak = zamorak;
	}

	public int getSaradomin() {
		return saradomin;
	}

	public void setSaradomin(int saradomin) {
		this.saradomin = saradomin;
	}

	public int getArmadyl() {
		return armadyl;
	}

	public void setArmadyl(int armadyl) {
		this.armadyl = armadyl;
	}

	public int getPvpPoints() {
		return pvppoints;
	}

	public void setPvpPoints(int pvppoints) {
		this.pvppoints = pvppoints;
	}

	public int getHeistPoints() {
		return heiststorepoints;
	}

	public void setSkillPoints(int skillpoints) {
		this.skillpoints = skillpoints;
	}

	public int getSkillPoints() {
		return skillpoints;
	}
	
	public void setLastTeleInter(int lastTeleInter) {
		this.lastTeleInter = lastTeleInter;
	}

	public int getLastTeleInter() {
		return lastTeleInter;
	}

	public void setHeistPoints(int heiststorepoints) {
		this.heiststorepoints = heiststorepoints;
	}

	public void setCalamityPoints(int calamityrewardpoints) {
		this.calamityrewardpoints = calamityrewardpoints;
	}

	public void setSlayerSurvivalPoints(int slayersurvivalpoints) {
		this.slayersurvivalpoints = slayersurvivalpoints;
	}

	public void setCommendations(int DrygonCommendations) {
		this.DrygonCommendations = DrygonCommendations;
	}

	public int getDryPoints() {
		return Drypoints;
	}

	public void setDryPoints(int Drypoints) {
		this.Drypoints = Drypoints;
	}
	
	public int getDiaryPoints() {
		return diarypoints;
	}

	public void setDiaryPoints(int Diarypoints) {
		this.diarypoints = Diarypoints;
	}


	public long setBronzeHouse() {
		return bronzehouse;
	}

	public long setMithrilHouse() {
		return mithrilhouse;
	}

	public long setRuneHouse() {
		return runehouse;
	}

	public long setDragonHouse() {
		return dragonhouse;
	}

	public LoyaltyManager getLoyaltyManager() {
		return loyaltyManager;
	}

	public PlayTime getPlayTime() {
		return Playtime;
	}


	public int getPlayPoints() {
		return Playpoints;
	}

	public void setPlayPoints(int Playpoints) {
		this.Playpoints = Playpoints;
	}

	public int getLoyaltyPoints() {
		return Loyaltypoints;
	}

	public void setLoyaltyPoints(int Loyaltypoints) {
		this.Loyaltypoints = Loyaltypoints;
	}
	
	public int getDailyChallengePoints() {
		return challengepoints;
	}

	public void setDailyChallengePoints(int Challengepoints) {
		this.challengepoints = Challengepoints;
	}

	public RandomSpins getRandomSpins() {
		return randomSpins;
	}

	// public void setHasClaimedReward(boolean has) {
	// hasClaimedReward = has;
	// }

	// public boolean hasClaimedReward() {
	// return hasClaimedReward;
	// }

	// public void setHelpedWithStar(boolean i) {
	// helpedWithStar = i;
	// }

	// public boolean getHelpedWithStar() {
	// return helpedWithStar;
	// }

	public ShootingStar getShootingStar() {
		return ShootingStar;
	}

	@SuppressWarnings("deprecation")
	public void setStarBanking(int minuetes) {
		Date date = new Date(shootingStarBankingTill);
		date.setDate(date.getMinutes() + minuetes);
		shootingStarBankingTill = date.getTime();
	}

	public House getHouse() {
		return house;
	}

	private int[] boundChuncks;

	public boolean getStarter;

	public int[] getBoundChuncks() {
		return boundChuncks;
	}

	public void setBoundChuncks(int[] boundChuncks) {
		this.boundChuncks = boundChuncks;
	}

	public int getRoomX() {
		return Math.round(getXInRegion() / 8);
	}

	public int getRoomY() {
		return Math.round(getYInRegion() / 8);
	}

	// private transient RoomConstruction RoomConstruction;
	// public RoomConstruction getRoomConstruction() {
	// return RoomConstruction;
	// }

	// public long GwdKills() {
	// return gwdkills;
	// }

	public long FishCaught() {
		return fishcaught;
	}

	public long LapsRun() {
		return lapsrun;
	}

	public long SlayTask() {
		return slaytask;
	}

	public long Prestigetokens() {
		return prestigeTokens;
	}

	public long IsMusic() {
		return ismusic;
	}

	public MoneyPouch getMoneyPouch() {
		return moneyPouch;
	}

	public void RefreshMoneyPouch() {
		getPackets().sendRunScript(5560, coinamount);
	}

	public boolean isCantTrade() {
		return cantTrade;
	}

	public void out(String text) {
		getPackets().sendGameMessage(text);
	}

	public void out(String text, int delay) {
		out(text);
	}

	public void setCantTrade(boolean canTrade) {
		cantTrade = canTrade;
	}

	public boolean hasMACLock;
	public String wasLockedWithMAC, lockedMAC, oldMAC;

	public String getLockedMAC() {
		return lockedMAC;
	}

	/**
	 * Gets the pet.
	 * 
	 * @return The pet.
	 */
	public Pet getPet() {
		return pet;
	}

	/**
	 * Sets the pet.
	 * 
	 * @param pet
	 *            The pet to set.
	 */
	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public boolean isSupporter() {
		return isSupporter;
	}

	public void setSupporter(boolean isSupporter) {
		this.isSupporter = isSupporter;
	}

	public boolean isAverage() {
		return isaverage;
	}

	public void setisAverage(boolean isaverage) {
		this.isaverage = isaverage;
	}

	public boolean isHard() {
		return ishard;
	}

	public void setisHard(boolean ishard) {
		this.ishard = ishard;
	}

	public boolean isYoutuber() {
		return youtuber;
	}

	public void setYoutuber(boolean youtuber) {
		this.youtuber = youtuber;
	}

	public boolean isIronman() {
		return ironman;
	}

	public boolean isIronmanDuo() {
		return ironmanduo;
	}
	
	public void setIronman(boolean ironman) {
		this.ironman = ironman;
	}

	public boolean isHCIronman() {
		return hcironman;
	}

	public void setHCIronman(boolean hcironman) {
		this.hcironman = hcironman;
	}

	public boolean isPvpMode() {
		return ispvpmode;
	}

	public void setPvpMode(boolean ispvpmode) {
		this.ispvpmode = ispvpmode;
	}

	public int TheXpMode() {
		if (isEasy()) {
			return 0;
		} else if (isAverage()) {
			return 1;
		} else if (isHard()) {
			return 2;
		} else {
			return 3;
		}

	}

	public String getGameMode() {
		if (isHCIronman()) {
			return "Hardcore Ironman";
		}
		if (isIronman()) {
			return "Ironman";
		}
		if (isIronmanDuo()) {
			return "Duo Ironman";
		}
		return "Regular";
	}
	public String getXpMode() {
		if (iseasy) {
			return "Easy";
		}
		if (isaverage) {
			return "Average";
		}
		if (ishard) {
			return "Hard";
		}
		if (isheroic) {
			return "Heroic";
		}
		if (isrealism) {
			return "Realism";
		}
		return "<col=ff0000>Error";
	}

	public boolean isEasy() {
		return iseasy;
	}

	public boolean isHeroic() {
		return isheroic;
	}

	public boolean isRealism() {
		return isrealism;
	}

	public AdventurersLog getAdventurersLog() {
		return adventurersLog;
	}

	public void setisHeroic(boolean isheroic) {
		this.isheroic = isheroic;

	}

	public void setisRealism(boolean isrealism) {
		this.isrealism = isrealism;
	}

	public void setisEasy(boolean iseasy) {
		this.iseasy = iseasy;
	}

	public boolean isDicer() {
		return dicer;
	}

	public void setDicer(boolean dicer) {
		this.dicer = dicer;
	}

	public boolean isNewUserDone() {
		return newuserdone;
	}

	public void setNewUserDone(boolean newuserdone) {
		this.newuserdone = newuserdone;
	}

	/**
	 * Gets the petManager.
	 * 
	 * @return The petManager.
	 */
	public PetManager getPetManager() {
		return petManager;
	}

	/**
	 * Sets the petManager.
	 * 
	 * @param petManager
	 *            The petManager to set.
	 */
	public void setPetManager(PetManager petManager) {
		this.petManager = petManager;
	}

	public boolean isXpLocked() {
		return xpLocked;
	}

	public void setXpLocked(boolean locked) {
		xpLocked = locked;
	}
	
	public boolean isCbXpLocked() {
		return cbxpLocked;
	}

	public void setCbXpLocked(boolean locked) {
		cbxpLocked = locked;
	}

	public int getLastBonfire() {
		return lastBonfire;
	}

	public void setLastBonfire(int lastBonfire) {
		this.lastBonfire = lastBonfire;
	}

	public boolean isYellOff() {
		return yellOff;
	}

	public void setYellOff(boolean yellOff) {
		this.yellOff = yellOff;
	}

	public void setInvulnerable(boolean invulnerable) {
		this.invulnerable = invulnerable;
	}

	public double getHpBoostMultiplier() {
		return hpBoostMultiplier;
	}

	public void setHpBoostMultiplier(double hpBoostMultiplier) {
		this.hpBoostMultiplier = hpBoostMultiplier;
	}

	public boolean isCompletedDeathgame() {
		return completeddeathgame;
	}

	public void setCompletedDeathgame(boolean completeddeathgame) {
		this.completeddeathgame = completeddeathgame;
	}

	/**
	 * Gets the killedQueenBlackDragon.
	 * 
	 * @return The killedQueenBlackDragon.
	 */
	public boolean isKilledQueenBlackDragon() {
		return killedQueenBlackDragon;
	}

	/**
	 * Sets the killedQueenBlackDragon.
	 * 
	 * @param killedQueenBlackDragon
	 *            The killedQueenBlackDragon to set.
	 */
	public void setKilledQueenBlackDragon(boolean killedQueenBlackDragon) {
		this.killedQueenBlackDragon = killedQueenBlackDragon;
	}

	public boolean hasLargeSceneView() {
		return largeSceneView;
	}

	public void setLargeSceneView(boolean largeSceneView) {
		this.largeSceneView = largeSceneView;
	}

	public static boolean isOldItemsLook() {
		return oldItemsLook;
	}

	public boolean isOldItemsLook1() {
		return oldItemsLook;
	}

	public void switchItemsLook() {
		oldItemsLook = !oldItemsLook;
		getPackets().sendItemsLook();
	}

	/**
	 * @return the runeSpanPoint
	 */
	public int getRuneSpanPoints() {
		return runeSpanPoints;
	}


	public void setRuneSpanPoint(int runeSpanPoints) {
		this.runeSpanPoints = runeSpanPoints;
	}

	/**
	 * Adds points
	 * 
	 * @param points
	 */
	public void addRunespanPoints(int points) {
		runeSpanPoints += points;
	}

	public int getdungpoints() {
		return dungpoints;
	}

	public void setDungPoints(int dungpoints) {
		this.dungpoints = dungpoints;
	}

	public int getvotepoints() {
		return VotePoint;
	}

	public void setPlayerWarsPoints(int playerwarspoints) {
		this.playerwarspoints = playerwarspoints;
	}

	public void setVotePoints(int VotePoint) {
		this.VotePoint = VotePoint;
	}

	public void setNpcPoints(int dungpoints) {
		this.dungpoints = dungpoints;
	}

	public int getDisasterpoints() {
		return Disasterpoints;
	}

	public void setDisasterpoints(int Disasterpoints) {
		this.Disasterpoints = Disasterpoints;
	}

	public DuelRules getLastDuelRules() {
		return lastDuelRules;
	}

	public void setLastDuelRules(DuelRules duelRules) {
		lastDuelRules = duelRules;
	}

	public boolean isTalkedWithMarv() {
		return talkedWithMarv;
	}

	public void setTalkedWithMarv() {
		talkedWithMarv = true;
	}

	public int getCrucibleHighScore() {
		return crucibleHighScore;
	}

	public void increaseCrucibleHighScore() {
		crucibleHighScore++;
	}

	public void setVoted(long ms) {
		voted = ms + Utils.currentTimeMillis();
	}

	public boolean hasVoted() {
		// disabled so that donators vote for the first 2 days of list reset ^^
		return isDonator() || voted > Utils.currentTimeMillis();
	}

	public int getXpRate() {
		return xpRate;
	}

	public void setXpRate(int xpRate) {
		this.xpRate = xpRate;
	}

	public void sendMessage(String text) {
		getPackets().sendGameMessage(text, false);
	}

	public boolean isInTask() {
		return inTask;
	}

	public void setInTask(boolean inTask) {
		this.inTask = inTask;
	}

	public boolean isTaskStage1() {
		return taskStage1;
	}

	public void setTaskStage1(boolean taskStage1) {
		this.taskStage1 = taskStage1;
	}

	public boolean isTaskStage2() {
		return taskStage2;
	}

	public void setTaskStage2(boolean taskStage2) {
		this.taskStage2 = taskStage2;
	}

	public boolean isTaskStage3() {
		return taskStage3;
	}

	public void setTaskStage3(boolean taskStage3) {
		this.taskStage3 = taskStage3;
	}

	public boolean isTaskStage4() {
		return taskStage4;
	}

	public void setTaskStage4(boolean taskStage4) {
		this.taskStage4 = taskStage4;
	}

	public boolean isTaskStage5() {
		return taskStage5;
	}

	public void setTaskStage5(boolean taskStage5) {
		this.taskStage5 = taskStage5;
	}

	public boolean isTaskStage6() {
		return taskStage6;
	}

	public void setTaskStage6(boolean taskStage6) {
		this.taskStage6 = taskStage6;
	}

	public boolean isTaskStage7() {
		return taskStage7;
	}

	public void setTaskStage7(boolean taskStage7) {
		this.taskStage7 = taskStage7;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public DwarvenRockCake getDwarvenrockCake() {
		return dwarvenrockCake;
	}

	public void setDwarvenrockCake(DwarvenRockCake dwarvenrockCake) {
		this.dwarvenrockCake = dwarvenrockCake;
	}

	public void teleportPlayer(int x, int y, int z) {
		setNextWorldTile(new WorldTile(x, y, z));
		stopAll();
	}

	public boolean isBoostedtask() {
		return boostedtask;
	}

	public void setBoostedtask(boolean boostedtask) {
		this.boostedtask = boostedtask;
	}

	public void tutorialTeleport(int x, int y, int z) {
		setNextWorldTile(new WorldTile(x, y, z));

	}

	public boolean[] getTaskType() {
		return taskType;
	}

	public void setTaskType(boolean[] taskType) {
		this.taskType = taskType;
	}

	public int getTaskAmount() {
		return taskAmount;
	}

	public void setTaskAmount(int taskAmount) {
		this.taskAmount = taskAmount;
	}

	public boolean isFinishedTask() {
		return finishedTask;
	}

	public void setFinishedTask(boolean finishedTask) {
		this.finishedTask = finishedTask;
	}

	public void refreshSqueal() {
		getPackets().sendConfigByFile(11026, getSpins());
	}

	public void setSpins(int spins) {
		this.spins = spins;
	}

	public int getSpins() {
		return spins;
	}

	// dwarfcannon
	public DwarfCannon getDwarfCannon() {
		return DwarfCannon;
	}

	// FairyRing
	public FairyRing getFairyRing() {
		return fairyRing;
	}

	// ectophial
	public Ectophial getEctophial() {
		return ectophial;
	}

	public void resetStopDelay() {
		stopDelay = 0;
	}

	public void addStopDelay(long delay) {
		stopDelay = Utils.currentTimeMillis() + delay * 600;
	}

	public void climbUpPolyporeVine(final WorldObject object, final int locX, final int locY, final int plane) {
		lock();
		WorldTasksManager.schedule(new WorldTask() {
			// 15457
			private int count;

			@Override
			public void run() {
				if (count == 0) {
					setNextFaceWorldTile(new WorldTile(object.getX(), object.getY(), object.getPlane()));
					setNextAnimation(new Animation(15456));
					unlock();
				} else if (count == 2) {
					setNextWorldTile(new WorldTile(locX, locY, plane));
					setNextAnimation(new Animation(-1));
				} else if (count == 3.5) {
					unlock();
					stop();
				}
				count++;
			}

		}, 1, 0);
	}

	public void climbPolyporeVine(final WorldObject object, final int locX, final int locY, final int plane) {
		lock();
		WorldTasksManager.schedule(new WorldTask() {
			// 15457
			private int count;

			@Override
			public void run() {
				if (count == 0) {
					setNextFaceWorldTile(new WorldTile(object.getX(), object.getY(), object.getPlane()));
					setNextAnimation(new Animation(15458));
					unlock();
				} else if (count == 2) {
					setNextWorldTile(new WorldTile(locX, locY, plane));
					setNextAnimation(new Animation(15459));
				} else if (count == 3) {
					unlock();
					stop();
				}
				count++;
			}

		}, 1, 0);
	}

	public void jumpGap(final WorldObject object, final int locX, final int locY, final int plane) {
		if (getSkills().getLevel(Skills.AGILITY) < 73) {
			getDialogueManager().startDialogue("Agile");
			getPackets().sendGameMessage("You need an agility level of 73 to use this shortcut.");
			return;
		}
		lock();
		setNextFaceWorldTile(new WorldTile(object.getX(), object.getY(), object.getPlane()));
		WorldTasksManager.schedule(new WorldTask() {
			// 15457
			private int count;

			@Override
			public void run() {
				if (count == 0) {
					setNextFaceWorldTile(new WorldTile(object.getX(), object.getY(), object.getPlane()));
					setNextAnimation(new Animation(15461));
					unlock();
				} else if (count == 4) {
					setNextWorldTile(new WorldTile(locX, locY, plane));
					setNextAnimation(new Animation(15459));
				} else if (count == 5) {
					unlock();
					stop();
				}
				count++;
			}

		}, 1, 0);

	}

	public void pickNeemVine(final WorldObject object) {
		lock();
		WorldTasksManager.schedule(new WorldTask() {
			// 15457
			private int count;

			@Override
			public void run() {
				if (count++ == 1) {
					setNextFaceWorldTile(new WorldTile(object.getX(), object.getY(), object.getPlane()));
					if (!getInventory().containsItem(1935, 1)) {
						out("You need a jug to get neem oil", 0);
						return;
					}
					if (Utils.random(0) > 1) {
						out("You manage to get some need oil", 0);
					}
					getInventory().addItem(22444, 1);
					setNextAnimation(new Animation(15460));
					unlock();
					stop();
				}
				count++;
			}

		}, 1, 0);
		unlock();
	}

	public final void getStarter() {
		if (!getStarter) {
			Starter.appendStarter(this);
			getStarter = true;
			for (Player p : World.getPlayers()) {
				if (p == null) {
					continue;
				}
			}
		}
	}



	// Slayer
	public int slayerPoints;
	private SlayerTask task;
	public int slayertask;

	public void setSlayerPoints(int slayerPoints) {
		int extrap = 0;
		this.slayerPoints = slayerPoints;
	}

	public int getSlayerPoints() {
		return slayerPoints;
	}

	public void setTask(SlayerTask task) {
		this.task = task;
	}

	public SlayerTask getTask() {
		return task;
	}

	public boolean isTalkedWithKuradal() {
		return talkedWithKuradal;
	}

	public void setTalkedWithKuradal() {
		talkedWithKuradal = true;
	}

	public void falseWithKuradal() {
		talkedWithKuradal = false;
	}

	private boolean talkedWithKuradal;

	public int getGuestChatSetup() {
		return guestChatSetup;
	}

	public void setGuestChatSetup(int guestChatSetup) {
		this.guestChatSetup = guestChatSetup;
	}

	public ClansManager getGuestClanManager() {
		return guestClanManager;
	}

	public void setGuestClanManager(ClansManager guestClanManager) {
		this.guestClanManager = guestClanManager;
	}

	public String getClanName() {
		return clanName;
	}

	public void setClanName(String clanName) {
		this.clanName = clanName;
	}

	public boolean isConnectedClanChannel() {
		return connectedClanChannel;
	}

	public void setConnectedClanChannel(boolean connectedClanChannel) {
		this.connectedClanChannel = connectedClanChannel;
	}

	public void setTotalDonatedToWell(long amount) {
		totalDonated = amount;
	}

	public long getTotalDonatedToWell() {
		return totalDonated;
	}

	public void sm(String text) {
		getPackets().sendGameMessage(text, false);
	}

	public void checkMovement(int x, int y, int plane) {
		Magic.teleControlersCheck(this, new WorldTile(x, y, plane));
	}

	public static String FormatNumber1(long count2, int coins, Player player) {
		return new DecimalFormat("#,###,##0").format(player.getInventory().getItems().getNumberOf(coins)).toString();
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public void setDice(SafeDicing safeDicing) {
		dice = safeDicing;
	}

	public FarmingManager farmingManager;

	public FarmingManager getFarmingManager() {
		return farmingManager;
	}

	public void setHouseStyle(int i) {
		houseStyle = i;

	}

	private int houseStyle;

	public boolean blockPlayers;

	public int getHouseStyle() {
		return houseStyle;
	}

	public int getXInChunk() {
		return getX() & 0x7;
	}

	public int getYInChunk() {
		return getY() & 0x7;
	}

	/*
	 * Vorago
	 */

	private boolean spokenToVorago;

	public boolean hasSpokenToVorago() {
		return spokenToVorago;
	}

	public void setSpokenToVorago(boolean spokenToVorago) {
		this.spokenToVorago = spokenToVorago;
	}

	private boolean[] mauledWeeks;

	public void setHasMauledWeek(int rotation) {
		mauledWeeks[rotation] = true;
	}

	public long vokeDelay;

	public boolean hasmauledvorago;

	public boolean gamblehostnew;

	public boolean unlockeddungperkbook;

	public boolean isGambleHost() {
		return gamblehostnew || rights >= 2;
	}

	private Toolbelt toolbeltNew;

	public Toolbelt getToolbeltNew() {
		return toolbeltNew;
	}

	private GearPresets gearPresets;

	public GearPresets getGearPresets() {
		return gearPresets;
	}

	public SquealOfFortune getSquealOfFortune() {
		return squealOfFortune;
	}

	public void setSquealOfFortune(SquealOfFortune squealOfFortune) {
		this.squealOfFortune = squealOfFortune;
	}

	/**
	 * DropLog
	 */
	public int bandosChest = 0;
	public int bandosTassets = 0;
	public int bandosHilt = 0;
	public int bandosShield = 0;
	public int bandosHelm = 0;
	public int bandosGloves;
	public int bandosBoots;
	public int armadylHelm = 0;
	public int armadylPlate = 0;
	public int armadylLegs = 0;
	public int armadylBoots = 0;
	public int armadylShield = 0;
	public int armadylHilt = 0;
	public int armadylGloves;
	public int saradominHilt = 0;
	public int saradominWhisp;
	public int saradominHiss;
	public int saradominMur;
	public int armadylCrossbow;
	public int oharmadylCrossbow;
	public int saradominSword;
	public int armadylCross = 0;
	public int zamorakHilt = 0;
	public int zamorakSpear = 0;
	public int subtop;
	public int sublegs;
	public int subboots;
	public int subgloves;
	public int subshield;
	public int subhelm;
	public int torvaHelm;
	public int torvaPlate;
	public int torvaLegs;
	public int torvaBoots;
	public int torvaGloves;
	public int pernixCowl;
	public int pernixBody;
	public int pernixChaps;
	public int pernixGloves;
	public int pernixBoots;
	public int virtusMask;
	public int virtusTop;
	public int virtusLegs;
	public int virtusBoots;
	public int virtusGloves;
	public int virtusWand;
	public int virtusBook;
	public int ancientEmblem;
	public int zaryteBow;
	public int wandofPraesul;
	public int imperiumCore;
	public int bloodChest;
	public int iceChest;
	public int shadowChest;
	public int smokeChest;

	public int bones;

	// drop log corp
	public int divineSs;
	public int specSs;
	public int elySs;
	public int arcaneSs;
	public int succubusSs;
	public int invocationSs;
	// drop log glacor
	public int steadBoots;
	public int glaivenBoots;
	public int rageBoots;
	// droplog TDS & dryax - dryax also drops visage,
	public int dragonClaws;
	public int dragonPlate;
	public int holyElixer;
	public int spiritHood;
	public int spiritBottom;
	public int spiritTop;
	// droplog wildywyrm
	public int wildHammer;
	public int wildTop;
	public int wildLegs;
	public int wildHelm;
	// hmwildywyrm
	public int dragonCrossbow;
	public int ohdragonCrossbow;
	// aquaticwyrm
	public int cannonbase;
	public int cannonstand;
	public int cannonbarrels;
	public int cannonfurnace;
	// vorago
	public int tectonicEnergy;
	public int seismicWand;
	public int seismicSing;
	// ava
	public int brutalWhip;
	public int babyBaslisk;
	public int abyssalMinion;
	// badsanta
	public int greenSanta;
	public int snowballPet;
	// KalphiteKing
	public int drygorelong;
	public int drygorerapier;
	public int drygoremace;
	public int ohdrygorelong;
	public int ohdrygorerapier;
	public int ohdrygoremace;
	// ascdung
	public int signet1;
	public int signet2;
	public int signet3;
	public int signet4;
	public int signet5;
	public int signet6;
	// drygon
	public int angerSpear;
	public int angerSword;
	public int angerMace;
	public int drygonicCape;
	// demingods
	public int annihilation;
	public int obliteration;
	public int decimation;
	// necrolord
	public int deathtouchDart;
	// sunfreet
	public int cinderbaneGloves;
	// anivia
	public int amuletOrison;
	public int amuletProtection;
	public int amuletBrutality;
	// arraxor
	public int spiderlegtop;
	public int spiderlegmiddle;
	public int spiderlegbottom;
	public int spiderFang;
	public int spiderEye;
	public int spiderWeb;
	// raptor
	public int raptorComponent1;
	public int raptorComponent2;
	public int raptorComponent3;
	public int zarosComponent;
	public int serenComponent;
	public int sliskeComponent;
	// gwd2
	public int domantHelm;
	public int domantBody;
	public int domantLegs;
	public int zarosCrest;
	public int serenCrest;
	public int sliskeCrest;
	public int zamorakCrest;
	public int dragonriderLance;
	public int twinblade;
	public int ohtwinblade;
	public int shadowGlaive;
	public int ohshadowGlaive;
	public int wandCywir;
	public int orbCywir;
	// rot6
	public int meleekite;
	public int magekite;
	public int rangekite;
	public int malvenergy;
	// qbd & kbd drop log
	public int dVisage;
	public int dbonehelm;
	public int dboneplatebody;
	public int dboneplatelegs;
	public int dbonemeleegloves;
	public int dbonemeleeboots;
	public int dbonehat;
	public int dbonetop;
	public int dbonerobelegs;
	public int dbonemagegloves;
	public int dbonemageboots;
	public int royalspring;
	public int royalsight;
	public int royalframe;
	public int royalstabiliser;
	// end of boss droplog

	// gano
	public int ganoPonch;
	public int ganoLegs;
	public int polyStaff;

	public int vineWhip;
	public int aFury;
	public int frostBones;
	public int aWhip;

	public int dharoksHelm;
	public int dharoksPlate;
	public int dharoksLegs;
	public int dharoksGreataxe;

	public int bringI;
	public int aringI;
	public int sringI;

	public int vlS;
	public int vestacB;
	public int vestapS;
	public int vestaSpear;

	public int bosspetperk;

	public int barrowchestloot;

	public long checkTimer, checkTimerAmt = 15000;

	public int bosstelecomponent = 0;
	public int slayertelecomponent = 0;
	public int dailymoneymakercomponent = 0;
	public int researchtablecomponent = 0;
	public int perkcomponent = 0;
	public int petclaimcomponent = 0;
	public int petclaimpage = 0;
	public boolean skilltelingcomponent = false;
	public boolean masterslayertaskcomponent = false;

	/**
	 * Daily Money Makers
	 */

	public int dailyhillgiants;
	public int dailybarrowschest;
	public int dailyslayertask;
	public int dailyfarming;
	public int dailypotions;
	public int dailycluescrolls;
	public int dailyxericraids;
	public int dailywildytasks;
	public int dailynoobzamorak;
	public int dailytheatreofblood;
	public int dailyrunecoins;
	
	/**
	 * Catacombs
	 */
	
	public boolean dailydwarfcatacombfee;
	
	/**
	 * *
	 */

	public boolean dailychins;

	public int challengeid;
	public int wildernesstaskcompleted;
	public int challengeamount;
	public boolean dailyrerollchallenge;
	public int wildtaskrerollchallenge;

	/**
	 * Dung Tutorial variables
	 */
	public boolean lowdungtut;
	public boolean meddungtut;
	public boolean highdungtut;

	public int treasurechestshunted;

	public boolean admin100droprate;

	public int ragingcasketcount;

	public boolean startedxmas2018;
	public boolean completedxmas2018;

	public boolean insanefbtitle;

	public boolean eventhealperk;
	

	public boolean soulsplitperk;


	public String staffviewingorb;

	public int fallyshieldprayertime;

	public boolean bankis2;
	public boolean purchasedbank2;

	public String petname;

	public int homehealer;

	public int instancedelay;

	public int assassineventdamage;

	public int dailysheafglory;

	public boolean instanceclosed;

	public int runecoinervalue;

	public boolean bonecrusherI;

	public int dyeRecover;

	public boolean eggsterminatorc;
	public boolean pinkboxingglovesc;
	public boolean icecreamhatoverridec;
	public boolean angrybunnytitle;

	public boolean safealchemy;
	
	/**
	 * Rues Altar quest
	 */

	public boolean completedruesaltar;
	public int ruesaltarprogress;
	public int ruesessenceprogress;

	public boolean rodeffectincrease;

	public boolean raptorsgiftdroprate;

	public boolean haspetperkextra;

	public int coalstorage;
	public int coalstoragecap;

	public int turmoildirect;
	public boolean turmoilunlocked;
	
	public boolean meleeturmoilupgrade;
	public boolean rangeturmoilupgrade;
	public boolean mageturmoilupgrade;
	public boolean unlockedturmoilupgradeonce;
/**
 * Daily challenges
 */
	public boolean dc1sttime;
	public int challengepoints;
	public int totalchallengescompleted;

	public boolean gwdkcdouble;

	public boolean huntertrapboost;

	public boolean savespellrunes;

	public boolean freeslayerportal;

	
	



	public void DailymasterChins() {
		if (!getInventory().hasFreeSlots()) {
			sendMessage("Please make space first.");
			return;
		}
		dailychins = true;
		if (isEasy()) {
			getInventory().addItem(29448, 50);
		}
		if (isAverage()) {
			getInventory().addItem(29448, 100);
		}
		if (isHard()) {
			getInventory().addItem(29448, 200);
		}
		if (isHeroic()) {
			getInventory().addItem(29448, 400);
		// return ;
		}
		if (isRealism()) {
			getInventory().addItem(29448, 600);
		}
	}

	public boolean hasCleansingActivated() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Containers
	 */

	public RunePouch getRunePouch() {
		return runePouch;
	}

	public RunePouchUpgraded getRunePouchUpgraded() {
		return runePouchUpgraded;
	}
	
	public HerbSack getHerbSack() {
		return herbSack;
	}

	public OreBag getOreBag() {
		return oreBag;
	}

	public LootingBag getLootingBag() {
		return lootingBag;
	}

	public Grave getGrave() {
		return grave;
	}
	
	public SpareContainer getSpareContainer() {
		return sparecontainer;
	}

	public DMMContainer getDmm() {
		return dmmcontainer;
	}
	
	public perkContainer getPerk() {
		return perkcontainer;
	}
	public BoxInterface1 getBoxI1() {
		return boxinterface1;
	}
	
	public BoxInterface2 getBoxI2() {
		return boxinterface2;
	}
	
	public BoxInterface3 getBoxI3() {
		return boxinterface3;
	}

	public DropLogContainer getDropLogContainer() {
		return droplogcontainer;
	}

	/**
	 * Drop log containers
	 */
	public AmigoDropLog getAmigoDropLog() {
		return amigodroplog;
	}
	
	public CorpDropLog getCorpDropLog() {
		return corpdroplog;
	}

	public KBDDropLog KBDgetDropLog() {
		return kbddroplog;
	}

	public TdsDropLog TdsgetDropLog() {
		return tdsdroplog;
	}

	public NexDropLog NexgetDropLog() {
		return nexdroplog;
	}

	public GlacorDropLog GlacorgetDropLog() {
		return glacordroplog;
	}

	public Rot6DropLog Rot6getDropLog() {
		return rot6droplog;
	}

	public BandosDropLog BandosgetDropLog() {
		return bandosdroplog;
	}
	
	public HydraDropLog HydragetDropLog() {
		return hydradroplog;
	}
	public CelestiaDropLog CelestiagetDropLog() {
		return celestiadroplog;
	}
	public RaptorDropLog RaptorgetDropLog() {
		return raptordroplog;
	}
	public VorkathDropLog VorkathgetDropLog() {
		return vorkathdroplog;
	}

	public ZamorakDropLog ZamorakgetDropLog() {
		return zamorakdroplog;
	}

	public ArmadylDropLog ArmadylgetDropLog() {
		return armadyldroplog;
	}

	public SaradominDropLog SaradomingetDropLog() {
		return saradomindroplog;
	}

	public WildyWyrmDropLog WildyWyrmgetDropLog() {
		return wildywyrmdroplog;
	}

	public HmWildyWyrmDropLog HmWildyWyrmgetDropLog() {
		return hmwildywyrmdroplog;
	}

	public AquaticWyrmDropLog AquaticWyrmgetDropLog() {
		return aquaticwyrmdroplog;
	}

	public VoragoDropLog VoragogetDropLog() {
		return voragodroplog;
	}

	public AodDropLog AodgetDropLog() {
		return aoddroplog;
	}

	public GazerDropLog GazergetDropLog() {
		return gazerdroplog;
	}

	public SantaDropLog SantagetDropLog() {
		return santadroplog;
	}

	public DryaxDropLog DryaxgetDropLog() {
		return dryaxdroplog;
	}

	public KalphiteKingDropLog KalphiteKinggetDropLog() {
		return kalphitekingdroplog;
	}

	public HopeDropLog HopegetDropLog() {
		return hopedroplog;
	}

	public PDDropLog PDgetDropLog() {
		return pddroplog;
	}

	public ZulrahDropLog ZulrahgetDropLog() {
		return zulrahdroplog;
	}

	public NecrolordDropLog NecrolordgetDropLog() {
		return necrolorddroplog;
	}

	public ThunderousDropLog ThunderousgetDropLog() {
		return thunderousdroplog;
	}

	public SunfreetDropLog SunfreetgetDropLog() {
		return sunfreetdroplog;
	}

	public AniviaDropLog AniviagetDropLog() {
		return aniviadroplog;
	}

	public SliskeDropLog SliskegetDropLog() {
		return sliskedroplog;
	}

	public KrakenDropLog KrakengetDropLog() {
		return krakendroplog;
	}

	public SireDropLog SiregetDropLog() {
		return siredroplog;
	}

	public CerberusDropLog CerberusgetDropLog() {
		return cerberusdroplog;
	}

	public SirenicDropLog SirenicgetDropLog() {
		return sirenicdroplog;
	}

	public CallistoDropLog CallistogetDropLog() {
		return callistodroplog;
	}

	public VenenatisDropLog VenenatisgetDropLog() {
		return venenatisdroplog;
	}

	public ChaosFanaticDropLog ChaosFanaticgetDropLog() {
		return chaosfanaticdroplog;
	}

	public ArchaeologistDropLog ArchaeologistgetDropLog() {
		return archaeologistdroplog;
	}

	public ScorpiaDropLog ScorpiagetDropLog() {
		return scorpiadroplog;
	}

	public VetionDropLog VetiongetDropLog() {
		return vetiondroplog;
	}

	public XericDropLog XericgetDropLog() {
		return xericdroplog;
	}

	public BloodDropLog BloodgetDropLog() {
		return blooddroplog;
	}

	public TolDropLog TolgetDropLog() {
		return toldroplog;
	}

	public LizardmanDropLog LizardmangetDropLog() {
		return lizardmandroplog;
	}

	public SmokeDevilDropLog SmokeDevilgetDropLog() {
		return smokedevildroplog;
	}

	public BlinkDropLog BlinkgetDropLog() {
		return blinkdroplog;
	}
	/**
	 * End of drop log containers
	 */

	public PetPerk getPetPerk() {
		return petPerk;
	}

	public void SlayerGemTeleport() {
		if (getTask() == null) {
			return;
		}
		if (getTask().getName().equalsIgnoreCase("hellhound")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1645, 10065, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Crawling Hand")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(3420, 3545, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Ankou")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1640, 9993, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Sand crab")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1706, 9995, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Chaos dwarf")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(2924, 9764, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Moss giant")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1687, 10047, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Wyrm")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1271, 10187, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Hydra")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1304, 10236, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Drake")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1313, 10243, 1));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Rock crab")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(2710, 3723, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Ghost")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(2914, 9850, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Abyssal demon")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1674, 10086, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Iron dragon") || getTask().getName().equalsIgnoreCase("Bronze dragon")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1670, 10092, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Black dragon") || getTask().getName().equalsIgnoreCase("Blue dragon") || getTask().getName().equalsIgnoreCase("Red dragon")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1613, 10084, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Steel dragon")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(2694, 9414, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Mithril dragon")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1778, 5340, 1));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Nechryael")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1694, 10083, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Dust devil")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1712, 10016, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Infernal mage")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(3437, 3555, 1));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Fire giant")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(3229, 5497, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Aberrant spectre")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(3423, 3544, 1));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Mutated jadinko baby")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(3013, 9273, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Ice strykewyrm")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(2378, 3891, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Jungle strykewyrm")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(2461, 2898, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Desert strykewyrm")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(3376, 3158, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Black Demon")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1724, 10094, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Cow")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(3259, 3274, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Gargoyle")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(3435, 3545, 2));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Jelly")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1717, 10049, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Runite dragon") || getTask().getName().equalsIgnoreCase("Adamantite dragon")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(2128, 3857, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Bat")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(3350, 3489, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Hill giant")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(3109, 9835, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Dark beast")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1625, 10059, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Edimmu")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1747, 5342, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Bloodveld")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1690, 10015, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Ganodermic beast")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(4654, 5485, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Smoke devil")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(3662, 5770, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Frost dragon")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1298, 4510, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Magic axe")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1649, 10012, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Giant bat")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(2911, 9832, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Skeleton")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1641, 10047, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Farmer")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(2706, 3717, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Waterfiend")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1739, 5344, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Lesser demon")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1719, 10067, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Greater demon")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1713, 10100, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Cave crawler")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(2792, 9998, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Rockslug")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(2798, 10018, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Pyrefiend")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(2761, 10005, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Basilisk")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(2741, 10010, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Turoth")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(2724, 10007, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Banshee")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(3439, 3560, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Kurask")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(2698, 9999, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Dagannoth")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1667, 9998, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Aquanite")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(2720, 9970, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Cave horror")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(3795, 9444, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Lizardman shaman")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1490, 3698, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Cockatrice")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(2801, 10032, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Sulphur lizard")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(1332, 10188, 1));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Spitting wyvern")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(3608, 10265, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Long-tailed wyvern")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(3597, 10257, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Green dragon")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(2982, 3599, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Ancient wyvern")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(3624, 10247, 0));
			return;
		} else if (getTask().getName().equalsIgnoreCase("Glacor")) {
			Magic.sendAncientTeleportSpell(this, 0, 0, new WorldTile(4183, 5726, 0));
			return;
		} else {
			sendMessage("<col=ff0000>This monster teleport isn't supported.");
			return;
		}
	}


}
