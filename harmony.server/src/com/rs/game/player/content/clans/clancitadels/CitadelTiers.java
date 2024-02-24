package com.rs.game.player.content.clans.clancitadels;

import com.rs.game.RegionBuilder;
/**
 * 
 * @author Josh'
 *
 */
public class CitadelTiers {
	
	public enum Tiers {
		OBELISK(646, 505),
		TREE(650, 505),
		THEATRE(616, 501),
		MINING(622, 505),
		RESOURCES(662, 505),
		LOOM(666, 505);
		
		private int mapX, mapY;
		
		public int getMapX() {
			return mapX;
		}
		
		public int getMapY() {
			return mapY;
		}
		
		Tiers(int x, int y) {
			this.mapX = x;
			this.mapY = y;
		}
	}	
	
	public CitadelTiers(int newTier) {
		if (newTier == 1) {
			RegionBuilder.copyAllPlanesMap(Tiers.OBELISK.getMapX(),
					Tiers.OBELISK.getMapY(), ClanCitadel.boundChunk[0], ClanCitadel.boundChunk[1], 3);
			return;
		}
		else if (newTier == 2) {
			RegionBuilder.copyAllPlanesMap(Tiers.TREE.getMapX(),
					Tiers.TREE.getMapY(), ClanCitadel.boundChunk[0], ClanCitadel.boundChunk[1], 3);
			return;
		}
		else if (newTier == 3) {
			RegionBuilder.copyAllPlanesMap(Tiers.THEATRE.getMapX(),
					Tiers.THEATRE.getMapY(), ClanCitadel.boundChunk[0], ClanCitadel.boundChunk[1], 3);
			return;
		}
		else if (newTier == 4) {
			RegionBuilder.copyAllPlanesMap(Tiers.MINING.getMapX(),
					Tiers.MINING.getMapY(), ClanCitadel.boundChunk[0], ClanCitadel.boundChunk[1], 3);
			return;
		}
		else if (newTier == 5) {
			RegionBuilder.copyAllPlanesMap(Tiers.RESOURCES.getMapX(),
					Tiers.RESOURCES.getMapY(), ClanCitadel.boundChunk[0], ClanCitadel.boundChunk[1], 3);
			return;
		}
		else if (newTier == 6) {
			RegionBuilder.copyAllPlanesMap(Tiers.LOOM.getMapX(),
					Tiers.LOOM.getMapY(), ClanCitadel.boundChunk[0], ClanCitadel.boundChunk[1], 3);
			return;
		}
	}
}
		
		
		
		