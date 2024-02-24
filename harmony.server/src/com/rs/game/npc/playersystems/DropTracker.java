package com.rs.game.npc.playersystems;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.discord.Discord;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cores.CoresManager;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.Drop;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

import static com.rs.game.npc.playersystems.KillCounter.p;

public class DropTracker {

	private static final int[][] RAREDROP = {{4151, p.CorpKills}, {6571, 2}, {989, 2}};

	static List<String> rareDrops = Arrays.asList("arcane", "bandos", "subjugation", "divine", "spectral", "elysian", "visage", "stead"
			, "glaiven", "ragefire", "dragon claw", "santa hat", "dragonbone", "signet", "vengeful", "ancient upgrade", "merciless", "malevolent"
			, "noxious", "seismic", "drygore", "vorago", "garg", "feast", "blood necklace", "torso", "Saradomin's", "zamorakian", "whip"
			, "kraken", "dragon pickaxe", "berserker ring", "archers' ring", "seers' ring", "warrior ring", "trident", "fang");

	public static void TrackNews(Player player, Drop drop, Item item) {
		String dropName = ItemDefinitions.getItemDefinitions(drop.getItemId()).getName().toLowerCase();
		int itemValue = ItemDefinitions.getItemDefinitions(drop.getItemId()).getValue();
		ItemDefinitions dropID = ItemDefinitions.getItemDefinitions(drop.getItemId());

		if (dropName.contains(""+rareDrops)
				|| dropName.contains("armadyl") && !dropName.contains("rune") && !dropName.contains("shards")
				|| dropName.contains("ascension") && !dropName.contains("bolt") && !dropName.contains("keystone") || item.getDefinitions().getValue() >= 4000000) {
			World.sendWorldMessage("<img=7><col=ff8c38>News: " + Utils.formatPlayerNameForDisplay(player.getUsername()) + " has received " + handleGrammar(drop) + " " + dropName + " drop! KC: "+handleKc(player, drop), false);
			Discord.sendSimpleMessage("NEWS: " + player.getDisplayName() + " has received a " + ItemDefinitions.getItemDefinitions(drop.getItemId()).getName() + " rare drop!");
			player.raredropobtained++;
		}
	}

	private static int handleKc(Player player, Drop drop) {
		String dropName = ItemDefinitions.getItemDefinitions(drop.getItemId()).getName().toLowerCase();
		if (dropName.contains("whip")) {
			return player.CorpKills;
		} else {
			return -1;
		}
	}
	private static String handleGrammar(Drop drop) {
		if (ItemDefinitions.getItemDefinitions(drop.getItemId()).getName().toLowerCase().startsWith("a")
				|| ItemDefinitions.getItemDefinitions(drop.getItemId()).getName().toLowerCase().startsWith("e")
				|| ItemDefinitions.getItemDefinitions(drop.getItemId()).getName().toLowerCase().startsWith("i")
				|| ItemDefinitions.getItemDefinitions(drop.getItemId()).getName().toLowerCase().startsWith("o")
				|| ItemDefinitions.getItemDefinitions(drop.getItemId()).getName().toLowerCase().startsWith("u")) {
			return "an";
		}
		return "a";
	}

	public static void TrackTitle(Player player, Drop drop) {
		String dropName = ItemDefinitions.getItemDefinitions(drop.getItemId()).getName().toLowerCase();
		if (dropName.contains("divine") || (dropName.contains("elysian") || (dropName.contains("arcane")
				|| (dropName.contains("spectral") || (dropName.contains("succubus") && player.divine == false))))) {
			// player.getInterfaceManager().doAchievementInterface("Unlock sigil title");
			player.sendMessage("<col=33CC66>You have unlocked the title Sigil. ;;sigiltitle");
			player.divine = true;
			return;
		}
	}

	
	public static void TrackLootBeam(Player player,int x, int y, int z, int BeamId) {
		if (BeamId == 3) {
			/**
			 * Birds
			 */
			player.getPackets().sendGraphics(new Graphics(2794), new WorldTile (x, y, z));
			player.lootbeamgains++;
			WorldTasksManager.schedule(new WorldTask() {
				int loop = 0;
				@Override
				public void run() {

					if (loop == 1) {
						player.getPackets().sendGraphics(new Graphics(2794), new WorldTile (x, y, z));
					}
					if (loop == 3) {
						player.getPackets().sendGraphics(new Graphics(2794), new WorldTile(x, y, z));
					}
					if (loop == 5) {
						player.getPackets().sendGraphics(new Graphics(2794), new WorldTile (x, y, z));
					}
					if (loop == 7) {
						stop();
					}
					loop++;
				}

			}, 0, 1);

			player.sm(Colors.orange + "A spiral of golden birds of wealth came from one of your items.");
		}
		if (BeamId == 2) {
			/**
			 * Snow Cloud
			 */
			player.getPackets().sendGraphics(new Graphics(364), new WorldTile (x, y, z));
			player.lootbeamgains++;
			WorldTasksManager.schedule(new WorldTask() {
				int loop = 0;
				@Override
				public void run() {

					if (loop == 1) {
						player.getPackets().sendGraphics(new Graphics(364), new WorldTile (x, y, z));
					}
					if (loop == 5) {
						player.getPackets().sendGraphics(new Graphics(364), new WorldTile (x, y, z));
					}
					if (loop == 10) {
						stop();
					}
					loop++;
				}

			}, 0, 1);

			player.sm(Colors.orange + "<shad=000000>A snow cloud of wealth came from one of your items.");
		}
		if (BeamId == 1) {
			/**
			 * Rainbow
			 */
			player.getPackets().sendGraphics(new Graphics(6), new WorldTile(x, y, z));
			player.lootbeamgains++;
			WorldTasksManager.schedule(new WorldTask() {
				int loop = 0;
				@Override
				public void run() {

					if (loop >= 1) {
						player.getPackets().sendGraphics(new Graphics(6), new WorldTile(x, y, z));
					}
					if (loop == 10) {
						stop();
					}
					loop++;
				}

			}, 0, 1);

			player.sm(Colors.orange + "<shad=000000>A rainbow of wealth came from one of your items.");
		}
		if (BeamId == 0) {

			player.getPackets().sendGraphics(new Graphics(92), new WorldTile(x, y, z));
			player.sendMessage(Colors.orange + "<shad=000000>A cursed angel shines over one of your items.");
			CoresManager.slowExecutor.schedule(new Runnable() {

				@Override
				public void run() {
					player.getPackets().sendGraphics(new Graphics(-1),  new WorldTile (x, y, z));
				}
				
			}, 60, TimeUnit.SECONDS);
		}
	}


}
