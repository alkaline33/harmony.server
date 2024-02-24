//package com.rs.utils;
//
//import java.sql.PreparedStatement;
//
//import com.rs.cores.mysql.Database;
//import com.rs.game.item.Item;
//import com.rs.game.player.Player;
//
///**
// * Add to item drops (preferrable rare drops) like so:
// * new Thread(new WebActivityFeed(player, item)).start();
// * 
// * server-side done nub, pay me D: :3
// */
//public class WebActivityFeed implements Runnable {
//
//	private static String IP = "66.85.79.60";// connection ip
//	private static String DB = "drygonew_drygon_drops";// database name
//	private static String User = "drygonew_drops";// username
//	private static String Pass = "Nike2323"; // password
//	
//	private Player player;
//	private Item item;
//	
//	public WebActivityFeed(Player player, Item item) {
//		this.player = player;
//		this.item = item;
//	}//much cleaner c:
//
//	@Override
//	public void run() {
//		try {
//			Database db = new Database(this.IP, this.User, this.Pass, this.DB);
//			
//			if (!db.init()) {
//				return;
//			}
//			 
//			PreparedStatement stmt = db.prepare("INSERT INTO item_drops (item_id, item_name, item_amt, username) VALUES(?, ?, ?, ?)");
//			stmt.setInt(1, this.item.getId());
//			stmt.setString(2, this.item.getName());
//			stmt.setInt(3, this.item.getAmount());
//			stmt.setString(4, this.player.getDisplayName().replace("_", " "));
//			stmt.execute();
//			 
//			db.destroyAll();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}