package com.rs.game.player.actions.crafting;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

public class OrbCharging {

	public final static Animation CHARGING = new Animation(726);
	
	public enum Orbs {
		
		WATER(567, 56, 555, 30, 564, 3, 66, 571, 1383, 149),
		EARTH(567, 60, 557, 30, 564, 3, 70, 575, 1385, 151),
		FIRE(567, 63, 554, 30, 564, 3, 73, 569, 1387, 152),
		AIR(567, 66, 556, 30, 564, 3, 76, 573, 1381, 150);
		
		private int uncharged, level, rune, runeAmount, rune2, runeAmount2, orb, staff, gfx;
		private double xp;
		
		Orbs(int uncharged, int level, int rune, int runeAmount, int rune2, int runeAmount2, double xp, int orb, int staff, int gfx) {
			this.uncharged = uncharged;
			this.level = level;
			this.rune = rune;
			this.runeAmount = runeAmount;
			this.rune2 = rune2;
			this.runeAmount2 = runeAmount2;
			this.xp = xp;
			this.orb = orb;
			this.staff = staff;
			this.gfx = gfx;
		}
		
		public int getUncharged() {
			return uncharged;
		}
		
		public int getLevel() {
			return level;
		}
		
		public int getRune() {
			return rune;
		}
		
		public int getRuneAmount() {
			return runeAmount;
		}
		
		public int getRune2() {
			return rune2;
		}
		
		public int getRuneAmount2() {
			return runeAmount2;
		}
		
		public double getXp() {
			return xp;
		}
		
		public int getOrb() {
			return orb;
		}
		
		public int getStaff() {
			return staff;
		}
		
		public int getGFX() {
			return gfx;
		}
	}
	
	public Orbs orbs;
	public OrbCharging charging;
	
	public static void orbCharge(Player player, Orbs orbs) {
		int i = player.getInventory().getNumerOf(orbs.getUncharged());
			//if (player.getSkills().getLevel(Skills.MAGIC) <= orbs.getLevel()) {
			if (player.getInventory().containsItem(orbs.getRune2(), orbs.getRuneAmount2())) {
			if (player.getInventory().containsItem(orbs.getRune(), orbs.getRuneAmount())
			|| player.getEquipment().getWeaponId() == orbs.getStaff()) {
			if (player.getInventory().contains(orbs.getUncharged())) {
				player.getInventory().deleteItem(orbs.getRune2(), orbs.getRuneAmount2()*i);
				player.getInventory().deleteItem(orbs.getUncharged(), i);
				if (!(player.getEquipment().getWeaponId() == orbs.getStaff())) {
					player.getInventory().deleteItem(orbs.getRune(), orbs.getRuneAmount()*i);
				}
				player.getInventory().addItem(orbs.getOrb(), i);
				player.getSkills().addXp(Skills.MAGIC, orbs.getXp()*i);
				player.setNextAnimation(CHARGING);
				player.setNextGraphics(new Graphics(orbs.getGFX(), 0, 100));
			} else
				player.sm("You have no orbs to charge.");
			} else
				player.sm("You must have "+orbs.getRuneAmount()+""
						+ " "+ItemDefinitions.getItemDefinitions(orbs.getRune()).getName().toLowerCase()+""
								+ " to charge a "
								+ ""+ItemDefinitions.getItemDefinitions(orbs.getOrb()).getName().toLowerCase()+".");
			} else
				player.sm("You must have "+orbs.getRuneAmount2()+""
						+ " "+ItemDefinitions.getItemDefinitions(orbs.getRune2()).getName().toLowerCase()+""
								+ " to charge a "
								+ ""+ItemDefinitions.getItemDefinitions(orbs.getOrb()).getName().toLowerCase()+".");
			//} else
				//player.sm("You need a Magic level of "+orbs.getLevel()+" to charge a "+ItemDefinitions.getItemDefinitions(orbs.getOrb()).getName().toLowerCase()+".");
		}
	}