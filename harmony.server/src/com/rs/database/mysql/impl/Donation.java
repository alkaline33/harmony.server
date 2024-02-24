package com.rs.database.mysql.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.rs.game.player.Player;

/**
 * Using this class:
 * To call this class, it's best to make a new thread. You can do it below like so:
 * new Thread(new Donation(player)).start();
 */
public class Donation implements Runnable {

	public static final String HOST = "108.167.183.252"; // website ip address
	public static final String USER = "harmonyr_newstore";
	public static final String PASS = "arkinpass";
	public static final String DATABASE = "harmonyr_newstore";

	private Player player;
	private Connection conn;
	private Statement stmt;

	/**
	 * The constructor
	 * @param player
	 */
	public Donation(Player player) {
		this.player = player;
	}

	@Override
	public void run() {
		try {
			if (!connect(HOST, DATABASE, USER, PASS)) {
				return;
			}

			String name = player.getUsername().replace("_", " ");
			ResultSet rs = executeQuery("SELECT * FROM payments WHERE player_name='"+name+"' AND status='Completed' AND claimed=0");

			while (rs.next()) {
				int item_number = rs.getInt("item_number");
				double paid = rs.getDouble("amount");
				int quantity = rs.getInt("quantity");

				switch (item_number) {// add products according to their ID in the ACP

				case 19:
					player.getInventory().addItem(29370, 2000 * quantity);
					player.sendMessage("Donation claimed: " + quantity + " x Member tokens (2000)");
					player.donationvalue += paid;
					break;
				case 20:
					player.getInventory().addItem(29370, 50000 * quantity);
					player.sendMessage("Donation claimed: " + quantity + " x Member tokens (50000)");
					player.donationvalue += paid;
					break;
				case 21:
					player.getInventory().addItem(20072, 1 * quantity);
					player.sendMessage("Donation claimed: " + quantity + " x Dragon defender");
					player.donationvalue += paid;
					break;
				case 22:
					player.getInventory().addItem(29336, 1 * quantity);
					player.sendMessage("Donation claimed: " + quantity + " x Avernic defender");
					player.donationvalue += paid;
					break;
				case 23:
					player.getInventory().addItem(10176, 100 * quantity);
					player.sendMessage("Donation claimed: " + quantity + " x Silverhawk feather (100)");
					player.donationvalue += paid;
					break;
				case 24:
					player.getInventory().addItem(10176, 500 * quantity);
					player.sendMessage("Donation claimed: " + quantity + " x Silverhawk feather (500)");
					player.donationvalue += paid;
					break;
				case 25:
					player.getInventory().addItem(29798, 1 * quantity);
					player.sendMessage("Donation claimed: " + quantity + " x Bond");
					break;
				case 26:
					player.getInventory().addItem(29915, 1 * quantity);
					player.sendMessage("Donation claimed: " + quantity + " x Chaming imp");
					player.donationvalue += paid;
					break;
				case 27:
					player.getInventory().addItem(29113, 1 * quantity);
					player.sendMessage("Donation claimed: " + quantity + " x Bonecrusher (i)");
					player.donationvalue += paid;
					break;
				case 28:
					player.getInventory().addItem(19675, 1 * quantity);
					player.sendMessage("Donation claimed: " + quantity + " x Herbicide");
					player.donationvalue += paid;
					break;
				case 29:
					player.getInventory().addItem(28959, 1 * quantity);
					player.sendMessage("Donation claimed: " + quantity + " x Herb sack");
					player.donationvalue += paid;
					break;
				case 30:
					player.getInventory().addItem(29793, 1 * quantity);
					player.sendMessage("Donation claimed: " + quantity + " x Bar bag");
					player.donationvalue += paid;
					break;
				case 31:
					player.getInventory().addItem(29253, 1 * quantity);
					player.sendMessage("Donation claimed: " + quantity + " x Rune pouch (i)");
					player.donationvalue += paid;
					break;
				case 32:
					player.getInventory().addItem(29405, 1 * quantity);
					player.sendMessage("Donation claimed: " + quantity + " x Fighter torso (i)");
					player.donationvalue += paid;
					break;
				case 33:
					player.getInventory().addItem(6570, 1 * quantity);
					player.sendMessage("Donation claimed: " + quantity + " x Fire cape");
					player.donationvalue += paid;
					break;
				case 34:
					player.getInventory().addItem(11848, 1 * quantity);
					player.sendMessage("Donation claimed: " + quantity + " x Barrows - Dharok's set");
					player.donationvalue += paid;
					break;
				case 35:
					player.getInventory().addItem(11846, 1 * quantity);
					player.sendMessage("Donation claimed: " + quantity + " x Barrows - Ahrim's set");
					player.donationvalue += paid;
					break;
				case 36:
					player.getInventory().addItem(11852, 1 * quantity);
					player.sendMessage("Donation claimed: " + quantity + " x Barrows - Karil's set");
					player.donationvalue += paid;
					break;
				case 37:
					player.getInventory().addItem(11850, 1 * quantity);
					player.sendMessage("Donation claimed: " + quantity + " x Barrows - Guthan's set");
					player.donationvalue += paid;
					break;
				case 38:
					player.getInventory().addItem(11856, 1 * quantity);
					player.sendMessage("Donation claimed: " + quantity + " x Barrows - Verac's set");
					player.donationvalue += paid;
					break;
				case 39:
					player.getInventory().addItem(11854, 1 * quantity);
					player.sendMessage("Donation claimed: " + quantity + " x Barrows - Torag's set");
					player.donationvalue += paid;
					break;	
				}
				rs.updateInt("claimed", 1); // do not delete otherwise they can reclaim!
				rs.updateRow();
				checkUpgrade(player);
			}

			destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private void checkUpgrade(Player player) {
		if (player.donationvalue >= 10 && player.donationvalue < 25 && player.donator != true) {
			player.donator = true;
			player.sendMessage("As you've donated $10 or more, your status has been changed to Donator!");
			return;
		}
		if (player.donationvalue >= 25 && player.donationvalue < 50 && player.superDonator != true) {
			player.donator = false;
			player.superDonator = true;
			player.sendMessage("As you've donated $25 or more, your status has been changed to Super Donator!");
			return;
		}
		if (player.donationvalue >= 50 && player.donationvalue < 100 && player.extremeDonator != true) {
			player.donator = false;
			player.superDonator = false;
			player.extremeDonator = true;
			player.sendMessage("As you've donated $50 or more, your status has been changed to Extreme Donator!");
			return;
		}
		if (player.donationvalue >= 100 && player.donationvalue < 250 && player.ultimateDonator != true) {
			player.donator = false;
			player.superDonator = false;
			player.extremeDonator = false;
			player.ultimateDonator = true;
			player.sendMessage("As you've donated $100 or more, your status has been changed to Ultimate Donator!");
			return;
		}
		if (player.donationvalue >= 250 && player.donationvalue < 500 && player.legendaryDonator != true) {
			player.donator = false;
			player.superDonator = false;
			player.extremeDonator = false;
			player.ultimateDonator = false;
			player.legendaryDonator = true;
			player.sendMessage("As you've donated $250 or more, your status has been changed to Legendary Donator!");
			return;
		}
		if (player.donationvalue >= 500 && player.donationvalue < 1000 && player.vipDonator != true) {
			player.donator = false;
			player.superDonator = false;
			player.extremeDonator = false;
			player.ultimateDonator = false;
			player.legendaryDonator = false;
			player.vipDonator = true;
			player.sendMessage("As you've donated $500 or more, your status has been changed to VIP Donator!");
			return;
		}
		if (player.donationvalue >= 1000 && player.sponsorDonator != true) {
			player.donator = false;
			player.superDonator = false;
			player.extremeDonator = false;
			player.ultimateDonator = false;
			player.legendaryDonator = false;
			player.vipDonator = false;
			player.sponsorDonator = true;
			player.sendMessage("As you've donated $1000 or more, your status has been changed to Sponsor!");
			return;
		}
	}
	/**
	 *
	 * @param host the host ip address or url
	 * @param database the name of the database
	 * @param user the user attached to the database
	 * @param pass the users password
	 * @return true if connected
	 */
	public boolean connect(String host, String database, String user, String pass) {
		try {
			this.conn = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+database, user, pass);
			return true;
		} catch (SQLException e) {
			System.out.println("Failing connecting to database!");
			return false;
		}
	}

	/**
	 * Disconnects from the MySQL server and destroy the connection
	 * and statement instances
	 */
	public void destroy() {
        try {
    		conn.close();
        	conn = null;
        	if (stmt != null) {
    			stmt.close();
        		stmt = null;
        	}
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

	/**
	 * Executes an update query on the database
	 * @param query
	 * @see {@link Statement#executeUpdate}
	 */
	public int executeUpdate(String query) {
        try {
        	this.stmt = this.conn.createStatement(1005, 1008);
            int results = stmt.executeUpdate(query);
            return results;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

	/**
	 * Executres a query on the database
	 * @param query
	 * @see {@link Statement#executeQuery(String)}
	 * @return the results, never null
	 */
	public ResultSet executeQuery(String query) {
        try {
        	this.stmt = this.conn.createStatement(1005, 1008);
            ResultSet results = stmt.executeQuery(query);
            return results;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
