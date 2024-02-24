package com.rs.database.mysql.impl.cart;

public enum CartItems {
	
	YELLOW_PHAT(1, "Yellow Partyhat", 30.00),
	EE_KITESHIELD(2, "Eagle-Eye kiteshield", 25.00),
	
	;
	
	private int id;
	private String name;
	private double value;
	
	CartItems(int id, String name, double value) {
		this.id = id;
		this.name= name;
		this.value = value;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public double getValue() {
		return value;
	}
	
	public static CartItems getItem(int id) {
		for (CartItems items : CartItems.values()) {
			if (items.getId() == id) {
				return items;
			}
		}
		return null;
	}
	
}
