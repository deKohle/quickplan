package de.core.quickplan.service.misc;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import de.core.quickplan.domain.db.Appointment;

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

	/**
	 * converts a string to an local-date<br>
	 * required form:<br>
	 *  yyyy-mm-dd<br>
	 * @param value
	 * @return an local-date if everything went as expected
	 * @throws DateTimeParseException if the string could not get converted
	 */
	public static LocalDate date(String value) throws DateTimeParseException
	{
		return LocalDate.parse(value);
	}

	/**
	 * get the current time
	 * ->e.g. used to set the time last logged in
	 * @return
	 */
	public static Timestamp now() {
		return Timestamp.from(Instant.now());
	}

	/**
	 * checks if this appointment falls inside the time span
	 * @param date an appointment to check if it is inside that time frame
	 * @param start of the time frame
	 * @param end of the time frame
	 */
	public static boolean inside(Appointment date, LocalDateTime start, LocalDateTime end) {
		return date.getBegin().before(Timestamp.valueOf(end)) && date.getEnd().after(Timestamp.valueOf(start));
	}
}
