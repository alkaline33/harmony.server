package com.rs.game.item;


import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.SuperHeating;
import com.rs.game.player.actions.crafting.Enchanting;
import com.rs.game.player.content.Magic;
import com.rs.utils.Colors;


public class MagicOnItem {


    public static final int LOW_ALCHEMY = 38;
    public static final int HIGH_ALCHEMY = 59;
    public static final int SUPER_HEAT = 50;
    public static final int LV1_ENCHANT = 29;
    public static final int LV2_ENCHANT = 41;
    public static final int LV3_ENCHANT = 53;
    public static final int LV4_ENCHANT = 61;
    public static final int LV5_ENCHANT = 76;
    public static final int LV6_ENCHANT = 88;

    public static void handleMagic(Player player, int magicId, Item item) {
        int itemId = item.getId();
        switch (magicId) {
        
            case LOW_ALCHEMY:
                processAlchemy(player, item, true);
                break;
                
            case HIGH_ALCHEMY:
                processAlchemy(player, item, false);
                break;
                
            case LV1_ENCHANT:
                Enchanting.startEnchant(player, itemId, 1);
                break;
                
            case LV2_ENCHANT:
                Enchanting.startEnchant(player, itemId, 2);
                break;
                
            case LV3_ENCHANT:
                Enchanting.startEnchant(player, itemId, 3);
                break;
                
            case LV4_ENCHANT:
                Enchanting.startEnchant(player, itemId, 4);
                break;
                
            case LV5_ENCHANT:
                Enchanting.startEnchant(player, itemId, 5);
                break;
                
            case LV6_ENCHANT:
                Enchanting.startEnchant(player, itemId, 6);
                break;
                
            case SUPER_HEAT:
			SuperHeating.process(player, itemId, item);
                break;
                
            default:
                player.sendMessage("Invalid Magic Id: "+magicId+"");
                break;
        }
    }
    
	public static void processAlchemy(Player player, Item item, boolean low) {
		if (player.getSkills().getLevel(Skills.MAGIC) < (low == true ? 21 : 55)) {
			player.getPackets().sendGameMessage("You do not have the required level to cast this spell.");
			return;
		}
		if (item.getId() == 995) {
			player.getPackets().sendGameMessage("You can't alch this!");
			return;
		}
		if (World.TheCalamity(player)) {
			player.getPackets().sendGameMessage("You have no use for this spell here!");
			return;
		}
		if (item.getId() >= 5020 && item.getId() <= 5023) {
			player.sendMessage("You cannot alch these tickets, please sell them to the shop.");
			return;
		}
		if (item.getId() == 20072) {
			player.alchedddefender = true;
		}
		if (!Magic.checkRunes(player, true, 554, low == true ? 3 : 5, 561, 1)) {
			return;
		}
		if (item.getDefinitions().getValue() >= 5000000 && player.safealchemy != true) {
			player.getDialogueManager().startDialogue("SimpleMessage", ""+Colors.red+"You currently have safe alchemy active so you cannot alch anything over the value of 5 million. <br>You can turn this off in settings on the quest tab.");
			//player.sendMessage(Colors.red+"You currently have safe alchemy active so you cannot alch anything over the value of 5 million. You can turn this off in settings on the quest tab.");
			return;
		}
		// System.out.println("reee");
		player.setNextAnimation(new Animation(17099));
		player.setNextGraphics(new Graphics(3214));
		// player.getInventory().deleteItem(561, 1);
		// player.getInventory().deleteItem(554, low == true ? 3 : 5);
		player.getInventory().deleteItem(item.getId(), 1);
		int baseValue = ItemDefinitions.getItemDefinitions(item.getId()).getValue();
		int value = baseValue / (low == true ? 3 : 2);
		player.getInventory().addItem(995, value);
		int xp = low == true ? 31 : 65;
		player.getSkills().addXp(Skills.MAGIC, xp);
		player.getPackets().sendGlobalConfig(168, 7);
	}
    
}