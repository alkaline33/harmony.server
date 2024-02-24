package com.rs.game.player.content.bountyhunter;

import java.io.Serializable;

import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.controlers.Wilderness;
import com.rs.utils.EconomyPrices;

/**
 * Created by Peng on 28.3.2016 12:03.
 */
public class BountyHunter implements Serializable {

    private static final long serialVersionUID = 2011932556974180375L;

    private Player player;
    private Player target;

    private int likelihood = 30;

    private float ep = 0;

    private long lastTarget = 0;
    private long leaveTime = 0;

    /**
     * Bounty hunter class used for handling players target etc.
     */
    public BountyHunter(Player player) {
        this.player = player;
    }

    /**
     * Does this player have a target?
     *
     * @return target == null
     */
    boolean hasTarget() {
        return target != null;
    }

    /**
     * Set a target for this player
     *
     * @param target the target
     */
    void assignTarget(Player target) {
        this.target = target;
        player.getHintIconsManager().addHintIcon(target, 0, -1, false);
        updateInterfaces();
    }

    /**
     * Check whether a player is suitable for a target for this player
     *
     * @param target the target candidate
     * @return suitability
     */
    boolean isSuitableTarget(Player target) {
        if (likelihood < 60) {
			if (Math.abs(player.getSkills().getCombatLevel() - target.getSkills().getCombatLevel()) <= ((Wilderness) player.getControlerManager().getControler()).getWildLevel()) {
                return true;
            }
        } else {
            if (target.getBountyHunter().likelihood > 30) return true;
        }
        return false;
    }

    /**
     * Handle the player logging out
     */
    public void logout() {
        if (target != null) {
            target.getBountyHunter().removeTarget(true);
            removeTarget(false);
        }
        lastTarget = System.currentTimeMillis();
        likelihood = 0;
       // BountyHunterManager.removeHandledPlayer(player);
    }

    /**
     * Remove the players target
     *
     * @param loggedOut did the target logout?
     */
    public void removeTarget(boolean loggedOut) {
        if (loggedOut) {
            player.sendMessage("Your target has logged out. You will be assigned a new one shortly.");
            lastTarget = System.currentTimeMillis() - 50 * 60 * 500; // 10 minutes till next target
            likelihood = 50;
        } else {
            lastTarget = System.currentTimeMillis() - 30 * 60 * 500;
            likelihood = 30;
        }
        updateInterfaces();
        player.getHintIconsManager().removeUnsavedHintIcon();
        target = null;
    }

    /**
     * Check if the killed player was the target. You can add the rogue rewards here as well if you want.
     *
     * @param killed the killed player
     */
    public void checkKill(Player killed) {
        if (target == null) return;
        //I just put it to give 20 pkp for testing you can do whatever you want with it
        //You probably want to get the artifact ids and give some of those
        if (killed.getUsername().equalsIgnoreCase(target.getUsername())) {
            player.sendMessage("You have killed your target and receivedextra 5 pkp");
            player.setPvpPoints(player.getPvpPoints() + 5);
            target.getBountyHunter().removeTarget(false);
            player.getBountyHunter().removeTarget(false);
        }
    }

    /**
     * You may want to change the tab value of the skull (the number 27 in the two below methods) in Wilderness.java
     */

    /**
     * Send the bh interfaces
     */
    public void sendInterfaces() { // 25
        updateInterfaces();
        player.getInterfaceManager().sendTab(player.getInterfaceManager().hasRezizableScreen() ? 25 : 8, 591);
    }

    /**
     * Update the bh interfaces
     */
    void updateInterfaces() {
        if (target != null) player.getPackets().sendIComponentText(591, 8, target.getDisplayName());
        else player.getPackets().sendIComponentText(591, 8, "None");

        if (inHotZone()) player.getPackets().sendHideIComponent(745, 6, false);
        else player.getPackets().sendHideIComponent(745, 6, true);

        if (player.getInterfaceManager().hasRezizableScreen()) {
            player.getPackets().sendIComponentText(746, 17, getCombatDiff() + "<br> EP: <col=" + getEpColor() + ">" + getEpPercentage() + "%");
        } else {
            player.getPackets().sendIComponentText(548, 12, getCombatDiff());
            player.getPackets().sendIComponentText(548, 13, "EP: <col=" + getEpColor() + ">" + getEpPercentage() + "%");//EP
        }
        player.getPackets().sendConfig(1410, likelihood > 60 ? 60 : likelihood);
    }

    /**
     * Checks if player is in hot zone to gain more ep
     *
     * @return
     */
    private boolean inHotZone() {
        return player.getY() > 3700;
    }

    /**
     * Get the color that the ep percentage is displayed in
     *
     * @return the ep color
     */
    private String getEpColor() {
        if (ep < 25) return "red";
        if (ep < 50) return "or1";
        if (ep < 75) return "yel";
        else return "gre";
    }

    /**
     * Used just for the display
     *
     * @return combat level diff in format [min]-[max]
     */
    private String getCombatDiff() {
        int wildLevel;
        if (player.getY() > 9900) wildLevel = (player.getY() - 9912) / 8 + 1;
        else wildLevel = (player.getY() - 3520) / 8 + 1;
        int min = player.getSkills().getCombatLevel() - wildLevel;
        int max = player.getSkills().getCombatLevel() + wildLevel;

        return min + " - " + max;
    }

    /**
     * Fetch the ep percentage in full percents
     *
     * @return formatted percentage
     */
    private int getEpPercentage() {
        return Math.round(ep*100);
    }

    /**
     * Number of bounty hunter ticks (about 10secs) in wilderness
     */
    private int NORMAL_EP_TICKS = 0;
    private int HOT_ZONE_EP_TICKS = 0;

    /**
     * Increase the players earning potential
     * Add 25% for each 30 minutes in hot zone and 10% for 30 minutes in normal area
     */
    void increaseEP() {
        if (getWealth() < 750000) return; //made this 750k because 75k is too little for rsps
        if (Wilderness.isAtWild(player)) {
            if (inHotZone()) HOT_ZONE_EP_TICKS++;
            else NORMAL_EP_TICKS++;
        }
        if (HOT_ZONE_EP_TICKS > 6) {
            ep += 0.25 / 30.0;
            HOT_ZONE_EP_TICKS = 0;
        }
        if (NORMAL_EP_TICKS > 6) {
            ep += 0.1 / 30.0;
            NORMAL_EP_TICKS = 0;
        }

    }

    private int getWealth() {
        int wealth = 0;
        for (Item item : player.getInventory().getItems().getItems())
            wealth += EconomyPrices.getPrice(item.getId());
        for (Item item : player.getEquipment().getItems().getItems())
            wealth += EconomyPrices.getPrice(item.getId());
        return wealth;
    }

    /**
     * Close the bh interfaces
     */
    public void closeInterfaces() {
        player.getPackets().closeInterface(player.getInterfaceManager().hasRezizableScreen() ? 9 : 8);
    }

    /**
     * A player can only get a target every 60 minutes, delays other than 60 minutes are handled by
     * setting the lastTarget to currentTime - the difference of the time to 60minutes
     * for example 25 minutes would be currentTime - (60-25)*60*1000
     *
     * @return whether it's been less than 60 minutes since last target
     */
    boolean isOnTargetCooldown() {
        return (System.currentTimeMillis() - lastTarget) < 30 * 60 * 500;
    }

    /**
     * Increase the players likelihood of getting a target
     */
    void increaseLikelihood() {
        likelihood++;
    }

    /**
     * Set the players likelihood to get a target
     *
     * @param likelihood likelihood
     */
    public void setLikelihood(int likelihood) {
        this.likelihood = likelihood;
    }

    /**
     * Resets the target likelihood, called upon entering wilderness etc
     */
    private void resetLikelihood() {
        likelihood = 0;
    }

    /**
     * Called upon entering bh
     */
    void enterBountyHunter() {
        if (System.currentTimeMillis() - leaveTime > 10 * 60 * 500) //If been out for more than 10 minutes
            resetLikelihood();
    }

    /**
     * Called upon leaving bh by logging out or teleporting etc.
     */
    void leaveBountyHunter() {
        leaveTime = System.currentTimeMillis();
    }

}