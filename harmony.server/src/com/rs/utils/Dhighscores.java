package com.rs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.rs.game.player.Player;
import com.rs.utils.Logger;

public class Dhighscores {

 public static Connection con = null;
 public static Statement stmt;
 public static boolean connectionMade;

 public static void createConnection() {
  try {
   Class.forName("com.mysql.jdbc.Driver").newInstance();// opens class
   String IP = "162.212.253.134";// connection ip
   String DB = "drygonsc_dighscore";// database name
   String User = "drygonsc_dhs";// username
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
 /*  if (player.getRights() >= 2) {
		  Logger.log("Hiscores", "Hiscores not saved for " + player.getUsername()
				     + " since admin+.");
		  return false;
	}
   if (player.iseasy == true && !player.getUsername().equalsIgnoreCase("joopz") ) {
	  Logger.log("Hiscores", "Hiscores not saved for " + player.getUsername()
			     + " since Easy xp.");
		  return false;
	  }*/

   query("DELETE FROM `hs_users` WHERE username = '"
     + player.getUsername() + "';");
   query("INSERT INTO `hs_users` (`username`,`amount`) VALUES ('"
     + player.getUsername()
     + "',"
     + player.donationvalue
     + ",");
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

}