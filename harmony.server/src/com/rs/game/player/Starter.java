package com.rs.game.player;

import com.rs.Settings;
import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.starter.StarterMap;

public class Starter {

        public static final int MAX_STARTER_COUNT = 3;

        

        public static void appendStarter(Player player) {
                String ip = player.getSession().getIP();
                int count = StarterMap.getSingleton().getCount(ip);
                player.starter = 1;
                if (count >= MAX_STARTER_COUNT) {
                        return;
                }
                if (player.ironman == true) {

			player.getInventory().addItem(1856, 1);
        			player.getInventory().addItem(1725, 1);//makeinterface
        			player.getInventory().addItem(1321, 1);
        			player.getInventory().addItem(1333, 1);
        			player.getInventory().addItem(841, 1);
        			player.getInventory().addItem(857, 1);
        			player.getInventory().addItem(884, 250);
        			player.getInventory().addItem(892, 50);
        			player.getInventory().addItem(1359, 1);
        			player.getInventory().addItem(1275, 1);
        			player.getInventory().addItem(318, 20);
        			player.getInventory().addItem(1512, 20);
        			player.getInventory().addItem(995, 50000);
                    player.getHintIconsManager().removeUnsavedHintIcon();
                    player.getMusicsManager().reset();
                    player.getCombatDefinitions().setAutoRelatie(false);
                    player.getCombatDefinitions().refreshAutoRelatie();
                    StarterMap.getSingleton().addIP(ip);
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
		} else {
                	
             
			player.getInventory().addItem(1856, 1);
                player.getInventory().addItem(1540, 1);
				player.getInventory().addItem(995, 1000000);
    			player.getInventory().addItem(1725, 1);//makeinterface
    			player.getInventory().addItem(380, 150);
    			player.getInventory().addItem(3105, 1);
    			player.getInventory().addItem(1321, 1);
    			player.getInventory().addItem(1333, 1);
    			player.getInventory().addItem(841, 1);
    			player.getInventory().addItem(857, 1);
    			player.getInventory().addItem(884, 250);
    			player.getInventory().addItem(892, 50);
    			player.getInventory().addItem(1359, 1);
    			player.getInventory().addItem(1275, 1);
    			player.getInventory().addItem(318, 20);
    			player.getInventory().addItem(1512, 20);
    			player.getInventory().addItem(6568, 1);
    			//----

                player.getHintIconsManager().removeUnsavedHintIcon();
                player.getMusicsManager().reset();
                player.getCombatDefinitions().setAutoRelatie(false);
                player.getCombatDefinitions().refreshAutoRelatie();
                StarterMap.getSingleton().addIP(ip);

               // player.unlo
                Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
		}
        }
}