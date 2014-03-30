package entities.buildings;

import java.util.HashMap;
import java.util.UUID;

import control.BuildingType;

import entities.resources.Resources;
import entities.stats.BaseStatsEnum;
import entities.stats.UnitStats;

public class GoldMine extends ResourceBuilding{

	public GoldMine(UUID id, int playerId, BaseStatsEnum baseStats,
			UnitStats new_stats, 
			BuildingType buildingType, float xco, float yco, int height,
			int width) {
		super(id, playerId, baseStats, new_stats, buildingType, xco,
				yco, height, width, new Resources(20, 0, 0, 0, 0));
	}

	@Override
	protected void setActions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HashMap<String, String> getActions() {
		// TODO Auto-generated method stub
		return null;
	}

}
