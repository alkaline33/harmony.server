/*package com.rs.game.npc.vorago;

import com.rs.cache.loaders.AnimationDefinitions;
import com.rs.game.Animation;
import com.rs.game.WorldTile;
import com.rs.game.map.bossInstance.impl.VoragoInstance;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

@SuppressWarnings("serial")
public class VoragoFace extends NPC {

	private transient VoragoInstance instance;
	private boolean battleInGoing;
	private boolean transforming;

	public VoragoFace(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea,
			VoragoInstance instance) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
		this.instance = instance;
		this.setNextFaceEntity(null);
		battleInGoing = false;
		transforming = false;
	}

	public boolean isBattleInGoing() {
		return battleInGoing;
	}

	public void setBattleInGoing(boolean battleInGoing) {
		this.battleInGoing = battleInGoing;
		if (battleInGoing)
			setNextNPCTransformation(17162);
		else
			startTransformation(null);
	}

	public VoragoInstance getInstance() {
		return instance;
	}

	public boolean isTransforming() {
		return transforming;
	}

	public void startTransformation(Player player) {
		if (player != null)
			player.lock();
		setNextAnimation(new Animation(20335));
		transforming = true;
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				setNextNPCTransformation(17161);
				transforming = false;
				if (player != null) {
					player.unlock();
					player.getDialogueManager().startDialogue("VoragoFaceD", getId(), 0, instance);
				}
			}
		}, (AnimationDefinitions.getAnimationDefinitions(20335).getEmoteClientCycles() / 30));
	}
}*/