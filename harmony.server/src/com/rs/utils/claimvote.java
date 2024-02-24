package com.rs.utils;

import java.sql.ResultSet;

import com.rs.Settings;
import com.rs.cores.mysql.Database;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.game.player.content.event.season.SeasonEvent;
/**
 * Add to command like so:
 * new Thread(new FoxVote(player)).start();
 * 
 */
public class claimvote implements Runnable {
    
    public static final String host = "108.167.183.252";
    public static final String user = "harmonyr_votes";
    public static final String pass = "+HjlO7tZw.L^";
    public static final String data = "harmonyr_votes";
    
    private Player player;
    public claimvote(Player player) {
        this.player = player;
    }
    
    @Override
    public void run() {
        try {
            Database db = new Database(host, user, pass, data);
            
            if (!db.init()) {
                return;
            }
            
            String name = player.getUsername();
            
            ResultSet rs = db.executeQuery("SELECT * FROM fx_votes WHERE username='"+name+"' AND claimed=0 AND callback_date IS NOT NULL");
            while (rs.next()) {
                String timestamp = rs.getTimestamp("callback_date").toString();
                String ipAddress = rs.getString("ip_address");
                if (Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("double vote points")) {
                    player.VotePoint++;
                    player.sendMessage("<col=00ff00>Spotlight rewards you 1 extra vote point, you now have " + player.VotePoint + " vote points.</col>");
                }
                player.VotePoint ++;
                if (player.getInventory().hasFreeSlots() != true) {
                    player.getBank().addItem(29596, 1, true);
                } else {
                player.getInventory().addItem(29596, 1);
                }
                //player.amountvotedthismonth4 ++;
                //TopVoters.checkRank(player);
                player.sendMessage("<col=00ff00>You were rewarded 1 vote point & 1 vote box! You now have "+player.VotePoint+" vote points.</col>");
                if (player.votedtoday != true) {
                    World.sendWorldMessage("<col=ff0000><img=7>"+player.getDisplayName()+" has just voted! Vote now by typing ::vote!", false);
                    player.votedtoday = true;
                }
                
                rs.updateInt("claimed", 1); // do not delete otherwise they can reclaim!
                rs.updateRow();
                
            //  System.out.println("[FoxVote] Vote claimed by "+name+". (sid: "+siteId+", ip: "+ipAddress+", time: "+timestamp+")");
            }
            db.destroyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
