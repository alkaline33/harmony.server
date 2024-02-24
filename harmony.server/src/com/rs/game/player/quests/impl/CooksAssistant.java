package com.rs.game.player.quests.impl;

import com.rs.game.player.Player;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.quests.AbstractQuest;

/**
 * Handles the cooks assistant quest.
 * 
 * @author Thomas Le Godais <thomaslegodais@live.com>
 *
 */
public class CooksAssistant extends AbstractQuest<CooksAssistant.Stage, CooksAssistant.Nodes> {

	/**
	 * The current nodes of the quest.
	 * 
	 * @author Thomas Le Godais <thomaslegodais@live.com>
	 *
	 */
	public enum Nodes {
		/*
		 * The chef node.
		 */
		CHEF(false);
		
		
		/** The interaction value. **/
		private boolean value;
		
		/**
		 * Constructs a new Nodes instance.
		 * 
		 * @param value the value.
		 */
		private Nodes(boolean value) {
			this.value = value;
		}
		
		/**
		 * Gets the value of the node.
		 * 
		 * @return the value.
		 */
		public boolean getValue() {
			return value;
		}
		
		/**
		 * Sets the value of the node.
		 * 
		 * @param newValue the new value to set.
		 */
		public void setValue(boolean newValue) {
			this.value = newValue;
		}
	}

	/**
	 * The different stages of the quest.
	 * 
	 * @author Thomas Le Godais <thomaslegodais@live.com>
	 *
	 */
	public enum Stage {
		/*
		 * Start the quest.
		 */
		START,
		
		/*
		 * Gather the ingredients.
		 */
		GATHER_INGREDIENTS, 
		
		/*
		 * The talk to chef.
		 */
		TALK_TO_CHEF, 
		
		/*
		 * Finish the quest.
		 */
		FINISH

	}
	
	/**
	 * Constructs a new CooksAssistant instance.
	 */
	public CooksAssistant() {
		super("Cooks' Assistant", 1, Stage.START, Nodes.CHEF);
	}


	@Override
	public void handleQuest(Player player) {
		switch(getQuestState()) {
		case START:
			player.getPackets().sendRunScript(1207, new Object[] { 5 });	
			player.getInterfaceManager().sendInterface(275);
			
			for(int i = 0; i < 21; i++)
				player.getPackets().sendIComponentText(275, i, "");
			
			player.getPackets().sendIComponentText(275, 1, this.getName());
			player.getPackets().sendIComponentText(275, 10, "Begin by speaking with the Cook in Lumbridge Castle.");
			
			player.getPackets().sendIComponentText(275, 12, "Requirments:");
			player.getPackets().sendIComponentText(275, 13, "<str>None.</str>");
			break;
			
		case GATHER_INGREDIENTS:
			
			break;
			
		case TALK_TO_CHEF:
			
			break;
			
		case FINISH:
			
			break;
		}
	}


	@Override
	public void handleDialogue(Player player, int npcId) {
		switch(npcId) {
		case 847:
			player.getDialogueManager().startDialogue(new Dialogue() {

				private int npcId;
								
				@Override
				public void start() {					
					npcId = 847;
					
					if(hasInteracted(Nodes.CHEF))
						sendNPCDialogue(npcId, 9827, "Have you gathered my ingredients?.");
					else
						sendNPCDialogue(npcId, 9827, "Waahh, what am I to do? I'm so done for!!");
				}

				@Override
				public void run(int interfaceId, int componentId) {
					if ((hasInteracted(Nodes.CHEF))) {
						switch (stage) {
						case -1:
							this.sendOptionsDialogue("What would you like?", new String[] { "yeah", "nah", "Nevermind, I'll leave you alone." });
							stage = 0;
							break;
						}
					}
					if (!(hasInteracted(Nodes.CHEF))) {
						switch (stage) {
						case -1:
							this.sendOptionsDialogue("What would you like?", new String[] { "What's wrong, chef?", "Grow up, you big baby!", "Nevermind, I'll leave you alone." });
							stage = 0;
							break;
						case 0:
							if (componentId == OPTION_1) {
								sendPlayerDialogue(9827, "What's wrong, chef?");
								stage = 1;
							} else if (componentId == OPTION_2) {
								sendPlayerDialogue(9827, "Grow up, you big baby!");
							} else if (componentId == OPTION_3) {
								sendPlayerDialogue(9827, "Nevermind, I'll leave you alone.");
							}
							break;
							/*
							 * I need some ingredients for the kings birthday cake.
							 */
						case 1:
							sendNPCDialogue(npcId, 9827, "I need some ingredients for the kings birthday cake, and I do not have enough time to go get them myself.");
							stage = 2;
							break;
							/*
							 * What do you need?
							 */
						case 2:
							sendPlayerDialogue(9827, "Well maybe I could be of assistance, what do you need?");
							stage = 3;
							break;
							/*
							 * I need milk, eggs, and flour.
							 */
						case 3:
							sendNPCDialogue(npcId, 9827, "Well I have the frosting, so I guess that just leaves milk, eggs, and flour. Do you think you could help me out, there will be a small reward.");
							stage = 4;
							break;
							/*
							 * That shouldn't be too hard to get.
							 */
						case 4:
							sendPlayerDialogue(9827, "That shouldn't be too hard for me to gather, I'll be back in a jiffy!");
							stage = 5;
							break;
							/*
							 * Oh my god, that you so much.
							 */
						case 5:
							sendNPCDialogue(npcId, 9827, "Thank you so much, " + player.getUsername() + ", I owe you big time!");
							
							for(Nodes node : Nodes.values()) {
								if(node.equals(Nodes.CHEF)) {
									node.setValue(true);
									end();
								}
							}
							break;
							/*
							 * This is where we end the current dialogue.
							 */
						case 15:
							end();
							break;
						}
					} else {
						switch(stage) {
						case -1:
							break;
						}
					}
				}

				@Override
				public void finish() {					
				}
			});
			break;
		}
			}


	@Override
	public void handleObjectClick(Player player, int objectId, boolean firstClick, boolean secondClick, boolean thirdClick) {		
		if(firstClick) {
			switch(objectId) {
			
			}
			return;
		} else if(secondClick) {
			switch(objectId) {
			
			}
			return;
		} else if(thirdClick) {
			switch(objectId) {
			
			}
			return;
		}
	}


	@Override
	public boolean hasDialogue() {
		return true;
	}


	@Override
	public boolean hasObjectClick() {
		return false;
	}


	@Override
	public boolean hasInteracted(Nodes node) {
		if(node.getValue() == true)
			return true;
		return false;
	}
}