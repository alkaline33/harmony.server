package com.rs.database.mysql.impl.cart;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.rs.cores.mysql.Database;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;

public class CartSystem implements Runnable { 

	public static final String HOST = "";
	public static final String USER = ""; // lol u do the same thing i do, make user and database the same xdD
	public static final String PASS = "";
	public static final String DATABASE = "";
	
	private Player player;
	
	public CartSystem(Player player) {
		this.player = player;
	}
	
	@Override
	public void run() {
		try {
			Database db = new Database(HOST, USER, PASS, DATABASE);
			
			if (!db.init()) {
				return;
			}
			
			String user = Utils.formatPlayerNameForDisplay(player.getUsername());
			ResultSet rs = db.executeQuery("SELECT * FROM purchases WHERE custom="+user+" WHERE claimed=0");
			
			while (rs.next()) {
				CartItems item = CartItems.getItem(rs.getInt("item_number"));
				
				if (item == null 
						|| !item.getName().equalsIgnoreCase(rs.getString("item_name")) 
						|| item.getValue() != rs.getDouble("mc_gross")) {
					continue;
				}
				
				switch (item.getId()) {
				case 1:
					// do item adding, processing w/e for package 1 or w/e the ID is
					break;
				}
				
				rs.updateInt("clamed", 1); 
				rs.updateRow();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
