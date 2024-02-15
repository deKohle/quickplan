package de.core.quickplan.service.misc;

import java.sql.Timestamp;

/**
 * methods helping at working with time
 * 
 * @author Sebastian Kohler
 *
 */
public class TimeService {
	/**
	 * converts a string into an time-stamp<br>
	 * required form:<br>
	 *  yyyy-[m]m-[d]d hh:mm[:ss[.f...]]<br>
	 * @param time a string which could not be in the required form
	 * @return an Timestamp if everything went as expected
	 * @throws IllegalArgumentException if the string could not get converted
	 */
	public static Timestamp timestamp(String time) throws IllegalArgumentException
	{
		try {
			return Timestamp.valueOf(time);
		}
		catch(Exception e)
		{
			return Timestamp.valueOf(time+":00");
		}
	}
}
