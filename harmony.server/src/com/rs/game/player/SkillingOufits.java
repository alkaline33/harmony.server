package com.rs.game.player;


public class SkillingOufits {

	/**
	 * Xp Suits
	 * 
	 * @param skill
	 * @param exp
	 */

	/**
	 * XP modifier by wearing items.
	 *
	 * @param player
	 *            The player.
	 * @return the XP modifier.
	 */
	public static double chefsSuit(Player player) {
		double xpBoost = 1.0;
		if (player.getEquipment().getHatId() == 25180)
			xpBoost *= 1.01;
		if (player.getEquipment().getChestId() == 25181)
			xpBoost *= 1.01;
		if (player.getEquipment().getLegsId() == 25182)
			xpBoost *= 1.01;
		if (player.getEquipment().getBootsId() == 25183)
			xpBoost *= 1.01;
		if (player.getEquipment().getGlovesId() == 25184)
			xpBoost *= 1.01;
		if (player.getEquipment().getHatId() == 25180 && player.getEquipment().getChestId() == 25181 && player.getEquipment().getLegsId() == 25182 && player.getEquipment().getBootsId() == 25183 && player.getEquipment().getGlovesId() == 25184)
			xpBoost *= 1.01;
		return xpBoost;

	}

	public static double constructorOutfit(Player player) {
		double xpBoost = 1.0;
		if (player.getEquipment().getHatId() == 21446)
			xpBoost *= 1.01;
		if (player.getEquipment().getChestId() == 21447)
			xpBoost *= 1.01;
		if (player.getEquipment().getLegsId() == 21448)
			xpBoost *= 1.01;
		if (player.getEquipment().getBootsId() == 21450)
			xpBoost *= 1.01;
		if (player.getEquipment().getGlovesId() == 21449)
			xpBoost *= 1.01;
		if (player.getEquipment().getHatId() == 21446 && player.getEquipment().getChestId() == 21447 && player.getEquipment().getLegsId() == 21448 && player.getEquipment().getBootsId() == 21450 && player.getEquipment().getGlovesId() == 21449)
			xpBoost *= 1.01;
		return xpBoost;

	}

	public static double GoldenMining(Player player) {
		double xpBoost = 1.0;
		if (player.getEquipment().getHatId() == 20789)
			xpBoost *= 1.01;
		if (player.getEquipment().getChestId() == 20791)
			xpBoost *= 1.01;
		if (player.getEquipment().getLegsId() == 20790)
			xpBoost *= 1.01;
		if (player.getEquipment().getBootsId() == 20788)
			xpBoost *= 1.01;
		if (player.getEquipment().getGlovesId() == 20787)
			xpBoost *= 1.01;
		if (player.getEquipment().getHatId() == 20789 && player.getEquipment().getChestId() == 20791 && player.getEquipment().getLegsId() == 20790 && player.getEquipment().getBootsId() == 20788 && player.getEquipment().getGlovesId() == 20787)
			xpBoost *= 1.01;
		return xpBoost;

	}

	public static double Lumberjack(Player player) {
		double xpBoost = 1.0;
		if (player.getEquipment().getHatId() == 10941)
			xpBoost *= 1.01;
		if (player.getEquipment().getChestId() == 10939)
			xpBoost *= 1.01;
		if (player.getEquipment().getLegsId() == 10940)
			xpBoost *= 1.01;
		if (player.getEquipment().getBootsId() == 10933)
			xpBoost *= 1.01;
		if (player.getEquipment().getHatId() == 10941 && player.getEquipment().getChestId() == 10939 && player.getEquipment().getLegsId() == 10940 && player.getEquipment().getBootsId() == 10933)
			xpBoost *= 1.01;
		return xpBoost;

	}

	public static double Blacksmith(Player player) {
		double xpBoost = 1.0;
		if (player.getEquipment().getHatId() == 25195)
			xpBoost *= 1.01;
		if (player.getEquipment().getChestId() == 25196)
			xpBoost *= 1.01;
		if (player.getEquipment().getLegsId() == 25197)
			xpBoost *= 1.01;
		if (player.getEquipment().getBootsId() == 25198)
			xpBoost *= 1.01;
		if (player.getEquipment().getGlovesId() == 25199)
			xpBoost *= 1.01;
		if (player.getEquipment().getHatId() == 25195 && player.getEquipment().getChestId() == 25196 && player.getEquipment().getLegsId() == 25197 && player.getEquipment().getBootsId() == 25198 && player.getEquipment().getGlovesId() == 25199)
			xpBoost *= 1.01;
		return xpBoost;

	}

	public static double artisansBonus(Player player) {
		double xpBoost = 1.0;
		if (player.getEquipment().getHatId() == 25185)
			xpBoost *= 1.01;
		if (player.getEquipment().getChestId() == 25186)
			xpBoost *= 1.01;
		if (player.getEquipment().getLegsId() == 25187)
			xpBoost *= 1.01;
		if (player.getEquipment().getBootsId() == 25188)
			xpBoost *= 1.01;
		if (player.getEquipment().getGlovesId() == 25189)
			xpBoost *= 1.01;
		if (player.getEquipment().getHatId() == 25185 && player.getEquipment().getChestId() == 25186 && player.getEquipment().getLegsId() == 25187 && player.getEquipment().getBootsId() == 25188 && player.getEquipment().getGlovesId() == 25189)
			xpBoost *= 1.01;
		return xpBoost;
	}

	

	/**
	 * XP modifier by wearing items.
	 *
	 * @param player
	 *            The player.
	 * @return the XP modifier.
	 */
	public static double outfitBoost(Player player) {
		double xpBoost = 1.0;
		if (player.getEquipment().getHatId() == 21482)
			xpBoost *= 1.01;
		if (player.getEquipment().getChestId() == 21480)
			xpBoost *= 1.01;
		if (player.getEquipment().getLegsId() == 21481)
			xpBoost *= 1.01;
		if (player.getEquipment().getBootsId() == 21483)
			xpBoost *= 1.01;
		if (player.getEquipment().getHatId() == 21482 && player.getEquipment().getChestId() == 21480 && player.getEquipment().getLegsId() == 21481 && player.getEquipment().getBootsId() == 21483)
			xpBoost *= 1.01;
		return xpBoost;
	}

	public static double runecrafterSuit(Player player) {
		double xpBoost = 1.0;
		if (player.getEquipment().getHatId() == 21485)
			xpBoost *= 1.01;
		if (player.getEquipment().getChestId() == 21484)
			xpBoost *= 1.01;
		if (player.getEquipment().getLegsId() == 21486)
			xpBoost *= 1.01;
		if (player.getEquipment().getBootsId() == 21487)
			xpBoost *= 1.01;
		if (player.getEquipment().getHatId() == 21485 && player.getEquipment().getChestId() == 21484 && player.getEquipment().getLegsId() == 21486 && player.getEquipment().getBootsId() == 21487)
			xpBoost *= 1.01;
		return xpBoost;
	}

	public static double agileOutfit(Player player) {
		double xpBoost = 1.0;
		if (player.getEquipment().getChestId() == 14936)
			xpBoost *= 1.05;
		if (player.getEquipment().getLegsId() == 14938)
			xpBoost *= 1.05;
		return xpBoost;
	}

	public static double fishingSuit(Player player) {
		double xpBoost = 1.0;
		if (player.getEquipment().getHatId() == 24423)
			xpBoost *= 1.01;
		if (player.getEquipment().getChestId() == 24424)
			xpBoost *= 1.01;
		if (player.getEquipment().getLegsId() == 24425)
			xpBoost *= 1.01;
		if (player.getEquipment().getBootsId() == 24426)
			xpBoost *= 1.01;
		if (player.getEquipment().getHatId() == 24423 && player.getEquipment().getChestId() == 24424 && player.getEquipment().getLegsId() == 24425 && player.getEquipment().getBootsId() == 24426)
			xpBoost *= 1.01;
		return xpBoost;
	}

	public static double botanistOutfit(Player player) {
		double xpBoost = 1.0;
		if (player.getEquipment().getHatId() == 25190)
			xpBoost *= 1.01;
		if (player.getEquipment().getChestId() == 25191)
			xpBoost *= 1.01;
		if (player.getEquipment().getLegsId() == 25192)
			xpBoost *= 1.01;
		if (player.getEquipment().getBootsId() == 25193)
			xpBoost *= 1.01;
		if (player.getEquipment().getGlovesId() == 25194)
			xpBoost *= 1.01;
		if (player.getEquipment().getHatId() == 25190 && player.getEquipment().getChestId() == 25191 && player.getEquipment().getLegsId() == 25192 && player.getEquipment().getGlovesId() == 25194 && player.getEquipment().getBootsId() == 25193)
			xpBoost *= 1.01;
		return xpBoost;
	}

	/**
	 * End of Xp Suits
	 * 
	 * @param skill
	 * @param exp
	 */

}
