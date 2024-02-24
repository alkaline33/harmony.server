package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.item.Item;
import com.rs.game.player.content.UpdateActivities;
import com.rs.game.player.dialogues.Dialogue;

public class EstateAgent extends Dialogue {

        private int npcId = 4247;

        @Override
        public void start() {
                sendEntityDialogue(SEND_2_TEXT_CHAT,
                                new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
                                                "Good day, "+player.getDisplayName()+", I'm the estate agent here in "+Settings.SERVER_NAME+". My job is to sell properties, May I interest you in a property?"}, IS_NPC, npcId, 9827);
        }
        
        @Override
        public void run(int interfaceId, int componentId) {
        	switch (stage) {
        	case -1:
        		if (!player.hasHouse) {
        			sendOptionsDialogue("Would you like to buy a property?", "Yes please", "No thank you.");
                    stage = 1;
        		} else {
        			sendEntityDialogue(SEND_2_TEXT_CHAT,
                            new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "Can I interest you in a change of scenery? It'll cost you a bit for the move, but it's well worth it."}, IS_NPC, npcId, 9827);
                	stage = 8;
        		}
        		break;
        	case 1:
        		switch (componentId) {
        		case OPTION_1:
        			sendNPCDialogue(npcId, 9827, "I can sell you a lovely house, which you'd be able to access from any house portal around Harmony for a cheap price!");
                    stage = 2;
        			break;
        			default:
        				end();
        				break;
        		}
        		break;
        	case 2:
        		sendPlayerDialogue(9827, "It sounds amazing! How much exactly does it cost?");
            	stage = 3;
        		break;
        	case 3:
        		sendNPCDialogue(npcId, 9827, "This house only costs 100,000 gold coins. It's a bargain if you ask me!");
        		stage = 4;
        		break;
        	case 4:
        		sendNPCDialogue(npcId, 9827, "So would you like to buy it now or later?");
            	stage = 5;
            	break;
        	case 5:
        	    sendOptionsDialogue("Would you like to buy a property for 100,000 gold coins?", "I'd like to buy it now please.", "I'll think about it and come back later.");
                stage = 6;
        		break;
        	case 6:
        		switch (componentId) {
        		case OPTION_1:
        			if (player.getInventory().containsItem(995, 100_000)) {
        			//if (player.getInventory().getCoinsAmount() >= 100000) {
                    	sendNPCDialogue(npcId, 9827, "It's been a pleasure doing business Sir! To access your house, simply find a house portal anywhere around Harmony.");
                    	player.getInventory().removeItemMoneyPouch(new Item(995, 100000));
                    	player.hasHouse = true;
                    	player.spokeToAgent = true;
                    	player.setHouseStyle(0);
                    	//UpdateActivities.Activities(player, null, 10, 1, 1);
                            stage = 7;
                    	} else {
                    		sendNPCDialogue(npcId, 9827, "I'm sorry, it looks like you don't have the funds in your inventory, please come back when you do.");
                    		stage = 7;
                    	}
        			break;
        			default:
        				end();
        				break;
        		}
        		break;
        	case 7:
        		end();
        		break;
        	case 8:
        		sendPlayerDialogue(9827, "That sounds amazing! How much would a 'change in scenery' cost?");
            	stage = 11;
        		break;
        	case 11:
        		sendNPCDialogue(npcId, 9827, "That depends very on the type of environment you desire. Which of the following draws your interest?");
            	stage = 12;
        		break;
        	case 12:
        		sendOptionsDialogue("Which theme are you interested in?", "Basic timber.", "Basic stone.", "White washed stone.", "Fremmenik timber.", "Next page.");
            	stage = 13;
        		break;
        	case 13:
        		switch (componentId) {
            	case OPTION_1:
            		sendNPCDialogue(npcId, 9827, "This is the default environment, chances are, you already have this selected. It'll cost 50,000 gold coins to purchase.");
            		player.getTemporaryAttributtes().put("HouseEnvironment", 0);
            		player.getTemporaryAttributtes().put("HouseCost", 50000);
            		stage = 15;
            		break;
            	case OPTION_2:
            		sendNPCDialogue(npcId, 9827, "This environment is slightly more expensive to purchase, it'll cost you 200,000 gold coins.");
            		player.getTemporaryAttributtes().put("HouseEnvironment", 1);
            		player.getTemporaryAttributtes().put("HouseCost", 200000);
            		stage = 15;
            		break;
            	case OPTION_3:
            		sendNPCDialogue(npcId, 9827, "This environment is set in the desert. It'll cost you 350,000 gold coins.");
            		player.getTemporaryAttributtes().put("HouseEnvironment", 2);
            		player.getTemporaryAttributtes().put("HouseCost", 350000);
            		stage = 15;
            		break;
            	case OPTION_4:
            		sendNPCDialogue(npcId, 9827, "This environment will cost you 600,000 coins to purchase.");
            		player.getTemporaryAttributtes().put("HouseEnvironment", 3);
            		player.getTemporaryAttributtes().put("HouseCost", 600000);
            		stage = 15;
            		break;
            	case OPTION_5:
            		sendOptionsDialogue("Which theme are you interested in?", "Tropical timber.", "Fancy Stone.", "None, thank you.");
            		stage = 14;
            		break;
            	}
        		break;
        	case 14:
        		switch (componentId) {
        		case OPTION_1:
        			sendNPCDialogue(npcId, 9827, "This environment will cost you 800,000 coins to purchase.");
        			player.getTemporaryAttributtes().put("HouseEnvironment", 4);
        			player.getTemporaryAttributtes().put("HouseCost", 800000);
        			stage = 15;
        			break;
        		case OPTION_2:
        			sendNPCDialogue(npcId, 9827, "This environment will cost you 1,000,000 coins to purchase.");
        			player.getTemporaryAttributtes().put("HouseEnvironment", 5);
        			player.getTemporaryAttributtes().put("HouseCost", 1000000);
        			stage = 15;
        			break;
        		//case OPTION_3:
        		//	sendNPCDialogue(npcId, 9827, "This environment is the most costly to purchase; it'll cost you 1,500,000 coins to purchase.");
        		//	player.getTemporaryAttributes().put("HouseEnvironment", 6);
        		//	stage = 15;
        		//	break;
        		//case OPTION_3:
        			default:
        			end();
        			break;
        		}
        		break;
        	case 15:
        		sendOptionsDialogue("Are you sure you wish to make this purchase?", "Yes.", "Maybe another time.");
        		stage = 16;
        		break;
        	case 16:
        		switch (componentId) {
        		case OPTION_1:
        			final Integer selection = (Integer) player.getTemporaryAttributtes().remove("HouseEnvironment");
        			final Integer value = (Integer) player.getTemporaryAttributtes().remove("HouseCost");
        			if (selection == null || value == null) {
        				end();
        				return;
        			}
        			if (player.getInventory().containsItem(995, value)) {
        				player.getInventory().removeItemMoneyPouch(new Item(995, value));
        				player.setHouseStyle(selection);
        				//player.sm("Thank you for your purchase, changes to your house will take effect the next time you log in.");
        				player.getHouse().changeLook(player);
        			} else
        				player.sm("You don't have enough coins in your inventory for this purchase.");
        			end();
        			break;
        		default:
        			end();
        			break;
        		}
        		break;
        	}
        }

        @Override
        public void finish() {

        }
}