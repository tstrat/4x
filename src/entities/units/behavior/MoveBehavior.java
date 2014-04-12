package entities.units.behavior;

import control.GameModel;
import entities.util.Point;

public abstract class MoveBehavior {
	protected Point targetLocation;
	
	public abstract boolean move(GameModel model, int timeScale);
	
	//dependent on behavior
	public abstract boolean canMove(GameModel model);
	
	public abstract boolean setTargetLocation(Point newTarget);
}
