package com.smartholiday.team.gaming.anki.model;

/**
 * @author Akli HAMRAOUI
 * Card model used for Anki Game
 *
 */
public class Card {

	int knowledge;
	int session;
	String question;
	String answer;
	Integer md5HexaKey;
	
	
	/**
	 * @return the card knowledge
	 */
	public int getKnowledge() {
		return knowledge;
	}
	
	/**
	 * @param knowledge
	 */
	public void setKnowledge(int knowledge) {
		this.knowledge = knowledge;
	}
	
	/**
	 * @return the card session
	 */
	public int getSession() {
		return session;
	}
	
	/**
	 * @param session
	 */
	public void setSession(int session) {
		this.session = session;
	}
	
	/**
	 * @return the card question
	 */
	public String getQuestion() {
		return question;
	}
	
	/**
	 * @param question
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	
	/**
	 * @return the card answer
	 */
	public String getAnswer() {
		return answer;
	}
	
	/**
	 * @param answer
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * @return the unique key represented by the question
	 */
	public Integer getMd5HexaKey() {
		return md5HexaKey;
	}

	/**
	 * @param md5HexaKey
	 */
	public void setMd5HexaKey(Integer md5HexaKey) {
		this.md5HexaKey = md5HexaKey;
	}
	
}
