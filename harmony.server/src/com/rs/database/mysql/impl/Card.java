/*package com.rs.database.mysql.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.rs.game.player.Skills;
import com.rs.game.player.Player;
import com.rs.utils.Logger;

public class Card {

    public static Connection con = null;
    public static Statement stmt;
    public static boolean connectionMade;

    public static void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();//opens class
            String IP="gladeonrsps.com";//connection ip
            String DB="gladeonr_card";//database name
            String User="gladeonr_cardu";//username
            String Pass="gladeoncard123451"; //password
            con = DriverManager.getConnection("jdbc:mysql://"+IP+"/"+DB, User, Pass);//creates connection
            stmt = con.createStatement();
        } catch (Exception e) {//catches if connection failed
            Logger.log("Card", "Connection to SQL database failed!");//tells you it failed @ the run.bat
            e.printStackTrace();
        }
    }


    public static ResultSet query(String s) throws SQLException {
        try {
            if (s.toLowerCase().startsWith("select")) {
                ResultSet rs = stmt.executeQuery(s);
                return rs;
            } else {
                stmt.executeUpdate(s);
            }
            return null;
        } catch (Exception e) {
            destroyConnection();
        }
        return null;
    }

    public static void destroyConnection() {
        try {
            stmt.close();
            con.close();
        } catch (Exception e) {
        }
    }

    public static boolean saveCard(Player player) {
        try {
        	createConnection();
            Skills skills = player.getSkills();
            int[] overall = getOverall(player);
            query("DELETE FROM `card_users` WHERE username = '"+player.getUsername()+"';");
			query("INSERT INTO `card_users` (`username`,`overall_lvl`,`attack_lvl`,`defence_lvl`,`strength_lvl`,`constitution_lvl`,`ranged_lvl`,`prayer_lvl`,`magic_lvl`,`cooking_lvl`,`woodcutting_lvl`,`fletching_lvl`,`fishing_lvl`,`firemaking_lvl`,`crafting_lvl`,`smithing_lvl`,`mining_lvl`,`herblore_lvl`,`agility_lvl`,`thieving_lvl`,`slayer_lvl`,`farming_lvl`,`runecrafting_lvl`,`hunter_lvl`,`construction_lvl`,`summoning_lvl`,`dungeoneering_lvl`) VALUES ('"+player.getUsername()+"',"+ overall[0] +","+skills.getLevel(0)+","+skills.getLevel(1)+","+skills.getLevel(2)+","+skills.getLevel(3)+","+skills.getLevel(4)+","+skills.getLevel(5)+","+skills.getLevel(6)+","+skills.getLevel(7)+","+skills.getLevel(8)+","+skills.getLevel(9)+","+skills.getLevel(10)+","+skills.getLevel(11)+","+skills.getLevel(12)+","+skills.getLevel(13)+","+skills.getLevel(14)+","+skills.getLevel(15)+","+skills.getLevel(16)+","+skills.getLevel(17)+","+skills.getLevel(18)+","+skills.getLevel(19)+","+skills.getLevel(20)+"," + skills.getLevel(21) + "," + skills.getLevel(22) + "," + skills.getLevel(23)+"," + skills.getLevel(24)+");");
            Logger.log("Card", "saved for " + player.getUsername() + ".");
			destroyConnection();
        } catch (Exception e) {
        	Logger.log("Card", "Error, could not save Card for " + player.getUsername() +".");
            return false;
        }
        return true;
    }


	public static int[] getOverall(Player player) {
		int totalLevel = 0;
		for (int i = 0; i < 25; i++) {
			totalLevel += player.getSkills().getLevelForXp(i);
		}
		return new int[] { totalLevel };
	}
    
}*/