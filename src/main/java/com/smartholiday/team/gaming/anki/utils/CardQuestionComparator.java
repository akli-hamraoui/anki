package com.smartholiday.team.gaming.anki.utils;

import java.util.Comparator;

import com.smartholiday.team.gaming.anki.model.Card;

public class CardQuestionComparator implements Comparator<Card> {

	@Override
	public int compare(Card card1, Card card2) {
		return card1.getQuestion().compareTo(card2.getQuestion());
	}

}
