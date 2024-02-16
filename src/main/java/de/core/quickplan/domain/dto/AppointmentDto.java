package de.core.quickplan.domain.dto;

import de.core.quickplan.constants.DbConstants;
import de.core.quickplan.validation.Day;
import de.core.quickplan.validation.Timestamp;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * an appointment showed inside the calendar
 * 
 * @author Sebastian Kohler
 *
 */
public class AppointmentDto {
	/**
	 * the UUID-identifier of the object as an string
	 */
	public String identifier;
	/**
	 * the time this appointment begins<br>
	 * -> this gets converted to an time-stamp
	 */
	@Timestamp
	public String begin;
	/**
	 * the time this appointment ends<br>
	 * -> this gets converted to an time-stamp
	 */
	@Timestamp
	public String end;
	/**
	 * can be used instead of begin and end to mark an appointment for the whole day
	 */
	@Day
	public String date;
	/**
	 * a description of this appointment
	 */
	@NotBlank(message="error.description.blank")
	@Size(max=DbConstants.DEFAULT_TEXT_LENGTH,message="error.description.length")
	public String description;
	
	public AppointmentDto()
	{
		super();
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
