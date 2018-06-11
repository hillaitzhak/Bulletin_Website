package utils;

import org.owasp.esapi.ESAPI;

public class Xss {

		public static String cleanString(String fieldName, String input) {
			String safeOutput = ESAPI.encoder().encodeForHTML(input);
			return safeOutput;
	}
}
