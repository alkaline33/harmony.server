package com.rs.game.player.interfaces;

import java.util.Calendar;

import com.rs.Settings;
import com.rs.game.player.Player;
import com.rs.utils.Colors;

public class WorldEventInformationInterface {
	
	private static int INTER = 3021;
	

	
	@SuppressWarnings("deprecation")
	public static void SendInterface(Player player) {
		/**
		 * Sends initial interface
		 */
		player.getInterfaceManager().sendInterface(INTER);
		/**
		 * Sends Server Current Time
		 */
		player.getPackets().sendIComponentText(INTER, 21, "Server Time: "+Calendar.getInstance().getTime().getHours()+":"+Calendar.getInstance().getTime().getMinutes()+".");
		/**
		 * Sends Event Panel
		 */
		player.getPackets().sendIComponentText(INTER, 30, ""+Colors.cyan+"Party Demon");
		player.getPackets().sendIComponentText(INTER, 31, ""+Colors.cyan+"Blink");
		player.getPackets().sendIComponentText(INTER, 32, ""+Colors.cyan+"Assassin");
		player.getPackets().sendIComponentText(INTER, 33, ""+Colors.cyan+"Icy Bones");
		player.getPackets().sendIComponentText(INTER, 34, ""+Colors.cyan+"Boss Spotlight");
		player.getPackets().sendIComponentText(INTER, 35, ""+Colors.cyan+"Event Spotlight");
		player.getPackets().sendIComponentText(INTER, 36, ""+Colors.cyan+"Season Event");
		player.getPackets().sendIComponentText(INTER, 37, ""+Colors.cyan+"Happy Hour");
		player.getPackets().sendIComponentText(INTER, 38, ""+Colors.cyan+"Double Drops");
		player.getPackets().sendIComponentText(INTER, 39, ""+Colors.cyan+"Double Experience");
		player.getPackets().sendIComponentText(INTER, 40, ""+Colors.cyan+"Treasure Hunt");
		player.getPackets().sendIComponentText(INTER, 41, ""+Colors.cyan+"Double Damage");
		player.getPackets().sendIComponentText(INTER, 42, ""+Colors.cyan+"Mobs Drop Event Tokens");
		player.getPackets().sendIComponentText(INTER, 43, ""+Colors.cyan+"10% Drop Rate Increase");
		player.getPackets().sendIComponentText(INTER, 44, ""+Colors.cyan+"OSRS Raids X2 Rewards");
		player.getPackets().sendIComponentText(INTER, 45, ""+Colors.cyan+"No Damage Taken In Pvm");
		player.getPackets().sendIComponentText(INTER, 46, ""+Colors.cyan+"X2 Skilling Resources");
		player.getPackets().sendIComponentText(INTER, 47, ""+Colors.cyan+"Increase Pet Chance");
		player.getPackets().sendIComponentText(INTER, 48, ""+Colors.cyan+"");
		player.getPackets().sendIComponentText(INTER, 49, ""+Colors.cyan+"");
		player.getPackets().sendIComponentText(INTER, 50, ""+Colors.cyan+"");
		
		/**
		 * Sends Description Panel
		 */
		player.getPackets().sendIComponentText(INTER, 51, ""+Colors.cyan+"A Group Boss");
		player.getPackets().sendIComponentText(INTER, 52, ""+Colors.cyan+"A Group Boss");
		player.getPackets().sendIComponentText(INTER, 53, ""+Colors.cyan+"A Group Boss");
		player.getPackets().sendIComponentText(INTER, 54, ""+Colors.cyan+"A Group Boss");
		player.getPackets().sendIComponentText(INTER, 55, ""+Colors.cyan+"Increases Drop Rate");
		player.getPackets().sendIComponentText(INTER, 56, ""+Colors.cyan+"Increases Rewards");
		player.getPackets().sendIComponentText(INTER, 57, ""+Colors.cyan+"Provides Season Tokens");
		player.getPackets().sendIComponentText(INTER, 58, ""+Colors.cyan+"Double Drops & Xp");
		player.getPackets().sendIComponentText(INTER, 59, ""+Colors.cyan+"X2 Drops");
		player.getPackets().sendIComponentText(INTER, 60, ""+Colors.cyan+"X2 Experience");
		player.getPackets().sendIComponentText(INTER, 61, ""+Colors.cyan+"Find The Loot");
		player.getPackets().sendIComponentText(INTER, 62, ""+Colors.cyan+"X2 PVM Damage");
		player.getPackets().sendIComponentText(INTER, 63, ""+Colors.cyan+"Used To Buy Exclusives");
		player.getPackets().sendIComponentText(INTER, 64, ""+Colors.cyan+"Higher Rare Drop Chance");
		player.getPackets().sendIComponentText(INTER, 65, ""+Colors.cyan+"Extra Casket Reward");
		player.getPackets().sendIComponentText(INTER, 66, ""+Colors.cyan+"You're Invincible");
		player.getPackets().sendIComponentText(INTER, 67, ""+Colors.cyan+"X2 Skilling Resources");
		player.getPackets().sendIComponentText(INTER, 68, ""+Colors.cyan+"Double Pet Chance");
		player.getPackets().sendIComponentText(INTER, 69, ""+Colors.cyan+"");
		player.getPackets().sendIComponentText(INTER, 70, ""+Colors.cyan+"");
		player.getPackets().sendIComponentText(INTER, 71, ""+Colors.cyan+"");
		
		/**
		 * Sends Information Panel
		 */
		player.getPackets().sendIComponentText(INTER, 72, ""+Colors.red+"7:00 & 18:00");
		player.getPackets().sendIComponentText(INTER, 73, ""+Colors.red+"00:00, 8:00 & 16:00");
		player.getPackets().sendIComponentText(INTER, 74, ""+Colors.red+"6:00, 12:00, 18:00<br><col=ff0000> & 00:00");
		player.getPackets().sendIComponentText(INTER, 75, ""+Colors.red+"Spawned By Admin");
		player.getPackets().sendIComponentText(INTER, 76, ""+Colors.green+""+Settings.BOSS_SPOTLIGHT+"");
		player.getPackets().sendIComponentText(INTER, 77, ""+Colors.green+""+Settings.EVENT_SPOTLIGHT+"");
		player.getPackets().sendIComponentText(INTER, 78, ""+Colors.green+""+Settings.SEASON_EVENT+"");
		player.getPackets().sendIComponentText(INTER, 79, ""+(player.isHappyHour() ? "<col=00ff00>" : "<col=ff0000>")+"Sun, Tue, Thur, Sat 7pm<br>"+(player.isHappyHour() ? "<col=00ff00>" : "<col=ff0000>")+"Mon, Wed, Fri, Sat 10am");
		player.getPackets().sendIComponentText(INTER, 80, ""+(Settings.DOUBLEDROPS == true || player.isHappyHour() ? "<col=00ff00>ACTIVE" : "<col=ff0000>INACTIVE"));
		player.getPackets().sendIComponentText(INTER, 81, ""+(player.isWeekend() == true || Settings.WELLDOUBLE == true || player.isHappyHour() ? "<col=00ff00>ACTIVE" : "<col=ff0000>INACTIVE"));
		player.getPackets().sendIComponentText(INTER, 82, ""+Colors.red+"Spawns on 45th minute");
		player.getPackets().sendIComponentText(INTER, 83, ""+(Settings.eventdoublehits > 0 ? "<col=00ff00>ACTIVE" : "<col=ff0000>INACTIVE"));
		player.getPackets().sendIComponentText(INTER, 84, ""+(Settings.eventdropeventtokens > 0 ? "<col=00ff00>ACTIVE" : "<col=ff0000>INACTIVE"));
		player.getPackets().sendIComponentText(INTER, 85, ""+(Settings.TENPDROPS == true ? "<col=00ff00>ACTIVE" : "<col=ff0000>INACTIVE"));
		player.getPackets().sendIComponentText(INTER, 86, ""+(Settings.eventdoublecaskets > 0 ? "<col=00ff00>ACTIVE" : "<col=ff0000>INACTIVE"));
		player.getPackets().sendIComponentText(INTER, 87, ""+(Settings.eventspotlightdouble > 0 ? "<col=00ff00>ACTIVE" : "<col=ff0000>INACTIVE"));
		player.getPackets().sendIComponentText(INTER, 88, ""+(Settings.eventdoubleskillingresources > 0 ? "<col=00ff00>ACTIVE" : "<col=ff0000>INACTIVE"));
		player.getPackets().sendIComponentText(INTER, 89, ""+(Settings.eventbosspetchance > 0 ? "<col=00ff00>ACTIVE" : "<col=ff0000>INACTIVE"));
		player.getPackets().sendIComponentText(INTER, 90, ""+Colors.red+"");
		player.getPackets().sendIComponentText(INTER, 91, ""+Colors.red+"");
		player.getPackets().sendIComponentText(INTER, 92, ""+Colors.red+"");
	
	}

}
