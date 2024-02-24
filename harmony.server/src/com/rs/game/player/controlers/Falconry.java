package com.rs.game.player.controlers;

import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.npc.others.PartyDemon;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.DailyChallenges;
import com.rs.game.player.content.Hunter;
import com.rs.game.player.content.Hunter.DynamicFormula;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Logger;
import com.rs.utils.Utils;

public class Falconry extends Controler {

	public int[] xp = {104, 132, 156};
	public int[] furRewards = {10125, 10115, 10127};
	public int[] levels = {43, 57, 69};
	
	public static boolean hasSpawnedKebbits;

	public static void beginFalconry(Player player) {
		if (player.getEquipment().hasShield() || player.getEquipment().hasTwoHandedWeapon() || player.getEquipment().hasWeapon()) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You need both hands free to use a falcon.");
			return;
		}
		if ((player.getEquipment().getItem(3) != null && player.getEquipment().getItem(3).getId() == -1) || (player.getEquipment().getItem(5) != null && player.getEquipment().getItem(5).getId() == -1)) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You need both hands free to use a falcon.");
			return;
		} else if (player.getSkills().getLevel(Skills.HUNTER) < 43) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You need a Hunter level of at least 43 to use a falcon, come back later.");
			return;
		}
		player.getControlerManager().startControler("Falconry");
	}

	@Override
	public void start() {
		player.setNextAnimation(new Animation(1560));
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.setNextWorldTile(new WorldTile(2371, 3619, 0));
				if (hasSpawnedKebbits == true) {
					Logger.log("Falconry", "Kebbit's already spawned.");
				} else { 
					spawnKebbits();
					hasSpawnedKebbits = true;
				}
			}
		});
		player.getEquipment().getItems().set(3, new Item(10024, 1));
		player.getEquipment().refresh(3);
		player.getAppearence().generateAppearenceData();
		player.getDialogueManager().startDialogue("SimpleMessage", "Simply click on the target and try your luck.");
	}

	@Override
	public boolean canEquip(int slotId, int itemId) {
		if (slotId == 3 || slotId == 5)
			return false;
		return true;
	}

	@Override
	public void magicTeleported(int type) {
		forceClose();
	}

	@Override
	public void forceClose() {
		player.getEquipment().getItems().remove(3, new Item(10024, 1));
		player.getEquipment().refresh(3);
		player.getAppearence().generateAppearenceData();
		hasSpawnedKebbits = false;
		removeControler();
	}
	
	/**
	 * The Queen Black Dragon NPC.
	 */
	private PartyDemon npc;
	
	@Override
	public boolean processObjectClick1(WorldObject object) {
		if (object.getId() == 70815) {
			player.getDialogueManager().startDialogue("PdemonChest", npc);
			return false;
		}
			
		return true;
	}

	@Override
	public boolean processNPCClick1(final NPC npc) {
		player.setNextFaceEntity(npc);
		if (npc.getDefinitions().name.toLowerCase().contains("kebbit")) {
			if (player.getTemporaryAttributtes().get("falconReleased") != null) {
				player.getDialogueManager().startDialogue("SimpleMessage", "You cannot catch a kebbit without your falcon.");
				return false;
			}
			int level = levels[(npc.getId() - 5098)];
			if (proccessFalconAttack(npc)) {
				if (player.getSkills().getLevel(Skills.HUNTER) < level) {
					player.getDialogueManager().startDialogue("SimpleMessage", "You need a Hunter level of "+ level + " to capture this kebbit.");
					return true;
				} else if (Hunter.isSuccessful(player, level, new DynamicFormula() {
					@Override
					public int getExtraProbablity(Player player) {
						if (player.getEquipment().getGlovesId() == 10075)
							return 3;
						return 1;
					}
				})) {
					player.getEquipment().getItems().set(3, new Item(10023, 1));
					player.getEquipment().refresh(3);
					player.getAppearence().generateAppearenceData();
					player.getTemporaryAttributtes().put("falconReleased", true);
					WorldTasksManager.schedule(new WorldTask() {
						@Override
						public void run() {
							World.sendProjectile(player, npc, 918, 41, 16, 31, 35, 16, 0);
							WorldTasksManager.schedule(new WorldTask() {
								@Override
								public void run() {
									npc.transformIntoNPC(npc.getId() - 4);
									player.getTemporaryAttributtes().put("ownedFalcon", npc);
									player.getPackets().sendGameMessage("The falcon successfully swoops down and captures the kebbit.");
									npc.setRandomWalk(false);
									player.getHintIconsManager().addHintIcon(npc, 1, -1, false);
									
								}
							});
						}
					});
				} else {
					player.getEquipment().getItems().set(3, new Item(10023, 1));
					player.getEquipment().refresh(3);
					player.getAppearence().generateAppearenceData();
					player.getTemporaryAttributtes().put("falconReleased", true);
					WorldTasksManager.schedule(new WorldTask() {
						@Override
						public void run() {
							World.sendProjectile(player, npc, 918, 41, 16, 31, 35, 16, 0);
							WorldTasksManager.schedule(new WorldTask() {
								@Override
								public void run() {
									World.sendProjectile(npc, player, 918, 41, 16, 31, 35, 16, 0);
									WorldTasksManager.schedule(new WorldTask() {
										@Override
										public void run() {
											player.getEquipment().getItems().set(3, new Item(10024, 1));
											player.getEquipment().refresh(3);
											player.getAppearence().generateAppearenceData();
											player.getTemporaryAttributtes().remove("falconReleased");
											player.getPackets().sendGameMessage("The falcon swoops down on the kebbit, but just barely misses catching it.");
										}
									});
								}
							}, Utils.getDistance(player, npc) > 3 ? 2 : 1);
						}
					});
				}
			}
		} else if (npc.getDefinitions().name.toLowerCase().contains("gyr falcon")){
			NPC kill = (NPC) player.getTemporaryAttributtes().get("ownedFalcon");
			if(kill == null)
				return false;
			if (kill != npc) {
				player.getDialogueManager().startDialogue("SimpleMessage", "This isn't your kill!");
				return false;
			}
			npc.setRespawnTask();
			player.getInventory().addItem(new Item(furRewards[(npc.getId() - 5094)], 1));
			player.getInventory().addItem(new Item(526, 1));
			player.getSkills().addXp(Skills.HUNTER, xp[(npc.getId() - 5094)]);
			// SeasonEvent.HandleActivity(player, "Falconry");
			if (player.challengeid == 33 && player.challengeamount > 0) {
				DailyChallenges.UpdateChallenge(player);
			}
			player.getPackets().sendGameMessage("You retreive the falcon as well as the fur of the dead kebbit.");
			if(Utils.random(99999) == 0) {
				if (player.getPet() == null && player.hunterpet != true) {
					player.getPetManager().spawnPet(29525, false);
					player.hunterpet = true;
					player.sendMessage("You have a funny feeling something is following you.");
					World.sendWorldMessage("<img=7><col=339966>News: "+player.getDisplayName()+" has just found a Hunter pet! ("+ Utils.getFormattedNumber((int) player.getSkills().getXp(Skills.HUNTER))+"XP)", false);
				} else if (player.getPet() != null && player.hunterpet != true) {
					player.hunterpet = true;
					player.sendMessage("Speak to the pet tamer at home to claim your pet.");
					World.sendWorldMessage("<img=7><col=339966>News: "+player.getDisplayName()+" has just found a Hunter pet! ("+ Utils.getFormattedNumber((int) player.getSkills().getXp(Skills.HUNTER))+"XP)", false);
				} else {
					player.sendMessage("You feel like something was following you.");
				}
			}
			player.getHintIconsManager().removeUnsavedHintIcon();
			player.getEquipment().getItems().set(3, new Item(10024, 1));
			player.getEquipment().refresh(3);
			player.getAppearence().generateAppearenceData();
			npc.transformIntoNPC(npc.getId() + 4);
			player.getTemporaryAttributtes().remove("ownedFalcon");
			player.getTemporaryAttributtes().remove("falconReleased");
			return true;
		}
		return true;
	}
	
	public static void spawnKebbits() {
		World.spawnNPC(5098, new WorldTile(2379, 3600, 0), -1, false, false);
		World.spawnNPC(5098, new WorldTile(2379, 3596, 0), -1, false, false);
		World.spawnNPC(5098, new WorldTile(2372, 3593, 0), -1, false, false);
		World.spawnNPC(5098, new WorldTile(2370, 3594, 0), -1, false, false);
		World.spawnNPC(5098, new WorldTile(2372, 3598, 0), -1, false, false);
		//End of 1 npc
		World.spawnNPC(5099, new WorldTile(2374, 3590, 0), -1, false, false);
		World.spawnNPC(5099, new WorldTile(2384, 3592, 0), -1, false, false);
		World.spawnNPC(5099, new WorldTile(2379, 3603, 0), -1, false, false);
		World.spawnNPC(5099, new WorldTile(2371, 3604, 0), -1, false, false);
		World.spawnNPC(5099, new WorldTile(2377, 3596, 0), -1, false, false);
		//End of 2 npc
		World.spawnNPC(5099, new WorldTile(2377, 3596, 0), -1, false, false);
		World.spawnNPC(5099, new WorldTile(2377, 3591, 0), -1, false, false);
		World.spawnNPC(5099, new WorldTile(2380, 3603, 0), -1, false, false);
		World.spawnNPC(5099, new WorldTile(2380, 3609, 0), -1, false, false);
		World.spawnNPC(5099, new WorldTile(2370, 3595, 0), -1, false, false);
		//End of 3 npc
		World.spawnNPC(5100, new WorldTile(2378, 3608, 0), -1, false, false);
		World.spawnNPC(5100, new WorldTile(2372, 3593, 0), -1, false, false);
		hasSpawnedKebbits = true;
	}
	
	private boolean proccessFalconAttack(NPC target) {
		int distanceX = player.getX() - target.getX();
		int distanceY = player.getY() - target.getY();
		int size = player.getSize();
		int maxDistance = 16;
		player.resetWalkSteps();
		if ((!player.clipedProjectile(target, maxDistance  == 0)) || distanceX > size + maxDistance || distanceX < -1 - maxDistance || distanceY > size + maxDistance || distanceY < -1 - maxDistance) {
			if(!player.addWalkStepsInteract(target.getX(), target.getY(), 2, size, true)) 
				return true;
		}
		return true;
	}
}