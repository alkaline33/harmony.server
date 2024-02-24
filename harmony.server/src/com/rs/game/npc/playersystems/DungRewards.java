package com.rs.game.npc.playersystems;

import com.rs.Settings;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.DailyChallenges;
import com.rs.utils.Utils;

/**
*
* @author Kryeus / John J. Woloszyk
* @date 10.29.2017
* 
*/

public class DungRewards {

	static Player p;
	static NPC n;
	public static void processKill(Player p, NPC n)
	{
		DungRewards.p = p;
		DungRewards.n = n;
		int npcID = n.getId();
		switch(npcID) {
		case 11750: hl(); break;
		case 9766: ml(); break;
		case 9911: ll(); break;
		default: break;
		}
	}
	
	public static void hl() {
		int level = p.getSkills().getLevelForXp(Skills.DUNGEONEERING);
		double xpnow = 0;
		double xpb4 = 0;
		xpb4 = p.getSkills().getXp(Skills.DUNGEONEERING);
		p.getSkills().addXp(Skills.DUNGEONEERING, 500 * level / 10);
		if (p.challengeid == 10 && p.challengeid > 0) {
			DailyChallenges.UpdateChallenge(p);
		}
		if (p.dungperk) {
			level *= 2;
		}
		p.dungpoints += 50 * level;
		int pointsgained = 50 * level;
		//p.sendDeath(n);
		p.setNextWorldTile(Settings.RESPAWN_PLAYER_LOCATION);
		xpnow = p.getSkills().getXp(Skills.DUNGEONEERING);
		long boo = (long) (xpnow - xpb4);
		p.sendMessage("<col=660066>You have gained " + Utils.format(boo) + " experience and " + pointsgained
				+ " tokens.");
		// SeasonEvent.HandleActivity(p, "Dungeoneering");
	}
	
	public static void ml() {
		int level = p.getSkills().getLevelForXp(Skills.DUNGEONEERING);
		double xpnow = 0;
		double xpb4 = 0;
		xpb4 = p.getSkills().getXp(Skills.DUNGEONEERING);
		p.getSkills().addXp(Skills.DUNGEONEERING, 1000 * level / 10);
		if (p.challengeid == 11 && p.challengeid > 0) {
			DailyChallenges.UpdateChallenge(p);
		}
		if (p.dungperk) {
			level *= 2;
		}
		p.dungpoints += 100 * level;		
		//p.sendDeath(n);
		p.setNextWorldTile(Settings.RESPAWN_PLAYER_LOCATION);
		xpnow = p.getSkills().getXp(Skills.DUNGEONEERING);
		long boo = (long) (xpnow - xpb4);
		p.sendMessage("<col=660066>You have gained " + Utils.format(boo) + " experience and "
				+ 100 * level + " tokens.");
		// SeasonEvent.HandleActivity(p, "Dungeoneering");
	}
	
	public static void ll() {
		int level = p.getSkills().getLevelForXp(Skills.DUNGEONEERING);
		double xpnow = 0;
		double xpb4 = 0;
		xpb4 = p.getSkills().getXp(Skills.DUNGEONEERING);
		p.getSkills().addXp(Skills.DUNGEONEERING, 2000 * level / 10);
		if (p.challengeid == 12 && p.challengeid > 0) {
			DailyChallenges.UpdateChallenge(p);
		}
		if (p.dungperk) {
			level *= 2;
		}
		p.dungpoints += p.getEquipment().getCapeId() == 19709 || p.getEquipment().getCapeId() == 19710 ? 280 * level : 200 * level;
		int pointsgained = p.getEquipment().getCapeId() == 19709 || p.getEquipment().getCapeId() == 19710 ? 280 * level : 200 * level;
		//p.sendDeath(n);
		p.setNextWorldTile(Settings.RESPAWN_PLAYER_LOCATION);
		xpnow = p.getSkills().getXp(Skills.DUNGEONEERING);
		long boo = (long) (xpnow - xpb4);
		p.sendMessage("<col=660066>You have gained " + Utils.format(boo) + " experience and "
				+ pointsgained + " tokens.");
		// SeasonEvent.HandleActivity(p, "Dungeoneering");
		
	}
	
}