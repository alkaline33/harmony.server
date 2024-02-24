package com.rs.game.player.content;

import java.io.Serializable;

import com.rs.game.player.Player;

public final class AdventurersLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3397895632491962036L;
	private boolean[] settings;
	private transient Player player;

	public AdventurersLog() {
		settings = new boolean[20];
		for (int i = 1; i < settings.length; i++)
			settings[i] = true;// Its all true default
	}

	public void open() {
		player.getInterfaceManager().sendInterface(623);
		sendConfigs();
	}

	public void sendConfigs() {
		int value = 0;
		for (int i = 1; i <= 19; i++) {
			if (!getSetting(i)) {
				value += ((i >= 12) ? ((int) Math.pow(2, i) * 2) : (int) Math
						.pow(2, i));
			}
		}
		player.getPackets().sendConfig(2396, value);
	}

	public boolean setSetting(int settingId, boolean checked) {
		return settings[settingId] = checked;
	}

	public boolean getSetting(int settingId) {
		return settings[settingId];
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void handleButtons(int componentId) {
		if (componentId != 74) {
			int settingsId = (componentId / 2);
			setSetting(settingsId, getSetting(settingsId) ? false : true);
			sendConfigs();
			
		}
	}
}
