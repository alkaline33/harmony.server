package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * @author Mr_Joopz @rune-server.org
 */
public class MagicChest {

    private static final int COMMON[][] = {{1165, 1},{1163, 1},{1161, 1},{1159, 1},{1157, 1},{1155, 1},{1153, 1},{1079, 1},{1077, 1},{1075, 1},{1073, 1},{1071, 1},{1069, 1},{1067, 1},{1127, 1},{1125, 1},{1123, 1},{1121, 1},{1119, 1},{1117, 1},{1115, 1},{1127, 1},};
    private static final int UNCOMMON[][] = {{3140, 1},{14479, 1},{4087, 1},{11335, 1},};
    private static final int RARE[][] = {{4714, 1},{4712, 1},{4708, 1},{4730, 1},{4728, 1},{4724, 1},{4738, 1},{4736, 1},{4732, 1},{4759, 1},{4757, 1},{4753, 1},{4751, 1},{4749, 1},{4745, 1},{4722, 1},{4720, 1},{4716, 1},};
    private static int reward, r, c, q;

    private static void handle(Player p) {
    	if (!p.getInventory().containsItem(29542, 1)) {
    		p.sendMessage("You need a Harmony key [Level 1] to use this chest!");
    		return;
    	}
    	WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					//p.getInterfaceManager().sendMagicChest();
					p.getInterfaceManager().sendInterface(28);
					p.getPackets().sendIComponentText(28, 19, "Harmony Chest");
					p.getPackets().sendIComponentText(28, 3, "The final item is your loot.");
					p.getPackets().sendIComponentModel(28, 43, -1);
					p.getPackets().sendIComponentModel(28, 40, -1);
					p.getPackets().sendIComponentModel(28, 36, -1);
					p.getPackets().sendIComponentModel(28, 33, -1);
					p.getPackets().sendIComponentModel(28, 30, -1);
					p.getPackets().sendIComponentModel(28, 27, -1);
					p.getPackets().sendIComponentModel(28, 24, -1);
					p.getPackets().sendIComponentModel(28, 21, -1);
					p.getPackets().sendIComponentText(28, 42, "");
					p.getPackets().sendIComponentText(28, 39, "");
					p.getPackets().sendIComponentText(28, 37, "");
					p.getPackets().sendIComponentText(28, 34, "");
					p.getPackets().sendIComponentText(28, 31, "");
					p.getPackets().sendIComponentText(28, 28, "");
					p.getPackets().sendIComponentText(28, 25, "");
					p.getPackets().sendIComponentText(28, 22, "");
					p.getPackets().sendIComponentText(28, 44, "");
					p.getPackets().sendIComponentText(28, 41, "");
					p.getPackets().sendIComponentText(28, 38, "");
					p.getPackets().sendIComponentText(28, 35, "");
					p.getPackets().sendIComponentText(28, 32, "");
					p.getPackets().sendIComponentText(28, 29, "");
					p.getPackets().sendIComponentText(28, 26, "");
					p.getPackets().sendIComponentText(28, 23, "");
				} else if (loop == 1) {
				    r = Utils.random(50);
			        switch (r) {
			            case 1:
			            case 39:
			                r = Utils.random(RARE.length);
			                q = RARE[r][1];
			                reward = RARE[r][0];
						// p.getPackets().sendIComponentModel(28, 43, getModelId());
						p.getPackets().sendItemOnIComponent(28, 43, reward, 1);
			            	p.getPackets().sendIComponentText(28, 42, getNameForItem());
			                break;
			            case 3:
			            case 50:
			            case 46:
			            case 44:
			            case 0:
			            case 29:
			            case 31:
			            case 40:
			            case 49:
			            case 77:
			            case 11:
			            case 14:
			            case 19:
			            case 10:
			            case 4:
			            case 5:
			                r = Utils.random(UNCOMMON.length);
			                q = UNCOMMON[r][1];
			                reward = UNCOMMON[r][0];
						// p.getPackets().sendIComponentModel(28, 43, getModelId());
						p.getPackets().sendItemOnIComponent(28, 43, reward, 1);
			            	p.getPackets().sendIComponentText(28, 42, getNameForItem());
			                break;
			            default:
			                c = Utils.random(COMMON[0][1]);
			                r = Utils.random(COMMON.length);
			                q = COMMON[r][1];
			                reward = COMMON[r][0];
						// p.getPackets().sendIComponentModel(28, 43, getModelId());
						p.getPackets().sendItemOnIComponent(28, 43, reward, 1);
			            	p.getPackets().sendIComponentText(28, 42, getNameForItem());
			              break;
			        }
				} else if (loop == 2) {
				    r = Utils.random(50);
			        switch (r) {
			            case 1:
			                r = Utils.random(RARE.length);
			                q = RARE[r][1];
			                reward = RARE[r][0];
						// p.getPackets().sendIComponentModel(28, 40, getModelId());
						p.getPackets().sendItemOnIComponent(28, 40, reward, 1);
			            	p.getPackets().sendIComponentText(28, 39, getNameForItem());
			                break;
			            case 3:
			            case 50:
			            case 46:
			            case 94:
			            case 0:
			            case 29:
			            case 71:
			            case 90:
			            case 15:
			            case 33:
			            case 61:
			            case 69:
			            case 77:
			            case 81:
			            case 84:
			            case 89:
			            case 100:
			            case 4:
			            case 5:
			                r = Utils.random(UNCOMMON.length);
			                q = UNCOMMON[r][1];
			                reward = UNCOMMON[r][0];
						// p.getPackets().sendIComponentModel(28, 40, getModelId());
						p.getPackets().sendItemOnIComponent(28, 40, reward, 1);
			            	p.getPackets().sendIComponentText(28, 39, getNameForItem());
			                break;
			            default:
			                c = Utils.random(COMMON[0][1]);
			                r = Utils.random(COMMON.length);
			                q = COMMON[r][1];
			                reward = COMMON[r][0];
						// p.getPackets().sendIComponentModel(28, 40, getModelId());
						p.getPackets().sendItemOnIComponent(28, 40, reward, 1);
			            	p.getPackets().sendIComponentText(28, 39, getNameForItem());
			              break;
			        }
				} else if (loop == 3) {
				    r = Utils.random(50);
			        switch (r) {
			            case 1:
			                r = Utils.random(RARE.length);
			                q = RARE[r][1];
			                reward = RARE[r][0];
						// p.getPackets().sendIComponentModel(28, 36, getModelId());
						p.getPackets().sendItemOnIComponent(28, 36, reward, 1);
			            	p.getPackets().sendIComponentText(28, 37, getNameForItem());
			                break;
			            case 3:
			            case 50:
			            case 46:
			            case 94:
			            case 0:
			            case 29:
			            case 71:
			            case 90:
			            case 15:
			            case 33:
			            case 61:
			            case 69:
			            case 77:
			            case 81:
			            case 84:
			            case 89:
			            case 100:
			            case 4:
			            case 5:
			                r = Utils.random(UNCOMMON.length);
			                q = UNCOMMON[r][1];
			                reward = UNCOMMON[r][0];
						// p.getPackets().sendIComponentModel(28, 36, getModelId());
						p.getPackets().sendItemOnIComponent(28, 36, reward, 1);
			            	p.getPackets().sendIComponentText(28, 37, getNameForItem());
			                break;
			            default:
			                c = Utils.random(COMMON[0][1]);
			                r = Utils.random(COMMON.length);
			                q = COMMON[r][1];
			                reward = COMMON[r][0];
						// p.getPackets().sendIComponentModel(28, 36, getModelId());
						p.getPackets().sendItemOnIComponent(28, 36, reward, 1);
			            	p.getPackets().sendIComponentText(28, 37, getNameForItem());
			              break;
			        }
				} else if (loop == 4) {
				    r = Utils.random(50);
			        switch (r) {
			            case 1:
			                r = Utils.random(RARE.length);
			                q = RARE[r][1];
			                reward = RARE[r][0];
						// p.getPackets().sendIComponentModel(28, 33, getModelId());
						p.getPackets().sendItemOnIComponent(28, 33, reward, 1);
			            	p.getPackets().sendIComponentText(28, 34, getNameForItem());
			                break;
			            case 3:
			            case 50:
			            case 46:
			            case 94:
			            case 0:
			            case 29:
			            case 71:
			            case 90:
			            case 15:
			            case 33:
			            case 61:
			            case 69:
			            case 77:
			            case 81:
			            case 84:
			            case 89:
			            case 100:
			            case 4:
			            case 5:
			                r = Utils.random(UNCOMMON.length);
			                q = UNCOMMON[r][1];
			                reward = UNCOMMON[r][0];
						// p.getPackets().sendIComponentModel(28, 33, getModelId());
						p.getPackets().sendItemOnIComponent(28, 33, reward, 1);
			            	p.getPackets().sendIComponentText(28, 34, getNameForItem());
			                break;
			            default:
			                c = Utils.random(COMMON[0][1]);
			                r = Utils.random(COMMON.length);
			                q = COMMON[r][1];
			                reward = COMMON[r][0];
						// p.getPackets().sendIComponentModel(28, 33, getModelId());
						p.getPackets().sendItemOnIComponent(28, 33, reward, 1);
			            	p.getPackets().sendIComponentText(28, 34, getNameForItem());
			              break;
			        }
				} else if (loop == 5) {
				    r = Utils.random(50);
			        switch (r) {
			            case 1:
			                r = Utils.random(RARE.length);
			                q = RARE[r][1];
			                reward = RARE[r][0];
						// p.getPackets().sendIComponentModel(28, 30, getModelId());
						p.getPackets().sendItemOnIComponent(28, 30, reward, 1);
			            	p.getPackets().sendIComponentText(28, 31, getNameForItem());
			                break;
			            case 3:
			            case 50:
			            case 46:
			            case 94:
			            case 0:
			            case 29:
			            case 71:
			            case 90:
			            case 15:
			            case 33:
			            case 61:
			            case 69:
			            case 77:
			            case 81:
			            case 84:
			            case 89:
			            case 100:
			            case 4:
			            case 5:
			                r = Utils.random(UNCOMMON.length);
			                q = UNCOMMON[r][1];
			                reward = UNCOMMON[r][0];
						p.getPackets().sendItemOnIComponent(28, 30, reward, 1);
			            	p.getPackets().sendIComponentText(28, 31, getNameForItem());
			                break;
			            default:
			                c = Utils.random(COMMON[0][1]);
			                r = Utils.random(COMMON.length);
			                q = COMMON[r][1];
			                reward = COMMON[r][0];
						p.getPackets().sendItemOnIComponent(28, 30, reward, 1);
			            	p.getPackets().sendIComponentText(28, 31, getNameForItem());
			              break;
			        }
				} else if (loop == 6) {
				    r = Utils.random(50);
			        switch (r) {
			            case 1:
			                r = Utils.random(RARE.length);
			                q = RARE[r][1];
			                reward = RARE[r][0];
						p.getPackets().sendItemOnIComponent(28, 27, reward, 1);
			            	p.getPackets().sendIComponentText(28, 28, getNameForItem());
			                break;
			            case 3:
			            case 50:
			            case 46:
			            case 94:
			            case 0:
			            case 29:
			            case 71:
			            case 90:
			            case 15:
			            case 33:
			            case 61:
			            case 69:
			            case 77:
			            case 81:
			            case 84:
			            case 89:
			            case 100:
			            case 4:
			            case 5:
			                r = Utils.random(UNCOMMON.length);
			                q = UNCOMMON[r][1];
			                reward = UNCOMMON[r][0];
						p.getPackets().sendItemOnIComponent(28, 27, reward, 1);
			            	p.getPackets().sendIComponentText(28, 28, getNameForItem());
			                break;
			            default:
			                c = Utils.random(COMMON[0][1]);
			                r = Utils.random(COMMON.length);
			                q = COMMON[r][1];
			                reward = COMMON[r][0];
						p.getPackets().sendItemOnIComponent(28, 27, reward, 1);
			            	p.getPackets().sendIComponentText(28, 28, getNameForItem());
			              break;
			        }
				} else if (loop == 7) {
				    r = Utils.random(50);
			        switch (r) {
			            case 1:
			                r = Utils.random(RARE.length);
			                q = RARE[r][1];
			                reward = RARE[r][0];
						p.getPackets().sendItemOnIComponent(28, 24, reward, 1);
			            	p.getPackets().sendIComponentText(28, 25, getNameForItem());
			                break;
			            case 3:
			            case 50:
			            case 46:
			            case 94:
			            case 0:
			            case 29:
			            case 71:
			            case 90:
			            case 15:
			            case 33:
			            case 61:
			            case 69:
			            case 77:
			            case 81:
			            case 84:
			            case 89:
			            case 100:
			            case 4:
			            case 5:
			                r = Utils.random(UNCOMMON.length);
			                q = UNCOMMON[r][1];
			                reward = UNCOMMON[r][0];
						p.getPackets().sendItemOnIComponent(28, 24, reward, 1);
			            	p.getPackets().sendIComponentText(28, 25, getNameForItem());
			                break;
			            default:
			                c = Utils.random(COMMON[0][1]);
			                r = Utils.random(COMMON.length);
			                q = COMMON[r][1];
			                reward = COMMON[r][0];
						p.getPackets().sendItemOnIComponent(28, 24, reward, 1);
			            	p.getPackets().sendIComponentText(28, 25, getNameForItem());
			              break;
			        }
				} else if (loop == 8) {
				    r = Utils.random(50);
			        switch (r) {
			            case 1:
			                r = Utils.random(RARE.length);
			                q = RARE[r][1];
			                reward = RARE[r][0];
						p.getPackets().sendItemOnIComponent(28, 21, reward, 1);
			            	p.getPackets().sendIComponentText(28, 22, getNameForItem());
			            	if (p.getInventory().contains(29542)) {
			            		 p.getInventory().deleteItem(29542, 1);
							p.getBank().addItem(RARE[r][0], q, true);
				            	  p.sendMessage("<col=ff0000>Your reward is a "+getNameForItem()+".");
			            	} else {
			            		p.sendMessage("<col=ff0000>You don't have the required key for the reward.");
			            	}
			                break;
			            case 3:
			            case 50:
			            case 46:
			            case 94:
			            case 0:
			            case 29:
			            case 71:
			            case 90:
			            case 15:
			            case 33:
			            case 61:
			            case 69:
			            case 77:
			            case 81:
			            case 84:
			            case 89:
			            case 100:
			            case 4:
			            case 5:
			                r = Utils.random(UNCOMMON.length);
			                q = UNCOMMON[r][1];
			                reward = UNCOMMON[r][0];
						p.getPackets().sendItemOnIComponent(28, 21, reward, 1);
			            	p.getPackets().sendIComponentText(28, 22, getNameForItem());
			            	if (p.getInventory().contains(29542)) {
			            		 p.getInventory().deleteItem(29542, 1);
							p.getBank().addItem(UNCOMMON[r][0], q, true);
				            	  p.sendMessage("<col=ff0000>Your reward is a "+getNameForItem()+".");
			            	} else {
			            		p.sendMessage("<col=ff0000>You don't have the required key for the reward.");
			            	}
			                break;
			            default:
			                c = Utils.random(COMMON[0][1]);
			                r = Utils.random(COMMON.length);
			                q = COMMON[r][1];
			                reward = COMMON[r][0];
						p.getPackets().sendItemOnIComponent(28, 21, reward, 1);
			            	p.getPackets().sendIComponentText(28, 22, getNameForItem());
			            	if (p.getInventory().contains(29542)) {
			            		 p.getInventory().deleteItem(29542, 1);
							p.getBank().addItem(COMMON[r][0], COMMON[r][0] == 995 ? c : q, true);
				            	  p.sendMessage("<col=ff0000>Your reward is a "+getNameForItem()+".");
			            	} else {
			            		p.sendMessage("<col=ff0000>You don't have the required key for the reward.");
			            	}
			                break;
			        }
				} else if (loop == 9) {
					stop();
				}
				loop++;
			}
		}, 0, 1);
     
    }

    private static String getGrammar() {
        if (q == 1) {
            return sw("a") || sw("u") || sw("o") ? "an" : "a";
        }
        return q + "";
    }

    private static boolean sw(String n) {
        return getNameForItem().startsWith(n);
    }
    
	private static int getModelId() {
		return ItemDefinitions.getItemDefinitions(reward).modelId;
	}

    private static String getNameForItem() {
        switch (reward) {
            case 995:
                return COMMON[r][1] == 1 ? "coin" : "coins";
            case 1061:
                return "pair of leather boots";
            case 592:
                return "ash";
            case 563:
                return "law runes";
            case 561:
                return "nature runes";
            case 1329:
                return "mithril scimitar";
            case 1315:
                return "mithril two handed sword";
        }
        return ItemDefinitions.getItemDefinitions(reward).getName().toLowerCase();
    }

    public static boolean isBox(int objectId, Player p) {
        switch (objectId) {
            case 13291:
                handle(p);
                return true;
        }
        return false;
    }
}