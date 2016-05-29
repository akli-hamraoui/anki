package com.smartholiday.team.gaming.anki.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Akli HAMRAOUI Used to generate the md5 Hexa value
 *
 */
public class Md5HexaGenerator {

	static Logger logger = LoggerFactory.getLogger(Md5HexaGenerator.class);


	public static String hashString(final String message) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] hashedBytes = digest.digest(message.trim().getBytes("UTF-8"));

			return convertByteArrayToHexString(hashedBytes);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
			logger.error("Could not generate hash from String because of : " + ex);
			return null;
		}
	}

	private static String convertByteArrayToHexString(byte[] arrayBytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < arrayBytes.length; i++) {
			stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuffer.toString();
	}

}
