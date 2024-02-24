package com.rs.game.player.bot.definition;


import com.rs.game.player.Skills;
import com.rs.game.player.bot.Bot;

/**
 * Created by Valkyr on 21/05/2016.
 */
public enum SkillsDefinition {
    // Skillers
    LOW_WOODCUTTER(new int[]{Skills.WOODCUTTING, 30}),
    MED_WOODCUTTER(new int[]{Skills.WOODCUTTING, 60}),
    HIGH_WOODCUTTER(new int[]{Skills.WOODCUTTING, 90}),
    LOW_FISHER(new int[]{Skills.FISHING, 30}),
    MED_FISHER(new int[]{Skills.FISHING, 60}),
    HIGH_FISHER(new int[]{Skills.FISHING, 90}),
    LOW_MINER(new int[]{Skills.MINING, 30}),
    MED_MINER(new int[]{Skills.MINING, 60}),
    HIGH_MINER(new int[]{Skills.MINING, 90}),
    // Meleers
    LOW_MAIN(new int[]{Skills.HITPOINTS, 50},
            new int[]{Skills.ATTACK, 40},
            new int[]{Skills.STRENGTH, 60},
            new int[]{Skills.DEFENCE, 50},
            new int[]{Skills.MAGIC, 40},
            new int[]{Skills.RANGE, 40},
            new int[]{Skills.PRAYER, 43},
            new int[]{Skills.SUMMONING, 1}
    ),
    MED_MAIN(new int[]{Skills.HITPOINTS, 70},
            new int[]{Skills.ATTACK, 70},
            new int[]{Skills.STRENGTH, 80},
            new int[]{Skills.DEFENCE, 75},
            new int[]{Skills.MAGIC, 60},
            new int[]{Skills.RANGE, 60},
            new int[]{Skills.PRAYER, 70},
            new int[]{Skills.SUMMONING, 60}
    ),
    MAX_MAIN(new int[]{Skills.HITPOINTS, 99},
            new int[]{Skills.ATTACK, 99},
            new int[]{Skills.STRENGTH, 99},
            new int[]{Skills.DEFENCE, 99},
            new int[]{Skills.MAGIC, 99},
            new int[]{Skills.RANGE, 99},
            new int[]{Skills.PRAYER, 99},
            new int[]{Skills.SUMMONING, 99}
    ),
    PURE_ZERKER(new int[]{Skills.HITPOINTS, 70},
            new int[]{Skills.ATTACK, 70},
            new int[]{Skills.STRENGTH, 70},
            new int[]{Skills.DEFENCE, 45},
            new int[]{Skills.MAGIC, 1},
            new int[]{Skills.RANGE, 1},
            new int[]{Skills.PRAYER, 70},
            new int[]{Skills.SUMMONING, 1}
    ),
    MAX_ZERKER(new int[]{Skills.HITPOINTS, 80},
            new int[]{Skills.ATTACK, 70},
            new int[]{Skills.STRENGTH, 99},
            new int[]{Skills.DEFENCE, 45},
            new int[]{Skills.MAGIC, 94},
            new int[]{Skills.RANGE, 1},
            new int[]{Skills.PRAYER, 95},
            new int[]{Skills.SUMMONING, 1}
    ),
    // Rangers
    PURE_RANGER(new int[]{Skills.HITPOINTS, 60},
            new int[]{Skills.ATTACK, 1},
            new int[]{Skills.STRENGTH, 1},
            new int[]{Skills.DEFENCE, 1},
            new int[]{Skills.MAGIC, 1},
            new int[]{Skills.RANGE, 99},
            new int[]{Skills.PRAYER, 70},
            new int[]{Skills.SUMMONING, 1}
    ),
    TANK_RANGER(new int[]{Skills.HITPOINTS, 99},
            new int[]{Skills.ATTACK, 60},
            new int[]{Skills.STRENGTH, 70},
            new int[]{Skills.DEFENCE, 80},
            new int[]{Skills.MAGIC, 99},
            new int[]{Skills.RANGE, 99},
            new int[]{Skills.PRAYER, 95},
            new int[]{Skills.SUMMONING, 1}
    ),
    MAX_PURE_RANGER(new int[]{Skills.HITPOINTS, 99},
            new int[]{Skills.ATTACK, 1},
            new int[]{Skills.STRENGTH, 1},
            new int[]{Skills.DEFENCE, 1},
            new int[]{Skills.MAGIC, 1},
            new int[]{Skills.RANGE, 99},
            new int[]{Skills.PRAYER, 95},
            new int[]{Skills.SUMMONING, 1}
    ),
    // Mages
    MED_MAGE(new int[]{Skills.HITPOINTS, 70},
            new int[]{Skills.ATTACK, 70},
            new int[]{Skills.STRENGTH, 70},
            new int[]{Skills.DEFENCE, 60},
            new int[]{Skills.MAGIC, 99},
            new int[]{Skills.RANGE, 1},
            new int[]{Skills.PRAYER, 70},
            new int[]{Skills.SUMMONING, 1}
    ),
    HIGH_MAGE(new int[]{Skills.HITPOINTS, 70},
            new int[]{Skills.ATTACK, 70},
            new int[]{Skills.STRENGTH, 99},
            new int[]{Skills.DEFENCE, 80},
            new int[]{Skills.MAGIC, 99},
            new int[]{Skills.RANGE, 1},
            new int[]{Skills.PRAYER, 95},
            new int[]{Skills.SUMMONING, 1}
    ),
    ;

    private final int[][] levels;

    SkillsDefinition(int[]... levels) {
        this.levels = levels;
    }

    public void apply(Bot bot) {
        for (int[] level : levels) {
            int skillId = level[0];
            int skillLevel = level[1];
            int skillXp = Skills.getXPForLevel(skillLevel);
            bot.getSkills().setXp(skillId, skillXp);
            bot.getSkills().set(skillId, skillLevel);
        }
    }
}
