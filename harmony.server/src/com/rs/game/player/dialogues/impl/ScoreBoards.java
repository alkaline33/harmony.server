package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.CalamityBestWave;
import com.rs.utils.HeistBags;
import com.rs.utils.TopTriviaAnswers;
import com.rs.utils.TopVoters;
import com.rs.utils.TotalBossKills;

public class ScoreBoards extends Dialogue {
	
	/**
	 * @author Mr_Joopz
	 */

	public ScoreBoards() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Choose a Scoreboard to view", "Total Boss Kills",
				"Top Voters (month)", "Calamity waves", "Trivia Answers", "Heist Bags Deposited");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				CloseDialogue();
				TotalBossKills.checkRank(player);
				TotalBossKills.showRanks(player);
			} else if (componentId == OPTION_2) {
				CloseDialogue();
				TopVoters.checkRank(player);
				TopVoters.showRanks(player);
			} else if (componentId == OPTION_3) {
				CloseDialogue();
				CalamityBestWave.checkRank(player);
				CalamityBestWave.showRanks(player);
			} else if (componentId == OPTION_4) {
				CloseDialogue();
				TopTriviaAnswers.checkRank(player);
				TopTriviaAnswers.showRanks(player);
			} else if (componentId == OPTION_5) {
				CloseDialogue();
				HeistBags.checkRank(player);
				HeistBags.showRanks(player);
			}
			
		}
	}
	
	public void CloseDialogue() {
		player.getInterfaceManager().closeChatBoxInterface();
		player.getInterfaceManager().closeOverlay(true);
	}
		

	@Override
	public void finish() {
	}

}
