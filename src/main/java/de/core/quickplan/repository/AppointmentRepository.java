package de.core.quickplan.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import de.core.quickplan.domain.db.Appointment;

@Repository
public interface AppointmentRepository extends DefaultRepository<Appointment,UUID> {

}
