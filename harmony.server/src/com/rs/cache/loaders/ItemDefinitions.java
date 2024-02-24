package com.rs.cache.loaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.alex.io.OutputStream;
import com.alex.utils.Constants;
import com.rs.cache.Cache;
import com.rs.game.item.Item;
import com.rs.game.player.Equipment;
import com.rs.game.player.Skills;
import com.rs.io.InputStream;
import com.rs.utils.ItemExamines;
import com.rs.utils.Utils;

@SuppressWarnings("unused")
public final class ItemDefinitions {//old

	public static final ItemDefinitions[] itemsDefinitions;

	static { // that's why this is here
		itemsDefinitions = new ItemDefinitions[Utils.getItemDefinitionsSize()];
	}

	public int id;
	public boolean loaded;

	public int modelId;
	public String name;

	// model size information
	public int modelZoom;
	public int modelRotation1;
	public int modelRotation2;
	public int modelOffset1;
	public int modelOffset2;

	// extra information
	public int stackable;
	public int value;
	public boolean membersOnly;

	// wearing model information
	public int maleEquip1;
	public int femaleEquip1;
	public int maleEquip2;
	public int femaleEquip2;

	// options
	public String[] groundOptions;
	public String[] inventoryOptions;

	// model information
	public int[] originalModelColors;
	public int[] modifiedModelColors;
	public short[] originalTextureColors;
	public short[] modifiedTextureColors;
	public byte[] unknownArray1;
	public byte[] unknownArray3;
	public int[] unknownArray2;
	// extra information, not used for newer items
	public boolean unnoted;

	public int maleEquipModelId3;
	public int femaleEquipModelId3;
	public int unknownInt1;
	public int unknownInt2;
	public int unknownInt3;
	public int unknownInt4;
	public int unknownInt5;
	public int unknownInt6;
	public int certId;
	public int certTemplateId;
	public int[] stackIds;
	public int[] stackAmounts;
	public int unknownInt7;
	public int unknownInt8;
	public int unknownInt9;
	public int unknownInt10;
	public int unknownInt11;
	public int teamId;
	public int lendId;
	public int lendTemplateId;
	public int unknownInt12;
	public int unknownInt13;
	public int unknownInt14;
	public int unknownInt15;
	public int unknownInt16;
	public int unknownInt17;
	public int unknownInt18;
	public int unknownInt19;
	public int unknownInt20;
	public int unknownInt21;
	public int unknownInt22;
	public int unknownInt23;
	public int equipSlot;
	public int equipType;
	public int equipType2;

	// extra added
	public boolean noted;
	public boolean lended;

	public HashMap<Integer, Object> clientScriptData;
	public HashMap<Integer, Integer> itemRequiriments;
	public int[] unknownArray5;
	public int[] unknownArray4;
	public byte[] unknownArray6;
	
	 public byte[] encode() {
	        OutputStream stream = new OutputStream();

	        stream.writeByte(1);
	        stream.writeBigSmart(modelId);

	        if (!name.equals("null") && certTemplateId == -1) {
	            stream.writeByte(2);
	            stream.writeString(name);
	        }

	        if (modelZoom != 2000) {
	            stream.writeByte(4);
	            stream.writeShort(modelZoom);
	        }

	        if (modelRotation1 != 0) {
	            stream.writeByte(5);
	            stream.writeShort(modelRotation1);
	        }

	        if (modelRotation2 != 0) {
	            stream.writeByte(6);
	            stream.writeShort(modelRotation2);
	        }

	        if (modelOffset1 != 0) {
	            stream.writeByte(7);
	            int value = modelOffset1 >>= 0;
	            if (value < 0) {
	                value += 65536;
	            }
	            stream.writeShort(value);
	        }

	        if (modelOffset2 != 0) {
	            stream.writeByte(8);
	            int value = modelOffset2 >>= 0;
	            if (value < 0) {
	                value += 65536;
	            }
	            stream.writeShort(value);
	        }

	        if (stackable == 1 && certTemplateId == -1) {
	            stream.writeByte(11);
	        }

	        if (value != 1 && lendTemplateId == -1) {
	            stream.writeByte(12);
	            stream.writeInt(value);
	        }

	        if (equipSlot != -1) {
	            stream.writeByte(13);
	            stream.writeByte(equipSlot);
	        }

	        if (equipType != -1) {
	            stream.writeByte(14);
	            stream.writeByte(equipType);
	        }

	        if (membersOnly && certTemplateId == -1) {
	            stream.writeByte(16);
	        }

	        if (maleEquip1 != -1) {
	            stream.writeByte(23);
	            stream.writeBigSmart(maleEquip1);
	        }

	        if (maleEquip2 != -1) {
	            stream.writeByte(24);
	            stream.writeBigSmart(maleEquip2);
	        }

	        if (femaleEquip1 != -1) {
	            stream.writeByte(25);
	            stream.writeBigSmart(femaleEquip1);
	        }

	        if (femaleEquip2 != -1) {
	            stream.writeByte(26);
	            stream.writeBigSmart(femaleEquip2);
	        }

	        for (int index = 0; index < groundOptions.length; index++) {
	            if (groundOptions[index] == null
	                    || (index == 2 && groundOptions[index].equals("take"))) {
	                continue;
	            }
	            stream.writeByte(30 + index);
	            stream.writeString(groundOptions[index]);
	        }

	        for (int index = 0; index < inventoryOptions.length; index++) {
	            if (inventoryOptions[index] == null
	                    || (index == 4 && inventoryOptions[index].equals("drop"))) {
	                continue;
	            }
	            stream.writeByte(35 + index);
	            stream.writeString(inventoryOptions[index]);
	        }

	        if (originalModelColors != null && modifiedModelColors != null) {
	            stream.writeByte(40);
	            stream.writeByte(originalModelColors.length);
	            for (int index = 0; index < originalModelColors.length; index++) {
	                stream.writeShort(originalModelColors[index]);
	                stream.writeShort(modifiedModelColors[index]);
	            }
	        }

	        if (originalTextureColors != null && modifiedTextureColors != null) {
	            stream.writeByte(41);
	            stream.writeByte(originalTextureColors.length);
	            for (int index = 0; index < originalTextureColors.length; index++) {
	                stream.writeShort(originalTextureColors[index]);
	                stream.writeShort(modifiedTextureColors[index]);
	            }
	        }

	        if (unknownArray1 != null) {
	            stream.writeByte(42);
	            stream.writeByte(unknownArray1.length);
	            for (int index = 0; index < unknownArray1.length; index++) {
	                stream.writeByte(unknownArray1[index]);
	            }
	        }
	        
//	        if (hasNameColor) {
//	        	stream.writeByte(43);
//	        	stream.writeInt(nameColor);
//	        }

	        if (unnoted) {
	            stream.writeByte(65);
	        }

	        if (maleEquipModelId3 != -1) {
	            stream.writeByte(78);
	            stream.writeBigSmart(maleEquipModelId3);
	        }

	        if (femaleEquipModelId3 != -1) {
	            stream.writeByte(79);
	            stream.writeBigSmart(femaleEquipModelId3);
	        }

	        if (unknownInt1 != 0) {
	            stream.writeByte(90);
	            stream.writeBigSmart(unknownInt1);
	        }
	        if (unknownInt2 != 0) {
	            stream.writeByte(91);
	            stream.writeBigSmart(unknownInt2);
	        }
	        if (unknownInt3 != 0) {
	            stream.writeByte(92);
	            stream.writeBigSmart(unknownInt3);
	        }
	        if (unknownInt4 != 0) {
	            stream.writeByte(93);
	            stream.writeBigSmart(unknownInt4);
	        }
	        if (unknownInt5 != 0) {
	            stream.writeByte(95);
	            stream.writeBigSmart(unknownInt5);
	        }
	        if (unknownInt6 != 0) {
	            stream.writeByte(96);
	            stream.writeBigSmart(unknownInt6);
	        }

	        if (certId != -1) {
	            stream.writeByte(97);
	            stream.writeShort(certId);
	        }

	        if (certTemplateId != -1) {
	            stream.writeByte(98);
	            stream.writeShort(certTemplateId);
	        }

	        if (stackIds != null && stackAmounts != null) {
	            for (int index = 0; index < stackIds.length; index++) {
	                if (stackIds[index] == 0 && stackAmounts[index] == 0) {
	                    continue;
	                }
	                stream.writeByte(100 + index);
	                stream.writeShort(stackIds[index]);
	                stream.writeShort(stackAmounts[index]);
	            }
	        }
	        
	        if (unknownInt7 != 128) {
	            stream.writeByte(110);
	            stream.writeShort(unknownInt7);
	        }
	        if (unknownInt8 != 128) {
	            stream.writeByte(111);
	            stream.writeShort(unknownInt8);
	        }
	        if (unknownInt9 != 128) {
	            stream.writeByte(112);
	            stream.writeShort(unknownInt9);
	        }
	        if (unknownInt10 != 0) {
	            stream.writeByte(113);
	            stream.writeByte(unknownInt10);
	        }
	        if (unknownInt11 != 0) {
	            stream.writeByte(114);
	            stream.writeByte(unknownInt11);
	        }

	        if (teamId != 0) {
	            stream.writeByte(115);
	            stream.writeByte(teamId);
	        }

	        if (lendId != -1) {
	            stream.writeByte(121);
	            stream.writeShort(lendId);
	        }

	        if (lendTemplateId != -1) {
	            stream.writeByte(122);
	            stream.writeShort(lendTemplateId);
	        }
	        if (unknownInt12 != 0 && unknownInt13 != 0 && unknownInt14 != 0) {
	            stream.writeByte(125);
	            stream.writeByte(unknownInt12);
	            stream.writeByte(unknownInt13);
	            stream.writeByte(unknownInt14);
	        }
	        if (unknownInt15 != 0 && unknownInt16 != 0 && unknownInt17 != 0) {
	            stream.writeByte(126);
	            stream.writeByte(unknownInt15);
	            stream.writeByte(unknownInt16);
	            stream.writeByte(unknownInt17);
	        }
	        if (unknownInt18 != 0 && unknownInt19 != 0) {
	            stream.writeByte(127);
	            stream.writeByte(unknownInt18);
	            stream.writeShort(unknownInt19);
	        }
	        if (unknownInt20 != 0 && unknownInt21 != 0) {
	            stream.writeByte(128);
	            stream.writeByte(unknownInt20);
	            stream.writeShort(unknownInt21);
	        }
	        if (unknownInt20 != 0 && unknownInt21 != 0) {
	            stream.writeByte(129);
	            stream.writeByte(unknownInt20);
	            stream.writeShort(unknownInt21);
	        }
	        if (unknownInt22 != 0 && unknownInt23 != 0) {
	            stream.writeByte(130);
	            stream.writeByte(unknownInt22);
	            stream.writeShort(unknownInt23);
	        }

	        if (unknownArray2 != null) {
	            stream.writeByte(132);
	            stream.writeByte(unknownArray2.length);
	            for (int index = 0; index < unknownArray2.length; index++) {
	                stream.writeShort(unknownArray2[index]);
	            }
	        }
	        
//	        if (anInt7938 != 0) {
//	        	stream.writeByte(134);
//	        	stream.writeByte(anInt7938);
//	        }
//	        if (bindId != -1) {
//	        	stream.writeByte(139);
//	        	stream.writeShort(bindLink);
//	        }
//	        if (bindTemplate != -1) {
//	        	stream.writeByte(140);
//	        	stream.writeShort(bindTemplate);
//	        }
	        
	        if (unknownArray4 != null) {
	            for (int index = 0; index < unknownArray4.length; index++) {
	                if (unknownArray4[index] == -1) {
	                    continue;
	                }
	                stream.writeByte(142 + index);
	                stream.writeShort(unknownArray4[index]);
	            }
	        }
	        if (unknownArray5 != null) {
	            for (int index = 0; index < unknownArray5.length; index++) {
	                if (unknownArray5[index] == -1) {
	                    continue;
	                }
	                stream.writeByte(150 + index);
	                stream.writeShort(unknownArray5[index]);
	            }
	        }
	        
//	        if (aBool8089) {
//	        	stream.writeByte(157);
//	        }
//	        if (shardLink != -1) {
//	        	stream.writeByte(161);
//	        	stream.writeShort(shardLink);
//	        }
//	        if (shardTemplate != -1) {
//	        	stream.writeByte(162);
//	        	stream.writeShort(shardTemplate);
//	        }
//	        if (shardCombineAmount != 0) {
//	        	stream.writeByte(163);
//	        	stream.writeShort(shardCombineAmount);
//	        }
//	        if (!shardName.equals("null") || shardName != null) {
//	        	stream.writeByte(164);
//	        	stream.writeString(shardName);
//	        }
	        if (stackable == 2)
	        	stream.writeByte(165);
	        
	        if (clientScriptData != null) {
	            stream.writeByte(249);
	            stream.writeByte(clientScriptData.size());
	            for (int key : clientScriptData.keySet()) {
	                Object value = clientScriptData.get(key);
	                stream.writeByte(value instanceof String ? 1 : 0);
	                stream.write24BitInt(key);
	                if (value instanceof String) {
	                    stream.writeString((String) value);
	                } else {
	                    stream.writeInt((Integer) value);
	                }
	            }
	        }
	        // end
	        stream.writeByte(0);

	        byte[] data = new byte[stream.getOffset()];
	        stream.setOffset(0);
	        stream.getBytes(data, 0, data.length);
	        return data;
	    }
	
	 
	public void write() {
		byte[] data = this.encode();
		Cache.getStore().getIndexes()[19].putFile(this.id >>> 8, 0xff & this.id, data);
	}

	public static final ItemDefinitions getItemDefinitions(int itemId) {
		if (itemId < 0 || itemId >= itemsDefinitions.length)
			itemId = 0;
		ItemDefinitions def = itemsDefinitions[itemId];
		if (def == null)
			itemsDefinitions[itemId] = def = new ItemDefinitions(itemId);
		return def;
	}

	public static final void clearItemsDefinitions() {
		for (int i = 0; i < itemsDefinitions.length; i++)
			itemsDefinitions[i] = null;
	}

	public ItemDefinitions(int id) {
		this.id = id;
		setDefaultsVariableValues();
		setDefaultOptions();
		loadItemDefinitions();
	}

	public boolean isLoaded() {
		return loaded;
	}

	public final void loadItemDefinitions() {
		byte[] data = Cache.STORE.getIndexes()[Constants.ITEM_DEFINITIONS_INDEX].getFile(getArchiveId(), getFileId());
		if (data == null) {
			// System.out.println("Failed loading Item " + id+".");
			return;
		}
		readOpcodeValues(new InputStream(data));
		if (certTemplateId != -1)
			toNote();
		if (lendTemplateId != -1)
			toLend();
		if (unknownValue1 != -1)
			toBind();
		loaded = true;
	}
	
	
	
	public void toNote() {
		// ItemDefinitions noteItem; //certTemplateId
		ItemDefinitions realItem = getItemDefinitions(certId);
		membersOnly = realItem.membersOnly;
		value = realItem.value;
		name = realItem.name;
		stackable = 1;
		noted = true;
	}

	public void toBind() {
		// ItemDefinitions lendItem; //lendTemplateId
		ItemDefinitions realItem = getItemDefinitions(unknownValue2);
		originalModelColors = realItem.originalModelColors;
		maleEquipModelId3 = realItem.maleEquipModelId3;
		femaleEquipModelId3 = realItem.femaleEquipModelId3;
		teamId = realItem.teamId;
		value = 0;
		membersOnly = realItem.membersOnly;
		name = realItem.name;
		inventoryOptions = new String[5];
		groundOptions = realItem.groundOptions;
		if (realItem.inventoryOptions != null)
			for (int optionIndex = 0; optionIndex < 4; optionIndex++)
				inventoryOptions[optionIndex] = realItem.inventoryOptions[optionIndex];
		inventoryOptions[4] = "Discard";
		maleEquip1 = realItem.maleEquip1;
		maleEquip2 = realItem.maleEquip2;
		femaleEquip1 = realItem.femaleEquip1;
		femaleEquip2 = realItem.femaleEquip2;
		clientScriptData = realItem.clientScriptData;
		equipSlot = realItem.equipSlot;
		equipType = realItem.equipType;
	}

	public void toLend() {
		// ItemDefinitions lendItem; //lendTemplateId
		ItemDefinitions realItem = getItemDefinitions(lendId);
		originalModelColors = realItem.originalModelColors;
		maleEquipModelId3 = realItem.maleEquipModelId3;
		femaleEquipModelId3 = realItem.femaleEquipModelId3;
		teamId = realItem.teamId;
		value = 0;
		membersOnly = realItem.membersOnly;
		name = realItem.name;
		inventoryOptions = new String[5];
		groundOptions = realItem.groundOptions;
		if (realItem.inventoryOptions != null)
			for (int optionIndex = 0; optionIndex < 4; optionIndex++)
				inventoryOptions[optionIndex] = realItem.inventoryOptions[optionIndex];
		inventoryOptions[4] = "Discard";
		maleEquip1 = realItem.maleEquip1;
		maleEquip2 = realItem.maleEquip2;
		femaleEquip1 = realItem.femaleEquip1;
		femaleEquip2 = realItem.femaleEquip2;
		clientScriptData = realItem.clientScriptData;
		equipSlot = realItem.equipSlot;
		equipType = realItem.equipType;
		lended = true;
	}

	public int getArchiveId() {
		return getId() >>> 8;
	}

	public int getFileId() {
		return 0xff & getId();
	}

	public boolean isDestroyItem() {
		if (inventoryOptions == null)
			return false;
		for (String option : inventoryOptions) {
			if (option == null)
				continue;
			if (option.equalsIgnoreCase("destroy"))
				return true;
		}
		return false;
	}

	public boolean containsOption(int i, String option) {
		if (inventoryOptions == null || inventoryOptions[i] == null
				|| inventoryOptions.length <= i)
			return false;
		return inventoryOptions[i].equals(option);
	}

	public boolean containsOption(String option) {
		if (inventoryOptions == null)
			return false;
		for (String o : inventoryOptions) {
			if (o == null || !o.equals(option))
				continue;
			return true;
		}
		return false;
	}

	public boolean isWearItem() {
		return equipSlot != -1;
	}

	public boolean isWearItem(boolean male) {
		if (equipSlot < Equipment.SLOT_RING
				&& (male ? getMaleWornModelId1() == -1
				: getFemaleWornModelId1() == -1))
			return false;
		return equipSlot != -1;
	}

	public boolean hasSpecialBar() {
		if (clientScriptData == null)
			return false;
		Object specialBar = clientScriptData.get(686);
		if (specialBar != null && specialBar instanceof Integer)
			return (Integer) specialBar == 1;
		return false;
	}
	
	public int getAttackSpeed() {
		if (clientScriptData == null)
			return 4;
		Object attackSpeed = clientScriptData.get(14);
		if (attackSpeed != null && attackSpeed instanceof Integer)
			return (int) attackSpeed;
		return 4;
	}
	
	public int getStabAttack() {
		if (clientScriptData == null)
			return 0;
		Object value = clientScriptData.get(0);
		if (value != null && value instanceof Integer)
			return (int) value;
		return 0;
	}
	
	public int getSlashAttack() {
		if (clientScriptData == null)
			return 0;
		Object value = clientScriptData.get(1);
		if (value != null && value instanceof Integer)
			return (int) value;
		return 0;
	}
	
	public int getCrushAttack() {
		if (clientScriptData == null)
			return 0;
		Object value = clientScriptData.get(2);
		if (value != null && value instanceof Integer)
			return (int) value;
		return 0;
	}
	
	public int getMagicAttack() {
		if (clientScriptData == null)
			return 0;
		Object value = clientScriptData.get(3);
		if (value != null && value instanceof Integer)
			return (int) value;
		return 0;
	}
	
	public int getRangeAttack() {
		if (clientScriptData == null)
			return 0;
		Object value = clientScriptData.get(4);
		if (value != null && value instanceof Integer)
			return (int) value;
		return 0;
	}
	
	public int getStabDef() {
		if (clientScriptData == null)
			return 0;
		Object value = clientScriptData.get(5);
		if (value != null && value instanceof Integer)
			return (int) value;
		return 0;
	}
	
	public int getSlashDef() {
		if (clientScriptData == null)
			return 0;
		Object value = clientScriptData.get(6);
		if (value != null && value instanceof Integer)
			return (int) value;
		return 0;
	}
	
	public int getCrushDef() {
		if (clientScriptData == null)
			return 0;
		Object value = clientScriptData.get(7);
		if (value != null && value instanceof Integer)
			return (int) value;
		return 0;
	}
	
	public int getMagicDef() {
		if (clientScriptData == null)
			return 0;
		Object value = clientScriptData.get(8);
		if (value != null && value instanceof Integer)
			return (int) value;
		return 0;
	}
	
	public int getRangeDef() {
		if (clientScriptData == null)
			return 0;
		Object value = clientScriptData.get(9);
		if (value != null && value instanceof Integer)
			return (int) value;
		return 0;
	}
	
	public int getSummoningDef() {
		if (clientScriptData == null)
			return 0;
		Object value = clientScriptData.get(417);
		if (value != null && value instanceof Integer)
			return (int) value;
		return 0;
	}
	
	public int getAbsorveMeleeBonus() {
		if (clientScriptData == null)
			return 0;
		Object value = clientScriptData.get(967);
		if (value != null && value instanceof Integer)
			return (int) value;
		return 0;
	}
	
	public int getAbsorveMageBonus() {
		if (clientScriptData == null)
			return 0;
		Object value = clientScriptData.get(969);
		if (value != null && value instanceof Integer)
			return (int) value;
		return 0;
	}
	
	public int getAbsorveRangeBonus() {
		if (clientScriptData == null)
			return 0;
		Object value = clientScriptData.get(968);
		if (value != null && value instanceof Integer)
			return (int) value;
		return 0;
	}
	
	public int getStrengthBonus() {
		if (clientScriptData == null)
			return 0;
		Object value = clientScriptData.get(641);
		if (value != null && value instanceof Integer)
			return (int) value / 10;
		return 0;
	}
	
	public int getRangedStrBonus() {
		if (clientScriptData == null)
			return 0;
		Object value = clientScriptData.get(643);
		if (value != null && value instanceof Integer)
			return (int) value / 10;
		return 0;
	}
	
	public int getMagicDamage() {
		if (clientScriptData == null)
			return 0;
		Object value = clientScriptData.get(685);
		if (value != null && value instanceof Integer)
			return (int) value;
		return 0;
	}
	
	public int getPrayerBonus() {
		if (clientScriptData == null)
			return 0;
		Object value = clientScriptData.get(11);
		if (value != null && value instanceof Integer)
			return (int) value;
		return 0;
	}


	public int getRenderAnimId() {
		if (clientScriptData == null)
			return 1426;
		Object animId = clientScriptData.get(644);
		if (animId != null && animId instanceof Integer)
			return (Integer) animId;
		return 1426;
	}

	public int getModelZoom() {
		return modelZoom;
	}

	public int getModelOffset1() {
		return modelOffset1;
	}

	public int getModelOffset2() {
		return modelOffset2;
	}

	public int getQuestId() {
		if (clientScriptData == null)
			return -1;
		Object questId = clientScriptData.get(861);
		if (questId != null && questId instanceof Integer)
			return (Integer) questId;
		return -1;
	}

	public List<Item> getCreateItemRequirements() {
		if (clientScriptData == null)
			return null;
		List<Item> items = new ArrayList<Item>();
		int requiredId = -1;
		int requiredAmount = -1;
		for (int key : clientScriptData.keySet()) {
			Object value = clientScriptData.get(key);
			if (value instanceof String)
				continue;
			if (key >= 538 && key <= 770) {
				if (key % 2 == 0)
					requiredId = (Integer) value;
				else
					requiredAmount = (Integer) value;
				if (requiredId != -1 && requiredAmount != -1) {
					items.add(new Item(requiredId, requiredAmount));
					requiredId = -1;
					requiredAmount = -1;
				}
			}
		}
		return items;
	}

	public HashMap<Integer, Object> getClientScriptData() {
		return clientScriptData;
	}

	public HashMap<Integer, Integer> getWearingSkillRequiriments() {
		if (clientScriptData == null)
			return null;
		if (itemRequiriments == null) {
			HashMap<Integer, Integer> skills = new HashMap<Integer, Integer>();
			for (int i = 0; i < 10; i++) {
				Integer skill = (Integer) clientScriptData.get(749 + (i * 2));
				if (skill != null) {
					Integer level = (Integer) clientScriptData.get(750 + (i * 2));
					if (level != null)
						skills.put(skill, level);
				}
			}
			Integer maxedSkill = (Integer) clientScriptData.get(277);
			if (maxedSkill != null)
				skills.put(maxedSkill, getId() == 19709 ? 120 : 99);
			itemRequiriments = skills;
			if (getId() == 7462)
				itemRequiriments.put(Skills.DEFENCE, 40);
			else if (getId() == 19784 || getId() == 22401 || getId() == 19780) { // Korasi
				itemRequiriments.put(Skills.ATTACK, 78);
				itemRequiriments.put(Skills.STRENGTH, 78);
			} else if (getId() == 20822 || getId() == 20823 || getId() == 20824
					|| getId() == 20825 || getId() == 20826)
				itemRequiriments.put(Skills.DEFENCE, 99);
			else if (name.equals("Dragon defender")) {
				itemRequiriments.put(Skills.ATTACK, 60);
				itemRequiriments.put(Skills.DEFENCE, 60);
			}
		}
		return itemRequiriments;
	}

	public void setDefaultOptions() {
		groundOptions = new String[] { null, null, "take", null, null };
		inventoryOptions = new String[] { null, null, null, null, "drop" };
	}

	public void setDefaultsVariableValues() {
		name = "null";
		maleEquip1 = -1;
		maleEquip2 = -1;
		femaleEquip1 = -1;
		femaleEquip2 = -1;
		modelZoom = 2000;
		lendId = -1;
		lendTemplateId = -1;
		certId = -1;
		certTemplateId = -1;
		unknownInt9 = 128;
		value = 1;
		maleEquipModelId3 = -1;
		femaleEquipModelId3 = -1;
		unknownValue1 = -1;
		unknownValue2 = -1;
		teamId = -1;
		equipSlot = -1;
		equipLookHideSlot2 = -1;
		equipType = -1;
	}

	public final void readValues(InputStream stream, int opcode) {
		if (opcode == 1)
			modelId = stream.readBigSmart();
		else if (opcode == 2)
			name = stream.readString();
		else if (opcode == 4)
			modelZoom = stream.readUnsignedShort();
		else if (opcode == 5)
			modelRotation1 = stream.readUnsignedShort();
		else if (opcode == 6)
			modelRotation2 = stream.readUnsignedShort();
		else if (opcode == 7) {
			modelOffset1 = stream.readUnsignedShort();
			if (modelOffset1 > 32767)
				modelOffset1 -= 65536;
			modelOffset1 <<= 0;
		} else if (opcode == 8) {
			modelOffset2 = stream.readUnsignedShort();
			if (modelOffset2 > 32767)
				modelOffset2 -= 65536;
			modelOffset2 <<= 0;
		} else if (opcode == 11)
			stackable = 1;
		else if (opcode == 12)
			value = stream.readInt();
		else if (opcode == 13) {
			equipSlot = stream.readUnsignedByte();
		} else if (opcode == 14) {
			equipType = stream.readUnsignedByte();
		} else if (opcode == 16)
			membersOnly = true;
		else if (opcode == 18) { // added
			stream.readUnsignedShort();
		} else if (opcode == 23)
			maleEquip1 = stream.readBigSmart();
		else if (opcode == 24)
			maleEquip2 = stream.readBigSmart();
		else if (opcode == 25)
			femaleEquip1 = stream.readBigSmart();
		else if (opcode == 26)
			femaleEquip2 = stream.readBigSmart();
		else if (opcode == 27)
			equipLookHideSlot2 = stream.readUnsignedByte();
		else if (opcode >= 30 && opcode < 35)
			groundOptions[opcode - 30] = stream.readString();
		else if (opcode >= 35 && opcode < 40)
			inventoryOptions[opcode - 35] = stream.readString();
		else if (opcode == 40) {
			int length = stream.readUnsignedByte();
			originalModelColors = new int[length];
			modifiedModelColors = new int[length];
			for (int index = 0; index < length; index++) {
				originalModelColors[index] = stream.readUnsignedShort();
				modifiedModelColors[index] = stream.readUnsignedShort();
			}
		} else if (opcode == 41) {
			int length = stream.readUnsignedByte();
			originalTextureColors = new short[length];
			modifiedTextureColors = new short[length];
			for (int index = 0; index < length; index++) {
				originalTextureColors[index] = (short) stream
						.readUnsignedShort();
				modifiedTextureColors[index] = (short) stream
						.readUnsignedShort();
			}
		} else if (opcode == 42) {
			int length = stream.readUnsignedByte();
			unknownArray1 = new byte[length];
			for (int index = 0; index < length; index++)
				unknownArray1[index] = (byte) stream.readByte();
		} else if (opcode == 44) {
			int length = stream.readUnsignedShort();
			int arraySize = 0;
			for (int modifier = 0; modifier > 0; modifier++) {
				arraySize++;
				unknownArray3 = new byte[arraySize];
				byte offset = 0;
				for (int index = 0; index < arraySize; index++) {
					if ((length & 1 << index) > 0) {
						unknownArray3[index] = offset;
					} else {
						unknownArray3[index] = -1;
					}
				}
			}
		} else if (45 == opcode) {
			int i_97_ =(short) stream.readUnsignedShort();
			int i_98_ = 0;
			for (int i_99_ = i_97_; i_99_ > 0; i_99_ >>= 1)
				i_98_++;
			unknownArray6 = new byte[i_98_];
			byte i_100_ = 0;
			for (int i_101_ = 0; i_101_ < i_98_; i_101_++) {
				if ((i_97_ & 1 << i_101_) > 0) {
					unknownArray6[i_101_] = i_100_;
					i_100_++;
				} else
					unknownArray6[i_101_] = (byte) -1;
			}
		} else if (opcode == 65)
			unnoted = true;
		else if (opcode == 78)
			maleEquipModelId3 = stream.readBigSmart();
		else if (opcode == 79)
			femaleEquipModelId3 = stream.readBigSmart();
		else if (opcode == 90)
			unknownInt1 = stream.readBigSmart();
		else if (opcode == 91)
			unknownInt2 = stream.readBigSmart();
		else if (opcode == 92)
			unknownInt3 = stream.readBigSmart();
		else if (opcode == 93)
			unknownInt4 = stream.readBigSmart();
		else if (opcode == 95)
			unknownInt5 = stream.readUnsignedShort();
		else if (opcode == 96)
			unknownInt6 = stream.readUnsignedByte();
		else if (opcode == 97)
			certId = stream.readUnsignedShort();
		else if (opcode == 98)
			certTemplateId = stream.readUnsignedShort();
		else if (opcode >= 100 && opcode < 110) {
			if (stackIds == null) {
				stackIds = new int[10];
				stackAmounts = new int[10];
			}
			stackIds[opcode - 100] = stream.readUnsignedShort();
			stackAmounts[opcode - 100] = stream.readUnsignedShort();
		} else if (opcode == 110)
			unknownInt7 = stream.readUnsignedShort();
		else if (opcode == 111)
			unknownInt8 = stream.readUnsignedShort();
		else if (opcode == 112)
			unknownInt9 = stream.readUnsignedShort();
		else if (opcode == 113)
			unknownInt10 = stream.readByte();
		else if (opcode == 114)
			unknownInt11 = stream.readByte() * 5;
		else if (opcode == 115)
			teamId = stream.readUnsignedByte();
		else if (opcode == 121)
			lendId = stream.readUnsignedShort();
		else if (opcode == 122)
			lendTemplateId = stream.readUnsignedShort();
		else if (opcode == 125) {
			unknownInt12 = stream.readByte() << 0;
			unknownInt13 = stream.readByte() << 0;
			unknownInt14 = stream.readByte() << 0;
		} else if (opcode == 126) {
			unknownInt15 = stream.readByte() << 0;
			unknownInt16 = stream.readByte() << 0;
			unknownInt17 = stream.readByte() << 0;
		} else if (opcode == 127) {
			unknownInt18 = stream.readUnsignedByte();
			unknownInt19 = stream.readUnsignedShort();
		} else if (opcode == 128) {
			unknownInt20 = stream.readUnsignedByte();
			unknownInt21 = stream.readUnsignedShort();
		} else if (opcode == 129) {
			unknownInt20 = stream.readUnsignedByte();
			unknownInt21 = stream.readUnsignedShort();
		} else if (opcode == 130) {
			unknownInt22 = stream.readUnsignedByte();
			unknownInt23 = stream.readUnsignedShort();
		} else if (opcode == 132) {
			int length = stream.readUnsignedByte();
			unknownArray2 = new int[length];
			for (int index = 0; index < length; index++)
				unknownArray2[index] = stream.readUnsignedShort();
		} else if (opcode == 134) {
			int unknownValue = stream.readUnsignedByte();
		} else if (opcode == 139) {
			unknownValue2 = stream.readUnsignedShort();
		} else if (opcode == 140) {
			unknownValue1 = stream.readUnsignedShort();
		} else if (opcode >= 142 && opcode < 147) {
			if (unknownArray4 == null) {
				unknownArray4 = new int[6];
				Arrays.fill(unknownArray4, -1);
			}
			unknownArray4[opcode - 142] = stream.readUnsignedShort();
		} else if (opcode >= 150 && opcode < 155) {
			if (null == unknownArray5) {
				unknownArray5 = new int[5];
				Arrays.fill(unknownArray5, -1);
			}
			unknownArray5[opcode - 150] = stream.readUnsignedShort();
		} else if (opcode == 142) {
			stream.readByte();
			stream.readByte();
		} else if (opcode == 144) {
			stream.readByte();
			stream.readByte();
			stream.readByte();
		} else if (opcode == 151) {
			stream.readByte();
			stream.readByte();
		} else if (opcode == 249) {
			int length = stream.readUnsignedByte();
			if (clientScriptData == null)
				clientScriptData = new HashMap<Integer, Object>(length);
			for (int index = 0; index < length; index++) {
				boolean stringInstance = stream.readUnsignedByte() == 1;
				int key = stream.read24BitInt();
				Object value = stringInstance ? stream.readString() : stream
						.readInt();
				clientScriptData.put(key, value);
			}
		} else
			throw new RuntimeException("MISSING OPCODE " + opcode
					+ " FOR ITEM " + getId());
	}
	
	 public static ItemDefinitions forName(String name) {
	        for (ItemDefinitions definition : itemsDefinitions) {
	            if (definition.name.equalsIgnoreCase(name)) {
	                return definition;
	            }
	        }
	        return null;
	    }

	  public String getExamine() {
			return ItemExamines.getExamine(new Item(id));
		}

	public String getShopExamine() {
		return ItemExamines.getShopExamine(new Item(id));
	}
	    

	public void setValue(int value) {
			this.value = value;
		}


	public int unknownValue1;
	public int unknownValue2;

	public final void readOpcodeValues(InputStream stream) {
		while (true) {
			int opcode = stream.readUnsignedByte();
			if (opcode == 0)
				break;
			readValues(stream, opcode);
		}
	}

	public String getName() {
		return name;
	}

	public int getFemaleWornModelId1() {
		return femaleEquip1;
	}

	public int getFemaleWornModelId2() {
		return femaleEquip2;
	}

	public int getFemaleWornModelId3() {
		return femaleEquipModelId3;
	}

	public int getMaleWornModelId1() {
		return maleEquip1;
	}

	public int getMaleWornModelId2() {
		return maleEquip2;
	}

	public int getMaleWornModelId3() {
		return maleEquipModelId3;
	}

	public boolean isOverSized() {
		return modelZoom > 6000;
	}

	public boolean isLended() {
		return lended;
	}

	public boolean isMembersOnly() {
		return membersOnly;
	}

	public boolean isStackable() {
		return stackable == 1;
	}

	public boolean isNoted() {
		return noted;
	}

	public int getLendId() {
		return lendId;
	}

	public int getCertId() {
		return certId;
	}

	public int getValue() {
		return value;
	}

	public int getId() {
		return id;
	}

	public int getEquipSlot() {
		return equipSlot;
	}

	public int getEquipType() {
		return equipType;
	}
	public int equipLookHideSlot2;
	public int getEquipType2() {
		return equipLookHideSlot2;
	}
	public int getEquipLookHideSlot2() {
		return equipLookHideSlot2;
	}

	public void setStackable(int newValue) {
		this.stackable = newValue;
	}


		public long getbValue() {
		return value;
	}
}