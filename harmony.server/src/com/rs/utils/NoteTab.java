package com.rs.utils;

import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;


public class NoteTab {
	

	private static boolean resizableScreen;
	/**
	 * 
	 * @param player
	 */
	
	public static void sendTab(final Player player) {
		WorldTasksManager.schedule(new WorldTask() {
		@Override
		public void run(){
			player.getPackets().sendIComponentText(930, 10, "<col=0066FF>Player Profile:</col>");
			player.getPackets().sendIComponentText(930, 16, "Xp Mode: <col=ff0000>" + player.getXpMode() + " <br>"
						+ "Donated Amount: <col=ff0000>$" + player.donationvalue + " <br>"
					+ "Time Played: <col=ff0000>" + player.playdays + "D, " + player.playhours
					+ "H, " + player.getPlayPoints() + "M <br>"
					+ "Slayer Task: <col=ff0000>" + (player.getTask() != null ? player.getTask().getName()+"<br>" : "No Task<br>")
					+ "Diary Points: <col=ff0000>" + player.getDiaryPoints() + "<br>"
					+ "PvP Points: <col=ff0000>" + player.getPvpPoints() + "<br>"
					+ "Daily Challenge Points: <col=ff0000>" + player.challengepoints + "<br>"
						+ "Harmony Points: <col=ff0000>" + Utils.format(player.getDryPoints()) + " <br>" + "Vote Points: <col=ff0000>" + Utils.format(player.VotePoint) + " <br>"
					+ "Slayer Points: <col=ff0000>" + player.getSlayerPoints() + "<br>"
					+ "Dung Points: <col=ff0000>"  + Utils.format(player.getdungpoints()) + " <br>"
					+ "Skilling points: <col=ff0000>"  + Utils.format(player.skillpoints) + " <br>"
					+ "Player Wars points: <col=ff0000>"  + Utils.format(player.playerwarspoints) + " <br>"
					+ "Slayer Survival points: <col=ff0000>"  + Utils.format(player.slayersurvivalpoints) + " <br>"
					+ "Boss Kills: <col=ff0000>"  + Utils.format(player.BossKills()) + " <br>"
					+ "Loyalty Points: <col=ff0000>" + Utils.format(player.getLoyaltyPoints()) + " <br>"
					+ "Clue Scrolls Completed: <col=ff0000>"  + player.cluescompleted + " <br>"
					+ "Calamity Points: <col=ff0000>"  + player.calamityrewardpoints + " <br>"
					+ "Calamity Highest Wave: <col=ff0000>"  + player.calamitybestwave + " <br>"
					+ "Calamity Total Waves: <col=ff0000>"  + player.calamitytotalwavescomplete + " <br>"
					+ "Double xp Minutes: <col=ff0000>"  + player.d60mxp1 + " <br>"
					+ "Ingenuity chests looted: <col=ff0000>"  + player.ingenuitychestslooted + " <br>"
					+ "Heist games played: <col=ff0000>"  + player.heistgamesplayed + " <br>"
					+ "Heist cash bags deposited: <col=ff0000>"  + player.heistcashbagsdeposit + " <br>"
					+ "Drop rate boost: <col=ff0000>" + player.getDropRateBonus("") + " + (" + (player.getInventory().hasItem(1720) ? 5 : player.getInventory().hasItem(29216) ? 9 : 0) + ")%");
					//+ "Drop rate cap: <col=ff0000>" + player.getDropRateBonus("") + " + (" + (player.getInventory().hasItem(1720) ? 5 : player.getInventory().hasItem(29216) ? 9 : 0) + ")%");
			}
		
		//to ad - current slay task -
		
		
		
		
	}, 0, 5);
	
	
}
}