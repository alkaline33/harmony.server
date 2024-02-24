package com.rs.database.mysql.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

//import com.rs.Constants;
import com.rs.cores.mysql.Database;
import com.rs.game.player.Player;
 
public class Highscores implements Runnable {


	public static final String HOST = "108.167.189.35"; // website ip address
	public static final String USER = "harmonyr_highscores";
	public static final String PASS = "testing41603";
	public static final String DATABASE = "harmonyr_highscores";
	public static final String TABLE = "harmonyr_highscores";
	
	private Player player;
	private Connection conn;
	private Statement stmt;
	
	public Highscores(Player player) {
		
		this.player = player;
	}
	
/*	public boolean connect(String host, String database, String user, String pass) {
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+database, user, pass);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}*/
	
	@Override
	public void run() {
		try {
Database db = new Database(HOST, USER, PASS, DATABASE);
			if (!db.init()) {
				return;
			}
			if (player.getRights() == 2) {
				return;
			}
			String name = player.getDisplayName();
			
			PreparedStatement stmt1 = db.prepare("DELETE FROM "+TABLE+" WHERE username=?");
			stmt1.setString(1, player.getUsername());
			stmt1.execute();
				
			PreparedStatement stmt2 = db.prepare(generateQuery());
			stmt2.setString(1, player.getUsername());
			stmt2.setInt(2, player.prestigeTokens);
			stmt2.setInt(3, player.TheXpMode());
			stmt2.setLong(4, player.getSkills().getTotalExp());
			
			for (int i = 0; i < 25; i++) {
				stmt2.setInt(5 + i, (int)player.getSkills().getXp()[i]);
			}
			stmt2.execute();
			
			destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
/*	public PreparedStatement prepare(String query) throws SQLException {
		return conn.prepareStatement(query);
	}*/
	
	public void destroy() {
        try {
        	if (conn != null) {
    		conn.close();
        	conn = null;
        	}
        	if (stmt != null) {
    			stmt.close();
        		stmt = null;
        	}
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	public static String generateQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO "+TABLE+" (");
		sb.append("username, ");
		sb.append("prestige_level, ");
		sb.append("difficulty, ");
		sb.append("overall_xp, ");
		sb.append("attack_xp, ");
		sb.append("defence_xp, ");
		sb.append("strength_xp, ");
		sb.append("constitution_xp, ");
		sb.append("ranged_xp, ");
		sb.append("prayer_xp, ");
		sb.append("magic_xp, ");
		sb.append("cooking_xp, ");
		sb.append("woodcutting_xp, ");
		sb.append("fletching_xp, ");
		sb.append("fishing_xp, ");
		sb.append("firemaking_xp, ");
		sb.append("crafting_xp, ");
		sb.append("smithing_xp, ");
		sb.append("mining_xp, ");
		sb.append("herblore_xp, ");
		sb.append("agility_xp, ");
		sb.append("thieving_xp, ");
		sb.append("slayer_xp, ");
		sb.append("farming_xp, ");
		sb.append("runecrafting_xp, ");
		sb.append("hunter_xp, ");
		sb.append("construction_xp, ");
		sb.append("summoning_xp, ");
		sb.append("dungeoneering_xp) ");
		sb.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		return sb.toString();// literally thats it...
	}
	
}