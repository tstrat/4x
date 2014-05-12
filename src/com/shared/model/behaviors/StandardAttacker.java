package com.shared.model.behaviors;

import com.shared.utils.PhysicsVector;



public class StandardAttacker implements Attacker {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7599376887116225985L;
	private int coolDown; // Number of milliseconds for attack cooldown
	private int coolDownTimer = 0; // Number of milliseconds since previous attack
	private Combatable target = null; // Current attack target
	private float range; // Attack range
	private int strength; // Attack strength
	private boolean isAttacking; // Whether or not I am actively attacking my target
	private boolean isAnimatingAttack = false;
	/*
	 * Must have an associated movementBehavior in order to calculate distance from
	 * target and in order to perform appropriate attack-move commands
	 */
	private Movable moveBehavior;
	
	public StandardAttacker() {
		this.coolDown = 1000;
		this.range = 1.0F;
		this.strength = 1;
		this.isAttacking = false;
	}
	
	public StandardAttacker(int strength, float range, float coolDown, Movable mb) {
		this.coolDown = (int) coolDown * 1000;
		this.range = range;
		this.strength = strength;
		this.isAttacking = false;
		this.moveBehavior = mb;
	}
	
	public void setTarget( Combatable target ) {
		this.target = target;
	}
	
	public void startAttack() {
		isAttacking = true;
	}
	
	public void stopAttack() {
		isAttacking = false;
	}

	@Override
	public void simulateAttack(int timeStep) {
		if(target != null && isAttacking && !target.isDead()) {
			PhysicsVector targetPosition = target.getPosition();
			PhysicsVector myPosition = moveBehavior.getPosition();
			double distanceToTarget = targetPosition.sub(myPosition).magnitude();
			
			coolDownTimer += timeStep;
			int numAttacks = coolDownTimer / coolDown;
			if(numAttacks > 0) isAnimatingAttack = true;
			coolDownTimer %= coolDown;
			
			if( distanceToTarget <= range ) {
				double x = myPosition.getX();
				double y = myPosition.getY();
				moveBehavior.setMoveTarget(x, y);
				for(int i = 0; i < numAttacks; i++) target.takeDamage(strength);
			} else {
				PhysicsVector dir = targetPosition.sub(myPosition);
				PhysicsVector moveTo = targetPosition.sub(dir.normalize(range - 1));
				double x = moveTo.getX();
				double y = moveTo.getY();
				moveBehavior.setMoveTarget(x, y);
			}
		} else {
			if( isAttacking && (target == null || target.isDead())) {
				// Stop moving if the target has been killed already
				PhysicsVector myPosition = moveBehavior.getPosition();
				double x = myPosition.getX();
				double y = myPosition.getY();
				moveBehavior.setMoveTarget(x, y);
				// STOP WIGGLING
				moveBehavior.setVelocity(0, 0);
			}
			stopAttack();
			coolDownTimer += timeStep;
			coolDownTimer = coolDownTimer > coolDown ? coolDown : coolDownTimer;
		}
	}

	@Override
	public boolean isAnimatingAttack() {
		boolean temp = isAnimatingAttack;
		isAnimatingAttack = false;
		return temp;
	}
	
}
