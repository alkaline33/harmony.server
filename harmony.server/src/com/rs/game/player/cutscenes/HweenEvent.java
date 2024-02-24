package com.rs.game.player.cutscenes;

import java.util.ArrayList;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.player.Player;
import com.rs.game.player.cutscenes.actions.ConstructMapAction;
import com.rs.game.player.cutscenes.actions.CreateNPCAction;
import com.rs.game.player.cutscenes.actions.CutsceneAction;
import com.rs.game.player.cutscenes.actions.DestroyCachedObjectAction;
import com.rs.game.player.cutscenes.actions.LookCameraAction;
import com.rs.game.player.cutscenes.actions.MoveNPCAction;
import com.rs.game.player.cutscenes.actions.MovePlayerAction;
import com.rs.game.player.cutscenes.actions.NPCAnimationAction;
import com.rs.game.player.cutscenes.actions.NPCFaceTileAction;
import com.rs.game.player.cutscenes.actions.NPCForceTalkAction;
import com.rs.game.player.cutscenes.actions.NPCGraphicAction;
import com.rs.game.player.cutscenes.actions.PlayerAnimationAction;
import com.rs.game.player.cutscenes.actions.PlayerFaceTileAction;
import com.rs.game.player.cutscenes.actions.PlayerForceTalkAction;
import com.rs.game.player.cutscenes.actions.PlayerGraphicAction;
import com.rs.game.player.cutscenes.actions.PlayerMusicEffectAction;
import com.rs.game.player.cutscenes.actions.PosCameraAction;

public class HweenEvent extends Cutscene {

	@Override
	public boolean hiddenMinimap() {
		return false;
	}

	private static int REAPER = 1, HETTY = 2, IDK = 3;

	@Override
	public CutsceneAction[] getActions(Player player) {
		ArrayList<CutsceneAction> actionsList = new ArrayList<CutsceneAction>();
		// first part
		actionsList.add(new ConstructMapAction(449, 416, 3, 3));
		actionsList
				.add(new MovePlayerAction(10, 0, 0, Player.TELE_MOVE_TYPE, 0)); // out
		actionsList.add(new LookCameraAction(10, 8, 1000, -1));
		actionsList.add(new PosCameraAction(10, 0, 2000, 3));
		actionsList.add(new CreateNPCAction(REAPER, 14387, 10, 6, 0, -1));
		actionsList.add(new NPCFaceTileAction(REAPER, 10, 5, -1));
		actionsList.add(new NPCGraphicAction(REAPER, new Graphics(184), 2));

		actionsList.add(new NPCForceTalkAction(REAPER, "....", 3));

		actionsList.add(new NPCForceTalkAction(REAPER, "Earth... It's too cold here.", -1));

		actionsList.add(new NPCFaceTileAction(REAPER, 9, 6, -1));
		actionsList
				.add(new MovePlayerAction(9, 6, 0, Player.TELE_MOVE_TYPE, -1));
		actionsList.add(new PlayerFaceTileAction(9, 5, -1));
		actionsList.add(new PlayerAnimationAction(new Animation(2111), -1));
		actionsList.add(new PlayerGraphicAction(new Graphics(184), 1));

		//actionsList.add(new DestroyCachedObjectAction(REAPER, 0));

		actionsList.add(new PlayerFaceTileAction(9, 7, 1));

		actionsList.add(new PlayerFaceTileAction(8, 6, 1));

		actionsList.add(new PlayerFaceTileAction(10, 6, 1));

		actionsList.add(new PlayerForceTalkAction("Huh?", 1));

		actionsList.add(new PlayerAnimationAction(new Animation(857), -1));
		actionsList.add(new PlayerForceTalkAction("Grim? What are you doing here?!", 3));
		actionsList.add(new NPCForceTalkAction(REAPER,
				"It's that time of year that i walk on earth to kill many humans! And children muhaaha!", -1));
		actionsList.add(new CreateNPCAction(HETTY, 30047, 8, 6, 0, -1)); // Todo
		//actionsList.add(new CreateNPCAction(IDK, 298, 3, 5, 0, -1)); // Todo
		actionsList.add(new MoveNPCAction(HETTY, 9, 7, false, 0));
		actionsList.add(new NPCFaceTileAction(HETTY,10, 6, 1));
		//actionsList.add(new MoveNPCAction(IDK, 8, 5, false, 2));
		actionsList.add(new NPCForceTalkAction(HETTY,
				"My Lord! It's an honour to see you once again.", 3));

		actionsList.add(new PlayerFaceTileAction(8, 6, 3));

		actionsList.add(new PlayerForceTalkAction("Why am i here?!!", 1));

		actionsList.add(new NPCForceTalkAction(HETTY, "I needed you to find my Lord and that's why you're here.",
				2));

		actionsList.add(new NPCForceTalkAction(HETTY,
				"You've done a fantastic job "+player.getDisplayName()+". Thank you.", 3));

		actionsList.add(new NPCForceTalkAction(REAPER, "Enough of this! Hetty, bring me soul.", 2));

		actionsList.add(new NPCForceTalkAction(HETTY,
				"Lord.. No souls are ready yet.", 3));
		
		actionsList.add(new NPCForceTalkAction(REAPER, "You're useless! We must get this started!", 1));
		
		player.sendMessage("Congratulations! You've completed the H'ween event of 2015!");
		player.sendMessage("You can keep the broomstick as a gift!");

		
		return actionsList.toArray(new CutsceneAction[actionsList.size()]);
	}

}
