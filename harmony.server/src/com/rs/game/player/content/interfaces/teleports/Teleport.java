package com.rs.game.player.content.interfaces.teleports;

import com.rs.game.WorldTile;

public class Teleport {
    public int getId() {
        return id;
    }

    public TeleportCategory getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String[] getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public WorldTile getDestination() {
        return destination;
    }

    public boolean isWilderness() {
        return isWilderness;
    }

    public int getWildLevel() {
        return wildLevel;
    }

    public int getSuggestedLevel() {
        return suggestedLevel;
    }
    
    public boolean isHasInstance() {
		return hasInstance;
	}

	public void setHasInstance(boolean hasInstance) {
		this.hasInstance = hasInstance;
	}

    public Teleport(int id, TeleportCategory category, String name, String[] description, int price, WorldTile destination, boolean isWilderness, int wildLevel, int suggestedLevel,boolean hasInstance) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.destination = destination;
        this.isWilderness = isWilderness;
        this.wildLevel = wildLevel;
        this.suggestedLevel = suggestedLevel;
        this.hasInstance = hasInstance;
    }
	private int id;
    private TeleportCategory category;
    private String name;
    private String[] description;
    private int price;
    private WorldTile destination;
    private boolean isWilderness;
    private int wildLevel;
    private int suggestedLevel;
    private boolean hasInstance;
}
