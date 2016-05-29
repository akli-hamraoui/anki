package com.smartholiday.team.gaming.anki.main;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smartholiday.team.gaming.anki.service.impl.AnkiGameService;
import com.smartholiday.team.gaming.anki.service.interfaces.IAnkiGameService;

public class AnkiGameMan {

	static Logger logger = LoggerFactory.getLogger(AnkiGameMan.class);

	public static void main(String[] args) {
		if (args != null && args.length != 0) {
			IAnkiGameService ankiGameService = new AnkiGameService();
			File inputFile = new File(args[0]);
			String ankiResult = ankiGameService.getAnkiResult(inputFile);
			String displayInput;
			try {
				displayInput = FileUtils.readFileToString(inputFile);
				logger.info("#####################################################################");
				logger.info("Input : \n" + displayInput);
				logger.info("#####################################################################");
				logger.info("Output : \n" + ankiResult);
				logger.info("#####################################################################");
			} catch (IOException e) {
				logger.error("Failed to run Anki Game because of : " + e.getMessage());
			}
		} else {
			logger.info("Please add as argument the input file path.");
		}
	}

}
