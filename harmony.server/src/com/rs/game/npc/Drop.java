package com.rs.game.npc;

import com.rs.utils.Utils;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Drop {

	private int itemId, minAmount, maxAmount;
	private double rate;

	private final boolean rare;

	@NotNull
	@Contract("_, _, _, _, _ -> new")
	public static Drop create(int itemId, double rate, int minAmount,
							  int maxAmount, boolean rare) {
		return new Drop((short) itemId, rate, minAmount, maxAmount, rare);
	}

	public Drop(int itemId, double rate, int minAmount, int maxAmount,
			boolean rare) {
		this.itemId = itemId;
		this.rate = rate;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
		this.rare = rare;
	}

	public boolean oneAmount() {
		return maxAmount == minAmount;
	}

	public String getFormattedMinAmount() {
		return Utils.formatGP(minAmount);
	}
	public String getFormattedMaxAmount() {
		return Utils.formatGP(maxAmount);
	}
	public String getFormattedRate() {
		return Utils.formatPercentage(rate);
	}
	
	public int getMinAmount() {
		return minAmount;
	}

	public int getExtraAmount() {
		return maxAmount - minAmount;
	}

	public int getMaxAmount() {
		return maxAmount;
	}

	public int getItemId() {
		return itemId;
	}

	public double getRate() {
		return rate;
	}

	public void setItemId(short itemId) {
		this.itemId = itemId;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public void setMinAmount(int amount) {
		this.minAmount = amount;
	}

	public void setMaxAmount(int amount) {
		this.maxAmount = amount;
	}

	public boolean isFromRareTable() {
		return rare;
	}

}