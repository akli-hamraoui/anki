package com.smartholiday.team.gaming.anki.model;

import com.smartholiday.team.gaming.anki.enums.EAnkiColor;

/**
 * @author Akli HAMRAOUI
 * This is the combination between card and evaluation as party
 *
 */
public class Party {
	
	private EAnkiColor evaluation;
	
	private Card card;

	public EAnkiColor getEvaluation() {
		return evaluation;
	}

	/**
	 * @param evaluation
	 */
	public void setEvaluation(EAnkiColor evaluation) {
		this.evaluation = evaluation;
	}

	/**
	 * @return the card shown in this party
	 */
	public Card getCard() {
		return card;
	}

	/**
	 * @param card
	 */
	public void setCard(Card card) {
		this.card = card;
	}
	
}
