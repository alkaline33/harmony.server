/*@author Dikkekont
 */
package com.rs.utils;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.utils.Utils.EntityDirection;


public class NPCSpawning {
//
	/**
	 * Contains the custom npc spawning
	 */
	
	public static void spawnNPCS() {
		
		
		//New home in Piscatoris
		World.spawnObject(new WorldObject(42378, 10, 1, 2332, 3687, 0), true); //bank
		World.spawnObject(new WorldObject(42378, 10, 1, 2331, 3687, 0), true); //bank
		World.spawnObject(new WorldObject(42378, 10, 1, 2330, 3687, 0), true); //bank
		World.spawnObject(new WorldObject(42378, 10, 1, 2329, 3687, 0), true); //bank
		World.spawnObject(new WorldObject(42378, 10, 1, 2328, 3687, 0), true); //bank
		World.spawnObject(new WorldObject(42378, 10, 1, 2327, 3687, 0), true); //bank
		World.spawnNPC(494, new WorldTile(2332, 3686, 0), 0, false, EntityDirection.NORTH); //banker
		World.spawnNPC(494, new WorldTile(2331, 3686, 0), 0, false, EntityDirection.NORTH); //banker
		World.spawnNPC(494, new WorldTile(2330, 3686, 0), 0, false, EntityDirection.NORTH); //banker
		World.spawnNPC(494, new WorldTile(2329, 3686, 0), 0, false, EntityDirection.NORTH); //banker
		World.spawnNPC(494, new WorldTile(2328, 3686, 0), 0, false, EntityDirection.NORTH); //banker
		World.spawnNPC(494, new WorldTile(2327, 3686, 0), 0, false, EntityDirection.NORTH); //banker
		World.spawnNPC(1419, new WorldTile(2327, 3690, 0), 0, false, EntityDirection.EAST); //GE
		World.spawnObject(new WorldObject(13291, 10, 1, 2333, 3686, 0), true); //magic chest
		World.spawnObject(new WorldObject(2588, 10, 1, 2333, 3687, 0), true); //crystal key chest
		World.spawnObject(new WorldObject(67551, 10, 3, 2333, 3688, 0), true); //rot6 chest
		World.spawnObject(new WorldObject(47150, 10, 1, 2342, 3689, 0), true); //fountain
		World.spawnNPC(13930, new WorldTile(2331, 3693, 0), 0, false, EntityDirection.SOUTH); //donate shop
		World.spawnObject(new WorldObject(17309, 10, 0, 2331, 3685, 0), true); //research table
		World.spawnObject(new WorldObject(409, 10, 0, 2844, 3345, 0), true); //altar
		World.spawnNPC(3705, new WorldTile(2329, 3685, 0), 0, false, EntityDirection.SOUTH); //max
		World.spawnObject(new WorldObject(2563, 10, 0, 2330, 3685, 0), true); //cape stand
		World.spawnNPC(561, new WorldTile(2356, 3691, 0), 0, false, EntityDirection.WEST); //buy anything shop
		World.spawnObject(new WorldObject(4874, 10, 0, 2356, 3690, 0), true); //thieving stall
		World.spawnObject(new WorldObject(4875, 10, 0, 2356, 3689, 0), true); //thieving stall
		World.spawnObject(new WorldObject(4876, 10, 0, 2356, 3688, 0), true); //thieving stall
		World.spawnObject(new WorldObject(4877, 10, 0, 2356, 3687, 0), true); //thieving stall
		World.spawnObject(new WorldObject(4878, 10, 0, 2356, 3686, 0), true); //thieving stall
		World.spawnObject(new WorldObject(2465, 10, 0, 2333, 3692, 0), true); //slayer task portal
		World.spawnNPC(9085, new WorldTile(2333, 3691, 0), 0, false, EntityDirection.EAST); //kuradal
		World.spawnNPC(7891, new WorldTile(2327, 3692, 0), 0, true, EntityDirection.WEST); //max hit dummy
		World.spawnNPC(14386, new WorldTile(2352, 3697, 0), 0, false, EntityDirection.WEST); //death
		World.spawnNPC(8623, new WorldTile(2354, 3695, 0), 0, false, EntityDirection.WEST); //grave
		World.spawnNPC(6524, new WorldTile(2329, 3693, 0), 0, false, EntityDirection.SOUTH); //potion decanter
		World.spawnNPC(30144, new WorldTile(2346, 3679, 0), 0, false, EntityDirection.NORTH); //trivia shop
		World.spawnNPC(30044, new WorldTile(2345, 3679, 0), 0, false, EntityDirection.NORTH); //event shop
		World.spawnNPC(100, new WorldTile(2344, 3679, 0), 0, false, EntityDirection.NORTH); //season event
		World.spawnNPC(528, new WorldTile(2330, 3693, 0), 0, false, EntityDirection.SOUTH); //shop hub
		World.spawnNPC(14706, new WorldTile(2332, 3693, 0), 0, false, EntityDirection.SOUTH); //daily challenge
		World.spawnNPC(2676, new WorldTile(2351, 3683, 0), 0, false, EntityDirection.WEST); //makeover mage
		World.spawnNPC(598, new WorldTile(2351, 3682, 0), 0, false, EntityDirection.WEST); //haircut
		World.spawnNPC(548, new WorldTile(2351, 3681, 0), 0, false, EntityDirection.WEST); //thessalia
		World.spawnNPC(1972, new WorldTile(2343, 3679, 0), 0, false, EntityDirection.NORTH); //cosmetic override
		World.spawnNPC(13727, new WorldTile(2341, 3679, 0), 0, false, EntityDirection.NORTH); //loyalty
		World.spawnNPC(905, new WorldTile(2340, 3679, 0), 0, false, EntityDirection.NORTH); //skill points
		World.spawnNPC(13251, new WorldTile(2339, 3679, 0), 0, false, EntityDirection.NORTH); //prestige master
		World.spawnNPC(4455, new WorldTile(2338, 3679, 0), 0, false, EntityDirection.NORTH); //repair
		World.spawnNPC(2824, new WorldTile(2337, 3678, 0), 0, false, EntityDirection.WEST); //tanner
		World.spawnNPC(885, new WorldTile(2337, 3677, 0), 0, false, EntityDirection.WEST); //pet tamer
		World.spawnNPC(3709, new WorldTile(2354, 3694, 0), 0, false, EntityDirection.WEST); //wilderness task
		World.spawnNPC(11508, new WorldTile(2343, 3697, 0), 0, false, EntityDirection.SOUTH); //commander korasi
		World.spawnObject(new WorldObject(22728, 10, 1, 2353, 3682, 0), true); //recycling machine
		World.spawnNPC(4287, new WorldTile(2355, 3680, 0), 0, false, EntityDirection.WEST); //recycle manager
		World.spawnObject(new WorldObject(11923, 10, 3, 2325, 3678, 0), true); //evil tree
		World.spawnNPC(4247, new WorldTile(2332, 3701, 0), 0, false, EntityDirection.EAST); //estate agent
		World.spawnObject(new WorldObject(15482, 10, 1, 2332, 3695, 0), true); //poh portal
		
		//Entrana home remove objects
		World.deleteObject(new WorldObject(1088, 10, 3, 2849, 3354, 0));
		World.deleteObject(new WorldObject(884, 10, 2, 2811, 3347, 0));
		World.deleteObject(new WorldObject(1102, 10, 1, 2852, 3345, 0));
		World.deleteObject(new WorldObject(602, 10, 1, 2852, 3344, 0));
		World.deleteObject(new WorldObject(1102, 10, 1, 2852, 3342, 0));
		World.deleteObject(new WorldObject(608, 10, 1, 2850, 3342, 0));
		World.deleteObject(new WorldObject(1096, 11, 2, 2857, 3338, 0));
		World.deleteObject(new WorldObject(1088, 10, 3, 2849, 3342, 0));
		//World.deleteObject(new WorldObject(1088, 10, 1, 2849, 3343, 0));
		World.deleteObject(new WorldObject(1112, 10, 3, 2848, 3346, 0));
		World.deleteObject(new WorldObject(1112, 10, 3, 2846, 3346, 0));
		World.deleteObject(new WorldObject(1112, 10, 3, 2844, 3346, 0));
		World.deleteObject(new WorldObject(1112, 10, 3, 2842, 3346, 0));
		World.deleteObject(new WorldObject(1112, 10, 3, 2842, 3350, 0));
		World.deleteObject(new WorldObject(1112, 10, 3, 2844, 3350, 0));
		World.deleteObject(new WorldObject(1112, 10, 3, 2846, 3350, 0));
		World.deleteObject(new WorldObject(1112, 10, 3, 2848, 3350, 0));
		World.deleteObject(new WorldObject(10638, 10, 1, 2853, 3348, 0));
		World.deleteObject(new WorldObject(203, 10, 3, 2857, 3347, 0));
		World.deleteObject(new WorldObject(203, 10, 3, 2857, 3350, 0));
		World.deleteObject(new WorldObject(203, 10, 2, 2847, 3352, 0));
		World.deleteObject(new WorldObject(203, 10, 2, 2845, 3352, 0));
		World.deleteObject(new WorldObject(203, 10, 2, 2843, 3352, 0));
		//World.deleteObject(new WorldObject(1158, 10, 2, 2841, 3352, 0));
		World.deleteObject(new WorldObject(1747, 10, 1, 2857, 3346, 0));
		World.deleteObject(new WorldObject(203, 10, 0, 2847, 3345, 0));
		World.removeObject(new WorldObject(203, 10, 0, 2845, 3345, 0), true);
		//World.deleteObject(new WorldObject(203, 10, 0, 2845, 3345, 0));
		//World.deleteObject(new WorldObject(203, 10, 0, 2843, 3345, 0));
		//World.deleteObject(new WorldObject(1158, 10, 0, 2841, 3345, 0));
		World.deleteObject(new WorldObject(918, 22, 1, 2849, 3346, 0));
		World.deleteObject(new WorldObject(918, 22, 1, 2849, 3347, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2848, 3347, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2848, 3347, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2848, 3346, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2847, 3346, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2847, 3347, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2846, 3346, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2846, 3347, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2845, 3346, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2845, 3347, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2844, 3346, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2844, 3347, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2843, 3346, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2843, 3347, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2842, 3346, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2842, 3347, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2842, 3350, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2842, 3351, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2843, 3350, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2843, 3351, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2844, 3350, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2844, 3351, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2845, 3350, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2845, 3351, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2846, 3350, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2846, 3351, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2847, 3350, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2847, 3351, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2848, 3350, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2848, 3351, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2849, 3350, 0));
		World.deleteObject(new WorldObject(918, 22, 3, 2849, 3351, 0));
		World.deleteObject(new WorldObject(930, 22, 0, 2856, 3350, 0));
		World.deleteObject(new WorldObject(930, 22, 0, 2856, 3349, 0));
		World.deleteObject(new WorldObject(930, 22, 0, 2856, 3348, 0));
		World.deleteObject(new WorldObject(930, 22, 0, 2856, 3357, 0));
		World.deleteObject(new WorldObject(930, 22, 0, 2855, 3350, 0));
		World.deleteObject(new WorldObject(930, 22, 0, 2855, 3349, 0));
		World.deleteObject(new WorldObject(930, 22, 0, 2855, 3348, 0));
		World.deleteObject(new WorldObject(930, 22, 0, 2855, 3347, 0));
		World.deleteObject(new WorldObject(930, 22, 0, 2854, 3350, 0));
		World.deleteObject(new WorldObject(930, 22, 0, 2854, 3349, 0));
		World.deleteObject(new WorldObject(930, 22, 0, 2854, 3348, 0));
		World.deleteObject(new WorldObject(930, 22, 0, 2854, 3347, 0));
		World.deleteObject(new WorldObject(930, 22, 0, 2856, 3347, 0));
		World.deleteObject(new WorldObject(37047, 10, 2, 2853, 3342, 0));
		World.deleteObject(new WorldObject(37047, 10, 2, 2853, 3344, 0));
		//World.deleteObject(new WorldObject(1533, 0, 1, 2851, 3341, 0));
		//World.deleteObject(new WorldObject(1533, 0, 1, 2851, 3341, 0));
		//World.deleteObject(new WorldObject(1846, 5, 1, 2852, 3341, 0));
		//World.deleteObject(new WorldObject(1902, 0, 1, 2852, 3341, 0));
		//World.deleteObject(new WorldObject(41202, 22, 1, 2852, 3341, 0));
		//World.deleteObject(new WorldObject(1846, 4, 3, 2852, 3342, 0));
		//World.deleteObject(new WorldObject(1846, 4, 3, 2850, 3342, 0));
		//World.deleteObject(new WorldObject(1846, 5, 1, 2850, 3341, 0));
		//World.deleteObject(new WorldObject(1902, 0, 1, 2850, 3341, 0));
		World.deleteObject(new WorldObject(357, 10, 1, 2829, 3352, 0));
		World.deleteObject(new WorldObject(356, 10, 3, 2830, 3353, 0));
		World.deleteObject(new WorldObject(358, 10, 1, 2829, 3353, 0));
		World.deleteObject(new WorldObject(1102, 10, 0, 2830, 3350, 0));
		World.deleteObject(new WorldObject(1102, 10, 2, 2831, 3348, 0));
		World.deleteObject(new WorldObject(595, 10, 0, 2830, 3349, 0));
		World.deleteObject(new WorldObject(356, 10, 2, 2829, 3346, 0));
		World.deleteObject(new WorldObject(362, 10, 0, 2833, 3346, 0));
		//World.deleteObject(new WorldObject(362, 10, 3, 2833, 3347, 0));
		//World.deleteObject(new WorldObject(38147, 11, 1, 2852, 3339, 0));
		World.deleteObject(new WorldObject(357, 10, 1, 2845, 3337, 0));
		World.deleteObject(new WorldObject(357, 10, 1, 2844, 3337, 0));
		World.deleteObject(new WorldObject(357, 10, 3, 2844, 3338, 0));
		World.deleteObject(new WorldObject(608, 10, 1, 2817, 3353, 0));
		World.deleteObject(new WorldObject(608, 10, 1, 2818, 3355, 0));
		World.deleteObject(new WorldObject(1088, 10, 0, 2818, 3356, 0));
		World.deleteObject(new WorldObject(598, 10, 1, 2817, 3355, 0));
		World.deleteObject(new WorldObject(1088, 10, 3, 2816, 3354, 0));
		World.deleteObject(new WorldObject(1747, 10, 3, 2816, 3352, 0));
		World.deleteObject(new WorldObject(33500, 10, 2, 2822, 3351, 0));
		World.deleteObject(new WorldObject(944, 22, 0, 2821, 3353, 0));
		World.deleteObject(new WorldObject(944, 22, 0, 2821, 3352, 0));
		World.deleteObject(new WorldObject(944, 22, 0, 2820, 3353, 0));
		World.deleteObject(new WorldObject(944, 22, 2, 2820, 3352, 0));
		World.deleteObject(new WorldObject(944, 22, 0, 2819, 3353, 0));
		World.deleteObject(new WorldObject(944, 22, 0, 2819, 3352, 0));
		World.deleteObject(new WorldObject(944, 22, 0, 2818, 3353, 0));
		World.deleteObject(new WorldObject(944, 22, 0, 2818, 3352, 0));
		World.deleteObject(new WorldObject(33931, 10, 2, 2822, 3355, 0));
		World.deleteObject(new WorldObject(611, 10, 1, 2821, 3357, 0));
		World.deleteObject(new WorldObject(1102, 10, 2, 2857, 3335, 0));
		World.deleteObject(new WorldObject(595, 10, 2, 2859, 3335, 0));
		World.deleteObject(new WorldObject(38147, 10, 1, 2846, 3340, 0));
		World.deleteObject(new WorldObject(5615, 10, 2, 2844, 3333, 0));
		//entrana home spawns
		World.spawnNPC(494, new WorldTile(2849, 3354, 0), 0, false, EntityDirection.SOUTH); //banker
		World.spawnNPC(494, new WorldTile(2850, 3354, 0), 0, false, EntityDirection.SOUTH); //banker
		World.spawnNPC(494, new WorldTile(2851, 3354, 0), 0, false, EntityDirection.SOUTH); //banker
		World.spawnNPC(494, new WorldTile(2852, 3354, 0), 0, false, EntityDirection.SOUTH); //banker
		World.spawnNPC(494, new WorldTile(2853, 3354, 0), 0, false, EntityDirection.SOUTH); //banker
		World.spawnNPC(1419, new WorldTile(2848, 3352, 0), 0, false, EntityDirection.SOUTH); //GE
		World.spawnObject(new WorldObject(13291, 10, 0, 2854, 3351, 0), true); //magic chest
		World.spawnObject(new WorldObject(2588, 10, 2, 2855, 3351, 0), true); //crystal key chest
		World.spawnObject(new WorldObject(67551, 10, 0, 2856, 3351, 0), true); //rot6 chest
		World.spawnObject(new WorldObject(47150, 10, 1, 2844, 3340, 0), true); //fountain
		World.spawnNPC(13930, new WorldTile(2846, 3352, 0), 0, false, EntityDirection.SOUTH); //donate shop
		World.spawnObject(new WorldObject(17309, 10, 3, 2841, 3350, 0), true); //research table
		World.spawnObject(new WorldObject(2012, 10, 3, 2849, 3353, 0), true);//Bank booth
		World.spawnObject(new WorldObject(2019, 10, 3, 2850, 3353, 0), true);//Bank booth
		World.spawnObject(new WorldObject(2019, 10, 3, 2851, 3353, 0), true);//Bank booth
		World.spawnObject(new WorldObject(2019, 10, 3, 2852, 3353, 0), true);//Bank booth
		World.spawnObject(new WorldObject(2015, 10, 3, 2853, 3353, 0), true);//Bank booth
		World.spawnNPC(561, new WorldTile(2857, 3338, 0), 0, false, EntityDirection.SOUTH); //buy anything shop
		World.spawnObject(new WorldObject(4874, 10, 0, 2858, 3338, 0), true); //thieving stall
		World.spawnObject(new WorldObject(4875, 10, 0, 2859, 3338, 0), true); //thieving stall
		World.spawnObject(new WorldObject(4876, 10, 0, 2860, 3338, 0), true); //thieving stall
		World.spawnObject(new WorldObject(4877, 10, 0, 2861, 3338, 0), true); //thieving stall
		World.spawnObject(new WorldObject(4878, 10, 0, 2862, 3338, 0), true); //thieving stall
		World.spawnObject(new WorldObject(2465, 10, 0, 2841, 3346, 0), true); //slayer task portal
		World.spawnNPC(9085, new WorldTile(2841, 3347, 0), 0, false, EntityDirection.EAST); //kuradal
		World.spawnNPC(9711, new WorldTile(2844, 3352, 0), 0, false, EntityDirection.SOUTH); //rewards trader
		World.spawnObject(new WorldObject(36325, 10, -1, 2850, 3355, 0), true);//barrel
		World.spawnObject(new WorldObject(36326, 10, -1, 2852, 3355, 0), true);//barrel
		World.spawnObject(new WorldObject(4046, 10, 1, 2831, 3353, 0), true);//Anvil
		World.spawnObject(new WorldObject(36970, 10, 2, 2818, 3351, 0), true);//Spinningwheel
		World.spawnObject(new WorldObject(43788, 10, 0, 2849, 3355, 0), true);//Safe
		World.spawnObject(new WorldObject(33795, 4, 0, 2849, 3354, 0), true);//Lantern
		World.spawnObject(new WorldObject(33795, 4, 2, 2853, 3354, 0), true);//Lantern
		World.spawnObject(new WorldObject(33795, 4, 1, 2851, 3355, 0), true);//Lantern
		World.spawnObject(new WorldObject(229241, 10, 1, 2849, 3343, 0), true);//Rejuvenation fountain
		World.spawnObject(new WorldObject(358, 10, 1, 2853, 3355, 0), true);//Boxes
		World.spawnObject(new WorldObject(42192, 10, 2, 2860, 3334, 0), true);//Bank
		World.spawnObject(new WorldObject(9398, 10, -1, 2874, 3341, 0), true);//Deposit
		World.spawnObject(new WorldObject(9398, 10, -1, 2874, 3332, 0), true);//Deposit
		World.spawnObject(new WorldObject(15482, 10, 0, 2808, 3347, 0), true);//House portal
		World.spawnObject(new WorldObject(563, 10, 4, 2853, 3341, 0), true);//Statue king
		World.spawnObject(new WorldObject(15611, 10, 4, 2849, 3341, 0), true);//Statue queen
		World.spawnObject(new WorldObject(9250, 10, 0, 2827, 3344, 0), true);//Saradomin statue
		//World.spawnObject(new WorldObject(26192, 10, 0, 2849, 3342, 0), true);//Fireplace
		World.spawnObject(new WorldObject(2563, 10, 1, 2853, 3343, 0), true);//Comp cape
		World.spawnObject(new WorldObject(203, 10, 0, 2846, 3345, 0), true);//candle
		//World.spawnNPC(3705, new WorldTile(2853, 3342, 0), 0, false, EntityDirection.WEST); //max
		World.spawnNPC(7891, new WorldTile(2857, 3351, 0), 0, true, EntityDirection.NORTH); //max hit dummy
		World.spawnObject(new WorldObject(1814, 4, 2, 2855, 3338, 0), true);//wildylever
		World.spawnNPC(14386, new WorldTile(2848, 3334, 0), 0, false, EntityDirection.NORTH); //death
		World.spawnNPC(528, new WorldTile(2847, 3352, 0), 0, false, EntityDirection.SOUTH); //shop hub
		World.spawnNPC(2676, new WorldTile(2857, 3346, 0), 0, false, EntityDirection.NORTH); //makeover mage
		World.spawnNPC(598, new WorldTile(2856, 3346, 0), 0, false, EntityDirection.NORTH); //haircut
		World.spawnNPC(548, new WorldTile(2855, 3346, 0), 0, false, EntityDirection.NORTH); //thessalia
		//World.spawnNPC(1972, new WorldTile(2854, 3346, 0), 0, false, EntityDirection.NORTH); //cosmetic override
		World.spawnNPC(6524, new WorldTile(2845, 3352, 0), 0, false, EntityDirection.SOUTH); //potion decanter
		World.spawnNPC(3709, new WorldTile(2842, 3345, 0), 0, false, EntityDirection.NORTH); //wilderness task
		World.spawnNPC(14870, new WorldTile(2848, 3345, 0), 0, false, EntityDirection.NORTH); //skill points
		World.spawnNPC(13727, new WorldTile(2847, 3345, 0), 0, false, EntityDirection.NORTH); //loyalty
		World.spawnObject(new WorldObject(22728, 10, 0, 2833, 3347, 0), true); //recycling machine
		World.spawnNPC(4287, new WorldTile(2833, 3346, 0), 0, false, EntityDirection.WEST); //recycle manager
		World.spawnObject(new WorldObject(4483, 10, 3, 2829, 3350, 0), true); //bank chest
		//World.spawnObject(new WorldObject(11923, 10, 3, 2851, 3332, 0), true); //evil tree
		World.spawnNPC(8623, new WorldTile(2853, 3344, 0), 0, false, EntityDirection.WEST); //prestige master
		World.spawnNPC(14706, new WorldTile(2853, 3345, 0), 0, false, EntityDirection.WEST); //daily challenge
		World.spawnNPC(30144, new WorldTile(2854, 3346, 0), 0, false, EntityDirection.NORTH); //trivia shop
		World.spawnNPC(4455, new WorldTile(2844, 3334, 0), 0, false, EntityDirection.EAST); //repair
		World.spawnNPC(885, new WorldTile(2844, 3333, 0), 0, false, EntityDirection.EAST); //pet tamer
		World.spawnNPC(11508, new WorldTile(2847, 3333, 0), 0, false, EntityDirection.NORTH); //commander korasi
		
		//World.spawnNPC(30044, new WorldTile(2345, 3679, 0), 0, false, EntityDirection.NORTH); //event shop
		//World.spawnNPC(100, new WorldTile(2344, 3679, 0), 0, false, EntityDirection.NORTH); //season event
		World.spawnNPC(2824, new WorldTile(2829, 3348, 0), 0, false, EntityDirection.EAST); //tanner
		World.spawnNPC(4247, new WorldTile(2807, 3348, 0), 0, false, EntityDirection.NORTH); //estate agent
		World.spawnNPC(327, new WorldTile(2879, 3339, 0), 0, false, EntityDirection.NORTH); //fishing spot - shrimp
		World.spawnNPC(329, new WorldTile(2879, 3338, 0), 0, false, EntityDirection.NORTH); //fishing spot - trout
		World.spawnNPC(312, new WorldTile(2879, 3335, 0), 0, false, EntityDirection.NORTH); //fishing spot - lobster
		World.spawnNPC(323, new WorldTile(2879, 3334, 0), 0, false, EntityDirection.NORTH); //fishing spot - monkfish
		World.spawnNPC(313, new WorldTile(2876, 3331, 0), 0, false, EntityDirection.NORTH); //fishing spot - shark

	//shop in  || player.getX() == 2327 && player.getY() == 3670
		World.spawnObject(new WorldObject(-1, 10, -1, 1914, 4639, 0), true);// -1
		
		World.spawnNPC(14237, new WorldTile(2402, 2843, 0), 0, false, EntityDirection.SOUTH);
		World.spawnNPC(14241, new WorldTile(2403, 2843, 0), 0, false, EntityDirection.SOUTH);

		//agility shop npc
		World.spawnNPC(162, new WorldTile(2471, 3439, 0), 0, false, EntityDirection.NORTH); //gnome trainer	
		World.spawnNPC(607, new WorldTile(2554, 3562, 0), 0, false, EntityDirection.NORTH); //gunnjorn
		
		
		//new dz1
		//deletes
		World.deleteObject(new WorldObject(10799, 10, 0, 3370, 9621, 0));
		World.deleteObject(new WorldObject(10801, 10, 2, 3381, 9623, 0));
		World.deleteObject(new WorldObject(10801, 10, 3, 3381, 9627, 0));
		World.deleteObject(new WorldObject(10801, 10, 0, 3378, 9625, 0));
		World.deleteObject(new WorldObject(10801, 10, 1, 3379, 9621, 0));
		World.deleteObject(new WorldObject(10801, 10, 0, 3376, 9624, 0));
		World.deleteObject(new WorldObject(10801, 10, 1, 3374, 9621, 0));
		World.deleteObject(new WorldObject(10800, 10, 0, 3355, 9621, 0));
		World.deleteObject(new WorldObject(10802, 10, 0, 3352, 9622, 0));
		World.deleteObject(new WorldObject(10802, 10, 1, 3350, 9621, 0));
		World.deleteObject(new WorldObject(10802, 10, 3, 3347, 9620, 0));
		World.deleteObject(new WorldObject(10802, 10, 1, 3345, 9623, 0));
		World.deleteObject(new WorldObject(10802, 10, 2, 3345, 9628, 0));
		World.deleteObject(new WorldObject(10802, 10, 3, 3347, 9628, 0));
		World.deleteObject(new WorldObject(10799, 10, 2, 3346, 9651, 0));
		World.deleteObject(new WorldObject(10799, 10, 3, 3348, 9652, 0));
		World.deleteObject(new WorldObject(10799, 10, 3, 3345, 9654, 0));
		World.deleteObject(new WorldObject(10799, 10, 0, 3348, 9655, 0));
		World.deleteObject(new WorldObject(10799, 10, 2, 3345, 9657, 0));
		World.deleteObject(new WorldObject(10799, 10, 1, 3350, 9657, 0));
		World.deleteObject(new WorldObject(10799, 10, 0, 3352, 9654, 0));
		World.deleteObject(new WorldObject(10799, 10, 1, 3354, 9658, 0));
		World.deleteObject(new WorldObject(10801, 10, 0, 3356, 9657, 0));
		World.deleteObject(new WorldObject(10802, 10, 0, 3371, 9658, 0));
		World.deleteObject(new WorldObject(10800, 10, 2, 3375, 9657, 0));
		World.deleteObject(new WorldObject(10802, 10, 3, 3379, 9655, 0));
		World.deleteObject(new WorldObject(10802, 10, 3, 3377, 9653, 0));
		World.deleteObject(new WorldObject(10802, 10, 0, 3381, 9653, 0));
		World.deleteObject(new WorldObject(10802, 10, 2, 3381, 9650, 0));
		World.deleteObject(new WorldObject(10800, 10, 1, 3381, 9657, 0));
		
		//spawns
		World.spawnObject(new WorldObject(6189, 10, 0, 3374, 9624, 0), true); //furnace
		World.spawnObject(new WorldObject(24534, 10, 0, 3374, 9626, 0), true); //coal box
		World.spawnObject(new WorldObject(67968, 10, 0, 3376, 9620, 0), true); //robust glass
		World.spawnObject(new WorldObject(11936, 10, 2, 3384, 9627, 0), true); //copper ore
		World.spawnObject(new WorldObject(11933, 10, 2, 3384, 9626, 0), true); //tin ore
		World.spawnObject(new WorldObject(2092, 10, 2, 3384, 9625, 0), true); //iron ore
		World.spawnObject(new WorldObject(2096, 10, 2, 3384, 9624, 0), true); //coal ore
		World.spawnObject(new WorldObject(37312, 10, 0, 3383, 9628, 0), true); //gold ore
		World.spawnObject(new WorldObject(37304, 10, 2, 3383, 9629, 0), true); //silver ore
		World.spawnObject(new WorldObject(2102, 10, 2, 3384, 9623, 0), true); //mithril ore
		World.spawnObject(new WorldObject(2104, 10, 2, 3384, 9622, 0), true); //adamant ore
		World.spawnObject(new WorldObject(33079, 10, 2, 3384, 9621, 0), true); //rune ore
		World.spawnObject(new WorldObject(33221, 10, 2, 3383, 9620, 0), true); //ancient ore
		World.spawnObject(new WorldObject(11364, 10, 2, 3382, 9619, 0), true); //gem rock
		World.spawnObject(new WorldObject(5999, 10, 1, 3381, 9631, 0), true); //coal mineral deposit
		World.spawnObject(new WorldObject(45076, 10, 0, 3371, 9621, 0), true); //gold mineral deposit
		World.spawnObject(new WorldObject(4483, 10, 0, 3378, 9629, 0), true); //bank chest
		World.spawnObject(new WorldObject(4483, 10, 0, 3379, 9629, 0), true); //bank chest
		World.spawnObject(new WorldObject(4483, 10, 3, 3365, 9640, 0), true); //bank chest
		World.spawnObject(new WorldObject(4483, 10, 0, 3363, 9638, 0), true); //bank chest
		World.spawnObject(new WorldObject(4483, 10, 1, 3361, 9640, 0), true); //bank chest
		World.spawnObject(new WorldObject(4483, 10, 2, 3363, 9642, 0), true); //bank chest
		World.spawnObject(new WorldObject(4046, 10, 1, 3379, 9625, 0), true); //anvil
		World.spawnObject(new WorldObject(2330, 10, 0, 3377, 9619, 0), true); //sandstone
		World.spawnObject(new WorldObject(47150, 10, 0, 3372, 9638, 0), true); //fountain
		World.spawnObject(new WorldObject(2465, 10, 0, 3375, 9636, 0), true); //slayer portal
		World.spawnNPC(9085, new WorldTile(3375, 9635, 0), 0, false, EntityDirection.WEST); //kuradal
		World.spawnNPC(813, new WorldTile(3365, 9642, 0), 0, false, EntityDirection.NORTH); //status upgrade
		World.spawnNPC(14386, new WorldTile(3375, 9633, 0), 0, false, EntityDirection.WEST); //reaper
		World.spawnObject(new WorldObject(27254, 10, 1, 3375, 9631, 0), true); //boss portal
		World.spawnNPC(14706, new WorldTile(3365, 9641, 0), 0, false, EntityDirection.EAST); //daily task
		World.spawnObject(new WorldObject(28734, 10, 1, 3361, 9638, 0), true); //obelisk
		World.spawnObject(new WorldObject(37823, 10, 0, 3345, 9631, 0), true); //magic tree
		World.spawnObject(new WorldObject(37823, 10, 1, 3354, 9621, 0), true); //magic tree
		World.spawnObject(new WorldObject(1309, 10, 1, 3343, 9626, 0), true); //yew tree
		World.spawnObject(new WorldObject(1309, 10, 1, 3349, 9619, 0), true); //yew tree
		World.spawnObject(new WorldObject(9036, 10, 1, 3344, 9629, 0), true); //teak tree
		World.spawnObject(new WorldObject(9036, 10, 1, 3343, 9625, 0), true); //teak tree
		World.spawnObject(new WorldObject(9036, 10, 1, 3352, 9620, 0), true); //teak tree
		World.spawnObject(new WorldObject(9036, 10, 1, 3348, 9619, 0), true); //teak tree
		//World.spawnObject(new WorldObject(11923, 10, 3, 3344, 9620, 0), true); //afk tree
		World.spawnObject(new WorldObject(11923, 10, 2, 3356, 9626, 0), true); //afk tree
		World.spawnObject(new WorldObject(9034, 10, 2, 3344, 9620, 0), true); //mahogany tree
		World.spawnObject(new WorldObject(70770, 10, 3, 3348, 9625, 0), true); //bonfire
		World.spawnObject(new WorldObject(4483, 10, 1, 3352, 9625, 0), true); //bank chest
		World.spawnObject(new WorldObject(4483, 10, 1, 3352, 9624, 0), true); //bank chest
		World.spawnObject(new WorldObject(4483, 10, 0, 3348, 9629, 0), true); //bank chest
		World.spawnObject(new WorldObject(4483, 10, 0, 3349, 9629, 0), true); //bank chest
		World.spawnObject(new WorldObject(4483, 10, 3, 3343, 9653, 0), true); //bank chest
		World.spawnObject(new WorldObject(4483, 10, 3, 3343, 9656, 0), true); //bank chest
		World.spawnObject(new WorldObject(2728, 10, 0, 3343, 9654, 0), true); //cooking range
		World.spawnObject(new WorldObject(4483, 10, 4, 3348, 9660, 0), true); //bank chest
		World.spawnObject(new WorldObject(4483, 10, 4, 3351, 9660, 0), true); //bank chest
		World.spawnObject(new WorldObject(2728, 10, 1, 3349, 9660, 0), true); //cooking range
		World.spawnNPC(556, new WorldTile(3364, 9642, 0), 0, false, EntityDirection.NORTH); //donator shop
		World.spawnNPC(327, new WorldTile(3347, 9648, 0), 0, false, EntityDirection.WEST); //shrimp spot
		World.spawnNPC(329, new WorldTile(3344, 9647, 0), 0, false, EntityDirection.WEST); //trout & salmon
		World.spawnNPC(312, new WorldTile(3344, 9649, 0), 0, false, EntityDirection.WEST); //lobster, tuna & swordfish
		World.spawnNPC(323, new WorldTile(3355, 9659, 0), 0, false, EntityDirection.WEST); //monkfish
		World.spawnNPC(313, new WorldTile(3355, 9656, 0), 0, false, EntityDirection.WEST); //shark
		World.spawnNPC(8841, new WorldTile(3344, 9659, 0), 0, false, EntityDirection.WEST); //cavefish
		World.spawnNPC(8842, new WorldTile(3345, 9659, 0), 0, false, EntityDirection.WEST); //rocktail
		World.spawnObject(new WorldObject(27253, 10, 2, 3345, 9646, 0), true); //fish tank
		World.spawnObject(new WorldObject(28340, 10, 3, 3357, 9657, 0), true); //fish tank
		World.spawnObject(new WorldObject(208138, 10, 0, 3371, 9657, 0), true); //herb patch
		World.spawnObject(new WorldObject(208395, 10, 0, 3380, 9656, 0), true); //tree patch
		World.spawnObject(new WorldObject(208050, 10, 0, 3381, 9648, 0), true); //fruit tree patch
		World.spawnObject(new WorldObject(4483, 10, 3, 3374, 9654, 0), true); //bank chest
		World.spawnObject(new WorldObject(4483, 10, 3, 3374, 9655, 0), true); //bank chest
		World.spawnNPC(4250, new WorldTile(3378, 9650, 0), 0, false, EntityDirection.NORTH); //sawmill
		World.spawnObject(new WorldObject(9036, 10, 1, 3384, 9651, 0), true); //teak tree
		World.spawnObject(new WorldObject(9036, 10, 1, 3384, 9653, 0), true); //teak tree
		World.spawnObject(new WorldObject(9036, 10, 1, 3384, 9655, 0), true); //teak tree
		World.spawnObject(new WorldObject(9036, 10, 1, 3375, 9660, 0), true); //teak tree
		World.spawnObject(new WorldObject(9036, 10, 1, 3377, 9660, 0), true); //teak tree
		World.spawnObject(new WorldObject(9036, 10, 1, 3379, 9660, 0), true); //teak tree
		World.spawnObject(new WorldObject(7837, 10, 2, 3379, 9650, 0), true); //compost bin
		World.spawnObject(new WorldObject(22728, 10, 3, 3372, 9626, 0), true); //recycling machine
		World.spawnNPC(4287, new WorldTile(3371, 9626, 0), 0, false, EntityDirection.NORTH); //recycling manager
		World.spawnNPC(2234, new WorldTile(3378, 9654, 0), 0, false, EntityDirection.SOUTH); //master farmer
		World.spawnNPC(1419, new WorldTile(3364, 9638, 0), 0, false, EntityDirection.SOUTH); //shop hub
		World.spawnNPC(528, new WorldTile(3365, 9638, 0), 0, false, EntityDirection.SOUTH); //member token store
		World.spawnNPC(13930, new WorldTile(3365, 9639, 0), 0, false, EntityDirection.EAST); //ge
		World.spawnObject(new WorldObject(409, 10, 0, 3368, 9626, 0), true); // normal altar
		World.spawnObject(new WorldObject(4483, 10, 2, 3367, 9626, 0), true); //bank chest
		World.spawnNPC(3021, new WorldTile(2674, 3377, 0), 0, false, EntityDirection.NORTH); //leprechaun
		
		
		//leprechaun spawns
		World.spawnNPC(3021, new WorldTile(2674, 3377, 0), 0, false, EntityDirection.NORTH); //leprechaun
		World.spawnNPC(3021, new WorldTile(3599, 3522, 0), 0, false, EntityDirection.NORTH); //leprechaun
		World.spawnNPC(3021, new WorldTile(2477, 3450, 0), 0, false, EntityDirection.NORTH); //leprechaun

		/**
		 * Anti safespots
		 */
		
		World.spawnObject(new WorldObject(-1, 10, 2, 3556, 9759, 0), true);
		World.spawnObject(new WorldObject(-1, 10, 2, 3555, 9759, 0), true);
		World.spawnObject(new WorldObject(-1, 10, 2, 3554, 9759, 0), true);
		World.spawnObject(new WorldObject(-1, 10, 2, 3553, 9759, 0), true);
		World.spawnObject(new WorldObject(-1, 10, 2, 3552, 9759, 0), true);
		World.spawnObject(new WorldObject(-1, 10, 2, 3551, 9759, 0), true);
		World.spawnObject(new WorldObject(-1, 10, 2, 3550, 9759, 0), true);
		World.spawnObject(new WorldObject(-1, 10, 2, 3549, 9759, 0), true);
		World.spawnObject(new WorldObject(-1, 10, 2, 3548, 9759, 0), true);
		World.spawnObject(new WorldObject(-1, 10, 2, 2848, 3342, 0), true);//home wall
		World.spawnObject(new WorldObject(-1, 10, 2, 2847, 3344, 0), true);//home wall
		
		/**
		 * Commanditos clanzone
		 */
		/*
		 * Mining area
		 */
		World.spawnObject(new WorldObject(10946, 10, 2, 2527, 3886, 0), true); //sandstone
		World.spawnObject(new WorldObject(10946, 10, 2, 2528, 3885, 0), true); //sandstone
		World.spawnObject(new WorldObject(10946, 10, 2, 2529, 3885, 0), true); //sandstone
		World.spawnObject(new WorldObject(10946, 10, 2, 2530, 3886, 0), true); //sandstone
		World.spawnObject(new WorldObject(10946, 10, 2, 2527, 3887, 0), true); //sandstone
		World.spawnObject(new WorldObject(10946, 10, 2, 2528, 3887, 0), true); //sandstone
		
		World.spawnObject(new WorldObject(2092, 10, -1, 2522, 3893, 0), true);// iron
		World.spawnObject(new WorldObject(2092, 10, -1, 2522, 3895, 0), true);// iron
		World.spawnObject(new WorldObject(2092, 10, -1, 2523, 3896, 0), true);// iron
		World.spawnObject(new WorldObject(2092, 10, -1, 2523, 3895, 0), true);// iron
		
		World.spawnObject(new WorldObject(11933, 10, -1, 2528, 3897, 0), true);// tin
		World.spawnObject(new WorldObject(11936, 10, -1, 2529, 3896, 0), true);// copper
		World.spawnObject(new WorldObject(11936, 10, -1, 2534, 3888, 0), true);// copper
		World.spawnObject(new WorldObject(11936, 10, -1, 2533, 3888, 0), true);// copper
		World.spawnObject(new WorldObject(11933, 10, -1, 2533, 3889, 0), true);// tin
		World.spawnObject(new WorldObject(11933, 10, -1, 2533, 3890, 0), true);// tin
		
		World.spawnObject(new WorldObject(2102, 10, -1, 2530, 3894, 0), true);// mithril
		World.spawnObject(new WorldObject(2102, 10, -1, 2531, 3893, 0), true);// mithril
		World.spawnObject(new WorldObject(2102, 10, -1, 2531, 3892, 0), true);// mithril
		World.spawnObject(new WorldObject(2102, 10, -1, 2531, 3891, 0), true);// mithril
		
		World.spawnObject(new WorldObject(2104, 10, -1, 2526, 3888, 0), true); // adamant ore
		World.spawnObject(new WorldObject(2104, 10, -1, 2525, 3889, 0), true); // adamant ore
		World.spawnObject(new WorldObject(2104, 10, -1, 2524, 3890, 0), true); // adamant ore
		World.spawnObject(new WorldObject(2104, 10, -1, 2524, 3891, 0), true); // adamant ore
		
		World.spawnObject(new WorldObject(33079, 10, -1, 2527, 3892, 0), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 2528, 3894, 0), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 2524, 3892, 0), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 2523, 3892, 0), true);// Runite ore
		
		World.spawnObject(new WorldObject(12809, 10, 3, 2548, 3897, 0), true); // furnace
		
		World.spawnObject(new WorldObject(2045, 10, -1, 2535, 3891, 0), true);// deposit box
		
		/*
		 * Woodcutting area
		 */
		World.spawnObject(new WorldObject(2045, 10, -1, 2567, 3856, 0), true);// deposit box
		World.spawnObject(new WorldObject(2045, 10, -1, 2560, 3877, 0), true);// deposit box
		
		World.spawnObject(new WorldObject(-1, 10, -1, 2576, 3880, 0), true);//default box
		World.spawnObject(new WorldObject(-1, 10, -1, 2576, 3879, 0), true);//default box
		
		World.spawnObject(new WorldObject(1281, 10, -1, 2559, 3860, 0), true);// oak
		World.spawnObject(new WorldObject(1281, 10, -1, 2561, 3857, 0), true);// oak
		World.spawnObject(new WorldObject(1281, 10, -1, 2561, 3865, 0), true);// oak
		World.spawnObject(new WorldObject(1281, 10, -1, 2562, 3869, 0), true);// oak
		World.spawnObject(new WorldObject(1281, 10, -1, 2566, 3873, 0), true);// oak
		
		World.spawnObject(new WorldObject(2210, 10, -1, 2570, 3870, 0), true);// willow
		World.spawnObject(new WorldObject(2210, 10, -1, 2567, 3869, 0), true);// willow
		World.spawnObject(new WorldObject(2210, 10, -1, 2565, 3867, 0), true);// willow
		World.spawnObject(new WorldObject(2210, 10, -1, 2564, 3862, 0), true);// willow
		World.spawnObject(new WorldObject(2210, 10, -1, 2569, 3855, 0), true);// willow
		
		World.spawnObject(new WorldObject(9036, 10, -1, 2550, 3882, 0), true);// teak
		World.spawnObject(new WorldObject(9036, 10, -1, 2552, 3882, 0), true);// teak
		World.spawnObject(new WorldObject(9036, 10, -1, 2554, 3882, 0), true);// teak
		World.spawnObject(new WorldObject(9036, 10, -1, 2556, 3882, 0), true);// teak
		World.spawnObject(new WorldObject(9036, 10, -1, 2558, 3882, 0), true);// teak
		
		World.spawnObject(new WorldObject(1309, 10, 1, 2574, 3877, 0), true); // yew tree
		World.spawnObject(new WorldObject(1309, 10, 1, 2572, 3876, 0), true); // yew tree
		World.spawnObject(new WorldObject(1309, 10, 1, 2570, 3874, 0), true); // yew tree
		World.spawnObject(new WorldObject(1309, 10, 1, 2574, 3873, 0), true); // yew tree
		
		
		World.spawnObject(new WorldObject(37823, 10, 1, 2570, 3881, 0), true); // magic tree
		World.spawnObject(new WorldObject(37823, 10, 1, 2567, 3881, 0), true); // magic tree
		World.spawnObject(new WorldObject(37823, 10, 1, 2564, 3879, 0), true); // magic tree
		World.spawnObject(new WorldObject(37823, 10, 1, 2565, 3884, 0), true); // magic tree
		
		World.spawnObject(new WorldObject(16604, 10, -1, 2571, 3896, 0), true);// dream tree
		
		/*
		 * Fishing area
		 */
		World.spawnObject(new WorldObject(2045, 10, 1, 2573, 3854, 0), true);// deposit box
		World.spawnObject(new WorldObject(2045, 10, -1, 2575, 3844, 0), true);// deposit box
		
		World.spawnNPC(1597, new WorldTile(3428, 3522, 0), 0, false, EntityDirection.SOUTHEAST); //slayer survival
		World.spawnObject(new WorldObject(11354, 10, -1, 3245, 3198, 0), true);// trial of the gods
		
		/*
		 * other
		 */
		World.spawnNPC(528, new WorldTile(2506, 3862, 0), 0, false, EntityDirection.SOUTHEAST); // shop hub
		World.spawnNPC(9085, new WorldTile(2509, 3858, 0), 0, false, EntityDirection.NORTHWEST); // SLAYER - KURADAL
		World.spawnNPC(14386, new WorldTile(2506, 3858, 0), 0, false, EntityDirection.NORTHEAST); // DEATH -
		World.spawnNPC(7780, new WorldTile(2509, 3862, 0), 0, false, EntityDirection.SOUTHWEST); // legendary slayer
		World.spawnObject(new WorldObject(22819, 10, 1, 2505, 3860, 0), true); //bank booth
		
		World.spawnObject(new WorldObject(4878, 10, -2, 2515, 3862, 0), true); // thieving stall l85
		World.spawnObject(new WorldObject(2045, 10, 0, 2516, 3862, 0), true);// deposit box
		World.spawnObject(new WorldObject(70767, 10, 1, 2511, 3858, 0), true); // fire
		World.spawnObject(new WorldObject(28734, 10, 1, 2510, 3865, 0), true); // summoning oblisk
		World.spawnObject(new WorldObject(409, 10, 0, 2515, 3858, 0), true); // normal altar
		
		/*
		 * Barricade area
		 */
		World.spawnObject(new WorldObject(9242, 10, 0, 2518, 3864, 0), true); // barricade
		World.spawnObject(new WorldObject(9242, 10, 0, 2517, 3864, 0), true); // barricade
		World.spawnObject(new WorldObject(9242, 10, 0, 2516, 3864, 0), true); // barricade
		World.spawnObject(new WorldObject(9242, 10, 0, 2515, 3864, 0), true); // barricade
		World.spawnObject(new WorldObject(9242, 10, 0, 2514, 3864, 0), true); // barricade
		World.spawnObject(new WorldObject(9242, 10, 0, 2518, 3865, 0), true); // barricade
		World.spawnObject(new WorldObject(9242, 10, 0, 2518, 3866, 0), true); // barricade
		World.spawnObject(new WorldObject(9242, 10, 0, 2518, 3867, 0), true); // barricade
		World.spawnObject(new WorldObject(9242, 10, 0, 2514, 3868, 0), true); // barricade
		World.spawnObject(new WorldObject(9242, 10, 0, 2515, 3868, 0), true); // barricade
		World.spawnObject(new WorldObject(9242, 10, 0, 2516, 3868, 0), true); // barricade
		World.spawnObject(new WorldObject(9242, 10, 0, 2517, 3868, 0), true); // barricade
		World.spawnObject(new WorldObject(9242, 10, 0, 2518, 3868, 0), true); // barricade
		World.spawnObject(new WorldObject(9242, 10, 0, 2518, 3868, 0), true); // barricade
		World.spawnObject(new WorldObject(9242, 10, 0, 2518, 3867, 0), true); // barricade
		World.spawnObject(new WorldObject(9242, 10, 0, 2518, 3866, 0), true); // barricade
		World.spawnObject(new WorldObject(9242, 10, 0, 2518, 3865, 0), true); // barricade
		World.spawnObject(new WorldObject(9242, 10, 0, 2514, 3865, 0), true); // barricade
		World.spawnObject(new WorldObject(9242, 10, 0, 2514, 3866, 0), true); // barricade
		World.spawnObject(new WorldObject(9242, 10, 0, 2514, 3867, 0), true); // barricade
		
		
		/**
		 * End of Clan zone
		 */
		
		/**
		 *
		 */
		World.spawnObject(new WorldObject(2273, 10, 1, 2841, 4828, 0), true); //air rc portal
		/**
		 * Prestige zone
		 */
		World.spawnObject(new WorldObject(32015, 10, 3, 2663, 9636, 0), true);//ladder up
		World.spawnObject(new WorldObject(-1, 10, 2, 1382, 3813, 0), true);
		World.spawnObject(new WorldObject(-1, 10, 2, 2664, 9635, 0), true);
		World.spawnObject(new WorldObject(-1, 10, 2, 2664, 9634, 0), true);
		World.spawnObject(new WorldObject(-1, 10, 2, 2661, 9641, 0), true);
		World.spawnObject(new WorldObject(-1, 10, 2, 2664, 9633, 0), true);
		World.spawnObject(new WorldObject(70767, 10, 1, 1384, 3824, 0), true); // fire
		World.spawnObject(new WorldObject(70767, 10, 1, 1380, 3824, 0), true); // fire
		World.spawnObject(new WorldObject(49036, 10, -1, 1377, 3828, 0), true); // Anvil
		World.spawnObject(new WorldObject(2213, 10, 0, 1382, 3825, 0), true); // bank
		World.spawnObject(new WorldObject(233483, 10, 2, 1382, 3822, 0), true); // stairs down
		
		World.spawnObject(new WorldObject(1307, 10, 1, 1380, 3814, 0), true); // maple tree
		World.spawnObject(new WorldObject(1307, 10, 1, 1383, 3814, 0), true); // maple tree
		World.spawnObject(new WorldObject(37823, 10, 1, 1373, 3819, 0), true); // magic tree
		World.spawnObject(new WorldObject(37823, 10, 1, 1373, 3823, 0), true); // magic tree
		World.spawnObject(new WorldObject(1309, 10, 1, 1374, 3827, 0), true); // yew tree
		World.spawnObject(new WorldObject(37823, 10, 1, 1390, 3819, 0), true); // magic tree
		World.spawnObject(new WorldObject(37823, 10, 1, 1390, 3823, 0), true); // magic tree
		World.spawnObject(new WorldObject(1309, 10, 1, 1389, 3827, 0), true); // yew tree
		
		
		World.spawnObject(new WorldObject(33079, 10, -1, 1379, 3831, 0), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 1387, 3831, 0), true);// Runite ore
		World.spawnObject(new WorldObject(2104, 10, -1, 1385, 3831, 0), true); // addy ore
		World.spawnObject(new WorldObject(2104, 10, -1, 1383, 3831, 0), true); // addy ore
		World.spawnObject(new WorldObject(37310, 10, -1, 1378, 3831, 0), true); // goal ore
		World.spawnObject(new WorldObject(37310, 10, -1, 1377, 3831, 0), true); // goal ore
		World.spawnObject(new WorldObject(37310, 10, -1, 1380, 3831, 0), true); // goal ore
		World.spawnObject(new WorldObject(37310, 10, -1, 1382, 3831, 0), true); // goal ore
		World.spawnObject(new WorldObject(2096, 10, -1, 1381, 3831, 0), true);// Coal ore
		World.spawnObject(new WorldObject(2096, 10, -1, 1384, 3831, 0), true);// Coal ore
		World.spawnObject(new WorldObject(2096, 10, -1, 1386, 3831, 0), true);// Coal ore
		World.spawnNPC(30162, new WorldTile(1383, 3825, 0), 0, false, EntityDirection.SOUTH);
		World.spawnNPC(30169, new WorldTile(1381, 3825, 0), 0, false, EntityDirection.SOUTH);
		
		World.spawnNPC(30158, new WorldTile(1377, 3830, 0), 0, false, EntityDirection.NORTH);
		World.spawnNPC(30159, new WorldTile(1384, 3830, 0), 0, false, EntityDirection.NORTH);
		World.spawnNPC(30160, new WorldTile(1382, 3829, 0), 0, false, EntityDirection.SOUTH);
		World.spawnNPC(30161, new WorldTile(1376, 3829, 0), 0, false, EntityDirection.NORTH);
		
		/**
		 * end of pz
		 */
		
		World.spawnNPC(8641, new WorldTile(4453, 4426, 0), 0, false, EntityDirection.NORTH);

		World.spawnObject(new WorldObject(2213, 10, 0, 4447, 4434, 0), true); // bank
		World.spawnObject(new WorldObject(2213, 10, 0, 4448, 4434, 0), true); // bank
		World.spawnObject(new WorldObject(2213, 10, 0, 4449, 4434, 0), true); // bank

		World.spawnObject(new WorldObject(409, 10, 2, 4452, 4434, 0), true); // normal altar

		World.spawnObject(new WorldObject(14873, 10, 2, 3029, 2980, 0), true); // staff desk
		
		
		World.spawnObject(new WorldObject(-1, 10, 2, 1631, 3660, 0), true);
		
		/**
		 * ASssassin
		 */
		
		World.spawnObject(new WorldObject(724, 10, 2, 3514, 9839, 0), true);
		World.spawnObject(new WorldObject(724, 10, 2, 3514, 9838, 0), true);
		World.spawnObject(new WorldObject(2273, 10, 2, 3514, 9837, 0), true);
		World.spawnObject(new WorldObject(724, 10, 2, 3514, 9836, 0), true);

		// World.spawnObject(new WorldObject(63, 10, -1, 2564, 9629, 0), true);//
		// World.spawnObject(new WorldObject(63, 10, -1, 2564, 9630, 0), true);//
		// World.spawnObject(new WorldObject(63, 10, -1, 2564, 9631, 0), true);//
		// World.spawnObject(new WorldObject(63, 10, -1, 2564, 9632, 0), true);//
		//
		// World.spawnObject(new WorldObject(64, 10, -1, 2591, 9608, 0), true);//
		// World.spawnObject(new WorldObject(64, 10, -1, 2591, 9609, 0), true);//

		// World.spawnObject(new WorldObject(-1, 10, -1, 2584, 9611, 0), true);// -1
		// World.spawnObject(new WorldObject(-1, 10, -1, 2585, 9611, 0), true);// -1
		// World.spawnObject(new WorldObject(-1, 10, -1, 2588, 9611, 0), true);// -1
		// World.spawnObject(new WorldObject(-1, 10, -1, 2589, 9611, 0), true);// -1
		//
		// World.spawnObject(new WorldObject(-1, 10, -1, 2606, 9644, 0), true);// -1
		// World.spawnObject(new WorldObject(-1, 10, -1, 2607, 9644, 0), true);// -1
		// World.spawnObject(new WorldObject(-1, 10, -1, 2611, 9639, 0), true);// -1

		// World.spawnObject(new WorldObject(-1, 10, -1, 2590, 9606, 0), true);// -1
		// World.spawnObject(new WorldObject(-1, 10, -1, 2588, 9606, 0), true);// -1
		// World.spawnObject(new WorldObject(-1, 10, -1, 2589, 9606, 0), true);// -1
		// World.spawnObject(new WorldObject(-1, 10, -1, 2586, 9606, 0), true);// -1
		// World.spawnObject(new WorldObject(-1, 10, -1, 2585, 9606, 0), true);// -1

		World.spawnObject(new WorldObject(-1, 10, -1, 4446, 4407, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4446, 4408, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4446, 4409, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4446, 4410, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4446, 4411, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4446, 4412, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4446, 4413, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4446, 4414, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4446, 4415, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4446, 4416, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4446, 4417, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4446, 4418, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4446, 4419, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4446, 4420, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4446, 4421, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4446, 4422, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4446, 4423, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4446, 4424, 0), true);// -1
		
		World.spawnObject(new WorldObject(-1, 10, -1, 4457, 4407, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4457, 4408, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4457, 4409, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4457, 4410, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4457, 4411, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4457, 4412, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4457, 4413, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4457, 4414, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4457, 4415, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4457, 4416, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4457, 4417, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4457, 4418, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4457, 4419, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4457, 4420, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4457, 4421, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4457, 4422, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4457, 4423, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4457, 4424, 0), true);// -1
		
		World.spawnObject(new WorldObject(-1, 10, -1, 4456, 4406, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4455, 4406, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4454, 4406, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4453, 4406, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4452, 4406, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4451, 4406, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4450, 4425, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4449, 4425, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4448, 4425, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4447, 4425, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4456, 4425, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4455, 4425, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4454, 4425, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4453, 4425, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4452, 4425, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4451, 4425, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4450, 4425, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4449, 4425, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4448, 4425, 0), true);// -1
		World.spawnObject(new WorldObject(-1, 10, -1, 4447, 4425, 0), true);// -1
		
		/**
		 * Rues altar
		 */
		
		World.spawnNPC(8031, new WorldTile(3044, 4844, 0), 0, false, EntityDirection.SOUTH); // rue
		

		/**
		 * noob zone
		 */

		World.spawnObject(new WorldObject(37009, 10, -1, 2891, 2728, 0), true);// -1
		World.spawnObject(new WorldObject(2213, 10, -1, 2891, 2727, 0), true);

		/**
		 * Level 3 zone
		 */
		World.spawnObject(new WorldObject(2045, 10, -1, 4447, 4328, 3), true);// deposit box
		
		World.spawnObject(new WorldObject(49934, 10, 0, 4454, 4314, 3), true);//spin wheel

		World.spawnObject(new WorldObject(409, 10, 1, 4441, 4319, 3), true); // normal altar
		
		World.spawnObject(new WorldObject(9242, 10, -1, 4441, 4314, 3), true);// barricade
		World.spawnObject(new WorldObject(9242, 10, -1, 4442, 4314, 3), true);// barricade
		World.spawnObject(new WorldObject(9242, 10, -1, 4443, 4314, 3), true);// barricade
		World.spawnObject(new WorldObject(9242, 10, -1, 4443, 4314, 3), true);// barricade
		World.spawnObject(new WorldObject(9242, 10, -1, 4443, 4315, 3), true);// barricade
		World.spawnObject(new WorldObject(9242, 10, -1, 4443, 4316, 3), true);// barricade
		World.spawnObject(new WorldObject(9242, 10, -1, 4441, 4316, 3), true);// barricade
		World.spawnObject(new WorldObject(9242, 10, -1, 4442, 4316, 3), true);// barricade
		World.spawnObject(new WorldObject(9242, 10, -1, 4443, 4316, 3), true);// barricade
		
		World.spawnObject(new WorldObject(359, 10, -1, 4441, 4317, 3), true);// boxes -agility

		World.spawnObject(new WorldObject(2096, 10, -1, 4441, 4312, 3), true);// Coal ore
		World.spawnObject(new WorldObject(2096, 10, -1, 4442, 4312, 3), true);// Coal ore
		World.spawnObject(new WorldObject(2096, 10, -1, 4443, 4312, 3), true);// Coal ore
		World.spawnObject(new WorldObject(2096, 10, -1, 4444, 4312, 3), true);// Coal ore
		World.spawnObject(new WorldObject(2096, 10, -1, 4445, 4312, 3), true);// Coal ore
		World.spawnObject(new WorldObject(2096, 10, -1, 4446, 4312, 3), true);// Coal ore

		World.spawnObject(new WorldObject(2104, 10, -1, 4447, 4312, 3), true); // addy ore
		World.spawnObject(new WorldObject(2104, 10, -1, 4448, 4312, 3), true); // addy ore
		World.spawnObject(new WorldObject(2104, 10, -1, 4449, 4312, 3), true); // addy ore
		World.spawnObject(new WorldObject(2104, 10, -1, 4450, 4312, 3), true); // addy ore

		World.spawnObject(new WorldObject(14859, 10, -1, 4451, 4312, 3), true); // runite ore
		World.spawnObject(new WorldObject(14859, 10, -1, 4452, 4312, 3), true); // runite ore
		World.spawnObject(new WorldObject(14859, 10, -1, 4453, 4312, 3), true); // runite ore

		World.spawnObject(new WorldObject(92713, 10, -1, 4454, 4312, 3), true); // seren stone
		World.spawnObject(new WorldObject(92713, 10, -1, 4455, 4312, 3), true); // seren stone

		World.spawnObject(new WorldObject(2213, 10, -1, 4456, 4323, 3), true); // bank
		World.spawnObject(new WorldObject(2213, 10, -1, 4440, 4323, 3), true); // bank

		World.spawnObject(new WorldObject(37823, 10, 1, 4453, 4326, 3), true);// magic tree
		World.spawnObject(new WorldObject(37823, 10, 1, 4451, 4326, 3), true);// magic tree
		World.spawnObject(new WorldObject(37823, 10, 1, 4449, 4326, 3), true);// magic tree
		World.spawnObject(new WorldObject(37823, 10, 1, 4447, 4326, 3), true);// magic tree
		World.spawnObject(new WorldObject(1309, 10, 1, 4445, 4326, 3), true); // yew tree
		World.spawnObject(new WorldObject(1309, 10, 1, 4443, 4326, 3), true); // yew tree
		World.spawnObject(new WorldObject(1309, 10, 1, 4441, 4326, 3), true); // yew tree
		World.spawnObject(new WorldObject(16604, 10, -1, 4448, 4329, 3), true);// dream tree
		World.spawnObject(new WorldObject(12809, 10, 1, 4454, 4319, 3), true); // furnace
		World.spawnObject(new WorldObject(49036, 10, -1, 4454, 4318, 3), true); // Anvil
		World.spawnObject(new WorldObject(70766, 10, 1, 4457, 4323, 3), true); // fire
		
		World.spawnObject(new WorldObject(4878, 10, -2, 4441, 4324, 3), true); // thieving stall l85
		World.spawnObject(new WorldObject(18819, 10, -2, 4445, 4319, 3), true); // patch
		World.spawnObject(new WorldObject(67006, 10, -2, 4455, 4312, 3), true); // rock
		World.spawnObject(new WorldObject(67006, 10, -2, 4454, 4312, 3), true); // rock

		World.spawnNPC(30142, new WorldTile(4441, 4321, 3), 0, false, EntityDirection.EAST); // donor shop npc
		World.spawnNPC(8842, new WorldTile(4443, 4323, 3), 0, false, EntityDirection.WEST); // rocktail
		/**
		 * DZ5
		 */

		World.spawnObject(new WorldObject(30624, 10, -3, 3423, 5275, 0), true); //blood altar
		World.spawnNPC(15012, new WorldTile(3403, 5275, 0), 0, false, EntityDirection.EAST); // dz5 shop
		World.spawnNPC(30142, new WorldTile(3403, 5272, 0), 0, false, EntityDirection.EAST); // donor shop npc
		World.spawnNPC(528, new WorldTile(3411, 5275, 0), 0, false, EntityDirection.WEST); // shop hub

		/**
		 * end dung
		 */
		
		World.spawnNPC(4250, new WorldTile(3302, 3492, 0), 0, false, EntityDirection.SOUTH); // SAWMILL
		World.spawnNPC(4250, new WorldTile(1593, 3480, 0), 0, false, EntityDirection.WEST); // SAWMILL
		
		//World.removeObject(new WorldObject(10326, 0, 2, 2847, 5088, 0));
		World.deleteObject(new WorldTile(3815, 3062, 0));
		World.deleteObject(new WorldTile(3814, 3062, 0));
		World.deleteObject(new WorldTile(3815, 3063, 0));
		World.deleteObject(new WorldTile(3814, 3063, 0));
		World.deleteObject(new WorldTile(3477, 9846, 0));
		World.deleteObject(new WorldTile(3336, 3896, 0)); //wilderness gates
		World.deleteObject(new WorldTile(3224, 3904, 0)); //wilderness gates
		World.deleteObject(new WorldTile(2974, 3904, 0)); //wilderness gates
		World.deleteObject(new WorldTile(2794, 9327, 0));
		World.deleteObject(new WorldTile(2629, 5072, 0));
		World.deleteObject(new WorldTile(2586, 5595, 0));
		World.deleteObject(new WorldTile(2583, 5594, 0));
		World.deleteObject(new WorldTile(2584, 5599, 0));
		World.deleteObject(new WorldTile(2588, 5599, 0));
		World.deleteObject(new WorldTile(2584, 5596, 0));
		World.deleteObject(new WorldTile(2585, 5593, 0));
		World.deleteObject(new WorldTile(3483, 9826, 0));
		World.deleteObject(new WorldTile(3492, 9816, 0));
		World.deleteObject(new WorldTile(2841, 5073, 0));
		World.deleteObject(new WorldTile(4183, 5726, 0));
		World.deleteObject(new WorldTile(2325, 3666, 0));
		World.deleteObject(new WorldTile(3082, 3513, 0));
		World.deleteObject(new WorldTile(2898, 4449, 0));
		World.deleteObject(new WorldTile(2328, 3670, 0));
		World.deleteObject(new WorldTile(2330, 3670, 0));
		World.deleteObject(new WorldTile(2899, 4449, 0));
		World.deleteObject(new WorldTile(2332, 3670, 0));
		World.deleteObject(new WorldTile(2328, 3666, 0));
		World.deleteObject(new WorldTile(2330, 3666, 0));
		World.deleteObject(new WorldTile(2592, 5591, 0));
		World.deleteObject(new WorldTile(2333, 3666, 0));
		World.deleteObject(new WorldTile(2825, 3003, 0));
		World.deleteObject(new WorldTile(2823, 2999, 0));
		World.deleteObject(new WorldTile(2823, 2999, 0));
		World.deleteObject(new WorldTile(2821, 2998, 0));
		World.deleteObject(new WorldTile(2333, 10016, 0));
	//	World.deleteObject(new WorldTile(3432, 3550, 2));
		World.deleteObject(new WorldTile(4188, 5724, 0));
		World.deleteObject(new WorldTile(2902, 3873, 0));
		World.deleteObject(new WorldTile(2924, 3880, 0));
		//World.deleteObject(new WorldTile(4196, 5711, 0));
		World.deleteObject(new WorldTile(4208, 5707, 0));
		World.deleteObject(new WorldTile(4170, 5709, 0));
		World.deleteObject(new WorldTile(4183, 5730, 0));
		World.deleteObject(new WorldTile(4178, 5729, 0));
		World.deleteObject(new WorldTile(4178, 5725, 0));
		//World.deleteObject(new WorldTile(4195, 5712, 0));
		World.deleteObject(new WorldTile(4209, 5721, 0));
		World.deleteObject(new WorldTile(4184, 5722, 0));
		//World.deleteObject(new WorldTile(4200, 5713, 0));
		//World.deleteObject(new WorldTile(4199, 5711, 0));
		
		World.spawnObject(new WorldObject(4420, 10, -1, 2732, 3468, 0), true); //court judge
		
		
		World.spawnObject(new WorldObject(1, 10, -1, 2855, 3371, 0), true); //box at starter
		World.spawnObject(new WorldObject(1, 10, -1, 2855, 3370, 0), true); //box at starter
		
		World.spawnObject(new WorldObject(-1, 10, -1, 1801, 3213, 0), true); // safespot AOD
		
		/**
		 * TheDwarvenDiscovery
		 */
		World.spawnObject(new WorldObject(2823, 10, -1, 3012, 3445, 0), true); //hole
		World.spawnNPC(15231, new WorldTile(3012, 3445, 0), 0, false, EntityDirection.SOUTH); //HEAD DWARF
		World.spawnObject(new WorldObject(73699, 10, -1, 2823, 9877, 0), true); //dragon ore
		World.spawnObject(new WorldObject(73699, 10, -1, 2823, 9877, 0), true); //dragon ore
		World.spawnObject(new WorldObject(73699, 10, -1, 2829, 9874, 0), true); //dragon ore
		World.spawnObject(new WorldObject(73699, 10, -1, 2825, 9872, 0), true); //dragon ore
		World.spawnObject(new WorldObject(73699, 10, -1, 2831, 9868, 0), true); //dragon ore
		World.spawnObject(new WorldObject(73699, 10, -1, 2829, 9863, 0), true); //dragon ore
		World.spawnObject(new WorldObject(73699, 10, -1, 2849, 9878, 0), true); //dragon ore
		World.spawnObject(new WorldObject(73699, 10, -1, 2846, 9879, 0), true); //dragon ore
		/*
		 * Heist
		 */
		World.spawnObject(new WorldObject(16050, 10, -1, 3506, 3316, 0), true); //enter portal
		World.spawnObject(new WorldObject(1, 10, -1, 3506, 3309, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 3463, 3309, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 3463, 3308, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 3488, 3261, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 3086, 3125, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 3489, 3261, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 3523, 3280, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 3523, 3279, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 3534, 3279, 0), true);
	//	World.spawnObject(new WorldObject(-1, 10, -1, 3488, 3292, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 3534, 3280, 0), true);
		World.spawnObject(new WorldObject(2191, 10, -1, 3461, 3321, 0), true);
		World.spawnObject(new WorldObject(2361, 10, -1, 3516, 3276, 0), true);
		World.spawnObject(new WorldObject(11368, 10, -1, 3088, 3122, 0), true);
		World.spawnObject(new WorldObject(11368, 10, -1, 3138, 3096, 0), true);
		World.spawnObject(new WorldObject(24875, 10, -1, 3488, 3296, 0), true);
		World.spawnNPC(11280, new WorldTile(3507, 3315, 0), 0, false, EntityDirection.WEST); //cheifthief
		
		//lauren new yr 2017
		//World.spawnNPC(9996, new WorldTile(3450, 3745, 0), 0, false);
		
		
		World.deleteObject(new WorldTile(2537, 3096, 0)); //YANILLE STAIRS
		World.deleteObject(new WorldTile(2537, 3085, 0)); //YANILLE STAIRS
		
		World.spawnObject(new WorldObject(1, 10, -1, 3541, 9507, 0), true); //vorago create
		
		World.spawnObject(new WorldObject(1, 10, -1, 2910, 9910, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2909, 9910, 0), true);
		
		//orb charging
		World.spawnObject(new WorldObject(2152, 10, -1, 2844, 3420, 0), true);
		World.spawnObject(new WorldObject(29415, 10, -1, 2847, 3420, 0), true);
		World.spawnObject(new WorldObject(74862, 10, -1, 2846, 3425, 0), true);
		
		/**
		 * Max Guild
		 */
		World.spawnObject(new WorldObject(-1, 10, 0, 6256, 6243, 0), true); // normal altar


		World.spawnObject(new WorldObject(2213, 10, -1, 3357, 3337, 0), true); // bank
		World.spawnObject(new WorldObject(2213, 10, -1, 3357, 3339, 0), true); // bank

		World.spawnObject(new WorldObject(2563, 10, 3, 3348, 3335, 0), true); // comp cape stand

		World.spawnNPC(528, new WorldTile(3362, 3340, 0), 0, false, EntityDirection.WEST); // shop hub
		World.spawnNPC(1419, new WorldTile(3357, 3338, 0), 0, false, EntityDirection.EAST); // ge
		/**
		 * Raids 2
		 */
		World.spawnObject(new WorldObject(2096, 10, -1, 2392, 10272, 1), true);// Coal ore
		World.spawnObject(new WorldObject(2096, 10, -1, 2393, 10272, 1), true);// Coal ore
		World.spawnObject(new WorldObject(2096, 10, -1, 2392, 10275, 1), true);// Coal ore
		
		World.spawnObject(new WorldObject(2096, 10, -1, 2388, 10274, 1), true);// Coal ore
		World.spawnObject(new WorldObject(2096, 10, -1, 2388, 10273, 1), true);// Coal ore
		World.spawnObject(new WorldObject(2096, 10, -1, 2388, 10272, 1), true);// Coal ore
		World.spawnObject(new WorldObject(2096, 10, -1, 2391, 10271, 1), true);// Coal ore
		World.spawnObject(new WorldObject(2096, 10, -1, 2384, 10270, 1), true);// Coal ore
		World.spawnObject(new WorldObject(2096, 10, -1, 2382, 10284, 1), true);// Coal ore
		
		
		World.spawnObject(new WorldObject(2104, 10, -1, 2398, 10269, 1), true); //addy ore
		World.spawnObject(new WorldObject(2104, 10, -1, 2399, 10269, 1), true); //addy ore
		World.spawnObject(new WorldObject(2104, 10, -1, 2400, 10270, 1), true); //addy ore
		World.spawnObject(new WorldObject(2104, 10, -1, 2400, 10272, 1), true); //addy ore
		
		World.spawnObject(new WorldObject(14859, 10, -1, 2400, 10271, 1), true); //runite ore
		World.spawnObject(new WorldObject(14859, 10, -1, 2383, 10280, 1), true); //runite ore
		World.spawnObject(new WorldObject(14859, 10, -1, 2383, 10281, 1), true); //runite ore
		World.spawnObject(new WorldObject(14859, 10, -1, 2381, 10282, 1), true); //runite ore
		World.spawnObject(new WorldObject(14859, 10, -1, 2380, 10282, 1), true); //runite ore
		World.spawnObject(new WorldObject(14859, 10, -1, 2401, 10289, 1), true); //runite ore
		World.spawnObject(new WorldObject(14859, 10, -1, 2402, 10292, 1), true); //runite ore
		
		World.spawnObject(new WorldObject(73699, 10, -1, 2379, 10280, 1), true); //dragon ore
		World.spawnObject(new WorldObject(73699, 10, -1, 2379, 10279, 1), true); //dragon ore
		World.spawnObject(new WorldObject(73699, 10, -1, 2380, 10278, 1), true); //dragon ore
		World.spawnObject(new WorldObject(73699, 10, -1, 2401, 10279, 1), true); //dragon ore
		World.spawnObject(new WorldObject(73699, 10, -1, 2402, 10280, 1), true); //dragon ore
		
		World.spawnObject(new WorldObject(12809, 10, 0, 2393, 10299, 1), true); //furnace
		
		World.spawnObject(new WorldObject(49036, 10, -1, 2396, 10298, 1), true); //Anvil
		
		World.spawnNPC(6267, new WorldTile(2408, 10284, 1), 0, false, EntityDirection.SOUTH); //LOBSTER
		World.spawnNPC(313, new WorldTile(2403, 10280, 1), 0, false, EntityDirection.SOUTH); //shark
		
		World.spawnNPC(30088, new WorldTile(2393, 10254, 1), 0, false, EntityDirection.WEST); //leave raid
		
		
		
		/**
		 * Robust glass machine
		 */
		World.spawnObject(new WorldObject(67968, 10, 1, 2558, 2847, 0), true);
		
		/**
		 * 
		 * Trio boss raid
		 * 
		 */
		
		World.spawnObject(new WorldObject(2798, 10, 1, 2907, 9904, 0), true);
		World.spawnObject(new WorldObject(5107, 10, 1, 2904, 9907, 0), true);
		World.spawnObject(new WorldObject(4954, 10, 1, 2911, 9903, 0), true);
		
		
		World.spawnNPC(568, new WorldTile(2914, 9904, 0), 0, false, EntityDirection.NORTH); //LEAVE TRIO RAID
		
		// /**
		// *
		// * commanditos Clan Zone
		// *
		// */
		// World.spawnObject(new WorldObject(37823, 10, 1, 3410, 3173, 0), true);//magic
		// tree
		// World.spawnObject(new WorldObject(1309, 10, 1, 3406, 3173, 0), true); //yew
		// tree
		// World.spawnObject(new WorldObject(37823, 10, 1, 3410, 3171, 0), true);//magic
		// tree
		// World.spawnObject(new WorldObject(1309, 10, 1, 3406, 3171, 0), true); //yew
		// tree
		// World.spawnObject(new WorldObject(37823, 10, 1, 3410, 3169, 0), true);//magic
		// tree
		// World.spawnObject(new WorldObject(37823, 10, 1, 3410, 3167, 0), true);//magic
		// tree
		// World.spawnObject(new WorldObject(1309, 10, 1, 3406, 3169, 0), true); //yew
		// tree
		// World.spawnObject(new WorldObject(2213, 10, -1, 3413, 3159, 0), true); //bank
		// World.spawnNPC(9492, new WorldTile(2332, 10015, 0), 0, false,
		// EntityDirection.SOUTH); //SUMM STORE
		// World.spawnObject(new WorldObject(28734, 10, 1, 3425, 3150, 0), true);
		// //summoning oblisk
		// World.spawnObject(new WorldObject(409, 10, 3, 3450, 3163, 0), true); //normal
		// altar
		// World.spawnObject(new WorldObject(2213, 10, -1, 3450, 3166, 0), true); //bank
		// World.spawnObject(new WorldObject(2213, 10, -1, 3450, 3161, 0), true); //bank
		// World.spawnObject(new WorldObject(11194, 10, -1, 3410, 3160, 0), true);// gem
		// rock
		// World.spawnObject(new WorldObject(11194, 10, -1, 3410, 3157, 0), true);// gem
		// rock
		// World.spawnObject(new WorldObject(11194, 10, -1, 3410, 3158, 0), true);// gem
		// rock
		// World.spawnObject(new WorldObject(11194, 10, -1, 3410, 3159, 0), true);// gem
		// rock
		// World.spawnObject(new WorldObject(2096, 10, -1, 3406, 3160, 0), true);// Coal
		// ore
		// World.spawnObject(new WorldObject(2096, 10, -1, 3406, 3157, 0), true);// Coal
		// ore
		// World.spawnObject(new WorldObject(2096, 10, -1, 3406, 3158, 0), true);// Coal
		// ore
		// World.spawnObject(new WorldObject(2096, 10, -1, 3406, 3159, 0), true);// Coal
		// ore
		// World.spawnObject(new WorldObject(2104, 10, -1, 3402, 3159, 0), true);//addy
		// rock
		// World.spawnObject(new WorldObject(2104, 10, -1, 3402, 3158, 0), true);//addy
		// rock
		// World.spawnObject(new WorldObject(2104, 10, -1, 3402, 3160, 0), true);//addy
		// rock
		// World.spawnObject(new WorldObject(2104, 10, -1, 3402, 3157, 0), true);//addy
		// rock
		// World.spawnObject(new WorldObject(33079, 10, -1, 3400, 3160, 0), true);//
		// Runite ore
		// World.spawnObject(new WorldObject(33079, 10, -1, 3400, 3157, 0), true);//
		// Runite ore
		// World.spawnObject(new WorldObject(33079, 10, -1, 3400, 3158, 0), true);//
		// Runite ore
		// World.spawnObject(new WorldObject(33079, 10, -1, 3400, 3159, 0), true);//
		// Runite ore
		// World.spawnObject(new WorldObject(2102, 10, -1, 3404, 3160, 0), true);// mith
		// ore
		// World.spawnObject(new WorldObject(2102, 10, -1, 3404, 3157, 0), true);// mith
		// ore
		// World.spawnObject(new WorldObject(2102, 10, -1, 3404, 3158, 0), true);// mith
		// ore
		// World.spawnObject(new WorldObject(2102, 10, -1, 3404, 3159, 0), true);// mith
		// ore
		// World.spawnObject(new WorldObject(2092, 10, -1, 3408, 3160, 0), true);// iron
		// ore
		// World.spawnObject(new WorldObject(2092, 10, -1, 3408, 3157, 0), true);// iron
		// ore
		// World.spawnObject(new WorldObject(2092, 10, -1, 3408, 3158, 0), true);// iron
		// ore
		// World.spawnObject(new WorldObject(2092, 10, -1, 3408, 3159, 0), true);// iron
		// ore
		// World.spawnObject(new WorldObject(12809, 10, 2, 3397, 3156, 0), true);
		// //furnace
		// World.spawnObject(new WorldObject(12809, 10, 2, 3396, 3156, 0), true);
		// //furnace
		// World.spawnObject(new WorldObject(12809, 10, 2, 3395, 3156, 0), true);
		// //furnace
		// World.spawnObject(new WorldObject(12809, 10, 2, 3394, 3156, 0), true);
		// //furnace
		// World.spawnObject(new WorldObject(49036, 10, -1, 3397, 3159, 0), true);
		// //Anvil
		// World.spawnObject(new WorldObject(49036, 10, -1, 3394, 3159, 0), true);
		// //Anvil
		// World.spawnNPC(9085, new WorldTile(3420, 3166, 0), 0, false,
		// EntityDirection.NORTH); //SLAYER - KURADAL
		// World.spawnNPC(13768, new WorldTile(3423, 3161, 0), 0, false,
		// EntityDirection.SOUTH); //DUNGEONEERING
		// World.spawnNPC(587, new WorldTile(3421, 3161, 0), 0, false,
		// EntityDirection.SOUTH); //HERB STORE
		// World.spawnNPC(805, new WorldTile(3422, 3161, 0), 0, false,
		// EntityDirection.SOUTH); //CRAFTING STORE
		// World.spawnNPC(3705, new WorldTile(3424, 3161, 0), 0, false,
		// EntityDirection.SOUTH); //MAX
		// World.spawnNPC(481, new WorldTile(3420, 3161, 0), 0, false,
		// EntityDirection.SOUTH); //MISC
		// World.spawnNPC(528, new WorldTile(3422, 3166, 0), 0, false,
		// EntityDirection.NORTH); //GEN STORE
		// World.spawnNPC(546, new WorldTile(3423, 3166, 0), 0, false,
		// EntityDirection.NORTH); //MAGIC STORE
		// World.spawnNPC(519, new WorldTile(3424, 3166, 0), 0, false,
		// EntityDirection.NORTH); //SKILLING STORE
		// World.spawnNPC(8864, new WorldTile(3421, 3166, 0), 0, false,
		// EntityDirection.NORTH); //POTIONS AND FLASKS
		// World.spawnObject(new WorldObject(4875, 10, -2, 3419, 3165, 0), true);
		// World.spawnObject(new WorldObject(4876, 10, -2, 3419, 3164, 0), true);
		// World.spawnObject(new WorldObject(4877, 10, -2, 3419, 3163, 0), true);
		// World.spawnObject(new WorldObject(4878, 10, -2, 3419, 3162, 0), true);
		// //thieving stall l85
		// World.deleteObject(new WorldTile(3423, 3176, 0)); //junk
		// World.deleteObject(new WorldTile(3425, 3176, 0)); //junk
		// World.deleteObject(new WorldTile(3423, 3177, 0)); //junk
		// World.deleteObject(new WorldTile(3425, 3177, 0)); //junk
		// World.deleteObject(new WorldTile(3443, 3161, 0)); //junk
		// World.deleteObject(new WorldTile(3417, 3177, 0)); //junk
		// World.deleteObject(new WorldTile(3416, 3177, 0)); //junk
		// World.deleteObject(new WorldTile(3415, 3177, 0)); //junk
		// World.deleteObject(new WorldTile(3415, 3178, 0)); //junk
		// World.deleteObject(new WorldTile(3415, 3177, 0)); //junk
		// World.deleteObject(new WorldTile(3415, 3176, 0)); //junk
		// World.deleteObject(new WorldTile(3415, 3175, 0)); //junk
		// World.deleteObject(new WorldTile(3415, 3174, 0)); //junk
		// World.deleteObject(new WorldTile(3415, 3173, 0)); //junk
		// World.deleteObject(new WorldTile(3419, 3178, 0)); //junk
		// World.deleteObject(new WorldTile(3420, 3177, 0)); //junk
		// World.deleteObject(new WorldTile(3420, 3175, 0)); //junk
		// World.deleteObject(new WorldTile(3419, 3173, 0)); //junk
		// World.spawnObject(new WorldObject(2213, 10, -1, 3420, 3173, 0), true); //bank
		// World.spawnNPC(8842, new WorldTile(3416, 3177, 0), 0, false,
		// EntityDirection.SOUTH); //rocktail
		// World.spawnNPC(8841, new WorldTile(3419, 3177, 0), 0, false,
		// EntityDirection.SOUTH); //cave fish
		// World.spawnObject(new WorldObject(2213, 10, 0, 3433, 3176, 0), true); //bank
		// World.spawnObject(new WorldObject(2728, 10, 2, 3434, 3174, 0),
		// true);//cooking range
		// World.spawnNPC(605, new WorldTile(3418, 3151, 0), 0, false,
		// EntityDirection.SOUTH); //blood altare teke
		// World.spawnObject(new WorldObject(47120, 10, -2, 3443, 3166, 0), true);
		// //zaros altar
		// World.spawnObject(new WorldObject(6552, 10, 1, 3436, 3163, 0), true);
		// //ancient altar
		// World.spawnObject(new WorldObject(17010, 10, -1, 3442, 3160, 0), true);
		// //lunar altar
		// World.spawnObject(new WorldObject(2213, 10, -1, 3429, 3165, 0), true); //bank
		// World.spawnObject(new WorldObject(2213, 10, -1, 3429, 3164, 0), true); //bank
		// World.spawnObject(new WorldObject(2213, 10, -1, 3429, 3163, 0), true); //bank
		// World.spawnObject(new WorldObject(2213, 10, -1, 3429, 3162, 0), true); //bank
		// World.spawnObject(new WorldObject(2588, 10, 1, 3429, 3166, 0),
		// true);//crystal key chest
		// World.spawnNPC(8206, new WorldTile(3429, 3161, 0), 0, false,
		// EntityDirection.WEST); //LOTTIE
		// World.spawnObject(new WorldObject(70766, 10, 1, 3425, 3166, 0), true); //fire
		// World.spawnObject(new WorldObject(70766, 10, 1, 3425, 3161, 0), true); //fire
		// World.spawnObject(new WorldObject(1, 10, -2, 3390, 3164, 0), true);
		// World.spawnObject(new WorldObject(1, 10, -2, 3390, 3163, 0), true);
		// /**
		// *
		// * Temptation Clan Zone
		// *
		// */
		// World.spawnObject(new WorldObject(1, 10, -1, 2329, 3172, 0), true); //block
		// World.spawnObject(new WorldObject(1, 10, -1, 2329, 3171, 0), true); //block
		// World.spawnObject(new WorldObject(2213, 10, -1, 2357, 3170, 0), true); //bank
		// World.spawnObject(new WorldObject(2213, 10, -1, 2357, 3173, 0), true); //bank
		// World.spawnObject(new WorldObject(49036, 10, -1, 2351, 3177, 0), true);
		// //Anvil
		// World.spawnObject(new WorldObject(49036, 10, -1, 2351, 3179, 0), true);
		// //Anvil
		// World.spawnObject(new WorldObject(12809, 10, 1, 2355, 3177, 0), true);
		// //furnace
		// World.spawnObject(new WorldObject(4878, 10, -2, 2665, 3300, 0), true);
		// //thieving stall l85
		// World.spawnObject(new WorldObject(70766, 10, 1, 2328, 3170, 0), true); //fire
		// World.spawnObject(new WorldObject(70768, 10, 1, 2328, 3173, 0), true); //fire
		// World.spawnObject(new WorldObject(15482, 10, 1, 2330, 3161, 0), true);
		// //house
		// //World.spawnObject(new WorldObject(15482, 10, 1, 2330, 3161, 0), true);
		// //clan wars - safe
		// World.spawnNPC(9085, new WorldTile(2334, 3186, 0), 0, false,
		// EntityDirection.SOUTH); //SLAYER - KURADAL
		// World.spawnNPC(13768, new WorldTile(2335, 3186, 0), 0, false,
		// EntityDirection.SOUTH); //DUNGEONEERING
		// World.spawnNPC(9492, new WorldTile(2336, 3186, 0), 0, false,
		// EntityDirection.SOUTH); //SUMM STORE
		// World.spawnNPC(219, new WorldTile(2337, 3186, 0), 0, false,
		// EntityDirection.SOUTH); //FISHING STORE
		// World.spawnNPC(546, new WorldTile(2338, 3186, 0), 0, false,
		// EntityDirection.SOUTH); //MAGIC STORE
		// World.spawnNPC(1923, new WorldTile(2339, 3186, 0), 0, false,
		// EntityDirection.SOUTH); //RANGED STORE
		// World.spawnObject(new WorldObject(33079, 10, -1, 2344, 3182, 0), true);//
		// Runite ore
		// World.spawnObject(new WorldObject(33079, 10, -1, 2345, 3182, 0), true);//
		// Runite ore
		// World.spawnObject(new WorldObject(33079, 10, -1, 2346, 3182, 0), true);//
		// Runite ore
		// World.spawnObject(new WorldObject(33079, 10, -1, 2347, 3182, 0), true);//
		// Runite ore
		// World.spawnObject(new WorldObject(33079, 10, -1, 2344, 3181, 0), true);//
		// Runite ore
		// World.spawnObject(new WorldObject(33079, 10, -1, 2345, 3181, 0), true);//
		// Runite ore
		// World.spawnObject(new WorldObject(33079, 10, -1, 2346, 3181, 0), true);//
		// Runite ore
		// World.spawnObject(new WorldObject(33079, 10, -1, 2347, 3181, 0), true);//
		// Runite ore
		// World.spawnObject(new WorldObject(2096, 10, -1, 2348, 3180, 0), true);// Coal
		// ore
		// World.spawnObject(new WorldObject(2096, 10, -1, 2348, 3179, 0), true);// Coal
		// ore
		// World.spawnObject(new WorldObject(2096, 10, -1, 2348, 3178, 0), true);// Coal
		// ore
		// World.spawnObject(new WorldObject(2096, 10, -1, 2348, 3177, 0), true);// Coal
		// ore
		// World.spawnObject(new WorldObject(2096, 10, -1, 2347, 3180, 0), true);// Coal
		// ore
		// World.spawnObject(new WorldObject(2096, 10, -1, 2347, 3179, 0), true);// Coal
		// ore
		// World.spawnObject(new WorldObject(2096, 10, -1, 2347, 3178, 0), true);// Coal
		// ore
		// World.spawnObject(new WorldObject(2096, 10, -1, 2347, 3177, 0), true);// Coal
		// ore
		// World.spawnObject(new WorldObject(37823, 10, 1, 2340, 3175, 0), true);//magic
		// tree
		// World.spawnObject(new WorldObject(37823, 10, -1, 2355, 3175, 0),
		// true);//magic tree
		// World.spawnObject(new WorldObject(37823, 10, 1, 2355, 3167, 0), true);//magic
		// tree
		// World.spawnObject(new WorldObject(37823, 10, -1, 2340, 3168, 0),
		// true);//magic tree
		// World.spawnObject(new WorldObject(37823, 10, 1, 2333, 3170, 0), true);//magic
		// tree
		// World.spawnObject(new WorldObject(37823, 10, -1, 2333, 3173, 0),
		// true);//magic tree
		// World.spawnObject(new WorldObject(37823, 10, -1, 2342, 3173, 0),
		// true);//magic tree
		// World.spawnObject(new WorldObject(37823, 10, 1, 2342, 3170, 0), true);//magic
		// tree
		// World.spawnObject(new WorldObject(1309, 10, 1, 2346, 3168, 0), true); //yew
		// tree
		// World.spawnObject(new WorldObject(1309, 10, 1, 2344, 3168, 0), true); //yew
		// tree
		// World.spawnObject(new WorldObject(1309, 10, 1, 2346, 3166, 0), true); //yew
		// tree
		// World.spawnObject(new WorldObject(1309, 10, 1, 2344, 3166, 0), true); //yew
		// tree
		// World.spawnObject(new WorldObject(409, 10, 1, 2349, 3173, 0), true); //normal
		// altar
		// World.spawnNPC(587, new WorldTile(2356, 3174, 0), 0, false,
		// EntityDirection.SOUTH); //HERB STORE
		// World.spawnNPC(805, new WorldTile(2356, 3169, 0), 0, false,
		// EntityDirection.EAST); //CRAFTING STORE
		// World.spawnNPC(8842, new WorldTile(2335, 3167, 0), 0, false,
		// EntityDirection.SOUTH); //rocktail
		// World.spawnNPC(8842, new WorldTile(2336, 3167, 0), 0, false,
		// EntityDirection.SOUTH); //rocktail
		// World.spawnNPC(8842, new WorldTile(2337, 3167, 0), 0, false,
		// EntityDirection.SOUTH); //rocktail
		// World.spawnNPC(8842, new WorldTile(2338, 3167, 0), 0, false,
		// EntityDirection.SOUTH); //rocktail
		// World.spawnNPC(313, new WorldTile(2340, 3176, 0), 0, false,
		// EntityDirection.SOUTH); //shark
		// World.spawnNPC(313, new WorldTile(2339, 3176, 0), 0, false,
		// EntityDirection.SOUTH); //shark
		// World.spawnNPC(313, new WorldTile(2338, 3176, 0), 0, false,
		// EntityDirection.SOUTH); //shark
		// World.spawnNPC(313, new WorldTile(2337, 3176, 0), 0, false,
		// EntityDirection.SOUTH); //shark
		// World.deleteObject(new WorldTile(2350, 3175, 0)); //junk
		// World.deleteObject(new WorldTile(2350, 3174, 0)); //junk
		// World.deleteObject(new WorldTile(2352, 3175, 0)); //junk
		// World.deleteObject(new WorldTile(2354, 3174, 0)); //junk
		// World.deleteObject(new WorldTile(2355, 3174, 0)); //junk
		// World.deleteObject(new WorldTile(2354, 3172, 0)); //junk
		// World.deleteObject(new WorldTile(2355, 3172, 0)); //junk
		// World.deleteObject(new WorldTile(2354, 3171, 0)); //junk
		// World.deleteObject(new WorldTile(2355, 3171, 0)); //junk
		// World.deleteObject(new WorldTile(2355, 3169, 0)); //junk
		// World.deleteObject(new WorldTile(2354, 3169, 0)); //junk
		// World.deleteObject(new WorldTile(2354, 3170, 0)); //junk
		// World.deleteObject(new WorldTile(2354, 3173, 0)); //junk
		// World.deleteObject(new WorldTile(2357, 3172, 0)); //junk
		// World.deleteObject(new WorldTile(2351, 3174, 0)); //junk
		// World.deleteObject(new WorldTile(2335, 3185, 0)); //junk
		// World.deleteObject(new WorldTile(2334, 3186, 0)); //junk
		// World.deleteObject(new WorldTile(2335, 3186, 0)); //junk
		//
		
		/**
		 * professor discovery area reward
		 * 
		 */
		World.spawnNPC(494, new WorldTile(2196, 3823, 0), 0, false, EntityDirection.NORTH); // bank
		World.spawnNPC(324, new WorldTile(2198, 3827, 0), 0, false, EntityDirection.NORTH); //fishing spot
		World.spawnObject(new WorldObject(16684, 10, -1, 2204, 3825, 0), true); //mining ess
		World.spawnObject(new WorldObject(3970, 10, 0, 2201, 3823, 0), true); // farming
		//World.spawnObject(new WorldObject(3970, 10, -1, 3230, 2636, 0), true); //farming
		World.spawnObject(new WorldObject(-1, 10, -1, 2195, 3821, 0), true); //block
		World.spawnObject(new WorldObject(-1, 10, -1, 2196, 3821, 0), true); //block
/**
 * 
 */
		World.spawnNPC(494, new WorldTile(2277, 3310, 1), 0, false, EntityDirection.NORTH); //sliske teleport
		World.spawnNPC(494, new WorldTile(2275, 3310, 1), 0, false, EntityDirection.NORTH); //sliske teleport
		World.spawnNPC(1419, new WorldTile(2276, 3310, 1), 0, false, EntityDirection.NORTH); //sliske teleport
		World.spawnNPC(3705, new WorldTile(2278, 3313, 1), 0, false, EntityDirection.WEST); //MAX

		/**
		 * Skiller island
		 */
		World.spawnObject(new WorldObject(2330, 10, 3, 3817, 3567, 0), true);
		World.spawnObject(new WorldObject(67968, 10, 0, 3809, 3571, 0), true);
		
		World.spawnObject(new WorldObject(9034, 10, 3, 3795, 3557, 0), true);
		World.spawnObject(new WorldObject(9034, 10, 3, 3795, 3552, 0), true);
		World.spawnObject(new WorldObject(9034, 10, 3, 3786, 3557, 0), true);
		World.spawnObject(new WorldObject(9034, 10, 3, 3792, 3557, 0), true);
		World.spawnObject(new WorldObject(9036, 10, 3, 3807, 3560, 0), true);
		World.spawnObject(new WorldObject(9036, 10, 3, 3807, 3559, 0), true);
		World.spawnObject(new WorldObject(9036, 10, 3, 3807, 3558, 0), true);
		World.spawnObject(new WorldObject(9036, 10, 3, 3807, 3557, 0), true);
		
		World.spawnNPC(323, new WorldTile(3800, 3561, 0), 0, false, EntityDirection.NORTH);
		/**
		 * Start Area - Tut island
		 */
		World.spawnObject(new WorldObject(1, 10, -1, 3098, 3107, 0), true); //Door stop
		
		World.spawnObject(new WorldObject(692, 10, -1, 4317, 5454, 0), true); //hetty tank
		
		
		//World.spawnNPC(14293, new WorldTile(3357, 3876, 0), 0, false, EntityDirection.NORTH); //sliske teleport
		

		World.spawnNPC(30064, new WorldTile(2740, 5086, 0), 0, false, EntityDirection.NORTH);
		
		
		//World.spawnNPC(30047, new WorldTile(2850, 3353, 0), 0, false, EntityDirection.SOUTH); //HETTY
		//World.spawnNPC(30047, new WorldTile(4317, 5455, 0), 0, false, EntityDirection.EAST); //HETTY
		
		/**
		 * 
		 * Drygon Vs Suns
		 * 
		 */
		
		World.spawnObject(new WorldObject(824, 10, -2, 2239, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2240, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2241, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2242, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2243, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2244, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2245, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2246, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2247, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2248, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2249, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2250, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2251, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2252, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2253, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2254, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2255, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2256, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2257, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2258, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2259, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2260, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2261, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2262, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2263, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2264, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2265, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2266, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2267, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2268, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2269, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2270, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2271, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2272, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2273, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2274, 3334, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -2, 2275, 3334, 0), true); //fence
		
		World.spawnObject(new WorldObject(824, 10, -1, 2275, 3333, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -1, 2275, 3332, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -1, 2275, 3331, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -1, 2275, 3330, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -1, 2275, 3329, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -1, 2275, 3328, 0), true); //fence
		//World.spawnObject(new WorldObject(824, 10, -1, 2275, 3327, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -1, 2275, 3326, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -1, 2275, 3325, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -1, 2275, 3324, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -1, 2275, 3323, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -1, 2275, 3322, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -1, 2275, 3321, 0), true); //fence
		
		World.spawnObject(new WorldObject(824, 10, 0, 2274, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, 0, 2273, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, 0, 2272, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2271, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2270, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2269, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2268, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2267, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2266, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2265, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2264, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2263, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2262, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2261, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2260, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2259, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2258, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2257, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2256, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2255, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2254, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2253, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2252, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2251, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2250, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2249, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2248, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2247, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2246, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2245, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2244, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2243, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2242, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2241, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2240, 3320, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -4, 2239, 3320, 0), true); //fence
		
		World.spawnObject(new WorldObject(824, 10, -3, 2239, 3321, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -3, 2239, 3322, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -3, 2239, 3323, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -3, 2239, 3324, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -3, 2239, 3325, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -3, 2239, 3326, 0), true); //fence
	//	World.spawnObject(new WorldObject(824, 10, -3, 2239, 3327, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -3, 2239, 3328, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -3, 2239, 3329, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -3, 2239, 3330, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -3, 2239, 3331, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -3, 2239, 3332, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -3, 2239, 3333, 0), true); //fence
		
		World.spawnObject(new WorldObject(36696, 10, 0, 2264, 3327, 0), true); //scenery
		World.spawnObject(new WorldObject(36696, 10, 0, 2260, 3330, 0), true); //scenery
		World.spawnObject(new WorldObject(36696, 10, 0, 2255, 3323, 0), true); //scenery
		
		World.spawnObject(new WorldObject(65648, 10, 0, 2255, 3327, 0), true); //scenery
		
		World.spawnObject(new WorldObject(824, 10, -3, 2276, 3328, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -3, 2276, 3326, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -3, 2277, 3327, 0), true); //fence
		
		World.spawnObject(new WorldObject(824, 10, -3, 2238, 3326, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -3, 2237, 3327, 0), true); //fence
		World.spawnObject(new WorldObject(824, 10, -3, 2238, 3328, 0), true); //fence
		
		World.spawnNPC(30060, new WorldTile(2275, 3327, 0), 0, false, EntityDirection.WEST); //commander drygon
		World.spawnNPC(30061, new WorldTile(2239, 3327, 0), 0, false, EntityDirection.EAST); //commander sun
		
		//asc dung
		//World.spawnNPC(17143, new WorldTile(2508, 2888, 0), 0, false, EntityDirection.SOUTH); //ocelus
		
		
		/**
		 * KK
		 */
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2781, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2782, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2783, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2784, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2785, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2786, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2787, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2788, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2789, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2790, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2791, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2792, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2793, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2794, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2795, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3348, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3349, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3350, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3351, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3352, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3353, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3354, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3355, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3356, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3357, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3358, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3359, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3360, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3361, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3362, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3363, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3364, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3365, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2781, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2782, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2783, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2784, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2785, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2786, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2787, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2788, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2789, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2790, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2791, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2792, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2793, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2794, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2795, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2796, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2797, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2798, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2799, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3365, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3364, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3363, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3362, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3361, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3360, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3359, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3358, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3357, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3356, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3355, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3354, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3353, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3352, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3351, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3350, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3349, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3348, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2799, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2798, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2797, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2796, 0), true);
		
		
		/**
		 * 
		 * Ingenuity Boss
		 * 
		 */
		World.spawnObject(new WorldObject(4028, 10, -1, 2588, 3918, 0), true); //mining ore
		World.spawnNPC(2722, new WorldTile(2596, 3908, 0), 0, false, EntityDirection.WEST); //fishing
		World.spawnObject(new WorldObject(2023, 10, -1, 2586, 3907, 0), true); //woodcutting tree
		World.spawnObject(new WorldObject(29095, 10, -1, 2588, 3912, 0), true); //firepit
		World.spawnObject(new WorldObject(13202, 10, -1, 2586, 3913, 0), true); //torch
		
		World.spawnObject(new WorldObject(3109, 10, -1, 2584, 3906, 0), true); //fire spawns
		World.spawnObject(new WorldObject(3109, 10, -1, 2584, 3907, 0), true); //fire spawns
		World.spawnObject(new WorldObject(3109, 10, -1, 2584, 3908, 0), true); //fire spawns
		World.spawnObject(new WorldObject(3109, 10, -1, 2584, 3909, 0), true); //fire spawns
		World.spawnObject(new WorldObject(3109, 10, -1, 2584, 3910, 0), true); //fire spawns
		World.spawnObject(new WorldObject(3109, 10, -1, 2584, 3911, 0), true); //fire spawns
		World.spawnObject(new WorldObject(3109, 10, -1, 2584, 3912, 0), true); //fire spawns
		World.spawnObject(new WorldObject(3109, 10, -1, 2584, 3913, 0), true); //fire spawns
		World.spawnObject(new WorldObject(3109, 10, -1, 2584, 3914, 0), true); //fire spawns
		World.spawnObject(new WorldObject(3109, 10, -1, 2584, 3915, 0), true); //fire spawns
		World.spawnObject(new WorldObject(3109, 10, -1, 2584, 3916, 0), true); //fire spawns
		World.spawnObject(new WorldObject(3109, 10, -1, 2584, 3917, 0), true); //fire spawns
		World.spawnObject(new WorldObject(3109, 10, -1, 2584, 3918, 0), true); //fire spawns
		World.spawnObject(new WorldObject(3109, 10, -1, 2584, 3919, 0), true); //fire spawns
		World.spawnObject(new WorldObject(3109, 10, -1, 2584, 3920, 0), true); //fire spawns
		
		/**
		 * 
		 * End of ingenuity
		 * 
		 */
		
		
		
		
		
		//castlewars
		
		World.spawnNPC(1526, new WorldTile(2442, 3092, 0), 0, false, EntityDirection.SOUTH);
		
		//spawnnpcs
		/*|| player.getX() == 2328 && player.getY() == 3666
|| player.getX() == 2330 && player.getY() == 3666
|| player.getX() == 2332 && player.getY() == 3666*/
		
		/*				else if (npc.getId() == 528)
					ShopsHandler.openShop(player, 1);
				else if (npc.getId() == 546)
					ShopsHandler.openShop(player, 3);
				else if (npc.getId() == 7950)
					ShopsHandler.openShop(player, 4);
				else if (npc.getId() == 550)
					ShopsHandler.openShop(player, 5);
				else if (npc.getId() == 519)
					ShopsHandler.openShop(player, 6);
				else if (npc.getId() == 549)
					ShopsHandler.openShop(player, 7);
				else if (npc.getId() == 8864)
					ShopsHandler.openShop(player, 8);
				else if (npc.getId() == 219)
					ShopsHandler.openShop(player, 13);
				else if (npc.getId() == 587)
					ShopsHandler.openShop(player, 14);
				else if (npc.getId() == 7954)
					ShopsHandler.openShop(player, 15);*/
		//|| player.getX() == 2327 && player.getY() == 3671
		//|| player.getX() == 2332 && player.getY() == 3671
		
		/**
		 * Party Demon
		 */
		
		//World.spawnObject(new WorldObject(2079, 10, -1, 3237, 9860, 0), true);
		
		
		/**
		 * hunger games
		 * 
		 */
		World.spawnObject(new WorldObject(1, 10, -1, 2346, 3884, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2347, 3884, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2348, 3884, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2349, 3884, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2350, 3884, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2351, 3884, 0), true);
		World.deleteObject(new WorldTile(2410, 3823, 0));
		World.deleteObject(new WorldTile(2421, 3823, 0));
		World.deleteObject(new WorldTile(2388, 3793, 0));
		World.deleteObject(new WorldTile(2388, 3804, 0));
		World.deleteObject(new WorldTile(2373, 3800, 0));
		
		
		/**
		 * Dungeoneering
		 */
		
		World.spawnNPC(1114, new WorldTile(2826, 10078, 0), 0, false, EntityDirection.EAST);
		
		/**
		 * Dryaxions dungeon
		 */
		World.spawnObject(new WorldObject(378, 10, -1, 2737, 9689, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2727, 9690, 0), true);
		World.spawnObject(new WorldObject(-1, 10, -1, 3561, 9754, 0), true);
		World.spawnObject(new WorldObject(-1, 10, -1, 3562, 9753, 0), true);
		World.spawnObject(new WorldObject(-1, 10, -1, 3555, 9748, 0), true);
		World.spawnObject(new WorldObject(-1, 10, -1, 3555, 9749, 0), true);
		World.spawnObject(new WorldObject(-1, 10, -1, 3555, 9750, 0), true);
		World.spawnObject(new WorldObject(-1, 10, -1, 3555, 9751, 0), true); //3554 9759 == dryax
		World.spawnObject(new WorldObject(-1, 10, -1, 3555, 9753, 0), true);
		World.spawnObject(new WorldObject(-1, 10, -1, 3556, 9753, 0), true);
		World.spawnObject(new WorldObject(-1, 10, -1, 3557, 9753, 0), true);
		World.spawnObject(new WorldObject(-1, 10, -1, 3558, 9754, 0), true);
		
		/**
		 * Kane's House
		 */
		World.spawnObject(new WorldObject(1, 10, -1, 2728, 3350, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2729, 3350, 0), true);
		World.spawnNPC(15584, new WorldTile(2728, 3349, 0), 0, false, EntityDirection.SOUTH);
		
		/**
		 * The Calamity
		 */
		
		World.spawnNPC(11551, new WorldTile(113, 4310, 0), 0, false, EntityDirection.EAST); //food
		World.spawnNPC(8329, new WorldTile(113, 4311, 0), 0, false, EntityDirection.EAST); //Potions
		World.spawnNPC(15465, new WorldTile(113, 4312, 0), 0, false, EntityDirection.EAST); //MeleeWeps
		World.spawnNPC(13285, new WorldTile(113, 4313, 0), 0, false, EntityDirection.EAST); //otherweps
		World.spawnNPC(12320, new WorldTile(113, 4314, 0), 0, false, EntityDirection.EAST); //MeleeArmour
		World.spawnNPC(6706, new WorldTile(113, 4315, 0), 0, false, EntityDirection.EAST); //otherarmour
		World.spawnNPC(1201, new WorldTile(113, 4316, 0), 0, false, EntityDirection.EAST); //bolts&runes
		World.spawnObject(new WorldObject(42031, 10, -1, 2833, 3859, 0), true); //portal to go in
		World.spawnObject(new WorldObject(30205, 10, 0, 3900, 3888, 0), true); //scoreboard
		World.spawnNPC(2725, new WorldTile(2836, 3862, 0), 0, false, EntityDirection.WEST); //Rewards Shop
		World.spawnNPC(494, new WorldTile(2829, 3862, 0), 0, false, EntityDirection.WEST); //Bank
		
		World.spawnObject(new WorldObject(1, 10, -1, 2829, 3851, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2828, 3851, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2827, 3851, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2826, 3851, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2825, 3851, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2825, 3850, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2825, 3849, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2825, 3848, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2825, 3847, 0), true);
		
		
		World.spawnObject(new WorldObject(1, 10, -1, 2830, 3852, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2830, 3851, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2830, 3850, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2830, 3849, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2830, 3848, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2830, 3847, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2830, 3846, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2830, 3845, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2826, 3859, 0), true);
		World.spawnObject(new WorldObject(1, 10, -1, 2824, 3862, 0), true);
		
//		/**
//		 * EASTER
//		 */
//
//		World.spawnNPC(9687, new WorldTile(2857, 2587, 0), 0, false, EntityDirection.NORTH);
//
//		World.spawnObject(new WorldObject(42650, 10, -1, 2857, 2586, 0), true);
		//World.spawnObject(new WorldObject(42651, 10, -1, 2589, 5595, 0), true);
		//World.spawnObject(new WorldObject(42652, 10, -1, 2593, 5596, 0), true);
		//World.spawnObject(new WorldObject(42652, 10, -1, 2592, 5596, 0), true);
		//World.spawnObject(new WorldObject(42651, 10, -1, 2593, 5581, 0), true);
		//World.spawnObject(new WorldObject(57376, 10, -1, 2595, 5585, 0), true);
		
		//World.spawnObject(new WorldObject(42650, 10, -1, 2766, 4640, 0), true);
		
		//World.spawnObject(new WorldObject(42652, 10, -1, 1823, 5090, 2), true);
		
		//event
		
		World.spawnNPC(13642, new WorldTile(1561, 4507, 0), 0, false, EntityDirection.WEST);
		World.spawnObject(new WorldObject(11435, 10, -1, 1629, 4521, 0), true);
		World.spawnObject(new WorldObject(11435, 10, -1, 1620, 4535, 0), true);
		World.spawnObject(new WorldObject(11435, 10, -1, 1632, 4507, 0), true);
		World.spawnObject(new WorldObject(11435, 10, -1, 1610, 4533, 0), true);
		
		//afterevent
		//World.spawnNPC(9687, new WorldTile(1560, 4505, 0), 0, false, EntityDirection.WEST);
		/**
		 * KK
		 */
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2781, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2782, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2783, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2784, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2785, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2786, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2787, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2788, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2789, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2790, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2791, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2792, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2793, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2794, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2795, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3348, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3349, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3350, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3351, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3352, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3353, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3354, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3355, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3356, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3357, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3358, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3359, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3360, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3361, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3362, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3363, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3364, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3365, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2780, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2781, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2782, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2783, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2784, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2785, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2786, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2787, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2788, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2789, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2790, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2791, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2792, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2793, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2794, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2795, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2796, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2797, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2798, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2799, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3366, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3365, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3364, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3363, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3362, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3361, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3360, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3359, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3358, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3357, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3356, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3355, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3354, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3353, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3352, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3351, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3350, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3349, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3348, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2800, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2799, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2798, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2797, 0), true);
		World.spawnObject(new WorldObject(21574, 10, -1, 3347, 2796, 0), true);
		
		World.spawnObject(new WorldObject(1309, 10, 1, 2753, 4631, 0), true); //yew tree dz
		World.spawnObject(new WorldObject(1309, 10, 1, 2753, 4633, 0), true); //yew tree dz
		World.spawnObject(new WorldObject(1309, 10, 1, 2753, 4635, 0), true); //yew tree dz
		World.spawnObject(new WorldObject(409, 10, 1, 2753, 4644, 0), true); // normal altar dz
		World.spawnObject(new WorldObject(28734, 10, 1, 2753, 4641, 0), true); // summoning oblis dz
		World.spawnNPC(9492, new WorldTile(2332, 10015, 0), 0, false, EntityDirection.SOUTH); // SUMM STORE
		//dz3
		World.spawnObject(new WorldObject(2465, 10, -3, 1829, 5089, 2), true); //normal altar
		World.spawnObject(new WorldObject(409, 10, -3, 1832, 5092, 2), true); //normal altar
		World.spawnObject(new WorldObject(30624, 10, -3, 1835, 5082, 2), true); //blood altar
		World.spawnObject(new WorldObject(70770, 10, 1, 1823, 5085, 2), true); //fire
		World.spawnNPC(7780, new WorldTile(1829, 5090, 2), 0, false, EntityDirection.SOUTH);
		
		//dz2
		World.spawnNPC(300, new WorldTile(1898, 5366, 2), 0, false, EntityDirection.SOUTH); // black market
		
		World.spawnObject(new WorldObject(1309, 10, 1, 1890, 5346, 2), true); //yew tree dz
		World.spawnObject(new WorldObject(1309, 10, 1, 1890, 5348, 2), true); //yew tree dz
		World.spawnObject(new WorldObject(1309, 10, 1, 1890, 5350, 2), true); //yew tree dz
		World.spawnObject(new WorldObject(1309, 10, 1, 1890, 5352, 2), true); //yew tree dz
		World.spawnObject(new WorldObject(1309, 10, 1, 1894, 5352, 2), true); //yew tree dz
		World.spawnObject(new WorldObject(1309, 10, 1, 1894, 5350, 2), true); //yew tree dz
		World.spawnObject(new WorldObject(45076, 10, 1, 1894, 5346, 2), true); //gold mineral deposit dz2
		
		World.spawnObject(new WorldObject(37823, 10, -1, 1890, 5358, 2), true);//magic tree
		World.spawnObject(new WorldObject(37823, 10, -1, 1890, 5360, 2), true);//magic tree
		World.spawnObject(new WorldObject(37823, 10, -1, 1890, 5362, 2), true);//magic tree
		World.spawnObject(new WorldObject(37823, 10, -1, 1890, 5364, 2), true);//magic tree
		World.spawnObject(new WorldObject(37823, 10, -1, 1894, 5364, 2), true);//magic tree
		World.spawnObject(new WorldObject(37823, 10, -1, 1894, 5362, 2), true);//magic tree
		World.spawnObject(new WorldObject(37823, 10, -1, 1894, 5360, 2), true);//magic tree
		World.spawnObject(new WorldObject(37823, 10, -1, 1894, 5358, 2), true);//magic tree
		
		World.spawnObject(new WorldObject(16604, 10, -1, 1902, 5355, 2), true);//dream tree
		
		World.spawnObject(new WorldObject(33079, 10, -1, 1910, 5365, 2), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 1910, 5364, 2), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 1910, 5363, 2), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 1910, 5362, 2), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 1910, 5361, 2), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 1910, 5360, 2), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 1910, 5359, 2), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 1910, 5358, 2), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 1910, 5357, 2), true);// Runite ore
		
		World.spawnObject(new WorldObject(2104, 10, -1, 1910, 5355, 2), true);//addy rock
		World.spawnObject(new WorldObject(2104, 10, -1, 1910, 5354, 2), true);//addy rock
		World.spawnObject(new WorldObject(2104, 10, -1, 1910, 5353, 2), true);//addy rock
		World.spawnObject(new WorldObject(2104, 10, -1, 1910, 5352, 2), true);//addy rock
		World.spawnObject(new WorldObject(2104, 10, -1, 1910, 5351, 2), true);//addy rock
		World.spawnObject(new WorldObject(2104, 10, -1, 1910, 5350, 2), true);//addy rock
		World.spawnObject(new WorldObject(2104, 10, -1, 1910, 5349, 2), true);//addy rock
		World.spawnObject(new WorldObject(2104, 10, -1, 1910, 5348, 2), true);//addy rock
		World.spawnObject(new WorldObject(2104, 10, -1, 1910, 5347, 2), true);//addy rock
		World.spawnObject(new WorldObject(2104, 10, -1, 1910, 5346, 2), true);//addy rock
		
		World.spawnObject(new WorldObject(33221, 10, -3, 1909, 5365, 2), true); //blurite ore
		World.spawnObject(new WorldObject(33221, 10, -3, 1908, 5365, 2), true); //blurite ore
		World.spawnObject(new WorldObject(33221, 10, -3, 1907, 5365, 2), true); //blurite ore
		World.spawnObject(new WorldObject(33221, 10, -3, 1906, 5365, 2), true); //blurite ore
		World.spawnObject(new WorldObject(33221, 10, -3, 1909, 5346, 2), true); //blurite ore
		World.spawnObject(new WorldObject(33221, 10, -3, 1908, 5346, 2), true); //blurite ore
		World.spawnObject(new WorldObject(33221, 10, -3, 1907, 5346, 2), true); //blurite ore
		World.spawnObject(new WorldObject(33221, 10, -3, 1906, 5346, 2), true); //blurite ore
		
		World.spawnObject(new WorldObject(2213, 10, -1, 1910, 5356, 2), true);// Bank
		World.spawnObject(new WorldObject(2213, 10, 0, 1899, 5366, 2), true);// Bank
		World.spawnObject(new WorldObject(2213, 10, -1, 1889, 5355, 2), true);// Bank
		World.spawnObject(new WorldObject(2213, 10, 0, 1900, 5345, 2), true);// Bank
		
		World.spawnObject(new WorldObject(409, 10, 0, 1901, 5345, 2), true);// altar
		World.spawnObject(new WorldObject(28734, 10, 1, 1901, 5365, 2), true); //summoning oblisk
		
		World.spawnNPC(8842, new WorldTile(1903, 5343, 2), 0, false, EntityDirection.WEST); //rocktail//30092  noted
		World.spawnNPC(8842, new WorldTile(1905, 5343, 2), 0, false, EntityDirection.WEST); //rocktail
		World.spawnNPC(8842, new WorldTile(1907, 5343, 2), 0, false, EntityDirection.WEST); //rocktail
		
		World.spawnNPC(8842, new WorldTile(1903, 5367, 2), 0, false, EntityDirection.WEST); //rocktail
		World.spawnNPC(8842, new WorldTile(1905, 5367, 2), 0, false, EntityDirection.WEST); //rocktail
		World.spawnNPC(8842, new WorldTile(1907, 5367, 2), 0, false, EntityDirection.WEST); //rocktail16604
		
		World.spawnNPC(528, new WorldTile(1898, 5266, 3), 0, false, EntityDirection.SOUTH); // shop hub
		
		//endof dz2
		
		// /**
		// * Xmas Stuff
		// */
		//
		// World.spawnObject(new WorldObject(66000, 10, -3, 2766, 4638, 0), true); //
		// small tree at dz
		// World.spawnObject(new WorldObject(66000, 10, -3, 1815, 5087, 2), true); //
		// small tree at dz3
		// World.spawnObject(new WorldObject(66000, 10, -3, 1910, 5366, 2), true); //
		// small tree at dz2
		// World.spawnObject(new WorldObject(66000, 10, -3, 1910, 5345, 2), true); //
		// small tree at dz2
		// World.spawnObject(new WorldObject(66000, 10, -3, 2594, 5576, 0), true); //
		// small tree at dz4
		// World.spawnObject(new WorldObject(66000, 10, -3, 3411, 5275, 0), true); //
		// small tree at dz5
		
		/**
		 * home
		 */

		// World.spawnObject(new WorldObject(65996, 10, -3, 2857, 2588, 0), true); //
		// main xmas tree at home
		// World.spawnObject(new WorldObject(-1, 10, -3, 2858, 2589, 0), true);
		// World.spawnObject(new WorldObject(47760, 10, -3, 2852, 2596, 0), true);
		// World.spawnObject(new WorldObject(47762, 10, -3, 2853, 2599, 0), true);
		// World.spawnObject(new WorldObject(41745, 10, 0, 2855, 2598, 0), true);
		// World.spawnNPC(8540, new WorldTile(2853, 2596, 0), 0, false,
		// EntityDirection.EAST); // santa
		//
		// World.spawnObject(new WorldObject(65819, 10, -3, 3188, 3401, 0), true);
		// World.spawnObject(new WorldObject(65819, 10, -3, 3055, 3362, 0), true);
		// World.spawnObject(new WorldObject(65819, 10, -3, 3048, 3235, 0), true);
		// World.spawnObject(new WorldObject(65819, 10, -3, 3220, 3248, 0), true);
		// World.spawnObject(new WorldObject(65819, 10, -3, 2151, 3868, 0), true);


		/**
		 * end of xmas stuff
		 */

		World.spawnNPC(4287, new WorldTile(2865, 2581, 0), 0, false, EntityDirection.SOUTH); // recycle manager

		World.spawnObject(new WorldObject(2587, 10, -3, 2766, 4639, 0), true);
		World.spawnObject(new WorldObject(4878, 10, -3, 2766, 4645, 0), true);
		
		World.spawnNPC(291, new WorldTile(2753, 4648, 0), 0, false, EntityDirection.WEST); //spec restore
		World.spawnNPC(1519, new WorldTile(2766, 4640, 0), 0, false, EntityDirection.WEST);
		
		/**
		 * VIP dz
		 */
		World.spawnObject(new WorldObject(409, 10, -3, 2589, 5590, 0), true); // altar
		World.spawnObject(new WorldObject(16604, 10, -3, 2591, 5585, 0), true); //dream tree
		World.spawnObject(new WorldObject(16604, 10, -3, 2591, 5582, 0), true); //dream tree
		World.spawnObject(new WorldObject(16604, 10, -3, 2591, 5579, 0), true); //dream tree
		World.spawnObject(new WorldObject(16604, 10, -3, 2596, 5585, 0), true); //dream tree
		World.spawnObject(new WorldObject(16604, 10, -3, 2596, 5582, 0), true); //dream tree
		World.spawnObject(new WorldObject(16604, 10, -3, 2596, 5579, 0), true); //dream tree
		World.spawnObject(new WorldObject(37823, 10, -1, 2582, 5581, 0), true);// magic tree
		World.spawnObject(new WorldObject(37823, 10, -1, 2582, 5582, 0), true);//magic tree
		World.spawnObject(new WorldObject(37823, 10, -1, 2582, 5584, 0), true);//magic tree
		World.spawnObject(new WorldObject(28734, 10, -1, 2577, 5581, 0), true);// magic tree
		World.spawnObject(new WorldObject(37823, 10, -1, 2582, 5583, 0), true);//magic tree
		World.spawnObject(new WorldObject(37823, 10, -1, 2582, 5585, 0), true);//magic tree
		
		World.spawnObject(new WorldObject(3272, 10, -3, 2604, 5590, 0), true); //boss chest
		
		World.spawnNPC(494, new WorldTile(2592, 5588, 0), 0, false, EntityDirection.SOUTH);
		World.spawnNPC(494, new WorldTile(2593, 5588, 0), 0, false, EntityDirection.SOUTH);
		World.spawnObject(new WorldObject(33221, 10, -3, 2588, 5585, 0), true); //blurite ore
		World.spawnObject(new WorldObject(33221, 10, -3, 2588, 5584, 0), true);
		World.spawnObject(new WorldObject(33221, 10, -3, 2588, 5583, 0), true);
		World.spawnObject(new WorldObject(33221, 10, -3, 2588, 5582, 0), true);
		World.spawnObject(new WorldObject(33221, 10, -3, 2588, 5581, 0), true);
		World.spawnObject(new WorldObject(33221, 10, -3, 2588, 5580, 0), true); //end of blurite ore
		World.spawnObject(new WorldObject(12809, 10, -1, 2592, 5593, 0), true); //furnace
		World.spawnObject(new WorldObject(33079, 10, -1, 2599, 5586, 0), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 2599, 5585, 0), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 2599, 5584, 0), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 2599, 5583, 0), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 2599, 5582, 0), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 2599, 5581, 0), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 2599, 5580, 0), true);// Runite ore
		World.spawnObject(new WorldObject(2104, 10, -1, 2601, 5580, 0), true);//addy rock
		World.spawnObject(new WorldObject(2104, 10, -1, 2601, 5581, 0), true);//addy rock
		World.spawnObject(new WorldObject(2104, 10, -1, 2601, 5582, 0), true);//addy rock
		World.spawnObject(new WorldObject(2104, 10, -1, 2601, 5583, 0), true);//addy rock
		World.spawnObject(new WorldObject(2104, 10, -1, 2601, 5584, 0), true);//addy rock
		World.spawnObject(new WorldObject(2104, 10, -1, 2601, 5585, 0), true);//addy rock
		World.spawnObject(new WorldObject(2104, 10, -1, 2601, 5586, 0), true);//addy rock
		World.spawnObject(new WorldObject(49036, 10, -1, 2595, 5595, 0), true);// Anvil
		World.spawnObject(new WorldObject(2213, 10, -1, 2593, 5576, 0), true);// Bank
		World.spawnObject(new WorldObject(2213, 10, -1, 2593, 5577, 0), true);// Bank
		World.spawnObject(new WorldObject(2728, 10, -1, 2590, 5576, 0), true);//cooking range
		
		
		/**
		 * End of VIP dz
		 **/
		
		/**
		 * 
		 * Legendary dz
		 */
		World.spawnNPC(494, new WorldTile(1840, 5087, 2), 0, false, EntityDirection.WEST);
		World.spawnNPC(494, new WorldTile(1840, 5088, 2), 0, false, EntityDirection.WEST);
		World.spawnObject(new WorldObject(33221, 10, -3, 1826, 5076, 2), true);
		World.spawnObject(new WorldObject(33221, 10, -3, 1825, 5076, 2), true);
		World.spawnObject(new WorldObject(33221, 10, -3, 1824, 5076, 2), true);
		World.spawnObject(new WorldObject(33221, 10, -3, 1823, 5076, 2), true);
		World.spawnObject(new WorldObject(33221, 10, -3, 1822, 5076, 2), true);
		World.spawnObject(new WorldObject(33221, 10, -3, 1821, 5076, 2), true);
		World.spawnObject(new WorldObject(33221, 10, -3, 1820, 5076, 2), true);
		World.spawnObject(new WorldObject(12809, 10, -1, 1821, 5087, 2), true);
		World.spawnObject(new WorldObject(33079, 10, -1, 1826, 5082, 2), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 1825, 5082, 2), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 1824, 5082, 2), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 1823, 5082, 2), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 1822, 5082, 2), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 1821, 5082, 2), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 1820, 5082, 2), true);// Runite ore
		
		World.spawnObject(new WorldObject(37823, 10, -1, 1812, 5084, 2), true);//magic tree
		World.spawnObject(new WorldObject(37823, 10, -1, 1812, 5086, 2), true);//magic tree
		World.spawnObject(new WorldObject(37823, 10, -1, 1812, 5088, 2), true);//magic tree
		World.spawnObject(new WorldObject(37823, 10, -1, 1812, 5090, 2), true);//magic tree
		
		World.spawnObject(new WorldObject(1309, 10, 1, 1820, 5098, 2), true); //yew tree
		World.spawnObject(new WorldObject(1309, 10, 1, 1822, 5098, 2), true); //yew tree
		World.spawnObject(new WorldObject(1309, 10, 1, 1824, 5098, 2), true); //yew tree
		World.spawnObject(new WorldObject(1309, 10, 1, 1826, 5098, 2), true); //yew tree
		
		World.spawnNPC(30092, new WorldTile(1827, 5089, 2), 0, false, EntityDirection.WEST);
		World.spawnNPC(30092, new WorldTile(1827, 5085, 2), 0, false, EntityDirection.WEST);
		
		World.spawnObject(new WorldObject(49036, 10, -1, 1824, 5085, 2), true);// Anvil
		World.spawnObject(new WorldObject(2213, 10, -1, 1822, 5086, 2), true);// Bank
		World.spawnObject(new WorldObject(2213, 10, -1, 1822, 5089, 2), true);// Bank
		World.spawnNPC(494, new WorldTile(1823, 5079, 2), 0, false, EntityDirection.WEST);
		World.spawnObject(new WorldObject(2728, 10, -1, 1823, 5093, 2), true);
		/**
		 * End of Legendary dz
		 **/
		
		World.spawnObject(new WorldObject(1, 10, -3, 3416, 3160, 2), true);
		World.spawnObject(new WorldObject(1, 10, -3, 3416, 3161, 2), true);
		World.spawnObject(new WorldObject(1, 10, -3, 3415, 3161, 2), true);
		World.spawnObject(new WorldObject(1, 10, -3, 3416, 3159, 2), true);
		
		
	//end
		//shops
		//other
		World.spawnNPC(4247, new WorldTile(2547, 3098, 0), 0, false, EntityDirection.SOUTH); //ESTATE AGENT
		
		
		//SZ
		World.spawnNPC(566, new WorldTile(3028, 2978, 0), 0, false, EntityDirection.EAST); //staff npc
	
	
		/**
		 * Hween
		 */
		if (Settings.HWEEN) {
			/**
			 * Home
			 */
			World.spawnObject(new WorldObject(62416, 10, 3, 1628, 3669, 0), true);//gravestone
			World.spawnObject(new WorldObject(32034, 10, 2, 1625, 3671, 0), true);//pumpkin
			World.spawnObject(new WorldObject(32034, 10, 2, 1626, 3671, 0), true);//pumpkin
			World.spawnObject(new WorldObject(32034, 10, 0, 1625, 3675, 0), true);//pumpkin
			World.spawnObject(new WorldObject(32034, 10, 0, 1626, 3675, 0), true);//pumpkin
			/**
			 * DZ1
			 */
			World.spawnObject(new WorldObject(32034, 10, 1, 2766, 4646, 0), true);//pumpkin
			World.spawnObject(new WorldObject(32034, 10, 1, 2766, 4638, 0), true);//pumpkin
			/**
			 * DZ2
			 */
			World.spawnObject(new WorldObject(32034, 10, 0, 1900, 5366, 2), true);//pumpkin
			World.spawnObject(new WorldObject(32034, 10, 2, 1903, 5345, 2), true);//pumpkin
			/**
			 * DZ3
			 */
			World.spawnObject(new WorldObject(32034, 10, 3, 1829, 5086, 2), true);//pumpkin
			World.spawnObject(new WorldObject(32034, 10, 3, 1829, 5089, 2), true);//pumpkin
			/**
			 * DZ4
			 */
			World.spawnObject(new WorldObject(32034, 10, 2, 2594, 5576, 0), true);//pumpkin
			World.spawnObject(new WorldObject(32034, 10, 0, 2594, 5587, 0), true);//pumpkin
			World.spawnObject(new WorldObject(32034, 10, 0, 2594, 5596, 0), true);//pumpkin
			World.spawnObject(new WorldObject(32034, 10, 0, 2591, 5596, 0), true);//pumpkin
			/**
			 * DZ5
			 */
			World.spawnObject(new WorldObject(32034, 10, 0, 3412, 5278, 0), true);//pumpkin
			World.spawnObject(new WorldObject(32034, 10, 0, 3408, 5275, 0), true);//pumpkin
		
		}

		
		//gamble zone
		World.spawnObject(new WorldObject(22819, 10, 1, 2854, 2572, 0), true); //bank booth

		//Building 1
		World.deleteObject(new WorldTile(2200, 3357, 1));
		World.deleteObject(new WorldTile(1637, 3683, 0));
		World.deleteObject(new WorldTile(1626, 3664, 0));
		World.deleteObject(new WorldTile(1631, 3661, 0));
		// World.spawnNPC(494, new WorldTile(2858, 2597, 0), 0, false,
		// EntityDirection.EAST); // BANKER
		//Building 2
		World.spawnObject(new WorldObject(409, 10, 0, 6256, 6250, 0), true); //normal altar
		//chests
		World.spawnObject(new WorldObject(13291, 10, 0, 6256, 6243, 0), true); //Magic chest
		World.spawnObject(new WorldObject(67551, 10, 0, 6257, 6244, 0), true); //rot6 - change
		World.spawnObject(new WorldObject(2588, 10, 0, 6255, 6255, 0), true);//crystal key chest
		

		
		World.deleteObject(new WorldTile(1633, 3662, 0));
		World.deleteObject(new WorldTile(1632, 3662, 0));
		World.deleteObject(new WorldTile(1631, 3662, 0));
		World.deleteObject(new WorldTile(1630, 3662, 0));
		World.deleteObject(new WorldTile(1629, 3662, 0));
		World.deleteObject(new WorldTile(1628, 3662, 0));
		World.deleteObject(new WorldTile(1635, 3662, 0));
		World.deleteObject(new WorldTile(1639, 3662, 0));
		World.deleteObject(new WorldTile(1626, 3681, 0));
		
		//other
		World.spawnObject(new WorldObject(43797, 10, 2, 2332, 3672, 0), true); //scoreboard
		World.spawnObject(new WorldObject(43797, 10, 1, 6666, 3123, 0), true); //scoreboard
		World.spawnObject(new WorldObject(-1, 10, 1, 1647, 3673, 0), true); //scoreboard
		World.spawnObject(new WorldObject(-1, 10, 1, 1632, 3686, 0, 0), true);
		World.spawnObject(new WorldObject(-1, 10, 1, 1631, 3686, 0, 0), true);
		World.spawnObject(new WorldObject(-1, 10, 1, 1630, 3686, 0, 0), true);
		World.spawnObject(new WorldObject(-1, 10, 1, 1630, 3660, 0, 0), true);
		World.spawnObject(new WorldObject(-1, 10, 1, 1629, 3660, 0, 0), true);
		World.spawnObject(new WorldObject(-1, 10, 1, 1628, 3660, 0, 0), true);
		World.spawnObject(new WorldObject(-1, 10, 1, 1627, 3660, 0, 0), true);
		// World.spawnNPC(30141, new WorldTile(2857, 2594, 0), 0, false,
		// EntityDirection.EAST); // vet
		World.spawnObject(new WorldObject(43096, 10, 2, 6666, 7777, 0), true); //Well of Fortune
		// World.spawnObject(new WorldObject(11354, 10, 1, 2837, 2576, 0), true); //TOG
		// PORTAL
		World.spawnNPC(3709, new WorldTile(5399, 3769, 0), 0, false, EntityDirection.WEST); // wilderness task

		//zark
	//	World.spawnNPC(9052, new WorldTile(2563, 9506, 0), 0, false, EntityDirection.NORTH);
		
		World.spawnNPC(30142, new WorldTile(1899, 5345, 2), 0, false, EntityDirection.NORTH); // donor shop npc
		World.spawnNPC(30142, new WorldTile(1829, 5085, 2), 0, false, EntityDirection.NORTH); // donor shop npc
		World.spawnNPC(30142, new WorldTile(2595, 5576, 0), 0, false, EntityDirection.NORTH); // donor shop npc
		
		//FISHING SPOTS HOME
		World.spawnNPC(327, new WorldTile(2337, 3703, 0), 0, false, false);
		World.spawnNPC(329, new WorldTile(2340, 3702, 0), 0, false, false);
		World.spawnNPC(312, new WorldTile(2344, 3702, 0), 0, false, false);
		//end
		/*|| player.getX() == 3292 && player.getY() == 3298
|| player.getX() == 3301 && player.getY() == 3298

*/World.spawnObject(new WorldObject(1, 10, -3, 2638, 10424, 0), true);
		World.spawnObject(new WorldObject(1, 10, -3, 2413, 3521, 0), true);
		World.spawnObject(new WorldObject(1, 10, -3, 2427, 3521, 0), true);
		World.spawnObject(new WorldObject(1, 10, -3, 2426, 3521, 0), true);
		World.spawnObject(new WorldObject(1, 10, -3, 2720, 9774, 0), true);
		World.spawnObject(new WorldObject(1, 10, -3, 2720, 9775, 0), true);
		
	/**
	 * Crystal tree
	 */
		World.spawnObject(new WorldObject(87538, 10, -3, 2740, 3458, 0), true);
		World.spawnObject(new WorldObject(87538, 10, -3, 2740, 3459, 0), true);
		World.spawnObject(new WorldObject(87538, 10, -3, 2744, 3458, 0), true);
		World.spawnObject(new WorldObject(87538, 10, -3, 2744, 3459, 0), true);
		World.spawnObject(new WorldObject(87538, 10, -3, 2742, 3460, 0), true);
/**
 * Elder tree
 */
		World.spawnObject(new WorldObject(87508, 10, -3, 2750, 3458, 0), true);
		
		//vorago
		World.spawnObject(new WorldObject(1, 10, -3, 1968, 3243, 0), true);
		World.spawnObject(new WorldObject(1, 10, -3, 1967, 3243, 0), true);
		World.spawnObject(new WorldObject(1, 10, -3, 1966, 3243, 0), true);
		World.spawnObject(new WorldObject(1, 10, -3, 1807, 3219, 0), true);
		

		// World.spawnNPC(9032, new WorldTile(3177, 3947, 0), 0, false,
		// EntityDirection.NORTH);
		World.spawnNPC(501, new WorldTile(3184, 3927, 0), 0, false, EntityDirection.NORTH);
		// World.spawnNPC(510, new WorldTile(3178, 3945, 0), 0, false,
		// EntityDirection.EAST);
		// World.spawnNPC(510, new WorldTile(3072, 3867, 0), 0, false,
		// EntityDirection.EAST);
		World.spawnObject(new WorldObject(33221, 10, -3, 3183, 3927, 0), true);
		World.spawnObject(new WorldObject(33221, 10, -3, 3183, 3926, 0), true);
		World.spawnObject(new WorldObject(33221, 10, -3, 3183, 3925, 0), true);
		World.spawnObject(new WorldObject(33221, 10, -3, 3182, 3923, 0), true);
		World.spawnObject(new WorldObject(33221, 10, -3, 3180, 3924, 0), true);
		World.spawnObject(new WorldObject(33221, 10, -3, 3180, 3926, 0), true);
		World.spawnObject(new WorldObject(33221, 10, -3, 3181, 3927, 0), true);
		World.spawnObject(new WorldObject(37823, 10, -1, 3195, 3921, 0), true);// mage tree
		World.spawnObject(new WorldObject(37823, 10, -1, 3195, 3923, 0), true);// mage tree
		World.spawnObject(new WorldObject(37823, 10, -1, 3195, 3925, 0), true);// mage tree
		World.spawnObject(new WorldObject(37823, 10, -1, 3195, 3927, 0), true);// mage tree
		World.spawnObject(new WorldObject(33079, 10, -1, 3188, 3929, 0), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 3188, 3926, 0), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 3188, 3923, 0), true);// Runite ore
		World.spawnObject(new WorldObject(33079, 10, -1, 3188, 3920, 0), true);// Runite ore
		World.spawnObject(new WorldObject(2104, 10, -1, 3188, 3928, 0), true);// addy rock
		World.spawnObject(new WorldObject(2104, 10, -1, 3188, 3925, 0), true);// addy rock
		World.spawnObject(new WorldObject(2104, 10, -1, 3188, 3922, 0), true);// addy rock
		World.spawnObject(new WorldObject(1307, 10, -1, 3193, 3929, 0), true);// maple tree
		World.spawnObject(new WorldObject(1307, 10, -1, 3191, 3929, 0), true);// maple tree
		World.spawnObject(new WorldObject(1307, 10, -1, 3193, 3921, 0), true);// maple tree
		World.spawnObject(new WorldObject(1307, 10, -1, 3191, 3921, 0), true);// maple tree

		World.spawnNPC(8842, new WorldTile(3192, 3925, 0), 0, false, EntityDirection.SOUTH); // rocktail
		World.spawnNPC(8842, new WorldTile(3192, 3926, 0), 0, false, EntityDirection.SOUTH); // rocktail
		/**
		 * End of wildy skilling
		 */
		
		World.spawnObject(new WorldObject(1, 10, -3, 2635, 5068, 0), true);
		World.spawnObject(new WorldObject(1, 10, -3, 2635, 5069, 0), true);
		World.spawnObject(new WorldObject(1, 10, -3, 2635, 5070, 0), true);
		World.spawnObject(new WorldObject(1, 10, -3, 2635, 5071, 0), true);
		World.spawnObject(new WorldObject(1, 10, -3, 2635, 5072, 0), true);
		
		
		
		
		//dz
		World.spawnObject(new WorldObject(5999, 10, 1, 2770, 4641, 0), true); //coal mineral deposit dz1
		World.spawnNPC(30142, new WorldTile(2767, 4648, 0), 0, false, EntityDirection.SOUTH); // MRS XP - DONATOR ZONE!!
		World.spawnNPC(6183, new WorldTile(2765, 4648, 0), 0, false, EntityDirection.SOUTH); //JOOPZ - DZ!
		World.spawnNPC(959, new WorldTile(2766, 4640, 0), 0, false, EntityDirection.WEST); // healer
		World.spawnObject(new WorldObject(37823, 10, -3, 2762, 4635, 0), true);//start of trees
		World.spawnObject(new WorldObject(37823, 10, -3, 2762, 4633, 0), true);
		World.spawnObject(new WorldObject(37823, 10, -3, 2762, 4631, 0), true);
		World.spawnObject(new WorldObject(37823, 10, -3, 2759, 4631, 0), true);
		World.spawnObject(new WorldObject(37823, 10, -3, 2759, 4633, 0), true);
		World.spawnObject(new WorldObject(37823, 10, -3, 2759, 4635, 0), true);
		World.spawnObject(new WorldObject(1307, 10, -1, 2765, 4635, 0), true);
		World.spawnObject(new WorldObject(1307, 10, -1, 2765, 4633, 0), true);
		World.spawnObject(new WorldObject(1307, 10, -1, 2765, 4631, 0), true);
		World.spawnObject(new WorldObject(1307, 10, -1, 2755, 4631, 0), true);
		World.spawnObject(new WorldObject(1307, 10, -1, 2755, 4633, 0), true);
		World.spawnObject(new WorldObject(1307, 10, -1, 2755, 4635, 0), true); //end of trees
		World.spawnObject(new WorldObject(33079, 10, -1, 2755, 4641, 0), true);// start of mining
		World.spawnObject(new WorldObject(33079, 10, -1, 2755, 4642, 0), true);
		World.spawnObject(new WorldObject(33079, 10, -1, 2755, 4643, 0), true);
		World.spawnObject(new WorldObject(33079, 10, -1, 2755, 4644, 0), true);
		World.spawnObject(new WorldObject(2104, 10, -1, 2757, 4641, 0), true);
		World.spawnObject(new WorldObject(2104, 10, -1, 2757, 4642, 0), true);
		World.spawnObject(new WorldObject(2104, 10, -1, 2757, 4643, 0), true);
		World.spawnObject(new WorldObject(2104, 10, -1, 2757, 4644, 0), true);
		World.spawnObject(new WorldObject(11942, 10, -1, 2759, 4641, 0), true);
		World.spawnObject(new WorldObject(11942, 10, -1, 2759, 4642, 0), true);
		World.spawnObject(new WorldObject(11942, 10, -1, 2759, 4643, 0), true);
		World.spawnObject(new WorldObject(11942, 10, -1, 2759, 4644, 0), true);
		World.spawnObject(new WorldObject(11930, 10, -1, 2761, 4641, 0), true);
		World.spawnObject(new WorldObject(11930, 10, -1, 2761, 4642, 0), true);
		World.spawnObject(new WorldObject(11930, 10, -1, 2761, 4643, 0), true);
		World.spawnObject(new WorldObject(11930, 10, -1, 2761, 4644, 0), true);
		World.spawnObject(new WorldObject(11932, 10, -1, 2763, 4641, 0), true);
		World.spawnObject(new WorldObject(11932, 10, -1, 2763, 4642, 0), true);
		World.spawnObject(new WorldObject(11932, 10, -1, 2763, 4643, 0), true);
		World.spawnObject(new WorldObject(11932, 10, -1, 2763, 4644, 0), true);//end of mining
		World.spawnNPC(8842, new WorldTile(2763, 4628, 0), 0, false, false); //start of fishing
		World.spawnNPC(8842, new WorldTile(2759, 4628, 0), 0, false, false); //end of fishing
		World.spawnObject(new WorldObject(2728, 10, -2, 2766, 4643, 0), true);
		World.spawnObject(new WorldObject(2213, 10, -1, 2766, 4642, 0), true);
		World.spawnObject(new WorldObject(2213, 10, -1, 2766, 4641, 0), true);
		World.spawnNPC(556, new WorldTile(2755, 4648, 0), 0, false, EntityDirection.SOUTH);
		World.spawnNPC(813, new WorldTile(2757, 4648, 0), 0, false, EntityDirection.SOUTH);
		World.spawnNPC(2996, new WorldTile(2759, 4648, 0), 0, false, EntityDirection.SOUTH);
		World.spawnNPC(9085, new WorldTile(2761, 4648, 0), 0, false, EntityDirection.SOUTH);
		World.spawnNPC(3705, new WorldTile(2763, 4648, 0), 0, false, EntityDirection.SOUTH);
		World.spawnNPC(5445, new WorldTile(2756, 4639, 0), 0, false, EntityDirection.SOUTH);

		World.deleteObject(new WorldTile(3415, 3161, 0));
		World.deleteObject(new WorldTile(3415, 3160, 0));
		
		World.deleteObject(new WorldTile(2600, 5605, 0));
		World.deleteObject(new WorldTile(2599, 5605, 0));
		World.deleteObject(new WorldTile(2598, 5605, 0));
		World.deleteObject(new WorldTile(2599, 5604, 0));
		World.deleteObject(new WorldTile(3616, 3335, 0));
		World.deleteObject(new WorldTile(3616, 3339, 0));
		World.deleteObject(new WorldTile(3616, 3344, 0));
		World.deleteObject(new WorldTile(3616, 3348, 0));
		World.deleteObject(new WorldTile(3623, 3348, 0));
		World.deleteObject(new WorldTile(3623, 3344, 0));
		World.deleteObject(new WorldTile(3623, 3339, 0));
		World.deleteObject(new WorldTile(3623, 3335, 0));
		World.deleteObject(new WorldTile(3621, 3349, 0));
		World.deleteObject(new WorldTile(3618, 3349, 0));
		World.deleteObject(new WorldTile(3614, 3344, 0));
		World.deleteObject(new WorldTile(3614, 3339, 0));
		World.deleteObject(new WorldTile(3618, 3334, 0));
		World.deleteObject(new WorldTile(3621, 3334, 0));
		World.deleteObject(new WorldTile(3625, 3340, 0));
		World.deleteObject(new WorldTile(3625, 3343, 0));
		
		//farming
		World.spawnObject(new WorldObject(18819, 10, -3, 2264, 3291, 0), true);
		World.spawnObject(new WorldObject(18820, 10, -3, 2262, 3291, 0), true);
		World.spawnObject(new WorldObject(18821, 10, -3, 2260, 3291, 0), true);
		
		//glacor fixes
		World.spawnObject(new WorldObject(42774, 10, -2, 4205, 5722, 0), true);
		World.spawnObject(new WorldObject(42774, 10, -2, 4213, 5722, 0), true);
		World.spawnObject(new WorldObject(42774, 10, -2, 4203, 5722, 0), true);
		World.spawnObject(new WorldObject(42774, 10, -2, 4202, 5722, 0), true);
		World.spawnObject(new WorldObject(42774, 10, -2, 4201, 5723, 0), true);
		World.spawnObject(new WorldObject(42774, 10, -2, 4201, 5722, 0), true);
		World.spawnObject(new WorldObject(42774, 10, -2, 4200, 5714, 0), true);
		World.spawnObject(new WorldObject(42774, 10, -2, 4200, 5712, 0), true);
		World.spawnObject(new WorldObject(42774, 10, -2, 4200, 5710, 0), true);
		//Troz
		World.spawnObject(new WorldObject(2800, 10, -2, 2747, 3186, 0), true);
		World.spawnObject(new WorldObject(2802, 10, -2, 2746, 3186, 0), true);
		World.spawnObject(new WorldObject(2802, 10, -2, 2742, 3191, 0), true);
		World.spawnObject(new WorldObject(2802, 10, -2, 2741, 3184, 0), true);
		
		//Smithing
		World.spawnObject(new WorldObject(12809, 10, -3, 1757, 5291, 1), true);
		
		World.spawnObject(new WorldObject(24534, 10, -1, 1757, 5293, 1), true); // coal storage
		
		//Dryaxions
		World.spawnObject(new WorldObject(11195, 10, -2, 3191, 9819, 0), true);
		World.spawnObject(new WorldObject(11195, 10, -2, 3189, 9819, 0), true);
		World.spawnObject(new WorldObject(11195, 10, -2, 3189, 9821, 0), true);
		World.spawnObject(new WorldObject(11195, 10, -2, 3191, 9824, 0), true);
		World.spawnObject(new WorldObject(11195, 10, -2, 3195, 9824, 0), true);
		World.spawnObject(new WorldObject(11195, 10, -2, 3192, 9820, 0), true);
		
		//TheRiseOfZark
//		World.spawnObject(new WorldObject(16043, 10, 0, 3240, 9800, 0), false);
//		World.spawnObject(new WorldObject(16044, 10, 0, 3240, 9799, 0), false);
//		World.spawnNPC(589, new WorldTile(3444, 3676, 0), 0, false, false);
//		World.spawnNPC(4731, new WorldTile(2743, 3470, 0), 0, false, false);
//

		
		// // thieving - home
		// World.spawnObject(new WorldObject(4874, 10, -2, 2857, 2600, 0), true);
		// World.spawnObject(new WorldObject(4875, 10, -2, 2857, 2599, 0), true);
		// World.spawnObject(new WorldObject(4876, 10, -2, 2857, 2598, 0), true);
		// World.spawnObject(new WorldObject(4877, 10, -2, 2857, 2597, 0), true);
		// World.spawnObject(new WorldObject(4878, 10, -2, 2858, 2596, 0), true);

		//Construction
		World.spawnObject(new WorldObject(15482, 10, 2, 2990, 3302, 0), true);//construction teleport
		World.spawnNPC(5865, new WorldTile(2541, 3098, 0), 0, false, EntityDirection.SOUTH);
		
		//Mining
		World.spawnObject(new WorldObject(2213, 10, -2, 3292, 3298, 0), true);
		World.spawnObject(new WorldObject(2213, 10, -2, 3293, 3298, 0), true);
		World.spawnObject(new WorldObject(2213, 10, -2, 3294, 3298, 0), true);
		World.spawnObject(new WorldObject(2213, 10, -2, 3295, 3298, 0), true);
		World.spawnObject(new WorldObject(2213, 10, -2, 3296, 3298, 0), true);
		World.spawnObject(new WorldObject(2213, 10, -2, 3297, 3298, 0), true);
		//World.spawnObject(new WorldObject(2213, 10, -2, 3298, 3298, 0), true);
		World.spawnObject(new WorldObject(2213, 10, -2, 3299, 3298, 0), true);
		World.spawnObject(new WorldObject(2213, 10, -2, 3300, 3298, 0), true);
		World.spawnObject(new WorldObject(2213, 10, -2, 3301, 3298, 0), true);
		//World.spawnObject(new WorldObject(724, 10, -1, 2333, 3692, 0), true);//dz stuff
		//World.spawnObject(new WorldObject(2213, 10, -1, 2328, 3686, 0), true);//dz stuff
		World.deleteObject(new WorldTile(2852, 5088, 0));
		
		World.deleteObject(new WorldTile(2848, 5088, 0));
		World.deleteObject(new WorldTile(2603, 5587, 0));
		World.deleteObject(new WorldTile(2860, 5085, 0));
		World.deleteObject(new WorldTile(2862, 5085, 0));
		World.deleteObject(new WorldTile(2863, 5088, 0));
		World.deleteObject(new WorldTile(2856, 5089, 0));
		World.deleteObject(new WorldTile(2857, 5087, 0));
		World.deleteObject(new WorldTile(2858, 5086, 0));
		World.deleteObject(new WorldTile(2857, 5086, 0));
		World.deleteObject(new WorldTile(2857, 5088, 0));
		World.deleteObject(new WorldTile(2856, 5090, 0));
		World.deleteObject(new WorldTile(2856, 5092, 0));
		World.deleteObject(new WorldTile(2857, 5093, 0));
		World.deleteObject(new WorldTile(2858, 5093, 0));
		World.deleteObject(new WorldTile(2859, 5092, 0));
		World.deleteObject(new WorldTile(2858, 5091, 0));
		World.deleteObject(new WorldTile(2863, 5090, 0));
		World.deleteObject(new WorldTile(2863, 5089, 0));
		World.deleteObject(new WorldTile(2864, 5089, 0));
		World.deleteObject(new WorldTile(2863, 5085, 0));
		World.deleteObject(new WorldTile(2857, 5092, 0));
		World.deleteObject(new WorldTile(2864, 5086, 0));
		World.deleteObject(new WorldTile(2858, 5085, 0));
		
	//	World.deleteObject(new WorldTile(2846, 5088, 0));
		World.deleteObject(new WorldTile(2131, 5539, 3));
		World.deleteObject(new WorldTile(2332, 3691, 0));
		World.deleteObject(new WorldTile(2332, 3692, 0));
		World.deleteObject(new WorldTile(2332, 3693, 0));
		World.deleteObject(new WorldTile(2332, 3689, 0));
		World.deleteObject(new WorldTile(2327, 3686, 0));
		//World.deleteObject(new WorldTile(2327, 3687, 0));
		World.deleteObject(new WorldTile(2329, 3686, 0));
		World.deleteObject(new WorldTile(2327, 3688, 0));
		World.deleteObject(new WorldTile(2327, 3689, 0));
		World.deleteObject(new WorldTile(2327, 3690, 0));
		World.deleteObject(new WorldTile(2327, 3691, 0));
		World.deleteObject(new WorldTile(2332, 3686, 0));
		//World.deleteObject(new WorldTile(2332, 3687, 0));
		World.deleteObject(new WorldTile(2331, 3692, 0));
		World.deleteObject(new WorldTile(2331, 3693, 0));
		World.deleteObject(new WorldTile(2330, 3692, 0));
		World.deleteObject(new WorldTile(2330, 3693, 0));
		World.deleteObject(new WorldTile(2329, 3693, 0));
		//World.deleteObject(new WorldTile(2333, 3686, 0));
		//World.deleteObject(new WorldTile(2333, 3692, 0));
		World.deleteObject(new WorldTile(2333, 3693, 0));
		World.deleteObject(new WorldTile(2338, 3690, 0));
		World.deleteObject(new WorldTile(2338, 3689, 0));
		World.deleteObject(new WorldTile(2339, 3690, 0));
		World.deleteObject(new WorldTile(2339, 3689, 0));
		World.deleteObject(new WorldTile(2339, 3688, 0));
		World.deleteObject(new WorldTile(2340, 3691, 0));
		World.deleteObject(new WorldTile(2340, 3690, 0));
		World.deleteObject(new WorldTile(2340, 3689, 0));
		World.deleteObject(new WorldTile(2340, 3688, 0));
		World.deleteObject(new WorldTile(2341, 3692, 0));
		World.deleteObject(new WorldTile(2341, 3690, 0));
		World.deleteObject(new WorldTile(2341, 3689, 0));
		World.deleteObject(new WorldTile(2341, 3688, 0));
		World.deleteObject(new WorldTile(2342, 3690, 0));
		//World.deleteObject(new WorldTile(2342, 3689, 0));
		World.deleteObject(new WorldTile(2344, 3688, 0));
		World.deleteObject(new WorldTile(2336, 3691, 0));
		
		//END
		
		
		/*|| player.getX() == 2712 && player.getY() == 9750
|| player.getX() == 2721 && player.getY() == 9751
|| player.getX() == 2724 && player.getY() == 9755
|| player.getX() == 2719 && player.getY() == 9757
|| player.getX() == 2721 && player.getY() == 9742
|| player.getX() == 2711 && player.getY() == 9735
|| player.getX() == 2704 && player.getY() == 9737
|| player.getX() == 2704 && player.getY() == 9740
|| player.getX() == 2704 && player.getY() == 9743
|| player.getX() == 2704 && player.getY() == 9746
|| player.getX() == 2704 && player.getY() == 9749
|| player.getX() == 2704 && player.getY() == 9753*/
		//Dungeoneering
	}

	public static void spawnObjects() {
	}
	
	

	/**
	 * The NPC classes.
	 */
	private static final Map<Integer, Class<?>> CUSTOM_NPCS = new HashMap<Integer, Class<?>>();

	public static void npcSpawn() {
		int size = 0;
		boolean ignore = false;
		try {
			for (String string : FileUtilities
					.readFile("data/npcs/npcspawns.txt")) {
				if (string.startsWith("//") || string.equals("")) {
					continue;
				}
				if (string.contains("/*")) {
					ignore = true;
					continue;
				}
				if (ignore) {
					if (string.contains("*/")) {
						ignore = false;
					}
					continue;
				}
				String[] spawn = string.split(" ");
				@SuppressWarnings("unused")
				int id = Integer.parseInt(spawn[0]), x = Integer
						.parseInt(spawn[1]), y = Integer.parseInt(spawn[2]), z = Integer
						.parseInt(spawn[3]), faceDir = Integer
						.parseInt(spawn[4]);
				NPC npc = null;
				Class<?> npcHandler = CUSTOM_NPCS.get(id);
				if (npcHandler == null) {
					npc = new NPC(id, new WorldTile(x, y, z), -1, true, false);
				} else {
					npc = (NPC) npcHandler.getConstructor(int.class)
							.newInstance(id);
				}
				if (npc != null) {
					WorldTile spawnLoc = new WorldTile(x, y, z);
					npc.setLocation(spawnLoc);
					World.spawnNPC(npc.getId(), spawnLoc, -1, true, false);
					size++;
				}
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		}
		System.err.println("Loaded " + size + " custom npc spawns!");
	}

}