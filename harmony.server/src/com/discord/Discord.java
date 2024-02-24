package com.discord;


import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
/**
 * 
 * Discord.java | 8:06:53 AM
 */
public class Discord {
	private static JDA discord = null;

	public static void startUp() {

		try {
			discord = new JDABuilder(AccountType.BOT).setToken(Constants.TOKEN).buildBlocking();
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		discord.addEventListener(new BotListener());
	}
	public static JDA getJDA() {
		return discord;
	}
	public static void sendSimpleMessage(String message) {
		if (discord == null) {
			System.out.println("discord error: discord is null.");
			return;
		}
		discord.getTextChannelById(Constants.BOT_CHANNEL).sendMessage(message).queue();
	}

	public static void sendEventsMessage(String message) {
		if (discord == null) {
			System.out.println("discord error: discord is null.");
			return;
		}
		discord.getTextChannelById(Constants.EVENTS_CHANNEL).sendMessage(message).queue();
	}

	public static void sendAnnouncementsMessage(String message) {
		if (discord == null) {
			System.out.println("discord error: discord is null.");
			return;
		}
		discord.getTextChannelById(Constants.ANNOUNCEMENTS_CHANNEL).sendMessage(message).queue();
	}
	public static void sendGEMessage(String message) {
		if (discord == null) {
			System.out.println("discord error: discord is null.");
			return;
		}
		discord.getTextChannelById(Constants.MARKET_CHANNEL).sendMessage(message).queue();
	}
	public static void sendDropMessage(String message, String reciever) {
		if (discord == null) {
			System.out.println("discord error: discord is null.");
			return;
		}
		
		discord.getTextChannelById(Constants.BOT_CHANNEL).sendMessage(new EmbedBuilder()
	                .setAuthor(reciever, null, null)
				.setDescription(message)
	                .build()).queue();
	}	
}
