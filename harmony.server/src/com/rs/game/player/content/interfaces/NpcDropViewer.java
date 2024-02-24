
package com.rs.game.player.content.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.npc.Drop;
import com.rs.game.player.Player;
import com.rs.utils.NPCDrops;
import com.rs.utils.Utils;

public class NpcDropViewer {

    public static final int INTERFACE_ID = 3221;

    public static void sendInterface(Player player) {
        player.getInterfaceManager().sendInterface(INTERFACE_ID);
        cleanDrops(player);
    }

    public static void openInterface(Player player) {
        player.getInterfaceManager().sendInterface(3221);
        NpcDropViewer.sendDrops(player, 1615);
        player.getPackets().sendIComponentText(INTERFACE_ID, 37, "Abyssal demon");
        for (int i = 38; i <= 49; i++)
            player.getPackets().sendIComponentText(INTERFACE_ID, i, "");
    }


    /**
     * Formats the interface to clean any previous results...
     * Used on the start of the interface
     */
    private static void cleanDrops(Player player) {
        //Cleans the Left hand entrys
        for (int i = 37; i <= 49; i++)
            player.getPackets().sendIComponentText(INTERFACE_ID, i, "");

        //Cleans the right hand entrys
        for (int i = 76; i <= 348; i += 10) {
            player.getPackets().sendHideIComponent(INTERFACE_ID, i - 1, true); //Hidden name?
            player.getPackets().sendIComponentText(INTERFACE_ID, i, ""); //Name
            player.getPackets().sendIComponentText(INTERFACE_ID, i + 2, ""); //Amount
            player.getPackets().sendIComponentText(INTERFACE_ID, i + 4, ""); //Chance
            //	player.getPackets().sendIComponentText(INTERFACE_ID, i + 6, ""); //Value
        }
    }

    /**
     * Given a player to send to and a npcid to search for this will display the drops
     * @param player
     * @param npcId
     */
    public static void sendDrops(Player player, int npcId) {
        int componentIndex = 76;
        //cleanDrops(player);
        player.getPackets().sendIComponentText(INTERFACE_ID, 11, "Drop viewer for: " + NPCDefinitions.getNPCDefinitions(npcId).name);
        for (Drop drop : NPCDrops.getDrops(npcId)) {
            if (drop == null)
                continue;
            if (drop.getItemId() == 0)
                continue;
            int temp = componentIndex;
            player.getPackets().sendHideIComponent(INTERFACE_ID, temp - 1, false); //The Item Icon
            player.getPackets().sendItemOnIComponent(INTERFACE_ID, temp - 1, drop.getItemId(), drop.getMinAmount());
            player.getPackets().sendIComponentText(INTERFACE_ID, temp, ItemDefinitions.getItemDefinitions(drop.getItemId()).name);
            player.getPackets().sendIComponentText(INTERFACE_ID, temp + 2, drop.getFormattedMinAmount() + "-" + drop.getFormattedMaxAmount());
            player.getPackets().sendIComponentText(INTERFACE_ID, temp + 4, drop.getFormattedRate()); //Rate in % rather than a Chance 1/1
//			player.getPackets().sendIComponentText(INTERFACE_ID, temp + 4, drop.getRate() + "%"); //Rate in % rather than a Chance 1/1
            //	player.getPackets().sendIComponentText(INTERFACE_ID, temp + 6, ""+ItemDefinitions.getItemDefinitions(drop.getItemId()).value); //Requires formatting
            if (componentIndex < 348)
                componentIndex += 8;

        }
    }

    public static void sendPossibleNPCs(Player p, int page) {
        int startIndex = 37;
        p.npcPage = page;
        if (p.foundNpcs == null)
            return;
        // Cleans the Left hand entrys
        for (int i = 37; i <= 49; i++)
            p.getPackets().sendIComponentText(INTERFACE_ID, i, "");


        //p.sm("test..: " + p.foundNpcs.size());

        if (p.foundNpcs.size() > 13) {
            for (int i = 0; i < 13; i++) {
                if (i + (page * 13) < p.foundNpcs.size())
                    p.getPackets().sendIComponentText(INTERFACE_ID, startIndex + i,
                            NPCDefinitions.getNPCDefinitions(p.foundNpcs.get(i + (page * 13))).name);
            }
        } else {
            for (int i = 0; i < p.foundNpcs.size(); i++) {
                p.getPackets().sendIComponentText(INTERFACE_ID, startIndex + i, NPCDefinitions.getNPCDefinitions(p.foundNpcs.get(i)).name);
            }
        }
    }

    public static List<Integer> getAllNPCByPrefix(String npcPrefix) {
        List<Integer> possibleNpcs = new ArrayList<Integer>();
        for (String s : NPCDefinitions.npcDefinitionsByName.keySet()) {
            if (s.toLowerCase().contains(npcPrefix.toLowerCase())) {
                possibleNpcs.add(NPCDefinitions.npcDefinitionsByName.get(s));
            }
        }
        return possibleNpcs;
    }

    public static List<Integer> getNPCByDrops(String itemName) {
        List<Integer> possibleNpcs = new ArrayList<Integer>();
        List<String> duplicates = new ArrayList<String>();
        for (int i = 0; i < Utils.getNPCDefinitionsSize(); i++) {
            Drop[] drops = NPCDrops.getDrops(i);
            if (drops == null)
                continue;
            for (Drop d : drops) {
                if (ItemDefinitions.getItemDefinitions(d.getItemId()).name.toLowerCase().contains(itemName.toLowerCase())) {
                    String npcName = NPCDefinitions.getNPCDefinitions(i).name.toLowerCase();
                    if (duplicates.contains(npcName)) //since there are different id's for the same npc (eg. green dragons);
                        break;
                    duplicates.add(npcName);
                    possibleNpcs.add(i);
                    break;
                }
            }
        }
        duplicates.clear();
        return possibleNpcs;
    }

    public static void sendDropsExamine(Player player, int npcId) {
        player.getInterfaceManager().sendInterface(INTERFACE_ID);
        cleanDrops(player);
        player.getPackets().sendIComponentText(INTERFACE_ID, 11, "Drop viewer for: " + NPCDefinitions.getNPCDefinitions(npcId).name);
        sendDrops(player, npcId);
    }

    public static void handelButtons(Player player, int buttonId) {
        switch (buttonId) {

            case 23: //Find Item
                player.getPackets().sendInputNameScript("Enter the name of an item.");
                player.getTemporaryAttributtes().put("drop_find", Boolean.TRUE);
                break;

            case 27: //Find NPC
                player.getPackets().sendInputNameScript("Enter the name of an NPC");
                player.getTemporaryAttributtes().put("npc_find", Boolean.TRUE);
                break;

            case 51: //Next
                int currPage = player.npcPage;
                //Gets the highest page we could go to for this set of foundNPCS Math.ceil rounds up..
                int maxPage = (int) Math.ceil((double)player.foundNpcs.size() / 13.0);
                if(currPage + 1 >= maxPage) {
                    player.sm("There are no higher pages...");
                } else {
                    player.npcPage++;
                    sendPossibleNPCs(player, player.npcPage);
                }
                break;

            case 50: //Previous
                /**
                 * Not a lot of logic here since if they are on a higher page then it must be aloud previously
                 */
                currPage = player.npcPage;
                if(currPage == 0) {
                    player.sm("You are on the first page.");
                } else {
                    player.npcPage--;
                    sendPossibleNPCs(player, player.npcPage);
                }
                break;
        }
        if (buttonId >= 37 && buttonId <= 49) {
            try {
                sendDrops(player, player.foundNpcs.get(buttonId - 37 + (player.npcPage * 13)));
            } catch (Exception ex) {
                //don't want spam in the console if the player misclicked
            }
        }
    }
}
