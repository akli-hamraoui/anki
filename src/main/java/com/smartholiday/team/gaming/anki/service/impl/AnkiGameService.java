package com.smartholiday.team.gaming.anki.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartholiday.team.gaming.anki.enums.EAnkiColor;
import com.smartholiday.team.gaming.anki.model.Card;
import com.smartholiday.team.gaming.anki.model.Party;
import com.smartholiday.team.gaming.anki.service.interfaces.IAnkiGameService;
import com.smartholiday.team.gaming.anki.utils.CardQuestionComparator;
import com.smartholiday.team.gaming.anki.utils.Md5HexaGenerator;

/**
 * @author Akli HAMRAOUI Anki Service implements the business functions of Anki
 *         Game
 *
 */
public class AnkiGameService implements IAnkiGameService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String getAnkiResult(File inputFile) {
		Map<String, Card> cardMap;
		try {
			cardMap = new HashMap<String, Card>();
			LineIterator lines = FileUtils.lineIterator(inputFile);
			// avoid first line
			if (lines.hasNext()) {
				lines.nextLine();
			}
			while (lines.hasNext()) {
				String line = lines.nextLine();
				Party party = buildParty(line, cardMap);
				Card card = party.getCard();
				int currentKnowledge = getCurrentKnowledge(party);
				card.setKnowledge(currentKnowledge);
				// always update knowledge before session
				int nextSession = getNextSession(party);
				card.setSession(nextSession);
				cardMap.put(Md5HexaGenerator.hashString(card.getQuestion()), card);
			}
			lines.close();
		} catch (IOException e) {
			cardMap = null;
			logger.error("Failed to parse file, because of : " + e.getMessage());
		}
		if (cardMap != null & !cardMap.isEmpty()) {
			String newligne = System.getProperty("line.separator");
			StringBuilder output = new StringBuilder();
			output.append("card question | card | next session | current knowledge");
			output.append(newligne);
			int index = 0;
			Collection<Card> uniqueCards = cardMap.values();
			ArrayList<Card> listCards = new ArrayList<Card>(uniqueCards);
			Collections.sort(listCards, new CardQuestionComparator());
			for (Card card : listCards) {
				index++;
				output.append(card.getQuestion() + " | " + card.getAnswer() + " | " + card.getSession() + " | "
						+ card.getKnowledge());
				if (cardMap.size() > index) {
					output.append(newligne);
				}
			}
			return output.toString();
		} else {
			return StringUtils.EMPTY;
		}
	}

	@Override
	public int getCurrentKnowledge(Party party) {
		Card card = party.getCard();
		EAnkiColor color = party.getEvaluation();
		int cardKnowledge = calculateCardKnowledge(card.getKnowledge());
		int currentKnowledge = cardKnowledge + color.getStep();
		int calculatedKnowledge = calculateCardKnowledge(currentKnowledge);
		return calculatedKnowledge;
	}

	/**
	 * @param card
	 *            knowledge
	 * @return return 1 if knowledge <=0 else knowledge value
	 */
	private int calculateCardKnowledge(int knowledge) {
		int cardKnowledge = knowledge <= 0 ? 1 : knowledge;
		return cardKnowledge;
	}

	/**
	 * @param card
	 *            session
	 * @return return 1 if session <=0 else session value
	 */
	private int calculateSession(int session) {
		int cardSession = session <= 0 ? 1 : session;
		return cardSession;
	}

	@Override
	public int getNextSession(Party party) {
		Card card = party.getCard();
		int nextSession = 0;
		int calculatedSession = calculateSession(card.getSession());
		int calculatedCardKnowledge = calculateCardKnowledge(card.getKnowledge());
		nextSession = calculateSession(calculatedCardKnowledge - 1);
		nextSession = nextSession + calculatedSession;
		return nextSession;
	}

	@Override
	public Party buildParty(String line, Map<String, Card> cardMap) {
		if (org.apache.commons.lang3.StringUtils.isBlank(line)) {
			throw new IllegalArgumentException("line is empty or null");
		} else {
			Party party = new Party();
			Card card;
			String[] cardArray = line.split("\\|");
			Integer session = Integer.valueOf(cardArray[0].trim());
			String question = cardArray[1];
			String md5Key = Md5HexaGenerator.hashString(question);
			if (cardMap.containsKey(md5Key)) {
				card = cardMap.get(md5Key);
			} else {
				card = new Card();
				card.setQuestion(question);
				card.setAnswer(cardArray[2]);
			}
			card.setSession(session);
			String evaluation = cardArray[3].trim();
			party.setCard(card);
			party.setEvaluation(EAnkiColor.valueOf(evaluation));
			return party;
		}
	}

}
