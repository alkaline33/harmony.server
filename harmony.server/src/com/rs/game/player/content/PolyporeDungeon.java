package com.rs.game.player.content;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
/**
 * 
 * @author Taylor Moon
 */
 
public class PolyporeDungeon {

	public void useObject(WorldObject o, Player player) {//Needs a bit of work
		String name = o.getDefinitions().getFirstOption();
		switch(name) {
		case "Climb up":
		player.climbUpPolyporeVine(o, player.getX(), player.getY(), player.getPlane() + 1);
		break;
		case "Climb down":
		player.climbPolyporeVine(o, player.getX(), player.getY(), player.getPlane() - 1);
		break;
		case "Jump":
			player.jumpGap(o, player.getX(), player.getY() + 5, player.getPlane());
			break;
		}
	}
	
	public static void handleObjects(WorldObject object, Player player) {
		int id = object.getId();
		if (id == 63093) {
			 player.setNextWorldTile(new WorldTile(4620, 5458, 3));
			 player.setForceMultiArea(true);
		if (id == 63094) {
			 player.setNextWorldTile(new WorldTile(3410, 3328, 0));
			 player.setForceMultiArea(false);
			 /**
			  * Climbing UP vines
			  */
			  
		} else if (id == 64360) {
			player.climbPolyporeVine(object, 4629, 5451, 2);
		} else if (id == 64362) {
			 player.climbUpPolyporeVine(object, 4653, 5389, 1);
		} else if (id == 64361) {
			if (object.getX() == 4629 && object.getY() == 5452 && object.getPlane() == 2)
			player.climbUpPolyporeVine(object, 4629, 5453, 3); 
        else if (object.getX() == 4691 && object.getY() == 5468 && object.getPlane() == 2)
       	player.climbUpPolyporeVine(object, 4691, 5470, 3); 
        else if (object.getX() == 4699 && object.getY() == 5459)
       	 player.climbUpPolyporeVine(object, 4697, 5459, 3);
        else if (object.getX() == 4705 && object.getY() == 5461)
       	 player.climbUpPolyporeVine(object, 4705, 5459, 2);
        else if (object.getX() == 4633 & object.getY() == 5409)
       	 player.climbUpPolyporeVine(object, 4631, 5409, 3);
			/** 2nd level*
        else if (object.getX() == 4643 && object.getY() == 5389)//2nd level
       	 player.climbUpPolyporeVine(object, 4641, 5389, 2);
        else if (object.getX() == 4632 && object.getY() == 5442)
       	 player.climbUpPolyporeVine(object, 4632, 5444, 2);
        else if (object.getX() == 4689 && object.getY() == 5480) 
        	player.climbUpPolyporeVine(object, 4689, 5478, 2);
        	 else if (object.getX() == 4718 && object.getY() == 5466) 
            	player.climbUpPolyporeVine(object, 4718, 5466, 1);
        
       	 /**
       	  * shortcuts
       	  */
       	  
		} else if (id == 64294) {
			if (object.getX() == 4659 && object.getY() == 5476 && object.getPlane() == 3) //first gap
				player.jumpGap(object, 4662, 5476, 3);
			 else if (object.getX() == 4684 && object.getY() == 5476 && object.getPlane() == 3)//second gap jumping back
				player.jumpGap(object, 4681, 5476, 3);
			player.setNextFaceWorldTile(new WorldTile(4663, 5476, 3));
		 } else if (id == 64295) {
				if (object.getX() == 4661 && object.getY() == 5476 && object.getPlane() == 3)//first gap jumping back
			  player.jumpGap(object, 4658, 5476, 3);
   	 else if (object.getX() == 4682 && object.getY() == 5476 && object.getPlane() == 3)//second gap
							player.jumpGap(object, 4685, 5476, 3);
				/**
				 * Climbing DOWN vines
				 */
				 
		} else if (id == 64359) {
			if (object.getX() == 4698 && object.getY() == 5459)
				player.climbPolyporeVine(object, 4697, 5458, 2);
			else if (object.getX() == 4632 && object.getY() == 5409)
				player.climbPolyporeVine(object, 4632, 5409, 2);
			else if (object.getX() == 4691 && object.getY() == 5469)
			player.climbPolyporeVine(object, 4691, 5467, 2);
			else if (object.getX() == 4632 && object.getY() == 5443)
				player.climbPolyporeVine(object, 4633, 5440, 1);
			else if (object.getX() == 4642 && object.getY() == 5389)
				player.climbPolyporeVine(object, 4644, 5390, 1);
			else if (object.getX() == 4652 && object.getY() == 5388)
				player.climbPolyporeVine(object, 4653, 5386, 0);
			else if (object.getX() == 4705 && object.getY() == 5460)
				player.climbPolyporeVine(object, 4704, 5461, 1);
			else if (object.getX() == 4689 && object.getY() == 5479)
				player.climbPolyporeVine(object, 4689, 5479, 1);
		   else if (object.getX() == 4718 && object.getY() == 5467) 
			   player.climbPolyporeVine(object, 4718, 5465, 0);
				
			
		} else if (id == 64125) {
			player.pickNeemVine(object);
		}
	}
	
	}
}
