package com.rs.game.player.content;

import java.util.concurrent.TimeUnit;

import com.rs.cores.CoresManager;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.utils.Utils;

/**
 * 
 * @author Savions Sw
 *
 */
public class WildywyrmAnnouncer {
	
	/**
	 * The {@link WildywyrmAnnouncer} instance.
	 */
	public static final WildywyrmAnnouncer INSTANCE = new WildywyrmAnnouncer();
	
	/**
	 * The wildy wyrm reference.
	 */
	public NPC WILDY_WYRM;
	
	/**
	 * The spawn locations.
	 */
	private static final WorldTile[] LOCATIONS = { new WorldTile(3296, 3887, 0), new WorldTile(3197, 3690, 0), new WorldTile(3238, 3623, 0),
		new WorldTile(3237, 3765, 0), new WorldTile(3305, 3927, 0), new WorldTile(3206, 3774, 0) };
	
	/**
	 * Initializes this {@link WildywyrmAnnouncer}.
	 */
	public final void init() {
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				if (WILDY_WYRM == null || WILDY_WYRM.hasFinished())
					spawnWildyWyrm();
			}
		}, 0, 30, TimeUnit.SECONDS);
	}

	/**
	 * Spawn a wildywyrm.
	 */
	private final void spawnWildyWyrm() {
		if (WILDY_WYRM != null)
			WILDY_WYRM.finish();
		WILDY_WYRM = new NPC(3334, LOCATIONS[Utils.random(LOCATIONS.length)], -1, true, true);
		World.sendWorldMessage("<img=7>News: " + "<col=ff0000>" + "A new Wildywyrm has spawned in a random location in the Wilderness!" + "</col>", false);
	}
}