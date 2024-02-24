package com.rs.game.player.quests;

import java.util.HashMap;
import java.util.Map;

import com.rs.game.player.Player;
import com.rs.game.player.quests.impl.CooksAssistant;

/**
 * A component that is used for storing, and handling quests.
 * 
 * @author Thomas Le Godais <thomaslegodais@live.com>
 *
 */
public class QuestComponent {

	/** The singleton instance for the component **/
	private static QuestComponent singleton = null;
	
	/** A map containing current quests. **/
	private Map<Integer, AbstractQuest<?, ?>> questMap = new HashMap<Integer, AbstractQuest<?, ?>>();
	
	/**
	 * Constructs a new QuestComponent instance.
	 */
	public QuestComponent() {
		questMap.put(1, new CooksAssistant());
	}
	
	/**
	 * Starts a quest based on the id.
	 * 
	 * @param player The player.
	 * @param questId The quest id.
	 */
	public void startQuest(Player player, int questId) {
		questMap.get(questId).handleQuest(player);
	}
	

	/**
	 * Gets a certain quest from the map.
	 * 
	 * @param questIndex the index.
	 * @return the quest.
	 */
	public AbstractQuest<?, ?> getQuest(int questIndex) {
		return questMap.get(questIndex);
	}
	
	/**
	 * Gets the quests.
	 * 
	 * @return the quests.
	 */
	public AbstractQuest<?, ?> getQuests() {
		for(int index = 0; index < questMap.size();)
				return questMap.get(index);
		return null;
	}
	
	/**
	 * Gets the singleton instance for the component.
	 * 
	 * @return The singleton instance.
	 */
	public static QuestComponent getSingleton() {
		if(singleton == null)
			singleton = new QuestComponent();
		return singleton;
	}
}