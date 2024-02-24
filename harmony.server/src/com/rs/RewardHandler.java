// package com.rs;
//
// import com.rs.game.World;
// import com.rs.game.player.Player;
// import com.rs.game.player.content.Raffle;
// import com.rs.utils.Utils;
// import com.rspserver.motivote.MotivoteHandler;
// import com.rspserver.motivote.Reward;
//
// public class RewardHandler extends MotivoteHandler<Reward> {
// @Override
// public void onCompletion(Reward reward) {
// Player p = World.getPlayerByDisplayName(reward.username());
// if (p == null || p.hasFinished() || p.isDead())
// return;
// int itemId = -1;
// int amount = reward.amount();
// String name = reward.rewardName();
// if (name.equalsIgnoreCase("coins")) {
// itemId = 995;
// } else if (name.equalsIgnoreCase("crystal key")) {
// itemId = 989;
// } else if (name.equalsIgnoreCase("overloads")) {
// itemId = 15332;
// } else if (name.equalsIgnoreCase("double xp")) {
// itemId = 14808;
// }
// synchronized(p) {
// if (itemId != -1) {
// if (p.getInventory().addItem(itemId, amount )) {
// //p.VotePoint++;
// Raffle.addVoterToList(p.getUsername());
// p.sendMessage("<img=1> Thank You for voting! You have receivedyour
// reward!");
// p.sendMessage("<img=1> You can spend your vote point in the store at home!");
// World.sendWorldMessage("<col=ff0000><img=1>News: "+p.getDisplayName()+" has
// just voted for "+Utils.formatAorAn(reward.rewardName())+"
// "+reward.rewardName().toLowerCase()+"!", false);
// reward.complete();
// return;
// }
// } else if (itemId == -1) {
// if (name.equalsIgnoreCase("votepoint")) {
// p.VotePoint++;
// } else if (name.equalsIgnoreCase("spins")) {
// p.setSpins(p.getSpins() + 2);
// }
// Raffle.addVoterToList(p.getUsername());
// p.sendMessage("<img=1> Thank You for voting! You have receivedyour
// reward!");
// p.sendMessage("<img=1> You can spend your vote point in the store at home!");
// World.sendWorldMessage("<col=ff0000><img=1>News: "+p.getDisplayName()+" has
// just voted! Do ;;vote to get started!", false);
// reward.complete();
// return;
// } else {
// p.getPackets().sendGameMessage("You need to make some inventory space before
// claiming your reward!");
// return;
// }
// }
// }
// }