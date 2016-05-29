package com.smartholiday.team.gaming.anki.enums;

public enum EAnkiColor {
	GREEN(2), ORANGE(1), RED(-2);
	
	int step;

	private EAnkiColor(int step) {
		this.step = step;
	}

	public int getStep() {
		return step;
	}
	
}
