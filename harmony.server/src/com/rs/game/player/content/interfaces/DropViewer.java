package com.rs.game.player.content.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.npc.Drop;
import com.rs.game.player.Player;
import com.rs.utils.NPCDrops;
import com.rs.utils.Utils;

/**
 *
 * @author paolo
 *
 */
public class DropViewer {
    private static final int INTERFACE_ID = 3033;

    public static void sendInterface(Player player) {
        player.getInterfaceManager().sendInterface(INTERFACE_ID);
        for (int i = 101; i <= 112; i++)
            player.getPackets().sendIComponentText(INTERFACE_ID, i, "");
        cleanDrops(player);
        player.getPackets().sendIComponentText(INTERFACE_ID, 113, "");
    }

    private static void cleanDrops(Player player) {
        for (int i = 115; i <= 596; i += 10) {
            player.getPackets().sendHideIComponent(INTERFACE_ID, i - 1, true);
            player.getPackets().sendIComponentText(INTERFACE_ID, i, "");
            player.getPackets().sendIComponentText(INTERFACE_ID, i + 1, "");
            player.getPackets().sendIComponentText(INTERFACE_ID, i + 2, "");
            if (i == 125)
                ++i;
        }
    }

    public static void sendDrops(Player player, int npcId) {
        int componentIndex = 115;
        cleanDrops(player);
        player.getPackets().sendIComponentText(INTERFACE_ID, 14, "Drop viewer for: " + NPCDefinitions.getNPCDefinitions(npcId).name);
        for (Drop drop : NPCDrops.getDrops(npcId)) {
            if (drop == null)
                continue;
            if (drop.getItemId() == 0)
                continue;
            int temp = componentIndex;
            player.getPackets().sendHideIComponent(INTERFACE_ID, temp - 1, false);
            player.getPackets().sendItemOnIComponent(INTERFACE_ID, temp - 1, drop.getItemId(), drop.getMinAmount());
            player.getPackets().sendIComponentText(INTERFACE_ID, temp, ItemDefinitions.getItemDefinitions(drop.getItemId()).name);
            if (drop.getMinAmount() == drop.getMaxAmount()) {
                player.getPackets().sendIComponentText(INTERFACE_ID, temp + 1, drop.getMinAmount() +"");
            } else {
                player.getPackets().sendIComponentText(INTERFACE_ID, temp + 1, drop.getMinAmount() + "-" + drop.getMaxAmount());
            }

            Drop[] drops = NPCDrops.getDrops(npcId);
            player.getPackets().sendIComponentText(INTERFACE_ID, temp + 2, drop.getFormattedRate());
            //			player.getPackets().sendIComponentText(INTERFACE_ID, temp + 2, drop.getRate() + "%");
            if (componentIndex < 596)
                componentIndex += 10;
            if (componentIndex == 135) {
                ++componentIndex;
            }
        }
    }

    public static void sendPossibleNPCs(Player p, int page) {
        int startIndex = 101;
        p.npcPage = page;
        if (p.foundNpcs == null)
            return;
        for (int i = 101; i <= 112; i++)
            p.getPackets().sendIComponentText(INTERFACE_ID, i, "");
        if (p.foundNpcs.size() > 12) {
            for (int i = 0; i < 12; i++) {
                if (i + (page * 12) < p.foundNpcs.size())
                    p.getPackets().sendIComponentText(INTERFACE_ID, startIndex + i,
                            NPCDefinitions.getNPCDefinitions(p.foundNpcs.get(i + (page * 12))).name);
            }
        } else {
            for (int i = 0; i < p.foundNpcs.size(); i++) {
                p.getPackets().sendIComponentText(INTERFACE_ID, startIndex + i, NPCDefinitions.getNPCDefinitions(p.foundNpcs.get(i)).name);
            }
        }
        if (p.foundNpcs.size() > 12)
            p.getPackets().sendIComponentText(INTERFACE_ID, 113, "Next page");
        else
            p.getPackets().sendIComponentText(INTERFACE_ID, 113, "");
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
                if (ItemDefinitions.getItemDefinitions(d.getItemId()).name.equalsIgnoreCase(itemName)) {
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
        Drop[] drops = NPCDrops.getDrops(npcId);
        if (drops == null) {
//			player.sendMessage("This NPC has no Drops");
            return;
        }
        player.getInterfaceManager().sendInterface(INTERFACE_ID);
        int componentIndex = 115;
        cleanDrops(player);
        player.getPackets().sendIComponentText(INTERFACE_ID, 14, "Drop viewer for: " + NPCDefinitions.getNPCDefinitions(npcId).name);
        sendDrops(player, npcId);

        int[] componentIds = { 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 112, 113 };
        // let's clear everything else
        for (int id : componentIds)
            player.getPackets().sendHideIComponent(INTERFACE_ID, id - 1, true);
    }

    public static void handelButtons(Player player, int buttonId) {
        switch (buttonId) {
            case 96: //find npc
                player.getPackets().sendInputLongTextScript("Enter the name of an item.");
                player.getTemporaryAttributtes().put("drop_find", Boolean.TRUE);
                break;
            case 98: //find drop
                player.getPackets().sendInputLongTextScript("Enter the name of an NPC");
                player.getTemporaryAttributtes().put("npc_find", Boolean.TRUE);
                break;
            case 113:
                if (player.foundNpcs.size() > 9) {
                    if (player.npcPage == 1) {
                        if (player.foundNpcs.size() > 18) {
                            sendPossibleNPCs(player, 2);
                            return;
                        } else {
                            sendPossibleNPCs(player, 0);
                            return;
                        }
                    }
                    if (player.npcPage == 0)
                        sendPossibleNPCs(player, 1);
                    else
                        sendPossibleNPCs(player, 0);
                    //return;
                }
                break;
        }
        if (buttonId >= 101 && buttonId <= 112) {
            try {
                sendDrops(player, player.foundNpcs.get(buttonId - 101 + (player.npcPage * 12)));
            } catch (Exception ex) {
                //don't want spam in the console if the player misclicked
            }
        }
    }
}
