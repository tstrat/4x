package com;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fourx.civilizations.PerfectCivilization;

import control.Player;
import entities.Action;
import entities.PlayerUnits;
import entities.buildings.Barracks;
import entities.buildings.Building;
import entities.gameboard.GameBoard;
import entities.units.Agent;
import entities.units.Infantry;
import entities.units.Unit;

public class GameBoardTest {

	@Test
	public void testBoard() {

		GameBoard game = new GameBoard(20, 20, 2);
		assertEquals(20, game.getRows());
		assertEquals(20, game.getCols());

	}

	@Test
	public void testTile() {

		GameBoard game = new GameBoard(20, 20, 2);
		Player juan = new Player("juan", new PerfectCivilization());

	}

	@Test
	public void testAction() {

		// create a 200x200 board with 2 players
		GameBoard game = new GameBoard(200, 200, 2);
		// get the player references
		Player p1 = game.getPlayerList().get(0);
		Player p2 = game.getPlayerList().get(1);

		// no units should be added to this game yet
		assertEquals(0, p1.getUnits().getUnitList().size());
		assertEquals(0, p2.getUnits().getUnitList().size());

		// add a unit to player 1
		Unit temp = new Infantry(p1, 1, 1);

		assertEquals(1, p1.getUnits().getUnitList().size());
		assertEquals(0, p2.getUnits().getUnitList().size());

		// add a unit to player 2
		Unit temp2 = new Infantry(p2, 2, 2);

		// add some actions
		temp.addAction(Action.PLAYER_ATTACK);
		temp.addAction(Action.DEATH);
		temp.addAction(Action.PLAYER_ATTACK);
		temp2.addAction(Action.PLAYER_ATTACK_MOVE);

		assertEquals(1, p2.getUnits().getUnitList().size());

		// test if actions were added
		assertEquals(3, temp.getActionQueue().size());
		assertEquals(1, temp2.getActionQueue().size());

		System.out.println("Temp1: " + temp.getActionQueue());
		System.out.println("Temp2: " + temp2.getActionQueue());

		// execute actions
		temp.performActions();
		temp2.performActions();

		// test if actions were removed
		assertEquals(0, temp.getActionQueue().size());
		assertEquals(0, temp2.getActionQueue().size());
		assertEquals(0, p1.getUnits().getUnitList().size());
		assertEquals(1, p2.getUnits().getUnitList().size());

		// testing if the player can add an action to a unit

		p1.addActionTo(temp, Action.DEATH);
		temp.performActions();

	}

	@Test
	public void testPlayerUnits() {

		// create a 200x200 board with 2 players
		GameBoard game = new GameBoard(200, 200, 2);
		// get the player references
		Player p1 = game.getPlayerList().get(0);
		Player p2 = game.getPlayerList().get(1);

		// no units should be added to this game yet
		assertEquals(0, p1.getUnits().getUnitList().size());
		assertEquals(0, p2.getUnits().getUnitList().size());

		// add a unit to player 1
		Unit temp = new Infantry(p1, 1, 1);

		assertEquals(1, p1.getUnits().getUnitList().size());
		assertEquals(0, p2.getUnits().getUnitList().size());

		// add a unit to player 2
		Unit temp2 = new Infantry(p2, 2, 2);
		Building bld = new Barracks(p1, 1, 1);
		Agent a1 = new Agent(p1);

		PlayerUnits pu1 = p1.getUnits();
		PlayerUnits pu2 = p2.getUnits();

		assertFalse(pu1.hasUnitAt(0, 0));
		assertTrue(pu1.hasUnitAt(1, 1));
		assertFalse(pu1.hasUnitAt(0, 1));
		assertFalse(pu1.hasUnitAt(2, 2));
		assertFalse(pu2.hasUnitAt(1, 1));
		assertTrue(pu2.hasUnitAt(2, 2));
		assertFalse(pu2.hasUnitAt(0, 1));
		assertFalse(pu2.hasUnitAt(1, 0));

		assertFalse(pu1.hasBuildingAt(0, 1));
		assertTrue(pu1.hasBuildingAt(1, 1));
		assertFalse(pu1.hasBuildingAt(1, 0));
		assertFalse(pu1.hasBuildingAt(0, 0));

		assertEquals(1, pu1.getAgentList().size());

	}
}