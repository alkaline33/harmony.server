package com.rs.game.player.controlers;

public class RubberChicken extends Controler {

	@Override
	public void start() {
		if (player.getEquipment().getWeaponId() == 4566) {
		player.getAppearence().generateAppearenceData();
		player.getPackets().sendPlayerOption("Wack", 5, true);
		}
		else if (!(player.getEquipment().getWeaponId() == 4566)) {
			player.getAppearence().generateAppearenceData();
			player.getPackets().sendPlayerOption("null", 5, true);
		}
		
	}

	
}
