package com.rs.game.player.interfaces;
 
import java.io.IOException;
import com.rs.game.player.Player;
import com.rs.game.player.content.grandExchange.GrandExchangeLoader;
import com.rs.game.player.content.grandExchange.GrandExchangeLoader.GrandExchangeOffer;
import com.rs.game.player.content.grandExchange.GrandExchangeLoader.GrandExchangeOffer.Type;
import com.rs.utils.Utils;
 
public class SellGeInterface {
 
        public static void sendInterface(Player player) throws IOException {
                byte pos = 0;
                player.getInterfaceManager().sendInterface(275);
                for (int i = 0; i < 310; i++) {
                        player.getPackets().sendIComponentText(275, i, "");
                }
                for (GrandExchangeOffer offer : GrandExchangeLoader.getOffers()) {
                        String owner = offer.getOwner();
                        int amount = offer.getPrimaryAmount();
                        int sold = offer.getSecondaryAmount();
                        int gePrice = offer.getPrice();
                        String ItemN = offer.getItem().getName();
                        int ItemId = offer.getItem().getId();
                        Type Type1 = offer.getType();
                        //need to check if sold out/aborted
                       
                        if (offer.isAborted() || offer.isFinished())
                                continue; // skip offers aborted or finished
                        
                        if (Type1.name().contentEquals("BUY")) 
                        	continue;
                        
                        
                       
                        if (pos >= 42)
                                break; // break loop after 42 results
                       
                        //gonna add a few helpful equations here
                       
                        int remaining = (amount - sold);
                        long totalPrice = (amount * gePrice); // price per whole stack, assuming the gePrice variable is price per item
                       
                        int percentSold = 0; // gives a percentage of amount sold
                        int amountleft = amount - sold;
                        if (sold > 0) // always check > 0 cause divide by 0 error
                                percentSold = (sold * 100) / amount;
                        player.getPackets().sendIComponentText(275,1,"Harmony Grand Exchange Selling List");
                        player.getPackets().sendIComponentText(275, 10 + (pos), "<col=000066>"+amountleft+ " x " + ItemN + " for " +Utils.format(gePrice)+ " each.");
                        
//                            player.getPackets().sendIComponentText(275, 13 + (pos * 6), "<col=000066><u>" + owner);
//                            player.getPackets().sendIComponentText(275, 14 + (pos * 6), "" + ItemN);
//                            player.getPackets().sendIComponentText(275, 15 + (pos * 6), "" + amount +" in offer");
//                            player.getPackets().sendIComponentText(275, 16 + (pos * 6), "" + percentSold +"% complete");
//                            player.getPackets().sendIComponentText(275, 17 + (pos * 6), "" + remaining + " Remaining");
//                            player.getPackets().sendIComponentText(275, 18 + (pos * 6), "" + gePrice + " Coins each");
                          //  player.getPackets().sendIComponentText(275, 19 + (pos * 7), "<col=ff0000>" + offer.getType().name() + "</col>");
                /*      System.out.println("Item Name: "+ItemN);
                        System.out.println("Amount Available: "+amount);
                        System.out.println("Sold: "+sold);
                        System.out.println("Remaining: "+remaining);
                        System.out.println("Percentage Sold: "+percentSold);
                        System.out.println("Price per Item: "+gePrice);
                        System.out.println("Stack Value: "+totalPrice);
                               
                        System.out.println("Pos: "+pos);
                       
                        System.out.println("Id: "+offer.getItem().getId()+"");
                        System.out.println("Amount: "+offer.getItem().getAmount()+"");
                        System.out.println("Sold: "+offer.getSecondaryAmount()+"");
                        System.out.println("Owner: "+offer.getOwner()+"");
                        System.out.println("Type: "+offer.getType().name()+"");*/
                        pos++;
 
                }
               
               
        }
       
}