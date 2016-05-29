package com.smartholiday.team.gaming.anki.service.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartholiday.team.gaming.anki.enums.EAnkiColor;
import com.smartholiday.team.gaming.anki.model.Card;
import com.smartholiday.team.gaming.anki.model.Party;
import com.smartholiday.team.gaming.anki.service.interfaces.IAnkiGameService;

/**
 * @author Akli HAMRAOUI Unit test for Anki Game
 *
 */
public class AnkiGameServiceTest {

	Logger logger = LoggerFactory.getLogger(getClass());
	IAnkiGameService ankiGameService;
	final static String inputDataPath = "src/test/resources/input.dat";
	final static String outputDataPath = "src/test/resources/output.dat";
	File inputFile;
	File outputFile;
	Card aCard;

	@Before
	public void setUp() throws Exception {
		ankiGameService = new AnkiGameService();
		inputFile = new File(inputDataPath);
		outputFile = new File(outputDataPath);
		aCard = buildaCard();
	}

	@Test
	public void testGetAnkiResult() throws Exception {
		String stringOutput = ankiGameService.getAnkiResult(inputFile);

		String displayInput = FileUtils.readFileToString(inputFile);
		logger.info("Input : \n" + displayInput);
		logger.info("#####################################################################");
		logger.info("Output : \n" + stringOutput);

		String expectedContent = FileUtils.readFileToString(outputFile);
		assertEquals(expectedContent.trim(), stringOutput.trim());
	}

	@Test
	public void testGetCurrentKnowledge() throws Exception {
		Party party = new Party();
		party.setCard(aCard);
		party.setEvaluation(EAnkiColor.GREEN);
		int kca = ankiGameService.getCurrentKnowledge(party);
		assertEquals(3, kca);
	}

	@Test
	public void testGetNextSession() throws Exception {
		Party party = new Party();
		party.setCard(aCard);
		party.setEvaluation(EAnkiColor.GREEN);
		int kca = ankiGameService.getCurrentKnowledge(party);
		aCard.setKnowledge(kca);
		int nsa = ankiGameService.getNextSession(party);
		assertEquals(3, nsa);
	}

	@Test
	public void testBuildParty() throws Exception {
		String qes = "(A) What is the first name of Dicaprio ?";
		String ans = "Leonardo";
		int ses = 1;
		String eval = "GREEN";
		String lineCard = "" + ses + "       | " + qes + " | " + ans + "        | " + eval + "";
		Party party = ankiGameService.buildParty(lineCard, new HashMap<String, Card>());
		Card card = party.getCard();
		assertEquals(card.getQuestion().trim(), qes.trim());
		assertEquals(card.getAnswer().trim(), ans.trim());
		assertEquals(card.getSession(), ses);
		assertEquals(party.getEvaluation(), EAnkiColor.GREEN);
	}

	private Card buildaCard() {
		Card aCard = new Card();
		aCard.setQuestion("Is TDD Fun ?");
		aCard.setAnswer("Yes");
		aCard.setSession(1);
		return aCard;
	}
}
