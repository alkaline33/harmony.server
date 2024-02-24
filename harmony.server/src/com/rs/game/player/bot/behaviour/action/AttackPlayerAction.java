package com.rs.game.player.bot.behaviour.action;

import com.rs.game.Region;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.game.player.actions.PlayerCombat;
import com.rs.game.player.bot.Bot;
import com.rs.game.player.bot.behaviour.action.condition.OutOfCombatCondition;
import com.rs.game.player.bot.definition.MetadataDefinition;
import com.rs.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valkyr on 21/05/2016.
 */
public class AttackPlayerAction extends Action {
    public AttackPlayerAction() {
        super(1000, 2000, new OutOfCombatCondition());
    }

    @Override
    public boolean process(Bot bot) {
        Player target = getTarget(bot);
        if (target != null) {
            bot.putMetaData(MetadataDefinition.INTERACT_TARGET, target);
            bot.getActionManager().setAction(new PlayerCombat(target));
            return true;
        }
        System.out.println("No target found!");
        return false;
    }

    public Player findAttackablePlayer(Bot bot) {
        final List<Player> players = new ArrayList<Player>();
        final List<Integer> playerIndexes = World.getRegion(bot.getRegionId()).getPlayerIndexes();
        if (playerIndexes == null)
            return null;
        for (final Integer playerIndex : playerIndexes) {
            final Player player = World.getPlayers().get(playerIndex);
            if (canAttack(bot, player))
                continue;
            players.add(player);
        }
        if (!players.isEmpty())
            return players.get(Utils.random(players.size()));
        return null;
    }

    private boolean canAttack(Bot bot, Player player) {
        return player != null && !player.isDead() && !player.hasFinished()
                && bot.isCanPvp() && player.isCanPvp()
                && (player.getAttackedByDelay() + 10000 > Utils.currentTimeMillis())
                && !bot.equals(player)
                && (bot.getControlerManager().getControler() == null || bot.getControlerManager().getControler().canHit(player) && bot.getControlerManager().canAttack(player))
                && bot.withinDistance(player, 15)
                && !player.isLocked();
    }

    public Player findTarget(Bot bot) {
        Region region = World.getRegion(bot.getRegionId());
        for (int i : region.getPlayerIndexes()) {
            Player player = World.getPlayers().get(i);
            if (player.isCanPvp() && !player.equals(bot)
                    && bot.withinDistance(player, 15)
                    && (player.isAtMultiArea() || (player.getAttackedByDelay() < Utils.currentTimeMillis() || (bot.getAttackedBy() != null && bot.getAttackedBy().equals(player))))) {
                return player;
            }
        }
        return null;
    }

    public Player getTarget(Bot bot) {
        Player target = bot.getMetaData(MetadataDefinition.INTERACT_TARGET);
        if (target == null || target.isDead()
                || (bot.getAttackedBy() != null && !bot.getAttackedBy().equals(target))
                || target.getRegionId() != bot.getRegionId()) {
            target = findAttackablePlayer(bot);

        }
        bot.putMetaData(MetadataDefinition.INTERACT_TARGET, target);
        return target;
    }
}
