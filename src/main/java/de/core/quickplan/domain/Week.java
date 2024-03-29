package de.core.quickplan.domain;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import de.core.quickplan.domain.db.Appointment;

/**
 * an object, which typically has 7 days
 * 
 * @author Sebastian Kohler
 *
 */
public class Week {
	/**
	 * if the counting of days starts at 0, 1 or even another number
	 */
	private static final int FIRST_DAY = 1;
	/**
	 * the length of this week -> mostly seven
	 */
	private static final int LENGTH = DayOfWeek.values().length;
	/**
	 * all days before this day are unfocused<br>
	 * used to mark an old month
	 */
	private int firstRealDay;
	/**
	 * all days after this day are unfocused<br>
	 * used to mark a new month
	 */
	private int lastRealDay;
	/**
	 * the days inside this week
	 */
	private List<Day> days;

	/**
	 * private way for creating an week
	 * @param firstRealDay the first accepted day
	 * @param lastRealDay the last accepted day
	 * @param monthDay the day of the month this week starts (starts with 1)
	 * @param month
	 * @param year
	 */
	private Week(int firstRealDay, int lastRealDay, int monthDay, int month, int year) {
		super();
		this.firstRealDay = firstRealDay;
		this.lastRealDay = lastRealDay;
		days = new ArrayList<Day>();
		for(int i = FIRST_DAY; i <= LENGTH; ++i)
		{
			days.add(new Day(i,monthDay+(i - firstRealDay),(i < firstRealDay || i > lastRealDay),month,year));
		}
	}

	/**
	 * creates the first week of a month
	 * @param dayOfWeek the first day of the week
	 * @param month
	 * @param year
	 * @return
	 */
	public static Week starts(DayOfWeek dayOfWeek, int month, int year) {
		return new Week(dayOfWeek.getValue(),LENGTH, FIRST_DAY,month,year);
	}
	/**
	 * creates the last week of a month
	 * @param dayOfWeek the last day of the week
	 * @param dayOfMonth the day of month this week starts
	 * @param month
	 * @param year
	 * @return
	 */
	public static Week ends(DayOfWeek dayOfWeek, int dayOfMonth, int month, int year) {
		return new Week(FIRST_DAY,dayOfWeek.getValue(),dayOfMonth,month,year);
	}
	/**
	 * creates an week of a month with no unfocused days
	 * @param dayOfMonth the day of month this week starts
	 * @param month
	 * @param year
	 * @return
	 */
	public static Week whole(int dayOfMonth, int month, int year) {
		return new Week(FIRST_DAY,LENGTH, dayOfMonth,month,year);
	}
	/**
	 * how many days of this week are focused,<br> 
	 * because they are inside the range of firstRealDay to lastRealDay
	 * @return the number
	 */
	public int getDaysInsideMonth()
	{
		return lastRealDay - firstRealDay + FIRST_DAY;
	}
	/**
	 * @return the length of an typical/this week
	 */
	public int getWeekLength()
	{
		return LENGTH;
	}

	public int getFirstRealDay() {
		return firstRealDay;
	}

	public void setFirstRealDay(int firstRealDay) {
		this.firstRealDay = firstRealDay;
	}

	public int getLastRealDay() {
		return lastRealDay;
	}

	public void setLastRealDay(int lastRealDay) {
		this.lastRealDay = lastRealDay;
	}

	public List<Day> getDays() {
		return days;
	}

	public void setDays(List<Day> days) {
		this.days = days;
	}

	/**
	 * used to add the appointments to the days
	 * @param dates
	 */
	public void addAppointments(List<Appointment> dates) {
		for(Day day : days)
		{
			day.addAppointments(dates);
		}
	}
}
