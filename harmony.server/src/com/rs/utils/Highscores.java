package com.rs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Logger;

public class Highscores {

 public static Connection con = null;
 public static Statement stmt;
 public static boolean connectionMade;

 public static void createConnection() {
  try {
   Class.forName("com.mysql.jdbc.Driver").newInstance();// opens class
   String IP = "160.153.128.39";// connection ip
   String DB = "drygon_highscore";// database name
   String User = "drygon_highscors";// username
   String Pass = "nike2323"; // password
   con = DriverManager.getConnection("jdbc:mysql://" + IP + "/" + DB,
     User, Pass);// creates connection
   stmt = con.createStatement();
  } catch (Exception e) {// catches if connection failed
   Logger.log("Hiscores", "Connection to SQL database failed!");
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

 public static boolean saveHighScore(Player player) {// saves hiscores
  try {
   createConnection();// creates connection
   Skills skills = player.getSkills();// gets skills
   long[] overall = getOverall(player);// just a int
   if (player.getRights() >= 2) {
		  Logger.log("Hiscores", "Hiscores not saved for " + player.getUsername()
				     + " since admin+.");
		  return false;
	}
   if (player.iseasy == true) {
	  Logger.log("Hiscores", "Hiscores not saved for " + player.getUsername()
			     + " since Easy xp.");
		  return false;
	  }

   query("DELETE FROM `hs_users` WHERE username = '"
     + player.getUsername() + "';");
   query("INSERT INTO `hs_users` (`username`,`overall_xp`,`donation_amount_xp`,`attack_xp`,`defence_xp`,`strength_xp`,`constitution_xp`,`ranged_xp`,`prayer_xp`,`magic_xp`,`cooking_xp`,`woodcutting_xp`,`fletching_xp`,`fishing_xp`,`firemaking_xp`,`crafting_xp`,`smithing_xp`,`mining_xp`,`herblore_xp`,`agility_xp`,`thieving_xp`,`slayer_xp`,`farming_xp`,`runecrafting_xp`, `hunter_xp`, `construction_xp`, `summoning_xp`, `dungeoneering_xp`) VALUES ('"
     + player.getUsername()
     + "',"
     + overall[1]
     + ","
     + player.getDonation()
     + ","
     + skills.getXp(0)
     + ","
     + skills.getXp(1)
     + ","
     + skills.getXp(2)
     + ","
     + skills.getXp(3)
     + ","
     + skills.getXp(4)
     + ","
     + skills.getXp(5)
     + ","
     + skills.getXp(6)
     + ","
     + skills.getXp(7)
     + ","
     + skills.getXp(8)
     + ","
     + skills.getXp(9)
     + ","
     + skills.getXp(10)
     + ","
     + skills.getXp(11)
     + ","
     + skills.getXp(12)
     + ","
     + skills.getXp(13)
     + ","
     + skills.getXp(14)
     + ","
     + skills.getXp(15)
     + ","
     + skills.getXp(16)
     + ","
     + skills.getXp(17)
     + ","
     + skills.getXp(18)
     + ","
     + skills.getXp(19)
     + ","
     + skills.getXp(20)
     + ","
     + skills.getXp(21)
     + ","
     + skills.getXp(22)
     + ","
     + skills.getXp(23)
     + ","
     + skills.getXp(24) + ");");
   Logger.log("Hiscores", "Hiscores saved for " + player.getUsername()
     + ".");
   destroyConnection();
  } catch (Exception e) {
   Logger.log("Hiscores", "Error, could not save highscores for "
     + player.getUsername() + ".");
   return false;
  }
  return true;
 }
 

 public static void restore(Player player) throws SQLException {
  createConnection();// creates connection
  Statement statement = con.createStatement();
  String query = "SELECT * FROM users WHERE username = '"
    + player.getUsername() + "'";
  ResultSet results = statement.executeQuery(query);
  if (results.next()) {
   for (int skill = 0; skill < player.getSkills().level.length; skill++) {
    player.getSkills().level[skill] = (short) player.getSkills()
      .getLevelForXp(skill);
    player.getSkills().refresh(skill);
   }
  }
  destroyConnection();
 }

 public static long[] getOverall(Player player) {
     long totalLevel = 0;
     long totalXp = 0;
     for(int i = 0; i < 25; i++) {
         totalLevel += player.getSkills().getLevelForXp(i);
     }
     for(int i = 0; i < 25; i++) {
         totalXp += player.getSkills().getXp(i);
     }
     return new long[] {totalLevel, totalXp};
 }

}