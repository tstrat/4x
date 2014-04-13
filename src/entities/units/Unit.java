package entities.units;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.UUID;

import com.shared.MoveBehavior;

import control.UnitType;
import entities.Action;
import entities.stats.BaseStatsEnum;
import entities.AttackBehavior;
import entities.GameObject;
import entities.GameObjectType;
import entities.stats.UnitStats;

/*
 * 
 * 
 * Purpose:  This abstract class defines the concept of a unit.  Every unit will atleast have the following 
 * information known about itself.  
 */

// TODO add A* path finding, use diagonals to make nice looking paths
// returns a queue/list of tiles that it needs to go to, at each turn pop one off and move player there. 

public class Unit extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5568927886403172710L;
	private UnitType unitType;
	private int creationTime;
	private AttackBehavior attackBehavior;
	
	public Unit() {
		super();
		this.unitType = UnitType.ARCHER;
		this.creationTime = baseStats.getCreationTime();
	}

	public Unit(int id, int playerId, BaseStatsEnum baseStats,
			UnitStats new_stats, UnitType unitType,
			float xco, float yco) {
		super(id, playerId, baseStats, new_stats, GameObjectType.UNIT, xco, yco);
		this.unitType = unitType;
		this.creationTime = baseStats.getCreationTime();
		attackBehavior = new AttackBehavior( stats.damage, stats.range, stats.actionSpeed, moveBehavior );
	}
	
	public AttackBehavior getAttackBehavior() {
		return attackBehavior;
	}

	/**
	 * getActionQueue()
	 * returns the list of actions this unit is in process of doing. 
	 * @return
	 */
	public PriorityQueue<Action> getActionQueue() {
		return actionQueue;
	}

	/**
	 * getCreationTime():
	 * returns the creation time for this unit.
	 * @return
	 */
	public int getCreationTime() {
		return this.baseStats.getCreationTime();
	}
	
	public UnitType getUnitType() {
		return unitType;
	}
	
	/**
	 * decrementCreationTime()
	 * decrements remaining time for unit production
	 * @param int timestep - how much to decrement by
	 */
	public void decrementCreationTime(int timestep)
	{
		this.creationTime -= timestep;
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
	
	public void advanceTimeStep( int timeStep ) {
		super.advanceTimeStep(timeStep);
		attackBehavior.advanceTimeStep(timeStep);
	}
}
