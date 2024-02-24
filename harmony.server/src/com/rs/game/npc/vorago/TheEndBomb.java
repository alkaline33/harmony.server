/*package com.rs.game.npc.vorago;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;

@SuppressWarnings("serial")
public class TheEndBomb extends WorldObject {

    private transient Vorago vorago;
    private boolean active;
    private int charges;

    public TheEndBomb(int id, int type, int rotation, WorldTile tile, Vorago vorago, int charges) {
	super(id, type, rotation, tile);
	this.vorago = vorago;
	this.charges = charges;
    }

    public int getCharges() {
	return charges;
    }

    public boolean decreaseCharges() {
	if (charges > 0) {
	    charges--;
	    if (charges == 0)
		active = false;
	    return true;
	}
	return false;
    }

    public boolean isActive() {
	return active;
    }

    public void setActive(boolean active) {
	this.active = active;
    }

    public Vorago getVorago() {
	return vorago;
    }
}*/