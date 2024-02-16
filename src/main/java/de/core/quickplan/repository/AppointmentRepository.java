package de.core.quickplan.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import de.core.quickplan.domain.db.Appointment;

@Repository
public interface AppointmentRepository extends DefaultRepository<Appointment,UUID> {

	@Query("SELECT a FROM Appointment a WHERE a.begin < :end AND a.end > :start")
	List<Appointment> findAll(@Param("start") Timestamp start, @Param("end") Timestamp end);

}
