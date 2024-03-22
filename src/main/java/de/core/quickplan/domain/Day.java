package de.core.quickplan.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import de.core.quickplan.domain.db.Appointment;
import de.core.quickplan.domain.dto.AppointmentDto;
import de.core.quickplan.service.misc.TimeService;

/**
 * an day with appointments<br>
 * or without ;)<br>
 * 
 * @author Sebastian Kohler
 *
 */
public class Day {
	/**
	 * the day as an object
	 */
	private LocalDate thisDay;
	/**
	 * the day of the week, mostly from 1 to 7
	 */
	private int dayOfWeek;
	/**
	 * if 0, this day is part of another month<br>
	 * should try to never be 0
	 */
	private int dayOfMonth;
	/**
	 * if this day is not so important in the current context
	 */
	private boolean unfocused;
	/**
	 * the dates on this day
	 */
	private List<AppointmentDto> dates;
	/**
	 * if the dates have been loaded for this day
	 */
	private boolean datesLoaded;
	/**
	 * creates a new day, which can contain appointments
	 * @param dayOfWeek -> mostly Monday = 1 to Sunday = 7
	 * @param dayOfMonth -> from 1 to 31 the day of the month
	 * @param unfocused if this day should be displayed as an unimportant day<br>
	 * (because it was in another month)
	 * @param month
	 * @param year
	 */
	public Day(int dayOfWeek, int dayOfMonth, boolean unfocused, int month, int year) {
		super();
		this.dayOfWeek = dayOfWeek;
		this.dayOfMonth = dayOfMonth;
		this.unfocused = unfocused;
		this.dates = new ArrayList<AppointmentDto>();
		this.datesLoaded = false;
		this.thisDay = LocalDate.of(1970, 1, 1);
		if(dayOfMonth > 0 && !unfocused)
		{
			this.thisDay = LocalDate.of(year, month, dayOfMonth);
		}
	}
	public Object getDayOfMonth() {
		return dayOfMonth;
	}
	public int getDayOfWeek() {
		return dayOfWeek;
	}
	public boolean isUnfocused() {
		return unfocused;
	}
	public List<AppointmentDto> getDates() {
		if(!datesLoaded)
		{
			throw new IllegalStateException("the dates were not loaded");
		}
		return dates;
	}
	public boolean isDatesLoaded() {
		return datesLoaded;
	}
	/**
	 * used to add the appointments to this day
	 * @param myDates
	 */
	public void addAppointments(List<Appointment> myDates) {
		for(Appointment date : myDates)
		{
			if(TimeService.inside(date,thisDay.atStartOfDay(),thisDay.atTime(LocalTime.MAX)))
			{
				dates.add(new AppointmentDto(date));
			}
		}
		this.datesLoaded = true;
	}
}
