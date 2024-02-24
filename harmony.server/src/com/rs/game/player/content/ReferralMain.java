package com.rs.game.player.content;

import com.rs.game.player.Player;
import com.rs.game.player.content.raids.ClaimedReferral;
import com.rs.utils.YoutuberReferral;
import com.rs.utils.YoutuberReferral2;

public class ReferralMain {
	
	public static void CheckReferral(Player player, String value) {
		switch (value) {
		case "runelocus":
		case "topg":
		case "rune-server":
		case "runeserver":
		case "rune server":
		case "top100arena":
		case "moparscape":
		case "rsps-list":
		case "rsps list":
		case "rspslist":
		case "facebook":
		case "instagram":
			if (ClaimedReferral.HasClaimed(player.getMacAddress())){
				player.getDialogueManager().startDialogue("SimpleMessage", "<col=ff0000>A Toplist referral has already been claimed on this device.");
				return;
			}
			if (player.claimedtoplist) {
				player.getDialogueManager().startDialogue("SimpleMessage", "<col=ff0000>A Toplist referral has already been claimed on this account.");
				return;
			}
			if (player.playdays >= 1) {
				player.getDialogueManager().startDialogue("SimpleMessage", "<col=ff0000>This account is no longer eligible for Toplist referral rewards.");
				return;
			}
			player.printReferralLog(player, value);
			player.getInventory().addItemDrop(29117, 2);
			player.getDialogueManager().startDialogue("SimpleMessage", "Thank you for joining via "+value.toLowerCase()+", we have added 2 loot boxes to your inventory.<br>Be sure to check out ;;starterguide.");
			ClaimedReferral.addMacClaimed(player.getMacAddress());
			player.claimedtoplist = true;
			break;
		case "eggyrs":
			if (YoutuberReferral2.HasClaimed(player.getMacAddress())){
				player.getDialogueManager().startDialogue("SimpleMessage", "<col=ff0000>A Youtuber referral has recently been claimed on this device.");
				return;
			}
			player.printReferralLog(player, value);

			player.getInventory().addItemDrop(29870, 1);
			player.getDialogueManager().startDialogue("SimpleMessage", ""+value.toLowerCase()+" referral reward has been added to your inventory!<br>Be sure to check out ;;starterguide.");
			YoutuberReferral2.addMacClaimed(player.getMacAddress());
		break;
		case "eggy":
			if (YoutuberReferral.HasClaimed(player.getMacAddress())){
				player.getDialogueManager().startDialogue("SimpleMessage", "<col=ff0000>A Youtuber referral has recently been claimed on this device.");
				return;
			}
			player.printReferralLog(player, value);
			player.getInventory().addItemDrop(29117, 2);
			player.getDialogueManager().startDialogue("SimpleMessage", ""+value.toLowerCase()+" referral reward has been added to your inventory!<br>Be sure to check out ;;starterguide.");
			YoutuberReferral.addMacClaimed(player.getMacAddress());
			break;
			/**
			 * Noobs own reward
			 */
			default:
			player.getDialogueManager().startDialogue("SimpleMessage", "Your referral code did not match any existing codes, please try again.<br> If you're from a toplist, simply type the name of the website.");
				break;
		}
	}

}
