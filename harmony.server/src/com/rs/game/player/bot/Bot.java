package com.rs.game.player.bot;

import java.util.HashMap;
import java.util.Map;

import com.rs.game.Hit;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.bot.behaviour.Behaviour;
import com.rs.game.player.bot.definition.BotDefinition;
import com.rs.game.player.bot.definition.ItemsDefinition;
import com.rs.game.player.bot.definition.MagicDefinition;
import com.rs.game.player.bot.definition.MetadataDefinition;
import com.rs.net.encoders.WorldPacketsEncoder;
import com.rs.utils.IsaacKeyPair;
import com.rs.utils.Utils;

/**
 * Created by Valkyr on 21/05/2016.
 */
public class Bot extends Player {

    public static String[] names = new String[]{"jimbo2", "Dwight", "Adom", "Hinech", "Lenor", "Therria", "Issfach", "Iplay4", "Smacked9", "Lerlore", "Naroth", "Vanessa", "Achvor", "Serird", "Denec", "Gaum", "Perad", "Enpero", "Lodyn", "HeyJim", "Pyvor", "Tonkim", "Nysoph", "Nysbani", "Kelperi", "Hinel", "Dagar", "Perbver", "Waros", "Tasoy", "Chother", "Ataugh", "Emchae", "Radpera", "Frostedgrass", "Sebastian", "Tyum", "Creeper", "Nysentho", "Cheftom", "Numor", "Chiend", "Nalest", "Trahin", "Tiay", "Tiaskeli", "Belarda", "Darot", "Tindtin", "Aneng", "Perbur", "Noas", "Dynnon", "Quazild", "Imano", "Aughsay", "Veryn", "Chetech", "Belunde", "Tyess", "Ingtonu", "Lorkal", "Deldynu", "Aruk", "Aughild", "Yerurna", "Banormi", "Vorthero", "Lokin", "Warand", "Tainys", "Enddra", "Torum", "Worend", "Omser", "Irusk", "Eldisso", "Echtia", "Imsryn", "Garvori", "Kousk", "Tanenth", "Bogha", "Undorma", "Umkom", "Rayquey", "Usktem", "Ormtald", "Engcha", "Rayin", "Torema", "Osia", "Hinhqua", "Blaray", "Serur", "Issest", "Emtas", "Rodus", "Aughonn", "Eldomi", "Ightntor", "Risis", "Gartar", "Blidra", "Darbbur", "Quoust", "Belhwar", "Awdryn", "Llyan", "Adene", "Rothaph", "Hinem", "Ades", "Enthen", "Itor", "Ormena", "Eldden", "Achquei", "Shydtur"};

    private BotDefinition definition;
    private Behaviour behaviour;

    private WorldTile spawnWorldTile;

    private Map<MetadataDefinition, Object> metadata = new HashMap<>();
    private transient WorldPacketsEncoder packets;

    public Bot(String displayName) {
        super("");
        init(createName());
    }

    public void init(String displayName) {
        init(null, displayName, 1, 1, 1, null, new IsaacKeyPair(new int[]{0, 1, 2, 3}));
        start();
        getAppearence().generateAppearenceData();
        getCombatDefinitions().setAutoRelatie(true);
        getCombatDefinitions().refreshAutoRelatie();
        
    }

    @Override
    public void processEntity() {
        super.processEntity();
        if (behaviour != null)
            behaviour.process(this);
    }

    @Override
	public void processReceivedHits() {
		super.processReceivedHits();
        final Player owner = getMetaData(MetadataDefinition.OWNER);
    }

    @Override
    public int getMaxHitpoints() {
        int maxHp = getSkills().getLevel(Skills.HITPOINTS) * 10 + getEquipment().getEquipmentHpIncrease();
        final Player owner = getMetaData(MetadataDefinition.OWNER);
        if (owner != null)
            maxHp *= 4;
        return maxHp;
    }


    @Override
    public void handleIngoingHit(final Hit hit) {
        putMetaData(MetadataDefinition.LAST_INCOMING_HIT, hit.getLook());
        super.handleIngoingHit(hit);
    }


    @Override
    public WorldPacketsEncoder getPackets() {
        if (packets == null)
            packets = new FakeWorldPacketsEncoder(null, this);
        return packets;
    }

    public void loadInventory() {
        if (definition != null) {
            getInventory().removeItems(getInventory().getItems().toArray());
            MagicDefinition spell = getMetaData(MetadataDefinition.MAGIC_SPELL);
            if (spell != null)
                for (ItemsDefinition set : spell.getRunes()) {
                    getInventory().addItem(set.getId(), set.getAmount());
                }

            for (ItemsDefinition set : definition.getItems()) {
                getInventory().addItem(set.getId(), set.getAmount());
            }
            getInventory().refresh();
        }
    }

    public void loadEquipment() {
        if (definition != null && definition.getEquipment() != null)
            definition.getEquipment()[(int) (Math.random() * definition.getEquipment().length)].apply(this);
    }


    public void setBehaviour(Behaviour behaviour) {
        this.behaviour = behaviour;
    }

    public WorldTile getSpawnWorldTile() {
        return spawnWorldTile;
    }

    public void setSpawnWorldTile(WorldTile location) {
        this.spawnWorldTile = location;
        setLocation(location);
    }

    public void setDefinition(BotDefinition definition) {
        this.definition = definition;

        for (Object[] data : definition.getMetadata()) {
            Object key = data[0];
            Object value = data[1];
            System.out.println(getDisplayName() + " METADATA[" + key.getClass().getSimpleName() + "] = " + data.getClass().getSimpleName());
            if (key instanceof MetadataDefinition)
                putMetaData((MetadataDefinition) key, value);
        }

        setBehaviour(definition.getBehaviour());
        definition.getStats().apply(this);
        loadInventory();
        loadEquipment();
        setHitpoints(getMaxHitpoints());
    }


    public <T> T getMetaData(MetadataDefinition key) {
        if (!metadata.containsKey(key))
            return null;
        return (T) metadata.get(key);
    }

    public void putMetaData(MetadataDefinition key, Object value) {
        metadata.put(key, value);
    }

    public static String createName(String... names) {
        String numbers = "";
        if (names.length > 0) {
            final int rand = Utils.getRandom(2);
            for (int x = 0; x < rand; x++) {
                numbers = numbers + "" + Utils.getRandom(9);
            }
        }
        String name = Bot.names[Utils.getRandom(Bot.names.length - 1)];
        if (names.length > 0) {
            name = names[0];
        }
        final String finishedname = name + numbers;
        if (World.getPlayerByDisplayName(finishedname) != null)
            return createName(finishedname);
        return finishedname;
    }
}
