package com.rs.game.player.content.interfaces.titles;

/**
 * @author Sagacity - https://www.rune-server.ee/members/sagacity/
 * Date: 25/08/2019
 */

public class Titles {

    public enum Title {
        TheCompletionist(0, "<col=FFFF00>the Completionist</col>", "This title is obtained when you meet the requirements for the completionist cape.",
                new String[][]{{"Have all completionist requirements fulfilled"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[0], 34),
        Maxed(1, "<col=580000>Maxed</col>", "maximizing all skills that exists",
                new String[][]{{"Have at least level 99 in all skills"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[1], 80),
        TheDefeater(2, "<col=00FFFF>the Defeater</col>", "This title is obtained when you killed Vorago.",
                new String[][]{{"Defeat Vorago at least once"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[2], 32),
        Sigil(3, "<col=33CC66>Sigil</col>", "This title is obtained when you have obtained the divine sigil as a drop.",
                new String[][]{{"Complete Sigil achievement"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[3], 813700),
        OfZamorak(4, "<col=993300>of Zamorak</col>", "This title is obtained when you have killed Zamorak 50 times.",
                new String[][]{{"Defeat K'ril Tsusaroth at least 50 times"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[4], 38),
        OfBandos(5, "<col=006600>of Bandos</col>", "This title is obtained when you have killed Bandos 50 times.",
                new String[][]{{"Defeat General Graardor at least 50 times"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[5], 35),
        OfSaradomin(6, "<col=0066CC>of Saradomin</col>", "This title is obtained when you have killed Saradomin 50 times.",
                new String[][]{{"Defeat Commander Zilyana at least 50 times"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[6], 36),
        OfArmadyl(7, "<col=6666FF>of Armadyl</col>", "This title is obtained when you have killed Armadyl 50 times.",
                new String[][]{{"Defeat Kree'arra at least 50 times"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[7], 37),
        OfChristmas(8, "<col=CC0000>of Christmas</col>", "This title is obtained when you have killed Bad Santa.",
                new String[][]{{"Defeat Bad Santa at least once"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[8], 56),
        TheDreamer(9, "<col=CC0099> The Dreamer </col>", "This title is obtained when you have chopped 1,000 dream logs.",
                new String[][]{{"Complete The Adventurer task"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[9], 33),
        FinalBoss(10, "<col=FF0000><shad=600000>Final Boss</shad></col>", "This title is obtained when you have completed the requirements.",
                new String[][]{{"Total of 4,000 boss kills"}, {"50 kills of each and every boss"}, {"Complete 50 of each raid"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[10], 66),
        //                new String[][]{{"Have at least level 99 in all skills"}, {"You need a total of 4,000 boss kills and atleast 50 in the following bosses;"}, {"Corporeal beast, KBD, QBD, Bork, Nex, Glacors, Rise of the Six, Bandos, Saradomin, Zamorak, Armadyl, WildyWyrm, Aquatic Wyrm, Demi Gods,"}, {"Vorago, AOD, Gazer, Bad Santa, Dryax, Hope Devourer, Kalphite King, Zulrah, Necrolord, Thunderous, Sunfreet, Anivia, Sliske, Giant Mole, 3 Amigo's,"}, {"Hydra, Celestia, KQ, Kraken, Sire, Cerberus, Sirenic, Callisto, Venenatis, Chaos Fanatic, Crazy Archaeologist, Scorpia, Vet'ion, Chambers of Xeric, Theatre of Blood, Skotizo & Lizardman!"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[10], 2000),
        InsaneBoss(11, "<col=F16F0F><shad=600000>Insane Final Boss</shad></col>", "completing all tasks, quests, maximizing skills and having bunch of " +
                "other requirements completed, check max for a full list",
                new String[][]{{"Total of 5,000 boss kills"}, {"100 kills of each and every boss"}, {"Complete 100 of each raid"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[11], 67),
        Warmonger(12, "<col=006666>Warmonger</col>", "This title is obtained when you have 1,500 boss kills.",
                new String[][]{{"Kill 1500 bosses"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[12], 22906),
        LieutenantCommander(13, "<col=800080>Lieutenant Commander</col>", "This title is obtained when you have 25 days playtime.",
                new String[][]{{"Obtain at least 25 days playtime"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[13], 22905),
        Sunkiller(14, "<col=D00000>Sun killer</col>", "This title is obtained when you have killed Sunfreet 1,000 times.",
                new String[][]{{"Kill Sunfreet at least 1000 times"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[14], 22904),
        DefenderOfHydrix(15, "<col=00FFFF><shad=FFFFFF>defender of Harmony</col></shad>", "This title is obtained when you have 15,000 boss kills.",
                new String[][]{{"Kill at least 15,000 bosses."}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[15], 204),
        TheThunderous(16, "<col=FFFF00><shad=FF00FF>the Thunderous</col></shad>", "This title is obtained when you have obtained it as a drop from Thunderous.",
                new String[][]{{"Kill Thunderous until the title drops"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[16], 55),
        TheLight(17, "<col=EEEA93>the Light</col>", "This title is obtained when you have looted 500 pillars of light.",
                new String[][]{{"Loot 500 pillars of light"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[17], 206),
        Mummified(18, "<col=58388D>Mummified</col>", "This title is obtained when you have killed 200 Mummies inside the Temple of Light.",
                new String[][]{{"Kill 200 mummies inside Temple of Light"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[18], 22907),
        XPMode(19, "Xp Mode Title", "This title is set based on your game/xp mode.",
                new String[][]{{""}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[19], 1610),
        Recycled(20, "<col=0FF1E7>Recycled</col>", "This title is obtained when you have obtained 300 Runecoins via the recycle centre.",
                new String[][]{{"Recycle 300 Runecoins worth of items"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[20], 22908),
        TheRegenerated(21, "<col=F1650F>the Regenerated</col>", "This title is obtained when you have obtained 1,500 Runecoins via the recycle centre.",
                new String[][]{{"Recycle 1500 Runecoins worth of items"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[21], 22909),
        EcoFriendly(22, "Eco-friendly", "This title is obtained when you have obtained 5,000 Runecoins via the recycle centre.",
                new String[][]{{"Recycle 5000 Runecoins worth of items"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[22], 22910),
        Biodegradable(23, "Biodegradable", "This title is obtained when you have obtained 7,500 Runecoins via the recycle centre.",
                new String[][]{{"Recycle 7500 Runecoins worth of items"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[23], 22911),
        TheToxic(24, "<col=42F10F><shad=FFFFFF>the Toxic</shad></col>", "This title is obtained when you have obtained 10,000 Runecoins via the recycle centre.",
                new String[][]{{"Recycle 10,000 Runecoins worth of items"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[24], 22912),
        ExperienceMaster(25, "<col=E622E9><shad=000000>Experience</col><col=22E92E> Master</shad></col>", "This title is obtained when you have 9,000,000,000 total experience.",
                new String[][]{{"Gain 9B experience"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[25], 22914),
        DefenderOfSliske(26, "<col=22E92E> Defender of Sliske", "This title is obtained when you have obtained it as a drop from Celestia, Defender of Sliske.",
                new String[][]{{"Kill Defender of Sliske until the title drops"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[26], 22915),
        TheEggCollector(27, "<col=582E2E>the Egg Collector</col>", "This title was obtained during the 2019 Easter event.",
                new String[][]{{"Complete the 2019 Easter event"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[27], 22918),
        TheBunnyKiller(28, "<col=fafed3><shad=5a4c3e>the Bunny Killer</shad></col>", "This title is obtained when you have obtained it as a drop from the Angry Easter Bunny.",
                new String[][]{{"Kill the Angry Easter Bunny until the title drops"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[28], 22919),
        RaidsMaster(29, "<col=6E2C00><shad=ffffff>Raids <col=B70FEB>master</shad></col>", "This title is obtained when you have completed 500 of each OSRS raid.",
                new String[][]{{"Complete 500 of each OSRS raid"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[29], 22920),
        TheBlood(30, "<col=ff0000>the Blood</shad></col>", "This title is obtained when you have completed 500 Theatre of Blood raids.",
                new String[][]{{"Complete 500 Theatre of Blood raids"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[30], 22921),
        OfTheChambers(31, "<col=84EB0F>of the Chambers</shad></col>", "This title is obtained when you have completed 500 Chambers of Xeric raids.",
                new String[][]{{"Complete 500 Chambers of Xeric raids"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[31], 22922),
        Raider(32, "<col=EB840F>Raider </shad></col>", "This title is obtained when you have completed 100 combined total OSRS raids.",
                new String[][]{{"Complete a total of 100 OSRS raids"}}, TitlesManager.TITLE_CLICKABLE_COMPONENTS[32], 22923),

        ;

        private int titleId;
        private String titleName;
        private String titleDescription;
        private String[][] titleRequirements;
        private int titleCompId;
        private int titleRealId;

        public int getTitleId() {
            return titleId;
        }

        public void setTitleId(int titleId) {
            this.titleId = titleId;
        }

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }

        public String getTitleDescription() {
            return titleDescription;
        }

        public void setTitleDescription(String titleDescription) {
            this.titleDescription = titleDescription;
        }

        public String[][] getTitleRequirements() {
            return titleRequirements;
        }

        public void setTitleRequirements(String[][] titleRequirements) {
            this.titleRequirements = titleRequirements;
        }


        public int getTitleCompId() {
            return titleCompId;
        }

        public void setTitleCompId(int titleCompId) {
            this.titleCompId = titleCompId;
        }

        Title(int titleId, String titleName, String titleDescription, String[][] titleRequirements, int titleCompId, int titleRealId) {
            this.titleId = titleId;
            this.titleName = titleName;
            this.titleDescription = titleDescription;
            this.titleRequirements = titleRequirements;
            this.titleCompId = titleCompId;
            this.titleRealId = titleRealId;
        }

        public int getTitleRealId() {
            return titleRealId;
        }

        public void setTitleRealId(int titleRealId) {
            this.titleRealId = titleRealId;
        }
    }
}