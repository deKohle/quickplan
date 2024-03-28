package de.core.quickplan.domain;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import de.core.quickplan.domain.db.Appointment;

/**
 * an month-object prepared for display
 * 
 * @author Sebastian Kohler
 *
 */
public class Month {
	/**
	 * if the counting of days starts at 0, 1 or even another number
	 */
	private static final int FIRST_DAY = 1;
	/**
	 * used to save the month-name and year
	 */
	private YearMonth month;
	/**
	 * the weeks of this month
	 */
	private List<Week> weeks;
	/**
	 * creates a new month from the given local-date<br>
	 * ->this could need reworking if other calendar-systems are required 
	 * @param day
	 */
	public Month(LocalDate day)
	{
		this(YearMonth.of(day.getYear(), day.getMonth()));
	}
	/**
	 * @param year
	 * @param month
	 * @throws DateTimeException if the parameters are invalid
	 */
	public Month(int year, int month) throws DateTimeException
	{
		this(YearMonth.of(year, month));
	}
	/**
	 * creates a month by the given month
	 * @param year
	 */
	private Month(YearMonth year)
	{
		super();
		month = year;
		weeks = new ArrayList<Week>();
		LocalDate firstDay = month.atDay(FIRST_DAY);
		Week firstWeek = Week.starts(firstDay.getDayOfWeek(),year.getMonthValue(),year.getYear());
		weeks.add(firstWeek);
		int currentDay = firstWeek.getDaysInsideMonth() + 1;
		for(;(currentDay+firstWeek.getWeekLength())<month.lengthOfMonth();currentDay+=firstWeek.getWeekLength())
		{
			weeks.add(Week.whole(currentDay,year.getMonthValue(),year.getYear()));
		}
		Week lastWeek = Week.ends(month.atEndOfMonth().getDayOfWeek(),currentDay,year.getMonthValue(),year.getYear());
		weeks.add(lastWeek);
	}
	/**
	 * @return the first moment of this month
	 */
	public LocalDateTime start() {
		return month.atDay(FIRST_DAY).atStartOfDay();
	}
	/**
	 * @return the last moment of this month
	 */
	public LocalDateTime end() {
		return month.atEndOfMonth().atTime(LocalTime.MAX);
	}
	/**
	 * @return the year number of the next month
	 */
	public int getNextMonthYear()
	{
		return month.plusMonths(1).getYear();
	}
	/**
	 * @return the year number of the previous month
	 */
	public int getPreviousMonthYear()
	{
		return month.minusMonths(1).getYear();
	}
	/**
	 * @return the month number of the next month
	 */
	public int getNextMonth()
	{
		return month.plusMonths(1).getMonthValue();
	}
	/**
	 * @return the month number of the previous month
	 */
	public int getPreviousMonth()
	{
		return month.minusMonths(1).getMonthValue();
	}
	/**
	 * @return the year this month is inside
	 */
	public int getYear()
	{
		return month.getYear();
	}
	/**
	 * @return the number of the month (May = 5)
	 */
	public int getMonth()
	{
		return month.getMonthValue();
	}
	public String getMonthNumberAsString()
	{
		int monthValue = month.getMonthValue();
		if(monthValue<10)
		{
			return "0"+monthValue;
		}
		return monthValue+"";
	}
	/**
	 * @return the string to get the text from the message.properties
	 */
	public String getMonthI18n()
	{
		return "month."+month.getMonth().name();
	}
	/**
	 * @return all weeks inside this month
	 */
	public List<Week> getWeeks() {
		return weeks;
	}
	/**
	 * used to add the appointments to the days
	 * @param dates
	 */
	public void addAppointments(List<Appointment> dates) {
		for(Week week : weeks)
		{
			week.addAppointments(dates);
		}
	}
	
	@Override
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		s.append(getYear());
		s.append(" - "+month.getMonth().name()+"\n");
		for(Week week : weeks)
		{
			for(Day day : week.getDays())
			{
				if(day.isUnfocused())
				{
					s.append(" ");
				}
				else
				{
					s.append(day.getDayOfMonth());
				}
				s.append("\t");
			}
			s.append("\n");
		}
		return s.toString();
	}
}
