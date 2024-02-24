package com.rs.game.player.dialogues.impl;

import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.ForceTalk;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.PrestigeHS;

public class PrestigeOne extends Dialogue {

        private int npcId = 13251;

        @Override
        public void start() {
                sendEntityDialogue(SEND_2_TEXT_CHAT,
                                new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
                                                "Hello "+player.getDisplayName()+" Would you like to learn about the Prestige system?"}, IS_NPC, npcId, 9827);
        }
        
        @Override
        public void run(int interfaceId, int componentId) {
                if (stage == -1) {
                        sendOptionsDialogue("Prestige System", "Yes", "Could i see the prestige highscores please.", "I'd like to check my Prestige tokens please.", "I would like to recieve my prestige title please.");
                        stage = 1;
                } else if (stage == 1) {
                        if (componentId == OPTION_1) {
                        	sendNPCDialogue(npcId, 9827, "The prestige system allows you to reset ALL of you're skills, you will be rewarded with one prestige token for every time you prestige, you will need every 99 in order to prestige, For every prestige you will gain one new user title. ");
                                stage = 2;
                                }
                        else if (componentId == OPTION_2) {
                        	PrestigeHS.showRanks(player);
                                end();
                                }
                        else if (componentId == OPTION_3) {
                        	player.getPackets().sendGameMessage("I Currently have: "+player.prestigeTokens+" prestige tokens.");
                        	player.setNextForceTalk(new ForceTalk("I Currently have: "+player.prestigeTokens+" prestige tokens."));
                            end();
                            }
                        else if (componentId == OPTION_4) {
            				if (player.prestigeTokens == 1) {
            					player.getAppearence().setTitle(676);
            				} else if (player.prestigeTokens == 2) {
            					player.getAppearence().setTitle(6575);
            				} else if (player.prestigeTokens == 3) {
            					player.getAppearence().setTitle(1657);
            				} else if (player.prestigeTokens == 4) {
            					player.getAppearence().setTitle(345);
            				} else if (player.prestigeTokens == 5) {
            					player.getAppearence().setTitle(346);
            				} else if (player.prestigeTokens == 6) {
            					player.getAppearence().setTitle(347);
            				} else if (player.prestigeTokens == 7) {
            					player.getAppearence().setTitle(348);
            				} else if (player.prestigeTokens == 8) {
            					player.getAppearence().setTitle(349);
            				} else if (player.prestigeTokens == 9) {
            					player.getAppearence().setTitle(350);
            				} else if (player.prestigeTokens == 10) {
            					player.getAppearence().setTitle(999);
            				} else {
            					player.getPackets().sendGameMessage("You need to have prestiged to use this.");
            				}
                            end();
                            }
                        else if (componentId == OPTION_5) {
                        	player.prestigeShops();
                            end();
                            }
                } else if (stage == 2) {
                	sendPlayerDialogue(9827, "Wow sounds amazing!");
                	stage = 3;
                } else if (stage == 3) {
                	sendNPCDialogue(npcId, 9827, "That's because it is! All of your stats will be reset every prestige level, but you have 10 prestige levels to gain, so get grinding!");
                	stage = 4;
                } else if (stage == 4) {
        			sendOptionsDialogue("Would you like to prestige?", "Yes!", "no");
                	stage = 5;
                } else if (stage == 5) {
        			if (componentId == OPTION_1) {
						player.prestige();
					}
        			if(!player.isPrestige1()) {
        				player.setNextForceTalk(new ForceTalk("I must get all 99's & 120 dungeoneering in order to prestige!"));
        			}
        			else if (player.isPrestige1()) {
        				if (player.prestigeTokens >= 10) {
        					player.sendMessage("You have already achieved maximum prestige!");
        					player.nothing();
        				}
        				else if (player.prestigeTokens >= 0) {
        				player.setCompletedPrestigeOne();
        				}
        				
        			}
        			else if (componentId == OPTION_2) {
                    	end();
                    }
                end();
                }
            }


        @Override
        public void finish() {

        }
}