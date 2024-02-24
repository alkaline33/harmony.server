package com.rs.game.player.bot.definition;

import com.rs.game.item.Item;
import com.rs.game.player.Equipment;
import com.rs.game.player.bot.Bot;

/**
 * Created by Valkyr on 21/05/2016.
 */
public enum EquipmentDefinition {
    // Melee Equipment
    TORSO_DDEF_WHIP(new int[]{Equipment.SLOT_HAT, 10828},
            new int[]{Equipment.SLOT_AMULET, 6585},
            new int[]{Equipment.SLOT_CHEST, 10551},
            new int[]{Equipment.SLOT_LEGS, 4087},
            new int[]{Equipment.SLOT_FEET, 11732},
            new int[]{Equipment.SLOT_CAPE, 23659},
            new int[]{Equipment.SLOT_RING, 6737},
            new int[]{Equipment.SLOT_WEAPON, 4151},
            new int[]{Equipment.SLOT_SHIELD, 20072}
    ),
    TORSO_DDEF_CRAP(new int[]{Equipment.SLOT_HAT, 10828},
            new int[]{Equipment.SLOT_AMULET, 1704},
            new int[]{Equipment.SLOT_CHEST, 10551},
            new int[]{Equipment.SLOT_LEGS, 4087},
            new int[]{Equipment.SLOT_FEET, 11732},
            new int[]{Equipment.SLOT_CAPE, 23659},
            new int[]{Equipment.SLOT_RING, 6737},
            new int[]{Equipment.SLOT_WEAPON, 18349},
            new int[]{Equipment.SLOT_SHIELD, 20072}
    ),
    VESTA_DFS(new int[]{Equipment.SLOT_HAT, 13896},
            new int[]{Equipment.SLOT_AMULET, 1704},
            new int[]{Equipment.SLOT_CHEST, 13887},
            new int[]{Equipment.SLOT_LEGS, 13893},
            new int[]{Equipment.SLOT_FEET, 11728},
            new int[]{Equipment.SLOT_CAPE, 6570},
            new int[]{Equipment.SLOT_RING, 6737},
            new int[]{Equipment.SLOT_WEAPON, 4151},
            new int[]{Equipment.SLOT_SHIELD, 20072}
    ),
    DHAROK_DFS(new int[]{Equipment.SLOT_HAT, 4716},
            new int[]{Equipment.SLOT_AMULET, 1704},
            new int[]{Equipment.SLOT_CHEST, 4720},
            new int[]{Equipment.SLOT_LEGS, 4722},
            new int[]{Equipment.SLOT_FEET, 11728},
            new int[]{Equipment.SLOT_CAPE, 6570},
            new int[]{Equipment.SLOT_RING, 6737},
            new int[]{Equipment.SLOT_WEAPON, 4151},
            new int[]{Equipment.SLOT_SHIELD, 6524}
    ),
    DHAROK_DEFENDER(new int[]{Equipment.SLOT_HAT, 4716},
            new int[]{Equipment.SLOT_AMULET, 1704},
            new int[]{Equipment.SLOT_CHEST, 4720},
            new int[]{Equipment.SLOT_LEGS, 4722},
            new int[]{Equipment.SLOT_FEET, 11728},
            new int[]{Equipment.SLOT_CAPE, 6570},
            new int[]{Equipment.SLOT_RING, 6737},
            new int[]{Equipment.SLOT_WEAPON, 4151},
            new int[]{Equipment.SLOT_SHIELD, 20072}
    ),
    RUNE_DFS(new int[]{Equipment.SLOT_HAT, 3751},
            new int[]{Equipment.SLOT_AMULET, 1704},
            new int[]{Equipment.SLOT_CHEST, 1127},
            new int[]{Equipment.SLOT_LEGS, 1079},
            new int[]{Equipment.SLOT_FEET, 4131},
            new int[]{Equipment.SLOT_CAPE, 1052},
            new int[]{Equipment.SLOT_RING, 2550},
            new int[]{Equipment.SLOT_WEAPON, 4587},
            new int[]{Equipment.SLOT_SHIELD, 8850}
    ),
    RUNE_DEFENDER(new int[]{Equipment.SLOT_HAT, 3751},
            new int[]{Equipment.SLOT_AMULET, 1704},
            new int[]{Equipment.SLOT_CHEST, 1127},
            new int[]{Equipment.SLOT_LEGS, 1079},
            new int[]{Equipment.SLOT_FEET, 4131},
            new int[]{Equipment.SLOT_CAPE, 1052},
            new int[]{Equipment.SLOT_RING, 2550},
            new int[]{Equipment.SLOT_WEAPON, 4151},
            new int[]{Equipment.SLOT_SHIELD, 8850}
    ),
    C_VESTA_DEFENDER(new int[]{Equipment.SLOT_HAT, 3751},
            new int[]{Equipment.SLOT_AMULET, 1704},
            new int[]{Equipment.SLOT_CHEST, 13911},
            new int[]{Equipment.SLOT_LEGS, 13917},
            new int[]{Equipment.SLOT_FEET, 4131},
            new int[]{Equipment.SLOT_CAPE, 1052},
            new int[]{Equipment.SLOT_RING, 6737},
            new int[]{Equipment.SLOT_WEAPON, 4587},
            new int[]{Equipment.SLOT_SHIELD, 8850}
    ),

    //Range Equipment
    DHIDE_ZAMBOOK_CCB(new int[]{Equipment.SLOT_HAT, 10828},
            new int[]{Equipment.SLOT_AMULET, 6585},
            new int[]{Equipment.SLOT_CHEST, 2503},
            new int[]{Equipment.SLOT_LEGS, 2497},
            new int[]{Equipment.SLOT_FEET, 11732},
            new int[]{Equipment.SLOT_CAPE, 29855},
            new int[]{Equipment.SLOT_RING, 6737},
            new int[]{Equipment.SLOT_WEAPON, 18357},
            new int[]{Equipment.SLOT_SHIELD, 3842},
            new int[]{Equipment.SLOT_ARROWS, 9244, 100}

    ),
    DHAROK_RANGE_TANK(new int[]{Equipment.SLOT_HAT, 4716},
            new int[]{Equipment.SLOT_AMULET, 1704},
            new int[]{Equipment.SLOT_CHEST, 2503},
            new int[]{Equipment.SLOT_LEGS, 4722},
            new int[]{Equipment.SLOT_FEET, 11728},
            new int[]{Equipment.SLOT_CAPE, 10499},
            new int[]{Equipment.SLOT_RING, 6733},
            new int[]{Equipment.SLOT_WEAPON, 9185},
            new int[]{Equipment.SLOT_ARROWS, 9244, 100},
            new int[]{Equipment.SLOT_SHIELD, 11283}
    ),
    VESTA_RANGE_TANK(new int[]{Equipment.SLOT_HAT, 13896},
            new int[]{Equipment.SLOT_AMULET, 1704},
            new int[]{Equipment.SLOT_CHEST, 4736},
            new int[]{Equipment.SLOT_LEGS, 13893},
            new int[]{Equipment.SLOT_FEET, 11728},
            new int[]{Equipment.SLOT_CAPE, 10499},
            new int[]{Equipment.SLOT_RING, 6733},
            new int[]{Equipment.SLOT_WEAPON, 9185},
            new int[]{Equipment.SLOT_ARROWS, 9244, 100},
            new int[]{Equipment.SLOT_SHIELD, 11283}
    ),
    KHARIL_RANGER(new int[]{Equipment.SLOT_HAT, 4732},
            new int[]{Equipment.SLOT_AMULET, 1704},
            new int[]{Equipment.SLOT_CHEST, 4736},
            new int[]{Equipment.SLOT_LEGS, 4738},
            new int[]{Equipment.SLOT_FEET, 3105},
            new int[]{Equipment.SLOT_CAPE, 10499},
            new int[]{Equipment.SLOT_RING, 6733},
            new int[]{Equipment.SLOT_WEAPON, 861},
            new int[]{Equipment.SLOT_ARROWS, 892, 100},
            new int[]{Equipment.SLOT_SHIELD, -1}
    ),
    KHARIL_RANGER_CBOW_DFS(new int[]{Equipment.SLOT_HAT, 4732},
            new int[]{Equipment.SLOT_AMULET, 6585},
            new int[]{Equipment.SLOT_CHEST, 4736},
            new int[]{Equipment.SLOT_LEGS, 4738},
            new int[]{Equipment.SLOT_FEET, 3105},
            new int[]{Equipment.SLOT_CAPE, 10499},
            new int[]{Equipment.SLOT_RING, 6733},
            new int[]{Equipment.SLOT_WEAPON, 9185},
            new int[]{Equipment.SLOT_ARROWS, 9144, 100},
            new int[]{Equipment.SLOT_SHIELD, 11283}
    ),
    BLACK_DHIDE_RANGER(new int[]{Equipment.SLOT_HAT, 3749},
            new int[]{Equipment.SLOT_AMULET, 1704},
            new int[]{Equipment.SLOT_CHEST, 2503},
            new int[]{Equipment.SLOT_LEGS, 2497},
            new int[]{Equipment.SLOT_FEET, 3105},
            new int[]{Equipment.SLOT_CAPE, 10499},
            new int[]{Equipment.SLOT_RING, 2550},
            new int[]{Equipment.SLOT_WEAPON, 861},
            new int[]{Equipment.SLOT_ARROWS, 892, 100},
            new int[]{Equipment.SLOT_SHIELD, -1}
    ),
    BLACK_DHIDE_RANGER_KNIVES(new int[]{Equipment.SLOT_HAT, 3749},
            new int[]{Equipment.SLOT_AMULET, 1704},
            new int[]{Equipment.SLOT_CHEST, 2503},
            new int[]{Equipment.SLOT_LEGS, 2497},
            new int[]{Equipment.SLOT_FEET, 3105},
            new int[]{Equipment.SLOT_CAPE, 10499},
            new int[]{Equipment.SLOT_RING, 2550},
            new int[]{Equipment.SLOT_WEAPON, 868, 100},
            new int[]{Equipment.SLOT_ARROWS, -1},
            new int[]{Equipment.SLOT_SHIELD, -1}
    ),

    // Mage Equipment
    MYSTIC_BOOK_AS(new int[]{Equipment.SLOT_HAT, 10828},
            new int[]{Equipment.SLOT_AMULET, 6585},
            new int[]{Equipment.SLOT_CHEST, 4091},
            new int[]{Equipment.SLOT_LEGS, 4093},
            new int[]{Equipment.SLOT_FEET, 11732},
            new int[]{Equipment.SLOT_CAPE, 29856},
            new int[]{Equipment.SLOT_RING, 6737},
            new int[]{Equipment.SLOT_WEAPON, 4675},
            new int[]{Equipment.SLOT_SHIELD, 3842}
    ),
    VIRTUS_DIVINE_POLYPORE(new int[]{Equipment.SLOT_HAT, 20159},
            new int[]{Equipment.SLOT_AMULET, 6585},
            new int[]{Equipment.SLOT_CHEST, 20163},
            new int[]{Equipment.SLOT_LEGS, 20167},
            new int[]{Equipment.SLOT_FEET, 21793},
            new int[]{Equipment.SLOT_CAPE, 23659},
            new int[]{Equipment.SLOT_RING, 2550},
            new int[]{Equipment.SLOT_WEAPON, 22494},
            new int[]{Equipment.SLOT_SHIELD, 13740}
    ),
    ZURIEL_DFS_SOL(new int[]{Equipment.SLOT_HAT, 13864},
            new int[]{Equipment.SLOT_AMULET, 6585},
            new int[]{Equipment.SLOT_CHEST, 13858},
            new int[]{Equipment.SLOT_LEGS, 13861},
            new int[]{Equipment.SLOT_FEET, 24984},
            new int[]{Equipment.SLOT_CAPE, 23659},
            new int[]{Equipment.SLOT_RING, 2550},
            new int[]{Equipment.SLOT_WEAPON, 15486},
            new int[]{Equipment.SLOT_SHIELD, 11283}
    ),
    ZURIEL_DFS_POLYPORE(new int[]{Equipment.SLOT_HAT, 13864},
            new int[]{Equipment.SLOT_AMULET, 6585},
            new int[]{Equipment.SLOT_CHEST, 13858},
            new int[]{Equipment.SLOT_LEGS, 13861},
            new int[]{Equipment.SLOT_FEET, 24984},
            new int[]{Equipment.SLOT_CAPE, 23659},
            new int[]{Equipment.SLOT_RING, 2550},
            new int[]{Equipment.SLOT_WEAPON, 22494},
            new int[]{Equipment.SLOT_SHIELD, 11283}
    ),
    AHRIM_BOOK_SOL(new int[]{Equipment.SLOT_HAT, 4708},
            new int[]{Equipment.SLOT_AMULET, 6585},
            new int[]{Equipment.SLOT_CHEST, 4712},
            new int[]{Equipment.SLOT_LEGS, 4714},
            new int[]{Equipment.SLOT_FEET, 6920},
            new int[]{Equipment.SLOT_CAPE, 1052},
            new int[]{Equipment.SLOT_RING, 2550},
            new int[]{Equipment.SLOT_WEAPON, 15486},
            new int[]{Equipment.SLOT_SHIELD, 3842}
    ),
    AHRIM_BOOK_POLYPORE(new int[]{Equipment.SLOT_HAT, 4708},
            new int[]{Equipment.SLOT_AMULET, 6585},
            new int[]{Equipment.SLOT_CHEST, 4712},
            new int[]{Equipment.SLOT_LEGS, 4714},
            new int[]{Equipment.SLOT_FEET, 6920},
            new int[]{Equipment.SLOT_CAPE, 1052},
            new int[]{Equipment.SLOT_RING, 2550},
            new int[]{Equipment.SLOT_WEAPON, 22494},
            new int[]{Equipment.SLOT_SHIELD, 3842}
    ),
    ENCHANTED_BOOK_OLYPORE(new int[]{Equipment.SLOT_HAT, 7400},
            new int[]{Equipment.SLOT_AMULET, 6585},
            new int[]{Equipment.SLOT_CHEST, 7399},
            new int[]{Equipment.SLOT_LEGS, 7398},
            new int[]{Equipment.SLOT_FEET, 6920},
            new int[]{Equipment.SLOT_CAPE, 1052},
            new int[]{Equipment.SLOT_RING, 2550},
            new int[]{Equipment.SLOT_WEAPON, 22494},
            new int[]{Equipment.SLOT_SHIELD, 3842}
    ),
    ENCHANTED_BOOK_ANCIENT_STAFF(new int[]{Equipment.SLOT_HAT, 7400},
            new int[]{Equipment.SLOT_AMULET, 6585},
            new int[]{Equipment.SLOT_CHEST, 7399},
            new int[]{Equipment.SLOT_LEGS, 7398},
            new int[]{Equipment.SLOT_FEET, 6920},
            new int[]{Equipment.SLOT_CAPE, 1052},
            new int[]{Equipment.SLOT_RING, 2550},
            new int[]{Equipment.SLOT_WEAPON, 4675},
            new int[]{Equipment.SLOT_SHIELD, 3842}
    ),
    INFINITY_BOOK_ANCIENT_STAFF(new int[]{Equipment.SLOT_HAT, 6918},
            new int[]{Equipment.SLOT_AMULET, 6585},
            new int[]{Equipment.SLOT_CHEST, 6916},
            new int[]{Equipment.SLOT_LEGS, 6924},
            new int[]{Equipment.SLOT_FEET, 6920},
            new int[]{Equipment.SLOT_CAPE, 1052},
            new int[]{Equipment.SLOT_RING, 2550},
            new int[]{Equipment.SLOT_WEAPON, 4675},
            new int[]{Equipment.SLOT_SHIELD, 3842}
    ),
    // Skiller Equipment
    WOODCUTTER(new int[]{Equipment.SLOT_HAT, 10933},
            new int[]{Equipment.SLOT_CHEST, 10939},
            new int[]{Equipment.SLOT_LEGS, 6924},
            new int[]{Equipment.SLOT_FEET, 10940}
    ),;
    private final int[][] equipment;

    EquipmentDefinition(int[]... equipment) {
        this.equipment = equipment;
    }

    public void apply(Bot bot) {
        for (int[] item : equipment) {
            int slot = item[0];
            int itemId = item[1];
            int quantity = item.length > 2 ? item[2] : 1;
            bot.getEquipment().getItems().set(slot, new Item(itemId, quantity));
            bot.getEquipment().refresh(slot);
        }
        bot.getAppearence().generateAppearenceData();
    }
}
