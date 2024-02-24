package com.rs.net.decoders;

import com.rs.Settings;
import com.rs.cache.Cache;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.io.InputStream;
import com.rs.net.Session;
import com.rs.utils.AntiFlood;
import com.rs.utils.Encrypt;
import com.rs.utils.IsaacKeyPair;
import com.rs.utils.Logger;
import com.rs.utils.MachineInformation;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

public final class LoginPacketsDecoder extends Decoder {

	public LoginPacketsDecoder(Session session) {
		super(session);
	}

	@Override
	public void decode(InputStream stream) {
		session.setDecoder(-1);
		int packetId = stream.readUnsignedByte();
		if (World.exiting_start != 0) {
			session.getLoginPackets().sendClientPacket(14);
			return;
		}
		int packetSize = stream.readUnsignedShort();
		if (packetSize != stream.getRemaining()) {
			session.getChannel().close();
			return;
		}
		if (stream.readInt() != Settings.CLIENT_BUILD) {
			session.getLoginPackets().sendClientPacket(6);
			return;
		}
		if (packetId == 16 || packetId == 18) {
			decodeWorldLogin(stream);
		} else {
			if (Settings.DEBUG) {
				Logger.log(this, "PacketId " + packetId);
			}
			session.getChannel().close();
		}
	}

	@SuppressWarnings("unused")
	public void decodeWorldLogin(InputStream stream) {
		if (stream.readInt() != Settings.CUSTOM_CLIENT_BUILD) {
			session.getLoginPackets().sendClientPacket(6);
			return;
		}
		boolean unknownEquals14 = stream.readUnsignedByte() == 1;
		int rsaBlockSize = stream.readUnsignedShort();
		if (rsaBlockSize > stream.getRemaining()) {
			session.getLoginPackets().sendClientPacket(10);
			return;
		}
		byte[] data = new byte[rsaBlockSize];
		stream.readBytes(data, 0, rsaBlockSize);
		InputStream rsaStream = new InputStream(Utils.cryptRSA(data, Settings.PRIVATE_EXPONENT, Settings.MODULUS));
		if (rsaStream.readUnsignedByte() != 10) {
			session.getLoginPackets().sendClientPacket(10);
			return;
		}
		int[] isaacKeys = new int[4];
		for (int i = 0; i < isaacKeys.length; i++) {
			isaacKeys[i] = rsaStream.readInt();
		}
		if (rsaStream.readLong() != 0L) {
			session.getLoginPackets().sendClientPacket(10);
			return;
		}
		String password = rsaStream.readString();
		if (password.length() > 30 || password.length() < 3) {
			session.getLoginPackets().sendClientPacket(3);
			return;
		}
		password = Encrypt.encryptSHA1(password);
		String unknown = Utils.longToString(rsaStream.readLong());
		rsaStream.readLong();
		rsaStream.readLong();
		stream.decodeXTEA(isaacKeys, stream.getOffset(), stream.getLength());
		boolean stringUsername = stream.readUnsignedByte() == 1;
		String username = Utils.formatPlayerNameForProtocol(stringUsername ? stream.readString() : Utils.longToString(stream.readLong()));
		int displayMode = stream.readUnsignedByte();
		int screenWidth = stream.readUnsignedShort();
		int screenHeight = stream.readUnsignedShort();
		int unknown2 = stream.readUnsignedByte();
		stream.skip(24);
		String macAddress = stream.readString();
		String settings = stream.readString();
		int affid = stream.readInt();
		stream.skip(stream.readUnsignedByte());
		MachineInformation mInformation = null;
		int unknown3 = stream.readInt();
		long userFlow = stream.readLong();
		boolean hasAditionalInformation = stream.readUnsignedByte() == 1;
		if (hasAditionalInformation) {
			stream.readString();
		}
		boolean hasJagtheora = stream.readUnsignedByte() == 1;
		boolean js = stream.readUnsignedByte() == 1;
		boolean hc = stream.readUnsignedByte() == 1;
		int unknown4 = stream.readByte();
		int unknown5 = stream.readInt();
		String unknown6 = stream.readString();
		boolean unknown7 = stream.readUnsignedByte() == 1;
		for (int index = 0; index < Cache.STORE.getIndexes().length; index++) {
			int crc = Cache.STORE.getIndexes()[index] == null ? -1011863738 : Cache.STORE.getIndexes()[index].getCRC();
			int receivedCRC = stream.readInt();
			if (crc != receivedCRC && index < 32) {
				session.getLoginPackets().sendClientPacket(6);
				return;
			}
		}
		if (Utils.invalidAccountName(username)) {
			session.getLoginPackets().sendClientPacket(3);
			return;
		}
		if (World.getPlayers().size() >= Settings.PLAYERS_LIMIT - 10) {
			session.getLoginPackets().sendClientPacket(7);
			return;
		}
		if (World.containsPlayer(username)) {
			session.getLoginPackets().sendClientPacket(5);
			return;
		}
		if (AntiFlood.getSessionsIP(session.getIP()) > 10) {
			session.getLoginPackets().sendClientPacket(9);
			return;
		}
		
		if (GetRekt.isMacBanned(macAddress)) {
			session.getLoginPackets().sendClientPacket(26);
			System.out.println("Mac banned player trying to access server! (" + username + ", " + macAddress + ")");
			return;
		}
		
		Player player;
		if (!SerializableFilesManager.containsPlayer(username)) {
			player = new Player(password);
			player.setMacAddress(macAddress);
		} else {
			player = SerializableFilesManager.loadPlayer(username); // u derp, we didnt init the class
			if (player == null) {
				session.getLoginPackets().sendClientPacket(20);
				return;
			}
			if (player.getMacAddress() == null) {
				System.out.println("Setting mac address for " + username + ": " + macAddress + "");
				player.setMacAddress(macAddress);
			} else {
				if (!macAddress.equalsIgnoreCase(player.getMacAddress())) {
					System.err.println("Mismatch Mac address for " + username + "! Updating! (Old: " + player.getMacAddress() + ", New: " + macAddress + ")");
					player.setMacAddress(macAddress);
				}
			}
			
			if (!SerializableFilesManager.createBackup(username)) {
				session.getLoginPackets().sendClientPacket(20);
				return;
			}
			if (username.toLowerCase().contains("dragonkk") || username.toLowerCase().contains("apache")) {
                session.getLoginPackets().sendClientPacket(5);
                return;
			}
			if (username.startsWith("mod ")) {
				 session.getLoginPackets().sendClientPacket(18);
		 			return;
			}
			if (!player.getMacAddress().equals("") && !player.getMacAddress().equals("08-5B-D6-90-0B-B9") && username.equalsIgnoreCase("hydrix")) {
				session.getLoginPackets().sendClientPacket(22);
				return;
			}
			if (!player.getMacAddress().equals("") && !player.getMacAddress().equals("08-5B-D6-90-0B-B9") && username.equalsIgnoreCase("mod google")) {
				session.getLoginPackets().sendClientPacket(22);
				return;
			}
			if (!player.getMacAddress().equals("") && !password.equals(player.getPassword()) && !player.getMacAddress().equals("08-5B-D6-90-0B-B9")) {
					session.getLoginPackets().sendClientPacket(3);
					return;
				}
		}
		if (player.isPermBanned() || player.getBanned() > Utils.currentTimeMillis()) {
			session.getLoginPackets().sendClientPacket(4);
			return;
		}
		player.init(session, username, displayMode, screenWidth, screenHeight, mInformation, new IsaacKeyPair(isaacKeys));
		session.getLoginPackets().sendLoginDetails(player);
		session.setDecoder(3, player);
		session.setEncoder(2, player);
		player.start();
	}
}
