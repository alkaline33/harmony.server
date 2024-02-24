package com.rs.game.player.content;
 
import java.util.Random;

import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.utils.Utils;
 

public class TriviaBot {
     
	   
    private static String songs [][] = {
          {"I could stay awake, just to hear you breathin'", "I Don't Wanna Miss a Thing"},
          {"Oh Mama Mia, Mama Mia, Mama Mia let me go!", "Bohemian Rhapsody"},
          {"Lamborghini Mercy", "Mercy"},
          {"Let's go to the beach beach, let's go get away", "Starships"},
          {"I got sunshine, on a cloudy day", "My Girl"},
          {"Move to the sound of my body", "Hips Don't Lie"},
          {"Your poor little heart, will be all alone", "Runaway Baby"},
          {"Went the distance, now I'm back on my feet", "Eye of the Tiger"},
          {"Hey I just met you, and this is crazy", "Call Me Maybe"},
          {"Let's take this back to straight Hip-Hop, and start it from scratch", "Berzerk"},
          {"It goes one for the money, two for the show", "Go To Church"},
          {"Put your weary head to rest, don't you cry no more", "Carry On Wayward Son"},
          {"I got nine lives, cats eyes", "Back in Black"},
          {"Rejoice every time you hear the sound of my voice", "When I'm Gone"},
          {"My love, you'll never live without, I know you want me girl cause I can see you checking me out", "We Made You"},
          {"Brings me back sweet childhood memories", "Sweet Child O' Mine"},
          {"California, knows how to party", "California Love"},
          {"Today is gonna be the day that they're gonna throw it back to you", "Wonderwall"},
          {"I crashed my car into the bridge", "I Love It"},
          {"Ah whimo whep ah whimo whep", "In The Jungle"},
          {"And you can tell anybody, this is a song for you", "Your Song"},
          {"So I set the world on fire, we can burn brighter than the sun", "We Are Young"},
          {"I won't answer what you need answered", "I won Lucker"},
          {"I'm burning through the sky yeah! Two hundred degrees that's why they call me Mister Fahrenheit", "Don't stop me now"},
          {"You hear my voice, you hear that sound, like thunder gonna shake the ground", "Roar"},
          {"Are you ready, Are you ready for this, Are you hanging on the edge of your seat", "Another one bites the dust"},
          {"It's Raining Men! Hallelujah! It's Raining Men! Amen!", "It's raining men"},
          {"Snap back to reality, oh there goes gravity oh, there goes Rabbit", "Lose yourself"},
          {"I need a hero, I'm holding out for a hero 'til the end of the night", "Holding out for a hero"},
          {"I wake up everyday it's a daydream, everything in my life isn't what it seems, I wake up just to go back to sleep", "Bonkers"},
          {"Cause we throw bombs on it, throw bombs on it, just smash something, yeah mosh for me, hey yeah!", "Earthquake"},
          {"I don't like walking around this old and empty house, so hold my hand, I'll walk with you, my dear", "Little talks"},
          {"Wub wub wub wub woo bang wobble wibble", "A dubstep song"},
          {"Pack it up, pack it in, let me begin, I came to win battle me that's a sin", "Jump Around"},
          {"I've become so numb, I can't feel you there. Become so tired, so much more aware", "Numb"} };
     
    private static String puzzles [][] = {
              {"Figure out this anagram: 'hicacot'", "Chaotic"},
              {"Figure out this anagram: 'runtie'", "Runite"},
              {"Figure out this anagram: 'damanta'", "Adamant"},
              {"Figure out this anagram: 'lmhrtii'", "Mithril"},
              {"Figure out this anagram: 'tcas'", "cats"},
              {"Figure out this anagram: 'gdos'", "dogs"},
              {"Figure out this anagram: 'obom'", "boom"},
              {"Figure out this anagram: 'veto'", "Vote"},
              {"Figure out this anagram: 'grando'", "dragon"},
              {"Figure out this anagram: 'obesn'", "Bones"},
              {"Figure out this anagram: 'guerdoningene'", "Dungeoneering"},
              {"Figure out this anagram: 'osetirot'", "Tortoise"},
              {"Figure out this anagram: 'staminodiatrr'", "Administrator"},
              {"Figure out this anagram: 'edrootrma'", "Moderator"},
              {"Figure out this anagram: 'troupsp'", "Support"},
              {"Figure out this anagram: 'yaronhm'", "Harmony"},
              {"Figure out this anagram: 'godeyrr'", "Drygore"},
              {"Figure out this anagram: 'rotonda'", "Donator"},
              {"Figure out this anagram: 'xmtreee'", "Extreme"},
              {"Figure out this anagram: 'aygreenld'", "Legendary"},
              {"Figure out this anagram: 'rocksalti'", "Rocktails"}};
     
    private static String server [][] = {
          {"What is the Owners Real Name?", "Connor"},
          {"What was the first obsidian weapon Jagex released?", "Dark Dagger"},
          {"What grants you the Fire Cape?", "Fight Caves"},
          {"What is the maximum total level you can achieve?", "2496"},
          {"What gaming genre is Runescape?", "MMORPG"},
          {"What is the max skill cap?", "99"},
          {"How many portals are there at clan wars?", "3"},
          {"What weapon is a Tormented Demon's weakness?", "Darklight"},
          {"How many barrows brothers were there originally?", "6"},
          {"What is the first ancient spell?", "Smoke Rush"},
          {"What is the most powerful curse?", "Turmoil"},
          {"How much of a percentage does a dragon dagger special require?", "25%"},
          {"How much XP is required to achieve 120 Dungeoneering?", "104m"},
          {"What do you receive when a fire disappears?", "Ashes"},
          {"What is the name of the kiln cape?", "TokHaar-Kal"},
          {"What is the maximum amount of XP you can gain per skill?", "360m"},
          {"What colours are on the Saradomin Flag? (3 Colours)", "Blue White Yellow"},
          {"What colours are on the Zamorak Flag? (2 Colours)", "Black Red"},
          {"What colour is the Guthix Flag?", "Green"},
          {"What prayer Level do you need to use Steel Skin?", "28"},
          {"What is the Maxed level for Runescape in Evolution of Combat?", "138"},
          {"What is King Black Dragons Combat Level", "276"},
          {"What is the Maxed level for Runescape before Evolution of Combat?", "138"},
          {"What attack level do you need to use an iron scimitar in Evolution of Combat?", "10"},
          {"At what mining level can you mine Runite Ore?", "85"},
          {"At what mining level can you mine Mithril Ore?", "55"},
          {"At what mining level can you mine Adamantite Ore?", "70"},
          {"At what mining level can you mine Gold Ore?", "40"},
          {"At what fishing level can you fish Lobster?", "40"},
          {"At what fishing level can you fish swordfish?", "50"},
          {"At what fishing level can you fish Tuna?", "35"},
          {"What tool do you need to fish lobsters?", "Lobster Pot"},
          {"What tool do you need to fish tuna?", "Harpoon"},
          {"What tool do you need to fish swordfish?", "Harpoon"},
          {"What is the strongest Dungeoneering weapon type?", "Primal"},
          {"What woodcutting level do you need to cut magic logs?", "75"},
          {"What cooking level do you need to cook Rocktails?", "93"},
          {"What Smithing level do you need to smith rune Warhammers?", "94"},
          {"What bones give the most XP per bury?", "Reinforced Dragon Bones"},
          {"What Summoning level do you need to use the TzRek Jad?", "99"},
          {"What Summoning level do you need to summon the Fruit bat?", "69"},
          {"What Slayer level do you need to fight Ganodermic beasts?", "95"},
          {"What Fishing level do you need to fish manta rays?", "81"},
          {"What Fletching level do you need to fletch Magic Shortbows?", "80"},
          {"What Agility level do you need for advanced Barbarian outpost?", "90"},
          {"What emote uses a cannon?", "Chaotic Cookery"},
          {"What's the first four digits of a max stack?", "2147"},
          {"What's all the digits of a max stack?", "2147483647"},
          {"What Prayer level do you need for Turmoil?", "95"},
          {"What does AGS stand for?", "Armadyl Godsword"},
          {"What does BGS stand for?", "Bandos Godsword"},
          {"What does SGS stand for?", "Saradomin Godsword"},
          {"What does ZGS stand for?", "Zamorak Godsword"},
          {"What's the strongest spirit shield type?", "Divine"},
          {"What's the Highest Slayer Master?", "Kuradal"},
          {"What does SOF stand for?", "Squeal of Fortune"},
          {"What weapon one hits almost anything?", "Deathtouched Dart"},
          {"What aura gives you red wings?", "Corruption"},
          {"What aura gives you white wings?", "Salvation"},
          {"What aura gives you green wings?", "Harmony"},
          {"What color charm gives you the most XP?", "Blue"},
          {"What shield does General Graador Drop?", "Bandos Warshield"},
          {"What currency is used to buy Chaotics?", "Dungeoneering Points"},
          {"How much Mill do you need to make your cash turn Green?", "10m"},
          {"What potion blocks dragonfire breath?", "Antifire Potion"},
          {"What is maximum combat level in Harmony?", "138"},
          {"What's the hardest skill to get max level in?", "Dungeoneering"},
          {"What Rank is a gold Crown?", "Administrator"},
          {"What Rank is a Silver Crown?", "Moderator"},
          {"What Rank is an i?", "Support"},
          {"What colour is Regular Donator?", "Green"},
          {"What colour is Extreme Donator?", "Red"},
          {"What colour is Legendary Donator?", "Yellow"},
          {"What colour is Ultimate Donator?", "White"},
          {"What colour is VIP Donator?", "Purple"},
          {"What colour is Sponsor Donator?", "Rainbow"},
          {"What is the name of this server?", "Harmony"},
          {"What points do you get from voting?", "Vote points"},
         // {"What is the highest Clan Level?", "25"},
          {"How many Dungeoneering points are needed to buy a Chaotic?", "200k"}};
     
     
    private static String movies [][] = {
          {"Oh no, Leonardo is sinking!", "Titanic"},
          {"Pie, Boobs, and more!", "American Pie"},
		  {"I'ma take you on a ride along..", "The Ride Along"},
          {"Retired and Extremely Dangerous.", "RED"},
          {"Oh no, it's Jason with a machete!", "Friday the 13th"},
          {"You don't want to swim in Cape Cod anymore.", "Jaws"},
          {"So after all this, I'm the missing patient?", "Shutter Island"},
          {"Did you touch my drumset?", "Step Brothers"},
          {"The greatest parody including the movie Narnia.", "Epic Movie"},
          {"The series of scary movie parodies.", "Scary Movie"},
          {"Too fast for you?", "Fast and Furious"},
          {"The world is ending!", "2012"},
		  {"I'M ON FIRE!!!, You're not on fire!", "Talladega Nights"},
		  {"Adriannnnn ", "Rocky"},
          {"Good old James Bond.", "007"} };
    
  
     
    private static String categories [][][] = { songs, puzzles, server, movies};
    private static int catId;
    public static int questionid = -1;
    public static int round = 0;
    public static boolean victory = false;
    public static int answers = 0;
 
    public TriviaBot() {
        //TODO
    }
     
    public static void Run() {
        catId = Utils.random(0, 4);
        int rand = RandomQuestion(catId);
        questionid = rand;
        answers = 0;
        victory = false;
        String title = "Harmony Trivia";
        if (catId == 0) {
			title = "Name the Song";
		} else if (catId == 1) {
			title = "Puzzles";
		} else if (catId == 2) {
			title = "Runescape/Harmony";
		} else if (catId == 3) {
			title = "General Trivia";
		} else if (catId == 4) {
			title = "Name the Movie";
		}
        for(Player participant : World.getPlayers()) {
            if(participant == null) {
				continue;
			}
               participant.hasAnswered = false;
                participant.getPackets().sendGameMessage("[<col=56A5EC>"+title+"</col>] - <col=56A5EC>"+categories[catId][rand][0]+"</col>");
        }
    }
     
    public static void sendRoundWinner(String winner, Player player) {
    	String title = "Harmony Trivia";
        if (answers < 5) {
			answers++;
		}
	    if (answers >= 1) {
			victory = true;
		}
	    player.tAnswers++;
	    player.setTriviaPoints(player.getTriviaPoints() + 1);
	    player.getPackets().sendGameMessage("[<col=56A5EC>"+ title + "</col>] - <col=56A5EC>"+winner+", you now have "+player.getTriviaPoints()+" Trivia Points.</col>");
	    player.hasAnswered = true;
	    World.sendWorldMessage("[<col=56A5EC>Trivia Winner</col>] - "+ winner +"</col><col=56A5EC> answered the question correctly!</col>", false);
	    return;
    }
     
    public static void verifyAnswer(final Player player, String answer) {
        if(victory) {
            player.getPackets().sendGameMessage("That round has already been won, wait for the next round.");
        } else if (player.hasAnswered) {
            player.getPackets().sendGameMessage("You have already answered this question.");
        } else if(categories[catId][questionid][1].equalsIgnoreCase(answer)) {
        //	SeasonEvent.HandleActivity(player, "Trivia", 0);
        	round++;
            sendRoundWinner(player.getDisplayName(), player);
        } else {
            player.getPackets().sendGameMessage("That answer wasn't correct, please try it again.");
        }
    }
     
    public static int RandomQuestion(int i) {
        int random = 0;
        Random rand = new Random();
        random = rand.nextInt(categories[i].length);
        return random;
    }
     
}