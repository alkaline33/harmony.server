package com.rs.game.player;

import java.util.TimerTask;

import com.rs.cores.CoresManager;
import com.rs.game.World;
import com.rs.utils.Colors;

public class PlayTime {
 
	private transient Player player;
	
	public PlayTime(Player player) {
		this.player = player;
	}
	public void startTimer() {
	
		
	CoresManager.fastExecutor.schedule(new TimerTask() {
		
			int timer = 60;
			@Override
			public void run() {
				if (timer == 1) {
					player.setPlayPoints(player.getPlayPoints() + 1);
					timer = 60;
					if (player.discordgenotifytimer > 0) {
						player.discordgenotifytimer -= 1;
					}
					if (player.fallyshieldprayertime > 0) {
						player.fallyshieldprayertime -= 1;
					}
					if (player.d60mxp1 > 0 && !player.isWeekend()) {
						player.d60mxp1 -= 1;
					}
					player.timeloggedintoday++;
					if (!player.isAFK()) {
						player.dailylootboxtimer --;
						if (player.dailylootboxtimer <= 0) {
							player.dailylootboxtimer = 120;
							player.sendMessage(Colors.orange+"Nice one! You just received a loot crate for playing!");
							if (World.TheCalamity(player)) {
								player.getBank().addItem(28965, 1, true);
							} else {
							player.getInventory().addItemDrop(28965, 1);
							}
						}
						//player.getPackets().sendIComponentText(3023, 2, "Loot box<br> in: "+player.dailylootboxtimer+"<br> mins");
						player.getPackets().sendIComponentText(751, 16, "Loot box<br> in: "+player.dailylootboxtimer+"");
						if (player.timeloggedintoday == 60 && player.dailyperkamount < 7) {
							player.dailyperkamount++;
							player.sendMessage("You have unlocked Daily Perk " + player.dailyperkamount + "!");
						}
					}
					if (player.silverhawks >= 1 && player.getEquipment().getBootsId() == 29922 && !player.isAFK()) {
						player.getSkills().addXp(Skills.AGILITY, 300);
						player.silverhawks -= 1;
					}
					
				}
				if (player.playdays >= 12 && player.veteran == false) {
					player.getBank().addItem(20763,1, true);
					player.getBank().addItem(20764,1, true);
					player.veteran = true;
					player.sendMessage("Well done, you've been awarded the veteran rank for playing 300 hours ingame time.");
					// UpdateActivities.Activities(player, null , 13, 4, 0); TODO
				}
				if (player.Playpoints >= 60) {
					player.playhours ++;
					player.Playpoints -= 60;
				}
				if (player.playhours >= 24) {
					player.playdays ++;
					player.playhours -= 24;
					player.dcapedart = true;
					if (player.isSuperDonator() || player.isExtremeDonator() || player.isLegendaryDonator() || player.isVIP() || player.isSponsor()) {
						player.superclaimedtoday = false;
						}
				}
				if (timer > 0) {
					if (player.instancedelay > 0) {
						player.instancedelay -= 1;
					}
					timer--;
				}
			}
		}, 0L, 1000L);
	}
}