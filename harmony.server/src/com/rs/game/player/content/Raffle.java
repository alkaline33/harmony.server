package com.rs.game.player.content;

import java.util.ArrayList;
import java.util.List;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.player.Player;
//import com.rs.utils.Misc;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz'
 * + Edits by @author -Mr_Joopz
 *
 */

public class Raffle {
	
	private static String winner;
	public static ItemsContainer<Item> items = Player.items;

	private static List<String> voters = new ArrayList<String>();
	
	private static final int[] voteRewards = {23673,7671,7673, 29881, 29767, 29763, 23673, 14808, 23671, 20952, 25202, 22408, 20949, 20950, 20951, 9472, 18744, 18745, 18746}; //Set to your own rewards...
	
	public static void addVoterToList(String player) {
		voters.add(player);
	}
	
	public static String chooseRandomVoter() {
		int voteListSize = voters.size();
		int randomVoter = Utils.random(voteListSize - 1);
		String voterUsername = voters.get(randomVoter);
		winner = voterUsername;
		appendReward(winner);
		return voterUsername;
	}
	
	public static List<String> Whoisin() {
		int voteListSize = voters.size();
		return voters;
	}
	
	public static int Chanceofwin() {
		//int voteListSize = voters.size();
		int chance = 100 / voters.size();
		return chance;
	}
	
	
	
	public static void appendReward(String winner) {
		for (Player p : World.getPlayers()) {
			if (p.getUsername().equalsIgnoreCase(winner) && p != null) {
				int index = Utils.random(voteRewards.length);
				p.getBank().addItem(voteRewards[index], 1, true); //Adds a random vote reward
					int item = voteRewards[index];
						World.sendWorldMessage("<img=6><col=ff8c38>News: '"+p.getDisplayName()+"' has just won item: "+ItemDefinitions.getItemDefinitions(item).getName()+" from the Raffle!", false);
						World.sendWorldMessage("<img=6><col=ff8c38>Raffle: If you wish to be entered in the raffle, please make sure you ::vote!", false);
						World.sendWorldMessage("<img=6><col=ff8c38>Raffle: The raffle is drawn randomly", false);
						voters.clear();
			}
			}
		}
	}