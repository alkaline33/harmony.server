package com.rs.game.player.bot.behaviour.action;

import com.rs.game.Hit;
import com.rs.game.player.bot.Bot;
import com.rs.game.player.bot.definition.EquipmentDefinition;
import com.rs.game.player.bot.definition.MetadataDefinition;

/**
 * Created by Valkyr on 30/05/2016.
 */
public class HybridSwitchEquipmentAction extends Action {

    public HybridSwitchEquipmentAction() {
        super(2000, 10000);
    }

    @Override
    public boolean process(Bot bot) {
        EquipmentDefinition switchEquipment = getSwitchEquipment(bot);
        if (switchEquipment != null) {
            switchEquipment.apply(bot);
            return true;
        }
        return false;
    }

    private EquipmentDefinition getSwitchEquipment(Bot bot) {
        MetadataDefinition switchDefinition = null;
        Hit.HitLook lastHit = bot.getMetaData(MetadataDefinition.LAST_INCOMING_HIT);
        if (lastHit != null)
            switch (lastHit) {
                case MELEE_DAMAGE:
                    switchDefinition = MetadataDefinition.HYBRID_SET_MAGE;
                    break;
                case RANGE_DAMAGE:
                    switchDefinition = MetadataDefinition.HYBRID_SET_MELEE;
                    break;
                case MAGIC_DAMAGE:
                    switchDefinition = MetadataDefinition.HYBRID_SET_RANGE;
                    break;

            }
        return switchDefinition == null ? null : bot.getMetaData(switchDefinition);

    }
}
