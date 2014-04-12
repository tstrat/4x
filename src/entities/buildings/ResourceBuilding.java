package entities.buildings;

import entities.resources.Resources;
import entities.units.Unit;

// TODO for construction, need to add check to make sure resource building is 
// being placed on correct tile type (food on food, gold on gold, etc.)

public interface ResourceBuilding {

	public static Resources globalRateModifier = new Resources(1, 1, 1, 1, 1);

	/**
	 * ResourceBuidling(): Base constructor for Resource Type buildings, calls
	 * super building constructor and sets base amount of resources.
	 * 
	 * Parameters
	 * 
	 * @param UUID
	 *            id - the unique identifier for this Resource Building
	 * @param int playerId - the id of the player who owns this resource
	 *        building
	 * @param baseStats
	 *            - the basic stats for this building (ie health, capacity,
	 *            etc.)
	 * @param new_stats
	 *            - the current stats of this unit (less than or equal to
	 *            baseStats generally)
	 * @param buildingType
	 *            Type of building -
	 * @param float xco - initial x coordinate
	 * @param float yco - initial y coordinate
	 * @param int height - how many tiles high building takes up
	 * @param int width - how many tiles wide the building takes up
	 * @param Reosources
	 *            resourceAmount - base amount of resource building generates
	 * 
	 */

	/* Setters and Getters */
	public void setGlobalRateModifier(Resources newRate);

	public Resources getGlobalRateModifier();

	public Resources generateResource();

	public Unit advanceResourceProduction(int timestep);

}
