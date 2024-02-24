package com.rs.game.player.dialogues;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rs.utils.Logger;

public final class DialogueHandler {

	private static final HashMap<Object, Class<Dialogue>> handledDialogues = new HashMap<Object, Class<Dialogue>>();

	private static List<Class> findClasses(File directory, String packageName)
		    throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
		    return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
		    if (file.isDirectory()) {
			assert !file.getName().contains(".");
			classes.addAll(findClasses(file,
				packageName + "." + file.getName()));
		    } else if (file.getName().endsWith(".class")) {
			classes.add(Class.forName(packageName
				+ '.'
				+ file.getName().substring(0,
					file.getName().length() - 6)));
		    }
		}
		return classes;
	    }
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static final void init() {
    	initImpl();
    	initConf();
    	initConstr();
    	initDyes();
    	initEcoQuest();
    	initHM();
    	initInstances();
    	initHween();
    	initRue();
    	initProp();
    	initTDD();
    	initTheTruth();
    	initSA();
    	initEaster();
	    Logger.log("DialogueHandler", handledDialogues.size()+" dialogues initiated..");
    }

    public static void initImpl() {
    	String fileLoc = "bin/com/rs/game/player/dialogues/impl";
    	String packageDir = "com.rs.game.player.dialogues.impl";
    	try {
    	    List<Class> files = findClasses(new File(fileLoc), packageDir);
    	    for (Class<Dialogue> c : files) {
    		if (Dialogue.class.isAssignableFrom(c)) {
    		    handledDialogues.put("" + c.getSimpleName() + "",
    			    (Class<Dialogue>) Class.forName(c
    				    .getCanonicalName()));
    		}
    	    }
    	    
    	} catch (ClassNotFoundException e) {
    	    e.printStackTrace();
    	}
    }
    
    public static void initConf() {
    	String fileLoc = "bin/com/rs/game/player/dialogues/confirmations";
    	String packageDir = "com.rs.game.player.dialogues.confirmations";
    	try {
    	    List<Class> files = findClasses(new File(fileLoc), packageDir);
    	    for (Class<Dialogue> c : files) {
    		if (Dialogue.class.isAssignableFrom(c)) {
    		    handledDialogues.put("" + c.getSimpleName() + "",
    			    (Class<Dialogue>) Class.forName(c
    				    .getCanonicalName()));
    		}
    	    }
    	    
    	} catch (ClassNotFoundException e) {
    	    e.printStackTrace();
    	}
    }
    
    public static void initConstr() {
    	String fileLoc = "bin/com/rs/game/player/dialogues/construction";
    	String packageDir = "com.rs.game.player.dialogues.construction";
    	try {
    	    List<Class> files = findClasses(new File(fileLoc), packageDir);
    	    for (Class<Dialogue> c : files) {
    		if (Dialogue.class.isAssignableFrom(c)) {
    		    handledDialogues.put("" + c.getSimpleName() + "",
    			    (Class<Dialogue>) Class.forName(c
    				    .getCanonicalName()));
    		}
    	    }
    	    
    	} catch (ClassNotFoundException e) {
    	    e.printStackTrace();
    	}
    }
    
    public static void initDyes() {
    	String fileLoc = "bin/com/rs/game/player/dialogues/dyes";
    	String packageDir = "com.rs.game.player.dialogues.dyes";
    	try {
    	    List<Class> files = findClasses(new File(fileLoc), packageDir);
    	    for (Class<Dialogue> c : files) {
    		if (Dialogue.class.isAssignableFrom(c)) {
    		    handledDialogues.put("" + c.getSimpleName() + "",
    			    (Class<Dialogue>) Class.forName(c
    				    .getCanonicalName()));
    		}
    	    }
    	    
    	} catch (ClassNotFoundException e) {
    	    e.printStackTrace();
    	}
    }
    
    public static void initEcoQuest() {
    	String fileLoc = "bin/com/rs/game/player/dialogues/economicbreakdown";
    	String packageDir = "com.rs.game.player.dialogues.economicbreakdown";
    	try {
    	    List<Class> files = findClasses(new File(fileLoc), packageDir);
    	    for (Class<Dialogue> c : files) {
    		if (Dialogue.class.isAssignableFrom(c)) {
    		    handledDialogues.put("" + c.getSimpleName() + "",
    			    (Class<Dialogue>) Class.forName(c
    				    .getCanonicalName()));
    		}
    	    }
    	    
    	} catch (ClassNotFoundException e) {
    	    e.printStackTrace();
    	}
    }

    public static void initHM() {
    	String fileLoc = "bin/com/rs/game/player/dialogues/HM";
    	String packageDir = "com.rs.game.player.dialogues.HM";
    	try {
    	    List<Class> files = findClasses(new File(fileLoc), packageDir);
    	    for (Class<Dialogue> c : files) {
    		if (Dialogue.class.isAssignableFrom(c)) {
    		    handledDialogues.put("" + c.getSimpleName() + "",
    			    (Class<Dialogue>) Class.forName(c
    				    .getCanonicalName()));
    		}
    	    }
    	    
    	} catch (ClassNotFoundException e) {
    	    e.printStackTrace();
    	}
    }

    public static void initInstances() {
    	String fileLoc = "bin/com/rs/game/player/dialogues/instances";
    	String packageDir = "com.rs.game.player.dialogues.instances";
    	try {
    	    List<Class> files = findClasses(new File(fileLoc), packageDir);
    	    for (Class<Dialogue> c : files) {
    		if (Dialogue.class.isAssignableFrom(c)) {
    		    handledDialogues.put("" + c.getSimpleName() + "",
    			    (Class<Dialogue>) Class.forName(c
    				    .getCanonicalName()));
    		}
    	    }
    	    
    	} catch (ClassNotFoundException e) {
    	    e.printStackTrace();
    	}
    }
    
    public static void initHween() {
    	String fileLoc = "bin/com/rs/game/player/dialogues/hween";
    	String packageDir = "com.rs.game.player.dialogues.hween";
    	try {
    	    List<Class> files = findClasses(new File(fileLoc), packageDir);
    	    for (Class<Dialogue> c : files) {
    		if (Dialogue.class.isAssignableFrom(c)) {
    		    handledDialogues.put("" + c.getSimpleName() + "",
    			    (Class<Dialogue>) Class.forName(c
    				    .getCanonicalName()));
    		}
    	    }
    	    
    	} catch (ClassNotFoundException e) {
    	    e.printStackTrace();
    	}
    }
    
    public static void initRue() {
    	String fileLoc = "bin/com/rs/game/player/dialogues/ruesaltar";
    	String packageDir = "com.rs.game.player.dialogues.ruesaltar";
    	try {
    	    List<Class> files = findClasses(new File(fileLoc), packageDir);
    	    for (Class<Dialogue> c : files) {
    		if (Dialogue.class.isAssignableFrom(c)) {
    		    handledDialogues.put("" + c.getSimpleName() + "",
    			    (Class<Dialogue>) Class.forName(c
    				    .getCanonicalName()));
    		}
    	    }
    	    
    	} catch (ClassNotFoundException e) {
    	    e.printStackTrace();
    	}
    }
    
    public static void initEaster() {
    	String fileLoc = "bin/com/rs/game/player/dialogues/easter";
    	String packageDir = "com.rs.game.player.dialogues.easter";
    	try {
    	    List<Class> files = findClasses(new File(fileLoc), packageDir);
    	    for (Class<Dialogue> c : files) {
    		if (Dialogue.class.isAssignableFrom(c)) {
    		    handledDialogues.put("" + c.getSimpleName() + "",
    			    (Class<Dialogue>) Class.forName(c
    				    .getCanonicalName()));
    		}
    	    }
    	    
    	} catch (ClassNotFoundException e) {
    	    e.printStackTrace();
    	}
    }

    public static void initProp(){
    	String fileLoc = "bin/com/rs/game/player/dialogues/properties";
    	String packageDir = "com.rs.game.player.dialogues.properties";
    	try {
    	    List<Class> files = findClasses(new File(fileLoc), packageDir);
    	    for (Class<Dialogue> c : files) {
    		if (Dialogue.class.isAssignableFrom(c)) {
    		    handledDialogues.put("" + c.getSimpleName() + "",
    			    (Class<Dialogue>) Class.forName(c
    				    .getCanonicalName()));
    		}
    	    }
    	    
    	} catch (ClassNotFoundException e) {
    	    e.printStackTrace();
    	}
    }

    public static void initTDD(){
    	String fileLoc = "bin/com/rs/game/player/dialogues/quests/thedwarvendiscovery";
    	String packageDir = "com.rs.game.player.dialogues.quests.thedwarvendiscovery";
    	try {
    	    List<Class> files = findClasses(new File(fileLoc), packageDir);
    	    for (Class<Dialogue> c : files) {
    		if (Dialogue.class.isAssignableFrom(c)) {
    		    handledDialogues.put("" + c.getSimpleName() + "",
    			    (Class<Dialogue>) Class.forName(c
    				    .getCanonicalName()));
    		}
    	    }
    	    
    	} catch (ClassNotFoundException e) {
    	    e.printStackTrace();
    	}
    }
    
    public static void initSA(){
    	String fileLoc = "bin/com/rs/game/player/dialogues/quests/sunfreetawaken";
    	String packageDir = "com.rs.game.player.dialogues.quests.sunfreetawaken";
    	try {
    	    List<Class> files = findClasses(new File(fileLoc), packageDir);
    	    for (Class<Dialogue> c : files) {
    		if (Dialogue.class.isAssignableFrom(c)) {
    		    handledDialogues.put("" + c.getSimpleName() + "",
    			    (Class<Dialogue>) Class.forName(c
    				    .getCanonicalName()));
    		}
    	    }
    	    
    	} catch (ClassNotFoundException e) {
    	    e.printStackTrace();
    	}
    }


    public static void initTheTruth() {
    	String fileLoc = "bin/com/rs/game/player/dialogues/thetruth";
    	String packageDir = "com.rs.game.player.dialogues.thetruth";
    	try {
    	    List<Class> files = findClasses(new File(fileLoc), packageDir);
    	    for (Class<Dialogue> c : files) {
    		if (Dialogue.class.isAssignableFrom(c)) {
    		    handledDialogues.put("" + c.getSimpleName() + "",
    			    (Class<Dialogue>) Class.forName(c
    				    .getCanonicalName()));
    		}
    	    }
    	    
    	} catch (ClassNotFoundException e) {
    	    e.printStackTrace();
    	}
    }
    
    
    public static final void reload() {
	handledDialogues.clear();
	init();
    }

    public static final Dialogue getDialogue(Object key) {
	if (key instanceof Dialogue) {
		return (Dialogue) key;
	}
	Class<Dialogue> classD = handledDialogues.get(key);
	if (classD == null) {
		return null;
	}
	try {
	    return classD.newInstance();
	} catch (Throwable e) {
	    Logger.handle(e);
	}
	return null;
    }

	
//	@SuppressWarnings("unchecked")
//	public static final void init() {
//		try {
//			Class<Dialogue> value1 = (Class<Dialogue>) Class.forName(LevelUp.class.getCanonicalName());
//			handledDialogues.put("LevelUp", value1);
//			Class<Dialogue> value2 = (Class<Dialogue>) Class.forName(ZarosAltar.class.getCanonicalName());
//			handledDialogues.put("ZarosAltar", value2);
//			Class<Dialogue> value3 = (Class<Dialogue>) Class.forName(ClimbNoEmoteStairs.class.getCanonicalName());
//			handledDialogues.put("ClimbNoEmoteStairs", value3);
//			Class<Dialogue> value4 = (Class<Dialogue>) Class.forName(Banker.class.getCanonicalName());
//			handledDialogues.put("Banker", value4);
//
//			Class<Dialogue> value5 = (Class<Dialogue>) Class.forName(DestroyItemOption.class.getCanonicalName());
//			handledDialogues.put("DestroyItemOption", value5);
//			Class<Dialogue> value6 = (Class<Dialogue>) Class.forName(FremennikShipmaster.class.getCanonicalName());
//			handledDialogues.put("FremennikShipmaster", value6);
//			Class<Dialogue> value8 = (Class<Dialogue>) Class.forName(NexEntrance.class.getCanonicalName());
//			handledDialogues.put("NexEntrance", value8);
//			Class<Dialogue> value9 = (Class<Dialogue>) Class.forName(MagicPortal.class.getCanonicalName());
//			handledDialogues.put("MagicPortal", value9);
//			Class<Dialogue> value10 = (Class<Dialogue>) Class.forName(LunarAltar.class.getCanonicalName());
//			handledDialogues.put("LunarAltar", value10);
//			Class<Dialogue> value11 = (Class<Dialogue>) Class.forName(AncientAltar.class.getCanonicalName());
//			handledDialogues.put("AncientAltar", value11);
//			// TODO 12 and 13
//			Class<Dialogue> value12 = (Class<Dialogue>) Class.forName(FletchingD.class.getCanonicalName());
//			handledDialogues.put("FletchingD", value12);
//			Class<Dialogue> value14 = (Class<Dialogue>) Class.forName(RuneScapeGuide.class.getCanonicalName());
//			handledDialogues.put("RuneScapeGuide", value14);
//			Class<Dialogue> value15 = (Class<Dialogue>) Class.forName(SurvivalExpert.class.getCanonicalName());
//			handledDialogues.put("SurvivalExpert", value15);
//			Class<Dialogue> value16 = (Class<Dialogue>) Class.forName(SimpleMessage.class.getCanonicalName());
//			handledDialogues.put("SimpleMessage", value16);
//			Class<Dialogue> value17 = (Class<Dialogue>) Class.forName(ItemMessage.class.getCanonicalName());
//			handledDialogues.put("ItemMessage", value17);
//			Class<Dialogue> value18 = (Class<Dialogue>) Class.forName(ClimbEmoteStairs.class.getCanonicalName());
//			handledDialogues.put("ClimbEmoteStairs", value18);
//			Class<Dialogue> value19 = (Class<Dialogue>) Class.forName(QuestGuide.class.getCanonicalName());
//			handledDialogues.put("QuestGuide", value19);
//			Class<Dialogue> value20 = (Class<Dialogue>) Class.forName(GemCuttingD.class.getCanonicalName());
//			handledDialogues.put("GemCuttingD", value20);
//			Class<Dialogue> value21 = (Class<Dialogue>) Class.forName(CookingD.class.getCanonicalName());
//			handledDialogues.put("CookingD", value21);
//			Class<Dialogue> value22 = (Class<Dialogue>) Class.forName(HerbloreD.class.getCanonicalName());
//			handledDialogues.put("HerbloreD", value22);
//			Class<Dialogue> value23 = (Class<Dialogue>) Class.forName(BarrowsD.class.getCanonicalName());
//			handledDialogues.put("BarrowsD", value23);
//			Class<Dialogue> value24 = (Class<Dialogue>) Class.forName(SmeltingD.class.getCanonicalName());
//			handledDialogues.put("SmeltingD", value24);
//			Class<Dialogue> value25 = (Class<Dialogue>) Class.forName(LeatherCraftingD.class.getCanonicalName());
//			handledDialogues.put("LeatherCraftingD", value25);
//			Class<Dialogue> value26 = (Class<Dialogue>) Class.forName(EnchantedGemDialouge.class.getCanonicalName());
//			handledDialogues.put("EnchantedGemDialouge", value26);
//			Class<Dialogue> value27 = (Class<Dialogue>) Class.forName(ForfeitDialouge.class.getCanonicalName());
//			handledDialogues.put("ForfeitDialouge", value27);
//			Class<Dialogue> value28 = (Class<Dialogue>) Class.forName(Transportation.class.getCanonicalName());
//			handledDialogues.put("Transportation", value28);
//			Class<Dialogue> value29 = (Class<Dialogue>) Class.forName(WildernessDitch.class.getCanonicalName());
//			handledDialogues.put("WildernessDitch", value29);
//			Class<Dialogue> value30 = (Class<Dialogue>) Class.forName(SimpleNPCMessage.class.getCanonicalName());
//			handledDialogues.put("SimpleNPCMessage", value30);
//			Class<Dialogue> value31 = (Class<Dialogue>) Class.forName(Transportation.class.getCanonicalName());
//			handledDialogues.put("Transportation", value31);
//			Class<Dialogue> value32 = (Class<Dialogue>) Class.forName(DTSpectateReq.class.getCanonicalName());
//			handledDialogues.put("DTSpectateReq", value32);
//			Class<Dialogue> value33 = (Class<Dialogue>) Class.forName(StrangeFace.class.getCanonicalName());
//			handledDialogues.put("StrangeFace", value33);
//			/*
//			 * Class<Dialogue> value34 = (Class<Dialogue>)
//			 * Class.forName(AncientEffigiesD.class.getCanonicalName());
//			 * handledDialogues.put("AncientEffigiesD", value34);
//			 */
//			Class<Dialogue> value35 = (Class<Dialogue>) Class.forName(DTClaimRewards.class.getCanonicalName());
//			handledDialogues.put("DTClaimRewards", value35);
//			Class<Dialogue> value36 = (Class<Dialogue>) Class.forName(SetSkills.class.getCanonicalName());
//			handledDialogues.put("SetSkills", value36);
//			Class<Dialogue> value37 = (Class<Dialogue>) Class.forName(DismissD.class.getCanonicalName());
//			handledDialogues.put("DismissD", value37);
//			// Class<Dialogue> value38 = (Class<Dialogue>) Class
//			// .forName(MrEx.class.getCanonicalName());
//			// handledDialogues.put("MrEx", value38);
//			Class<Dialogue> value39 = (Class<Dialogue>) Class.forName(MakeOverMage.class.getCanonicalName());
//			handledDialogues.put("MakeOverMage", value39);
//			Class<Dialogue> value40 = (Class<Dialogue>) Class.forName(KaramjaTrip.class.getCanonicalName());
//			handledDialogues.put("KaramjaTrip", value40);
//			Class<Dialogue> value42 = (Class<Dialogue>) Class.forName(DagonHai.class.getCanonicalName());
//			handledDialogues.put("DagonHai", value42);
//
//			Class<Dialogue> value46 = (Class<Dialogue>) Class.forName(Repair.class.getCanonicalName());
//			handledDialogues.put("Repair", value46);
//			Class<Dialogue> value47 = (Class<Dialogue>) Class.forName(ModPanel.class.getCanonicalName());
//			handledDialogues.put("ModPanel", value47);
//			Class<Dialogue> value48 = (Class<Dialogue>) Class.forName(PotatoCommands.class.getCanonicalName());
//			handledDialogues.put("PotatoCommands", value48);
//			Class<Dialogue> value49 = (Class<Dialogue>) Class.forName(RuneSpanLeaving.class.getCanonicalName());
//			handledDialogues.put("RuneSpanLeaving", value49);
//			Class<Dialogue> value50 = (Class<Dialogue>) Class.forName(TeleportCrystal.class.getCanonicalName());
//			handledDialogues.put("TeleportCrystal", value50);
//			Class<Dialogue> value51 = (Class<Dialogue>) Class.forName(PrestigeOne.class.getCanonicalName());
//			handledDialogues.put("PrestigeOne", value51);
//			Class<Dialogue> value52 = (Class<Dialogue>) Class.forName(HouseTeleport.class.getCanonicalName());
//			handledDialogues.put("HouseTeleport", value52);
//			Class<Dialogue> value53 = (Class<Dialogue>) Class.forName(LeaveHouse.class.getCanonicalName());
//			handledDialogues.put("LeaveHouse", value53);
//			Class<Dialogue> value54 = (Class<Dialogue>) Class.forName(EstateAgent.class.getCanonicalName());
//			handledDialogues.put("EstateAgent", value54);
//			Class<Dialogue> value55 = (Class<Dialogue>) Class.forName(AdventureLog.class.getCanonicalName());
//			handledDialogues.put("StatLog", value55);
//			Class<Dialogue> value56 = (Class<Dialogue>) Class.forName(HouseFee.class.getCanonicalName());
//			handledDialogues.put("HouseFee", value56);
//			Class<Dialogue> value57 = (Class<Dialogue>) Class.forName(DryaxionsRift.class.getCanonicalName());
//			handledDialogues.put("DryaxionsRift", value57);
//			Class<Dialogue> value58 = (Class<Dialogue>) Class.forName(OnyxCut.class.getCanonicalName());
//			handledDialogues.put("OnyxCut", value58);
//			handledDialogues.put("BossInstanceDialogue", (Class<Dialogue>) Class.forName(BossInstanceDialogue.class.getCanonicalName()));
//			handledDialogues.put("LegendarySlayerMaster", (Class<Dialogue>) Class.forName(LegendarySlayerMaster.class.getCanonicalName()));
//			handledDialogues.put("MajorNigel", (Class<Dialogue>) Class.forName(MajorNigel.class.getCanonicalName()));
//			handledDialogues.put("MajorNigel1", (Class<Dialogue>) Class.forName(MajorNigel1.class.getCanonicalName()));
//			handledDialogues.put("MajorNigel2", (Class<Dialogue>) Class.forName(MajorNigel2.class.getCanonicalName()));
//			handledDialogues.put("Welcome", (Class<Dialogue>) Class.forName(Welcome.class.getCanonicalName()));
//			handledDialogues.put("ValentineHeart", (Class<Dialogue>) Class.forName(ValentineHeart.class.getCanonicalName()));
//			handledDialogues.put("CommanderDrygon", (Class<Dialogue>) Class.forName(CommanderDrygon.class.getCanonicalName()));
//			handledDialogues.put("DrygonicGod", (Class<Dialogue>) Class.forName(DrygonicGod.class.getCanonicalName()));
//			handledDialogues.put("Email", (Class<Dialogue>) Class.forName(Email.class.getCanonicalName()));
//			/**
//			 * EcoBreakdown
//			 */
//			handledDialogues.put("CaptainRabidJack", (Class<Dialogue>) Class.forName(CaptainRabidJack.class.getCanonicalName()));
//			handledDialogues.put("CaptainTobias", (Class<Dialogue>) Class.forName(CaptainTobias.class.getCanonicalName()));
//			handledDialogues.put("CaptainTobias2", (Class<Dialogue>) Class.forName(CaptainTobias2.class.getCanonicalName()));
//			handledDialogues.put("KingRoald", (Class<Dialogue>) Class.forName(KingRoald.class.getCanonicalName()));
//			handledDialogues.put("KingRoald2", (Class<Dialogue>) Class.forName(KingRoald2.class.getCanonicalName()));
//
//			handledDialogues.put("LotteryDialogue", (Class<Dialogue>) Class.forName(LotteryDialogue.class.getCanonicalName()));
//			handledDialogues.put("SkillingTask", (Class<Dialogue>) Class.forName(SkillingTask.class.getCanonicalName()));
//			handledDialogues.put("SkillingTaskRewards", (Class<Dialogue>) Class.forName(SkillingTaskRewards.class.getCanonicalName()));
//			handledDialogues.put("BlinkConfirmation", (Class<Dialogue>) Class.forName(BlinkConfirmation.class.getCanonicalName()));
//			handledDialogues.put("SliskeConfirmation", (Class<Dialogue>) Class.forName(SliskeConfirmation.class.getCanonicalName()));
//			handledDialogues.put("SirenicConfirmation", (Class<Dialogue>) Class.forName(SirenicConfirmation.class.getCanonicalName()));
//			handledDialogues.put("LavaWyrmConfirmation", (Class<Dialogue>) Class.forName(LavaWyrmConfirmation.class.getCanonicalName()));
//			handledDialogues.put("MalevolentCrafting", (Class<Dialogue>) Class.forName(MalevolentCrafting.class.getCanonicalName()));
//			handledDialogues.put("DropRateManager", (Class<Dialogue>) Class.forName(DropRateManager.class.getCanonicalName()));
//			handledDialogues.put("Hetty", (Class<Dialogue>) Class.forName(Hetty.class.getCanonicalName()));
//			handledDialogues.put("AnimationExpert", (Class<Dialogue>) Class.forName(AnimationExpert.class.getCanonicalName()));
//			handledDialogues.put("BookofMovement", (Class<Dialogue>) Class.forName(BookofMovement.class.getCanonicalName()));
//			handledDialogues.put("CalamityConfirmation", (Class<Dialogue>) Class.forName(CalamityConfirmation.class.getCanonicalName()));
//			// handledDialogues.put("RagoWeek", (Class<Dialogue>)
//			// Class.forName(RagoWeek.class.getCanonicalName()));
//			handledDialogues.put("CreateOublietteD", (Class<Dialogue>) Class.forName(CreateOublietteD.class.getCanonicalName()));
//
//			handledDialogues.put("ChallengeModeLeverD", (Class<Dialogue>) Class.forName(ChallengeModeLeverD.class.getCanonicalName()));
//			handledDialogues.put("ZenyteCraftD", (Class<Dialogue>) Class.forName(ZenyteCraftD.class.getCanonicalName()));
//			handledDialogues.put("VoragoEntrance", (Class<Dialogue>) Class.forName(VoragoEntrance.class.getCanonicalName()));
//			handledDialogues.put("CrystalBoots", (Class<Dialogue>) Class.forName(CrystalBoots.class.getCanonicalName()));
//			// flasks
//			handledDialogues.put("FlaskDecantingD", (Class<Dialogue>) Class.forName(FlaskDecantingD.class.getCanonicalName()));
//			// INSTANCES
//			handledDialogues.put("InstancesD", (Class<Dialogue>) Class.forName(InstancesD.class.getCanonicalName()));
//			handledDialogues.put("KingBlackDragonD", (Class<Dialogue>) Class.forName(KingBlackDragonD.class.getCanonicalName()));
//			handledDialogues.put("KalphiteQueenD", (Class<Dialogue>) Class.forName(KalphiteQueenD.class.getCanonicalName()));
//			handledDialogues.put("CorporealBeastD", (Class<Dialogue>) Class.forName(CorporealBeastD.class.getCanonicalName()));
//			// ENDOFINSTANCES
//			// CONFIRMATIONS
//			handledDialogues.put("TogConfirmation", (Class<Dialogue>) Class.forName(TogConfirmation.class.getCanonicalName()));
//			// END OF CONFIRMATIONS
//			handledDialogues.put("FlowerPickup", (Class<Dialogue>) Class.forName(FlowerPickup.class.getCanonicalName()));
//			handledDialogues.put("PetTamer", (Class<Dialogue>) Class.forName(PetTamer.class.getCanonicalName()));
//			handledDialogues.put("GlacorBootUpgrade", (Class<Dialogue>) Class.forName(GlacorBootUpgrade.class.getCanonicalName()));
//			handledDialogues.put("WildyWyrm", (Class<Dialogue>) Class.forName(WildyWyrm.class.getCanonicalName()));
//			handledDialogues.put("GeInterfaces", (Class<Dialogue>) Class.forName(GeInterfaces.class.getCanonicalName()));
//			handledDialogues.put("Scientist", (Class<Dialogue>) Class.forName(Scientist.class.getCanonicalName()));
//			handledDialogues.put("BarBagD", (Class<Dialogue>) Class.forName(BarBagD.class.getCanonicalName()));
//			handledDialogues.put("CitadelEnterD", (Class<Dialogue>) Class.forName(CitadelEnterD.class.getCanonicalName()));
//			handledDialogues.put("CitadelLeaveD", (Class<Dialogue>) Class.forName(CitadelLeaveD.class.getCanonicalName()));
//			handledDialogues.put("Clan", (Class<Dialogue>) Class.forName(Clan.class.getCanonicalName()));
//			handledDialogues.put("QueenOfSnow", (Class<Dialogue>) Class.forName(QueenOfSnow.class.getCanonicalName()));
//			handledDialogues.put("TanningD", (Class<Dialogue>) Class.forName(TanningD.class.getCanonicalName()));
//			handledDialogues.put("Banning", (Class<Dialogue>) Class.forName(Banning.class.getCanonicalName()));
//			handledDialogues.put("ClanMotto", (Class<Dialogue>) Class.forName(ClanMotto.class.getCanonicalName()));
//			handledDialogues.put("ClanInvite", (Class<Dialogue>) Class.forName(ClanInvite.class.getCanonicalName()));
//			handledDialogues.put("ClanCreateD", (Class<Dialogue>) Class.forName(ClanCreateD.class.getCanonicalName()));
//			handledDialogues.put("LeaveClan", (Class<Dialogue>) Class.forName(LeaveClan.class.getCanonicalName()));
//			handledDialogues.put("ClaimClanItem", (Class<Dialogue>) Class.forName(ClaimClanItem.class.getCanonicalName()));
//			handledDialogues.put("DoubleXpLord", (Class<Dialogue>) Class.forName(DoubleXpLord.class.getCanonicalName()));
//			handledDialogues.put("RoyalCraft", (Class<Dialogue>) Class.forName(RoyalCraft.class.getCanonicalName()));
//			handledDialogues.put("DonatorUpgrade", (Class<Dialogue>) Class.forName(DonatorUpgrade.class.getCanonicalName()));
//			handledDialogues.put("GambleD", (Class<Dialogue>) Class.forName(GambleD.class.getCanonicalName()));
//			handledDialogues.put("DrygoreCraft", (Class<Dialogue>) Class.forName(DrygoreCraft.class.getCanonicalName()));
//			handledDialogues.put("DaggerCraft85", (Class<Dialogue>) Class.forName(DaggerCraft85.class.getCanonicalName()));
//			handledDialogues.put("ModTable", (Class<Dialogue>) Class.forName(ModTable.class.getCanonicalName()));
//			// handledDialogues.put("DiaryExpert", (Class<Dialogue>)
//			// Class.forName(DiaryExpert.class.getCanonicalName()));
//			handledDialogues.put("MalevolentCreation", (Class<Dialogue>) Class.forName(MalevolentCreation.class.getCanonicalName()));
//			handledDialogues.put("WildFinish", (Class<Dialogue>) Class.forName(WildFinish.class.getCanonicalName()));
//			handledDialogues.put("WildBank", (Class<Dialogue>) Class.forName(WildBank.class.getCanonicalName()));
//			handledDialogues.put("SirenicCraft", (Class<Dialogue>) Class.forName(SirenicCraft.class.getCanonicalName()));
//			handledDialogues.put("TectonicCraft", (Class<Dialogue>) Class.forName(TectonicCraft.class.getCanonicalName()));
//			handledDialogues.put("Pikkenmix", (Class<Dialogue>) Class.forName(Pikkenmix.class.getCanonicalName()));
//			handledDialogues.put("WildSkillFee", (Class<Dialogue>) Class.forName(WildSkillFee.class.getCanonicalName()));
//			handledDialogues.put("UpgradeArmour", (Class<Dialogue>) Class.forName(UpgradeArmour.class.getCanonicalName()));
//			handledDialogues.put("GoldChestD", (Class<Dialogue>) Class.forName(GoldChestD.class.getCanonicalName()));
//			handledDialogues.put("ThirdAgeCreate", (Class<Dialogue>) Class.forName(ThirdAgeCreate.class.getCanonicalName()));
//			handledDialogues.put("LootBeamManagerD", (Class<Dialogue>) Class.forName(LootBeamManagerD.class.getCanonicalName()));
//			handledDialogues.put("ChiefThiefRobin", (Class<Dialogue>) Class.forName(ChiefThiefRobin.class.getCanonicalName()));
//			handledDialogues.put("TrimmedComp", (Class<Dialogue>) Class.forName(TrimmedComp.class.getCanonicalName()));
//			handledDialogues.put("RingImbue", (Class<Dialogue>) Class.forName(RingImbue.class.getCanonicalName()));
//			handledDialogues.put("GanoCraft", (Class<Dialogue>) Class.forName(GanoCraft.class.getCanonicalName()));
//			handledDialogues.put("LuckySell", (Class<Dialogue>) Class.forName(LuckySell.class.getCanonicalName()));
//			handledDialogues.put("Voragofee", (Class<Dialogue>) Class.forName(Voragofee.class.getCanonicalName()));
//			handledDialogues.put("KkFee", (Class<Dialogue>) Class.forName(KkFee.class.getCanonicalName()));
//			handledDialogues.put("DbossFee", (Class<Dialogue>) Class.forName(DbossFee.class.getCanonicalName()));
//			handledDialogues.put("BossSlayer", (Class<Dialogue>) Class.forName(BossSlayer.class.getCanonicalName()));
//			handledDialogues.put("Santa2016", (Class<Dialogue>) Class.forName(Santa2016.class.getCanonicalName()));
//
//			handledDialogues.put("VoteStore", (Class<Dialogue>) Class.forName(VoteStore.class.getCanonicalName()));
//			handledDialogues.put("clan_wars_view", (Class<Dialogue>) Class.forName(ClanWarsViewing.class.getCanonicalName()));
//			handledDialogues.put("Dungeoneering", (Class<Dialogue>) Class.forName(Dungeoneering.class.getCanonicalName()));
//			handledDialogues.put("DryPoints", (Class<Dialogue>) Class.forName(DryPoints.class.getCanonicalName()));
//			handledDialogues.put("DryPointsO", (Class<Dialogue>) Class.forName(DryPointsO.class.getCanonicalName()));
//			handledDialogues.put("Santa", (Class<Dialogue>) Class.forName(Santa.class.getCanonicalName()));
//			handledDialogues.put("BobBarterD", (Class<Dialogue>) Class.forName(BobBarterD.class.getCanonicalName()));
//			handledDialogues.put("DryPointsD", (Class<Dialogue>) Class.forName(DryPointsD.class.getCanonicalName()));
//			handledDialogues.put("Dunghandler", (Class<Dialogue>) Class.forName(Dunghandler.class.getCanonicalName()));
//			handledDialogues.put("FightKilnReward", (Class<Dialogue>) Class.forName(FightKilnReward.class.getCanonicalName()));
//			handledDialogues.put("Warrior", (Class<Dialogue>) Class.forName(Warrior.class.getCanonicalName()));
//			handledDialogues.put("Shamus", (Class<Dialogue>) Class.forName(Shamus.class.getCanonicalName()));
//			handledDialogues.put("MonkOfEntrana", (Class<Dialogue>) Class.forName(MonkOfEntrana.class.getCanonicalName()));
//			handledDialogues.put("Titles", (Class<Dialogue>) Class.forName(Titles.class.getCanonicalName()));
//			handledDialogues.put("Npcshop2", (Class<Dialogue>) Class.forName(Npcshop2.class.getCanonicalName()));
//			handledDialogues.put("SkillingTeleport", (Class<Dialogue>) Class.forName(SkillingTeleport.class.getCanonicalName()));
//			handledDialogues.put("NewUser", (Class<Dialogue>) Class.forName(NewUser.class.getCanonicalName()));
//			handledDialogues.put("ConTutorial", (Class<Dialogue>) Class.forName(ConTutorial.class.getCanonicalName()));
//			handledDialogues.put("ConTutorial2", (Class<Dialogue>) Class.forName(ConTutorial2.class.getCanonicalName()));
//			handledDialogues.put("DiceBag", (Class<Dialogue>) Class.forName(DiceBag.class.getCanonicalName()));
//			handledDialogues.put("PartyPete", (Class<Dialogue>) Class.forName(PartyPete.class.getCanonicalName()));
//			handledDialogues.put("PartyRoomLever", (Class<Dialogue>) Class.forName(PartyRoomLever.class.getCanonicalName()));
//			handledDialogues.put("DrogoDwarf", (Class<Dialogue>) Class.forName(DrogoDwarf.class.getCanonicalName()));
//			handledDialogues.put("GeneralStore", (Class<Dialogue>) Class.forName(GeneralStore.class.getCanonicalName()));
//			handledDialogues.put("Nurmof", (Class<Dialogue>) Class.forName(Nurmof.class.getCanonicalName()));
//			handledDialogues.put("BootDwarf", (Class<Dialogue>) Class.forName(BootDwarf.class.getCanonicalName()));
//			handledDialogues.put("MiningGuildDwarf", (Class<Dialogue>) Class.forName(MiningGuildDwarf.class.getCanonicalName()));
//			handledDialogues.put("Man", (Class<Dialogue>) Class.forName(Man.class.getCanonicalName()));
//			handledDialogues.put("Guildmaster", (Class<Dialogue>) Class.forName(Guildmaster.class.getCanonicalName()));
//			handledDialogues.put("Scavvo", (Class<Dialogue>) Class.forName(Scavvo.class.getCanonicalName()));
//			handledDialogues.put("Valaine", (Class<Dialogue>) Class.forName(Valaine.class.getCanonicalName()));
//			handledDialogues.put("ModPanel", (Class<Dialogue>) Class.forName(ModPanel.class.getCanonicalName()));
//			handledDialogues.put("Hura", (Class<Dialogue>) Class.forName(Hura.class.getCanonicalName()));
//			handledDialogues.put("TzHaarMejJal", (Class<Dialogue>) Class.forName(TzHaarMejJal.class.getCanonicalName()));
//			handledDialogues.put("TzHaarMejKah", (Class<Dialogue>) Class.forName(TzHaarMejKah.class.getCanonicalName()));
//			handledDialogues.put("LanderD", (Class<Dialogue>) Class.forName(LanderD.class.getCanonicalName()));
//			handledDialogues.put("Acid", (Class<Dialogue>) Class.forName(Acid.class.getCanonicalName()));
//			handledDialogues.put("MasterOfFear", (Class<Dialogue>) Class.forName(MasterOfFear.class.getCanonicalName()));
//			handledDialogues.put("TokHaarHok", (Class<Dialogue>) Class.forName(TokHaarHok.class.getCanonicalName()));
//			handledDialogues.put("NomadThrone", (Class<Dialogue>) Class.forName(NomadThrone.class.getCanonicalName()));
//			handledDialogues.put("SimplePlayerMessage", (Class<Dialogue>) Class.forName(SimplePlayerMessage.class.getCanonicalName()));
//			handledDialogues.put("BonfireD", (Class<Dialogue>) Class.forName(BonfireD.class.getCanonicalName()));
//			handledDialogues.put("PortalTeleports", (Class<Dialogue>) Class.forName(PortalTeleports.class.getCanonicalName()));
//			handledDialogues.put("ServerPolling", (Class<Dialogue>) Class.forName(ServerPolling.class.getCanonicalName()));
//			handledDialogues.put("HydrixCut", (Class<Dialogue>) Class.forName(HydrixCut.class.getCanonicalName()));
//			handledDialogues.put("MasterChef", (Class<Dialogue>) Class.forName(MasterChef.class.getCanonicalName()));
//			handledDialogues.put("FightKilnDialogue", (Class<Dialogue>) Class.forName(FightKilnDialogue.class.getCanonicalName()));
//			handledDialogues.put("RewardChest", (Class<Dialogue>) Class.forName(RewardChest.class.getCanonicalName()));
//			handledDialogues.put("T92wepCreation", (Class<Dialogue>) Class.forName(T92wepCreation.class.getCanonicalName()));
//			handledDialogues.put("ChristmasCrackerD", (Class<Dialogue>) Class.forName(ChristmasCrackerD.class.getCanonicalName()));
//			handledDialogues.put("UltraChristmasCrackerD", (Class<Dialogue>) Class.forName(UltraChristmasCrackerD.class.getCanonicalName()));
//			handledDialogues.put("WizardFinix", (Class<Dialogue>) Class.forName(WizardFinix.class.getCanonicalName()));
//			handledDialogues.put("PrayerD", (Class<Dialogue>) Class.forName(PrayerD.class.getCanonicalName()));
//			handledDialogues.put("RunespanPortalD", (Class<Dialogue>) Class.forName(RunespanPortalD.class.getCanonicalName()));
//			handledDialogues.put("SorceressGardenNPCs", (Class<Dialogue>) Class.forName(SorceressGardenNPCs.class.getCanonicalName()));
//			handledDialogues.put("Marv", (Class<Dialogue>) Class.forName(Marv.class.getCanonicalName()));
//			handledDialogues.put("FlamingSkull", (Class<Dialogue>) Class.forName(FlamingSkull.class.getCanonicalName()));
//			handledDialogues.put("Xuans", (Class<Dialogue>) Class.forName(Xuans.class.getCanonicalName()));
//			handledDialogues.put("StartDryaxions", (Class<Dialogue>) Class.forName(StartDryaxions.class.getCanonicalName()));
//			handledDialogues.put("Hairdresser", (Class<Dialogue>) Class.forName(Hairdresser.class.getCanonicalName()));
//			handledDialogues.put("WeeklyOverride", (Class<Dialogue>) Class.forName(WeeklyOverride.class.getCanonicalName()));
//			handledDialogues.put("Thessalia", (Class<Dialogue>) Class.forName(Thessalia.class.getCanonicalName()));
//			handledDialogues.put("SandD", (Class<Dialogue>) Class.forName(SandD.class.getCanonicalName()));
//			// handledDialogues.put("RocktailSoup", (Class<Dialogue>)
//			// Class.forName(RocktailSoup.class.getCanonicalName()));
//			handledDialogues.put("HeadMiner1", (Class<Dialogue>) Class.forName(HeadMiner1.class.getCanonicalName()));
//			handledDialogues.put("HeadMiner2", (Class<Dialogue>) Class.forName(HeadMiner2.class.getCanonicalName()));
//			handledDialogues.put("HeadMiner3", (Class<Dialogue>) Class.forName(HeadMiner3.class.getCanonicalName()));
//			handledDialogues.put("ProfessorOnglewip1", (Class<Dialogue>) Class.forName(ProfessorOnglewip1.class.getCanonicalName()));
//			handledDialogues.put("ProfessorOnglewip2", (Class<Dialogue>) Class.forName(ProfessorOnglewip2.class.getCanonicalName()));
//			handledDialogues.put("XpRates", (Class<Dialogue>) Class.forName(XpRates.class.getCanonicalName()));
//			handledDialogues.put("GrilleGoatsD", (Class<Dialogue>) Class.forName(GrilleGoatsDialogue.class.getCanonicalName()));
//			handledDialogues.put("DeathChat", (Class<Dialogue>) Class.forName(DeathChat.class.getCanonicalName()));
//			handledDialogues.put("KeepSake", (Class<Dialogue>) Class.forName(KeepSake.class.getCanonicalName()));
//			handledDialogues.put("SilverHawk", (Class<Dialogue>) Class.forName(SilverHawk.class.getCanonicalName()));
//			handledDialogues.put("WeavingD", (Class<Dialogue>) Class.forName(WeavingD.class.getCanonicalName()));
//			handledDialogues.put("ConvertMemoriesD", (Class<Dialogue>) Class.forName(ConvertMemoriesD.class.getCanonicalName()));
//			/**
//			 * Con
//			 */
//			handledDialogues.put("EnterHouseD", (Class<Dialogue>) Class.forName(EnterHouseD.class.getCanonicalName()));
//			handledDialogues.put("ClimbHouseStairD", (Class<Dialogue>) Class.forName(ClimbHouseStairD.class.getCanonicalName()));
//			handledDialogues.put("CreatePortal", (Class<Dialogue>) Class.forName(CreatePortal.class.getCanonicalName()));
//			handledDialogues.put("CreateRoomD", (Class<Dialogue>) Class.forName(CreateRoomD.class.getCanonicalName()));
//			handledDialogues.put("CreateRoomStairsD", (Class<Dialogue>) Class.forName(CreateRoomStairsD.class.getCanonicalName()));
//			handledDialogues.put("EstateAgent", (Class<Dialogue>) Class.forName(EstateAgent.class.getCanonicalName()));
//			handledDialogues.put("RemoveBuildD", (Class<Dialogue>) Class.forName(RemoveBuildD.class.getCanonicalName()));
//			handledDialogues.put("RemoveRoomD", (Class<Dialogue>) Class.forName(RemoveRoomD.class.getCanonicalName()));
//			handledDialogues.put("NewServantD", (Class<Dialogue>) Class.forName(NewServantD.class.getCanonicalName()));
//			handledDialogues.put("ServantDialogue", (Class<Dialogue>) Class.forName(ServantDialogue.class.getCanonicalName()));
//			handledDialogues.put("ItemOnServantD", (Class<Dialogue>) Class.forName(ItemOnServantD.class.getCanonicalName()));
//			handledDialogues.put("Ocellus", (Class<Dialogue>) Class.forName(Ocellus.class.getCanonicalName()));
//			handledDialogues.put("BarBag", (Class<Dialogue>) Class.forName(BarBag.class.getCanonicalName()));
//
//			/**
//			 * Con
//			 */
//			handledDialogues.put("MuseumGuard", (Class<Dialogue>) Class.forName(MuseumGuard.class.getCanonicalName()));
//			handledDialogues.put("Kuradal", (Class<Dialogue>) Class.forName(Kuradal.class.getCanonicalName()));
//			handledDialogues.put("Kuradal1", (Class<Dialogue>) Class.forName(Kuradal1.class.getCanonicalName()));
//			handledDialogues.put("Connie", (Class<Dialogue>) Class.forName(Connie.class.getCanonicalName()));
//			handledDialogues.put("Zik1", (Class<Dialogue>) Class.forName(Zik1.class.getCanonicalName()));
//			handledDialogues.put("Zik2", (Class<Dialogue>) Class.forName(Zik2.class.getCanonicalName()));
//			handledDialogues.put("Zik3", (Class<Dialogue>) Class.forName(Zik3.class.getCanonicalName()));
//			handledDialogues.put("Zik4", (Class<Dialogue>) Class.forName(Zik4.class.getCanonicalName()));
//			handledDialogues.put("GardenersD", (Class<Dialogue>) Class.forName(GardenersD.class.getCanonicalName()));
//			handledDialogues.put("GearPresetsD", (Class<Dialogue>) Class.forName(GearPresetsD.class.getCanonicalName()));
//			handledDialogues.put("CosmeticsD", (Class<Dialogue>) Class.forName(CosmeticsD.class.getCanonicalName()));
//
//			/**
//			 * 839 Content Update
//			 */
//			handledDialogues.put("AraxHyveD", (Class<Dialogue>) Class.forName(AraxHyveD.class.getCanonicalName()));
//			handledDialogues.put("NoxiousCreateD", (Class<Dialogue>) Class.forName(NoxiousCreateD.class.getCanonicalName()));
//			handledDialogues.put("VoragoFaceD", (Class<Dialogue>) Class.forName(VoragoFaceD.class.getCanonicalName()));
//			handledDialogues.put("BossInstanceD", (Class<Dialogue>) Class.forName(BossInstanceD.class.getCanonicalName()));
//			handledDialogues.put("VoragoSignpostD", (Class<Dialogue>) Class.forName(VoragoSignpostD.class.getCanonicalName()));
//			handledDialogues.put("VoragoInstanceD", (Class<Dialogue>) Class.forName(VoragoInstanceD.class.getCanonicalName()));
//		} catch (Throwable e) {
//			Logger.handle(e);
//		}
//	}
//
//	public static final void reload() {
//		handledDialogues.clear();
//		init();
//	}
//
//	public static final Dialogue getDialogue(Object key) {
//		if (key instanceof Dialogue)
//			return (Dialogue) key;
//		Class<Dialogue> classD = handledDialogues.get(key);
//		if (classD == null)
//			return null;
//		try {
//			return classD.newInstance();
//		} catch (Throwable e) {
//			Logger.handle(e);
//		}
//		return null;
//	}

	private DialogueHandler() {

	}
}
