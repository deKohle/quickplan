package de.core.quickplan.domain.db;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.UUID;

import de.core.quickplan.constants.DbConstants;
import de.core.quickplan.domain.dto.AppointmentDto;
import de.core.quickplan.service.misc.TimeService;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * an appointment as saved inside the database
 * 
 * @author Sebastian Kohler
 *
 */
@Entity
@Table(name = "appointment")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID identifier;
	/**
	 * the moment this appointment was created on the server-side
	 */
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp createdAt;
	/**
	 * the starting-time
	 */
	@Column(name = "begin")
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp begin;
	/**
	 * the end-time
	 */
	@Column(name = "end")
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp end;
	/**
	 * the appointment includes only whole days<br>
	 * not needed, but allows for a different treatment of appointments from 00:00 to 24:00 and whole-day appointments
	 */
	@Column(name = "whole_day")
	private boolean wholeDay;
	/**
	 * the text describing the appointment
	 */
	@Column(name = "description", length = DbConstants.DEFAULT_TEXT_LENGTH)
	private String description;
	
	/**
	 * only used for creating a new appointment
	 * @param app
	 */
	public Appointment(AppointmentDto app) {
		super();
		this.identifier = null;
		this.createdAt = TimeService.now();
		this.edit(app);
	}
	/**
	 * changes all the fields that are allowed to get changed
	 * @param app
	 */
	public void edit(AppointmentDto app) {
		if(app.getBegin() == null || app.getBegin().isBlank())
		{
			this.begin = Timestamp.valueOf(TimeService.date(app.getDate()).atStartOfDay());
			this.wholeDay = true;
		}
		else
		{
			this.begin = TimeService.timestamp(app.getBegin());
			this.wholeDay = false;
		}
		if(app.getEnd() == null || app.getEnd().isBlank())
		{
			this.end = Timestamp.valueOf(TimeService.date(app.getDate()).atTime(LocalTime.MAX));
			this.wholeDay = true;
		}
		else
		{
			this.end = TimeService.timestamp(app.getBegin());
			this.wholeDay = false;
		}
		this.description = app.getDescription();
	}
	
	public UUID getIdentifier() {
		return identifier;
	}
	public void setIdentifier(UUID identifier) {
		this.identifier = identifier;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getBegin() {
		return begin;
	}
	public void setBegin(Timestamp begin) {
		this.begin = begin;
	}
	public Timestamp getEnd() {
		return end;
	}
	public void setEnd(Timestamp end) {
		this.end = end;
	}
	public boolean isWholeDay() {
		return wholeDay;
	}
	public void setWholeDay(boolean wholeDay) {
		this.wholeDay = wholeDay;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
