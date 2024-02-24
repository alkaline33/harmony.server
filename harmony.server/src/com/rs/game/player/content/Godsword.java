package com.rs.game.player.content;

import com.rs.game.player.Player;

/**
 * 
 * @author Taylor Moon<Axter>
 * Skype: axter12345
 */
public enum Godsword {

	ARMADYL(11694, 11702), ZAMORAK(11700, 11708), BANDOS(11696, 11704), 
	SARADOMIN(11698, 11706);

	/**
	 * Godsword components
	 */
	private int godSwordId;
	private int godSwordHilt;
	
	/**
	 * The godsword blade
	 */
	private static final int BLADE = 11690;

	/**
	 * Defines the godsword type
	 * @param gsId - the first index in the enum array
	 * @param hilt - the second index in the enum array
	 */
	Godsword(int gsId, int hilt) {
		godSwordId = gsId;
		godSwordHilt = hilt;
	}

	/**
	 * Dismantles a godsword
	 * @param player - player dismantling
	 * @param gsId - godsword to dismantle
	 */
	public static void dismantle(Player player, int gsId) {
		if(!player.getInventory().containsOneItem(gsId))//incase stall, etc.
			return;
		player.lock();
		switch (gsId) {
		case 11694:
			player.getInventory().deleteItem(ARMADYL.godSwordId, 1);
			player.getInventory().addItem(ARMADYL.godSwordHilt, 1);
			break;
		case 11700:
			player.getInventory().deleteItem(ZAMORAK.godSwordId, 1);
			player.getInventory().addItem(ZAMORAK.godSwordHilt, 1);
			break;
		case 11696:
			player.getInventory().deleteItem(BANDOS.godSwordId, 1);
			player.getInventory().addItem(BANDOS.godSwordHilt, 1);
			break;
		case 11698:
			player.getInventory().deleteItem(SARADOMIN.godSwordId, 1);
			player.getInventory().addItem(SARADOMIN.godSwordHilt, 1);
			break;
			default:
				player.out(player.getRights() == 2 ? "GODSWORD ACTION: DISMANTLE, ID: " + gsId + "" 
						: "Nothing interesting happens.");
		}
		player.unlock();
		player.getInventory().addItem(BLADE, 1);
	}

	/**
	 * Assembles a godsword blade with a godsword hilt
	 * @param player - player assembling
	 * @param hilt - the hilt to be used on the blade
	 */
	public static void assemble(Player player, int hilt) {
		if (!player.getInventory().containsItem(hilt, 1) && !player.getInventory().containsItem(BLADE, 1))
			return;
		switch(hilt) {
		case 11702:
			//if (player.getInventory().containsItem(11702, 1)) {
			player.getInventory().deleteItem(ARMADYL.godSwordHilt, 1);	
			player.getInventory().addItem(ARMADYL.godSwordId, 1);
			break;
			//}
		case 11708:
			//if (player.getInventory().containsItem(11708, 1)) {
			player.getInventory().deleteItem(ZAMORAK.godSwordHilt, 1);
			player.getInventory().addItem(ZAMORAK.godSwordId, 1);
			break;
			//}
		case 11704:
			if (player.getInventory().containsItem(11704, 1)) {
			player.getInventory().deleteItem(BANDOS.godSwordHilt, 1);
			player.getInventory().addItem(BANDOS.godSwordId, 1);
			break;
			}
		case 11706:
			//if (player.getInventory().containsItem(11706, 1)) {
			player.getInventory().deleteItem(SARADOMIN.godSwordHilt, 1);
			player.getInventory().addItem(SARADOMIN.godSwordId, 1);
			break;
			//}
		default:
			player.out(player.getRights() == 2 ? "GODSWORD ACTION: ASSEMBLE, ID: " + hilt + "" 
					: "Nothing interesting happens.");
		}
		player.getInventory().deleteItem(BLADE, 1);
	}

	
	/**
	 * A method returning the godsword id from
	 * the enum instance
	 * @return - godsword's id
	 */
	public int getGodswordId() {
		int gsid = godSwordId;
		return gsid;
	}

	/**
	 * A method returning the godsword hilt from
	 * the enum instance
	 * @return - the godsword hilt
	 */
	public int getGodswordHilt() {
		return godSwordHilt;
	}
	
	/**
	 * Returns the constant 'Blade'
	 * @return - blade
	 */
	public static int getBlade() {
		return BLADE;
	}

}