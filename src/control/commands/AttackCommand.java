package control.commands;

import control.GameModel;
import entities.behaviors.Attacker;
import entities.behaviors.Combatable;

public class AttackCommand implements Command {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3042396025898165513L;
	private int attackerID;
	private int targetID;
	
	public AttackCommand(int attackerID, int targetID) {
		this.attackerID = attackerID;
		this.targetID = targetID;
	}

	@Override
	public boolean validateCommand(GameModel model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean performCommand(GameModel model) {
		Attacker attacker = (Attacker) model.getGameObject(attackerID);
		Combatable target = (Combatable) model.getGameObject(targetID);
		attacker.setTarget(target);
		// TODO Auto-generated method stub
		return false;
	}

}
