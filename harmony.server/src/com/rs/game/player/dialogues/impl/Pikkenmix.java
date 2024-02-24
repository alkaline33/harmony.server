package com.rs.game.player.dialogues.impl;

import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.ShopsHandler;
import com.rs.utils.Utils;

public class Pikkenmix extends Dialogue {


    
    @Override
    public void start() {
        stage = 1;
        if (stage == 1) {
            sendOptionsDialogue("Choose a store",
                    "Store 1", 
                    "Store 2",
                    "Scrolls",
                    "Nothing");
            stage = 2;
        }
    }

    @Override
    public void run(int interfaceId, int componentId) {
        if (stage == 2) {
            if (componentId == OPTION_1) {
            	ShopsHandler.openShop(player, 16);
                end();
            }
            if (componentId == OPTION_2) {
            	ShopsHandler.openShop(player, 21);
                end();
            }
            if (componentId == OPTION_3) {
            	ShopsHandler.openShop(player, 17);
            } else {
            	end();
            }
        }
    }
    
    @Override
    public void finish() {
        // TODO Auto-generated method stub
        
    }

}