package com.rs.game.player.content.clans.clancitadels;

/**
 * 
 * @author Josh'
 *
 */
public class CitadelMessageConstants {
	
	public static String message = "No Current Message Set.",
			north = "We have...",
			south = "We have...",
			west = "We have...",
			east = "We have...";
	
	public static String getMessage() {
		return message;
	}
	
	public static String setMessage(String newMessage) {
		return message = newMessage;
	}
	
	public static final int HOME_ANIMATION = 16385,
			HOME_GRAPHIC = 3017, DONE_ANIMATION = 16386;
	
}
