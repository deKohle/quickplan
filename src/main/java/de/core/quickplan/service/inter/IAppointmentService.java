package de.core.quickplan.service.inter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import de.core.quickplan.domain.db.Appointment;
import de.core.quickplan.domain.dto.AppointmentDto;

/**
 * methods regarding appointments
 * 
 * @author Sebastian Kohler
 *
 */
public interface IAppointmentService {

	/**
	 * creates an new appointment
	 * @param app the appointment to save
	 * @return 
	 * @throws IllegalArgumentException if the appointment could not get created<br>
	 * (because it may already exist)
	 */
	public AppointmentDto create(AppointmentDto app) throws IllegalArgumentException;
	/**
	 * updates an appointment
	 * @param app the appointment to update
	 * @return 
	 * @throws IllegalArgumentException if the appointment does not exist or another error happened<br>
	 */
	public AppointmentDto update(AppointmentDto app) throws IllegalArgumentException;
	/**
	 * deletes the object behind the given uuid
	 * @param uuid the uuid as an string
	 * @throws IllegalArgumentException if the object could not get deleted
	 */
	public void delete(String uuid) throws IllegalArgumentException;
	/**
	 * loads all appointments inside this time frame
	 * @param start
	 * @param end
	 * @return the appointments for display
	 */
	public List<Appointment> find(LocalDateTime start, LocalDateTime end);
	/**
	 * reads the appointment
	 * @param uuid
	 * @return the appointment of the uuid
	 */
	public AppointmentDto read(String uuid);
	/**
	 * loads all appointments inside this time frame
	 * @param start
	 * @param end
	 * @return the appointments for display
	 */
	public List<Appointment> find(Timestamp start, Timestamp end);
}
