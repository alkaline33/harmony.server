package com.rs.game.player.bot.definition;

import com.rs.game.WorldTile;
import com.rs.game.player.bot.Bot;
import com.rs.game.player.bot.behaviour.Behaviour;

/**
 * Created by Valkyr on 21/05/2016.
 */
public enum BotDefinition {
    // Melee PKers
    MAX_MELEE(BehaviourDefinition.PKER, SkillsDefinition.MAX_MAIN, new EquipmentDefinition[]{EquipmentDefinition.TORSO_DDEF_WHIP, EquipmentDefinition.TORSO_DDEF_CRAP,EquipmentDefinition.RUNE_DFS},
            new ItemsDefinition[]{ItemsDefinition.OVERLOAD, ItemsDefinition.SUPER_RESTORE_FLASK, ItemsDefinition.VARROCK_TAB, ItemsDefinition.ROCKTAILS},
            "Max Melee", new Object[][]{
            {MetadataDefinition.PRAYERS, new PrayersDefinition[]{PrayersDefinition.CURSES_PROTECT_ITEM, PrayersDefinition.CURSES_BERSERKER, PrayersDefinition.CURSES_TURMOIL}},
            {MetadataDefinition.SPEC_WEAPON, SpecialWeaponDefinition.KORASI},
            {MetadataDefinition.MAGIC_SPELL, MagicDefinition.VENGEANCE},
    }),
    DHAROKER(BehaviourDefinition.DHAROKER, SkillsDefinition.MAX_MAIN, new EquipmentDefinition[]{EquipmentDefinition.DHAROK_DEFENDER, EquipmentDefinition.DHAROK_DFS},
            new ItemsDefinition[]{ItemsDefinition.OVERLOAD, ItemsDefinition.SUPER_RESTORE_FLASK, ItemsDefinition.VARROCK_TAB, ItemsDefinition.ROCKTAILS},
            "Dharoker", new Object[][]{
            {MetadataDefinition.PRAYERS, new PrayersDefinition[]{PrayersDefinition.CURSES_PROTECT_ITEM, PrayersDefinition.CURSES_BERSERKER, PrayersDefinition.CURSES_WRATH}},
            {MetadataDefinition.SPEC_WEAPON, SpecialWeaponDefinition.DHAROKS_AXE},
            {MetadataDefinition.MAGIC_SPELL, MagicDefinition.VENGEANCE},
    }),
    MED_MELEE(BehaviourDefinition.PKER, SkillsDefinition.MED_MAIN, new EquipmentDefinition[]{EquipmentDefinition.DHAROK_DFS, EquipmentDefinition.RUNE_DFS},
            new ItemsDefinition[]{ItemsDefinition.OVERLOAD, ItemsDefinition.SUPER_RESTORE_FLASK, ItemsDefinition.VARROCK_TAB, ItemsDefinition.ROCKTAILS},
            "Med Melee", new Object[][]{
            {MetadataDefinition.PRAYERS, new PrayersDefinition[]{PrayersDefinition.REGULAR_PROTECT_ITEM, PrayersDefinition.REGULAR_SMITE, PrayersDefinition.REGULAR_PIETY}},
            {MetadataDefinition.SPEC_WEAPON, SpecialWeaponDefinition.DDS},
    }),
    PURE_ZERK(BehaviourDefinition.PKER, SkillsDefinition.MAX_ZERKER, new EquipmentDefinition[]{EquipmentDefinition.RUNE_DEFENDER},
            new ItemsDefinition[]{ItemsDefinition.OVERLOAD, ItemsDefinition.SUPER_RESTORE_FLASK, ItemsDefinition.VARROCK_TAB, ItemsDefinition.ROCKTAILS},
            "Zerker Pure", new Object[][]{
            {MetadataDefinition.PRAYERS, new PrayersDefinition[]{PrayersDefinition.REGULAR_PROTECT_ITEM, PrayersDefinition.REGULAR_SMITE, PrayersDefinition.REGULAR_PIETY}},
            {MetadataDefinition.SPEC_WEAPON, SpecialWeaponDefinition.DDS},
            {MetadataDefinition.MAGIC_SPELL, MagicDefinition.VENGEANCE},
    }),
    MAXED_ZERK(BehaviourDefinition.PKER, SkillsDefinition.MAX_ZERKER, new EquipmentDefinition[]{EquipmentDefinition.RUNE_DEFENDER, EquipmentDefinition.C_VESTA_DEFENDER},
            new ItemsDefinition[]{ItemsDefinition.OVERLOAD, ItemsDefinition.SUPER_RESTORE_FLASK, ItemsDefinition.VARROCK_TAB, ItemsDefinition.ROCKTAILS},
            "Zerker Pure", new Object[][]{
            {MetadataDefinition.PRAYERS, new PrayersDefinition[]{PrayersDefinition.CURSES_PROTECT_ITEM, PrayersDefinition.CURSES_BERSERKER, PrayersDefinition.CURSES_TURMOIL}},
            {MetadataDefinition.SPEC_WEAPON, SpecialWeaponDefinition.DDS},
            {MetadataDefinition.MAGIC_SPELL, MagicDefinition.VENGEANCE},
    }),
    // Range PKers
    MAX_RANGER(BehaviourDefinition.PKER, SkillsDefinition.MAX_PURE_RANGER, new EquipmentDefinition[]{EquipmentDefinition.BLACK_DHIDE_RANGER},
            new ItemsDefinition[]{ItemsDefinition.OVERLOAD, ItemsDefinition.SUPER_RESTORE_FLASK, ItemsDefinition.VARROCK_TAB, ItemsDefinition.ROCKTAILS},
            "Max Ranger", new Object[][]{
            {MetadataDefinition.PRAYERS, new PrayersDefinition[]{PrayersDefinition.CURSES_PROTECT_ITEM, PrayersDefinition.CURSES_BERSERKER, PrayersDefinition.CURSES_TURMOIL}},
            {MetadataDefinition.SPEC_WEAPON, SpecialWeaponDefinition.DARK_BOW},
    }),
    TANK_RANGER(BehaviourDefinition.PKER, SkillsDefinition.TANK_RANGER, new EquipmentDefinition[]{EquipmentDefinition.DHAROK_RANGE_TANK, EquipmentDefinition.VESTA_RANGE_TANK},
            new ItemsDefinition[]{ItemsDefinition.OVERLOAD, ItemsDefinition.SUPER_RESTORE_FLASK, ItemsDefinition.VARROCK_TAB, ItemsDefinition.ROCKTAILS},
            "Tank Ranger", new Object[][]{
            {MetadataDefinition.PRAYERS, new PrayersDefinition[]{PrayersDefinition.CURSES_PROTECT_ITEM, PrayersDefinition.CURSES_BERSERKER, PrayersDefinition.CURSES_SOUL_SPLIT, PrayersDefinition.CURSES_TURMOIL}},
            {MetadataDefinition.SPEC_WEAPON, SpecialWeaponDefinition.DDS},
            {MetadataDefinition.MAGIC_SPELL, MagicDefinition.VENGEANCE},
    }),
    PURE_RANGER(BehaviourDefinition.PKER, SkillsDefinition.MAX_PURE_RANGER, new EquipmentDefinition[]{EquipmentDefinition.BLACK_DHIDE_RANGER},
            new ItemsDefinition[]{ItemsDefinition.OVERLOAD, ItemsDefinition.SUPER_RESTORE_FLASK, ItemsDefinition.VARROCK_TAB, ItemsDefinition.ROCKTAILS},
            "Pure Ranger", new Object[][]{
            {MetadataDefinition.PRAYERS, new PrayersDefinition[]{PrayersDefinition.REGULAR_PROTECT_ITEM, PrayersDefinition.REGULAR_SMITE, PrayersDefinition.REGULAR_RIGOUR}},
            {MetadataDefinition.SPEC_WEAPON, SpecialWeaponDefinition.DARK_BOW},
    }),
    // Mage PKers
    MAX_MAGE(BehaviourDefinition.PKER, SkillsDefinition.MAX_MAIN, new EquipmentDefinition[]{EquipmentDefinition.MYSTIC_BOOK_AS, EquipmentDefinition.VIRTUS_DIVINE_POLYPORE, EquipmentDefinition.ZURIEL_DFS_SOL, EquipmentDefinition.ZURIEL_DFS_POLYPORE, EquipmentDefinition.AHRIM_BOOK_SOL, EquipmentDefinition.AHRIM_BOOK_POLYPORE, EquipmentDefinition.ENCHANTED_BOOK_ANCIENT_STAFF, EquipmentDefinition.ENCHANTED_BOOK_OLYPORE, EquipmentDefinition.INFINITY_BOOK_ANCIENT_STAFF},
            new ItemsDefinition[]{ItemsDefinition.OVERLOAD, ItemsDefinition.SUPER_RESTORE_FLASK, ItemsDefinition.VARROCK_TAB, ItemsDefinition.ROCKTAILS},
            "Max Mage", new Object[][]{
            {MetadataDefinition.PRAYERS, new PrayersDefinition[]{PrayersDefinition.CURSES_PROTECT_ITEM, PrayersDefinition.CURSES_BERSERKER, PrayersDefinition.CURSES_TURMOIL}},
            {MetadataDefinition.SPEC_WEAPON, SpecialWeaponDefinition.DDS},
            {MetadataDefinition.MAGIC_SPELL, MagicDefinition.ICE_BARRAGE},
    }),
    HIGH_MAGE(BehaviourDefinition.PKER, SkillsDefinition.HIGH_MAGE, new EquipmentDefinition[]{EquipmentDefinition.MYSTIC_BOOK_AS, EquipmentDefinition.VIRTUS_DIVINE_POLYPORE, EquipmentDefinition.ZURIEL_DFS_SOL, EquipmentDefinition.ZURIEL_DFS_POLYPORE, EquipmentDefinition.AHRIM_BOOK_SOL, EquipmentDefinition.AHRIM_BOOK_POLYPORE, EquipmentDefinition.ENCHANTED_BOOK_ANCIENT_STAFF, EquipmentDefinition.ENCHANTED_BOOK_OLYPORE, EquipmentDefinition.INFINITY_BOOK_ANCIENT_STAFF},
            new ItemsDefinition[]{ItemsDefinition.OVERLOAD, ItemsDefinition.SUPER_RESTORE_FLASK, ItemsDefinition.VARROCK_TAB, ItemsDefinition.ROCKTAILS},
            "Max Mage", new Object[][]{
            {MetadataDefinition.PRAYERS, new PrayersDefinition[]{PrayersDefinition.CURSES_PROTECT_ITEM, PrayersDefinition.CURSES_BERSERKER, PrayersDefinition.CURSES_TURMOIL}},
            {MetadataDefinition.SPEC_WEAPON, SpecialWeaponDefinition.DDS},
            {MetadataDefinition.MAGIC_SPELL, MagicDefinition.ICE_BARRAGE},
    }),
    MED_MAGE(BehaviourDefinition.PKER, SkillsDefinition.MED_MAGE, new EquipmentDefinition[]{EquipmentDefinition.AHRIM_BOOK_SOL, EquipmentDefinition.AHRIM_BOOK_POLYPORE, EquipmentDefinition.ENCHANTED_BOOK_ANCIENT_STAFF, EquipmentDefinition.ENCHANTED_BOOK_OLYPORE, EquipmentDefinition.INFINITY_BOOK_ANCIENT_STAFF},
            new ItemsDefinition[]{ItemsDefinition.OVERLOAD, ItemsDefinition.SUPER_RESTORE_FLASK, ItemsDefinition.VARROCK_TAB, ItemsDefinition.ROCKTAILS},
            "Max Mage", new Object[][]{
            {MetadataDefinition.PRAYERS, new PrayersDefinition[]{PrayersDefinition.CURSES_PROTECT_ITEM, PrayersDefinition.CURSES_BERSERKER, PrayersDefinition.CURSES_TURMOIL}},
            {MetadataDefinition.SPEC_WEAPON, SpecialWeaponDefinition.DDS},
            {MetadataDefinition.MAGIC_SPELL, MagicDefinition.ICE_BARRAGE},
    }),

    //Hybrids
    MAXED_HYBRID(BehaviourDefinition.HYBRID, SkillsDefinition.MAX_MAIN, new EquipmentDefinition[]{EquipmentDefinition.MYSTIC_BOOK_AS},
            new ItemsDefinition[]{ItemsDefinition.OVERLOAD, ItemsDefinition.SUPER_RESTORE_FLASK, ItemsDefinition.VARROCK_TAB, ItemsDefinition.ROCKTAILS},
            "Maxed Hybrid", new Object[][]{
            {MetadataDefinition.PRAYERS, new PrayersDefinition[]{PrayersDefinition.CURSES_PROTECT_ITEM, PrayersDefinition.CURSES_BERSERKER, PrayersDefinition.CURSES_TURMOIL}},
            {MetadataDefinition.SPEC_WEAPON, SpecialWeaponDefinition.DDS},
            {MetadataDefinition.MAGIC_SPELL, MagicDefinition.ICE_BARRAGE},
            {MetadataDefinition.HYBRID_SET_MAGE, EquipmentDefinition.MYSTIC_BOOK_AS},
            {MetadataDefinition.HYBRID_SET_MELEE, EquipmentDefinition.TORSO_DDEF_WHIP},
            {MetadataDefinition.HYBRID_SET_RANGE, EquipmentDefinition.DHIDE_ZAMBOOK_CCB},
    }),
    MED_HYBRID(BehaviourDefinition.HYBRID, SkillsDefinition.MED_MAIN, new EquipmentDefinition[]{EquipmentDefinition.ZURIEL_DFS_SOL},
            new ItemsDefinition[]{ItemsDefinition.OVERLOAD, ItemsDefinition.SUPER_RESTORE_FLASK, ItemsDefinition.VARROCK_TAB, ItemsDefinition.ROCKTAILS},
            "Med Hybrid", new Object[][]{
            {MetadataDefinition.PRAYERS, new PrayersDefinition[]{PrayersDefinition.CURSES_PROTECT_ITEM, PrayersDefinition.CURSES_BERSERKER, PrayersDefinition.CURSES_TURMOIL}},
            {MetadataDefinition.SPEC_WEAPON, SpecialWeaponDefinition.KORASI},
            {MetadataDefinition.MAGIC_SPELL, MagicDefinition.ICE_BARRAGE},
            {MetadataDefinition.HYBRID_SET_MAGE, EquipmentDefinition.ZURIEL_DFS_SOL},
            {MetadataDefinition.HYBRID_SET_MELEE, EquipmentDefinition.RUNE_DFS},
            {MetadataDefinition.HYBRID_SET_RANGE, EquipmentDefinition.KHARIL_RANGER_CBOW_DFS},
    }),
    LOW_HYBRID(BehaviourDefinition.HYBRID, SkillsDefinition.LOW_MAIN, new EquipmentDefinition[]{EquipmentDefinition.INFINITY_BOOK_ANCIENT_STAFF},
            new ItemsDefinition[]{ItemsDefinition.OVERLOAD, ItemsDefinition.SUPER_RESTORE_FLASK, ItemsDefinition.VARROCK_TAB, ItemsDefinition.ROCKTAILS},
            "Low Hybrid", new Object[][]{
            {MetadataDefinition.PRAYERS, new PrayersDefinition[]{PrayersDefinition.CURSES_PROTECT_ITEM, PrayersDefinition.CURSES_BERSERKER, PrayersDefinition.CURSES_TURMOIL}},
            {MetadataDefinition.SPEC_WEAPON, SpecialWeaponDefinition.DDS},
            {MetadataDefinition.MAGIC_SPELL, MagicDefinition.ICE_BARRAGE},
            {MetadataDefinition.HYBRID_SET_MAGE, EquipmentDefinition.INFINITY_BOOK_ANCIENT_STAFF},
            {MetadataDefinition.HYBRID_SET_MELEE, EquipmentDefinition.RUNE_DEFENDER},
            {MetadataDefinition.HYBRID_SET_RANGE, EquipmentDefinition.BLACK_DHIDE_RANGER},
    }),;

    private final Behaviour behaviour;

    private final SkillsDefinition stats;
    private final EquipmentDefinition[] equipment;
    private final ItemsDefinition[] items;

    private final String name;
    private final Object[][] metadata;

    BotDefinition(BehaviourDefinition behaviour, SkillsDefinition stats, EquipmentDefinition[] equipment, ItemsDefinition[] items, String name, Object[]... metadata) {
        this.behaviour = behaviour.getBehaviour();
        this.stats = stats;
        this.equipment = equipment;
        this.items = items;
        this.name = name;
        this.metadata = metadata;
    }

    public Behaviour getBehaviour() {
        return behaviour;
    }

    public SkillsDefinition getStats() {
        return stats;
    }

    public EquipmentDefinition[] getEquipment() {
        return equipment;
    }

    public ItemsDefinition[] getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public Object[][] getMetadata() {
        return metadata;
    }
}
