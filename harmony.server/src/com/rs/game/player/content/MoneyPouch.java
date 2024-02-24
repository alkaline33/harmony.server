package com.rs.game.player.content;

import java.io.Serializable;
import java.text.DecimalFormat;

import com.rs.game.item.Item;
import com.rs.game.player.Player;

public class MoneyPouch implements Serializable {

	private static final long serialVersionUID = -3847090682601697992L;

	private transient Player player;
	public boolean usingPouch;
	public int amount;

	public MoneyPouch(Player player) {
		this.player = player;
	}

	public void switchPouch() {
		usingPouch = !usingPouch;
		refresh(true);
	}

	private void refresh(boolean swap) {
		if (swap)
			player.getPackets().sendRunScript(5557, 1);
		player.getPackets().sendRunScript(5560, getCoinAmount());
	}

	public void sendDynamicInteraction(int amount, boolean remove) {
		int newAmount = remove ?  amount - player.coinamount : amount + player.coinamount;
		int space = 2147483647 - player.coinamount;
		if (usingPouch || !usingPouch) {
			Item item = new Item(995, amount - (remove ? 0 : Integer.MAX_VALUE));
			if (remove) {
				if (newAmount < 0) {
					if (player.getInventory().containsItem(item.getId(), item.getAmount())) {
						player.getPackets().sendGameMessage("You dont have enough money in your pouch. Your coins will be taken from your inventory instead.");
						player.getInventory().deleteItem(item);
					} else 
						player.getPackets().sendGameMessage("You don't have enough coins.");
					return;
				} else if (player.coinamount == 0){
					player.getPackets().sendGameMessage("Your money-pouch is currently empty.");
					return;
				} else if (player.getInventory().getItems().getNumberOf(995) + player.coinamount > 2147483647 || player.getInventory().getItems().getNumberOf(995) + player.coinamount <= 0) {
					int dif = 2147483647 - player.getInventory().getItems().getNumberOf(995);
					if (dif > 0) 
						player.getPackets().sendRunScript(5561, new Object[] {0, dif});
					player.coinamount -= dif;
					player.getPackets().sendRunScript(5560, new Object[] {player.coinamount});
					if (dif > 0) 
						player.getInventory().addItem(995, dif);
					if (dif > 0) 
						player.getPackets().sendGameMessage(getFormattedNumber(dif) +" coins have been removed from your money pouch.");
					player.refreshMoneyPouch();
					return;
				} else
					player.getPackets().sendRunScript(5561, new Object[] {0, amount});
				player.coinamount -= amount;
				player.getPackets().sendRunScript(5560, new Object[] {player.coinamount});
				player.getInventory().addItem(995, amount);
				player.refreshMoneyPouch();
				player.getPackets().sendGameMessage(getFormattedNumber(amount) +" coins have been removed from your money pouch.");
			} else {
				if (newAmount > 2147483647 || newAmount <= 0) {
					
					int dif = 2147483647 - player.coinamount;
					if (dif > 0) 
						player.getPackets().sendRunScript(5561, new Object[] {1, dif});
					player.coinamount += dif;
					player.getPackets().sendRunScript(5560, new Object[] {player.coinamount});
					if (dif > 0) 
						player.getInventory().deleteItem(995, dif);
					if (dif > 0) 
						player.getPackets().sendGameMessage(getFormattedNumber(dif) +" coins have been added to your money pouch.");
					player.refreshMoneyPouch();
					return;
				} else 
					player.getPackets().sendRunScript(5561, new Object[] {1, amount});
				player.coinamount += amount;
				player.getPackets().sendRunScript(5560, new Object[] {player.coinamount});
				player.getInventory().deleteItem(995, amount);
				player.refreshMoneyPouch();
				player.getPackets().sendGameMessage(getFormattedNumber(amount) +" coins have been added to your money pouch.");
			}
			setAmount(newAmount, amount, remove);
		} else {
			if (remove) 
				player.getInventory().deleteItem(new Item(995, amount));
			else
				player.getInventory().addItem(new Item(995, amount));
		}
	}

	public String getFormattedNumber(int amount) {
		return new DecimalFormat("#,###,##0").format(amount).toString();
	}

	public void sendExamine() {
		player.getPackets().sendGameMessage("Your money pouch current contains " + getFormattedNumber(getCoinAmount()) + " coins.");
	}

	private void setAmount(int coinAmount, int addedAmount, boolean remove) {
		this.setCoinAmount(coinAmount);
		player.getPackets().sendRunScript(5561 , remove ? 0 : 1, addedAmount);
		refresh(false);
	}

	public int getCoinAmount() {
		return player.coinamount;
	}

	public void setCoinAmount(int coinAmount) {
		this.player.coinamount = coinAmount;
	}
}