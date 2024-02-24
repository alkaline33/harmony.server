package com.rs.game.player.content;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

public class BossSpotlight {
	
	public static final String[] BOSSES = { "General Graardor", "Kree'arra", "Commander Zilyana", "K'ril Tsutsaroth", "King Black Dragon", "Bork"
	,"Corporeal Beast","Nex","Glacor","WildyWyrm","Aquatic Wyrm","Vorago","Avatar of Destruction","Night-Gazer","Bad Santa","Dryax","Kalphite King","Hope Devourer","Necrolord","Sunfreet"
			, "Anivia", "Crazy archaeologist", "Vorkath", "Cerberus", "Scorpia", "Callisto", "Venenatis", "Chaos fanatic", "Kalphite Queen", "Sirenic the Spider", "Yk'Lagor the Thunderous", "Sliske", "Kraken", "Zulrah", "Abyssal Sire", "Thermonuclear smoke devil", "Vet'ion"
	};

			
	/**
	 * 
	 * @Author Connor
	 */

	public static void GrabBoss() {
		Settings.BOSS_SPOTLIGHT = BOSSES[Utils.random(BOSSES.length)];
		World.sendWorldMessage(Colors.orange+"<img=7>Boss Spotlight: Kill "+Settings.BOSS_SPOTLIGHT+" for improved drop rate!", false);
		return;
		}
	}
