package com.rs.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.rs.cache.Cache;
import com.rs.game.npc.Drop;
import com.rs.utils.Logger;
import com.rs.utils.NPCDrops;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

public class DropJSONCreator {
    static String PACKED_PATH = "data/npcs/packedDrops.d";
    static Gson builder = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        try {
            Cache.init();

            RandomAccessFile in = new RandomAccessFile(PACKED_PATH, "r");
            FileChannel channel = in.getChannel();
            ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0,
                    channel.size());
            int dropSize = buffer.getShort() & 0xffff;
            for (int i = 0; i < dropSize; i++) {
                JsonObject object = new JsonObject();
                int npcId = buffer.getShort() & 0xffff;
                Drop[] drops = new Drop[buffer.getShort() & 0xffff];
                for (int d = 0; d < drops.length; d++) {
                    if (buffer.get() == 0)
                        object.addProperty(""+(buffer.getShort() & 0xffff), buffer.getDouble()+","+buffer.getInt()+","
                                +buffer.getInt()+","+false);
                    else
                        object.addProperty("0", "0,0,0,"+false);

                }
                try (FileWriter file = new FileWriter("data/npcs/drops/"+npcId+".json")) {
                    file.write(builder.toJson(object));
                    file.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            channel.close();
            in.close();

        } catch (Throwable e) {
            Logger.handle(e);
        }
    }

}
