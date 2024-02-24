package com.rs.game.player.interfaces;

import com.rs.Settings;
import com.rs.game.player.Player;
import com.rs.utils.Colors;

/**
 * 
 * @author Mr_Joopz
 *
 */

public class PetClaimInterface {

	private static final int containerKey = 100;
	
	private static int INTERFACE_ID = 3017;

	public static void OpenInterface(Player player) {
		player.getInterfaceManager().sendInterface(INTERFACE_ID);
		player.petclaimcomponent = 1;
		player.petclaimpage = 1;
		/**
		 * Pet name & Description
		 */
		player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Lil Thunderous");
		player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Thunderous.<br><col=ff0000>Rate: 1/1000.</col>");
		/**
		 * Claim button
		 */
		player.getPackets().sendIComponentText(INTERFACE_ID, 66, "Claim");
		/**
		 * Scroll Pet Names - (Boss)
		 */
		player.getPackets().sendIComponentText(INTERFACE_ID, 24, ""+(player.gotthunderouspet ? "<col=00ff00>" : "<col=ff0000>") + "Lil Thunderous");
		player.getPackets().sendIComponentText(INTERFACE_ID, 25, ""+(player.gotarmadylpet ? "<col=00ff00>" : "<col=ff0000>") + "Arma");
		player.getPackets().sendIComponentText(INTERFACE_ID, 26, ""+(player.gotbandospet ? "<col=00ff00>" : "<col=ff0000>") + "Bandi");
		player.getPackets().sendIComponentText(INTERFACE_ID, 27, ""+(player.gotsaradominpet ? "<col=00ff00>" : "<col=ff0000>") + "Sara");
		player.getPackets().sendIComponentText(INTERFACE_ID, 28, ""+(player.gotzamorakpet ? "<col=00ff00>" : "<col=ff0000>") + "Zammi");
		player.getPackets().sendIComponentText(INTERFACE_ID, 29, ""+(player.gotbadsantapet ? "<col=00ff00>" : "<col=ff0000>") + "Snowman");
		player.getPackets().sendIComponentText(INTERFACE_ID, 30, ""+(player.gotcorppet ? "<col=00ff00>" : "<col=ff0000>") + "Corpie");
		player.getPackets().sendIComponentText(INTERFACE_ID, 31, ""+(player.gotdryaxpet ? "<col=00ff00>" : "<col=ff0000>") + "Dryax Junior");
		player.getPackets().sendIComponentText(INTERFACE_ID, 32, ""+(player.gotdrygonpet ? "<col=00ff00>" : "<col=ff0000>") + "Young Devourer");
		player.getPackets().sendIComponentText(INTERFACE_ID, 33, ""+(player.gotglacorpet ? "<col=00ff00>" : "<col=ff0000>") + "Glacey");
		player.getPackets().sendIComponentText(INTERFACE_ID, 34, ""+(player.gotkbdpet ? "<col=00ff00>" : "<col=ff0000>") + "Kingy");
		player.getPackets().sendIComponentText(INTERFACE_ID, 35, ""+(player.gotnexpet ? "<col=00ff00>" : "<col=ff0000>") + "Nexy");
		player.getPackets().sendIComponentText(INTERFACE_ID, 36, ""+(player.gotvoragopet ? "<col=00ff00>" : "<col=ff0000>") + "Voragi");
		player.getPackets().sendIComponentText(INTERFACE_ID, 37, ""+(player.gotsunfreetpet ? "<col=00ff00>" : "<col=ff0000>") + "Sunny");
		player.getPackets().sendIComponentText(INTERFACE_ID, 38, ""+(player.gotpdemonpet ? "<col=00ff00>" : "<col=ff0000>") + "Disco Demon");
		player.getPackets().sendIComponentText(INTERFACE_ID, 39, ""+(player.gotawyrmpet ? "<col=00ff00>" : "<col=ff0000>") + "Aqua Wyrm");
		player.getPackets().sendIComponentText(INTERFACE_ID, 40, ""+(player.gotsmokedevilpet ? "<col=00ff00>" : "<col=ff0000>") + "Pet Smoke Devil");
		player.getPackets().sendIComponentText(INTERFACE_ID, 41, ""+(player.gotsirepet ? "<col=00ff00>" : "<col=ff0000>") + "Abyssal Orphan");
		player.getPackets().sendIComponentText(INTERFACE_ID, 42, ""+(player.gotolmpet ? "<col=00ff00>" : "<col=ff0000>") + "Olmlet");
		player.getPackets().sendIComponentText(INTERFACE_ID, 43, ""+(player.gotcerberuspet ? "<col=00ff00>" : "<col=ff0000>") + "Hellpuppy");
		player.getPackets().sendIComponentText(INTERFACE_ID, 44, ""+(player.gotcallistopet ? "<col=00ff00>" : "<col=ff0000>") + "Callisto Cub");
		player.getPackets().sendIComponentText(INTERFACE_ID, 45, ""+(player.gotbloodpet ? "<col=00ff00>" : "<col=ff0000>") + "Young Beast");
		player.getPackets().sendIComponentText(INTERFACE_ID, 46, ""+(player.gotskotizopet? "<col=00ff00>" : "<col=ff0000>") + "Skotos");
		player.getPackets().sendIComponentText(INTERFACE_ID, 47, ""+(player.gotvenenatispet ? "<col=00ff00>" : "<col=ff0000>") + "Venenatis");
		player.getPackets().sendIComponentText(INTERFACE_ID, 48, ""+(player.gotscorpiapet ? "<col=00ff00>" : "<col=ff0000>") + "Scorpia");
		player.getPackets().sendIComponentText(INTERFACE_ID, 49, ""+(player.gotvetionpet ? "<col=00ff00>" : "<col=ff0000>") + "Vet'ion Jr");
		player.getPackets().sendIComponentText(INTERFACE_ID, 50, ""+(player.gotballakpet ? "<col=00ff00>" : "<col=ff0000>") + "Bally");
		player.getPackets().sendIComponentText(INTERFACE_ID, 51, ""+(player.gotassassinpet ? "<col=00ff00>" : "<col=ff0000>") + "the Enforcer");
		player.getPackets().sendIComponentText(INTERFACE_ID, 52, ""+(player.gotvorkipet ? "<col=00ff00>" : "<col=ff0000>") + "Vorki");
		player.getPackets().sendIComponentText(INTERFACE_ID, 53, ""+(player.gothydrapet ? "<col=00ff00>" : "<col=ff0000>") + "Ikkle Hydra");
		player.getPackets().sendIComponentText(INTERFACE_ID, 54, ""+(player.gotcelestiapet ? "<col=00ff00>" : "<col=ff0000>") + "SkeeSkee");
		player.getPackets().sendIComponentText(INTERFACE_ID, 55, ""+(player.gotzulrahpet ? "<col=00ff00>" : "<col=ff0000>") + "Snakeling");
		player.getPackets().sendIComponentText(INTERFACE_ID, 56, ""+(player.gotkrakenpet ? "<col=00ff00>" : "<col=ff0000>") + "Kraken");
		player.getPackets().sendIComponentText(INTERFACE_ID, 68, ""+(player.gotgmolepet ? "<col=00ff00>" : "<col=ff0000>") + "Baby Mole");
		player.getPackets().sendIComponentText(INTERFACE_ID, 69, ""+(player.gotraptorpet ? "<col=00ff00>" : "<col=ff0000>") + "Mini Raptor");
		player.getPackets().sendIComponentText(INTERFACE_ID, 70, ""+(player.gotamigopet ? "<col=00ff00>" : "<col=ff0000>") + "Mini Amigo's");
		//NEED TO ADD GREEN/RED IF UNLOCKED + CLAIM BUTTON
	}
	
	public static void HandleButtonsPage3(Player player, int componentId) {
		switch (componentId) {
		case 17:
			OpenInterface(player);
			break;
		case 65:
			HandleCreation(player);
			break;
			/**
			 * Misc Pets
			 */
		case 21:
			player.petclaimcomponent = 41;
			player.petclaimpage = 3;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Dusty");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained from the Slayer store.</col>");
			/**
			 * Claim button
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 66, "Claim");
			/**
			 * Scroll Pet Names - (Boss)
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 24, ""+(player.gotdustypet ? "<col=00ff00>" : "<col=ff0000>") + "Dusty");
			player.getPackets().sendIComponentText(INTERFACE_ID, 25, ""+(player.gotedipet ? "<col=00ff00>" : "<col=ff0000>") + "Edi");
			player.getPackets().sendIComponentText(INTERFACE_ID, 26, ""+(player.gotfreezypet ? "<col=00ff00>" : "<col=ff0000>") + "Freezy");
			player.getPackets().sendIComponentText(INTERFACE_ID, 27, ""+(player.gotidragpet ? "<col=00ff00>" : "<col=ff0000>") + "Iron Dragon");
			player.getPackets().sendIComponentText(INTERFACE_ID, 28, ""+(player.gotsdragpet ? "<col=00ff00>" : "<col=ff0000>") + "Steel Dragon");
			player.getPackets().sendIComponentText(INTERFACE_ID, 29, ""+(player.gotpugpet ? "<col=00ff00>" : "<col=ff0000>") + "Pup");
			player.getPackets().sendIComponentText(INTERFACE_ID, 30, ""+(player.cluescompleted >= 500 ? "<col=00ff00>" : "<col=ff0000>") + "Bloodhound");
			player.getPackets().sendIComponentText(INTERFACE_ID, 31, ""+(player.gotdarkfeastpet ? "<col=00ff00>" : "<col=ff0000>") + "Dark Feast");
			player.getPackets().sendIComponentText(INTERFACE_ID, 32, ""+(player.gotfrostypet ? "<col=00ff00>" : "<col=ff0000>") + "Frosty");
			player.getPackets().sendIComponentText(INTERFACE_ID, 33, ""+(player.gotgargpet ? "<col=00ff00>" : "<col=ff0000>") + "Garg");
			player.getPackets().sendIComponentText(INTERFACE_ID, 34, ""+(player.gotcorruptveracpet ? "<col=00ff00>" : "<col=ff0000>") + "Corrupted Verac");
			player.getPackets().sendIComponentText(INTERFACE_ID, 35, ""+(player.gotcorruptkarilpet ? "<col=00ff00>" : "<col=ff0000>") + "Corrupted Karil");
			player.getPackets().sendIComponentText(INTERFACE_ID, 36, ""+(player.gotcorruptahrimpet ? "<col=00ff00>" : "<col=ff0000>") + "Corrupted Ahrim");
			player.getPackets().sendIComponentText(INTERFACE_ID, 37, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 38, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 39, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 40, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 41, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 42, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 43, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 44, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 45, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 46, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 47, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 48, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 49, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 50, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 51, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 52, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 53, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 54, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 55, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 56, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 68, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 69, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 70, "");
			break;
			
			/**
			 * Skilling pets
			 */
		case 19:
			player.petclaimcomponent = 35;
			player.petclaimpage = 2;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Fishing");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst Fishing.</col>");
			/**
			 * Claim button
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 66, "Claim");
			/**
			 * Scroll Pet Names - (Boss)
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 24, ""+(player.fishingpet ? "<col=00ff00>" : "<col=ff0000>") + "Fishing pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 25, ""+(player.firemakingpet ? "<col=00ff00>" : "<col=ff0000>") + "Firemaking pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 26, ""+(player.cookingpet ? "<col=00ff00>" : "<col=ff0000>") + "Cooking pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 27, ""+(player.miningpet ? "<col=00ff00>" : "<col=ff0000>") + "Mining pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 28, ""+(player.woodcutpet ? "<col=00ff00>" : "<col=ff0000>") + "Woodcutting pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 29, ""+(player.hunterpet ? "<col=00ff00>" : "<col=ff0000>") + "Hunter pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 30, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 31, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 32, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 33, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 34, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 35, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 36, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 37, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 38, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 39, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 40, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 41, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 42, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 43, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 44, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 45, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 46, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 47, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 48, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 49, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 50, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 51, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 52, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 53, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 54, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 55, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 56, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 68, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 69, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 70, "");
			break;
			
		case 24:
			player.petclaimcomponent = 41;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Dusty");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained from the Slayer store.");
			break;
		case 25:
			player.petclaimcomponent = 42;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Edi");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained from the Slayer store.");
			break;
		case 26:
			player.petclaimcomponent = 43;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Freezy");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained from the Slayer store.");
			break;
		case 27:
			player.petclaimcomponent = 44;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Iron Dragon");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained from the Slayer store.");
			break;
		case 28:
			player.petclaimcomponent = 45;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Steel Dragon");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained from the Slayer store.");
			break;
		case 29:
			player.petclaimcomponent = 46;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Pug");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained from the Slayer store.");
			break;
		case 30:
			player.petclaimcomponent = 47;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Bloodhound");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained after completing 500 clue scrolls.");
			break;
		case 31:
			player.petclaimcomponent = 48;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Dark Feast");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Dark Beast. <col=ff0000>Rate: 1/1000.</col>");
			break;
		case 32:
			player.petclaimcomponent = 49;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Frosty");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Frost Dragons. <col=ff0000>Rate: 1/1000.</col>");
			break;
		case 33:
			player.petclaimcomponent = 50;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Garg");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Gargoyle. <col=ff0000>Rate: 1/1000.</col>");
			break;
		case 34:
			player.petclaimcomponent = 51;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Corrupted Verac");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Corrupted Verac. <col=ff0000>Rate: 1/2000.</col>");
			break;
		case 35:
			player.petclaimcomponent = 52;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Corrupted Karil");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Corrupted Karil. <col=ff0000>Rate: 1/2000.</col>");
			break;
		case 36:
			player.petclaimcomponent = 53;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Corrupted Ahrim");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Corrupted Ahrim. <col=ff0000>Rate: 1/2000.</col>");
			break;
		}
	}
	
	public static void HandleButtonsPage2(Player player, int componentId) {
		switch (componentId) {
		case 17:
			OpenInterface(player);
			break;
		case 65:
			HandleCreation(player);
			break;
			/**
			 * Misc Pets
			 */
		case 21:
			player.petclaimcomponent = 41;
			player.petclaimpage = 3;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Dusty");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained from the Slayer store.</col>");
			/**
			 * Claim button
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 66, "Claim");
			/**
			 * Scroll Pet Names - (Boss)
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 24, ""+(player.gotdustypet ? "<col=00ff00>" : "<col=ff0000>") + "Dusty");
			player.getPackets().sendIComponentText(INTERFACE_ID, 25, ""+(player.gotedipet ? "<col=00ff00>" : "<col=ff0000>") + "Edi");
			player.getPackets().sendIComponentText(INTERFACE_ID, 26, ""+(player.gotfreezypet ? "<col=00ff00>" : "<col=ff0000>") + "Freezy");
			player.getPackets().sendIComponentText(INTERFACE_ID, 27, ""+(player.gotidragpet ? "<col=00ff00>" : "<col=ff0000>") + "Iron Dragon");
			player.getPackets().sendIComponentText(INTERFACE_ID, 28, ""+(player.gotsdragpet ? "<col=00ff00>" : "<col=ff0000>") + "Steel Dragon");
			player.getPackets().sendIComponentText(INTERFACE_ID, 29, ""+(player.gotpugpet ? "<col=00ff00>" : "<col=ff0000>") + "Pup");
			player.getPackets().sendIComponentText(INTERFACE_ID, 30, ""+(player.cluescompleted >= 500 ? "<col=00ff00>" : "<col=ff0000>") + "Bloodhound");
			player.getPackets().sendIComponentText(INTERFACE_ID, 31, ""+(player.gotdarkfeastpet ? "<col=00ff00>" : "<col=ff0000>") + "Dark Feast");
			player.getPackets().sendIComponentText(INTERFACE_ID, 32, ""+(player.gotfrostypet ? "<col=00ff00>" : "<col=ff0000>") + "Frosty");
			player.getPackets().sendIComponentText(INTERFACE_ID, 33, ""+(player.gotgargpet ? "<col=00ff00>" : "<col=ff0000>") + "Garg");
			player.getPackets().sendIComponentText(INTERFACE_ID, 34, ""+(player.gotcorruptveracpet ? "<col=00ff00>" : "<col=ff0000>") + "Corrupted Verac");
			player.getPackets().sendIComponentText(INTERFACE_ID, 35, ""+(player.gotcorruptkarilpet ? "<col=00ff00>" : "<col=ff0000>") + "Corrupted Karil");
			player.getPackets().sendIComponentText(INTERFACE_ID, 36, ""+(player.gotcorruptahrimpet ? "<col=00ff00>" : "<col=ff0000>") + "Corrupted Ahrim");
			player.getPackets().sendIComponentText(INTERFACE_ID, 37, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 38, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 39, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 40, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 41, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 42, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 43, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 44, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 45, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 46, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 47, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 48, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 49, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 50, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 51, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 52, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 53, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 54, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 55, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 56, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 68, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 69, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 70, "");
			break;
			/**
			 * Skilling pets
			 */
		case 19:
			player.petclaimcomponent = 35;
			player.petclaimpage = 2;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Fishing");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst Fishing.</col>");
			/**
			 * Claim button
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 66, "Claim");
			/**
			 * Scroll Pet Names - (Boss)
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 24, ""+(player.fishingpet ? "<col=00ff00>" : "<col=ff0000>") + "Fishing pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 25, ""+(player.firemakingpet ? "<col=00ff00>" : "<col=ff0000>") + "Firemaking pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 26, ""+(player.cookingpet ? "<col=00ff00>" : "<col=ff0000>") + "Cooking pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 27, ""+(player.miningpet ? "<col=00ff00>" : "<col=ff0000>") + "Mining pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 28, ""+(player.woodcutpet ? "<col=00ff00>" : "<col=ff0000>") + "Woodcutting pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 29, ""+(player.hunterpet ? "<col=00ff00>" : "<col=ff0000>") + "Hunter pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 30, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 31, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 32, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 33, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 34, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 35, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 36, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 37, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 38, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 39, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 40, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 41, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 42, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 43, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 44, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 45, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 46, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 47, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 48, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 49, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 50, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 51, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 52, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 53, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 54, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 55, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 56, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 68, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 69, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 70, "");
			break;
		case 24:
			player.petclaimcomponent = 35;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Fishing pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst Fishing.");
			break;
		case 25:
			player.petclaimcomponent = 36;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Firemaking pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst Firemaking.");
			break;
		case 26:
			player.petclaimcomponent = 37;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Cooking pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst Cooking.");
			break;
		case 27:
			player.petclaimcomponent = 38;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Mining pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst Mining.");
			break;
		case 28:
			player.petclaimcomponent = 39;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Woodcutting pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst Woodcutting.");
			break;
		case 29:
			player.petclaimcomponent = 40;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Hunter pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst Hunting.");
			break;
		
		}
	}

	public static void HandleButtonsPage1(Player player, int componentId) {
		//System.out.println(componentId);
		switch (componentId) {
		case 17:
			OpenInterface(player);
			break;
		case 65:
			HandleCreation(player);
			break;
			/**
			 * Misc Pets
			 */
		case 21:
			player.petclaimcomponent = 41;
			player.petclaimpage = 3;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Dusty");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained from the Slayer store.</col>");
			/**
			 * Claim button
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 66, "Claim");
			/**
			 * Scroll Pet Names - (Boss)
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 24, ""+(player.gotdustypet ? "<col=00ff00>" : "<col=ff0000>") + "Dusty");
			player.getPackets().sendIComponentText(INTERFACE_ID, 25, ""+(player.gotedipet ? "<col=00ff00>" : "<col=ff0000>") + "Edi");
			player.getPackets().sendIComponentText(INTERFACE_ID, 26, ""+(player.gotfreezypet ? "<col=00ff00>" : "<col=ff0000>") + "Freezy");
			player.getPackets().sendIComponentText(INTERFACE_ID, 27, ""+(player.gotidragpet ? "<col=00ff00>" : "<col=ff0000>") + "Iron Dragon");
			player.getPackets().sendIComponentText(INTERFACE_ID, 28, ""+(player.gotsdragpet ? "<col=00ff00>" : "<col=ff0000>") + "Steel Dragon");
			player.getPackets().sendIComponentText(INTERFACE_ID, 29, ""+(player.gotpugpet ? "<col=00ff00>" : "<col=ff0000>") + "Pup");
			player.getPackets().sendIComponentText(INTERFACE_ID, 30, ""+(player.cluescompleted >= 500 ? "<col=00ff00>" : "<col=ff0000>") + "Bloodhound");
			player.getPackets().sendIComponentText(INTERFACE_ID, 31, ""+(player.gotdarkfeastpet ? "<col=00ff00>" : "<col=ff0000>") + "Dark Feast");
			player.getPackets().sendIComponentText(INTERFACE_ID, 32, ""+(player.gotfrostypet ? "<col=00ff00>" : "<col=ff0000>") + "Frosty");
			player.getPackets().sendIComponentText(INTERFACE_ID, 33, ""+(player.gotgargpet ? "<col=00ff00>" : "<col=ff0000>") + "Garg");
			player.getPackets().sendIComponentText(INTERFACE_ID, 34, ""+(player.gotcorruptveracpet ? "<col=00ff00>" : "<col=ff0000>") + "Corrupted Verac");
			player.getPackets().sendIComponentText(INTERFACE_ID, 35, ""+(player.gotcorruptkarilpet ? "<col=00ff00>" : "<col=ff0000>") + "Corrupted Karil");
			player.getPackets().sendIComponentText(INTERFACE_ID, 36, ""+(player.gotcorruptahrimpet ? "<col=00ff00>" : "<col=ff0000>") + "Corrupted Ahrim");
			player.getPackets().sendIComponentText(INTERFACE_ID, 37, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 38, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 39, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 40, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 41, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 42, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 43, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 44, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 45, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 46, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 47, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 48, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 49, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 50, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 51, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 52, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 53, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 54, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 55, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 56, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 68, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 69, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 70, "");
			break;
			
			/**
			 * Skilling pets
			 */
		case 19:
			player.petclaimcomponent = 35;
			player.petclaimpage = 2;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Fishing");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst Fishing.</col>");
			/**
			 * Claim button
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 66, "Claim");
			/**
			 * Scroll Pet Names - (Boss)
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 24, ""+(player.fishingpet ? "<col=00ff00>" : "<col=ff0000>") + "Fishing pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 25, ""+(player.firemakingpet ? "<col=00ff00>" : "<col=ff0000>") + "Firemaking pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 26, ""+(player.cookingpet ? "<col=00ff00>" : "<col=ff0000>") + "Cooking pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 27, ""+(player.miningpet ? "<col=00ff00>" : "<col=ff0000>") + "Mining pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 28, ""+(player.woodcutpet ? "<col=00ff00>" : "<col=ff0000>") + "Woodcutting pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 29, ""+(player.hunterpet ? "<col=00ff00>" : "<col=ff0000>") + "Hunter pet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 30, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 31, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 32, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 33, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 34, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 35, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 36, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 37, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 38, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 39, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 40, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 41, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 42, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 43, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 44, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 45, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 46, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 47, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 48, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 49, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 50, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 51, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 52, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 53, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 54, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 55, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 56, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 68, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 69, "");
			player.getPackets().sendIComponentText(INTERFACE_ID, 70, "");
			break;
		case 24:
			player.petclaimcomponent = 1;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Lil Thunderous");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Thunderous.<br><col=ff0000>Rate: 1/1000.</col>");
			break;
		case 25:
			player.petclaimcomponent = 2;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Arma");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Kree'Arra.<br><col=ff0000>Rate: 1/800.</col>");
			break;
		case 26:
			player.petclaimcomponent = 3;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Bandi");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing General Graardor.<br><col=ff0000>Rate: 1/800.</col>");
			break;
		case 27:
			player.petclaimcomponent = 4;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Sara");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Commander Zilyana.<br><col=ff0000>Rate: 1/800.</col>");
			break;
		case 28:
			player.petclaimcomponent = 5;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Zammi");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing K'ril Tsutsaroth.<br><col=ff0000>Rate: 1/800.</col>");
			break;
		case 29:
			player.petclaimcomponent = 6;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Snowman");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Bad Santa.<br><col=ff0000>Rate: 1/5000.</col>");
			break;
		case 30:
			player.petclaimcomponent = 7;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Corpie");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Corporeal Beast.<br><col=ff0000>Rate: 1/600.</col>");
			break;
		case 31:
			player.petclaimcomponent = 8;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Dryax Junior");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Dryax.<br><col=ff0000>Rate: 1/1200.</col>");
			break;
		case 32:
			player.petclaimcomponent = 9;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Young Devourer");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Hope Devourer.<br><col=ff0000>Rate: 1/400.</col>");
			break;
		case 33:
			player.petclaimcomponent = 10;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Glacey");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Glacor's.<br><col=ff0000>Rate: 1/1000.</col>");
			break;
		case 34:
			player.petclaimcomponent = 11;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Kingy");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing King Black Dragon.<br><col=ff0000>Rate: 1/500.</col>");
			break;
		case 35:
			player.petclaimcomponent = 12;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Nexy");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Nex.<br><col=ff0000>Rate: 1/250.</col>");
			break;
		case 36:
			player.petclaimcomponent = 13;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Voragi");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Vorago.<br><col=ff0000>Rate: 1/150.</col>");
			break;
		case 37:
			player.petclaimcomponent = 14;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Sunny");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Sunfreet.<br><col=ff0000>Rate: 1/400.</col>");
			break;
		case 38:
			player.petclaimcomponent = 15;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Disco Demon");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained from a Party Demon chest.<br><col=ff0000>Rate: 1/50.</col>");
			break;
		case 39:
			player.petclaimcomponent = 16;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Aqua Wyrm");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Aquatic Wyrm.<br><col=ff0000>Rate: 1/800.</col>");
			break;
		case 40:
			player.petclaimcomponent = 17;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Pet Smoke Devil");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Thermonuclear Smoke Devil.<br><col=ff0000>Rate: 1/1000.</col>");
			break;
		case 41:
			player.petclaimcomponent = 18;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Abyssal Orphan");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Abyssal Sire.<br><col=ff0000>Rate: 1/1000.</col>");
			break;
		case 42:
			player.petclaimcomponent = 19;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Olmlet");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Tekton.<br><col=ff0000>Rate: 1/65.</col>");
			break;
		case 43:
			player.petclaimcomponent = 20;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Hellpuppy");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Cerberus.<br><col=ff0000>Rate: 1/750.</col>");
			break;
		case 44:
			player.petclaimcomponent = 21;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Callisto Cub");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Callisto.<br><col=ff0000>Rate: 1/750.</col>");
			break;
		case 45:
			player.petclaimcomponent = 22;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Young Beast");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Blood Beast.<br><col=ff0000>Rate: 1/65.</col>");
			break;
		case 46:
			player.petclaimcomponent = 23;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Skotos");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Skotizo.<br><col=ff0000>Rate: 1/65.</col>");
			break;
		case 47:
			player.petclaimcomponent = 24;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Venenatis Spiderling");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Venenatis.<br><col=ff0000>Rate: 1/750.</col>");
			break;
		case 48:
			player.petclaimcomponent = 25;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Scorpia's Offspring");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Scorpia.<br><col=ff0000>Rate: 1/750.</col>");
			break;
		case 49:
			player.petclaimcomponent = 26;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Vet'ion Jr");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Vet'ion.<br><col=ff0000>Rate: 1/750.</col>");
			break;
		case 50:
			player.petclaimcomponent = 27;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Bally");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Bal'lak.<br><col=ff0000>Rate: 1/400.</col>");
			break;
		case 51:
			player.petclaimcomponent = 28;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "the Enforcer");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing the Assassin.<br><col=ff0000>Rate: 1/100.</col>");
			break;
		case 52:
			player.petclaimcomponent = 29;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Vorki");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Vorkath.<br><col=ff0000>Rate: 1/1000.</col>");
			break;
		case 53:
			player.petclaimcomponent = 30;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Ikkle Hydra");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Alchemical Hydra.<br><col=ff0000>Rate: 1/2000.</col>");
			break;
		case 54:
			player.petclaimcomponent = 31;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "SkeeSkee");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Celestia, Defender of Sliske.<br><col=ff0000>Rate: 1/500.</col>");
			break;
		case 55:
			player.petclaimcomponent = 32;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Snakeling");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Zulrah.<br><col=ff0000>Rate: 1/400.</col>");
			break;
		case 56:
			player.petclaimcomponent = 33;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Kraken");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing Kraken.<br><col=ff0000>Rate: 1/1000.</col>");
			break;
		case 68:
			player.petclaimcomponent = 34;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Baby Mole");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing the Giant Mole.<br><col=ff0000>Rate: 1/400.</col>");
			break;
		case 69:
			player.petclaimcomponent = 54;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Mini Raptor");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing the Raptor.<br><col=ff0000>Rate: 1/5000.</col>");
			break;
		case 70:
			player.petclaimcomponent = 55;
			/**
			 * Pet name & Description
			 */
			player.getPackets().sendIComponentText(INTERFACE_ID, 63, "Mini Amigo's");
			player.getPackets().sendIComponentText(INTERFACE_ID, 60, "This pet is obtained whilst killing the 3 Amigo's.<br><col=ff0000>Rate: 1/1000.</col>");
			break;
		}
	}

	private static void takePayment(Player player) {
		player.coinamount -= 1000000;
		Settings.GpSyncAmount += 1000000;
		player.getPackets().sendRunScript(5561, 0, 1000000);
		player.refreshMoneyPouch();
		player.out("1,000,000 coins have been removed from your money pouch.");
	}
	
	public static void HandleCreation(Player player) {
		if (player.getInventory().getFreeSlots() < 1) {
			player.sendMessage(Colors.red+"You need inventory space to claim a pet!");
			return;
		}
		if (player.coinamount < 1000000) {
			player.sendMessage(Colors.red+"You need 1,000,000 coins in your money pouch to pay for the pet!");
			return;
		}
		switch (player.petclaimcomponent) {
		case 1:
		if (player.gotthunderouspet) {
			takePayment(player);
			player.getInventory().addItem(29586, 1);
			player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
		} else {
			player.sendMessage(Colors.red+"You have not yet obtained this pet!");
		}
		break;
		case 2:
			if (player.gotarmadylpet) {
				takePayment(player);
				player.getInventory().addItem(29906, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 3:
			if (player.gotbandospet) {
				takePayment(player);
				player.getInventory().addItem(29907, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 4:
			if (player.gotsaradominpet) {
				takePayment(player);
				player.getInventory().addItem(29905, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 5:
			if (player.gotzamorakpet) {
				takePayment(player);
				player.getInventory().addItem(29904, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 6:
			if (player.gotbadsantapet) {
				takePayment(player);
				player.getInventory().addItem(11951, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 7:
			if (player.gotcorppet) {
				takePayment(player);
				player.getInventory().addItem(29865, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 8:
			if (player.gotdryaxpet) {
				takePayment(player);
				player.getInventory().addItem(29859, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 9:
			if (player.gotdrygonpet) {
				takePayment(player);
				player.getInventory().addItem(29786, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 10:
			if (player.gotglacorpet) {
				takePayment(player);
				player.getInventory().addItem(29860, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 11:
			if (player.gotkbdpet) {
				takePayment(player);
				player.getInventory().addItem(29862, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 12:
			if (player.gotnexpet) {
				takePayment(player);
				player.getInventory().addItem(29864, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 13:
			if (player.gotvoragopet) {
				takePayment(player);
				player.getInventory().addItem(29760, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 14:
			if (player.gotsunfreetpet) {
				takePayment(player);
				player.getInventory().addItem(29853, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 15:
			if (player.gotpdemonpet) {
				takePayment(player);
				player.getInventory().addItem(29706, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 16:
			if (player.gotawyrmpet) {
				takePayment(player);
				player.getInventory().addItem(29526, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 17:
			if (player.gotsmokedevilpet) {
				takePayment(player);
				player.getInventory().addItem(29449, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 18:
			if (player.gotsirepet) {
				takePayment(player);
				player.getInventory().addItem(29442, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 19:
			if (player.gotolmpet) {
				takePayment(player);
				player.getInventory().addItem(29437, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 20:
			if (player.gotcerberuspet) {
				takePayment(player);
				player.getInventory().addItem(29415, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 21:
			if (player.gotcallistopet) {
				takePayment(player);
				player.getInventory().addItem(29380, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 22:
			if (player.gotbloodpet) {
				takePayment(player);
				player.getInventory().addItem(29327, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 23:
			if (player.gotskotizopet) {
				takePayment(player);
				player.getInventory().addItem(29320, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 24:
			if (player.gotvenenatispet) {
				takePayment(player);
				player.getInventory().addItem(29317, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 25:
			if (player.gotscorpiapet) {
				takePayment(player);
				player.getInventory().addItem(29305, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 26:
			if (player.gotvetionpet) {
				takePayment(player);
				player.getInventory().addItem(29300, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 27:
			if (player.gotballakpet) {
				takePayment(player);
				player.getInventory().addItem(29841, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 28:
			if (player.gotassassinpet) {
				takePayment(player);
				player.getInventory().addItem(29201, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 29:
			if (player.gotvorkipet) {
				takePayment(player);
				player.getInventory().addItem(29189, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 30:
			if (player.gothydrapet) {
				takePayment(player);
				player.getInventory().addItem(29141, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 31:
			if (player.gotcelestiapet) {
				takePayment(player);
				player.getInventory().addItem(29124, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 32:
			if (player.gotzulrahpet) {
				takePayment(player);
				player.getInventory().addItem(29046, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 33:
			if (player.gotkrakenpet) {
				takePayment(player);
				player.getInventory().addItem(29041, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 34:
			if (player.gotgmolepet) {
				takePayment(player);
				player.getInventory().addItem(29215, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 35:
			if (player.fishingpet) {
				takePayment(player);
				player.getInventory().addItem(29520, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 36:
			if (player.firemakingpet) {
				takePayment(player);
				player.getInventory().addItem(29521, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 37:
			if (player.cookingpet) {
				takePayment(player);
				player.getInventory().addItem(29522, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 38:
			if (player.miningpet) {
				takePayment(player);
				player.getInventory().addItem(29523, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 39:
			if (player.woodcutpet) {
				takePayment(player);
				player.getInventory().addItem(29524, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 40:
			if (player.hunterpet) {
				takePayment(player);
				player.getInventory().addItem(29525, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 41:
			if (player.gotdustypet) {
				takePayment(player);
				player.getInventory().addItem(29697, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 42:
			if (player.gotedipet) {
				takePayment(player);
				player.getInventory().addItem(29702, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 43:
			if (player.gotfreezypet) {
				takePayment(player);
				player.getInventory().addItem(29699, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 44:
			if (player.gotidragpet) {
				takePayment(player);
				player.getInventory().addItem(29701, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 45:
			if (player.gotsdragpet) {
				takePayment(player);
				player.getInventory().addItem(29700, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 46:
			if (player.gotpugpet) {
				takePayment(player);
				player.getInventory().addItem(29698, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 47:
			if (player.cluescompleted >= 500) {
				takePayment(player);
				player.getInventory().addItem(29143, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 48:
			if (player.gotdarkfeastpet) {
				takePayment(player);
				player.getInventory().addItem(29850, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 49:
			if (player.gotfrostypet) {
				takePayment(player);
				player.getInventory().addItem(29849, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 50:
			if (player.gotgargpet) {
				takePayment(player);
				player.getInventory().addItem(29852, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 51:
			if (player.gotcorruptveracpet) {
				takePayment(player);
				player.getInventory().addItem(28986, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 52:
			if (player.gotcorruptkarilpet) {
				takePayment(player);
				player.getInventory().addItem(28987, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 53:
			if (player.gotcorruptahrimpet) {
				takePayment(player);
				player.getInventory().addItem(28988, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 54:
			if (player.gotraptorpet) {
				takePayment(player);
				player.getInventory().addItem(28979, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		case 55:
			if (player.gotamigopet) {
				takePayment(player);
				player.getInventory().addItem(28904, 1);
				player.sendMessage(Colors.green+"Your little friend is now in your inventory.");
			} else {
				player.sendMessage(Colors.red+"You have not yet obtained this pet!");
			}
			break;
		}
	}

}