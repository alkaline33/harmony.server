package com.rs.game.player.dialogues.impl;


import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.ShopsHandler;

public class Npcshop2 extends Dialogue {



@Override
public void start() {
sendOptionsDialogue("Choose an Shop!", "Skillcape shop 1",
"Skillcape shop 2", "Master cape shop", "Nevermind");
stage = 2;
}


@Override
public void run(int interfaceId, int componentId) {

if (stage == 2) {
if (componentId == OPTION_1) {
	player.closeInterfaces();
ShopsHandler.openShop(player, 11 ); 
} else if (componentId == OPTION_2) {
	player.closeInterfaces();
ShopsHandler.openShop(player, 12);
end();
} else if (componentId == OPTION_3) {
	player.closeInterfaces();
	ShopsHandler.openShop(player, 30);
	end();
} else if (componentId == OPTION_4) {
	end();
	player.closeInterfaces();
}
}
}



@Override
public void finish() {

}
}