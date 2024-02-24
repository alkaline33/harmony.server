package com.rs.game.minigames;

import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * @author Monum3ntal
 *
 */
public class Gamble {
	
	public static void Gamble(final Player player) {
		int random = Utils.random(1, 100);
		int prize = 5000000;
		int newamount = player.coinamount + prize;
		if (player.getMoneyPouch().usingPouch) {
			if (player.coinamount > 5000000) { 
				player.closeInterfaces();
					if (player.getMoneyPouch().usingPouch)
						if (random <= 49) {
							player.getPackets().sendGameMessage("<col=0099CC>[Gamble]<col=FF0000>Sorry you lost, better luck next time.");
							player.coinamount -= 5000000;
							player.getPackets().sendRunScript(5560, player.coinamount);
							player.getPackets().sendRunScript(5561, new Object[] {0, 5000000});
								}
									else
										if (random >= 50 && random <= 100) {
											if (newamount > 2147483647 || newamount < 0) {
												player.getInventory().addItem(995, prize);
												player.getPackets().sendGameMessage("<col=0099CC>[Gamble]<col=00FF00>Congratulations, you have won"  + prize +".");
												return;
											}
												else
													player.coinamount += prize;
													player.getPackets().sendRunScript(5560, player.coinamount);
													player.getPackets().sendRunScript(5561, new Object[] {1, prize});
													player.getPackets().sendGameMessage("<col=0099CC>[Gamble]<col=00FF00>Congratulations, you have won "  + player.getMoneyPouch().getFormattedNumber(prize) +".");
					}
				}
			}
		}
	public static void Gamble2(Player player) {
		player.sendMessage("true");
		int random = Utils.random(1, 100);
		int prize = 5000000;
		int coins = player.getInventory().getItems().getNumberOf(995);
			if (coins > 4999999) { 
				player.closeInterfaces();
					if (random <= 49) {
						player.getPackets().sendGameMessage("<col=0099CC>[Gamble]<col=FF0000>Sorry you lost, better luck next time.");
						player.getInventory().deleteItem(995, 5000000);
							return;
								}
									else
										player.getInventory().addItem(995, prize);
										player.getPackets().sendGameMessage("<col=0099CC>[Gamble]<col=00FF00>Congratulations, you have won "  + player.getMoneyPouch().getFormattedNumber(prize) +".");
	}
		
	}
}