package com.rs.game.player.controlers;

import java.util.HashMap;

import com.rs.game.minigames.BrimhavenAgility;
import com.rs.game.minigames.PuroPuro;
import com.rs.game.minigames.RefugeOfFear;
import com.rs.game.minigames.clanwars.FfaZone;
import com.rs.game.minigames.clanwars.RequestController;
import com.rs.game.minigames.clanwars.WarControler;
import com.rs.game.minigames.creations.StealingCreationGame;
import com.rs.game.minigames.creations.StealingCreationLobby;
import com.rs.game.minigames.duel.DuelArena;
import com.rs.game.minigames.duel.DuelControler;
import com.rs.game.minigames.gungame.GunGameBoss;
import com.rs.game.minigames.rfd.RecipeforDisaster;
import com.rs.game.player.actions.dungeoneering.DungeoneeringControler;
import com.rs.game.player.content.bossinstance.InstancedBossController;
import com.rs.game.player.content.raids.gulega.GulegaPlaying;
import com.rs.game.player.content.raids.gulega.GulegaWaiting;
import com.rs.game.player.content.raids.gulega.GulegaWaitingC;
import com.rs.game.player.content.raids.trioboss.TrioPlaying;
import com.rs.game.player.content.raids.trioboss.TrioWaitingC;
import com.rs.game.player.controlers.calamity.CalamityPlaying;
import com.rs.game.player.controlers.calamity.CalamityWaiting;
import com.rs.game.player.controlers.castlewars.CastleWarsPlaying;
import com.rs.game.player.controlers.castlewars.CastleWarsWaiting;
import com.rs.game.player.controlers.events.DeathEvent;
import com.rs.game.player.controlers.fightpits.FightPitsArena;
import com.rs.game.player.controlers.fightpits.FightPitsLobby;
import com.rs.game.player.controlers.heist.HeistPlaying;
import com.rs.game.player.controlers.heist.HeistWaiting;
import com.rs.game.player.controlers.pestcontrol.PestControlGame;
import com.rs.game.player.controlers.pestcontrol.PestControlLobby;
import com.rs.utils.Logger;

public class ControlerHandler {

	private static final HashMap<Object, Class<Controler>> handledControlers = new HashMap<Object, Class<Controler>>();

	@SuppressWarnings("unchecked")
	public static final void init() {
		try {
			Class<Controler> value1 = (Class<Controler>) Class.forName(Wilderness.class.getCanonicalName());
			handledControlers.put("Wilderness", value1);
			Class<Controler> value2 = (Class<Controler>) Class.forName(Kalaboss.class.getCanonicalName());
			handledControlers.put("Kalaboss", value2);
			Class<Controler> value4 = (Class<Controler>) Class.forName(GodWars.class.getCanonicalName());
			handledControlers.put("GodWars", value4);
			Class<Controler> value5 = (Class<Controler>) Class.forName(ZGDControler.class.getCanonicalName());
			handledControlers.put("ZGDControler", value5);
			Class<Controler> value6 = (Class<Controler>) Class.forName(TutorialIsland.class.getCanonicalName());
			handledControlers.put("TutorialIsland", value6);
			Class<Controler> value7 = (Class<Controler>) Class.forName(StartTutorial.class.getCanonicalName());
			handledControlers.put("StartTutorial", value7);
			Class<Controler> value9 = (Class<Controler>) Class.forName(DuelArena.class.getCanonicalName());
			handledControlers.put("DuelArena", value9);
			Class<Controler> value10 = (Class<Controler>) Class.forName(DuelControler.class.getCanonicalName());
			handledControlers.put("DuelControler", value10);
			Class<Controler> value11 = (Class<Controler>) Class.forName(CorpBeastControler.class.getCanonicalName());
			handledControlers.put("CorpBeastControler", value11);
			Class<Controler> value14 = (Class<Controler>) Class.forName(DTControler.class.getCanonicalName());
			handledControlers.put("DTControler", value14);
			Class<Controler> value15 = (Class<Controler>) Class.forName(JailControler.class.getCanonicalName());
			handledControlers.put("JailControler", value15);
			Class<Controler> value17 = (Class<Controler>) Class.forName(CastleWarsPlaying.class.getCanonicalName());
			handledControlers.put("CastleWarsPlaying", value17);
			Class<Controler> value18 = (Class<Controler>) Class.forName(CastleWarsWaiting.class.getCanonicalName());
			handledControlers.put("CastleWarsWaiting", value18);
			Class<Controler> value99 = (Class<Controler>) Class.forName(DungeonControler.class.getCanonicalName());
			handledControlers.put("DungeonControler", value99);
			// Class<Controler> value23 = (Class<Controler>) Class
			// .forName(HouseCon.class.getCanonicalName());
			// handledControlers.put("HouseCon", value23);
			Class<Controler> value20 = (Class<Controler>) Class.forName(NewHomeControler.class.getCanonicalName());
			handledControlers.put("NewHomeControler", value20);
			Class<Controler> value21 = (Class<Controler>) Class.forName(WGuildControler.class.getCanonicalName());
			handledControlers.put("WGuildControler", value21);
			Class<Controler> value22 = (Class<Controler>) Class.forName(Dryaxions.class.getCanonicalName());
			handledControlers.put("Dryaxions", value22);
			Class<Controler> value23 = (Class<Controler>) Class.forName(Deathgame.class.getCanonicalName());
			handledControlers.put("Deathgame", value23);
			Class<Controler> value25 = (Class<Controler>) Class
					.forName(DungeoneeringControler.class.getCanonicalName());
			handledControlers.put("DungeoneeringControler", value25);

			handledControlers.put(InstancedBossController.IDENTIFIER,
					(Class<Controler>) Class.forName(InstancedBossController.class.getCanonicalName()));
			handledControlers.put("HeistPlaying",
					(Class<Controler>) Class.forName(HeistPlaying.class.getCanonicalName()));
			handledControlers.put("HeistWaiting",
					(Class<Controler>) Class.forName(HeistWaiting.class.getCanonicalName()));
			handledControlers.put("CalamityPlaying",
					(Class<Controler>) Class.forName(CalamityPlaying.class.getCanonicalName()));
			handledControlers.put("CalamityWaiting",
					(Class<Controler>) Class.forName(CalamityWaiting.class.getCanonicalName()));
			handledControlers.put("GunGameBoss",
					(Class<Controler>) Class.forName(GunGameBoss.class.getCanonicalName()));
			handledControlers.put("TrioPlaying",
					(Class<Controler>) Class.forName(TrioPlaying.class.getCanonicalName()));
			handledControlers.put("TrioWaitingC",
					(Class<Controler>) Class.forName(TrioWaitingC.class.getCanonicalName()));

			handledControlers.put("TrialOfTheGods",
					(Class<Controler>) Class.forName(TrialOfTheGods.class.getCanonicalName()));
			handledControlers.put("PuroPuro", (Class<Controler>) Class.forName(PuroPuro.class.getCanonicalName()));
			// handledControlers.put("SlayerWars", (Class<Controler>)
			// Class.forName(SlayerWars.class.getCanonicalName()));
			handledControlers.put("RecipeforDisaster",
					(Class<Controler>) Class.forName(RecipeforDisaster.class.getCanonicalName()));
			handledControlers.put("clan_wars_request",
					(Class<Controler>) Class.forName(RequestController.class.getCanonicalName()));
			handledControlers.put("clan_war", (Class<Controler>) Class.forName(WarControler.class.getCanonicalName()));
			handledControlers.put("clan_wars_ffa", (Class<Controler>) Class.forName(FfaZone.class.getCanonicalName()));
			handledControlers.put("NomadsRequiem",
					(Class<Controler>) Class.forName(NomadsRequiem.class.getCanonicalName()));
			handledControlers.put("BorkControler",
					(Class<Controler>) Class.forName(BorkControler.class.getCanonicalName()));
			handledControlers.put("BrimhavenAgility",
					(Class<Controler>) Class.forName(BrimhavenAgility.class.getCanonicalName()));
			handledControlers.put("FightCavesControler",
					(Class<Controler>) Class.forName(FightCaves.class.getCanonicalName()));
			handledControlers.put("SlayerSurvivalControler", (Class<Controler>) Class.forName(SlayerSurvival.class.getCanonicalName()));
			handledControlers.put("PWControler",
					(Class<Controler>) Class.forName(PlayerWars.class.getCanonicalName()));
			handledControlers.put("FightKilnControler",
					(Class<Controler>) Class.forName(FightKiln.class.getCanonicalName()));
			handledControlers.put("FightPitsLobby",
					(Class<Controler>) Class.forName(FightPitsLobby.class.getCanonicalName()));
			handledControlers.put("FightPitsArena",
					(Class<Controler>) Class.forName(FightPitsArena.class.getCanonicalName()));
			handledControlers.put("PestControlGame",
					(Class<Controler>) Class.forName(PestControlGame.class.getCanonicalName()));
			handledControlers.put("PestControlLobby",
					(Class<Controler>) Class.forName(PestControlLobby.class.getCanonicalName()));
			handledControlers.put("Barrows", (Class<Controler>) Class.forName(Barrows.class.getCanonicalName()));
			handledControlers.put("PestControlGame",
					(Class<Controler>) Class.forName(PestControlGame.class.getCanonicalName()));
			handledControlers.put("PestControlLobby",
					(Class<Controler>) Class.forName(PestControlLobby.class.getCanonicalName()));
			handledControlers.put("RefugeOfFear",
					(Class<Controler>) Class.forName(RefugeOfFear.class.getCanonicalName()));
			handledControlers.put("Falconry", (Class<Controler>) Class.forName(Falconry.class.getCanonicalName()));
			handledControlers.put("QueenBlackDragonControler",
					(Class<Controler>) Class.forName(QueenBlackDragonController.class.getCanonicalName()));
			handledControlers.put("HouseControler",
					(Class<Controler>) Class.forName(HouseControler.class.getCanonicalName()));
			handledControlers.put("RuneSpanControler",
					(Class<Controler>) Class.forName(RunespanControler.class.getCanonicalName()));
			handledControlers.put("DeathEvent", (Class<Controler>) Class.forName(DeathEvent.class.getCanonicalName()));
			handledControlers.put("SorceressGarden",
					(Class<Controler>) Class.forName(SorceressGarden.class.getCanonicalName()));
			handledControlers.put("CrucibleControler",
					(Class<Controler>) Class.forName(CrucibleControler.class.getCanonicalName()));
			handledControlers.put("StealingCreationsGame",
					(Class<Controler>) Class.forName(StealingCreationGame.class.getCanonicalName()));
			handledControlers.put("StealingCreationsLobby",
					(Class<Controler>) Class.forName(StealingCreationLobby.class.getCanonicalName()));
			handledControlers.put("GulegaPlaying",
					(Class<Controler>) Class.forName(GulegaPlaying.class.getCanonicalName()));
			handledControlers.put("GulegaWaiting",
					(Class<Controler>) Class.forName(GulegaWaiting.class.getCanonicalName()));
			handledControlers.put("GulegaWaitingC",
					(Class<Controler>) Class.forName(GulegaWaitingC.class.getCanonicalName()));
			handledControlers.put("SlayerSurvival", (Class<Controler>) Class.forName(SlayerSurvival.class.getCanonicalName()));
		
		} catch (Throwable e) {
			Logger.handle(e);
		}
	}

	public static final void reload() {
		handledControlers.clear();
		init();
	}

	public static final Controler getControler(Object key) {
		if (key instanceof Controler)
			return (Controler) key;
		Class<Controler> classC = handledControlers.get(key);
		if (classC == null)
			return null;
		try {
			return classC.newInstance();
		} catch (Throwable e) {
			Logger.handle(e);
		}
		return null;
	}
}
