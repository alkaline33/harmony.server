package com.rs.game.player.actions.mining;

import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public abstract class MiningBase extends Action {


	protected int emoteId;
	protected int gfxId;
	protected int pickaxeTime;
	
	public static void propect(final Player player, final String endMessage) {
		propect(player, "You examine the rock for ores....", endMessage);
	}
	
	public static void propect(final Player player, String startMessage, final String endMessage) {
		player.getPackets().sendGameMessage(startMessage, true);
		player.lock(5);
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.getPackets().sendGameMessage(endMessage);
			}
		}, 4);
	}
	
	protected boolean setPickaxe(Player player) {
		int level = player.getSkills().getLevel(Skills.MINING);
		int weaponId = player.getEquipment().getWeaponId();
		if (weaponId != -1) {
			switch (weaponId) {
			case 16315: // primal pic
				if (level >= 99) {
					emoteId = 13084;
					pickaxeTime = 26;
					return true;
				}
				break;
			case  20786: //Guilded Dragon Pickaxe
				if (level >= 61 && player.minanim == true) {
					emoteId = 16001;
					pickaxeTime = 21;
					return true;
				} else if (level >= 61 && player.miningskillfuanim == true) {
					emoteId = 17310;
					gfxId = 3304;
					pickaxeTime = 21;
					return true;
				} else if (level >= 61) {
					emoteId = 272;
					pickaxeTime = 21;
					return true;
				}
				break;
			case 15259: // dragon pickaxe
			case 29411:
				if (level >= 61 && player.minanim == true) {
					emoteId = 16001;
					pickaxeTime = 13;
					return true;
				} else if (level >= 61 && player.miningskillfuanim == true) {
					emoteId = 17310;
					gfxId = 3304;
					pickaxeTime = 13;
					return true;
				} else if (level >= 61) {
					emoteId = 12190;
					pickaxeTime = 13;
					return true;
				}
				break;
			case 20785: // G rune pickaxe
				if (level >= 41) {
					emoteId = 624;
					pickaxeTime = 11;
					return true;
				} else if (level >= 41 && player.miningskillfuanim == true) {
					emoteId = 17310;
					gfxId = 3304;
					pickaxeTime = 11;
					return true;
				}
				break;
			case 1275: // rune pickaxe
				if (level >= 41 && player.minanim == true) {
					emoteId = 16000;
					pickaxeTime = 10;
					return true;
				} else if (level >= 41 && player.miningskillfuanim == true) {
					emoteId = 17310;
					gfxId = 3304;
					pickaxeTime = 10;
					return true;
				} else if (level >= 41) {
					emoteId = 624;
					pickaxeTime = 10;
					return true;
				}
				break;
			case 20784: // G adam pickaxe
				if (level >= 31) {
					emoteId = 628;
					pickaxeTime = 8;
					return true;
				}
				break;
			case 1271: // adam pickaxe
				if (level >= 31 && player.miningskillfuanim == true) {
					emoteId = 17310;
					gfxId = 3304;
					pickaxeTime = 7;
					return true;
				} else 	if (level >= 31) {
					emoteId = 628;
					pickaxeTime = 7;
					return true;

				}
				break;
				
			case 20783: // G mith pickaxe
				if (level >= 21) {
					emoteId = 629;
					pickaxeTime = 6;
					return true;
				}
				break;
			case 1273: // mith pickaxe
			if (level >= 21 && player.miningskillfuanim == true) {
				emoteId = 17310;
				gfxId = 3304;
				pickaxeTime = 5;
				return true;
			} else if (level >= 21) {
					emoteId = 629;
					pickaxeTime = 5;
					return true;
				
				}
				break;
			case 20782: // G steel pickaxe
				if (level >= 6) {
					emoteId = 627;
					pickaxeTime = 4;
					return true;
				}
				break;
			case 1269: // steel pickaxe
				if (level >= 6 && player.miningskillfuanim == true) {
					emoteId = 17310;
					gfxId = 3304;
					pickaxeTime = 3;
					return true;
				} else if (level >= 6) {
					emoteId = 627;
					pickaxeTime = 3;
					return true;

				}
				break;
			case 20781: // G iron pickaxe
				emoteId = 626;
				pickaxeTime = 3;
				return true;
			case 1267: // iron pickaxe
				emoteId = 626;
				pickaxeTime = 2;
				return true;
			case 20780: // G bronze axe
				emoteId = 625;
				pickaxeTime = 2;
				return true;
			case 1265: // bronze axe
				emoteId = 625;
				pickaxeTime = 1;
				return true;
			case 13661: // Inferno adze
			 if (level >= 61 && player.miningskillfuanim == true) {
				emoteId = 17310;
				gfxId = 3304;
				pickaxeTime = 13;
				return true;
			 } else if (level >= 61) {
					emoteId = 10222;
					pickaxeTime = 13;
					return true;
			
			}
				break;
			}
		}
		if (player.getInventory().containsItemToolBelt(16315)) {
			if (level >= 99) {
				emoteId = 13084;
				pickaxeTime = 26;
				return true;
		}
		}
		if (player.getInventory().containsItemToolBelt(20786)) {
			if (level >= 61 && player.minanim == true) {
				emoteId = 16001;
				pickaxeTime = 21;
				return true;
			} else if (level >= 61 && player.miningskillfuanim == true) {
				emoteId = 17310;
				gfxId = 3304;
				pickaxeTime = 21;
				return true;
			} else if (level >= 61) {
				emoteId = 12190;
				pickaxeTime = 21;
				return true;
			}
		}
		if (player.getInventory().containsItemToolBelt(29411)) {
			if (level >= 61 && player.minanim == true) {
				emoteId = 16001;
				pickaxeTime = 13;
				return true;
			} else if (level >= 61 && player.miningskillfuanim == true) {
				emoteId = 17310;
				gfxId = 3304;
				pickaxeTime = 13;
				return true;
			} else if (level >= 61) {
				emoteId = 12190;
				pickaxeTime = 13;
				return true;
			}
		}
		if (player.getInventory().containsItemToolBelt(15259)) {
			if (level >= 61 && player.minanim == true) {
				emoteId = 16001;
				pickaxeTime = 13;
				return true;
			} else if (level >= 61 && player.miningskillfuanim == true) {
				emoteId = 17310;
				gfxId = 3304;
				pickaxeTime = 13;
				return true;
			} else if (level >= 61) {
				emoteId = 12190;
				pickaxeTime = 13;
				return true;
			}
		}
		if (player.getInventory().containsItemToolBelt(20785)) {
			if (level >= 41) {
				emoteId = 624;
				pickaxeTime = 11;
				return true;
			} else if (level >= 41 && player.miningskillfuanim == true) {
				emoteId = 17310;
				gfxId = 3304;
				pickaxeTime = 11;
				return true;
			}
		}
		if (player.getInventory().containsItemToolBelt(1275)) {
			if (level >= 41 && player.minanim == true) {
				emoteId = 16000;
				pickaxeTime = 12;
			} else if (level >= 41 && player.miningskillfuanim == true) {
				emoteId = 17310;
				gfxId = 3304;
				pickaxeTime = 10;
				return true;
			} else if (level >= 41) {
				emoteId = 624;
				pickaxeTime = 10;
				return true;
			}
		}
		if (player.getInventory().containsItemToolBelt(20784)) {
			 if (level >= 11 && player.miningskillfuanim == true) {
					emoteId = 17310;
					gfxId = 3304;
					pickaxeTime = 8;
					return true;
				 } else
			if (level >= 31) {
				emoteId = 628;
				pickaxeTime = 8;
				return true;
			}
		}
		if (player.getInventory().containsItemToolBelt(1271)) {
			 if (level >= 31 && player.miningskillfuanim == true) {
					emoteId = 17310;
					gfxId = 3304;
					pickaxeTime = 7;
					return true;
				 } else
			if (level >= 31) {
				emoteId = 628;
				pickaxeTime = 7;
				return true;
			
			}
		}
		if (player.getInventory().containsItemToolBelt(20783)) {
			
			if (level >= 21) {
				emoteId = 629;
				pickaxeTime = 5;
				return true;
			}
		}
		if (player.getInventory().containsItemToolBelt(1273)) {
			 if (level >= 21 && player.miningskillfuanim == true) {
					emoteId = 17310;
					gfxId = 3304;
					pickaxeTime = 5;
					return true;
				 } else
			if (level >= 21) {
				emoteId = 629;
				pickaxeTime = 5;
				return true;
			}
		}
		if (player.getInventory().containsItemToolBelt(20782)) {
			if (level >= 6) {
				emoteId = 627;
				pickaxeTime = 4;
				return true;
			}
		}
		if (player.getInventory().containsItemToolBelt(1269)) {
			if (level >= 6) {
				emoteId = 627;
				pickaxeTime = 3;
				return true;
			}
		}
		if (player.getInventory().containsItemToolBelt(20781)) {
			emoteId = 626;
			pickaxeTime = 3;
			return true;
		}
		if (player.getInventory().containsItemToolBelt(1267)) {
			emoteId = 626;
			pickaxeTime = 2;
			return true;
		}
		if (player.getInventory().containsItemToolBelt(20780)) {
			emoteId = 625;
			pickaxeTime = 2;
			return true;
		}
		if (player.getInventory().containsItemToolBelt(1265)) {
			emoteId = 625;
			pickaxeTime = 1;
			return true;
		}
		if (player.getInventory().containsItemToolBelt(13661)) {
			if (level >= 61) {
				emoteId = 10222;
				pickaxeTime = 13;
				return true;
			}
		}
		return false;

	}

	protected boolean hasPickaxe(Player player) {
		if (player.getInventory().containsOneItem(15259, 1275, 1271, 1273,
				1269, 1267, 1265, 13661, 29411, 20786, 16315)
				|| player.getInventory().containsItemToolBelt(1265))
			return true;
		int weaponId = player.getEquipment().getWeaponId();
		if (weaponId == -1 && !player.getInventory().containsItemToolBelt(1265))
			return false;
		switch (weaponId) {
		case 1265:// Bronze PickAxe
		case 1267:// Iron PickAxe
		case 1269:// Steel PickAxe
		case 1273:// Mithril PickAxe
		case 1271:// Adamant PickAxe
		case 1275:// Rune PickAxe
		case 15259:// Dragon PickAxe
		case 20786:// G Dragon PickAxe
		case 20785: // G Rune Pickaxe
		case 20784: // G Adamant Pickaxe
		case 20783: // G Mithril Pickaxe
		case 20782: // G Steel Pickaxe
		case 20781: // G Iron Pickaxe
		case 20780: // G Bronze Pickaxe
		case 29411:
		case 13661: // Inferno adze
		case 16315: // primal pic
			return true;
		default:
			return false;
		}
	}

	@Override
	public void stop(Player player) {
		setActionDelay(player, 3);
		player.refreshSpawnedObjects();
	}
	
}
