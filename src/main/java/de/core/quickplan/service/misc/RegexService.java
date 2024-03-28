package de.core.quickplan.service.misc;

import java.util.regex.Matcher;

/**
 * used for working with regular expressions
 * 
 * @author Sebastian Kohler
 *
 */
public class RegexService {

	/**
	 * escapes this string to be used inside a regEx without having to worry about special characters
	 * @param string
	 * @return
	 */
	public static String escape(String string) {
		return Matcher.quoteReplacement(string);
	}

}
