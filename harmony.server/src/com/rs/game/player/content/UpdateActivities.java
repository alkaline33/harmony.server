package com.rs.game.player.content;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import com.rs.cores.CoresManager;
import com.rs.game.player.Player;

public class UpdateActivities {

	/**
	 * The lock object variable.
	 */
	private static Object lock = new Object();

	/**
	 * Update a player activities to the Adventurer's Log.
	 * @param player The player reference.
	 * @param displayname The player's display name.
	 */
	public static void Activities(final Player player, final String displayname, final int type, final int num1, final int num2) {
		if(player == null) {
			return;
		}
		if(player.getRights() == 2) {
			return;
		}
		if(!player.getAdventurersLog().getSetting(type))
			return;
		if(!requirements(player)) {
			return;
		}
		final String aLogWebsite = "http://drygonscape.co.uk/advlog/";
		final String PASS = "nike2323";
		CoresManager.slowExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					synchronized(lock) {
						URL url = new URL(""+aLogWebsite+"updateactivities.php?pass="+PASS+"&username="+
								player.getDisplayName().replaceAll(" ", "_")+"&type="+type+"&num1="+num1+"&num2="+num2+(displayname != null ? "&oldname="+displayname.replaceAll(" ", "_")+"" : "")+"");
						//System.out.println(url);
						url.openStream().close();
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Check if the player has the requirements to be on the highscores.
	 * @param player The player reference.
	 * @return If the player has the requirements {@code true}.
	 */
	private static boolean requirements(Player player) {
		
			/*return false;*/
		return true;
	}
	
}
