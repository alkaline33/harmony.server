package com.rs.game.player.content;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

public class EventSpotlight {

	public static final String[] EVENT = { "Double vote points", "Double slayer points", "Double Harmony points", "Double minigame rewards", "Double loyalty points", "5% droprate boost", "Free entry to gwd" };

	/**
	 * 
	 * @Author Connor
	 */

	public static void GrabEvent() {
		String currentevent = Settings.EVENT_SPOTLIGHT;
		Settings.EVENT_SPOTLIGHT = EVENT[Utils.random(EVENT.length)];
		if (Settings.EVENT_SPOTLIGHT.equalsIgnoreCase(currentevent)) {
			GrabEvent();
			System.out.println("Event spotlight: Same event was rolled. Event was re-rolled.");
			return;
		}
		World.sendWorldMessage(Colors.orange + "Event Spotlight: " + Settings.EVENT_SPOTLIGHT + "!", false);
		return;
	}
}
