package com.rs.game.player.content.interfaces.titles;

import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.net.decoders.WorldPacketsDecoder;
import com.rs.utils.Colors;

public class TitlesManager {
    private static final int INTER = 3034, TITLE_REQS = 46, HELP_BUTT = 51, TITLE_DESC = 42;

    public static final int[] TITLE_COMPONENTS = {33, 52, 55, 58, 61, 64, 67, 70, 73, 76, 79, 82,
            85, 88, 91, 94, 97, 100, 103, 106, 109, 112, 115, 118, 121, 124, 127, 130, 133, 136, 139, 142, 145, 148, 151,
            154, 157, 160, 163, 166, 169, 172, 175, 178};

    public static final int[] TITLE_CLICKABLE_COMPONENTS = {35, 54, 57, 60, 63, 66, 69, 72, 75, 78, 81, 84,
            87, 90, 93, 96, 99, 102, 105, 108, 111, 114, 117, 120, 123, 126, 129, 132, 135, 138, 141, 144, 147, 150, 153,
            156, 159, 162, 165, 168, 171, 174, 177, 180};

    public static void sendInterface(Player player) {
        if (player.isIronman()){
            player.sendMessage("Ironmans cannot switch title, sorry for the inconvenience!");
            return;
        }
        sendTitleWithDescription(player, 0);
        sendTitleList(player);
        sendSelectedTitle(player, TITLE_CLICKABLE_COMPONENTS[0]);
        player.getInterfaceManager().sendInterface(INTER);
    }


    private static void sendTitleWithDescription(Player player, int titleId) {
        final StringBuilder stb2 = new StringBuilder();
        for (Titles.Title title : Titles.Title.values()) {
            if (title.getTitleId() == titleId) {
                player.getPackets().sendIComponentText(INTER, 24,  "Description");
                player.getPackets().sendIComponentText(INTER, TITLE_DESC,  "<col=bd6500>Name:</col> "+title.getTitleName()+"<br><col=bd6500>Status:</col> "+(getRequirements(player, titleId) ? Colors.green+"Unlocked</col>":Colors.red+"Locked</col>")+"<br><br>" +title.getTitleDescription()+".");
                if (title.getTitleRequirements() != null) {
                    for (int x2 = 0; x2 < title.getTitleRequirements().length; x2++) {
                        stb2.append((title.getTitleRequirements().length > 1 ? "- " + title.getTitleRequirements()[x2][0] + ".<br>" : title.getTitleRequirements()[x2][0] + ".<br>"));
                    }
                } else {
                    stb2.append("None.<br>");
                }
                player.getPackets().sendIComponentText(INTER,
                        TITLE_REQS, stb2.toString());
            }
        }
    }

    private static void sendTitleList(Player player) {
        int i2 = 0;

        for (int i = 0; i < TITLE_COMPONENTS.length; i++) {
            player.getPackets().sendHideIComponent(INTER, TITLE_COMPONENTS[i], true);
        }


        for (Titles.Title title : Titles.Title.values()) {
            if (title != null) {
                if (title.getTitleId() != i2)
                    continue;
                player.getPackets().sendHideIComponent(INTER, TITLE_COMPONENTS[i2], false);
                player.getPackets().sendIComponentText(INTER, TITLE_CLICKABLE_COMPONENTS[i2], title.getTitleName());
                i2++;
            }
        }
    }

    private static void sendSelectedTitle(Player player, int compId) {
        for (Titles.Title title : Titles.Title.values()) {
            if (title.getTitleCompId() == compId) {
                player.getPackets().sendIComponentText(INTER, compId, "<col=ff981f>" + title.getTitleName());
            }
        }
    }

    public static void handleButtons(Player player, int compId, int packetId) {
        for (int i = 0; i < TITLE_COMPONENTS.length; i++) {
            if (compId == TITLE_CLICKABLE_COMPONENTS[i]) {
                if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) { //Select title
                    sendTitleWithDescription(player, i);
                    sendTitleList(player);
                    sendSelectedTitle(player, compId);
                }
                if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) { //Apply Title
                    sendTitleWithDescription(player, i);
                    sendTitleList(player);
                    sendSelectedTitle(player, compId);
                    if (getRequirements(player, i)) {
                        for (Titles.Title title : Titles.Title.values()) {
                            if (title != null) {
                                if (title.getTitleId() != i)
                                    continue;
                                if (title.getTitleCompId() != compId)
                                    continue;
                                player.getAppearence().setTitle(title.getTitleRealId());
                                player.getAppearence().generateAppearenceData();
                                player.getDialogueManager().startDialogue("SimpleMessage", "Your title was succesfully set to: <col=f00000>"+title.getTitleName()+"</col>.");
                            }
                        }
                    } else {
                        player.getDialogueManager().startDialogue("SimpleMessage", "Sorry, but you haven't unlocked this title yet!");
                        sendTitleWithDescription(player, i);
                        sendTitleList(player);
                        sendSelectedTitle(player, compId);
                    }
                }
            }
        }
        if (compId == HELP_BUTT)
            player.getDialogueManager().startDialogue("SimpleMessage", "To apply a title to your character, right-click the title name from the list and then left-click the option 'Apply'!");
    }

    private static boolean getRequirements(Player player, int index) {
        switch(index) {
            case 0:
                return player.comped == true;
            case 1:
                return player.maxed == true;
//                return player.hasCompletedAchievement(Achievements.Store.POWERTATOES);
            case 2:
                return player.VoragoKills >= 1;
            case 3:
                return player.divine == true;
            case 4:
                return player.zamorak >= 50;
            case 5:
                return player.bandos >= 50;
            case 6:
                return player.saradomin >= 50;
            case 7:
                return player.armadyl >= 50;
            case 8:
                return player.santatitle == true;
            case 9:
                return player.dreamcut >= 1000;
            case 10:
                if (player.FinalBossTitle(player)) {
                    if (player.fbtitle == false) {
                        World.sendWorldMessage("<col=FF0000><shad=600000>" + player.getDisplayName() + " has just unlocked the *Final Boss* title!", true);
                        player.fbtitle = true;
                    }
//    				player.getAppearence().setTitle(66);
//    				player.getPackets().sendGameMessage("Title acquired.", true);
                }
                player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have completed the requirements stated in the chat box.");
                player.sendMessage("<col==FF0000>You need a total of 4,000 boss kills and atleast 50 in the following bosses;");
                player.sendMessage("<col=FF0000>Corporeal beast, KBD, QBD, Bork, Nex, Glacors, Rise of the Six, Bandos, Saradomin, Zamorak, Armadyl, WildyWyrm, Aquatic Wyrm, Demi Gods,");
                player.sendMessage("<col=FF0000>Vorago, AOD, Gazer, Bad Santa, Dryax, Hope Devourer, Kalphite King, Zulrah, Necrolord, Thunderous, Sunfreet, Anivia, Sliske, Giant Mole, 3 Amigo's,");
                player.sendMessage("<col=FF0000>Hydra, Celestia, KQ, Kraken, Sire, Cerberus, Sirenic, Callisto, Venenatis, Chaos Fanatic, Crazy Archaeologist, Scorpia, Vet'ion, Chambers of Xeric, Theatre of Blood, Skotizo & Lizardman!");
                return false;
            case 11:
                if (player.InsaneFinalBossTitle(player)) {
                    if (player.insanefbtitle == false) {
                        World.sendWorldMessage("<col=F16F0F><shad=600000>" + player.getDisplayName() + " has just unlocked the *Insane Final Boss* title!", true);
                        player.insanefbtitle = true;
                    }
//    				player.getAppearence().setTitle(67);
//    				player.getPackets().sendGameMessage("Title acquired.", true);
                }
                player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have completed the requirements stated in the chat box.");
                player.sendMessage("<col==FF0000>You need a total of 5,000 boss kills and atleast 100 in the following bosses;");
                player.sendMessage("<col=FF0000>Corporeal beast, KBD, QBD, Bork, Nex, Glacors, Rise of the Six, Bandos, Saradomin, Zamorak, Armadyl, WildyWyrm, Aquatic Wyrm, Demi Gods,");
                player.sendMessage("<col=FF0000>Vorago, AOD, Gazer, Bad Santa, Dryax, Hope Devourer, Kalphite King, Zulrah, Necrolord, Thunderous, Sunfreet, Anivia, Sliske, Giant Mole, 3 Amigo's");
                player.sendMessage("<col=FF0000>Hydra, Celestia, KQ, Kraken, Sire, Cerberus, Sirenic, Callisto, Venenatis, Chaos Fanatic, Crazy Archaeologist, Scorpia, Vet'ion, Chambers of Xeric, Theatre of Blood, Skotizo & Lizardman!");
                return false;
            case 12:
                return player.BossKills() >= 1500;
            case 13:
                return player.playdays >= 25;
            case 14:
                return player.SunfreetKills >= 1000;
            case 15:
                return player.BossKills() >= 15000;
            case 16:
                return player.thunderous == true;
            case 17:
                return player.pillaroflighttouched >= 500;
            case 18:
                return player.templeoflightmummykills >= 200;
            case 19:
                return true;
            case 20:
                return player.runecoinsobtained >= 300;
            case  21:
                return player.runecoinsobtained >= 1500;
            case 22:
                return player.runecoinsobtained >= 5000;
            case 23:
                return player.runecoinsobtained >= 7500;
            case 24:
                return player.runecoinsobtained >= 10000;
            case 25:
                String totalxp = player.getSkills().getTotalXp(player);
                return totalxp.equalsIgnoreCase("9000000000");
            case 26:
                return player.defenderofslisketitle == true;
            case 27:
                return player.finishedeasterevent == true;
            case 28:
                return player.angrybunnytitle == true;
            case 29:
                return player.theatreofbloodcompleted >= 500 && player.osrsraidscompleted >= 500;
            case 30:
                return player.theatreofbloodcompleted >= 500;
            case 31:
                return player.osrsraidscompleted >= 500;
            case 32:
                return player.TotalOSRSRaids() >= 100;
            case 34:
                return true;
            case 35:
                return true;
            case 36:
                return true;
            case 37:
                return true;
            case 38:
                return true;
            case 39:
                return true;
            case 40:
                return true;
            case 41:
                return true;
            case 42:
                return true;
            case 43:
                return true;
            case 44:
                return true;
            case 45:
                return true;
            case 46:
                return true;
            case 47:
                return true;
            case 48:
                return true;
            case 49:
                return true;
            case 50:
                return true;
            default:
                return false;
        }
    }

}