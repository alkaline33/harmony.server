package com.rs.net.decoders;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.RegionBuilder;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.FloorItem;
import com.rs.game.item.Item;
import com.rs.game.minigames.clanwars.ClanWars;
import com.rs.game.minigames.creations.StealingCreation;
import com.rs.game.npc.NPC;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.npc.familiar.Familiar.SpecialAttack;
import com.rs.game.player.ChatMessage;
import com.rs.game.player.CoordsEvent;
import com.rs.game.player.Equipment;
import com.rs.game.player.Inventory;
import com.rs.game.player.LogicPacket;
import com.rs.game.player.Player;
import com.rs.game.player.PlayerOwnedShop;
import com.rs.game.player.PublicChatMessage;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.PlayerCombat;
import com.rs.game.player.actions.PlayerFollow;
import com.rs.game.player.actions.Summoning;
import com.rs.game.player.content.Commands;
import com.rs.game.player.content.CosmeticsHandler;
import com.rs.game.player.content.FriendChatsManager;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.Notes.Note;
import com.rs.game.player.content.ReferralMain;
import com.rs.game.player.content.SkillCapeCustomizer;
import com.rs.game.player.content.Vote;
import com.rs.game.player.content.WellOfFortuneV2;
import com.rs.game.player.content.bossinstance.BossInstance;
import com.rs.game.player.content.bossinstance.BossInstanceManager;
import com.rs.game.player.content.bossinstance.impl.AmigoBossInstance;
import com.rs.game.player.content.bossinstance.impl.ArmadylBossInstance;
import com.rs.game.player.content.bossinstance.impl.BandosBossInstance;
import com.rs.game.player.content.bossinstance.impl.CorpBossInstance;
import com.rs.game.player.content.bossinstance.impl.KbdBossInstance;
import com.rs.game.player.content.bossinstance.impl.KkBossInstance;
import com.rs.game.player.content.bossinstance.impl.SaradominBossInstance;
import com.rs.game.player.content.bossinstance.impl.TheThunderousInstance;
import com.rs.game.player.content.bossinstance.impl.ZamorakBossInstance;
import com.rs.game.player.content.clans.ClansManager;
import com.rs.game.player.content.clans.clancitadels.ClanCitadel;
import com.rs.game.player.content.grandExchange.GrandExchangeLoader.GrandExchangeOffer;
import com.rs.game.player.content.grandExchange.GrandExchangeLoader.GrandExchangeOffer.Type;
import com.rs.game.player.content.interfaces.NpcDropViewer;
import com.rs.game.player.content.interfaces.ViewProfile;
import com.rs.game.player.controlers.HouseControler;
import com.rs.game.player.interfaces.StarterInterface;
import com.rs.game.route.RouteEvent;
import com.rs.io.InputStream;
import com.rs.io.OutputStream;
import com.rs.net.Session;
import com.rs.net.decoders.handlers.ButtonHandler;
import com.rs.net.decoders.handlers.InventoryOptionsHandler;
import com.rs.net.decoders.handlers.NPCHandler;
import com.rs.net.decoders.handlers.ObjectHandler;
import com.rs.utils.Colors;
import com.rs.utils.DisplayNames;
import com.rs.utils.Logger;
import com.rs.utils.Utils;
import com.rs.utils.huffman.Huffman;

public final class WorldPacketsDecoder extends Decoder {

	private static final byte[] PACKET_SIZES = new byte[106];

	private final static int WALKING_PACKET = 8;
	private final static int MINI_WALKING_PACKET = 58;
	private final static int AFK_PACKET = -1;
	private final static int KICK_CLAN_CHAT_PACKET = 92;
	public final static int ACTION_BUTTON1_PACKET = 14;
	public final static int ACTION_BUTTON2_PACKET = 67;
	public final static int ACTION_BUTTON3_PACKET = 5;
	public final static int ACTION_BUTTON4_PACKET = 55;
	public final static int ACTION_BUTTON5_PACKET = 68;
	public final static int ACTION_BUTTON6_PACKET = 90;
	public final static int ACTION_BUTTON7_PACKET = 6;
	public final static int ACTION_BUTTON8_PACKET = 32;
	public final static int ACTION_BUTTON9_PACKET = 27;
	private final static int TELEKINETIC_GRAB_SPELL_PACKET = 21;
	public final static int WORLD_MAP_CLICK = 38;
	public final static int ACTION_BUTTON10_PACKET = 96;
	public final static int RECEIVE_PACKET_COUNT_PACKET = 33;
	private final static int MAGIC_ON_ITEM_PACKET = -1;
	private final static int PLAYER_OPTION_4_PACKET = 17;
	private final static int GRAND_EXCHANGE_PACKET = 71;
	private final static int MOVE_CAMERA_PACKET = 103;
	private final static int INTERFACE_ON_OBJECT = 37;
	private static final int FORUM_THREAD_ID_PACKET = 18;
	private final static int CLICK_PACKET = -1;
	private final static int MOUVE_MOUSE_PACKET = -1;
	private final static int KEY_TYPED_PACKET = -1;
	private final static int CLOSE_INTERFACE_PACKET = 54;
	private final static int COMMANDS_PACKET = 60;
	private final static int ITEM_ON_ITEM_PACKET = 3;
	private final static int IN_OUT_SCREEN_PACKET = -1;
	private final static int DONE_LOADING_REGION_PACKET = 30;
	private final static int PING_PACKET = 21;
	private final static int SCREEN_PACKET = 98;
	private final static int CHAT_TYPE_PACKET = 83;
	private final static int CHAT_PACKET = 53;
	private final static int PUBLIC_QUICK_CHAT_PACKET = 86;
	private final static int ADD_FRIEND_PACKET = 89;
	private final static int ADD_IGNORE_PACKET = 4;
	private final static int REMOVE_IGNORE_PACKET = 73;
	private final static int JOIN_FRIEND_CHAT_PACKET = 36;
	private final static int CHANGE_FRIEND_CHAT_PACKET = 22;
	private final static int KICK_FRIEND_CHAT_PACKET = 74;
	private final static int REMOVE_FRIEND_PACKET = 24;
	private final static int SEND_FRIEND_MESSAGE_PACKET = 82;
	private final static int SEND_FRIEND_QUICK_CHAT_PACKET = 0;
	private final static int OBJECT_CLICK1_PACKET = 26;
	private final static int OBJECT_CLICK2_PACKET = 59;
	private final static int OBJECT_CLICK3_PACKET = 40;
	private final static int OBJECT_CLICK4_PACKET = 23;
	private final static int OBJECT_CLICK5_PACKET = 80;
	private final static int OBJECT_EXAMINE_PACKET = 25;
	private final static int NPC_CLICK1_PACKET = 31;
	private final static int NPC_CLICK2_PACKET = 101;
	private final static int NPC_CLICK3_PACKET = 34;
	private final static int ATTACK_NPC = 20;
	private final static int PLAYER_OPTION_1_PACKET = 42;
	private final static int PLAYER_OPTION_2_PACKET = 46;
	private final static int PLAYER_OPTION_6_PACKET = 49;
	private final static int ITEM_TAKE_PACKET = 57;
	private final static int DIALOGUE_CONTINUE_PACKET = 72;
	private final static int ENTER_INTEGER_PACKET = 81;
	private final static int ENTER_NAME_PACKET = 29;
	private final static int ENTER_STRING_PACKET = -1;
	private final static int SWITCH_INTERFACE_ITEM_PACKET = 76;
	private final static int INTERFACE_ON_PLAYER = 50;
	private final static int INTERFACE_ON_NPC = 66;
	private final static int COLOR_ID_PACKET = 97;
	private static final int NPC_EXAMINE_PACKET = 9;
	private final static int REPORT_ABUSE_PACKET = 11;
	// private final static int REPORT_ABUSE_PACKET = -1;
	private final static int PLAYER_OPTION_5_PACKET = 77;
	private final static int PLAYER_OPTION_9_PACKET = 56;
	private final static int WORLD_LIST_UPDATE = 87;
	private final static int DROP_ITEM_PACKET = 104;
	public final static int ESCAPE_PRESSED_PACKET = 105;

	static {
		loadPacketSizes();
	}

	/*
	 * public static void loadPacketSizes() { PACKET_SIZES[0] = -1; PACKET_SIZES[1]
	 * = -2; PACKET_SIZES[2] = -1; PACKET_SIZES[3] = 16; PACKET_SIZES[4] = -1;
	 * PACKET_SIZES[5] = 8; PACKET_SIZES[6] = 8; PACKET_SIZES[7] = 3;
	 * PACKET_SIZES[8] = -1; PACKET_SIZES[9] = 3; PACKET_SIZES[10] = -1;
	 * PACKET_SIZES[11] = -1; PACKET_SIZES[12] = -1; PACKET_SIZES[13] = 7;
	 * PACKET_SIZES[14] = 8; PACKET_SIZES[15] = 6; PACKET_SIZES[16] = 2;
	 * PACKET_SIZES[17] = 3; PACKET_SIZES[18] = -1; PACKET_SIZES[19] = -2;
	 * PACKET_SIZES[20] = 3; PACKET_SIZES[21] = 0; PACKET_SIZES[22] = -1;
	 * PACKET_SIZES[23] = 9; PACKET_SIZES[24] = -1; PACKET_SIZES[25] = 9;
	 * PACKET_SIZES[26] = 9; PACKET_SIZES[27] = 8; PACKET_SIZES[28] = 4;
	 * PACKET_SIZES[29] = -1; PACKET_SIZES[30] = 0; PACKET_SIZES[31] = 3;
	 * PACKET_SIZES[32] = 8; PACKET_SIZES[33] = 4; PACKET_SIZES[34] = 3;
	 * PACKET_SIZES[35] = -1; PACKET_SIZES[36] = -1; PACKET_SIZES[37] = 17;
	 * PACKET_SIZES[38] = 4; PACKET_SIZES[39] = 4; PACKET_SIZES[40] = 9;
	 * PACKET_SIZES[41] = -1; PACKET_SIZES[42] = 3; PACKET_SIZES[43] = 7;
	 * PACKET_SIZES[44] = -2; PACKET_SIZES[45] = 7; PACKET_SIZES[46] = 3;
	 * PACKET_SIZES[47] = 4; PACKET_SIZES[48] = -1; PACKET_SIZES[49] = 3;
	 * PACKET_SIZES[50] = 11; PACKET_SIZES[51] = 3; PACKET_SIZES[52] = -1;
	 * PACKET_SIZES[53] = -1; PACKET_SIZES[54] = 0; PACKET_SIZES[55] = 8;
	 * PACKET_SIZES[56] = 3; PACKET_SIZES[57] = 7; PACKET_SIZES[58] = -1;
	 * PACKET_SIZES[59] = 9; PACKET_SIZES[60] = -1; PACKET_SIZES[61] = 7;
	 * PACKET_SIZES[62] = 7; PACKET_SIZES[63] = 12; PACKET_SIZES[64] = 4;
	 * PACKET_SIZES[65] = 3; PACKET_SIZES[66] = 11; PACKET_SIZES[67] = 8;
	 * PACKET_SIZES[68] = 8; PACKET_SIZES[69] = 15; PACKET_SIZES[70] = 1;
	 * PACKET_SIZES[71] = 2; PACKET_SIZES[72] = 6; PACKET_SIZES[73] = -1;
	 * PACKET_SIZES[74] = -1; PACKET_SIZES[75] = -2; PACKET_SIZES[76] = 16;
	 * PACKET_SIZES[77] = 3; PACKET_SIZES[78] = 1; PACKET_SIZES[79] = 3;
	 * PACKET_SIZES[80] = 9; PACKET_SIZES[81] = 4; PACKET_SIZES[82] = -2;
	 * PACKET_SIZES[83] = 1; PACKET_SIZES[84] = 1; PACKET_SIZES[85] = 3;
	 * PACKET_SIZES[86] = -1; PACKET_SIZES[87] = 4; PACKET_SIZES[88] = 3;
	 * PACKET_SIZES[89] = -1; PACKET_SIZES[90] = 8; PACKET_SIZES[91] = -2;
	 * PACKET_SIZES[92] = -1; PACKET_SIZES[93] = -1; PACKET_SIZES[94] = 9;
	 * PACKET_SIZES[95] = -2; PACKET_SIZES[96] = 8; PACKET_SIZES[97] = 2;
	 * PACKET_SIZES[98] = 6; PACKET_SIZES[99] = 2; PACKET_SIZES[100] = -2;
	 * PACKET_SIZES[101] = 3; PACKET_SIZES[102] = 7; PACKET_SIZES[103] = 4; }
	 */

	public static void loadPacketSizes() {
		PACKET_SIZES[0] = -1;
		PACKET_SIZES[1] = -2;
		PACKET_SIZES[2] = -1;
		PACKET_SIZES[3] = 16;
		PACKET_SIZES[4] = -1;
		PACKET_SIZES[5] = 8;
		PACKET_SIZES[6] = 8;
		PACKET_SIZES[7] = 3;
		PACKET_SIZES[8] = -1;
		PACKET_SIZES[9] = 3;
		PACKET_SIZES[10] = -1;
		PACKET_SIZES[11] = -1;
		PACKET_SIZES[12] = -1;
		PACKET_SIZES[13] = 7;
		PACKET_SIZES[14] = 8;
		PACKET_SIZES[15] = 6;
		PACKET_SIZES[16] = 2;
		PACKET_SIZES[17] = 3;
		PACKET_SIZES[18] = -1;
		PACKET_SIZES[19] = -2;
		PACKET_SIZES[20] = 3;
		PACKET_SIZES[21] = 0;
		PACKET_SIZES[22] = -1;
		PACKET_SIZES[23] = 9;
		PACKET_SIZES[24] = -1;
		PACKET_SIZES[25] = 9;
		PACKET_SIZES[26] = 9;
		PACKET_SIZES[27] = 8;
		PACKET_SIZES[28] = 4;
		PACKET_SIZES[29] = -1;
		PACKET_SIZES[30] = 0;
		PACKET_SIZES[31] = 3;
		PACKET_SIZES[32] = 8;
		PACKET_SIZES[33] = 4;
		PACKET_SIZES[34] = 3;
		PACKET_SIZES[35] = -1;
		PACKET_SIZES[36] = -1;
		PACKET_SIZES[37] = 17;
		PACKET_SIZES[38] = 4;
		PACKET_SIZES[39] = 4;
		PACKET_SIZES[40] = 9;
		PACKET_SIZES[41] = -1;
		PACKET_SIZES[42] = 3;
		PACKET_SIZES[43] = 7;
		PACKET_SIZES[44] = -2;
		PACKET_SIZES[45] = 7;
		PACKET_SIZES[46] = 3;
		PACKET_SIZES[47] = 4;
		PACKET_SIZES[48] = -1;
		PACKET_SIZES[49] = 3;
		PACKET_SIZES[50] = 11;
		PACKET_SIZES[51] = 3;
		PACKET_SIZES[52] = -1;
		PACKET_SIZES[53] = -1;
		PACKET_SIZES[54] = 0;
		PACKET_SIZES[55] = 8;
		PACKET_SIZES[56] = 3;
		PACKET_SIZES[57] = 7;
		PACKET_SIZES[58] = -1;
		PACKET_SIZES[59] = 9;
		PACKET_SIZES[60] = -1;
		PACKET_SIZES[61] = 7;
		PACKET_SIZES[62] = 7;
		PACKET_SIZES[63] = 12;
		PACKET_SIZES[64] = 4;
		PACKET_SIZES[65] = 3;
		PACKET_SIZES[66] = 11;
		PACKET_SIZES[67] = 8;
		PACKET_SIZES[68] = 8;
		PACKET_SIZES[69] = 15;
		PACKET_SIZES[70] = 1;
		PACKET_SIZES[71] = 2;
		PACKET_SIZES[72] = 6;
		PACKET_SIZES[73] = -1;
		PACKET_SIZES[74] = -1;
		PACKET_SIZES[75] = -2;
		PACKET_SIZES[76] = 16;
		PACKET_SIZES[77] = 3;
		PACKET_SIZES[78] = 1;
		PACKET_SIZES[79] = 3;
		PACKET_SIZES[80] = 9;
		PACKET_SIZES[81] = 4;
		PACKET_SIZES[82] = -2;
		PACKET_SIZES[83] = 1;
		PACKET_SIZES[84] = 1;
		PACKET_SIZES[85] = 3;
		PACKET_SIZES[86] = -1;
		PACKET_SIZES[87] = 4;
		PACKET_SIZES[88] = 3;
		PACKET_SIZES[89] = -1;
		PACKET_SIZES[90] = 8;
		PACKET_SIZES[91] = -2;
		PACKET_SIZES[92] = -1;
		PACKET_SIZES[93] = -1;
		PACKET_SIZES[94] = 9;
		PACKET_SIZES[95] = -2;
		PACKET_SIZES[96] = 8;
		PACKET_SIZES[97] = 2;
		PACKET_SIZES[98] = 6;
		PACKET_SIZES[99] = 2;
		PACKET_SIZES[100] = -2;
		PACKET_SIZES[101] = 3;
		PACKET_SIZES[102] = 7;
		PACKET_SIZES[103] = 4;
		PACKET_SIZES[104] = 8;
		PACKET_SIZES[105] = 0;
	}

	private Player player;
	private int chatType;

	public WorldPacketsDecoder(Session session, Player player) {
		super(session);
		this.player = player;
	}

	@Override
	public void decode(InputStream stream) {
		while (stream.getRemaining() > 0 && session.getChannel().isConnected() && !player.hasFinished()) {
			int packetId = stream.readPacket(player);
			if (packetId >= PACKET_SIZES.length || packetId < 0) {
				if (Settings.DEBUG) {
					System.out.println("PacketId " + packetId + " has fake packet id.");
				}
				break;
			}
			int length = PACKET_SIZES[packetId];
			//System.out.println(packetId);
			if (length == -1) {
				length = stream.readUnsignedByte();
			} else if (length == -2) {
				length = stream.readUnsignedShort();
			} else if (length == -3) {
				length = stream.readInt();
			} else if (length == -4) {
				length = stream.getRemaining();
				if (Settings.DEBUG) {
					System.out.println("Invalid size for PacketId " + packetId + ". Size guessed to be " + length);
				}
			}
			if (length > stream.getRemaining()) {
				length = stream.getRemaining();
				if (Settings.DEBUG) {
					System.out.println("PacketId " + packetId + " has fake size. - expected size " + length);
					// break;
				}

			}
			/*
			 * System.out.println("PacketId " +packetId+ " has . - expected size " +length);
			 */
			// System.out.println(packetId);
			int startOffset = stream.getOffset();
			processPackets(packetId, stream, length);
			stream.setOffset(startOffset + length);
		}
	}

	public static void decodeLogicPacket(final Player player, LogicPacket packet) {
		int packetId = packet.getId();
		InputStream stream = new InputStream(packet.getData());
		if (packetId == PLAYER_OPTION_5_PACKET) {
			boolean unknown = stream.readByte() == 1;
			int playerIndex = stream.readUnsignedShortLE128();
			Player other = World.getPlayers().get(playerIndex);
			if (other == null || other.isDead() || other.hasFinished() || !player.getMapRegionsIds().contains(other.getRegionId())) {
				return;
			}
			if (player.getLockDelay() > Utils.currentTimeMillis()) {
				return;
			}
			if (!other.withinDistance(player, 14)) {
				player.getPackets().sendGameMessage("Unable to find target " + other.getDisplayName());
				return;
			}
			if (!player.hasEnteredPin && player.hasBankPin) {
				player.getTemporaryAttributtes().put("bank_pin1", Boolean.TRUE);
				player.getPackets().sendRunScript(108, new Object[] { "Enter Your Bank Pin Please" });
				return;
			}
			if (player.getRights() == 1 || player.getRights() == 2) {
				player.getDialogueManager().startDialogue("ModPanel", playerIndex);
			} else {
				ViewProfile.sendInterface(player, other);
			}
		}
		if (packetId == WALKING_PACKET || packetId == MINI_WALKING_PACKET) {
			if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead()) {
				return;
			}
			if (player.getTemporaryAttributtes().get("RingNPC") == Boolean.TRUE) {
				player.unlock();
				player.getAppearence().transformIntoNPC(-1);
				player.getTemporaryAttributtes().remove("RingNPC");
				player.setNextAnimation(new Animation(14884));
			}
			long currentTime = Utils.currentTimeMillis();
			if (player.getLockDelay() > currentTime) {
				return;
			}
			if (player.getFreezeDelay() >= currentTime) {
				player.getPackets().sendGameMessage("A magical force prevents you from moving.");
				return;
			}
			int length = stream.getLength();
			// if (packetId == MINI_WALKING_PACKET)
			// length -= 13;
			int baseX = stream.readUnsignedShort128();
			boolean forceRun = stream.readUnsigned128Byte() == 1;
			int baseY = stream.readUnsignedShort128();
			int steps = (length - 5) / 2;
			if (steps > 25) {
				steps = 25;
			}
			player.stopAll();
			if (forceRun) {
				player.setRun(forceRun);
			}
			for (int step = 0; step < steps; step++) {
				if (!player.addWalkSteps(baseX + stream.readUnsignedByte(), baseY + stream.readUnsignedByte(), 25, true)) {
					break;
				}
			}
		} else if (packetId == WORLD_LIST_UPDATE) {
			int updateType = stream.readInt();
			player.getPackets().sendWorldList(updateType == 0);
			// break;
		} else if (packetId == INTERFACE_ON_OBJECT) {
			boolean forceRun = stream.readByte128() == 1;
			int itemId = stream.readShortLE128();
			int y = stream.readShortLE128();
			int objectId = stream.readIntV2();
			int interfaceHash = stream.readInt();
			final int interfaceId = interfaceHash >> 16;
			int slot = stream.readShortLE();
			int x = stream.readShort128();
			if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead()) {
				return;
			}
			long currentTime = Utils.currentTimeMillis();
			if (player.getLockDelay() >= currentTime || player.getEmotesManager().getNextEmoteEnd() >= currentTime) {
				return;
			}
			final WorldTile tile = new WorldTile(x, y, player.getPlane());
			int regionId = tile.getRegionId();
			if (!player.getMapRegionsIds().contains(regionId)) {
				return;
			}
			/*
			 * WorldObject mapObject = World.getRegion(regionId).getObject(objectId, tile);
			 * if (mapObject == null || mapObject.getId() != objectId) return; final
			 * WorldObject object = !player.isAtDynamicRegion() ? mapObject : new
			 * WorldObject(objectId, mapObject.getType(), mapObject.getRotation(), x, y,
			 * player.getPlane());
			 */
			final WorldObject object = World.getObjectWithId(tile, objectId);// TODO
			if (object == null || object.getId() != objectId) {
				return;
			}
			final Item item = player.getInventory().getItem(slot);
			if (player.isDead() || Utils.getInterfaceDefinitionsSize() <= interfaceId) {
				return;
			}
			if (player.getLockDelay() > Utils.currentTimeMillis()) {
				return;
			}
			if (!player.getInterfaceManager().containsInterface(interfaceId)) {
				return;
			}
			if (item == null || item.getId() != itemId) {
				return;
			}
			player.stopAll(false); // false
			if (forceRun) {
				player.setRun(forceRun);
			}
			switch (interfaceId) {
			case Inventory.INVENTORY_INTERFACE: // inventory
				ObjectHandler.handleItemOnObject(player, object, interfaceId, item);
				break;
			}
		} else if (packetId == PLAYER_OPTION_2_PACKET) {
			if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead()) {
				return;
			}
			@SuppressWarnings("unused")
			boolean unknown = stream.readByte() == 1;
			int playerIndex = stream.readUnsignedShortLE128();
			Player p2 = World.getPlayers().get(playerIndex);
			if (p2 == null || p2.isDead() || p2.hasFinished() || !player.getMapRegionsIds().contains(p2.getRegionId())) {
				return;
			}
			if (player.getLockDelay() > Utils.currentTimeMillis()) {
				return;
			}
			player.stopAll(false);
			player.getActionManager().setAction(new PlayerFollow(p2));
		} else if (packetId == PLAYER_OPTION_5_PACKET) {
			// if (packetId == PLAYER_OPTION_5_PACKET) {
			if (player.getRights() < 1 || player.getRights() > 2) {
				return;
			}
			@SuppressWarnings("unused")
			boolean unknown = stream.readByte() == 1;
			int playerIndex = stream.readUnsignedShortLE128();
			Player other = World.getPlayers().get(playerIndex);
			if (other == null || other.isDead() || other.hasFinished() || !player.getMapRegionsIds().contains(other.getRegionId())) {
				return;
			}
			if (player.getLockDelay() > Utils.currentTimeMillis()) {
				return;
			}
			if (!other.withinDistance(player, 14)) {
				player.getPackets().sendGameMessage("Unable to find target " + other.getDisplayName());
				return;
			}
			if (player.getRights() < 1) {
				player.getPackets().sendGameMessage("You must be a <img=0>Player Moderator<img=0> to use this.");
				return;
			}
			player.getPackets().sendGameMessage("You have muted " + (player.getRights() >= 1 ? " 48 hours by " : "1 hour to ") + other.getDisplayName() + ".");
			World.sendWorldMessage("<col=ccff9900><shad=000000><img=5>News:<col=cc0000>" + Utils.formatPlayerNameForDisplay(player.getDisplayName()) + " has muted " + Utils.formatPlayerNameForDisplay(other.getDisplayName()) + " for 48 hours!", true);
			other.setMuted(Utils.currentTimeMillis() + (player.getRights() >= 1 ? 48 * 60 * 60 * 1000 : 1 * 60 * 60 * 1000));
			return;
		} else if (packetId == PLAYER_OPTION_6_PACKET) {
			@SuppressWarnings("unused")
			boolean unknown = stream.readByte() == 1;
			int playerIndex = stream.readUnsignedShortLE128();
			Player p2 = World.getPlayers().get(playerIndex);
			if (p2 == null || p2.isDead() || p2.hasFinished() || !player.getMapRegionsIds().contains(p2.getRegionId())) {
				return;
			}
			if (player.getLockDelay() > Utils.currentTimeMillis()) {
				return;
			}
			player.stopAll(false);
			if (player.ironman == true && !player.isIronmanDuo()) {
				player.sendMessage("This account cannot use this!");
				return;
			}
			if (p2.ironmanpartner != null || p2.ironmanduo != true) {
				player.sendMessage("This player cannot accept your partner request.");
				return;
			}
			if (player.ironmanpartner != null) {
				player.sendMessage("You already have a partner!");
				return;
			}
			if (player.isIronmanDuo() && player.ironmanpartner == null) {
			player.requestedpartner = p2.getDisplayName();
			p2.requestedpartner = player.getDisplayName();
			p2.sendMessage(Colors.brown+""+player.getDisplayName()+" has requested to be your ironman partner. Type ::acceptpartner to confirm. This cannot be reversed!");
			player.sendMessage(Colors.brown+"You have requested to be "+p2.getDisplayName()+"'s ironman partner. Type ::acceptpartner to confirm. This cannot be reversed!");
			player.sendMessage(Colors.red+"If you wish to cancel this, please relog!");
			p2.sendMessage(Colors.red+"If you wish to cancel this, please relog!");
				return;
			}
			if (player.isCanPvp() || World.Scorpia(player) || World.HungerGames(player) || World.TheCalamity(player) || player.getControlerManager().getControler() != null) {
				player.sendMessage("You cannot perform this action right now.");
				return;
			}
			if (player.isPvpMode() && !p2.isPvpMode()) {
				player.sendMessage("Sorry. You two cannot trade between economies.");
				p2.sendMessage("Sorry. You two cannot trade between economies.");
				return;
			}
			if (!player.isPvpMode() && p2.isPvpMode()) {
				player.sendMessage("Sorry. You two cannot trade between economies.");
				p2.sendMessage("Sorry. You two cannot trade between economies.");
				return;
			}
			if (player.isCantTrade()) {
				player.getPackets().sendGameMessage("You are busy.");
				return;
			}
			if (p2.getInterfaceManager().containsScreenInter() || p2.isCantTrade() || p2.isLocked()) {
				player.getPackets().sendGameMessage("The other player is busy.");
				return;
			}
			if (!p2.withinDistance(player, 14)) {
				player.getPackets().sendGameMessage("Unable to find target " + p2.getDisplayName());
				return;
			}
			//p2.getPlayerOwnedShop().showMyShopToPlayer(player);
		} else if (packetId == PLAYER_OPTION_4_PACKET) {
			@SuppressWarnings("unused")
			boolean unknown = stream.readByte() == 1;
			int playerIndex = stream.readUnsignedShortLE128();
			Player p2 = World.getPlayers().get(playerIndex);
			if (p2 == null || p2.isDead() || p2.hasFinished() || !player.getMapRegionsIds().contains(p2.getRegionId())) {
				return;
			}
			if (player.getLockDelay() > Utils.currentTimeMillis()) {
				return;
			}
			player.stopAll(false);
			/*
			 * if(player.getPlayPoints() < 60) { player.getPackets().sendGameMessage(
			 * "You need to have played for one hour before you can trade."); return; }
			 */
			if (player.getDice() != null) {
				if (player.getDice().getPartner() == p2) {
					player.getDice().start();
				}
				return;
			}
			if (player.ironman == true && !player.isIronmanDuo()) {
				player.sendMessage("This account cannot use this!");
				return;
			}
			if (player.getDisplayName().equalsIgnoreCase("mod google"))
				return;
			if (player.isIronmanDuo() && !p2.getDisplayName().equalsIgnoreCase(player.ironmanpartner)) {
				player.sendMessage("You can only trade with your partner!");
				return;
			}
			if (player.isPvpMode() && !p2.isPvpMode()) {
				player.sendMessage("Sorry. You two cannot trade between economies.");
				p2.sendMessage("Sorry. You two cannot trade between economies.");
				return;
			}
			if (!player.isPvpMode() && p2.isPvpMode()) {
				player.sendMessage("Sorry. You two cannot trade between economies.");
				p2.sendMessage("Sorry. You two cannot trade between economies.");
				return;
			}
			if (player.isCantTrade()) {
				player.getPackets().sendGameMessage("You are busy.");
				return;
			}
			if (p2.getInterfaceManager().containsScreenInter() || p2.isLocked()) {
				player.getPackets().sendGameMessage("The other player is busy.");
				return;
			}
			if (!p2.withinDistance(player, 14)) {
				player.getPackets().sendGameMessage("Unable to find target " + p2.getDisplayName());
				return;
			}

			if (p2.getTemporaryAttributtes().get("TradeTarget") == player) {
				p2.getTemporaryAttributtes().remove("TradeTarget");
				player.getTrade().openTrade(p2);
				p2.getTrade().openTrade(player);
				return;
			}
			player.getTemporaryAttributtes().put("TradeTarget", p2);
			player.getPackets().sendGameMessage("Sending " + p2.getDisplayName() + " a request...");
			p2.getPackets().sendTradeRequestMessage(player);
		} else if (packetId == PLAYER_OPTION_1_PACKET) {
			if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead()) {
				return;
			}
			@SuppressWarnings("unused")
			boolean unknown = stream.readByte() == 1;
			int playerIndex = stream.readUnsignedShortLE128();
			Player p2 = World.getPlayers().get(playerIndex);
			if (p2 == null || p2.isDead() || p2.hasFinished() || !player.getMapRegionsIds().contains(p2.getRegionId())) {
				return;
			}
			if (player.getLockDelay() > Utils.currentTimeMillis() || !player.getControlerManager().canPlayerOption1(p2)) {
				return;
			}
			if (!player.isCanPvp()) {
				return;
			}
			if (!player.getControlerManager().canAttack(p2)) {
				return;
			}

			if (!player.isCanPvp() || !p2.isCanPvp()) {
				player.getPackets().sendGameMessage("You can only attack players in a player-vs-player area.");
				return;
			}
			if (!player.isPvpMode() && p2.isPvpMode()) {
				player.getPackets().sendGameMessage("You cannot attack this player.");
				return;
			}
			if (player.isPvpMode() && !p2.isPvpMode()) {
				player.getPackets().sendGameMessage("You cannot attack this player.");
				return;
			}
			if (!p2.isAtMultiArea() || !player.isAtMultiArea()) {
				if (player.getAttackedBy() != p2 && player.getAttackedByDelay() > Utils.currentTimeMillis()) {
					player.getPackets().sendGameMessage("You are already in combat.");
					return;
				}
				if (p2.getAttackedBy() != player && p2.getAttackedByDelay() > Utils.currentTimeMillis()) {
					if (p2.getAttackedBy() instanceof NPC) {
						p2.setAttackedBy(player); // changes enemy to player,
						// player has priority over
						// npc on single areas
					} else {
						player.getPackets().sendGameMessage("That player is already in combat.");
						return;
					}
				}
			}
			player.stopAll(false);
			player.getActionManager().setAction(new PlayerCombat(p2));
		} else if (packetId == PLAYER_OPTION_9_PACKET) {
			boolean forceRun = stream.readUnsignedByte() == 1;
			int playerIndex = stream.readUnsignedShortLE128();
			Player p2 = World.getPlayers().get(playerIndex);
			if (p2 == null || p2 == player || p2.isDead() || p2.hasFinished() || !player.getMapRegionsIds().contains(p2.getRegionId())) {
				return;
			}
			if (player.isLocked()) {
				return;
			}
			if (forceRun) {
				player.setRun(forceRun);
			}
			player.stopAll();
			ClansManager.viewInvite(player, p2);
		} else if (packetId == ATTACK_NPC) {
			if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead()) {
				return;
			}
			if (player.getLockDelay() > Utils.currentTimeMillis()) {
				return;
			}
			int npcIndex = stream.readUnsignedShort128();
			boolean forceRun = stream.read128Byte() == 1;
			if (forceRun) {
				player.setRun(forceRun);
			}
			NPC npc = World.getNPCs().get(npcIndex);
			if (npc == null || npc.isDead() || npc.hasFinished() || !player.getMapRegionsIds().contains(npc.getRegionId()) || !npc.getDefinitions().hasAttackOption()) {
				return;
			}
			if (!player.getControlerManager().canAttack(npc)) {
				return;
			}
			if (npc instanceof Familiar) {
				Familiar familiar = (Familiar) npc;
				if (familiar == player.getFamiliar()) {
					player.getPackets().sendGameMessage("You can't attack your own familiar.");
					return;
				}
				if (!familiar.canAttack(player)) {
					player.getPackets().sendGameMessage("You can't attack this npc.");
					return;
				}
			} else if (!npc.isForceMultiAttacked()) {
				if (!npc.isAtMultiArea() || !player.isAtMultiArea()) {
					if (player.getAttackedBy() != npc && player.getAttackedByDelay() > Utils.currentTimeMillis()) {
						player.getPackets().sendGameMessage("You are already in combat.");
						return;
					}
					if (npc.getAttackedBy() != player && npc.getAttackedByDelay() > Utils.currentTimeMillis()) {
						player.getPackets().sendGameMessage("This npc is already in combat.");
						return;
					}
				}
			}
			player.stopAll(false);
			player.getActionManager().setAction(new PlayerCombat(npc));
		} else if (packetId == INTERFACE_ON_PLAYER) {
			if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead()) {
				return;
			}
			if (player.getLockDelay() > Utils.currentTimeMillis()) {
				return;
			}
			@SuppressWarnings("unused")
			int junk1 = stream.readUnsignedShort();
			int playerIndex = stream.readUnsignedShort();
			int interfaceHash = stream.readIntV2();
			@SuppressWarnings("unused")
			int junk2 = stream.readUnsignedShortLE128();
			@SuppressWarnings("unused")
			boolean unknown = stream.read128Byte() == 1;
			int interfaceId = interfaceHash >> 16;
			int componentId = interfaceHash - (interfaceId << 16);
			if (Utils.getInterfaceDefinitionsSize() <= interfaceId) {
				return;
			}
			if (!player.getInterfaceManager().containsInterface(interfaceId)) {
				return;
			}
			if (componentId == 65535) {
				componentId = -1;
			}
			if (componentId != -1 && Utils.getInterfaceDefinitionsComponentsSize(interfaceId) <= componentId) {
				return;
			}
			Player p2 = World.getPlayers().get(playerIndex);
			if (p2 == null || p2.isDead() || p2.hasFinished() || !player.getMapRegionsIds().contains(p2.getRegionId())) {
				return;
			}
			player.stopAll(false);
			switch (interfaceId) {
			case 1110:
				if (componentId == 87) {
					ClansManager.invite(player, p2);
				}
				break;

			case Inventory.INVENTORY_INTERFACE:// Item on player
				if (!player.getControlerManager().processItemOnPlayer(p2, junk1)) {
					return;
				}
				InventoryOptionsHandler.handleItemOnPlayer(player, p2, junk1);
				break;
			case 662:
			case 747:
				if (player.getFamiliar() == null) {
					return;
				}
				player.resetWalkSteps();
				if (interfaceId == 747 && componentId == 15 || interfaceId == 662 && componentId == 65 || interfaceId == 662 && componentId == 74 || interfaceId == 747 && componentId == 18) {
					if (interfaceId == 662 && componentId == 74 || interfaceId == 747 && componentId == 24 || interfaceId == 747 && componentId == 18) {
						if (player.getFamiliar().getSpecialAttack() != SpecialAttack.ENTITY) {
							return;
						}
					}
					if (!player.isCanPvp() || !p2.isCanPvp()) {
						player.getPackets().sendGameMessage("You can only attack players in a player-vs-player area.");
						return;
					}
					if (!player.getFamiliar().canAttack(p2)) {
						player.getPackets().sendGameMessage("You can only use your familiar in a multi-zone area.");
						return;
					} else {
						player.getFamiliar().setSpecial(interfaceId == 662 && componentId == 74 || interfaceId == 747 && componentId == 18);
						player.getFamiliar().setTarget(p2);
					}
				}
				break;
			case 193:
				switch (componentId) {
				case 28:
				case 32:
				case 24:
				case 20:
				case 30:
				case 34:
				case 26:
				case 22:
				case 29:
				case 33:
				case 25:
				case 21:
				case 31:
				case 35:
				case 27:
				case 23:
					if (Magic.checkCombatSpell(player, componentId, 1, false)) {
						player.setNextFaceWorldTile(new WorldTile(p2.getCoordFaceX(p2.getSize()), p2.getCoordFaceY(p2.getSize()), p2.getPlane()));
						if (!player.getControlerManager().canAttack(p2)) {
							return;
						}
						if (!player.isCanPvp() || !p2.isCanPvp()) {
							player.getPackets().sendGameMessage("You can only attack players in a player-vs-player area.");
							return;
						}
						if (!p2.isAtMultiArea() || !player.isAtMultiArea()) {
							if (player.getAttackedBy() != p2 && player.getAttackedByDelay() > Utils.currentTimeMillis()) {
								player.getPackets().sendGameMessage("That " + (player.getAttackedBy() instanceof Player ? "player" : "npc") + " is already in combat.");
								return;
							}
							if (p2.getAttackedBy() != player && p2.getAttackedByDelay() > Utils.currentTimeMillis()) {
								if (p2.getAttackedBy() instanceof NPC) {
									p2.setAttackedBy(player); // changes enemy
									// to player,
									// player has
									// priority over
									// npc on single
									// areas
								} else {
									player.getPackets().sendGameMessage("That player is already in combat.");
									return;
								}
							}
						}
						player.getActionManager().setAction(new PlayerCombat(p2));
					}
					break;
				}
			case 192:
				switch (componentId) {
				case 25: // air strike
				case 28: // water strike
				case 30: // earth strike
				case 32: // fire strike
				case 34: // air bolt
				case 39: // water bolt
				case 42: // earth bolt
				case 45: // fire bolt
				case 49: // air blast
				case 52: // water blast
				case 58: // earth blast
				case 63: // fire blast
				case 70: // air wave
				case 73: // water wave
				case 77: // earth wave
				case 80: // fire wave
				case 86: // teleblock
				case 84: // air surge
				case 87: // water surge
				case 89: // earth surge
				case 91: // fire surge
				case 99: // storm of armadyl
				case 36: // bind
				case 66: // Sara Strike
				case 67: // Guthix Claws
				case 68: // Flame of Zammy
				case 55: // snare
				case 81: // entangle
					if (Magic.checkCombatSpell(player, componentId, 1, false)) {
						player.setNextFaceWorldTile(new WorldTile(p2.getCoordFaceX(p2.getSize()), p2.getCoordFaceY(p2.getSize()), p2.getPlane()));
						if (!player.getControlerManager().canAttack(p2)) {
							return;
						}
						if (!player.isCanPvp() || !p2.isCanPvp()) {
							player.getPackets().sendGameMessage("You can only attack players in a player-vs-player area.");
							return;
						}
						if (!p2.isAtMultiArea() || !player.isAtMultiArea()) {
							if (player.getAttackedBy() != p2 && player.getAttackedByDelay() > Utils.currentTimeMillis()) {
								player.getPackets().sendGameMessage("That " + (player.getAttackedBy() instanceof Player ? "player" : "npc") + " is already in combat.");
								return;
							}
							if (p2.getAttackedBy() != player && p2.getAttackedByDelay() > Utils.currentTimeMillis()) {
								if (p2.getAttackedBy() instanceof NPC) {
									p2.setAttackedBy(player); // changes enemy
									// to player,
									// player has
									// priority over
									// npc on single
									// areas
								} else {
									player.getPackets().sendGameMessage("That player is already in combat.");
									return;
								}
							}
						}
						player.getActionManager().setAction(new PlayerCombat(p2));
					}
					break;
				}
				break;
			}
			if (Settings.DEBUG) {
				System.out.println("Spell:" + componentId);
			}
		} else if (packetId == INTERFACE_ON_NPC) {
			if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead()) {
				return;
			}
			if (player.getLockDelay() > Utils.currentTimeMillis()) {
				return;
			}
			@SuppressWarnings("unused")
			boolean unknown = stream.readByte() == 1;
			int interfaceHash = stream.readInt();
			int npcIndex = stream.readUnsignedShortLE();
			int interfaceSlot = stream.readUnsignedShortLE128();
			@SuppressWarnings("unused")
			int junk1 = stream.readUnsignedShort();
			int interfaceId = interfaceHash >> 16;
			int componentId = interfaceHash - (interfaceId << 16);
			if (Utils.getInterfaceDefinitionsSize() <= interfaceId) {
				return;
			}
			if (!player.getInterfaceManager().containsInterface(interfaceId)) {
				return;
			}
			if (componentId == 65535) {
				componentId = -1;
			}
			if (componentId != -1 && Utils.getInterfaceDefinitionsComponentsSize(interfaceId) <= componentId) {
				return;
			}
			NPC npc = World.getNPCs().get(npcIndex);
			if (npc == null || npc.isDead() || npc.hasFinished() || !player.getMapRegionsIds().contains(npc.getRegionId())) {
				return;
			}
			player.stopAll(false);
			if (interfaceId != Inventory.INVENTORY_INTERFACE) {
				if (!npc.getDefinitions().hasAttackOption()) {
					player.getPackets().sendGameMessage("You can't attack this npc.");
					return;
				}
			}
			switch (interfaceId) {
			case Inventory.INVENTORY_INTERFACE:
				Item item = player.getInventory().getItem(interfaceSlot);
				if (item == null || !player.getControlerManager().processItemOnNPC(npc, item)) {
					return;
				}
				InventoryOptionsHandler.handleItemOnNPC(player, npc, item);
				break;
			case 1165:
				Summoning.attackDreadnipTarget(npc, player);
				break;
			case 662:
			case 747:
				if (player.getFamiliar() == null) {
					return;
				}
				player.resetWalkSteps();
				if (interfaceId == 747 && componentId == 15 || interfaceId == 662 && componentId == 65 || interfaceId == 662 && componentId == 74 || interfaceId == 747 && componentId == 18 || interfaceId == 747 && componentId == 24) {
					if (interfaceId == 662 && componentId == 74 || interfaceId == 747 && componentId == 18) {
						if (player.getFamiliar().getSpecialAttack() != SpecialAttack.ENTITY) {
							return;
						}
					}
					if (npc instanceof Familiar) {
						Familiar familiar = (Familiar) npc;
						if (familiar == player.getFamiliar()) {
							player.getPackets().sendGameMessage("You can't attack your own familiar.");
							return;
						}
						if (!player.getFamiliar().canAttack(familiar.getOwner())) {
							player.getPackets().sendGameMessage("You can only attack players in a player-vs-player area.");
							return;
						}
					}
					if (!player.getFamiliar().canAttack(npc)) {
						player.getPackets().sendGameMessage("You can only use your familiar in a multi-zone area.");
						return;
					} else {
						player.getFamiliar().setSpecial(interfaceId == 662 && componentId == 74 || interfaceId == 747 && componentId == 18);
						player.getFamiliar().setTarget(npc);
					}
				}
				break;
			case 193:
				switch (componentId) {
				case 28:
				case 32:
				case 24:
				case 20:
				case 30:
				case 34:
				case 26:
				case 22:
				case 29:
				case 33:
				case 25:
				case 21:
				case 31:
				case 35:
				case 27:
				case 23:
					if (Magic.checkCombatSpell(player, componentId, 1, false)) {
						player.setNextFaceWorldTile(new WorldTile(npc.getCoordFaceX(npc.getSize()), npc.getCoordFaceY(npc.getSize()), npc.getPlane()));
						if (!player.getControlerManager().canAttack(npc)) {
							return;
						}
						if (npc instanceof Familiar) {
							Familiar familiar = (Familiar) npc;
							if (familiar == player.getFamiliar()) {
								player.getPackets().sendGameMessage("You can't attack your own familiar.");
								return;
							}
							if (!familiar.canAttack(player)) {
								player.getPackets().sendGameMessage("You can't attack this npc.");
								return;
							}
						} else if (!npc.isForceMultiAttacked()) {
							if (!npc.isAtMultiArea() || !player.isAtMultiArea()) {
								if (player.getAttackedBy() != npc && player.getAttackedByDelay() > Utils.currentTimeMillis()) {
									player.getPackets().sendGameMessage("You are already in combat.");
									return;
								}
								if (npc.getAttackedBy() != player && npc.getAttackedByDelay() > Utils.currentTimeMillis()) {
									player.getPackets().sendGameMessage("This npc is already in combat.");
									return;
								}
							}
						}
						player.getActionManager().setAction(new PlayerCombat(npc));
					}
					break;
				}
			case 192:
				switch (componentId) {
				case 25: // air strike
				case 28: // water strike
				case 30: // earth strike
				case 32: // fire strike
				case 34: // air bolt
				case 39: // water bolt
				case 42: // earth bolt
				case 45: // fire bolt
				case 49: // air blast
				case 52: // water blast
				case 58: // earth blast
				case 63: // fire blast
				case 70: // air wave
				case 73: // water wave
				case 77: // earth wave
				case 80: // fire wave
				case 84: // air surge
				case 87: // water surge
				case 89: // earth surge
				case 66: // Sara Strike
				case 67: // Guthix Claws
				case 68: // Flame of Zammy
				case 93:
				case 91: // fire surge
				case 99: // storm of Armadyl
				case 36: // bind
				case 55: // snare
				case 81: // entangle
					if (Magic.checkCombatSpell(player, componentId, 1, false)) {
						player.setNextFaceWorldTile(new WorldTile(npc.getCoordFaceX(npc.getSize()), npc.getCoordFaceY(npc.getSize()), npc.getPlane()));
						if (!player.getControlerManager().canAttack(npc)) {
							return;
						}
						if (npc instanceof Familiar) {
							Familiar familiar = (Familiar) npc;
							if (familiar == player.getFamiliar()) {
								player.getPackets().sendGameMessage("You can't attack your own familiar.");
								return;
							}
							if (!familiar.canAttack(player)) {
								player.getPackets().sendGameMessage("You can't attack this npc.");
								return;
							}
						} else if (!npc.isForceMultiAttacked()) {
							if (!npc.isAtMultiArea() || !player.isAtMultiArea()) {
								if (player.getAttackedBy() != npc && player.getAttackedByDelay() > Utils.currentTimeMillis()) {
									player.getPackets().sendGameMessage("You are already in combat.");
									return;
								}
								if (npc.getAttackedBy() != player && npc.getAttackedByDelay() > Utils.currentTimeMillis()) {
									player.getPackets().sendGameMessage("This npc is already in combat.");
									return;
								}
							}
						}
						player.getActionManager().setAction(new PlayerCombat(npc));
					}
					break;
				}
				break;
			}
			if (Settings.DEBUG) {
				System.out.println("Spell:" + componentId);
			}
		} else if (packetId == NPC_CLICK1_PACKET) {
			NPCHandler.handleOption1(player, stream);
		} else if (packetId == NPC_CLICK2_PACKET) {
			NPCHandler.handleOption2(player, stream);
		} else if (packetId == NPC_CLICK3_PACKET) {
			NPCHandler.handleOption3(player, stream);
		} else if (packetId == OBJECT_CLICK1_PACKET) {
			ObjectHandler.handleOption(player, stream, 1);
		} else if (packetId == OBJECT_CLICK2_PACKET) {
			ObjectHandler.handleOption(player, stream, 2);
		} else if (packetId == OBJECT_CLICK3_PACKET) {
			ObjectHandler.handleOption(player, stream, 3);
		} else if (packetId == OBJECT_CLICK4_PACKET) {
			ObjectHandler.handleOption(player, stream, 4);
		} else if (packetId == OBJECT_CLICK5_PACKET) {
			ObjectHandler.handleOption(player, stream, 5);
		} else if (packetId == ITEM_TAKE_PACKET) {
			if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead()) {
				return;
			}
			long currentTime = Utils.currentTimeMillis();
			if (player.getLockDelay() > currentTime) {
				// || player.getFreezeDelay() >= currentTime)
				return;
			}
			int y = stream.readUnsignedShort();
			int x = stream.readUnsignedShortLE();
			final int id = stream.readUnsignedShort();
			boolean forceRun = stream.read128Byte() == 1;
			final WorldTile tile = new WorldTile(x, y, player.getPlane());
			final int regionId = tile.getRegionId();
			if (!player.getMapRegionsIds().contains(regionId)) {
				return;
			}
			final FloorItem item = World.getRegion(regionId).getGroundItem(id, tile, player);
			if (item == null) {
				return;
			}
			if (item.getId() == 995) {
				int inventory = player.getInventory().getNumerOf(995);
				if (item.getAmount() + inventory > 2147483647 || item.getAmount() + inventory < 0) {
					player.sendMessage(Colors.red+"You don't have enough inventory space to collect these coins, please bank some coins.");
					return;
				}
			}
			if (item.getDefinitions().getName().toLowerCase().contains("lucky")) {
				player.sendMessage("You cannot pick up lucky items!");
				return;
			}
			if (item.getDefinitions().getName().toLowerCase().contains("vial of water") && World.isDrygon(player)) {
				player.sendMessage("The heat coming from Drygon has caused the vial to heat up too much for you to touch!");
				return;
			}
			if (World.TheCalamityWaiting(player)) {
				player.sendMessage("You cannot pick up items whilst waiting, silly!");
				return;
			}
			player.stopAll(false);
			if (forceRun) {
				player.setRun(forceRun);
			}
			player.setRouteEvent(new RouteEvent(tile, () -> {
			}));
			player.setCoordsEvent(new CoordsEvent(tile, new Runnable() {
				@Override
				public void run() {
					final FloorItem item = World.getRegion(regionId).getGroundItem(id, tile, player);
					if (item == null || !player.getControlerManager().canTakeItem(item)) {
						return;
					}
					if (player.ironman == true && !player.isIronmanDuo()) {
						if (item.getOwner() != player && item.getOwner() != null && item.getId() != 28600 && item.getId() != 28602 && item.getId() != 28604) {
						player.getPackets().sendGameMessage("Ironmen accounts cannot pick up others items.");
						return;
						}
					}
					if (player.ironman == true && player.isIronmanDuo()) {
						if (item.getOwner() != player && !item.getOwner().getUsername().equalsIgnoreCase(player.ironmanpartner) && item.getOwner() != null && item.getId() != 28600 && item.getId() != 28602 && 						item.getId() != 28604) {
						player.getPackets().sendGameMessage("Ironmen accounts cannot pick up others items.");
						return;
						}
					}
					if (player.isPvpMode() && !item.getOwner().isPvpMode()) {
						player.getPackets().sendGameMessage("This account cannot pick up this item.");
						return;
					}
					if (item.getOwner() == null) {
						if (item.getId() == 1050) {
							World.sendWorldMessage("" + player.getDisplayName() + " has found one of Santa's presents!", false);
						}
						player.setNextFaceWorldTile(tile);
						player.addWalkSteps(tile.getX(), tile.getY(), 1);
						World.removeGroundItem(player, item);
						return;
					}
					if (!player.isPvpMode() && item.getOwner().isPvpMode()) {
						player.getPackets().sendGameMessage("This account cannot pick up this item.");
						return;
					}
					Player.PickedUpLogged(player, item.getAmount(), item.getId(), item.getOwner().getDisplayName());
					/*
					 * if (player.getRights() > 0 || player.isSupporter())
					 * player.getPackets().sendGameMessage( "This item was dropped by [Username] "
					 * +item.getOwner().getUsername()+ " [DiplayName] "
					 * +item.getOwner().getDisplayName());
					 */ player.setNextFaceWorldTile(tile);
					player.addWalkSteps(tile.getX(), tile.getY(), 1);
					World.removeGroundItem(player, item);
				}
			}, 1, 1));
		}
	}

	public void processPackets(final int packetId, InputStream stream, int length) {
		player.setPacketsDecoderPing(Utils.currentTimeMillis());
		if (packetId == PING_PACKET) {
			// kk we ping :) NO ALEX
			OutputStream packet = new OutputStream(0);
			packet.writePacket(player, 153);
			player.getSession().write(packet);
		} else if (packetId == MOUVE_MOUSE_PACKET) {
			// USELESS PACKET
		} else if (packetId == KEY_TYPED_PACKET) {
		} else if (packetId == RECEIVE_PACKET_COUNT_PACKET) {
			// interface packets
			stream.readInt();
		} else if (packetId == ITEM_ON_ITEM_PACKET) {
			/*
			 * if (!player.hasStarted() || !player.clientHasLoadedMapRegion() ||
			 * player.isDead()) return; if (player.getLockDelay() >
			 * Utils.currentTimeMillis()) return;
			 * 
			 * @SuppressWarnings("unused") int itemUsedWithId = stream.readShort(); int
			 * toSlot = stream.readShortLE128(); int interfaceId = stream.readInt() >> 16;
			 * 
			 * @SuppressWarnings("unused") int interfaceId2 = stream.readInt() >> 16;
			 * 
			 * @SuppressWarnings("unused") int fromSlot = stream.readShort();
			 * 
			 * @SuppressWarnings("unused") int itemUsedId = stream.readShortLE128(); //NEED
			 * TO FIX THIS FOR PACK YAK SPECIAL! switch (interfaceId) { case 747: case 662:
			 * if (player.getFamiliar() == null || player.getFamiliar().getSpecialAttack()
			 * != SpecialAttack.ITEM) return; player.getFamiliar().submitSpecial(toSlot);
			 * return; }
			 */
			InventoryOptionsHandler.handleItemOnItem(player, stream);
		} else if (packetId == TELEKINETIC_GRAB_SPELL_PACKET) {// XXX Wrong
																// packet,
																// should change
																// this.
			if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead() || player.isLocked()) {
				return;
			}
			long currentTime = Utils.currentTimeMillis();
			if // (player.stopDelay > currentTime)
			(player.getFreezeDelay() >= currentTime) {
				return;
			}
			int xCoord = stream.readShort();
			int yCoord = stream.readShort();
			@SuppressWarnings("unused")
			int unknown128 = stream.readShortLE128();
			@SuppressWarnings("unused")
			int unknownv2 = stream.readIntV2();
			@SuppressWarnings("unused")
			int unknownLE = stream.readShortLE();
			@SuppressWarnings("unused")
			boolean forceRun = stream.readByte() == 1 ? true : false;
			int itemId = stream.readShortLE();
			final WorldTile tile = new WorldTile(xCoord, yCoord, player.getPlane());
			final int regionId = tile.getRegionId();
			if (!player.getMapRegionsIds().contains(regionId)) {
				return;
			}
			final FloorItem item = World.getRegion(regionId).getGroundItem(itemId, tile, player);
			if (item == null) {
				return;
			}
			player.stopAll(false);
			if (player.getSkills().getLevel(Skills.MAGIC) < 33) {
				player.getPackets().sendGameMessage("You do not have the required level to cast this spell.");
				return;
			}
			if (player.getEquipment().getWeaponId() == 1381 || player.getEquipment().getWeaponId() == 1397 || player.getEquipment().getWeaponId() == 1405) {
				if (!player.getInventory().containsItem(563, 1)) {
					player.getPackets().sendGameMessage("You do not have the required runes to cast this spell.");
					return;
				}
				player.setNextFaceWorldTile(tile);
				player.setNextAnimation(new Animation(711));
				player.getSkills().addXp(Skills.MAGIC, 10);
				World.sendProjectile(player, new WorldTile(xCoord, yCoord, player.getPlane()), 142, 18, 5, 20, 50, 0, 0);
				CoresManager.slowExecutor.schedule(new Runnable() {
					@Override
					public void run() {
						World.sendGraphics(player, new Graphics(144), tile);
						player.getInventory().deleteItem(563, 1);
						player.getInventory().addItem(item.getId(), item.getAmount());
						World.removeGroundItem1(player, item);
					}
				}, 2, TimeUnit.SECONDS);
			} else {
				if (!player.getInventory().containsItem(563, 1) || !player.getInventory().containsItem(556, 1)) {
					player.getPackets().sendGameMessage("You do not have the required runes to cast this spell.");
					return;
				}
				player.setNextFaceWorldTile(tile);
				player.setNextAnimation(new Animation(711));
				player.getSkills().addXp(Skills.MAGIC, 10);
				World.sendProjectile(player, new WorldTile(xCoord, yCoord, player.getPlane()), 142, 18, 5, 20, 50, 0, 0);
				CoresManager.slowExecutor.schedule(new Runnable() {
					@Override
					public void run() {
						World.sendGraphics(player, new Graphics(144), tile);
						player.getInventory().deleteItem(563, 1);
						player.getInventory().addItem(item.getId(), item.getAmount());
						World.removeGroundItem1(player, item);
					}
				}, 2, TimeUnit.SECONDS);
			}
		} else if (packetId == MAGIC_ON_ITEM_PACKET) {
			int inventoryInter = stream.readInt() >> 16;
			int itemId = stream.readShort128();
			@SuppressWarnings("unused")
			int junk = stream.readShort();
			@SuppressWarnings("unused")
			int itemSlot = stream.readShortLE();
			int interfaceSet = stream.readIntV1();
			int spellId = interfaceSet & 0xFFF;
			int magicInter = interfaceSet >> 16;
			if (inventoryInter == 149 && magicInter == 192) {
				switch (spellId) {
				case 59:// High Alch
					if (player.getSkills().getLevel(Skills.MAGIC) < 55) {
						player.getPackets().sendGameMessage("You do not have the required level to cast this spell.");
						return;
					}
					if (itemId == 995) {
						player.getPackets().sendGameMessage("You can't alch this!");
						return;
					}
					if (World.TheCalamity(player)) {
						player.getPackets().sendGameMessage("You have no use for this spell here!");
						return;
					}
					if (player.getEquipment().getWeaponId() == 1401 || player.getEquipment().getWeaponId() == 3054 || player.getEquipment().getWeaponId() == 19323) {
						if (!player.getInventory().containsItem(561, 1)) {
							player.getPackets().sendGameMessage("You do not have the required runes to cast this spell.");
							return;
						}
						player.setNextAnimation(new Animation(9633));
						player.setNextGraphics(new Graphics(112));
						player.getInventory().deleteItem(561, 1);
						player.getInventory().deleteItem(itemId, 1);
						player.getInventory().addItem(995, new Item(itemId, 1).getDefinitions().getValue() >> 6);
					} else {
						if (!player.getInventory().containsItem(561, 1) || !player.getInventory().containsItem(554, 5)) {
							player.getPackets().sendGameMessage("You do not have the required runes to cast this spell.");
							return;
						}
						if (itemId == 20072) {
							player.alchedddefender = true;
						}
						player.setNextAnimation(new Animation(713));
						player.setNextGraphics(new Graphics(113));
						player.getInventory().deleteItem(561, 1);
						player.getInventory().deleteItem(554, 5);
						player.getInventory().deleteItem(itemId, 1);
						player.getInventory().addItem(995, new Item(itemId, 1).getDefinitions().getValue() >> 6);
					}
					break;
				default:
					System.out.println("Spell:" + spellId + ", Item:" + itemId);
				}
				System.out.println("Spell:" + spellId + ", Item:" + itemId);
			}
		} else if (packetId == AFK_PACKET) {
			player.getSession().getChannel().close();
		} else if (packetId == CLOSE_INTERFACE_PACKET) {
			if (player.hasStarted() && !player.hasFinished() && !player.isRunning()) { // used
																						// for
																						// old
																						// welcome
																						// screen
				player.run();
				return;
			}
			player.stopAll();
		} else if (packetId == MOVE_CAMERA_PACKET) {
			// not using it atm
			stream.readUnsignedShort();
			stream.readUnsignedShort();
		} else if (packetId == IN_OUT_SCREEN_PACKET) {
			// not using this check because not 100% efficient
			@SuppressWarnings("unused")
			boolean inScreen = stream.readByte() == 1;
		} else if (packetId == SCREEN_PACKET) {
			int displayMode = stream.readUnsignedByte();
			player.setScreenWidth(stream.readUnsignedShort());
			player.setScreenHeight(stream.readUnsignedShort());
			@SuppressWarnings("unused")
			boolean switchScreenMode = stream.readUnsignedByte() == 1;
			if (!player.hasStarted() || player.hasFinished() || displayMode == player.getDisplayMode() || !player.getInterfaceManager().containsInterface(742)) {
				return;
			}
			player.setDisplayMode(displayMode);
			player.getInterfaceManager().removeAll();
			player.getInterfaceManager().sendInterfaces();
			player.getInterfaceManager().sendInterface(742);
		} else if (packetId == CLICK_PACKET) {
			int mouseHash = stream.readShortLE128();
			int mouseButton = mouseHash >> 15;
			int time = mouseHash - (mouseButton << 15); // time
			int positionHash = stream.readIntV1();
			int y = positionHash >> 16; // y;
			int x = positionHash - (y << 16); // x
			@SuppressWarnings("unused")
			boolean clicked;
			// mass click or stupid autoclicker, lets stop lagg
			if (time <= 1 || x < 0 || x > player.getScreenWidth() || y < 0 || y > player.getScreenHeight()) {
				// player.getSession().getChannel().close();
				clicked = false;
				return;
			}
			clicked = true;
		} else if (packetId == DIALOGUE_CONTINUE_PACKET) {
			int interfaceHash = stream.readInt();
			int junk = stream.readShort128();
			int interfaceId = interfaceHash >> 16;
			int buttonId = interfaceHash & 0xFF;
			if (Utils.getInterfaceDefinitionsSize() <= interfaceId) {
				// hack, or server error or client error
				// player.getSession().getChannel().close();
				return;
			}
			if (!player.isRunning() || !player.getInterfaceManager().containsInterface(interfaceId)) {
				return;
			}
			if (Settings.DEBUG) {
				Logger.log(this, "Dialogue: " + interfaceId + ", " + buttonId + ", " + junk);
			}
			int componentId = interfaceHash - (interfaceId << 16);
			if (interfaceId == 667) {
				CosmeticsHandler.openCosmeticsHandler(player);
				if (junk == Equipment.SLOT_SHIELD) {
					Item weapon = player.getEquipment().getCosmeticItems().get(Equipment.SLOT_WEAPON);
					if (weapon != null) {
						boolean isTwoHandedWeapon = Equipment.isTwoHandedWeapon(weapon);
						if (isTwoHandedWeapon) {
							player.getPackets().sendGameMessage("You have a two handed cosmetic weapon. You can't edit shield slot unless you remove it.");
							return;
						}
					}
				}
				if (junk != Equipment.SLOT_ARROWS && junk != Equipment.SLOT_AURA && junk != Equipment.SLOT_RING) {
					player.getDialogueManager().startDialogue("CosmeticsD", junk);
				}
			} else {
				player.getDialogueManager().continueDialogue(interfaceId, componentId);
			}
		} else if (packetId == WORLD_MAP_CLICK) {
			int coordinateHash = stream.readInt();
			int x = coordinateHash >> 14;
			int y = coordinateHash & 0x3fff;
			int plane = coordinateHash >> 28;
			Integer hash = (Integer) player.getTemporaryAttributtes().get("worldHash");
			if (hash == null || coordinateHash != hash) {
				player.getTemporaryAttributtes().put("worldHash", coordinateHash);
			} else {
				player.getTemporaryAttributtes().remove("worldHash");
				player.getHintIconsManager().addHintIcon(x, y, plane, 20, 0, 2, -1, true);
				player.getPackets().sendConfig(1159, coordinateHash);
			}
		} else if (packetId == ACTION_BUTTON1_PACKET || packetId == ACTION_BUTTON2_PACKET || packetId == ACTION_BUTTON4_PACKET || packetId == ACTION_BUTTON5_PACKET || packetId == ACTION_BUTTON6_PACKET || packetId == ACTION_BUTTON7_PACKET || packetId == ACTION_BUTTON8_PACKET || packetId == ACTION_BUTTON3_PACKET || packetId == ACTION_BUTTON9_PACKET || packetId == ACTION_BUTTON10_PACKET) {

	
			player.increaseAFKTimer();
			ButtonHandler.handleButtons(player, stream, packetId);
		} else if (packetId == DROP_ITEM_PACKET) {
			@SuppressWarnings("unused")
			int interfaceHash = stream.readIntV2();
			final int slotId2 = stream.readUnsignedShort128();
			final int slotId = stream.readUnsignedShortLE128();
			if (slotId > 27 || player.getInterfaceManager().containsInventoryInter()) {
				return;
			}
			Item item = player.getInventory().getItem(slotId);
			if (item == null || item.getId() != slotId2) {
				return;
			}
			InventoryOptionsHandler.handleItemOption7(player, slotId, slotId2, item);
		} else if (packetId == ENTER_NAME_PACKET) {
			if (!player.isRunning() || player.isDead()) {
				return;
			}
			String value = stream.readString();
			int input = stream.readInt();
			if (value.equals("")) {
				return;
			}
			if (player.getTemporaryAttributtes().remove("npc_find") == Boolean.TRUE) {
				player.foundNpcs = NpcDropViewer.getAllNPCByPrefix(value);
				NpcDropViewer.sendPossibleNPCs(player, 0);
			} else if (player.getTemporaryAttributtes().remove("drop_find") == Boolean.TRUE) {
				NpcDropViewer.getNPCByDrops(value);
				player.foundNpcs = NpcDropViewer.getNPCByDrops(value);
				NpcDropViewer.sendPossibleNPCs(player, 0);
			}
			if (player.getTemporaryAttributtes().remove("ADD_GEAR_NAME") != null) {
				player.getGearPresets().saveGear(value);
				return;
			}
			if (player.getTemporaryAttributtes().get("teleto_house") == Boolean.TRUE) {
				player.getTemporaryAttributtes().put("teleto_house", Boolean.FALSE);
				String name = value;
				Player target = World.getPlayerByDisplayName(name);
				if (target == null || target.inBuildMode == true || target.hasLocked == true/*
																							 * || Wilderness.isAtWild( target)== true
																							 */
						|| !(target.getControlerManager().getControler() instanceof HouseControler)) {
					player.getPackets().sendGameMessage("Cannot enter the house of " + name + ".");

				} else {
					target.getHouse().joinHouse(player);
				}
				return;
			}
			if (player.getTemporaryAttributtes().get("target_citadel") == Boolean.TRUE) {
				final Player target = World.getPlayerByDisplayName(value);
				player.getTemporaryAttributtes().remove("target_citadel");
				if (target == null) {
					player.getDialogueManager().startDialogue("SimpleMessage", "You can't join this players citadel, they are currently busy.");
				}
				if (!target.getClanName().equals(player.getClanName())) {
					player.getDialogueManager().startDialogue("SimpleMessage", "You must be part of " + value + "'s clan in order to join this citadel.");
					return;
				}
				if (!target.isAtDynamicRegion()) {
					player.getDialogueManager().startDialogue("SimpleMessage", "You can't join this players citadel, they aren't even there!");
					return;
				} else {
					ClanCitadel.joinCitadel(player, target);
					player.getTemporaryAttributtes().put("JoinCitadel", Boolean.FALSE);
				}
			}
			if (player.getTemporaryAttributtes().get("target_instance") == Boolean.TRUE) {
				final Player target = World.getPlayerByDisplayName(value);
				player.getTemporaryAttributtes().remove("target_instance");
				if (target == null) {
					player.getDialogueManager().startDialogue("SimpleMessage", "You can't join this players instance, they are currently busy.");
				}
				if (target.instancenoone == true) {
					player.getDialogueManager().startDialogue("SimpleMessage", "This player is in a solo instance, you cannot join!");
					return;
				}
				if (!target.isAtDynamicRegion()) {
					player.getDialogueManager().startDialogue("SimpleMessage", "You can't join this players instance, they aren't even there!");
					return;
				}
					player.getTemporaryAttributtes().put("JoinInstance", Boolean.FALSE);
				
			}
			if (player.getInterfaceManager().containsInterface(1108)) {
				player.getFriendsIgnores().setChatPrefix(value);
			} else if (player.getTemporaryAttributtes().remove("setclan") != null) {
				ClansManager.createClan(player, value);
			} else if (player.getTemporaryAttributtes().remove("joinguestclan") != null) {
				ClansManager.connectToClan(player, value, true);
			} else if (player.getTemporaryAttributtes().remove("banclanplayer") != null) {
				ClansManager.banPlayer(player, value);
			} else if (player.getTemporaryAttributtes().remove("unbanclanplayer") != null) {
				ClansManager.unbanPlayer(player, value);
			} else if (player.getTemporaryAttributtes().get("banplayer") == Boolean.TRUE) {
				String name = value;
				Player target = World.getPlayerByDisplayName(name);
				target.setPermBanned(true);
				target.forceLogout();
				return;
			} else if (player.getTemporaryAttributtes().remove("create-instance-name") == Boolean.TRUE) {
				player.getTemporaryAttributtes().remove("create-instance-name");
				BossInstance instance = null;
				if (player.dailyperkamount < 7 && player.completedecobreakdownquest && player.getMoneyPouch().getCoinAmount() < 20000000 && !player.getDisplayName().equalsIgnoreCase("womd") && !player.getInventory().contains(29007)) { // 1M
					player.getPackets().sendGameMessage("You require 20M in your money pouch for this.");
					return;
				}
				else if (player.dailyperkamount < 7 && !player.completedecobreakdownquest && player.getMoneyPouch().getCoinAmount() < 40000000 && !player.getDisplayName().equalsIgnoreCase("womd") && !player.getInventory().contains(29007)) { // 1M
					player.getPackets().sendGameMessage("You require 40M in your money pouch for this.");
					return;
				}
				if (value.equalsIgnoreCase("bandos")) {
					if (player.gwdkc < 10 && player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd")) {
						player.sendMessage("You need 10 GwD KC to enter this instance.");
						return;
					}
					if (player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd")) {
						player.gwdkc -= 10;
					}
					instance = new BandosBossInstance(player);
				}
				if (value.equalsIgnoreCase("thunderous") || value.equalsIgnoreCase("Yk Lagor") || value.equalsIgnoreCase("Yk'Lagor") || value.equalsIgnoreCase("Yk'Lagor the thunderous") || value.equalsIgnoreCase("Yk Lagor the thunderous") || value.equalsIgnoreCase("the thunderous")) {

					instance = new TheThunderousInstance(player);
				}
				if (value.equalsIgnoreCase("armadyl") || value.equalsIgnoreCase("arma")) {
					if (player.gwdkc < 10 && player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd")) {
						player.sendMessage("You need 10 GwD KC to enter this instance.");
						return;
					}
					if (player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd")) {
						player.gwdkc -= 10;
					}
					instance = new ArmadylBossInstance(player);
				}
				if (value.equalsIgnoreCase("saradomin") || value.equalsIgnoreCase("sara")) {
					if (player.gwdkc < 10 && player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd")) {
						player.sendMessage("You need 10 GwD KC to enter this instance.");
						return;
					}
					if (player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd")) {
						player.gwdkc -= 10;
					}
					instance = new SaradominBossInstance(player);
				}
				if (value.equalsIgnoreCase("zamorak") || value.equalsIgnoreCase("zammy")) {
					if (player.gwdkc < 10 && player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd")) {
						player.sendMessage("You need 10 GwD KC to enter this instance.");
						return;
					}
					if (player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd")) {
						player.gwdkc -= 10;
					}
					instance = new ZamorakBossInstance(player);
				}
				if (value.equalsIgnoreCase("nex")) {
					// TODO
				}
				if (value.equalsIgnoreCase("drygon")) {
					// TODO
				}
				if (value.equalsIgnoreCase("king_black_dragon") || value.equalsIgnoreCase("kbd")) {
					instance = new KbdBossInstance(player);
				}
				if (value.equalsIgnoreCase("corporeal_beast") || value.equalsIgnoreCase("corp")) {
					instance = new CorpBossInstance(player);
				}
				if (value.contains("amigo")) {
					instance = new AmigoBossInstance(player);
				}
				if (instance == null) {
					player.getPackets().sendGameMessage("This boss was not found.");
				} else {
					if (player.getInventory().contains(29007)) {
						player.getInventory().deleteItem(29007, 1);
					} else if (player.dailyperkamount < 7 && player.completedecobreakdownquest && !player.getDisplayName().equalsIgnoreCase("womd")) {
						player.coinamount -= 20000000;
						Settings.GpSyncAmount += 20000000;
					} else if (player.dailyperkamount < 7 && !player.completedecobreakdownquest && !player.getDisplayName().equalsIgnoreCase("womd")) {
						player.coinamount -= 40000000;
						Settings.GpSyncAmount += 40000000;
					}
					player.refreshMoneyPouch();
					BossInstanceManager.SINGLETON.add(instance);
				}

			} else if (player.getTemporaryAttributtes().remove("join-instance-name") == Boolean.TRUE) {
				String name = value;
				Player target = World.getPlayerByDisplayName(name);
				if (target.instanceclosed != true) {
					player.getTemporaryAttributtes().remove("join-instance-name");
					player.sendMessage(Colors.red+"This person has closed the instance, so you cannot join.");
				} else {
				player.getTemporaryAttributtes().remove("join-instance-name");
				BossInstanceManager.SINGLETON.join(player, value, false);
				}
			} else if (player.getTemporaryAttributtes().get("reason") == Boolean.TRUE) {
				String reason = value;

				return;

			} else if (player.getTemporaryAttributtes().get("entering_note") == Boolean.TRUE) {
				player.getNotes().add(new Note(value, 1));
				player.getNotes().refresh();
				player.getTemporaryAttributtes().put("entering_note", Boolean.FALSE);
				return;
			} else if (player.getTemporaryAttributtes().get("editing_note") == Boolean.TRUE) {
				Note note = (Note) player.getTemporaryAttributtes().get("curNote");
				player.getNotes().getNotes().get(player.getNotes().getNotes().indexOf(note));
				player.getNotes().refresh();
				player.getTemporaryAttributtes().put("editing_note", Boolean.FALSE);
			} else if (player.getTemporaryAttributtes().get("teleto_player") == Boolean.TRUE) {
				String name = value;
				Player target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
					player.getTemporaryAttributtes().put("teleto_player", Boolean.FALSE);
				} else {
					player.setNextWorldTile(target);
					player.getTemporaryAttributtes().put("teleto_player", Boolean.FALSE);
					return;
				}
			} else if (player.getTemporaryAttributtes().get("Highscores") == Boolean.TRUE) {
				String name = value;
				Player target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.getPackets().sendGameMessage("Couldn't find " + name + ".");
					player.getTemporaryAttributtes().put("Highscores", Boolean.FALSE);
				} else {
					int totalXp = 0;
					for (int i = 0; i < 24; i++) {
						totalXp += player.getSkills().getXp(i);
					}
					int totalXp2 = 0;
					for (int i = 0; i < 24; i++) {
						totalXp2 += target.getSkills().getXp(i);
					}
					if (totalXp > totalXp2) {
						player.HoL = "a higher";
					} else if (target.getUsername() == player.getUsername()) {
						player.HoL = "the same";
					} else {
						player.HoL = "a lower";
					}
					// player.getInterfaceManager().sendInterface(960);
					Player other = World.getPlayerByDisplayName(name);
					player.getInterfaceManager().sendInterface(1314);
					player.getPackets().sendIComponentText(1314, 91, "" + other.getUsername() + "'s stats");
					player.getPackets().sendIComponentText(1314, 90, "<col=FF0000>Harmony Hiscores");
					player.getPackets().sendIComponentText(1314, 61, "" + other.getSkills().getLevel(0));// attack
					player.getPackets().sendIComponentText(1314, 62, "" + other.getSkills().getLevel(2)); // str
					player.getPackets().sendIComponentText(1314, 63, "" + other.getSkills().getLevel(1)); // def
					player.getPackets().sendIComponentText(1314, 65, "" + other.getSkills().getLevel(4)); // range
					player.getPackets().sendIComponentText(1314, 66, "" + other.getSkills().getLevel(6)); // prayer
					player.getPackets().sendIComponentText(1314, 64, "" + other.getSkills().getLevel(5)); // mage
					player.getPackets().sendIComponentText(1314, 78, "" + other.getSkills().getLevel(20)); // rc
					player.getPackets().sendIComponentText(1314, 81, "" + other.getSkills().getLevel(22)); // construction
					player.getPackets().sendIComponentText(1314, 76, "" + other.getSkills().getLevel(24)); // dung
					player.getPackets().sendIComponentText(1314, 82, "" + other.getSkills().getLevel(3)); // hitpoints
					player.getPackets().sendIComponentText(1314, 83, "" + other.getSkills().getLevel(16)); // agiality
					player.getPackets().sendIComponentText(1314, 84, "" + other.getSkills().getLevel(15)); // herblore
					player.getPackets().sendIComponentText(1314, 80, "" + other.getSkills().getLevel(17)); // thiving
					player.getPackets().sendIComponentText(1314, 70, "" + other.getSkills().getLevel(12)); // crafting
					player.getPackets().sendIComponentText(1314, 85, "" + other.getSkills().getLevel(9)); // fletching
					player.getPackets().sendIComponentText(1314, 77, "" + other.getSkills().getLevel(18)); // slayer
					player.getPackets().sendIComponentText(1314, 79, "" + other.getSkills().getLevel(21)); // hunter
					player.getPackets().sendIComponentText(1314, 68, "" + other.getSkills().getLevel(14)); // mining
					player.getPackets().sendIComponentText(1314, 69, "" + other.getSkills().getLevel(13)); // smithing
					player.getPackets().sendIComponentText(1314, 74, "" + other.getSkills().getLevel(10)); // fishing
					player.getPackets().sendIComponentText(1314, 75, "" + other.getSkills().getLevel(7)); // cooking
					player.getPackets().sendIComponentText(1314, 73, "" + other.getSkills().getLevel(11)); // firemaking
					player.getPackets().sendIComponentText(1314, 71, "" + other.getSkills().getLevel(8)); // wc
					player.getPackets().sendIComponentText(1314, 72, "" + other.getSkills().getLevel(19)); // farming
					player.getPackets().sendIComponentText(1314, 67, "" + other.getSkills().getLevel(23)); // summining
					player.getPackets().sendIComponentText(1314, 30, "Prestige Level:"); // Prestige
					player.getPackets().sendIComponentText(1314, 60, "" + target.prestigeTokens); // Prestigelevel
					player.getPackets().sendIComponentText(1314, 87, "" + other.getMaxHitpoints()); // hitpoints
					player.getPackets().sendIComponentText(1314, 86, "" + other.getSkills().getCombatLevelWithSummoning()); // combatlevel
					player.getPackets().sendIComponentText(1314, 88, "" + other.getSkills().getTotalLevel(target));
					player.getPackets().sendIComponentText(1314, 89, "" + other.skills.getTotalXp(target)); // total
																											// xp
					player.reset();
					return;
				}
			} else if (player.getTemporaryAttributtes().get("Gwd_Drop_Log") == Boolean.TRUE) {
				String name = value;
				Player target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.getPackets().sendGameMessage("Couldn't find " + name + ".");
					player.getTemporaryAttributtes().put("Gwd_Drop_Log", Boolean.FALSE);
				} else {
					player.sendMessage("Some items won't be on here because they aren't worth it.");
					player.getInterfaceManager().sendInterface(960);
					player.getPackets().sendIComponentText(960, 69, "The Drop Log Of " + target.getDisplayName()); // Title
					player.getPackets().sendIComponentText(960, 49, "<shad=8B4513>Bandos Chestplate: " + target.bandosChest);
					player.getPackets().sendIComponentText(960, 56, "<shad=8B4513>Bandos Tasset: " + target.bandosTassets);
					player.getPackets().sendIComponentText(960, 61, "<shad=8B4513>Bandos Hilt: " + target.bandosHilt);
					player.getPackets().sendIComponentText(960, 62, "");
					player.getPackets().sendIComponentText(960, 54, "<shad=FFFFF0>Saradomin Hilt: " + target.saradominHilt);
					player.getPackets().sendIComponentText(960, 63, "");
					player.getPackets().sendIComponentText(960, 55, "<shad=00FFFF>Armadyl Helmet: " + target.armadylHelm);
					player.getPackets().sendIComponentText(960, 51, "<shad=00FFFF>Armadyl Chestplate: " + target.armadylPlate);
					player.getPackets().sendIComponentText(960, 60, "<shad=00FFFF>Armadyl Chainskirt: " + target.armadylLegs);
					player.getPackets().sendIComponentText(960, 58, "<shad=00FFFF>Armadyl Hilt: " + target.armadylHilt);
					player.getPackets().sendIComponentText(960, 53, "");
					player.getPackets().sendIComponentText(960, 50, "<shad=FF0000>Zamorak Hilt: " + target.zamorakHilt);
					player.getPackets().sendIComponentText(960, 57, "<shad=FF0000>Zamorakian Spear: " + target.zamorakSpear);
					player.getPackets().sendIComponentText(960, 59, "");
					player.getPackets().sendIComponentText(960, 52, "");

					// Right side page
					player.getPackets().sendIComponentText(960, 33, "<shad=FF00FF>Virtus Mask: " + target.virtusMask);
					player.getPackets().sendIComponentText(960, 39, "<shad=FF00FF>Virtus Robe Top: " + target.virtusTop);
					player.getPackets().sendIComponentText(960, 36, "<shad=FF00FF>Virtus Robe Legs: " + target.virtusLegs);
					player.getPackets().sendIComponentText(960, 44, "");
					player.getPackets().sendIComponentText(960, 37, "<shad=FF00FF>Pernix Cowl: " + target.pernixCowl);
					player.getPackets().sendIComponentText(960, 46, "<shad=FF00FF>Pernix Body: " + target.pernixBody);
					player.getPackets().sendIComponentText(960, 40, "<shad=FF00FF>Pernix Chaps: " + target.pernixChaps);
					player.getPackets().sendIComponentText(960, 42, "");
					player.getPackets().sendIComponentText(960, 34, "<shad=FF00FF>Torva Full Helmet: " + target.torvaHelm);
					player.getPackets().sendIComponentText(960, 35, "<shad=FF00FF>Torva Platebody: " + target.torvaPlate);
					player.getPackets().sendIComponentText(960, 38, "<shad=FF00FF>Torva Platelegs: " + target.torvaLegs);
					player.getPackets().sendIComponentText(960, 43, "");
					player.getPackets().sendIComponentText(960, 47, "<shad=FF00FF>Zaryte Bow: " + target.zaryteBow);
					player.getPackets().sendIComponentText(960, 45, "");
					player.getPackets().sendIComponentText(960, 41, "");
					player.getPackets().sendIComponentText(960, 41, "");

					// Right and left button text
					player.getPackets().sendIComponentText(960, 70, "");
					player.getPackets().sendIComponentText(960, 71, "");
					player.getTemporaryAttributtes().put("Gwd_Drop_Log", Boolean.FALSE);
					return;
				}
			} else if (player.getTemporaryAttributtes().get("Misc_Drop_Log") == Boolean.TRUE) {
				String name = value;
				Player target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.getPackets().sendGameMessage("Couldn't find " + name + ".");
					player.getTemporaryAttributtes().put("Misc_Drop_Log", Boolean.FALSE);
				} else {
					player.getInterfaceManager().sendInterface(960);
					player.getPackets().sendIComponentText(960, 69, "The Drop Log Of " + target.getDisplayName()); // Title
					player.getPackets().sendIComponentText(960, 49, "Spectral Sigil: " + target.specSs);
					player.getPackets().sendIComponentText(960, 56, "Arcane Sigil: " + target.arcaneSs);
					player.getPackets().sendIComponentText(960, 61, "Elysian Sigil: " + target.elySs);
					player.getPackets().sendIComponentText(960, 62, "Divine Sigil: " + target.divineSs);
					player.getPackets().sendIComponentText(960, 54, "Steadfast Boots: " + target.steadBoots);
					player.getPackets().sendIComponentText(960, 63, "Glaiven Boots: " + target.glaivenBoots);
					player.getPackets().sendIComponentText(960, 55, "Ragefire Boots: " + target.rageBoots);
					player.getPackets().sendIComponentText(960, 51, "Dragon Claws: " + target.dragonClaws);
					player.getPackets().sendIComponentText(960, 60, "Dragon Platebody: " + target.dragonPlate);
					player.getPackets().sendIComponentText(960, 58, "Ganodermic Poncho: " + target.ganoPonch);
					player.getPackets().sendIComponentText(960, 53, "Ganodermic Leggings: " + target.ganoLegs);
					player.getPackets().sendIComponentText(960, 50, "Polypore Staff: " + target.polyStaff);
					player.getPackets().sendIComponentText(960, 57, "Draconic Visage: " + target.dVisage);
					player.getPackets().sendIComponentText(960, 59, "Vine whip: " + target.vineWhip);
					player.getPackets().sendIComponentText(960, 52, "Abyssal Whip: " + target.aWhip);

					// Right side page
					player.getPackets().sendIComponentText(960, 33, "Berserker Ring (i): " + target.bringI);
					player.getPackets().sendIComponentText(960, 39, "Archer Ring (i): " + target.aringI);
					player.getPackets().sendIComponentText(960, 36, "Seer's Ring (i) " + target.sringI);
					player.getPackets().sendIComponentText(960, 44, "Vesta's Longsword: " + target.vlS);
					player.getPackets().sendIComponentText(960, 37, "Vesta's Chainbody: " + target.vestacB);
					player.getPackets().sendIComponentText(960, 46, "Vesta's Plateskirt: " + target.vestapS);
					player.getPackets().sendIComponentText(960, 40, "Vesta's Spear: " + target.vestaSpear);
					player.getPackets().sendIComponentText(960, 42, "Pernix Chaps: " + target.pernixChaps);
					player.getPackets().sendIComponentText(960, 34, "Dharoks Helm: " + target.dharoksHelm);
					player.getPackets().sendIComponentText(960, 35, "Dharoks Plate: " + target.dharoksPlate);
					player.getPackets().sendIComponentText(960, 38, "Dharoks Legs: " + target.dharoksLegs);
					player.getPackets().sendIComponentText(960, 43, "Dharoks Greataxe: " + target.dharoksGreataxe);
					player.getPackets().sendIComponentText(960, 47, "Frost Dragon Bones: " + target.frostBones);
					player.getPackets().sendIComponentText(960, 45, "PK'd an item worth:");
					player.getPackets().sendIComponentText(960, 41, "");

					// Right and left button text
					player.getPackets().sendIComponentText(960, 70, "");
					player.getPackets().sendIComponentText(960, 71, "");
					player.getTemporaryAttributtes().put("Misc_Drop_Log", Boolean.FALSE);
					return;
				}
			} else if (player.getTemporaryAttributtes().get("Kill_Tracker_Other") == Boolean.TRUE) {
				String name = value;
				Player target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.getPackets().sendGameMessage("Couldn't find " + name + ".");
					player.getTemporaryAttributtes().put("Kill_Tracker_Other", Boolean.FALSE);
				} else {
					player.getInterfaceManager().sendInterface(553);
					player.getPackets().sendIComponentText(553, 90, "" + target.getDisplayName() + "'s Monster Kills"); // Title
					player.getPackets().sendIComponentText(553, 66, ""); // button
					player.getPackets().sendIComponentText(553, 65, "");// button

					player.getPackets().sendIComponentText(553, 68, "<col=CC0000>Total Npc Kills: " + target.npckills);
					player.getPackets().sendIComponentText(553, 69, "<col=CC0000>Zombie Horde: " + target.zkills);
					player.getPackets().sendIComponentText(553, 70, "<col=CC0000>Glacor: " + target.GlacorKills);
					player.getPackets().sendIComponentText(553, 71, "<col=CC0000>Rune Dragon: " + target.runedragkills);
					player.getPackets().sendIComponentText(553, 72, "<col=CC0000>High Level Dungeoneering floors: " + target.dunggkills);
					player.getPackets().sendIComponentText(553, 73, "");
					player.getPackets().sendIComponentText(553, 74, "");
					player.getPackets().sendIComponentText(553, 75, "");
					player.getPackets().sendIComponentText(553, 76, "");
					player.getPackets().sendIComponentText(553, 77, "");
					player.getPackets().sendIComponentText(553, 78, "");

					player.getPackets().sendIComponentText(553, 79, "");
					player.getPackets().sendIComponentText(553, 80, "");
					player.getPackets().sendIComponentText(553, 81, "");
					player.getPackets().sendIComponentText(553, 82, "");
					player.getPackets().sendIComponentText(553, 83, "");
					player.getPackets().sendIComponentText(553, 84, "");
					player.getPackets().sendIComponentText(553, 85, "");
					player.getPackets().sendIComponentText(553, 86, "");
					player.getPackets().sendIComponentText(553, 87, "");
					player.getPackets().sendIComponentText(553, 88, "");
					player.getPackets().sendIComponentText(553, 89, "");

					player.getTemporaryAttributtes().put("Kill_Tracker_Other", Boolean.FALSE);
					return;
				}
			} else if (player.getTemporaryAttributtes().get("Kill_Tracker") == Boolean.TRUE) {
				String name = value;
				Player target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.getPackets().sendGameMessage("Couldn't find " + name + ".");
					player.getTemporaryAttributtes().put("Kill_Tracker", Boolean.FALSE);
				} else {
					player.getInterfaceManager().sendInterface(553);
					player.getPackets().sendIComponentText(553, 90, "" + target.getDisplayName() + "'s Boss Kills"); // Title
					player.getPackets().sendIComponentText(553, 66, ""); // button
					player.getPackets().sendIComponentText(553, 65, "");// button

					player.getPackets().sendIComponentText(553, 68, "<col=CC0000>Nex: " + target.NexKills);
					player.getPackets().sendIComponentText(553, 69, "<col=CC0000>Kbd: " + target.KbdKills);
					player.getPackets().sendIComponentText(553, 70, "<col=CC0000>Qbd: " + target.QbdKills);
					player.getPackets().sendIComponentText(553, 71, "<col=CC0000>Kalphite Queen: " + target.KqKills);
					player.getPackets().sendIComponentText(553, 72, "<col=CC0000>Corporeal Beast: " + target.CorpKills);
					// player.getPackets().sendIComponentText(553, 51,
					// "<col=3366CC>Glacor: "+target.GlacorKills);
					player.getPackets().sendIComponentText(553, 73, "<col=CC0000>Bandos: " + target.BandosKills);
					player.getPackets().sendIComponentText(553, 74, "<col=CC0000>Armadyl: " + target.ArmadylKills);
					player.getPackets().sendIComponentText(553, 75, "<col=CC0000>Zamorak: " + target.ZamorakKills);
					player.getPackets().sendIComponentText(553, 76, "<col=CC0000>Saradomin: " + target.SaradominKills);
					player.getPackets().sendIComponentText(553, 77, "<col=CC0000>Rise Of The Six: " + target.Rot6Kills);
					player.getPackets().sendIComponentText(553, 78, "<col=CC0000>Avatar of Destruction: " + target.AvaKills);

					player.getPackets().sendIComponentText(553, 79, "<col=CC0000>WildyWyrm: " + target.WwyrmKills);
					player.getPackets().sendIComponentText(553, 80, "<col=CC0000>(Hardmode) WildyWyrm: " + target.HmWildyWyrmKills);
					player.getPackets().sendIComponentText(553, 81, "<col=CC0000>Aquatic WildyWyrm: " + target.AwyrmKills);
					player.getPackets().sendIComponentText(553, 82, "<col=CC0000>Night-Gazer: " + target.GazerKills);
					/*
					 * player.getPackets().sendIComponentText(553, 81,
					 * "<col=CC0000>Bal'lak (Donator boss): " + target.Dboss);
					 */
					player.getPackets().sendIComponentText(553, 83, "<col=CC0000>Kalphite King: " + target.KkingKills);
					player.getPackets().sendIComponentText(553, 84, "<col=CC0000>Sliske: " + target.SliskeKills);
					player.getPackets().sendIComponentText(553, 85, "<col=CC0000>Hope Devourer: " + target.DrygonKills);
					// player.getPackets().sendIComponentText(553, 86,
					// "<col=CC0000>Sunfreet: "+target.SunfreetKills);
					player.getPackets().sendIComponentText(553, 87, "<col=CC0000>Party Demon: " + target.PdemonKills);
					player.getPackets().sendIComponentText(553, 88, "<col=CC0000>Bad Santa: " + target.SantaKills);
					player.getPackets().sendIComponentText(553, 89, "<col=CC0000>Sirenic: " + target.SirenicKills);

					player.getTemporaryAttributtes().put("Kill_Tracker", Boolean.FALSE);
					return;
				}
			} else if (player.getTemporaryAttributtes().get("teleto_house") == Boolean.TRUE) {
				int[] boundChuncks;
				boundChuncks = RegionBuilder.findEmptyChunkBound(8, 8);
				String name = value;
				Player target = World.getPlayerByDisplayName(name);
				if (target == null || target.inBuildMode == true || target.hasLocked == true) {
					player.getPackets().sendGameMessage("Cannot enter the house of " + name + ".");
					player.getTemporaryAttributtes().put("teleto_house", Boolean.FALSE);
				} else {
					player.setNextWorldTile(target);
					player.out("This has not been fully coded yet.");
					player.getTemporaryAttributtes().put("teleto_house", Boolean.FALSE);
					return;
				}
			} else if (player.getTemporaryAttributtes().get("view_name") == Boolean.TRUE) {
				player.getTemporaryAttributtes().remove("view_name");
				Player other = World.getPlayerByDisplayName(value);
				if (other == null) {
					player.getPackets().sendGameMessage("Couldn't find player.");
					return;
				}
				ClanWars clan = other.getCurrentFriendChat() != null ? other.getCurrentFriendChat().getClanWars() : null;
				if (clan == null) {
					player.getPackets().sendGameMessage("This player's clan is not in war.");
					return;
				}
				if (clan.getSecondTeam().getOwnerDisplayName() != other.getCurrentFriendChat().getOwnerDisplayName()) {
					player.getTemporaryAttributtes().put("view_prefix", 1);
				}
				player.getTemporaryAttributtes().put("view_clan", clan);
				ClanWars.enter(player);
			} else if (player.getTemporaryAttributtes().remove("setdisplay") != null) {
				String[] invalid = { "<euro", "<img", "<img=", "<col", "<col=", "<shad", "<shad=", "<str>", "mod", "admin", "staff", "support", "owner", "developer", "cunt", "fuck", "gay", "<u>" };
				for (String s : invalid) {
					if (player.getRights() < 2) {
						if (value.contains(s)) {
							player.getPackets().sendGameMessage("You cannnot use this sort of text.");
							return;
						}
					}
				}
				if (Utils.invalidAccountName(Utils.formatPlayerNameForProtocol(value))) {
					player.getPackets().sendGameMessage("Invalid name!");
					return;
				}
				if (!DisplayNames.setDisplayName(player, value)) {
					player.getPackets().sendGameMessage("Name already in use!");
					return;
				}
				player.getInventory().deleteItem(29552, 1);
				player.getPackets().sendGameMessage("Changed display name!");
			} else if (player.getTemporaryAttributtes().remove("referral") != null) {
				ReferralMain.CheckReferral(player, value.toLowerCase());
			} else if (player.getTemporaryAttributtes().remove("setemail") != null) {
				if (value.contains("@")) {
					player.getTemporaryAttributtes().put("setemail", Boolean.TRUE);
					player.getPackets().sendInputNameScript("Do not include the @address, just your custom part before @:");
					return;
				}
				player.setAccountEmail("" + value + "" + player.getAccountEmail() + "");
				player.sendMessage("Your email is now set as <col=ff0000>" + player.getAccountEmail() + ".");
			} else if (player.getTemporaryAttributtes().remove("checkvoteinput") != null) {
				Vote.checkVote(player, value);
			}
		} else if (packetId == ENTER_STRING_PACKET) {
			if (!player.isRunning() || player.isDead()) {
				return;
			}
			String value = stream.readString();
			if (value.equals("")) {
				return;
			}
		} else if (packetId == ESCAPE_PRESSED_PACKET) {
				player.closeInterfaces();
			

		} else if (packetId == ENTER_INTEGER_PACKET) {
			if (!player.isRunning() || player.isDead()) {
				return;
			}
			int value = stream.readInt();
			long value1 = stream.readInt();
			if (player.getInterfaceManager().containsInterface(762) && player.getInterfaceManager().containsInterface(763) || player.getInterfaceManager().containsInterface(11)) {
				if (value < 0) {
					return;
				}
				Integer bank_item_X_Slot = (Integer) player.getTemporaryAttributtes().remove("bank_item_X_Slot");
				if (bank_item_X_Slot == null) {
					return;
				}
				if (player.bankis2) {
					player.getBank2().setLastX(value);
					player.getBank2().refreshLastX();
				} else {
				player.getBank().setLastX(value);
				player.getBank().refreshLastX();
				}
				if (player.bankis2) {
					if (player.getTemporaryAttributtes().remove("bank_isWithdraw") != null) {
						player.getBank2().withdrawItem(bank_item_X_Slot, value);
					} else {
						player.getBank2().depositItem(bank_item_X_Slot, value, player.getInterfaceManager().containsInterface(11) ? false : true);
					}
				} else {
					if (player.getTemporaryAttributtes().remove("bank_isWithdraw") != null) {
						player.getBank().withdrawItem(bank_item_X_Slot, value);
					} else {
						player.getBank().depositItem(bank_item_X_Slot, value, player.getInterfaceManager().containsInterface(11) ? false : true);
					}
				}
			} else if (player.getTemporaryAttributtes().get("bank_pin") == Boolean.TRUE) {
				if (value < 0) {
					return;
				}
				player.setBankPin(value);
				player.hasBankPin = true;
				player.getAppearence().generateAppearenceData();
				player.getDialogueManager().startDialogue("SimpleMessage", "Your Bank Pin Is... <col=FF0000>" + player.getBankPin() + " </col>Remember it! You can only ever have one pin!");
				player.getTemporaryAttributtes().put("bank_pin", Boolean.FALSE);
			} else if (player.getTemporaryAttributtes().get("bank_pin1") == Boolean.TRUE) {
				if (value < 0) {
					return;
				}
				if (player.pin != value) {
					player.getDialogueManager().startDialogue("SimpleMessage", "Wrong Pin! Please try again.");
				} else {
					player.getAppearence().generateAppearenceData();
					player.getDialogueManager().startDialogue("SimpleMessage", "You have entered your bank pin, Thank You");
					player.hasEnteredPin = true;
					player.unlock();
				}
				player.getTemporaryAttributtes().put("bank_pin1", Boolean.FALSE);
			} else if (player.getTemporaryAttributtes().remove("turn_into_npc") == Boolean.TRUE) {
				player.getTemporaryAttributtes().remove("turn_into_npc");
				player.getAppearence().transformIntoNPC(Integer.valueOf(value));
			} else if (player.getTemporaryAttributtes().remove("world_does_emote") == Boolean.TRUE) {
				player.getTemporaryAttributtes().remove("world_does_emote");
				for (Player p : World.getPlayers()) {
					p.setNextAnimation(new Animation(value));
				}
			} else if (player.getTemporaryAttributtes().remove("well_of_fortune") == Boolean.TRUE) {
				player.getTemporaryAttributtes().remove("well_of_fortune");
				WellOfFortuneV2.handleWell(player, value, null);
			} else if (player.getTemporaryAttributtes().remove("setting_geoffer_amount") != null) {
				GrandExchangeOffer offer = (GrandExchangeOffer) player.getTemporaryAttributtes().get("grand_exchange_offer");
				if (offer != null) {
					offer.setPrimaryAmount(value);
					player.getPackets().sendConfig(1110, offer.getPrimaryAmount());
				}
				return;
			}
			if (player.getTemporaryAttributtes().get("POS_ChooseAmount") != null) {
				if (player.isIronman()) {
					player.sendMessage("Ironmen cannot do this.");
					return;
				}
				int itemId = (int) player.getTemporaryAttributtes().remove("POS_ChooseAmount");
				PlayerOwnedShop store = player.getPlayerOwnedShop();
				if (store.getStoreItems().containsOne(new Item(itemId))) {
					store.addItem(itemId, value, store.getStoreItemsPrices().get(itemId));
					return;
				}
				player.getPackets().sendInputIntegerScript(true, "Choose price for the item: ");
				player.getTemporaryAttributtes().put("POS_ChoosePrice", new Object[] { itemId, value });
				return;
			}
			if (player.getTemporaryAttributtes().get("POS_ChooseAmountRemove") != null) {
				int slotId = (int) player.getTemporaryAttributtes().remove("POS_ChooseAmountRemove");
				PlayerOwnedShop store = player.getPlayerOwnedShop();
				store.removeItem(slotId, value);
				return;
			}
			if (player.getTemporaryAttributtes().get("POS_ChoosePrice") != null) {
				Object[] attributes = (Object[]) player.getTemporaryAttributtes().remove("POS_ChoosePrice");
				int itemId = (int) attributes[0];
				int amount = (int) attributes[1];
				// System.out.println("" + itemId + " " + amount);
				if (player.getPlayerOwnedShop().addItem(itemId, amount, value)) {
					return;
				}
				return;
			}
			if (player.getTemporaryAttributtes().get("POS_ChooseNewPrice") != null) {
				int itemId = (int) player.getTemporaryAttributtes().remove("POS_ChooseNewPrice");
				PlayerOwnedShop store = player.getPlayerOwnedShop();
				if (store.getStoreItems().containsOne(new Item(itemId))) {
					store.getStoreItemsPrices().replace(itemId, value);
					for (Player p : store.getViewingPlayers()) {
						if (p == null) {
							continue;
						}
						p.getPackets().sendGameMessage("The price for the item : " + ItemDefinitions.getItemDefinitions(itemId).getName() + " has been changed new price is <col=00ffff>" + Utils.getFormattedNumber(value) + "</col>coins.");
					}
					return;
				}
				return;
			}
			if (player.getTemporaryAttributtes().get("POS_ChooseAmountBuy") != null) {
				int slotId = (int) player.getTemporaryAttributtes().remove("POS_ChooseAmountBuy");
				PlayerOwnedShop shop = (PlayerOwnedShop) player.getTemporaryAttributtes().get("POS");
				shop.buy(player, slotId, value);
				return;
			}
			if (player.getControlerManager().getControler() != null && player.getTemporaryAttributtes().get("SERVANT_REQUEST_ITEM") != null) {
				Integer type = (Integer) player.getTemporaryAttributtes().remove("SERVANT_REQUEST_TYPE");
				Integer item = (Integer) player.getTemporaryAttributtes().remove("SERVANT_REQUEST_ITEM");
				if (!player.getHouse().getPlayers().contains(player) || type == null || item == null) {
					return;
				}
				player.getHouse().getServantInstance().requestType(item, value, type.byteValue());
			}
			if (player.getTemporaryAttributtes().get("removebbarbag") == Boolean.TRUE) {
				if (value < 0 || value > player.bbar) {
					return;
				}
				if (player.bbar < 1) {
					return;
				}
				player.bbar -= value;
				player.totalbagbars -= value;
				player.getInventory().addItem(2350, value);
				player.getTemporaryAttributtes().put("removebbarbag", Boolean.FALSE);
				return;
			} else if (player.getTemporaryAttributtes().get("removeibarbag") == Boolean.TRUE) {
				if (value < 0 || value > player.ibar) {
					return;
				}
				if (player.ibar < 1) {
					return;
				}
				player.ibar -= value;
				player.totalbagbars -= value;
				player.getInventory().addItem(2352, value);
				player.getTemporaryAttributtes().put("removeibarbag", Boolean.FALSE);
				return;
			} else if (player.getTemporaryAttributtes().get("removesbarbag") == Boolean.TRUE) {
				if (value < 0 || value > player.sbar) {
					return;
				}
				if (player.sbar < 1) {
					return;
				}
				player.sbar -= value;
				player.totalbagbars -= value;
				player.getInventory().addItem(2354, value);
				player.getTemporaryAttributtes().put("removesbarbag", Boolean.FALSE);
				return;
			} else if (player.getTemporaryAttributtes().get("removembarbag") == Boolean.TRUE) {
				if (value < 0 || value > player.mbar) {
					return;
				}
				if (player.mbar < 1) {
					return;
				}
				player.mbar -= value;
				player.totalbagbars -= value;
				player.getInventory().addItem(2360, value);
				player.getTemporaryAttributtes().put("removembarbag", Boolean.FALSE);
				return;
			} else if (player.getTemporaryAttributtes().get("removeabarbag") == Boolean.TRUE) {
				if (value < 0 || value > player.abar) {
					player.sendMessage("1111");
					return;
				}
				if (player.abar < 1) {
					player.sendMessage("2222");
					return;
				}

				player.abar -= value;
				player.totalbagbars -= value;
				player.getInventory().addItem(2362, value);
				player.getTemporaryAttributtes().put("removeabarbag", Boolean.FALSE);
				return;
			} else if (player.getTemporaryAttributtes().get("removerbarbag") == Boolean.TRUE) {
				if (value < 0 || value > player.rbar) {
					return;
				}
				if (player.rbar < 1) {
					return;
				}
				player.totalbagbars -= value;
				player.rbar -= value;
				player.getInventory().addItem(2364, value);
				player.getTemporaryAttributtes().put("removerbarbag", Boolean.FALSE);
				return;
			}
			if (player.getTemporaryAttributtes().remove("setting_geoffer_price") != null) {
				GrandExchangeOffer offer = (GrandExchangeOffer) player.getTemporaryAttributtes().get("grand_exchange_offer");
				if (offer != null) {
					offer.setPrice(value);
					// System.out.println("PRICE UPDATED");
					player.getPackets().sendConfig(1111, offer.getPrice());
				}
				return;

			} else if (player.getInterfaceManager().containsInterface(206) && player.getInterfaceManager().containsInterface(207)) {
				if (value < 0) {
					return;
				}
				Integer pc_item_X_Slot = (Integer) player.getTemporaryAttributtes().remove("pc_item_X_Slot");
				if (pc_item_X_Slot == null) {
					return;
				}
				if (player.getTemporaryAttributtes().remove("pc_isRemove") != null) {
					player.getPriceCheckManager().removeItem(pc_item_X_Slot, value);
				} else {
					player.getPriceCheckManager().addItem(pc_item_X_Slot, value);
				}
			} else if (player.getInterfaceManager().containsInterface(671) && player.getInterfaceManager().containsInterface(665)) {
				if (player.getFamiliar() == null || player.getFamiliar().getBob() == null) {
					return;
				}
				if (value < 0) {
					return;
				}
				Integer bob_item_X_Slot = (Integer) player.getTemporaryAttributtes().remove("bob_item_X_Slot");
				if (bob_item_X_Slot == null) {
					return;
				}
				if (player.getTemporaryAttributtes().remove("bob_isRemove") != null) {
					player.getFamiliar().getBob().removeItem(bob_item_X_Slot, value);
				} else {
					player.getFamiliar().getBob().addItem(bob_item_X_Slot, value);
				}
			} else if (player.getInterfaceManager().containsInterface(335) && player.getInterfaceManager().containsInterface(336)) {
				if (value < 0) {
					return;
				}
				Integer trade_item_X_Slot = (Integer) player.getTemporaryAttributtes().remove("trade_item_X_Slot");
				if (trade_item_X_Slot == null) {
					return;
				}
				if (player.getTemporaryAttributtes().remove("trade_isRemove") != null) {
					player.getTrade().removeItem(trade_item_X_Slot, value);
				} else {
					player.getTrade().addItem(trade_item_X_Slot, value);
				}
			} else if (player.getTemporaryAttributtes().get("skillId") != null) {
				if (player.getEquipment().wearingArmour()) {
					player.getDialogueManager().finishDialogue();
					player.getDialogueManager().startDialogue("SimpleMessage", "You cannot do this while having armour on!");
					return;
				}
				int skillId = (Integer) player.getTemporaryAttributtes().remove("skillId");
				if (skillId == Skills.HITPOINTS && value <= 9) {
					value = 10;
				} else if (value < 1) {
					value = 1;
				} else if (value > 99) {
					value = 99;
				}
				player.getSkills().set(skillId, value);
				player.getSkills().setXp(skillId, Skills.getXPForLevel(value));
				player.getAppearence().generateAppearenceData();
				player.getDialogueManager().finishDialogue();

			} else if (player.getTemporaryAttributtes().get("kilnX") != null) {
				int index = (Integer) player.getTemporaryAttributtes().get("scIndex");
				int componentId = (Integer) player.getTemporaryAttributtes().get("scComponentId");
				int itemId = (Integer) player.getTemporaryAttributtes().get("scItemId");
				player.getTemporaryAttributtes().remove("kilnX");
				if (StealingCreation.proccessKilnItems(player, componentId, index, itemId, value)) {
					;
				}
			} else if (player.getInterfaceManager().containsInterface(548) || player.getInterfaceManager().containsInterface(746)) {
				if (value < 0) {
					return;
				}
				Integer remove_X_money = (Integer) player.getTemporaryAttributtes().remove("remove_X_money");
				if (remove_X_money == null) {
					return;
				}
				int amount = player.getInventory().getItems().getNumberOf(remove_X_money);
				if (player.getTemporaryAttributtes().remove("remove_money") != null) {
					if (value <= player.coinamount) {
						if (amount + value > 0) {
							player.getInventory().addItem(remove_X_money, value);
							player.getPackets().sendRunScript(5561, 0, value);
							player.coinamount -= value;
							player.refreshMoneyPouch();
							player.getPackets().sendGameMessage(player.getMoneyPouch().getFormattedNumber(value) + " coins have been removed from your money pouch.");
						} else {
							player.getPackets().sendGameMessage("You can't have more then " + player.getMoneyPouch().getFormattedNumber(2147483647) + " coins in your inventory.");
						}
					} else {
						player.getPackets().sendGameMessage("You don't have enough coins to withdraw that many.");
					}
				}
				if (value < 0) {
					return;
				}
				if (remove_X_money == null) {
					return;
				}
				int amount1 = player.getInventory().getItems().getNumberOf(remove_X_money);
				if (player.getTemporaryAttributtes().remove("remove_money1") != null) {
					if (value1 <= player.count) {
						if (amount1 + value > 0) {
							player.getInventory().addItem(remove_X_money, value);
							player.count -= value;
							player.unlock();
							player.sm("You Withdrew<col=B00000> " + FormatNumber(value) + " coins </col>from your Money Vault");
							player.sm("You have " + Player.FormatNumber(player.count) + "  coins left in your Money Vault");
						} else {
							player.unlock();
							player.getPackets().sendGameMessage("You can't have more then 2147483647 coins in your inventory.");
						}
					} else {
						player.unlock();
						player.getPackets().sendGameMessage("You don't have enough coins to withdraw that many.");
					}
				}
				if (value < 0) {
					return;
				}
				if (remove_X_money == null) {
					return;
				}
				int amount3 = player.getInventory().getItems().getNumberOf(995);
				if (player.getTemporaryAttributtes().remove("Add_money") != null) {
					// if (value <= player.count) {
					if (amount3 - value + 1 > 0) {
						player.getInventory().deleteItem(remove_X_money, value);
						player.count += value;
						player.unlock();
						player.sm("You Deposited<col=B00000> " + FormatNumber(value) + " coins</col> to your Money Vault");
						player.sm("You have " + player.FormatNumber(player.count) + " coins left in your Money Vault");
					} else {
						player.unlock();
						player.getPackets().sendGameMessage("You can't have more then what you already have in your Inventory.");
					}
				}
			}
			return;
			// }

		} else if (packetId == SWITCH_INTERFACE_ITEM_PACKET) {
			stream.readShortLE128();
			int fromInterfaceHash = stream.readIntV1();
			int toInterfaceHash = stream.readInt();
			int fromSlot = stream.readUnsignedShort();
			int toSlot = stream.readUnsignedShortLE128();
			stream.readUnsignedShortLE();

			int toInterfaceId = toInterfaceHash >> 16;
			int toComponentId = toInterfaceHash - (toInterfaceId << 16);
			int fromInterfaceId = fromInterfaceHash >> 16;
			int fromComponentId = fromInterfaceHash - (fromInterfaceId << 16);

			if (Utils.getInterfaceDefinitionsSize() <= fromInterfaceId || Utils.getInterfaceDefinitionsSize() <= toInterfaceId) {
				return;
			}
			if (!player.getInterfaceManager().containsInterface(fromInterfaceId) || !player.getInterfaceManager().containsInterface(toInterfaceId)) {
				return;
			}
			if (fromComponentId != -1 && Utils.getInterfaceDefinitionsComponentsSize(fromInterfaceId) <= fromComponentId) {
				return;
			}
			if (toComponentId != -1 && Utils.getInterfaceDefinitionsComponentsSize(toInterfaceId) <= toComponentId) {
				return;
			}
			if (fromInterfaceId == Inventory.INVENTORY_INTERFACE && fromComponentId == 0 && toInterfaceId == Inventory.INVENTORY_INTERFACE && toComponentId == 0) {
				toSlot -= 28;
				if (toSlot < 0 || toSlot >= player.getInventory().getItemsContainerSize() || fromSlot >= player.getInventory().getItemsContainerSize()) {
					return;
				}
				player.getInventory().switchItem(fromSlot, toSlot);
			} else if (fromInterfaceId == 763 && fromComponentId == 0 && toInterfaceId == 763 && toComponentId == 0) {
				if (toSlot >= player.getInventory().getItemsContainerSize() || fromSlot >= player.getInventory().getItemsContainerSize()) {
					return;
				}
				player.getInventory().switchItem(fromSlot, toSlot);
			} else if (fromInterfaceId == 762 && toInterfaceId == 762) {
				if (toSlot < 0) {
					toSlot = 65535; // temp fix
				}
				if (player.bankis2 != true) {
					if (player.getBank().isInsertItems()) {
						player.getBank().insertItem(fromSlot, toSlot);
					} else {
						player.getBank().switchItem(fromSlot, toSlot, fromComponentId, toComponentId);
					}
				} else if (player.bankis2) {
					if (player.getBank2().isInsertItems()) {
						player.getBank2().insertItem(fromSlot, toSlot);
					} else {
						player.getBank2().switchItem(fromSlot, toSlot, fromComponentId, toComponentId);
					}
				}
			}
			if (Settings.DEBUG) {
				System.out.println("Switch item " + fromInterfaceId + ", " + fromSlot + ", " + toSlot);
			}
		} else if (packetId == DONE_LOADING_REGION_PACKET) {
			/*
			 * if(!player.clientHasLoadedMapRegion()) { //load objects and items here
			 * player.setClientHasLoadedMapRegion(); } //player.refreshSpawnedObjects();
			 * //player.refreshSpawnedItems();
			 */
		} else if (packetId == WALKING_PACKET || packetId == MINI_WALKING_PACKET || packetId == ITEM_TAKE_PACKET || packetId == PLAYER_OPTION_2_PACKET || packetId == PLAYER_OPTION_4_PACKET || packetId == PLAYER_OPTION_6_PACKET || packetId == PLAYER_OPTION_1_PACKET || packetId == ATTACK_NPC || packetId == INTERFACE_ON_PLAYER || packetId == INTERFACE_ON_NPC || packetId == NPC_CLICK1_PACKET || packetId == NPC_CLICK2_PACKET || packetId == NPC_CLICK3_PACKET || packetId == OBJECT_CLICK1_PACKET || packetId == SWITCH_INTERFACE_ITEM_PACKET || packetId == OBJECT_CLICK2_PACKET || packetId == OBJECT_CLICK3_PACKET // lemme see if its me or
																																																																																																																																																									// not, holdf on
				|| packetId == OBJECT_CLICK4_PACKET || packetId == OBJECT_CLICK5_PACKET || packetId == PLAYER_OPTION_5_PACKET || packetId == INTERFACE_ON_OBJECT || packetId == PLAYER_OPTION_9_PACKET) {
			if (player.getXpMode().equalsIgnoreCase("<col=ff0000>Error")) {
				StarterInterface.SendInterface(player);
				return;
			}
			player.increaseAFKTimer();
			player.addLogicPacketToQueue(new LogicPacket(packetId, length, stream));
		} else if (packetId == OBJECT_EXAMINE_PACKET) {
			ObjectHandler.handleOption(player, stream, -1);
		} else if (packetId == NPC_EXAMINE_PACKET) {
			NPCHandler.handleExamine(player, stream);
			player.examinenpc++;
		} else if (packetId == JOIN_FRIEND_CHAT_PACKET) {
			if (!player.hasStarted()) {
				return;
			}
			FriendChatsManager.joinChat(stream.readString(), player);
		} else if (packetId == KICK_FRIEND_CHAT_PACKET) {
			if (!player.hasStarted()) {
				return;
			}
			player.setLastPublicMessage(Utils.currentTimeMillis() + 1000); // avoids
			// message
			// appearing
			player.kickPlayerFromFriendsChannel(stream.readString());
		} else if (packetId == CHANGE_FRIEND_CHAT_PACKET) {
			if (!player.hasStarted() || !player.getInterfaceManager().containsInterface(1108)) {
				return;
			}
			player.getFriendsIgnores().changeRank(stream.readString(), stream.readUnsignedByte128());
		} else if (packetId == ADD_FRIEND_PACKET) {
			if (!player.hasStarted()) {
				return;
			}
			player.getFriendsIgnores().addFriend(stream.readString());
			player.addfriend++;
		} else if (packetId == REMOVE_FRIEND_PACKET) {
			if (!player.hasStarted()) {
				return;
			}
			player.getFriendsIgnores().removeFriend(stream.readString());
		} else if (packetId == ADD_IGNORE_PACKET) {
			if (!player.hasStarted()) {
				return;
			}
			player.getFriendsIgnores().addIgnore(stream.readString(), stream.readUnsignedByte() == 1);
		} else if (packetId == REMOVE_IGNORE_PACKET) {
			if (!player.hasStarted()) {
				return;
			}
			player.getFriendsIgnores().removeIgnore(stream.readString());
		} else if (packetId == SEND_FRIEND_MESSAGE_PACKET) {
			if (!player.hasEnteredPin && player.hasBankPin) {
				player.getTemporaryAttributtes().put("bank_pin1", Boolean.TRUE);
				player.getPackets().sendRunScript(108, new Object[] { "Enter Your Bank Pin Please" });
				return;
			}
			if (!player.hasStarted()) {
				return;
			}
			if (player.getMuted() > Utils.currentTimeMillis()) {
				player.getPackets().sendGameMessage("You're currently muted. Please re-check in 48 hours.");
				return;
			}
			String username = stream.readString();
			Player p2 = World.getPlayerByDisplayName(username);
			String message1 = Huffman.readEncryptedMessage(150, stream);
			if (message1 == null || message1.replaceAll(" ", "").equals("")) {
				return;
			}
			if (message1.startsWith(" ")) {
				player.sendMessage("Your message cannot start with a space!");
				return;
			}
			if (message1.contains("0hdr2ufufl9ljlzlyla") || message1.contains("0hdr")) {
				return;
			}
			if (message1.startsWith("::") || message1.startsWith(";;")) {
				Commands.processCommand(player, message1.replace("::", "").replace(";;", ""), false, false);
				return;
			}
			if (p2 == null) {
				return;
			}
			try {
				BufferedWriter bf = new BufferedWriter(new FileWriter("data/logs/private_messages/main/privmsgs.txt", true));
				bf.write("[" + player.getDisplayName() + " sent to " + p2.getDisplayName() + ", on " + DateFormat.getDateTimeInstance().format(new Date()) + "]: " + message1 + "");
				bf.newLine();
				bf.flush();
				bf.close();
			} catch (IOException ignored) {
			}
			player.getFriendsIgnores().sendMessage(p2, Utils.fixChatMessage(message1));
			/*
			 * } else if (packetId == SEND_FRIEND_QUICK_CHAT_PACKET) { if
			 * (!player.hasStarted()) return; String username = stream.readString(); int
			 * fileId = stream.readUnsignedShort(); byte[] data = null; if (length > 3 +
			 * username.length()) { data = new byte[length - (3 + username.length())];
			 * stream.readBytes(data); } data = Utils.completeQuickMessage(player, fileId,
			 * data); Player p2 = World.getPlayerByDisplayName(username); if (p2 == null)
			 * return; player.getFriendsIgnores().sendQuickChatMessage(p2, new
			 * QuickChatMessage(fileId, data)); } else if (packetId ==
			 * PUBLIC_QUICK_CHAT_PACKET) { if (!player.hasStarted()) return; if
			 * (player.getLastPublicMessage() > Utils.currentTimeMillis()) return;
			 * player.setLastPublicMessage(Utils.currentTimeMillis() + 300); // just tells
			 * you which client script created packet
			 * 
			 * @SuppressWarnings("unused") boolean secondClientScript = stream.readByte() ==
			 * 1;// script 5059 // or 5061 int fileId = stream.readUnsignedShort(); byte[]
			 * data = null; if (length > 3) { data = new byte[length - 3];
			 * stream.readBytes(data); } data = Utils.completeQuickMessage(player, fileId,
			 * data); if (chatType == 0) player.sendPublicChatMessage(new
			 * QuickChatMessage(fileId, data)); else if (chatType == 1)
			 * player.sendFriendsChannelQuickMessage(new QuickChatMessage( fileId, data));
			 * else if (Settings.DEBUG) Logger.log(this, "Unknown chat type: " + chatType);
			 */
		} else if (packetId == CHAT_TYPE_PACKET) {
			chatType = stream.readUnsignedByte();
		} else if (packetId == CHAT_PACKET) {
			int hour = Calendar.getInstance().getTime().getHours();
			int minute = Calendar.getInstance().getTime().getMinutes();
			int second = Calendar.getInstance().getTime().getSeconds();
			if (!player.hasEnteredPin && player.hasBankPin) {
				player.getTemporaryAttributtes().put("bank_pin1", Boolean.TRUE);
				player.getPackets().sendRunScript(108, new Object[] { "Enter Your Bank Pin Please" });
				return;
			}
			if (!player.hasStarted()) {
				return;
			}
			if (player.getLastPublicMessage() > Utils.currentTimeMillis()) {
				return;
			}
			player.setLastPublicMessage(Utils.currentTimeMillis() + 300);
			int colorEffect = stream.readUnsignedByte();
			int moveEffect = stream.readUnsignedByte();
			String message = Huffman.readEncryptedMessage(200, stream);
			if (message.contains("0hdr2ufufl9ljlzlyla") || message.contains("0hdr")) {
				return;
			}
			if (message == null || message.replaceAll(" ", "").equals("")) {
				return;
			}
			if (message.startsWith("::") || message.startsWith(";;")) {
				// if command exists and processed wont send message as public
				// message
				Commands.processCommand(player, message.replace("::", "").replace(";;", ""), false, false);
				return;
			}
			if (player.getMuted() > Utils.currentTimeMillis()) {
				player.getPackets().sendGameMessage("You temporary muted. Recheck in 48 hours.");
				return;
			}
			if (player.getUsername().equalsIgnoreCase("batman") || player.getUsername().equalsIgnoreCase("spider man")) {
				return;
			}
			int effects = colorEffect << 8 | moveEffect & 0xff;
			if (colorEffect > 11 || moveEffect > 5) {
				return;
			}
			if (chatType == 1) {
				player.sendFriendsChannelMessage(Utils.fixChatMessage(message));
			} else if (chatType == 2) {
				player.sendClanChannelMessage(new ChatMessage(message));
			} else if (chatType == 3) {
				player.sendGuestClanChannelMessage(new ChatMessage(message));
			} else {
				player.sendPublicChatMessage(new PublicChatMessage(Utils.fixChatMessage(message), effects));
			}
			player.setLastMsg(message);
			if (Settings.DEBUG) {
				Logger.log(this, "Chat type: " + chatType);
			}
		} else if (packetId == COMMANDS_PACKET) {
			if (!player.isRunning()) {
				return;
			}
			boolean clientCommand = stream.readUnsignedByte() == 1;
			@SuppressWarnings("unused")
			boolean unknown = stream.readUnsignedByte() == 1;
			String command = stream.readString();
			if (!Commands.processCommand(player, command, true, clientCommand) && Settings.DEBUG) {
				Logger.log(this, "Command: " + command);
			}
		} else if (packetId == COLOR_ID_PACKET) {
			if (!player.hasStarted()) {
				return;
			}
			int colorId = stream.readUnsignedShort();
			if (player.getTemporaryAttributtes().get("SkillcapeCustomize") != null) {
				SkillCapeCustomizer.handleSkillCapeCustomizerColor(player, colorId);
			} else if (player.getTemporaryAttributtes().get("MottifCustomize") != null) {
				ClansManager.setMottifColor(player, colorId);
			} else if (player.getTemporaryAttributtes().remove("COSTUME_COLOR_CUSTOMIZE") != null) {
				SkillCapeCustomizer.handleCostumeColor(player, colorId);
			}
		} else if (packetId == GRAND_EXCHANGE_PACKET) {
			int id = stream.readShort();
			Item item = new Item(id);
			if (player.getAttributes().get("grand_exchange_slot_clicked") == null) {
				return; // savions
			}
			GrandExchangeOffer offer = new GrandExchangeOffer(player.getDisplayName(), (int) player.getTemporaryAttributtes().get("grand_exchange_slot_clicked"), item, 1, item.getDefinitions().getValue(), Type.BUY);
			player.getPackets().sendConfig(1109, offer.getItem().getId());
			player.getPackets().sendConfig(1110, 1);
			player.getPackets().sendConfig(1111, offer.getPrice());
			player.getPackets().sendConfig(1114, offer.getPrimaryAmount());
			player.getPackets().sendConfig(1115, offer.getPrimaryAmount());
			player.getPackets().sendConfig(1116, offer.getPrimaryAmount());
			player.getTemporaryAttributtes().put("grand_exchange_offer", offer);
		} else if (packetId == FORUM_THREAD_ID_PACKET) {
			String threadId = stream.readString();
			if (player.getInterfaceManager().containsInterface(1100)) {
				ClansManager.setThreadIdInterface(player, threadId);
			} else if (Settings.DEBUG) {
				Logger.log(this, "Called FORUM_THREAD_ID_PACKET: " + threadId);
			}
		} else if (packetId == REPORT_ABUSE_PACKET) {
			if (!player.hasStarted()) {
				return;
			}
			@SuppressWarnings("unused")
			String username = stream.readString();
			@SuppressWarnings("unused")
			int type = stream.readUnsignedByte();
			@SuppressWarnings("unused")
			boolean mute = stream.readUnsignedByte() == 1;

			System.out.print("username=" + username + " type=" + type + " mute? " + "" + mute + " \n");

			// ReportAbuse.handle(player.getUsername(), username, type, mute);

			// player.getPackets().sendGameMessage("Thank you for reporting this
			// player. We will investigate your case.");
		} else {
			if (Settings.DEBUG) {
				Logger.log(this, "Missing packet " + packetId + ", expected size: " + length + ", actual size: " + PACKET_SIZES[packetId]);
			}
		}
	}

	public static String FormatNumber(long count) {
		return new DecimalFormat("#,###,##0").format(count).toString();
	}

	public static String FormatNumber(int coins, Player player) {
		return new DecimalFormat("#,###,##0").format(player.getInventory().getItems().getNumberOf(coins)).toString();
	}

}
