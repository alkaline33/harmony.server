package com.rs.tools;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rs.game.npc.Drop;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TestJSONDrops {

    static HashMap<Integer, Drop[]> npcDrops = new HashMap<>();
    private final static String UNPACKED_PATH = "data/npcs/drops/";

        public static void main(String[] args) {
            HashMap<Integer, HashMap<String, String>> settings = new HashMap<>();
            for (File file : Objects.requireNonNull(new File(UNPACKED_PATH).listFiles())) {
                try (FileReader fileReader = new FileReader(file)) {

                    JsonObject reader = new JsonParser().parse(fileReader).getAsJsonObject();

                    Set<Map.Entry<String, JsonElement>> entrySet = reader.entrySet();
                    settings.put(Integer.parseInt(file.getName().replace(".json", "")), new HashMap<>());
                    for (Map.Entry<String, JsonElement> entry : entrySet) {
                        settings.get(Integer.parseInt(file.getName().replace(".json", ""))).put(entry.getKey(), reader.get(entry.getKey()).toString().replace("\"", ""));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            for (Map.Entry<Integer, HashMap<String, String>> mods : settings.entrySet()) {
                int npcId = mods.getKey();
                List<Drop> dropList = new ArrayList<>(mods.getValue().entrySet().size());
                for (Map.Entry<String, String> drops : mods.getValue().entrySet()) {
                    String[] drop = drops.getValue().split(",");
                    int itemId = Integer.parseInt(drops.getKey());
                    double rate = Double.parseDouble(drop[0]);
                    int minAmount = Integer.parseInt(drop[1]);
                    int maxAmount = Integer.parseInt(drop[2]);
                    boolean rare = Boolean.parseBoolean(drop[3]);
                    Drop d = new Drop(itemId, rate, minAmount, maxAmount, rare);
                    dropList.add(d);
                }
                npcDrops.put(npcId, dropList.toArray(new Drop[0]));
            }

            for(Map.Entry<Integer, Drop[]> drops : npcDrops.entrySet()){
                System.out.println("NPC ID: "+drops.getKey());
                for(Drop drop : drops.getValue()){
                    System.out.println(drop);
                }
            }
        }
}
