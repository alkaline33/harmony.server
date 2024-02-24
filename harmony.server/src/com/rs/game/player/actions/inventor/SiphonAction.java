package com.rs.game.player.actions.inventor;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz				/**
 * 									**
 * 								@@TODO ADD DIFFERENT IDEA MODELS
 * 									ADD DIFFERENT SCIENTIST MODELS
 * 									SORT XP OUT
 * 									SORT HP OUT
 * 									LEVELREQ IS AN XP REQ
 * 									
 * 
 */
public class SiphonAction extends Action {

	private static enum Creature {

		AIR_ESSLING(15403, 9.5, 16596, 24215, 16634, 10, 1, 16571, 0.1, 24215), 

		MIND_ESSLING(15404, 10, 16596, 24217, 16634, 10, 1, 16571, 0.2, 24215);

		private int npcId, ideaId, playerEmoteId, npcEmoteId, npcLife, levelRequired, deathEmote, gatherIdea;
		private double xp, pointValue;
		private Creature(int npcId, double xp, int playerEmoteId, int ideaId,
				int npcEmoteId, int npcLife, int levelRequired, int deathEmote, double pointValue, int gatherIdea) {
			this.npcId = npcId;
			this.xp = xp;
			this.playerEmoteId = playerEmoteId;
			this.ideaId = ideaId;
			this.npcEmoteId = npcEmoteId;
			this.npcLife = npcLife;
			this.levelRequired = levelRequired;
			this.deathEmote = deathEmote;
			this.pointValue = pointValue;
			this.gatherIdea = gatherIdea; //the ideas they will gain
		}
		/**
		 * Gets the creatures chipping runes.
		 * @return the chipRune.
		 */
		public int getIdeas() {
			return gatherIdea;
		}
		/**
		 * Gets the creatures deathEmote.
		 * @return the deathEmote
		 */
		public int getDeathEmote() {
			return deathEmote;
		}

		/**
		 * @return the npcEmoteId
		 */
		public int getNpcEmoteId() {
			return npcEmoteId;
		}

		/**
		 * Gets the rune id from the essling enum.
		 * 
		 * @return runeId
		 */
		public int getIdeaId() {
			return ideaId;
		}
		/**
		 * 
		 * @return the levelRequired
		 */
		public int getLevelRequired() {
			return levelRequired;
		}

	}

	private Creature creatures;
	private NPC creature;
	private boolean started;
	private int npcLife;
	private double points;

	/**
	 * RSRuneCrafting constructor for npcs.
	 * @param creatures
	 * @param creature
	 */
	public SiphonAction(Creature creatures, NPC creature) {
		this.creatures = creatures;
		this.creature = creature;
	}

	public static boolean siphon(Player player, NPC npc) {
		Creature creature = getCreature(npc.getId());
		if(creature == null)
			return false;
		player.getActionManager().setAction(new SiphonAction(creature, npc));
		return true;
	}

	private static Creature getCreature(int id) {
		for(Creature creature : Creature.values())
			if(creature.npcId == id)
				return creature;
		return null;
	}

	@Override
	public boolean start(Player player) {
		if(checkAll(player)) {
			npcLife = creatures.npcLife;
			return true;
		}
		return false;
	}

	@Override
	public boolean process(Player player) {
		return checkAll(player);
	}
	/**
	 * Checks the players requirements.
	 * @param player
	 * @return requirements.
	 */
	public boolean checkAll(final Player player) {
		if(player.isLocked()) {
			return false;
		}
		if (creature.hasFinished())
			return false;
		if(!started && !player.withinDistance(creature, 6)) 
			return true;
		if(player.getSkills().getLevel(Skills.RUNECRAFTING) < creatures.getLevelRequired()) {
			player.getDialogueManager().startDialogue("SimpleMessage",
					"This creature requires level " + creatures.getLevelRequired() + " to siphon.");
			return false;
		}
		if (!player.getInventory().hasFreeSlots() && !player.getInventory().containsItem(creatures.getIdeaId(), 1))  {
			player.getPackets().sendGameMessage(
					"Not enough space in your inventory.");
			return false;
		}
		if(!started) {
			creature.resetWalkSteps();
			player.resetWalkSteps();
			player.setNextAnimation(new Animation(creatures.playerEmoteId));
			started = true;
		}
		return true;
	}

	@Override
	public int processWithDelay(final Player player) {
		if (started) {
			int xp = player.inventorxp;
			if (Utils.getRandom(xp <= 500000 ? 2 : 1) == 1) {
				npcLife--;
				player.getInventory().addItem(creatures.getIdeaId(), 1);
				player.getInventory().deleteItem(24227, 1);
				double totalXp = creatures.xp;
				xp += totalXp;
				player.setNextGraphics(new Graphics(3071));
			} else {// When you don't get a rune random chance to get xp.
				xp += Utils.random(0 , 2000);
			}
			if (npcLife == 0) {
				processEsslingDeath(player);
				return -1;
			}

			player.setNextAnimation(new Animation(16596));
			creature.setNextAnimation(new Animation(creatures.getNpcEmoteId()));
			creature.setNextFaceWorldTile(player);
			creature.resetWalkSteps();
			player.setNextFaceWorldTile(creature);
			World.sendProjectile(creature, creature, player, 3060, 31, 35, 35, 0, 2, 0);
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					player.setNextGraphics(new Graphics(3062));
				}
			}, 1);
		}
		return 1;
	}
	/**
	 * Process the creatures death.
	 * @param player
	 */
	public void processEsslingDeath(final Player player) {
		creature.setNextAnimation(new Animation(creatures.getDeathEmote()));
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.getPackets().sendGameMessage("The scientist has lost his knowledge.");
				player.getPackets().sendGameMessage("You've drained the scientist to death...", true);
				player.setNextAnimation(new Animation(16599));
				creature.setRespawnTask();
				player.getInventory().addItem(24227, 1); //IDEA ITEM : - CHANCE OF MASTER IDEA?
				stop();
			}
		}, 2);
	}
	@Override
	public void stop(Player player) {
		player.setNextAnimation(new Animation(16599));
		setActionDelay(player, 3);
	}
	/**
	 * Process the chipping of creatures.
	 * @param player
	 * @param npc
	 */
	public static boolean chipCreature(Player player, NPC npc) {
		Creature creature = getCreature(npc.getId());
			player.getInventory().addItem(24227, 1); //IDEA ITEM
			World.sendProjectile(player, npc, player, 3060, 31, 35, 35, 0, 2, 0);//Sent to the creature instead of from
		return false;
	}
}
