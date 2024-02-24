package com.rs.game.player;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.dialogues.impl.LevelUp;
import com.rs.utils.Utils;

public final class Skills implements Serializable {

	private static final long serialVersionUID = -7086829989489745985L;

	public static final double MAXIMUM_EXP = 360000000L;
	public static final int ATTACK = 0, DEFENCE = 1, STRENGTH = 2, HITPOINTS = 3, RANGE = 4, PRAYER = 5, MAGIC = 6, COOKING = 7, WOODCUTTING = 8, FLETCHING = 9, FISHING = 10, FIREMAKING = 11, CRAFTING = 12, SMITHING = 13, MINING = 14, HERBLORE = 15, AGILITY = 16, THIEVING = 17, SLAYER = 18, FARMING = 19, RUNECRAFTING = 20, CONSTRUCTION = 22, HUNTER = 21, SUMMONING = 23, DUNGEONEERING = 24;

	public static final String[] SKILL_NAME = { "Attack", "Defence", "Strength", "Constitution", "Ranged", "Prayer", "Magic", "Cooking", "Woodcutting", "Fletching", "Fishing", "Firemaking", "Crafting", "Smithing", "Mining", "Herblore", "Agility", "Thieving", "Slayer", "Farming", "Runecrafting", "Hunter", "Construction", "Summoning", "Dungeoneering" };

	public short level[];
	private double xp[];
	private double[] xpTracks;
	private boolean[] trackSkills;
	private byte[] trackSkillsIds;
	private boolean xpDisplay, xpPopup;

	private int skillp;

	// AdvLog :D
	public boolean[] levelUpMilestones = new boolean[10];
	public boolean[] totalLevelMilestones = new boolean[5];

	private transient int currentCounter;
	private transient Player player;

	public void passLevels(Player p) {
		level = p.getSkills().level;
		xp = p.getSkills().xp;
	}

	public Skills() {
		level = new short[25];
		xp = new double[25];
		for (int i = 0; i < level.length; i++) {
			level[i] = 1;
			xp[i] = 0;
		}
		level[3] = 10;
		xp[3] = 1184;
		level[HERBLORE] = 3;
		xp[HERBLORE] = 250;
		xpPopup = true;
		xpTracks = new double[3];
		trackSkills = new boolean[3];
		trackSkillsIds = new byte[3];
		trackSkills[0] = true;
		for (int i = 0; i < trackSkillsIds.length; i++) {
			trackSkillsIds[i] = 30;
		}

	}

	public void sendXPDisplay() {
		for (int i = 0; i < trackSkills.length; i++) {
			player.getPackets().sendConfigByFile(10444 + i, trackSkills[i] ? 1 : 0);
			player.getPackets().sendConfigByFile(10440 + i, trackSkillsIds[i] + 1);
			refreshCounterXp(i);
		}
	}

	public void setupXPCounter() {
		player.getInterfaceManager().sendXPDisplay(1214);
	}

	public void refreshCurrentCounter() {
		player.getPackets().sendConfig(2478, currentCounter + 1);
	}

	public void setCurrentCounter(int counter) {
		if (counter != currentCounter) {
			currentCounter = counter;
			refreshCurrentCounter();
		}
	}

	public void switchTrackCounter() {
		trackSkills[currentCounter] = !trackSkills[currentCounter];
		player.getPackets().sendConfigByFile(10444 + currentCounter, trackSkills[currentCounter] ? 1 : 0);
	}

	public void resetCounterXP() {
		xpTracks[currentCounter] = 0;
		refreshCounterXp(currentCounter);
	}

	public void setCounterSkill(int skill) {
		xpTracks[currentCounter] = 0;
		trackSkillsIds[currentCounter] = (byte) skill;
		player.getPackets().sendConfigByFile(10440 + currentCounter, trackSkillsIds[currentCounter] + 1);
		refreshCounterXp(currentCounter);
	}

	public void refreshCounterXp(int counter) {
		player.getPackets().sendConfig(counter == 0 ? 1801 : 2474 + counter, (int) (xpTracks[counter] * 10));
	}

	public void handleSetupXPCounter(int componentId) {
		if (componentId == 18) {
			player.getInterfaceManager().sendXPDisplay();
		} else if (componentId >= 22 && componentId <= 24) {
			setCurrentCounter(componentId - 22);
		} else if (componentId == 27) {
			switchTrackCounter();
		} else if (componentId == 61) {
			resetCounterXP();
		} else if (componentId >= 31 && componentId <= 57) {
			if (componentId == 33) {
				setCounterSkill(4);
			} else if (componentId == 34) {
				setCounterSkill(2);
			} else if (componentId == 35) {
				setCounterSkill(3);
			} else if (componentId == 42) {
				setCounterSkill(18);
			} else if (componentId == 49) {
				setCounterSkill(11);
			} else {
				setCounterSkill(componentId >= 56 ? componentId - 27 : componentId - 31);
			}
		}

	}

	public void restoreSummoning() {
		level[23] = (short) getLevelForXp(23);
		refresh(23);
	}

	public void sendInterfaces() {
		if (xpDisplay) {
			player.getInterfaceManager().sendXPDisplay();
		}
		if (xpPopup) {
			player.getInterfaceManager().sendXPPopup();
		}
	}

	public void switchXPDisplay() {
		xpDisplay = !xpDisplay;
		if (xpDisplay) {
			player.getInterfaceManager().sendXPDisplay();
		} else {
			player.getInterfaceManager().closeXPDisplay();
		}
	}

	public void switchXPPopup() {
		xpPopup = !xpPopup;
		player.getPackets().sendGameMessage("XP pop-ups are now " + (xpPopup ? "en" : "dis") + "abled.");
		if (xpPopup) {
			player.getInterfaceManager().sendXPPopup();
		} else {
			player.getInterfaceManager().closeXPPopup();
		}
	}

	public void restoreSkills() {
		for (int skill = 0; skill < level.length; skill++) {
			level[skill] = (short) getLevelForXp(skill);
			refresh(skill);
		}
	}

	public void setPlayer(Player player) {
		this.player = player;
		// temporary
		if (xpTracks == null) {
			xpPopup = true;
			xpTracks = new double[3];
			trackSkills = new boolean[3];
			trackSkillsIds = new byte[3];
			trackSkills[0] = true;
			for (int i = 0; i < trackSkillsIds.length; i++) {
				trackSkillsIds[i] = 30;
			}
		}
	}

	public short[] getLevels() {
		return level;
	}

	public double[] getXp() {
		return xp;
	}

	public int getLevel(int skill) {
		return level[skill];
	}

	public double getXp(int skill) {
		return xp[skill];
	}

	public boolean hasRequiriments(int... skills) {
		for (int i = 0; i < skills.length; i += 2) {
			int skillId = skills[i];
			if (skillId == CONSTRUCTION || skillId == FARMING) {
				continue;
			}
			int skillLevel = skills[i + 1];
			if (getLevelForXp(skillId) < skillLevel) {
				return false;
			}

		}
		return true;
	}

	public int getCombatLevel() {
		int attack = getLevelForXp(0);
		int defence = getLevelForXp(1);
		int strength = getLevelForXp(2);
		int hp = getLevelForXp(3);
		int prayer = getLevelForXp(5);
		int ranged = getLevelForXp(4);
		int magic = getLevelForXp(6);
		int combatLevel = 3;
		combatLevel = (int) ((defence + hp + Math.floor(prayer / 2)) * 0.25) + 1;
		double melee = (attack + strength) * 0.325;
		double ranger = Math.floor(ranged * 1.5) * 0.325;
		double mage = Math.floor(magic * 1.5) * 0.325;
		if (melee >= ranger && melee >= mage) {
			combatLevel += melee;
		} else if (ranger >= melee && ranger >= mage) {
			combatLevel += ranger;
		} else if (mage >= melee && mage >= ranger) {
			combatLevel += mage;
		}
		return combatLevel;
	}

	public void set(int skill, int newLevel) {
		level[skill] = (short) newLevel;
		refresh(skill);
	}

	public int drainLevel(int skill, int drain) {
		int drainLeft = drain - level[skill];
		if (drainLeft < 0) {
			drainLeft = 0;
		}
		level[skill] -= drain;
		if (level[skill] < 0) {
			level[skill] = 0;
		}
		refresh(skill);
		return drainLeft;
	}

	public int getCombatLevelWithSummoning() {
		return getCombatLevel() + getSummoningCombatLevel();
	}

	public int getSummoningCombatLevel() {
		return getLevelForXp(Skills.SUMMONING) / 8;
	}

	public void drainSummoning(int amt) {
		int level = getLevel(Skills.SUMMONING);
		if (level == 0) {
			return;
		}
		set(Skills.SUMMONING, amt > level ? 0 : level - amt);
	}

	public static int getXPForLevel(int level) {
		int points = 0;
		int output = 0;
		for (int lvl = 1; lvl <= level; lvl++) {
			points += Math.floor(lvl + 300.0 * Math.pow(2.0, lvl / 7.0));
			if (lvl >= level) {
				return output;
			}
			output = (int) Math.floor(points / 4);
		}
		return 0;
	}

	public int getLevelForXp(int skill) {
		double exp = xp[skill];
		int points = 0;
		int output = 0;
		for (int lvl = 1; lvl <= (skill == DUNGEONEERING ? 120 : 99); lvl++) {
			points += Math.floor(lvl + 300.0 * Math.pow(2.0, lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if (output - 1 >= exp) {
				return lvl;
			}
		}
		return skill == DUNGEONEERING ? 120 : 99;
	}

	public void init() {
		for (int skill = 0; skill < level.length; skill++) {
			refresh(skill);
		}
		sendXPDisplay();
	}

	public void refresh(int skill) {
		player.getPackets().sendSkillLevel(skill);
	}

	/*
	 * if(componentId == 33) setCounterSkill(4); else if(componentId == 34)
	 * setCounterSkill(2); else if(componentId == 35) setCounterSkill(3); else
	 * if(componentId == 42) setCounterSkill(18); else if(componentId == 49)
	 * setCounterSkill(11);
	 */

	public int getCounterSkill(int skill) {
		switch (skill) {
		case ATTACK:
			return 0;
		case STRENGTH:
			return 1;
		case DEFENCE:
			return 4;
		case RANGE:
			return 2;
		case HITPOINTS:
			return 5;
		case PRAYER:
			return 6;
		case AGILITY:
			return 7;
		case HERBLORE:
			return 8;
		case THIEVING:
			return 9;
		case CRAFTING:
			return 10;
		case MINING:
			return 12;
		case SMITHING:
			return 13;
		case FISHING:
			return 14;
		case COOKING:
			return 15;
		case FIREMAKING:
			return 16;
		case WOODCUTTING:
			return 17;
		case SLAYER:
			return 19;
		case FARMING:
			return 20;
		case CONSTRUCTION:
			return 21;
		case HUNTER:
			return 22;
		case SUMMONING:
			return 23;
		case DUNGEONEERING:
			return 24;
		case MAGIC:
			return 3;
		case FLETCHING:
			return 18;
		case RUNECRAFTING:
			return 11;
		default:
			return -1;
		}

	}

	public void addXp(int skill, double exp) {
		int calcbonus = 0;
		player.getControlerManager().trackXP(skill, (int) exp);
		if (exp == 0) {
			return;
		}
		if (player.isXpLocked()) {
			return;
		}
		if (skill == ATTACK || skill == STRENGTH || skill == HITPOINTS || skill == PRAYER || skill == SUMMONING || skill == RANGE || skill == MAGIC || skill == DEFENCE) {
		if (player.isCbXpLocked()) {
			return;
		}
		}
		if (skill == 4 && player.getEquipment().getWeaponId() == 29472) {
			exp /= 10;
		}
		exp *= player.isEasy() ? 125.0 : player.isAverage() ? 70.0 : player.isHard() ? 25.0 : player.isHeroic() ? 3.0 : 1.0;
		int expnow = (int) exp;
		/** Equipment experience increases **/
		if (skill == Skills.AGILITY) {
			exp *= SkillingOufits.agileOutfit(player);
		}
		if (skill == Skills.THIEVING) {
			exp *= SkillingOufits.outfitBoost(player);
		}
		if (skill == Skills.RUNECRAFTING) {
			exp *= SkillingOufits.runecrafterSuit(player);
		}
		if (skill == COOKING) {
			exp *= SkillingOufits.chefsSuit(player);
		}
		if (skill == CRAFTING) {
			exp *= SkillingOufits.artisansBonus(player);
		}
		if (skill == CONSTRUCTION) {
			exp *= SkillingOufits.constructorOutfit(player);
		}
		if (skill == FISHING) {
			exp *= SkillingOufits.fishingSuit(player);
		}
		if (skill == HERBLORE) {
			exp *= SkillingOufits.botanistOutfit(player);
		}
		if (skill == MINING) {
			exp *= SkillingOufits.GoldenMining(player);
		}
		if (skill == SMITHING) {
			exp *= SkillingOufits.Blacksmith(player);
		}
		if (skill == WOODCUTTING) {
			exp *= SkillingOufits.Lumberjack(player);
		}
		if (player.isWeekend() || Settings.WELLDOUBLE == true || player.isHappyHour()) {
			exp *= 2;
		}
		if (World.DryVsSuns(player)) {
			exp /= 10;
		}
		if (player.getAuraManager().usingFestive()) {
			exp *= 2;
		}
		if (player.d60mxp1 > 0 && !player.isWeekend() && !player.getAuraManager().usingFestive()) {
			exp *= 2;
		}
		if (player.dailyperkamount >= 2) {
			exp *= 1.05;
		}
		if (player.dailyperkamount >= 4) {
			exp *= 1.05;
		}
		if (player.ddxp == true) {
			exp *= 2;
		}
		for (int bosspet : Settings.PETS_WITH_PERKS) {
			if (player.getPetManager().getNpcId() == bosspet && player.getPetPerk().getContainer().contains(new Item(29395))) {
				exp *= 1.01;
			}
			if (player.getPetManager().getNpcId() == bosspet && player.getPetPerk().getContainer().contains(new Item(29394))) {
				exp *= 1.02;
			}
			if (player.getPetManager().getNpcId() == bosspet && player.getPetPerk().getContainer().contains(new Item(29393))) {
				exp *= 1.04;
			}
		}
		if (World.DZ3(player)) {
			exp *= 1.1;
		}
		if (World.DZ4(player)) {
			exp *= 1.2;
		}
		if (World.DZ5(player)) {
			exp *= 1.4;
		}
		if (World.Level3Zone(player)) {
			exp *= 1.5;
		}
		if (player.getEquipment().getRingId() == 28930) {
			exp *= 1.1;
		}
		if (player.getEquipment().getShieldId() == 28918) {
			exp *= 1.1;
		}
		if (player.getEquipment().getRingId() == 28908) {
			exp *= 1.5;
		}
		if (World.CommitzoClanZone(player)) {
			exp *= player.isSponsor() ? 1.4
					: player.isVIP() ? 1.2
							: player.isLegendaryDonator() ? 1.15
									: player.isUltimateDonator() ? 1.1 
											: player.isExtremeDonator() ? 1.05 
													: 1;
		}
		if (World.PrestigeZone(player)) {
			exp *= player.isSponsor() ? 1.45 : player.isVIP() ? 1.25 : 1.05;
		}
		if (skill != ATTACK && skill != DEFENCE && skill != STRENGTH && skill != MAGIC && skill != RANGE && skill != HITPOINTS && !player.isEasy()) {
			if (player.getAuraManager().usingWisdom()) {
				exp *= 1.025;
			}
		}
		if (skill == ATTACK || skill == DEFENCE || skill == STRENGTH || skill == RANGE) {
			exp *= 2 * Settings.COMBAT_XP_RATE;
		}
		if (skill == MAGIC) {
			exp *= 2 * Settings.COMBAT_XP_RATE;
		}
		if (skill == HITPOINTS) {
			exp *= 2 * Settings.COMBAT_XP_RATE;
		}
		int oldLevel = getLevelForXp(skill);
		int oldxp = (int) xp[skill];
		xp[skill] += exp;
		// System.out.println("4");
		for (int i = 0; i < trackSkills.length; i++) {
			if (trackSkills[i]) {
				if (trackSkillsIds[i] == 30 || trackSkillsIds[i] == 29 && (skill == Skills.ATTACK || skill == Skills.DEFENCE || skill == Skills.STRENGTH || skill == Skills.MAGIC || skill == Skills.RANGE || skill == Skills.HITPOINTS) || trackSkillsIds[i] == getCounterSkill(skill)) {
					 xpTracks[i] += exp;
					refreshCounterXp(i);
					Settings.serverxp += exp;
					if (player.partofclan == true) {
						player.myclanxp += exp;
					}
					if (player.isWeekend()) {
						player.weekendxp += exp;
					}
				}
			}
		}
		// System.out.println(exp);
		if (skill != ATTACK && skill != PRAYER && skill != SUMMONING && skill != DEFENCE && skill != RANGE && skill != MAGIC && skill != STRENGTH && skill != HITPOINTS) {
			skillp = Utils.random(World.Level3Zone(player) ? 2 : 3);
			switch (skillp) {
			case 0:
			case 2:
			case 3:
				break;
			default:
				if (World.WildySkilling(player)) {
					skillp = 2;
				}
				if (player.getEquipment().getRingId() == 28908) {
					skillp = 2;
				}
				player.skillpoints += skillp;
				player.getPackets().sendGameMessage("You have recieved " + skillp + " skilling point.", true);
				break;
			}
		}
		if (xp[skill] > MAXIMUM_EXP) {
			xp[skill] = MAXIMUM_EXP;
		}
		if (oldxp != 200000000 && xp[skill] == 200000000) {
			LevelUp.send200m(player, skill);
		}
		if (oldxp != 300000000 && xp[skill] == 300000000) {
			LevelUp.send300m(player, skill);
		}
		if (oldxp != 360000000 && xp[skill] == 360000000) {
			LevelUp.send360m(player, skill);
		}
		if (oldxp < 104273167 && xp[skill] > 104273167) {
			LevelUp.send104m(player, skill);
		}
		// System.out.println("1");
		int newLevel = getLevelForXp(skill);
		int levelDiff = newLevel - oldLevel;
		if (newLevel > oldLevel) {
			level[skill] += levelDiff;
			player.getDialogueManager().startDialogue("LevelUp", skill);
			updateAdvlog(skill);
			if (skill == SUMMONING || skill >= ATTACK && skill <= MAGIC) {
				player.getAppearence().generateAppearenceData();
				if (skill == HITPOINTS) {
					player.heal(levelDiff * 10);
				} else if (skill == PRAYER) {
					player.getPrayer().restorePrayer(levelDiff * 10);
				}
			}
			player.getQuestManager().checkCompleted();
		}
		// System.out.println("2");
		refresh(skill);
		calcbonus = (int) (exp - expnow);
		if (skill != HITPOINTS) {
			player.getPackets().sendConfig(2044, calcbonus * 10);
			// System.out.println("3");
		}
	}

	public void addSkillXpRefresh(int skill, double xp) {
		this.xp[skill] += xp;
		level[skill] = (short) getLevelForXp(skill);
	}

	public static int getTotalLevel(Player player) {
		int totallevel = 0;
		for (int i = 0; i <= 24; i++) {
			totallevel += player.getSkills().getLevelForXp(i);
		}
		return totallevel;
	}

	public long getTotalExp() {
		long total = 0;
		for (double xp : getXp()) {
			total += (int) xp;
		}
		return total;
	}

	public static String getTotalXp(Player player) {
		double totalxp = 0;
		for (double xp : player.getSkills().getXp()) {
			totalxp += xp;
		}
		NumberFormat formatter = new DecimalFormat("#######");
		return formatter.format(totalxp);
	}

	public void resetSkillNoRefresh(int skill) {
		xp[skill] = 0;
		level[skill] = 1;
	}

	public void setXp(int skill, double exp) {
		xp[skill] = exp;
		refresh(skill);

	}

	public static String getMiningLevel() {

		return getMiningLevel();
	}

	public void updateAdvlog(int skill) {
		// UpdateActivities.Activities(player, null , 3, skill, getLevelForXp(skill));
		if (levelUpMilestones == null) {
			levelUpMilestones = new boolean[10];
		}
		if (totalLevelMilestones == null) {
			totalLevelMilestones = new boolean[5];
		}
		for (int i = 1; i < levelUpMilestones.length; i++) {
			if (allLevelsOver(i * 10) && !levelUpMilestones[i]) {
				// UpdateActivities.Activities(player, null , 4, skill, getLevelForXp(skill));
				levelUpMilestones[i] = true;
			}
		}
		for (int i = 1; i < totalLevelMilestones.length; i++) {
			if (getTotalLevel() >= i * 500 && !totalLevelMilestones[i] && getTotalLevel() < 2496) {
				// UpdateActivities.Activities(player, null , 5, (i * 500), 0);
				totalLevelMilestones[i] = true;
			}
		}
	}

	public boolean allLevelsOver(int lvl) {
		for (int i = 0; i <= 24; i++) {
			if (level[i] < lvl) {
				return false;
			}
		}
		return true;
	}

	public int getTotalLevel() {
		int totalLevel = 0;
		for (int i = 0; i < level.length; i++) {
			totalLevel += getLevelForXp(i);
		}
		return totalLevel;
	}

	public double addXpLamp(int skill, double exp) {
		// System.out.println(exp);
		exp *= player.isEasy() ? 125.0 : player.isAverage() ? 70.0 : player.isHard() ? 25.0 : 3.0;
		if (player.ddxp == true) {
			exp *= 2;
		}
		player.getControlerManager().trackXP(skill, (int) exp);
		if (player.isXpLocked()) {
			return 0;
		}
		exp *= 1;
		int oldxp = (int) xp[skill];
		int oldLevel = getLevelForXp(skill);
		xp[skill] += exp;
		for (int i = 0; i < trackSkills.length; i++) {
			if (trackSkills[i]) {
				if (trackSkillsIds[i] == 30 || trackSkillsIds[i] == 29 && (skill == Skills.ATTACK || skill == Skills.DEFENCE || skill == Skills.STRENGTH || skill == Skills.MAGIC || skill == Skills.RANGE || skill == Skills.HITPOINTS) || trackSkillsIds[i] == getCounterSkill(skill)) {
					xpTracks[i] += exp;
					refreshCounterXp(i);
				}
			}
		}

		if (xp[skill] > MAXIMUM_EXP) {
			xp[skill] = MAXIMUM_EXP;
		}
		if (oldxp != 200000000 && xp[skill] == 200000000) {
			LevelUp.send200m(player, skill);
		}
		if (oldxp != 300000000 && xp[skill] == 300000000) {
			LevelUp.send300m(player, skill);
		}
		if (oldxp != MAXIMUM_EXP && xp[skill] == MAXIMUM_EXP) {
			LevelUp.send360m(player, skill);
		}
		if (oldxp < 104273167 && xp[skill] > 104273167) {
			LevelUp.send104m(player, skill);
		}
		int newLevel = getLevelForXp(skill);
		int levelDiff = newLevel - oldLevel;
		if (newLevel > oldLevel) {
			level[skill] += levelDiff;
			player.getDialogueManager().startDialogue("LevelUp", skill);
			if (skill == SUMMONING || skill >= ATTACK && skill <= MAGIC) {
				player.getAppearence().generateAppearenceData();
				if (skill == HITPOINTS) {
					player.heal(levelDiff * 10);
				} else if (skill == PRAYER) {
					player.getPrayer().restorePrayer(levelDiff * 10);
				}
			}
			player.getQuestManager().checkCompleted();
		}
		refresh(skill);
		return exp;
	}
}