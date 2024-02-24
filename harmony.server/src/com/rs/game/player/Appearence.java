package com.rs.game.player;

import java.io.Serializable;
import java.util.Arrays;

import com.rs.cache.loaders.ClientScriptMap;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.ItemsEquipIds;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.content.clans.ClansManager;
import com.rs.io.OutputStream;
import com.rs.utils.Utils;

public class Appearence implements Serializable {

	private static final long serialVersionUID = 7655608569741626586L;

	private transient int renderEmote;
	private int title;
	private String title1;
	private int[] lookI;
	private byte[] colour;
	private boolean male;
	private transient boolean glowRed;
	private transient byte[] appeareanceData;
	private transient byte[] md5AppeareanceDataHash;
	private transient short transformedNpcId;

	private transient boolean hidePlayer;
	public int cr, cg, cb, ca, ci;
	public static boolean ce;

	private transient Player player;

	public Appearence() {
		male = true;
		renderEmote = -1;
		title = -1;
		resetAppearence();
	}

	public void setGlowRed(boolean glowRed) {
		this.glowRed = glowRed;
		generateAppearenceData();
	}

	public void setPlayer(Player player) {
		this.player = player;
		transformedNpcId = -1;
		renderEmote = -1;
		if (lookI == null) {
			resetAppearence();
		}
	}

	public void transformIntoNPC(int id) {
		transformedNpcId = (short) id;
		generateAppearenceData();
	}

	public void switchHidden() {
		hidePlayer = !hidePlayer;
		generateAppearenceData();
	}

	public boolean isHidden() {
		return hidePlayer;
	}

	public boolean isGlowRed() {
		return glowRed;
	}

	/**
	 * Checks if the given slot matches a specific equipment slot
	 * 
	 * @param slotId
	 * @return true if slot id matches
	 */
	private boolean isOverrideSlot(int slotId) {
		return slotId == 0 || slotId == 1 || slotId == 3 || slotId == 4 || slotId == 7 || slotId == 9 || slotId == 10;
	}

	public void generateAppearenceData() {
		OutputStream stream = new OutputStream();
		cr = 0;
		cg = 0;
		cb = 0;
		ce = false;
		ca = 0;
		ci = 0;
		int flag = 0;
		if (!male) {
			flag |= 0x1;
		}
		if (transformedNpcId >= 0 && NPCDefinitions.getNPCDefinitions(transformedNpcId).aBoolean3190) {
			flag |= 0x2;
		}
		if (title != 0) {
			flag |= title >= 32 && title <= 58 || title == 22909 || title == 22912 || title == 22915 || title == 22916 || title == 22921 || title == 22922 || title == 22918 || title == 22919 || title >= 200 && title <= 206 ? 0x80 : 0x40; // after/before
		}
		stream.writeByte(flag);
		if (title != 0) {
			String titleName = title == 204 ? " <col=00FFFF><shad=FFFFFF> defender of Harmony</col></shad>"
					/**
					 * Recycle titles
					 */
					: title == 22908 ? "<col=0FF1E7>Recycled </col>"
							: title == 22910 ? "<col=1DF10F>Eco-friendly </col>"
									: title == 22911 ? "<col=DC0FF1>Biodegradable </col>"
											: title == 22909 ? "<col=F1650F> the Regenerated</col>"
													: title == 22912 ? "<col=42F10F><shad=FFFFFF> the Toxic</shad></col>"
															: title == 22913 ? "<col=ff0000><shad=000000>Xmas 2018 </shad></col>"
																	: title == 22914 ? "<col=E622E9><shad=000000>Experience</col><col=22E92E> Master </shad></col>"
																			: title == 22915 ? ",<col=22E92E> Defender of Sliske</col>"
																					: title == 22916 ? "<col=22B6E9> the <col=7F22E9>Luckiest</col>"
																							: title == 22917 ? "<col=FFFFFF> the Toilet Master</col>"
																									: title == 22918 ? "<col=582E2E> the Egg Collector</col>"
																											: title == 22919 ? "<col=fafed3><shad=5a4c3e> the Bunny Killer</shad></col>"
																													: title == 22920 ? "<col=6E2C00><shad=ffffff>Raids <col=B70FEB>master </shad></col>" //500 both raids
																															: title == 22921 ? "<col=ff0000> the Blood</shad></col>" //500 tob
																																	: title == 22922 ? "<col=84EB0F> of the Chambers</shad></col>" //500 cox
																																			: title == 22923 ? "<col=EB840F>Raider </shad></col>" //100 total cox/tob
																																					
															/**
															 * Other titles
															 */
																																					: title == 22924 ? "<col=EBEBEB>King Baldy </col>"
																																							: title == 22925 ? "<col=ff0000><shad=ABABAB>The Dry </shad></col>"
					: title == 22904 ? " <col=D00000> Sun killer</col>"
							: title == 22905 ? " <col=800080> Lieutenant Commander</col>"
									: title == 22906 ? " <col=006666> Warmonger</col>"
											: title == 22907 ? "<col=58388D>Mummified </col>"
													: title == 200 ? " <col=D00000> Defeater of Satan</col>"
															: title == 201 ? " <col=330000> Defeater of the Undead</col>"
																	: title == 202 ? " <col=FFCCCC> Defeater of the Osteology</col>"
																			: title == 203 ? " <col=33CCFF> Warrior of the Calamity</col>"
																					: title == 205 ? " <col=BBB033> the Mash Man</col>"
																							: title == 206 ? " <col=EEEA93> the Light</col>"
																									: title == 34 ? " <col=FFFF00> the Completionist</col>"
																											: title == 35 ? " <col=006600> of Bandos</col>"
																													: title == 36 ? " <col=0066CC> of Saradomin</col>"
																															: title == 37 ? " <col=6666FF> of Armadyl</col>"
																																	: title == 38 ? " <col=993300> of Zamorak</col>"
																																			: title == 56 ? " <col=CC0000> of Christmas</col>"
																																					: title == 55 ? " <col=FFFF00><shad=FF00FF> the Thunderous</col></shad>"
																																							: title == 39 ? " <col=CC6633> Slayer of gods</col>"
																																									: title == 33 ? " <col=CC0099> The Dreamer </col>"
																																											: title == 40 ? " <col=00FFFF> Is So Pro </col>"
																																													: title == 32 ? " <col=00FFFF> the Defeater </col>"
																																															: title == 58 ? "<col=00FFFF> the Heroic</col>"
																																																	: title == 57 ? " <col=990066><shad=99FFFF> the Dice Host </col></shad>"
																																																			: title == 59 ? "<col=C12006>Pedobear </col>"
																																																					: title == 60 ? "<col=C12006>Yt'Haar </col>"
																																																							: title == 61 ? "<col=008000>Harmony addict </col>"
																																																									: title == 66 ? "<col=FF0000><shad=600000>Final Boss </shad></col>"
																																																											: title == 67 ? "<col=F16F0F><shad=600000>Insane Final Boss </shad></col>"
																																																											: title == 62 ? "<col=800080>Sexy </col>"
																																																													: title == 1610 ? "<col=660066>Hardcore </col>"
																																																															: title == 192384 ? "<col=FFFF00>Forum Admin </col>"
																																																																	: title == 192385 ? "<col=A35B05>Chocolate Chip </col>"
																																																																			: title == 1390 ? "<col=3300CC>World heavyweight champion </col>"
																																																																					: title == 1391 ? "<col=ff0000><shad=000000>Absolute God </shad></col>"
																																																																							: title == 63 ? "<col=ff00ff>The Dictator </col>"
																																																																									: title == 64 ? "<col=ff0000>The Baws </col>"
																																																																											: title == 9861 ? "<col=787878>Ironman </col>"
																																																																													: title == 9862 ? "<col=787878>Duo </col>"
																																																																													: title == 65 ? "<col=ff0000>The Beautiful </col> "
																																																																															: title == 80 ? "<col=580000>Maxed </col>"
																																																																																	: title == 703 ? "<col=00FFFF>Loyalty Master </col>"
																																																																																			: title == 704 ? "<col=990066>I love </col>"
																																																																																					: title == 709 ? "<col=660066>Predator </col>"
																																																																																							: title == 705 ? "<col=990066>Skiller </col>"
																																																																																									: title == 706 ? "<col=8B4513>IpBanned </col>"
																																																																																											: title == 1611 ? "<col=558035>Developer "
																																																																																													: title == 1612 ? "<col=ff0000>Owner "
																																																																																															: title == 1613 ? "<col=bf00ff>Moderator "
																																																																																															: title == 69 ? "<col=00CC66>Easy</col> "
																																																																																																	: title == 70 ? "<col=0000FF>Average</col> "
																																																																																																			: title == 68 ? "<col=33CCCC>The</col><col=3366FF> exclusive</col> "
																																																																																																					: title == 676 ? "<col=C12006>Prestige One </col>"
																																																																																																							: title == 6575 ? "<col=C12006>Prestige Two </col>"
																																																																																																									: title == 1657 ? "<col=C12006>Prestige Three </col>"
																																																																																																											: title == 345 ? "<col=C12006>Prestige Four </col>"
																																																																																																													: title == 346 ? "<col=C12006>Prestige Five </col>"
																																																																																																															: title == 347 ? "<col=C12006>Prestige Six </col>"
																																																																																																																	: title == 348 ? "<col=C12006>Prestige Seven </col>"
																																																																																																																			: title == 349 ? "<col=C12006>Prestige Eight </col>"
																																																																																																																					: title == 350 ? "<col=C12006>Prestige Nine </col>"
																																																																																																																							: title == 999 ? "<col=C12006>Prestige Master </col>"
																																																																																																																									: title == 701 ? "<col=C12006>Player </col>"
																																																																																																																											: title == 9182 ? "<col=ff0000><shad=ffffff>Harmony's best </shad></col>"
																																																																																																																													: title == 7912 ? "<shad=FFFFFF><col=0000CC>Mr</col> <col=00CC66>De</col><col=CC6600>li</col><col=66CCCC>ci</col><col=FF0000>o</col><col=FF00FF>us</col></shad> "
																																																																																																																															: title == 813700 ? "<col=33CC66>Sigil </col>"
																																																																																																																																	: title == 813745 ? "<col=300000>The Dungeoneer </col>"
																																																																																																																																			: title == 813791 ? "<col=300000>Co</col><col=FF0000>nn</col><col=0000FF>or</col><col=00FF00>s M</col><col=FFFF00>in</col><col=FF00FF>io</col><col=00FFFF>n "
																																																																																																																																					: title == 813712 ? "<col=FF0000>Master Of Jad </col>"
																																																																																																																																							: title == 813713 ? "<col=33FF49>Sandwich <col=DEE80E>boi </col>"
																																																																																																																																									: title == 813709 ? "<col=FF0000>I</col><col=000000>m</col><col=FFFF00>m</col><col=FFFFFF>o</col><col=00FFFF>r</col><col=FF0000>t</col><col=000000>a</col><col=FFFF00>l </col>" : ClientScriptMap.getMap(male ? 1093 : 3872).getStringValue(title);
			stream.writeGJString(titleName);

		}
		if (player.ispvpmode == true) {
			stream.writeByte(player.hasSkull() ? player.getSkullId() : 3);
		} else {
			stream.writeByte(player.hasSkull() ? player.getSkullId() : -1);
		}
		stream.writeByte(player.getPrayer().getPrayerHeadIcon()); // prayer icon
		stream.writeByte(hidePlayer ? 1 : 0);
		// npc
		if (transformedNpcId >= 0) {
			stream.writeShort(-1); // 65535 tells it a npc
			stream.writeShort(transformedNpcId);
			stream.writeByte(0);
		} else {// i undid everything i just added the cosmetics handling code
				// if ahu awefisondme , cheers buddy
			Item[] cosmetics = player.getEquipment().getCosmeticItems().getItems();
			Item[] items = new Item[15];
			boolean[] skipLook = new boolean[items.length];

			boolean[] hiddenIndex = player.getEquipment().getHiddenSlots();
			for (int index = 0; index < items.length; index++) {
				Item item = player.getEquipment().isCanDisplayCosmetic() ? cosmetics[index] : null;

				if (item == null) {
					item = player.getEquipment().getItems().get(index);
				}

				if (hiddenIndex[index] && player.getEquipment().isCanDisplayCosmetic()) {
					item = null;
				}

				if (item != null) {
					items[index] = item;
					int skipSlotLook = item.getDefinitions().getEquipType();
					if (skipSlotLook != -1) {
						skipLook[skipSlotLook] = true;
					}
					int skipSlotLook2 = item.getDefinitions().getEquipType2();
					if (skipSlotLook2 != -1) {
						skipLook[skipSlotLook2] = true;
					}
				}
			}

			for (int index = 0; index < 4; index++) {
				Item item = items[index];
				if (glowRed) {
					if (index == 0) {
						stream.writeShort(32768 + ItemsEquipIds.getEquipId(2910));
						continue;
					}
					if (index == 1) {
						stream.writeShort(32768 + ItemsEquipIds.getEquipId(14641));
						continue;
					}
				}
				if (item == null) {
					stream.writeByte(0);
				} else {
					stream.writeShort(32768 + item.getEquipId());
				}
			}
			Item item = items[Equipment.SLOT_CHEST];
			stream.writeShort(item == null ? 0x100 + lookI[2] : 32768 + item.getEquipId());
			item = items[Equipment.SLOT_SHIELD];
			if (item == null) {
				stream.writeByte(0);
			} else {
				stream.writeShort(32768 + item.getEquipId());
			}
			item = items[Equipment.SLOT_CHEST];
			if (item == null || !Equipment.hideArms(item)) {
				stream.writeShort(0x100 + lookI[3]);
			} else {
				stream.writeByte(0);
			}
			item = items[Equipment.SLOT_LEGS];
			stream.writeShort(glowRed ? 32768 + ItemsEquipIds.getEquipId(2908) : item == null ? 0x100 + lookI[5] : 32768 + item.getEquipId());
			item = items[Equipment.SLOT_HAT];
			if (!glowRed && (item == null || !Equipment.hideHair(item))) {
				stream.writeShort(0x100 + lookI[0]);
			} else {
				stream.writeByte(0);
				
			}
			item = items[Equipment.SLOT_HANDS];
			stream.writeShort(glowRed ? 32768 + ItemsEquipIds.getEquipId(2912) : item == null ? 0x100 + lookI[4] : 32768 + item.getEquipId());
			item = items[Equipment.SLOT_FEET];
			stream.writeShort(glowRed ? 32768 + ItemsEquipIds.getEquipId(2904) : item == null ? 0x100 + lookI[6] : 32768 + item.getEquipId());
			// tits for female, bear for male
			item = items[male ? Equipment.SLOT_HAT : Equipment.SLOT_CHEST];
			if (item == null || male && Equipment.showBear(item)) {
				stream.writeShort(0x100 + lookI[1]);
			} else {
				stream.writeByte(0);
			}
			item = items[Equipment.SLOT_AURA];
			if (item == null) {
				stream.writeByte(0);
			} else {
				stream.writeShort(32768 + item.getEquipId()); // Fixes the
				
			}
			// winged auras
			// lookIing
			// fucked.
			OutputStream streamModify = new OutputStream();
			int modifyFlag = 0;
			int slotIndex = -1;
			ItemModify[] modify = generateItemModify(items, cosmetics);
			for (int index = 0; index < modify.length; index++) {
				if (Equipment.DISABLED_SLOTS[index] != 0) {
					continue;
				}
				slotIndex++;
				ItemModify im = modify[index];
				if (im == null) {
					continue;
				}
				modifyFlag |= 1 << slotIndex;
				int itemFlag = 0;
				OutputStream streamItem = new OutputStream();
				if (im.maleModelId1 != -1 || im.femaleModelId1 != -1) {
					itemFlag |= 0x1;
					streamItem.writeBigSmart(im.maleModelId1);
					streamItem.writeBigSmart(im.femaleModelId1);
					if (im.maleModelId2 != -2 || im.femaleModelId2 != -2) {
						streamItem.writeBigSmart(im.maleModelId2);
						streamItem.writeBigSmart(im.femaleModelId2);
					}
					if (im.maleModelId3 != -2 || im.femaleModelId3 != -2) {
						streamItem.writeBigSmart(im.maleModelId3);
						streamItem.writeBigSmart(im.femaleModelId3);
					}
				}
				if (im.colors != null) {
					itemFlag |= 0x4;
					streamItem.writeShort(0 | 1 << 4 | 2 << 8 | 3 << 12);
					for (int i = 0; i < 4; i++) {
						streamItem.writeShort(im.colors[i]);
					}
				}
				if (im.textures != null) {
					itemFlag |= 0x8;
					streamItem.writeByte(0 | 1 << 4);
					for (int i = 0; i < 2; i++) {
						streamItem.writeShort(im.textures[i]);
					}
				}
				streamModify.writeByte(itemFlag);
				streamModify.writeBytes(streamItem.getBuffer(), 0, streamItem.getOffset());
			}
			stream.writeShort(modifyFlag);
			stream.writeBytes(streamModify.getBuffer(), 0, streamModify.getOffset());
		}
		for (byte element : colour) {
			// colour length 10
			stream.writeByte(element);
		}

		stream.writeShort(getRenderEmote());
		stream.writeString(player.getDisplayName());
		boolean pvpArea = World.isPvpArea(player);
		stream.writeByte(pvpArea ? player.getSkills().getCombatLevel() : player.getSkills().getCombatLevelWithSummoning());
		stream.writeByte(pvpArea ? player.getSkills().getCombatLevelWithSummoning() : 0);
		stream.writeByte(-1); // higher level acc name appears in front :P
		stream.writeByte(transformedNpcId >= 0 ? 1 : 0); // to end here else id
															// need to send more
															// data
		if (transformedNpcId >= 0) {
			NPCDefinitions defs = NPCDefinitions.getNPCDefinitions(transformedNpcId);
			stream.writeShort(defs.anInt876);
			stream.writeShort(defs.anInt842);
			stream.writeShort(defs.anInt884);
			stream.writeShort(defs.anInt875);
			stream.writeByte(defs.anInt875);
			if (ce) {
				stream.writeByte(1);
				stream.writeByte(cr);
				stream.writeByte(cg);
				stream.writeByte(cb);
				stream.writeByte(ci);
				stream.writeByte(ca);
			} else {
				stream.writeByte(0);
			}
		}

		// done separated for safe because of synchronization
		byte[] appeareanceData = new byte[stream.getOffset()];
		System.arraycopy(stream.getBuffer(), 0, appeareanceData, 0, appeareanceData.length);
		byte[] md5Hash = Utils.encryptUsingMD5(appeareanceData);
		this.appeareanceData = appeareanceData;
		md5AppeareanceDataHash = md5Hash;
		
	}

	private ItemModify[] generateItemModify(Item[] items, Item[] cosmetics) {
		ItemModify[] modify = new ItemModify[15];
		for (int slotId = 0; slotId < modify.length; slotId++) {
			if (items[slotId] != null && items[slotId] == cosmetics[slotId]) {
				int[] colors = new int[4];
				colors[0] = player.getEquipment().getCostumeColor();
				colors[1] = colors[0] + 12;
				colors[2] = colors[1] + 12;
				colors[3] = colors[2] + 12;
				setItemModifyColor(items[slotId], slotId, modify, colors);
			} else {
				int id = items[slotId] == null ? -1 : items[slotId].getId();
				if (id == 20768 || id == 20770 || id == 20772 || id == 20767 || id == 20769 || id == 20771) {
					setItemModifyColor(items[slotId], slotId, modify, id == 20768 || id == 20767 ? player.getMaxedCapeCustomized() : player.getCompletionistCapeCustomized());
				} else if (id == 20708 || id == 20709) {
					ClansManager manager = player.getClanManager();
					if (manager == null) {
						continue;
					}
					int[] colors = manager.getClan().getMottifColors();
					setItemModifyColor(items[slotId], slotId, modify, colors);
					setItemModifyTexture(items[slotId], slotId, modify, new short[] { (short) ClansManager.getMottifTexture(manager.getClan().getMottifTop()), (short) ClansManager.getMottifTexture(manager.getClan().getMottifBottom()) });
				} else if (player.getAuraManager().isActivated() && slotId == Equipment.SLOT_AURA) {
					int auraId = player.getEquipment().getAuraId();
					if (auraId == -1) {
						continue;
					}
					int modelId = player.getAuraManager().getAuraModelId();
					int modelId2 = player.getAuraManager().getAuraModelId2();
					setItemModifyModel(items[slotId], slotId, modify, modelId, modelId, modelId2, modelId2, -1, -1);
				}
			}

		}
		return modify;
	}

	private void setItemModifyModel(Item item, int slotId, ItemModify[] modify, int maleModelId1, int femaleModelId1, int maleModelId2, int femaleModelId2, int maleModelId3, int femaleModelId3) {
		ItemDefinitions defs = item.getDefinitions();
		if (defs.getMaleWornModelId1() == -1 || defs.getFemaleWornModelId1() == -1) {
			return;
		}
		if (modify[slotId] == null) {
			modify[slotId] = new ItemModify();
		}
		modify[slotId].maleModelId1 = maleModelId1;
		modify[slotId].femaleModelId1 = femaleModelId1;
		if (defs.getMaleWornModelId2() != -1 || defs.getFemaleWornModelId2() != -1) {
			modify[slotId].maleModelId2 = maleModelId2;
			modify[slotId].femaleModelId2 = femaleModelId2;
		}
		if (defs.getMaleWornModelId3() != -1 || defs.getFemaleWornModelId3() != -1) {
			modify[slotId].maleModelId2 = maleModelId3;
			modify[slotId].femaleModelId2 = femaleModelId3;
		}
	}

	private void setItemModifyTexture(Item item, int slotId, ItemModify[] modify, short[] textures) {
		ItemDefinitions defs = item.getDefinitions();
		if (defs.originalTextureColors == null || defs.originalTextureColors.length != textures.length) {
			return;
		}
		if (Arrays.equals(textures, defs.originalTextureColors)) {
			return;
		}
		if (modify[slotId] == null) {
			modify[slotId] = new ItemModify();
		}
		modify[slotId].textures = textures;
	}

	private void setItemModifyColor(Item item, int slotId, ItemModify[] modify, int[] colors) {
		ItemDefinitions defs = item.getDefinitions();
		if (defs.originalModelColors == null || defs.originalModelColors.length != colors.length) {
			return;
		}
		if (Arrays.equals(colors, defs.originalModelColors)) {
			return;
		}
		if (modify[slotId] == null) {
			modify[slotId] = new ItemModify();
		}
		modify[slotId].colors = colors;
	}

	private static class ItemModify {

		private int[] colors;
		private short[] textures;
		private int maleModelId1;
		private int femaleModelId1;
		private int maleModelId2;
		private int femaleModelId2;
		private int maleModelId3;
		private int femaleModelId3;

		private ItemModify() {
			maleModelId1 = femaleModelId1 = -1;
			maleModelId2 = femaleModelId2 = -2;
			maleModelId3 = femaleModelId3 = -2;
		}
	}

	public int getSize() {
		if (transformedNpcId >= 0) {
			return NPCDefinitions.getNPCDefinitions(transformedNpcId).size;
		}
		return 1;
	}

	public void setRenderEmote(int id) {
		renderEmote = id;
		generateAppearenceData();
	}

	public void setRenderEmote(int id, boolean refresh) {
		renderEmote = id;
		if (refresh) {
			generateAppearenceData();
		}
	}

	public int getRenderEmote() {
		if (renderEmote >= 0) {
			return renderEmote;
		}
		if (transformedNpcId >= 0) {
			return NPCDefinitions.getNPCDefinitions(transformedNpcId).renderEmote;
		}
		return player.getEquipment().getWeaponRenderEmote();
	}

	public void resetAppearence() {
		lookI = new int[7];
		colour = new byte[10];
		male();
	}

	public void male() {
		lookI[0] = 3; // Hair
		lookI[1] = 14; // Beard
		lookI[2] = 18; // Torso
		lookI[3] = 26; // Arms
		lookI[4] = 34; // Bracelets
		lookI[5] = 38; // Legs
		lookI[6] = 42; // Shoes~

		colour[2] = 16;
		colour[1] = 16;
		colour[0] = 3;
		male = true;
	}

	public void female() {
		lookI[0] = 48; // Hair
		lookI[1] = 57; // Beard
		lookI[2] = 57; // Torso
		lookI[3] = 65; // Arms
		lookI[4] = 68; // Bracelets
		lookI[5] = 77; // Legs
		lookI[6] = 80; // Shoes

		colour[2] = 16;
		colour[1] = 16;
		colour[0] = 3;
		male = false;
	}

	public byte[] getAppeareanceData() {
		return appeareanceData;
	}

	public byte[] getMD5AppeareanceDataHash() {
		return md5AppeareanceDataHash;
	}

	public boolean isMale() {
		return male;
	}

	public void setLook(int i, int i2) {
		lookI[i] = i2;
	}

	public void setColor(int i, int i2) {
		colour[i] = (byte) i2;
	}

	public void setMale(boolean male) {
		this.male = male;
	}

	public void setHairStyle(int i) {
		lookI[0] = i;
	}

	public void setTopStyle(int i) {
		lookI[2] = i;
	}

	public int getTopStyle() {
		return lookI[2];
	}

	public void setArmsStyle(int i) {
		lookI[3] = i;
	}

	public void setWristsStyle(int i) {
		lookI[4] = i;
	}

	public void setLegsStyle(int i) {
		lookI[5] = i;
	}

	public int getHairStyle() {
		return lookI[0];
	}

	public void setBeardStyle(int i) {
		lookI[1] = i;
	}

	public int getBeardStyle() {
		return lookI[1];
	}

	public void setFacialHair(int i) {
		lookI[1] = i;
	}

	public int getFacialHair() {
		return lookI[1];
	}

	public void setSkinColor(int color) {
		colour[4] = (byte) color;
	}

	public int getSkinColor() {
		return colour[4];
	}

	public void setHairColor(int color) {
		colour[0] = (byte) color;
	}

	public void setTopColor(int color) {
		colour[1] = (byte) color;
	}

	public void setLegsColor(int color) {
		colour[2] = (byte) color;
	}

	public int getHairColor() {
		return colour[0];
	}

	public void setTitle1(String string) {
		title1 = string;
		generateAppearenceData();
	}

	public void setTitle(int title) {
		this.title = title;
		generateAppearenceData();
	}

}
