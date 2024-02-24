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

public class SunfreetsEnd extends Cutscene {

	@Override
	public boolean hiddenMinimap() {
		return false;
	}

	private static int SUNFREET = 1, SLISKE = 2, IDK = 3;

	@Override
	public CutsceneAction[] getActions(Player player) {
		ArrayList<CutsceneAction> actionsList = new ArrayList<CutsceneAction>();
		// first part
		actionsList.add(new ConstructMapAction(395, 1221, 3, 3));
		//actionsList
		//		.add(new MovePlayerAction(10, 0, 0, Player.TELE_MOVE_TYPE, 0)); // out
		actionsList.add(new LookCameraAction(10, 8, 1000, -1));
		actionsList.add(new PosCameraAction(10, 0, 2000, 3));
		actionsList.add(new CreateNPCAction(SUNFREET, 15222, 12, 8, 0, -1));
		actionsList.add(new NPCFaceTileAction(SUNFREET, 10, 5, -1));
		actionsList.add(new NPCGraphicAction(SUNFREET, new Graphics(184), 2));
		actionsList.add(new CreateNPCAction(SLISKE, 14286, 14, 12, 0, -1));

		actionsList.add(new NPCForceTalkAction(SUNFREET, "Grrr", 3));
		
		actionsList.add(new NPCFaceTileAction(SLISKE, 10, 5, 3));

		actionsList.add(new NPCForceTalkAction(SLISKE, "Cut the sh!t Sunfreet. I know you're more than a wild beast.", 3));

		actionsList.add(new NPCFaceTileAction(SUNFREET, 15, 12, 3));

		actionsList.add(new NPCForceTalkAction(SUNFREET,
				"I have walked this dungeon for many years and never once has such a disgrace crossed my path.", 3));

		actionsList.add(new NPCForceTalkAction(SUNFREET,
				"What do you want... Sliske. You're wasting my time.", 3));
		
		actionsList.add(new NPCForceTalkAction(SLISKE,
				"That's just the thing. Drygon has asked me to replace you and that, i will do.", 3));

		actionsList.add(new NPCForceTalkAction(SUNFREET, "Grrr.. You can't replace me, you pitiful, long legged, freak!",
				2));

		actionsList.add(new NPCForceTalkAction(SLISKE,
				"You under estimate me.. As a fat nerd once said.. I will fk u up m8. MLG 360scope style.", 2));

		actionsList.add(new NPCForceTalkAction(SUNFREET, "Fuck you Sliske and your dwarf boss!", 2));

		actionsList.add(new NPCForceTalkAction(SLISKE,
				"Last of your race.. Now i'm going to end it!", 3));
		
		actionsList.add(new NPCAnimationAction(SLISKE, new Animation(11375), -1));
		actionsList.add(new NPCGraphicAction(SUNFREET, new Graphics(56), -1));
		actionsList.add(new NPCGraphicAction(SUNFREET, new Graphics(47), -1));
		actionsList.add(new NPCAnimationAction(SUNFREET, new Animation(16313), 2));
		actionsList.add(new NPCGraphicAction(SUNFREET, new Graphics(83), -1));
		actionsList.add(new DestroyCachedObjectAction(SUNFREET, 0));
		
		actionsList.add(new NPCForceTalkAction(SLISKE,
				"Ha, now it's my time to behold this mighty bow.", 3));
		
		actionsList.add(new PlayerFaceTileAction(14, 12, 1));
		actionsList.add(new PlayerAnimationAction(new Animation(859), -1));
		actionsList.add(new PlayerForceTalkAction("No it fucking ain't m8.", 2));
		
		actionsList.add(new NPCForceTalkAction(SLISKE,
				"I'll be seeing you around... Human. My combat isn't broken like Sunfreets. Good luck.", 3));
		
		player.seensunfreetendcutscene = true;

		
		return actionsList.toArray(new CutsceneAction[actionsList.size()]);
	}

}
