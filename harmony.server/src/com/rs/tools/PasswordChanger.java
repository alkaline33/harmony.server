package com.rs.tools;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.rs.game.player.Player;

public class PasswordChanger {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		String characterName = JOptionPane.showInputDialog("whirlwind");

		if (characterName == null) {
			return;
		}

		File file = new File(DIRECTORY + characterName + EXTENSION);
		if (!file.exists()) {
			System.err.println("Character does not exist.");
			return;
		}

		Player player = (Player) com.rs.utils.SerializableFilesManager.loadSerializedFile(file);
		if (player == null) {
			System.err.println("Failed to load player.");
			return;
		}

		String nonEncrypted = null;
		String newPassword = com.rs.utils.Encrypt.encryptSHA1(nonEncrypted = JOptionPane.showInputDialog("firecape"));
		if (newPassword == null || nonEncrypted == null) {
			System.err.println("Invalid password entered.");
			return;
		}

		char[] asChars = nonEncrypted.toCharArray();
		boolean containsInvalid = false;
		char invalidCharacter = 0;
		for (char character : asChars) {
			for (char invalid : INVALID_CHARS){
				if (character == invalid) {
					containsInvalid = true;
					invalidCharacter = character;
					break;
				}
			}
		}

		if (containsInvalid) {
			System.err.println("Your password contains inalid character: " + invalidCharacter);
			return;
		}

		player.setPassword(newPassword);
		com.rs.utils.SerializableFilesManager.storeSerializableClass(player, file);
		System.out.println("Password was successfully changed for " + file.getName());
	}

	public static final String DIRECTORY = "./data/characters/";
	public static final String EXTENSION = ".p";
	public static final char[] INVALID_CHARS = new char[] {
		'!', '@', '$', '%', '^', '&', '*', '(', ')', '-',
		'_', '+', '=', '[', ']', ';', ':', '"', ',', '<',
		'.', '>', '?', '/', '{', '}'
	};
}