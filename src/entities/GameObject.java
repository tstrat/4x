package entities;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.UUID;

import entities.stats.BaseStatsEnum;
import entities.stats.UnitStats;
import entities.util.Point;

public abstract class GameObject {
	private final UUID id;
	private final int playerId;
	protected HashMap<String, String> allActions;
	protected GameObjectType type;
	private UnitStats stats;
	protected BaseStatsEnum baseStats;

	protected Point position;
	protected PriorityQueue<Action> actionQueue;

	public GameObject(UUID id, int playerId, BaseStatsEnum baseStats, UnitStats new_stats, GameObjectType type, float xco,
			float yco) {
		this.id = id;
		this.playerId = playerId;
		this.baseStats = baseStats;
		stats = baseStats.getStats();
		updateStats(baseStats, new_stats);
		this.type = type;
		position = new Point(xco, yco);
		actionQueue = new PriorityQueue<Action>();

	}
	
	protected abstract void setActions();//might get rid 
	
	public abstract HashMap<String, String> getActions();//might get rid of
	
	public UUID getId() {
		return id;
	}
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * called to reset the stats of the unit to (basestats + upgrades)
	 */

	public void updateStats(BaseStatsEnum s, UnitStats new_stats) {
		if (this.baseStats == s) {
			float percentage_health = stats.health / (float) stats.max_health;
			stats = new_stats.clone();
			stats.health = stats.max_health * percentage_health;
		}
	}

	public Point getPosition() {
		return position;
	}

	/**
	 * 
	 * @param x
	 *            - x coordinate of the unit.
	 * @param y
	 *            - y coordinate of the unit.
	 */
	public void setLocation(int x, int y) {
		position.x = x;
		position.y = y;
	}

	/**
	 * 
	 * @param n
	 *            - set the health of the Unit to the specified amount.
	 * @return - whether the unit is still alive.
	 */
	public boolean setHealth(int n) {
		stats.health = n;
		if (stats.health > stats.max_health)
			stats.health = stats.max_health;

		if (stats.health <= 0)
			return false;
		else
			return true;

	}

	/**
	 * 
	 * @param n
	 *            - the amount of health to modify the unit by. can be a
	 *            negative amount to specify damage.
	 * @return - whether the unit is still alive.
	 */
	public boolean modifyHealth(int n) {

		stats.health = stats.health + n;
		if (stats.health > stats.max_health)
			stats.health = stats.max_health;

		if (stats.health <= 0)
			return false;
		else
			return true;

	}

	/**
	 * 
	 * @return - the current health of the Unit.
	 */
	public float getHealth() {
		return stats.health;
	}

	public int getOwner() {
		return playerId;
	}

//	/**
//	 * 
//	 * @param a
//	 *            - add an action to the PriorityQueue to be performed during
//	 *            the turn.
//	 */
//	public void addAction(Action a) {
//		actionQueue.add(a);
//		getOwner().getCommandQueue().push(a, this);
//	}

//	/**
//	 * Logic to handle actions that the Unit may do. TODO: add all necessary
//	 * actions
//	 */
//	public void performActions()
}