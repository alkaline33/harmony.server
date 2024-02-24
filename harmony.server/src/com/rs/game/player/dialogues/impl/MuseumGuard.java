package com.rs.game.player.dialogues.impl;

import java.text.DecimalFormat;

import com.discord.Discord;
import com.rs.Settings;
import com.rs.game.Animation;
import com.rs.game.ForceMovement;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.interfaces.AchievementDiaryInter;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class MuseumGuard extends Dialogue {

    public static final int[] randomAnimal = {
        6935, 3283, 4344, 6966
    };

    /**
     * This dialogue is sent upon the player clicking on object id: 2562.
     */

    @Override
    public void start() {
        sendNPCDialogue(4405, 9827,
            "Hey! Get your hands off that! Don't you know not to touch items in the museum.");

    }

    @Override
    public void run(int interfaceId, int componentId) {
		if (!AchievementDiaryInter.CompletedAllDiarys(player)) {
			player.sendMessage("<col=ff0000>You must complete the Achievement diary before accessing this.");
			end();
			return;
		}
        switch (stage) {
  
            case -1:
                sendPlayerDialogue(9827, "Sorry, I just wondered what it was.");
                stage = 1;
                break;
            case 1:
                sendNPCDialogue(4405, 9827,
                    "A good question. It's perhaps one of more mysterious exibits in this museum.");
                stage = 2;
                break;
            case 2:
                sendNPCDialogue(
                4405,
                9827,
                    "It was only discovered recently, but our curators have" + " dated it back almost as far as we can track. It turns out" + " this cape might be the product, or at least involved with a" + " Second Age mage by the name of Dahmaroc.");
                stage = 3;
                break;
            case 3:
                sendPlayerDialogue(9827, "Dahmaroc?");
                stage = 4;
                break;
            case 4:
                sendNPCDialogue(
                4405,
                9827,
                    "Indeed, you might have learned about him downstairs. He" + " was a powerful mage back in the Second Age. Very skill-" + " focused too, so this cape was a particular find.");
                stage = 5;
                break;
            case 5:
                sendPlayerDialogue(9827, "What do you mean by that?");
                stage = 6;
                break;
            case 6:
                sendNPCDialogue(
                4405,
                9827,
                    "Well, generally, his magical abilities were focused away" + " from combat - it seems this cape is under the mose" + " powerful enchantment we've ever seen.");
                stage = 7;
                break;
            case 7:
                sendPlayerDialogue(9827, "This cape is enchanted?");
                stage = 8;
                break;
            case 8:
                sendNPCDialogue(
                4405,
                9827,
                    "Yes, and more than we can grasp. It physiclly repels anyone who tries to touch" + " it. We had quite a hassle getting it up here.");
                stage = 9;
                break;
            case 9:
                sendPlayerDialogue(9827, "So no one has worn this cape?");
                stage = 10;
                break;
            case 10:
                sendNPCDialogue(
                4405,
                9827,
                    "No one can! It's like it has a mind of it's own juding those who try as unworthy.");
                stage = 11;
                break;
            case 11:
                sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Can I try?",
                    "How interesting.", "I would like to claim the trimmed cape please.");
                stage = 12;
                break;
            case 12:
                switch (componentId) {
                    case OPTION_1:
                        stage = 13;
                        sendPlayerDialogue(9827, "Can I try?");
                        if (!player.isWorthy()) {
							sendRequirementMessages();
						}
                        break;
                    case OPTION_3:
                    	player.getDialogueManager().startDialogue("TrimmedComp");
                    	break;
                    case OPTION_2:
                    default:
                        stage = 30;
                        sendPlayerDialogue(9827, "How interesting.");
                        break;
                }
                break;

            case 13:
                sendNPCDialogue(
                4405,
                9827,
                    "I said no touching... but, if you get close enough, I'm sure you'll the enchantment affects" + " ... Good luck!");
                stage = 14;
                break;
            case 14:
                if (!player.isWorthy()) {
                    player.getInterfaceManager().closeChatBoxInterface();
                    player.lock();
                    //final int[] randomAnimal = { 6935, 3283, 4344, 6966 };
                    //final NPC guard = 4405;
                    WorldTasksManager.schedule(new WorldTask() {
                        int phase = 0;

                        @Override
                        public void run() {
                            switch (phase) {
                                case 0:
                                
                                    player.setNextAnimation(new Animation(857));
                                    break;
                                case 1:
                                    player.setNextAnimation(new Animation(915));
                                    break;
                                case 2:
                                    player.setNextAnimation(new Animation(857));
                                    break;
                                case 3:
                                    player.setNextGraphics(new Graphics(86));
                                    player.getAppearence().transformIntoNPC(randomAnimal[Utils.random(getLength() - 1)]);
                                    break;
                                case 4:
                                    player.setNextForceTalk(new ForceTalk("..What."));
                                    break;
                                case 5:
                                    player.setNextGraphics(new Graphics(86));
                                    player.getAppearence().transformIntoNPC(-1);
                                    player.setNextAnimation(new Animation(10070));
                                    player.setNextForceMovement(new ForceMovement(
                                    new WorldTile(player.getX() + 3,
                                    player.getY(), player.getPlane()), 1, 0));
                                    //player.setNextFaceEntity(4405);
                                    break;
                                case 6:
                                    player.setNextWorldTile(new WorldTile(
                                    player.getX() + 3, player.getY(), player.getPlane()));
                                    sendNPCDialogue(4405, 9827,
                                        "Looks like you had a sense of humour!");
                                    player.unlock();
                                    end();
                                    break;
                            }
                            phase++;
                        }
                    }, 2, 2);
                    stage = 15;
                } else { // TODO: Animation & graphics
                    sendNPCDialogue(4405, 9827,
                        "Wait, i know you... My apologies " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ", you are talked about all over. There is no doubt you are worthy to wear the Completionist Cape.");
                    stage = 16;
                }
                break;
            case 15:
                player.setNextFaceEntity(null);
                player.unlock();
                sendNPCDialogue(4405, 9827,
                    "Sorry, it doesn't look like you are worthy of this cape. At" + " least not yet...");
                stage = -2;
                break;
            case 16:
                sendPlayerDialogue(9827, "You mean I can have it?");
                stage = 17;
                break;
            case 17:
                sendNPCDialogue(
                4405,
                9827,
                    "Well, yes, but... I can't just let you take the exhibit. You" + " may be the true owner, but it is one of the most" + " treasured items we have here.");
                stage = 18;
                break;
            case 18:
                sendNPCDialogue(
                4405,
                9827,
                    "I suppose if the museum were compensated, perhaps I" + " could let you take it... How does 50,000,000 coins" + " sound?");
                stage = 19;
                break;
            case 19:
                sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE,
                    "That sounds fair.", "That sounds like a joke!");
                stage = 20;
                break;
            case 20:
                switch (componentId) {
                    case OPTION_1:
                        sendPlayerDialogue(9827, "That sounds fair.");
                        stage = 21;
                        break;
                    case OPTION_2:
                        sendPlayerDialogue(9827, "That sounds like a joke!");
                        stage = -2;
                        break;
                }
                break;
            case 21:
                if (!player.getInventory().containsItem(995, 50000000)) {
                    sendNPCDialogue(4405, 9827,
                        "You don't have enough Coins in your inventory.");
                    stage = -2;
                } else {
                    if (player.getInventory().getFreeSlots() < 2) {
                        sendNPCDialogue(4405, 9827,
                            "Sorry, but your inventory seems to be full, please come back " + "with more space.");
                        stage = -2;
                    } else {
                    	player.getInventory().deleteItem(995, 50000000);
					Settings.GpSyncAmount += 50000000;
                        sendNPCDialogue(4405, 9827, "Thanks, enjoy!");
                        player.getInventory().addItem(20769, 1);
                        player.getInventory().addItem(20770, 1);
                        player.comped = true;
                		if(player.getSkills().getLevelForXp(Skills.DUNGEONEERING) == 120) {
                		player.setNextForceTalk(new ForceTalk("<col=008000>I'M SO HORNY OVER THIS CAPE RIGHT NOW!!!"));
                		player.sendMessage("Congratulations on completionist cape, do ;;comptitle for your new title.");
                		World.sendWorldMessage("<img=7><col=ff0000>News: "+player.getDisplayName()+" has been awarded the Completionist Cape!", false);
						Discord.sendSimpleMessage("NEWS: " + player.getDisplayName() + " has been awarded the Completionist Cape!");
                     //  UpdateActivities.Activities(player, null, 2, 3, 0);
                		player.isMaxed = true;
                        
                        
                        stage = -2;
                    }
                }
                }
                break;
            case 30:
                sendNPCDialogue(4405, 9827, "Thanks for wasting my time.");
                stage = -2;
                break;
            case -2:
                end();
                break;
        }
    }

    public String getFormattedNumber(int amount) {
        return new DecimalFormat("#,###,##0").format(amount).toString();
    }

  

    public static int getLength() {
        return randomAnimal.length;
    }

    public void sendRequirementMessages() {
        player.getSkills();
		if (Skills.getTotalLevel(player) != 2496) {
            player.getPackets().sendGameMessage("To wear this cape you need level 99 in all skills: ");
            for (int skill = 0; skill < 24; skill++) {
                if (player.getSkills().getLevelForXp(skill) == 99) {
					continue;
				}
                
            }
            if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) != 120) {
                player.getPackets().sendGameMessage("To wear this cape you need level 120 in the following: ");
                player.getPackets().sendGameMessage("Dungeoneering ");
            }
        }
		if (player.getDominionTower().getKilledBossesCount() < 100) { player.getPackets().sendGameMessage("You need to have kill at least 100 bosses in the Dominion tower to wear these gloves.");
		if (player.heistgamesplayed < 3) { player.getPackets().sendGameMessage("You must have completed at least 3 Heists to wear this cape.");
		if (player.LogsCut() < 2500) { player.getPackets().sendGameMessage("You must have cut at least 2500 Yew logs to wear this cape.");
		if (player.LogsBurnt() < 2500) { player.getPackets().sendGameMessage("You must have burnt at least 2500 magic logs to wear this cape.");
		if (player.OreMined() < 500) { player.getPackets().sendGameMessage("You must have mined 500 runite ore to wear this cape.");
		if (player.LapsRun() < 500) { player.getPackets().sendGameMessage("You need to have completed 500 advanced barb agility laps to wear this cape.");
		if (player.SlayTask() < 80) { player.getPackets().sendGameMessage("You need to have completed 80 slayer tasks to wear this cape.");
		//if (player.SlayTask() >= 80) { player.getPackets().sendGameMessage("<str>You need to have completed 80 slayer tasks to wear this cape.<str>");
		if (player.royalcompmade == false) { player.getPackets().sendGameMessage("You need to have created the royal crossbow.");
		if (player.BarSmelt() < 10) { player.getPackets().sendGameMessage("You need to have smelted 10 ancient bars to wear this cape.");
		if (player.GazerKills < 20) { player.getPackets().sendGameMessage("You need to have killed the Night-Gazer atleast 20 times to use this cape.");
		if (player.Dboss < 20) { player.getPackets().sendGameMessage("You need to have killed the Bal'lak atleast 20 times to use this cape.");
		if (player.kilnruns < 3) { player.getPackets().sendGameMessage("You need to have completed the fight kiln atleast 3 times to wear this cape.");
		if (player.getBandos() < 10) { player.getPackets().sendGameMessage("You must have killed bandos at least 10 times to wear this cape.");
		if (player.getSaradomin() < 10) { player.getPackets().sendGameMessage("You must have killed saradomin at least 10 times to wear this cape.");
		if (player.getZamorak() < 10) { player.getPackets().sendGameMessage("You must have killed zamorak at least 10 times to wear this cape.");
		if (player.getArmadyl() < 10) { player.getPackets().sendGameMessage("You must have killed armadyl at least 10 times to wear this cape.");
		if (player.cluescompleted < 3) { player.getPackets().sendGameMessage("You must have completed at least 3 clue scrolls to wear this cape.");
		if (player.pwamountcompleted < 1) { player.getPackets().sendGameMessage("You must have completed Player Wars atleast once.");
		//if (player.completeddiary == false) { player.getPackets().sendGameMessage("You need to have completed the achievement diary to wear this cape.");
		// if (!player.getQuestManager().completedQuest(Quests.NOMADS_REQUIEM)) player.getPackets().sendGameMessage("You need to have completed Nomads Requiem quest to wear this cape.");
      //  if (!player.getQuestManager().completedQuest(Quests.LOST_CITY)) player.getPackets().sendGameMessage("You need to have completed the Lost City quest to wear this cape.");
        if (!player.isCompletedFightKiln()) {
			player.getPackets().sendGameMessage("You must have completed the fight kiln to wear this cape.");
		}
        if (!player.isKilledQueenBlackDragon()) {
			player.getPackets().sendGameMessage("You must have defeated the Queen Black Dragon and obtained the first dragonkin journal to wear this cape.");
		}
		  }
		}
     }
   }
 }
}
}
}
}
	//	}
		}
}
}
		}
		}
}
		}
}
		}
}
		
    
    
    

    /*public void sendRequirementInterface() {
player.getInterfaceManager().sendInterface(275);
for (int i = 0; i < 250; i++)
player.getPackets().sendIComponentText(275, i, "");
player.getPackets().sendIComponentText(275, 1,
"Completionist Requirements");
for (int skill = 0; skill < 24; skill++)
player.getPackets()
.sendIComponentText(
275,
10,
((player.getSkills().getLevelForXp(skill) == 99 ? "<str>"
: " "))
+ " Have level 99 in every skill besides Dungeoneering.");
player.getPackets().sendIComponentText(
275,
11,
((player.getSkills().getLevelForXp(24) == 120 ? "<str>" : " "))
+ " Have level 120 in Dungeoneering.");
player.getPackets()
.sendIComponentText(
275,
12,
((player.getQuestManager().completedQuest(
Quests.NOMADS_REQUIEM) ? "<str>" : " "))
+ " Complete Nomad's Requiem miniquest.");
player.getPackets().sendIComponentText(
275,
13,
((player.isCompletedFightKiln() ? "<str>" : " "))
+ " Complete the Fight kiln minigame.");
player.getPackets().sendIComponentText(
275,
14,
((player.isKilledQueenBlackDragon() ? "<str>" : " "))
+ " Kill the Queen Black Dragon.");
}*/


    @Override
    public void finish() {

    }
}