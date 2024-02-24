package com.rs.game.player.content.bountyhunter;

import com.rs.cores.CoresManager;
import com.rs.game.player.Player;
import com.rs.game.player.controlers.Wilderness;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Peng on 28.3.2016 11:56.
 */
public class BountyHunterManager {


    private static CopyOnWriteArrayList<Player> handledPlayers = new CopyOnWriteArrayList<>();

    /**
     * Initiate the bounty hunter
     */
    public static void init() {
        CoresManager.slowExecutor.scheduleWithFixedDelay(() -> {
            for (Player player : handledPlayers)
                handleHunter(player);
        }, 10, 10, TimeUnit.SECONDS);
    }

    /**
     * Add a player to be handled as a bounty hunter
     *
     * @param player the hunter
     */
    public static void addHandledPlayer(Player player) {
        handledPlayers.add(player);
        player.getBountyHunter().enterBountyHunter();
    }

    /**
     * Remove a player from the handled hunters
     *
     * @param player the player
     */
    public static void removeHandledPlayer(Player player) {
        handledPlayers.remove(player);
        player.getBountyHunter().leaveBountyHunter();
    }

    /**
     * Are we handling this player? Used for adding the player to handled players on login etc.
     *
     * @param player the player
     * @return whether the player is being handled as a bounty hunter
     */
    public static boolean handlingPlayer(Player player) {
        return handledPlayers.contains(player);
    }

    /**
     * Process a player playing bounty hunter, (add targets, etc.)
     * Should get ran about once a minute
     *
     * @param player
     */
    private static void handleHunter(Player player) {
        if (player.getControlerManager().getControler() instanceof Wilderness) {
            player.getBountyHunter().increaseLikelihood();
            player.getBountyHunter().increaseEP();
            if (!player.getBountyHunter().hasTarget() && !player.getBountyHunter().isOnTargetCooldown()) {
                findTarget(player);
            }
        } else {
            if (!player.getBountyHunter().hasTarget())
                handledPlayers.remove(player);
        }
        player.getBountyHunter().updateInterfaces();
    }

    /**
     * Attempt to locate a target for the player
     *
     * @param player the player
     */
    private static void findTarget(Player player) {
        for (Player player2 : handledPlayers) {
            if (player2.getBountyHunter().hasTarget() || player2.getBountyHunter().isOnTargetCooldown()
                    || player2.getUsername().equalsIgnoreCase(player.getUsername()))
                continue;
            if (!(player2.getControlerManager().getControler() instanceof Wilderness && !player2.getBountyHunter().hasTarget())) {
                handledPlayers.remove(player2);
                continue;
            }
            if (player.getBountyHunter().isSuitableTarget(player2) && player2.getBountyHunter().isSuitableTarget(player)) {
                player.getBountyHunter().assignTarget(player2);
                player2.getBountyHunter().assignTarget(player);
                return;
            }
        }
    }


}