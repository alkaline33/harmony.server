package com.rs.game.player.content.interfaces.teleports;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.rs.Settings;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.cache.loaders.RenderAnimDefinitions;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.minigames.RefugeOfFear.Bosses;
import com.rs.game.npc.Drop;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Summoning;
import com.rs.game.player.content.DonatorZone;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.Shop;
import com.rs.game.player.content.bossinstance.BossInstance;
import com.rs.game.player.content.bossinstance.BossInstanceManager;
import com.rs.game.player.content.bossinstance.impl.AbyssalSire;
import com.rs.game.player.content.bossinstance.impl.ArmadylBossInstance;
import com.rs.game.player.content.bossinstance.impl.BandosBossInstance;
import com.rs.game.player.content.bossinstance.impl.CorpBossInstance;
import com.rs.game.player.content.bossinstance.impl.KbdBossInstance;
import com.rs.game.player.content.bossinstance.impl.KkBossInstance;
import com.rs.game.player.content.bossinstance.impl.Rot6Instance;
import com.rs.game.player.content.bossinstance.impl.SaradominBossInstance;
import com.rs.game.player.content.bossinstance.impl.VorkathInstance;
import com.rs.game.player.content.bossinstance.impl.ZamorakBossInstance;
import com.rs.game.player.content.bossinstance.impl.ZulrahInstance;
import com.rs.game.player.content.interfaces.teleports.TeleportsData.Store;
import com.rs.game.player.content.pet.Pets;
import com.rs.utils.Colors;
import com.rs.utils.NPCCombatDefinitionsL;
import com.rs.utils.NPCDrops;
import com.rs.utils.Utils;

/**
 * @author Sagacity - http://rune-server.org/members/sagacity
 * @date 05/17/2020 - 07:05
 */

public class TeleportsInterface {
    private static final int listComponents[] = {77,157,82,162,87,167,92,172,97,177,102,182,107,187,112,192,117,197,122,202,127,207,132,212,137,217,142,222,147,227,152,253,258,263,268,273,278,283,288,293,298,303,308,313,318,323,328,333,338,343,348};
    private static final int listComponents2[] = {240,320,245,325,250,330,255,335,260,340,265,345,270,350,275,355,280,360,285,365,290,370,295,375,300,380,305,385,310,390};
    private static final int categoryComponents[] = {18, 21, 24, 27, 30,232};
    private static final String UNMARKED = "<col=bf751d>", MARKED = "<col=ff981f>";

    public static void sendBossesInterface(Player player) {
        player.getBossInter = 3061;
        player.getInterfaceManager().sendInterface(player.getBossInter);
        sendTeleportsList(player, TeleportsData.Store.BANDOS.getTeleport().getCategory().getId());
        sendCategoriesList(player);
        player.getTemporaryAttributtes().put("TELEPORTS_CAT", 0);
        sendTeleportInfos(player, TeleportsData.Store.BANDOS);
        selectCategory(player, TeleportsData.Store.BANDOS);
        selectTeleportDestination(player, TeleportsData.Store.BANDOS);
        player.getTemporaryAttributtes().put("instanceSelected", false);
    }
    
    public static void sendMonstersInterface(Player player) {
        player.getBossInter = 3061;
        player.getInterfaceManager().sendInterface(player.getBossInter);
        sendTeleportsList(player, TeleportsData.Store.COWS.getTeleport().getCategory().getId());
        sendCategoriesList(player);
        player.getTemporaryAttributtes().put("TELEPORTS_CAT", 1);
        sendTeleportInfos(player, TeleportsData.Store.COWS);
        selectCategory(player, TeleportsData.Store.COWS);
        selectTeleportDestination(player, TeleportsData.Store.COWS);
        player.getTemporaryAttributtes().put("instanceSelected", false);
    }
    
    public static void sendRaidsInterface(Player player) {
        player.getBossInter = 3062;
        player.getInterfaceManager().sendInterface(player.getBossInter);
        sendTeleportsList(player, TeleportsData.Store.LAIR_OF_SHADOWS.getTeleport().getCategory().getId());
        sendCategoriesList(player);
        player.getTemporaryAttributtes().put("TELEPORTS_CAT", 2);
        sendTeleportInfos(player, TeleportsData.Store.LAIR_OF_SHADOWS);
        selectCategory(player, TeleportsData.Store.LAIR_OF_SHADOWS);
        selectTeleportDestination(player, TeleportsData.Store.LAIR_OF_SHADOWS);
        player.getTemporaryAttributtes().put("instanceSelected", false);
    }
    
    public static void sendMinigamesInterface(Player player) {
        player.getBossInter = 3062;
        player.getInterfaceManager().sendInterface(player.getBossInter);
        sendTeleportsList(player, TeleportsData.Store.DUEL_ARENA.getTeleport().getCategory().getId());
        sendCategoriesList(player);
        player.getTemporaryAttributtes().put("TELEPORTS_CAT", 3);
        sendTeleportInfos(player, TeleportsData.Store.DUEL_ARENA);
        selectCategory(player, TeleportsData.Store.DUEL_ARENA);
        selectTeleportDestination(player, TeleportsData.Store.DUEL_ARENA);
        player.getTemporaryAttributtes().put("instanceSelected", false);
    }
    
    public static void sendSkillingInterface(Player player) {
        player.getBossInter = 3060;
        player.getTemporaryAttributtes().put("TELEPORTS_CAT", 4);
        player.getInterfaceManager().sendInterface(player.getBossInter);
        sendTeleportsList(player, TeleportsData.Store.SKILLING.getTeleport().getCategory().getId());
        sendCategoriesList(player);        
        sendTeleportInfos(player, TeleportsData.Store.SKILLING);
        selectCategory(player, TeleportsData.Store.SKILLING);
        selectTeleportDestination(player, TeleportsData.Store.SKILLING);
        player.getTemporaryAttributtes().put("instanceSelected", false);
            
    }
    
    public static void sendAreaInterface(Player player) {
        player.getBossInter = 3060;
        player.getInterfaceManager().sendInterface(player.getBossInter);
        sendTeleportsList(player, TeleportsData.Store.DONATOR.getTeleport().getCategory().getId());
        sendCategoriesList(player);
        player.getTemporaryAttributtes().put("TELEPORTS_CAT", 5);
        sendTeleportInfos(player, TeleportsData.Store.DONATOR);
        selectCategory(player, TeleportsData.Store.DONATOR);
        selectTeleportDestination(player, TeleportsData.Store.DONATOR);
        player.getTemporaryAttributtes().put("instanceSelected", false);
    }
    
    public static void sendFarmingInterface(Player player) {
        player.getBossInter = 3060;
        player.getTemporaryAttributtes().put("TELEPORTS_CAT", 4);
        player.getInterfaceManager().sendInterface(player.getBossInter);
        sendTeleportsList(player, TeleportsData.Store.FARMING.getTeleport().getCategory().getId());
        sendCategoriesList(player);        
        sendTeleportInfos(player, TeleportsData.Store.FARMING);
        selectCategory(player, TeleportsData.Store.FARMING);
        selectTeleportDestination(player, TeleportsData.Store.FARMING);
        player.getTemporaryAttributtes().put("instanceSelected", false);
            
    }
    
    public static void sendWoodcuttingInterface(Player player) {
        player.getBossInter = 3060;
        player.getTemporaryAttributtes().put("TELEPORTS_CAT", 4);
        player.getInterfaceManager().sendInterface(player.getBossInter);
        sendTeleportsList(player, TeleportsData.Store.WC.getTeleport().getCategory().getId());
        sendCategoriesList(player);        
        sendTeleportInfos(player, TeleportsData.Store.WC);
        selectCategory(player, TeleportsData.Store.WC);
        selectTeleportDestination(player, TeleportsData.Store.WC);
        player.getTemporaryAttributtes().put("instanceSelected", false);
            
    }
    
    public static void sendSummoningInterface(Player player) {
        player.getBossInter = 3060;
        player.getTemporaryAttributtes().put("TELEPORTS_CAT", 4);
        player.getInterfaceManager().sendInterface(player.getBossInter);
        sendTeleportsList(player, TeleportsData.Store.SUMMONING.getTeleport().getCategory().getId());
        sendCategoriesList(player);        
        sendTeleportInfos(player, TeleportsData.Store.SUMMONING);
        selectCategory(player, TeleportsData.Store.SUMMONING);
        selectTeleportDestination(player, TeleportsData.Store.SUMMONING);
        player.getTemporaryAttributtes().put("instanceSelected", false);
            
    }
    
    public static void sendCraftingInterface(Player player) {
        player.getBossInter = 3060;
        player.getTemporaryAttributtes().put("TELEPORTS_CAT", 4);
        player.getInterfaceManager().sendInterface(player.getBossInter);
        sendTeleportsList(player, TeleportsData.Store.CRAFTING.getTeleport().getCategory().getId());
        sendCategoriesList(player);        
        sendTeleportInfos(player, TeleportsData.Store.CRAFTING);
        selectCategory(player, TeleportsData.Store.CRAFTING);
        selectTeleportDestination(player, TeleportsData.Store.CRAFTING);
        player.getTemporaryAttributtes().put("instanceSelected", false);
            
    }
    
    public static void sendConstructionInterface(Player player) {
        player.getBossInter = 3060;
        player.getTemporaryAttributtes().put("TELEPORTS_CAT", 4);
        player.getInterfaceManager().sendInterface(player.getBossInter);
        sendTeleportsList(player, TeleportsData.Store.CONSTRUCTION.getTeleport().getCategory().getId());
        sendCategoriesList(player);        
        sendTeleportInfos(player, TeleportsData.Store.CONSTRUCTION);
        selectCategory(player, TeleportsData.Store.CONSTRUCTION);
        selectTeleportDestination(player, TeleportsData.Store.CONSTRUCTION);
        player.getTemporaryAttributtes().put("instanceSelected", false);
            
    }
    
    public static void sendSmithingInterface(Player player) {
        player.getBossInter = 3060;
        player.getTemporaryAttributtes().put("TELEPORTS_CAT", 4);
        player.getInterfaceManager().sendInterface(player.getBossInter);
        sendTeleportsList(player, TeleportsData.Store.SMITHING.getTeleport().getCategory().getId());
        sendCategoriesList(player);        
        sendTeleportInfos(player, TeleportsData.Store.SMITHING);
        selectCategory(player, TeleportsData.Store.SMITHING);
        selectTeleportDestination(player, TeleportsData.Store.SMITHING);
        player.getTemporaryAttributtes().put("instanceSelected", false);
            
    }
    
    public static void sendRunecraftingInterface(Player player) {
        player.getBossInter = 3060;
        player.getTemporaryAttributtes().put("TELEPORTS_CAT", 4);
        player.getInterfaceManager().sendInterface(player.getBossInter);
        sendTeleportsList(player, TeleportsData.Store.RUNECRAFTING.getTeleport().getCategory().getId());
        sendCategoriesList(player);        
        sendTeleportInfos(player, TeleportsData.Store.RUNECRAFTING);
        selectCategory(player, TeleportsData.Store.RUNECRAFTING);
        selectTeleportDestination(player, TeleportsData.Store.RUNECRAFTING);
        player.getTemporaryAttributtes().put("instanceSelected", false);
            
    }
    
    public static void sendFishingInterface(Player player) {
        player.getBossInter = 3060;
        player.getTemporaryAttributtes().put("TELEPORTS_CAT", 4);
        player.getInterfaceManager().sendInterface(player.getBossInter);
        sendTeleportsList(player, TeleportsData.Store.FISHING.getTeleport().getCategory().getId());
        sendCategoriesList(player);        
        sendTeleportInfos(player, TeleportsData.Store.FISHING);
        selectCategory(player, TeleportsData.Store.FISHING);
        selectTeleportDestination(player, TeleportsData.Store.FISHING);
        player.getTemporaryAttributtes().put("instanceSelected", false);
            
    }
    
    public static void sendAgilityInterface(Player player) {
        player.getBossInter = 3060;
        player.getTemporaryAttributtes().put("TELEPORTS_CAT", 4);
        player.getInterfaceManager().sendInterface(player.getBossInter);
        sendTeleportsList(player, TeleportsData.Store.AGILITY.getTeleport().getCategory().getId());
        sendCategoriesList(player);        
        sendTeleportInfos(player, TeleportsData.Store.AGILITY);
        selectCategory(player, TeleportsData.Store.AGILITY);
        selectTeleportDestination(player, TeleportsData.Store.AGILITY);
        player.getTemporaryAttributtes().put("instanceSelected", false);
            
    }
    
    public static void sendHunterInterface(Player player) {
        player.getBossInter = 3060;
        player.getTemporaryAttributtes().put("TELEPORTS_CAT", 4);
        player.getInterfaceManager().sendInterface(player.getBossInter);
        sendTeleportsList(player, TeleportsData.Store.HUNTER.getTeleport().getCategory().getId());
        sendCategoriesList(player);        
        sendTeleportInfos(player, TeleportsData.Store.HUNTER);
        selectCategory(player, TeleportsData.Store.HUNTER);
        selectTeleportDestination(player, TeleportsData.Store.HUNTER);
        player.getTemporaryAttributtes().put("instanceSelected", false);
            
    }
    
    public static void sendThievingInterface(Player player) {
        player.getBossInter = 3060;
        player.getTemporaryAttributtes().put("TELEPORTS_CAT", 4);
        player.getInterfaceManager().sendInterface(player.getBossInter);
        sendTeleportsList(player, TeleportsData.Store.THIEVING.getTeleport().getCategory().getId());
        sendCategoriesList(player);        
        sendTeleportInfos(player, TeleportsData.Store.THIEVING);
        selectCategory(player, TeleportsData.Store.THIEVING);
        selectTeleportDestination(player, TeleportsData.Store.THIEVING);
        player.getTemporaryAttributtes().put("instanceSelected", false);
            
    }
    
    public static void sendMiningInterface(Player player) {
        player.getBossInter = 3060;
        player.getTemporaryAttributtes().put("TELEPORTS_CAT", 4);
        player.getInterfaceManager().sendInterface(player.getBossInter);
        sendTeleportsList(player, TeleportsData.Store.MINING.getTeleport().getCategory().getId());
        sendCategoriesList(player);        
        sendTeleportInfos(player, TeleportsData.Store.MINING);
        selectCategory(player, TeleportsData.Store.MINING);
        selectTeleportDestination(player, TeleportsData.Store.MINING);
        player.getTemporaryAttributtes().put("instanceSelected", false);
            
    }
    
    public static void sendSlayerInterface(Player player) {
        player.getBossInter = 3060;
        player.getTemporaryAttributtes().put("TELEPORTS_CAT", 4);
        player.getInterfaceManager().sendInterface(player.getBossInter);
        sendTeleportsList(player, TeleportsData.Store.SLAYER.getTeleport().getCategory().getId());
        sendCategoriesList(player);        
        sendTeleportInfos(player, TeleportsData.Store.SLAYER);
        selectCategory(player, TeleportsData.Store.SLAYER);
        selectTeleportDestination(player, TeleportsData.Store.SLAYER);
        player.getTemporaryAttributtes().put("instanceSelected", false);
            
    }
    
    public static void sendDungeoneeringInterface(Player player) {
        player.getBossInter = 3060;
        player.getTemporaryAttributtes().put("TELEPORTS_CAT", 4);
        player.getInterfaceManager().sendInterface(player.getBossInter);
        sendTeleportsList(player, TeleportsData.Store.DUNG.getTeleport().getCategory().getId());
        sendCategoriesList(player);        
        sendTeleportInfos(player, TeleportsData.Store.DUNG);
        selectCategory(player, TeleportsData.Store.DUNG);
        selectTeleportDestination(player, TeleportsData.Store.DUNG);
        player.getTemporaryAttributtes().put("instanceSelected", false);
            
    }
    

    public static void handleButtons(Player player, int componentId,int slotId) {       
        int category = (int) player.getTemporaryAttributtes().get("TELEPORTS_CAT");
        //player.sm("componentId: "+componentId+" category: "+category);
            for (int i = 0; i < listComponents.length; i++) {
                if (listComponents[i]+3 == componentId) {
                    for (TeleportsData.Store tele : TeleportsData.Store.values()) {               
                        if (tele.getTeleport().getId() != i)
                            continue;
                        if (tele.getTeleport().getCategory().getId() != category)
                            continue;
                        sendTeleportInfos(player, tele);
                        selectTeleportDestination(player, tele);                       
                    }
                }
            }
            @SuppressWarnings("unchecked")
            ArrayList<Store> arrayStore = (ArrayList<Store>) player.getTemporaryAttributtes().get("arrayStore");
            for (int i = 0; i < listComponents2.length; i++) {
                if (listComponents2[i]+3 == componentId) {
                        sendTeleportInfos2(player, arrayStore.get(i));
                }
            }
        if (componentId == 62) { // process teleport
            processTeleport(player);
        }
        if (componentId == 247) { // drops
            Item[] itemsArray = (Item[]) player.getTemporaryAttributtes().get("itemsArray");
            Drop[] droplist = (Drop[]) player.getTemporaryAttributtes().get("droplist");
            String name = (String) player.getTemporaryAttributtes().get("npcName");
            if(itemsArray != null) {
                handledropsinfo(player, itemsArray, droplist, slotId,name);
            }
        }
        if(componentId == 18) {//bosses
            sendBossesInterface(player);
        }
        if(componentId == 21) {//monsters
            sendMonstersInterface(player);
        }
        if(componentId == 24) {//raids
            sendRaidsInterface(player);
        }
        if(componentId == 27) {//minigames
            sendMinigamesInterface(player);
        }
        if(componentId == 30) {//skilling
            sendSkillingInterface(player);
        }
        if(componentId == 232) {//areas
            sendAreaInterface(player);
        }
        if(componentId == 251) {//instance
            if(player.getTemporaryAttributtes().get("instanceSelected") != null && (boolean) player.getTemporaryAttributtes().get("instanceSelected")) {
            player.getTemporaryAttributtes().put("instanceSelected", false);
            player.getPackets().sendHideIComponent(player.getBossInter, 68, true); //un-hide free
            player.getPackets().sendHideIComponent(player.getBossInter, 69, false);
            player.getPackets().sendIComponentText(player.getBossInter, 69, "Free");
            }else {
            player.getTemporaryAttributtes().put("instanceSelected", true);
                if (player.dailyperkamount < 7 && !player.completedecobreakdownquest && !player.getDisplayName().equalsIgnoreCase("womd") && !player.getInventory().contains(29007)) { // 1M
                 player.getPackets().sendHideIComponent(player.getBossInter, 69, true); //hide free
                    player.getPackets().sendHideIComponent(player.getBossInter, 68, false);
                    player.getPackets().sendIComponentText(player.getBossInter, 68, intToKOrMil(40000000) + " coins.");
                }else if (player.dailyperkamount < 7 && player.completedecobreakdownquest && !player.getDisplayName().equalsIgnoreCase("womd") && !player.getInventory().contains(29007)) { // 1M
                     player.getPackets().sendHideIComponent(player.getBossInter, 69, true); //hide free
                     player.getPackets().sendHideIComponent(player.getBossInter, 68, false);
                     player.getPackets().sendIComponentText(player.getBossInter, 68, intToKOrMil(20000000) + " coins.");
                }
            }
            //player.sm("instance status: "+String.valueOf(player.getTemporaryAttributtes().get("instanceSelected")));
        }
        
    }

    private static void sendTeleportInfos2(Player player, Store tele) {
        selectTeleportDestination2(player,tele);
        sendTeleportInfo2(player, tele);
        //player.sm(tele.getTeleport().getCategory().getId()+" category");
    }

    private static void sendTeleportInfo2(Player player, Store store) {
            player.getPackets().sendIComponentText(player.getBossInter, 60, "Info:"); //small text
            StringBuilder desc = new StringBuilder();
            if (store.getTeleport().getSuggestedLevel() != -1) {
                desc.append("Suggested Level: (<col=f00000>" + store.getTeleport().getSuggestedLevel() + "</col>)");
                desc.append("<br><br>");            
            }
            //description
            desc.append(store.getTeleport().getDescription()[0]);
            player.getPackets().sendIComponentText(player.getBossInter, 58, desc.toString());
            
            player.getPackets().sendIComponentText(player.getBossInter, 59, store.getTeleport().getName()); //title

            //resizeScrollbar(player, tele.getTeleport().getCategory().getId());

            if (store.getTeleport().isWilderness()) {
                player.getPackets().sendHideIComponent(player.getBossInter, 71, false); //show wild component
                player.getPackets().sendIComponentText(player.getBossInter, 72, "<col=f00000>This teleport is in the Wilderness at level "+store.getTeleport().getWildLevel()+".</col>"); //small text
            } else {
                player.getPackets().sendHideIComponent(player.getBossInter, 71, true); //hide wild component
            }

            if (store.getTeleport().getPrice() <= 0) {
                if (store.getTeleport().getName().toLowerCase().contains("vorago") && player.voragofee > 0) {
                     player.getPackets().sendHideIComponent(player.getBossInter, 69, true); //hide price
                     player.getPackets().sendHideIComponent(player.getBossInter, 68, false);
                     player.getPackets().sendIComponentText(player.getBossInter, 68, intToKOrMil(150000 * player.voragofee) + " coins."); //hide price
                }else if (store.getTeleport().getName().toLowerCase().equals("kalphite king") && player.kkfee > 0) {
                     player.getPackets().sendHideIComponent(player.getBossInter, 69, true); //hide price
                     player.getPackets().sendHideIComponent(player.getBossInter, 68, false);
                     player.getPackets().sendIComponentText(player.getBossInter, 68, intToKOrMil(150000 * player.kkfee) + " coins."); //hide price
                }else {
                     player.getPackets().sendHideIComponent(player.getBossInter, 68, true); //un-hide price
                     player.getPackets().sendHideIComponent(player.getBossInter, 69, false);
                     player.getPackets().sendIComponentText(player.getBossInter, 69, "Free");
                }
            } else {
                player.getPackets().sendHideIComponent(player.getBossInter, 69, true); //hide price
                player.getPackets().sendHideIComponent(player.getBossInter, 68, false);
                player.getPackets().sendIComponentText(player.getBossInter, 68, intToKOrMil(store.getTeleport().getPrice()) + " coins."); //hide price
            }
        }

    private static void sendTeleportsList(Player player, int category) {
        clearTeleportList(player);
        for (int i = 0; i < TeleportsData.Store.values().length; i++) {
            for (TeleportsData.Store store : TeleportsData.Store.values()) {
                if (store.getTeleport().getId() != i)
                    continue;
                if (store.getTeleport().getCategory().getId() != category)
                    continue;
                if (store.getTeleport() == null) {
                    continue;
                }
                player.getPackets().sendHideIComponent(player.getBossInter, listComponents[i], false); //unhid
                player.getPackets().sendIComponentText(player.getBossInter, listComponents[i] + 3, UNMARKED+store.getTeleport().getName()); //set option title
                player.getPackets().sendHideIComponent(player.getBossInter, listComponents[i] + 2, true);
            }
        }
    }
    
    private static void sendTeleportsList2(Player player, int storeId, int category) {
        ArrayList<Store> arrayStore = new ArrayList<Store>();
        clearTeleportList2(player);
        for (int i = 0; i < TeleportsData.Store.values().length; i++) {
            for (TeleportsData.Store store : TeleportsData.Store.values()) {
                if (store.getTeleport().getId() != i)
                    continue;
                if (store.getId() != storeId)
                    continue;
                if (store.getTeleport().getCategory().getId() != category)
                    continue;                
                if (store.getTeleport() == null) {
                    continue;
                }
                arrayStore.add(store);
                player.getPackets().sendHideIComponent(player.getBossInter, listComponents2[i], false); //unhid
                player.getPackets().sendIComponentText(player.getBossInter, listComponents2[i] + 3, UNMARKED+store.getTeleport().getName()); //set option title
                player.getPackets().sendHideIComponent(player.getBossInter, listComponents2[i] + 2, true);
            }
        }
        if(!arrayStore.isEmpty())
            player.getTemporaryAttributtes().put("arrayStore", arrayStore);
    }
    
    private static void sendCategoriesList(Player player) {
        clearCategoriesList(player);
            for (int i = 0; i < categoryComponents.length; i++) {
                for (TeleportsData.Store store: TeleportsData.Store.values()) {
                    if (store.getTeleport().getCategory() == null)
                        continue;
                    if (store.getTeleport().getCategory().getId() == i) {
                        player.getPackets().sendHideIComponent(player.getBossInter, categoryComponents[i], false); //unhid
                        player.getPackets().sendIComponentText(player.getBossInter, categoryComponents[i] + 2, UNMARKED + store.getTeleport().getCategory().getName()); //set option title
                    }
            }
        }
    }

    private static void sendTeleportInfos(Player player, TeleportsData.Store store) {
        for (TeleportsData.Store tele: TeleportsData.Store.values()) {
            if (tele.getTeleport().getCategory() != store.getTeleport().getCategory())
                continue;
            int category = (int) player.getTemporaryAttributtes().get("TELEPORTS_CAT");
            clearAttributes(player);
            if(category == 0 || category == 1) {
                int npcId = Integer.parseInt(tele.getTeleport().getDescription()[0]);
                sendDrops(player,npcId);
                //sendModel(player,npcId,Integer.parseInt(tele.getTeleport().getDescription()[4]));
                player.getPackets().sendIComponentText(player.getBossInter, 60, "Description:"); //small text
                  StringBuilder desc = new StringBuilder();
                  desc.append("Health: <col=f00000>" + NPCCombatDefinitionsL.getNPCCombatDefinitions(npcId).getHitpoints() + "</col><br>");
                  desc.append("Recom. Team Size: <col=f00000>" + tele.getTeleport().getDescription()[1] + "</col><br>");
                  desc.append("Attack Styles: <col=f00000>" + tele.getTeleport().getDescription()[2] + "</col><br>");
                  desc.append("Difficulty: <col=f00000>" + tele.getTeleport().getDescription()[3] + "</col><br>");
                  player.getPackets().sendIComponentText(player.getBossInter, 58, desc.toString());
            }else {
            if(category == 4){
                sendTeleportsList2(player, tele.getId(),6);
            }
            if(category == 5){
                sendTeleportsList2(player, store.getId(),7);
            }
            player.getPackets().sendIComponentText(player.getBossInter, 60, "Info:"); //small text
            StringBuilder desc = new StringBuilder();
            if (tele.getTeleport().getSuggestedLevel() != -1) {
                desc.append("Suggested Level: (<col=f00000>" + tele.getTeleport().getSuggestedLevel() + "</col>)");
                desc.append("<br><br>");            
            }
            //description
            desc.append(tele.getTeleport().getDescription()[0]);
            player.getPackets().sendIComponentText(player.getBossInter, 58, desc.toString());
            }
            player.getPackets().sendIComponentText(player.getBossInter, 59, tele.getTeleport().getName()); //title

            //resizeScrollbar(player, tele.getTeleport().getCategory().getId());

            if (tele.getTeleport().isWilderness()) {
                player.getPackets().sendHideIComponent(player.getBossInter, 71, false); //show wild component
                player.getPackets().sendIComponentText(player.getBossInter, 72, "<col=f00000>This teleport is in the Wilderness at level "+tele.getTeleport().getWildLevel()+"</col>"); //small text
            } else {
                player.getPackets().sendHideIComponent(player.getBossInter, 71, true); //show wild component
            }

            if (tele.getTeleport().getPrice() <= 0) {
                 if (store.getTeleport().getName().toLowerCase().contains("vorago") && player.voragofee > 0) {
                     player.getPackets().sendHideIComponent(player.getBossInter, 69, true); //hide free
                     player.getPackets().sendHideIComponent(player.getBossInter, 68, false);
                     player.getPackets().sendIComponentText(player.getBossInter, 68, intToKOrMil(150000 * player.voragofee) + " coins.");
                 }else if (store.getTeleport().getName().toLowerCase().equals("kalphite king") && player.kkfee > 0) {
                     player.getPackets().sendHideIComponent(player.getBossInter, 69, true); //hide free
                     player.getPackets().sendHideIComponent(player.getBossInter, 68, false);
                     player.getPackets().sendIComponentText(player.getBossInter, 68, intToKOrMil(150000 * player.kkfee) + " coins.");
                 }else if (player.dailyperkamount < 7 && player.completedecobreakdownquest && !player.getDisplayName().equalsIgnoreCase("womd") && !player.getInventory().contains(29007) && store.getTeleport().isHasInstance() && player.getTemporaryAttributtes().get("instanceSelected") != null && (boolean) player.getTemporaryAttributtes().get("instanceSelected")) { // 1M
                     player.getPackets().sendHideIComponent(player.getBossInter, 69, true); //hide free
                     player.getPackets().sendHideIComponent(player.getBossInter, 68, false);
                     player.getPackets().sendIComponentText(player.getBossInter, 68, intToKOrMil(20000000) + " coins.");
                 }else if (player.dailyperkamount < 7 && !player.completedecobreakdownquest && !player.getDisplayName().equalsIgnoreCase("womd") && !player.getInventory().contains(29007) && store.getTeleport().isHasInstance() && player.getTemporaryAttributtes().get("instanceSelected") != null && (boolean) player.getTemporaryAttributtes().get("instanceSelected")) { // 1M
                     player.getPackets().sendHideIComponent(player.getBossInter, 69, true); //hide free
                     player.getPackets().sendHideIComponent(player.getBossInter, 68, false);
                     player.getPackets().sendIComponentText(player.getBossInter, 68, intToKOrMil(40000000) + " coins.");
                 }else {
                     player.getPackets().sendHideIComponent(player.getBossInter, 68, true); //un-hide free
                     player.getPackets().sendHideIComponent(player.getBossInter, 69, false);
                     player.getPackets().sendIComponentText(player.getBossInter, 69, "Free");
                }
            } else {
                player.getPackets().sendHideIComponent(player.getBossInter, 69, true); //hide price
                player.getPackets().sendHideIComponent(player.getBossInter, 68, false);
                player.getPackets().sendIComponentText(player.getBossInter, 68, intToKOrMil(tele.getTeleport().getPrice()) + " coins."); //hide price
            }
            if(player.getBossInter == 3061) {
                if(tele.getTeleport().isHasInstance()) {
                    player.getPackets().sendHideIComponent(player.getBossInter, 251, false); //un-hide instance button
                    player.getPackets().sendHideIComponent(player.getBossInter, 252, false); 
                }else {
                    player.getPackets().sendHideIComponent(player.getBossInter, 251, true); //hide instance button
                    player.getPackets().sendHideIComponent(player.getBossInter, 252, true); 
                }
            }
        }
    }

    private static void clearAttributes(Player player) {
        player.getTemporaryAttributtes().remove("itemsArray");
        player.getTemporaryAttributtes().remove("droplist");
        player.getTemporaryAttributtes().remove("npcName");
    }


    private static void sendModel(Player player, int npcId, int zoom) {
        player.getPackets().sendNPCOnWidget(npcId, player.getBossInter, 242, true);
        player.getPackets().setZoomDistance(player.getBossInter, 242, zoom);
        RenderAnimDefinitions rAnim = RenderAnimDefinitions.getRenderAnimDefinitions(NPCDefinitions.getNPCDefinitions(npcId).renderEmote);
        int emoteId = 0;
        if(rAnim != null)
            emoteId = rAnim.standAnimation;
        player.getPackets().sendIComponentAnimation(emoteId, player.getBossInter, 242);
    }


    private static void sendDrops(Player player,int npcId) {
        Drop[] drops = NPCDrops.getDrops(npcId);    
        List<Item> items = new ArrayList<Item>();
        Drop[] droplist = null;
        if(drops != null) {
            droplist = new Drop[drops.length];
            int count = 0;
             for (Drop drop : drops) {
                 if(drops == null)
                     continue;               
                 droplist[count] = drop;
                 count++;
             }
             if(droplist != null) {
                 sorter(droplist);
                 for(Drop drop : droplist) {
                     items.add(new Item(drop.getItemId(),drop.getMaxAmount()));
                 }
             }
            Item[] itemsArray = new Item[items.size() + 1];
            for (int i = 0; i <items.size(); i++) {
                itemsArray[i] = items.get(i);
            }
            player.getTemporaryAttributtes().put("itemsArray", itemsArray);
            player.getTemporaryAttributtes().put("droplist", droplist);
            player.getTemporaryAttributtes().put("npcName", NPCDefinitions.getNPCDefinitions(npcId).getName());
            if (itemsArray != null) {
                player.getPackets().sendInterSetItemsOptionsScript(player.getBossInter, 247, 100, 4, 15, "Examine");
                player.getPackets().sendUnlockIComponentOptionSlots(player.getBossInter, 247, 0, 150, 0);
                player.getPackets().sendItems(100, itemsArray);
            }
        }
    }

    public static void handledropsinfo(Player player, Item[] itemsArray, Drop[] droplist, int slotId, String name) {
        if(slotId >= 65535)
            return;
        Item item = itemsArray[slotId];
        for(Drop d : droplist) {
            if(d.getItemId() == item.getId()) {
                String price = Utils.formatGP(Shop.getSellPrice(item,1));
//              player.sm(Colors.green+"["+name+"]</col> "+item.getName()+" droprate: <col=ff0000>"+d.getRate()+"%</col> cost: <col=ff0000>"+price);
                player.sm(Colors.green+"["+name+"]</col> "+item.getName()+" droprate: <col=ff0000>"+d.getFormattedRate()+"</col> cost: <col=ff0000>"+price);
            }
        }
        
    }

    private static void sorter(Drop[] droplist) {
        Arrays.sort(droplist, new Comparator<Drop>() {
            @Override
            public int compare(Drop arg0, Drop arg1) {
                if (arg0 == null)
                    return 1;
                if (arg1 == null)
                    return -1;
                if (arg0.getRate() > arg1.getRate())
                    return 1;
                else if (arg0.getRate() < arg1.getRate())
                    return -1;
                else
                    return 0;
            }
        });
    }
    private static void selectCategory(Player player, TeleportsData.Store store) {
        sendCategoriesList(player);
        player.getPackets().sendIComponentSprite(player.getBossInter, categoryComponents[store.getTeleport().getCategory().getId()]+1, 952);
        player.getPackets().sendIComponentText(player.getBossInter, categoryComponents[store.getTeleport().getCategory().getId()]+2, MARKED+store.getTeleport().getCategory().getName());
        System.out.println("switched cat to "+store.getTeleport().getCategory().getName());
        if (store.getTeleport().getCategory().getName() == "Bosses") {
            player.setLastTeleInter(0);
        } else if (store.getTeleport().getCategory().getName() == "Monsters") {
            player.setLastTeleInter(1);
        } else if (store.getTeleport().getCategory().getName() == "Raids") {
            player.setLastTeleInter(2);
        } else if (store.getTeleport().getCategory().getName() == "Minigames") {
            player.setLastTeleInter(3);
        } else if (store.getTeleport().getCategory().getName() == "Skilling") {
            player.setLastTeleInter(4);
        } else if (store.getTeleport().getCategory().getName() == "Areas") {
            player.setLastTeleInter(5);
        } 
        
    }
    private static void selectTeleportDestination(Player player, TeleportsData.Store store) {
        player.getTemporaryAttributtes().put("TELEPORTS_SELECTED", store.getTeleport().getDestination() == null ? Settings.RESPAWN_PLAYER_LOCATION : store.getTeleport().getDestination());
        sendTeleportsList(player, store.getTeleport().getCategory().getId());
        player.getPackets().sendIComponentText(player.getBossInter, listComponents[store.getTeleport().getId()]+3, MARKED+store.getTeleport().getName());
        player.getPackets().sendHideIComponent(player.getBossInter, listComponents[store.getTeleport().getId()]+2, false);
//        if (store.getTeleport().isHasInstance() == false); {
//          player.sm("no instance available");
////        return;
//        }
        System.out.println("selected teleport destination: "+store.getTeleport().getName());
    }
    
    private static void selectTeleportDestination2(Player player, TeleportsData.Store store) {
        player.getTemporaryAttributtes().put("TELEPORTS_SELECTED", store.getTeleport().getDestination());
        sendTeleportsList2(player, store.getId(), store.getTeleport().getCategory().getId());
        player.getPackets().sendIComponentText(player.getBossInter, listComponents2[store.getTeleport().getId()]+3, MARKED+store.getTeleport().getName());
        player.getPackets().sendHideIComponent(player.getBossInter, listComponents2[store.getTeleport().getId()]+2, false);
        System.out.println("selected teleport destination2: "+store.getTeleport().getName());
    }
    
    private static void clearTeleportList(Player player) {
        int componentsLength = player.getBossInter == 3061 ? listComponents.length : 30;
        for (int i = 0; i < componentsLength; i++) {
            player.getPackets().sendHideIComponent(player.getBossInter, listComponents[i], true);
        }       
    }
    
    private static void clearTeleportList2(Player player) {
        for (int i = 0; i < listComponents2.length; i++) {
            player.getPackets().sendHideIComponent(player.getBossInter, listComponents2[i], true);
        }
    }

    private static void clearCategoriesList(Player player) {
        for (int i = 0; i < categoryComponents.length; i++) {
            player.getPackets().sendIComponentSprite(player.getBossInter, categoryComponents[i]+1, 953);
            player.getPackets().sendHideIComponent(player.getBossInter, categoryComponents[i], true);
        }
    }

    private static void processTeleport(Player player) {
        WorldTile selected_teleport = (WorldTile) player.getTemporaryAttributtes().get("TELEPORTS_SELECTED");
        int category = (int) player.getTemporaryAttributtes().get("TELEPORTS_CAT");
        if(category == 4)
            category = 6;
        if(category == 5)
            category = 7;
        for (TeleportsData.Store store : TeleportsData.Store.values()) {
            if (store.getTeleport().getCategory().getId() != category)
                continue;
            if (store.getTeleport().getDestination() == selected_teleport) {
                if (store.getTeleport().getPrice() != 0) {
                    if (player.takeMoney(store.getTeleport().getPrice())) {
                        Settings.GpSyncAmount += (store.getTeleport().getPrice());
                    } else {
                        player.getPackets().sendGameMessage(
                                "You need atleast " + intToKOrMil(store.getTeleport().getPrice()) + " coins to teleport to this location.",
                                true);
                        return;
                    }
                }
                if(store.getTeleport().getDestination() == Settings.RESPAWN_PLAYER_LOCATION)
                    return;
                // --------- BOSSES ---------
                if (store.getTeleport().getName().toLowerCase().contains("bandos")) {
                    if (player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd") && player.isSuperDonator() != true) {
                        if (player.gwdkc < 20) {
                            player.sendMessage("You need 20 GWD kc to enter this boss.");
                            Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2927, 5294, 0));
                            player.getInterfaceManager().closeChatBoxInterface();
                            player.getInterfaceManager().closeOverlay(true);
                            return;
                        }
                    }
                    if (player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd") && player.isSuperDonator() != true) {
                        player.gwdkc -= 20;
                    }
                }else if (store.getTeleport().getName().toLowerCase().equalsIgnoreCase("zamorak")) {//this wasn't added yet idk if that's the check for kill count, not sure
                    if (player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd") && player.isSuperDonator() != true) {
                        if (player.gwdkc < 20) {
                            player.sendMessage("You need 20 GWD kc to enter this boss.");
                            Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2927, 5294, 0));
                            player.getInterfaceManager().closeChatBoxInterface();
                            player.getInterfaceManager().closeOverlay(true);
                            return;
                        }
                    }
                    if (player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd") && player.isSuperDonator() != true) {
                        player.gwdkc -= 20;
                    }
                }else if (store.getTeleport().getName().toLowerCase().contains("armadyl")) {
                    if (player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd") && player.isSuperDonator() != true) {
                        if (player.gwdkc < 20) {
                            player.sendMessage("You need 20 GWD kc to enter this boss.");
                            Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2927, 5294, 0));
                            player.getInterfaceManager().closeChatBoxInterface();
                            player.getInterfaceManager().closeOverlay(true);
                            return;
                        }
                    }
                    if (player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd") && player.isSuperDonator() != true) {
                        player.gwdkc -= 20;
                    }
                }else if (store.getTeleport().getName().toLowerCase().contains("saradomin")) {
                    if (player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd") && player.isSuperDonator() != true) {
                        if (player.gwdkc < 20) {
                            player.sendMessage("You need 20 GWD kc to enter this boss.");
                            Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2927, 5294, 0));
                            player.getInterfaceManager().closeChatBoxInterface();
                            player.getInterfaceManager().closeOverlay(true);
                            return;
                        }
                    }
                    if (player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd") && player.isSuperDonator() != true) {
                        player.gwdkc -= 20;
                    }
                }else if (store.getTeleport().getName().toLowerCase().contains("vorago")) {
                    int fee = 150000 * player.voragofee;
                    if (player.getMoneyPouch().getCoinAmount() >= fee) {
                        player.coinamount -= fee;
                        Settings.GpSyncAmount += fee;
                        player.refreshMoneyPouch();
                        if (player.voragofee < 4) {
                            player.voragofee ++;
                        }
                    }else {
                        player.sendMessage("You need " + fee + " coins in your money pouch to fight Vorago!");
                        return;
                    }
                }else if (store.getTeleport().getName().toLowerCase().equals("kalphite king")) {
                    int fee = 150000 * player.kkfee;
                    if (player.getMoneyPouch().getCoinAmount() >= fee) {
                        player.coinamount -= fee;
                        Settings.GpSyncAmount += fee;
                        player.refreshMoneyPouch();
                        if (player.kkfee < 4) {
                            player.kkfee ++;
                        }
                    }else {
                        player.sendMessage("You need " + fee + " coins in your money pouch to fight Kalphite King!");
                        return;
                    }
                }else if (store.getTeleport().getName().toLowerCase().contains("dryax")) {
                      if (player.getInventory().containsOneItem(29866)) {
                        player.getInventory().deleteItem(29866, 1);
                    } else {
                        player.sendMessage("<col=ff0000>You need a dryaxion key to enter this boss.");
                        return;
                    }
                }else if (store.getTeleport().getName().toLowerCase().equals("party demon")){
                     if (Settings.canteletopdemon != true) {
                        player.sendMessage("You have no reason to go here yet.");
                        return;
                     }
                     if (Skills.getTotalLevel(player) < 1500) {
                        player.getDialogueManager().startDialogue("SimpleMessage", "The Demon will not reward you for yout efforts.");
                        return;
                    }
                    player.canlootpdemonchest = true;
                }else if(store.getTeleport().getName().toLowerCase().contains("necrolord")) {
                    if (!player.isVeteran()) {
                        player.sendMessage("You must be a veteran to access this boss!");
                        return;
                    }
                }else if(store.getTeleport().getName().toLowerCase().contains("sunfreet")) {
                    if (player.getSkills().getLevel(Skills.SLAYER) < 95) {
                        player.getPackets().sendGameMessage("You need a slayer level of 95 to kill Sunfreet.");
                        return;
                    }
                }else if(store.getTeleport().getName().toLowerCase().contains("zmi altar")) {
                    if (!player.completedruesaltar) {
                        player.getInterfaceManager().closeInterfaceCustom();
                        player.sendMessage(Colors.red+"You must've completed Rue's Altar quest before accessing this altar.");
                        return;
                    }
                }else if(store.getTeleport().getName().toLowerCase().contains("ganodermic beasts")) {
                    if (player.getSkills().getLevel(Skills.SLAYER) < 90) {
                        player.getPackets().sendGameMessage("You need a slayer level of 90 to kill Ganodermic Beasts.");
                        return;
                    }
                }else if(store.getTeleport().getName().toLowerCase().contains("jadinkos")) {
                    if (player.getSkills().getLevel(Skills.SLAYER) < 80) {
                        player.getPackets().sendGameMessage("You need a slayer level of 80 to kill Jadinkos.");
                        return;
                    }
                }else if(store.getTeleport().getName().toLowerCase().contains("dragon dungeon")) {
                    if (player.getSkills().getLevel(Skills.SLAYER) < 80 && player.getSkills().getLevel(Skills.DUNGEONEERING) < 105) {
                        player.getPackets().sendGameMessage("You need a slayer level of 80 & a dungeoneering level of 105 to enter the Dragon Dungeon.");
                        return;
                    }
                }else if(store.getTeleport().getName().toLowerCase().contains("strykewyrms")) {
                    if (player.rights >= 0) {
                        player.getInterfaceManager().closeInterfaceCustom();
                        player.getDialogueManager().startDialogue("StrykeWyrm");
                        return;
                    }
                }else if(store.getTeleport().getName().toLowerCase().contains("nex")) {
                    if (player.rights >= 0) {
                        player.setNextWorldTile(new WorldTile(2905, 5203, 0));
                        player.getControlerManager().startControler("GodWars");
                        player.getInterfaceManager().closeInterfaceCustom();
                        return;
                    }
                }else if(store.getTeleport().getName().toLowerCase().contains("puro-puro")) {
                    if (player.rights >= 0) {
                        player.getInterfaceManager().closeInterfaceCustom();
                        player.getControlerManager().startControler("PuroPuro");
                    }
                }else if(store.getTeleport().getName().toLowerCase().contains("wyvern cave")) {
                    if (player.getSkills().getLevel(Skills.SLAYER) < 66) {
                        player.sendMessage("You must have 66 slayer to access the Wyvern Cave");
                        return;
                    }
                }else if(store.getTeleport().getName().toLowerCase().contains("glacors")) {
                    if (player.getSkills().getLevel(Skills.SLAYER) < 50) {
                        player.sendMessage("You need a slayer level of 50 to kill Glacors.");
                        return;
                    }
                }else if(store.getTeleport().getName().toLowerCase().contains("prestige zone")) {
                    if (player.prestigeTokens < 1) {
                        player.getPackets().sendGameMessage("You must have prestiged to use this!");
                        return;
                    }
                }else if(store.getTeleport().getName().toLowerCase().contains("temple of light")) {
                    if (player.templeoflightcharges < 1) {
                        player.sendMessage(Colors.cyan + "You don't have any Temple of Light teleport charges!");
                        return;
                    }
                }else if(store.getTeleport().getName().toLowerCase().contains("assassin")) {
                    if (player.rights >= 0) {
                        player.getInterfaceManager().closeInterfaceCustom();
                        player.getDialogueManager().startDialogue("AssassinWarning");
                        return;
                    }
                }else if(store.getTeleport().getName().toLowerCase().contains("regular")) {
                    if (!player.isDonator()) {
                        player.sendMessage("You must have donated at least $20 to teleport here.");
                        return;
                    }
                }else if(store.getTeleport().getName().toLowerCase().contains("extreme")) {
                    if (!player.isExtremeDonator()) {
                        player.sendMessage("You must have donated at least $50 to teleport here.");
                        return;
                    }
                }else if(store.getTeleport().getName().toLowerCase().contains("legendary")) {
                    if (!player.isLegendaryDonator()) {
                        player.sendMessage("You must have donated at least $250 to teleport here.");
                        return;
                    }
                }else if(store.getTeleport().getName().toLowerCase().contains("vip")) {
                    if (!player.isVIP()) {
                        player.sendMessage("You must have donated at least $500 to teleport here.");
                        return;
                    }
                }else if(store.getTeleport().getName().toLowerCase().contains("sponsor")) {
                    if (!player.isSponsor()) {
                        player.sendMessage("You must have donated at least $1000 to teleport here.");
                        return;
                    }
                }else if(store.getTeleport().getName().toLowerCase().contains("chambers of xeric")) {
                    if (player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player) || Pets.hasPet(player)) {
                        player.sendMessage("You cannot bring a familiar to this raid.");
                    }
                    if (!World.Level3Zone(player)) {
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3307, 5195, 0));
                    player.osrsraiddamagepoints = 0;
                    return;
                    }
                    
                }else if(store.getTeleport().getName().toLowerCase().contains("theatre of blood")) {
                    if (player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player) || Pets.hasPet(player)) {
                        player.sendMessage("You cannot bring a familiar to this raid.");
                    }
                    if (!World.Level3Zone(player)) {
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2578, 9650, 0));
                    player.theatreofblooddamagepoints = 0;
                    return;
                    }
                    
                }else if(store.getTeleport().getName().toLowerCase().contains("lair of shadows")) {
                    if (player.rights >= 0) {
                        player.getInterfaceManager().closeInterfaceCustom();
                        player.sm("This content is coming very soon! You have not been charged for this teleport.");
                    }
//                  if(player.getControlerManager().getControler() != null) {
//                      player.sm("You can't do that!");
//                  }
//                  if (!player.getInventory().containsItem(995, 1000000) && !World.Level3Zone(player)) {
//                      player.sendMessage("Please have 1,000,000 coins in your inventory in order to pay a fee!");
//                  }
//                  player.getPackets().sendRunScript(5561, 0, 1000000);
//                  player.refreshMoneyPouch();
//                  player.getInterfaceManager().closeInterfaceCustom();
//                  new RaidsManager(player,new Player[] {player});
//                  return;
                    
                    
                    
                }else if(store.getTeleport().getName().equalsIgnoreCase("Medium(51-95)")) {
                    if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) < 51) {
                        player.sendMessage("You must have at least 51 dungeoneering to access this dungeon.");
                        return;
                    }
                    if (!player.getInventory().contains(13305)) {
                        if (!player.getInventory().hasFreeSlots()) {
                            player.sendMessage("Your inventory was full and we wasn't able to give you the crowbar.");
                        }
                        player.getInventory().addItem(13305, 1);
                    }
                }else if(store.getTeleport().getName().equalsIgnoreCase("High(96-120)")) {
                    if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) < 96) {
                        player.sendMessage("You must have at least 96 dungeoneering to access this dungeon.");
                        return;
                    }
                }
               
                
                if (store.getTeleport().isWilderness()) {
                    player.getControlerManager().startControler("Wilderness");
                }
                
                System.out.println("teleport to " + store.getTeleport().getName());
                if(player.getTemporaryAttributtes().get("instanceSelected") != null && (boolean) player.getTemporaryAttributtes().get("instanceSelected") && store.getTeleport().isHasInstance()) {
                    
                    if (player.dailyperkamount < 7 && player.completedecobreakdownquest && player.getMoneyPouch().getCoinAmount() < 20000000 && !player.getDisplayName().equalsIgnoreCase("womd") && !player.getInventory().contains(29007)) { // 1M
                        player.getPackets().sendGameMessage("You require 20M in your money pouch for this.");
                        return;
                    }
                    else if (player.dailyperkamount < 7 && !player.completedecobreakdownquest && player.getMoneyPouch().getCoinAmount() < 40000000 && !player.getDisplayName().equalsIgnoreCase("womd") && !player.getInventory().contains(29007)) { // 1M
                        player.getPackets().sendGameMessage("You require 40M in your money pouch for this.");
                        return;
                    }
                    BossInstance instance = null;
                    if(store.getTeleport().getName().toLowerCase().contains("bandos")) {
                        instance = new BandosBossInstance(player);
                    }else if(store.getTeleport().getName().toLowerCase().contains("corporeal")) {
                        instance = new CorpBossInstance(player);
                    }else if(store.getTeleport().getName().toLowerCase().contains("saradomin")) {
                        instance = new SaradominBossInstance(player);
                    }else if(store.getTeleport().getName().toLowerCase().contains("armadyl")) {
                        instance = new ArmadylBossInstance(player);
                    }else if(store.getTeleport().getName().toLowerCase().contains("zamorak")) {
                        instance = new ZamorakBossInstance(player);
                    }else if(store.getTeleport().getName().toLowerCase().contains("kalphite king")) {
                        instance = new KkBossInstance(player);
                    }else if(store.getTeleport().getName().toLowerCase().contains("king black dragon")) {
                        instance = new KbdBossInstance(player);
                    }
                    if (player.getInventory().contains(29007)) {
                        player.getInventory().deleteItem(29007, 1);
                    } else if (player.dailyperkamount < 7 && player.completedecobreakdownquest && !player.getDisplayName().equalsIgnoreCase("womd")) {
                        player.coinamount -= 20000000;
                        Settings.GpSyncAmount += 20000000;
                        player.getPackets().sendRunScript(5561, 0, 20000000);
                    } else if (player.dailyperkamount < 7 && !player.completedecobreakdownquest && !player.getDisplayName().equalsIgnoreCase("womd")) {
                        player.coinamount -= 40000000;
                        Settings.GpSyncAmount += 40000000;
                        player.getPackets().sendRunScript(5561, 0, 40000000);
                    }
                    player.refreshMoneyPouch();
                    BossInstanceManager.SINGLETON.add(instance);    
                    player.getInterfaceManager().closeInterfaceCustom();
                }else if(store.getTeleport().getName().toLowerCase().equals("rise of the six")) {
                    if (!player.getInventory().contains(29481)) {
                        player.sendMessage("You need a barrows Totem to access this. Normal Barrows drop them.");
                        return;
                    }
                    if (player.getSkills().getLevel(Skills.SLAYER) < 95) {
                        player.sendMessage("You need a slayer level of 95 to access this boss.");
                        return;
                    } else {
                        player.getInventory().deleteItem(29481, 1);
                        BossInstance instance = null;
                        instance = new Rot6Instance(player);
                        BossInstanceManager.SINGLETON.add(instance);
                        player.getInterfaceManager().closeInterfaceCustom();
                    }
                }else if(store.getTeleport().getName().toLowerCase().contains("vorkath")){
                    BossInstance instance = new VorkathInstance(player);
                    BossInstanceManager.SINGLETON.add(instance);
                    player.getInterfaceManager().closeInterfaceCustom();
                }else if(store.getTeleport().getName().toLowerCase().contains("zulrah")){
                    BossInstance instance = new ZulrahInstance(player);
                    BossInstanceManager.SINGLETON.add(instance);
                    player.getInterfaceManager().closeInterfaceCustom();
                }else if(store.getTeleport().getName().toLowerCase().equals("abyssal sire")){
                    BossInstance instance = new AbyssalSire(player);
                    BossInstanceManager.SINGLETON.add(instance);
                    player.getInterfaceManager().closeInterfaceCustom();
                }else
                Magic.sendNormalTeleportSpell(player, 0, 0, selected_teleport);//every teleport goes down here, which will teleport based on the teleports data 
                
                //DUNG
                if(store.getTeleport().getName().equalsIgnoreCase("low(1-50)")){
                    if (player.lowdungtut == true) {
                        player.sendMessage("<col=ff0000> Guide: Kill a warrior for some instructions.");
                        player.sendMessage("<col=ff0000> Guide: Search chest for a key part.");
                        player.sendMessage("<col=ff0000> Guide: Use the key part on the coffin & combine the parts.");
                        player.sendMessage("<col=ff0000> Guide: Proceed through the doors to the boss room.");
                        player.getInterfaceManager().closeChatBoxInterface();
                        player.getInterfaceManager().closeOverlay(true);
                    } else {
                        player.getDialogueManager().startDialogue("LowDungTut");
                    }
                }else if(store.getTeleport().getName().equalsIgnoreCase("Medium(51-95)")) {
                    if (player.meddungtut == true) {
                        player.sendMessage("<col=ff0000> Guide: Use the crowbar on the *Shelves* until you get a metal key.");
                        player.sendMessage("<col=ff0000> Guide: Search the wall south. Proceed all the way south.");
                        player.sendMessage("<col=ff0000> Guide: Kill the warrior for gloom and smash the boards north.");
                        player.sendMessage("<col=ff0000> Guide: Proceed south again and head through the doors to the boss.");
                        player.getInterfaceManager().closeChatBoxInterface();
                        player.getInterfaceManager().closeOverlay(true);
                        } else {
                            player.getDialogueManager().startDialogue("MedDungTut");
                        }
                }else if(store.getTeleport().getName().equalsIgnoreCase("High(96-120)")) {
                    if (player.highdungtut == true) {
                        player.sendMessage("<col=ff0000> Guide: Search the rack and then pickpocket Berry.");
                        player.sendMessage("<col=ff0000> Guide: Use the damp cloth on the key. Go in the jail with *Wise old man*.");
                            player.sendMessage("<col=ff0000> Guide: Use the key on the weird old man and then click the next jail door.");
                        player.sendMessage("<col=ff0000> Guide: Use the hammer on Godric and proceed south to the boss.");
                        player.getInterfaceManager().closeChatBoxInterface();
                        player.getInterfaceManager().closeOverlay(true);
                    } else {
                            player.getDialogueManager().startDialogue("HighDungTut");
                    }
                }
            }
        }
    }

    private static char[] c = new char[]{'K', 'M', 'B'};

    private static String formatValue(double a, int b) {
        double d = ((long) a / 100) / 10.0;
        boolean isRound = (d * 10) %10 == 0;
        return (d < 1000?((d > 99.9 || isRound || (!isRound && d > 9.99)?
                (int) d * 10 / 10 : d + "") + "" + c[b]) : formatValue(d, b+1));
    }

    public static String intToKOrMil(int j) {
        if(j < 10000)
            return String.valueOf(j);
        if(j < 0x989680)
            return formatValue(j, 0);
        else if(j < 0x3B9ACA00)
            return formatValue(j, 0);
        else
            return formatValue(j, 0);
    }

    private static void resizeScrollbar(Player player, int category) {
        //player.getPackets().sendIComponentScrollSizeHeight(INTER, 76, 75, 0, false);
        //player.getPackets().sendIComponentScrollSizeHeight(INTER, 76, 75, getScrollbarSize(category, 30), false);
    }

    private static int getScrollbarSize(int index, int eachComponentHeight) {
        int total = 0;
        switch (index) {
            case 0:
                total = getTotalItemsByCategory(0); /*;*/
                break;
            case 1:
                total = getTotalItemsByCategory(1);/*4;*/
                break;
            case 2:
                total = getTotalItemsByCategory(2);/*5;*/
                break;
            case 3:
                total = getTotalItemsByCategory(3);/*3;*/
                break;
            case 4:
                total = getTotalItemsByCategory(4);/*2;*/
                break;
        }
        return (total * eachComponentHeight);
    }

    private static int getTotalItemsByCategory(int cat) {
        int cont = 0;

        for (TeleportsData.Store st : TeleportsData.Store.values()) {
            if (st.getTeleport().getCategory().getId() != cat)
                continue;
            cont++;
        }
        return cont;
    }
}
