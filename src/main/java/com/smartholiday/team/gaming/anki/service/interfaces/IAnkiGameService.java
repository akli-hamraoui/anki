package com.smartholiday.team.gaming.anki.service.interfaces;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.smartholiday.team.gaming.anki.enums.EAnkiColor;
import com.smartholiday.team.gaming.anki.model.Card;
import com.smartholiday.team.gaming.anki.model.Party;

/**
 * @author Akli HAMRAOUI
 * Expose business interfaces of Anki Game
 *
 */
public interface IAnkiGameService {

	/**
	 * @param file the input data
	 * @return the output result as String
	 */
	String getAnkiResult(File file);
	
	/**
	 * @param party the Anki party
	 * @return the current knowledge of the card
	 */
	int getCurrentKnowledge(Party party);
	
	/**
	 * @param party the Anki party
	 * @return the current session of the card
	 */
	int getNextSession(Party party);
	
	/**
	 * @param line
	 * @param cardMap
	 * @return build a part from the string line and update card
	 */
	Party buildParty(String line, Map<String, Card> cardMap);
}
