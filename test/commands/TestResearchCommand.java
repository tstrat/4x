package commands;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import control.Controller;
import control.GameModel;
import control.Player;
import control.commands.Command;
import control.commands.ConstructBuildingCommand;
import control.commands.ResearchTechnologyCommand;
import entities.buildings.Building;
import entities.buildings.BuildingType;
import entities.buildings.ResearchBuilding;
import entities.research.Technology;
import entities.research.TechnologyEnum;
import entities.resources.Resources;

public class TestResearchCommand {

	@Test
	public void testCreate() {
		Controller controller = new Controller();
		GameModel model = controller.getGameModel();
		Thread t = new Thread(controller);
		t.start();
		assertEquals(2, model.getPlayers().size());

		// test create for player 1
		Player p = model.getPlayer(1);
		p.resources.receive(new Resources(1000, 1000, 1000, 1000, 1000));
		Map<UUID, Building> buildings = p.getGameObjects().getBuildings();
		assertEquals(0, buildings.size());

		p = model.getPlayer(1);
		HashMap<String, Integer> temp = p.getTechTree().currently_researching;
		assertEquals(0, temp.size());

		Command comm = new ConstructBuildingCommand(p, p.getId(),
				BuildingType.BLACKSMITH, 1, 1, model.getBoard());
		controller.addCommand(comm);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals(1, buildings.size());

		buildings = p.getGameObjects().getBuildings();
		ResearchBuilding b = (ResearchBuilding) buildings.values().toArray()[0];
		comm = new ResearchTechnologyCommand(p, b, TechnologyEnum.INFANTRYARMOR);
		controller.addCommand(comm);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		buildings = p.getGameObjects().getBuildings();
		b = (ResearchBuilding) buildings.values().toArray()[0];

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		p = model.getPlayer(1);
		temp = p.getTechTree().currently_researching;
		assertEquals(1, temp.size());
		
	}

}
