package com.rs.game.player.content.interfaces.teleports;

public class TeleportCategory {
    public TeleportCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private int id;
    private String name;
}
