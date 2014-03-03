package com.fourx.civilizations;

import com.fourx.research.TechnologyEnum;

public class TestCivilization extends Civilization {

	public TestCivilization() {
		super();
		research_max_level.put(TechnologyEnum.INFANTRYDAMAGE1.name(), 1);
	}

	public void addResearch(String name, int level) {
		research_max_level.put(name, level);
	}
}