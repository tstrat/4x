package entities.units.pawns;

import java.util.HashMap;
import java.util.UUID;

import entities.stats.BaseStatsEnum;
import entities.units.Unit;
import entities.units.UnitType;

public class Ranged_Calvary extends Unit{

	public Ranged_Calvary(UUID id, int playerId, float xco, float yco) {
		super(id, playerId, BaseStatsEnum.RANGED_CALVARY, BaseStatsEnum.RANGED_CALVARY.getStats(), UnitType.RANGED_CALVARY, xco, yco);
		// TODO Auto-generated constructor stub
	}


}
