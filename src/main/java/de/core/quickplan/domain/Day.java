package de.core.quickplan.domain;

/**
 * an day with appointments<br>
 * or without ;)<br>
 * 
 * @author Sebastian Kohler
 *
 */
public class Day {
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
	 * creates a new day, which can contain appointments
	 * @param dayOfWeek -> mostly Monday = 1 to Sunday = 7
	 * @param unfocused if this day should be displayed as an unimportant day<br>
	 * (because it was in another month)
	 */
	public Day(int dayOfWeek, int dayOfMonth, boolean unfocused) {
		super();
		this.dayOfWeek = dayOfWeek;
		this.dayOfMonth = dayOfMonth;
		this.unfocused = unfocused;
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
}
