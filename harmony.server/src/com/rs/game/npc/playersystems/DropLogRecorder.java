package com.rs.game.npc.playersystems;

import com.rs.game.item.Item;
import com.rs.game.npc.Drop;
import com.rs.game.player.Player;

public class DropLogRecorder {

	public static void Track(Player player, Drop drop) {

		switch (drop.getItemId()) {
		/*
		 * Corp
		 */
		case 13746:
		case 13748:
		case 13750:
		case 13752:
		case 29891:
		case 29527:
		case 29598:
			player.getCorpDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 28905:
		case 28906:
		case 28907:
			player.getAmigoDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 13754:
		//	player.DryaxgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			player.getCorpDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 11286: // visage
			player.KBDgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
		//	player.DryaxgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 14484: // dragon claws
			player.TdsgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
		//	player.DryaxgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			player.BlinkgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 14472: // ruined dragon
		case 14474:// ruined dragon
		case 14476:// ruined dragon
			player.TdsgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 28972:
		case 28973:
		case 28974:
			player.RaptorgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 28975:
			player.raptorseals++;
			player.RaptorgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 20135:
		case 20139:
		case 20143:
		case 20147:
		case 20151:
		case 20155:
		case 24974:
		case 24989:
		case 20159:
		case 24986:
		case 20163:
		case 20167:
		case 24980:
		case 25062:
		case 24977:
		case 24983:
		case 20171:
		case 29928:
			player.NexgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 21787: // steadfast boots
		case 21793: // ragefire boots
		case 21790: // glaiven boots
			player.GlacorgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 11724:
		case 11726:
		case 11704:
		case 11728:
			player.BandosgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29192:
		case 29191:
		case 29190:
		case 29188:
		case 29186:
			player.VorkathgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29125:
		case 29126:
		case 29127:
		case 29128:
			player.CelestiagetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 11708:
		case 11716:
		case 29454:
		case 24992:
		case 24995:
		case 24998:
			player.ZamorakgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29171:
		case 29174:
		case 29175:
		case 29176:
		case 29177:
		case 29169:
			player.HydragetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 11702:
		case 11718:
		case 11720:
		case 11722:
			player.ArmadylgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 11706:
		case 11730:
		case 25028:
		case 25031:
		case 25034:
		case 25037:
			//System.out.println("added");
			player.SaradomingetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29559:
		case 29560:
		case 29561:
		case 29562:
			player.WildyWyrmgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29783:
			player.HmWildyWyrmgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29967:
		case 29918:
			player.AquaticWyrmgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29944:
		case 29943:
			player.VoragogetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29869:
		case 14642:
		case 14641:
		case 14654:
		case 14651:
			player.AodgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 24354:
		case 24355:
		case 24356:
		case 24357:
		case 24358:
		case 24359:
		case 24360:
		case 24361:
		case 24362:
		case 24363:
		case 24364:
			player.GazergetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29877:
		case 11951:
			player.SantagetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 16753:
		case 16863:
		case 17235:
			//player.DryaxgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29930:
		case 29931:
		case 29929:
			player.KalphiteKinggetDropLog().getContainer().add(new Item(drop.getItemId(), 30));
			break;
		case 7806:
		case 7808:
		case 7809:
		case 29785:
			player.HopegetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29468:
		case 29469:
		case 29467:
		case 29465:
		case 29043:
		case 29042:
			player.ZulrahgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 25202:
		case 24707:
		case 24708:
		case 24709:
		case 24710:
		case 20763:
		case 20764:
		case 20745:
		case 20744:
			player.NecrolordgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29594:
		case 29587:
		case 29592:
			player.ThunderousgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29782:
		case 21371:
		case 9006:
			player.SunfreetgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29565:
		case 29564:
		case 29563:
			player.AniviagetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29649:
		case 20786:
		case 29680:
			player.SliskegetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29461:
		case 29463:
		case 29464:
			player.KrakengetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29441:
		case 29444:
		case 29445:
		case 29446:
		case 29443:
		case 4151:
			player.SiregetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29420:
		case 29421:
		case 29422:
		case 29419:
			player.CerberusgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29887:
			player.SirenicgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29382:
			player.CallistogetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29316:
			player.VenenatisgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29313:
		case 29309:
			player.ChaosFanaticgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29312:
		case 29308:
			player.ArchaeologistgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29311:
		case 29307:
			player.ScorpiagetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29302:
			player.VetiongetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29440:
			player.LizardmangetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29450:
		case 29451:
			player.SmokeDevilgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29728:
			player.AniviagetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			player.BlinkgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
		case 29611:
		case 29610:
		case 29609:
		case 29603:
		case 29604:
		case 29605:
		case 29585:
		case 29584:
		case 29583:
			player.BlinkgetDropLog().getContainer().add(new Item(drop.getItemId(), 1));
			break;
			
		default:
			break;
		}

	}
}
