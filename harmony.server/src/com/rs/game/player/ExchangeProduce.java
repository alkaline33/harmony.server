package com.rs.game.player;

import com.rs.game.item.Item;

public class ExchangeProduce  {

	public static void exchangeProduce (Player player, Item item) {
		switch (item.getId()) {
			/*
			 * Allotments
			 */
			case 1942: //potato
				int potatoAmount = player.getInventory().getNumberOf(1942);
				player.getInventory().deleteItem(1942, potatoAmount);
				player.getInventory().addItem(1943, potatoAmount);
				break;
			case 1957: //onion
				int onionAmount = player.getInventory().getNumberOf(1957);
				player.getInventory().deleteItem(1957, onionAmount);
				player.getInventory().addItem(1958, onionAmount);
				break;
			case 1965: //cabbage
				int cabbageAmount = player.getInventory().getNumberOf(1965);
				player.getInventory().deleteItem(1965, cabbageAmount);
				player.getInventory().addItem(1966, cabbageAmount);
				break;
			case 1982: //tomato
				int tomatoAmount = player.getInventory().getNumberOf(1982);
				player.getInventory().deleteItem(1982, tomatoAmount);
				player.getInventory().addItem(1983, tomatoAmount);
				break;
			case 5986: //sweetcorn
				int sweetcornAmount = player.getInventory().getNumberOf(5986);
				player.getInventory().deleteItem(5986, sweetcornAmount);
				player.getInventory().addItem(5987, sweetcornAmount);
				break;
			case 5504: //strawberry
				int strawberryAmount = player.getInventory().getNumberOf(5504);
				player.getInventory().deleteItem(5504, strawberryAmount);
				player.getInventory().addItem(5505, strawberryAmount);
				break;
			case 5982: //watermelon
				int watermelonAmount = player.getInventory().getNumberOf(5982);
				player.getInventory().deleteItem(5982, watermelonAmount);
				player.getInventory().addItem(5983, watermelonAmount);
				break;
			/*
			 * Hops
			 */
			case 6006: //barley
				int barleyAmount = player.getInventory().getNumberOf(6006);
				player.getInventory().deleteItem(6006, barleyAmount);
				player.getInventory().addItem(6007, barleyAmount);
				break;
			case 5994: //hammerstone
				int hammerstoneAmount = player.getInventory().getNumberOf(5994);
				player.getInventory().deleteItem(5994, hammerstoneAmount);
				player.getInventory().addItem(5995, hammerstoneAmount);
				break;
			case 5996: //asgarnian
				int asgarnianAmount = player.getInventory().getNumberOf(5996);
				player.getInventory().deleteItem(5996, asgarnianAmount);
				player.getInventory().addItem(5997, asgarnianAmount);
				break;
			case 5931: //jute
				int juteAmount = player.getInventory().getNumberOf(5931);
				player.getInventory().deleteItem(5931, juteAmount);
				player.getInventory().addItem(5932, juteAmount);
				break;
			case 5998: //yanillian
				int yanillianAmount = player.getInventory().getNumberOf(5998);
				player.getInventory().deleteItem(5998, yanillianAmount);
				player.getInventory().addItem(5999, yanillianAmount);
				break;
			case 6000: //krandorian
				int krandorianAmount = player.getInventory().getNumberOf(6000);
				player.getInventory().deleteItem(6000, krandorianAmount);
				player.getInventory().addItem(6001, krandorianAmount);
				break;
			case 6002: //wildblood
				int wildbloodAmount = player.getInventory().getNumberOf(6002);
				player.getInventory().deleteItem(6002, wildbloodAmount);
				player.getInventory().addItem(6003, wildbloodAmount);
				break;
			/*
			 * Bushes
			 */
			case 1951: //redberry
				int redberryAmount = player.getInventory().getNumberOf(1951);
				player.getInventory().deleteItem(1951, redberryAmount);
				player.getInventory().addItem(1952, redberryAmount);
				break;
			case 753: //cadavaberry
				int cadavaberryAmount = player.getInventory().getNumberOf(753);
				player.getInventory().deleteItem(753, cadavaberryAmount);
				player.getInventory().addItem(754, cadavaberryAmount);
				break;
			case 2126: //dwellberry
				int dwellberryAmount = player.getInventory().getNumberOf(2126);
				player.getInventory().deleteItem(2126, dwellberryAmount);
				player.getInventory().addItem(2127, dwellberryAmount);
				break;
			case 247: //jangerberry
				int jangerberryAmount = player.getInventory().getNumberOf(247);
				player.getInventory().deleteItem(247, jangerberryAmount);
				player.getInventory().addItem(248, jangerberryAmount);
				break;
			case 239: //whiteberry
				int whiteberryAmount = player.getInventory().getNumberOf(239);
				player.getInventory().deleteItem(239, whiteberryAmount);
				player.getInventory().addItem(240, whiteberryAmount);
				break;
			case 6018: //posion ivy berry
				int ivyAmount = player.getInventory().getNumberOf(6018);
				player.getInventory().deleteItem(6018, ivyAmount);
				player.getInventory().addItem(6019, ivyAmount);
				break;
			/*
			 * Flowers
			 */
			case 6010: //marigold
				int marigoldAmount = player.getInventory().getNumberOf(6010);
				player.getInventory().deleteItem(6010, marigoldAmount);
				player.getInventory().addItem(6011, marigoldAmount);
				break;
			case 6014: //rosemary
				int rosemaryAmount = player.getInventory().getNumberOf(6014);
				player.getInventory().deleteItem(6014, rosemaryAmount);
				player.getInventory().addItem(6015, rosemaryAmount);
				break;
			case 6012: //nasturtium
				int nasturtiumAmount = player.getInventory().getNumberOf(6012);
				player.getInventory().deleteItem(6012, nasturtiumAmount);
				player.getInventory().addItem(6013, nasturtiumAmount);
				break;
			case 225: //limpwurt
				int limpwurtAmount = player.getInventory().getNumberOf(225);
				player.getInventory().deleteItem(225, limpwurtAmount);
				player.getInventory().addItem(226, limpwurtAmount);
				break;
			/*
			 * Special
			 */
			case 12134: //evil turnip
				int turnipAmount = player.getInventory().getNumberOf(12134);
				player.getInventory().deleteItem(12134, turnipAmount);
				player.getInventory().addItem(12135, turnipAmount);
				break;
			case 6004: //bittercap mushroom
				int bittercapAmount = player.getInventory().getNumberOf(6004);
				player.getInventory().deleteItem(6004, bittercapAmount);
				player.getInventory().addItem(6005, bittercapAmount);
				break;
			case 6016: //cactus
				int cactusAmount = player.getInventory().getNumberOf(6016);
				player.getInventory().deleteItem(6016, cactusAmount);
				player.getInventory().addItem(6017, cactusAmount);
				break;
			case 21622: //morchella mushroom
				int morchellaAmount = player.getInventory().getNumberOf(21622);
				player.getInventory().deleteItem(21622, morchellaAmount);
				player.getInventory().addItem(21623, morchellaAmount);
				break;
			/*
			 * Fruit Trees
			 */
			case 1955: //apple
				int appleAmount = player.getInventory().getNumberOf(1955);
				player.getInventory().deleteItem(1955, appleAmount);
				player.getInventory().addItem(1956, appleAmount);
				break;
			case 1963: //banana
				int bananaAmount = player.getInventory().getNumberOf(1963);
				player.getInventory().deleteItem(1963, bananaAmount);
				player.getInventory().addItem(1964, bananaAmount);
				break;
			case 2108: //orange
				int orangeAmount = player.getInventory().getNumberOf(2108);
				player.getInventory().deleteItem(2108, orangeAmount);
				player.getInventory().addItem(2109, orangeAmount);
				break;
			case 5970: //curry
				int curryAmount = player.getInventory().getNumberOf(5970);
				player.getInventory().deleteItem(5970, curryAmount);
				player.getInventory().addItem(5971, curryAmount);
				break;
			case 2114: //pineapple
				int pineappleAmount = player.getInventory().getNumberOf(2114);
				player.getInventory().deleteItem(2114, pineappleAmount);
				player.getInventory().addItem(2115, pineappleAmount);
				break;
			case 5972: //papaya
				int papayaAmount = player.getInventory().getNumberOf(5972);
				player.getInventory().deleteItem(5972, papayaAmount);
				player.getInventory().addItem(5973, papayaAmount);
				break;
			case 5974: //palm
				int coconutAmount = player.getInventory().getNumberOf(5974);
				player.getInventory().deleteItem(5974, coconutAmount);
				player.getInventory().addItem(5975, coconutAmount);
				break;
			/*
			 * Grimy Herbs
			 */
			case 199:
				int grimyGuam = player.getInventory().getNumerOf(199);
				player.getInventory().deleteItem(199, grimyGuam);
				player.getInventory().addItem(200, grimyGuam);
				break;
			case 201:
				int grimyMarrentill = player.getInventory().getNumerOf(201);
				player.getInventory().deleteItem(201, grimyMarrentill);
				player.getInventory().addItem(202, grimyMarrentill);
				break;
			case 203:
				int grimyTarromin = player.getInventory().getNumerOf(203);
				player.getInventory().deleteItem(203, grimyTarromin);
				player.getInventory().addItem(204, grimyTarromin);
				break;
			case 205:
				int grimyHarralander = player.getInventory().getNumerOf(205);
				player.getInventory().deleteItem(205, grimyHarralander);
				player.getInventory().addItem(206, grimyHarralander);
				break;
			case 207:
				int grimyRanarr = player.getInventory().getNumerOf(207);
				player.getInventory().deleteItem(207, grimyRanarr);
				player.getInventory().addItem(208, grimyRanarr);
				break;
			case 3049:
				int grimyToadflax = player.getInventory().getNumerOf(3049);
				player.getInventory().deleteItem(3049, grimyToadflax);
				player.getInventory().addItem(3050, grimyToadflax);
				break;
			case 209:
				int grimyIrit = player.getInventory().getNumerOf(209);
				player.getInventory().deleteItem(209, grimyIrit);
				player.getInventory().addItem(210, grimyIrit);
				break;
			case 211:
				int grimyAvantoe = player.getInventory().getNumerOf(211);
				player.getInventory().deleteItem(211, grimyAvantoe);
				player.getInventory().addItem(212, grimyAvantoe);
				break;
			case 213:
				int grimyKwuarm = player.getInventory().getNumerOf(213);
				player.getInventory().deleteItem(213, grimyKwuarm);
				player.getInventory().addItem(214, grimyKwuarm);
				break;
			case 215:
				int grimyCadantine = player.getInventory().getNumerOf(215);
				player.getInventory().deleteItem(215, grimyCadantine);
				player.getInventory().addItem(216, grimyCadantine);
				break;
			case 217:
				int grimyDwarfWeed = player.getInventory().getNumerOf(217);
				player.getInventory().deleteItem(217, grimyDwarfWeed);
				player.getInventory().addItem(218, grimyDwarfWeed);
				break;
			case 219:
				int grimyTorstol = player.getInventory().getNumerOf(219);
				player.getInventory().deleteItem(219, grimyTorstol);
				player.getInventory().addItem(220, grimyTorstol);
				break;
			/*
			 * Clean Herbs
			 */
			case 249:
				int cleanGuam = player.getInventory().getNumerOf(249);
				player.getInventory().deleteItem(249, cleanGuam);
				player.getInventory().addItem(250, cleanGuam);
				break;
			case 251:
				int cleanMarrentill = player.getInventory().getNumerOf(251);
				player.getInventory().deleteItem(251, cleanMarrentill);
				player.getInventory().addItem(252, cleanMarrentill);
				break;
			case 253:
				int cleanTarromin = player.getInventory().getNumerOf(253);
				player.getInventory().deleteItem(253, cleanTarromin);
				player.getInventory().addItem(254, cleanTarromin);
				break;
			case 255:
				int cleanHarralander = player.getInventory().getNumerOf(255);
				player.getInventory().deleteItem(255, cleanHarralander);
				player.getInventory().addItem(256, cleanHarralander);
				break;
			case 257:
				int cleanRanarr = player.getInventory().getNumerOf(257);
				player.getInventory().deleteItem(257, cleanRanarr);
				player.getInventory().addItem(258, cleanRanarr);
				break;
			case 2998:
				int cleanToadflax = player.getInventory().getNumerOf(2998);
				player.getInventory().deleteItem(2998, cleanToadflax);
				player.getInventory().addItem(2999, cleanToadflax);
				break;
			case 259:
				int cleanIrit = player.getInventory().getNumerOf(259);
				player.getInventory().deleteItem(259, cleanIrit);
				player.getInventory().addItem(260, cleanIrit);
				break;
			case 261:
				int cleanAvantoe = player.getInventory().getNumerOf(261);
				player.getInventory().deleteItem(261, cleanAvantoe);
				player.getInventory().addItem(262, cleanAvantoe);
				break;
			case 263:
				int cleanKwuarm = player.getInventory().getNumerOf(263);
				player.getInventory().deleteItem(263, cleanKwuarm);
				player.getInventory().addItem(264, cleanKwuarm);
				break;
			case 265:
				int cleanCadantine = player.getInventory().getNumerOf(265);
				player.getInventory().deleteItem(265, cleanCadantine);
				player.getInventory().addItem(266, cleanCadantine);
				break;
			case 267:
				int cleanDwarfWeed = player.getInventory().getNumerOf(267);
				player.getInventory().deleteItem(267, cleanDwarfWeed);
				player.getInventory().addItem(268, cleanDwarfWeed);
				break;
			case 269:
				int cleanTorstol = player.getInventory().getNumerOf(269);
				player.getInventory().deleteItem(269, cleanTorstol);
				player.getInventory().addItem(270, cleanTorstol);
				break;
		}
	}
}