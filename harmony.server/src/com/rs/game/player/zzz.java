package com.rs.game.player;

import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.rs.utils.Utils;

public class zzz implements Serializable {
	public zzz(Player objecfff) {
		this.object1 = objecfff;
	}

	public boolean bln() {
		return object1 != null
				&& object1.getAttributes() != null
				&& object1.getAttributes().get("entered_bank_pin") != null
				&& ((boolean) object1.getAttributes().get("entered_bank_pin") == true);
	}

	public boolean vkg() {
		for (int i : object2) {
			if (i == -1) {
				return false;
			}
		}
		return true;
	}

	public void void1(int... numbers) {
		for (int i = 0; i < 4; i++) {
			object2[i] = numbers[i];
		}
	}

	public void void2(boolean sevenDays) {
		object4 = sevenDays;
		void3();
	}

	public void void3() {
		object1.getPackets()
				.sendGlobalString(
						344,
						"Your recovery delay has now been set to "
								+ (object4 ? "7" : "3")
								+ " days.<br><br>You would have to wait this long to delete your PIN if you set one and then forgot it.");
	}

	public void void4() {
		object1.getInterfaceManager().sendInterface(14);
		object1.getPackets().sendGlobalConfig(98, -1);
		object1.getPackets().sendIComponentText(14, 32,
				"Do you really wish to set a PIN on your bank account?");
		object1.getPackets().sendIComponentText(14, 34,
				"Yes, I really want a Bank PIN. I will never forget it!");
		object1.getPackets().sendIComponentText(14, 36,
				"No, I might forget it!");
	}

	public void void5() {
		object1.getAttributes().remove("pin_number_stage");
		object3 = new int[] { -1, -1, -1, -1 };
		object1.getPackets().sendConfig(163, 0);
		object1.getPackets().sendGlobalConfig(98, 0);
		object1.getPackets().sendGlobalConfig(199, -1);
		object1.getInterfaceManager().sendInterface(13);
		object1.getPackets().sendInterface(true, 13, 5, 759);
		object1.getPackets().sendIComponentSettings(13, 24, -1, -1, 0);
		object1.getPackets().sendIComponentText(13, 27, "Set new PIN");
		object1.getPackets().sendIComponentText(13, 26,
				"Please choose a new FOUR DIGIT PIN using the buttons below.");
		object1.getAttributes().put("pin_enter_reason", "setfirst");
		void10();
	}

	public void void6() {
		object1.getAttributes().remove("pin_number_stage");
		object1.getPackets().sendConfig(163, 0);
		object1.getPackets().sendGlobalConfig(98, 0);
		object1.getPackets().sendGlobalConfig(199, -1);
		object1.getInterfaceManager().sendInterface(13);
		object1.getPackets().sendInterface(true, 13, 5, 759);
		object1.getPackets().sendIComponentSettings(13, 24, -1, -1, 0);
		object1.getPackets().sendIComponentText(13, 27, "Confirm new PIN");
		object1.getPackets().sendIComponentText(13, 26,
				"Now please enter that number again.");
		object1.getAttributes().put("pin_enter_reason", "setsecond");
		void10();
	}

	public void voi7() {
		object1.getInterfaceManager().sendInterface(14);
		boolean bln45 = object5 > 0;
		boolean bln46 = bln45 && System.currentTimeMillis() < object5;
		int algorithmAdvanced = vkg() && !bln46 ? 1 : bln45 ? 3 : 0;
		if (object4) {
			algorithmAdvanced |= 1024;
		}

		if (bln45 && System.currentTimeMillis() < object5) {
			object1.getPackets()
					.sendGlobalString(
							344,
							"You have requested that a PIN be set on your bank account. This will take effect in 6 days.<br><br>If you wish to cancel this PIN, please use the button on the left.");
		} else {
			object1.getPackets()
					.sendGlobalString(
							344,
							"Customers are reminded that they should NEVER tell anyone their Bank PINs or passwords, nor should they ever enter their PINs on any website form.<br><br>Have you read the PIN guide on the website?");
		}

		object1.getPackets().sendGlobalConfig(98, algorithmAdvanced);
	}

	public void void8() {
		object4 = !object4;
		void3();
	}

	public void void9() {
		object2 = new int[] { -1, -1, -1, -1 };
		object5 = -1l;
	}

	public void handlePinDigit(int parameter, int parameter2) {
		if (object1.getAttributes().get("pin_enter_reason") == null) {
			return;
		}
		String object = (String) object1.getAttributes()
				.get("pin_enter_reason");
		if (object.equals("setfirst")) {
			object3[parameter] = parameter2;
		} else if (object.equals("setsecond")) {
			object3[parameter] = parameter2;
		}
	}

	public void finishPin(int parameter) {
		for (int vkii = 0; vkii < 3; vkii++) {
			if (object3[vkii] == -1) {
				return;
			}
		}
		object3[3] = parameter;
		if (object1.getAttributes().get("entering_pin_to_bank") != null
				&& (boolean) object1.getAttributes().remove(
						"entering_pin_to_bank") == true) {
			if (!Arrays.equals(object2, object3)) {
				object1.getInterfaceManager().closeScreenInterface();
				object1.getDialogueManager().startDialogue(
						"SimpleMessage",
						"You entered " + Arrays.toString(object3)
								+ "; it was not your bank pin!");
				void12(get() + 1);
				if (get() == 3) {
					void13(Utils.currentTimeMillis()
							+ TimeUnit.DAYS.toMillis(1));
				}
				return;
			}
			object1.getInterfaceManager().closeScreenInterface();
			object1.getAttributes().put("entered_bank_pin", true);
			return;
		}
		String object = (String) object1.getAttributes()
				.get("pin_enter_reason");
		if (object.equals("setfirst")) {
			object1.getAttributes().put("first_pin", Arrays.copyOf(object3, 4));
			void6();
		} else if (object.equals("setsecond")) {
			int[] first = (int[]) object1.getAttributes().get("first_pin");
			for (int vkfi = 0; vkfi < first.length; vkfi++) {
				if (object3[vkfi] != first[vkfi]) {
					object3 = new int[] { -1, -1, -1, -1 };
					voi7();
					object1.getPackets()
							.sendGlobalString(
									344,
									"Those numbers did not match.<br><br>Your PIN has not been set; please try again if you wish to set a new PIN.");
					return;
				}
			}
			object5 = 0;
			object1.getPackets()
					.sendGlobalString(
							344,
							"You have requested that a PIN be set on your bank account. This will take effect in 6 days.<br><br>If you wish to cancel this PIN, please use the button on the left.");
			for (int i = 0; i < 4; i++) {
				object2[i] = object3[i];
			}
			voi7();
		}
		object3 = new int[] { -1, -1, -1, -1 };
	}

	public void void10() {
		object1.getPackets().sendConfigByFile(1010, 0);
		object1.getPackets().sendRunScript(1271, 1);
	}

	public void void11() {
		object2 = new int[] { -1, -1, -1, -1 };
		object3 = new int[] { -1, -1, -1, -1 };
		object5 = -1;
		voi7();
		object1.getPackets()
				.sendGlobalString(
						344,
						"The PIN has been cancelled and will NOT be set.<br><br>You still do not have a Bank PIN.");
	}

	public int get() {
		return object7;
	}

	public void void12(int param) {
		this.object7 = param;
	}

	public long get2() {
		return object6;
	}

	public void void13(long param) {
		this.object6 = param;
	}

	public Player vkk() {
		return object1;
	}

	private Player object1;
	private int[] object2 = new int[] { -1, -1, -1, -1 };
	private int[] object3 = new int[] { -1, -1, -1, -1 };
	private boolean object4 = false;
	private long object5 = -1l;
	private long object6;
	private transient int object7;
	private static final long serialVersionUID = -7132860503719848112L;

}
