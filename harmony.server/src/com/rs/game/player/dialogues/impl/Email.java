package com.rs.game.player.dialogues.impl;

import com.rs.game.Animation;
import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;

public class Email extends Dialogue {

/**
 * Author Mr Joopz
 */

	@Override
	public void start() {
		
		sendNPCDialogue(
				-1,
				9827,
				"Hey "
						+ player.getUsername()
						+ ", I'm the Security guard. Please choose which email provider you're with.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Email provider", 
					"@Hotmail.co.uk",
					"@Hotmail.com",
					"@Outlook.com",
					"@Gmail.com",
			"Next page");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				player.setAccountEmail("@hotmail.co.uk");
				player.getTemporaryAttributtes().put("setemail", Boolean.TRUE);
				player.getPackets().sendInputNameScript("Enter the first part of your email upto @:");
				end();
			}
			else if (componentId == OPTION_2) {
					player.setAccountEmail("@hotmail.com");
					player.getTemporaryAttributtes().put("setemail", Boolean.TRUE);
					player.getPackets().sendInputNameScript("Enter the first part of your email upto @:");
					end();
				}
			else if (componentId == OPTION_3) {
				player.setAccountEmail("@outlook.com");
				player.getTemporaryAttributtes().put("setemail", Boolean.TRUE);
				player.getPackets().sendInputNameScript("Enter the first part of your email upto @:");
				end();
			}
			else
			if (componentId == OPTION_4) {
				player.setAccountEmail("@gmail.com");
				player.getTemporaryAttributtes().put("setemail", Boolean.TRUE);
				player.getPackets().sendInputNameScript("Enter the first part of your email upto @:");
				end();
			}
			else
			if (componentId == OPTION_5) {
				sendOptionsDialogue("Email provider", 
						"@Yahoo.co.uk",
						"@Yahoo.com",
						"@Inbox.com",
						"I don't have one of these.");
				stage = 5;
			}
			} else if (stage == 5) {
				if (componentId == OPTION_1) {
					player.setAccountEmail("@yahoo.co.uk");
					player.getTemporaryAttributtes().put("setemail", Boolean.TRUE);
					player.getPackets().sendInputNameScript("Enter the first part of your email upto @:");
					end();
				}
				if (componentId == OPTION_2) {
					player.setAccountEmail("@yahoo.com");
					player.getTemporaryAttributtes().put("setemail", Boolean.TRUE);
					player.getPackets().sendInputNameScript("Enter the first part of your email upto @:");
					end();				}
			
				if (componentId == OPTION_3) {
					player.setAccountEmail("@inbox.com");
					player.getTemporaryAttributtes().put("setemail", Boolean.TRUE);
					player.getPackets().sendInputNameScript("Enter the first part of your email upto @:");
					end();
				}

					if (componentId == OPTION_4) {
					player.sendMessage("You will need to make an email with one of the following to proceed.");
						end();
					}
		} else if (stage == 3) {
			end();
		}
}
	

	@Override
	public void finish() {

	}

}