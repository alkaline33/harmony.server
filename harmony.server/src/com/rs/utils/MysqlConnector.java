package com.rs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import com.rs.game.World;
import com.rs.game.player.Player;

public class MysqlConnector {

	private static Connection connection;
private static long lasConnection = System.currentTimeMillis();
	static {
		createConnection();
	}

	public static void init() {
		createConnection();
	}

	public static void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			connection = DriverManager.getConnection(
					"jdbc:mysql://drygonscape.co.uk/drygonsc_donate", "drygonsc_don8",
					"nike2323");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void destroyConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void claimPayment(final Player p, final String name1) {
		try {
			if (System.currentTimeMillis() - lasConnection > 10000) {
				destroyConnection();
				createConnection();
				lasConnection = System.currentTimeMillis();
			}
			Statement s = connection.createStatement();
			String name2 = name1.replaceAll(" ", "_");
			String query = "SELECT * FROM itemstore WHERE username = '"+name2+"'";
			ResultSet rs = s.executeQuery(query);
			boolean claimed = false;
			while(rs.next()) {
				int prod = Integer.parseInt(rs.getString("productid"));
				int price = Integer.parseInt(rs.getString("price"));
				if (prod == 1 && price == 5) {
					claimed = true;
					p.getBank().addItem(29944, 1, true);
					World.sendWorldMessage("<col=3c5f96><img=6>" + p.getDisplayName() + " has just donated!", false);
				} else if (prod == 2 && price  == 10) {
					claimed = true;
					World.sendWorldMessage("<col=3c5f96><img=6>" + p.getDisplayName() + " has just donated!", false);
				}
				}
				if (claimed) {
					s.execute("DELETE FROM `itemstore` WHERE `username` = '"+name2+"';");
					
					p.sendMessage("Thank you for donating!");
				}
				else
					p.sendMessage("You haven't donated yet!");
			
				
			} catch (Exception e) {
				e.printStackTrace();
				p.sendMessage("You were unable to claim your item.");
			}
		}
		
	}