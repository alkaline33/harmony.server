package com.rs.game.player.bot.definition;

/**
 * Created by Valkyr on 21/05/2016.
 */
public enum SpecialWeaponDefinition {
    KORASI(19784),
   // AGS(11694),
    //DRAGON_CLAWS(14484),
    DARK_BOW(11235),
    DHAROKS_AXE(4718),
	DDS(1215);
    private int id;

    SpecialWeaponDefinition(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
