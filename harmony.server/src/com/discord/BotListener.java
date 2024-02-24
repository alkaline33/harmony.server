package com.discord;

import java.io.File;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.player.Player;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;
/**
 * 
 * Discord.java | 8:06:53 AM
 */
public class BotListener implements EventListener {

	// private Player player;

	@Override
	public void onEvent(Event event) {
		if (Discord.getJDA() == null) {
			System.out.println("Returning JDA FAIL");
			return;
		}

		// System.out.println("[EVENT] " + event.getClass().getSimpleName()); // used
		// for debuging

		// Player player = null;
		if (event instanceof MessageReceivedEvent) { // handles messages recieved
			MessageReceivedEvent msg = (MessageReceivedEvent) event;
			String message = msg.getMessage().getContentDisplay();
			/**
			 * Calling the command type is correct prefix
			 */
			if (message.startsWith(Constants.COMMAND_PREFIX)) { // commands
				MessageReceivedEvent messageEvent = (MessageReceivedEvent) event;
				String command = message.substring(Constants.COMMAND_PREFIX.length()).toLowerCase();
				String[] cmd = command.split(" ");
				switch (cmd[0]) {
				/**
				 * Handles player specific command types
				 */
					case "events":
					case "spotlight":
					messageEvent.getChannel().sendMessage("Boss Spotlight: " + Settings.BOSS_SPOTLIGHT + "!\n" + "" + "Event Spotlight: " + Settings.EVENT_SPOTLIGHT + "!\nSeason Event: " + Settings.SEASON_EVENT + "!\n").queue();
					break;

				case "cmds":
					messageEvent.getChannel().sendMessage("!spotlight, !events, !players, !bosspets..").queue();
					break;

				case "players":
					messageEvent.getChannel().sendMessage("There are currently " + World.getPlayers().size() + " players online..").queue();
					break;

				//case "bosspets":
				case "pets":
					messageEvent.getChannel().sendMessage("Boss pet drop rates: Party Demon 1/50, the Assassin 1/75, Celestia 1/500, Giant Mole 1/400 Hydra 1/2000, Zulrah 1/400, Kraken 1/1000, Cerberus 1/750, Vet'ion 1/750, Callisto 1/750, Anivia 1/750, Hope Devourer 1/400, Smoke Devil 1/1000, Abyssal Sire 1/1000, Bad Santa 1/3000, Nex 1/250, Vorago 1/150, Thunderous 1/1000, KBD 1/500, Dark Feast 1/1000, Frosty 1/1000, Garg 1/1000, Skotizo 1/65, Venenatis 1/750, Scorpia 1/750, Dryax 1/1200, Corp 1/600, Glacor 1/1000, GwD 1/800, Aquatic Wyrm 1/800, Vorkath 1/1000, Chambers of Xeric 1/65, Theatre of Blood 1/65...").queue();
					break;

				case "happyhour":
					messageEvent.getChannel().sendMessage("10am Happy Hours occur on: Monday, Wednesday, Friday, Saturday.\n7pm Happy Hours occur on: Tuesday, Thursday, Saturday, Sunday.").queue();
					break;

				/**
				 * Handles support commands
				 */

				/**
				 * Handles moderator commands
				 */

				/**
				 * Handles admin commands
				 */

				case "doubledrops":
					if (messageEvent.getMember().isOwner()) {
					if (Settings.DOUBLEDROPS != true) {
						Settings.DOUBLEDROPS = true;
						World.sendWorldMessage("<col=00ff00><img=1>Double drops are now active!", false);
							Discord.sendAnnouncementsMessage("@everyone Double Drops are now active!");
					} else {
						Settings.DOUBLEDROPS = false;
						World.sendWorldMessage("<col=ff0000><img=1>Double drops are now inactive!", false);
						messageEvent.getChannel().sendMessage("Double Drops are now in-active!").queue();
					}
					}
					break;
				case "restart":
					if (messageEvent.getMember().isOwner()) {
						File file1 = new File("data/npcs/packedCombatDefinitions.ncd");

						if (file1.delete()) {
							System.out.println(file1.getName() + " is deleted!");
						} else {
							System.out.println("Delete operation is failed.");
						}
						// int delay = Integer.valueOf(cmd[1]);
						String reason = "";
						for (int i = 1; i < cmd.length; i++) {
							reason += cmd[i] + (i == cmd.length - 1 ? "" : " ");
						}

						for (Player p : World.getPlayers()) {
							p.getDialogueManager().startDialogue("SimpleNPCMessage", 646, "<col=000000><shad=DEED97>This is a server restart authorised by Discord");
							p.authclaimed = 0;
						}
						Discord.sendSimpleMessage("Server restarting in 60 seconds!");
						World.safeShutdown(true, 60);
					}
					break;


					}
				}
			}
		}
	}
			