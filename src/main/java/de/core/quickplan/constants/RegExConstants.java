package de.core.quickplan.constants;

/**
 * constant regular expression strings 
 * 
 * @author Sebastian Kohler
 *
 */
public class RegExConstants {
	/**
	 * if an string is in the required form for an time-stamp-string<br>
	 * currently not completely working as intended<br> 
	 * allowed forms:<br>
	 *  yyyy-MM-dd hh:mm<br>
	 *  yyyy-MM-dd<br>
	 */
	public static final String TIMESTAMP_WITH_MINUTE = "^[0-9]{1,4}-[0-9]{1,2}-[0-9]{1,2}( [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2})?";
}
