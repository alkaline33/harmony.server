package com.rs.game.player.bot;

import javax.xml.stream.Location;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.FloorItem;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.npc.NPC;
import com.rs.game.player.ChatMessage;
import com.rs.game.player.HintIcon;
import com.rs.game.player.Player;
import com.rs.game.player.PublicChatMessage;
import com.rs.game.player.QuickChatMessage;
import com.rs.game.player.content.clans.ClansManager;
import com.rs.net.Session;
import com.rs.net.encoders.WorldPacketsEncoder;

/**
 * Created by Valkyr on 26/05/2016.
 */
public class FakeWorldPacketsEncoder extends WorldPacketsEncoder {
    public FakeWorldPacketsEncoder(Session o, Bot bot) {
        super(o, bot);
    }
    
    @Override
	public void sendSkillLevel(int skill) {
		
	}
    
	@Override
	public void closeInterface(int windowComponentId) {
	}

	// public static boolean trayDisabled = true;

	@Override
	public void closeInterface(int windowId, int windowComponentId) {
	}

	@Override
	public void receiveClanChatMessage(boolean myClan, String display, int rights, ChatMessage message) {
	}
	@Override
	public void receiveFriendChatQuickMessage(String name, String display, int rights, String chatName, QuickChatMessage message) {
	}

	@Override
	public void receivePrivateChatQuickMessage(String name, String display, int rights, QuickChatMessage message) {
	}

	@Override
	public void resetItems(int key, boolean negativeKey, int size) {
	}

	@Override
	public void resetSounds() {
	}

	/**
	 * This will blackout specified area.
	 * 
	 * @param byte
	 *            area = area which will be blackout (0 = unblackout; 1 =
	 *            blackout orb; 2 = blackout map; 5 = blackout orb and map)
	 */
	@Override
	public void sendBlackOut(int area) {
	}

	// instant
	@Override
	public void sendCameraLook(int viewLocalX, int viewLocalY, int viewZ) {
	}

	@Override
	public void sendCameraLook(int viewLocalX, int viewLocalY, int viewZ, int speed1, int speed2) {
	}

	@Override
	public void sendCameraPos(int moveLocalX, int moveLocalY, int moveZ) {
		sendCameraPos(moveLocalX, moveLocalY, moveZ, -1, -1);
	}

	@Override
	public void sendCameraPos(int moveLocalX, int moveLocalY, int moveZ, int speed1, int speed2) {
	}

	@Override
	public void sendCameraRotation(int unknown1, int unknown2) {
	}

	@Override
	public void sendCameraShake(int slotId, int b, int c, int d, int e) {
	}

	@Override
	public void sendClanChannel(ClansManager manager, boolean myClan) {
	}

	@Override
	public void sendClanInviteMessage(Player p) {
	}

	@Override
	public void sendClanSettings(ClansManager manager, boolean myClan) {
	}

	@Override
	public void sendClanWarsRequestMessage(Player p) {
	}

	@Override
	public void sendClientConsoleCommand(String command) {
	}

	@Override
	public void sendConfig(int id, int value) {
		return;
	}

	@Override
	public void sendConfig1(int id, int value) {
	}

	@Override
	public void sendConfig2(int id, int value) {
	}

	@Override
	public void sendConfigByFile(int fileId, int value) {
	}

	@Override
	public void sendCutscene(int id) {
	}

	@Override
	public void sendDestroyObject(WorldObject object) {
	}

	@Override
	public void sendDuelChallengeRequestMessage(Player p, boolean friendly) {
	}

	/*
	 * dynamic map region
	 */
	@Override
	public void sendDynamicMapRegion(boolean sendLswp) {
	}

	@Override
	public void sendEntityOnIComponent(boolean isPlayer, int entityId, int interfaceId, int componentId) {
	}

	@Override
	public void sendFaceOnIComponent(int interfaceId, int componentId, int look1, int look2, int look3) {
	}

	@Override
	public void sendFriend(String username, String displayName, int world, boolean putOnline, boolean warnMessage) {
	}

	@Override
	public void sendFriends() {
	}

	@Override
	public void sendFriendsChatChannel() {
	}

	@Override
	public void sendGameBarStages() {
	}

	@Override
	public void sendGameMessage(String text) {
	}

	@Override
	public void sendGameMessage(String text, boolean filter) {
	}

	@Override
	public void sendGlobalConfig(int id, int value) {
	}

	@Override
	public void sendGlobalConfig1(int id, int value) {
	}

	@Override
	public void sendGlobalConfig2(int id, int value) {
	}

	@Override
	public void sendGlobalString(int id, String string) {
	}

	@Override
	public void sendGraphics(Graphics graphics, Object target) {
	}

	@Override
	public void sendGroundItem(FloorItem item) {
	}

	@Override
	public void sendHideIComponent(int interfaceId, int componentId, boolean hidden) {
	}

	@Override
	public void sendHintIcon(HintIcon icon) {
	}

	@Override
	public void sendIComponentAnimation(int emoteId, int interfaceId, int componentId) {
	}

	@Override
	public void sendIComponentModel(int interfaceId, int componentId, int modelId) {
	}

	@Override
	public void sendIComponentSettings(int interfaceId, int componentId, int fromSlot, int toSlot, int settingsHash) {
	}

	@Override
	public void sendIComponentSprite(int interfaceId, int componentId, int spriteId) {
	}

	@Override
	public void sendIComponentText(int interfaceId, int componentId, String text) {
	}

	@Override
	public void sendIgnore(String name, String display, boolean updateName) {
	}

	@Override
	public void sendIgnores() {
	}

	@Override
	public void sendIndex14Sound(int id, int delay) {
	}

	@Override
	public void sendIndex15Sound(int id, int delay) {
	}

	@Override
	public void sendInputIntegerScript(boolean integerEntryOnly, String message) {
	}

	@Override
	public void sendInputLongTextScript(String message) {
	}

	@Override
	public void sendInputNameScript(String message) {
	}

	@Override
	public void sendInterface(boolean nocliped, int windowId, int windowComponentId, int interfaceId) {
	}

	// 131 clan chat quick message

	@Override
	public void sendInterFlashScript(int interfaceId, int componentId, int width, int height, int slot) {
	}

	@Override
	public void sendInterSetItemsOptionsScript(int interfaceId, int componentId, int key, boolean negativeKey, int width, int height, String... options) {
	}

	@Override
	public void sendInterSetItemsOptionsScript(int interfaceId, int componentId, int key, int width, int height, String... options) {
	}

	@Override
	public void sendInventoryMessage(int border, int slotId, String message) {
	}

	@Override
	public void sendItemOnIComponent(int interfaceid, int componentId, int id, int amount) {
	}

	@Override
	public void sendItems(int key, boolean negativeKey, Item[] items) {
	}

	@Override
	public void sendItems(int key, boolean negativeKey, ItemsContainer<Item> items) {
	}

	@Override
	public void sendItems(int key, Item[] items) {
	}

	@Override
	public void sendItems(int key, ItemsContainer<Item> items) {
	}

	@Override
	public void sendItemsLook() {
	}

	/*
	 * sends local npcs update
	 */
	@Override
	public void sendLocalNPCsUpdate() {
	}

	/*
	 * sends local players update
	 */
	@Override
	public void sendLocalPlayersUpdate() {
	}

	@Override
	public void sendLogout(boolean lobby) {
	}

	/*
	 * normal map region
	 */
	@Override
	public void sendMapRegion(boolean sendLswp) {
	}

	@Override
	public void sendMessage(int type, String text, Player p) {
	}
	@Override
	public void sendMusic(int id) {
	}

	@Override
	public void sendMusic(int id, int delay, int volume) {
	}

	@Override
	public void sendMusicEffect(int id) {
	}

	@Override
	public void sendNPCInterface(NPC npc, boolean nocliped, int windowId, int windowComponentId, int interfaceId) {
	}

	@Override
	public void sendNPCMessage(int border, int color, NPC npc, String message) {
	}

	@Override
	public void sendNPCMessage(int border, NPC npc, String message) {
	}

	@Override
	public void sendNPCOnIComponent(int interfaceId, int componentId, int npcId) {
	}

	@Override
	public void sendObjectAnimation(WorldObject object, Animation animation) {
	}

	@Override
	public void sendOpenURL(String url) {
	}

	@Override
	public void sendOtherGameBarStages() {
	}

	@Override
	public void sendPanelBoxMessage(String text) {
	}

	@Override
	public void sendPlayerOnIComponent(int interfaceId, int componentId) {
	}

	@Override
	public void sendPlayerOption(String option, int slot, boolean top) {
	}

	@Override
	public void sendPlayerOption(String option, int slot, boolean top, int cursor) {
	}

	@Override
	public void sendPlayerUnderNPCPriority(boolean priority) {
	}

	@Override
	public void sendPrivateGameBarStage() {
	}

	@Override
	public void sendPrivateQuickMessageMessage(String username, QuickChatMessage message) {
	}

	@Override
	public void sendPublicMessage(Player p, PublicChatMessage message) {
	}

	@Override
	public void sendRandomOnIComponent(int interfaceId, int componentId, int id) {
	}

	@Override
	public void sendRemoveGroundItem(FloorItem item) {
	}

	@Override
	public void sendResetCamera() {
	}

	@Override
	public void sendResetMinimapFlag() {
	}

	@Override
	public void sendRunEnergy() {
	}

	@Override
	public void sendRunScript(int scriptId, Object... params) {
	}

	@Override
	public void sendSetMouse(String walkHereReplace, int cursor) {
	}

	// effect type 1 or 2(index4 or index14 format, index15 format unusused by
	// jagex for now)
	@Override
	public void sendSound(int id, int delay, int effectType) {
	}

	@Override
	public void sendSpawnedObject(WorldObject object) {
	}

	@Override
	public void sendStopCameraShake() {
	}

	@Override
	public void sendSystemUpdate(int delay) {
	}

	// CUTSCENE PACKETS START

	@Override
	public void sendTradeRequestMessage(Player p) {
	}

	@Override
	public void sendUnlockIComponentOptionSlots(int interfaceId, int componentId, int fromSlot, int toSlot, int... optionsSlots) {
	}

	/*
	 * useless, sending friends unlocks it
	 */
	@Override
	public void sendUnlockIgnoreList() {
	}

	@Override
	public void sendUpdateItems(int key, boolean negativeKey, Item[] items, int... slots) {
	}

	@Override
	public void sendUpdateItems(int key, Item[] items, int... slots) {
	}

	@Override
	public void sendUpdateItems(int key, ItemsContainer<Item> items, int... slots) {
	}

	@Override
	public void sendVoice(int id) {
	}


	/*
	 * sets the pane interface
	 */
	@Override
	public void sendWindowsPane(int id, int type) {
	}

	@Override
	public void sendWorldList(boolean full) {
	}

	@Override
	public void sendWorldTile(WorldTile tile) {
	}

}
