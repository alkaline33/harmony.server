package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.player.Equipment;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.net.decoders.handlers.ButtonHandler;

public class CosmeticsHandler {

	/**
	 * Every Item Id You Put will show in the dialogue when clicked at the slot
	 * add no matter how many it will calculate the number of pages needed go to
	 * wildy btw, yh i saw u on preview automaticly this is where all you need
	 * to change okay? go ok ty
	 */
	public static int[] HATS = { 29544,24930,28971, 29009,29011,13672, 29135, 29147, 9925, 29473, 29828, 24583, 24595, 24605, 24627, 24639, 24567,
			24827,24822,25074,25086,25098,25136,25148,25160,25170,25174,25273,24806,
			24819};
	public static int[] CAPES = { 29544, 29008,29349, 29538, 24613, 24623, 24635, 24649, 25142, 25154, 25166, 29530, 29529 }; // check "wings" in itemdb
	public static int[] AMULETS = { 29544,25100,24815 };
	public static int[] CHESTS = { 29544, 28940, 29106, 13673, 29134, 29148, 9924, 24585, 24597, 24607, 24617, 24629, 24641, 24828, 25076, 25088, 25102,
			25138,25150,25162,25275,24807,};
	public static int[] WEAPONS = { 29544, 28914, 24145,29104, 24573, 29539, 24651, 25110, 25112, 24904, 25283,
			24900,24886,24824,24902,24898,24892,24894,24577,24575,25285,};
	public static int[] SHIELDS = { 29518,28941,29107, 29109,29129, 29130, 29350, 29544, 24896, 25287 };
	public static int[] LEGS = { 29544, 28939, 29105, 13674, 29133, 29146, 9923, 24587, 24599, 24609, 24619, 24631, 24643, 24830, 25080,
			25092,25106,25140,25152,25164,25277,24809};
	public static int[] BOOTS = { 29544, 28938,29002,13675,9921, 24589, 24601, 24611, 24621, 24633, 24645, 24832, 25094, 25108,
			25144,25156,25168,25281,24811};
	public static int[] GLOVES = { 29544,29003,29132, 29131, 9922, 22362, 22366, 22358, 24591, 24637, 25078, 25090, 25104, 24813 };

	public static void openCosmeticsHandler(final Player player) {
		player.stopAll();
		player.getTemporaryAttributtes().put("Cosmetics", Boolean.TRUE);
		player.getInterfaceManager().sendInventoryInterface(670);
		player.getInterfaceManager().sendInterface(667);
		player.getPackets().sendConfigByFile(8348, 1);
		player.getPackets().sendConfigByFile(4894, 0);
		player.getPackets().sendItems(93, player.getInventory().getItems());
		player.getPackets().sendInterSetItemsOptionsScript(670, 0, 93, 4, 7, "Equip", "Compare", "Stats", "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(670, 0, 0, 27, 0, 1, 2, 3);
		Item[] cosmetics = player.getEquipment().getItems().getItemsCopy();
		for (int i = 0; i < cosmetics.length; i++) {
			Item item = cosmetics[i];
			if (i == Equipment.SLOT_AURA || i == Equipment.SLOT_ARROWS || i == Equipment.SLOT_RING) {
				continue;
			}
			if (item == null) {
				cosmetics[i] = new Item(0);
			}
		}
		player.getPackets().sendItems(94, cosmetics);
		player.getPackets().sendUnlockIComponentOptionSlots(667, 9, 0, 13, true, 0, 1, 2, 3);
		ButtonHandler.refreshEquipBonuses(player);
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.getPackets().sendConfigByFile(8348, 1);
				player.getPackets().sendRunScriptBlank(2319);
			}
		});
		player.setCloseInterfacesEvent(new Runnable() {
			@Override
			public void run() {
				player.getDialogueManager().finishDialogue();
				player.getTemporaryAttributtes().remove("Cosmetics");
				for (int i = 0; i < 15; i++) {
					player.getEquipment().refresh(i);
				}
			}
		});
	}

	/*
	 * Change the names of certain items of the costumes
	 */

	public static String getNameOnDialogue(int itemId) {
		switch (itemId) {
		case 29782:
		case 29792:
		case 29794:
		case 29784:
		case 29796:
		case 29786:
		case 29788:
		case 29790:
			return "Deathless regent";
		case 29544:
			return "Keepsaked Item";
		case 24605:
		case 24613:
		case 24607:
		case 24609:
		case 24611:
			return "Feline";
		case 24623:
		case 24621:
		case 24617:
		case 24619:
			return "Gothic";
		case 24627:
		case 24635:
		case 24629:
		case 24631:
		case 24633:
			return "Swashbuckler";
		case 24639:
		case 24649:
		case 24651:
		case 24641:
		case 26029:
		case 24643:
		case 24647:
		case 24645:
			return "Assassin";
		case 24827:
		case 24832:
		case 24828:
		case 24830:
			return "Beachwear";
		case 25074:
		case 25076:
		case 25080:
		case 25078:
		case 25082:
			return "Monarch";
		case 25086:
		case 25088:
		case 25092:
		case 25090:
		case 25094:
			return "Noble";
		case 25098:
		case 25100:
		case 25102:
		case 25106:
		case 25104:
		case 25108:
			return "Servant";
		case 25136:
		case 25142:
		case 25138:
		case 25140:
		case 25144:
			return "Fox";
		case 25148:
		case 25154:
		case 25150:
		case 25152:
		case 25156:
			return "Wolf";
		case 25160:
		case 25166:
		case 25162:
		case 25164:
		case 25168:
			return "Panda (Broken)";
		case 25273:
		case 25275:
		case 25277:
		case 25279:
		case 25281:
			return "Ultimate Donator Outfit";

		case 25374:
		case 25376:
		case 25378:
		case 25380:
		case 25382:
			return "Kril's Battlegear";
		case 25386:
		case 25396:
		case 25388:
		case 25390:
		case 25392:
		case 25394:
			return "Kril's Godcrusher";
		case 26043:
		case 26045:
		case 26047:
		case 26049:
		case 26051:
			return "Ariane's Outfit";
		case 26071:
		case 26063:
		case 26065:
		case 26067:
		case 26069:
			return "Ozan's Outfit";
		case 26158:
		case 26160:
		case 26162:
		case 26164:
		case 26166:
			return "TokHaar Brute";
		case 26170:
		case 26172:
		case 26174:
		case 26176:
		case 26178:
			return "TokHaar Veteran";
		case 26182:
		case 26184:
		case 26186:
		case 26188:
		case 26190:
			return "TokHaar Warlord";
		case 26472:
		case 26470:
		case 26466:
		case 26468:
			return "Paladin)";
		case 26464:
			return "Paladin Hero";
		case 27075:
		case 27076:
		case 27077:
		case 27078:
		case 27079:
		case 27080:
			return "Kalphite Sentinel";
		case 27083:
		case 27084:
		case 27085:
		case 27087:
		case 27086:
		case 27088:
			return "Kalphite Emissary";
		case 27174:
		case 27178:
		case 27175:
		case 27176:
		case 27177:
			return "Shadow Cat";
		case 27181:
		case 27186:
		case 27182:
		case 27183:
		case 27185:
		case 27184:
			return "Shadow Hunter";
		case 27189:
		case 27190:
		case 27191:
		case 27192:
		case 27193:
			return "Shadow Sentinel";
		case 27205:
		case 27210:
		case 27206:
		case 27207:
		case 27208:
		case 27209:
			return "Shadow Demon";
		case 27198:
		case 27199:
		case 27200:
		case 27202:
		case 27201:
			return "Shadow Knight";
		case 29976:
		case 29986:
		case 29980:
		case 29982:
		case 29984:
		case 29978:
			return "Ogre infiltrator";
		case 27120:
		case 27130:
		case 27122:
		case 27124:
		case 27126:
		case 27128:
			return "Greater demonflesh";
		case 27134:
		case 27136:
		case 27138:
		case 27140:
		case 27142:
			return "Lesser demonflesh";
		case 27419:
		case 27421:
		case 27423:
		case 27425:
		case 27427:
			return "Guthixian war robes";
		case 27431:
		case 27433:
		case 27435:
		case 27437:
		case 27439:
			return "Saradominist war robes";
		case 27443:
		case 27445:
		case 27447:
		case 27449:
		case 27451:
			return "Zamorakian war robes";
		case 27455:
		case 27457:
		case 27459:
		case 27461:
		case 27463:
			return "Zarosian war robes";
		case 27549:
		case 27554:
		case 27550:
		case 27551:
		case 27553:
		case 27522:
			return "Skypouncer Outfit";
		case 28049:
		case 28050:
		case 28051:
		case 28052:
		case 28053:
			return "Flameheart";
		case 28056:
		case 28057:
		case 28059:
		case 28058:
		case 28060:
			return "Stoneheart";
		case 28063:
		case 28064:
		case 28066:
		case 28065:
		case 28067:
			return "Stormheart";
		case 28070:
		case 28071:
		case 28073:
		case 28072:
		case 28074:
			return "Iceheart";
		case 28838:
		case 28843:
		case 28839:
		case 28840:
		case 28842:
		case 28841:
			return "Colossus";
		case 28846:
		case 28851:
		case 28847:
		case 28848:
		case 28849:
		case 28850:
			return "Veteran Colossus";
		case 28854:
		case 28855:
		case 28856:
		case 28857:
		case 28858:
			return "Titan";
		case 28861:
		case 28862:
		case 28863:
		case 28864:
		case 28865:
			return "Veteran Titan";
		case 28868:
		case 28873:
		case 28869:
		case 28870:
		case 28871:
		case 28872:
			return "Behemoth";
		case 28876:
		case 28881:
		case 28877:
		case 28878:
		case 28879:
		case 28880:
			return "Veteran behemoth";
		case 28884:
		case 28885:
		case 28886:
		case 28887:
		case 28888:
			return "Beast";
		case 28891:
		case 28892:
		case 28893:
		case 28894:
		case 28895:
			return "Veteran beast";
		case 29057:
		case 29060:
		case 29058:
		case 29059:
			return "Colonist outfit";
		case 29063:
		case 29066:
		case 29064:
		case 29065:
			return "Highland outfit";
		case 29075:
		case 29078:
		case 29076:
		case 29077:
			return "Musketeer outfit";
		case 29081:
		case 29084:
		case 29082:
		case 29083:
			return "Elven outfit";
		case 29087:
		case 29088:
		case 29090:
		case 29089:
		case 29091:
			return "Werewolf outfit";
		case 29561:
		case 29571:
		case 29563:
		case 29565:
		case 29567:
		case 29569:
			return "Ambassador of Order";
		case 29575:
		case 29577:
		case 29579:
		case 29581:
		case 29583:
			return "Envoy of Order";
		case 29587:
		case 29597:
		case 29589:
		case 29591:
		case 29593:
		case 29595:
			return "Ambassador of Chaos";
		case 29601:
		case 29603:
		case 29605:
		case 29607:
		case 29609:
			return "Envoy of Chaos";
		case 28428:
		case 28429:
		case 28431:
		case 28430:
		case 28432:
		case 28433:
		case 28434:
			return "Aurora armour";
		case 24822:
			return "Glasses";
		case 28941:
		case 28942:
		case 28943:
		case 28944:
		case 28945:
			return "Templar armour";
		case 29421:
		case 29424:
		case 29419:
		case 29420:
			return "Superhero outfit";
		case 29431:
		case 29436:
		case 29432:
		case 29433:
			return "Staff outfit";
		case 29461:
		case 29462:
		case 29463:
		case 29464:
		case 29465:
			return "Dulcin armour";
		case 29766:
		case 29776:
		case 29768:
		case 29778:
		case 29770:
		case 29772:
		case 29774:
			return "Ravenskull outfit";
		case 29958:
		case 29962:
		case 29960:
		case 29964:
		case 29966:
			return "Ancient mummy outfit";
		case 29990:
		case 30000:
		case 30002:
		case 29992:
		case 29996:
		case 29994:
		case 29998:
			return "Drakewing outfit";
		case 30417:
		case 30427:
		case 30419:
		case 30421:
		case 30423:
		case 30425:
			return "Frostwalker";
		case 30433:
		case 30445:
		case 30437:
		case 30439:
		case 30441:
		case 30443:
			return "Glad tidings outfit";
		case 30461:
		case 30466:
		case 30462:
		case 30463:
		case 30464:
		case 30465:
			return "Golem of Strength armour";
		case 30476:
		case 30481:
		case 30477:
		case 30478:
		case 30480:
		case 30479:
			return "Golem of Justice armour";
		case 30597:
		case 30602:
		case 30603:
		case 20598:
		case 30599:
		case 30601:
		case 30600:
			return "Blessed Sentinel outfit";
		case 30606:
		case 30611:
		case 30612:
		case 30607:
		case 30608:
		case 30610:
		case 30609:
			return "Cursed Reaver outfit";
		case 31979:
			return "Zarosian shadow boots";
		case 31986:
			return "Zarosian praetor boots";
		case 31184:
			return "Godless cape";
		case 30617:
		case 30618:
		case 30619:
			return "Replica Virtus";
		case 30622:
		case 30623:
		case 30624:
			return "Replica Torva";
		case 30627:
		case 30628:
		case 30629:
			return "Replica Pernix";
		case 30952:
		case 30956:
		case 30953:
		case 30954:
		case 30955:
			return "Clown costume";
		case 31232:
		case 31233:
		case 31234:
			return "Replica Armadyl";
		case 31237:
		case 31238:
		case 31239:
			return "Replica Bandos";
		case 31296:
		case 31300:
		case 31297:
		case 31298:
		case 31301:
		case 31299:
			return "Hiker costume";
		case 31536:
		case 31543:
		case 31541:
		case 31537:
		case 31538:
		case 31540:
		case 31539:
			return "Aviansie Skyguard";
		case 31546:
		case 31551:
		case 31552:
		case 31547:
		case 31548:
		case 31549:
		case 31550:
			return "Vyrewatch Skyshadow";
		case 32315:
		case 32316:
		case 32317:
		case 32319:
		case 32318:
			return "Elven warrior outfit";
		case 32322:
		case 32323:
		case 32324:
		case 32326:
		case 32325:
			return "Elven ranger outfit";
		case 32329:
		case 32330:
		case 32331:
		case 32333:
		case 32332:
			return "Elven mage outfit";
		case 32551:
		case 32557:
		case 32556:
		case 32552:
		case 32553:
		case 32555:
		case 32554:
			return "Attuned Nex outfit";
		case 32568:
		case 32574:
		case 32573:
		case 32569:
		case 32570:
		case 32572:
		case 32571:
			return "Attuned King Black Dragon outfit";
		case 33637:
		case 33642:
		case 33638:
		case 33639:
		case 33641:
		case 33640:
		case 44640:
			return "Samurai outfit";
		case 33711:
		case 33709:
		case 33712:
		case 33713:
		case 33715:
		case 33714:
			return "Outfit of Omens";
		case 33755:
		case 33756:
		case 33757:
		case 33759:
		case 33758:
			return "Warm winter outfit";
		case 24819:
			return "Seaweed hair";
		case 24806:
		case 24807:
		case 24813:
		case 24809:
		case 24811:
		case 24815:
			return "Tropical islander outfit";
		case 24817:
			return "Squid Tentacle cape";
		case 24904:
			return "Seaborne dagger";
		case 25283:
			return "Ultimate Donator Longsword";
		case 25398:
			return "Cleaver of Tsutsaroth";
		case 24900:
			return "Scorching axe";
		case 24886:
			return "Blazing flamberge";
		case 24824:
			return "Parasol 2H sword";
		case 24902:
			return "Shipwrecker trident";
		case 24898:
			return "Daggerfist claws";
		case 24892:
			return "Razor whip";
		case 24894:
			return "Flaming lash";
		case 24577:
			return "Shock Eye staff";
		case 24890:
			return "Shatterstorm wand";
		case 24888:
			return "Firebrand bow";
		case 25285:
			return "Ultimate Donator Crossbow";
		case 25999:
			return "Dwarven crossbow";
		case 24896:
			return "Solarius shield";
		case 25287:
			return "Ultimate Donator Shield";
		case 26474:
			return "Paladin shield";
		
			
		}
		return ItemDefinitions.getItemDefinitions(itemId).getName();
	}

	/*
	 * Restricts player from using certain items if they doesn't have the
	 * requirment needed
	 */

	public static boolean isRestrictedItem(Player player, int itemId) {
	if (player.getRights() >= 2) {
		return false;
	}
		switch (itemId) {
		case 24930:
			return player.icecreamhatoverridec == false;
		case 29473:
			return player.goldenchefc == false;
		case 29518:
			return player.teddybearc == false;
		case 29538:
			return player.cupidwingsc == false;
		case 29539:
			return player.cupidbowc == false;
		case 24145:
			return player.eggsterminatorc == false;
		case 28914:
			return player.pinkboxingglovesc == false;
		case 22362:
		case 22366:
		case 22358:
		case 29349:
		case 29350:
		case 24573:
			return !player.getUsername().equalsIgnoreCase("cheesecake");
		case 28941:
		case 28940:
		case 28939:
		case 28938:
		return !player.getUsername().equalsIgnoreCase("cheesecake") && !player.getUsername().equalsIgnoreCase("the iron man");
		case 29109:
		case 29104:
		case 29105:
		case 29106:
			return !player.getUsername().equalsIgnoreCase("scouse");
		case 13672:
		case 13673:
		case 13674:
		case 13675:
			return player.ringmasterc == false;
		case 24595:
		case 24597:
		case 24599:
		case 24601:
			return player.colosseumc == false;
		case 29828:
			return player.bunnymaskc == false;
		case 24822:
			return player.glassesc == false;
		case 24583:
		case 24585:
		case 24587:
		case 24589:
		case 24591:
			return player.cabaretc == false;
		case 29529:
			return player.zamorakwingssc == false;
		case 29530:
			return player.crystalwingsc == false;
		case 25074:
		case 25076:
		case 25080:
		case 25078:
		case 25082:
			return player.monarchc == false;
		case 24605:
		case 24613:
		case 24607:
		case 24609:
		case 24611:
			return player.felinec == false;
		case 24627:
		case 24635:
		case 24629:
		case 24631:
		case 24633:
			return player.swashbucklerc == false;
		case 24639:
		case 24649:
		case 24651:
		case 24641:
		case 24643:
		case 24647:
		case 24645:
		case 24637:
			return player.assassinc == false;
		case 9921:
		case 9922:
		case 9923:
		case 9924:
		case 9925:
			return player.skeletonc == false;
		case 24567:
			return player.guyfawkesc == false;
		case 24827:
		case 24832:
		case 24828:
		case 24830:
			return player.beachwearc == false;
		case 25086:
		case 25088:
		case 25092:
		case 25090:
		case 25094:
		return player.noblec == false;
		case 25098:
		case 25100:
		case 25102:
		case 25106:
		case 25104:
		case 25108:
			return player.servantc == false;
		case 25136:
		case 25142:
		case 25138:
		case 25140:
		case 25144:
			return player.foxc == false;
		case 25148:
		case 25154:
		case 25150:
		case 25152:
		case 25156:
			return player.wolfc == false;
		case 25160:
		case 25166:
		case 25162:
		case 25164:
		case 25168:
			return player.pandac == false;
		case 25170:
			return player.woodlandcownc == false;
		case 25174:
			return player.kalphitegreatc == false;
		case 25273:
		case 25275:
		case 25277:
		case 25279:
		case 25281:
		case 25283:
		case 25285:
		case 25287:
			return !player.isUltimateDonator();
		case 24806:
		case 24807:
		case 24813:
		case 24809:
		case 24811:
		case 24815:
			return player.tropicalislanderc == false;
		case 29130:
		case 29131:
		case 29132:
		case 29133:
		case 29134:
		case 29135:
		case 29146:
		case 29147:
		case 29148:
		case 29129:
		case 29011:
		case 29107:
		case 29008:
		case 29009:
		case 29003:
		case 29002:
			return !player.getDisplayName().equalsIgnoreCase("level iron") && !player.getDisplayName().equalsIgnoreCase("level 1") && !player.getDisplayName().equalsIgnoreCase("level duo");
		case 28971:
			return !player.getDisplayName().equalsIgnoreCase("godrx119");
		case 24819:
			return player.seasweedhairc == false;
		case 18667:
			return player.runefestc == false;
		case 24623:
		case 24621:
		case 24617:
		case 24619:
			return player.gothicc == false;
		case 25172:
			return player.bunnytailc == false;
		case 24817:
			return player.squidtentaclecapec == false;
		case 24904:
			return player.seabornec == false;
		case 25112:
		case 25110:
			return player.royalcourtc == false;
		case 26025:
			return player.pactbrakerc== false;
		case 25398:
		case 26023:
			return player.oftsutsarothc == false;
		case 24900:
		case 26035:
			return player.scorchingpackc==false;
		case 24886:
			return player.blazingpackc == false;
		case 27597:
			return player.edictspackc ==false;
		case 24824:
			return player.parasol2hc == false;
		case 24902:
			return player.shipwreckpackc == false;
		case 24898:
		case 26033:
			return player.daggerfistpackc == false;
		case 24892:
		case 24894://razor,flaming whips
			return player.whippackc == false;
		case 24577:
			return player.shockpackc == false;
		case 24890://shatterstorm
			return player.wandpackc==false;
		case 24888:
		case 27614://barb,firebrand
			return player.bowpackc==false;
		case 24575:
		case 26027:
			return player.quickfirec ==false;
		case 24896:
			return player.solariusc ==false;
		}
		return false;
	}

}