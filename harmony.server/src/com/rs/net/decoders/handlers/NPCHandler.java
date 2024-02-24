package com.rs.net.decoders.handlers;

import com.rs.Settings;
import com.rs.game.Animation;
import com.rs.game.ForceTalk;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.minigames.CalamityMRArmour;
import com.rs.game.minigames.CalamityMRWeps;
import com.rs.game.minigames.CalamityMeleeArmour;
import com.rs.game.minigames.CalamityMeleeWeps;
import com.rs.game.npc.NPC;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.npc.others.FireSpirit;
import com.rs.game.npc.others.LivingRock;
import com.rs.game.npc.pet.Pet;
import com.rs.game.npc.slayer.LavaStryke;
import com.rs.game.npc.slayer.Strykewyrm;
import com.rs.game.player.CoordsEvent;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Fishing;
import com.rs.game.player.actions.Fishing.FishingSpots;
import com.rs.game.player.actions.FlyingEntityHunter;
import com.rs.game.player.actions.Summoning;
import com.rs.game.player.actions.mining.LivingMineralMining;
import com.rs.game.player.actions.mining.MiningBase;
import com.rs.game.player.actions.runecrafting.SiphonActionCreatures;
import com.rs.game.player.actions.thieving.PickPocketAction;
import com.rs.game.player.actions.thieving.PickPocketableNPC;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.PlayerLook;
import com.rs.game.player.content.grandExchange.GrandExchangeSystem;
import com.rs.game.player.content.pet.Pets;
import com.rs.game.player.content.raids.TheTrioWaiting;
import com.rs.game.player.content.raids.gulega.GulegaWaiting;
import com.rs.game.player.controlers.PlayerWars;
import com.rs.game.player.controlers.Wilderness;
import com.rs.game.player.dialogues.impl.FremennikShipmaster;
import com.rs.game.player.interfaces.GraveInterface;
import com.rs.game.player.interfaces.ShopsInterface;
import com.rs.game.player.interfaces.TitlesInterface;
import com.rs.io.InputStream;
import com.rs.utils.Colors;
import com.rs.utils.Logger;
import com.rs.utils.NPCExamines;
import com.rs.utils.PetExamine;
import com.rs.utils.ShopsHandler;
import com.rs.utils.Utils;

public class NPCHandler {

	public static void handleExamine(final Player player, InputStream stream) {
		int npcIndex = stream.readUnsignedShort128();
		boolean forceRun = stream.read128Byte() == 1;
		if (forceRun) {
			player.setRun(forceRun);
		}
		final NPC npc = World.getNPCs().get(npcIndex);
		if (npc == null || npc.hasFinished() || !player.getMapRegionsIds().contains(npc.getRegionId())) {
			return;
		}
		if (npc instanceof Pet) {
			Pet pet = (Pet) npc;
			Player owner = pet.getOwner();
			PetExamine.Examine(player, owner, pet);
			return;
		}
		player.getInterfaceManager().sendDropRates(npc.getId()); // dark beast
		player.getPackets().sendNPCMessage(0, 15263739, npc, NPCExamines.getExamine(npc));
		player.getPackets().sendResetMinimapFlag();
		if (player.getRights() == 2) {
			player.sendMessage("Npc ID: " + npc.getId());
		}
		if (Settings.DEBUG) {
			Logger.log("NPCHandler", "examined npc: " + npcIndex + ", " + npc.getId());
		}
	}

	public static void handleOption1(final Player player, InputStream stream) {
		if (!player.hasEnteredPin && player.hasBankPin) {
			player.getTemporaryAttributtes().put("bank_pin1", Boolean.TRUE);
			player.getPackets().sendRunScript(108, new Object[] { "Enter Your Bank Pin Please" });
			return;
		}
		int npcIndex = stream.readUnsignedShort128();
		boolean forceRun = stream.read128Byte() == 1;
		final NPC npc = World.getNPCs().get(npcIndex);
		if (npc == null || npc.isCantInteract() || npc.isDead() || npc.hasFinished()
				|| !player.getMapRegionsIds().contains(npc.getRegionId())) {
			return;
		}
		player.stopAll(false);
		if (forceRun) {
			player.setRun(forceRun);
		}
		if (npc.getDefinitions().name.contains("Banker") || npc.getDefinitions().name.contains("banker")) {
			player.faceEntity(npc);
			if (!player.withinDistance(npc, 2)) {
				return;
			}
			npc.faceEntity(player);
			player.getDialogueManager().startDialogue("Banker", npc.getId());
			return;
		}

		if (SiphonActionCreatures.siphon(player, npc)) {
			return;
		}
		if (npc.getId() == 00000) {
			String chat = "";
			switch (Utils.random(4)) {
				case 0:
					chat = "OPTION 1";
					break;
				case 1:
					chat = "OPTION 2";
					break;
				case 2:
					chat = "OPTION 3";
					break;
				default:
					chat = "OPTION 4";
					break;
			}
			npc.setNextForceTalk(new ForceTalk (chat));
			player.setNextForceTalk(new ForceTalk (chat));
			npc.addWalkSteps(npc.getX() + 3, npc.getY());
		}
		if (npc.getId() == 17161 || npc.getId() == 17162) {
			if (player.getControlerManager().getControler() == null) {
				player.getDialogueManager().startDialogue("SimpleMessage",
						"Vorago is not responding to your calls looks like he can't see you in the room. Try leaving and re-entering the instance again.");
				return;
			}
		}
		player.setCoordsEvent(new CoordsEvent(npc, new Runnable() {
			@Override
			public void run() {
				npc.resetWalkSteps();
				player.faceEntity(npc);
				if (!player.getControlerManager().processNPCClick1(npc)) {
					return;
				}
				FishingSpots spot = FishingSpots.forId(npc.getId() | 1 << 24);
				if (spot != null) {
					player.getActionManager().setAction(new Fishing(spot, npc));
					return; // its a spot, they wont face us
				} else if (npc.getId() >= 8837 && npc.getId() <= 8839) {
					player.getActionManager().setAction(new LivingMineralMining((LivingRock) npc));
					return;
				}
				if (npc.getId() == 566) {
					if (player.rights >= 1 || player.isSupporter()) {
						player.getDialogueManager().startDialogue("ModTable");
						return;
					} else {
						player.sendMessage("You need to be a support memeber or higher to user this");
					}
				}
				if (npc.getId() == 1597) {
					if (player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player)
							|| Pets.hasPet(player)) {
						player.sendMessage("Pets aren't allowed here!");
						player.getInterfaceManager().closeChatBoxInterface();
						player.getInterfaceManager().closeOverlay(true);
						return;
					}
					player.getDialogueManager().startDialogue("SeasonEventD");
				}
				if (npc.getId() == 14706) {
					player.getDialogueManager().startDialogue("DailyChallengeD");
					return;
				}
				if (npc.getId() == 100) {
					//player.getDialogueManager().startDialogue("SeasonEventD");
					return;
				}
				if (npc.getId() == 8540) {
					player.getDialogueManager().startDialogue("Santa");
					return;
				}
				if (npc.getId() == 162 || npc.getId() == 607) {
					ShopsHandler.openShop(player, 135);
				}
				if (npc.getId() == 8031) {
					player.getDialogueManager().startDialogue("Rue");
					return;
				}
				if (npc.getId() == 4250) {
					player.getDialogueManager().startDialogue("SimpleMessage",
							"You should give the operator some logs to turn into planks.");
					return;
				}
				if (npc.getId() == 4287) {
					player.getDialogueManager().startDialogue("RecyclingManagerD");
					return;
				}
				if (npc.getId() == 9711) {
					ShopsHandler.openShop(player, 107);
				}
				if (npc.getId() == 959) {
					if (player.homehealer > 3) {
						player.sendMessage(
								"You've used the healer as much as you can today. Build an Ornate rejuvention pool in your house for unlimited uses!");
						return;
					}
					player.heal(player.getMaxHitpoints());
					player.getPoison().reset();
					player.getCombatDefinitions().resetSpecialAttack();
					player.sendMessage("You have been healed & cured!");
					return;
				}
				if (npc.getId() == 8623) {
					player.getInterfaceManager().sendInterface(new GraveInterface(player));
					player.sendMessage(
							Colors.red + "You need to have " + Utils.format(player.getGrave().checkValue() / 20)
									+ " coins in your money pouch to pay your claim fee.");
					return;
				}
				if (npc.getId() == 15513 || npc.getId() >= 11303 && npc.getId() <= 11307) {
					player.getDialogueManager().startDialogue("ServantDialogue", npc.getId());
				}
				/*if (npc.getId() == 11270) {
					PlayerWars.enterPlayerWars(player);
					return;
				}*/
				if (npc.getId() == 11508) {
					player.getDialogueManager().startDialogue("PlayerWarDialogue");
					return;
				}
				if (npc.getId() == 14237) {
					GulegaWaiting.joinPortal(player, 2);
					return;
				}
				if (npc.getId() == 14241) {
					TheTrioWaiting.joinPortal(player, 2);
					return;
				}
				if (npc.getId() == 8641) {
					player.getDialogueManager().startDialogue("PrestigeBossTeleporter");
					return;
				}
				if (npc.getId() == 528) {
					player.getTemporaryAttributtes().put("shopsinterface", "true");
					player.getTemporaryAttributtes().remove("skillteleport");
					player.getTemporaryAttributtes().remove("skillteleport2");
					player.getTemporaryAttributtes().remove("bossteleport1");
					player.getTemporaryAttributtes().remove("shopsinterface1");
					player.getTemporaryAttributtes().remove("shopsinterface2");
					player.getTemporaryAttributtes().remove("bossteleport2");
					player.getTemporaryAttributtes().remove("minigameteleport");
					player.getTemporaryAttributtes().remove("slayerteleport");
					ShopsInterface.sendInterface(player);
					return;
				}
				if (player.getFarmingManager().isGardening(npc.getId(), 1)) {
					return;
				}
				/*
				 * if (npc.getId() == 17161)
				 * player.getDialogueManager().startDialogue("VoragoFaceD", 17161, 0, npc);
				 */
				if (npc.getId() == 14293) {
					if (player.getAttackedByDelay() + 10000 > Utils.currentTimeMillis()) {
						player.getDialogueManager().startDialogue("SimpleMessage",
								"You can't teleport until 10 seconds after the end of combat.");
						return;
					}
					if (player.getInventory().containsItem(995, 5000000)) {
						player.getInventory().deleteItem(995, 5000000);
						Settings.GpSyncAmount += 5000000;
						player.getControlerManager().forceStop();
						player.setNextWorldTile(new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
						return;
					} else {
						player.sendMessage("Ha! My spells aren't free. I'll take 5 million gold.");
						return;
					}
				} else if (npc.getDefinitions().name.contains("Estate agent")) {
					player.getDialogueManager().startDialogue("EstateAgent");
				}
				if (npc.getName().toLowerCase().contains("impling")
						|| npc.getName().toLowerCase().equals("ruby harvest")
						|| npc.getName().toLowerCase().equals("sapphire glacialis")
						|| npc.getName().toLowerCase().equals("snowy knight")
						|| npc.getName().toLowerCase().equals("black warlock")) {
					FlyingEntityHunter.captureFlyingEntity(player, npc);
					return;
				}
				if (npc.getId() == 30050) {
					switch (Utils.getRandom(10)) {
						case 0:
							npc.setNextForceTalk(new ForceTalk("Oi! Fuck off touching me."));
							break;
						case 1:
							npc.setNextForceTalk(new ForceTalk("RAPE! This fucking bitch touched my willy."));
							break;
						case 2:
							npc.setNextForceTalk(new ForceTalk("HELP! HE STEALING! HE STEALING!"));
							break;
						case 3:
							npc.setNextForceTalk(new ForceTalk("Do you even lift?"));
							break;
						case 4:
							npc.setNextForceTalk(new ForceTalk("Happy H'ween."));
							break;
						case 5:
							npc.setNextForceTalk(new ForceTalk("OUCH!"));
							break;
						case 6:
							npc.setNextForceTalk(new ForceTalk("What are you doing? Can't you see i'm sleeping here."));
							break;
						case 7:
							npc.setNextForceTalk(new ForceTalk("GTFO noob!"));
							break;
						case 8:
							npc.setNextForceTalk(new ForceTalk("Dude i will fuck you up."));
							break;
						case 9:
							npc.setNextForceTalk(new ForceTalk("Oi! That tickles."));
							break;
						case 10:
							npc.setNextForceTalk(new ForceTalk(
									"Seriously. Happy H'ween from Harmony and all of us from the staff team."));
							break;
					}

				}

				/**
				 * Economic Breakdown
				 */

				if (npc.getId() == 648 && player.ecobdpart == 0) {
					player.getDialogueManager().startDialogue("KingRoald");
					return;
				} else if (npc.getId() == 648 && player.ecobdpart == 2) {
					player.getDialogueManager().startDialogue("KingRoald2");
					return;
				}
				if (npc.getId() == 376 && player.ecobdpart == 1) {
					player.getDialogueManager().startDialogue("CaptainTobias");
					return;
				} else if (npc.getId() == 376 && player.ecobdpart == 4) {
					player.getDialogueManager().startDialogue("CaptainTobias2");
					return;
				} else if (npc.getId() == 7847 && player.ecobdpart == 4) {
					player.getDialogueManager().startDialogue("CaptainRabidJack");
					return;
				}
				if (npc.getId() == 3709) {
					player.getDialogueManager().startDialogue("WildernessTasksD");
					return;
				}
				/**
				 * start of TheDwarvenDiscovery
				 */
				if (npc.getId() == 1419) {
					player.getDialogueManager().startDialogue("GeInterfaces");
					return;
				}
				if (npc.getId() == 15231) {
					if (player.starteddwarvenquest != true) {
						player.getDialogueManager().startDialogue("HeadMiner1");
					} else if (player.dwarvenpart == 3) {
						player.getDialogueManager().startDialogue("HeadMiner2");
					} else if (player.dwarvenpart == 4 && player.getInventory().contains(29551)) {
						player.getDialogueManager().startDialogue("HeadMiner3");
					}
				}
				if (npc.getId() == 4585 && player.dwarvenpart == 1) {
					player.getDialogueManager().startDialogue("ProfessorOnglewip1");
				}
				if (npc.getId() == 4585 && player.dwarvenpart == 2 && player.getInventory().contains(29557)
						&& player.getInventory().contains(29556) && player.getInventory().contains(29555)) {
					player.getDialogueManager().startDialogue("ProfessorOnglewip2");
				}
				if (npc.getId() == 2778 && player.dwarvenpart == 2 && !player.getInventory().contains(29557)) {
					player.getInventory().addItem(29557, 1);
					player.sendMessage("You're given a letter");
				}
				if (npc.getId() == 794 && player.dwarvenpart == 2 && !player.getInventory().contains(29556)) {
					player.getInventory().addItem(29556, 1);
					player.sendMessage("You're given a letter");
				}
				if (npc.getId() == 544 && player.dwarvenpart == 2 && !player.getInventory().contains(29555)) {
					player.getInventory().addItem(29555, 1);
					player.sendMessage("You're given a letter");
				}

				/**
				 * end of TheDwarvenDiscovery
				 */
				if (npc.getId() == 1972) {
					player.getDialogueManager().startDialogue("WeeklyOverride");
					// player.sendMessage("1");
				}
				if (npc.getId() == 17143) {
					player.getDialogueManager().startDialogue("Ocellus");
				}
				if (npc.getId() == 1206) {
					player.getDialogueManager().startDialogue("ServerPolling");
				}
				if (npc.getId() == 9400) {
					player.getDialogueManager().startDialogue("Santa2016");
				}
				if (npc.getId() == 11280) {
					player.getDialogueManager().startDialogue("ChiefThiefRobin");
				}
				if (npc.getId() == 568) {
					player.applyHit(new Hit(player, player.getMaxHitpoints() * 10, HitLook.REGULAR_DAMAGE));
				}
				if (npc.getId() == 30088) {
					player.applyHit(new Hit(player, player.getMaxHitpoints() * 10, HitLook.REGULAR_DAMAGE));
				}
				if (npc.getId() == 817) {
					player.getDialogueManager().startDialogue("BossInstanceDialogue");
					player.sendMessage("Use one of these to enter an instance;");
					player.sendMessage("corp,bandos,sara,arma,zammy,kk,kbd,");
				}
				if (npc.getId() == 30160) {
					player.setNextWorldTile(new WorldTile(1382, 3830, 0));
				}
				if (npc.getId() == 30161) {
					player.setNextWorldTile(new WorldTile(1378, 3828, 0));
				}
				if (npc.getId() == 605) {
					player.setNextWorldTile(new WorldTile(2462, 4893, 1));
				}
				if (npc.getId() == 711) {
					player.setNextWorldTile(new WorldTile(2848, 5091, 0));
				}
				if (npc.getId() == 291) {
					player.getCombatDefinitions().resetSpecialAttack();
				}
				if (npc.getId() == 30068) {
					player.setNextWorldTile(new WorldTile(2630, 5063, 0));
				}
				if (npc.getId() == 921) {
					player.setNextWorldTile(new WorldTile(2717, 5325, 0));
					player.sendMessage("You can find all of the shops South.");
				}
				if (npc.getId() == 30069) {
					player.setNextWorldTile(new WorldTile(2639, 5065, 0));
				}
				if (npc.getId() == 885) {
					player.getDialogueManager().startDialogue("VetorTamer");
				}
				if (npc.getId() == 30060) {
					player.getDialogueManager().startDialogue("CommanderDrygon");
				}
				if (npc.getId() == 8206) {
					if (player.isPvpMode() || player.isIronman()) {
						player.getDialogueManager().startDialogue("SimpleMessage", "You cannot use the lottery.");
						return;
					}
					player.getDialogueManager().startDialogue("LotteryDialogue", npc);
				}
				if (npc.getId() == 30064) {
					player.getDialogueManager().startDialogue("DrygonicGod");
				}
				if (npc.getId() == 8226) {
					player.getDialogueManager().startDialogue("DropRateManager");
				}
				if (npc.getId() == 30052) {
					player.getDialogueManager().startDialogue("SkillingTask");
				}
				if (npc.getId() == 30051) {
					player.getDialogueManager().startDialogue("SkillingTaskRewards");
				}
				if (npc.getId() == 945) {
					player.getDialogueManager().startDialogue("Welcome");
					// player.getDialogueManager().startDialogue("XpRates");
				}
				if (npc.getId() == 30047) {
					player.getDialogueManager().startDialogue("Hetty");
				}
				if (npc.getId() == 30162) {
					ShopsHandler.openShop(player, 132);
				}
				if (npc.getId() == 30169) {
					ShopsHandler.openShop(player, 133);
				}
				if (npc.getId() == 30044) {
					ShopsHandler.openShop(player, 125);
				}
				if (npc.getId() == 300) {
					ShopsHandler.openShop(player, 129);
				}
				if (npc.getId() == 712) {
					player.setNextWorldTile(new WorldTile(2848, 5085, 0));
				}
				if (npc.getId() == 6183 && player.isDonator()) {
					player.setNextWorldTile(new WorldTile(3377, 3513, 0));
					player.sendMessage("Welcome to the donator boss.");
				}
				if (npc.getId() == 11551) {
					if (player.calamitykillpoints < 50) {
						player.sendMessage(
								"<col=ff0000>You only have " + player.calamitykillpoints + " points, you need 50!");
						return;
					} else if (player.getInventory().getFreeSlots() < 5) {
						player.sendMessage("<col=ff0000>You need at least 5 free inventory slots to buy this!");
						return;
					} else {
						player.calamitykillpoints -= 50;
						player.getInventory().addItem(15272, 5);
					}
				}
				if (npc.getId() == 8329) {
					if (player.calamitykillpoints < 150) {
						player.sendMessage(
								"<col=ff0000>You only have " + player.calamitykillpoints + " points, you need 150!");
						return;
					} else if (player.getInventory().getFreeSlots() < 4) {
						player.sendMessage("<col=ff0000>You need at least 4 free inventory slots to buy this!");
						return;
					} else {
						player.calamitykillpoints -= 150;
						player.getInventory().addItem(2434, 3);
						player.getInventory().addItem(15332, 1);
					}
				}
				if (npc.getId() == 1201) {
					if (player.calamitykillpoints < 150) {
						player.sendMessage(
								"<col=ff0000>You only have " + player.calamitykillpoints + " points, you need 150!");
						return;
					} else if (player.getInventory().getFreeSlots() < 11) {
						player.sendMessage("<col=ff0000>You need at least 11 free inventory slots to buy this!");
						return;
					} else {
						player.calamitykillpoints -= 150;
						player.getInventory().addItem(556, 100);
						player.getInventory().addItem(554, 100);
						player.getInventory().addItem(557, 100);
						player.getInventory().addItem(555, 100);
						player.getInventory().addItem(560, 100);
						player.getInventory().addItem(565, 100);
						player.getInventory().addItem(884, 100);
						player.getInventory().addItem(892, 100);
						player.getInventory().addItem(9140, 100);
						player.getInventory().addItem(9144, 100);
						player.getInventory().addItem(9242, 100);
					}
				}
				if (npc.getId() == 15465) {
					if (player.calamitykillpoints < 100) {
						player.sendMessage(
								"<col=ff0000>You only have " + player.calamitykillpoints + " points, you need 100!");
						return;
					} else if (player.getInventory().getFreeSlots() < 1) {
						player.sendMessage("<col=ff0000>You need` at least 1 free inventory slots to use this!");
						return;
					} else {
						CalamityMeleeWeps.RandomWepPick(player);
					}
				}
				if (npc.getId() == 13285) {
					if (player.calamitykillpoints < 100) {
						player.sendMessage(
								"<col=ff0000>You only have " + player.calamitykillpoints + " points, you need 100!");
						return;
					} else if (player.getInventory().getFreeSlots() < 1) {
						player.sendMessage("<col=ff0000>You need` at least 1 free inventory slots to use this!");
						return;
					} else {
						CalamityMRWeps.RandomWepPick(player);
					}
				}
				if (npc.getId() == 12320) {
					if (player.calamitykillpoints < 60) {
						player.sendMessage(
								"<col=ff0000>You only have " + player.calamitykillpoints + " points, you need 60!");
						return;
					} else if (player.getInventory().getFreeSlots() < 1) {
						player.sendMessage("<col=ff0000>You need` at least 1 free inventory slots to use this!");
						return;
					} else {
						CalamityMeleeArmour.RandomArmourPick(player);
					}
				}
				if (npc.getId() == 6706) {
					if (player.calamitykillpoints < 60) {
						player.sendMessage(
								"<col=ff0000>You only have " + player.calamitykillpoints + " points, you need 60!");
						return;
					} else if (player.getInventory().getFreeSlots() < 1) {
						player.sendMessage("<col=ff0000>You need` at least 1 free inventory slots to use this!");
						return;
					} else {
						CalamityMRArmour.RandomArmourPick(player);
					}
				}
				if (npc.getId() == 6524) {
					player.getDialogueManager().startDialogue("BobBarterD", npc.getId());
					return;
				}
				if (npc.getId() == 15584) {
					player.getDialogueManager().startDialogue("KaneProp", npc.getId());
					return;
				}
				if (npc.getId() == 13642) {
					player.getDialogueManager().startDialogue("QueenOfSnow", npc.getId());
					return;
				}
				if (npc.getId() == 30024) {
					if (!player.getInventory().contains(29787) && player.scientisthasegg == 0) {
						player.getDialogueManager().startDialogue("SimpleMessage", "I don't need to speak to you yet.");
						return;
					}
					player.getDialogueManager().startDialogue("Scientist", npc.getId());
					return;
				}
				if (npc.getId() == 9687) {
					if (player.completedEasterEvent == false) {
						player.getDialogueManager().startDialogue("SimpleMessage", "I don't need to speak to you yet.");
						return;
					} else if (player.completedEasterEvent == true && !player.getInventory().containsItem(29827, 100)) {
						player.getDialogueManager().startDialogue("SimpleMessage",
								"You need to get me 100 bunny ears!");
						return;
					} else {
						player.getDialogueManager().startDialogue("SimpleMessage",
								"GOODJOB! I've made you a gift to say thank you.");
						player.sendMessage("A bunny mask was added to your bank.");
						player.getInventory().deleteItem(29827, 100);
						player.getBank().addItem(29828, 1, true);
					}
				}

				if (npc.getId() == 14386) {
					if (player.getSkills().getLevelForXp(Skills.SLAYER) < 99) {
						player.sendMessage("You need a slayer level of 99 to access this slayer master.");
						return;
					}

					player.getDialogueManager().startDialogue("BossSlayer");
					// player.getDialogueManager().startDialogue("SimpleMessage",
					// "It's not time yet.");
				}
				if (npc.getId() == 6537) {
					player.getDialogueManager().startDialogue("LootBeamManagerD");
				}
				if (npc.getId() == 4949 && player.isExtremeDonator()) {
					if (player.isWeekend()) {
						player.sendMessage("You cannot purchase double xp during double xp weekend.");
						return;
					}
					player.getDialogueManager().startDialogue("DoubleXpLord");
					// player.getDialogueManager().startDialogue("SimpleMessage",
					// "It's not time yet.");
				}
				if (npc.getId() == 2241) {
					player.getDialogueManager().startDialogue("GeInterfaces");
					return;
				}
				if (npc.getId() == 556) {
					player.getInterfaceManager().sendInterface(1297);
					player.getPackets().sendIComponentText(1297, 249, "Donators Store");
					player.getPackets().sendIComponentText(1297, 124, "Information");
					player.getPackets().sendIComponentText(1297, 125, "Price");
					player.getPackets().sendIComponentText(1297, 273, "Shop 1");
					player.getPackets().sendIComponentText(1297, 266, "Shop 2");
					player.getPackets().sendIComponentText(1297, 259, "Shop 3");
					player.getPackets().sendIComponentText(1297, 88, "New woodcutting animation");
					player.getPackets().sendIComponentText(1297, 154, "<col=00FF00>5M</col>");
					player.getPackets().sendIComponentText(1297, 144, "New mining animation");
					player.getPackets().sendIComponentText(1297, 155, "<col=00FF00>5M</col>");
					player.getPackets().sendIComponentText(1297, 145, "Gnome teleport animation");
					player.getPackets().sendIComponentText(1297, 156, "<col=FF00FF>10M</col>");
					player.getPackets().sendIComponentText(1297, 146, "Funny death animation");
					player.getPackets().sendIComponentText(1297, 157, "<col=ff0000>20M</col>");
					player.getPackets().sendIComponentText(1297, 147, "New throwing weapon animation");
					player.getPackets().sendIComponentText(1297, 158, "<col=FF00FF>10M</col>");
					player.getPackets().sendIComponentText(1297, 148, "Old woodcutting animation");
					player.getPackets().sendIComponentText(1297, 159, "<col=FFFF00>Free</col>");
					player.getPackets().sendIComponentText(1297, 149, "Old mining animation");
					player.getPackets().sendIComponentText(1297, 160, "<col=FFFF00>Free</col>");
					player.getPackets().sendIComponentText(1297, 150, "Old teleport animation");
					player.getPackets().sendIComponentText(1297, 161, "<col=FFFF00>Free</col>");
					player.getPackets().sendIComponentText(1297, 151, "Old death animation");
					player.getPackets().sendIComponentText(1297, 162, "<col=FFFF00>Free</col>");
					player.getPackets().sendIComponentText(1297, 152, "Old Throwing weapon animation");
					player.getPackets().sendIComponentText(1297, 163, "<col=FFFF00>Free</col>");

				}

				if (npc.getId() == 6653) {

					player.getDialogueManager().startDialogue("DiaryExpert", npc.getId());
				}

				if (npc.getId() == 6185) {
					TitlesInterface.SendInterface(player);
					return;
				}

				if (npc.getId() == 501) {

					player.getDialogueManager().startDialogue("WildBank", npc.getId());
				}
				if (npc.getId() == 510) {

					player.getDialogueManager().startDialogue("WildFinish", npc.getId());
				}

				if (npc.getId() == 9032) {

					player.getDialogueManager().startDialogue("WildSkillFee", npc.getId());
				}

				if (npc.getId() == 8340) {

					// player.getDialogueManager().startDialogue("UpgradeArmour",
					// npc.getId());
					player.sendMessage("Sorry, i'm currently out of use.");
				}
				if (npc.getId() == 13930) { //30142
					player.getDialogueManager().startDialogue("UpgradeArmour");
				}
				if (npc.getId() == 30142) { //30142
					ShopsHandler.openShop(player, 119);
				}
				if (npc.getId() == 30144) {
					ShopsHandler.openShop(player, 130);
				}
				if (npc.getId() == 561) {
					ShopsHandler.openShop(player, 131);
				}
				if (npc.getId() == 3002) {
					ShopsHandler.openShop(player, 108);
				}
				if (npc.getId() == 15012) {
					ShopsHandler.openShop(player, 121);
				}
				if (npc.getId() == 30115) {
					ShopsHandler.openShop(player, 109);
				}

				if (npc.getId() == 2998) {
					// player.getDialogueManager().startDialogue("DryPoints", npc.getId());
					ShopsHandler.openShop(player, 110);
				}

				if (npc.getId() == 2996) {
					player.getDialogueManager().startDialogue("DryPointsD", npc.getId());
				}

				npc.faceEntity(player);
				if (npc.getId() == 3709) {
					player.getDialogueManager().startDialogue("MrEx", npc.getId());
				}
				npc.faceEntity(player);
				if (npc.getId() == 3705) {
					player.getDialogueManager().startDialogue("SkillingTeleport", npc.getId());
				} else if (npc.getId() == 5915) {
					player.getDialogueManager().startDialogue("ClaimClanItem", npc.getId(), 20709);
				} else if (npc.getId() == 13633) {
					player.getDialogueManager().startDialogue("ClaimClanItem", npc.getId(), 20708);
				} else if (npc.getId() == 4455) {
					player.getDialogueManager().startDialogue("Repair", npc.getId());
				} else if (npc.getId() == 11575) {
					player.getControlerManager().startControler("StartDryaxions", npc.getId());
				} else if (npc.getId() == 13251) {
					player.getDialogueManager().startDialogue("PrestigeOne");
				} else if (npc.getId() == 13727) {
					player.getDialogueManager().startDialogue("Xuans");
				} else if (npc.getId() == 2824) {
					player.getDialogueManager().startDialogue("TanningD", 2824);
				} else if (npc.getId() == 9085) {
					player.getDialogueManager().startDialogue("Kuradal", false);
				} else if (npc.getId() == 7780) {
					if (!player.isLegendaryDonator()) {
						player.getDialogueManager().startDialogue("SimpleMessage",
								"She is not interested in speaking with you.");
						return;
					}
					player.getDialogueManager().startDialogue("LegendarySlayerMaster", false);
				} else if (npc.getId() == 5532) {
					player.getDialogueManager().startDialogue("SorceressGardenNPCs", npc);
				} else if (npc.getId() == 813) {
					player.getDialogueManager().startDialogue("DonatorUpgrade");
				} else if (npc.getId() == 5563) {
					player.getDialogueManager().startDialogue("SorceressGardenNPCs", npc);
				} else if (npc.getId() == 5559) {
					player.sendDeath(npc);
				} else if (npc.getId() == 3000) {
					player.getDialogueManager().startDialogue("StatLog", npc.getId());
				} else if (npc.getId() == 15418) {
					player.getDialogueManager().startDialogue("RuneSpanLeaving", npc.getId());
				} else if (npc.getId() == 15451 && npc instanceof FireSpirit) {
					FireSpirit spirit = (FireSpirit) npc;
					spirit.giveReward(player);
				} else if (npc.getId() == 653) {
					if (player.getSkills().getCombatLevel() < 60) {
						player.getDialogueManager().startDialogue("SimpleMessage",
								"You need at least 60 combat level to enter this minigame.");
						return;
					} else if (Settings.hungergamesactive == false) {
						player.getDialogueManager().startDialogue("SimpleMessage",
								"Sorry the Hunger games is current inactive.");
						return;
					} else if (player.ironman == true || player.isPvpMode()) {
						player.getDialogueManager().startDialogue("SimpleMessage",
								"Sorry the Hunger games doesn't support Ironmen/Ironwomen.");
						return;
					} else if (player.getInventory().getFreeSlots() < 28 || player.getEquipment().wearingArmour()) {
						player.getDialogueManager().startDialogue("SimpleMessage",
								"You cannot take items into this minigame!");
						return;
					} else if (player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player)
							|| Pets.hasPet(player)) {
						player.getDialogueManager().startDialogue("SimpleMessage",
								"Sorry, pets aren't permitted here.!");
						return;
					} else {
						Magic.sendWhirlpoolTeleportSpell(player, 0, 0, new WorldTile(2355, 3865, 0));
						player.getControlerManager().startControler("Wilderness");
						player.sendMessage("<col=ff0000> DO NOT MOVE UNTIL YOU SEE THE ITEMS SPAWN");
						player.sendMessage("<col=ff0000> YOU WILL BE REMOVED FROM THE GAME IF YOU DO!");
						return;
					}
				} else if (npc.getId() == 949) {
					player.getDialogueManager().startDialogue("QuestGuide", npc.getId(), null);
				} else if (npc.getId() >= 1 && npc.getId() <= 6 || npc.getId() >= 7875 && npc.getId() <= 7884) {
					player.getDialogueManager().startDialogue("Man", npc.getId());
				} else if (npc.getId() == 198) {
					player.getDialogueManager().startDialogue("Guildmaster", npc.getId());
				} else if (npc.getId() == 9462 || npc.getId() == 9464 || npc.getId() == 9466) {
					Strykewyrm.handleStomping(player, npc);
				} else if (npc.getId() == 20629) {
					if (player.getAttackedByDelay() + 10000 > Utils.currentTimeMillis()) {
						player.getDialogueManager().startDialogue("SimpleMessage",
								"You can't stomp whilst under attack!");
						return;
					}
					LavaStryke.handleStomping(player, npc);
				} else if (npc.getId() == 9707) {
					player.getDialogueManager().startDialogue("FremennikShipmaster", npc.getId(), true);
				} else if (npc.getId() == 9708) {
					player.getDialogueManager().startDialogue("FremennikShipmaster", npc.getId(), false);
				} else if (npc.getId() == 6034) {
					player.getDialogueManager().startDialogue("ThirdAgeCreate", npc.getId(), true);
				} else if (npc.getId() == 9492) {
					player.getDialogueManager().startDialogue("Pikkenmix");
				} else if (npc.getId() == 14870) { //905
					ShopsHandler.openShop(player, 103);
				} else if (npc.getId() == 562) {
					ShopsHandler.openShop(player, 32);
				} else if (npc.getId() == 2938) {
					ShopsHandler.openShop(player, 93);
				} else if (npc.getId() == 2725) {
					ShopsHandler.openShop(player, 94);
				} else if (npc.getId() == 1834) {
					ShopsHandler.openShop(player, 30);
				} else if (npc.getId() == 804) {
					ShopsHandler.openShop(player, 102);
				} else if (npc.getId() == 5113) {
					ShopsHandler.openShop(player, 27);
				} else if (npc.getId() == 5865) {
					ShopsHandler.openShop(player, 10);
				} else if (npc.getId() == 528) {
					ShopsHandler.openShop(player, 1);
				} else if (npc.getId() == 546) {
					ShopsHandler.openShop(player, 3);
				} else if (npc.getId() == 7950) {
					ShopsHandler.openShop(player, 4);
				} else if (npc.getId() == 550) {
					ShopsHandler.openShop(player, 5);
				} else if (npc.getId() == 519) {
					ShopsHandler.openShop(player, 6);
				} else if (npc.getId() == 549) {
					ShopsHandler.openShop(player, 7);
				} else if (npc.getId() == 8864) {
					ShopsHandler.openShop(player, 8);
				} else if (npc.getId() == 481) {
					ShopsHandler.openShop(player, 9);
				} else if (npc.getId() == 219) {
					ShopsHandler.openShop(player, 13);
				} else if (npc.getId() == 587) {
					ShopsHandler.openShop(player, 14);
				} else if (npc.getId() == 805) {
					ShopsHandler.openShop(player, 15);
				} else if (npc.getId() == 786) {
					ShopsHandler.openShop(player, 23);
				} else if (npc.getId() == 713) {
					ShopsHandler.openShop(player, 22);
				} else if (npc.getId() == 1923) {
					ShopsHandler.openShop(player, 19);
				} else if (npc.getId() == 2253) {
					player.getDialogueManager().startDialogue("Npcshop2", npc.getId());
				} else if (npc.getId() == 2676) {
					PlayerLook.openMageMakeOver(player);
				} else if (npc.getId() == 598) {
					player.getDialogueManager().startDialogue("Hairdresser", npc.getId());
				} else if (npc.getId() == 548) {
					player.getDialogueManager().startDialogue("Thessalia", npc.getId());
				} else if (npc.getId() == 659) {
					player.getDialogueManager().startDialogue("PartyPete");
				} else if (npc.getId() == 579) {
					player.getDialogueManager().startDialogue("DrogoDwarf", npc.getId());
				} else if (npc.getId() == 582) {
					player.getDialogueManager().startDialogue("GeneralStore", npc.getId(), 31);
				} else if (npc.getId() == 528 || npc.getId() == 529) {
					player.getDialogueManager().startDialogue("GeneralStore", npc.getId(), 1);
				} else if (npc.getId() == 522 || npc.getId() == 523) {
					player.getDialogueManager().startDialogue("GeneralStore", npc.getId(), 8);
				} else if (npc.getId() == 520 || npc.getId() == 521) {
					player.getDialogueManager().startDialogue("GeneralStore", npc.getId(), 4);
				} else if (npc.getId() == 594) {
					player.getDialogueManager().startDialogue("Nurmof", npc.getId());
				} else if (npc.getId() == 665) {
					player.getDialogueManager().startDialogue("BootDwarf", npc.getId());
				} else if (npc.getId() == 382 || npc.getId() == 3294 || npc.getId() == 4316) {
					player.getDialogueManager().startDialogue("MiningGuildDwarf", npc.getId(), false);
				} else if (npc.getId() == 3295) {
					player.getDialogueManager().startDialogue("MiningGuildDwarf", npc.getId(), true);
				} else if (npc.getId() == 537) {
					player.getDialogueManager().startDialogue("Scavvo", npc.getId());
				} else if (npc.getId() == 536) {
					player.getDialogueManager().startDialogue("Valaine", npc.getId());
				} else if (npc.getId() == 4563) {
					player.getDialogueManager().startDialogue("Hura", npc.getId());
				} else if (npc.getId() == 2617) {
					player.getDialogueManager().startDialogue("TzHaarMejJal", npc.getId());
				} else if (npc.getId() == 2618) {
					player.getDialogueManager().startDialogue("TzHaarMejKah", npc.getId());
				} else if (npc.getId() == 13768) {
					player.getDialogueManager().startDialogue("Dunghandler", npc.getId());
				} else if (npc.getId() == 278) {
					player.getControlerManager().startControler("RecipeforDisaster");
				} else if (npc.getId() == 654) {
					player.getDialogueManager().startDialogue("Shamus", npc.getId());
				} else if (npc.getId() == 650) {
					player.getDialogueManager().startDialogue("Warrior", npc.getId());
				} else if (npc.getId() == 2729 || npc.getId() == 657 || npc.getId() == 2728) {
					player.getDialogueManager().startDialogue("MonkOfEntrana", npc.getId());
				} else if (npc.getId() == 3021) {
					player.getDialogueManager().startDialogue("FarmingTeleport");
				} else if (npc instanceof Pet) {
					Pet pet = (Pet) npc;
					if (pet != player.getPet()) {
						if (pet.getId() == 30173) {
							player.getDialogueManager().startDialogue("SimpleMessage",
									"You don't own me, but have a sandwich...");
							if (Utils.random(2) == 0) {
								player.getInventory().addItem(6962, 1);
							} else {
								player.getInventory().addItem(6965, 1);
							}
						} else if (pet.getId() == 30174) {
							player.getDialogueManager().startDialogue("SimpleMessage", "Here's some weed my g");
							player.getInventory().addItem(6055, 1);
						}
						player.getPackets().sendGameMessage("This isn't your pet!");
						// player.sendMessage(""+owner.+"");
						return;
					}

					if (player.getInventory().getFreeSlots() < 1) {
						player.getPackets().sendGameMessage("You need more inventory space to pick up this pet.");
						return;
					}
					player.setNextAnimation(new Animation(827));
					pet.pickup();
				} else {
					// player.getPackets().sendGameMessage(
					// "");
					if (Settings.DEBUG) {
						System.out.println("cliked 1 at npc id : " + npc.getId() + ", " + npc.getX() + ", " + npc.getY()
								+ ", " + npc.getPlane());
					}
				}
			}
		}, npc.getSize()));
	}

	public static void handleOption2(final Player player, InputStream stream) {
		if (!player.hasEnteredPin && player.hasBankPin) {
			player.getTemporaryAttributtes().put("bank_pin1", Boolean.TRUE);
			player.getPackets().sendRunScript(108, new Object[] { "Enter Your Bank Pin Please" });
			return;
		}
		int npcIndex = stream.readUnsignedShort128();
		boolean forceRun = stream.read128Byte() == 1;
		final NPC npc = World.getNPCs().get(npcIndex);
		if (npc == null || npc.isCantInteract() || npc.isDead() || npc.hasFinished()
				|| !player.getMapRegionsIds().contains(npc.getRegionId())) {
			return;
		}
		player.stopAll(false);
		if (forceRun) {
			player.setRun(forceRun);
		}
		if (npc.getDefinitions().name.contains("Banker") || npc.getDefinitions().name.contains("banker")) {
			player.faceEntity(npc);
			if (!player.withinDistance(npc, 2)) {
				return;
			}
			npc.faceEntity(player);
			player.getBank().openBank();
			return;
		}

		/*
		 * if (npc.getId() == 17161) { if (player.getControlerManager().getControler()
		 * == null || !(player.getControlerManager().getControler() instanceof
		 * VoragoInstanceController)) {
		 * player.getDialogueManager().startDialogue("SimpleMessage",
		 * "Vorago is not responding to your calls looks like he can't see you in the room. Try leaving and re-entering the instance again."
		 * ); return; } }
		 */
		player.setCoordsEvent(new CoordsEvent(npc, new Runnable() {
			@Override
			public void run() {
				npc.resetWalkSteps();
				player.faceEntity(npc);
				FishingSpots spot = FishingSpots.forId(npc.getId() | 2 << 24);
				if (spot != null) {
					player.getActionManager().setAction(new Fishing(spot, npc));
					return;
				}
				PickPocketableNPC pocket = PickPocketableNPC.get(npc.getId());
				if (pocket != null) {
					player.getActionManager().setAction(new PickPocketAction(npc, pocket));
					return;
				}
				if (player.getFarmingManager().isGardening(npc.getId(), 2)) {
					return;
				}
				if (npc.getId() == 9085) {
					player.getDialogueManager().startDialogue("KuradalGetTask", false);
				}
				if (npc.getId() == 7780) {
					player.getDialogueManager().startDialogue("SumonaGetTask", false);
				}
				if (npc.getId() == 30021) {
					Pet pet = (Pet) npc;
					if (pet != player.getPet()) {
						player.getPackets().sendGameMessage("You aren't worthy enough to have this pet!");
						return;
					}
					if (player.getControlerManager().getControler() instanceof Wilderness) {
						player.sendMessage("You cannot do this whilst in the wild!");
						return;
					}
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2594, 5582, 0));
				}
				if (npc.getId() == 1911) {
					if (!player.getInventory().hasFreeSlots()) {
						player.sendMessage("Seems like you can't carry anymore feathers!");
						return;
					}
					player.setNextAnimation(new Animation(3667));
					player.getInventory().addItem(4621, 1);
					return;
				}
				if (npc.getId() == 9711) {
					ShopsHandler.openShop(player, 107);
				}
				if (npc.getId() == 1129) {
					player.setNextAnimation(new Animation(881));
					if (player.getInventory().contains(20732)) {
						player.sendMessage("You have no need to do this.");
						return;
					}
					if (player.getInventory().contains(1485) || player.getInventory().contains(14496)) {
						player.sendMessage("You've already taken enough.");
						return;
					}
					player.sendMessage("<col=ff0000>You find a damp cloth");
					player.getInventory().addItem(1485, 1);
					return;
				}
				// if (npc instanceof Pet) {
				if (npc.getId() == 30029) {
					Pet pet = (Pet) npc;
					if (pet != player.getPet()) {
						player.getPackets().sendGameMessage("This isn't your pet!");
						return;
					}
					npc.setNextForceTalk(new ForceTalk(
							"" + player.getDisplayName() + " has " + player.PdemonKills + " Party Demon kills!"));
				}
				if (npc.getId() == 13656) {
					Pet pet = (Pet) npc;
					if (pet != player.getPet()) {
						player.getPackets().sendGameMessage("This isn't your pet!");
						return;
					}
					if (player.superclaimedtoday == true) {
						npc.setNextForceTalk(new ForceTalk("Sorry boss, i have no stock for you!"));

					} else if (player.superclaimedtoday == false) {
						npc.setNextForceTalk(new ForceTalk("I have activated your daily 60 minutes double xp boss."));
						player.d60mxp1 += 60;
						player.superclaimedtoday = true;
					}
				}
				if (npc.getId() == 19716) {
					player.getDialogueManager().startDialogue("WeeklyOverride");
				}
				if (npc.getId() == 30032) {
					Pet pet = (Pet) npc;
					if (pet != player.getPet()) {
						player.getPackets().sendGameMessage("This isn't your pet!");
						return;
					}
					npc.setNextForceTalk(new ForceTalk("" + player.getDisplayName() + " was rewarded me after looting "
							+ player.ingenuityjrachieved + " Ingenuity chests!"));
				}
				if (npc.getId() == 30033) {
					Pet pet = (Pet) npc;
					if (pet != player.getPet()) {
						player.getPackets().sendGameMessage("This isn't your pet!");
						return;
					}
					npc.setNextForceTalk(new ForceTalk("" + player.getDisplayName() + " was rewarded me after looting "
							+ player.redeemerachieved + " Ingenuity chests!"));
				}
				// }
				if (npc.getId() == 14706) {
					ShopsHandler.openShop(player, 115);
				}
				if (npc.getId() == 29993) {
					if (!player.getEquipment().hasShield()) {
						player.sendMessage("You need a shield to prevent this hit!");
						return;
					}
					if (npc.kkbighit == true && player.getEquipment().hasShield()) {
						if (npc.kkbighitprevent <= 0) {
							npc.kkbighit = false;
							return;
						}
						npc.kkbighitprevent -= 1;
						return;
					}
				}
				// if (npc.getId() == 16697) {
				// if (player.getEquipment().getWeaponId() != 29973 ||
				// player.getEquipment().getWeaponId() != 29972
				// || player.getEquipment().getWeaponId() != 29971
				// || player.getEquipment().getWeaponId() != 29970
				// || player.getEquipment().getWeaponId() != 29969
				// || player.getEquipment().getWeaponId() != 29968) {
				// player.applyHit(new Hit(player, player.getMaxHitpoints() / 5 + 400,
				// HitLook.REGULAR_DAMAGE, 15));
				// npc.kkbighit = true;
				// npc.kkbighitprevent = 20;
				// return;
				// }
				// }
				if (npc instanceof Familiar) {
					if (npc.getDefinitions().hasOption("store")) {
						if (player.getFamiliar() != npc) {
							player.getPackets().sendGameMessage("That isn't your familiar.");
							return;
						}
						player.getFamiliar().store();
					} else if (npc.getDefinitions().hasOption("cure")) {
						if (player.getFamiliar() != npc) {
							player.getPackets().sendGameMessage("That isn't your familiar.");
							return;
						}
						if (!player.getPoison().isPoisoned()) {
							player.getPackets().sendGameMessage("Your arent poisoned or diseased.");
							return;
						} else {
							player.getFamiliar().drainSpecial(2);
							player.addPoisonImmune(120);
						}
					}
					return;
				}
				if (player.getControlerManager().getControler() == null
						|| player.getControlerManager().getControler() != null) {
					npc.faceEntity(player);
				}
				if (!player.getControlerManager().processNPCClick2(npc)) {
					return;
				}
				if (npc.getId() == 9707) {
					FremennikShipmaster.sail(player, true);
				} else if (npc.getId() == 9708) {
					FremennikShipmaster.sail(player, false);
				} else if (npc.getId() == 556) {
					if (!player.isExtremeDonator()) {
						player.sendMessage(Colors.red + "Auras in this shop are EXTREME donator only!");
						return;
					}
					ShopsHandler.openShop(player, 34);
					// player.sendMessage(Colors.red + "Auras in this shop are EXTREME donator
					// only!");
				} else if (npc.getId() == 6970) {
					ShopsHandler.openShop(player, 16);
				} else if (npc.getId() == 30060) {
					ShopsHandler.openShop(player, 104);
				} else if (npc.getId() == 9492) {
					ShopsHandler.openShop(player, 21);
				} else if (npc.getId() == 2824) {
					player.getDialogueManager().startDialogue("TanningD", 2824);
					// System.out.println("1");
				} else if (npc.getId() == 3000) {
					player.getDialogueManager().startDialogue("StatLog", npc.getId());
				} else if (npc.getId() == 13455 || npc.getId() == 2617 || npc.getId() == 2618 || npc.getId() == 15194) {
					player.getBank().openBank();
				} else if (npc.getId() == 1419 || npc.getId() == 19911 || npc.getId() == 2240 || npc.getId() == 2241
						|| npc.getId() == 2593) {
					GrandExchangeSystem.get().display(player);
				} else if (npc.getId() == 2622) {
					ShopsHandler.openShop(player, 29);
				} else if (npc.getId() == 13727) {
					ShopsHandler.openShop(player, 31);
				} else if (npc.getId() == 5915) {
					player.getDialogueManager().startDialogue("ClaimClanItem", npc.getId(), 20709);
				} else if (npc.getId() == 13633) {
					player.getDialogueManager().startDialogue("ClaimClanItem", npc.getId(), 20708);
				} else if (npc.getId() == 2676) {
					PlayerLook.openMageMakeOver(player);
				} else if (npc.getId() == 598) {
					PlayerLook.openHairdresserSalon(player);
				} else if (npc instanceof Pet) {
					if (npc != player.getPet()) {
						player.getPackets().sendGameMessage("This isn't your pet!");
						return;
					}
					Pet pet = player.getPet();
					player.getPackets().sendMessage(99,
							"Pet [id=" + pet.getId() + ", hunger=" + pet.getDetails().getHunger() + ", growth="
									+ pet.getDetails().getGrowth() + ", stage=" + pet.getDetails().getStage() + "].",
							player);
				} else {
					player.getPackets().sendGameMessage("Nothing interesting happens.");
					if (Settings.DEBUG) {
						System.out.println("cliked 2 at npc id : " + npc.getId() + ", " + npc.getX() + ", " + npc.getY()
								+ ", " + npc.getPlane());
					}
				}
			}
		}, npc.getSize()));
	}

	public static void handleOption3(final Player player, InputStream stream) {
		if (!player.hasEnteredPin && player.hasBankPin) {
			player.getTemporaryAttributtes().put("bank_pin1", Boolean.TRUE);
			player.getPackets().sendRunScript(108, new Object[] { "Enter Your Bank Pin Please" });
			return;
		}
		int npcIndex = stream.readUnsignedShort128();
		boolean forceRun = stream.read128Byte() == 1;
		final NPC npc = World.getNPCs().get(npcIndex);
		if (npc == null || npc.isCantInteract() || npc.isDead() || npc.hasFinished()
				|| !player.getMapRegionsIds().contains(npc.getRegionId())) {
			return;
		}
		player.stopAll(false);
		if (forceRun) {
			player.setRun(forceRun);
		}
		player.setCoordsEvent(new CoordsEvent(npc, new Runnable() {
			@Override
			public void run() {
				npc.resetWalkSteps();
				if (!player.getControlerManager().processNPCClick3(npc)) {
					return;
				}
				player.faceEntity(npc);
				if (npc.getId() >= 8837 && npc.getId() <= 8839) {
					MiningBase.propect(player, "You examine the remains...",
							"The remains contain traces of living minerals.");
					return;

				}
				npc.faceEntity(player);
				if (player.getFarmingManager().isGardening(npc.getId(), 3)) {
					return;
				}
				if (npc.getId() == 8462 || npc.getId() == 8464 || npc.getId() == 1597 || npc.getId() == 1598
						|| npc.getId() == 7780 || npc.getId() == 8467 || npc.getId() == 9084) {
					ShopsHandler.openShop(player, 29);
				}
				if (npc instanceof Pet && npc.getId() == 30021) {
					Pet pet = (Pet) npc;
					if (pet != player.getPet()) {
						player.getPackets().sendGameMessage("You aren't worthy enough to have this pet!");
						return;
					}
					player.getBank().openBank();
				} else if (npc.getId() == 548) {
					PlayerLook.openThessaliasMakeOver(player);
				} else if (npc.getId() == 1526) {
					ShopsHandler.openShop(player, 95);
				} else if (npc.getId() == 4250) {
					ShopsHandler.openShop(player, 128);
				} else if (npc.getId() == 13727) {
					player.getAppearence().setTitle(0);
				} else if (npc.getId() == 3705) {
					ShopsHandler.openShop(player, 21);
				} else if (npc.getId() == 9085) {
					ShopsHandler.openShop(player, 117);
				} else if (npc.getId() == 3000) {
					player.getDialogueManager().startDialogue("StatLog", npc.getId());
				} else if (npc.getId() == 5532) {
					npc.setNextForceTalk(new ForceTalk("Senventior Disthinte Molesko!"));
					player.getControlerManager().startControler("SorceressGarden");

				} else {
					player.getPackets().sendGameMessage("Nothing interesting happens.");
				}
			}
		}, npc.getSize()));
		if (Settings.DEBUG) {
			System.out.println("cliked 3 at npc id : " + npc.getId() + ", " + npc.getX() + ", " + npc.getY() + ", "
					+ npc.getPlane());
		}
	}
}
