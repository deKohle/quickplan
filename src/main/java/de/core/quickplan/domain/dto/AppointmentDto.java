package de.core.quickplan.domain.dto;

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
	 * the time this appointment begins<br>
	 * -> this gets converted to an time-stamp
	 */
	@Timestamp(message=)
	public String begin;
	/**
	 * the time this appointment ends<br>
	 * -> this gets converted to an time-stamp
	 */
	@Timestamp(message=)
	public String end;
	/**
	 * a description of this appointment
	 */
	@NotBlank(message=)
	@Size(max=,message=)
	public String description;
	
	public AppointmentDto()
	{
		super();
	}

	public AppointmentDto(String begin, String end, String description) {
		super();
		this.begin = begin;
		this.end = end;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
