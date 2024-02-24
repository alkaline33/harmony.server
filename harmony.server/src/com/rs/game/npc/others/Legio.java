package com.rs.game.npc.others;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Hit;
import com.rs.game.World;
import com.rs.game.Hit.HitLook;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class Legio extends NPC {

    private static final long serialVersionUID = 5759957167640447468L;

    private int ordinal;
    private String owner;
    private int attackStage;
    private int healthStage;
    private int activated;

    public Legio(String owner, int ordinal) {
	super(17149 + ordinal, getSpawnTile(ordinal), -1, true, true);
	this.ordinal = ordinal;
	this.owner = owner;
	healthStage = 0;
	activated = 0;
    }

    public int getOrdinal() {
	return ordinal;
    }

    public static WorldTile getSpawnTile(int index) {
	switch (index) {
	case 0:
	    return new WorldTile(1016, 643, 1);
	case 1:
	    return new WorldTile(1106, 683, 1);
	case 2:
	    return new WorldTile(1107, 652, 1);
	case 3:
	    return new WorldTile(1164, 625, 1);
	case 4:
	    return new WorldTile(1203, 629, 1);
	case 5:
	    return new WorldTile(1183, 605, 1);
	default:
	    return null;
	}
    }

    @Override
    public void sendDeath(final Entity source) {
    	final NPCCombatDefinitions defs = getCombatDefinitions();
    	resetWalkSteps();
    	getCombat().removeTarget();
    	setNextAnimation(null);
    	if (!isDead())
    		setHitpoints(0);
    	setAttackStage(0);
    	final int deathDelay = defs.getDeathDelay();
    	WorldTasksManager.schedule(new WorldTask() {
    		int loop;

    		@Override
    		public void run() {
    			if (loop == 0) {
    				setNextAnimation(new Animation(defs.getDeathEmote()));
    			} else if (loop >= deathDelay) {
    				drop();
    				reset();
    				finish();
    				if (ordinal == 4)
    					AscensionDungeon.clearLines();
    				if (ordinal == 2)
    					AscensionDungeon.clearMinions();
    				AscensionDungeon.FIGHTS_ACTIVE[ordinal] = null;
    				if (source.getAttackedBy() == Legio.this) {
    					source.setAttackedByDelay(0);
    					source.setAttackedBy(null);
    					source.setFindTargetDelay(0);
    				}
    				stop();
    			}
    			loop++;
    		}
    	}, 0, 1);
    }

    @Override
    public void processEntity() {
    	super.processEntity();
	if (getCombat().getTarget() != null) {
	    if (ordinal == 4) {
		for (WorldObject o : AscensionDungeon.LINES) {
		    if (o != null) {
			if (o.isAt(getCombat().getTarget().getX(), getCombat().getTarget().getY()))
			    getCombat().getTarget().applyHit(new Hit(this, Utils.random(150, 250), HitLook.MAGIC_DAMAGE));
		    }
		}
	    }
	}
	if (this.getHitpoints() <= 7500) {
	    healthStage = 3;
	    if (activated < 3) {
		if (ordinal == 2) {
		    AscensionDungeon.spawnMinion(findClosestFreeTile(), 17146);
		} else if (ordinal == 4) {
		    if (getCombat().getTarget() != null)
			AscensionDungeon.generateLine(getCombat().getTarget(), false);
		} else if (ordinal == 3) {
		    if (getCombat().getTarget() != null) {
			WorldTile tile = findClosestFreeTile();
			getCombat().getTarget().setNextWorldTile(tile);
			AscensionDungeon.handleLightning(this, tile, 150, 230);
		    }
		}
		activated = 3;
	    }
	} else if (this.getHitpoints() <= 15000) {
	    healthStage = 2;
	    if (activated < 2) {
		if (ordinal == 2) {
		    AscensionDungeon.spawnMinion(findClosestFreeTile(), 17145);
		} else if (ordinal == 4) {
		    if (getCombat().getTarget() != null)
			AscensionDungeon.generateLine(getCombat().getTarget(), true);
		} else if (ordinal == 3) {
		    if (getCombat().getTarget() != null) {
			WorldTile tile = findClosestFreeTile();
			getCombat().getTarget().setNextWorldTile(tile);
			AscensionDungeon.handleLightning(this, tile, 150, 230);
		    }
		}
		activated = 2;
	    }
	} else if (this.getHitpoints() <= 22500) {
	    healthStage = 1;
	    if (activated < 1) {
		if (ordinal == 2) {
		    AscensionDungeon.spawnMinion(findClosestFreeTile(), 17144);
		    AscensionDungeon.spawnMinion(findClosestFreeTile(), 17144);
		    AscensionDungeon.spawnMinion(findClosestFreeTile(), 17144);
		} else if (ordinal == 4) {
		    if (getCombat().getTarget() != null)
			AscensionDungeon.generateLine(getCombat().getTarget(), false);
		} else if (ordinal == 3) {
		    if (getCombat().getTarget() != null) {
			WorldTile tile = findClosestFreeTile();
			getCombat().getTarget().setNextWorldTile(tile);
			AscensionDungeon.handleLightning(this, tile, 1500, 2300);
		    }
		}
		activated = 1;
	    }
	}
    }


	@Override
    public void handleIngoingHit(Hit hit) {
	if (hit.getLook() == HitLook.MAGIC_DAMAGE || hit.getLook() == HitLook.MELEE_DAMAGE) {
	    hit.setDamage(hit.getDamage()/25);
	}
	int distance = Utils.getDistance(hit.getSource().getX(), hit.getSource().getY(), this.getX(), this.getY());
	double damageReduction = 1.3;
	if (distance >= 3)
	    damageReduction = 1;
	if (distance >= 6)
	    damageReduction = 0.75;
	if (distance >= 8)
	    damageReduction = 0.5;
	hit.setDamage((int) (hit.getDamage() * damageReduction));
	super.handleIngoingHit(hit);
    }

    public String getOwner() {
	return owner;
    }
    
    @Override
  	public double getMagePrayerMultiplier() {
  		return 0.5;
  	}

    public int getAttackStage() {
	return attackStage;
    }

    public void incrementAttackStage() {
	this.attackStage += 1;
    }

    public void setAttackStage(int attackStage) {
	this.attackStage = attackStage;
    }

    public int getHealthStage() {
	return healthStage;
    }

    public int getActivated() {
	return activated;
    }

    public void setActivated(int activated) {
	this.activated = activated;
    }
}
