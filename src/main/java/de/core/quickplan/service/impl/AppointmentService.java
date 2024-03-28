package de.core.quickplan.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.core.quickplan.domain.db.Appointment;
import de.core.quickplan.domain.dto.AppointmentDto;
import de.core.quickplan.repository.AppointmentRepository;
import de.core.quickplan.service.inter.IAppointmentService;

/**
 * implements all methods regarding appointments
 * 
 * @author Sebastian Kohler
 *
 */
@Service
public class AppointmentService implements IAppointmentService {
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(AppointmentService.class);
	
	@Autowired
	private AppointmentRepository dateRepo;
	
	@Override
	public AppointmentDto create(AppointmentDto app) throws IllegalArgumentException {
		if(app.getIdentifier() != null && !app.getIdentifier().isBlank()) 
		{
			throw new IllegalArgumentException("an identifier was given, but it tries to create a new object");
		}
		return new AppointmentDto(dateRepo.save(new Appointment(app)));
	}

	@Override
	public AppointmentDto update(AppointmentDto app) throws IllegalArgumentException {
		Appointment old = getAppointment(app.getIdentifier());
		old.edit(app);
		return new AppointmentDto(dateRepo.save(old));
	}

	/**
	 * loads an appointment by its uuid
	 * @param identifier the uuid as an string
	 * @return the loaded appointment
	 * @throws IllegalArgumentException if the object could not get loaded
	 */
	private Appointment getAppointment(String identifier) throws IllegalArgumentException 
	{
		try {
			return dateRepo.findById(UUID.fromString(identifier)).get();
		}
		catch(NoSuchElementException e)
		{
			throw new IllegalArgumentException("requested object does not exist");
		}
	}

	@Override
	public void delete(String uuid) throws IllegalArgumentException {
		dateRepo.deleteById(UUID.fromString(uuid));
	}

	/*@Override
	public List<Appointment> find(String start, String end) {
		return dateRepo.findAll(start, end);
	}*/

	@Override
	public AppointmentDto read(String uuid) {
		return new AppointmentDto(dateRepo.findById(UUID.fromString(uuid)).get());
	}
	
	@Override
	public List<Appointment> find(LocalDateTime start, LocalDateTime end) {
		return dateRepo.findAll(Timestamp.valueOf(start), Timestamp.valueOf(end));
	}
	@Override
	public List<Appointment> find(Timestamp start, Timestamp end) {
		return dateRepo.findAll(start, end);
	}

}
