package com.rs.game.npc.playersystems;

import com.rs.game.npc.NPC;
import com.rs.game.player.Player;

/**
*
* @author Kryeus / John J. Woloszyk
* @date 10.29.2017
* 
*/

public class CalamityRewards {

	
	static Player p;
	static NPC n;
	public static void processKill(Player p, NPC n) 
	{
		CalamityRewards.p = p;
		CalamityRewards.n = n;
		int npcID = n.getId();
		switch(npcID) 
		{
		case 30200:
		case 30201:
		case 30202:
			proc20pt();
			break;
		case 30203:
		case 30206:
		case 30207:
		case 30208:
			proc30pt();
			break;
		case 30211:
		case 30212:
		case 30213:
		case 30204:
		case 30209:
			proc35pt();
			break;
		case 30210:
		case 30215:
			proc55pt();
			break;
		case 30205:
			proc75pt();
			break;
		case 30214:
			proc140pt();
			break;
		default: break;
		}
	}
	
	public static void proc20pt()
	{
		if (p.doublekillpointscalamity == true) {
			p.calamitykillpoints += 40;
		} else if (p.doublekillpointscalamity != true) {
			p.calamitykillpoints += 20;
		}
	}
	
	public static void proc30pt()
	{
		if (p.doublekillpointscalamity == true) {
			p.calamitykillpoints += 60;
		} else if (p.doublekillpointscalamity != true) {
			p.calamitykillpoints += 30;
		}
	}
	
	public static void proc35pt()
	{
		if (p.doublekillpointscalamity == true) {
			p.calamitykillpoints += 70;
		} else if (p.doublekillpointscalamity != true) {
			p.calamitykillpoints += 35;
		}
	}
	
	public static void proc55pt()
	{
		if (p.doublekillpointscalamity == true) {
			p.calamitykillpoints += 110;
		} else if (p.doublekillpointscalamity != true) {
			p.calamitykillpoints += 55;
		}
	}
	
	public static void proc75pt()
	{
		if (p.doublekillpointscalamity == true) {
			p.calamitykillpoints += 150;
		} else if (p.doublekillpointscalamity != true) {
			p.calamitykillpoints += 75;
		}
	}
	
	public static void proc140pt()
	{
		if (p.doublekillpointscalamity == true) {
			p.calamitykillpoints += 280;
		} else if (p.doublekillpointscalamity != true) {
			p.calamitykillpoints += 140;
		}
	}
	
}
