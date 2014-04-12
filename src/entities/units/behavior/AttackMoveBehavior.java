package entities.units.behavior;

import control.GameModel;
import entities.units.Unit;
import entities.util.Point;

/**
 * Starts with normal attack and normal move behavior
 * 
 * Attack when possible (target in range)
 * 
 * @author Lloyd
 *
 */
public class AttackMoveBehavior extends UnitBehavior{
	
	//User wishes for unit to attack/move to a location
	public AttackMoveBehavior(Point targetLocation) {
		attackBehavior = new NormalAttack();
		movementBehavior = new NormalMovement(targetLocation);
	}

	@Override
	public boolean performBehavior(GameModel model, Unit currUnit, int timeScale) {
		//TODO: needs Ben's line of sight stuff
		if (attackBehavior.enemyInSight(model)) {
			if (attackBehavior.canAttack(model)) {
				
			}
		}
		return false;
	}

	@Override
	public boolean setTargetLocation(GameModel model, Point target) {
		// TODO Auto-generated method stub
		
	}
	
	

}
